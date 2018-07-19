package com.labwinner.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.InventoryUser;
import com.labwinner.service.InventoryUserService;

@RestController
public class InventoryUserController {
	
	private static Logger logger = LoggerFactory
			.getLogger(InventoryUserController.class);

	@Autowired
	private InventoryUserService inventoryUserService;
	
	@RequestMapping(value = "/inventoryUser/{id}", method = RequestMethod.GET)
	public List<InventoryUser> getById(@PathVariable("id") Integer id) {
		List<InventoryUser> inventoryUsers = inventoryUserService.getById(id);
		if (inventoryUsers == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return inventoryUsers;
	}
	

	@RequestMapping(value = "/inventoryUser", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<InventoryUser> inventoryUsers) {
		ResultVo resultVo = new ResultVo();
		try{
			for(InventoryUser inventoryUser :inventoryUsers){
				inventoryUserService.save(inventoryUser);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("保存成功");
			return resultVo;
		}
		catch(Exception e){
			String message = "save fail";
			logger.error(message);
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/inventoryUser/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id")Integer id) {
		ResultVo resultVo = new ResultVo();
		try{
			
				inventoryUserService.delete(id);
		
			resultVo.setErrCode(0);
			resultVo.setErrMsg("保存成功");
			return resultVo;
		}
		catch(Exception e){
			String message = "save fail";
			logger.error(message);
			e.printStackTrace();
		}
		return null;
	}
}
