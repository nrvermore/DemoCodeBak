package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SysMenuDao;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.SysMenu;
import com.labwinner.domain.SysUser;
import com.labwinner.service.SysMenuService;

/**
 * @Description 菜单Service实现
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
	private static final Logger log = LoggerFactory
			.getLogger(SysMenuServiceImpl.class);

	@Autowired
	private SysMenuDao sysMenuDao;

	/**
	 * {@inheritDoc}
	 */
	public List<SysMenu> getAll() {
		log.debug("finding all SysMenu instances");
		try {
			return sysMenuDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<SysMenu> getAllPageable(String filter) {
		log.debug("finding all SysMenu instances");
		try {
			return sysMenuDao.getAllPageable(filter);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public SysMenu getById(Long id) {
		log.debug("getting SysMenu instance with id: " + id);
		try {

			SysMenu sysMenu = (SysMenu) sysMenuDao.getById(id);
			return sysMenu;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(SysMenu sysMenu) {
		log.debug("saving SysMenu instance");
		try {
			log.debug("save successful");
			sysMenuDao.save(sysMenu);
			return sysMenu.getMenuId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(SysMenu sysMenu) {
		log.debug("saving SysMenu instance");
		try {
			sysMenuDao.update(sysMenu);
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
		log.debug("deleting SysMenu instance");
		try {
			sysMenuDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<SysMenu> getAllFirst() {
		log.debug("finding all SysMenu instances");
		try {
			return sysMenuDao.getAllFirst();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public SysMenu getTree(Integer menuId) {
		SysMenu node = sysMenuDao.getSysMenu(menuId);
		// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
		List<SysMenu> childTreeNodes = sysMenuDao
				.getSysMenus(menuId);
		// 遍历子节点
		for (SysMenu child : childTreeNodes) {
			SysMenu n = getTree(child.getMenuId()); // 递归
			node.getChildren().add(n);
		}
		return node;
	}

	@Override
	public void updateFlag(Long id,Integer flag) {
		log.debug("deleting SysMenu instance");
		try {
			sysMenuDao.updateFlag(id,flag);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

}