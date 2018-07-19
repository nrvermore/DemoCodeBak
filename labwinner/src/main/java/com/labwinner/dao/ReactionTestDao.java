package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.ReactionTest;

public interface ReactionTestDao {

	public void save(ReactionTest ReactionTest);
	
	public void update(ReactionTest ReactionTest);
	
	public void delete(Integer id);
	
	public List<Integer> getTestIds(Integer id);
	
	public void deleteByReactionId(Integer id);
	
	public List<ReactionTest> getByKeyword(String keyword);
	
	public List<ReactionTest> getByReactionId(Integer id);
	
	public List<ReactionTest> getAll();
	
	public ReactionTest getById(Integer id);
	
	public List<ReactionTest> getUserList(Integer userId);
	
	public List<ReactionTest> getUserListByKeyword(@Param("keyword")String keyword,@Param("userId")Integer userId);
}
