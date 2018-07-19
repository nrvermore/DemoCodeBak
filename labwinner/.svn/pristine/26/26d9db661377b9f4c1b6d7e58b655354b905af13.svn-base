package com.labwinner.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


















import com.labwinner.dao.ProjectAssistDao;
import com.labwinner.domain.ProjectAssist;
import com.labwinner.service.ProjectAssistService;
@Service
public class ProjectAssistServiceImpl implements ProjectAssistService {

	private static final Logger log = LoggerFactory
			.getLogger(ProjectAssistServiceImpl.class);
	@Autowired
	private ProjectAssistDao projectAssistDao;
	@Override
	public List<Map<String,Integer>> getProNumber(Integer userId,Integer projectId) {
		log.debug("getAll Expert instance");
		try {
			List<Map<String,Integer>> proNumber= projectAssistDao.getProNumber(userId,projectId);
		        return proNumber;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}
	@Override
	public void save(ProjectAssist projectAssist) {
		log.debug("saving Inventory instance");
		try {
			projectAssistDao.save(projectAssist);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	@Override
	public List<ProjectAssist> getAllContentList(Integer userId,
			Integer projectId) {
		try {
			List<ProjectAssist> projectAssists= projectAssistDao.getAllContentList(userId,projectId);
		        return projectAssists;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}
	@Override
	public void updateReadMan(String readMan,Integer projectAssistId) {
		log.debug("saving Inventory instance");
		try {
			projectAssistDao.updateReadMan(readMan,projectAssistId);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	@Override
	public void updateDelete(Integer projectAssistId, String deleteMan) {
		log.debug("saving Inventory instance");
		try {
			projectAssistDao.updateDelete(projectAssistId,deleteMan);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	@Override
	public List<ProjectAssist> getAssistList(Integer userId, String roleName,
			Integer endCount) {
		try {
			List<ProjectAssist> projectAssists= projectAssistDao.getAssistList(userId,roleName,endCount);
		        return projectAssists;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}
	@Override
	public List<ProjectAssist> getAllUnread(Integer userId, Integer proId) {
		try {
			List<ProjectAssist> projectAssists= projectAssistDao.getAllUnread(userId,proId);
		        return projectAssists;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}
	@Override
	public List<ProjectAssist> getByProjectAssistId(Integer projectAssistId) {
		try {
			List<ProjectAssist> projectAssists= projectAssistDao.getByProjectAssistId(projectAssistId);
		        return projectAssists;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}
	@Override
	public List<ProjectAssist> getNewProjectAssist(Integer userId) {
		try {
			List<ProjectAssist> projectAssists= projectAssistDao.getNewProjectAssist(userId);
		        return projectAssists;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}
	@Override
	public List<ProjectAssist> getAllMessageContentList(Integer userId,
			Integer id) {
		try {
			List<ProjectAssist> projectAssists= projectAssistDao.getAllMessageContentList(userId,id);
		        return projectAssists;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}
	@Override
	public void updateMessage(String msgDelete, Integer projectAssistId) {
		projectAssistDao.updateMessage(msgDelete,projectAssistId);
		
	}
	@Override
	public List<ProjectAssist> getNewTeamAssist(Integer userId) {
		try {
			List<ProjectAssist> projectAssists= projectAssistDao.getNewTeamAssist(userId);
		        return projectAssists;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}
	

	

}
