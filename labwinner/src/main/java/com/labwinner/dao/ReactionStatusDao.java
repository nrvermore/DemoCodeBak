package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.ModifyCode;
import com.labwinner.domain.ReactionStatus;

public interface ReactionStatusDao {
	
	public void save(ReactionStatus reactionStatus);
	
	public List<ReactionStatus> getAll();
	
	public List<ReactionStatus> getByType(Integer id);

	public void update(ReactionStatus reactionStatus);
	
	public void delete(Integer id);

	public ReactionStatus getById(Integer id);

}
