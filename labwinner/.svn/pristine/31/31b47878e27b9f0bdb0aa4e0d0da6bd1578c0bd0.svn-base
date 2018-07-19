package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SolutionDao;
import com.labwinner.domain.SolutionEntity;
import com.labwinner.service.SolutionService;

@Service
public class SolutionServiceImpl implements SolutionService{

	private static final Logger log = LoggerFactory
			.getLogger(SolutionServiceImpl.class);
	@Autowired
	private SolutionDao solutionDao;
	
	@Override
	public void save(SolutionEntity solutionEntity) {
		log.debug("saving SolutionEntity instance");
		try {
			solutionDao.save(solutionEntity);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(SolutionEntity solutionEntity) {
		log.debug("saving SolutionEntity instance");
		try {
			solutionDao.update(solutionEntity);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving SolutionEntity instance");
		try {
			solutionDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public SolutionEntity getById(Integer id) {
		log.debug("saving SolutionEntity instance");
		try {
			return solutionDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<SolutionEntity> getBykeyword(String keyword) {
		log.debug("saving SolutionEntity instance");
		try {
			return solutionDao.getBykeyword(keyword);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<SolutionEntity> getAll() {
		log.debug("saving SolutionEntity instance");
		try {
			return solutionDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<String> getBarCodes() {
		log.debug("saving SolutionEntity instance");
		try {
			return solutionDao.getBarCodes();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public SolutionEntity getByBarcode(String barcode) {
		log.debug("saving SolutionEntity instance");
		try {
			return solutionDao.getByBarcode(barcode);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
