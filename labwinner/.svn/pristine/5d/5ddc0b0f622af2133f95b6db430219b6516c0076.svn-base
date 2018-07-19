package com.labwinner.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.IndustryReactionTemplateParameterDao;
import com.labwinner.dao.ProfessionProcessDao;
import com.labwinner.domain.IndustryReactionTemplateParameter;
import com.labwinner.domain.ProfessionProcess;
import com.labwinner.service.ProfessionProcessService;

/**
 * 行业试验模板步骤ServiceIml
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class ProfessionProcessServiceImpl implements ProfessionProcessService{
	private static final Logger log = LoggerFactory
			.getLogger(ProfessionProcessServiceImpl.class);
    
	@Autowired
	private ProfessionProcessDao professionProcessDao;
    
	@Autowired
	private IndustryReactionTemplateParameterDao industryReactionTemplateParameterDao;
	
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(ProfessionProcess professionProcess) {
		log.debug("saving ProfessionProcess instance");
		try {
			professionProcessDao.save(professionProcess);
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
		log.debug("deleting ProfessionProcess instance");
		try {
			ProfessionProcess professionProcess=professionProcessDao.getById(id);
			List<IndustryReactionTemplateParameter> industryReactionTemplateParameters=new ArrayList<IndustryReactionTemplateParameter>(professionProcess.getIndustryReactionTemplateParameters());
			for (IndustryReactionTemplateParameter industryReactionTemplateParameter : industryReactionTemplateParameters) {
				industryReactionTemplateParameterDao.delete(id);
			}
			professionProcessDao.delete(id);
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
	public ProfessionProcess getById(Integer id) {
		log.debug("getting ProfessionProcess instance with id: " + id);
		try {
			ProfessionProcess instance = (ProfessionProcess) professionProcessDao.getById(id);
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
	public List<ProfessionProcess> getAll() {
		log.debug("finding all ProfessionProcess instances");
		try {
			 return professionProcessDao.getAll();
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
	public void update(ProfessionProcess professionProcess) {
		log.debug("merging ProfessionProcess instance");
		try {
			professionProcessDao.update(professionProcess);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	
}