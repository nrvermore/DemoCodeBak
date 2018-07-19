package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.InventoryUserDao;
import com.labwinner.domain.InventoryUser;
import com.labwinner.service.InventoryUserService;

@Service
public class InventoryUserServiceImpl implements InventoryUserService{

	private static final Logger log = LoggerFactory
			.getLogger(InventoryUserServiceImpl.class);
	@Autowired
	private InventoryUserDao inventoryUserDao;
	
	@Override
	public void save(InventoryUser inventoryUser) {
		log.debug("saving Inventory instance");
		try {
			inventoryUserDao.save(inventoryUser);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(InventoryUser inventoryUser) {
		log.debug("saving Inventory instance");
		try {
			inventoryUserDao.update(inventoryUser);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving Inventory instance");
		try {
			inventoryUserDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<InventoryUser> getById(Integer id) {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return inventoryUserDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void batchRemove(List<Integer> ids) {
		log.debug("saving Inventory instance");
		try {
			inventoryUserDao.batchRemove(ids);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
