package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.InventoriesDao;
import com.labwinner.domain.Inventories;
import com.labwinner.service.InventoriesService;

@Service
public class InventoriesServiceImpl implements InventoriesService{
	
	private static final Logger log = LoggerFactory
			.getLogger(InventoriesServiceImpl.class);

	@Autowired
	private InventoriesDao inventoriesDao;
	
	@Override
	public void save(Inventories inventories) {
		log.debug("saving Inventory instance");
		try {
			inventoriesDao.save(inventories);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(Inventories inventories) {
		log.debug("update Inventory instance");
		try {
			inventoriesDao.update(inventories);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
		
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete Inventory instance");
		try {
			inventoriesDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void deleteByGroupId(Integer id) {
		log.debug("delete Inventory instance");
		try {
			inventoriesDao.deleteByGroupId(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public List<Inventories> getByGroupId(Integer id,Integer cid) {
		log.debug("getByGroupId Inventory instance");
		try {
			return inventoriesDao.getByGroupId(id,cid);
		} catch (RuntimeException re) {
			log.error("getByGroupId failed", re);
			throw re;
		}
	}

	@Override
	public List<Inventories> getAllInventorys() {
		log.debug("getByGroupId Inventory instance");
		try {
			return inventoriesDao.getAllInventorys();
		} catch (RuntimeException re) {
			log.error("getByGroupId failed", re);
			throw re;
		}
	}

	@Override
	public List<Inventories> findUserInventorys(String keyword) {
		log.debug("findUserInventorys Inventory instance");
		try {
			return inventoriesDao.findUserInventorys(keyword);
		} catch (RuntimeException re) {
			log.error("findUserInventorys failed", re);
			throw re;
		}
	}

	@Override
	public List<Inventories> getAll(Integer userId) {
		log.debug("findUserInventorys Inventory instance");
		try {
			return inventoriesDao.getAll(userId);
		} catch (RuntimeException re) {
			log.error("findUserInventorys failed", re);
			throw re;
		}
	}

	@Override
	public Inventories getById(Integer id) {
		log.debug("getById Inventory instance");
		try {
			return inventoriesDao.getById(id);
		} catch (RuntimeException re) {
			log.error("getById failed", re);
			throw re;
		}
	}

	@Override
	public List<Inventories> getByKeyword(String keyword,
			Integer userId) {
		log.debug("getByKeyword Inventory instance");
		try {
			return inventoriesDao.getByKeyword(keyword,userId);
		} catch (RuntimeException re) {
			log.error("getByKeyword failed", re);
			throw re;
		}
	}

	@Override
	public List<Inventories> findByName(String keyword) {
		log.debug("getByKeyword Inventory instance");
		try {
			return inventoriesDao.findByName(keyword);
		} catch (RuntimeException re) {
			log.error("getByKeyword failed", re);
			throw re;
		}
	}

	@Override
	public List<Inventories> findNameByUser(String keyword, Integer userId) {
		log.debug("findNameByUser Inventory instance");
		try {
			return inventoriesDao.findNameByUser(keyword,userId);
		} catch (RuntimeException re) {
			log.error("findNameByUser failed", re);
			throw re;
		}
	}

	@Override
	public void updateInventory(Inventories inventories) {
		log.debug("updateInventory Inventory instance");
		try {
			inventoriesDao.updateInventory(inventories);
		} catch (RuntimeException re) {
			log.error("updateInventory failed", re);
			throw re;
		}
		
	}

	@Override
	public List<Inventories> getHotWords() {
		log.debug("getHotWords Inventory instance");
		try {
			return inventoriesDao.getHotWords();
		} catch (RuntimeException re) {
			log.error("getHotWords failed", re);
			throw re;
		}
	}

	@Override
	public List<String> getBarCodes() {
		log.debug("getHotWords Inventory instance");
		try {
			return inventoriesDao.getBarCodes();
		} catch (RuntimeException re) {
			log.error("getHotWords failed", re);
			throw re;
		}
	}

	@Override
	public Inventories findByBarCode(String barcode) {
		log.debug("findByBarCode Inventory instance");
		try {
			return inventoriesDao.findByBarCode(barcode);
		} catch (RuntimeException re) {
			log.error("findByBarCode failed", re);
			throw re;
		}
	}

	@Override
	public int getInventoryNum(String roleName, Integer userId) {
		log.debug("getInventoryNum Inventory instance");
		try {
			return inventoriesDao.getInventoryNum(roleName,userId);
		} catch (RuntimeException re) {
			log.error("getInventoryNum failed", re);
			throw re;
		}
	}

	@Override
	public List<Inventories> getByLocationId(Integer id) {
		log.debug("getByLocationId Inventory instance");
		try {
			return inventoriesDao.getByLocationId(id);
		} catch (RuntimeException re) {
			log.error("getByLocationId failed", re);
			throw re;
		}
	}

	@Override
	public Inventories getReactions(Integer id) {
		log.debug("getReactions Inventory instance");
		try {
			return inventoriesDao.getReactions(id);
		} catch (RuntimeException re) {
			log.error("getReactions failed", re);
			throw re;
		}
	}

	@Override
	public Inventories getUserReactions(Integer userId, Integer id) {
		log.debug("getUserReactions Inventory instance");
		try {
			return inventoriesDao.getUserReactions(userId,id);
		} catch (RuntimeException re) {
			log.error("getUserReactions failed", re);
			throw re;
		}
	}

	@Override
	public List<Inventories> getBySupId(Integer id) {
		log.debug("getBySupId Inventory instance");
		try {
			return inventoriesDao.getBySupId(id);
		} catch (RuntimeException re) {
			log.error("getBySupId failed", re);
			throw re;
		}
	}

	@Override
	public List<Inventories> getUserHotWords(Integer userId) {
		log.debug("getBySupId Inventory instance");
		try {
			return inventoriesDao.getUserHotWords(userId);
		} catch (RuntimeException re) {
			log.error("getBySupId failed", re);
			throw re;
		}
	}

	@Override
	public List<Inventories> getByGroupIds(List<Integer> groupIds) {
		log.debug("getBySupId Inventory instance");
		try {
			return inventoriesDao.getByGroupIds(groupIds);
		} catch (RuntimeException re) {
			log.error("getBySupId failed", re);
			throw re;
		}
	}

	@Override
	public List<Inventories> getScreenInventorys() {
		log.debug("getBySupId Inventory instance");
		try {
			return inventoriesDao.getScreenInventorys();
		} catch (RuntimeException re) {
			log.error("getBySupId failed", re);
			throw re;
		}
	}

}
