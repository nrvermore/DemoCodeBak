package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MeasurementTypeDao;
import com.labwinner.domain.MeasurementType;
import com.labwinner.service.MeasurementTypeService;

@Service
public class MeasurementTypeServiceImpl implements MeasurementTypeService{

	private static final Logger log = LoggerFactory
			.getLogger(MeasurementTypeServiceImpl.class);
	
	@Autowired
	private MeasurementTypeDao measurementTypeDao;
	
	@Override
	public void save(MeasurementType measurementType) {
		log.debug("saving MeasurementType instance");
		try {
			measurementTypeDao.save(measurementType);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete MeasurementType instance");
		try {
			measurementTypeDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MeasurementType> getAll() {
		log.debug("get MeasurementType instance");
		try {
			return measurementTypeDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Integer getByName(String name) {
		log.debug("get MeasurementType instance");
		try {
			return measurementTypeDao.getByName(name);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(MeasurementType measurementType) {
		log.debug("saving MeasurementType instance");
		try {
			measurementTypeDao.update(measurementType);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<MeasurementType> getList() {
		log.debug("get MeasurementType instance");
		try {
			return measurementTypeDao.getList();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Integer getCount(String name, Integer id) {
		log.debug("get MeasurementType instance");
		try {
			return measurementTypeDao.getCount(name,id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
