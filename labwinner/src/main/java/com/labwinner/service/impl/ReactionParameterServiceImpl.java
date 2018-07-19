package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ReactionParameterDao;
import com.labwinner.domain.ReactionParameter;
import com.labwinner.service.ReactionParameterService;

@Service
public class ReactionParameterServiceImpl implements ReactionParameterService {

	private static final Logger log = LoggerFactory
			.getLogger(ReactionParameterServiceImpl.class);
	@Autowired
	private ReactionParameterDao reactionParameterDao;
	
	@Override
	public void save(ReactionParameter reactionParameter) {
		log.debug("saving Inventory instance");
		try {
			reactionParameterDao.save(reactionParameter);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(ReactionParameter reactionParameter) {
		log.debug("saving Inventory instance");
		try {
			reactionParameterDao.update(reactionParameter);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving Inventory instance");
		try {
			reactionParameterDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<ReactionParameter> getById(Integer id) {
		log.debug("saving Inventory instance");
		try {
			return reactionParameterDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionParameter> getAll() {
		log.debug("saving Inventory instance");
		try {
			return reactionParameterDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
