package com.labwinner.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceVideo;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.SysUser;
import com.labwinner.service.*;
import com.labwinner.util.Base64Util;
import com.labwinner.util.FileUtil;
import com.labwinner.util.MergeUtil;
import com.labwinner.util.PdfUtil;
import com.labwinner.util.QRCreateAndParse;
import com.labwinner.util.videoToImage;
import com.labwinner.vo.DevicePdfVo;
import com.labwinner.vo.DeviceVo;
import com.labwinner.vo.VideoVO;

/**
 * 设备Controller
 * 
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午6:05:23
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class DeviceVideoController {
	private static Logger logger = LoggerFactory
			.getLogger(DeviceVideoController.class);

	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceVideoService deviceVideoService;
	
	@Autowired
	private SysUserService sysUserService;

	@Value("${deviceVideo.upload-path}")
	String filePath;

	@Value("${deviceVideo.url-path}")
	String urlPath;
	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/deviceVideo", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		List<DeviceVideo> list = deviceVideoService.getAll();
		if(list!=null && list.size()>0){
			for (DeviceVideo deviceVideo : list) {
				deviceVideo.setVideo(urlPath+deviceVideo.getVideo());
				}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(list);
			return resultVo;
			}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("list is null");
			return resultVo;
		}
	/**
	 * 根据设备id获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/getDeviceVideoByDeviceId/{id}", method = RequestMethod.GET)
	public ResultVo getAllByDeviceId(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List<DeviceVideo> list = deviceVideoService.getAllByDeviceId(id);
		if(list!=null && list.size()>0){
				for (DeviceVideo deviceVideo : list) {
					deviceVideo.setVideo(urlPath+deviceVideo.getVideo());
					deviceVideo.setVideoPic(urlPath+deviceVideo.getVideoPic());
					}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(list);
			return resultVo;
		}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("list is null");
			return resultVo;
	}

	/**
	 * @Description 根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/deviceVideoPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable String keyword) {
		
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<DeviceVideo> deviceVideos = deviceVideoService.getAllPageableByKeyword(keyword);
			if(deviceVideos!=null && deviceVideos.size()>0){
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(deviceVideos));
				return resultVo;
				}
		} else {
			List<DeviceVideo> deviceVideos = deviceVideoService.getAll();
			if(deviceVideos!=null && deviceVideos.size()>0){
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(deviceVideos));
				return resultVo;
				}
		}
		return resultVo;
	}

	/**
	 * 根据主键获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/deviceVideo/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

		DeviceVideo deviceVideo = deviceVideoService.getById(id);
		if (deviceVideo == null) {
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			return resultVo;

		}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find null");
			resultVo.setResultData(null);
			return resultVo;
	}

	/**
	 * 保存对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/deviceVideo", method = RequestMethod.POST)
	public ResultVo save(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "deviceId", required = false) Integer deviceId,
			@RequestParam(value = "video", required = false) String video,
			@RequestParam(value = "videoRemark", required = false) String videoRemark) {
		
			ResultVo resultVo = new ResultVo();
		//	Base64Util base64Util=new Base64Util();
			FileUtil fileUtil=new FileUtil();
			DeviceVideo deviceVideo = new DeviceVideo();
			try {
				if(file!=null && !"".equals(file)){
					String fileName=fileUtil.uploadVideoFile(file, filePath);
					deviceVideo.setVideo(fileName);
					videoToImage vti=new videoToImage();
					String picName=UUID.randomUUID().toString()+".jpg";
					vti.fetchFrame(filePath+fileName,filePath+picName);
					deviceVideo.setVideoPic(picName);
				}
				if(deviceId!=null){
					Device device=deviceService.getById(deviceId);
					if(device!=null){
						deviceVideo.setDevice(device);	
					}
				}
				if(videoRemark!=null&&!"".equals(videoRemark)){
					deviceVideo.setVideoRemark(videoRemark);
				}
				deviceVideo.setUploadTime(new Date());
				deviceVideoService.save(deviceVideo);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save success");
				return resultVo;
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("save fail");
				return resultVo;
			}
			
			
	}
	
	
	
	/**
	 * 保存对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/deviceVideo/webSave", method = RequestMethod.POST)
	public ResultVo webSave(@RequestBody VideoVO videoVO) {
		
		String fileStr = videoVO.getFileStr();
		Integer deviceId = videoVO.getDeviceId();
		String video = videoVO.getVideo();
		String videoRemark = videoVO.getVideoRemark();
		
			ResultVo resultVo = new ResultVo();
			DeviceVideo deviceVideo = new DeviceVideo();
			try {
				if(fileStr!=null && !"".equals(fileStr)){
					// base64保存图片
					Base64Util base64Util=new Base64Util();
					Device device = new Device();
					device.setDeviceId(deviceId);
					fileStr = fileStr.substring(fileStr.indexOf(",") + 1);
					deviceVideo.setDevice(device);
					deviceVideo.setVideoRemark(videoRemark);
					deviceVideo.setUploadTime(new Date());
					video = base64Util.GenerateVideo(fileStr, filePath, video);
					deviceVideo.setVideo(video);
					videoToImage vti=new videoToImage();
					String picName=UUID.randomUUID().toString()+".jpg";
					vti.fetchFrame(filePath+video,filePath+picName);
					deviceVideo.setVideoPic(picName);
					
					deviceVideoService.save(deviceVideo);
				}
				deviceVideo.setVideoPic(urlPath+deviceVideo.getVideoPic());
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save success");
				resultVo.setResultData(deviceVideo);
				return resultVo;
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("save fail");
				return resultVo;
			}
			
			
	}
	

	/**
	 * 更新对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	@RequestMapping(value = "/deviceVideo", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody @RequestParam(value = "file", required = false) MultipartFile file ,
			@RequestParam(value = "deviceId", required = false) Integer deviceId,
			@RequestParam(value = "video", required = false) String video,
			@RequestParam(value = "videoRemark", required = false) String videoRemark) {
		

		    ResultVo resultVo = new ResultVo();
		try {
		//	DeviceVideo deviceVideo = deviceVideoService.getById(deviceId);
			DeviceVideo deviceVideo=new DeviceVideo();
			Device device=deviceService.getById(deviceId);
			
				if ((file != null && !"".equals(file))){ // 新增
				// 删除旧视频
					String fileName=deviceVideo.getVideo();
						fileName=fileName.substring(fileName.lastIndexOf("/")+1);
						new File(filePath + fileName).delete();
						// 删除数据库中视频信息
						deviceVideoService.delete(deviceId);
					}
				// 保存上传的视频
				if(deviceId!=null){
					deviceVideo.setDevice(device);
				}
				deviceVideo.setUploadTime(new Date());
				deviceVideoService.save(deviceVideo);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update success");
				return resultVo;
		} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("update fail");
				return resultVo;
		}
	}
	
	/**
	 * 更新对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	@RequestMapping(value = "/deviceVideoUpdate", method = RequestMethod.POST)
	public ResultVo update(@RequestBody DeviceVideo deviceVideo) {
		

		    ResultVo resultVo = new ResultVo();
		try {
				deviceVideo.setUploadTime(new Date());
				deviceVideoService.update(deviceVideo);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update success");
				return resultVo;
		} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("update fail");
				return resultVo;
		}
	}
	
	
	/**
	 * 删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/deviceVideo/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
			try {
				DeviceVideo deviceVideo = deviceVideoService.getById(id);
				if(deviceVideo!=null){
					// 删除视频
					String fileName=deviceVideo.getVideo();
						fileName=fileName.substring(fileName.lastIndexOf("/")+1);
						new File(filePath + fileName).delete();	
					// 删除旧帧图片
					String picfileName=deviceVideo.getVideoPic();
					picfileName=picfileName.substring(picfileName.lastIndexOf("/")+1);
					new File(filePath + picfileName).delete();			
				}
				deviceVideoService.delete(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("删除成功！");
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("删除失败！");
			}
			return resultVo;
		}
	/**
	 * app删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/deviceVideoApp/{id}", method = RequestMethod.GET)
	public ResultVo deleteDeviceVideoApp(@PathVariable("id") Integer id) {
		
		ResultVo resultVo = new ResultVo();
		try {
			DeviceVideo deviceVideo = deviceVideoService.getById(id);
			if(deviceVideo!=null){
				// 删除旧视频
				String fileName=deviceVideo.getVideo();
				fileName=fileName.substring(fileName.lastIndexOf("/")+1);
				new File(filePath + fileName).delete();	
				// 删除旧帧图片
				String picfileName=deviceVideo.getVideoPic();
				picfileName=picfileName.substring(picfileName.lastIndexOf("/")+1);
				new File(filePath + picfileName).delete();	
			}
			deviceVideoService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("删除成功！");
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("删除失败！");
		}
		return resultVo;
	}
	
	
}
