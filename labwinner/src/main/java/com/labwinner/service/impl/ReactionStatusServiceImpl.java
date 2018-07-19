package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ReactionStatusDao;
import com.labwinner.domain.ReactionStatus;
import com.labwinner.service.ReactionStatusService;

@Service
public class ReactionStatusServiceImpl implements ReactionStatusService{

	private static final Logger log = LoggerFactory
			.getLogger(PrototypeServiceImpl.class);
	@Autowired
	private ReactionStatusDao reactionStatusDao;
	
	@Override
	public void save(ReactionStatus reactionStatus) {
		log.debug("saving Inventory instance");
		try {
			reactionStatusDao.save(reactionStatus);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<ReactionStatus> getAll() {
		log.debug("saving Inventory instance");
		try {
			return reactionStatusDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionStatus> getByType(Integer id) {
		log.debug("saving Inventory instance");
		try {
			return reactionStatusDao.getByType(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(ReactionStatus reactionStatus) {
		
		reactionStatusDao.update(reactionStatus);
	}

	@Override
	public void delete(Integer id) {
		reactionStatusDao.delete(id);
		
	}

	@Override
	public ReactionStatus getById(Integer id) {
		// TODO Auto-generated method stub
		return reactionStatusDao.getById(id);
	}

}
