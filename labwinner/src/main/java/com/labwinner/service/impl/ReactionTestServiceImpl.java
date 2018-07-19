package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.NoteDao;
import com.labwinner.dao.ReactionTestDao;
import com.labwinner.domain.ReactionTest;
import com.labwinner.service.ReactionTestService;

@Service
public class ReactionTestServiceImpl implements ReactionTestService{

	private static final Logger log = LoggerFactory
			.getLogger(ReactionTestServiceImpl.class);
	@Autowired
	private ReactionTestDao reactionTestDao;
	
	@Override
	public void save(ReactionTest reactionTest) {
		log.debug("saving note instance");
		try {
			reactionTestDao.save(reactionTest);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(ReactionTest reactionTest) {
		log.debug("saving note instance");
		try {
			reactionTestDao.update(reactionTest);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving note instance");
		try {
			reactionTestDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<ReactionTest> getByKeyword(String keyword) {
		log.debug("saving note instance");
		try {
			return reactionTestDao.getByKeyword(keyword);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionTest> getByReactionId(Integer id) {
		log.debug("saving note instance");
		try {
			return reactionTestDao.getByReactionId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionTest> getAll() {
		log.debug("saving note instance");
		try {
			return reactionTestDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public ReactionTest getById(Integer id) {
		log.debug("saving note instance");
		try {
			return reactionTestDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByReactionId(Integer id) {
		log.debug("saving note instance");
		try {
			reactionTestDao.deleteByReactionId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<ReactionTest> getUserList(Integer userId) {
		log.debug("saving note instance");
		try {
			return reactionTestDao.getUserList(userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionTest> getUserListByKeyword(String keyword,
			Integer userId) {
		log.debug("saving note instance");
		try {
			return reactionTestDao.getUserListByKeyword(keyword,userId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
