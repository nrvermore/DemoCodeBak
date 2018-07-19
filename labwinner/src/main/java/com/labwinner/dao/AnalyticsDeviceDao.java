package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.AnalyticsDevice;

public interface AnalyticsDeviceDao {
	
	public void save(AnalyticsDevice analyticsDevice);
	
	public void update(AnalyticsDevice analyticsDevice);
	
	public void delete(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public List<AnalyticsDevice> getById(Integer id);

}
