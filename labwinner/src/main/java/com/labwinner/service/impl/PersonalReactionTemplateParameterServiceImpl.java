package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.PersonalReactionTemplateParameterDao;
import com.labwinner.dao.PersonalReactionTemplateProcessDao;
import com.labwinner.domain.PersonalReactionTemplateParameter;
import com.labwinner.domain.PersonalReactionTemplateProcess;
import com.labwinner.service.PersonalReactionTemplateParameterService;
import com.labwinner.service.PersonalReactionTemplateProcessService;

/**
 * 个人试验模板参数ServiceIml
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class PersonalReactionTemplateParameterServiceImpl implements PersonalReactionTemplateParameterService{
	private static final Logger log = LoggerFactory
			.getLogger(PersonalReactionTemplateParameterServiceImpl.class);
    
	@Autowired
	private PersonalReactionTemplateParameterDao personalReactionTemplateParameterDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(PersonalReactionTemplateParameter personalReactionTemplateParameter) {
		log.debug("saving PersonalReactionTemplateParameter instance");
		try {
			personalReactionTemplateParameterDao.save(personalReactionTemplateParameter);
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
		log.debug("deleting PersonalReactionTemplateParameter instance");
		try {
			personalReactionTemplateParameterDao.delete(id);
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
	public PersonalReactionTemplateParameter getById(Integer id) {
		log.debug("getting PersonalReactionTemplateProcess instance with id: " + id);
		try {
			PersonalReactionTemplateParameter instance = (PersonalReactionTemplateParameter) personalReactionTemplateParameterDao.getById(id);
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
	public List<PersonalReactionTemplateParameter> getAll() {
		log.debug("finding all PersonalReactionTemplateParameter instances");
		try {
			 return personalReactionTemplateParameterDao.getAll();
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
	public void update(PersonalReactionTemplateParameter personalReactionTemplateParameter) {
		log.debug("merging PersonalReactionTemplateParameter instance");
		try {
			personalReactionTemplateParameterDao.update(personalReactionTemplateParameter);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	
}