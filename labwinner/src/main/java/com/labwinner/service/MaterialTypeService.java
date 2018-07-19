package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.MaterialType;


public interface MaterialTypeService{
	
	public void save(MaterialType materialType);
	
	public List<MaterialType> getAll();
    
	public void delete(Integer id);
    
	public MaterialType getById(Integer id);
   
	public void update(MaterialType materialType);
	
	public MaterialType getMaterialType(Integer materialCategoryId);
	
	public List<MaterialType> getMaterialTypes(Integer materialTypeCodeId);

	public List<MaterialType> getAllPageable(String keyword);
	
	public MaterialType getTree(Integer materialTypeCodeId);
}