package com.labwinner.domain;

	/**
	 * MessageRecipients entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 消息收件表
	 */
public class MessageRecipients implements java.io.Serializable {

	/**
	 * 消息收件人主键
	 */
	private Integer msgRecipientsId;
	
	/**
	 * 收件人主键
	 */
	private SysUser sysUser;
	
	/**
	 * 消息主键
	 */
	private Message message;
	
	/**
	 * 是否删除 1，删除 
	 */
	private Integer flag;
	
	private MsgState msgState;

	/**
	 * 是否删除 1，删除 
	 */
	private Integer agencyId;
	// Constructors

	/** default constructor */
	public MessageRecipients() {
	}

	/** full constructor */
	public MessageRecipients(SysUser sysUser, Message message,Integer flag,MsgState msgState,Integer agencyId) {
		this.sysUser = sysUser;
		this.message = message;
		this.flag = flag;
		this.msgState=msgState;
		this.agencyId=agencyId;
	}

	public Integer getMsgRecipientsId() {
		return msgRecipientsId;
	}

	public void setMsgRecipientsId(Integer msgRecipientsId) {
		this.msgRecipientsId = msgRecipientsId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public MsgState getMsgState() {
		return msgState;
	}

	public void setMsgState(MsgState msgState) {
		this.msgState = msgState;
	}

	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}

	
	
}