package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DesignDosageDao;
import com.labwinner.domain.DesignDosage;
import com.labwinner.service.DesignDosageService;


@Service
public class DesignDosageServiceImpl implements DesignDosageService{

	private static final Logger log = LoggerFactory
			.getLogger(DesignDosageServiceImpl.class);
	
	@Autowired
	private DesignDosageDao designDosageDao;
	
	@Override
	public void save(DesignDosage designDosage) {
		log.debug("saving Inventory instance");
		try {
			designDosageDao.save(designDosage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(DesignDosage designDosage) {
		log.debug("update Inventory instance");
		try {
			designDosageDao.update(designDosage);
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
			designDosageDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public DesignDosage getById(Integer id) {
		log.debug("getById Inventory instance");
		try {
			log.debug("getById successful");
			return designDosageDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<DesignDosage> getAll() {
		log.debug("update Inventory instance");
		try {
			log.debug("getAll successful");
			return designDosageDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<DesignDosage> getByName(String name) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<DesignDosage> DesignDosages =designDosageDao.getByName(name);
			return  DesignDosages;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
