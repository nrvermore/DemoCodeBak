package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.JournalUserDao;
import com.labwinner.domain.JournalUser;
import com.labwinner.service.JournalUserService;

@Service
public class JournalUserServiceImpl implements JournalUserService{

	private static final Logger log = LoggerFactory
			.getLogger(JournalUserServiceImpl.class);
	@Autowired
	private JournalUserDao journalUserDao;
	
	@Override
	public void save(JournalUser journalUser) {
		log.debug("saving JournalArticle instance");
		try {
			journalUserDao.save(journalUser);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw new RuntimeException();
		}
		
	}

	@Override
	public void update(JournalUser journalUser) {
		log.debug("saving JournalArticle instance");
		try {
			journalUserDao.update(journalUser);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id,Integer classId) {
		log.debug("saving JournalArticle instance");
		try {
			journalUserDao.delete(id,classId);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<JournalUser> getById(Integer id,Integer classId) {
		log.debug("saving JournalArticle instance");
		try {
			log.debug("save successful");
			return journalUserDao.getById(id,classId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
