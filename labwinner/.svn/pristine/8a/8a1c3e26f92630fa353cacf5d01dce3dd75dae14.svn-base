package com.labwinner.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SignInDao;
import com.labwinner.domain.SignIn;
import com.labwinner.service.SignInService;

/**
 * 设备Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class SignInServiceImpl implements SignInService{
	private static final Logger log = LoggerFactory
			.getLogger(SignInServiceImpl.class);
    
	@Autowired
	private SignInDao signInDao;
    
    
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(SignIn signIn) {
		log.debug("saving SignIn instance");
		try {
			signInDao.save(signIn);
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
		log.debug("deleting SignIn instance");
		try {
			signInDao.delete(id);
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
	public SignIn getById(Integer userId) {
		log.debug("getting SignIn instance with id: " + userId);
		try {
			SignIn instance = (SignIn) signInDao.getById(userId);
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
	public List<SignIn> getAll(Integer userId) {
		log.debug("finding all SignIn instances");
		try {
			 return signInDao.getAll(userId);
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
	public void update(SignIn signIn) {
		log.debug("merging SignIn instance");
		try {
			 signInDao.update(signIn);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<SignIn> getSignForFirt(String roleName, Integer userId) {
		log.debug("finding all SignIn instances");
		try {
			 return signInDao.getSignForFirt(roleName,userId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public Integer getSignCount(Integer userId) {
		log.debug("finding all SignIn instances");
		try {
			 return signInDao.getSignCount(userId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<SignIn> getWeekList(Integer userId) {
		log.debug("finding all SignIn instances");
		try {
			 return signInDao.getWeekList(userId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public List<SignIn> getDayList() {
		log.debug("finding all SignIn instances");
		try {
			 return signInDao.getDayList();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
		@Override
	public List<SignIn> getAllByDay(String date) {
		log.debug("finding all SignIn instances");
		try {
			 return signInDao.getAllByDay(date);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


		@Override
		public List<SignIn> getGroupDayList() {
			log.debug("finding all SignIn instances");
			try {
				 return signInDao.getGroupDayList();
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}


		@Override
		public List<SignIn> getExcelList(String startDate, String endDate) {
			log.debug("finding all SignIn instances");
			try {
				 return signInDao.getExcelList(startDate,endDate);
			} catch (RuntimeException re) {
				log.error("find all failed", re);
				throw re;
			}
		}

	
}