package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.KnowledgeAccDao;
import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.service.KnowledgeAccService;

/**
 * @Description 知识附件Service实现
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class KnowledgeAccServiceImpl implements KnowledgeAccService {
	private static final Logger log = LoggerFactory
			.getLogger(KnowledgeAccServiceImpl.class);

	@Autowired
	private KnowledgeAccDao knowledgeAccDao;

	/**
	 * {@inheritDoc}
	 */
	public List<KnowledgeAcc> getAll() {
		log.debug("finding all KnowledgeAcc instances");
		try {
			return knowledgeAccDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public KnowledgeAcc getById(Integer id) {
		log.debug("getting KnowledgeAcc instance with id: " + id);
		try {

			KnowledgeAcc knowledgeAcc = (KnowledgeAcc) knowledgeAccDao
					.getById(id);
			return knowledgeAcc;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(KnowledgeAcc knowledgeAcc) {
		log.debug("saving KnowledgeAcc instance");
		try {
			log.debug("save successful");
			knowledgeAccDao.save(knowledgeAcc);
			return knowledgeAcc.getKnowledgeAccId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(KnowledgeAcc knowledgeAcc) {
		log.debug("saving KnowledgeAcc instance");
		try {
			knowledgeAccDao.update(knowledgeAcc);
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
		log.debug("deleting KnowledgeAcc instance");
		try {
			knowledgeAccDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<KnowledgeAcc> getByName(String name) {
		log.debug("getting KnowledgeAcc instance with id: " + name);
		try {

			List<KnowledgeAcc> knowledgeAcc = (List<KnowledgeAcc>) knowledgeAccDao
					.getByName(name);
			return knowledgeAcc;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public KnowledgeAcc getKnowledgeAcc(Integer knowledgeId, Integer classifyId) {
		log.debug("getting KnowledgeAcc instance");
		try {
			return knowledgeAccDao.getKnowledgeAcc(knowledgeId, classifyId);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<KnowledgeAcc> getAllConversion() {
		log.debug("getting KnowledgeAcc instance");
		try {
			return knowledgeAccDao.getAllConversion();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}




}