package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * ReactionStatus entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 试验状态表
	 */
public class ReactionStatus implements java.io.Serializable {

	/**
	 * 试验状态主键
	 */
	private Integer reactionStatusId;
	
	/**
	 * 试验状态
	 */
	private String reactionStatus;
	
	/**
	 * 状态类型
	 */
	private Integer statusType;
	
	/**
	 * 试验基本信息表
	 */
	private Set<Reaction> reactions = new HashSet<Reaction>(0);

	// Constructors

	/** default constructor */
	public ReactionStatus() {
	}

	/** full constructor */
	public ReactionStatus(String reactionStatus, Set<Reaction> reactions) {
		this.reactionStatus = reactionStatus;
		this.reactions = reactions;
	}

	public Integer getReactionStatusId() {
		return reactionStatusId;
	}

	public void setReactionStatusId(Integer reactionStatusId) {
		this.reactionStatusId = reactionStatusId;
	}

	public String getReactionStatus() {
		return reactionStatus;
	}

	public void setReactionStatus(String reactionStatus) {
		this.reactionStatus = reactionStatus;
	}

	public Set<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(Set<Reaction> reactions) {
		this.reactions = reactions;
	}

	public Integer getStatusType() {
		return statusType;
	}

	public void setStatusType(Integer statusType) {
		this.statusType = statusType;
	}

	
	

}