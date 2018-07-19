package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ExecuteParameterDao;
import com.labwinner.domain.ReactionExecuteParameter;
import com.labwinner.service.ExecuteParemeterService;

@Service
public class ExecuteParameterServiceImpl implements ExecuteParemeterService{

	private static final Logger log = LoggerFactory
			.getLogger(ExecuteParameterServiceImpl.class);
	@Autowired
	private ExecuteParameterDao executeParameterDao;
	
	@Override
	public void save(ReactionExecuteParameter executeParemeter) {
		log.debug("saving Inventory instance");
		try {
			executeParameterDao.save(executeParemeter);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(ReactionExecuteParameter executeParemeter) {
		log.debug("saving Inventory instance");
		try {
			executeParameterDao.update(executeParemeter);
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
			executeParameterDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public ReactionExecuteParameter getById(Integer id) {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return executeParameterDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void deleteByProcessId(Integer id) {
		log.debug("saving Inventory instance");
		try {
			executeParameterDao.deleteByProcessId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void batchRemove(List<Integer> ids) {
		log.debug("saving Inventory instance");
		try {
			executeParameterDao.batchRemove(ids);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
