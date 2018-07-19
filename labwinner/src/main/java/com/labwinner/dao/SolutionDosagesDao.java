package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.SolutionDosages;

public interface SolutionDosagesDao {
	
	public void save(SolutionDosages solutionDosages);
	
	public List<Integer> getAllInventories();
	
	public List<SolutionDosages> getBySolutionId(Integer id);
	
	public void delete(Integer id);
	
}
