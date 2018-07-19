package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.service.ProjectNumberService;
import com.labwinner.dao.ProjectNumberDao;
import com.labwinner.domain.ProjectNumber;

/**
 * 项目成员Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class ProjectNumberServiceImpl implements ProjectNumberService{
	private static final Logger log = LoggerFactory
			.getLogger(ProjectNumberServiceImpl.class);
    
	@Autowired
	private  ProjectNumberDao projectNumberDao;
    
     
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(ProjectNumber projectNumber) {
		log.debug("saving ProjectNumber instance");
		try {
			projectNumberDao.save(projectNumber);
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
		log.debug("deleting ProjectNumber instance");
		try {
			projectNumberDao.delete(id);
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
	public ProjectNumber getById(Integer id) {
		log.debug("getting ProjectBasicInfo instance with id: " + id);
		try {
			ProjectNumber instance =projectNumberDao.getById(id);
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
	public List<ProjectNumber> getAll(Integer id) {
		log.debug("finding all ProjectNumber instances");
		try {
			 return projectNumberDao.getAll(id);
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
	public void update(ProjectNumber projectNumber) {
		log.debug("merging ProjectNumber instance");
		try {
			 projectNumberDao.update(projectNumber);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<ProjectNumber> getByProId(Integer id) {
		log.debug("merging ProjectNumber instance");
		try {
			return  projectNumberDao.getByProId(id);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<ProjectNumber> getPrincipals(Integer id) {
		log.debug("merging ProjectNumber instance");
		try {
			return  projectNumberDao.getPrincipals(id);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<ProjectNumber> getProjects(Integer userId) {
		log.debug("merging ProjectNumber instance");
		try {
			return  projectNumberDao.getProjects(userId);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<ProjectNumber> getAllProjects() {
		log.debug("merging ProjectNumber instance");
		try {
			return  projectNumberDao.getAllProjects();
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public void updateByDelete(Integer proNumberId, Integer flag,Integer roleId) {
		log.debug("merging ProjectNumber instance");
		try {
			  projectNumberDao.updateByDelete(proNumberId,flag,roleId);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		log.debug("merging ProjectNumber instance");
		try {
			  projectNumberDao.deleteById(id);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
		
	}


	@Override
	public ProjectNumber getProjectNumber(Integer roleId, Integer userId, Integer flag,Integer proId) {
		log.debug("merging ProjectNumber instance");
		try {
			return  projectNumberDao.getProjectNumber(roleId,userId,flag,proId);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<ProjectNumber> getAllNumberByProId(Integer projectId) {
		log.debug("merging ProjectNumber instance");
		try {
			return  projectNumberDao.getAllNumberByProId(projectId);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public ProjectNumber getByUser(int managerId, int proId) {
		log.debug("merging ProjectNumber instance");
		try {
			return  projectNumberDao.getByUser(managerId,proId);
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	
}