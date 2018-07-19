package com.labwinner.domain;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

	/**
	 * Reaction entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 试验基本信息表
	 */
public class Reaction implements java.io.Serializable {

	/**
	 * 试验基本信息主键
	 */
	private Integer reactionId;
	
	/**
	 * 项目主键
	 */
	private ProjectBasicInfo projectBasicInfo;
	
	/**
	 * 用户主键
	 */
	private ProjectNumber projectNumber;
	
	/**
	 * 用户主键
	 */
	private SysUser sysUser;
	
	/**
	 * 试验设计主键
	 */
	private ReactionDesign reactionDesign;
	
	/**
	 * 试验状态主键
	 */
	private ReactionStatus reactionStatus;
	
	/**
	 * 知识分类主键
	 */
	private KnowledgeClassify knowledgeClassify;
	
	/**
	 * 试验名称
	 */
	private String reactionName;
	
	/**
	 * 试验开始时间
	 */
	private Date startTime;
	
	/**
	 * 试验结束时间
	 */
	private Date endTime;
	
	private String isLock;
	
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
	 * 信不过日期
	 */
	private Date modifyDate;
	
	
	/**
	 * 报告生成日期
	 */
	private Date reportDate;
	
	
	/**
	 * 报告doc路径
	 */
	private String reportUrl;
	
	/**
	 * 此实验步骤数量
	 */
	private Integer processSize;
	/**
	 * 此实验对应试验设计下的所有试验个数
	 */
	private Integer reactionSize;
	
	/**
	 * 试验记录表
	 */
	private List<ReactionProcess> reactionProcesses = new ArrayList<ReactionProcess>(
			0);
	
	/**
	 * 分析表
	 */
	private List<ExecuteChemicalGroup> executeChemicalGroups = new ArrayList<ExecuteChemicalGroup>(0);
	
	/**
	 * 分析表
	 */
	private List<ExecuteSolution> executeSolutions = new ArrayList<ExecuteSolution>(0);
	
	/**
	 * 分析表
	 */
	private List<Analytics> analyticses = new ArrayList<Analytics>(0);
	
	/**
	 * 试验设备预约表
	 */
	private List<DeviceAppointment> deviceAppointments = new ArrayList<DeviceAppointment>(
			0);
	
	/**
	 * 知识试验关系表
	 */
	private List<KnowledgeClassifyReacRela> knowledgeClassifyReacRelas = new ArrayList<KnowledgeClassifyReacRela>(
			0);

	// Constructors

	/** default constructor */
	public Reaction() {
	}

	public Reaction(Integer reactionId, ProjectBasicInfo projectBasicInfo,
			ProjectNumber projectNumber, SysUser sysUser,
			ReactionDesign reactionDesign, ReactionStatus reactionStatus,
			KnowledgeClassify knowledgeClassify, String reactionName,
			Date startTime, Date endTime, String isLock, String creater,
			Date createDate, String modifier, Date modifyDate, Date reportDate,
			String reportUrl, Integer processSize, Integer reactionSize,
			List<ReactionProcess> reactionProcesses,
			List<ExecuteChemicalGroup> executeChemicalGroups,
			List<ExecuteSolution> executeSolutions,
			List<Analytics> analyticses,
			List<DeviceAppointment> deviceAppointments,
			List<KnowledgeClassifyReacRela> knowledgeClassifyReacRelas) {
		super();
		this.reactionId = reactionId;
		this.projectBasicInfo = projectBasicInfo;
		this.projectNumber = projectNumber;
		this.sysUser = sysUser;
		this.reactionDesign = reactionDesign;
		this.reactionStatus = reactionStatus;
		this.knowledgeClassify = knowledgeClassify;
		this.reactionName = reactionName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isLock = isLock;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.reportDate = reportDate;
		this.reportUrl = reportUrl;
		this.processSize = processSize;
		this.reactionSize = reactionSize;
		this.reactionProcesses = reactionProcesses;
		this.executeChemicalGroups = executeChemicalGroups;
		this.executeSolutions = executeSolutions;
		this.analyticses = analyticses;
		this.deviceAppointments = deviceAppointments;
		this.knowledgeClassifyReacRelas = knowledgeClassifyReacRelas;
	}

	public Integer getReactionId() {
		return reactionId;
	}

	public void setReactionId(Integer reactionId) {
		this.reactionId = reactionId;
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

	public ReactionDesign getReactionDesign() {
		return reactionDesign;
	}

	public void setReactionDesign(ReactionDesign reactionDesign) {
		this.reactionDesign = reactionDesign;
	}

	public ReactionStatus getReactionStatus() {
		return reactionStatus;
	}

	public void setReactionStatus(ReactionStatus reactionStatus) {
		this.reactionStatus = reactionStatus;
	}

	public KnowledgeClassify getKnowledgeClassify() {
		return knowledgeClassify;
	}

	public void setKnowledgeClassify(KnowledgeClassify knowledgeClassify) {
		this.knowledgeClassify = knowledgeClassify;
	}

	public String getReactionName() {
		return reactionName;
	}

	public void setReactionName(String reactionName) {
		this.reactionName = reactionName;
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

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
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

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public Integer getProcessSize() {
		return processSize;
	}

	public void setProcessSize(Integer processSize) {
		this.processSize = processSize;
	}

	public Integer getReactionSize() {
		return reactionSize;
	}

	public void setReactionSize(Integer reactionSize) {
		this.reactionSize = reactionSize;
	}

	public List<ReactionProcess> getReactionProcesses() {
		return reactionProcesses;
	}

	public void setReactionProcesses(List<ReactionProcess> reactionProcesses) {
		this.reactionProcesses = reactionProcesses;
	}

	public List<ExecuteChemicalGroup> getExecuteChemicalGroups() {
		return executeChemicalGroups;
	}

	public void setExecuteChemicalGroups(
			List<ExecuteChemicalGroup> executeChemicalGroups) {
		this.executeChemicalGroups = executeChemicalGroups;
	}

	public List<ExecuteSolution> getExecuteSolutions() {
		return executeSolutions;
	}

	public void setExecuteSolutions(List<ExecuteSolution> executeSolutions) {
		this.executeSolutions = executeSolutions;
	}

	public List<Analytics> getAnalyticses() {
		return analyticses;
	}

	public void setAnalyticses(List<Analytics> analyticses) {
		this.analyticses = analyticses;
	}

	public List<DeviceAppointment> getDeviceAppointments() {
		return deviceAppointments;
	}

	public void setDeviceAppointments(List<DeviceAppointment> deviceAppointments) {
		this.deviceAppointments = deviceAppointments;
	}

	public List<KnowledgeClassifyReacRela> getKnowledgeClassifyReacRelas() {
		return knowledgeClassifyReacRelas;
	}

	public void setKnowledgeClassifyReacRelas(
			List<KnowledgeClassifyReacRela> knowledgeClassifyReacRelas) {
		this.knowledgeClassifyReacRelas = knowledgeClassifyReacRelas;
	}

	
}