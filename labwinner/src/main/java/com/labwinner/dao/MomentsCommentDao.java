package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.TeamMomentsLike;
import com.labwinner.domain.TeamProjMomentsComment;

public interface MomentsCommentDao {

	public void save(TeamProjMomentsComment momentsComment);
	
	public void delete(Integer id);
	
	public TeamProjMomentsComment getById(Integer id);
	
	public void deleteByMomentsId(@Param("momentsInfoId")Integer momentsInfoId,@Param("momentsType")Integer momentsType);
	
	public List<TeamProjMomentsComment> getAll();
	
	public List<TeamProjMomentsComment> getByMomentsId(@Param("momentsType")Integer momentsType,@Param("momentsInfoId")Integer momentsInfoId);
}
