package com.labwinner.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ProductSummaryDao;
import com.labwinner.domain.JournalArticle;
import com.labwinner.domain.ProductSummary;
import com.labwinner.domain.ProductType;
import com.labwinner.service.ProductSummaryService;

/**
 * @Description 采购产品Service实现
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class ProductSummaryServiceImpl implements ProductSummaryService {
	private static final Logger log = LoggerFactory
			.getLogger(ProductSummaryServiceImpl.class);

	@Autowired
	private ProductSummaryDao productSummaryDao;

	/**
	 * {@inheritDoc}
	 */
	public List<ProductSummary> getAll() {
		log.debug("finding all ProductSummary instances");
		try {
			return productSummaryDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ProductSummary getById(Integer id) {
		log.debug("getting ProductSummary instance with id: " + id);
		try {

			ProductSummary productSummary = (ProductSummary) productSummaryDao
					.getById(id);
			return productSummary;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(ProductSummary productSummary) {
		log.debug("saving ProductSummary instance");
		try {
			log.debug("save successful");
			productSummaryDao.save(productSummary);
			return productSummary.getProductSummaryId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(ProductSummary productSummary) {
		log.debug("saving ProductSummary instance");
		try {
			productSummaryDao.update(productSummary);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Integer id) {
		log.debug("deleting ProductSummary instance");
		try {
			productSummaryDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<ProductSummary> getByName(String keyword) {
		log.debug("finding all ProductSummary instances");
		try {
			return productSummaryDao.getByName(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getChemical(String keyword) {
		List<Map<String, Object>> res =new ArrayList<Map<String,Object>>();
		log.debug("finding all ProductSummary instances");
		try {
			res= productSummaryDao.getChemical(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		return res;
	}

	@Override
	public List<Map<String, Object>> getSupplier(String keyword) {
		List<Map<String, Object>> res =new ArrayList<Map<String,Object>>();
		log.debug("finding all ProductSummary instances");
		try {
			res= productSummaryDao.getSupplier(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		return res;
	}

	@Override
	public List<ProductSummary> getAllBySupId(Integer id) {
		log.debug("finding all ProductSummary instances");
		try {
			return productSummaryDao.getAllBySupId(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<ProductSummary> getByNameBySupId(Integer id, String keyword) {
		log.debug("finding all ProductSummary instances");
		try {
			return productSummaryDao.getByNameBySupId(id,keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<ProductSummary> getByChParId(Integer chParId, int supId) {
		List<ProductSummary> res =new ArrayList<ProductSummary>();
		log.debug("finding all ProductSummary instances");
		try {
			res= productSummaryDao.getByChParId(chParId,supId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		return res;
	}

	@Override
	public List<ProductSummary> getBySupId(Integer id) {
		try {
			List<ProductSummary> productSummary =  productSummaryDao.getBySupId(id);
			return productSummary;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<ProductSummary> findByChemical(Integer id) {
		try {
			List<ProductSummary> productSummary =  productSummaryDao.findByChemical(id);
			return productSummary;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<ProductSummary> findByKeyword(String keyword) {
		try {
			List<ProductSummary> productSummary =  productSummaryDao.findByKeyword(keyword);
			return productSummary;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<ProductType> getProductType() {
		try {
			List<ProductType> ProductType =  productSummaryDao.getProductType();
			return ProductType;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<ProductSummary> findByChemicalName(String keyword) {
		try {
			List<ProductSummary> productSummary =  productSummaryDao.findByChemicalName(keyword);
			return productSummary;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<ProductSummary> getAllProduct() {
		log.debug("finding all ProductSummary instances");
		try {
			return productSummaryDao.getAllProduct();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}