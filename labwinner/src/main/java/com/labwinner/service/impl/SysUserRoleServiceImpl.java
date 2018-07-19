package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SysUserRoleDao;
import com.labwinner.domain.SysUserRole;
import com.labwinner.service.SysUserRoleService;

/**
 * @Description 用户角色Service实现
 * @author liuhq
 * @version V1.0
 * @date 2017年6月16日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
	private static final Logger log = LoggerFactory
			.getLogger(SysUserRoleServiceImpl.class);

	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	/**
	 * {@inheritDoc}
	 */
	public List<SysUserRole> getAll() {
		log.debug("finding all SysUserRole instances");
		try {
			return sysUserRoleDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public SysUserRole getById(Long id) {
		log.debug("getting SysUserRole instance with id: " + id);
		try {

			SysUserRole sysUserRole = (SysUserRole) sysUserRoleDao.getById(id);
			return sysUserRole;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(SysUserRole sysUserRole) {
		log.debug("saving SysUserRole instance");
		try {
			log.debug("save successful");
			sysUserRoleDao.save(sysUserRole);
			return sysUserRole.getUserRoleId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(SysUserRole sysUserRole) {
		log.debug("saving SysUserRole instance");
		try {
			sysUserRoleDao.update(sysUserRole);
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
		log.debug("deleting SysUserRole instance");
		try {
			sysUserRoleDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteByRoleId(Long id) {
		log.debug("deleting SysUserRole instance");
		try {
			sysUserRoleDao.deleteByRoleId(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SysUserRole> getByRoleId(Long id) {
		log.debug("finding all SysUserRole instances");
		try {
			return sysUserRoleDao.getByRoleId(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<SysUserRole> getUser(Long id) {
		log.debug("finding all SysUserRole instances");
		try {
			return sysUserRoleDao.getUser(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<SysUserRole> getProAll() {
		log.debug("finding all SysUserRole instances");
		try {
			return sysUserRoleDao.getProAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}