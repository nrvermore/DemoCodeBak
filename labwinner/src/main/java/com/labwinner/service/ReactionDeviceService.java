package com.labwinner.service;


import com.labwinner.domain.ReactionDevice;

public interface ReactionDeviceService {

	public void save(ReactionDevice reactionDevice);
	
	public void update(ReactionDevice reactionDevice);
	
	public void delete(Integer id);
	
	public void deleteByProcessId(Integer id);
	
	public ReactionDevice getById(Integer id);
}
