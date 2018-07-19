package com.labwinner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.TeamMoments;

public interface TeamMomentsService {
	
	public void save(TeamMoments teamMoments);
	
	public void delete(Integer id);
	
	public TeamMoments getById(Integer id);
	
	public List<TeamMoments> getAll(Integer userId,Integer startCount,Integer endCount);

}
