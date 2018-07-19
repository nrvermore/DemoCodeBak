package com.labwinner.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.PatentDao;
import com.labwinner.domain.Patent;
import com.labwinner.service.PatentService;
/**
 * @Description 专利Service实现
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class PatentServiceImpl implements PatentService{

	private static final Logger log = LoggerFactory
			.getLogger(PatentServiceImpl.class);
	
	@Autowired
	private PatentDao patentDao;
	
	@Override
	public void save(Patent patent) {
		log.debug("saving Inventory instance");
		try {
			patentDao.save(patent);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(Patent patent) {
		log.debug("update Inventory instance");
		try {
			patentDao.update(patent);
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
			patentDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public Patent getById(Integer id) {
		log.debug("getById Inventory instance");
		try {
			log.debug("getById successful");
			return patentDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Patent> getByName(String name) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<Patent> patents =patentDao.getByName(name);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Patent> getAll() {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<Patent> patents =patentDao.getAll();
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Patent> getAllForApp(int size) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<Patent> patents =patentDao.getAllForApp(size);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Patent> getByNameForApp(int size, String keyword) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<Patent> patents =patentDao.getByNameForApp(size,keyword);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public int getPatentNum() {
		log.debug("getInventoryNum  instance");
		try {
		return	patentDao.getPatentNum();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public int getMediaNum(Integer userId) {
		log.debug("getInventoryNum  instance");
		try {
		return	patentDao.getMediaNum(userId);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Patent> getAgencyPatent(Integer userId,
			String roleName, int angency) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<Patent> patents =patentDao.getAgencyPatent(userId,roleName,angency);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Patent> getBasePatent() {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<Patent> patents =patentDao.getBasePatent();
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllBase(String keyword) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<Map<String, Object>> patents =patentDao.getAllBase(keyword);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllBaseByFiledId(String keyword,
			Integer filedId) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<Map<String, Object>> patents =patentDao.getAllBaseByFiledId(keyword,filedId);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllPerson(Integer userId,
			String roleName, Integer agencyId, String keyword) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<Map<String, Object>> patents =patentDao.getAllPerson(userId,roleName,agencyId,keyword);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllPersonByFiledId(Integer userId,
			String roleName, Integer agencyId, String keyword, Integer filedId) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<Map<String, Object>> patents =patentDao.getAllPersonByFiledId(userId,roleName,agencyId,keyword,filedId);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getSelfKnowledge(Integer userId,
			Integer agencyId, String keyword) {
		log.debug("update Inventory instance");
		try {
			log.debug("getById successful");
			List<Map<String, Object>> patents =patentDao.getSelfKnowledge(userId,agencyId,keyword);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllBaseKnowByFiledId(String keyword,
			Integer filedId) {
		try {
			log.debug("getById successful");
			List<Map<String, Object>> patents =patentDao.getAllBaseKnowByFiledId(keyword,filedId);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getSelfKnowledgeForApp(Integer userId,
			String keyword) {
		try {
			log.debug("getById successful");
			List<Map<String, Object>> patents =patentDao.getSelfKnowledgeForApp(userId,keyword);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getAllPersonKnowlist(String keyword,
			Integer filedId) {
		try {
			log.debug("getById successful");
			List<Map<String, Object>> patents =patentDao.getAllPersonKnowlist(keyword,filedId);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getKnowledgeFirst(String roleName,
			Integer userId) {
		try {
			log.debug("getById successful");
			List<Map<String, Object>> patents =patentDao.getKnowledgeFirst(roleName,userId);
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Map<String, Object>> getKnowledgeFirstBase() {
		try {
			log.debug("getById successful");
			List<Map<String, Object>> patents =patentDao.getKnowledgeFirstBase();
			return  patents;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Patent getByAccId(Integer knowledgeAccId) {
		try {
			log.debug("getById successful");
			Patent patent =patentDao.getByAccId(knowledgeAccId);
			return  patent;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public int getPersonKnoledgeNum(Integer userId) {
		try {
			log.debug("getById successful");
			return patentDao.getPersonKnoledgeNum(userId);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public int getMaterialNum(Integer cid) {
		try {
			log.debug("getById successful");
			return patentDao.getMaterialNum(cid);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
