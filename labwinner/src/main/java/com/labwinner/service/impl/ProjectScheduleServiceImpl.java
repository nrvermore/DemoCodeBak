package com.labwinner.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.labwinner.service.ProjectScheduleService;
import com.labwinner.dao.ProjectScheduleDao;
import com.labwinner.domain.ProjectSchedule;

/**
 * 项目进度状态Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class ProjectScheduleServiceImpl implements ProjectScheduleService{
	private static final Logger log = LoggerFactory
			.getLogger(ProjectScheduleServiceImpl.class);
    
	@Autowired
	private  ProjectScheduleDao projectScheduleDao;
    
     
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(ProjectSchedule projectSchedule) {
		log.debug("saving ProjectSchedule instance");
		try {
			projectScheduleDao.save(projectSchedule);
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
		log.debug("deleting ProjectSchedule instance");
		try {
			projectScheduleDao.delete(id);
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
	public ProjectSchedule getById(Integer id) {
		log.debug("getting ProjectSchedule instance with id: " + id);
		try {
			ProjectSchedule instance = (ProjectSchedule) projectScheduleDao.getById(id);
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
	public List<ProjectSchedule> getAll() {
		log.debug("finding all ProjectSchedule instances");
		try {
			 return projectScheduleDao.getAll();
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
	public void update(ProjectSchedule projectSchedule) {
		log.debug("merging ProjectSchedule instance");
		try {
			 projectScheduleDao.update(projectSchedule);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

}