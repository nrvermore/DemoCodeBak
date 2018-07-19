package com.labwinner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.TeamMomentsLike;

public interface TeamMomentsLikeService {

	public void save(TeamMomentsLike teamMomentsLike);
	
	public void delete(Integer id);
	
	public void deleteByMomentsId(Integer momentsInfoId,Integer momentsType);
	
	public TeamMomentsLike getById(Integer id);
	
	public List<TeamMomentsLike> getAll();
	
	public List<TeamMomentsLike> getByMomentsId(Integer momentsType,Integer momentsInfoId);
}
