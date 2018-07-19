package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.AnalyticsDeviceDao;
import com.labwinner.domain.AnalyticsDevice;
import com.labwinner.service.AnalyticsDeviceService;

@Service
public class AnalyticsDeviceServiceImpl implements AnalyticsDeviceService{

	private static final Logger log = LoggerFactory
			.getLogger(AnalyticsDeviceServiceImpl.class);
	@Autowired
	private AnalyticsDeviceDao analyticsDeviceDao;
	@Override
	public void save(AnalyticsDevice analyticsDevice) {
		log.debug("saving AnalyticsDevice instance");
		try {
			analyticsDeviceDao.save(analyticsDevice);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(AnalyticsDevice analyticsDevice) {
		log.debug("saving AnalyticsDevice instance");
		try {
			analyticsDeviceDao.update(analyticsDevice);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving AnalyticsDevice instance");
		try {
			analyticsDeviceDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<AnalyticsDevice> getById(Integer id) {
		log.debug("saving AnalyticsDevice instance");
		try {
			return analyticsDeviceDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
