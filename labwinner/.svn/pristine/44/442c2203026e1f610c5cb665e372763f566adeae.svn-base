package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DesignDosageDao;
import com.labwinner.dao.ReactionDesignChemicalDao;
import com.labwinner.dao.ReactionDesignDao;
import com.labwinner.domain.DesignDosage;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionDesignChemical;
import com.labwinner.service.ReactionDesignChemicalService;

@Service
public class ReactionDesignChemicalServiceImpl implements ReactionDesignChemicalService{

	private static final Logger log = LoggerFactory
			.getLogger(ReactionDesignChemicalServiceImpl.class);
	
	@Autowired
	private ReactionDesignChemicalDao reactionDesignChemicalDao;
	
	@Autowired
	private DesignDosageDao designDosageDao;
	
	@Autowired
	private ReactionDesignDao reactionDesignDao;
	
	@Override
	public void save(ReactionDesignChemical reactionDesignChemical) {
		log.debug("saving ReactionDesignChemical instance");
		try {
			reactionDesignChemicalDao.save(reactionDesignChemical);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}

	@Override
	public void update(ReactionDesignChemical reactionDesignChemical) {
		log.debug("update ReactionDesignChemical instance");
		try {
			reactionDesignChemicalDao.update(reactionDesignChemical);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		
	}

	@Override
	public void delete(Integer id) {
		log.debug("deleting ReactionDesignChemical instance");
		try {
			ReactionDesign reactionDesign=reactionDesignDao.getChemicals(id);
			
			if(reactionDesign!=null){
			List<ReactionDesignChemical> reactionDesignChemicals=reactionDesign.getReactionDesignChemicals();
			if(reactionDesignChemicals!=null){
				for (ReactionDesignChemical reactionDesignChemical : reactionDesignChemicals) {
					Integer id2=reactionDesignChemical.getDesignChemicalId();
					List<DesignDosage> designDosages=reactionDesignChemical.getDesignDosages();
					if(designDosages!=null){
						for (DesignDosage designDosage : designDosages) {
							designDosageDao.delete(id2);
						}
					}
					reactionDesignChemicalDao.delete(id);
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
	public ReactionDesignChemical getById(Integer id) {
		log.debug("getById ReactionDesignChemical instance");
		try {
			log.debug("getById successful");
			return reactionDesignChemicalDao.getById(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesignChemical> getAll() {
		log.debug("update ReactionDesignChemical instance");
		try {
			log.debug("getAll successful");
			return reactionDesignChemicalDao.getAll();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<ReactionDesignChemical> getByName(String name) {
		log.debug("update ReactionDesignChemical instance");
		try {
			log.debug("getById successful");
			List<ReactionDesignChemical> reactionDesignChemicals =reactionDesignChemicalDao.getByName(name);
			return  reactionDesignChemicals;
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Integer> getInventorys() {
		log.debug("update ReactionDesignChemical instance");
		try {
			log.debug("getAll successful");
			return reactionDesignChemicalDao.getInventorys();
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public void deleteByDesignChemicalId(Integer id) {
		log.debug("deleting ReactionDesignChemical instance");
		
			ReactionDesignChemical reactionDesignChemical=reactionDesignChemicalDao.getByDesignChemicalId(id);
					List<DesignDosage> designDosages=reactionDesignChemical.getDesignDosages();
					if(designDosages!=null){
						for (DesignDosage designDosage : designDosages) {
							designDosageDao.delete(id);
						}
					}
					reactionDesignChemicalDao.deleteByDesignChemicalId(id);
				}

	@Override
	public ReactionDesignChemical getByDesignChemicalId(Integer id) {
		log.debug("getById ReactionDesignChemical instance");
		try {
			log.debug("getById successful");
			return reactionDesignChemicalDao.getByDesignChemicalId(id);
			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
		
			
		
	}
