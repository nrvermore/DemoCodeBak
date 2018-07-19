package com.labwinner.domain;

import java.security.Timestamp;
import java.util.Date;

	/**
	 * ProjectPlan entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 项目进度计划表
	 */
public class ProjectPlan implements java.io.Serializable {

	/**
	 * 项目进度计划主键
	 */
	private Integer proPlanId;
	
	/**
	 * 项目主键
	 */
	private ProjectBasicInfo projectBasicInfo;
	
	/**
	 * 进度计划名称
	 */
	private String proPlanName;
	
	/**
	 * 进度计划开始时间
	 */
	private Date proPlanStarttime;
	
	/**
	 * 进度计划结束时间
	 */
	private Date proPlanDeadline;
	
	/**
	 * 项目进度状态
	 */
	private ProjectSchedule projectSchedule;
	
	/**
	 * 阶段描述
	 */
	private String stageDes;
	
	/**
	 * 阶段目标
	 */
	private String stageGoal;
	
	/**
	 * 创建人
	 */
	private String creater;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 修改人
	 */
	private String modifier;
	
	/**
	 * 修改日期
	 */
	private Date modifyDate;

	public ProjectPlan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectPlan(Integer proPlanId, ProjectBasicInfo projectBasicInfo,
			String proPlanName, Date proPlanStarttime, Date proPlanDeadline,
			ProjectSchedule projectSchedule, String stageDes, String stageGoal,
			String creater, Date createDate, String modifier, Date modifyDate) {
		super();
		this.proPlanId = proPlanId;
		this.projectBasicInfo = projectBasicInfo;
		this.proPlanName = proPlanName;
		this.proPlanStarttime = proPlanStarttime;
		this.proPlanDeadline = proPlanDeadline;
		this.projectSchedule = projectSchedule;
		this.stageDes = stageDes;
		this.stageGoal = stageGoal;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
	}

	public Integer getProPlanId() {
		return proPlanId;
	}

	public void setProPlanId(Integer proPlanId) {
		this.proPlanId = proPlanId;
	}

	public ProjectBasicInfo getProjectBasicInfo() {
		return projectBasicInfo;
	}

	public void setProjectBasicInfo(ProjectBasicInfo projectBasicInfo) {
		this.projectBasicInfo = projectBasicInfo;
	}

	public String getProPlanName() {
		return proPlanName;
	}

	public void setProPlanName(String proPlanName) {
		this.proPlanName = proPlanName;
	}

	public Date getProPlanStarttime() {
		return proPlanStarttime;
	}

	public void setProPlanStarttime(Date proPlanStarttime) {
		this.proPlanStarttime = proPlanStarttime;
	}

	public Date getProPlanDeadline() {
		return proPlanDeadline;
	}

	public void setProPlanDeadline(Date proPlanDeadline) {
		this.proPlanDeadline = proPlanDeadline;
	}

	public ProjectSchedule getProjectSchedule() {
		return projectSchedule;
	}

	public void setProjectSchedule(ProjectSchedule projectSchedule) {
		this.projectSchedule = projectSchedule;
	}

	public String getStageDes() {
		return stageDes;
	}

	public void setStageDes(String stageDes) {
		this.stageDes = stageDes;
	}

	public String getStageGoal() {
		return stageGoal;
	}

	public void setStageGoal(String stageGoal) {
		this.stageGoal = stageGoal;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	

}