package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.AttachmentDao;
import com.labwinner.domain.Attachment;
import com.labwinner.service.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService{

	private static final Logger log = LoggerFactory
			.getLogger(AttachmentServiceImpl.class);
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Override
	public void save(Attachment attachment) {
		log.debug("saving attachment instance");
		try {
			attachmentDao.save(attachment);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(Attachment attachment) {
		log.debug("update attachment instance");
		try {
			attachmentDao.update(attachment);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete attachment instance");
		try {
			attachmentDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Attachment getById(Integer id) {
		log.debug("update attachment instance");
		try {
			return attachmentDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Attachment> getAll() {
		log.debug("getAll attachment instance");
		try {
			return attachmentDao.getAll();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByMsgId(Integer id) {
		log.debug("delete attachment instance");
		try {
			attachmentDao.deleteByMsgId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<Attachment> getAllConversion() {
		log.debug("getAll attachment instance");
		try {
			return attachmentDao.getAllConversion();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void updatePdfCount(Integer attachmentId) {
		log.debug("update attachment instance");
		try {
			attachmentDao.updatePdfCount(attachmentId);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<Attachment> getByMessageId(Integer messageId) {
		log.debug("getAll attachment instance");
		try {
			return attachmentDao.getByMessageId(messageId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
