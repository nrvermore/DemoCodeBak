package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ChemicalParameterDao;
import com.labwinner.domain.ChemicalParameter;
import com.labwinner.service.ChemicalParameterService;


/**
 * A data access object (DAO) providing persistence and search support for
 * ChemicalParameter entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.labwinner.hibernate.ChemicalParameter
 * @author MyEclipse Persistence Tools
 */
@Service
public class ChemicalParameterServiceImpl implements ChemicalParameterService{
	private static final Logger log = LoggerFactory
			.getLogger(ChemicalParameterServiceImpl.class);
	@Autowired
	private ChemicalParameterDao chemicalParameterDao;

	public void save(ChemicalParameter chemicalParameter) {
		log.debug("saving Chemical instance");
		try {
			chemicalParameterDao.save(chemicalParameter);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Integer id) {
		log.debug("deleting Chemical instance");
		try {
			chemicalParameterDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	
	public List<ChemicalParameter> getAll(){
		log.debug("update Chemical instance");
		try {
			log.debug("get successful");
			return chemicalParameterDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public ChemicalParameter getById(Integer id){
		log.debug("update Chemical instance");
		try {
			log.debug("get successful");
			return chemicalParameterDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<ChemicalParameter> getByKeyword(String keyword) {
		log.debug("update Chemical instance");
		try {
			log.debug("get successful");
			return chemicalParameterDao.getByKeyword(keyword);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void update(ChemicalParameter chemicalParameter) {
		log.debug("update Chemical instance");
		try {
			log.debug("get successful");
			 chemicalParameterDao.update(chemicalParameter);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void saveByInventory(ChemicalParameter chemicalParameter) {
		log.debug("saving Chemical instance");
		try {
			chemicalParameterDao.saveByInventory(chemicalParameter);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void lockChemical(Integer id, String isLock) {
		log.debug("saving Chemical instance");
		try {
			chemicalParameterDao.lockChemical(id,isLock);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}


	@Override
	public Integer getNameCount(String name) {
		log.debug("update Chemical instance");
		try {
			log.debug("get successful");
			return chemicalParameterDao.getNameCount(name);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Integer getCasCount(String cas) {
		log.debug("update Chemical instance");
		try {
			log.debug("get successful");
			return chemicalParameterDao.getCasCount(cas);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
}