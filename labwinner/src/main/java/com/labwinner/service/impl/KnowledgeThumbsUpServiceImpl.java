package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.KnowledgeThumbsUpDao;
import com.labwinner.domain.KnowledgeClassifyReacRela;
import com.labwinner.domain.KnowledgeThumbsUp;
import com.labwinner.domain.SysAttachment;
import com.labwinner.service.KnowledgeThumbsUpService;
import com.labwinner.vo.KnowledgeThumbsUpVo;
@Service
public class KnowledgeThumbsUpServiceImpl implements KnowledgeThumbsUpService {

	private static final Logger log = LoggerFactory
			.getLogger(KnowledgeThumbsUpServiceImpl.class);
	
	@Autowired 
	private KnowledgeThumbsUpDao knowledgeThumbsUpDao;
	@Override 
	public List<KnowledgeThumbsUp> getAll(Integer id) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<KnowledgeThumbsUp> res= knowledgeThumbsUpDao.getAll(id);
			return  res;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Integer getById(Integer id) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			Integer res = knowledgeThumbsUpDao.getById(id);
			return  res;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public Integer save(KnowledgeThumbsUp knowledgeThumbsUp) {
		log.debug("update Inventory instance");
		Integer res=0;
		try {
			log.debug("getById successful");
			knowledgeThumbsUpDao.save(knowledgeThumbsUp);
			res=1;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		return res;
	}

	@Override
	public void update(KnowledgeThumbsUp knowledgeThumbsUp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByAccId(Integer knowledgeAccId) {
		try {
			log.debug("delete successful");
			knowledgeThumbsUpDao.deleteByAccId(knowledgeAccId);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
