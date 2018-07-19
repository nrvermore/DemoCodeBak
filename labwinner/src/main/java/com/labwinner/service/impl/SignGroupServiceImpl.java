package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SignGroupDao;
import com.labwinner.domain.SignGroup;
import com.labwinner.service.SignGroupService;


@Service
public class SignGroupServiceImpl implements SignGroupService {

	private static final Logger log = LoggerFactory
			.getLogger(ExpertServiceImpl.class);
	@Autowired
	private SignGroupDao signGroupDao;
	@Override
	public void save(SignGroup signGroup) {
		log.debug("saving Expert instance");
		try {
			signGroupDao.save(signGroup);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	@Override
	public void deleteAll() {
		log.debug("saving Expert instance");
		try {
			signGroupDao.deleteAll();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	@Override
	public List<SignGroup> getAll() {
		try {
			return signGroupDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	@Override
	public SignGroup getByUserId(Integer userId) {
		try {
			return signGroupDao.getByUserId(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	@Override
	public List<SignGroup> getAllGroup() {
		try {
			return signGroupDao.getAllGroup();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
