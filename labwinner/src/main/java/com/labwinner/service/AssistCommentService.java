package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.AssistComment;

public interface AssistCommentService {
	
	public void save(AssistComment assistComment);
	
	public void delete(Integer id);
	
	public void deleteById(Integer id);
	
	public void deleteByFirstId(Integer id);
	
	public Integer getNumById(Integer id);
	
	public List<AssistComment> getByPid(Integer id);
	
	public AssistComment getById(Integer id);
	
	public List<AssistComment> getFirstComments (Integer asistId);
	
	public List<AssistComment> getTree(Integer id);
	
	public List<AssistComment> getComments(Integer id);

	public AssistComment getLastAssistComment(Integer userId, Integer agencyId);


}
