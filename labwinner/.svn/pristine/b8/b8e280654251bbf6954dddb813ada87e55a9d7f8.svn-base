package com.labwinner.domain;



/**
 * Attachment entity. @author MyEclipse Persistence Tools
 */
    /**
     * 消息附件表
     */
public class Attachment implements java.io.Serializable {

	/**
     * 附件主键
     */
	private Integer attachmentId;
	
	/**
     * 消息主键
     */
	private Message message;
	
	/**
     * 附件名称
     */
	private String attachmentName;
	
	/**
     * 附件内容
     */
	private String attachmentContent;
	
	/**
     * 附件路径
     */
	private String urlPath;
	
	/**
     * 附件大小
     */
	private String size;
	
	private String fileType;
	
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
	public Attachment() {
	}

	/** full constructor */
	public Attachment(Message message, String attachmentName,
			String attachmentContent) {
		this.message = message;
		this.attachmentName = attachmentName;
		this.attachmentContent = attachmentContent;
	}

	public Integer getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentContent() {
		return attachmentContent;
	}

	public void setAttachmentContent(String attachmentContent) {
		this.attachmentContent = attachmentContent;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
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