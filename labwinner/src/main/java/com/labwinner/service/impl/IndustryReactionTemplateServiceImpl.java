package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.IndustryReactionTemplateDao;
import com.labwinner.domain.Device;
import com.labwinner.domain.IndustryReactionTemplate;
import com.labwinner.service.IndustryReactionTemplateService;

/**
 * 行业试验模板ServiceIml
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class IndustryReactionTemplateServiceImpl implements IndustryReactionTemplateService{
	private static final Logger log = LoggerFactory
			.getLogger(IndustryReactionTemplateServiceImpl.class);
    
	@Autowired
	private IndustryReactionTemplateDao industryReactionTemplateDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(IndustryReactionTemplate industryReactionTemplate) {
		log.debug("saving IndustryReactionTemplate instance");
		try {
			industryReactionTemplateDao.save(industryReactionTemplate);
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
		log.debug("deleting IndustryReactionTemplate instance");
		try {
			industryReactionTemplateDao.delete(id);
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
	public IndustryReactionTemplate getById(Integer id) {
		log.debug("getting ProfessionProcess instance with id: " + id);
		try {
			IndustryReactionTemplate instance = (IndustryReactionTemplate) industryReactionTemplateDao.getById(id);
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
	public List<IndustryReactionTemplate> getAll() {
		log.debug("finding all IndustryReactionTemplate instances");
		try {
			 return industryReactionTemplateDao.getAll();
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
	public void update(IndustryReactionTemplate industryReactionTemplate) {
		log.debug("merging industryReactionTemplate instance");
		try {
			industryReactionTemplateDao.update(industryReactionTemplate);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<IndustryReactionTemplate> getAllPageable(String keyword) {
		log.debug("finding all IndustryReactionTemplate instances");
		try {
			 return industryReactionTemplateDao.getAllPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
}