package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ProjectPictureDao;
import com.labwinner.domain.ProjectPicture;
import com.labwinner.service.ProjectPictureService;

/**
 * 项目图片Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class ProjectPictureServiceImpl implements ProjectPictureService{
	private static final Logger log = LoggerFactory
			.getLogger(ProjectPictureServiceImpl.class);
    
	@Autowired
	private ProjectPictureDao projectPictureDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(ProjectPicture projectPicture) {
		log.debug("saving ProjectPicture instance");
		try {
			projectPictureDao.save(projectPicture);
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
	public void delete(Integer id,String fileName) {
		log.debug("deleting ProjectPicture instance");
		try {
			projectPictureDao.delete(id, fileName);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
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
	public void deleteByPro(Integer id) {
		log.debug("deleting ProjectPicture instance");
		try {
			projectPictureDao.deleteByPro(id);
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
	public List<String> getByProId(Integer id) {
		log.debug("getting ProjectPicture instance with id: " + id);
		try {
			List<String> instance = projectPictureDao.getByProId(id);
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
	public List<ProjectPicture> getAll() {
		log.debug("finding all ProjectPicture instances");
		try {
			 return projectPictureDao.getAll();
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
	public void update(ProjectPicture projectPicture) {
		log.debug("merging ProjectPicture instance");
		try {
			 projectPictureDao.update(projectPicture);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	
}