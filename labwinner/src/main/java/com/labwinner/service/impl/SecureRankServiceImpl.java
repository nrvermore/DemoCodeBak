package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SecureRankDao;
import com.labwinner.domain.ModifyType;
import com.labwinner.domain.SecureRank;
import com.labwinner.service.SecureRankService;

@Service
public class SecureRankServiceImpl implements SecureRankService{

	private static final Logger log = LoggerFactory
			.getLogger(SecureRankServiceImpl.class);
	
	@Autowired 
	private SecureRankDao secureRankDao;
	
	public void save(SecureRank secureRank) {
		log.debug("saving Measurement instance");
		try {
			secureRankDao.save(secureRank);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public List<SecureRank> getAll() {
		log.debug("finding all Measurement instances");
		try {
			List<SecureRank> secureRankList= secureRankDao.getAll();
			return secureRankList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Integer id) {
		secureRankDao.delete(id);
	}

	@Override
	public SecureRank getById(Integer id) {
		// TODO Auto-generated method stub
		return secureRankDao.getById(id);
	}

	@Override
	public void update(SecureRank secureRank) {
		secureRankDao.update(secureRank);
		
	}

	@Override
	public List<SecureRank> getAllPageable(String keyword) {
		log.debug("finding all Measurement instances");
		try {
			List<SecureRank> secureRankList= secureRankDao.getAllPageable(keyword);
			return secureRankList;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}
