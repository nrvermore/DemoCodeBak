package com.labwinner.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.labwinner.service.ProjectRoleService;
import com.labwinner.dao.ProjectRoleDao;
import com.labwinner.domain.ProjectRole;

/**
 * 项目角色Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class ProjectRoleServiceImpl implements ProjectRoleService{
	private static final Logger log = LoggerFactory
			.getLogger(ProjectRoleServiceImpl.class);
    
	@Autowired
	private  ProjectRoleDao projectRoleDao;
    
     
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(ProjectRole projectRole) {
		log.debug("saving ProjectRole instance");
		try {
			projectRoleDao.save(projectRole);
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
		log.debug("deleting ProjectRole instance");
		try {
			projectRoleDao.delete(id);
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
	public ProjectRole getById(Integer id) {
		log.debug("getting ProjectRole instance with id: " + id);
		try {
			ProjectRole instance = (ProjectRole) projectRoleDao.getById(id);
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
	public List<ProjectRole> getAll() {
		log.debug("finding all ProjectRole instances");
		try {
			 return projectRoleDao.getAll();
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
	public void update(ProjectRole projectRole) {
		log.debug("merging ProjectRole instance");
		try {
			 projectRoleDao.update(projectRole);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

}