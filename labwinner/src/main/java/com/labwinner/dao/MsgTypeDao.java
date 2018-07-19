package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.MsgType;

public interface MsgTypeDao {
	
	public void save(MsgType msgType);
	
	public void update(MsgType msgType);
	
	public void delete(Integer id);
	
	public MsgType getById(Integer id);
	
	public List<MsgType> getAll();

}
