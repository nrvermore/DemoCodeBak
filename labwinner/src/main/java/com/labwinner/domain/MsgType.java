package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;


	/**
	 * MsgType entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 消息类型
	 */
public class MsgType implements java.io.Serializable {

	/**
	 * 消息类型主键
	 */
	private Integer msgTypeId;
	
	/**
	 * 消息类型
	 */
	private String msgType;
	
	/**
	 * 消息表
	 */
	private Set<Message> messages = new HashSet<Message>(0);

	// Constructors

	/** default constructor */
	public MsgType() {
	}

	/** full constructor */
	public MsgType(String msgType, Set<Message> messages) {
		this.msgType = msgType;
		this.messages = messages;
	}

	public Integer getMsgTypeId() {
		return msgTypeId;
	}

	public void setMsgTypeId(Integer msgTypeId) {
		this.msgTypeId = msgTypeId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

}