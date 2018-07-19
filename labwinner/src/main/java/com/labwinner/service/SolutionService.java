package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.SolutionEntity;

public interface SolutionService {

	public void save(SolutionEntity solutionEntity);
	
	public void update (SolutionEntity solutionEntity);
	
	public void delete (Integer id);
	
	public SolutionEntity getById(Integer id);
	
	public List<SolutionEntity> getBykeyword(String keyword);
	
	public List<SolutionEntity> getAll();
	
	public List<String> getBarCodes();
	
	public SolutionEntity getByBarcode(String barcode);
}
