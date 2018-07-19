package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.ReactionDesignParameter;

public interface ReactionDesignParameterService {
	
public void save(ReactionDesignParameter reactionDesignParameter);
	
	public void update(ReactionDesignParameter reactionDesignParameter);
	
	public void delete(Integer id);
	
	public List<ReactionDesignParameter> getAll();
	
	public ReactionDesignParameter getById(Integer id);
	
	public List<ReactionDesignParameter> getByName(String name);

}
