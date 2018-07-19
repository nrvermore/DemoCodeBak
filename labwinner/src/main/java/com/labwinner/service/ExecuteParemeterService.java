package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.ReactionExecuteParameter;

public interface ExecuteParemeterService {

	public void save(ReactionExecuteParameter executeParemeter);
	
	public void update(ReactionExecuteParameter executeParemeter);
	
	public void delete(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public void deleteByProcessId(Integer id);
	
	public ReactionExecuteParameter getById(Integer id);
}
