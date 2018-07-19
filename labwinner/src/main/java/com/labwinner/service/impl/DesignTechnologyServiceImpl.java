package com.labwinner.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DesignTechnologyDao;
import com.labwinner.domain.DesignTechnology;
import com.labwinner.service.DesignTechnologyService;


/**
 * 设计工艺Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class DesignTechnologyServiceImpl implements DesignTechnologyService{
	private static final Logger log = LoggerFactory
			.getLogger(DesignTechnologyServiceImpl.class);
    
	@Autowired
	private DesignTechnologyDao designTechnologyDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(DesignTechnology designTechnology) {
		log.debug("saving DesignTechnology instance");
		try {
			designTechnologyDao.save(designTechnology);
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
		log.debug("deleting DesignTechnology instance");
		try {
			designTechnologyDao.delete(id);
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
	public DesignTechnology getById(Integer id) {
		log.debug("getting DesignTechnology instance with id: " + id);
		try {
			DesignTechnology instance = designTechnologyDao.getById(id);
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
	public List<DesignTechnology> getAll() {
		log.debug("finding all DesignTechnology instances");
		try {
			 return designTechnologyDao.getAll();
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
	public void update(DesignTechnology designTechnology) {
		log.debug("merging DesignTechnology instance");
		try {
			 designTechnologyDao.update(designTechnology);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<DesignTechnology> getAllPageable(String keyword) {
		log.debug("finding all DesignTechnology instances");
		try {
			 return designTechnologyDao.getAllPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<DesignTechnology> getTechnologyByreactionDesignId(Integer id) {
		log.debug("finding all DesignTechnology instances");
		try {
			 return designTechnologyDao.getTechnologyByreactionDesignId(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}