package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SysDictionaryDao;
import com.labwinner.domain.SysDictionary;
import com.labwinner.service.SysDictionaryService;

/**
 * @Description 字典Service实现
 * @author liuhq
 * @version V1.0
 * @date 2017年6月7日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {
	private static final Logger log = LoggerFactory
			.getLogger(SysDictionaryServiceImpl.class);

	@Autowired
	private SysDictionaryDao sysDictionaryDao;

	/**
	 * {@inheritDoc}
	 */
	public List<SysDictionary> getAll() {
		log.debug("finding all SysDictionary instances");
		try {
			return sysDictionaryDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<SysDictionary> getByDictType(String dictType) {
		log.debug("finding all SysDictionary instances with dictType: "
				+ dictType);
		try {
			return sysDictionaryDao.getByDictType(dictType);
		} catch (RuntimeException re) {
			log.error("find all SysDictionary instances with dictType: "
					+ dictType + " failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public SysDictionary getById(Long id) {
		log.debug("getting SysDictionary instance with id: " + id);
		try {

			SysDictionary sysDictionary = (SysDictionary) sysDictionaryDao
					.getById(id);
			return sysDictionary;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(SysDictionary sysDictionary) {
		log.debug("saving SysDictionary instance");
		try {
			log.debug("save successful");
			sysDictionaryDao.save(sysDictionary);
			return sysDictionary.getDictId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(SysDictionary sysDictionary) {
		log.debug("saving SysDictionary instance");
		try {
			sysDictionaryDao.update(sysDictionary);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Long id) {
		log.debug("deleting SysDictionary instance");
		try {
			sysDictionaryDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}