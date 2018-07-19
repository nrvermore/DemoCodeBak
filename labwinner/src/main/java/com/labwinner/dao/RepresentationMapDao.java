package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.RepresentationMap;

public interface RepresentationMapDao {
	
	public void save(RepresentationMap representationMap);
	
	public void update(RepresentationMap representationMap);
	
	public void delete(Integer id);
	
	public RepresentationMap getById(Integer id);
	
	public List<RepresentationMap> getAll();
	
	public void deleteByUrl(String url);

}
