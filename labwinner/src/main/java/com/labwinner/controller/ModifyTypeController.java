package com.labwinner.controller;

import java.io.File;
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
import com.labwinner.domain.DevicePicture;
import com.labwinner.domain.MaterialType;
import com.labwinner.domain.ModifyType;
import com.labwinner.service.ModifyTypeService;

@RestController
public class ModifyTypeController {
	
	private static Logger logger = LoggerFactory
			.getLogger(ModifyTypeController.class);
	
	@Autowired 
	private ModifyTypeService modifyTypeService;
	
	@RequestMapping(value = "/modifyType/list", method = RequestMethod.GET)
	public List<ModifyType> getAll() {
		List<ModifyType> list = modifyTypeService.getAll();
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
	@RequestMapping(value = "/ModifyTypePageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<ModifyType> modifyTypes = modifyTypeService.getAllPageable(keyword);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(modifyTypes));
			return resultVo;
		} else {
			List<ModifyType> modifyTypes = modifyTypeService.getAll();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(modifyTypes));
			return resultVo;
		}
	}
	
	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/modifyType/add", method = RequestMethod.POST)
	public ResultVo save(@RequestBody ModifyType modifyType) {
		ResultVo resultVo = new ResultVo();
		try {
			modifyTypeService.save(modifyType);
			Integer modifyTypeId=modifyType.getModifyTypeId();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("sava successe");
			resultVo.setResultData(modifyTypeId);
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
	 * @Description 删除对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/modifyType/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();

		ModifyType modifyType = modifyTypeService.getById(id);

		if (modifyType.getInventoryModifies().size() > 0){
			resultVo.setErrCode(1);
			resultVo.setErrMsg("该变更类型存在库存变更中，不能被删除！！！");
		}else {
			
			modifyTypeService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("删除成功！！！");
		}
		return resultVo;
		
	}
	/**
	 * @Description 更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/modifyType", method = RequestMethod.PUT)
	public ResultVo Update(@RequestBody ModifyType modifyType) {
		ResultVo resultVo = new ResultVo();
		try {
			modifyTypeService.update(modifyType);
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
	 * @Description 查询对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/modifyType/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();

		try {
			ModifyType modifyType = modifyTypeService.getById(id);
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(modifyType);
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
}
