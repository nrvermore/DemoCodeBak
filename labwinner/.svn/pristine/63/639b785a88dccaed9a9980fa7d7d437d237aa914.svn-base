package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.DeviceState;
import com.labwinner.domain.SysRole;
import com.labwinner.service.SysRoleService;
import com.labwinner.service.SysUserService;

import java.util.List;

/**
 * @Description 角色Controller
 * @author liuhq
 * @version V1.0
 * @date 2017年6月6日 上午10:36:15
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping(value = "/sysRole/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Long id) {
		 ResultVo resultVo = new ResultVo();
			try {
				SysRole sysRole = sysRoleService.getById(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(sysRole);
				return resultVo;
				// }
			} catch (Exception e) {
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find fail");
				return resultVo;
			}
		
	}

	@RequestMapping(value = "/sysRole", method = RequestMethod.GET)
	public List<SysRole> getAll() {
		return sysRoleService.getAllForEdit();
	}

	@RequestMapping(value = "/sysRoleAllName", method = RequestMethod.GET)
	public List<SysRole> getAllName() {
		return sysRoleService.getAllName();
	}
	
	
	@RequestMapping(value = "/sysRole/list", method = RequestMethod.GET)
	public List<SysRole> getAllList() {
		return sysRoleService.getAll();
	}
	
	@RequestMapping(value = "/sysRole", method = RequestMethod.POST)
	public ResultVo save(@RequestBody SysRole sysRole) {
		 ResultVo resultVo = new ResultVo();
			try {
				int maxId=sysRoleService.getMaxId();
				sysRole.setRoleId(maxId+1);
				sysRoleService.save(sysRole);
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

	@RequestMapping(value = "/sysRole", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody SysRole sysRole) {
		 ResultVo resultVo = new ResultVo();
			try {
				sysRoleService.update(sysRole);
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

	@RequestMapping(value = "/sysRole/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Long id) {
		 ResultVo resultVo = new ResultVo();
		try {
			Integer count = sysUserService.findByRoleId(id);
			if (count>0){
				resultVo.setErrCode(2);
				resultVo.setErrMsg("您无权删除！！！");
				return resultVo;
			}
			sysRoleService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("删除成功！！！");
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("删除失败！！！");
			return resultVo;
		}
	}
	
	/**
	 * @Description 根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "sysRole/sysRolePageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable String keyword) {
		
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<SysRole> sysRoles = sysRoleService.getAllPageable(keyword);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(sysRoles));
			return resultVo;
		} else {
			List<SysRole> sysRoles = sysRoleService.getAllForEdit();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(sysRoles));
			return resultVo;
		}
		
	}
}
