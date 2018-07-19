package com.labwinner.domain;

import java.util.Date;
	
    /**
	 * MediaResource entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 媒体资源表
	 */
public class MediaResource implements java.io.Serializable {

	/**
	 * 媒体资源主键
	 */
	private Integer mediaResourceId;
	
	/**
	 * 用户主键
	 */
	private SysUser sysUser;
	
	/**
	 * URL
	 */
	private String url;
	
	/**
	 * 媒体资源标题
	 */
	private String title;
	
	/**
	 * 媒体资源作者
	 */
	private String author;
	
	/**
	 * 媒体资源分类
	 */
	private String category;
	
	/**
	 * 上传时间
	 */
	private Date uploadTime;
	
	/**
	 * 媒体资源内容
	 */
	private String content;
	
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
	 * 资源图片
	 */
	private byte[] sourceImage;
	
	/**
	 * 资源名称
	 */
	private String sourceTitle;
	
	private String imageUrl;
	
	private Integer urlType;
	
	private Integer source;

	// Constructors

	/** default constructor */
	public MediaResource() {
	}

	/** full constructor */
	public MediaResource(SysUser sysUser, String url, String title,
			String author, String category, Date uploadTime, String content,
			String creater, Date createDate, String modifier, Date modifyDate,byte[] sourceImage,String sourceTitle,String imageUrl) {
		this.sysUser = sysUser;
		this.url = url;
		this.title = title;
		this.author = author;
		this.category = category;
		this.uploadTime = uploadTime;
		this.content = content;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.sourceImage=sourceImage;
		this.sourceTitle=sourceTitle;
		this.imageUrl=imageUrl;
	}

	public Integer getMediaResourceId() {
		return mediaResourceId;
	}

	public void setMediaResourceId(Integer mediaResourceId) {
		this.mediaResourceId = mediaResourceId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public byte[] getSourceImage() {
		return sourceImage;
	}

	public void setSourceImage(byte[] sourceImage) {
		this.sourceImage = sourceImage;
	}

	public String getSourceTitle() {
		return sourceTitle;
	}

	public void setSourceTitle(String sourceTitle) {
		this.sourceTitle = sourceTitle;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getUrlType() {
		return urlType;
	}

	public void setUrlType(Integer urlType) {
		this.urlType = urlType;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
	
}