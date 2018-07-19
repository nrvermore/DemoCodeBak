package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.PriorityDao;
import com.labwinner.domain.Priority;
import com.labwinner.service.PriorityService;

@Service
public class PriorityServiceImpl implements PriorityService{

	private static final Logger log = LoggerFactory
			.getLogger(PriorityServiceImpl.class);
	@Autowired
	private PriorityDao priorityDao;
	
	@Override
	public void save(Priority priority) {
		log.debug("saving priority instance");
		try {
			priorityDao.save(priority);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(Priority priority) {
		log.debug("update priority instance");
		try {
			priorityDao.update(priority);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete priority instance");
		try {
			priorityDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Priority getById(Integer id) {
		log.debug("get priority instance");
		try {
			return priorityDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Priority> getAll() {
		log.debug("getAll priority instance");
		try {
			return priorityDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
