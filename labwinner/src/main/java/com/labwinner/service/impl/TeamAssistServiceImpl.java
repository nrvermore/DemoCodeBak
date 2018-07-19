package com.labwinner.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.TeamAssistDao;
import com.labwinner.domain.SysUser;
import com.labwinner.domain.TeamAssist;
import com.labwinner.service.TeamAssistService;

@Service
public class TeamAssistServiceImpl implements TeamAssistService{

	private static final Logger log = LoggerFactory
			.getLogger(PrototypeServiceImpl.class);
	@Autowired
	private TeamAssistDao teamAssistDao;
	
	@Override
	public void save(TeamAssist teamAssist) {
		log.debug("saving Inventory instance");
		try {
			teamAssistDao.save(teamAssist);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(TeamAssist teamAssist) {
		log.debug("saving Inventory instance");
		try {
			teamAssistDao.update(teamAssist);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving Inventory instance");
		try {
			teamAssistDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public TeamAssist getById(Integer id) {
		log.debug("saving Inventory instance");
		try {
			return teamAssistDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByProcessId(Integer id) {
		log.debug("saving Inventory instance");
		try {
			teamAssistDao.deleteByProcessId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<Map<String, String>> getByProcessId(Integer processId) {
		log.debug("saving Inventory instance");
		try {
			return teamAssistDao.getByProcessId(processId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}


}
