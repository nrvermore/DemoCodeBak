package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.TeamMomentsLike;

public interface TeamMomentsLikeDao {
	
	public void save(TeamMomentsLike teamMomentsLike);
	
	public void delete(Integer id);
	
	public void deleteByMomentsId(@Param("momentsInfoId")Integer momentsInfoId,@Param("momentsType")Integer momentsType);
	
	public TeamMomentsLike getById(Integer id);
	
	public List<TeamMomentsLike> getAll();
	
	public List<TeamMomentsLike> getByMomentsId(@Param("momentsType")Integer momentsType,@Param("momentsInfoId")Integer momentsInfoId);

}
