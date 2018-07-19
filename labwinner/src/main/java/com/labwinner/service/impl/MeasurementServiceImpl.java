package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.MeasurementDao;
import com.labwinner.domain.Measurement;
import com.labwinner.service.MeasurementService;
/**
 * A data access object (DAO) providing persistence and search support for
 * Measurement entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.labwinner.hibernate.Measurement
 * @author MyEclipse Persistence Tools
 */

@Service
public class MeasurementServiceImpl implements MeasurementService{
	private static final Logger log = LoggerFactory
			.getLogger(MeasurementServiceImpl.class);
	@Autowired
	private MeasurementDao measurementDao;
	
	public void save(Measurement measurement) {
		log.debug("saving Measurement instance");
		try {
			measurementDao.save(measurement);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public List<Measurement> findAll() {
		log.debug("finding all Measurement instances");
		try {
			List<Measurement> suppliers= measurementDao.getAll();
			return suppliers;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Measurement> getById(Integer id) {
		log.debug("saving Measurement instance");
		try {
			log.debug("find successful");
			return measurementDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		
		measurementDao.delete(id);
		
	}

	@Override
	public List<Measurement> getByMeasureType() {
			log.debug("saving Measurement instance");
			try {
				log.debug("find successful");
				return measurementDao.getByMeasureType();
				
			} catch (RuntimeException re) {
				log.error("save failed", re);
				throw re;
			}
		}

	@Override
	public Integer getByType(Integer id) {
		log.debug("saving Measurement instance");
		try {
			log.debug("find successful");
			return measurementDao.getByType(id);
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Measurement> getBySourceId() {
		log.debug("saving Measurement instance");
		try {
			log.debug("find successful");
			return measurementDao.getBySourceId();
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void update(Measurement measurement) {
		log.debug("saving Measurement instance");
		try {
			measurementDao.update(measurement);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public Integer getByUnit(String measureUnit) {
		log.debug("saving Measurement instance");
		try {
			log.debug("find successful");
			return measurementDao.getByUnit(measureUnit);
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteFlag(Integer id) {
		log.debug("saving Measurement instance");
		try {
			measurementDao.deleteFlag(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Integer> getMeasureTypes() {
		log.debug("saving Measurement instance");
		try {
			log.debug("find successful");
			return measurementDao.getMeasureTypes();
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Integer getCount(String name, Integer id) {
		log.debug("saving Measurement instance");
		try {
			log.debug("find successful");
			return measurementDao.getCount(name,id);
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Measurement> getMeasurementByMeasureType(Integer id) {
		log.debug("finding all Measurement instances");
		try {
			List<Measurement> suppliers= measurementDao.getMeasurementByMeasureType(id);
			return suppliers;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public Measurement getByMeasureUnitId(Integer id) {
		log.debug("saving Measurement instance");
		try {
			log.debug("find successful");
			return measurementDao.getByMeasureUnitId(id);
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}


}