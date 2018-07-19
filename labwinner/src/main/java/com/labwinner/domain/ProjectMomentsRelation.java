package com.labwinner.domain;

	/**
	 * ProjectMomentsRelation entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 项目圈关系表
	 */
public class ProjectMomentsRelation implements java.io.Serializable {

	/**
	 * 关系主键
	 */
	private Integer relationId;
	
	/**
	 * 项目主键
	 */
	private ProjectBasicInfo projectBasicInfo;
	
	/**
	 * 项目圈信息主键
	 */
	private ProjectMoments projectMoments;

	// Constructors

	/** default constructor */
	public ProjectMomentsRelation() {
	}

	/** full constructor */
	public ProjectMomentsRelation(ProjectBasicInfo projectBasicInfo,
			ProjectMoments projectMoments) {
		this.projectBasicInfo = projectBasicInfo;
		this.projectMoments = projectMoments;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public ProjectBasicInfo getProjectBasicInfo() {
		return projectBasicInfo;
	}

	public void setProjectBasicInfo(ProjectBasicInfo projectBasicInfo) {
		this.projectBasicInfo = projectBasicInfo;
	}

	public ProjectMoments getProjectMoments() {
		return projectMoments;
	}

	public void setProjectMoments(ProjectMoments projectMoments) {
		this.projectMoments = projectMoments;
	}

	
}