package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.ProjectMoments;

public interface ProjectMomentsDao {
	
	public void save(ProjectMoments projectMoments);
	
	public void delete(Integer id);
	
	public ProjectMoments getById(Integer id);
	
	public List<ProjectMoments> getAll(@Param("userId")Integer userId,@Param("roleName")String roleName,@Param("startCount")Integer startCount,@Param("endCount")Integer endCount);

	public List<ProjectMoments> getAllByDay();
	
	public Integer getTotalCount(Integer userId);

}
