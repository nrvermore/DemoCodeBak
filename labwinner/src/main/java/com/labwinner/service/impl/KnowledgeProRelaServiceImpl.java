package com.labwinner.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.KnowledgeProRelaDao;
import com.labwinner.domain.KnowledgeProRela;
import com.labwinner.service.KnowledgeProRelaService;

@Service
public class KnowledgeProRelaServiceImpl implements KnowledgeProRelaService{
	
	private static final Logger log = LoggerFactory
			.getLogger(KnowledgeProRelaServiceImpl.class);
	
	@Autowired
	private KnowledgeProRelaDao knowledgeProRelaDao;

	@Override
	public void save(KnowledgeProRela knowledgeProRela) {
		log.debug("saving Inventory instance");
		try {
			knowledgeProRelaDao.save(knowledgeProRela);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(KnowledgeProRela knowledgeProRela) {
		log.debug("update Inventory instance");
		try {
			knowledgeProRelaDao.update(knowledgeProRela);
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
			knowledgeProRelaDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public List<KnowledgeProRela> getAll() {
		log.debug("update Inventory instance");
		try {
			log.debug("getAll successful");
			return knowledgeProRelaDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public KnowledgeProRela getById(Integer id) {
		log.debug("getById Inventory instance");
		try {
			log.debug("getById successful");
			return knowledgeProRelaDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public List<KnowledgeProRela> findByName(String name) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<KnowledgeProRela> knowledgeProRelas =knowledgeProRelaDao.findByName(name);
			return  knowledgeProRelas;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<KnowledgeProRela> getAllByKnowladge(Map<String, Object> map) {
		log.debug("getAllByKnowladge KnowledgeProRela instance");
		try {
			log.debug("getById successful");
			return knowledgeProRelaDao.getAllByKnowladge(map);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public KnowledgeProRela getKnowledgeProRela(Integer knowledgeId, Integer classId,
			Integer proId) {
		KnowledgeProRela res=new KnowledgeProRela();
		try {
			res=knowledgeProRelaDao.getKnowledgeProRela(knowledgeId,classId,proId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public List<KnowledgeProRela> getKnowledgeProRelaByProId(int classfyId,
			int proId) {
		log.debug("getAllByKnowladge KnowledgeProRela instance");
		try {
			log.debug("getById successful");
			return knowledgeProRelaDao.getKnowledgeProRelaByProId(classfyId,proId);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}


}
