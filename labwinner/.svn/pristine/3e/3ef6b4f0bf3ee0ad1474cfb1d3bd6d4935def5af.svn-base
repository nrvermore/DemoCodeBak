package com.labwinner.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MaterialPurchaseDao;
import com.labwinner.domain.InventoryGroup;
import com.labwinner.domain.MaterialPurchase;
import com.labwinner.domain.ProductSummary;
import com.labwinner.service.MaterialPurchaseService;

@Service
public class MaterialPurchaseServiceImpl implements MaterialPurchaseService {

	private static final Logger log = LoggerFactory
			.getLogger(MaterialPurchaseServiceImpl.class);
	
	@Autowired
	private MaterialPurchaseDao materialPurchaseDao;
	@Override
	public void save(MaterialPurchase materialPurchase) {
		log.debug("update MaterialPurchase instance");
		try {
			materialPurchaseDao.save(materialPurchase);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public void update(MaterialPurchase materialPurchase) {
		log.debug("update MaterialPurchase instance");
		try {
			materialPurchaseDao.update(materialPurchase);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete MaterialPurchase instance");
		try {
			materialPurchaseDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public MaterialPurchase getById(Integer id) {
		log.debug("getAll MaterialPurchase instance");
		MaterialPurchase res=new MaterialPurchase();
		try {
			res=materialPurchaseDao.getById(id);
			log.debug("find successful");
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
		return res;
	}

	@Override
	public List<MaterialPurchase> getByName(Integer userId,Integer id,String roleName) {
		log.debug("getAll MaterialPurchase instance");
		List<MaterialPurchase> res=new ArrayList<MaterialPurchase>();
		try {
			res=materialPurchaseDao.getByName(userId,id,roleName);
			log.debug("find successful");
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
		return res;
	}

	@Override
	public List<MaterialPurchase> getAll(Integer userId, String roleName) {
		log.debug("getAll MaterialPurchase instance");
		List<MaterialPurchase> res=new ArrayList<MaterialPurchase>();
		try {
			res=materialPurchaseDao.getAll(userId,roleName);
			log.debug("find successful");
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
		return res;
	}

	@Override
	public List<Map<String, Object>> getHotPurchase(Integer userId) {
		log.debug("getHotPurchase MaterialPurchase instance");
		List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
		try {
			res=materialPurchaseDao.getHotPurchase(userId);
			log.debug("find successful");
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
		return res;
	}

	@Override
	public List<String> getChemical(String keyword) {
		log.debug("getHotPurchase MaterialPurchase instance");
		List<String> res=new ArrayList<String>();
		try {
			res=materialPurchaseDao.getChemical(keyword);
			log.debug("find successful");
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
		return res;
	}

	@Override
	public List<MaterialPurchase> getMaterialList(Integer productSummaryId,
			Integer supId, String roleName,Integer userId) {
		List<MaterialPurchase> res=new ArrayList<MaterialPurchase>();
		try {
			res=materialPurchaseDao.getMaterialList(productSummaryId,supId,roleName,userId);
			log.debug("find successful");
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
		return res;
	}

	@Override
	public List<MaterialPurchase> getBySupId(Integer id) {
		List<MaterialPurchase> res=new ArrayList<MaterialPurchase>();
		try {
			res=materialPurchaseDao.getBySupId(id);
			log.debug("find successful");
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
		return res;
	}

	@Override
	public List<MaterialPurchase> getByProductSummaryId(Integer id) {
		List<MaterialPurchase> res=new ArrayList<MaterialPurchase>();
		try {
			res=materialPurchaseDao.getByProductSummaryId(id);
			log.debug("find successful");
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
		return res;
	}

	@Override
	public List<MaterialPurchase> getOrders(Integer supId, Integer productId) {
		try {
			return materialPurchaseDao.getOrders(supId,productId);
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}

	@Override
	public void updateOrders(Integer orderId, Integer materialId) {
		log.debug("update MaterialPurchase instance");
		try {
			materialPurchaseDao.updateOrders(orderId,materialId);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	@Override
	public List<Integer> getByProductId(Integer id) {
		// TODO Auto-generated method stub
		List<Integer> res=new ArrayList<Integer>();
		try {
			res=materialPurchaseDao.getByProductId(id);
			log.debug("find successful");
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
		return res;
	}




	

}
