package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.AnalyticalAttachmentDao;
import com.labwinner.dao.RepresentationMapDao;
import com.labwinner.domain.RepresentationMap;
import com.labwinner.service.RepresentationMapService;

@Service
public class RepresentationMapServiceImpl implements RepresentationMapService{

	private static final Logger log = LoggerFactory
			.getLogger(RepresentationMapServiceImpl.class);
	@Autowired
	private RepresentationMapDao representationMapDao;
	
	@Override
	public void save(RepresentationMap representationMap) {
		log.debug("saving representationMap instance");
		try {
			representationMapDao.save(representationMap);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(RepresentationMap representationMap) {
		log.debug("saving representationMap instance");
		try {
			representationMapDao.update(representationMap);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving representationMap instance");
		try {
			representationMapDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public RepresentationMap getById(Integer id) {
		log.debug("saving representationMap instance");
		try {
			return representationMapDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<RepresentationMap> getAll() {
		log.debug("saving representationMap instance");
		try {
			return representationMapDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByUrl(String url) {
		log.debug("saving representationMap instance");
		try {
			representationMapDao.deleteByUrl(url);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

}
