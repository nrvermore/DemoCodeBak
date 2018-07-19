package com.labwinner.dao;

import java.util.List;



import com.labwinner.domain.CompanyType;
import com.labwinner.domain.Supplier;


public interface SupplierDao {
	
	public void save(Supplier supplier);
	
	public Supplier getById(Integer id);
	
	public List<Supplier> getAll();
	
	public void update(Supplier supplier);
	
	public void delete(Integer id);
	
	public List<Supplier> getAllPageable(String keyword);

	public List<Supplier> getByName(String keyword);

	public List<CompanyType> getCompanyType();

}
