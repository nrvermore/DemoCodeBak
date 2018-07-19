package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.InventoryGroupsDao;
import com.labwinner.domain.InventoryGroups;
import com.labwinner.service.InventoryGroupsService;

@Service
public class InventoryGroupsServiceImpl implements InventoryGroupsService{

	private static final Logger log = LoggerFactory
			.getLogger(InventoryGroupsServiceImpl.class);
	
	@Autowired
	private InventoryGroupsDao inventoryGroupsDao;
	
	@Override
	public void save(InventoryGroups inventoryGroups) {
		log.debug("saving InventoryGroups instance");
		try {
			inventoryGroupsDao.save(inventoryGroups);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete InventoryGroups instance");
		try {
			inventoryGroupsDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(InventoryGroups inventoryGroups) {
		log.debug("update InventoryGroups instance");
		try {
			inventoryGroupsDao.update(inventoryGroups);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
		
		
	}

	@Override
	public List<InventoryGroups> getUserInventorys(Integer userId) {
		log.debug("update InventoryGroups instance");
		try {
			return inventoryGroupsDao.getUserInventorys(userId);
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public List<InventoryGroups> getByKeyword(String keyword,Integer userId,Integer type,Integer locId) {
		log.debug("update InventoryGroups instance");
		try {
			return inventoryGroupsDao.getByKeyword(keyword,userId,type,locId);
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public List<InventoryGroups> getAllInventorys() {
		log.debug("update InventoryGroups instance");
		try {
			return inventoryGroupsDao.getAllInventorys();
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public List<InventoryGroups> getListByKeyword(String keyword,Integer type,Integer locId) {
		log.debug("update InventoryGroups instance");
		try {
			return inventoryGroupsDao.getListByKeyword(keyword,type,locId);
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public InventoryGroups getById(Integer id) {
		log.debug("update InventoryGroups instance");
		try {
			return inventoryGroupsDao.getById(id);
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public List<InventoryGroups> getAppByKeyword(String keyword, Integer userId) {
		log.debug("update InventoryGroups instance");
		try {
			return inventoryGroupsDao.getAppByKeyword(keyword,userId);
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public List<InventoryGroups> getAppListByKeyword(String keyword) {
		log.debug("update InventoryGroups instance");
		try {
			return inventoryGroupsDao.getAppListByKeyword(keyword);
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

}
