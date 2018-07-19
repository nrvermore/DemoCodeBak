package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * ProjectSchedule entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 项目进度状态
	 */
public class ProjectSchedule implements java.io.Serializable {

	/**
	 * 项目进度状态主键
	 */
	private Integer proScheduleId;
	
	/**
	 * 进度状态名称
	 */
	private String proScheduleName;
	
	/**
	 * 进度状态描述
	 */
	private String proScheduleDes;
	
	/**
	 * 项目信息表
	 */
	private Set<ProjectBasicInfo> projectBasicInfos = new HashSet<ProjectBasicInfo>(
			0);
	/**
	 * 项目进度计划表
	 */
	private Set<ProjectPlan> projectPlans = new HashSet<ProjectPlan>(
			0);
	public ProjectSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProjectSchedule(Integer proScheduleId, String proScheduleName,
			String proScheduleDes, Set<ProjectBasicInfo> projectBasicInfos,
			Set<ProjectPlan> projectPlans) {
		super();
		this.proScheduleId = proScheduleId;
		this.proScheduleName = proScheduleName;
		this.proScheduleDes = proScheduleDes;
		this.projectBasicInfos = projectBasicInfos;
		this.projectPlans = projectPlans;
	}
	public Integer getProScheduleId() {
		return proScheduleId;
	}
	public void setProScheduleId(Integer proScheduleId) {
		this.proScheduleId = proScheduleId;
	}
	public String getProScheduleName() {
		return proScheduleName;
	}
	public void setProScheduleName(String proScheduleName) {
		this.proScheduleName = proScheduleName;
	}
	public String getProScheduleDes() {
		return proScheduleDes;
	}
	public void setProScheduleDes(String proScheduleDes) {
		this.proScheduleDes = proScheduleDes;
	}
	public Set<ProjectBasicInfo> getProjectBasicInfos() {
		return projectBasicInfos;
	}
	public void setProjectBasicInfos(Set<ProjectBasicInfo> projectBasicInfos) {
		this.projectBasicInfos = projectBasicInfos;
	}
	public Set<ProjectPlan> getProjectPlans() {
		return projectPlans;
	}
	public void setProjectPlans(Set<ProjectPlan> projectPlans) {
		this.projectPlans = projectPlans;
	}


}