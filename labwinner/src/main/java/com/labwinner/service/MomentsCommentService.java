package com.labwinner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.TeamProjMomentsComment;

public interface MomentsCommentService {

	public void save(TeamProjMomentsComment momentsComment);
	
	public void delete(Integer id);
	
	public void deleteByMomentsId(Integer momentsInfoId,Integer momentsType);
	
	public TeamProjMomentsComment getById(Integer id);
	
	public List<TeamProjMomentsComment> getAll();
	
	public List<TeamProjMomentsComment> getByMomentsId(Integer momentsType,Integer momentsInfoId);
}
