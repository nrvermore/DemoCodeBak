package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.TeamMomentsImageDao;
import com.labwinner.domain.TeamMomentsImage;
import com.labwinner.service.TeamMomentsImageService;

@Service
public class TeamMomentsImageServiceImpl implements TeamMomentsImageService{

	private static final Logger log = LoggerFactory
			.getLogger(TeamMomentsImageServiceImpl.class);
	@Autowired
	private TeamMomentsImageDao teamMomentsImageDao;
	
	@Override
	public void save(TeamMomentsImage teamMomentsImage) {
		log.debug("saving TeamMomentsImage instance");
		try {
			teamMomentsImageDao.save(teamMomentsImage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving TeamMomentsImage instance");
		try {
			teamMomentsImageDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public TeamMomentsImage getById(Integer id) {
		log.debug("saving TeamMomentsImage instance");
		try {
			return teamMomentsImageDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<TeamMomentsImage> getAll() {
		log.debug("saving TeamMomentsImage instance");
		try {
			return teamMomentsImageDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByMomentsId(Integer momentsInfoId, Integer momentsType) {
		log.debug("saving TeamMomentsImage instance");
		try {
			teamMomentsImageDao.deleteByMomentsId(momentsInfoId,momentsType);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

}
