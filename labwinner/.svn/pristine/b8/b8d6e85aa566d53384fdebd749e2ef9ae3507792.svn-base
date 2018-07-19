package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MolecularImageDao;
import com.labwinner.domain.MolecularImage;
import com.labwinner.service.MolecularImageService;

@Service
public class MolecularImageServiceImpl implements MolecularImageService{

	private static final Logger log = LoggerFactory
			.getLogger(MolecularImageServiceImpl.class);
	@Autowired
	private MolecularImageDao molecularImageDao;
	
	@Override
	public void save(MolecularImage molecularImage) {
		log.debug("saving molecularImage instance");
		try {
			molecularImageDao.save(molecularImage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(MolecularImage molecularImage) {
		log.debug("saving molecularImage instance");
		try {
			molecularImageDao.update(molecularImage);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving molecularImage instance");
		try {
			molecularImageDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public MolecularImage getById(Integer id) {
		log.debug("saving molecularImage instance");
		try {
			return molecularImageDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MolecularImage> getAll() {
		log.debug("saving molecularImage instance");
		try {
			return molecularImageDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByUrl(String url) {
		log.debug("saving molecularImage instance");
		try {
			molecularImageDao.deleteByUrl(url);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

}
