package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SelfPaperDao;
import com.labwinner.domain.SelfPaper;
import com.labwinner.service.SelfPaperService;

/**
 * @Description 自主论文Service实现
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class SelfPaperServiceImpl implements SelfPaperService{

	private static final Logger log = LoggerFactory
			.getLogger(SelfPaperServiceImpl.class);
	
	@Autowired
	private SelfPaperDao selfPaperDao;
	
	@Override
	public void save(SelfPaper selfPaper) {
		log.debug("saving Inventory instance");
		try {
			selfPaperDao.save(selfPaper);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(SelfPaper selfPaper) {
		log.debug("update Inventory instance");
		try {
			selfPaperDao.update(selfPaper);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("deleting Inventory instance");
		try {
			selfPaperDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public SelfPaper getById(Integer id) {
		log.debug("getById Inventory instance");
		try {
			log.debug("getById successful");
			return selfPaperDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<SelfPaper> getByName(Integer id,String name,String roleName) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<SelfPaper> selfPapers =selfPaperDao.getByName(id,name,roleName);
			return  selfPapers;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<SelfPaper> getAll(Integer id,String roleName) {
		log.debug("SelfPaper getById ");
		try {
			log.debug("getById successful");
			List<SelfPaper> selfPapers =selfPaperDao.getAll(id,roleName);
			return  selfPapers;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<SelfPaper> getAllForApp(Integer userId, int size,String roleName) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<SelfPaper> selfPapers =selfPaperDao.getAllForApp(userId,size,roleName);
			return  selfPapers;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<SelfPaper> getByNameForApp(Integer userId, String keyword,
			int size,String roleName) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<SelfPaper> selfPapers =selfPaperDao.getByNameForApp(userId,keyword,size,roleName);
			return  selfPapers;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public int getSelfPaperNum(Integer userId, String roleName) {
		log.debug("getInventoryNum  instance");
		try {
		return	selfPaperDao.getSelfPaperNum(userId,roleName);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<SelfPaper> getByUserId(Integer userId, int id, String roleName) {
		try {
		return	selfPaperDao.getByUserId(userId,id,roleName);
	} catch (RuntimeException re) {
		log.error("delete failed", re);
		throw re;
	}
	}

}
