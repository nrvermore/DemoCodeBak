package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MarketAssistDao;
import com.labwinner.dao.MarketAssistVoiceDao;
import com.labwinner.domain.MarketAssist;
import com.labwinner.domain.MarketAssistVoice;
import com.labwinner.service.MarketAssistService;
import com.labwinner.service.MarketAssistVoiceService;

@Service
public class MarketAssistVoiceServiceImpl implements MarketAssistVoiceService{

	private static final Logger log = LoggerFactory
			.getLogger(MarketAssistVoiceServiceImpl.class);

	
	@Autowired
	private MarketAssistVoiceDao marketAssistVoiceDao;
	
	@Override
	public void save(MarketAssistVoice marketAssistVoice) {
		log.debug("saving marketAssist instance");
		try {
			marketAssistVoiceDao.save(marketAssistVoice);
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
			marketAssistVoiceDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void update(MarketAssistVoice marketAssistVoice) {
		log.debug("update marketAssist instance");
		try {
			marketAssistVoiceDao.update(marketAssistVoice);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public MarketAssistVoice getbyId(Integer id) {
		log.debug("getbyId marketAssist instance");
		try {
			return marketAssistVoiceDao.getbyId(id);
		} catch (RuntimeException re) {
			log.error("getbyId failed", re);
			throw re;
		}
		 
	}

	@Override
	public List<MarketAssistVoice> getAll() {
		log.debug("getAll marketAssist instance");
		try {
			return	marketAssistVoiceDao.getAll();
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}
}
