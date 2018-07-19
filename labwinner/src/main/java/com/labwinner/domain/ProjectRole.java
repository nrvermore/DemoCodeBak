package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * ProjectRole entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 项目角色表
	 */
public class ProjectRole implements java.io.Serializable {

	/**
	 * 项目角色主键
	 */
	private Integer proRoleId;
	
	/**
	 * 项目角色类型
	 */
	private String proRoleType;
	
	/**
	 * 项目成员表
	 */
	private Set<ProjectNumber> projectNumbers = new HashSet<ProjectNumber>(0);

	// Constructors

	/** default constructor */
	public ProjectRole() {
	}

	/** full constructor */
	public ProjectRole(String proRoleType, Set<ProjectNumber> projectNumbers) {
		this.proRoleType = proRoleType;
		this.projectNumbers = projectNumbers;
	}

	public Integer getProRoleId() {
		return proRoleId;
	}

	public void setProRoleId(Integer proRoleId) {
		this.proRoleId = proRoleId;
	}

	public String getProRoleType() {
		return proRoleType;
	}

	public void setProRoleType(String proRoleType) {
		this.proRoleType = proRoleType;
	}

	public Set<ProjectNumber> getProjectNumbers() {
		return projectNumbers;
	}

	public void setProjectNumbers(Set<ProjectNumber> projectNumbers) {
		this.projectNumbers = projectNumbers;
	}

	

}