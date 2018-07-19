package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ChemicalImageDao;
import com.labwinner.domain.ChemicalImage;
import com.labwinner.service.ChemicalImageService;

/**
 * A data access object (DAO) providing persistence and search support for
 * ChemicalImage entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.labwinner.hibernate.ChemicalImage
 * @author MyEclipse Persistence Tools
 */
@Service
public class ChemicalImageServiceImpl implements ChemicalImageService{
	private static final Logger log = LoggerFactory
			.getLogger(ChemicalImageServiceImpl.class);
	@Autowired
	private ChemicalImageDao chemicalImageDao;

	public void save(ChemicalImage chemicalImage) {
		log.debug("saving ChemicalImage instance");
		try {
			chemicalImageDao.save(chemicalImage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public List<ChemicalImage> getAll(){
		log.debug("update Inventory instance");
		try {
			log.debug("get successful");
			return chemicalImageDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public List<String> getByInventoryId(Integer id){
		log.debug("update Inventory instance");
		try {
			log.debug("get successful");
			return chemicalImageDao.getByInventoryId(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void deleteById(Integer id) {
		log.debug("update Inventory instance");
		try {
			log.debug("get successful");
			chemicalImageDao.deleteById(id);;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void deleteByUrl(String imageName,Integer id) {
		log.debug("update Inventory instance");
		try {
			log.debug("get successful");
			chemicalImageDao.deleteByUrl(imageName,id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void batchRemove(List<Integer> ids) {
		log.debug("update Inventory instance");
		try {
			log.debug("get successful");
			chemicalImageDao.batchRemove(ids);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public List<ChemicalImage> getByGroupId(Integer id) {
		log.debug("update chemicalImage instance");
		try {
			log.debug("get successful");
			return chemicalImageDao.getByGroupId(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
}