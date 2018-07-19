package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MarketKeywordDao;
import com.labwinner.domain.MarketKeyword;
import com.labwinner.service.MarketKeywordService;

@Service
public class MarketKeywordServiceImpl implements MarketKeywordService{

	private static final Logger log = LoggerFactory
			.getLogger(MarketKeywordServiceImpl.class);
	
	@Autowired
	private MarketKeywordDao marketKeywordDao;
	
	@Override
	public void save(MarketKeyword marketKeyword) {
		log.debug("saving marketKeyword instance");
		try {
			marketKeywordDao.save(marketKeyword);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MarketKeyword> getAll() {
		log.debug("saving marketKeyword instance");
		try {
			return marketKeywordDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(MarketKeyword marketKeyword) {
		log.debug("saving marketKeyword instance");
		try {
			marketKeywordDao.update(marketKeyword);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public MarketKeyword getByKeyword(String keyword) {
		log.debug("saving marketKeyword instance");
		try {
			return marketKeywordDao.getByKeyword(keyword);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public MarketKeyword getById(Integer id) {
		log.debug("saving marketKeyword instance");
		try {
			return marketKeywordDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
