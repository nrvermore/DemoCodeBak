package com.labwinner.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.AnalyticalAttachment;
import com.labwinner.domain.Analytics;
import com.labwinner.domain.AnalyticsDevice;
import com.labwinner.domain.Device;
import com.labwinner.domain.InventoryGroup;
import com.labwinner.domain.ProjectDocuments;
import com.labwinner.domain.ReactionStatus;
import com.labwinner.domain.ReactionTest;
import com.labwinner.domain.SysUser;
import com.labwinner.domain.TestAttachment;
import com.labwinner.service.AnalyticService;
import com.labwinner.service.AnalyticalAttachmentService;
import com.labwinner.service.AnalyticsDeviceService;
import com.labwinner.service.ReactionStatusService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.Base64Util;
import com.labwinner.vo.AnalyticsVo;

@RestController
public class AnalyticsController {

	private static Logger logger = LoggerFactory
			.getLogger(AnalyticsController.class);

	@Autowired
	private AnalyticService analyticService;
	@Autowired
	private AnalyticsDeviceService analyticsDeviceService;

	@Autowired
	private AnalyticalAttachmentService analyticalAttachmentService;

	@Autowired
	private ReactionStatusService reactionStatusService;

	@Autowired
	private SysUserService sysUserService;

	@Value("${reaction.upload-path}")
	String filePath;
	@Value("${reaction.url-path}")
	String urlPath;
	

