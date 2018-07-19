package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.SysMenu;
import com.labwinner.domain.SysRole;
import com.labwinner.domain.SysRoleMenu;
import com.labwinner.service.SysRoleMenuService;
import com.labwinner.service.SysRoleService;

import java.util.List;

/**
 * @Description 角色菜单Controller
 * @author liuhq
 * @version V1.0
 * @date 2017年6月15日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class SysRoleMenuController {

	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	@RequestMapping(value = "/sysRoleMenu", method = RequestMethod.GET)
	public List<SysRoleMenu> getAll() {
		return sysRoleMenuService.getAll();
	}

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	@RequestMapping(value = "/sysRoleMenu/{id}", method = RequestMethod.GET)
	public SysRoleMenu getById(@PathVariable("id") Long id) {
		return sysRoleMenuService.getById(id);
	}

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	@RequestMapping(value = "/sysRoleMenu", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<SysRoleMenu> sysRoleMenu) {
		ResultVo res=new ResultVo();
		try {
			if(sysRoleMenu!=null&&sysRoleMenu.size()>0){
				int id=sysRoleMenu.get(0).getSysRole().getRoleId();
				sysRoleMenuService.deleteByRoleId(Long.valueOf(String.valueOf(id)));
				SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(id)));
				if(sysRoleMenu.get(0).getSysMenu()!=null){
					if("ROLE_ADMIN".equals(sysRole.getRoleName())){
						SysRoleMenu  sysRoleMenu1=new SysRoleMenu();
						SysRole sysRole1=new SysRole();
						SysMenu sysMenu1=new SysMenu();
						sysRole1.setRoleId(sysRole.getRoleId());
						sysMenu1.setMenuId(12);
						sysRoleMenu1.setSysMenu(sysMenu1);
						sysRoleMenu1.setSysRole(sysRole1);
						sysRoleMenuService.save(sysRoleMenu1);
						
						SysRoleMenu  sysRoleMenu2=new SysRoleMenu();
						SysRole sysRole2=new SysRole();
						SysMenu sysMenu2=new SysMenu();
						sysRole2.setRoleId(sysRole.getRoleId());
						sysMenu2.setMenuId(1201);
						sysRoleMenu2.setSysMenu(sysMenu2);
						sysRoleMenu2.setSysRole(sysRole2);
						sysRoleMenuService.save(sysRoleMenu2);
						
						SysRoleMenu  sysRoleMenu3=new SysRoleMenu();
						SysRole sysRole3=new SysRole();
						SysMenu sysMenu3=new SysMenu();
						sysRole3.setRoleId(sysRole.getRoleId());
						sysMenu3.setMenuId(1202);
						sysRoleMenu3.setSysMenu(sysMenu3);
						sysRoleMenu3.setSysRole(sysRole3);
						sysRoleMenuService.save(sysRoleMenu3);
						
						SysRoleMenu  sysRoleMenu4=new SysRoleMenu();
						SysRole sysRole4=new SysRole();
						SysMenu sysMenu4=new SysMenu();
						sysRole4.setRoleId(sysRole.getRoleId());
						sysMenu4.setMenuId(1203);
						sysRoleMenu4.setSysMenu(sysMenu4);
						sysRoleMenu4.setSysRole(sysRole4);
						sysRoleMenuService.save(sysRoleMenu4);
					}
					for(int i=0;i<sysRoleMenu.size();i++){
						sysRoleMenuService.save(sysRoleMenu.get(i));
					}	
				}
			}
			res.setErrCode(0);
			res.setErrMsg("update success");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("update fail, message:"+e.getMessage());
		}
		return res;
	}

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	@RequestMapping(value = "/sysRoleMenu", method = RequestMethod.PUT)
	public void update(@RequestBody SysRoleMenu sysRoleMenu) {
		sysRoleMenuService.update(sysRoleMenu);
	}

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	@RequestMapping(value = "/sysRoleMenu/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		sysRoleMenuService.delete(id);
	}

	/**
	 * @Description 根据角色主键删除角色菜单
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	@RequestMapping(value = "/sysRoleMenu/deleteByRoleId/{id}", method = RequestMethod.DELETE)
	public void deleteByRoleId(@PathVariable("id") Long id) {
		sysRoleMenuService.deleteByRoleId(id);
	}
	
	/**
	 * @Description 根据角色主键获取角色菜单
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	@RequestMapping("/sysRoleMenu/getByRoleId/{id}")
	public List<SysRoleMenu> getByRoleId(@PathVariable("id") Long id) {
		return sysRoleMenuService.getByRoleId(id);
	}

}
