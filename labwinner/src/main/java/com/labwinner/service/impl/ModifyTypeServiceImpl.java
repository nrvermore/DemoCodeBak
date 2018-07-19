package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ModifyTypeDao;
import com.labwinner.domain.MaterialType;
import com.labwinner.domain.ModifyType;
import com.labwinner.service.ModifyTypeService;

/**
 * A data access object (DAO) providing persistence and search support for
 * ModifyType entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.labwinner.hibernate.ModifyType
 * @author MyEclipse Persistence Tools
 */

@Service
public class ModifyTypeServiceImpl implements ModifyTypeService{
	private static final Logger log = LoggerFactory
			.getLogger(ModifyTypeServiceImpl.class);
	
	@Autowired 
	private ModifyTypeDao modifyTypeDao;
	
	public void save(ModifyType modifyType) {
		log.debug("saving ModifyType instance");
		try {
			modifyTypeDao.save(modifyType);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public List<ModifyType> getAll() {
		log.debug("finding all ModifyType instances");
		try {
			List<ModifyType> modifyTypeList= modifyTypeDao.getAll();
			return modifyTypeList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		modifyTypeDao.delete(id);
		
	}

	@Override
	public ModifyType getById(Integer id) {
		// TODO Auto-generated method stub
		return modifyTypeDao.getById(id);
	}

	@Override
	public void update(ModifyType modifyType) {
		modifyTypeDao.update(modifyType);
		
	}

	@Override
	public List<ModifyType> getAllPageable(String keyword) {
		log.debug("finding all ModifyType instances");
		try {
			List<ModifyType> modifyTypeList= modifyTypeDao.getAllPageable(keyword);
			return modifyTypeList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}