package com.labwinner.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.AttachmentDao;
import com.labwinner.dao.CalendarsDao;
import com.labwinner.domain.Calendars;
import com.labwinner.service.CalendarsService;

@Service
public class CalendarsServiceImpl implements CalendarsService {
	private static final Logger log = LoggerFactory
			.getLogger(CalendarsServiceImpl.class);
	@Autowired
	private CalendarsDao calendarsDao;

	@Override
	public List<Calendars> getAll(Integer userId,Integer number) {
		List<Calendars> res=new ArrayList<Calendars>();
		try {
			res=calendarsDao.getAll(userId,number);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

	@Override
	public Calendars getById(Integer id) {
		Calendars res=new Calendars();
		try {
			res=calendarsDao.getById(id);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

	@Override
	public Integer save(Calendars calendars) {
		try {
			calendarsDao.save(calendars);
			log.debug("save successful");
			return calendars.getCalendarId();
		} catch (Exception e) {
			log.debug("save failed");
			return null;
		}
		
	}

	@Override
	public void update(Calendars calendars) {
		log.debug("saving Calendars instance");
		try {
			calendarsDao.update(calendars);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("deleting Calendars instance");
		try {
			calendarsDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Calendars> getOneDay(Integer userId) {
		List<Calendars> res=new ArrayList<Calendars>();
		try {
			res=calendarsDao.getOneDay(userId);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

	@Override
	public List<Calendars> getAllForApp(Integer userId) {
		List<Calendars> res=new ArrayList<Calendars>();
		try {
			res=calendarsDao.getAllForApp(userId);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

	@Override
	public List<Calendars> getAllUnStartCalendars(String calendarTime,Integer userId) {
		List<Calendars> res=new ArrayList<Calendars>();
		try {
			res=calendarsDao.getAllUnStartCalendars(calendarTime,userId);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

	@Override
	public List<Calendars> getAllStartCalendars(String calendarDate,
			Integer userId) {
		List<Calendars> res=new ArrayList<Calendars>();
		try {
			res=calendarsDao.getAllStartCalendars(calendarDate,userId);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

	@Override
	public List<Calendars> getAllMySendCalendars(String calendarDate,
			Integer userId) {
		List<Calendars> res=new ArrayList<Calendars>();
		try {
			res=calendarsDao.getAllMySendCalendars(calendarDate,userId);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

	@Override
	public List<Calendars> getAllMyDoCalendars(String calendarDate,
			Integer userId) {
		List<Calendars> res=new ArrayList<Calendars>();
		try {
			res=calendarsDao.getAllMyDoCalendars(calendarDate,userId);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

	@Override
	public List<Calendars> getAllMyCalendars(String calendarDate, Integer userId) {
		List<Calendars> res=new ArrayList<Calendars>();
		try {
			res=calendarsDao.getAllMyCalendars(calendarDate,userId);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

	@Override
	public List<Calendars> getCalendarsForTime(Integer type, Integer userId) {
		List<Calendars> res=new ArrayList<Calendars>();
		try {
			res=calendarsDao.getCalendarsForTime(type,userId);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

	@Override
	public List<Calendars> getAllEndCalendars(String calendarDate,
			Integer userId) {
		List<Calendars> res=new ArrayList<Calendars>();
		try {
			res=calendarsDao.getAllEndCalendars(calendarDate,userId);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

	@Override
	public void updateStatus(int id) {
		log.debug("saving Calendars instance");
		try {
			calendarsDao.updateStatus(id);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public List<Calendars> getUnStartCalendars(Integer userId) {
		List<Calendars> res=new ArrayList<Calendars>();
		try {
			res=calendarsDao.getUnStartCalendars(userId);
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

	@Override
	public List<Calendars> getScreenCalendars() {
		List<Calendars> res=new ArrayList<Calendars>();
		try {
			res=calendarsDao.getScreenCalendars();
			log.debug("find successful");
		} catch (Exception e) {
			log.debug("save failed");
		}
		return res;
	}

}
