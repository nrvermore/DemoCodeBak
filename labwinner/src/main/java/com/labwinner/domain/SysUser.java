package com.labwinner.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description 用户实体类
 * @author liuhq
 * @version V1.0
 * @date 2017年5月17日 下午12:06:11
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class SysUser implements java.io.Serializable {

	// Fields

	/**
	 * 用户主键
	 */
	private Integer userId;

	/**
	 * 签约机构
	 */
	private SysSigningAgency sysSigningAgency;

	/**
	 * 角色
	 */
	private SysRole sysRole;

	/**
	 * 登录帐号
	 */
	private String username;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 用户姓名
	 */
	private String realname;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 登录时间
	 */
	private Timestamp loginTime;

	/**
	 * 上次登录时间
	 */
	private Timestamp lastLoginTime;

	/**
	 * 登录次数
	 */
	private Integer loginTimes;

	/**
	 * 创建时间
	 */
	private Timestamp createTime;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 用户状态
	 */
	private String userState;

	/**
	 * 测试密码
	 */
	private String testPassWord;
	
	/**
	 * 用户头像
	 */
	private String userImage;

	/**
	 * 用户头像名称
	 */
	private String userImageName;

	/**
	 * 用户头像路径
	 */
	private String userImagePath;

	/**
	 * QQ号
	 */
	private String qq;

	/**
	 * 微信号
	 */
	private String weixin;

	/**
	 * 微博帐户
	 */
	private String blog;

	/**
	 * 个人简介
	 */
	private String personalProfile;

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

	private Integer tag;
	
	/**
	 * 备用字段1
	 */
	private String reserveField1;

	/**
	 * 备用字段2
	 */
	private String reserveField2;

	/**
	 * 备用字段3
	 */
	private String reserveField3;

	/**
	 * 备用字段4
	 */
	private Timestamp reserveField4;

	/**
	 * 备用字段5
	 */
	private Timestamp reserveField5;

	/**
	 * 经纬度
	 */
	private String latitudeLongitude;

	// demo
	private long id;
	private String loginName;
	private String plainPassword;
	// private String password;
	private String salt;
	private String name;
	// private String email;
	private String status;
	private String teamId;
	
	private Expert expert;

	/**
	 * 
	 */
	private Set<MaterialPurchase> materialPurchases = new HashSet<MaterialPurchase>(
			0);

	/**
	 * 
	 */
	private Set<Note> notes = new HashSet<Note>(0);

	/**
	 * 
	 */
	private Set<JournalArticle> journalArticles = new HashSet<JournalArticle>(0);

	/**
	 * 
	 */
	private Set<ReactionDesign> reactionDesigns = new HashSet<ReactionDesign>(0);

	/**
	 * 
	 */
	private Set<TeamMoments> teamMomentses = new HashSet<TeamMoments>(0);

	/**
	 * 
	 */
	private Set<MessageRecipients> messageRecipientses = new HashSet<MessageRecipients>(
			0);

	/**
	 * 
	 */
	private Set<KnowledgeClassifyPostil> knowledgeClassifyPostils = new HashSet<KnowledgeClassifyPostil>(
			0);

	/**
	 * 用户角色表
	 */
	private Set<SysUserRole> sysUserRoles = new HashSet<SysUserRole>(0);

	/**
	 * 
	 */
	private Set<FellingExp> fellingExps = new HashSet<FellingExp>(0);

	/**
	 * 
	 */
	private Set<SelfPaper> selfPapers = new HashSet<SelfPaper>(0);

	/**
	 * 
	 */
	private Set<DeviceAppointment> deviceAppointments = new HashSet<DeviceAppointment>(
			0);

	/**
	 * 
	 */
	private Set<Patent> patents = new HashSet<Patent>(0);

	/**
	 * 
	 */
	private Set<MediaResource> mediaResources = new HashSet<MediaResource>(0);

	/**
	 * 
	 */
	private Set<Reaction> reactions = new HashSet<Reaction>(0);

	/**
	 * 
	 */
	private Set<ProjectNumber> projectNumbers = new HashSet<ProjectNumber>(0);

	/**
	 * 
	 */
	private Set<Inventory> inventories = new HashSet<Inventory>(0);
	/**
	 * 反馈信息
	 */
	private List<Feedback> feedbacks = new ArrayList<Feedback>(0);
	
	
	/**
	 * 信息广场语音
	 */
	private List<MarketAssistVoice> marketAssistVoices = new ArrayList<MarketAssistVoice>(0);
	
	/**
	 * 
	 */
	private Set<Message> messages = new HashSet<Message>(0);

	/**
	 * 
	 */
	private Set<ProjectMoments> projectMomentses = new HashSet<ProjectMoments>(
			0);
    
	private Set<ProjectBasicInfo> projectBasicInfos = new HashSet<ProjectBasicInfo>(
			0);

	public SysUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysUser(Integer userId, SysSigningAgency sysSigningAgency,
			SysRole sysRole, String username, String password, String realname,
			String phone, String email, Timestamp loginTime,
			Timestamp lastLoginTime, Integer loginTimes, Timestamp createTime,
			String remark, String userState, String testPassWord,
			String userImage, String userImageName, String userImagePath,
			String qq, String weixin, String blog, String personalProfile,
			String creater, Date createDate, String modifier, Date modifyDate,
			Integer tag, String reserveField1, String reserveField2,
			String reserveField3, Timestamp reserveField4,
			Timestamp reserveField5, String latitudeLongitude, long id,
			String loginName, String plainPassword, String salt, String name,
			String status, String teamId, Expert expert,
			Set<MaterialPurchase> materialPurchases, Set<Note> notes,
			Set<JournalArticle> journalArticles,
			Set<ReactionDesign> reactionDesigns,
			Set<TeamMoments> teamMomentses,
			Set<MessageRecipients> messageRecipientses,
			Set<KnowledgeClassifyPostil> knowledgeClassifyPostils,
			Set<SysUserRole> sysUserRoles, Set<FellingExp> fellingExps,
			Set<SelfPaper> selfPapers,
			Set<DeviceAppointment> deviceAppointments, Set<Patent> patents,
			Set<MediaResource> mediaResources, Set<Reaction> reactions,
			Set<ProjectNumber> projectNumbers, Set<Inventory> inventories,
			List<Feedback> feedbacks,
			List<MarketAssistVoice> marketAssistVoices, Set<Message> messages,
			Set<ProjectMoments> projectMomentses,
			Set<ProjectBasicInfo> projectBasicInfos) {
		super();
		this.userId = userId;
		this.sysSigningAgency = sysSigningAgency;
		this.sysRole = sysRole;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.phone = phone;
		this.email = email;
		this.loginTime = loginTime;
		this.lastLoginTime = lastLoginTime;
		this.loginTimes = loginTimes;
		this.createTime = createTime;
		this.remark = remark;
		this.userState = userState;
		this.testPassWord = testPassWord;
		this.userImage = userImage;
		this.userImageName = userImageName;
		this.userImagePath = userImagePath;
		this.qq = qq;
		this.weixin = weixin;
		this.blog = blog;
		this.personalProfile = personalProfile;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.tag = tag;
		this.reserveField1 = reserveField1;
		this.reserveField2 = reserveField2;
		this.reserveField3 = reserveField3;
		this.reserveField4 = reserveField4;
		this.reserveField5 = reserveField5;
		this.latitudeLongitude = latitudeLongitude;
		this.id = id;
		this.loginName = loginName;
		this.plainPassword = plainPassword;
		this.salt = salt;
		this.name = name;
		this.status = status;
		this.teamId = teamId;
		this.expert = expert;
		this.materialPurchases = materialPurchases;
		this.notes = notes;
		this.journalArticles = journalArticles;
		this.reactionDesigns = reactionDesigns;
		this.teamMomentses = teamMomentses;
		this.messageRecipientses = messageRecipientses;
		this.knowledgeClassifyPostils = knowledgeClassifyPostils;
		this.sysUserRoles = sysUserRoles;
		this.fellingExps = fellingExps;
		this.selfPapers = selfPapers;
		this.deviceAppointments = deviceAppointments;
		this.patents = patents;
		this.mediaResources = mediaResources;
		this.reactions = reactions;
		this.projectNumbers = projectNumbers;
		this.inventories = inventories;
		this.feedbacks = feedbacks;
		this.marketAssistVoices = marketAssistVoices;
		this.messages = messages;
		this.projectMomentses = projectMomentses;
		this.projectBasicInfos = projectBasicInfos;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public SysSigningAgency getSysSigningAgency() {
		return sysSigningAgency;
	}

	public void setSysSigningAgency(SysSigningAgency sysSigningAgency) {
		this.sysSigningAgency = sysSigningAgency;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getTestPassWord() {
		return testPassWord;
	}

	public void setTestPassWord(String testPassWord) {
		this.testPassWord = testPassWord;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUserImageName() {
		return userImageName;
	}

	public void setUserImageName(String userImageName) {
		this.userImageName = userImageName;
	}

	public String getUserImagePath() {
		return userImagePath;
	}

	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getPersonalProfile() {
		return personalProfile;
	}

	public void setPersonalProfile(String personalProfile) {
		this.personalProfile = personalProfile;
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

	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public String getReserveField1() {
		return reserveField1;
	}

	public void setReserveField1(String reserveField1) {
		this.reserveField1 = reserveField1;
	}

	public String getReserveField2() {
		return reserveField2;
	}

	public void setReserveField2(String reserveField2) {
		this.reserveField2 = reserveField2;
	}

	public String getReserveField3() {
		return reserveField3;
	}

	public void setReserveField3(String reserveField3) {
		this.reserveField3 = reserveField3;
	}

	public Timestamp getReserveField4() {
		return reserveField4;
	}

	public void setReserveField4(Timestamp reserveField4) {
		this.reserveField4 = reserveField4;
	}

	public Timestamp getReserveField5() {
		return reserveField5;
	}

	public void setReserveField5(Timestamp reserveField5) {
		this.reserveField5 = reserveField5;
	}

	public String getLatitudeLongitude() {
		return latitudeLongitude;
	}

	public void setLatitudeLongitude(String latitudeLongitude) {
		this.latitudeLongitude = latitudeLongitude;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	public Set<MaterialPurchase> getMaterialPurchases() {
		return materialPurchases;
	}

	public void setMaterialPurchases(Set<MaterialPurchase> materialPurchases) {
		this.materialPurchases = materialPurchases;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public Set<JournalArticle> getJournalArticles() {
		return journalArticles;
	}

	public void setJournalArticles(Set<JournalArticle> journalArticles) {
		this.journalArticles = journalArticles;
	}

	public Set<ReactionDesign> getReactionDesigns() {
		return reactionDesigns;
	}

	public void setReactionDesigns(Set<ReactionDesign> reactionDesigns) {
		this.reactionDesigns = reactionDesigns;
	}

	public Set<TeamMoments> getTeamMomentses() {
		return teamMomentses;
	}

	public void setTeamMomentses(Set<TeamMoments> teamMomentses) {
		this.teamMomentses = teamMomentses;
	}

	public Set<MessageRecipients> getMessageRecipientses() {
		return messageRecipientses;
	}

	public void setMessageRecipientses(Set<MessageRecipients> messageRecipientses) {
		this.messageRecipientses = messageRecipientses;
	}

	public Set<KnowledgeClassifyPostil> getKnowledgeClassifyPostils() {
		return knowledgeClassifyPostils;
	}

	public void setKnowledgeClassifyPostils(
			Set<KnowledgeClassifyPostil> knowledgeClassifyPostils) {
		this.knowledgeClassifyPostils = knowledgeClassifyPostils;
	}

	public Set<SysUserRole> getSysUserRoles() {
		return sysUserRoles;
	}

	public void setSysUserRoles(Set<SysUserRole> sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

	public Set<FellingExp> getFellingExps() {
		return fellingExps;
	}

	public void setFellingExps(Set<FellingExp> fellingExps) {
		this.fellingExps = fellingExps;
	}

	public Set<SelfPaper> getSelfPapers() {
		return selfPapers;
	}

	public void setSelfPapers(Set<SelfPaper> selfPapers) {
		this.selfPapers = selfPapers;
	}

	public Set<DeviceAppointment> getDeviceAppointments() {
		return deviceAppointments;
	}

	public void setDeviceAppointments(Set<DeviceAppointment> deviceAppointments) {
		this.deviceAppointments = deviceAppointments;
	}

	public Set<Patent> getPatents() {
		return patents;
	}

	public void setPatents(Set<Patent> patents) {
		this.patents = patents;
	}

	public Set<MediaResource> getMediaResources() {
		return mediaResources;
	}

	public void setMediaResources(Set<MediaResource> mediaResources) {
		this.mediaResources = mediaResources;
	}

	public Set<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(Set<Reaction> reactions) {
		this.reactions = reactions;
	}

	public Set<ProjectNumber> getProjectNumbers() {
		return projectNumbers;
	}

	public void setProjectNumbers(Set<ProjectNumber> projectNumbers) {
		this.projectNumbers = projectNumbers;
	}

	public Set<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<MarketAssistVoice> getMarketAssistVoices() {
		return marketAssistVoices;
	}

	public void setMarketAssistVoices(List<MarketAssistVoice> marketAssistVoices) {
		this.marketAssistVoices = marketAssistVoices;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<ProjectMoments> getProjectMomentses() {
		return projectMomentses;
	}

	public void setProjectMomentses(Set<ProjectMoments> projectMomentses) {
		this.projectMomentses = projectMomentses;
	}

	public Set<ProjectBasicInfo> getProjectBasicInfos() {
		return projectBasicInfos;
	}

	public void setProjectBasicInfos(Set<ProjectBasicInfo> projectBasicInfos) {
		this.projectBasicInfos = projectBasicInfos;
	}

	
	
}