package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.TestAttachmentDao;
import com.labwinner.domain.TestAttachment;
import com.labwinner.service.TestAttachementService;

@Service
public class TestAttachementServiceImpl implements TestAttachementService{

	private static final Logger log = LoggerFactory
			.getLogger(TestAttachementServiceImpl.class);
	@Autowired
	private TestAttachmentDao testAttachmentDao;
	
	@Override
	public void save(TestAttachment testAttachment) {
		log.debug("saving prototype instance");
		try {
			testAttachmentDao.save(testAttachment);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving prototype instance");
		try {
			testAttachmentDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<TestAttachment> getById(Integer id) {
		log.debug("saving prototype instance");
		try {
			return testAttachmentDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByUrl(String filename) {
		log.debug("saving prototype instance");
		try {
			testAttachmentDao.deleteByUrl(filename);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
