package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	/**
	 * PersonalTemplate entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 个人模板表
	 */
public class PersonalTemplate implements java.io.Serializable {

	/**
	 * 个人模板主键
	 */
	private Integer personalTemplateId;
	
	/**
	 * 版本号
	 */
	private String version;
	
	/**
	 * 用户主键
	 */
	private SysUser sysUser;
	
	/**
	 * 模板名称
	 */
	private String templateName;
	
	/**
	 * 发布日期
	 */
	private Date publishDate;
	
	/**
	 * 共享范围
	 */
	private Integer shareScope;
	
	/**
	 * 创建人
	 */
	private SysUser creater;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 修改人
	 */
	private SysUser modifier;
	
	/**
	 * 修改日期
	 */
	private Date modifyDate;
	
	/**
	 * 模块简介
	 */
	private String templateIntro;
	
	/**
	 * 访问权限结果
	 */
	private Integer result;
	
	/**
	 * 个人试验模板步骤表
	 */
	private List<PersonalReactionTemplateProcess> personalReactionTemplateProcesses = new ArrayList<PersonalReactionTemplateProcess>(
			0);
	/**
	 * 共享项目表
	 */
	private List<ShareProject> shareProjects = new ArrayList<ShareProject>(0);
	public PersonalTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PersonalTemplate(
			Integer personalTemplateId,
			String version,
			SysUser sysUser,
			String templateName,
			Date publishDate,
			Integer shareScope,
			SysUser creater,
			Date createDate,
			SysUser modifier,
			Date modifyDate,
			String templateIntro,
			List<PersonalReactionTemplateProcess> personalReactionTemplateProcesses,
			List<ShareProject> shareProjects) {
		super();
		this.personalTemplateId = personalTemplateId;
		this.version = version;
		this.sysUser = sysUser;
		this.templateName = templateName;
		this.publishDate = publishDate;
		this.shareScope = shareScope;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.templateIntro = templateIntro;
		this.personalReactionTemplateProcesses = personalReactionTemplateProcesses;
		this.shareProjects = shareProjects;
	}
	public Integer getPersonalTemplateId() {
		return personalTemplateId;
	}
	public void setPersonalTemplateId(Integer personalTemplateId) {
		this.personalTemplateId = personalTemplateId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Integer getShareScope() {
		return shareScope;
	}
	public void setShareScope(Integer shareScope) {
		this.shareScope = shareScope;
	}
	public SysUser getCreater() {
		return creater;
	}
	public void setCreater(SysUser creater) {
		this.creater = creater;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public SysUser getModifier() {
		return modifier;
	}
	public void setModifier(SysUser modifier) {
		this.modifier = modifier;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getTemplateIntro() {
		return templateIntro;
	}
	public void setTemplateIntro(String templateIntro) {
		this.templateIntro = templateIntro;
	}
	public List<PersonalReactionTemplateProcess> getPersonalReactionTemplateProcesses() {
		return personalReactionTemplateProcesses;
	}
	public void setPersonalReactionTemplateProcesses(
			List<PersonalReactionTemplateProcess> personalReactionTemplateProcesses) {
		this.personalReactionTemplateProcesses = personalReactionTemplateProcesses;
	}
	public List<ShareProject> getShareProjects() {
		return shareProjects;
	}
	public void setShareProjects(List<ShareProject> shareProjects) {
		this.shareProjects = shareProjects;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	
}