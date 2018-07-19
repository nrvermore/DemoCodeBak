package com.labwinner.domain;

import java.util.Date;

	/**
	 * ReactionRecord entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 试验生成记录表
	 */
public class ReactionRecord implements java.io.Serializable {

	/**
	 * 试验生成记录主键
	 */
	private Integer reactionRecordId;
	
	/**
	 * 试验设计主键
	 */
	private ReactionDesign reactionDesign;
	
	/**
	 * 试验序号
	 */
	private String reactionNum;
	
	/**
	 * 试验名称
	 */
	private String reactionName;
	
	/**
	 * 是否生成
	 */
	private String execute;
	
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

	public ReactionRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReactionRecord(Integer reactionRecordId,
			ReactionDesign reactionDesign, String reactionNum,
			String reactionName, String execute, String creater,
			Date createDate, String modifier, Date modifyDate) {
		super();
		this.reactionRecordId = reactionRecordId;
		this.reactionDesign = reactionDesign;
		this.reactionNum = reactionNum;
		this.reactionName = reactionName;
		this.execute = execute;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
	}

	public Integer getReactionRecordId() {
		return reactionRecordId;
	}

	public void setReactionRecordId(Integer reactionRecordId) {
		this.reactionRecordId = reactionRecordId;
	}

	public ReactionDesign getReactionDesign() {
		return reactionDesign;
	}

	public void setReactionDesign(ReactionDesign reactionDesign) {
		this.reactionDesign = reactionDesign;
	}

	public String getReactionNum() {
		return reactionNum;
	}

	public void setReactionNum(String reactionNum) {
		this.reactionNum = reactionNum;
	}

	public String getReactionName() {
		return reactionName;
	}

	public void setReactionName(String reactionName) {
		this.reactionName = reactionName;
	}

	public String getExecute() {
		return execute;
	}

	public void setExecute(String execute) {
		this.execute = execute;
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

	// Constructors
   
	

}