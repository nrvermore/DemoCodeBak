package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	/**
	 * ProjectBasicInfo entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 项目基本信息
	 */
public class ProjectBasicInfo implements java.io.Serializable {

	/**
	 * 项目主键
	 */
	private Integer proId;
	
	/**
	 * 项目类型主键
	 */
	private ProjectType projectType;
	
	/**
	 * 项目进度状态主键
	 */
	private ProjectSchedule projectSchedule;
	
	/**
	 * 项目名称
	 */
	private String proName;
	
	/**
	 * 项目编号
	 */
	private String proNumber;
	
	/**
	 * 项目开始时间
	 */
	private Date proStartTime;
	
	/**
	 * 项目结束时间
	 */
	private Date proDeadline;
	
	/**
	 * 项目简介
	 */
	private String proIntroduce;
	
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
	private String modifier;
	
	/**
	 * 修改日期
	 */
	private Date modifyDate;
	
	/**
	 * 访问详情返回结果
	 */
	private Integer result;
	
	/**
	 * 试验设计基础信息表
	 */
	private Set<ReactionDesign> reactionDesigns = new HashSet<ReactionDesign>(0);
	
	/**
	 * 项目文档表
	 */
	private Set<ProjectDocuments> projectDocumentses = new HashSet<ProjectDocuments>(
			0);
	
	/**
	 * 知识项目关系表
	 */
	private Set<KnowledgeProRela> knowledgeProRelas = new HashSet<KnowledgeProRela>(
			0);
	
	/**
	 * 试验基础信息表
	 */
	private List<Reaction> reactions = new ArrayList<Reaction>(0);
	
	/**
	 * 项目项目圈关系表
	 */
	private Set<ProjectMomentsRelation> projectMomentsRelations = new HashSet<ProjectMomentsRelation>(
			0);
	
	/**
	 * 项目进度计划表
	 */
	private List<ProjectPlan> projectPlans = new ArrayList<ProjectPlan>(0);
	
	/**
	 * 项目成员表
	 */
	private List<ProjectNumber> projectNumbers = new ArrayList<ProjectNumber>(0);
	
	/**
	 * 项目Logo表
	 */
	private List<ProjectPicture> projectPictures = new ArrayList<ProjectPicture>(0);
	
	/**
	 * 共享项目表
	 */
	private Set<ShareProject> shareProjects = new HashSet<ShareProject>(0);

	public ProjectBasicInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProjectBasicInfo(Integer proId, ProjectType projectType,
			ProjectSchedule projectSchedule, String proName, String proNumber,
			Date proStartTime, Date proDeadline, String proIntroduce,
			SysUser creater, Date createDate, String modifier, Date modifyDate,
			Set<ReactionDesign> reactionDesigns,
			Set<ProjectDocuments> projectDocumentses,
			Set<KnowledgeProRela> knowledgeProRelas, Set<Reaction> reactions,
			Set<ProjectMomentsRelation> projectMomentsRelations,
			List<ProjectPlan> projectPlans, List<ProjectNumber> projectNumbers,
			List<ProjectPicture> projectPictures,
			Set<ShareProject> shareProjects) {
		super();
		this.proId = proId;
		this.projectType = projectType;
		this.projectSchedule = projectSchedule;
		this.proName = proName;
		this.proNumber = proNumber;
		this.proStartTime = proStartTime;
		this.proDeadline = proDeadline;
		this.proIntroduce = proIntroduce;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.reactionDesigns = reactionDesigns;
		this.projectDocumentses = projectDocumentses;
		this.knowledgeProRelas = knowledgeProRelas;
		this.projectMomentsRelations = projectMomentsRelations;
		this.projectPlans = projectPlans;
		this.projectNumbers = projectNumbers;
		this.projectPictures = projectPictures;
		this.shareProjects = shareProjects;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public ProjectSchedule getProjectSchedule() {
		return projectSchedule;
	}

	public void setProjectSchedule(ProjectSchedule projectSchedule) {
		this.projectSchedule = projectSchedule;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProNumber() {
		return proNumber;
	}

	public void setProNumber(String proNumber) {
		this.proNumber = proNumber;
	}

	public Date getProStartTime() {
		return proStartTime;
	}

	public void setProStartTime(Date proStartTime) {
		this.proStartTime = proStartTime;
	}

	public Date getProDeadline() {
		return proDeadline;
	}

	public void setProDeadline(Date proDeadline) {
		this.proDeadline = proDeadline;
	}

	public String getProIntroduce() {
		return proIntroduce;
	}

	public void setProIntroduce(String proIntroduce) {
		this.proIntroduce = proIntroduce;
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

	public Set<ReactionDesign> getReactionDesigns() {
		return reactionDesigns;
	}

	public void setReactionDesigns(Set<ReactionDesign> reactionDesigns) {
		this.reactionDesigns = reactionDesigns;
	}

	public Set<ProjectDocuments> getProjectDocumentses() {
		return projectDocumentses;
	}

	public void setProjectDocumentses(Set<ProjectDocuments> projectDocumentses) {
		this.projectDocumentses = projectDocumentses;
	}

	public Set<KnowledgeProRela> getKnowledgeProRelas() {
		return knowledgeProRelas;
	}

	public void setKnowledgeProRelas(Set<KnowledgeProRela> knowledgeProRelas) {
		this.knowledgeProRelas = knowledgeProRelas;
	}


	public List<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}

	public Set<ProjectMomentsRelation> getProjectMomentsRelations() {
		return projectMomentsRelations;
	}

	public void setProjectMomentsRelations(
			Set<ProjectMomentsRelation> projectMomentsRelations) {
		this.projectMomentsRelations = projectMomentsRelations;
	}

	public List<ProjectPlan> getProjectPlans() {
		return projectPlans;
	}

	public void setProjectPlans(List<ProjectPlan> projectPlans) {
		this.projectPlans = projectPlans;
	}

	public List<ProjectNumber> getProjectNumbers() {
		return projectNumbers;
	}

	public void setProjectNumbers(List<ProjectNumber> projectNumbers) {
		this.projectNumbers = projectNumbers;
	}

	public List<ProjectPicture> getProjectPictures() {
		return projectPictures;
	}

	public void setProjectPictures(List<ProjectPicture> projectPictures) {
		this.projectPictures = projectPictures;
	}

	public Set<ShareProject> getShareProjects() {
		return shareProjects;
	}

	public void setShareProjects(Set<ShareProject> shareProjects) {
		this.shareProjects = shareProjects;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

}