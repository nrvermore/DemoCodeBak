package com.labwinner.domain;

	/**
	 * TeamMomentsLike entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 团队圈点赞表
	 */
public class TeamMomentsLike implements java.io.Serializable {

	/**
	 * 团队圈点赞主键
	 */
	private Integer teamMomentsLikeId;
	
	/**
	 * 点赞人id
	 */
	private SysUser sysUser;
	
	/**
	 * 圈类型
	 */
	private Integer momentsType;
	
	/**
	 * 圈信息主键
	 */
	private Integer momentsInfoId;

	// Constructors

	/** default constructor */
	public TeamMomentsLike() {
	}

	/** full constructor */
	public TeamMomentsLike(SysUser sysUser, Integer momentsType,
			Integer momentsInfoId) {
		this.sysUser = sysUser;
		this.momentsType = momentsType;
		this.momentsInfoId = momentsInfoId;
	}

	public Integer getTeamMomentsLikeId() {
		return teamMomentsLikeId;
	}

	public void setTeamMomentsLikeId(Integer teamMomentsLikeId) {
		this.teamMomentsLikeId = teamMomentsLikeId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Integer getMomentsType() {
		return momentsType;
	}

	public void setMomentsType(Integer momentsType) {
		this.momentsType = momentsType;
	}

	public Integer getMomentsInfoId() {
		return momentsInfoId;
	}

	public void setMomentsInfoId(Integer momentsInfoId) {
		this.momentsInfoId = momentsInfoId;
	}

	

}