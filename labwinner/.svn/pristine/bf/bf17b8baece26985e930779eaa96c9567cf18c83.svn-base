package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ProjectMomentsDao;
import com.labwinner.domain.ProjectMoments;
import com.labwinner.service.ProjectMomentsService;

@Service
public class ProjectMomentsServiceImpl implements ProjectMomentsService{

	private static final Logger log = LoggerFactory
			.getLogger(ProjectMomentsServiceImpl.class);
	@Autowired
	private ProjectMomentsDao projectMomentsDao;
	
	@Override
	public void save(ProjectMoments projectMoments) {
		log.debug("saving ProjectMoments instance");
		try {
			projectMomentsDao.save(projectMoments);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving ProjectMoments instance");
		try {
			projectMomentsDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public ProjectMoments getById(Integer id) {
		log.debug("saving ProjectMoments instance");
		try {
		  return projectMomentsDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ProjectMoments> getAll(Integer userId,String roleName, Integer startCount,
			Integer endCount) {
		log.debug("saving ProjectMoments instance");
		try {
		  return projectMomentsDao.getAll(userId,roleName,startCount,endCount);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ProjectMoments> getAllByDay() {
		log.debug("saving ProjectMoments instance");
		try {
		  return projectMomentsDao.getAllByDay();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Integer getTotalCount(Integer userId) {
		log.debug("saving ProjectMoments instance");
		try {
		  return projectMomentsDao.getTotalCount(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
