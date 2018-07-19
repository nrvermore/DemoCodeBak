package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.InventoryLocationDao;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.service.InventoryLocationService;

@Service
public class InventoryLocationServiceImpl implements InventoryLocationService{
	private static final Logger log = LoggerFactory
			.getLogger(InventoryLocationServiceImpl.class);
	
	@Autowired 
	private InventoryLocationDao inventoryLocationDao;
	
	public void save(InventoryLocation inventoryLocation) {
		log.debug("saving Measurement instance");
		try {
			inventoryLocationDao.save(inventoryLocation);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public List<InventoryLocation> getAll() {
		log.debug("finding all Measurement instances");
		try {
			List<InventoryLocation> LocaList= inventoryLocationDao.getAll();
			return LocaList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public InventoryLocation getLocation(Integer cid) {
		log.debug("finding all Measurement instances");
		try {
			InventoryLocation location= inventoryLocationDao.getLocation(cid);
			return location;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<InventoryLocation> getLocations(Integer pid) {
		log.debug("finding all Measurement instances");
		try {
			List<InventoryLocation> LocaList= inventoryLocationDao.getLocations(pid);
			return LocaList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * 递归算法解析成树形结构
	 *
	 * @param cid
	 * @return
	 * @author jiqinlin
	 */
	public InventoryLocation getTree(Integer cid) {
		// 根据cid获取节点对象(SELECT * FROM tb_tree t WHERE t.cid=?)
		InventoryLocation node = inventoryLocationDao.getLocation(cid);
		// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
		List<InventoryLocation> childTreeNodes = inventoryLocationDao
				.getLocations(cid);
		// 遍历子节点
		if(childTreeNodes!=null && childTreeNodes.size()>0){
			for (InventoryLocation child : childTreeNodes) {
				InventoryLocation n = getTree(child.getCid()); // 递归
				node.getChildren().add(n);
			}
		}
		return node;
	}

	@Override
	public void delete(Integer pid) {
		
		inventoryLocationDao.delete(pid);
	}

	@Override
	public void update(InventoryLocation inventoryLocation) {
		inventoryLocationDao.update(inventoryLocation);
	}

	@Override
	public InventoryLocation getById(Integer id) {
		// TODO Auto-generated method stub
		return inventoryLocationDao.getById(id);
	
}

	@Override
	public void deleteByLocationId(Integer id) {
		inventoryLocationDao.deleteByLocationId(id);
	}

	@Override
	public List<InventoryLocation> getAllPageable(String keyword) {
		log.debug("finding all InventoryLocation instances");
		try {
			List<InventoryLocation> LocaList= inventoryLocationDao.getAllPageable(keyword);
			return LocaList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<InventoryLocation> getAllLocation(Integer id) {
		log.debug("finding all InventoryLocation instances");
		try {
			List<InventoryLocation> LocaList= inventoryLocationDao.getAllLocation(id);
			return LocaList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<InventoryLocation> getAllFirst() {
		log.debug("finding all Measurement instances");
		try {
			List<InventoryLocation> LocaList= inventoryLocationDao.getAllFirst();
			return LocaList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<String> getBarCodes() {
		log.debug("finding all Measurement instances");
		try {
			List<String> LocaList= inventoryLocationDao.getBarCodes();
			return LocaList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void updateQrName(InventoryLocation inventoryLocation) {
		log.debug("saving Measurement instance");
		try {
			inventoryLocationDao.updateQrName(inventoryLocation);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public InventoryLocation getByBarcode(String barcode) {
		log.debug("finding all Measurement instances");
		try {
			InventoryLocation location= inventoryLocationDao.getByBarcode(barcode);
			return location;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	}