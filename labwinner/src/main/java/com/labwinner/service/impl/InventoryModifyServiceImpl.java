package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.InventoryModifyDao;
import com.labwinner.domain.InventoryModify;
import com.labwinner.service.InventoryModifyService;

/**
 * A data access object (DAO) providing persistence and search support for
 * InventoryModify entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.labwinner.hibernate.InventoryModify
 * @author MyEclipse Persistence Tools
 */

@Service
public class InventoryModifyServiceImpl implements InventoryModifyService{
	private static final Logger log = LoggerFactory
			.getLogger(InventoryModifyServiceImpl.class);
	@Autowired
	private InventoryModifyDao inventoryModifyDao;

	public void save(InventoryModify inventoryModify) {
		log.debug("saving Inventory instance");
		try {
			inventoryModifyDao.save(inventoryModify);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Integer id) {
		log.debug("deleting Inventory instance");
		try {
			inventoryModifyDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	
	public List<InventoryModify> getAll(){
		log.debug("update Inventory instance");
		try {
			log.debug("getAll successful");
			return inventoryModifyDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public List<InventoryModify> getById(Integer id){
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			return inventoryModifyDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<InventoryModify> getReactions(Integer id) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			return inventoryModifyDao.getReactions(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void batchRemove(List<Integer> ids) {
		log.debug("deleting Inventory instance");
		try {
			inventoryModifyDao.batchRemove(ids);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Integer getProcessCount(Integer processId, Integer inventoryId) {
		log.debug("getProcessCount Inventory instance");
		try {
			log.debug("getProcessCount successful");
			return inventoryModifyDao.getProcessCount(processId,inventoryId);
			
		} catch (RuntimeException re) {
			log.error("getProcessCount failed", re);
			throw re;
		}
	}
}