package com.labwinner.domain;

import java.util.Date;



	/**
	 * KnowledgeClassifyPostil entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 批注记录表
	 */
public class KnowledgeClassifyPostil implements java.io.Serializable {

	/**
	 * 批注巨鹿主键
	 */
	private Integer knowledgeClassifyPostilId;
	
	/**
	 * 批注用户主键
	 */
	private SysUser sysUser;
	
	/**
	 * 知识附件主键
	 */
	private KnowledgeAcc knowledgeAcc;
	
	/**
	 * 批注或者
	 */
	private Integer postilId;
	
	/**
	 * 回复批注记录主键
	 */
	private String replyContent;
	
	/**
	 * 回复内容
	 */
	private Integer replyRecordId;
	
	/**
	 * 回复或者批注时间
	 */
	private Date replyDate;


	// Constructors

	/** default constructor */
	public KnowledgeClassifyPostil() {
	}

	/** full constructor */
	public KnowledgeClassifyPostil(SysUser sysUser, KnowledgeAcc knowledgeAcc,Integer postilId,
			String replyContent, Integer replyRecordId,Date replyDate) {
		this.sysUser = sysUser;
		this.postilId=postilId;
		this.knowledgeAcc = knowledgeAcc;
		this.replyContent = replyContent;
		this.replyRecordId = replyRecordId;
		this.replyDate=replyDate;
	}

	public Integer getKnowledgeClassifyPostilId() {
		return knowledgeClassifyPostilId;
	}

	public void setKnowledgeClassifyPostilId(Integer knowledgeClassifyPostilId) {
		this.knowledgeClassifyPostilId = knowledgeClassifyPostilId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public KnowledgeAcc getKnowledgeAcc() {
		return knowledgeAcc;
	}

	public void setKnowledgeAcc(KnowledgeAcc knowledgeAcc) {
		this.knowledgeAcc = knowledgeAcc;
	}



	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}



	public Integer getReplyRecordId() {
		return replyRecordId;
	}

	public void setReplyRecordId(Integer replyRecordId) {
		this.replyRecordId = replyRecordId;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public Integer getPostilId() {
		return postilId;
	}

	public void setPostilId(Integer postilId) {
		this.postilId = postilId;
	}

	

}