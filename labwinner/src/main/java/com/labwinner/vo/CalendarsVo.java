package com.labwinner.vo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import java.util.Map;

import com.labwinner.domain.KnowledgeClassifyPostil;
import com.labwinner.domain.SysUser;


public class CalendarsVo {
	/**
	 * 日程表主键
	 */
	private Integer calendarId;
	/**
	 * 日程表标题
	 */
	private String title;
	/**
	 * 起始时间
	 */
	private String start;
	//private Date start;
	
	/**
	 * 结束时间
	 */
	private String end;
	/**
	 * 协助人
	 */
	private String assistPeople;
	
	/**
	 * 日历表内容
	 */
	private String calendarContent;
	

	/**
	 * 事件class
	 */
	private String class_;
	

	public Integer getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getAssistPeople() {
		return assistPeople;
	}

	public void setAssistPeople(String assistPeople) {
		this.assistPeople = assistPeople;
	}

	public String getCalendarContent() {
		return calendarContent;
	}

	public void setCalendarContent(String calendarContent) {
		this.calendarContent = calendarContent;
	}

	

	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	
	


	
	
	
	
	
}
