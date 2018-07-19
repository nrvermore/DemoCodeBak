package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * ProjectType entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 项目类型表
	 */
public class ProjectType implements java.io.Serializable {

	/**
	 * 项目类型主键
	 */
	private Integer projectTypeId;
	
	/**
	 * 项目类型
	 */
	private String projectType;
	
	/**
	 * 项目基本信息表
	 */
	private Set<ProjectBasicInfo> projectBasicInfos = new HashSet<ProjectBasicInfo>(
			0);

	// Constructors

	/** default constructor */
	public ProjectType() {
	}

	/** full constructor */
	public ProjectType(String projectType,
			Set<ProjectBasicInfo> projectBasicInfos) {
		this.projectType = projectType;
		this.projectBasicInfos = projectBasicInfos;
	}

	public Integer getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public Set<ProjectBasicInfo> getProjectBasicInfos() {
		return projectBasicInfos;
	}

	public void setProjectBasicInfos(Set<ProjectBasicInfo> projectBasicInfos) {
		this.projectBasicInfos = projectBasicInfos;
	}

	

}