package com.labwinner.domain;

import java.util.Date;


	/**
	 * JournalArticle entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 科技论文信息表
	 */
public class JournalArticle implements java.io.Serializable {

	/**
	 * 科技论文主键
	 */
	private Integer journalArticleId;
	
	/**
	 * 用户主键
	 */
	private SysUser sysUser;
	
	/**
	 * 科技论文标题
	 */
	private String title;
	
	/**
	 * 科技论文作者
	 */
	private String author;
	
	/**
	 * 年份
	 */
	private String writeDate;
	
	/**
	 * 科技论文关键字
	 */
	private String keyWords;
	
	/**
	 * 科技论文摘要
	 */
	private String abstract_;
	
	/**
	 * 科技论文作者单位
	 */
	private String authorUnit;
	
	/**
	 * 杂志信息
	 */
	private Magazine magazine;
	
	/**
	 * 附件信息
	 */
	private  KnowledgeAcc knowledgeAcc;
	/**
	 * 页码
	 */
	private String pageNum;
	
	/**
	 * ISSN
	 */
	private String issn;
	
	/**
	 * 图书编目号码
	 */
	private String callNumber;
	
	/**
	 * 数据库来源
	 */
	private String databaseSources;
	
	/**
	 * 上传时间
	 */
	private Date uploadTime;
	
	/**
	 * ISSUE
	 */
	private String issue;
	
	/**
	 * 卷号
	 */
	private String volume;
	/**
	 * 保密级别
	 */
	private SecureRank secureRank;
	/**
	 * pdf页码
	 */
	private String pdfCode;
	
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
	 * 领域分类
	 */
	private MaterialField materialField;
	
	
	private String knowledgeField;
	/**
	 * 数据来源
	 */
	private Integer source;

	// Constructors

	/** default constructor */
	public JournalArticle() {
	}

	/** full constructor */
	public JournalArticle(SysUser sysUser, String title, String author,
			String writeDate, String keyWords, String abstract_,
			String authorUnit, Magazine magazine ,KnowledgeAcc knowledgeAcc, String pageNum, String issn,
			String callNumber, String databaseSources, Date uploadTime,
			String issue, String volume,SecureRank secureRank,String pdfCode, String creater, Date createDate,
			String modifier, Date modifyDate,MaterialField materialField,String knowledgeField,Integer source) {
		this.sysUser = sysUser;
		this.title = title;
		this.author = author;
		this.writeDate = writeDate;
		this.keyWords = keyWords;
		this.abstract_ = abstract_;
		this.authorUnit = authorUnit;
		this.magazine = magazine;
		this.knowledgeAcc=knowledgeAcc;
		this.pageNum = pageNum;
		this.issn = issn;
		this.callNumber = callNumber;
		this.databaseSources = databaseSources;
		this.uploadTime = uploadTime;
		this.issue = issue;
		this.volume = volume;
		this.secureRank=secureRank;
		this.pdfCode=pdfCode;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.materialField=materialField;
		this.knowledgeField=knowledgeField;
		this.source=source;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
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

	public String getAuthorUnit() {
		return authorUnit;
	}

	public void setAuthorUnit(String authorUnit) {
		this.authorUnit = authorUnit;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	public String getDatabaseSources() {
		return databaseSources;
	}

	public void setDatabaseSources(String databaseSources) {
		this.databaseSources = databaseSources;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
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



	public SecureRank getSecureRank() {
		return secureRank;
	}

	public void setSecureRank(SecureRank secureRank) {
		this.secureRank = secureRank;
	}

	public String getPdfCode() {
		return pdfCode;
	}

	public void setPdfCode(String pdfCode) {
		this.pdfCode = pdfCode;
	}

	public KnowledgeAcc getKnowledgeAcc() {
		return knowledgeAcc;
	}

	public void setKnowledgeAcc(KnowledgeAcc knowledgeAcc) {
		this.knowledgeAcc = knowledgeAcc;
	}

	public MaterialField getMaterialField() {
		return materialField;
	}

	public void setMaterialField(MaterialField materialField) {
		this.materialField = materialField;
	}

	
	
	public String getKnowledgeField() {
		return knowledgeField;
	}

	public void setKnowledgeField(String knowledgeField) {
		this.knowledgeField = knowledgeField;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	

}