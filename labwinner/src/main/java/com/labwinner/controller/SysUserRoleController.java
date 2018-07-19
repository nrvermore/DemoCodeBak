package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.labwinner.domain.SysUserRole;
import com.labwinner.domain.SysUserRole;
import com.labwinner.service.SysUserRoleService;

import java.util.List;

/**
 * @Description 用户角色Controller
 * @author liuhq
 * @version V1.0
 * @date 2017年6月16日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class SysUserRoleController {

	@Autowired
	private SysUserRoleService sysUserRoleService;

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	@RequestMapping(value = "/sysUserRole", method = RequestMethod.GET)
	public List<SysUserRole> getAll() {
		return sysUserRoleService.getAll();
	}
	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	@RequestMapping(value = "/sysUserRoleById", method = RequestMethod.GET)
	public List<SysUserRole> getProAll() {
		return sysUserRoleService.getProAll();
	}

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	@RequestMapping(value = "/sysUserRole/{id}", method = RequestMethod.GET)
	public SysUserRole getById(@PathVariable("id") Long id) {
		return sysUserRoleService.getById(id);
	}

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	@RequestMapping(value = "/sysUserRole", method = RequestMethod.POST)
	public Integer save(@RequestBody SysUserRole sysUserRole) {
		return sysUserRoleService.save(sysUserRole);
	}

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	@RequestMapping(value = "/sysUserRole", method = RequestMethod.PUT)
	public void update(@RequestBody SysUserRole sysUserRole) {
		sysUserRoleService.update(sysUserRole);
	}

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	@RequestMapping(value = "/sysUserRole/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		sysUserRoleService.delete(id);
	}

	/**
	 * @Description 根据角色主键删除角色菜单
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	@RequestMapping(value = "/sysUserRole/deleteByRoleId/{id}", method = RequestMethod.DELETE)
	public void deleteByRoleId(@PathVariable("id") Long id) {
		sysUserRoleService.deleteByRoleId(id);
	}

	/**
	 * @Description 根据角色主键获取角色菜单
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	@RequestMapping(value = "/sysUserRole/getByRoleId/{id}", method = RequestMethod.GET)
	public List<SysUserRole> getByRoleId(@PathVariable("id") Long id) {
		return sysUserRoleService.getByRoleId(id);
	}

}
