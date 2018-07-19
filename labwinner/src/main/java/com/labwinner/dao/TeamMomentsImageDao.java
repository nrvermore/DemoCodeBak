package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.TeamMomentsImage;

public interface TeamMomentsImageDao {

	public void save(TeamMomentsImage teamMomentsImage);
	
	public void delete(Integer id);
	
	public void deleteByMomentsId(@Param("momentsInfoId")Integer momentsInfoId,@Param("momentsType")Integer momentsType);
	
	public TeamMomentsImage getById(Integer id);
	
	public List<TeamMomentsImage> getAll();

}
