package com.labwinner.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.KnowledgeReacRelaDao;
import com.labwinner.domain.KnowledgeClassifyReacRela;
import com.labwinner.domain.KnowledgeProRela;
import com.labwinner.service.KnowledgeReacRelaService;


@Service
public class KnowledgeReacRelaServiceImpl implements KnowledgeReacRelaService{

	private static final Logger log = LoggerFactory
			.getLogger(KnowledgeReacRelaServiceImpl.class);
	
	@Autowired
	private KnowledgeReacRelaDao knowledgeReacRelaDao;
	
	@Override
	public void save(KnowledgeClassifyReacRela knowledgeClassifyReacRela) {
		log.debug("saving Inventory instance");
		try {
			knowledgeReacRelaDao.save(knowledgeClassifyReacRela);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(KnowledgeClassifyReacRela knowledgeClassifyReacRela) {
		log.debug("update Inventory instance");
		try {
			knowledgeReacRelaDao.update(knowledgeClassifyReacRela);
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
			knowledgeReacRelaDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public KnowledgeClassifyReacRela getById(Integer id) {
		log.debug("getById Inventory instance");
		try {
			log.debug("getById successful");
			return knowledgeReacRelaDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<KnowledgeClassifyReacRela> getAll() {
		log.debug("update Inventory instance");
		try {
			log.debug("getAll successful");
			return knowledgeReacRelaDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<KnowledgeClassifyReacRela> findByName(String name) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<KnowledgeClassifyReacRela> knowledgeClassifyReacRelas =knowledgeReacRelaDao.findByName(name);
			return  knowledgeClassifyReacRelas;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<KnowledgeClassifyReacRela> getAllByKnowladge(
			Map<String, Object> map) {
		log.debug("getAllByKnowladge KnowledgeProRela instance");
		try {
			log.debug("getById successful");
			return knowledgeReacRelaDao.getAllByKnowledge(map);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public KnowledgeClassifyReacRela getKnowledgeProRela(Integer knowledgeId,
			Integer classId, Integer reactionId) {
		KnowledgeClassifyReacRela res =new KnowledgeClassifyReacRela();
		try {
			res=knowledgeReacRelaDao.getKnowledgeProRela(knowledgeId,classId,reactionId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return res;
	}

}
