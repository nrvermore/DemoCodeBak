package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.IndustryDao;
import com.labwinner.domain.Industry;
import com.labwinner.service.IndustryService;

/**
 * @Description 行业Service实现
 * @author liuhq
 * @version V1.0
 * @date 2017年6月8日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class IndustryServiceImpl implements IndustryService {
	private static final Logger log = LoggerFactory
			.getLogger(IndustryServiceImpl.class);

	@Autowired
	private IndustryDao industryDao;

	/**
	 * {@inheritDoc}
	 */
	public List<Industry> getAll() {
		log.debug("finding all Industry instances");
		try {
			return industryDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Industry getById(Long id) {
		log.debug("getting Industry instance with id: " + id);
		try {

			Industry industry = (Industry) industryDao.getById(id);
			return industry;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(Industry industry) {
		log.debug("saving Industry instance");
		try {
			log.debug("save successful");
			industryDao.save(industry);
			return industry.getIndustryId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Industry industry) {
		log.debug("saving Industry instance");
		try {
			industryDao.update(industry);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Long id) {
		log.debug("deleting Industry instance");
		try {
			industryDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}