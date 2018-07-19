package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.ReactionExecuteParameter;

public interface ExecuteParameterDao {
	
	public void save(ReactionExecuteParameter executeParemeter);
	
	public void update(ReactionExecuteParameter executeParemeter);
	
	public void delete(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public ReactionExecuteParameter getById(Integer id);
	
	public void deleteByProcessId(Integer id);

}
