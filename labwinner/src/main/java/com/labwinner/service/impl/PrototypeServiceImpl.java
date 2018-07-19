package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.PrototypeDao;
import com.labwinner.domain.Prototype;
import com.labwinner.service.PrototypeService;

@Service
public class PrototypeServiceImpl implements PrototypeService{

	private static final Logger log = LoggerFactory
			.getLogger(PrototypeServiceImpl.class);
	@Autowired
	private PrototypeDao prototypeDao;
	
	@Override
	public void save(Prototype prototype) {
		log.debug("saving prototype instance");
		try {
			prototypeDao.save(prototype);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(Prototype prototype) {
		log.debug("saving prototype instance");
		try {
			prototypeDao.update(prototype);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving prototype instance");
		try {
			prototypeDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public Prototype getById(Integer id) {
		log.debug("saving prototype instance");
		try {
			return prototypeDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Prototype> getAll() {
		log.debug("saving prototype instance");
		try {
			return prototypeDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Prototype> getKeyWord(String keyword) {
		log.debug("saving prototype instance");
		try {
			return prototypeDao.getKeyWord(keyword);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByProcessId(Integer id) {
		log.debug("saving prototype instance");
		try {
			prototypeDao.deleteByProcessId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<String> getBarCodes() {
		log.debug("saving prototype instance");
		try {
			return prototypeDao.getBarCodes();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Prototype findByBarCode(String barcode) {
		log.debug("get prototype instance");
		try {
			return prototypeDao.findByBarCode(barcode);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<Prototype> getByProcessId(Integer id) {
		log.debug("get prototype instance");
		try {
			return prototypeDao.getByProcessId(id);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}
