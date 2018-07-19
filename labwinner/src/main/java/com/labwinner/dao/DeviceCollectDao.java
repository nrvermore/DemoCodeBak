package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.DeviceCollect;

public interface DeviceCollectDao {
	
	public List<DeviceCollect> getAllScreen();
	
	public void save(DeviceCollect deviceCollect);
	
	public void delete(Integer id);
	

}
