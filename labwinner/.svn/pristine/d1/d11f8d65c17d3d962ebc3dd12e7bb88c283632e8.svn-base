package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.NoteDao;
import com.labwinner.domain.Note;
import com.labwinner.service.NoteService;

@Service
public class NoteServiceImpl implements NoteService{

	private static final Logger log = LoggerFactory
			.getLogger(NoteServiceImpl.class);
	@Autowired
	private NoteDao notoDao;
	
	@Override
	public void save(Note note) {
		log.debug("saving note instance");
		try {
			notoDao.save(note);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(Note note) {
		log.debug("saving note instance");
		try {
			notoDao.update(note);
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
			notoDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public Note getById(Integer id) {
		log.debug("saving note instance");
		try {
			return notoDao.getById(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<Note> getAll(Integer id) {
		try {
			return notoDao.getAll(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByReactionId(Integer id) {
		log.debug("saving note instance");
		try {
			notoDao.deleteByReactionId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public List<Integer> getByReactionIdList(Integer id) {
		log.debug("saving note instance");
		try {
			return notoDao.getByReactionIdList(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Note> getByProcessId(Integer id) {
		log.debug("saving note instance");
		try {
			return notoDao.getByProcessId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Note> getByReactionId(Integer id) {
		log.debug("saving note instance");
		try {
			return notoDao.getByReactionId(id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Note> getByName(String keyword, Integer id) {
		log.debug("saving note instance");
		try {
			return notoDao.getByName(keyword,id);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

}
