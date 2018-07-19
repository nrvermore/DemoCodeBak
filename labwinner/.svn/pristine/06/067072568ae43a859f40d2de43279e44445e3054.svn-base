package com.labwinner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;








import com.labwinner.dao.CalendarAssistDao;
import com.labwinner.domain.CalendarAssist;
import com.labwinner.domain.Calendars;
import com.labwinner.service.CalendarAssistService;


@Service
public class CalendarAssistServiceImpl implements CalendarAssistService {
	private static final Logger log = LoggerFactory
			.getLogger(CalendarAssistServiceImpl.class);
	@Autowired
	private CalendarAssistDao calendarAssistDao;
	
	@Override
	public CalendarAssist getById(Integer id) {
		CalendarAssist res=new CalendarAssist();
		try {
			res=calendarAssistDao.getById(id);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("find failed");
		}
		return res;
	}
	@Override
	public void save(CalendarAssist calendarAssist) {
		try {
			calendarAssistDao.save(calendarAssist);
			log.debug("save successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
	}
	@Override
	public void update(CalendarAssist calendarAssist) {
		try {
			calendarAssistDao.update(calendarAssist);
			log.debug("save successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
	}
	@Override
	public void deleteByCalendarId(Integer id) {
		try {
			calendarAssistDao.deleteByCalendarId(id);
			log.debug("delete successful");
		} catch (Exception e) {
			log.debug("delete failed");
		}
	}
	@Override
	public List<CalendarAssist> getByCalendarId(Integer calendarId) {
		try {
			return calendarAssistDao.getByCalendarId(calendarId);
		} catch (Exception e) {
			log.debug("delete failed");
		}
		return null;
	}
	@Override
	public List<CalendarAssist> getAllUnFinish() {
		try {
			return calendarAssistDao.getAllUnFinish();
		} catch (Exception e) {
			log.debug("delete failed");
		}
		return null;
	}
	@Override
	public int getFinishCount(Integer id) {
		try {
			return calendarAssistDao.getFinishCount(id);
		} catch (Exception e) {
			log.debug("delete failed");
		}
		return 0;
	}

	

}
