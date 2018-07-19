package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.CalendarAssist;
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
public interface CalendarAssistDao {

	CalendarAssist getById(Integer id);

	void save(CalendarAssist calendarAssist);

	void update(CalendarAssist calendarAssist);

	void deleteByCalendarId(Integer id);

	List<CalendarAssist> getByCalendarId(Integer calendarId);

	List<CalendarAssist> getAllUnFinish();

	int getFinishCount(Integer id);

	

}
