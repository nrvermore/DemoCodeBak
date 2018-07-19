package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ExpertAssistDao;
import com.labwinner.dao.ExpertDao;
import com.labwinner.domain.Expert;
import com.labwinner.domain.ExpertAssist;
import com.labwinner.service.ExpertAssistService;
@Service
public class ExpertAssistServiceImpl implements ExpertAssistService {

	private static final Logger log = LoggerFactory
			.getLogger(ExpertServiceImpl.class);
	@Autowired
	private ExpertAssistDao expertAssistDao;
	@Override
	public void save(ExpertAssist expertAssist) {
		log.debug("saving Expert instance");
		try {
			expertAssistDao.save(expertAssist);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		log.debug("delete Expert instance");
		try {
			expertAssistDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<ExpertAssist> getByExpertAndUser(Integer userId,
			Integer expertId,Integer agencyId,Integer deleteType) {
		log.debug("getAll Expert instance");
		try {
			List<ExpertAssist> expertAssist= expertAssistDao.getByExpertAndUser(userId,expertId,agencyId,deleteType);
		        return expertAssist;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ExpertAssist> getExpertContent(Integer userId,
			Integer expertId, Integer agencyId) {
		log.debug("getAll Expert instance");
		try {
			List<ExpertAssist> expertAssist= expertAssistDao.getExpertContent(userId,expertId,agencyId);
		        return expertAssist;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ExpertAssist> getUserContent(Integer userId, Integer agencyId,Integer expertId) {
		log.debug("getAll Expert instance");
		try {
			List<ExpertAssist> expertAssist= expertAssistDao.getUserContent(userId,agencyId,expertId);
		        return expertAssist;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ExpertAssist> getAllUserContent(Integer userId, Integer agencyId) {
		log.debug("getAll Expert instance");
		try {
			List<ExpertAssist> expertAssist= expertAssistDao.getAllUserContent(userId,agencyId);
		        return expertAssist;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByExpert(Integer userId, Integer expertId,
			Integer agencyId,Integer deleteType) {
		log.debug("delete Expert instance");
		try {
			expertAssistDao.deleteByExpert(userId,expertId,agencyId,deleteType);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void updateIsRead(Integer userId,Integer agencyId, Integer userId1,Integer agencyId1,String readMan) {
		log.debug("delete Expert instance");
		try {
			expertAssistDao.updateIsRead(userId,agencyId,userId1,agencyId1,readMan);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void updateByExpert(Integer userId, Integer expertId,
			Integer agencyId,Integer deleteType) {
		log.debug("delete Expert instance");
		try {
			expertAssistDao.updateByExpert(userId,expertId,agencyId,deleteType);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public int getDeleteType(Integer userId, Integer expertId, Integer agencyId) {
		log.debug("getAll Expert instance");
		try {
			Integer deleteType= expertAssistDao.getDeleteType(userId,expertId,agencyId);
			if(deleteType==null){
				   deleteType=3;
			}
		      return deleteType;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ExpertAssist> getAllAssist(Integer userId, Integer expertId,
			Integer agencyId) {
		log.debug("getAll Expert instance");
		try {
			List<ExpertAssist> expertAssist= expertAssistDao.getAllAssist(userId,expertId,agencyId);
		        return expertAssist;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ExpertAssist> getAllContentList(Integer userId,
			Integer agencyId, Integer userId1, Integer agencyId1,String deleteMan) {
		log.debug("getAll Expert instance");
		try {
			List<ExpertAssist> expertAssist= expertAssistDao.getAllContentList(userId,agencyId,userId1,agencyId1,deleteMan);
		        return expertAssist;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ExpertAssist> getAllAssistList(Integer userId, Integer agencyId,String deleteMan) {
		log.debug("getAll Expert instance");
		try {
			List<ExpertAssist> expertAssist= expertAssistDao.getAllAssistList(userId,agencyId,deleteMan);
		        return expertAssist;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public void updateDelete(Integer userId, Integer agencyId, Integer userId1,
			Integer agencyId1, String deleteMan) {
		log.debug("delete Expert instance");
		try {
			expertAssistDao.updateDelete(userId,agencyId,userId1,agencyId1,deleteMan);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public int getUnReadNumber(Integer userId, int agencyId, String readMan) {
		log.debug("getAll Expert instance");
		try {
			int num= expertAssistDao.getUnReadNumber(userId,agencyId,readMan);
		        return num;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public int getUnRead(Integer userId, Integer agencyId,Integer userId1, Integer agencyId1, String readMan) {
		log.debug("getAll Expert instance");
		try {
			int num= expertAssistDao.getUnRead(userId,agencyId,userId1,agencyId1,readMan);
		        return num;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ExpertAssist> getNewAssistList(Integer userId,
			Integer agencyId, String deleteMan) {
		log.debug("getAll Expert instance");
		try {
			List<ExpertAssist> expertAssists= expertAssistDao.getNewAssistList(userId,agencyId,deleteMan);
		        return expertAssists;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ExpertAssist> getAllMessageAssistList(Integer userId,
			Integer agencyId, String deleteMan) {
		try {
			List<ExpertAssist> expertAssists= expertAssistDao.getAllMessageAssistList(userId,agencyId,deleteMan);
		        return expertAssists;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ExpertAssist> getAllMessageContentList(Integer userId,
			Integer agencyId, Integer userId2, Integer agencyId2,
			String deleteMan) {
		try {
			List<ExpertAssist> expertAssists= expertAssistDao.getAllMessageContentList(userId,agencyId,userId2,agencyId2,deleteMan);
		        return expertAssists;
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public void updateMessageDelete(Integer userId, Integer agencyId,
			Integer userId2, Integer agencyId2, String deleteMan) {
		try {
			expertAssistDao.updateMessageDelete(userId,agencyId,userId2,agencyId2,deleteMan);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
