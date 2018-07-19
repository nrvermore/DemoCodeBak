package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssistComment implements java.io.Serializable{
	
	private Integer assistCommentId;
	
	private String commentContent;
	
	private SysUser sysUser;
	
	private SysSigningAgency sysSigningAgency;
	
	private AssistComment assistComment;
	
	private Integer replyCommentId;
	
	private MarketAssist marketAssist;
	
	private Integer assistMarketId;
	
	private Date commentDate;
	
	private Integer likeNum;
	
	private Integer isLike;
	
	private Integer firstCommentId;
	
	private List<AssistComment> children = new ArrayList<AssistComment>();
	
	public AssistComment(){}

	public Integer getAssistCommentId() {
		return assistCommentId;
	}

	public void setAssistCommentId(Integer assistCommentId) {
		this.assistCommentId = assistCommentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public SysSigningAgency getSysSigningAgency() {
		return sysSigningAgency;
	}

	public void setSysSigningAgency(SysSigningAgency sysSigningAgency) {
		this.sysSigningAgency = sysSigningAgency;
	}

	public AssistComment getAssistComment() {
		return assistComment;
	}

	public void setAssistComment(AssistComment assistComment) {
		this.assistComment = assistComment;
	}

	public Integer getReplyCommentId() {
		return replyCommentId;
	}

	public void setReplyCommentId(Integer replyCommentId) {
		this.replyCommentId = replyCommentId;
	}

	public MarketAssist getMarketAssist() {
		return marketAssist;
	}

	public void setMarketAssist(MarketAssist marketAssist) {
		this.marketAssist = marketAssist;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public List<AssistComment> getChildren() {
		return children;
	}

	public void setChildren(List<AssistComment> children) {
		this.children = children;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Integer getFirstCommentId() {
		return firstCommentId;
	}

	public void setFirstCommentId(Integer firstCommentId) {
		this.firstCommentId = firstCommentId;
	}

	public Integer getAssistMarketId() {
		return assistMarketId;
	}

	public void setAssistMarketId(Integer assistMarketId) {
		this.assistMarketId = assistMarketId;
	}

	public Integer getIsLike() {
		return isLike;
	}

	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	};
	
	
}
