package com.labwinner.domain;


public class JournalUser implements java.io.Serializable{
	
	private Integer journalUserId;
	
	private Integer journalArticleId;
	
	private SysUser sysUser;
	
	private KnowledgeClassify knowledgeClassify;
	
	public JournalUser (){}

	public JournalUser (Integer journalUserId,Integer journalArticleId,SysUser sysUser,KnowledgeClassify knowledgeClassify){
		this.journalUserId=journalUserId;
		this.journalArticleId=journalArticleId;
		this.sysUser=sysUser;
		this.knowledgeClassify=knowledgeClassify;
	}
	public Integer getJournalUserId() {
		return journalUserId;
	}

	public void setJournalUserId(Integer journalUserId) {
		this.journalUserId = journalUserId;
	}

	public Integer getJournalArticleId() {
		return journalArticleId;
	}

	public void setJournalArticleId(Integer journalArticleId) {
		this.journalArticleId = journalArticleId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public KnowledgeClassify getKnowledgeClassify() {
		return knowledgeClassify;
	}

	public void setKnowledgeClassify(KnowledgeClassify knowledgeClassify) {
		this.knowledgeClassify = knowledgeClassify;
	}


	
	

}
