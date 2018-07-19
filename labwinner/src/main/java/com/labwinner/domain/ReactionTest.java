package com.labwinner.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ReactionTest implements java.io.Serializable{

	/**
	 * 测试主键
	 */
	private Integer testId;
	
	/**
	 * 试验基本信息表主键
	 */
	private Reaction reaction;
	
	/**
	 * 试验记录表主键
	 */
	private ReactionProcess reactionProcess;
	
	/**
	 * 测试名称
	 */
	private String testName;
	
	/**
	 * 测试内容
	 */
	private String testContent;
	
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
	 * 测试设备关系表
	 */
	private Set<TestDevice> testDevices = new HashSet<TestDevice>(
			0);
	
	/**
	 * 测试附件表
	 */
	private Set<TestAttachment> testAttachments = new HashSet<TestAttachment>(
			0);

	public ReactionTest(){}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
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

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestContent() {
		return testContent;
	}

	public void setTestContent(String testContent) {
		this.testContent = testContent;
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

	public Set<TestDevice> getTestDevices() {
		return testDevices;
	}

	public void setTestDevices(Set<TestDevice> testDevices) {
		this.testDevices = testDevices;
	}

	public Set<TestAttachment> getTestAttachments() {
		return testAttachments;
	}

	public void setTestAttachments(Set<TestAttachment> testAttachments) {
		this.testAttachments = testAttachments;
	}

	public SysUser getCreatSysUser() {
		return creatSysUser;
	}

	public void setCreatSysUser(SysUser creatSysUser) {
		this.creatSysUser = creatSysUser;
	}

}
