package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SupplierDao;
import com.labwinner.domain.CompanyType;
import com.labwinner.domain.Supplier;
import com.labwinner.service.SupplierService;
/**
 * A data access object (DAO) providing persistence and search support for
 * Supplier entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.labwinner.hibernate.Supplier
 * @author MyEclipse Persistence Tools
 */
@Service
public class SupplierServiceImpl implements SupplierService{
	
	private static final Logger log = LoggerFactory
			.getLogger(SupplierServiceImpl.class);
	
	@Autowired
	private SupplierDao supplierDao;
	
	public void save(Supplier supplier) {
		log.debug("saving Inventory instance");
		try {
			supplierDao.save(supplier);
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Integer id) {
		log.debug("deleting Inventory instance");
		try {
			supplierDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void update(Supplier supplier){
		log.debug("update Inventory instance");
		try {
			supplierDao.update(supplier);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public List<Supplier> getAll(){
		log.debug("update Inventory instance");
		try {
			log.debug("getAll successful");
			return supplierDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public Supplier getById(Integer id){
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			return supplierDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Supplier> getByName(String keyword) {
		log.debug("update Inventory instance");
		try {
			log.debug("getAll successful");
			return supplierDao.getByName(keyword);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<CompanyType> getCompanyType() {
		log.debug("update Inventory instance");
		try {
			log.debug("getAll successful");
			return supplierDao.getCompanyType();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	
	
}