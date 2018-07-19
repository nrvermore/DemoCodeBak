package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SysRoleMenuDao;
import com.labwinner.domain.SysRoleMenu;
import com.labwinner.service.SysRoleMenuService;

/**
 * @Description 角色菜单Service实现
 * @author liuhq
 * @version V1.0
 * @date 2017年6月15日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	private static final Logger log = LoggerFactory
			.getLogger(SysRoleMenuServiceImpl.class);

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	/**
	 * {@inheritDoc}
	 */
	public List<SysRoleMenu> getAll() {
		log.debug("finding all SysRoleMenu instances");
		try {
			return sysRoleMenuDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public SysRoleMenu getById(Long id) {
		log.debug("getting SysRoleMenu instance with id: " + id);
		try {

			SysRoleMenu sysRoleMenu = (SysRoleMenu) sysRoleMenuDao.getById(id);
			return sysRoleMenu;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(SysRoleMenu sysRoleMenu) {
		log.debug("saving SysRoleMenu instance");
		try {
			log.debug("save successful");
			sysRoleMenuDao.save(sysRoleMenu);
			return sysRoleMenu.getRoleMenuId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(SysRoleMenu sysRoleMenu) {
		log.debug("saving SysRoleMenu instance");
		try {
			sysRoleMenuDao.update(sysRoleMenu);
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
		log.debug("deleting SysRoleMenu instance");
		try {
			sysRoleMenuDao.delete(id);
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
		log.debug("deleting SysRoleMenu instance");
		try {
			sysRoleMenuDao.deleteByRoleId(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SysRoleMenu> getByRoleId(Long id) {
		log.debug("finding all SysRoleMenu instances");
		try {
			return sysRoleMenuDao.getByRoleId(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}