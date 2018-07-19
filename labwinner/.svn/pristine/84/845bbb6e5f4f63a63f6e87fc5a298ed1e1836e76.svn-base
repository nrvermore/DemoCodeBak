package com.labwinner.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.service.ProjectBasicInfoService;
import com.labwinner.vo.HomePageProVo;
import com.labwinner.dao.ProjectBasicInfoDao;
import com.labwinner.dao.ProjectDocumentsDao;
import com.labwinner.dao.ProjectNumberDao;
import com.labwinner.dao.ProjectPictureDao;
import com.labwinner.dao.ProjectPlanDao;
import com.labwinner.domain.ProjectBasicInfo;

/**
 * 项目基本信息Service
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:55:46
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@Service
public class ProjectBasicInfoServiceImpl implements ProjectBasicInfoService{
	private static final Logger log = LoggerFactory
			.getLogger(ProjectBasicInfoServiceImpl.class);
    
	@Autowired
	private  ProjectBasicInfoDao projectBasicInfoDao;
	@Autowired
	private ProjectPictureDao projectPictureDao;
	@Autowired
	private ProjectNumberDao projectNumberDao;
	@Autowired
	private ProjectPlanDao projectPlanDao;
	@Autowired
	private ProjectDocumentsDao projectDocumentsDao; 
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午5:59:46
	 */
	public void save(ProjectBasicInfo projectBasicInfo) {
		log.debug("saving ProjectBasicInfo instance");
		try {
			projectBasicInfoDao.save(projectBasicInfo);
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
		log.debug("deleting ProjectBasicInfo instance");
		try {
			projectPictureDao.deleteByPro(id);
			projectNumberDao.delete(id);
			projectPlanDao.delete(id);
			projectDocumentsDao.delete(id);
			projectBasicInfoDao.delete(id);
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
	public ProjectBasicInfo getById(Integer id) {
		log.debug("getting ProjectBasicInfo instance with id: " + id);
		try {
			ProjectBasicInfo instance =  projectBasicInfoDao.getById(id);
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
	public List<ProjectBasicInfo> getAll(Integer userId,String roleName) {
		log.debug("finding all ProjectBasicInfo instances");
		try {
			 return projectBasicInfoDao.getAll(userId, roleName);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
	
	@Override
	public List<ProjectBasicInfo> getAllPageable(Integer userId,String roleName,String keyword) {
	
		List<ProjectBasicInfo> projectBasicInfoList=projectBasicInfoDao.getAllPageable(userId, roleName, keyword);
		
		return projectBasicInfoList;
	}
	
	@Override
	public List<ProjectBasicInfo> getProReaPageable() {
		
		List<ProjectBasicInfo> projectBasicInfoList=projectBasicInfoDao.getProReaPageable();
		
		return projectBasicInfoList;
	}
	
	/**
	 * 修改对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:02:43
	 */
	public void update(ProjectBasicInfo projectBasicInfo) {
		log.debug("merging ProjectBasicInfo instance");
		try {
			 projectBasicInfoDao.update(projectBasicInfo);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}


	@Override
	public ProjectBasicInfo getProById(Integer id) {
		log.debug("getting ProjectBasicInfo instance with id: " + id);
		try {
			ProjectBasicInfo instance = (ProjectBasicInfo) projectBasicInfoDao.getProById(id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	@Override
	public List<Map<String, Object>> getArticleById(Integer id) {
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			list=projectBasicInfoDao.getArticleById(id);
		} catch (Exception e) {
			log.error("get failed", e);
			throw e;
		}
		return list;
	}
	@Override
	public List<HomePageProVo> getProAndReacALL(Integer id,String roleName) {
		List<HomePageProVo>  list=new ArrayList<HomePageProVo>();
		try {
			list=projectBasicInfoDao.getProAndReacALL(id,roleName);
		} catch (Exception e) {
			log.error("get failed", e);
			throw e;
		}
		return list;
	}
	@Override
	public void deleteKno(Integer proId, Integer classId, Integer knowId) {
		log.debug("deleting ProjectBasicInfo instance");
		try {
			projectBasicInfoDao.deleteKno(proId,classId,knowId);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	@Override
	public void updateProName(ProjectBasicInfo projectBasicInfo) {
		log.debug("merging ProjectBasicInfo instance");
		try {
			 projectBasicInfoDao.updateProName(projectBasicInfo);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	@Override
	public void updateProInt(ProjectBasicInfo projectBasicInfo) {
		log.debug("merging ProjectBasicInfo instance");
		try {
			 projectBasicInfoDao.updateProInt(projectBasicInfo);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	@Override
	public void updateProStatus(ProjectBasicInfo projectBasicInfo) {
		log.debug("merging ProjectBasicInfo instance");
		try {
			 projectBasicInfoDao.updateProStatus(projectBasicInfo);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	@Override
	public List<ProjectBasicInfo> getProNameAll() {
		log.debug("finding all ProjectBasicInfo instances");
		try {
			 return projectBasicInfoDao.getProNameAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@Override
	public List<ProjectBasicInfo> getShareProject(Integer userId) {
		log.debug("finding all ProjectBasicInfo instances");
		try {
			 return projectBasicInfoDao.getShareProject(userId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@Override
	public List<ProjectBasicInfo> getBykeyword(Integer userId, String roleName,
			String keyword, Integer endCount) {
		log.debug("finding all ProjectBasicInfo instances");
		try {
			 return projectBasicInfoDao.getBykeyword(userId,roleName,keyword,endCount);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@Override
	public List<ProjectBasicInfo> getUserList(Integer userId, String roleName,
			Integer endCount) {
		log.debug("finding all ProjectBasicInfo instances");
		try {
			 return projectBasicInfoDao.getUserList(userId,roleName,endCount);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@Override
	public ProjectBasicInfo getNumbers(Integer id) {
		log.debug("finding all ProjectBasicInfo instances");
		try {
			 return projectBasicInfoDao.getNumbers(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@Override
	public List<HomePageProVo> getProAndReacByNum(int userId, String roleName,
			Integer proId) {
		log.debug("finding all ProjectBasicInfo instances");
		try {
			 return projectBasicInfoDao.getProAndReacByNum(userId,roleName,proId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@Override
	public void updateProjectIntroduce(int proId, String projectIntroduce) {
		try {
			 projectBasicInfoDao.updateProjectIntroduce(proId,projectIntroduce);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		
	}
	@Override
	public int getCreaterIdByProId(int proId) {
		log.debug("finding all ProjectBasicInfo instances");
		try {
			 return projectBasicInfoDao.getCreaterIdByProId(proId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@Override
	public ProjectBasicInfo getProjectPlanTime(Integer id) {
		log.debug("finding all ProjectBasicInfo instances");
		try {
			 return projectBasicInfoDao.getProjectPlanTime(id);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	


}