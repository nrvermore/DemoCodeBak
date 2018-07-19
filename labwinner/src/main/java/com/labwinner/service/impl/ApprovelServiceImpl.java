package com.labwinner.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ApprovelDao;
import com.labwinner.domain.Approvel;
import com.labwinner.domain.ApprovelRefuse;
import com.labwinner.service.ApprovelService;

/**
 * @Description 附件Service实现
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class ApprovelServiceImpl implements ApprovelService {
	private static final Logger log = LoggerFactory
			.getLogger(ApprovelServiceImpl.class);

	@Autowired
	private ApprovelDao approvelDao;

	/**
	 * {@inheritDoc}
	 */
	public List<Approvel> getAll(String roleName,Integer userId) {
		log.debug("finding all Approvel instances");
		try {
			return approvelDao.getAll(roleName,userId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Approvel getById(Integer id) {
		log.debug("getting Approvel instance with id: " + id);
		try {

			Approvel Approvel =  approvelDao
					.getById(id);
			return Approvel;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(Approvel approvel) {
		log.debug("saving Approvel instance");
		try {
			log.debug("save successful");
			approvelDao.save(approvel);
			return approvel.getApproveId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Approvel approvel) {
		log.debug("saving Approvel instance");
		try {
			approvelDao.update(approvel);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Integer id) {
		log.debug("deleting Approvel instance");
		try {
			approvelDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Approvel> getByOrderNumber(String roleName, Integer userId,
			String keyword) {
		log.debug("finding all Approvel instances");
		try {
			return approvelDao.getByOrderNumber(roleName,userId,keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getRefuseList() {
		log.debug("finding all Approvel instances");
		try {
			return approvelDao.getRefuseList();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getOrderState() {
		log.debug("finding all Approvel instances");
		try {
			return approvelDao.getOrderState();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllForFirst(String roleName,
			Integer userId) {
		log.debug("finding all Approvel instances");
		try {
			return approvelDao.getAllForFirst(roleName,userId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Approvel> getScreenOrder() {
		log.debug("finding all Approvel instances");
		try {
			return approvelDao.getScreenOrder();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Integer getAllOrders() {
		log.debug("finding all Approvel instances");
		try {
			return approvelDao.getAllOrders();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}