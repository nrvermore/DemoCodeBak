package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.QuestionsDao;
import com.labwinner.domain.Questions;
import com.labwinner.service.QuestionsService;


/**
 * 设备类型Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午5:25:29
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class QuestionsServiceImpl implements QuestionsService{
	private static final Logger log = LoggerFactory
			.getLogger(QuestionsServiceImpl.class);
	
       @Autowired
       private QuestionsDao questionsDao;
       
       
	public void save(Questions questions) {
		log.debug("saving Questions instance");
		try {
			 questionsDao.save(questions);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Integer id) {
		log.debug("deleting Questions instance");
		try {
			questionsDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Questions getById(java.lang.Integer id) {
		log.debug("getting Questions instance with id: " + id);
		try {
			Questions questions = questionsDao.getById(id);
			return questions;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	
	public List<Questions> getAll() {
		log.debug("finding all Questions instances");
		try {
			return questionsDao.getAll(); 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void update(Questions questions) {
		log.debug("merging Questions instance");
		try {
			questionsDao.update(questions);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public List<Questions> getAllPageable(String keyword) {
		log.debug("finding all Questions instances");
		try {
			return questionsDao.getAllPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Questions> getAllName() {
		log.debug("finding all Questions instances");
		try {
			return questionsDao.getAllName(); 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void questions(Questions questions) {
		log.debug("finding all Questions instances");
		try {
			questionsDao.questions(questions); 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Questions> getAllDesc() {
		log.debug("finding all Questions instances");
		try {
			return questionsDao.getAllDesc(); 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Questions> getAllDescPageable(String keyword) {
		log.debug("finding all Questions instances");
		try {
			return questionsDao.getAllDescPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Questions> getAllDescQuestions() {
		log.debug("finding all Questions instances");
		try {
			return questionsDao.getAllDescQuestions(); 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


}