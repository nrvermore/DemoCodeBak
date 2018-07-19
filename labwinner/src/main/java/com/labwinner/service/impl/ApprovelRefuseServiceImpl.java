package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ApprovelRefuseDao;

import com.labwinner.domain.ApprovelRefuse;
import com.labwinner.service.ApprovelRefuseService;

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
public class ApprovelRefuseServiceImpl implements ApprovelRefuseService{
	private static final Logger log = LoggerFactory
			.getLogger(ApprovelRefuseServiceImpl.class);
    
	@Autowired
	private ApprovelRefuseDao approvelRefuseDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(ApprovelRefuse approvelRefuse) {
		log.debug("saving ApprovelRefuse instance");
		try {
			approvelRefuseDao.save(approvelRefuse);
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
		log.debug("deleting ApprovelRefuse instance");
		try {
			approvelRefuseDao.delete(id);
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
	public ApprovelRefuse getById(Integer id) {
		log.debug("getting approvelRefuse instance with id: " + id);
		try {
			ApprovelRefuse instance = approvelRefuseDao.getById(id);
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
	public List<ApprovelRefuse> getAll() {
		log.debug("finding all ApprovelRefuse instances");
		try {
			 return approvelRefuseDao.getAll();
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
	public void update(ApprovelRefuse approvelRefuse) {
		log.debug("merging approvelRefuse instance");
		try {
			approvelRefuseDao.update(approvelRefuse);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<ApprovelRefuse> getAllPageable(String keyword) {
		log.debug("finding all ApprovelRefuse instances");
		try {
			 return approvelRefuseDao.getAllPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<ApprovelRefuse> getFlagAll() {
		log.debug("finding all ApprovelRefuse instances");
		try {
			 return approvelRefuseDao.getFlagAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<ApprovelRefuse> getAllName() {
		log.debug("finding all ApprovelRefuse instances");
		try {
			 return approvelRefuseDao.getAllName();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}





}