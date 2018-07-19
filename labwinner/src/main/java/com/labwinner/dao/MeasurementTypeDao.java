package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.MeasurementType;

public interface MeasurementTypeDao {
	
	public void save(MeasurementType measurementType);
	
	public void update(MeasurementType measurementType);
	
	public void delete(Integer id);
	
	public List<MeasurementType> getAll();
	
	public Integer getByName(String name);
	
	public List<MeasurementType> getList();
	
	public Integer getCount(@Param("name")String name,@Param("id")Integer id);

}
