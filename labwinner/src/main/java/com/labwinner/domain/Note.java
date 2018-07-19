package com.labwinner.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

	/**
	 * Note entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 随手记表
	 */
public class Note implements java.io.Serializable {

	/**
	 * 随手记主键
	 */
	private Integer noteId;
	
	/**
	 * 用户主键
	 */
	private SysUser sysUser;
	
	/**
	 * 试验记录主键
	 */
	private ReactionProcess reactionProcess;
	
	/**
	 * 试验记录主键
	 */
	private String noteName;
	
	/**
	 * 试验主键
	 */
	private Reaction reaction;
	
	/**
	 * 项目主键
	 */
	private ProjectBasicInfo projectBasicInfo;
	
	/**
	 * 随手记内容
	 */
	private String noteContent;
	
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
	
	private Set<ReactionImage> reactionImages = new HashSet<ReactionImage> (0);
	
	private Set<NoteAssistant> noteAssistants = new HashSet<NoteAssistant> (0);


	// Constructors

	/** default constructor */
	public Note() {
	}

	/** full constructor */
	public Note(SysUser sysUser,
			ReactionProcess reactionProcess, String noteContent,
			Set<ReactionImage> reactionImages,Set<NoteAssistant> noteAssistants,
			String noteName,Reaction reaction,ProjectBasicInfo projectBasicInfo,
			String creater, Date createDate, String modifier, Date modifyDate) {
		this.sysUser = sysUser;
		this.reactionProcess = reactionProcess;
		this.noteContent = noteContent;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.noteName = noteName;
		this.reaction = reaction;
		this.projectBasicInfo = projectBasicInfo;
		this.noteAssistants = noteAssistants;
		this.reactionImages = reactionImages;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public ReactionProcess getReactionProcess() {
		return reactionProcess;
	}

	public void setReactionProcess(ReactionProcess reactionProcess) {
		this.reactionProcess = reactionProcess;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
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

	public Set<ReactionImage> getReactionImages() {
		return reactionImages;
	}

	public void setReactionImages(Set<ReactionImage> reactionImages) {
		this.reactionImages = reactionImages;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public Reaction getReaction() {
		return reaction;
	}

	public void setReaction(Reaction reaction) {
		this.reaction = reaction;
	}

	public ProjectBasicInfo getProjectBasicInfo() {
		return projectBasicInfo;
	}

	public void setProjectBasicInfo(ProjectBasicInfo projectBasicInfo) {
		this.projectBasicInfo = projectBasicInfo;
	}

	public Set<NoteAssistant> getNoteAssistants() {
		return noteAssistants;
	}

	public void setNoteAssistants(Set<NoteAssistant> noteAssistants) {
		this.noteAssistants = noteAssistants;
	}
	
	

}