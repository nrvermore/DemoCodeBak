package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.ChemicalParameter;



public interface ChemicalParameterDao {
	
	public void save(ChemicalParameter chemicalParameter);
	
	public void saveByInventory(ChemicalParameter chemicalParameter);
	
	public void update(ChemicalParameter chemicalParameter);
	
	public void delete(Integer id);
	
	public List<ChemicalParameter> getAll();
	
	public ChemicalParameter getById(Integer id);
	
	public List<ChemicalParameter> getByKeyword(String keyword);
	
	public void lockChemical(@Param("id")Integer id,@Param("isLock")String isLock);
	
	public Integer getNameCount(String name);
	
	public Integer getCasCount(String cas);
}
