package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.ExecuteChemicalGroup;

public interface ExecuteChemicalGroupDao {
	
	public void save(ExecuteChemicalGroup executeChemicalGroup);
	
	public void saveOlder(ExecuteChemicalGroup executeChemicalGroup);
	
	public void update(ExecuteChemicalGroup executeChemicalGroup);
	
	public void delete(Integer id);
	
	public void deleteByProcessId(Integer id);
	
	public List<ExecuteChemicalGroup> getByProcessId(Integer id);
	
	public List<Integer> getGroups(); 
	
	public ExecuteChemicalGroup getById(Integer id);


}
