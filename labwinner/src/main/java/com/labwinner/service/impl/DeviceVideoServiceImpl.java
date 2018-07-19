package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DeviceVideoDao;
import com.labwinner.domain.DeviceVideo;
import com.labwinner.service.DeviceVideoService;

/**
 * 设备视频Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class DeviceVideoServiceImpl implements DeviceVideoService{
	private static final Logger log = LoggerFactory
			.getLogger(DeviceVideoServiceImpl.class);
    
	@Autowired
	private DeviceVideoDao deviceVideoDao;

	@Override
	public void save(DeviceVideo deviceVideo) {
		log.debug("saving Device instance");
		try {
			deviceVideoDao.save(deviceVideo);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete Device instance");
		try {
			deviceVideoDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public DeviceVideo getById(Integer id) {
		log.debug("getting DeviceVideo instance with id: " + id);
		try {
			DeviceVideo instance = deviceVideoDao.getById(id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<DeviceVideo> getAll() {
		log.debug("finding all Device instances");
		try {
			 return deviceVideoDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<DeviceVideo> getAllPageableByKeyword(String keyword) {
		log.debug("finding getAllPageable Device instances");
		try {
			 return deviceVideoDao.getAllPageableByKeyword(keyword);
		} catch (RuntimeException re) {
			log.error("find getAllPageable failed", re);
			throw re;
		}
	}

	@Override
	public List<DeviceVideo> getAllPageableAppByKeyword(Integer startCount,
			Integer endCount, String keyword) {
		log.debug("finding getAllPageableAppByKeyword Device instances");
		try {
			 return deviceVideoDao.getAllPageableAppByKeyword(startCount, endCount, keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<DeviceVideo> getAllPageableApp(Integer startCount,
			Integer endCount) {
		log.debug("finding getAllPageableApp Device instances");
		try {
			 return deviceVideoDao.getAllPageableApp(startCount, endCount);
		} catch (RuntimeException re) {
			log.error("find getAllPageableApp failed", re);
			throw re;
		}
	}

	@Override
	public void update(DeviceVideo deviceVideo) {
		log.debug("merging Device instance");
		try {
			deviceVideoDao.update(deviceVideo);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
		
	}

	@Override
	public List<DeviceVideo> getAllByDeviceId(Integer id) {
		log.debug("finding getAllByDeviceId DeviceVideo instances");
		try {
			 return deviceVideoDao.getAllByDeviceId(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
    

	
}