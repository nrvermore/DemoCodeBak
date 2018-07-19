package com.labwinner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.ProjectMoments;

public interface ProjectMomentsService {
	
	public void save(ProjectMoments projectMoments);
	
	public void delete(Integer id);
	
	public ProjectMoments getById(Integer id);
	
	public List<ProjectMoments> getAll(Integer userId,String roleName,Integer startCount,Integer endCount);

	public List<ProjectMoments> getAllByDay();
	
	public Integer getTotalCount(Integer userId);

}
