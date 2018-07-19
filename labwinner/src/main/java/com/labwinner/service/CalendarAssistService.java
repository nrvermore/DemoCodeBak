package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.CalendarAssist;

/**
 * @Description 附件Service接口
 * @author wangll
 * @version V1.0
 * @date 2017年8月7日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface CalendarAssistService {



	/**
	 * @Description 根据主键获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	public CalendarAssist getById(Integer id);

	/**
	 * @Description 保存对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	public void save(CalendarAssist calendarAssist);

	/**
	 * @Description 更新对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	public void update(CalendarAssist calendarAssist);
	
	
	public void deleteByCalendarId(Integer id);

	public List<CalendarAssist> getByCalendarId(Integer calendarId);
	
	
	public List<CalendarAssist> getAllUnFinish();

	public int getFinishCount(Integer id);



}