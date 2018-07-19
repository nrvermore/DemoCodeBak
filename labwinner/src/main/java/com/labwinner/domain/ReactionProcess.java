package com.labwinner.domain;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
	/**
	 * ReactionProcess entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 试验记录表
	 */
public class ReactionProcess implements java.io.Serializable {

	/**
	 * 试验记录主键
	 */
	private Integer reactionProcessId;
	
	/**
	 * 试验基本信息主键
	 */
	private Reaction reaction;
	
	/**
	 * 试验记录
	 */
	private String reactionProcess;
	
	/**
	 * 步骤名称
	 */
	private String processName;
	
	/**
	 * 步骤序号
	 */
	private Integer process;
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	/**
	 * 步骤状态
	 */
	private ReactionStatus reactionStatus;
	
	/**
	 * 步骤备注
	 */
	private String remark;
	
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
	
	private List<CommentEntity> commentEntities = new ArrayList<CommentEntity>(0);
	
	/**
	 * 试验设备关系表
	 */
	private List<ReactionDevice> reactionDevices = new ArrayList<ReactionDevice>(0);
	
	/**
	 * 试验样品表
	 */
	private List<Prototype> prototypes = new ArrayList<Prototype>(0);
	
	/**
	 * 随手记表
	 */
	private List<Note> notes = new ArrayList<Note>(0);
	
	/**
	 * 执行原料表
	 */
	private List<ExecuteChemical> executeChemicals = new ArrayList<ExecuteChemical>(
			0);
	
	/**
	 * 执行原料表
	 */
	private List<ExecuteChemicalGroup> executeChemicalGroups = new ArrayList<ExecuteChemicalGroup>(
			0);
	
	/**
	 * 执行溶液表
	 */
	private List<ExecuteSolution> executeSolutions = new ArrayList<ExecuteSolution>(
			0);
	
	/**
	 * 分析表
	 */
	private List<Analytics> analyticses = new ArrayList<Analytics>(0);
	
	/**
	 * 测试表
	 */
	private List<ReactionTest> reactionTests = new ArrayList<ReactionTest>(0);
	
	/**
	 * 团队协助
	 */
	private List<TeamAssist> teamAssists = new ArrayList<TeamAssist>(0);
	
	/**
	 * 试验执行参数表
	 */
	private List<ReactionExecuteParameter> reactionExecuteParameters = new ArrayList<ReactionExecuteParameter>(
			0);

	// Constructors

	/** default constructor */
	public ReactionProcess() {
	}

	/** full constructor */
	public ReactionProcess(Reaction reaction,Integer process,
			String reactionProcess, String processName, Date startTime,
			Date endTime, ReactionStatus reactionStatus, String creater,
			Date createDate, String modifier, Date modifyDate,
			List<ReactionDevice> reactionDevices, List<Prototype> prototypes,
			List<Note> notes, List<ExecuteChemical> executeChemicals,
			List<Analytics> analyticses, List<TeamAssist> teamAssists,
			List<ReactionExecuteParameter> reactionExecuteParameters) {
		this.reaction = reaction;
		this.reactionProcess = reactionProcess;
		this.processName = processName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.reactionStatus = reactionStatus;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.reactionDevices = reactionDevices;
		this.prototypes = prototypes;
		this.notes = notes;
		this.process = process;
		this.teamAssists = teamAssists;
		this.executeChemicals = executeChemicals;
		this.analyticses = analyticses;
		this.reactionExecuteParameters = reactionExecuteParameters;
	}

	public Integer getReactionProcessId() {
		return reactionProcessId;
	}

	public void setReactionProcessId(Integer reactionProcessId) {
		this.reactionProcessId = reactionProcessId;
	}

	public Reaction getReaction() {
		return reaction;
	}

	public void setReaction(Reaction reaction) {
		this.reaction = reaction;
	}

	public String getReactionProcess() {
		return reactionProcess;
	}

	public void setReactionProcess(String reactionProcess) {
		this.reactionProcess = reactionProcess;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Integer getProcess() {
		return process;
	}

	public void setProcess(Integer process) {
		this.process = process;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public ReactionStatus getReactionStatus() {
		return reactionStatus;
	}

	public void setReactionStatus(ReactionStatus reactionStatus) {
		this.reactionStatus = reactionStatus;
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

	public List<ReactionDevice> getReactionDevices() {
		return reactionDevices;
	}

	public void setReactionDevices(List<ReactionDevice> reactionDevices) {
		this.reactionDevices = reactionDevices;
	}

	public List<Prototype> getPrototypes() {
		return prototypes;
	}

	public void setPrototypes(List<Prototype> prototypes) {
		this.prototypes = prototypes;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<ExecuteChemical> getExecuteChemicals() {
		return executeChemicals;
	}

	public void setExecuteChemicals(List<ExecuteChemical> executeChemicals) {
		this.executeChemicals = executeChemicals;
	}

	public List<Analytics> getAnalyticses() {
		return analyticses;
	}

	public void setAnalyticses(List<Analytics> analyticses) {
		this.analyticses = analyticses;
	}

	public List<TeamAssist> getTeamAssists() {
		return teamAssists;
	}

	public void setTeamAssists(List<TeamAssist> teamAssists) {
		this.teamAssists = teamAssists;
	}

	public List<ReactionExecuteParameter> getReactionExecuteParameters() {
		return reactionExecuteParameters;
	}

	public void setReactionExecuteParameters(
			List<ReactionExecuteParameter> reactionExecuteParameters) {
		this.reactionExecuteParameters = reactionExecuteParameters;
	}

	public List<ReactionTest> getReactionTests() {
		return reactionTests;
	}

	public void setReactionTests(List<ReactionTest> reactionTests) {
		this.reactionTests = reactionTests;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ExecuteSolution> getExecuteSolutions() {
		return executeSolutions;
	}

	public void setExecuteSolutions(List<ExecuteSolution> executeSolutions) {
		this.executeSolutions = executeSolutions;
	}

	public List<ExecuteChemicalGroup> getExecuteChemicalGroups() {
		return executeChemicalGroups;
	}

	public void setExecuteChemicalGroups(
			List<ExecuteChemicalGroup> executeChemicalGroups) {
		this.executeChemicalGroups = executeChemicalGroups;
	}

	public List<CommentEntity> getCommentEntities() {
		return commentEntities;
	}

	public void setCommentEntities(List<CommentEntity> commentEntities) {
		this.commentEntities = commentEntities;
	}

	
}