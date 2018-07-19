package com.labwinner.vo;

import java.util.Date;
import java.util.List;


import java.util.Map;
import java.util.Set;

import com.labwinner.domain.Calendars;
import com.labwinner.domain.KnowledgeClassifyPostil;
import com.labwinner.domain.SysUser;


public class CalendarsForAppVo {
	private List<Map<String, List<Calendars>>> calendarsMap;
	private List<Map<String,Object>> calendarsAndMap;
	private List<Calendars> listCalendars;
	
	public List<Map<String, List<Calendars>>> getCalendarsMap() {
		return calendarsMap;
	}
	public void setCalendarsMap(List<Map<String, List<Calendars>>> calendarsMap) {
		this.calendarsMap = calendarsMap;
	}
	public List<Calendars> getListCalendars() {
		return listCalendars;
	}
	public void setListCalendars(List<Calendars> listCalendars) {
		this.listCalendars = listCalendars;
	}
	public List<Map<String, Object>> getCalendarsAndMap() {
		return calendarsAndMap;
	}
	public void setCalendarsAndMap(List<Map<String, Object>> calendarsAndMap) {
		this.calendarsAndMap = calendarsAndMap;
	}
	
	
	

	

	
	
	
	
	
}
