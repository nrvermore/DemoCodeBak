package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.ReactionImage;

public interface ReactionImageDao {
	
	public void save(ReactionImage reactionImage);
	
	public void update(ReactionImage reactionImage);
	
	public void delete(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public List<String> getById(Integer id);
	
	public void deleteByImg(String img);
	
	public void deleteById(Integer id);
	
	public String getByImageId(Integer id);

}
