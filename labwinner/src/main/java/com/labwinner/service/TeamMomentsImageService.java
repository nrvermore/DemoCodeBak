package com.labwinner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.TeamMomentsImage;

public interface TeamMomentsImageService {

	public void save(TeamMomentsImage teamMomentsImage);
	
	public void delete(Integer id);
	
	public void deleteByMomentsId(Integer momentsInfoId,Integer momentsType);
	
	public TeamMomentsImage getById(Integer id);
	
	public List<TeamMomentsImage> getAll();
}
