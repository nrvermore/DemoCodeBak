package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SysSigningAgencyDao;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.service.SysSigningAgencyService;

/**
 * @Description 签约机构Service实现
 * @author liuhq
 * @version V1.0
 * @date 2017年6月7日 上午10:33:07
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class SysSigningAgencyServiceImpl implements SysSigningAgencyService {
	private static final Logger log = LoggerFactory
			.getLogger(SysSigningAgencyServiceImpl.class);

	@Autowired
	private SysSigningAgencyDao sysSigningAgencyDao;

	/**
	 * {@inheritDoc}
	 */
	public List<SysSigningAgency> getAll() {
		log.debug("finding all SysSigningAgency instances");
		try {
			return sysSigningAgencyDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SysSigningAgency> getAllPageable(String filter) {
		log.debug("finding all SysSigningAgency instances");
		try {
			return sysSigningAgencyDao.getAllPageable(filter);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public SysSigningAgency getById(Long id) {
		log.debug("getting SysSigningAgency instance with id: " + id);
		try {

			SysSigningAgency sysSigningAgency = (SysSigningAgency) sysSigningAgencyDao
					.getById(id);
			return sysSigningAgency;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(SysSigningAgency sysSigningAgency) {
		log.debug("saving SysSigningAgency instance");
		try {
			log.debug("save successful");
			sysSigningAgencyDao.save(sysSigningAgency);
			return sysSigningAgency.getAgencyId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(SysSigningAgency sysSigningAgency) {
		log.debug("saving SysSigningAgency instance");
		try {
			sysSigningAgencyDao.update(sysSigningAgency);
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
		log.debug("deleting SysSigningAgency instance");
		try {
			sysSigningAgencyDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}