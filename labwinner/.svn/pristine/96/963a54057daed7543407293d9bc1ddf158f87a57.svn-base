package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SysAttachmentDao;
import com.labwinner.domain.SysAttachment;
import com.labwinner.service.SysAttachmentService;

/**
 * @Description 附件Service实现
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class SysAttachmentServiceImpl implements SysAttachmentService {
	private static final Logger log = LoggerFactory
			.getLogger(SysAttachmentServiceImpl.class);

	@Autowired
	private SysAttachmentDao sysAttachmentDao;

	/**
	 * {@inheritDoc}
	 */
	public List<SysAttachment> getAll() {
		log.debug("finding all SysAttachment instances");
		try {
			return sysAttachmentDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public SysAttachment getById(Long id) {
		log.debug("getting SysAttachment instance with id: " + id);
		try {

			SysAttachment sysAttachment = (SysAttachment) sysAttachmentDao
					.getById(id);
			return sysAttachment;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(SysAttachment sysAttachment) {
		log.debug("saving SysAttachment instance");
		try {
			log.debug("save successful");
			sysAttachmentDao.save(sysAttachment);
			return sysAttachment.getAttaId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(SysAttachment sysAttachment) {
		log.debug("saving SysAttachment instance");
		try {
			sysAttachmentDao.update(sysAttachment);
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
		log.debug("deleting SysAttachment instance");
		try {
			sysAttachmentDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}