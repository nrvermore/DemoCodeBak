package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Measurement;


public interface MeasurementDao {
	
	public void save(Measurement measurement);
	
	public List<Measurement> getAll();
	
	public List<Measurement> getById(Integer id);
	
	public Measurement getByMeasureUnitId(Integer id);

	public List<Measurement> getMeasurementByMeasureType(Integer id);
		
	public List<Measurement> getByMeasureType();
	
	public Integer getByType(Integer id);
   
	public void delete(Integer id);
	
	public void deleteFlag(Integer id);
	
	public List<Measurement> getBySourceId();
	
	public void update(Measurement measurement);
	
	public Integer getByUnit(String measureUnit);
	
	public List<Integer> getMeasureTypes();
	
	public Integer getCount(@Param("name")String name,@Param("id")Integer id);
}
