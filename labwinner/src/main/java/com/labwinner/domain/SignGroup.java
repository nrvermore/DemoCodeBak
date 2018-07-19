package com.labwinner.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 专家实体类
 * @author liuhq
 * @version V1.0
 * @date 2017年5月17日 下午12:06:11
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class SignGroup implements java.io.Serializable {

	// Fields

	/**
	 * 专家主键
	 */
	private Integer signGroupId;

	
	
	private SysUser sysUser;
	
	private Date createDate;
	

	public SignGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignGroup(Integer signGroupId, SysUser sysUser, Date createDate) {
		super();
		this.signGroupId = signGroupId;
		this.sysUser = sysUser;
		this.createDate = createDate;
	}

	public Integer getSignGroupId() {
		return signGroupId;
	}

	public void setSignGroupId(Integer signGroupId) {
		this.signGroupId = signGroupId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	

	
	
	
	
}