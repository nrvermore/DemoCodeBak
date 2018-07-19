package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DeviceTypeDao;
import com.labwinner.domain.DeviceType;
import com.labwinner.service.DeviceTypeService;


/**
 * 设备类型Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午5:25:29
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class DeviceTypeServiceImpl implements DeviceTypeService{
	private static final Logger log = LoggerFactory
			.getLogger(DeviceTypeServiceImpl.class);
	
       @Autowired
       private DeviceTypeDao deviceTypeDao;
       
       
	public void save(DeviceType deviceType) {
		log.debug("saving DeviceType instance");
		try {
			 deviceTypeDao.save(deviceType);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Integer id) {
		log.debug("deleting DeviceType instance");
		try {
			deviceTypeDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DeviceType getById(java.lang.Integer id) {
		log.debug("getting DeviceType instance with id: " + id);
		try {
			DeviceType deviceType = (DeviceType) deviceTypeDao.getById(id);
			return deviceType;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	
	public List<DeviceType> getAll() {
		log.debug("finding all DeviceType instances");
		try {
			return deviceTypeDao.getAll(); 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void update(DeviceType deviceType) {
		log.debug("merging DeviceType instance");
		try {
			deviceTypeDao.update(deviceType);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public List<DeviceType> getAllPageable(String keyword) {
		log.debug("finding all DeviceType instances");
		try {
			return deviceTypeDao.getAllPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<DeviceType> getAllName() {
		log.debug("finding all DeviceType instances");
		try {
			return deviceTypeDao.getAllName(); 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}