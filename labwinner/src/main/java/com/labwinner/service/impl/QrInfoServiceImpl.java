package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.QrInfoDao;
import com.labwinner.domain.QrInfo;
import com.labwinner.service.QrInfoService;

@Service
public class QrInfoServiceImpl implements QrInfoService{

	private static final Logger log = LoggerFactory
			.getLogger(QrInfoServiceImpl.class);
	@Autowired
	private QrInfoDao qrInfoDao;
	
	@Override
	public void save(QrInfo qrInfo) {
		log.debug("saving qrInfo instance");
		try {
			qrInfoDao.save(qrInfo);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public QrInfo getById(Integer id) {
		log.debug("saving qrInfo instance");
		try {
			return qrInfoDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving qrInfo instance");
		try {
			qrInfoDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void batchRemove(List<Integer> ids) {
		log.debug("saving qrInfo instance");
		try {
			qrInfoDao.batchRemove(ids);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
