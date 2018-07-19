package com.labwinner.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.labwinner.dao.HotSearchDao;
import com.labwinner.domain.HotSearch;
import com.labwinner.service.HotSearchService;


@Service
public class HotSearchServiceImpl implements HotSearchService {

	
	private static final Logger log = LoggerFactory
			.getLogger(HotSearchServiceImpl.class);
	@Autowired
	private HotSearchDao hotSearchDao;
	
	
	@Override
	public HotSearch getByName(String name) {
		log.debug("saving Analytics instance");
		try {
			return hotSearchDao.getByName(name);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void save(HotSearch hotSearch) {
		log.debug("saving Analytics instance");
		try {
			hotSearchDao.save(hotSearch);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(HotSearch hotSearch) {
		log.debug("saving Analytics instance");
		try {
			hotSearchDao.update(hotSearch);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
