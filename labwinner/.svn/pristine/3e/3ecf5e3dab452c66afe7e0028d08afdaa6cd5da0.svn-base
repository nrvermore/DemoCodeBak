package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.InventoryGroupDao;
import com.labwinner.domain.InventoryGroup;
import com.labwinner.service.InventoryGroupService;

@Service
public class InventoryGroupServiceImpl implements InventoryGroupService{

	private static final Logger log = LoggerFactory
			.getLogger(InventoryGroupServiceImpl.class);

	@Autowired
	private InventoryGroupDao inventoryGroupDao;
	@Override
	public void save(InventoryGroup inventoryGroup) {
		log.debug("save InventoryGroup instances");
		try {
			inventoryGroupDao.save(inventoryGroup);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete InventoryGroup instances");
		try {
			inventoryGroupDao.delete(id);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public List<InventoryGroup> getUserInventorys(Integer userId) {
		log.debug("delete InventoryGroup instances");
		try {
			return inventoryGroupDao.getUserInventorys(userId);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<InventoryGroup> getAllInventorys() {
		log.debug("delete InventoryGroup instances");
		try {
			return inventoryGroupDao.getAllInventorys();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void update(InventoryGroup inventoryGroup) {
		log.debug("save InventoryGroup instances");
		try {
			inventoryGroupDao.update(inventoryGroup);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<InventoryGroup> getByKeyword(Integer name, String keyword,
			Integer userId) {
		log.debug("delete InventoryGroup instances");
		try {
			return inventoryGroupDao.getByKeyword(name,keyword,userId);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<InventoryGroup> getListByKeyword(String keyword, Integer name) {
		log.debug("delete InventoryGroup instances");
		try {
			return inventoryGroupDao.getListByKeyword(keyword,name);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
