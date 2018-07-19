package com.labwinner.service;

import java.util.List;
import com.labwinner.domain.SolutionDesignDosage;

public interface SolutionDesignDosageService {
	
	public void save(SolutionDesignDosage solutionDesignDosage);
	
	public void update(SolutionDesignDosage solutionDesignDosage);
	
	public void delete(Integer id);
	
	public SolutionDesignDosage getById(Integer id);
	
	public List<SolutionDesignDosage> getAll();
	
	public List<SolutionDesignDosage> getByName(String name);

}
