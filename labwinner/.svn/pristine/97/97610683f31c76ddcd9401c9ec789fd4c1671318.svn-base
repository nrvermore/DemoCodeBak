package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MsgTypeDao;
import com.labwinner.domain.MsgType;
import com.labwinner.service.MsgTypeService;

@Service
public class MsgTypeServiceImpl implements MsgTypeService{

	private static final Logger log = LoggerFactory
			.getLogger(MsgTypeServiceImpl.class);
	@Autowired
	private MsgTypeDao msgTypeDao;
	
	@Override
	public void save(MsgType msgType) {
		log.debug("saving msgType instance");
		try {
			msgTypeDao.save(msgType);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(MsgType msgType) {
		log.debug("update msgType instance");
		try {
			msgTypeDao.update(msgType);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete msgType instance");
		try {
			msgTypeDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public MsgType getById(Integer id) {
		log.debug("get msgType instance");
		try {
			return msgTypeDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MsgType> getAll() {
		log.debug("get msgType instance");
		try {
			return msgTypeDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
