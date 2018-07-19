package com.labwinner.service.impl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ShareProjectDao;
import com.labwinner.domain.ShareProject;
import com.labwinner.service.ShareProjectService;

/**
 * 共享项目Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class ShareProjectServiceImpl implements ShareProjectService{
	private static final Logger log = LoggerFactory
			.getLogger(ShareProjectServiceImpl.class);
    
	@Autowired
	private ShareProjectDao shareProjectDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(ShareProject shareProject) {
		log.debug("saving ShareProject instance");
		try {
			shareProjectDao.save(shareProject);
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
		log.debug("deleting ShareProject instance");
		try {
			shareProjectDao.delete(id);
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
	public ShareProject getById(Integer id) {
		log.debug("getting ShareProject instance with id: " + id);
		try {
			ShareProject instance = (ShareProject) shareProjectDao.getById(id);
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
	public List<ShareProject> getAll() {
		log.debug("finding all ShareProject instances");
		try {
			 return shareProjectDao.getAll();
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
	public void update(ShareProject shareProject) {
		log.debug("merging ShareProject instance");
		try {
			 shareProjectDao.update(shareProject);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	

	
}