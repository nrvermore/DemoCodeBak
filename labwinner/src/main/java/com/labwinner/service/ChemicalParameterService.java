package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.ChemicalParameter;

public interface ChemicalParameterService {
	

	public void save(ChemicalParameter chemicalParameter); 
	
	public void saveByInventory(ChemicalParameter chemicalParameter);

	public void delete(Integer id); 

	public void update(ChemicalParameter chemicalParameter);
	
	public List<ChemicalParameter> getAll();
	
	public ChemicalParameter getById(Integer id);
	
	public List<ChemicalParameter> getByKeyword(String keyword);
	
	public void lockChemical(Integer id,String isLock);
	
	public Integer getNameCount(String name);
	
	public Integer getCasCount(String cas);
}