package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ModifyCodeDao;
import com.labwinner.domain.ModifyCode;
import com.labwinner.service.ModifyCodeService;

@Service
public class ModifyCodeServiceImpl implements ModifyCodeService{

	private static Logger log = LoggerFactory
			.getLogger(ModifyCodeServiceImpl.class);
	@Autowired
	private ModifyCodeDao modifyCodeDao;
	
	@Override
	public void save(ModifyCode modifyCode) {
		log.debug("saving ChemicalImage instance");
		try {
			modifyCodeDao.save(modifyCode);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(ModifyCode modifyCode) {
		modifyCodeDao.update(modifyCode);
		
	}

	@Override
	public void delete(Integer id) {
		modifyCodeDao.delete(id);
		
	}

	@Override
	public List<ModifyCode> getAll() {
		log.debug("getAll ModifyCode instance");
		try {
			log.debug("getAll successful");
			return modifyCodeDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
		
	}

	@Override
	public ModifyCode getById(Integer id) {
		log.debug("getById ModifyCode instance");
		try {
			log.debug("getById successful");
			return modifyCodeDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
	}

	@Override
	public List<ModifyCode> getAllPageable(String keyword) {
		log.debug("getAllPageable ModifyCode instance");
		try {
			log.debug("getAllPageable successful");
			return modifyCodeDao.getAllPageable(keyword);
			
		} catch (RuntimeException re) {
			log.error("getAllPageable failed", re);
			throw re;
		}
	}

	@Override
	public List<ModifyCode> getFlagAll() {
		log.debug("update ModifyCode instance");
		try {
			log.debug("getFlagAll successful");
			return modifyCodeDao.getFlagAll();
			
		} catch (RuntimeException re) {
			log.error("getFlagAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ModifyCode> getAllName() {
		log.debug("getAllName ModifyCode instance");
		try {
			log.debug("get successful");
			return modifyCodeDao.getAllName();
			
		} catch (RuntimeException re) {
			log.error("getAllName failed", re);
			throw re;
		}
	}

}
