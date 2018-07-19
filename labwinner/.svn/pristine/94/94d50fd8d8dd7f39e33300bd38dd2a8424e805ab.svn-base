package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SolutionDosagesDao;
import com.labwinner.domain.SolutionDosages;
import com.labwinner.service.SolutionDosagesService;

@Service
public class SolutionDosagesServiceImpl implements SolutionDosagesService{

	private static final Logger log = LoggerFactory
			.getLogger(SolutionDosagesServiceImpl.class);
	
	@Autowired
	private SolutionDosagesDao solutionDosagesDao;
	
	@Override
	public void save(SolutionDosages solutionDosages) {
		log.debug("saving solutionDosages instance");
		try {
			solutionDosagesDao.save(solutionDosages);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Integer> getAllInventories() {
		log.debug("saving solutionDosages instance");
		try {
			return solutionDosagesDao.getAllInventories();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<SolutionDosages> getBySolutionId(Integer id) {
		log.debug("saving solutionDosages instance");
		try {
			return solutionDosagesDao.getBySolutionId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving solutionDosages instance");
		try {
			solutionDosagesDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
