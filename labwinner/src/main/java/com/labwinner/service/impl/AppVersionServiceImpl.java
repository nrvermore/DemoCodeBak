package com.labwinner.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.AppVersionDao;
import com.labwinner.domain.AppVersion;
import com.labwinner.service.AppVersionService;

@Service
public class AppVersionServiceImpl implements AppVersionService{

	private static final Logger log = LoggerFactory
			.getLogger(AppVersionServiceImpl.class);
	@Autowired
	private AppVersionDao appVersionDao;
	
	@Override
	public AppVersion getByLast() {
		log.debug("get AppVersion instance");
		try {
			log.debug("get successful");
			return appVersionDao.getByLast();
			
		} catch (RuntimeException re) {
			log.error("AppVersion failed", re);
			throw re;
		}
	}

}
