package com.labwinner.domain;

import java.sql.Timestamp;
import java.util.Date;


	/**
	 * 反馈信息实体类
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月17日 下午6:22:32
	 * 
	 * @Company 西安博文同创信息技术有限公司
	 * @Copyright Copyright (c) 2017, All rights reserved.
	 */
	
public class Feedback implements java.io.Serializable {

	/**
	 * 反馈信息主键
	 */
	private Integer feedbackId;
	
	/**
	 * 反馈内容
	 */
	private String submitFeedback;
	

	/**
	 * 联系方式
	 */
	private String contacts;
	
	/**
	 * 称呼
	 */
	private String name;
	
	/**
	 * 反馈人
	 */
	private SysUser sysUser;
	/**
	 * 反馈机构
	 */
	private SysSigningAgency sysSigningAgency;
	/**
	 * 反馈时间
	 */
	private Date feedbackTime;

	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feedback(Integer feedbackId, String submitFeedback, String contacts,
			String name, SysUser sysUser, SysSigningAgency sysSigningAgency,
			Date feedbackTime) {
		super();
		this.feedbackId = feedbackId;
		this.submitFeedback = submitFeedback;
		this.contacts = contacts;
		this.name = name;
		this.sysUser = sysUser;
		this.sysSigningAgency = sysSigningAgency;
		this.feedbackTime = feedbackTime;
	}

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getSubmitFeedback() {
		return submitFeedback;
	}

	public void setSubmitFeedback(String submitFeedback) {
		this.submitFeedback = submitFeedback;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public SysSigningAgency getSysSigningAgency() {
		return sysSigningAgency;
	}

	public void setSysSigningAgency(SysSigningAgency sysSigningAgency) {
		this.sysSigningAgency = sysSigningAgency;
	}

	public Date getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	

}