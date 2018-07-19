package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.TestDeviceDao;
import com.labwinner.domain.TestDevice;
import com.labwinner.service.TestDeviceService;

@Service
public class TestDeviceServiceImpl implements TestDeviceService{

	private static final Logger log = LoggerFactory
			.getLogger(TestDeviceServiceImpl.class);
	@Autowired
	private TestDeviceDao testDeviceDao;
	@Override
	public void save(TestDevice testDevice) {
		log.debug("saving testDevice instance");
		try {
			testDeviceDao.save(testDevice);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(TestDevice testDevice) {
		log.debug("saving testDevice instance");
		try {
			testDeviceDao.update(testDevice);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving testDevice instance");
		try {
			testDeviceDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<TestDevice> getById(Integer id) {
		log.debug("saving testDevice instance");
		try {
			return testDeviceDao.getByTestId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
