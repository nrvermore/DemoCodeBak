package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MediaResourceDao;
import com.labwinner.domain.MediaResource;
import com.labwinner.service.MediaResourceService;

@Service
public class MediaResourceServiceImpl implements MediaResourceService{

	private static final Logger log = LoggerFactory
			.getLogger(MediaResourceServiceImpl.class);
	
	@Autowired
	private MediaResourceDao mediaResourceDao;
	
	@Override
	public void save(MediaResource mediaResource) {
		log.debug("saving Inventory instance");
		try {
			mediaResourceDao.save(mediaResource);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(MediaResource mediaResource) {
		log.debug("update Inventory instance");
		try {
			mediaResourceDao.update(mediaResource);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("deleting Inventory instance");
		try {
			mediaResourceDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
		
	}

	@Override
	public MediaResource getById(Integer id) {
		log.debug("getById Inventory instance");
		try {
			log.debug("getById successful");
			return mediaResourceDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<MediaResource> getByName(String name) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<MediaResource> mediaResources =mediaResourceDao.getByName(name);
			return  mediaResources;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<MediaResource> getAll() {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<MediaResource> mediaResources =mediaResourceDao.getAll();
			return  mediaResources;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<MediaResource> getAllByDay() {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<MediaResource> mediaResources =mediaResourceDao.getAllByDay();
			return  mediaResources;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<MediaResource> getPageList(Integer pageCount) {
		log.debug("update Inventory instance");
		try {
			log.debug("getPageList successful");
			List<MediaResource> mediaResources =mediaResourceDao.getPageList(pageCount);
			return  mediaResources;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<MediaResource> getAllUsers() {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<MediaResource> mediaResources =mediaResourceDao.getAllUsers();
			return  mediaResources;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<MediaResource> getFirst() {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<MediaResource> mediaResources =mediaResourceDao.getFirst();
			return  mediaResources;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
