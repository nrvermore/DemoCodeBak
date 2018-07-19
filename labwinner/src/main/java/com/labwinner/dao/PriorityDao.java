package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.Priority;

public interface PriorityDao {
	
	public void save(Priority priority);
	
	public void update(Priority priority);
	
	public void delete(Integer id);
	
	public Priority getById(Integer id);
	
	public List<Priority> getAll();

}
