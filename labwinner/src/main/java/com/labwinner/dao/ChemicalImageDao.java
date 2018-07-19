package com.labwinner.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.ChemicalImage;


public interface ChemicalImageDao {
	
	public void save(ChemicalImage chemicalImage);
	
	public List<ChemicalImage> getAll();
	
	public List<String> getByInventoryId(Integer id);
	
	public List<ChemicalImage> getByGroupId(Integer id);
	
	public void deleteById(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public void deleteByUrl(@Param("imageName")String imageName,@Param("id")Integer id);

}
