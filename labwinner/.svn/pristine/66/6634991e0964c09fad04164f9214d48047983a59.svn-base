package com.labwinner.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.DevicePicture;
import com.labwinner.domain.DeviceVideo;
import com.labwinner.domain.SysRoleMenu;
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
public class DeviceController {
	private static Logger logger = LoggerFactory
			.getLogger(DeviceController.class);

	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceVideoService deviceVideoService;

	@Autowired
	private DevicePictureService devicePictureService;

	@Autowired
	private DeviceAppointmentService deviceAppointmentService;
	
	@Autowired
	private DeviceLocationService deviceLocationService;
	
	@Autowired
	private SysUserService sysUserService;

	private DeviceAppointment deviceAppointment;
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	@Value("${device.upload-path}")
	String filePath;

	@Value("${device.url-path}")
	String urlPath;
	
	@Value("${web.qr-path}")
	String qrPath;
	@Value("${web.qrUrl-path}")
	private String qrUrlPath; 
	@Value("${web.upload_path_pdf}")
	private String pdfPath; 
	@Value("${web.agency_pdf}")
	private String pdfLine; 

	@Value("${deviceVideo.upload-path}")
	String videoFilePath;

	@Value("${deviceVideo.url-path}")
	String videoUrlPath;
	
	
	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/device", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		List<Device> list = deviceService.getAll();
		if(list!=null && list.size()>0){
			for (Device device2 : list) {
				DeviceLocation location = device2.getDeviceLocation();
				if(location!=null && !"0".equals(location.getDeviceLocaPid())){
					String pname = getDevicePname(location.getDeviceLocaId());
					location.setParentName(pname);
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(list);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("list is null");
		return resultVo;
	}

	/**
	 * @Description 根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/devicePageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable String keyword) {
		
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<Device> devices = deviceService.getAllPageable(keyword);
			if(devices!=null && devices.size()>0){
				for (Device device2 : devices) {
					DeviceLocation location = device2.getDeviceLocation();
					if(location!=null && !"0".equals(location.getDeviceLocaPid())){
						String pname = getDevicePname(location.getDeviceLocaId());
						location.setParentName(pname);
					}
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(devices));
			return resultVo;
		} else {
			List<Device> devices = deviceService.getAll();
			if(devices!=null && devices.size()>0){
				for (Device device2 : devices) {
					DeviceLocation location = device2.getDeviceLocation();
					if(location!=null && !"0".equals(location.getDeviceLocaPid())){
						String pname = getDevicePname(location.getDeviceLocaId());
						location.setParentName(pname);
					}
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(devices));
			return resultVo;
		}
	}
	/**
	 * @Description APP根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/devicePageableAPP/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageableAPP(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable String keyword) {
		
		ResultVo resultVo = new ResultVo();
		Integer endCount = page*pageSize;
		
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<Device> deviceList = deviceService.getByKeyword(keyword);
//			List<Device> devices = deviceService.getAllPageableAppByKeyword(0, endCount, keyword);
			if(deviceList!=null && deviceList.size()>0){
				for (Device device2 : deviceList) {
					DeviceLocation location = device2.getDeviceLocation();
					if(location!=null && !"0".equals(location.getDeviceLocaPid())){
						String pname = getDevicePname(location.getDeviceLocaId());
						location.setParentName(pname);
					}
				}
			}
			int total = deviceList.size();
			double c = (((double) total) /pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page);
			pageEntity.setPageSize(pageSize);
			pageEntity.setTotal(deviceList.size());
//			pageEntity.setList(devices);
			int num = deviceList.size() > page * pageSize ? page * pageSize: deviceList.size();
			if(page<=d){
				pageEntity.setList(deviceList.subList(0, num));
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(pageEntity);
			return resultVo;
		} else {
			List<Device> deviceList = deviceService.getAllDevices();
//			List<Device> devices = deviceService.getAllPageableApp(0, endCount);
			if(deviceList!=null && deviceList.size()>0){
				for (Device device2 : deviceList) {
					
					DeviceLocation location = device2.getDeviceLocation();
					if(location!=null && !"0".equals(location.getDeviceLocaPid())){
						String pname = getDevicePname(location.getDeviceLocaId());
						location.setParentName(pname);
					}
				}
			}
			int total = deviceList.size();
			double c = (((double) total) /pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page);
			pageEntity.setPageSize(pageSize);
			pageEntity.setTotal(deviceList.size());
//			pageEntity.setList(devices);
			int num = deviceList.size() > page * pageSize ? page * pageSize: deviceList.size();
			if(page<=d){
				pageEntity.setList(deviceList.subList(0, num));
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(pageEntity);
			return resultVo;
		}
	}

	/**
	 * 根据主键获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/device/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

		Device device = deviceService.getById(id);
		if (device == null) {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("device is null");
			return resultVo;

		}
		
		if(device.getQrName()!=null){
			if(!new File(qrPath+device.getQrName()).exists()){
				QRCreateAndParse qr = new QRCreateAndParse();
				qr.createQr(device.getBarCode(), qrPath,device.getQrName());
			}
			device.setQrName(qrUrlPath+device.getQrName());
		}
		
		DeviceLocation location = device.getDeviceLocation();
		if(location!=null && !"0".equals(location.getDeviceLocaPid())){
			String pname = getDevicePname(location.getDeviceLocaId());
			location.setParentName(pname);
		}
		
		List<DevicePicture> devicePictures = new ArrayList<DevicePicture>(
				device.getDevicePictures());
		if (devicePictures != null && devicePictures.size() > 0) {
			for (DevicePicture devicePicture : devicePictures) {
				if (devicePictures != null) {
					devicePicture.setPictureNote(urlPath
							+ devicePicture.getPictureNote());
				}
			}
		}
		
		List<DeviceVideo> deviceVideos = device.getDeviceVideos();
		if (deviceVideos != null && deviceVideos.size() > 0) {
			for (DeviceVideo deviceVideo : deviceVideos) {
				deviceVideo.setVideo(videoUrlPath+deviceVideo.getVideo());
				deviceVideo.setVideoPic(videoUrlPath+deviceVideo.getVideoPic());
				}
			}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(device);
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
	@RequestMapping(value = "/device/getByBarcode/{barcode}", method = RequestMethod.GET)
	public ResultVo getByBarcode(@PathVariable("barcode") String barcode) {

		ResultVo resultVo = new ResultVo();
		List<Integer> menuIds = new ArrayList<Integer>();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer roleId = sysUser.getSysRole().getRoleId();
		
		Device device = deviceService.getByBarcode(barcode);
		if (device == null) {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("device is null");
			return resultVo;

		}
		
		List<SysRoleMenu> menus = sysRoleMenuService.getByRoleId(roleId.longValue());
		if(menus!=null && menus.size()>0){
			for (SysRoleMenu sysRoleMenu : menus) {
				menuIds.add(sysRoleMenu.getSysMenu().getMenuId());
			}
			if(menuIds.contains(402)){
				device.setMenuDeviceOpt("true");
			}else{
				device.setMenuDeviceOpt("false");
			}
			if(menuIds.contains(401)){
				device.setMenuAppointOpt("true");
			}else{
				device.setMenuAppointOpt("false");
			}
		}
		
		if(device.getQrName()!=null){
			if(!new File(qrPath+device.getQrName()).exists()){
				QRCreateAndParse qr = new QRCreateAndParse();
				qr.createQr(device.getBarCode(), qrPath,device.getQrName());
			}
			device.setQrName(qrUrlPath+device.getQrName());
		}
		
		DeviceLocation location = device.getDeviceLocation();
		if(location!=null && !"0".equals(location.getDeviceLocaPid())){
			String pname = getDevicePname(location.getDeviceLocaId());
			location.setParentName(pname);
		}
		
		List<DevicePicture> devicePictures = new ArrayList<DevicePicture>(
				device.getDevicePictures());
		if (devicePictures != null && devicePictures.size() > 0) {
			for (DevicePicture devicePicture : devicePictures) {
				if (devicePictures != null) {
					devicePicture.setPictureNote(urlPath
							+ devicePicture.getPictureNote());
				}
			}
		}
		List<DeviceVideo> deviceVideos = device.getDeviceVideos();
		if (deviceVideos != null && deviceVideos.size() > 0) {
			for (DeviceVideo deviceVideo : deviceVideos) {
				deviceVideo.setVideo(videoUrlPath+deviceVideo.getVideo());
				deviceVideo.setVideoPic(videoUrlPath+deviceVideo.getVideoPic());
				}
			}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(device);
		return resultVo;
	}

	/**
	 * 根据字段获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:21:45
	 */
	@RequestMapping(value = "/device/find/{keyword}", method = RequestMethod.GET)
	public ResultVo findByDeviceName(@PathVariable("keyword") String keyword) {
		List deviceNames = deviceService.findByDeviceName(keyword);
		ResultVo resultVo = new ResultVo();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find successe");
		resultVo.setResultData(deviceNames);

		if (deviceNames == null) {
			String message = "对象不存在(deviceName:" + keyword + ")";
			logger.warn(message);

		}
		return resultVo;
	}

	@RequestMapping(value = "/device/getByModifyDate/{date}", method = RequestMethod.GET)
	public Device getByModifyDate(@PathVariable("date") Date date) {

		return deviceService.getByModifydate(date);
	}

	/**
	 * 保存对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/device", method = RequestMethod.POST)
	public ResultVo save(@RequestBody DeviceVo deviceVo) {
		
		    ResultVo resultVo = new ResultVo();
		try {
			Device device = deviceVo.getDevice();
			
			FileUtil fileUtil=new FileUtil();
			DeviceVideo deviceVideo = new DeviceVideo();
			
			String barCode = createBarcode();
			device.setBarCode(barCode);
			QRCreateAndParse qr = new QRCreateAndParse();
			String qrName = qr.createQr(barCode, qrPath,null);
			device.setQrName(qrName);
			
			List<String> imgStrs = deviceVo.getImgStrs();
			
			List<MultipartFile> files = deviceVo.getFiles();
			
			deviceService.save(device);
			device.getDeviceId();
			
			if(imgStrs!=null && imgStrs.size()>0){
				saveDevice(device, imgStrs);
			    }
			if(files!=null && files.size()>0){
				for (MultipartFile file : files) {
					deviceVideo.setVideo(fileUtil.uploadVideoFile(file, videoFilePath));
					videoToImage vti=new videoToImage();
					String picName=UUID.randomUUID().toString()+".jpg";
					vti.fetchFrame(videoFilePath+fileUtil.uploadVideoFile(file, videoFilePath),videoFilePath+picName);
					deviceVideo.setVideoPic(picName);
					deviceVideo.setDevice(device);
					deviceVideo.setUploadTime(new Date());
					deviceVideoService.save(deviceVideo);
				}
				
			}
			    resultVo.setResultData(device);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("sava successe");
				return resultVo;
		} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("sava fail");
				return resultVo;
		}
	}

	/**
	 * @Description 保存图片
	 * @author shg
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveDevice(Device device, List<String> imgStrs) {

		// 保存上传图片
		if (imgStrs != null && imgStrs.size() > 0) {
			// base64保存图片
			Base64Util base64Util = new Base64Util();
			DevicePicture devicePicture = new DevicePicture();
			for (String imgStr : imgStrs) {
				imgStr = imgStr.substring(imgStr.indexOf(",") + 1);
				devicePicture.setDevice(device);
				devicePicture.setPictureNote(base64Util.GenerateImage(imgStr,
						filePath));
				devicePictureService.save(devicePicture);
			}
		}
	}
	/**
	 * @Description 保存视频
	 * @author shg
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveVideo(Device device, List<String> videos, String videoName) {
		
		// 保存上传图片
		if (videos != null && videos.size() > 0) {
			// base64保存图片
			Base64Util base64Util=new Base64Util();
			DeviceVideo deviceVideo = new DeviceVideo();
			for (String video : videos) {
				video = video.substring(video.indexOf(",") + 1);
				deviceVideo.setDevice(device);
				deviceVideo.setVideo(base64Util.GenerateVideo(video, filePath, videoName));
				deviceVideoService.save(deviceVideo);
			}
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
	@RequestMapping(value = "/device", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody DeviceVo deviceVo) {
		
		ResultVo resultVo = new ResultVo();
		try {
			Device device = deviceVo.getDevice();
			Integer id = device.getDeviceId();
			List<String> imgStrs = deviceVo.getImgStrs();
			List<String> urls = deviceVo.getUrls();
		
			   // 新增
				if ((imgStrs != null && imgStrs.size()>0)&&(urls.size()<=0)){ // 新增
				       saveDevice(device, imgStrs);
				} else if (urls != null && urls.size()> 0){ // 修改
				// 删除旧图片
					for (String fileName : urls) {
						fileName=fileName.substring(fileName.lastIndexOf("/")+1);
						new File(filePath + fileName).delete();
						// 删除数据库中图片信息
						fileName = fileName.replace(urlPath, "");
						devicePictureService.delete(id, fileName);
					}
				// 保存上传图片
				if (imgStrs != null && imgStrs.size() > 0) {
					saveDevice(device, imgStrs);
				}
			}
			deviceService.update(device);
			
			resultVo.setErrCode(0);
			resultVo.setResultData(device);
			resultVo.setErrMsg("update successe");
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
	@RequestMapping(value = "/device/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

		Device device = deviceService.getById(id);

		if (device.getDeviceAppointments().size() > 0){
			resultVo.setErrCode(1);
			resultVo.setErrMsg("该设备预约中，不能被删除！！！");
		} else if(device.getReactionDevices().size() > 0){
			resultVo.setErrCode(2);
			resultVo.setErrMsg("有试验使用该设备，不能删除！！！");
		}else if(device.getAnalyticsDevices().size() > 0){
			resultVo.setErrCode(3);
			resultVo.setErrMsg("该设备分析中，不能被删除！！！");
		}else if(device.getTestDevices().size() > 0){
			resultVo.setErrCode(6);
			resultVo.setErrMsg("该设备测试中，不能被删除！！！");
		}else {
			// 删除文件夹中图片文件
			List<String> filePathList = devicePictureService.getByPictureId(id);
		    if(filePathList!=null && filePathList.size()>0){
			 for(String fileName :filePathList){
				 new File(filePath + fileName).delete();
				       }
			 }
				 //删除数据库中图片信息
				 devicePictureService.deleteByDeviceId(id);
				 
		     List<DeviceVideo> deviceVideos=device.getDeviceVideos();
				 if(deviceVideos!=null && deviceVideos.size()>0){
				    for (DeviceVideo deviceVideo : deviceVideos) {
					   // 删除设备对应视频
						String fileName=deviceVideo.getVideo();
							fileName=fileName.substring(fileName.lastIndexOf("/")+1);
							new File(videoFilePath + fileName).delete();	
						// 删除旧帧图片
						String picfileName=deviceVideo.getVideoPic();
						picfileName=picfileName.substring(picfileName.lastIndexOf("/")+1);
						new File(videoFilePath + picfileName).delete();
					 
						 deviceVideoService.delete(deviceVideo.getDeviceVideoId());	
					}
				 }
			deviceService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("删除成功！！！");
		}
		return resultVo;

	}
	/**
	 * 查找所有设备型号的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/getAllModelName", method = RequestMethod.GET)
	public ResultVo getAllModelName() {
		
		ResultVo resultVo = new ResultVo();
		
		List<Device> devices = deviceService.getAllModelName();
		   if(devices!=null&&devices.size()>0){
			   resultVo.setErrCode(0);
				resultVo.setErrMsg("查找成功！");
				resultVo.setResultData(devices);
				return resultVo;
		     }
				resultVo.setErrCode(1);
				resultVo.setErrMsg("无数据");
				resultVo.setResultData(null);
				return resultVo;
		}
	
	
	 /**
	 * @Description 生成二维码打印
	 * @author llwangi
	 * @version V1.0
	 * @date 2017年10月27日 上午11:49:52
	 */
 @RequestMapping(value = "/devicePdfForPrint", method = RequestMethod.POST)
	public ResultVo createPdfForPrint(@RequestBody List<DevicePdfVo> devicePdfVo) {
		ResultVo resultVo = new ResultVo();
		try {
			List<String> list=new ArrayList<String>();
			if(devicePdfVo!=null&&devicePdfVo.size()>0){
				for(int i=0;i<devicePdfVo.size();i++){
					PdfUtil pdfUtil=new PdfUtil();
					// SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");  
					Map<String, Object> map =new HashMap<String, Object>();
					map.put("deviceName", devicePdfVo.get(i).getDeviceName());
					map.put("deviceTypeName", devicePdfVo.get(i).getDeviceTypeName());
					map.put("model", devicePdfVo.get(i).getModel());
					map.put("vendor", devicePdfVo.get(i).getVendor());
					if(devicePdfVo.get(i).getIamgePath()!=null){
						//if(devicePdfVo.get(i).getIamgePath().indexOf("http")>=0){
//							String qrPath=pdfUtil.getImageLoad(devicePdfVo.get(i).getIamgePath(), "src/main/resources/template/");
						String path=devicePdfVo.get(i).getIamgePath();
						String picPath=qrPath+path.substring(path.lastIndexOf("/")+1,path.length());
						map.put("iamgePath", picPath);
							//map.put("iamgePath", qrPath);
						//}
					}
					String filename="print"+UUID.randomUUID().toString()+".pdf";
					String outUrl="src/main/resources/template/"+filename;
					if(pdfUtil.exportPdf(map, outUrl, "设备模板.pdf")){
						list.add(outUrl);
					}else{
						resultVo.setErrCode(1);	
						resultVo.setErrMsg("生成pdf报错1");
						return resultVo;
					}
				}
			}
			MergeUtil mergeUtil=new MergeUtil();
			String endName=UUID.randomUUID().toString()+".pdf";
			Boolean flag=mergeUtil.mergePdfFiles(list, pdfPath+"/"+endName);
			if(flag){
				String out=pdfLine+endName;
				resultVo.setErrCode(0);
				resultVo.setResultData(out);
			}else{
				resultVo.setErrCode(1);
				resultVo.setErrMsg("生成pdf报错2");
				return resultVo;
			}
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("生成pdf报错3"+e.getMessage());
			return resultVo;
		}
		return resultVo;
	}
 
	
	@RequestMapping(value = "/device/getLocation/{id}", method = RequestMethod.GET)
	 public String getDevicePname(@PathVariable("id")Integer id) {
		    try {
		      String ss="";
		    loop:for(int i=0;i<10;i++){
		      DeviceLocation deviceLocation =  deviceLocationService.getById(id);
		      if(deviceLocation.getDeviceLocaPid()!=null&&!"0".equals(deviceLocation.getDeviceLocaPid())){
		        if(i==0){
		          ss="";
		        }else if(i==1){
		          ss=deviceLocation.getLabel();
		        }else{
		          ss=deviceLocation.getLabel()+">"+ss;
		        }
		        id= Integer.valueOf(deviceLocation.getDeviceLocaPid());
		      }else{
		        if(!"".equals(ss)){
		          ss=deviceLocation.getLabel()+">"+ss;
		        }else{
		          ss=deviceLocation.getLabel();  
		        }
		        break loop;
		      }
		      }
		      return ss;
		    } catch (Exception e) {
		    	return null;
		      // TODO: handle exception
		    }
		  }
	@RequestMapping(value = "/deviceAllName", method = RequestMethod.GET)
	public List<Device> getAllName() {
		 
			List<Device> devices=deviceService.getAllName();
			
		    return devices;
	}
	
	/**
	 * @Description 生成条形码
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	public String createBarcode() {

		Random random = new Random();
		int a = random.nextInt(999999999) + 1;

		String barCode = "DX" + a;
		List<String> barCodes = deviceService.getBarCodes();
		boolean flat = true;
		flat = barCodes.contains(barCode);
		if (flat) {
			createBarcode();
		}
		return barCode;
	}
	
	@RequestMapping(value = "/device/updateQrName", method = RequestMethod.GET)
	public ResultVo updateQrName() {
		 ResultVo resultVo = new ResultVo();
			try {
				List<Device> list = deviceService.getAll();
				
				for (Device device : list) {
					if(device.getBarCode()==null){
						String barCode = createBarcode();
						device.setBarCode(barCode);
						QRCreateAndParse qr = new QRCreateAndParse();
						String qrName = qr.createQr(barCode, qrPath,null);
						device.setQrName(qrName);
						deviceService.updateQrName(device);
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update successe");
				return resultVo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("save fail");
				return resultVo;
			}
	}
	


}
