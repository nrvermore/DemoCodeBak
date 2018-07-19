package com.labwinner.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SysAgencyLogoDao;
import com.labwinner.domain.SysAgencyLogo;
import com.labwinner.service.SysAgencyLogoService;

@Service
public class SysAgencyLogoServiceImpl implements SysAgencyLogoService{

	private static final Logger log = LoggerFactory
			.getLogger(SysAgencyLogoServiceImpl.class);

	@Autowired
	private SysAgencyLogoDao sysAgencyLogoDao;
	
	@Override
	public void save(SysAgencyLogo sysAgencyLogo) {
		log.debug("saving sysAgencyLogo instance");
		try {
			log.debug("save successful");
			sysAgencyLogoDao.save(sysAgencyLogo);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public SysAgencyLogo getById(Integer agencyId) {
		log.debug("get sysAgencyLogo instance");
		try {
			log.debug("save successful");
			return sysAgencyLogoDao.getById(agencyId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete sysAgencyLogo instance");
		try {
			log.debug("save successful");
			sysAgencyLogoDao.delete(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(SysAgencyLogo sysAgencyLogo) {
		log.debug("updateing sysAgencyLogo instance");
		try {
			log.debug("save successful");
			sysAgencyLogoDao.update(sysAgencyLogo);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
