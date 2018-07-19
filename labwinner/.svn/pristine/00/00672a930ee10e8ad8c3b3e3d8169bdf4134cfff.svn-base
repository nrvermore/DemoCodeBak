package com.labwinner.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.PersonalTemplateDao;
import com.labwinner.domain.PersonalTemplate;
import com.labwinner.service.PersonalTemplateService;

/**
 * 个人模板ServiceIml
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class PersonalTemplateServiceImpl implements PersonalTemplateService{
	private static final Logger log = LoggerFactory
			.getLogger(PersonalTemplateServiceImpl.class);
    
	@Autowired
	private PersonalTemplateDao personalTemplateDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(PersonalTemplate personalTemplate) {
		log.debug("saving personalTemplate instance");
		try {
			personalTemplateDao.save(personalTemplate);
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
		log.debug("deleting PersonalTemplate instance");
		try {
			personalTemplateDao.delete(id);
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
	public PersonalTemplate getById(Integer id) {
		log.debug("getting personalTemplate instance with id: " + id);
		try {
			PersonalTemplate instance = (PersonalTemplate) personalTemplateDao.getById(id);
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
	public List<PersonalTemplate> getAll(Integer userId) {
		log.debug("finding all PersonalTemplate instances");
		try {
			 return personalTemplateDao.getAll(userId);
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
	public void update(PersonalTemplate personalTemplate) {
		log.debug("merging Device instance");
		try {
			personalTemplateDao.update(personalTemplate);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<PersonalTemplate> getAllPageable(Integer userId, String keyword) {
		log.debug("finding all PersonalTemplate instances");
		try {
			 return personalTemplateDao.getAllPageable(userId, keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<PersonalTemplate> getMyAll(Integer userId) {
		log.debug("finding all PersonalTemplate instances");
		try {
			 return personalTemplateDao.getMyAll(userId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<PersonalTemplate> getAllTemplateName(Integer userId) {
		log.debug("finding all PersonalTemplate instances");
		try {
			 return personalTemplateDao.getAllTemplateName(userId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
}