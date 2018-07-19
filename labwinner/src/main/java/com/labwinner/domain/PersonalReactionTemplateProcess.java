package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	/**
	 * PersonalReactionTemplateProcess entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 个人试验模板步骤表
	 */
public class PersonalReactionTemplateProcess implements java.io.Serializable {

	/**
	 * 步骤主键
	 */
	private Integer personalProcessId;
	
	/**
	 * 个人模板主键
	 */
	private PersonalTemplate personalTemplate;
	
	/**
	 * 步骤名称
	 */
	private String personalProcessName;
	
	/**
	 * 步骤序号
	 */
	private Integer personalStepNumber;
	
	/**
	 * 步骤备注
	 */
	private String remark;
	
	/**
	 * 创建人
	 */
	private String creater;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 修改人
	 */
	private String modifier;
	
	/**
	 * 修改日期
	 */
	private Date modifyDate;
	
	/**
	 * 个人试验模板参数表
	 */
	private List<PersonalReactionTemplateParameter> personalReactionTemplateParameters = new ArrayList<PersonalReactionTemplateParameter>(
			0);

	// Constructors

	/** default constructor */
	public PersonalReactionTemplateProcess() {
	}

	public PersonalReactionTemplateProcess(
			Integer personalProcessId,
			PersonalTemplate personalTemplate,
			String personalProcessName,
			Integer personalStepNumber,
			String remark,
			String creater,
			Date createDate,
			String modifier,
			Date modifyDate,
			List<PersonalReactionTemplateParameter> personalReactionTemplateParameters) {
		super();
		this.personalProcessId = personalProcessId;
		this.personalTemplate = personalTemplate;
		this.personalProcessName = personalProcessName;
		this.personalStepNumber = personalStepNumber;
		this.remark = remark;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.personalReactionTemplateParameters = personalReactionTemplateParameters;
	}

	public Integer getPersonalProcessId() {
		return personalProcessId;
	}

	public void setPersonalProcessId(Integer personalProcessId) {
		this.personalProcessId = personalProcessId;
	}

	public PersonalTemplate getPersonalTemplate() {
		return personalTemplate;
	}

	public void setPersonalTemplate(PersonalTemplate personalTemplate) {
		this.personalTemplate = personalTemplate;
	}

	public String getPersonalProcessName() {
		return personalProcessName;
	}

	public void setPersonalProcessName(String personalProcessName) {
		this.personalProcessName = personalProcessName;
	}

	public Integer getPersonalStepNumber() {
		return personalStepNumber;
	}

	public void setPersonalStepNumber(Integer personalStepNumber) {
		this.personalStepNumber = personalStepNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<PersonalReactionTemplateParameter> getPersonalReactionTemplateParameters() {
		return personalReactionTemplateParameters;
	}

	public void setPersonalReactionTemplateParameters(
			List<PersonalReactionTemplateParameter> personalReactionTemplateParameters) {
		this.personalReactionTemplateParameters = personalReactionTemplateParameters;
	}


}