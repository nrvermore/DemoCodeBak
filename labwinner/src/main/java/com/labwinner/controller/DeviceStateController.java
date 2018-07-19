package com.labwinner.controller;

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
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.DeviceState;
import com.labwinner.service.DeviceStateService;

/**
 * 设备状态Controller
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午4:22:48
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class DeviceStateController {

	private static Logger logger = LoggerFactory
			.getLogger(DeviceStateController.class);
	@Autowired
	private DeviceStateService deviceStateService; 

	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:16
	 */
	 @RequestMapping(value = "/deviceState", method = RequestMethod.GET)
	    public List<DeviceState> getAll() {
		 List<DeviceState> list = deviceStateService.getAll();
			if (list == null) {
				String message = "没有对象";
				logger.warn(message);
				
			}
			return list;
	    }
	 
	 /**
		 * @Description 根据关键字获取对象
		 * @author liuhq
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		@RequestMapping(value = "/deviceStatePageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
		@ResponseBody
		public ResultVo getAllPageable(@PathVariable Integer page,
				@PathVariable Integer pageSize,
				@PathVariable String keyword) {
			
			ResultVo resultVo = new ResultVo();
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				List<DeviceState> deviceStates = deviceStateService.getAllPageable(keyword);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(deviceStates));
				return resultVo;
			} else {
				List<DeviceState> deviceStates = deviceStateService.getAll();
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(deviceStates));
				return resultVo;
			}
			
		}
	 

	/**
	 * 根据主键获取对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:26
	 */
	 @RequestMapping(value = "/deviceState/{id}", method = RequestMethod.GET)
	    public ResultVo getById(@PathVariable("id") Integer id) {
		 ResultVo resultVo = new ResultVo();
			try {
				DeviceState deviceState = deviceStateService.getById(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(deviceState);
				return resultVo;
				// }
			} catch (Exception e) {
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find fail");
				return resultVo;
			}
	    }

	/**
	 * 保存对象方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:40
	 */
	 @RequestMapping(value = "/deviceState", method = RequestMethod.POST)
	    public ResultVo save(@RequestBody DeviceState deviceState) {
		 ResultVo resultVo = new ResultVo();
			try {
				deviceStateService.save(deviceState);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("sava successe");
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
		 @RequestMapping(value = "/deviceState", method = RequestMethod.PUT)
		    public ResultVo update(@RequestBody DeviceState deviceState) {
			 ResultVo resultVo = new ResultVo();
				try {
					deviceStateService.update(deviceState);
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
	 * 删除对象方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:58
	 */
		 @RequestMapping(value = "/deviceState/{id}", method = RequestMethod.DELETE)
		    public ResultVo delete(@PathVariable("id") Integer id) {
			 ResultVo resultVo = new ResultVo();

			 DeviceState deviceState =  deviceStateService.getById(id);

				if (deviceState.getDeviceAppointments().size() > 0){
					resultVo.setErrCode(1);
					resultVo.setErrMsg("该设备状态存在设备预约中，不能被删除！！！");
				}else {
					
					deviceStateService.delete(id);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("删除成功！！！");
				}
				return resultVo;
		    }

}
