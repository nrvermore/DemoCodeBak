package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.AnalyticsDevice;

public interface AnalyticsDeviceService {

	public void save(AnalyticsDevice analyticsDevice);
	
	public void update(AnalyticsDevice analyticsDevice);
	
	public void delete(Integer id);
	
	public List<AnalyticsDevice> getById(Integer id);
}
