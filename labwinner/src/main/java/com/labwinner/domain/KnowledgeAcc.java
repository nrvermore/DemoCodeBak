package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;


/**
 * @Description 知识附件实体类
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日 
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class KnowledgeAcc implements java.io.Serializable {

	/**
	 * 知识附件表主键
	 */
	private Integer knowledgeAccId;
	
	/**
	 * 知识分类主键
	 */
	private KnowledgeClassify knowledgeClassify;
	
	/**
	 * 知识图片
	 */
	private String image;
	
	/**
	 * 知识上传文件
	 */
	private String uploadFiles;
	
	/**
	 * 知识表主键
	 */
	private Integer knowledgeId;
	
	/**
	 * 知识附件表pdf转换状态
	 */
	private Integer pdfCount;
	
	/**
	 * 知识附件表主键
	 */
	private String pdfUrl;
	
	
	/**
	 * 知识附件表pdf转换次数
	 */
	private Integer conversionCount;
	
	/**
	 * 知识附件表pdf转换次数
	 */
	private Integer source;
	
	/**
	 * 批注记录表
	 */
	private Set<KnowledgeClassifyPostil> knowledgeClassifyPostils = new HashSet<KnowledgeClassifyPostil>(
			0);

	// Constructors

	/** default constructor */
	public KnowledgeAcc() {
	}

	/** full constructor */
	public KnowledgeAcc(KnowledgeClassify knowledgeClassify, String image,
			String uploadFiles, Integer knowledgeId,
			Set<KnowledgeClassifyPostil> knowledgeClassifyPostils,Integer pdfCount,String pdfUrl,Integer conversionCount,Integer source) {
		this.knowledgeClassify = knowledgeClassify;
		this.image = image;
		this.uploadFiles = uploadFiles;
		this.knowledgeId = knowledgeId;
		this.knowledgeClassifyPostils = knowledgeClassifyPostils;
		this.pdfCount=pdfCount;
		this.pdfUrl=pdfUrl;
		this.conversionCount=conversionCount;
		this.source=source;
	}

	public Integer getKnowledgeAccId() {
		return knowledgeAccId;
	}

	public void setKnowledgeAccId(Integer knowledgeAccId) {
		this.knowledgeAccId = knowledgeAccId;
	}

	public KnowledgeClassify getKnowledgeClassify() {
		return knowledgeClassify;
	}

	public void setKnowledgeClassify(KnowledgeClassify knowledgeClassify) {
		this.knowledgeClassify = knowledgeClassify;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(String uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public Integer getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public Set<KnowledgeClassifyPostil> getKnowledgeClassifyPostils() {
		return knowledgeClassifyPostils;
	}

	public void setKnowledgeClassifyPostils(
			Set<KnowledgeClassifyPostil> knowledgeClassifyPostils) {
		this.knowledgeClassifyPostils = knowledgeClassifyPostils;
	}

	public Integer getPdfCount() {
		return pdfCount;
	}

	public void setPdfCount(Integer pdfCount) {
		this.pdfCount = pdfCount;
	}

	public String getPdfUrl() {
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public Integer getConversionCount() {
		return conversionCount;
	}

	public void setConversionCount(Integer conversionCount) {
		this.conversionCount = conversionCount;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
	
	
	
	

}