package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ExecuteSolutionDao;
import com.labwinner.domain.ExecuteSolution;
import com.labwinner.service.ExecuteSolutionService;

@Service
public class ExecuteSolutionServiceImpl implements ExecuteSolutionService{

	
	private static final Logger log = LoggerFactory
			.getLogger(ExecuteSolutionServiceImpl.class);
	@Autowired
	private ExecuteSolutionDao executeSolutionDao;
	
	@Override
	public void save(ExecuteSolution executeSolution) {
		log.debug("saving Solution instance");
		try {
			executeSolutionDao.save(executeSolution);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(ExecuteSolution executeSolution) {
		log.debug("saving Solution instance");
		try {
			executeSolutionDao.update(executeSolution);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving Solution instance");
		try {
			executeSolutionDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByProcessId(Integer id) {
		log.debug("saving Solution instance");
		try {
			executeSolutionDao.deleteByProcessId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void batchRemove(List<Integer> ids) {
		log.debug("saving Solution instance");
		try {
			executeSolutionDao.batchRemove(ids);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ExecuteSolution> getById(Integer id) {
		log.debug("saving Solution instance");
		try {
			return executeSolutionDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Integer> getSolutions() {
		log.debug("saving Solution instance");
		try {
			return executeSolutionDao.getSolutions();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
