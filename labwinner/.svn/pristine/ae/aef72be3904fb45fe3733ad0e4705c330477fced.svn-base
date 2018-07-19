package com.labwinner.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	/**
	 * Message entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 消息表
	 */
public class Message implements java.io.Serializable {

	/**
	 * 消息主键
	 */
	private Integer messageId;
	
	/**
	 * 消息类型主键
	 */
	private MsgType msgType;
	
	
	/**
	 * 消息细致类型主键
	 */
	private MsgDetailtype msgDetailtype;
	
	/**
	 * 发件人
	 */
	private SysUser sysUser;
	

	
	/**
	 * 优先级主键
	 */
	private Priority priority;
	
	/**
	 * 消息主题
	 */
	private String messageTitle;
	
	/**
	 * 消息时间
	 */
	private Date messageDate;
	
	/**
	 * 消息图标
	 */
	private String messageIcon;
	
	/**
	 * 发送时间
	 */
	private Date senderDate;
	
	/**
	 * 消息正文
	 */
	private String messageContent;
	
	/**
	 * 是否删除 1，删除 
	 */
	private Integer flag;
	
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
	 * 消息附件表
	 */
	private List<Attachment> attachments = new ArrayList<Attachment>(0);
	
	/**
	 * 消息收件人表
	 */
	private List<MessageRecipients> messageRecipientses = new ArrayList<MessageRecipients>(
			0);

	
	/**
	 * 业务主键 
	 */
	private Integer bussId;
	
	/**
	 * 机构
	 */
	private Integer agencyId;
	
	/**
	 * 查看类型
	 */
	private Integer type;
	
	private List<CommentEntity> commentEntities = new ArrayList<CommentEntity>(0);


	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(Integer messageId, MsgType msgType,
			MsgDetailtype msgDetailtype, SysUser sysUser, Priority priority,
			String messageTitle, Date messageDate, String messageIcon,
			Date senderDate, String messageContent, Integer flag,
			String creater, Date createDate, String modifier, Date modifyDate,
			List<Attachment> attachments,
			List<MessageRecipients> messageRecipientses, Integer bussId,
			Integer agencyId, Integer type) {
		super();
		this.messageId = messageId;
		this.msgType = msgType;
		this.msgDetailtype = msgDetailtype;
		this.sysUser = sysUser;
		this.priority = priority;
		this.messageTitle = messageTitle;
		this.messageDate = messageDate;
		this.messageIcon = messageIcon;
		this.senderDate = senderDate;
		this.messageContent = messageContent;
		this.flag = flag;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.attachments = attachments;
		this.messageRecipientses = messageRecipientses;
		this.bussId = bussId;
		this.agencyId = agencyId;
		this.type = type;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

	public MsgDetailtype getMsgDetailtype() {
		return msgDetailtype;
	}

	public void setMsgDetailtype(MsgDetailtype msgDetailtype) {
		this.msgDetailtype = msgDetailtype;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public String getMessageIcon() {
		return messageIcon;
	}

	public void setMessageIcon(String messageIcon) {
		this.messageIcon = messageIcon;
	}

	public Date getSenderDate() {
		return senderDate;
	}

	public void setSenderDate(Date senderDate) {
		this.senderDate = senderDate;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<MessageRecipients> getMessageRecipientses() {
		return messageRecipientses;
	}

	public void setMessageRecipientses(List<MessageRecipients> messageRecipientses) {
		this.messageRecipientses = messageRecipientses;
	}

	public Integer getBussId() {
		return bussId;
	}

	public void setBussId(Integer bussId) {
		this.bussId = bussId;
	}

	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<CommentEntity> getCommentEntities() {
		return commentEntities;
	}

	public void setCommentEntities(List<CommentEntity> commentEntities) {
		this.commentEntities = commentEntities;
	}
	
	
}