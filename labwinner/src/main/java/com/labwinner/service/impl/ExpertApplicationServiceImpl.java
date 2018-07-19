package com.labwinner.service.impl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ExpertApplicationDao;
import com.labwinner.domain.ExpertApplication;
import com.labwinner.service.ExpertApplicationService;


/**
 * 设备Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class ExpertApplicationServiceImpl implements ExpertApplicationService{
	private static final Logger log = LoggerFactory
			.getLogger(ExpertApplicationServiceImpl.class);
    
	@Autowired
	private ExpertApplicationDao expertApplicationDao;

	@Override
	public void save(ExpertApplication expertApplication) {
		log.debug("saving Expert instance");
		try {
			expertApplicationDao.save(expertApplication);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public ExpertApplication getByUser(Integer userId, Integer agencyId) {
		log.debug("saving Expert instance");
		try {
			ExpertApplication expertApplication=expertApplicationDao.getByUser(userId,agencyId);
			log.debug("save successful");
			return expertApplication;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	
    
	
	
}