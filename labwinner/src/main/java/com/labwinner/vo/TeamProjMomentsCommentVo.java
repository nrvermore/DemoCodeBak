package com.labwinner.vo;

public class TeamProjMomentsCommentVo {

	/**
	 * 团队项目圈评论主键
	 */
	private Integer teamProjMomentsCommentId;
	
	/**
	 * 评论内容
	 */
	private String commentContent;
	
	/**
	 * 评论人id
	 */
	private Integer commentUserId;
	
	/**
	 * 未用
	 */
	private String replyContent;
	
	/**
	 * 被评论人
	 */
	private Integer replyUserId;
	
	/**
	 * 圈类型
	 */
	private Integer momentsType;
	
	/**
	 * 圈信息主键
	 */
	private Integer momentsInfoId;
	
	public TeamProjMomentsCommentVo(){}

	public Integer getTeamProjMomentsCommentId() {
		return teamProjMomentsCommentId;
	}

	public void setTeamProjMomentsCommentId(Integer teamProjMomentsCommentId) {
		this.teamProjMomentsCommentId = teamProjMomentsCommentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(Integer commentUserId) {
		this.commentUserId = commentUserId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Integer getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Integer replyUserId) {
		this.replyUserId = replyUserId;
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
