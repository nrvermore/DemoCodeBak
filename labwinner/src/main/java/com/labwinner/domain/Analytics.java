package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Analytics entity. @author MyEclipse Persistence Tools
 */
    /**
     * 分析表
     */
public class Analytics implements java.io.Serializable {

	/**
	 * 分析主键
	 */
	private Integer analyticsId;
	
	/**
	 * 试验基本信息表主键
	 */
	private Reaction reaction;
	
	/**
	 * 试验记录表主键
	 */
	private ReactionProcess reactionProcess;
	
	/**
	 * 分析名称
	 */
	private String analyticsName;
	
	/**
	 * 分析内容
	 */
	private String analyticsContent;
	
	/**
	 * 创建人
	 */
	private Integer creater;
	
	private SysUser creatSysUser;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 修改人
	 */
	private Integer modifier;
	
	/**
	 * 修改日期
	 */
	private Date modifyDate;
	
	/**
	 * 分析设备关系表
	 */
	private List<AnalyticsDevice> analyticsDevices = new ArrayList<AnalyticsDevice>(
			0);
	
	/**
	 * 分析附件表
	 */
	private List<AnalyticalAttachment> analyticalAttachments = new ArrayList<AnalyticalAttachment>(
			0);

	// Constructors

	/** default constructor */
	public Analytics() {
	}

	/** full constructor */
	public Analytics(Reaction reaction, ReactionProcess reactionProcess,
			String analyticsName, String analyticsContent, Integer creater,
			Date createDate, Integer modifier, Date modifyDate,
			List<AnalyticsDevice> analyticsDevices,
			List<AnalyticalAttachment> analyticalAttachments) {
		this.reaction = reaction;
		this.reactionProcess = reactionProcess;
		this.analyticsName = analyticsName;
		this.analyticsContent = analyticsContent;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.analyticsDevices = analyticsDevices;
		this.analyticalAttachments = analyticalAttachments;
	}

	public Integer getAnalyticsId() {
		return analyticsId;
	}

	public void setAnalyticsId(Integer analyticsId) {
		this.analyticsId = analyticsId;
	}

	public Reaction getReaction() {
		return reaction;
	}

	public void setReaction(Reaction reaction) {
		this.reaction = reaction;
	}

	public ReactionProcess getReactionProcess() {
		return reactionProcess;
	}

	public void setReactionProcess(ReactionProcess reactionProcess) {
		this.reactionProcess = reactionProcess;
	}

	public String getAnalyticsName() {
		return analyticsName;
	}

	public void setAnalyticsName(String analyticsName) {
		this.analyticsName = analyticsName;
	}

	public String getAnalyticsContent() {
		return analyticsContent;
	}

	public void setAnalyticsContent(String analyticsContent) {
		this.analyticsContent = analyticsContent;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<AnalyticsDevice> getAnalyticsDevices() {
		return analyticsDevices;
	}

	public void setAnalyticsDevices(List<AnalyticsDevice> analyticsDevices) {
		this.analyticsDevices = analyticsDevices;
	}

	public List<AnalyticalAttachment> getAnalyticalAttachments() {
		return analyticalAttachments;
	}

	public void setAnalyticalAttachments(
			List<AnalyticalAttachment> analyticalAttachments) {
		this.analyticalAttachments = analyticalAttachments;
	}

	public SysUser getCreatSysUser() {
		return creatSysUser;
	}

	public void setCreatSysUser(SysUser creatSysUser) {
		this.creatSysUser = creatSysUser;
	}


}