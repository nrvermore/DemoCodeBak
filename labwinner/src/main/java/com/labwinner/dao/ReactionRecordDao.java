package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.ReactionRecord;

public interface ReactionRecordDao {
	
	public void save(ReactionRecord reactionRecord);
	
	public void update(ReactionRecord reactionRecord);
	
	public void updateRecord(ReactionRecord reactionRecord);
	
	public void delete(Integer id);
	
	public ReactionRecord getById(Integer id);
	
	public List<ReactionRecord> getAll();
	
	public List<ReactionRecord> getByName(String name);
	
	public List<ReactionRecord> getByDesignId(Integer id);

}
