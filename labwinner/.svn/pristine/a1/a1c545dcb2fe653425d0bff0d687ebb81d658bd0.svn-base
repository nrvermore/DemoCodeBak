package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.service.ProjectPlanService;
import com.labwinner.dao.ProjectPlanDao;
import com.labwinner.domain.ProjectPlan;

/**
 * 项目进度计划Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class ProjectPlanServiceImpl implements ProjectPlanService{
	private static final Logger log = LoggerFactory
			.getLogger(ProjectPlanServiceImpl.class);
    
	@Autowired
	private  ProjectPlanDao projectPlanDao;
    
     
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(ProjectPlan projectPlan) {
		log.debug("saving ProjectPlan instance");
		try {
			projectPlanDao.save(projectPlan);
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
		log.debug("deleting ProjectPlan instance");
		try {
			projectPlanDao.delete(id);
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
	public ProjectPlan getById(Integer id) {
		log.debug("getting ProjectPlan instance with id: " + id);
		try {
			ProjectPlan instance = (ProjectPlan) projectPlanDao.getById(id);
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
	public List<ProjectPlan> getAll() {
		log.debug("finding all ProjectPlan instances");
		try {
			 return projectPlanDao.getAll();
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
	public void update(ProjectPlan projectPlan) {
		log.debug("merging ProjectPlan instance");
		try {
			 projectPlanDao.update(projectPlan);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<ProjectPlan> getByProId(int proId) {
		log.debug("getting ProjectPlan instance with proId: " + proId);
		try {
			List<ProjectPlan> instance =  projectPlanDao.getByProId(proId);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	@Override
	public void deleteByPlanId(Integer id) {
		try {
			projectPlanDao.deleteByPlanId(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}


	@Override
	public ProjectPlan getProjectPlanTimeByProId(Integer id) {
		try {
			ProjectPlan projectPlan=projectPlanDao.getProjectPlanTimeByProId(id);
			return projectPlan;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}