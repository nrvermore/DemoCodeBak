package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.service.ProjectDocumentsService;
import com.labwinner.dao.ProjectDocumentsDao;
import com.labwinner.domain.ProjectDocuments;

/**
 * 项目文档Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class ProjectDocumentsServiceImpl implements ProjectDocumentsService{
	private static final Logger log = LoggerFactory
			.getLogger(ProjectDocumentsServiceImpl.class);
    
	@Autowired
	private  ProjectDocumentsDao projectDocumentsDao;
    
     
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(ProjectDocuments projectDocuments) {
		log.debug("saving ProjectBasicInfo instance");
		try {
			projectDocumentsDao.save(projectDocuments);
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
		log.debug("deleting ProjectDocuments instance");
		try {
			projectDocumentsDao.delete(id);
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
	public List<String> getByProId(Integer proId) {
		log.debug("getting ProjectBasicInfo instance with id: " + proId);
		try {
			List<String> instance =  projectDocumentsDao.getByProId(proId);
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
	public List<ProjectDocuments> getAll() {
		log.debug("finding all ProjectDocuments instances");
		try {
			 return projectDocumentsDao.getAll();
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
	public void update(ProjectDocuments projectDocuments) {
		log.debug("merging ProjectBasicInfo instance");
		try {
			 projectDocumentsDao.update(projectDocuments);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<ProjectDocuments> getAllConversion() {
		log.debug("finding all ProjectDocuments instances");
		try {
			 return projectDocumentsDao.getAllConversion();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public void updatePdfCount(Integer proDocId) {
		log.debug("merging ProjectBasicInfo instance");
		try {
			 projectDocumentsDao.updatePdfCount(proDocId);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public ProjectDocuments getById(Integer proDocId) {
		log.debug("finding all ProjectDocuments instances");
		try {
			 return projectDocumentsDao.getById(proDocId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}