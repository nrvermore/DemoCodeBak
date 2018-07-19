package com.labwinner.domain;

import java.util.Date;

	/**
	/**
 * @Description 自写论文实体类
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日 
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class SelfPaper implements java.io.Serializable {

	/**
	 * 自写论文信息表
	 */
	private Integer selfPaperId;
	
	/**
	 * 用户主键
	 */
	private SysUser sysUser;
	
	/**
	 * 自写论文信息主键
	 */
	private String title;
	
	/**
	 * 发布时间
	 */
	private Date publishTime;
	
	/**
	 * 自写论文关键字
	 */
	private String keyWords;
	
	/**
	 * 自写论文摘要
	 */
	private String abstract_;
	
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
	 * 附件信息
	 */
	private  KnowledgeAcc knowledgeAcc;
	
	private SecureRank secureRank;

	// Constructors

	/** default constructor */
	public SelfPaper() {
	}

	/** full constructor */
	public SelfPaper(SysUser sysUser, String title, Date publishTime,
			String keyWords, String abstract_, String creater, Date createDate,
			String modifier, Date modifyDate,KnowledgeAcc knowledgeAcc,SecureRank secureRank) {
		this.sysUser = sysUser;
		this.title = title;
		this.publishTime = publishTime;
		this.keyWords = keyWords;
		this.abstract_ = abstract_;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.knowledgeAcc=knowledgeAcc;
		this.secureRank=secureRank;
	}

	public Integer getSelfPaperId() {
		return selfPaperId;
	}

	public void setSelfPaperId(Integer selfPaperId) {
		this.selfPaperId = selfPaperId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getAbstract_() {
		return abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
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

	public KnowledgeAcc getKnowledgeAcc() {
		return knowledgeAcc;
	}

	public void setKnowledgeAcc(KnowledgeAcc knowledgeAcc) {
		this.knowledgeAcc = knowledgeAcc;
	}

	public SecureRank getSecureRank() {
		return secureRank;
	}

	public void setSecureRank(SecureRank secureRank) {
		this.secureRank = secureRank;
	}

	
}