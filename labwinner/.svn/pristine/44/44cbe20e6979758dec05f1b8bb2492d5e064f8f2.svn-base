package com.labwinner.service.impl;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.DesignTechnologyDao;
import com.labwinner.dao.DesignTechnologyDosageDao;
import com.labwinner.dao.DesignTechnologyProcessDao;
import com.labwinner.dao.DesignTechnologyRelationDao;
import com.labwinner.domain.DesignTechnology;
import com.labwinner.domain.DesignTechnologyDosage;
import com.labwinner.domain.DesignTechnologyProcess;
import com.labwinner.domain.DesignTechnologyRelation;
import com.labwinner.service.DesignTechnologyRelationService;
/**
 * 设计工艺关系Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class DesignTechnologyRelationServiceImpl implements DesignTechnologyRelationService{
	private static final Logger log = LoggerFactory
			.getLogger(DesignTechnologyRelationServiceImpl.class);
    
	@Autowired
	private DesignTechnologyRelationDao designTechnologyRelationDao;
    
	@Autowired
	private DesignTechnologyDao designTechnologyDao;
	
	@Autowired
	private DesignTechnologyDosageDao designTechnologyDosageDao;
	
	@Autowired
	private DesignTechnologyProcessDao  designTechnologyProcessDao;
	
	
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(DesignTechnologyRelation designTechnologyRelation) {
		log.debug("saving DesignTechnologyRelation instance");
		try {
			designTechnologyRelationDao.save(designTechnologyRelation);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
     
	
	/**
	 * 删除对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:00:30
	 */
	public void delete(Integer id) {
		log.debug("deleting DesignTechnologyRelation instance");
		try {
			List<DesignTechnologyRelation> designTechnologyRelations=designTechnologyRelationDao.getById(id);
		if(designTechnologyRelations!=null){
			for (DesignTechnologyRelation designTechnologyRelation : designTechnologyRelations) {
			DesignTechnology designTechnology=designTechnologyRelation.getDesignTechnology();
			    if(designTechnology!=null){
			Integer id2=designTechnology.getTechnologyId();
			List<DesignTechnologyProcess> designTechnologyProcesses=designTechnology.getDesignTechnologyProcesses();
			if(designTechnologyProcesses!=null){
				for (DesignTechnologyProcess designTechnologyProcess : designTechnologyProcesses) {
					Integer id3=designTechnologyProcess.getProcessId();
					List<DesignTechnologyDosage> designTechnologyDosages=designTechnologyProcess.getDesignTechnologyDosages();
					  if(designTechnologyDosages!=null){
						  for (DesignTechnologyDosage designTechnologyDosage : designTechnologyDosages) {
								designTechnologyDosageDao.delete(id3);
							}
					  }
					designTechnologyProcessDao.delete(id2);
				}
				designTechnologyDao.delete(id2);
				designTechnologyRelationDao.delete(id);
			    }
			  }
			}
		}
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
     
    
	/**
	 * 根据id号查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:00:58
	 */
	public List<DesignTechnologyRelation> getById(Integer id) {
		log.debug("getting DesignTechnologyRelation instance with id: " + id);
		try {
			List<DesignTechnologyRelation> instance = designTechnologyRelationDao.getById(id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * 显示所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:02:18
	 */
	public List<DesignTechnologyRelation> getAll() {
		log.debug("finding all DesignTechnologyRelation instances");
		try {
			 return designTechnologyRelationDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
	/**
	 * 修改对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:02:43
	 */
	public void update(DesignTechnologyRelation designTechnologyRelation) {
		log.debug("merging DesignTechnologyRelation instance");
		try {
			 designTechnologyRelationDao.update(designTechnologyRelation);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public List<DesignTechnologyRelation> getAllPageable(String keyword) {
		log.debug("finding all DesignTechnologyRelation instances");
		try {
			 return designTechnologyRelationDao.getAllPageable(keyword);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}


	@Override
	public void deleteByTechnologyId(Integer id) {
		log.debug("deleting DesignTechnologyRelation instance");
			
			try {
				List<DesignTechnologyRelation> designTechnologyRelations=designTechnologyRelationDao.getByTechnologyId(id);
				if(designTechnologyRelations!=null){
					for (DesignTechnologyRelation designTechnologyRelation : designTechnologyRelations) {
					DesignTechnology designTechnology=designTechnologyRelation.getDesignTechnology();
				      if(designTechnology!=null){
				    	  List<DesignTechnologyProcess> designTechnologyProcesses=designTechnology.getDesignTechnologyProcesses();
							if(designTechnologyProcesses!=null){
							for (DesignTechnologyProcess designTechnologyProcess : designTechnologyProcesses) {
								Integer id2=designTechnologyProcess.getProcessId();
								List<DesignTechnologyDosage> designTechnologyDosages=designTechnologyProcess.getDesignTechnologyDosages();
								  if(designTechnologyDosages!=null){
									  for (DesignTechnologyDosage designTechnologyDosage : designTechnologyDosages) {
											designTechnologyDosageDao.delete(id2);
										}
								  }
								designTechnologyProcessDao.delete(id);
							}
							designTechnologyDao.delete(id);
							designTechnologyRelationDao.deleteByTechnologyId(id);
							}
				      }
				}
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public List<DesignTechnologyRelation> getByTechnologyId(Integer id) {
		log.debug("getting DesignTechnologyRelation instance with id: " + id);
		try {
			List<DesignTechnologyRelation> instance = designTechnologyRelationDao.getByTechnologyId(id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
}