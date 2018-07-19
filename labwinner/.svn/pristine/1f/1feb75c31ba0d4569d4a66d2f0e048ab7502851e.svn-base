package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

	
	/**
	 * IndustryReactionTemplate entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 行业试验模板表
	 */
public class IndustryReactionTemplate implements java.io.Serializable {

	/**
	 * 行业模板主键
	 */
	private Integer industryTemplateId;
	
	/**
	 * 版本号
	 */
	private String version;
	
	/**
	 * 行业主键
	 */
	private Industry industry;
	
	/**
	 * 模板名称
	 */
	private String templateName;
	
	/**
	 * 模板说明
	 */
	private String templateReadme;
	
	/**
	 * 发布日期
	 */
	private Date publishDate;
	
	/**
	 * 行业试验模板步骤表
	 */
	private List<ProfessionProcess> professionProcesses = new ArrayList<ProfessionProcess>(
			0);

	public IndustryReactionTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IndustryReactionTemplate(Integer industryTemplateId, String version,
			Industry industry, String templateName, String templateReadme,
			Date publishDate, List<ProfessionProcess> professionProcesses) {
		super();
		this.industryTemplateId = industryTemplateId;
		this.version = version;
		this.industry = industry;
		this.templateName = templateName;
		this.templateReadme = templateReadme;
		this.publishDate = publishDate;
		this.professionProcesses = professionProcesses;
	}

	public Integer getIndustryTemplateId() {
		return industryTemplateId;
	}

	public void setIndustryTemplateId(Integer industryTemplateId) {
		this.industryTemplateId = industryTemplateId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateReadme() {
		return templateReadme;
	}

	public void setTemplateReadme(String templateReadme) {
		this.templateReadme = templateReadme;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public List<ProfessionProcess> getProfessionProcesses() {
		return professionProcesses;
	}

	public void setProfessionProcesses(List<ProfessionProcess> professionProcesses) {
		this.professionProcesses = professionProcesses;
	}



}