package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.TeamMoments;

public interface TeamMomentsDao {

	public void save(TeamMoments teamMoments);
	
	public void delete(Integer id);
	
	public TeamMoments getById(Integer id);
	
	public List<TeamMoments> getAll(@Param("userId")Integer userId,@Param("startCount")Integer startCount,@Param("endCount")Integer endCount);
}
