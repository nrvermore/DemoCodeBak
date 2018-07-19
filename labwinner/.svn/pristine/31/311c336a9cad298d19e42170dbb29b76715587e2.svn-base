package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DeviceCollectDao;
import com.labwinner.domain.DeviceCollect;
import com.labwinner.service.DeviceCollectService;

@Service
public class DeviceCollectServiceImpl implements DeviceCollectService{

	private static final Logger log = LoggerFactory
			.getLogger(DeviceCollectServiceImpl.class);
	
	@Autowired
	private DeviceCollectDao deviceCollectDao;
	
	@Override
	public List<DeviceCollect> getAllScreen() {
		log.debug("getting deviceCollect instance");
		try {
			return deviceCollectDao.getAllScreen();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public void save(DeviceCollect deviceCollect) {
		log.debug("deleting deviceCollect instance");
		try {
			deviceCollectDao.save(deviceCollect);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("deleting deviceCollect instance");
		try {
			deviceCollectDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
