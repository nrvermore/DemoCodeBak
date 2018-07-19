package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.AppHomeImageDao;
import com.labwinner.domain.AppHomeImage;
import com.labwinner.service.AppHomeImageService;

@Service
public class AppHomeImageServiceImpl implements AppHomeImageService{

	private static final Logger log = LoggerFactory
			.getLogger(AppHomeImageServiceImpl.class);
	
	@Autowired
	private AppHomeImageDao appHomeImageDao;
	
	@Override
	public void save(AppHomeImage appHomeImage) {
		log.debug("saving appHomeImage instance");
		try {
			appHomeImageDao.save(appHomeImage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("deleting appHomeImage instance");
		try {
			appHomeImageDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<AppHomeImage> getAll() {
		log.debug("get appHomeImage instance");
		try {
			return appHomeImageDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<AppHomeImage> getByShowNumber(Integer showNumber) {
		log.debug("get appHomeImage instance");
		try {
			return appHomeImageDao.getByShowNumber(showNumber);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public int getMaxShowNumber() {
		log.debug("get appHomeImage instance");
		try {
			return appHomeImageDao.getMaxShowNumber();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
