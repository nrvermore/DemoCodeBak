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
import com.labwinner.common.ResultVo;
import com.labwinner.domain.AnalyticalAttachment;
import com.labwinner.domain.Analytics;
import com.labwinner.domain.AnalyticsDevice;
import com.labwinner.domain.Attachment;
import com.labwinner.domain.Device;
import com.labwinner.domain.Message;
import com.labwinner.domain.ReactionTest;
import com.labwinner.domain.SysUser;
import com.labwinner.domain.TestAttachment;
import com.labwinner.domain.TestDevice;
import com.labwinner.service.ReactionTestService;
import com.labwinner.service.SysUserService;
import com.labwinner.service.TestAttachementService;
import com.labwinner.service.TestDeviceService;
import com.labwinner.util.Base64Util;
import com.labwinner.vo.ReactionTestVo;

@RestController
public class ReactionTestController {

	private static Logger logger = LoggerFactory
			.getLogger(ReactionTestController.class);

	@Autowired
	private ReactionTestService reactionTestService;

	@Autowired
	private TestDeviceService testDeviceService;

	@Autowired
	private TestAttachementService testAttachmentService;

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
	@RequestMapping(value = "/reactionTest/getByReactionId/{id}", method = RequestMethod.GET)
	public ResultVo getByReactionId(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();

		List<ReactionTest> reactionTests = reactionTestService
				.getByReactionId(id);
		if (reactionTests == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(reactionTests);
		return resultVo;
	}

	/**
	 * @Description 根据关键字实验主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionTest/getByKeyword/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getByKeyword(@PathVariable("page") Integer page,
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
				List<ReactionTest> list = reactionTestService.getUserListByKeyword(keyword,userId);
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
				List<ReactionTest> list = reactionTestService.getByKeyword(keyword);
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
				List<ReactionTest> list = reactionTestService.getUserList(userId);
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
				List<ReactionTest> list = reactionTestService.getAll();
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
	 * @Description 根据实验主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionTest/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();

		ReactionTest reactionTests = reactionTestService.getById(id);
		if (reactionTests != null) {
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(reactionTests);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("result is null");
		return resultVo;
	}
	
	@RequestMapping(value = "/reactionTest/uploadImage", method = RequestMethod.POST)
	public ResultVo uploadImage(@RequestBody String img) {
		ResultVo resultVo = new ResultVo();
		// 保存上传图片
		
			if(img!=null){
			img = img.substring(img.indexOf(",") + 1);
			String filename = new Base64Util().GenerateImage(img,filePath);
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


	/**
	 * @Description 根据实验主键删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionTest/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		
		ReactionTest reactionTests = reactionTestService.getById(id);
		Integer creater = reactionTests.getCreater();
		
		if(userId-creater ==0){
			testDeviceService.delete(id);
			testAttachmentService.delete(id);
			reactionTestService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete success");
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("您无权删除");
		return resultVo;
	}

	/**
	 * @Description 获取全部对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionTest", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		List<ReactionTest> reactionTests = reactionTestService.getAll();
		if (reactionTests == null) {
			String message = "对象不存在";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(reactionTests);
		return resultVo;
	}

	/**
	 * @Description 新增实验测试
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionTest", method = RequestMethod.POST)
	public ResultVo save(@RequestBody ReactionTestVo reactionTestVo) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		
		ReactionTest reactionTest = reactionTestVo.getReactionTest();
		List<Integer> deviceList = reactionTestVo.getDeviceList();
		List<String> files = reactionTestVo.getFiles();
		List<String> filenames = reactionTestVo.getFilenames();
		reactionTest.setCreater(userId);
		reactionTest.setCreateDate(new Date());
		reactionTestService.save(reactionTest);

		for (Integer deviceId : deviceList) {
			Device device = new Device();
			device.setDeviceId(deviceId);
			TestDevice testDevice = new TestDevice();
			testDevice.setReactionTest(reactionTest);
			testDevice.setDevice(device);
			testDeviceService.save(testDevice);
		}

		// 保存上传文件
		if (files != null && files.size() > 0) {
			// 保存上传文件
			saveFile(reactionTest, files,filenames);
		}
			
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}
	
	/**
	 * @Description 新增实验测试
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionTest", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody ReactionTestVo reactionTestVo) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		
		ReactionTest reactionTest = reactionTestVo.getReactionTest();
		List<Integer> deviceList = reactionTestVo.getDeviceList();
		List<String> files = reactionTestVo.getFiles();
		List<String> filenames = reactionTestVo.getFilenames();
		List<String> urls = reactionTestVo.getUrls();
		reactionTest.setCreater(userId);
		reactionTest.setCreateDate(new Date());
		reactionTestService.update(reactionTest);

		if(deviceList!=null && deviceList.size()>0){
			testDeviceService.delete(reactionTest.getTestId());
			for (Integer deviceId : deviceList) {
				Device device = new Device();
				device.setDeviceId(deviceId);
				TestDevice testDevice = new TestDevice();
				testDevice.setReactionTest(reactionTest);
				testDevice.setDevice(device);
				testDeviceService.save(testDevice);
			}
		}

		// 保存上传文件
		if (files != null && files.size() > 0) {
			// 保存上传文件
			saveFile(reactionTest, files,filenames);
		}
		
		//删除要删的url图片
		if (null != urls && urls.size() > 0) {
			for (String url : urls) {
				new File(filePath+url.substring(url.lastIndexOf("/") + 1)).delete();
				testAttachmentService.deleteByUrl(url.substring(url.lastIndexOf("/") + 1));
			}
		}
			
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}


	@RequestMapping(value = "/testDevice/{id}", method = RequestMethod.GET)
	public List<TestDevice> getById2(@PathVariable("id") Integer id) {
		List<TestDevice> reactionTests = testDeviceService.getById(id);
		if (reactionTests == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return reactionTests;
	}

	@RequestMapping(value = "/testAttachment/{id}", method = RequestMethod.GET)
	public List<TestAttachment> getById3(@PathVariable("id") Integer id) {
		List<TestAttachment> attachments = testAttachmentService.getById(id);
		if (attachments == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return attachments;
	}
	
	/**
	 * @Description Web保存文件
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveFile(ReactionTest reactionTest, List<String> fileStrs,List<String> filenames) {

		// 保存上传图片
		if (fileStrs != null && fileStrs.size() > 0) {
			// base64保存图片
			Base64Util base64Util = new Base64Util();
			for (int i =0;i<fileStrs.size();i++) {
				TestAttachment attachment = new TestAttachment();
				String fileStr = fileStrs.get(i).substring(fileStrs.get(i).indexOf(",") + 1);
				String attacheName = base64Util.GenerateFile(fileStr,filePath,filenames.get(i));
				attachment.setTestAttachmentName(attacheName);
				attachment.setReactionTest(reactionTest);
				testAttachmentService.save(attachment);
			}
		}
	}

}
