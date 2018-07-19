package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.MeasurementType;

public interface MeasurementTypeService {

	public void save(MeasurementType measurementType);
	
	public void update(MeasurementType measurementType);
	
	public void delete(Integer id);
	
	public List<MeasurementType> getAll();
	
	public Integer getByName(String name);
	
	public Integer getCount(String name,Integer id);
	
	public List<MeasurementType> getList();

}
