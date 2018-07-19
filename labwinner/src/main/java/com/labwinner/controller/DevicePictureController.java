package com.labwinner.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.DevicePicture;
import com.labwinner.service.DevicePictureService;
/**
 * 设备图片Controller
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午3:23:49
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class DevicePictureController {
	private static Logger logger = LoggerFactory
			.getLogger(DevicePictureController.class);
	@Autowired
	private DevicePictureService devicePictureService;
	@Value("${device.url-path}")
	private String urlPath;

	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午3:24:01
	 */
	@RequestMapping(value = "/devicePicture", method = RequestMethod.GET)
    public List<DevicePicture> getAll() {
		List<DevicePicture> list = devicePictureService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
    }

	/**
	 * 根据主键获取对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午3:24:14
	*/
	@RequestMapping(value = "/devicePicture/getImage/{id}", method = RequestMethod.GET)
	public ResultVo getList(@PathVariable("id") Integer id){
		ResultVo resultVo = new ResultVo();
		List<String> imageStrs = devicePictureService.getByPictureId(id);
		if(null==imageStrs){
			String message = "没有对象";
			logger.warn(message);
		}
		ArrayList<Object> urls = new ArrayList<Object>();
		for(String imagestr :imageStrs){
			String URL = urlPath+imagestr;
			urls.add(URL);
			
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("get files success");
		resultVo.setResultList(urls);
		return resultVo;
		
	}
	
}
