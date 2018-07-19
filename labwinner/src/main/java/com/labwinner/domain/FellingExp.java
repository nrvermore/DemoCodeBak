package com.labwinner.domain;

import java.util.Date;

	/**
	 * FellingExp entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 心得体会信息表
	 */
public class FellingExp implements java.io.Serializable {

	/**
	 * 心得体会信息主键
	 */
	private Integer fellingExpId;
	
	/**
	 * 用户主键
	 */
	private SysUser sysUser;
	
	/**
	 * 心得体会标题
	 */
	private String title;
	
	/**
	 * 心得体会内容
	 */
	private String fellingExpContent;
	
	/**
	 * 上传时间
	 */
	private Date uploadTime;
	
	/**
	 * 来源
	 */
	private String resources;
	
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

	// Constructors

	/** default constructor */
	public FellingExp() {
	}

	/** full constructor */
	public FellingExp(SysUser sysUser, String title, String fellingExpContent,
			Date uploadTime, String resources, String creater, Date createDate,
			String modifier, Date modifyDate) {
		this.sysUser = sysUser;
		this.title = title;
		this.fellingExpContent = fellingExpContent;
		this.uploadTime = uploadTime;
		this.resources = resources;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
	}

	public Integer getFellingExpId() {
		return fellingExpId;
	}

	public void setFellingExpId(Integer fellingExpId) {
		this.fellingExpId = fellingExpId;
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

	public String getFellingExpContent() {
		return fellingExpContent;
	}

	public void setFellingExpContent(String fellingExpContent) {
		this.fellingExpContent = fellingExpContent;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
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

}