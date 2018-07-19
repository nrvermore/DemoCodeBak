package com.labwinner.domain;

/**
 * @Description 系统附件
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class CompanyType implements java.io.Serializable {

	private Integer companyTypeId;
	
	private String companyTypeName;

	// Constructors

	/** default constructor */
	public CompanyType() {
	}

	/** full constructor */
	public CompanyType(Integer companyTypeId, String companyTypeName) {
		
	}

	public Integer getCompanyTypeId() {
		return companyTypeId;
	}

	public void setCompanyTypeId(Integer companyTypeId) {
		this.companyTypeId = companyTypeId;
	}

	public String getCompanyTypeName() {
		return companyTypeName;
	}

	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}

	
	
}