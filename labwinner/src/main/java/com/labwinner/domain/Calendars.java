package com.labwinner.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Calendars {
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
	private Date start;
	
	/**
	 * 结束时间
	 */
	private Date end;
	/**
	 * 协助人
	 */
	private String assistPeople;
	
	/**
	 * 日历表内容
	 */
	private String calendarContent;
	

	/**
	 * 用户对象
	 */
	private SysUser sysUser;
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
	 * 事件id
	 */
	private String _id;
	/**
	 * 事件class
	 */
	private String class_;
	
	private List<Integer> listUser;
	
	private List<String>  className;
	
	private Integer allDay;
	
	private Integer flag;
	
	
	private Integer manyFlag;
	
	private List<Map<String, Object>> userMap;
	
	private List<CalendarAssist> calendarAssists;
	
	private AssistStatus assistStatus;
	
	private Integer calendarType;
	//private Boolean allDayBool;
 
	// Constructors

	/** default constructor */
	public Calendars() {
	}
	
	/** full constructor */
	public Calendars(String title,Date start,Date end,
			String assistPeople,String calendarContent,SysUser sysUser,
			String creater,Date createDate,String modifier,Date modifyDate,
			List<Integer> listUser,List<String>  className,Integer allDay,
			Integer flag,Integer manyFlag,List<Map<String, Object>> userMap,
			List<CalendarAssist> calendarAssists,AssistStatus assistStatus,Integer calendarType) {
		this.title=title;
		this.start=start;
		this.end=end;
		this.assistPeople=assistPeople;
		this.calendarContent=calendarContent;
		this.sysUser=sysUser;
		this.creater=creater;
		this.createDate=createDate;
		this.modifier=modifier;
		this.modifyDate=modifyDate;
		this.listUser=listUser;
		this.className=className;
		this.allDay=allDay;
		this.flag=flag;
		this.manyFlag=manyFlag;
		this.userMap=userMap;
		this.calendarAssists=calendarAssists;
		this.assistStatus=assistStatus;
		this.calendarType=calendarType;
	}

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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
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

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
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

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public List<Integer> getListUser() {
		return listUser;
	}

	public void setListUser(List<Integer> listUser) {
		this.listUser = listUser;
	}

	public List<String> getClassName() {
		return className;
	}

	public void setClassName(List<String> className) {
		this.className = className;
	}

	public Integer getAllDay() {
		return allDay;
	}

	public void setAllDay(Integer allDay) {
		this.allDay = allDay;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getManyFlag() {
		return manyFlag;
	}

	public void setManyFlag(Integer manyFlag) {
		this.manyFlag = manyFlag;
	}

	public List<Map<String, Object>> getUserMap() {
		return userMap;
	}

	public void setUserMap(List<Map<String, Object>> userMap) {
		this.userMap = userMap;
	}

	public List<CalendarAssist> getCalendarAssists() {
		return calendarAssists;
	}

	public void setCalendarAssists(List<CalendarAssist> calendarAssists) {
		this.calendarAssists = calendarAssists;
	}

	public AssistStatus getAssistStatus() {
		return assistStatus;
	}

	public void setAssistStatus(AssistStatus assistStatus) {
		this.assistStatus = assistStatus;
	}

	public Integer getCalendarType() {
		return calendarType;
	}

	public void setCalendarType(Integer calendarType) {
		this.calendarType = calendarType;
	}

	
	
}
