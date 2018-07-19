package com.labwinner.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MarketAssistImageDao;
import com.labwinner.domain.MarketAssistImage;
import com.labwinner.service.MarketAssistImageService;

@Service
public class MarketAssistImageServiceImpl implements MarketAssistImageService{

	private static final Logger log = LoggerFactory
			.getLogger(MarketAssistImageServiceImpl.class);
	
	@Autowired
	private MarketAssistImageDao marketAssistImageDao;
	
	@Override
	public void save(MarketAssistImage marketAssistImage) {
		log.debug("saving marketAssistImage instance");
		try {
			marketAssistImageDao.save(marketAssistImage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving marketAssistImage instance");
		try {
			marketAssistImageDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

}
