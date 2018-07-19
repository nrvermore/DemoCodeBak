package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.TeamMomentsLikeDao;
import com.labwinner.domain.TeamMomentsLike;
import com.labwinner.service.TeamMomentsLikeService;

@Service
public class TeamMomentsLikeServiceImpl implements TeamMomentsLikeService{

	private static final Logger log = LoggerFactory
			.getLogger(TeamMomentsLikeServiceImpl.class);
	@Autowired
	private TeamMomentsLikeDao teamMomentsLikeDao;
	
	@Override
	public void save(TeamMomentsLike teamMomentsLike) {
		log.debug("saving TeamMomentsLike instance");
		try {
			teamMomentsLikeDao.save(teamMomentsLike);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving TeamMomentsLike instance");
		try {
			teamMomentsLikeDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public TeamMomentsLike getById(Integer id) {
		log.debug("saving TeamMomentsLike instance");
		try {
			return teamMomentsLikeDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<TeamMomentsLike> getAll() {
		log.debug("saving TeamMomentsLike instance");
		try {
			return teamMomentsLikeDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<TeamMomentsLike> getByMomentsId(Integer momentsType,
			Integer momentsInfoId) {
		log.debug("saving TeamMomentsLike instance");
		try {
			return teamMomentsLikeDao.getByMomentsId(momentsType,momentsInfoId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByMomentsId(Integer momentsInfoId, Integer momentsType) {
		log.debug("saving TeamMomentsLike instance");
		try {
			teamMomentsLikeDao.deleteByMomentsId(momentsInfoId,momentsType);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

}
