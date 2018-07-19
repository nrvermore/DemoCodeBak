package com.labwinner.service;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.vo.HomePageProVo;
/**
 * 项目基本信息Service接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface ProjectBasicInfoService {
	
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:19:42
	 */
	public void save(ProjectBasicInfo projectBasicInfo) ;
    
	
	/**
	 * 删除对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:05
	 */
	public void delete(Integer id) ;
     
	
	/**
	 * 根据id查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:29
	 */
	public ProjectBasicInfo getById(Integer id);
	
	/**
	 * 根据id查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:29
	 */
	public ProjectBasicInfo getNumbers(Integer id);
	
	/**
	 * 根据id查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:29
	 */
	public ProjectBasicInfo getProById(Integer id);
   
	/**
	 * 根据项目id获取该项目的项目计划开始时间已经结束时间
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public ProjectBasicInfo getProjectPlanTime(Integer id);
	
	
	/**
	 * 显示所有对象名称
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<ProjectBasicInfo> getProNameAll();
	
	/**
	 * 根据项目试验分页
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<ProjectBasicInfo> getProReaPageable();
	
	/**
	 * 修改对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:50:32
	 */
	public List<ProjectBasicInfo> getShareProject(Integer userId);
	
	public void update(ProjectBasicInfo projectBasicInfo);

	public void updateProName(ProjectBasicInfo projectBasicInfo);
	
	public void updateProInt(ProjectBasicInfo projectBasicInfo);
	
	public void updateProStatus(ProjectBasicInfo projectBasicInfo);
	
	public List<Map<String, Object>> getArticleById(Integer id);


	public List<HomePageProVo> getProAndReacALL(Integer id,String roleName);


	public void deleteKno(Integer proId, Integer classId, Integer knowId);


	List<ProjectBasicInfo> getAllPageable(Integer userId, String roleName,String keyword);
	
	List<ProjectBasicInfo> getAll(Integer userId, String roleName);
	
	/**
	 * App根据关键字查询对象
	 * @Description TODO
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<ProjectBasicInfo> getBykeyword(Integer userId,String roleName,String keyword,Integer endCount);
	
	/**
	 * App根据项目列表
	 * @Description TODO
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<ProjectBasicInfo> getUserList(Integer userId,String roleName,Integer endCount);


	public List<HomePageProVo> getProAndReacByNum(int userId, String roleName,
			Integer proId);


	public void updateProjectIntroduce(int proId, String projectIntroduce);


	public int getCreaterIdByProId(int proId);
	
	
}