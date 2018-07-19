package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * Priority entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 优先级表
	 */
public class Priority implements java.io.Serializable {

	/**
	 * 优先级主键
	 */
	private Integer priorityId;
	
	/**
	 * 优先级别
	 */
	private String priorityLevel;
	
	/**
	 * 消息表
	 */
	private Set<Message> messages = new HashSet<Message>(0);

	// Constructors

	/** default constructor */
	public Priority() {
	}

	/** full constructor */
	public Priority(String priorityLevel, Set<Message> messages) {
		this.priorityLevel = priorityLevel;
		this.messages = messages;
	}

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(String priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	

}