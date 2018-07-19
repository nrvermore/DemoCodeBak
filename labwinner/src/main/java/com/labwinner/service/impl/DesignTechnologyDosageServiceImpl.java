package com.labwinner.service.impl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.labwinner.dao.DesignTechnologyDosageDao;
import com.labwinner.domain.DesignTechnologyDosage;
import com.labwinner.service.DesignTechnologyDosageService;

/**
 * 设备Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class DesignTechnologyDosageServiceImpl implements DesignTechnologyDosageService{
	private static final Logger log = LoggerFactory
			.getLogger(DesignTechnologyDosageServiceImpl.class);
    
	@Autowired
	private DesignTechnologyDosageDao designTechnologyDosageDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(DesignTechnologyDosage designTechnologyDosage) {
		log.debug("saving DesignTechnologyDosage instance");
		try {
			designTechnologyDosageDao.save(designTechnologyDosage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
     
	
	/**
	 * 删除对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:00:30
	 */
	public void delete(Integer id) {
		log.debug("deleting DesignTechnologyDosage instance");
		try {
			designTechnologyDosageDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
     
    
	/**
	 * 根据id号查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:00:58
	 */
	public DesignTechnologyDosage getById(Integer id) {
		log.debug("getting DesignTechnologyDosage instance with id: " + id);
		try {
			DesignTechnologyDosage instance = designTechnologyDosageDao.getById(id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * 显示所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:02:18
	 */
	public List<DesignTechnologyDosage> getAll() {
		log.debug("finding all DesignTechnologyDosage instances");
		try {
			 return designTechnologyDosageDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * 修改对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:02:43
	 */
	public void update(DesignTechnologyDosage designTechnologyDosage) {
		log.debug("merging DesignTechnologyDosage instance");
		try {
			 designTechnologyDosageDao.update(designTechnologyDosage);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<DesignTechnologyDosage> getAllPageable(String keyword) {
		log.debug("finding all DesignTechnologyDosage instances");
		try {
			 return designTechnologyDosageDao.getAllPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}