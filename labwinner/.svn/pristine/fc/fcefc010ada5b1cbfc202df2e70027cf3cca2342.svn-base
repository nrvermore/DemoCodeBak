package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.ChemicalImage;


public interface ChemicalImageService{
	
	public void save(ChemicalImage chemicalImage);
	
	public List<ChemicalImage> getAll();
	
	public List<String> getByInventoryId(Integer id);
	
	public List<ChemicalImage> getByGroupId(Integer id);
	
	public void deleteById(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public void deleteByUrl(String imageName,Integer id);
}