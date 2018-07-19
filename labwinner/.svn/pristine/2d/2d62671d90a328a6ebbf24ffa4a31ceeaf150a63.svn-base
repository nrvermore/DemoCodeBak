package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.NoteAssistantDao;
import com.labwinner.domain.NoteAssistant;
import com.labwinner.service.NoteAssistantService;

@Service
public class NoteAssistantServiceImpl implements NoteAssistantService{

	private static final Logger log = LoggerFactory
			.getLogger(NoteAssistantServiceImpl.class);
	@Autowired
	private NoteAssistantDao noteAssistantDao;
	
	@Override
	public void save(NoteAssistant noteAssistant) {
		log.debug("saving note instance");
		try {
			noteAssistantDao.save(noteAssistant);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving note instance");
		try {
			noteAssistantDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(NoteAssistant noteAssistant) {
		log.debug("saving note instance");
		try {
			noteAssistantDao.update(noteAssistant);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<NoteAssistant> getById(Integer id) {
		log.debug("saving note instance");
		try {
			return noteAssistantDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void batchRemove(List<Integer> ids) {
		log.debug("saving note instance");
		try {
			noteAssistantDao.batchRemove(ids);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

}
