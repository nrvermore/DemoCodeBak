package com.labwinner.domain;

/**
 * @Description 角色菜单
 * @author liuhq
 * @version V1.0
 * @date 2017年6月15日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class SysRoleMenu implements java.io.Serializable {

	/**
	 * 角色菜单主键
	 */
	private Integer roleMenuId;

	/**
	 * 菜单主键
	 */
	private SysMenu sysMenu;

	/**
	 * 角色主键
	 */
	private SysRole sysRole;

	/**
	 * 备注
	 */
	private String remark;

	// Constructors

	/** default constructor */
	public SysRoleMenu() {
	}

	/** full constructor */
	public SysRoleMenu(SysMenu sysMenu, SysRole sysRole, String remark) {
		this.sysMenu = sysMenu;
		this.sysRole = sysRole;
		this.remark = remark;
	}

	public Integer getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(Integer roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}