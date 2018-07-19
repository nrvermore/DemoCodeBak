package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DeviceStateDao;
import com.labwinner.domain.DeviceState;
import com.labwinner.service.DeviceStateService;

/**
 * 设备状态Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午3:51:10
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class DeviceStateServiceImpl implements DeviceStateService{
	private static final Logger log = LoggerFactory
			.getLogger(DeviceStateServiceImpl.class);
	
		@Autowired
		private DeviceStateDao deviceStateDao;
	
     /**
      * 保存对象的方法
      * @Description TODO
      * @author suhg
      * @version V1.0
      * @date 2017年5月19日 下午3:58:50
      */
	public void save(DeviceState deviceState) {
		log.debug("saving DeviceState instance");
		try {
			deviceStateDao.save(deviceState);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
     
	/**
       * 删除对象的方法
       * @Description TODO
       * @author suhg
       * @version V1.0
       * @date 2017年5月19日 下午3:59:07
       */
	public void delete(Integer id) {
		log.debug("deleting DeviceState instance");
		try {
		    deviceStateDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
     
	
	/**
	 * 根据id获取对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午3:59:36
	 */
	public DeviceState getById(java.lang.Integer id) {
		log.debug("getting DeviceState instance with id: " + id);
		try {
			DeviceState deviceState = (DeviceState)deviceStateDao.getById(id);
			return deviceState;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	
   /**
    * 获取所有对象列表
    * @Description TODO
    * @author suhg
    * @version V1.0
    * @date 2017年5月19日 下午4:09:20
    */
	public List<DeviceState> getAll() {
		log.debug("finding all DeviceState instances");
		try {
			return deviceStateDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
	
	/**
	 * 修改对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:09:56
	 */
	public void update(DeviceState deviceState) {
		log.debug("merging DeviceState instance");
		try {
			deviceStateDao.update(deviceState);
			log.debug("merge successful");
		
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public List<DeviceState> getAllPageable(String keyword) {
		log.debug("finding all DeviceState instances");
		try {
			return deviceStateDao.getAllPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}