	/**
	 * @Description 根据实验主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/analytics/getByReactionId/{id}", method = RequestMethod.GET)
	public ResultVo getByProcessId(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		List<Analytics> analyticses = analyticService.getByReactionId(id);

		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(analyticses);
		return resultVo;
	}

	/**
	 * @Description 根据关键字实验主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/analytics/getByKeyword/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getByProcessId(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {

		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				List<Analytics> list = analyticService.getUserListByKeyword(keyword,userId);
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			} else {
				List<Analytics> list = analyticService.getByKeyword(keyword);
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				List<Analytics> list = analyticService.getUserList(userId);
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			} else {
				List<Analytics> list = analyticService.getAll();
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;
			}
		}
		
		
	}

	/**
	 * @Description 根据分析主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/analytics/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		Analytics analyticses = analyticService.getById(id);
		List<AnalyticalAttachment> analyticalAttachments=analyticses.getAnalyticalAttachments();
		if (analyticalAttachments != null && analyticalAttachments.size() > 0) {
			for (AnalyticalAttachment analyticalAttachment : analyticalAttachments) {
					String fileName=analyticalAttachment.getAnaAttachmentName();
					String type=fileName.substring(fileName.lastIndexOf(".") + 1);
					String pdfUrl=null;
					if("pdf".equals(type)){
						pdfUrl=urlPath + fileName;
					}else{
						pdfUrl=analyticalAttachment.getPdfUrl();
					}
					analyticalAttachment.setAnaAttachmentContent(urlPath+fileName);
					analyticalAttachment.setPdfUrl(pdfUrl);
			}
		}
		if (analyticses != null) {
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(analyticses);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("result is null");
		return resultVo;
		
	}

	/**
	 * @Description 根据分析主键删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/analytics/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		Analytics analyticses = analyticService.getById(id);
		Integer creater = analyticses.getCreater();
		if(userId -creater==0){
			analyticsDeviceService.delete(id);
			analyticalAttachmentService.delete(id);
			analyticService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete success");
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("您无权删除");
		return resultVo;
	}

	/**
	 * @Description 新增分析
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/analytics", method = RequestMethod.POST)
	public ResultVo save(@RequestBody AnalyticsVo analyticsVo) {

		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();	
		
		Analytics analytics = analyticsVo.getAnalytics();
		List<Integer> deviceList = analyticsVo.getDeviceList();
		List<String> files = analyticsVo.getFiles();
		List<String> filenames = analyticsVo.getFilenames();
		analytics.setCreater(userId);
		analytics.setCreateDate(new Date());
		analyticService.save(analytics);

		for (Integer deviceId : deviceList) {
			Device device = new Device();
			device.setDeviceId(deviceId);
			AnalyticsDevice analyticsDevice = new AnalyticsDevice();
			analyticsDevice.setAnalytics(analytics);
			analyticsDevice.setDevice(device);
			analyticsDeviceService.save(analyticsDevice);
		}

		// 保存上传文件
		if (files != null && files.size() > 0) {
			// 保存上传文件
			saveFile(analytics, files,filenames);
		}

		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;

	}
	
	/**
	 * @Description 新增分析
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/analytics", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody AnalyticsVo analyticsVo) {

		ResultVo resultVo = new ResultVo();

		Analytics analytics = analyticsVo.getAnalytics();
		List<Integer> deviceList = analyticsVo.getDeviceList();
		List<String> files = analyticsVo.getFiles();
		List<String> filenames = analyticsVo.getFilenames();
		List<String> urls = analyticsVo.getUrls();
		analyticService.update(analytics);

		if(deviceList!=null && deviceList.size()>0){
			analyticsDeviceService.delete(analytics.getAnalyticsId());
			for (Integer deviceId : deviceList) {
				Device device = new Device();
				device.setDeviceId(deviceId);
				AnalyticsDevice analyticsDevice = new AnalyticsDevice();
				analyticsDevice.setAnalytics(analytics);
				analyticsDevice.setDevice(device);
				analyticsDeviceService.save(analyticsDevice);
			}
		}

		// 保存上传文件
		if (files != null && files.size() > 0) {
			// 保存上传文件
			saveFile(analytics, files,filenames);
		}
		//删除要删的url图片
		if (null != urls && urls.size() > 0) {
			for (String url : urls) {
				new File(filePath+url.substring(url.lastIndexOf("/") + 1)).delete();
				analyticalAttachmentService.deleteByUrl(url.substring(url.lastIndexOf("/") + 1));
			}
		}

		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;

	}
	
	
	@RequestMapping(value = "/analytics/uploadImage", method = RequestMethod.POST)
	public ResultVo uploadImage(@RequestBody String img) {
		ResultVo resultVo = new ResultVo();
		// 保存上传图片
		String filename = "";
		// 保存上传图片

		if (img != null) {
			String imgHead = img.substring(0, img.indexOf(","));
			img = img.substring(img.indexOf(",") + 1);
			if(imgHead.contains("tiff")){
				filename = new Base64Util().GenerateTiffImage(img, filePath);
			}else{
				filename = new Base64Util().GenerateImage(img, filePath);
			}
			String url = urlPath+filename;
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			resultVo.setResultData(url);
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("upload is null");
		return resultVo;
	}

	@RequestMapping(value = "/analyticsDevice/{id}", method = RequestMethod.GET)
	public List<AnalyticsDevice> getById2(@PathVariable("id") Integer id) {
		List<AnalyticsDevice> devices = analyticsDeviceService.getById(id);
		if (devices == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return devices;
	}

	@RequestMapping(value = "/analyticalAttachment/{id}", method = RequestMethod.GET)
	public List<AnalyticalAttachment> getById3(@PathVariable("id") Integer id) {
		List<AnalyticalAttachment> attachments = analyticalAttachmentService
				.getById(id);
		if (attachments == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}else{
			for (AnalyticalAttachment analyticalAttachment : attachments) {
					String fileName=analyticalAttachment.getAnaAttachmentName();
					String type=fileName.substring(fileName.lastIndexOf(".") + 1);
					String pdfUrl=null;
					if("pdf".equals(type)){
						pdfUrl=urlPath + fileName;
					}else{
						pdfUrl=analyticalAttachment.getPdfUrl();
					}
					analyticalAttachment.setAnaAttachmentName(urlPath+fileName);
					analyticalAttachment.setPdfUrl(pdfUrl);
			}
		}
		return attachments;
	}
	
	/**
	 * @Description Web保存文件
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveFile(Analytics analytics, List<String> fileStrs,List<String> filenames) {

		// 保存上传图片
		if (fileStrs != null && fileStrs.size() > 0) {
			// base64保存图片
			Base64Util base64Util = new Base64Util();
			for (int i =0;i<fileStrs.size();i++) {
				AnalyticalAttachment attachment = new AnalyticalAttachment();
				String fileStr = fileStrs.get(i).substring(fileStrs.get(i).indexOf(",") + 1);
				String attacheName = base64Util.GenerateFile(fileStr,filePath,filenames.get(i));
				String type=attacheName.substring(attacheName.lastIndexOf(".")+1, attacheName.length());
				if(LabConstans.CONVERSION_TYPE.contains(type)){
					attachment.setPdfCount(0);
					attachment.setConversionCount(0);
				}else if("pdf".equals(type)){
					attachment.setPdfCount(2);
				}else{
					attachment.setPdfCount(99);
				}
				attachment.setAnaAttachmentName(attacheName);
				attachment.setAnalytics(analytics);
				analyticalAttachmentService.save(attachment);
			}
		}
	}

}
