package com.labwinner.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Calendars;

/**
 * @Description 附件Dao
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface CalendarsDao {

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public List<Calendars> getAll(@Param("userId")Integer userId,@Param("number")Integer number);

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	Calendars getById(Integer id);

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	void save(Calendars Calendars);

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	void update(Calendars Calendars);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	void delete(Integer id);

	public List<Calendars> getOneDay(Integer userId);

	public List<Calendars> getAllForApp(Integer userId);

	public List<Calendars> getAllUnStartCalendars(@Param("calendarTime")String calendarTime,@Param("userId")Integer userId);

	public List<Calendars> getAllStartCalendars(@Param("calendarTime")String calendarTime,@Param("userId")Integer userId);

	public List<Calendars> getAllMySendCalendars(@Param("calendarTime")String calendarTime,@Param("userId")Integer userId);

	public List<Calendars> getAllMyDoCalendars(@Param("calendarTime")String calendarTime,@Param("userId")Integer userId);

	public List<Calendars> getAllMyCalendars(@Param("calendarTime")String calendarTime, @Param("userId")Integer userId);

	public List<Calendars> getCalendarsForTime(@Param("type")Integer type, @Param("userId")Integer userId);

	public List<Calendars> getAllEndCalendars(@Param("calendarTime")String calendarTime, @Param("userId")Integer userId);

	public void updateStatus(int id);
	
	public List<Calendars> getUnStartCalendars(Integer userId);
	
	public List<Calendars> getScreenCalendars();

}
