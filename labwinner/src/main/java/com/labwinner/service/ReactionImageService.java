package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.ReactionImage;

public interface ReactionImageService {

	public void save(ReactionImage reactionImage);
	
	public void update(ReactionImage reactionImage);
	
	public void delete(Integer id);
	
	public List<String> getById(Integer id);
	
	public void deleteByImg(String img);
	
	public void batchRemove(List<Integer> ids);
	
	public void deleteById(Integer id);
	
	public String getByImageId(Integer id);
}
