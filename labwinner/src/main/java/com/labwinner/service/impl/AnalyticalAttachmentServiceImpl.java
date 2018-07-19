package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.AnalyticalAttachmentDao;
import com.labwinner.domain.AnalyticalAttachment;
import com.labwinner.service.AnalyticalAttachmentService;

@Service
public class AnalyticalAttachmentServiceImpl implements AnalyticalAttachmentService {

	private static final Logger log = LoggerFactory
			.getLogger(PrototypeServiceImpl.class);
	@Autowired
	private AnalyticalAttachmentDao analyticalAttachmentDao;
	
	@Override
	public void save(AnalyticalAttachment attachment) {
		log.debug("saving attachment instance");
		try {
			analyticalAttachmentDao.save(attachment);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving attachment instance");
		try {
			analyticalAttachmentDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<AnalyticalAttachment> getById(Integer id) {
		log.debug("saving attachment instance");
		try {
			return analyticalAttachmentDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void deleteByUrl(String filename) {
		log.debug("saving attachment instance");
		try {
			analyticalAttachmentDao.deleteByUrl(filename);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<AnalyticalAttachment> getAllConversion() {
		log.debug("saving attachment instance");
		try {
			return analyticalAttachmentDao.getAllConversion();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void updatePdfCount(Integer anaAttachmentId) {
		log.debug("updatePdfCount attachment instance");
		try {
			analyticalAttachmentDao.updatePdfCount(anaAttachmentId);
			log.debug("updatePdfCount successful");
		} catch (RuntimeException re) {
			log.error("updatePdfCount failed", re);
			throw re;
		}
	}

	@Override
	public void update(AnalyticalAttachment analyticalAttachment) {
		log.debug("updatePdfCount attachment instance");
		try {
			analyticalAttachmentDao.update(analyticalAttachment);
			log.debug("updatePdfCount successful");
		} catch (RuntimeException re) {
			log.error("updatePdfCount failed", re);
			throw re;
		}
	}

	@Override
	public AnalyticalAttachment getByAttachmentId(Integer anaAttachmentId) {
		log.debug("saving attachment instance");
		try {
			return analyticalAttachmentDao.getByAttachmentId(anaAttachmentId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
