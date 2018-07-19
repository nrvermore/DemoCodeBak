package com.labwinner.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	/**
	 * TeamAssist entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 团队协助表
	 */
public class TeamAssist implements java.io.Serializable {

	/**
	 * 团队协助主键
	 */
	private Integer teamAssistId;
	
	/**
	 * 成员主键
	 */
	private SysUser sysUser;
	
	/**
	 * 协助内容
	 */
	private String assistContent;
	
	/**
	 * 试验记录表
	 */
	private ReactionProcess reactionProcess;

	// Constructors

	/** default constructor */
	public TeamAssist() {
	}

	/** full constructor */

	public Integer getTeamAssistId() {
		return teamAssistId;
	}

	public void setTeamAssistId(Integer teamAssistId) {
		this.teamAssistId = teamAssistId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getAssistContent() {
		return assistContent;
	}

	public void setAssistContent(String assistContent) {
		this.assistContent = assistContent;
	}

	public ReactionProcess getReactionProcess() {
		return reactionProcess;
	}

	public void setReactionProcess(ReactionProcess reactionProcess) {
		this.reactionProcess = reactionProcess;
	}

	
}