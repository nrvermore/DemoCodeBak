package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.ReactionDesignDao;
import com.labwinner.dao.ReactionDesignSolutionDao;
import com.labwinner.dao.SolutionDesignDosageDao;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionDesignSolution;
import com.labwinner.domain.SolutionDesignDosage;
import com.labwinner.service.ReactionDesignSolutionService;

@Service
public class ReactionDesignSolutionServiceImpl implements ReactionDesignSolutionService{

	private static final Logger log = LoggerFactory
			.getLogger(ReactionDesignSolutionServiceImpl.class);
	
	@Autowired
	private ReactionDesignSolutionDao reactionDesignSolutionDao;
	
	@Autowired
	private ReactionDesignDao reactionDesignDao;
	
	@Autowired
	private SolutionDesignDosageDao solutionDesignDosageDao;

	@Override
	public void save(ReactionDesignSolution reactionDesignSolution) {
		log.debug("saving ReactionDesignSolution instance");
		try {
			reactionDesignSolutionDao.save(reactionDesignSolution);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(ReactionDesignSolution reactionDesignSolution) {
		log.debug("saving ReactionDesignSolution instance");
		try {
			reactionDesignSolutionDao.update(reactionDesignSolution);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("saving ReactionDesignSolution instance");
		try {
			ReactionDesign reactionDesign=reactionDesignDao.getSolutionById(id);
			if(reactionDesign!=null){
			List<ReactionDesignSolution> reactionDesignSolutions=reactionDesign.getReactionDesignSolutions();
			if(reactionDesignSolutions!=null){
				for (ReactionDesignSolution reactionDesignSolution : reactionDesignSolutions) {
					List<SolutionDesignDosage> solutionDesignDosages=reactionDesignSolution.getSolutionDesignDosages();
					if(solutionDesignDosages!=null){
						for (SolutionDesignDosage solutionDesignDosage : solutionDesignDosages) {
							Integer id2=reactionDesignSolution.getSolutionDesignId();
							solutionDesignDosageDao.delete(id2);
						}
					}
					reactionDesignSolutionDao.delete(id);
				}
			}
			}
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public ReactionDesignSolution getById(Integer id) {
		log.debug("saving ReactionDesignSolution instance");
		try {
			return reactionDesignSolutionDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
		
	}

	@Override
	public List<Integer> getSolutions() {
		log.debug("update ReactionDesignSolution instance");
		try {
			log.debug("getAll successful");
			return reactionDesignSolutionDao.getSolutions();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public List<ReactionDesignSolution> getAll() {
		log.debug("update ReactionDesignSolution instance");
		try {
			log.debug("getAll successful");
			return reactionDesignSolutionDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public List<ReactionDesignSolution> getByName(String name) {
		log.debug("update ReactionDesignSolution instance");
		try {
			log.debug("getAll successful");
			return reactionDesignSolutionDao.getByName(name);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void deleteSolutionDesignId(Integer id) {
		log.debug("saving ReactionDesignSolution instance");
	
			ReactionDesignSolution reactionDesignSolution=reactionDesignSolutionDao.getSolutionDesignId(id);
					List<SolutionDesignDosage> solutionDesignDosages=reactionDesignSolution.getSolutionDesignDosages();
					if(solutionDesignDosages!=null){
						for (SolutionDesignDosage solutionDesignDosage : solutionDesignDosages) {
							solutionDesignDosageDao.delete(id);
						}
					}
					reactionDesignSolutionDao.deleteSolutionDesignId(id);
				}
			
		@Override
		public ReactionDesignSolution getSolutionDesignId(Integer id) {
			log.debug("saving ReactionDesignSolution instance");
			try {
				return reactionDesignSolutionDao.getSolutionDesignId(id); 
				
			} catch (RuntimeException re) {
				log.error("save failed", re);
				throw re;
			}
		}
		
	}

