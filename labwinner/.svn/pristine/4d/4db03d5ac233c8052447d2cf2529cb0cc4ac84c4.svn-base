package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.CommentDao;
import com.labwinner.domain.CommentEntity;
import com.labwinner.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	private static final Logger log = LoggerFactory
			.getLogger(CommentServiceImpl.class);
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public void save(CommentEntity commentEntity) {
		log.debug("saving commentEntity instance");
		try {
			commentDao.save(commentEntity);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(CommentEntity commentEntity) {
		log.debug("saving commentEntity instance");
		try {
			commentDao.update(commentEntity);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("deleting commentEntity instance");
		try {
			commentDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByModuleId(Integer moduleId, Integer typeId) {
		log.debug("deleting commentEntity instance");
		try {
			commentDao.deleteByModuleId(moduleId,typeId);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<CommentEntity> getByModuleId(Integer moduleId, Integer typeId) {
		log.debug("getting commentEntity instance");
		try {
			return commentDao.getByModuleId(moduleId,typeId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
