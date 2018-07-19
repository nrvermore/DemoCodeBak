package com.labwinner.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ReactionDesignParameterDao;
import com.labwinner.dao.ReactionProcessDao;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.service.ReactionProcessService;

@Service
public class ReactionProcessServiceImpl implements ReactionProcessService{

	private static final Logger log = LoggerFactory
			.getLogger(ReactionProcessServiceImpl.class);
	@Autowired
	private ReactionProcessDao reactionProcessDao;
	
	@Autowired
	private ReactionDesignParameterDao reactionDesignParameterDao;
	
	@Override
	public void save(ReactionProcess reactionProcess) {
		log.debug("saving Inventory instance");
		try {
			reactionProcessDao.save(reactionProcess);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(ReactionProcess reactionProcess) {
		log.debug("saving Inventory instance");
		try {
			reactionProcessDao.update(reactionProcess);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving Inventory instance");
		try {
			reactionProcessDao.delete(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public ReactionProcess getById(Integer id) {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionProcessDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionProcess> getAll() {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionProcessDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<Integer> getByReactionId(Integer id) {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionProcessDao.getByReactionId(id);
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByReactionId(Integer id) {
		log.debug("saving Inventory instance");
		try {
			reactionProcessDao.deleteByReactionId(id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void updateProcessStatus(Date date,Integer process, Integer reactionId,
			Integer statusId) {
		log.debug("saving Inventory instance");
		try {
			reactionProcessDao.updateProcessStatus(date,process,reactionId,statusId);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void updateProcess(String reactionProcess, Integer id) {
		log.debug("saving Inventory instance");
		try {
			reactionProcessDao.updateProcess(reactionProcess,id);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}
	
	@Override
	public List<ReactionProcess> getProcessByReactionId(int reactionId) {
		log.debug("getProcessByReactionId Inventory instance");
		try {
			log.debug("getProcessByReactionId successful");
			return reactionProcessDao.getProcessByReactionId(reactionId);
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public ReactionProcess getByProcessId(Integer id) {
		log.debug("saving Inventory instance");
		try {
			log.debug("save successful");
			return reactionProcessDao.getByProcessId(id);
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void updateProcessNumber(ReactionProcess reactionProcess) {
		log.debug("saving Inventory instance");
		try {
			reactionProcessDao.updateProcessNumber(reactionProcess);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void updateOneProcess(ReactionProcess reactionProcess) {
		log.debug("saving Inventory instance");
		try {
			reactionProcessDao.updateOneProcess(reactionProcess);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}



}
