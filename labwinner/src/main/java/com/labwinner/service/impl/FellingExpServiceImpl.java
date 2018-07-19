package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.FellingExpDao;
import com.labwinner.domain.FellingExp;
import com.labwinner.service.FellingExpService;

@Service
public class FellingExpServiceImpl implements FellingExpService{

	private static final Logger log = LoggerFactory
			.getLogger(FellingExpServiceImpl.class);
	
	@Autowired
	private FellingExpDao fellingExpDao;
	
	@Override
	public void save(FellingExp fellingExp) {
		log.debug("saving Inventory instance");
		try {
			fellingExpDao.save(fellingExp);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
		
	}

	@Override
	public void update(FellingExp fellingExp) {
		log.debug("update Inventory instance");
		try {
			fellingExpDao.update(fellingExp);
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
			fellingExpDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public FellingExp getById(Integer id) {
		log.debug("getById Inventory instance");
		try {
			log.debug("getById successful");
			return fellingExpDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<FellingExp> getByName(String name) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<FellingExp> selfPapers =fellingExpDao.getByName(name);
			return  selfPapers;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<FellingExp> getAll() {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<FellingExp> selfPapers =fellingExpDao.getAll();
			return  selfPapers;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
