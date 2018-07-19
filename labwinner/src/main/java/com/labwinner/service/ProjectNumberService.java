package com.labwinner.service;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.ProjectNumber;
/**
 * 项目成员Service接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface ProjectNumberService {
	
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:19:42
	 */
	public void save(ProjectNumber projectNumber) ;
    
	
	/**
	 * 删除对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:05
	 */
	public void delete(java.lang.Integer id) ;
     
	
	/**
	 * 根据id查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:29
	 */
	public ProjectNumber getById(Integer id);
	
	/**
     * 显示当前用户所在项目的项目成员
     * @Description TODO
     * @author xux
     * @version V1.0
     * @date 2017年5月18日 下午4:20:54
     */
	public List<ProjectNumber> getPrincipals(Integer id);
	
	 /**
     * 显示当前用户所在项目
     * @Description TODO
     * @author xux
     * @version V1.0
     * @date 2017年5月18日 下午4:20:54
     */
	public List<ProjectNumber> getProjects(Integer userId);
	
     /**
      * 显示所有对象
      * @Description TODO
      * @author suhg
      * @version V1.0
      * @date 2017年5月18日 下午4:20:54
      */
	public List<ProjectNumber> getAll(Integer id);
	
	 /**
     * 显示所有对象
     * @Description TODO
     * @author xux
     * @version V1.0
     * @date 2017年5月18日 下午4:20:54
     */
	public List<ProjectNumber> getAllProjects();
	
	
	public List<ProjectNumber> getByProId(Integer id);
	
	
	/**
	 * 修改对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:50:32
	 */
	public void update(ProjectNumber projectNumber);
	
	/**
	 * 删除成员修改flag状态
	 * @Description TODO
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 下午4:50:32
	 */
	public void updateByDelete(Integer proNumberId,Integer flag,Integer roleId);
	
	public ProjectNumber getProjectNumber(Integer roleId,Integer userId,Integer flag,Integer proId);
	
	/**
	 * 删除对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:05
	 */
	public void deleteById(Integer id) ;


	public List<ProjectNumber> getAllNumberByProId(Integer projectId);


	public ProjectNumber getByUser(int managerId, int proId);
	
}