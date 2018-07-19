package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * ReactionParameter entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 试验参数表
	 */
public class ReactionParameter implements java.io.Serializable {

	/**
	 * 试验参数主键
	 */
	private Integer reactionParameterId;
	
	/**
	 * 试验参数名称
	 */
	private String reactionParameterName;
	
	/**
	 * 个人试验模板参数表
	 */
	private Set<PersonalReactionTemplateParameter> personalReactionTemplateParameters = new HashSet<PersonalReactionTemplateParameter>(
			0);
	
	/**
	 * 行业试验模板参数表
	 */
	private Set<IndustryReactionTemplateParameter> industryReactionTemplateParameters = new HashSet<IndustryReactionTemplateParameter>(
			0);
	
	/**
	 * 试验中心参数表
	 */
	private Set<ReactionExecuteParameter> reactionExecuteParameters = new HashSet<ReactionExecuteParameter>(
			0);
	
	/**
	 * 试验设计参数表
	 */
	private Set<ReactionDesignParameter> reactionDesignParameters = new HashSet<ReactionDesignParameter>(
			0);

	// Constructors

	/** default constructor */
	public ReactionParameter() {
	}

	/** full constructor */
	public ReactionParameter(
			String reactionParameterName,
			Set<PersonalReactionTemplateParameter> personalReactionTemplateParameters,
			Set<IndustryReactionTemplateParameter> industryReactionTemplateParameters,
			Set<ReactionExecuteParameter> reactionExecuteParameters,
			Set<ReactionDesignParameter> reactionDesignParameters) {
		this.reactionParameterName = reactionParameterName;
		this.personalReactionTemplateParameters = personalReactionTemplateParameters;
		this.industryReactionTemplateParameters = industryReactionTemplateParameters;
		this.reactionExecuteParameters = reactionExecuteParameters;
		this.reactionDesignParameters = reactionDesignParameters;
	}

	public Integer getReactionParameterId() {
		return reactionParameterId;
	}

	public void setReactionParameterId(Integer reactionParameterId) {
		this.reactionParameterId = reactionParameterId;
	}

	public String getReactionParameterName() {
		return reactionParameterName;
	}

	public void setReactionParameterName(String reactionParameterName) {
		this.reactionParameterName = reactionParameterName;
	}

	public Set<PersonalReactionTemplateParameter> getPersonalReactionTemplateParameters() {
		return personalReactionTemplateParameters;
	}

	public void setPersonalReactionTemplateParameters(
			Set<PersonalReactionTemplateParameter> personalReactionTemplateParameters) {
		this.personalReactionTemplateParameters = personalReactionTemplateParameters;
	}

	public Set<IndustryReactionTemplateParameter> getIndustryReactionTemplateParameters() {
		return industryReactionTemplateParameters;
	}

	public void setIndustryReactionTemplateParameters(
			Set<IndustryReactionTemplateParameter> industryReactionTemplateParameters) {
		this.industryReactionTemplateParameters = industryReactionTemplateParameters;
	}

	public Set<ReactionExecuteParameter> getReactionExecuteParameters() {
		return reactionExecuteParameters;
	}

	public void setReactionExecuteParameters(
			Set<ReactionExecuteParameter> reactionExecuteParameters) {
		this.reactionExecuteParameters = reactionExecuteParameters;
	}

	public Set<ReactionDesignParameter> getReactionDesignParameters() {
		return reactionDesignParameters;
	}

	public void setReactionDesignParameters(
			Set<ReactionDesignParameter> reactionDesignParameters) {
		this.reactionDesignParameters = reactionDesignParameters;
	}

	

}