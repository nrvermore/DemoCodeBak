package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MarketAssistDao;
import com.labwinner.domain.MarketAssist;
import com.labwinner.service.MarketAssistService;

@Service
public class MarketAssistServiceImpl implements MarketAssistService{

	private static final Logger log = LoggerFactory
			.getLogger(MarketAssistServiceImpl.class);
	
	@Autowired
	private MarketAssistDao marketAssistDao;
	
	@Override
	public void save(MarketAssist marketAssist) {
		log.debug("saving marketAssist instance");
		try {
			marketAssistDao.save(marketAssist);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete marketAssist instance");
		try {
			marketAssistDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public MarketAssist getbyId(Integer id) {
		log.debug("get marketAssist instance");
		try {
			return marketAssistDao.getbyId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MarketAssist> getByKeywordId(Integer id, Integer startCount,
			Integer endCount) {
		log.debug("get marketAssist instance");
		try {
			return marketAssistDao.getByKeywordId(id,startCount,endCount);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MarketAssist> getUser(Integer userId, Integer agencyId,
			Integer startCount, Integer endCount) {
		log.debug("get marketAssist instance");
		try {
			return marketAssistDao.getUser(userId,agencyId,startCount,endCount);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MarketAssist> getAll(Integer startCount, Integer endCount) {
		log.debug("get marketAssist instance");
		try {
			return marketAssistDao.getAll(startCount,endCount);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(MarketAssist marketAssist) {
		log.debug("saving marketAssist instance");
		try {
			marketAssistDao.update(marketAssist);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
