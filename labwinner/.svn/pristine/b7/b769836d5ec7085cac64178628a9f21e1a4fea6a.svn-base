package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.TeamMomentsDao;
import com.labwinner.domain.TeamMoments;
import com.labwinner.service.TeamMomentsService;

@Service
public class TeamMomentsServiceImpl implements TeamMomentsService{

	private static final Logger log = LoggerFactory
			.getLogger(TeamMomentsServiceImpl.class);
	@Autowired
	private TeamMomentsDao teamMomentsDao;
	
	@Override
	public void save(TeamMoments teamMoments) {
		log.debug("saving TeamMoments instance");
		try {
			teamMomentsDao.save(teamMoments);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving TeamMoments instance");
		try {
			teamMomentsDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public TeamMoments getById(Integer id) {
		log.debug("saving TeamMoments instance");
		try {
			return teamMomentsDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<TeamMoments> getAll(Integer userId, Integer startCount,
			Integer endCount) {
		log.debug("saving TeamMoments instance");
		try {
			return teamMomentsDao.getAll(userId,startCount,endCount);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
