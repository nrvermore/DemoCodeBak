package com.labwinner.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.CommentLikeDao;
import com.labwinner.domain.CommentLike;
import com.labwinner.service.CommentLikeService;

@Service
public class CommentLikeServiceImpl implements CommentLikeService{

	private static final Logger log = LoggerFactory
			.getLogger(CommentLikeServiceImpl.class);
	
	@Autowired
	private CommentLikeDao commentLikeDao;
	
	@Override
	public void save(CommentLike commentLike) {
		log.debug("saving commentLike instance");
		try {
			commentLikeDao.save(commentLike);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete commentLike instance");
		try {
			commentLikeDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Integer getNum(Integer id) {
		log.debug("getNum commentLike instance");
		try {
			return commentLikeDao.getNum(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteById(Integer id) {
		log.debug("delete commentLike instance");
		try {
			commentLikeDao.deleteById(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public CommentLike getByUser(Integer userId, Integer agencyId,
			Integer commentId) {
		log.debug("delete commentLike instance");
		try {
			return commentLikeDao.getByUser(userId,agencyId,commentId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
