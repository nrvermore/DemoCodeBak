package com.labwinner.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 角色实体类
 * @author liuhq
 * @version V1.0
 * @date 2017年6月6日 上午9:58:25
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class SysRole implements java.io.Serializable {

	/**
	 * 角色主键
	 */
	private Integer roleId;

	/**
	 * 父级主键
	 */
	private Integer parentId;
	
	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色描述
	 */
	private String roleDesc;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 备注
	 */
	private String remark;
	
	
	/**
	 * 是否是基础角色  0不是  1 是
	 */
	private Integer flag;

	/**
	 * 角色菜单表
	 */
	private Set<SysRoleMenu> sysRoleMenus = new HashSet<SysRoleMenu>(0);

	/**
	 * 用户角色表
	 */
	private Set<SysUserRole> sysUserRoles = new HashSet<SysUserRole>(0);

	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** minimal constructor */
	public SysRole(Timestamp createTime) {
		this.createTime = createTime;
	}

	/** full constructor */
	public SysRole(Integer parentId, String roleName, String roleDesc, Timestamp createTime,
			String remark, Set<SysRoleMenu> sysRoleMenus,
			Set<SysUserRole> sysUserRoles,Integer flag) {
		this.parentId = parentId;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.createTime = createTime;
		this.remark = remark;
		this.sysRoleMenus = sysRoleMenus;
		this.sysUserRoles = sysUserRoles;
		this.flag=flag;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<SysRoleMenu> getSysRoleMenus() {
		return sysRoleMenus;
	}

	public void setSysRoleMenus(Set<SysRoleMenu> sysRoleMenus) {
		this.sysRoleMenus = sysRoleMenus;
	}

	public Set<SysUserRole> getSysUserRoles() {
		return sysUserRoles;
	}

	public void setSysUserRoles(Set<SysUserRole> sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}