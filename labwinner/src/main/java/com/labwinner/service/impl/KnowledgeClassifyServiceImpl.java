package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.KnowledgeClassifyDao;
import com.labwinner.domain.KnowledgeClassify;
import com.labwinner.service.KnowledgeClassifyService;

@Service
public class KnowledgeClassifyServiceImpl implements  KnowledgeClassifyService{

	private static final Logger log = LoggerFactory
			.getLogger(KnowledgeClassifyServiceImpl.class);
	
	@Autowired
	private KnowledgeClassifyDao knowledgeClassifyDao;
	
	@Override
	public void save(KnowledgeClassify knowledgeClassify) {
		log.debug("saving Inventory instance");
		try {
			knowledgeClassifyDao.save(knowledgeClassify);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(KnowledgeClassify knowledgeClassify) {
		log.debug("update Inventory instance");
		try {
			knowledgeClassifyDao.update(knowledgeClassify);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("deleting Inventory instance");
		try {
			knowledgeClassifyDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public KnowledgeClassify getById(Integer id) {
		log.debug("getById Inventory instance");
		try {
			log.debug("getById successful");
			return knowledgeClassifyDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<KnowledgeClassify> getByName(String name) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<KnowledgeClassify> knowledgeClassifys =knowledgeClassifyDao.getByName(name);
			return  knowledgeClassifys;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<KnowledgeClassify> getAll() {
		log.debug("update Inventory instance");
		try {
			log.debug("getAll successful");
			return knowledgeClassifyDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
