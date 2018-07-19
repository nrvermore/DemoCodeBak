package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.AssistComment;

public interface AssistCommentDao {
	
	public void save(AssistComment assistComment);
	
	public void delete(Integer id);
	
	public void deleteById(Integer id);
	
	public void deleteByFirstId(Integer id);
	
	public Integer getNumById(Integer id);
	
	public List<AssistComment> getByPid(Integer id);
	
	public AssistComment getById(Integer id);
	
	public List<AssistComment> getFirstComments (Integer asistId);
	
	public List<AssistComment> getComments(Integer id);

	public AssistComment getLastAssistComment(@Param("userId")Integer userId, @Param("agencyId")Integer agencyId);

}
