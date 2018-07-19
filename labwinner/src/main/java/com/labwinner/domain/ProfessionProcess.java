package com.labwinner.domain;

import java.util.ArrayList;
import java.util.List;

	/**
	 * ProfessionProcess entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 行业试验模板步骤表
	 */
public class ProfessionProcess implements java.io.Serializable {

	/**
	 * 步骤主键
	 */
	private Integer processId;
	
	/**
	 *行业模板主键 
	 */
	private IndustryReactionTemplate industryReactionTemplate;
	
	/**
	 * 步骤名称
	 */
	private String processName;
	
	/**
	 * 步骤序号
	 */
	private Integer stepNumber;
	
	/**
	 * 行业试验模板参数表
	 */
	private List<IndustryReactionTemplateParameter> industryReactionTemplateParameters = new ArrayList<IndustryReactionTemplateParameter>(
			0);

	public ProfessionProcess() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfessionProcess(
			Integer processId,
			IndustryReactionTemplate industryReactionTemplate,
			String processName,
			Integer stepNumber,
			List<IndustryReactionTemplateParameter> industryReactionTemplateParameters) {
		super();
		this.processId = processId;
		this.industryReactionTemplate = industryReactionTemplate;
		this.processName = processName;
		this.stepNumber = stepNumber;
		this.industryReactionTemplateParameters = industryReactionTemplateParameters;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public IndustryReactionTemplate getIndustryReactionTemplate() {
		return industryReactionTemplate;
	}

	public void setIndustryReactionTemplate(
			IndustryReactionTemplate industryReactionTemplate) {
		this.industryReactionTemplate = industryReactionTemplate;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Integer getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(Integer stepNumber) {
		this.stepNumber = stepNumber;
	}

	public List<IndustryReactionTemplateParameter> getIndustryReactionTemplateParameters() {
		return industryReactionTemplateParameters;
	}

	public void setIndustryReactionTemplateParameters(
			List<IndustryReactionTemplateParameter> industryReactionTemplateParameters) {
		this.industryReactionTemplateParameters = industryReactionTemplateParameters;
	}

}