package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * MsgState entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 消息状态表
	 */
public class MsgState implements java.io.Serializable {

	/**
	 * 消息状态主键
	 */
	private Integer msgId;
	
	/**
	 * 消息状态
	 */
	private String msgState;
	
	/**
	 * 消息表
	 */
	private Set<Message> messages = new HashSet<Message>(0);

	// Constructors

	/** default constructor */
	public MsgState() {
	}

	/** full constructor */
	public MsgState(String msgState, Set<Message> messages) {
		this.msgState = msgState;
		this.messages = messages;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getMsgState() {
		return msgState;
	}

	public void setMsgState(String msgState) {
		this.msgState = msgState;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

}