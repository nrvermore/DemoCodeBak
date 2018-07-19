package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ExecuteChemicalDao;
import com.labwinner.domain.ExecuteChemical;
import com.labwinner.service.ExecuteChemicalService;

@Service
public class ExecuteChemicalServiceImpl implements  ExecuteChemicalService {

	private static final Logger log = LoggerFactory
			.getLogger(ExecuteChemicalServiceImpl.class);
	@Autowired
	private ExecuteChemicalDao executeChemicalDao;
	
	@Override
	public void save(ExecuteChemical executeChemical) {
		log.debug("saving Inventory instance");
		try {
			executeChemicalDao.save(executeChemical);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(ExecuteChemical executeChemical) {
		log.debug("saving Inventory instance");
		try {
			executeChemicalDao.update(executeChemical);
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
			executeChemicalDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<ExecuteChemical> getById(Integer id) {
		log.debug("saving Inventory instance");
		try {
			return executeChemicalDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByProcessId(Integer id) {
		log.debug("saving Inventory instance");
		try {
			executeChemicalDao.deleteByProcessId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Integer> getInventorys() {
		log.debug("saving Inventory instance");
		try {
			return executeChemicalDao.getInventorys();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByGroupId(Integer id) {
		log.debug("saving Inventory instance");
		try {
			executeChemicalDao.deleteByGroupId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ExecuteChemical> getByGroupId(Integer id) {
		log.debug("saving Inventory instance");
		try {
			return executeChemicalDao.getByGroupId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public ExecuteChemical getByChmicalId(Integer id) {
		log.debug("saving Inventory instance");
		try {
			return executeChemicalDao.getByChmicalId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
