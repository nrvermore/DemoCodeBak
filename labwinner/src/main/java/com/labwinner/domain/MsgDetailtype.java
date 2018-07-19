package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;


	/**
	 * MsgType entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 消息类型
	 */
public class MsgDetailtype implements java.io.Serializable {

	/**
	 * 消息类型主键
	 */
	private Integer msgDetailtypeId;
	
	/**
	 * 消息类型
	 */
	private String msgDetailtype;
	
	/**
	 * 消息表
	 */
	private Set<Message> messages = new HashSet<Message>(0);

	// Constructors

	/** default constructor */
	public MsgDetailtype() {
	}

	/** full constructor */
	public MsgDetailtype(String msgDetailtype, Set<Message> messages) {
		this.msgDetailtype = msgDetailtype;
		this.messages = messages;
	}

	

	public Integer getMsgDetailtypeId() {
		return msgDetailtypeId;
	}

	public void setMsgDetailtypeId(Integer msgDetailtypeId) {
		this.msgDetailtypeId = msgDetailtypeId;
	}

	public String getMsgDetailtype() {
		return msgDetailtype;
	}

	public void setMsgDetailtype(String msgDetailtype) {
		this.msgDetailtype = msgDetailtype;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

}