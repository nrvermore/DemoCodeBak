package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ReactionImageDao;
import com.labwinner.domain.ReactionImage;
import com.labwinner.service.ReactionImageService;

@Service
public class ReactionImageServiceImpl implements ReactionImageService{

	private static final Logger log = LoggerFactory
			.getLogger(ReactionImageServiceImpl.class);
	@Autowired
	private ReactionImageDao reactionImageDao;
	
	@Override
	public void save(ReactionImage reactionImage) {
		log.debug("saving reactionImage instance");
		try {
			reactionImageDao.save(reactionImage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(ReactionImage reactionImage) {
		log.debug("saving reactionImage instance");
		try {
			reactionImageDao.update(reactionImage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving reactionImage instance");
		try {
			reactionImageDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<String> getById(Integer id) {
		log.debug("saving reactionImage instance");
		try {
			return reactionImageDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByImg(String img) {
		log.debug("saving reactionImage instance");
		try {
			reactionImageDao.deleteByImg(img);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		log.debug("saving reactionImage instance");
		try {
			reactionImageDao.deleteById(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
		
	}

	@Override
	public String getByImageId(Integer id) {
		log.debug("saving reactionImage instance");
		try {
			return reactionImageDao.getByImageId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void batchRemove(List<Integer> ids) {
		log.debug("saving reactionImage instance");
		try {
			reactionImageDao.batchRemove(ids);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

}
