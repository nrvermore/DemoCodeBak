package com.labwinner.domain;

	/**
	 * ProjectDocuments entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 项目文档表
	 */
public class ProjectDocuments implements java.io.Serializable {

	/**
	 * 项目文档主键
	 */
	private Integer proDocId;
	
	/**
	 * 项目主键
	 */
	private ProjectBasicInfo projectBasicInfo;
	
	/**
	 * 文档内容
	 */
	private String proDocument;
	
	/**
	 * 文档名称
	 */
	private String documentName;
	
	/**
	 * 文档名称
	 */
	private String documentUrl;
	
	/**
	 * 文档描述
	 */
	private String documentDescription;
	
	/**
	 * 转化状态
	 */
	private Integer pdfCount;
	/**
	 * 转换后pdf路径
	 */
	private String pdfUrl;
	
	
	/**
	 * pdf转换次数
	 */
	private Integer conversionCount;

	// Constructors

	/** default constructor */
	public ProjectDocuments() {
	}

	/** full constructor */
	public ProjectDocuments(ProjectBasicInfo projectBasicInfo,
			String proDocument, String documentName, String documentDescription,
			Integer pdfCount,String pdfUrl,Integer conversionCount) {
		this.projectBasicInfo = projectBasicInfo;
		this.proDocument = proDocument;
		this.documentName = documentName;
		this.documentDescription = documentDescription;
		this.pdfCount=pdfCount;
		this.pdfUrl=pdfUrl;
		this.conversionCount=conversionCount;
	}

	public Integer getProDocId() {
		return proDocId;
	}

	public void setProDocId(Integer proDocId) {
		this.proDocId = proDocId;
	}

	public ProjectBasicInfo getProjectBasicInfo() {
		return projectBasicInfo;
	}

	public void setProjectBasicInfo(ProjectBasicInfo projectBasicInfo) {
		this.projectBasicInfo = projectBasicInfo;
	}

	public String getProDocument() {
		return proDocument;
	}

	public void setProDocument(String proDocument) {
		this.proDocument = proDocument;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentDescription() {
		return documentDescription;
	}

	public void setDocumentDescription(String documentDescription) {
		this.documentDescription = documentDescription;
	}

	public String getDocumentUrl() {
		return documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
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
	
}