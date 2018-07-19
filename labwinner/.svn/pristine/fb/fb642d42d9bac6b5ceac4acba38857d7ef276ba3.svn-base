package com.labwinner.service.impl;

import com.labwinner.dao.CityDao;
import com.labwinner.dao.SmsDao;
import com.labwinner.domain.City;
import com.labwinner.domain.Sms;
import com.labwinner.service.CityService;
import com.labwinner.service.SmsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class SmsServiceImpl implements SmsService {
	
	private static final Logger log = LoggerFactory
			.getLogger(SmsServiceImpl.class);
	
	 @Autowired
	 private SmsDao smsDao;

	@Override
	public Sms findVerByName(String phone) {
		log.debug("finding all DeviceState instances");
		try {
			return smsDao.findVerByName(phone);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void save(Sms sms) {
		log.debug("saving DeviceState instance");
		try {
			smsDao.save(sms);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Sms getSms(String phone, String verCode) {
		log.debug("finding all DeviceState instances");
		try {
			return smsDao.getSms(phone,verCode);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

   

}
