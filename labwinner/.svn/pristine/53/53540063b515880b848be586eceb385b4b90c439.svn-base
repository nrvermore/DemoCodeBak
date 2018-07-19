package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ReactionDesignDao;
import com.labwinner.dao.ReactionDesignParameterDao;
import com.labwinner.dao.ReactionDesignProcessDao;
import com.labwinner.domain.DesignDosage;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionDesignParameter;
import com.labwinner.domain.ReactionDesignProcess;
import com.labwinner.service.ReactionDesignProcessService;

@Service
public class ReactionDesignProcessServiceImpl implements ReactionDesignProcessService{

	private static final Logger log = LoggerFactory
			.getLogger(ReactionDesignProcessServiceImpl.class);
	
	@Autowired
	private ReactionDesignProcessDao reactionDesignProcessDao;

	@Autowired
	private ReactionDesignParameterDao reactionDesignParameterDao;
	
	@Autowired
	private ReactionDesignDao reactionDesignDao;
	
	
	@Override
	public void save(ReactionDesignProcess reactionDesignProcess) {
		log.debug("saving ReactionDesignProcess instance");
		try {
			reactionDesignProcessDao.save(reactionDesignProcess);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(ReactionDesignProcess reactionDesignProcess) {
		log.debug("update ReactionDesignProcess instance");
		try {
			reactionDesignProcessDao.update(reactionDesignProcess);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("deleting ReactionDesignProcess instance");
		try {
			ReactionDesign reactionDesign=reactionDesignDao.getById(id);
			List<ReactionDesignProcess> reactionDesignProcesses=reactionDesign.getReactionDesignProcesses();
			if(reactionDesignProcesses!=null){
			   for (ReactionDesignProcess reactionDesignProcess : reactionDesignProcesses) {
				   Integer id2=reactionDesignProcess.getReactionDesignProcessId();
				List<ReactionDesignParameter> reactionDesignParameters=reactionDesignProcess.getReactionDesignParameters();
				 if(reactionDesignParameters!=null){
					 for (ReactionDesignParameter reactionDesignParameter : reactionDesignParameters) {
						 reactionDesignParameterDao.delete(id2);
					}
				 }
				 reactionDesignProcessDao.delete(id);
			}
			
			}
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesignProcess> getAll() {
		log.debug("update ReactionDesignProcess instance");
		try {
			log.debug("getAll successful");
			return reactionDesignProcessDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public ReactionDesignProcess getById(Integer id) {
		log.debug("getById ReactionDesignProcess instance");
		try {
			log.debug("getById successful");
			return reactionDesignProcessDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesignProcess> getByName(String name) {
		log.debug("update ReactionDesignProcess instance");
		try {
			log.debug("getById successful");
			List<ReactionDesignProcess> reactionRecordList =reactionDesignProcessDao.getByName(name);
			return  reactionRecordList;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}
