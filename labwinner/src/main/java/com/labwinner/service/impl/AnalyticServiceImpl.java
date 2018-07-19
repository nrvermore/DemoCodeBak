package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.AnalyticsDao;
import com.labwinner.domain.Analytics;
import com.labwinner.service.AnalyticService;

@Service
public class AnalyticServiceImpl implements AnalyticService{

	private static final Logger log = LoggerFactory
			.getLogger(AnalyticServiceImpl.class);
	@Autowired
	private AnalyticsDao analyticsDao;
	
	@Override
	public void save(Analytics analytics) {
		log.debug("saving Analytics instance");
		try {
			analyticsDao.save(analytics);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(Analytics analytics) {
		log.debug("saving Analytics instance");
		try {
			analyticsDao.update(analytics);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving Analytics instance");
		try {
			analyticsDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<Analytics> getByKeyword(String keyword) {
		log.debug("saving Analytics instance");
		try {
			return analyticsDao.getByKeyword(keyword);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Analytics> getByReactionId(Integer id) {
		log.debug("saving Analytics instance");
		try {
			return analyticsDao.getByReactionId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Analytics getById(Integer id) {
		log.debug("saving Analytics instance");
		try {
			return analyticsDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByReactionId(Integer id) {
		log.debug("saving Analytics instance");
		try {
			analyticsDao.deleteByReactionId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<Integer> getAnalyticIds(Integer id) {
		log.debug("saving Analytics instance");
		try {
			return analyticsDao.getAnalyticIds(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Analytics> getAll() {
		log.debug("saving Analytics instance");
		try {
			return analyticsDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Analytics> getUserList(Integer userId) {
		log.debug("saving Analytics instance");
		try {
			return analyticsDao.getUserList(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Analytics> getUserListByKeyword(String keyword, Integer userId) {
		log.debug("saving Analytics instance");
		try {
			return analyticsDao.getUserListByKeyword(keyword,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Analytics> getByProcessId(Integer id) {
		log.debug("saving Analytics instance");
		try {
			return analyticsDao.getByProcessId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
