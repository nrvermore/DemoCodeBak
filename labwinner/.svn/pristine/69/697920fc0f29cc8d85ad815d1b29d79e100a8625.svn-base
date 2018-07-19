package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.IndustryReactionTemplateParameterDao;
import com.labwinner.domain.IndustryReactionTemplateParameter;
import com.labwinner.service.IndustryReactionTemplateParameterService;;

/**
 * 行业试验模板参数ServiceIml
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class IndustryReactionTemplateParameterServiceImpl implements IndustryReactionTemplateParameterService{
	private static final Logger log = LoggerFactory
			.getLogger(IndustryReactionTemplateParameterServiceImpl.class);
    
	@Autowired
	private IndustryReactionTemplateParameterDao industryReactionTemplateParameterDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(IndustryReactionTemplateParameter industryReactionTemplateParameter) {
		log.debug("saving industryReactionTemplateParameterDao instance");
		try {
			industryReactionTemplateParameterDao.save(industryReactionTemplateParameter);
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
		log.debug("deleting industryReactionTemplateParameterDao instance");
		try {
			industryReactionTemplateParameterDao.delete(id);
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
	public IndustryReactionTemplateParameter getById(Integer id) {
		log.debug("getting industryReactionTemplateParameter instance with id: " + id);
		try {
			IndustryReactionTemplateParameter instance = (IndustryReactionTemplateParameter) industryReactionTemplateParameterDao.getById(id);
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
	public List<IndustryReactionTemplateParameter> getAll() {
		log.debug("finding all Device instances");
		try {
			 return industryReactionTemplateParameterDao.getAll();
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
	public void update(IndustryReactionTemplateParameter industryReactionTemplateParameter) {
		log.debug("merging Device instance");
		try {
			industryReactionTemplateParameterDao.update(industryReactionTemplateParameter);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	
}