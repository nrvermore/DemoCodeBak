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
public class KnowledgeThumbsUp implements java.io.Serializable {

	/**
	 * 附件主键
	 */
	private Integer knowledgeThumbsUpId;

	/**
	 * 附件名称
	 */
	private SysUser sysUser;

	/**
	 * 备注
	 */
	private Integer count;

	/**
	 * 附件内容
	 */
	private KnowledgeAcc knowledgeAcc;
	
	/** default constructor */
	public KnowledgeThumbsUp() {
	}

	/** full constructor */
	public KnowledgeThumbsUp(Integer knowledgeThumbsUpId, SysUser sysUser, Integer count,KnowledgeAcc knowledgeAcc) {
		this.knowledgeThumbsUpId = knowledgeThumbsUpId;
		this.sysUser = sysUser;
		this.count = count;
		this.knowledgeAcc = knowledgeAcc;
	}



	public Integer getKnowledgeThumbsUpId() {
		return knowledgeThumbsUpId;
	}



	public void setKnowledgeThumbsUpId(Integer knowledgeThumbsUpId) {
		this.knowledgeThumbsUpId = knowledgeThumbsUpId;
	}



	public SysUser getSysUser() {
		return sysUser;
	}



	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}



	public Integer getCount() {
		return count;
	}



	public void setCount(Integer count) {
		this.count = count;
	}

	public KnowledgeAcc getKnowledgeAcc() {
		return knowledgeAcc;
	}

	public void setKnowledgeAcc(KnowledgeAcc knowledgeAcc) {
		this.knowledgeAcc = knowledgeAcc;
	}



	

	
	
}