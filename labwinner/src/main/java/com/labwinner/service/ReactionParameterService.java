package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.ReactionParameter;

public interface ReactionParameterService {

	public void save(ReactionParameter reactionParameter);
	
	public void update(ReactionParameter reactionParameter);
	
	public void delete(Integer id);
	
	public List<ReactionParameter> getById(Integer id);
	
	public List<ReactionParameter> getAll();
}
