package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.KnowledgePostilDao;
import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.KnowledgeClassifyPostil;
import com.labwinner.service.KnowledgePostilService;

@Service
public class KnowledgePostilServiceImpl implements KnowledgePostilService{

	private static final Logger log = LoggerFactory
			.getLogger(KnowledgeAccServiceImpl.class);
	
	@Autowired
	private KnowledgePostilDao knowledgePostilDao;
	
	@Override
	public void save(KnowledgeClassifyPostil knowledgeClassifyPostil) {
		log.debug("saving Inventory instance");
		try {
			knowledgePostilDao.save(knowledgeClassifyPostil);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(KnowledgeClassifyPostil knowledgeClassifyPostil) {
		log.debug("update Inventory instance");
		try {
			knowledgePostilDao.update(knowledgeClassifyPostil);
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
			knowledgePostilDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public List<KnowledgeClassifyPostil> getById(Integer id) {
		log.debug("getById Inventory instance");
		try {
			log.debug("getById successful");
			return knowledgePostilDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	

	@Override
	public List<KnowledgeClassifyPostil> getByName(String name) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<KnowledgeClassifyPostil> postils =knowledgePostilDao.getByName(name);
			return  postils;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<KnowledgeClassifyPostil> getAll(KnowledgeAcc knowledgeAcc) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<KnowledgeClassifyPostil> postils =knowledgePostilDao.getAll(knowledgeAcc);
			return  postils;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByPostileId(Integer id) {
		log.debug("deleting Inventory instance");
		try {
			knowledgePostilDao.deleteByPostileId(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByAccId(Integer knowledgeAccId) {
		log.debug("deleting Inventory instance");
		try {
			knowledgePostilDao.deleteByAccId(knowledgeAccId);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
