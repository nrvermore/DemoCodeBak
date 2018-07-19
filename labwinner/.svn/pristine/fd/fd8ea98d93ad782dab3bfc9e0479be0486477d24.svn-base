package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ReactionDesignParameterDao;
import com.labwinner.domain.ReactionDesignParameter;
import com.labwinner.service.ReactionDesignParameterService;


@Service
public class ReactionDesignParameterServiceImpl implements ReactionDesignParameterService{

	private static final Logger log = LoggerFactory
			.getLogger(ReactionDesignParameterServiceImpl.class);
	
	@Autowired
	private ReactionDesignParameterDao reactionDesignParameterDao;
	
	@Override
	public void save(ReactionDesignParameter reactionDesignParameter) {
		log.debug("saving Inventory instance");
		try {
			reactionDesignParameterDao.save(reactionDesignParameter);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(ReactionDesignParameter reactionDesignParameter) {
		log.debug("update Inventory instance");
		try {
			reactionDesignParameterDao.update(reactionDesignParameter);
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
			reactionDesignParameterDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public List<ReactionDesignParameter> getAll() {
		log.debug("update Inventory instance");
		try {
			log.debug("getAll successful");
			return reactionDesignParameterDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public ReactionDesignParameter getById(Integer id) {
		log.debug("getById Inventory instance");
		try {
			log.debug("getById successful");
			return reactionDesignParameterDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesignParameter> getByName(String name) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<ReactionDesignParameter> reactionDesignParameters =reactionDesignParameterDao.getByName(name);
			return  reactionDesignParameters;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
