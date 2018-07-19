package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ProjectMomentsRelationDao;
import com.labwinner.domain.ProjectMomentsRelation;
import com.labwinner.service.ProjectMomentsRelationService;

@Service
public class ProjectMomentsRelationServiceImpl implements ProjectMomentsRelationService{

	private static final Logger log = LoggerFactory
			.getLogger(ProjectMomentsRelationServiceImpl.class);
	@Autowired
	private ProjectMomentsRelationDao projectMomentsRelationDao;
	
	@Override
	public void save(ProjectMomentsRelation projectMomentsRelation) {
		log.debug("saving ProjectMomentsRelation instance");
		try {
			projectMomentsRelationDao.save(projectMomentsRelation);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving ProjectMomentsRelation instance");
		try {
			projectMomentsRelationDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public ProjectMomentsRelation getById(Integer id) {
		log.debug("saving ProjectMomentsRelation instance");
		try {
			return projectMomentsRelationDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ProjectMomentsRelation> getAll() {
		log.debug("saving ProjectMomentsRelation instance");
		try {
			return projectMomentsRelationDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
