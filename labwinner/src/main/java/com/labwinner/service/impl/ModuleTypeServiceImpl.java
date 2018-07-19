package com.labwinner.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DeviceDao;
import com.labwinner.dao.ModuleTypeDao;
import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.ModuleType;
import com.labwinner.service.DeviceService;
import com.labwinner.service.ModuleTypeService;

/**
 * 模块类型Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class ModuleTypeServiceImpl implements ModuleTypeService{
	private static final Logger log = LoggerFactory
			.getLogger(ModuleTypeServiceImpl.class);
    
	@Autowired
	private ModuleTypeDao moduleTypeDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(ModuleType moduleType) {
		log.debug("saving Device instance");
		try {
			moduleTypeDao.save(moduleType);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
     
	
	/**
	 * 删除对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:00:30
	 */
	public void delete(Integer id) {
		log.debug("deleting ModuleType instance");
		try {
			moduleTypeDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
     
    
	/**
	 * 根据id号查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:00:58
	 */
	public ModuleType getById(Integer id) {
		log.debug("getting ModuleType instance with id: " + id);
		try {
			ModuleType instance = moduleTypeDao.getById(id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * 显示所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:02:18
	 */
	public List<ModuleType> getAll() {
		log.debug("finding all Device instances");
		try {
			 return moduleTypeDao.getAll();
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
	 * @date 2017年5月18日 下午6:02:43
	 */
	public void update(ModuleType moduleType) {
		log.debug("merging ModuleType instance");
		try {
			 moduleTypeDao.update(moduleType);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public List<ModuleType> getAllName() {
		log.debug("finding all Device instances");
		try {
			 return moduleTypeDao.getAllName();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

/*
	@Override
	public List<ModuleType> getByIdAllDesc(Integer id) {
		log.debug("getting ModuleType instance with id: " + id);
		try {
			List<ModuleType> instance = moduleTypeDao.getByIdAllDesc(id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}*/


	@Override
	public List<ModuleType> getAllByType(Integer id) {
		log.debug("finding all Device instances");
		try {
			 return moduleTypeDao.getAllByType(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<ModuleType> getByType() {
		log.debug("finding all Device instances");
		try {
			 return moduleTypeDao.getByType();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
}