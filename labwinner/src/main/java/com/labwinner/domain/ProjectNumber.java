package com.labwinner.domain;

	/**
	 * ProjectNumber entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 项目成员表
	 */
public class ProjectNumber implements java.io.Serializable {

	/**
	 * 项目成员主键
	 */
	private Integer proNumberId;
	
	/**
	 * 删除状态
	 */
	private Integer flag;
	
	/**
	 * 项目主键
	 */
	private ProjectBasicInfo projectBasicInfo;
	
	/**
	 * 用户主键
	 */
	private SysUser sysUser;
	
	/**
	 * 项目角色主键
	 */
	private ProjectRole projectRole;

	// Constructors

	/** default constructor */
	public ProjectNumber() {
	}

	/** full constructor */
	public ProjectNumber(ProjectBasicInfo projectBasicInfo, SysUser sysUser,
			ProjectRole projectRole) {
		this.projectBasicInfo = projectBasicInfo;
		this.sysUser = sysUser;
		this.projectRole = projectRole;
	}

	public Integer getProNumberId() {
		return proNumberId;
	}

	public void setProNumberId(Integer proNumberId) {
		this.proNumberId = proNumberId;
	}

	public ProjectBasicInfo getProjectBasicInfo() {
		return projectBasicInfo;
	}

	public void setProjectBasicInfo(ProjectBasicInfo projectBasicInfo) {
		this.projectBasicInfo = projectBasicInfo;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public ProjectRole getProjectRole() {
		return projectRole;
	}

	public void setProjectRole(ProjectRole projectRole) {
		this.projectRole = projectRole;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}