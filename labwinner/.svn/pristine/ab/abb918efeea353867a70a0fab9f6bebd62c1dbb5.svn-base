package com.labwinner.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	/**
	 * ReactionDesignProcess entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 试验设计步骤表
	 */
public class ReactionDesignProcess implements java.io.Serializable {

	/**
	 * 试验设计步骤主键
	 */
	private Integer reactionDesignProcessId;
	
	/**
	 * 试验设计主键
	 */
	private ReactionDesign reactionDesign;
	
	/**
	 * 步骤序号
	 */
	private Integer process;
	
	/**
	 * 步骤名称
	 */
	private String processName;
	
	/**
	 * 步骤备注
	 */
	private String remark;
	
	/**
	 * 试验设计参数表
	 */
	private List<ReactionDesignParameter> reactionDesignParameters = new ArrayList<ReactionDesignParameter>(
			0);

	// Constructors

	/** default constructor */
	public ReactionDesignProcess() {
	}

	/** full constructor */
	public ReactionDesignProcess(ReactionDesign reactionDesign, Integer process,
			String processName,
			List<ReactionDesignParameter> reactionDesignParameters) {
		this.reactionDesign = reactionDesign;
		this.process = process;
		this.processName = processName;
		this.reactionDesignParameters = reactionDesignParameters;
	}

	public Integer getReactionDesignProcessId() {
		return reactionDesignProcessId;
	}

	public void setReactionDesignProcessId(Integer reactionDesignProcessId) {
		this.reactionDesignProcessId = reactionDesignProcessId;
	}

	public ReactionDesign getReactionDesign() {
		return reactionDesign;
	}

	public void setReactionDesign(ReactionDesign reactionDesign) {
		this.reactionDesign = reactionDesign;
	}

	public Integer getProcess() {
		return process;
	}

	public void setProcess(Integer process) {
		this.process = process;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public List<ReactionDesignParameter> getReactionDesignParameters() {
		return reactionDesignParameters;
	}

	public void setReactionDesignParameters(
			List<ReactionDesignParameter> reactionDesignParameters) {
		this.reactionDesignParameters = reactionDesignParameters;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}