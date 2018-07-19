package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ExecuteChemicalGroupDao;
import com.labwinner.domain.ExecuteChemicalGroup;
import com.labwinner.service.ExecuteChemicalGroupService;

@Service
public class ExecuteChemicalGroupServiceImpl implements ExecuteChemicalGroupService{
	
	private static final Logger log = LoggerFactory
			.getLogger(ExecuteChemicalGroupServiceImpl.class);
	@Autowired
	private ExecuteChemicalGroupDao executeChemicalGroupDao;

	@Override
	public void save(ExecuteChemicalGroup executeChemicalGroup) {
		log.debug("saving ExecuteChemicalGroup instance");
		try {
			executeChemicalGroupDao.save(executeChemicalGroup);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(ExecuteChemicalGroup executeChemicalGroup) {
		log.debug("saving ExecuteChemicalGroup instance");
		try {
			executeChemicalGroupDao.update(executeChemicalGroup);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving ExecuteChemicalGroup instance");
		try {
			executeChemicalGroupDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByProcessId(Integer id) {
		log.debug("saving ExecuteChemicalGroup instance");
		try {
			executeChemicalGroupDao.deleteByProcessId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<ExecuteChemicalGroup> getByProcessId(Integer id) {
		log.debug("saving ExecuteChemicalGroup instance");
		try {
		  return executeChemicalGroupDao.getByProcessId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void saveOlder(ExecuteChemicalGroup executeChemicalGroup) {
		log.debug("saving ExecuteChemicalGroup instance");
		try {
			executeChemicalGroupDao.saveOlder(executeChemicalGroup);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Integer> getGroups() {
		log.debug("saving ExecuteChemicalGroup instance");
		try {
		  return executeChemicalGroupDao.getGroups();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public ExecuteChemicalGroup getById(Integer id) {
		log.debug("saving ExecuteChemicalGroup instance");
		try {
		  return executeChemicalGroupDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
