package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.ReactionDesignProcess;

public interface ReactionDesignProcessDao {
	
	public void save(ReactionDesignProcess reactionDesignProcess);
	
	public void update(ReactionDesignProcess reactionDesignProcess);
	
	public void delete(Integer id);
	
	public List<ReactionDesignProcess> getAll();
	
	public ReactionDesignProcess getById(Integer id);
	
	public List<ReactionDesignProcess> getByName(String name);

}
