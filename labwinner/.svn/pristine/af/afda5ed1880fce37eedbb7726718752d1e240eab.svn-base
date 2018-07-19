package com.labwinner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Analytics;

public interface AnalyticService {

	public void save(Analytics analytics);
	
	public void update(Analytics analytics);
	
	public void deleteByReactionId(Integer id);
	
	public void delete(Integer id);
	
	public List<Integer> getAnalyticIds(Integer id);
	
	public List<Analytics> getByKeyword(String keyword);
	
	public List<Analytics> getByReactionId(Integer id);
	
	public List<Analytics> getByProcessId(Integer id);
	
	public Analytics getById(Integer id);
	
	public List<Analytics> getAll();
	
	public List<Analytics> getUserList(Integer userId);
	
	public List<Analytics> getUserListByKeyword(String keyword,Integer userId);
}
