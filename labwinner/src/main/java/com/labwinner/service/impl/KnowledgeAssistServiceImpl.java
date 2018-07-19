package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.KnowledgeAccDao;
import com.labwinner.dao.KnowledgeAssistDao;
import com.labwinner.domain.KnowledgeAssist;
import com.labwinner.service.KnowledgeAssistService;
@Service
public class KnowledgeAssistServiceImpl implements KnowledgeAssistService {

	private static final Logger log = LoggerFactory
			.getLogger(KnowledgeAssistServiceImpl.class);
	
	@Autowired
	private KnowledgeAssistDao knowledgeAssistDao;

	@Override
	public List<KnowledgeAssist> getAllKnowledgeAssist(Integer userId,
			Integer agencyId,int size) {
		log.debug("finding all KnowledgeAssist instances");
		try {
			return knowledgeAssistDao.getAllKnowledgeAssist(userId,agencyId,size);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void save(KnowledgeAssist knowledgeAssist) {
		log.debug("save KnowledgeAssist instances");
		try {
			 knowledgeAssistDao.save(knowledgeAssist);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<KnowledgeAssist> getById(Integer userId, Integer agencyId,
			Integer knowledgeAssistId) {
		log.debug("finding  KnowledgeAssist instances");
		try {
			return knowledgeAssistDao.getById(userId,agencyId,knowledgeAssistId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void updateReadFlag(Integer knowledgeAssistId) {
		try {
			 knowledgeAssistDao.updateReadFlag(knowledgeAssistId);
		} catch (RuntimeException re) {
			log.error("updateReadFlag failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		try {
			 knowledgeAssistDao.delete(id);
		} catch (RuntimeException re) {
			log.error("updateReadFlag failed", re);
			throw re;
		}
	}
	
	

	

}
