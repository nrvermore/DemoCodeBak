package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MsgStateDao;
import com.labwinner.domain.MsgState;
import com.labwinner.service.MsgStateService;

@Service
public class MsgStateServiceImpl implements  MsgStateService{

	
	private static final Logger log = LoggerFactory
			.getLogger(MsgStateServiceImpl.class);
	@Autowired
	private MsgStateDao msgStateDao;
	
	@Override
	public void save(MsgState msgState) {
		log.debug("saving msgState instance");
		try {
			msgStateDao.save(msgState);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(MsgState msgState) {
		log.debug("saving msgState instance");
		try {
			msgStateDao.update(msgState);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving msgState instance");
		try {
			msgStateDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public MsgState getById(Integer id) {
		log.debug("saving msgState instance");
		try {
			return msgStateDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MsgState> getAll() {
		log.debug("saving msgState instance");
		try {
			return msgStateDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

}
