package com.labwinner.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceType;
import com.labwinner.domain.MaterialType;
import com.labwinner.service.*;

/**
 * 设备类型Controller
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午5:41:35
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class DeviceTypeController {
  
	private static Logger logger = LoggerFactory
			.getLogger(DeviceTypeController.class);
	@Autowired
	private DeviceTypeService deviceTypeService;

	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:45
	 */
	@RequestMapping(value = "/deviceType", method = RequestMethod.GET)
    public List<DeviceType> getAll() {
		List<DeviceType> list = deviceTypeService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
    }
	
	@RequestMapping(value = "/deviceTypeAllName", method = RequestMethod.GET)
	public List<DeviceType> getAllName() {
		List<DeviceType> list = deviceTypeService.getAllName();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
	}
	
	
	/**
	 * @Description 根据关键字分页获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/DeviceTypePageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<DeviceType> deviceTypes = deviceTypeService.getAllPageable(keyword);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(deviceTypes));
			return resultVo;
		} else {
			List<DeviceType> deviceTypes = deviceTypeService.getAll();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(deviceTypes));
			return resultVo;
		}
	}

	/**
	 * 根据主键获取对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:53
	 */
	@RequestMapping(value = "/deviceType/{id}", method = RequestMethod.GET)
    public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();

		try {
			DeviceType deviceType = deviceTypeService.getById(id);
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(deviceType);
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find fail");
			resultVo.setResultData(null);
			return resultVo;
		}
    }

	/**
	 * 保存对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:42:03
	 */
	@RequestMapping(value = "/deviceType", method = RequestMethod.POST)
    public ResultVo save(@RequestBody DeviceType deviceType) {
		ResultVo resultVo = new ResultVo();
		try {
			deviceTypeService.save(deviceType);
			Integer deviceTypeId=deviceType.getDeviceTypeId();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("sava successe");
			resultVo.setResultData(deviceTypeId);
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("sava fail");
			return resultVo;
		}
 }
	 /**
		 * 更新对象的方法
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:57
		 */
		 @RequestMapping(value = "/deviceType", method = RequestMethod.PUT)
		    public ResultVo update(@RequestBody DeviceType deviceType) {
			 ResultVo resultVo = new ResultVo();
				try {
					deviceTypeService.update(deviceType);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("update successe");
					return resultVo;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultVo.setErrCode(1);
					resultVo.setErrMsg("update fail");
					return resultVo;
				}
		    }
      

	/**
	 * 删除对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:42:13
	 */
		 @RequestMapping(value = "/deviceType/{id}", method = RequestMethod.DELETE)
		    public ResultVo delete(@PathVariable("id") Integer id) {
			 ResultVo resultVo = new ResultVo();

			 DeviceType deviceType= deviceTypeService.getById(id);

				if (deviceType.getDevices()!=null&&deviceType.getDevices().size() > 0){
					resultVo.setErrCode(1);
					resultVo.setErrMsg("该设备类型被使用中，不能被删除！！！");
				}else {
					deviceTypeService.delete(id);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("删除成功！！！");
				}
				return resultVo;
		    }

}
