package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description 行业
 * @author liuhq
 * @version V1.0
 * @date 2017年6月8日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class Industry implements java.io.Serializable {

	/**
	 * 行业主键
	 */
	private Integer industryId;

	/**
	 * 行业名称
	 */
	private String industryName;

	/**
	 * 行业编码
	 */
	private String industryCode;

	/**
	 * 行业试验模板表
	 */
	private Set<IndustryReactionTemplate> industryReactionTemplates = new HashSet<IndustryReactionTemplate>(
			0);

	/**
	 * 签约机构表
	 */
	private Set<SysSigningAgency> sysSigningAgencies = new HashSet<SysSigningAgency>(
			0);

	// Constructors

	/** default constructor */
	public Industry() {
	}

	/** full constructor */
	public Industry(String industryName, String industryCode,
			Set<IndustryReactionTemplate> industryReactionTemplates,
			Set<SysSigningAgency> sysSigningAgencies) {
		this.industryName = industryName;
		this.industryCode = industryCode;
		this.industryReactionTemplates = industryReactionTemplates;
		this.sysSigningAgencies = sysSigningAgencies;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public Set<IndustryReactionTemplate> getIndustryReactionTemplates() {
		return industryReactionTemplates;
	}

	public void setIndustryReactionTemplates(
			Set<IndustryReactionTemplate> industryReactionTemplates) {
		this.industryReactionTemplates = industryReactionTemplates;
	}

	public Set<SysSigningAgency> getSysSigningAgencies() {
		return sysSigningAgencies;
	}

	public void setSysSigningAgencies(Set<SysSigningAgency> sysSigningAgencies) {
		this.sysSigningAgencies = sysSigningAgencies;
	}

}