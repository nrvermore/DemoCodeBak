package com.labwinner.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.labwinner.dao.MagazineDao;
import com.labwinner.domain.Magazine;
import com.labwinner.service.MagazineService;

@Service
public class MagazineServiceImpl implements MagazineService {
	
	private static final Logger log = LoggerFactory
			.getLogger(MagazineServiceImpl.class);
	
	@Autowired
	private MagazineDao magazineDao;
	@Transactional
	@Override
	public void save(Magazine magazine) {
		log.debug("saving Magazine instance");
		try {
			magazineDao.save(magazine);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw new RuntimeException();
		}
	}

	@Override
	public void update(Magazine magazine) {
		try {
			log.debug("getById successful");
			magazineDao.update(magazine);	
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		try {
			log.debug("getById successful");
			magazineDao.delete(id);	
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Magazine getById(Integer id) {
		try {
			log.debug("getById successful");
			Magazine magazine =magazineDao.getById(id);
			return  magazine;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Magazine> getByName(Map<String, String> map) {
		log.debug("getByName Magazine instance");
		try {
			log.debug("getById successful");
			return magazineDao.getByName(map);
		} catch (RuntimeException re) {
			log.error("select failed", re);
			throw re;
		}
	}

	@Override
	public List<Magazine> getAll() {
		try {
			log.debug("getById successful");
			List<Magazine> magazines =magazineDao.getAll();
			return  magazines;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Integer getMaxMagazineId() {
		log.debug("getMaxMagazineId id instance");
		try {
			log.debug("getById successful");
			return magazineDao.getMaxMagazineId();
		} catch (RuntimeException re) {
			log.error("select failed", re);
			throw re;
		}
	}

	@Override
	public List<Magazine> getAllPageable(String keyword) {
		try {
			log.debug("getById successful");
			List<Magazine> magazines =magazineDao.getAllPageable(keyword);
			return  magazines;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Magazine> getAllName() {
		try {
			log.debug("getAllName successful");
			List<Magazine> magazines =magazineDao.getAllName();
			return  magazines;
			
		} catch (RuntimeException re) {
			log.error("getAllName failed", re);
			throw re;
		}
	}

	
}
