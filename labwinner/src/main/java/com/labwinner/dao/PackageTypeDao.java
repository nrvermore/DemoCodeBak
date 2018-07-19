package com.labwinner.dao;

import java.util.List;
import com.labwinner.domain.PackageType;

public interface PackageTypeDao {
	
	public void save(PackageType packageType) ;
    
	public void delete(Integer id) ;
     
	public PackageType getById(Integer id);
  
	public List<PackageType> getAll();
	
	public List<PackageType> getAllName();
	
	public List<PackageType> getFlagAll();
	
	public List<PackageType> getAllPageable(String keyword);
	
	public void update(PackageType packageType);

}
