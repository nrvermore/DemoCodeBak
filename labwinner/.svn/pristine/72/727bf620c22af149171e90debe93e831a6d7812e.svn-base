package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MomentsCommentDao;
import com.labwinner.domain.TeamProjMomentsComment;
import com.labwinner.service.MomentsCommentService;

@Service
public class MomentsCommentServiceImpl implements MomentsCommentService{

	private static final Logger log = LoggerFactory
			.getLogger(ProjectMomentsServiceImpl.class);
	@Autowired
	private MomentsCommentDao momentsCommentDao;
	
	@Override
	public void save(TeamProjMomentsComment momentsComment) {
		log.debug("saving TeamProjMomentsComment instance");
		try {
			momentsCommentDao.save(momentsComment);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete TeamProjMomentsComment instance");
		try {
			momentsCommentDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public TeamProjMomentsComment getById(Integer id) {
		log.debug("delete TeamProjMomentsComment instance");
		try {
			return momentsCommentDao.getById(id);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<TeamProjMomentsComment> getAll() {
		log.debug("delete TeamProjMomentsComment instance");
		try {
			return momentsCommentDao.getAll();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<TeamProjMomentsComment> getByMomentsId(Integer momentsType,
			Integer momentsInfoId) {
		log.debug("delete TeamProjMomentsComment instance");
		try {
			return momentsCommentDao.getByMomentsId(momentsType,momentsInfoId);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByMomentsId(Integer momentsInfoId, Integer momentsType) {
		log.debug("delete TeamProjMomentsComment instance");
		try {
			momentsCommentDao.deleteByMomentsId(momentsInfoId,momentsType);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

}
