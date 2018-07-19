package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.labwinner.dao.SolutionDesignDosageDao;
import com.labwinner.domain.SolutionDesignDosage;
import com.labwinner.service.SolutionDesignDosageService;

@Service
public class SolutionDesignDosageServiceImpl implements SolutionDesignDosageService{

	private static final Logger log = LoggerFactory
			.getLogger(SolutionDesignDosageServiceImpl.class);
	
	@Autowired
	private SolutionDesignDosageDao solutionDesignDosageDao;

	@Override
	public void save(SolutionDesignDosage solutionDesignDosage) {
		log.debug("saving SolutionDesignDosage instance");
		try {
			solutionDesignDosageDao.save(solutionDesignDosage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(SolutionDesignDosage solutionDesignDosage) {
		log.debug("saving SolutionDesignDosage instance");
		try {
			solutionDesignDosageDao.update(solutionDesignDosage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving SolutionDesignDosage instance");
		try {
			solutionDesignDosageDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public SolutionDesignDosage getById(Integer id) {
		log.debug("saving SolutionDesignDosage instance");
		try {
			return solutionDesignDosageDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
		
	}

	@Override
	public List<SolutionDesignDosage> getAll() {
		log.debug("update SolutionDesignDosage instance");
		try {
			log.debug("getAll successful");
			return solutionDesignDosageDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public List<SolutionDesignDosage> getByName(String name) {
		log.debug("update SolutionDesignDosage instance");
		try {
			log.debug("getAll successful");
			return solutionDesignDosageDao.getByName(name);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}
}
