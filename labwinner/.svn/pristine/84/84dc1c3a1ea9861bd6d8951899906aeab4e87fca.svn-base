package com.labwinner.domain;

/**
 * @Description 用户角色
 * @author liuhq
 * @version V1.0
 * @date 2017年6月16日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class SysUserRole implements java.io.Serializable {

	/**
	 * 用户角色主键
	 */
	private Integer userRoleId;

	/**
	 * 用户主键
	 */
	private SysUser sysUser;

	/**
	 * 橘色主键
	 */
	private SysRole sysRole;

	/**
	 * 备注
	 */
	private String remark;

	// Constructors

	/** default constructor */
	public SysUserRole() {
	}

	/** full constructor */
	public SysUserRole(SysUser sysUser, SysRole sysRole, String remark) {
		this.sysUser = sysUser;
		this.sysRole = sysRole;
		this.remark = remark;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
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