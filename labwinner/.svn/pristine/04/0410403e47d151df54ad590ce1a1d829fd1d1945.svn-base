package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.labwinner.dao.PackageTypeDao;
import com.labwinner.domain.PackageType;
import com.labwinner.service.PackageTypeService;

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
public class PackageTypeServiceImpl implements PackageTypeService{
	private static final Logger log = LoggerFactory
			.getLogger(PackageTypeServiceImpl.class);
    
	@Autowired
	private PackageTypeDao packageTypeDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(PackageType packageType) {
		log.debug("saving PackageType instance");
		try {
			packageTypeDao.save(packageType);
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
		log.debug("deleting PackageType instance");
		try {
			packageTypeDao.delete(id);
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
	public PackageType getById(Integer id) {
		log.debug("getting approvelRefuse instance with id: " + id);
		try {
			PackageType instance = packageTypeDao.getById(id);
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
	public List<PackageType> getAll() {
		log.debug("finding all PackageType instances");
		try {
			 return packageTypeDao.getAll();
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
	public void update(PackageType packageType) {
		log.debug("merging approvelRefuse instance");
		try {
			packageTypeDao.update(packageType);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<PackageType> getAllPageable(String keyword) {
		log.debug("finding all ApprovelRefuse instances");
		try {
			 return packageTypeDao.getAllPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<PackageType> getFlagAll() {
		log.debug("finding all ApprovelRefuse instances");
		try {
			 return packageTypeDao.getFlagAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<PackageType> getAllName() {
		log.debug("finding all ApprovelRefuse instances");
		try {
			 return packageTypeDao.getAllName();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}





}