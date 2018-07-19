package com.labwinner.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DeviceDao;
import com.labwinner.dao.ExpertDao;
import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.Expert;
import com.labwinner.service.DeviceService;
import com.labwinner.service.ExpertService;

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
public class ExpertServiceImpl implements ExpertService{
	private static final Logger log = LoggerFactory
			.getLogger(ExpertServiceImpl.class);
    
	@Autowired
	private ExpertDao expertDao;

	@Override
	public void save(Expert expert) {
		log.debug("saving Expert instance");
		try {
			expertDao.save(expert);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete Expert instance");
		try {
			expertDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Expert getById(Integer id) {
		log.debug("getById Expert instance");
		try {
			Expert expert= expertDao.getById(id);
			return expert;
		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
	}

	@Override
	public List<Expert> getAll() {
		log.debug("getAll Expert instance");
		try {
			List<Expert> experts= expertDao.getAll();
		        return experts;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
		 
	}

	@Override
	public List<Expert> getAllName() {
		log.debug("getAllName Expert instance");
		try {
		List<Expert> experts=expertDao.getAllName();
			 return experts;
		} catch (RuntimeException re) {
			log.error("getAllName failed", re);
			throw re;
		}
		
	}

	@Override
	public List<Expert> getAllPageable(String keyword) {
		log.debug("getAllPageable Expert instance");
		try {
			List<Expert> experts= expertDao.getAllPageable(keyword);
			return experts;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(Expert expert) {
		log.debug("update Expert instance");
		try {
			expertDao.update(expert);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public List<Expert> getAllPageableKnow(String keyword) {
		log.debug("getAllPageable Expert instance");
		try {
			List<Expert> experts= expertDao.getAllPageableKnow(keyword);
			return experts;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Expert> getAllKnow() {
		log.debug("getAllPageable Expert instance");
		try {
			List<Expert> experts= expertDao.getAllKnow();
			return experts;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public int getKnowledgeNum(String name) {
		log.debug("getAllPageable Expert instance");
		try {
			int count= expertDao.getKnowledgeNum(name);
			return count;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Expert> getAllByAngency(Integer agencyId) {
		log.debug("getAllPageable Expert instance");
		try {
			List<Expert> experts= expertDao.getAllByAngency(agencyId);
			return experts;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Expert> getExpertUser() {
		log.debug("getAllPageable Expert instance");
		try {
			List<Expert> experts= expertDao.getExpertUser();
			return experts;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Expert> getAllExpertBase(String keyword) {
		log.debug("getAllPageable Expert instance");
		try {
			List<Expert> experts= expertDao.getAllExpertBase(keyword);
			return experts;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Expert> getAllBase() {
		log.debug("getAllPageable Expert instance");
		try {
			List<Expert> experts= expertDao.getAllBase();
			return experts;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
    
	
	
}