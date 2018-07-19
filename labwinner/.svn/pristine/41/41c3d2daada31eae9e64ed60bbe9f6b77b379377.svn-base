package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ReactionDesignDao;
import com.labwinner.dao.ReactionRecordDao;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionRecord;
import com.labwinner.service.ReactionRecordService;

@Service
public class ReactionRecordServiceImpl implements ReactionRecordService{
	
	private static final Logger log = LoggerFactory
			.getLogger(ReactionRecordServiceImpl.class);
	
	@Autowired
	private ReactionRecordDao reactionRecordDao;
	
	@Autowired
	private ReactionDesignDao reactionDesignDao;

	@Override
	public void save(ReactionRecord reactionRecord) {
		log.debug("saving ReactionRecord instance");
		try {
			reactionRecordDao.save(reactionRecord);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(ReactionRecord reactionRecord) {
		log.debug("update ReactionRecord instance");
		try {
			reactionRecordDao.update(reactionRecord);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("deleting ReactionRecord instance");
		try {
			ReactionDesign reactionDesign=reactionDesignDao.getReactionRecordsById(id);
			List<ReactionRecord> reactionRecords=reactionDesign.getReactionRecords();
			if(reactionRecords!=null){
				for (ReactionRecord reactionRecord : reactionRecords) {
					reactionRecordDao.delete(id);	
				}
			}
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public ReactionRecord getById(Integer id) {
		log.debug("getById ReactionRecord instance");
		try {
			log.debug("getById successful");
			return reactionRecordDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionRecord> getAll() {
		log.debug("update ReactionRecord instance");
		try {
			log.debug("getAll successful");
			return reactionRecordDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("getAll failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionRecord> getByName(String name) {
		log.debug("update ReactionRecord instance");
		try {
			log.debug("getByName successful");
			List<ReactionRecord> reactionRecordList =reactionRecordDao.getByName(name);
			return  reactionRecordList;
			
		} catch (RuntimeException re) {
			log.error("getByName failed", re);
			throw re;
		}
	}

	@Override
	public void updateRecord(ReactionRecord reactionRecord) {
		log.debug("update ReactionRecord instance");
		try {
			reactionRecordDao.updateRecord(reactionRecord);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
		
	}

	@Override
	public List<ReactionRecord> getByDesignId(Integer id) {
		log.debug("update ReactionRecord instance");
		try {
			log.debug("getByName successful");
			List<ReactionRecord> reactionRecordList =reactionRecordDao.getByDesignId(id);
			return  reactionRecordList;
			
		} catch (RuntimeException re) {
			log.error("getByName failed", re);
			throw re;
		}
	}

}
