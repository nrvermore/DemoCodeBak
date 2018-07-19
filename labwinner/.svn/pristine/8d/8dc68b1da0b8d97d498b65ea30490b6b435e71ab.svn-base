package com.labwinner.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DeviceDao;
import com.labwinner.dao.FeedbackDao;
import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.Feedback;
import com.labwinner.service.DeviceService;
import com.labwinner.service.FeedbackService;

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
public class FeedbackServiceImpl implements FeedbackService{
	private static final Logger log = LoggerFactory
			.getLogger(FeedbackServiceImpl.class);
    
	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public void save(Feedback feedback) {
		try {
			feedbackDao.save(feedback);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void update(Feedback feedback) {
		try {
			feedbackDao.update(feedback);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		try {
			feedbackDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Feedback getById(Integer id) {
		log.debug("finding all DeviceAppointment instances");
		try {
		  return feedbackDao.getById(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Feedback> getAll() {
		log.debug("finding all DeviceAppointment instances");
		try {
		  return feedbackDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Feedback> getAllPageable() {
		log.debug("finding all DeviceAppointment instances");
		try {
		  return feedbackDao.getAllPageable();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
  
	
}