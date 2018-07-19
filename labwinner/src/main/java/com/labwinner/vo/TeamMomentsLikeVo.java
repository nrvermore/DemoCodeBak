package com.labwinner.vo;


public class TeamMomentsLikeVo {

	/**
	 * 点赞人id
	 */
	private Integer userId;
	
	/**
	 * 圈类型
	 */
	private Integer momentsType;
	
	/**
	 * 圈信息主键
	 */
	private Integer momentsInfoId;
	
	public TeamMomentsLikeVo (){}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
