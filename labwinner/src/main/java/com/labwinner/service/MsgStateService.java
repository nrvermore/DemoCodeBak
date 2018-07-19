package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.MsgState;

	public interface MsgStateService {
	
	public void save(MsgState msgState);
	
	public void update(MsgState msgState);
	
	public void delete(Integer id);
	
	public MsgState getById(Integer id);
	
	public List<MsgState> getAll();

}
