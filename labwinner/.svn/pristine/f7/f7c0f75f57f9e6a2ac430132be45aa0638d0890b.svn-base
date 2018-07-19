package com.labwinner.service;

import java.util.Date;
import java.util.List;

import com.labwinner.domain.Calendars;

/**
 * @Description 附件Service接口
 * @author wangll
 * @version V1.0
 * @date 2017年8月7日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface CalendarsService {

	/**
	 * @Description 获取所有对象
	 * @author wangll
	 * @version V1.0
	 * @param userId 
	 * @date 2017年8月7日
	 */
	public List<Calendars> getAll(Integer userId,Integer number);

	/**
	 * @Description 根据主键获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	public Calendars getById(Integer id);

	/**
	 * @Description 保存对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	public Integer save(Calendars calendars);

	/**
	 * @Description 更新对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	public void update(Calendars calendars);

	/**
	 * @Description 删除对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	public void delete(Integer id);

	public List<Calendars> getOneDay(Integer userId);

	public List<Calendars> getAllForApp(Integer userId);

	public List<Calendars> getAllUnStartCalendars(String calendarTime,Integer userId);

	public List<Calendars> getAllStartCalendars(String calendarDate,
			Integer userId);

	public List<Calendars> getAllMySendCalendars(String calendarDate,
			Integer userId);

	public List<Calendars> getAllMyDoCalendars(String calendarDate,
			Integer userId);

	public List<Calendars> getAllMyCalendars(String calendarDate, Integer userId);

	public List<Calendars> getCalendarsForTime(Integer type, Integer userId);

	public List<Calendars> getAllEndCalendars(String calendarDate,
			Integer userId);

	public void updateStatus(int id);
	
	
	public List<Calendars> getUnStartCalendars(Integer userId);
	
	public List<Calendars> getScreenCalendars();

}