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
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.DeviceState;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.ModifyType;
import com.labwinner.service.DeviceLocationService;
import com.labwinner.service.DeviceService;

@RestController
public class DeviceLocationController {
	private static Logger logger = LoggerFactory
			.getLogger(DeviceLocationController.class);
	
	@Autowired
	private DeviceLocationService deviceLocationService;
	
	@Autowired
	private DeviceService deviceService;

	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:21:34
	 */
	 @RequestMapping(value = "/deviceLocation", method = RequestMethod.GET)
	    public List<DeviceLocation> getAll() {
		 List<DeviceLocation> list = deviceLocationService.getAll();
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
		@RequestMapping(value = "/deviceLocationPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
		@ResponseBody
		public ResultVo getAllPageable(@PathVariable Integer page,
				@PathVariable Integer pageSize, @PathVariable String keyword) {
			ResultVo resultVo = new ResultVo();
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				List<DeviceLocation> deviceLocations = deviceLocationService.getAllPageable(keyword);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(deviceLocations));
				return resultVo;
			} else {
				List<DeviceLocation> deviceLocations = deviceLocationService.getAll();
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(deviceLocations));
				return resultVo;
			}
			
		}

	/**
	 * 根据主键获取对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:21:45
	 */
	 @RequestMapping(value = "/deviceLocation/{id}", method = RequestMethod.GET)
	    public ResultVo getById(@PathVariable("id") Integer id) {
		 ResultVo resultVo = new ResultVo();
			try {
				DeviceLocation location = deviceLocationService.getById(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(location);
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
	 * 保存对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:21:57
	 */
	 @RequestMapping(value = "/deviceLocation", method = RequestMethod.POST)
	    public ResultVo save(@RequestBody DeviceLocation deviceLocation) {
		 ResultVo resultVo = new ResultVo();
			try {
				if(deviceLocation.getDeviceLocaPid()==null){
					deviceLocation.setDeviceLocaPid("0");
				}
				deviceLocationService.save(deviceLocation);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("sava successe");
				resultVo.setResultData(deviceLocation.getDeviceLocaId());
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
		 @RequestMapping(value = "/deviceLocation", method = RequestMethod.PUT)
		    public ResultVo update(@RequestBody DeviceLocation deviceLocation) {
			 ResultVo resultVo = new ResultVo();
				try {
					if(deviceLocation.getDeviceLocaPid()==null){
						deviceLocation.setDeviceLocaPid("0");
					}
					 deviceLocationService.update(deviceLocation);
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
		 
		 
		 @RequestMapping(value = "/deviceLocation/getTree", method = RequestMethod.GET)
			public ResultVo getTree() {
				ResultVo resultVo = new ResultVo();
				try {
					DeviceLocation location = deviceLocationService.getTree(1);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(location);
					return resultVo;
					// }
				} catch (Exception e) {
					e.printStackTrace();
					resultVo.setErrCode(1);
					resultVo.setErrMsg("find fail");
					return resultVo;
				}
				
		 }
		 
		 
			@RequestMapping(value = "/deviceLocation/listTreeForEdit", method = RequestMethod.GET)
			public ResultVo getAllTreeForEdit() {
				ResultVo resultVo = new ResultVo();
				try {
					List<DeviceLocation> list = deviceLocationService.getAllFirst();
					List<DeviceLocation> resList=new ArrayList<DeviceLocation>();
					if(list!=null&&list.size()>0){
						for(int i=0;i<list.size();i++){
							DeviceLocation location = deviceLocationService.getTree(list.get(i).getDeviceLocaId());
							if(location!=null){
								resList.add(location);
							}
						}
					}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(resList);
					return resultVo;
					// }
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find fail");
				return resultVo;
			}
	/**
	 * 删除对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
		 @RequestMapping(value = "/deviceLocation/{id}", method = RequestMethod.DELETE)
		    public ResultVo delete(@PathVariable("id") Integer id) {
			 ResultVo resultVo = new ResultVo();

				DeviceLocation deviceLocation =  deviceLocationService.getById(id);

				if (deviceLocation.getDevices().size() > 0){
					resultVo.setErrCode(1);
					resultVo.setErrMsg("该位置有存放设备，不能被删除！！！");
				}else {
					
					deviceLocationService.delete(id);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("删除成功！！！");
				}
				return resultVo;
			
		 }
		 
		 
		 
			/**
			 * 删除对象的方法
			 * @Description TODO
			 * @author llwang
			 * @version V1.0
			 * @date 2017年8月30日 上午11:22:08
			 */
				 @RequestMapping(value = "/deviceLocation/delete/{id}", method = RequestMethod.DELETE)
				    public ResultVo deleteWeb(@PathVariable("id") Integer id) {
					 ResultVo resultVo = new ResultVo();
					 	try {
							List<Device> list=deviceService.getByLocationId(id);
							if(list!=null&&list.size()>0){
								resultVo.setErrCode(1);
								resultVo.setErrMsg("该设备位置已被使用，不允许删除！");
							}else{
								List<DeviceLocation> listLocation=deviceLocationService.getAllLocation(id);
								if(listLocation!=null){
									for(int i=0;i<listLocation.size();i++){
										deviceLocationService.delete(listLocation.get(i).getDeviceLocaId());
									}
								}
								resultVo.setErrCode(0);
								resultVo.setErrMsg("删除成功！");
							}
							
						} catch (Exception e) {
							resultVo.setErrCode(2);
							resultVo.setErrMsg("程序错误："+e.getMessage());
						}
						return resultVo;
					
				 }

}
