package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.AppHomeImage;

public interface AppHomeImageDao {
	
	public void save(AppHomeImage appHomeImage);
	
	public void delete(Integer id);
	
	public List<AppHomeImage> getAll();
	
	public List<AppHomeImage> getByShowNumber(Integer showNumber);
	
	public int getMaxShowNumber();

}
