package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SysRoleDao;
import com.labwinner.domain.SysRole;
import com.labwinner.service.SysRoleService;

/**
 * @Description 角色Service实现
 * @author liuhq
 * @version V1.0
 * @date 2017年6月6日 上午10:33:07
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
	private static final Logger log = LoggerFactory
			.getLogger(SysRoleServiceImpl.class);

	@Autowired
	private SysRoleDao sysRoleDao;

	/**
	 * {@inheritDoc}
	 */
	public List<SysRole> getAll() {
		log.debug("finding all SysRole instances");
		try {
			return sysRoleDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public SysRole getById(Long id) {
		log.debug("getting SysRole instance with id: " + id);
		try {

			SysRole sysRole = (SysRole) sysRoleDao.getById(id);
			return sysRole;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(SysRole sysRole) {
		log.debug("saving SysRole instance");
		try {
			log.debug("save successful");
			sysRoleDao.save(sysRole);
			return sysRole.getRoleId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(SysRole sysRole) {
		log.debug("saving SysRole instance");
		try {
			sysRoleDao.update(sysRole);
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
		log.debug("deleting SysRole instance");
		try {
			sysRoleDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<SysRole> getAllPageable(String keyword) {
		log.debug("finding all SysRole instances");
		try {
			return sysRoleDao.getAllPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<SysRole> getAllForEdit() {
		log.debug("finding all SysRole instances");
		try {
			return sysRoleDao.getAllForEdit();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public int getMaxId() {
		log.debug("finding all SysRole instances");
		try {
			return sysRoleDao.getMaxId();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<SysRole> getAllName() {
		log.debug("finding all SysRole instances");
		try {
			return sysRoleDao.getAllName();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}