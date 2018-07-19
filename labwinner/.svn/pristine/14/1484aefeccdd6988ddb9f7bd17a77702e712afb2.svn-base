package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

	/**
	 * ReactionDesign entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 试验设计基础信息表
	 */
public class ReactionDesign implements java.io.Serializable {

	/**
	 * 试验设计主键
	 */
	private Integer reactionDesignId;
	
	/**
	 * 项目主键
	 */
	private ProjectBasicInfo projectBasicInfo;
	
	/**
	 * 项目试验负责人
	 */
	private ProjectNumber projectNumber;
	
	/**
	 * 非项目试验负责人
	 */
	private SysUser sysUser;
	
	/**
	 * 试验次数
	 */
	private Integer reactionNum;
	
	/**
	 * 试验组名称
	 */
	private String reactionGroupName;
	
	/**
	 * 步骤次数
	 */
	private Integer processNum;
	
	/**
	 * 试验试验设计状态
	 */
	private Integer type;
	
	/**
	 * 预计开始时间
	 */
	private Date preStartTime;
	
	/**
	 * 预计结束时间
	 */
	private Date preEndTime;
	
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
	 * 是否生成
	 */
	private String execute;
	
	/**
	 * 试验设计步骤表
	 */
	private List<ReactionDesignProcess> reactionDesignProcesses = new ArrayList<ReactionDesignProcess>(
			0);
	
	/**
	 * 设计原料表
	 */
	private List<ReactionDesignChemical> reactionDesignChemicals = new ArrayList<ReactionDesignChemical>(
			0);
	
	/**
	 * 试验生成记录表
	 */
	private List<ReactionRecord> reactionRecords = new ArrayList<ReactionRecord>(0);
	
	/**
	 * 试验设计溶液表
	 */
	private List<ReactionDesignSolution> reactionDesignSolutions = new ArrayList<ReactionDesignSolution>(0);
	
	/**
	 * 设计工艺关系表
	 */
	private List<DesignTechnologyRelation> designTechnologyRelations = new ArrayList<DesignTechnologyRelation>(0);
	
	/**
	 * 试验基本信息表
	 */
	private List<Reaction> reactions = new ArrayList<Reaction>(0);

	public ReactionDesign() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReactionDesign(Integer reactionDesignId,
			ProjectBasicInfo projectBasicInfo, ProjectNumber projectNumber,
			SysUser sysUser, Integer reactionNum, String reactionGroupName,
			Integer processNum, Integer type, Date preStartTime,
			Date preEndTime, String creater, Date createDate, String modifier,
			Date modifyDate, String execute,
			List<ReactionDesignProcess> reactionDesignProcesses,
			List<ReactionDesignChemical> reactionDesignChemicals,
			List<ReactionRecord> reactionRecords,
			List<ReactionDesignSolution> reactionDesignSolutions,
			List<DesignTechnologyRelation> designTechnologyRelations,
			List<Reaction> reactions) {
		super();
		this.reactionDesignId = reactionDesignId;
		this.projectBasicInfo = projectBasicInfo;
		this.projectNumber = projectNumber;
		this.sysUser = sysUser;
		this.reactionNum = reactionNum;
		this.reactionGroupName = reactionGroupName;
		this.processNum = processNum;
		this.type = type;
		this.preStartTime = preStartTime;
		this.preEndTime = preEndTime;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.execute = execute;
		this.reactionDesignProcesses = reactionDesignProcesses;
		this.reactionDesignChemicals = reactionDesignChemicals;
		this.reactionRecords = reactionRecords;
		this.reactionDesignSolutions = reactionDesignSolutions;
		this.designTechnologyRelations = designTechnologyRelations;
		this.reactions = reactions;
	}

	public Integer getReactionDesignId() {
		return reactionDesignId;
	}

	public void setReactionDesignId(Integer reactionDesignId) {
		this.reactionDesignId = reactionDesignId;
	}

	public ProjectBasicInfo getProjectBasicInfo() {
		return projectBasicInfo;
	}

	public void setProjectBasicInfo(ProjectBasicInfo projectBasicInfo) {
		this.projectBasicInfo = projectBasicInfo;
	}

	public ProjectNumber getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(ProjectNumber projectNumber) {
		this.projectNumber = projectNumber;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Integer getReactionNum() {
		return reactionNum;
	}

	public void setReactionNum(Integer reactionNum) {
		this.reactionNum = reactionNum;
	}

	public String getReactionGroupName() {
		return reactionGroupName;
	}

	public void setReactionGroupName(String reactionGroupName) {
		this.reactionGroupName = reactionGroupName;
	}

	public Integer getProcessNum() {
		return processNum;
	}

	public void setProcessNum(Integer processNum) {
		this.processNum = processNum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getPreStartTime() {
		return preStartTime;
	}

	public void setPreStartTime(Date preStartTime) {
		this.preStartTime = preStartTime;
	}

	public Date getPreEndTime() {
		return preEndTime;
	}

	public void setPreEndTime(Date preEndTime) {
		this.preEndTime = preEndTime;
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

	public String getExecute() {
		return execute;
	}

	public void setExecute(String execute) {
		this.execute = execute;
	}

	public List<ReactionDesignProcess> getReactionDesignProcesses() {
		return reactionDesignProcesses;
	}

	public void setReactionDesignProcesses(
			List<ReactionDesignProcess> reactionDesignProcesses) {
		this.reactionDesignProcesses = reactionDesignProcesses;
	}

	public List<ReactionDesignChemical> getReactionDesignChemicals() {
		return reactionDesignChemicals;
	}

	public void setReactionDesignChemicals(
			List<ReactionDesignChemical> reactionDesignChemicals) {
		this.reactionDesignChemicals = reactionDesignChemicals;
	}

	public List<ReactionRecord> getReactionRecords() {
		return reactionRecords;
	}

	public void setReactionRecords(List<ReactionRecord> reactionRecords) {
		this.reactionRecords = reactionRecords;
	}

	public List<ReactionDesignSolution> getReactionDesignSolutions() {
		return reactionDesignSolutions;
	}

	public void setReactionDesignSolutions(
			List<ReactionDesignSolution> reactionDesignSolutions) {
		this.reactionDesignSolutions = reactionDesignSolutions;
	}

	public List<DesignTechnologyRelation> getDesignTechnologyRelations() {
		return designTechnologyRelations;
	}

	public void setDesignTechnologyRelations(
			List<DesignTechnologyRelation> designTechnologyRelations) {
		this.designTechnologyRelations = designTechnologyRelations;
	}

	public List<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}


	
}