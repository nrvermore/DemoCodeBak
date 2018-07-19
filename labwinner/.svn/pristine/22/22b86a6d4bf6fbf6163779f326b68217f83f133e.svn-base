package com.labwinner.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.labwinner.dao.DesignTechnologyProcessDao;
import com.labwinner.domain.DesignTechnologyProcess;
import com.labwinner.service.DesignTechnologyProcessService;


/**
 * 设计工艺步骤Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class DesignTechnologyProcessServiceImpl implements DesignTechnologyProcessService{
	private static final Logger log = LoggerFactory
			.getLogger(DesignTechnologyProcessServiceImpl.class);
    
	@Autowired
	private DesignTechnologyProcessDao designTechnologyProcessDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(DesignTechnologyProcess designTechnologyProcess) {
		log.debug("saving DesignTechnologyProcess instance");
		try {
			designTechnologyProcessDao.save(designTechnologyProcess);
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
		log.debug("deleting DesignTechnologyProcess instance");
		try {
			designTechnologyProcessDao.delete(id);
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
	public DesignTechnologyProcess getById(Integer id) {
		log.debug("getting DesignTechnologyProcess instance with id: " + id);
		try {
			DesignTechnologyProcess instance = designTechnologyProcessDao.getById(id);
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
	public List<DesignTechnologyProcess> getAll() {
		log.debug("finding all DesignTechnologyProcess instances");
		try {
			 return designTechnologyProcessDao.getAll();
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
	public void update(DesignTechnologyProcess designTechnologyProcess) {
		log.debug("merging DesignTechnologyProcess instance");
		try {
			 designTechnologyProcessDao.update(designTechnologyProcess);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<DesignTechnologyProcess> getAllPageable(String keyword) {
		log.debug("finding all DesignTechnologyProcess instances");
		try {
			 return designTechnologyProcessDao.getAllPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}