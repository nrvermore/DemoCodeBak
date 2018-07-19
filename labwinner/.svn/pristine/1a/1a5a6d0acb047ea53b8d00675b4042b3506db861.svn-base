package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ReactionDeviceDao;
import com.labwinner.domain.ReactionDevice;
import com.labwinner.service.ReactionDeviceService;

@Service
public class ReactionDeviceServiceImpl implements ReactionDeviceService{

	private static final Logger log = LoggerFactory
			.getLogger(ExecuteChemicalServiceImpl.class);
	@Autowired
	private ReactionDeviceDao reactionDeviceDao;
	
	@Override
	public void save(ReactionDevice reactionDevice) {
		log.debug("saving Inventory instance");
		try {
			reactionDeviceDao.save(reactionDevice);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(ReactionDevice reactionDevice) {
		log.debug("saving Inventory instance");
		try {
			reactionDeviceDao.update(reactionDevice);
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
			reactionDeviceDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public ReactionDevice getById(Integer id) {
		log.debug("saving Inventory instance");
		try {
			return reactionDeviceDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByProcessId(Integer id) {
		log.debug("saving Inventory instance");
		try {
			reactionDeviceDao.deleteByProcessId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

}
