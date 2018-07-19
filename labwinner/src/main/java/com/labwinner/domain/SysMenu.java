package com.labwinner.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description 菜单
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class SysMenu implements java.io.Serializable {

	/**
	 * 菜单主键
	 */
	private Integer menuId;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 菜单描述
	 */
	private String menuDesc;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 父级菜单
	 */
	private Integer fatherId;
	
	/**
	 * 父级菜单名称
	 */
	private String fatherName;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 备注
	 */
	private String menuNameEn;
	
	/**
	 * 是否删除
	 */
	private Integer flag;
	
	/**
	 * 角色菜单表
	 */
	private Set<SysRoleMenu> sysRoleMenus = new HashSet<SysRoleMenu>(0);
	
	private List<SysMenu> children=new ArrayList<SysMenu>();

	// Constructors

	/** default constructor */
	public SysMenu() {
	}

	/** minimal constructor */
	public SysMenu(Timestamp createTime) {
		this.createTime = createTime;
	}

	/** full constructor */
	public SysMenu(String menuName, String menuDesc, Timestamp createTime,
			Integer fatherId,String fatherName, String remark, Set<SysRoleMenu> sysRoleMenus,List<SysMenu> children,String menuNameEn) {
		this.menuName = menuName;
		this.menuDesc = menuDesc;
		this.createTime = createTime;
		this.fatherId = fatherId;
		this.fatherName = fatherName;
		this.remark = remark;
		this.sysRoleMenus = sysRoleMenus;
		this.menuNameEn=menuNameEn;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getFatherId() {
		return fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	public String getMenuNameEn() {
		return menuNameEn;
	}

	public void setMenuNameEn(String menuNameEn) {
		this.menuNameEn = menuNameEn;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}