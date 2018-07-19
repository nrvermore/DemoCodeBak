package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.ModifyType;
import com.labwinner.domain.SecureRank;

public interface SecureRankDao {
	
	public void save(SecureRank secureRank);
	
	public List<SecureRank> getAll();

	public void delete(Integer id);
    
	public SecureRank getById(Integer id);
   
	public void update(SecureRank secureRank);
	
	public List<SecureRank> getAllPageable(String keyword);
	
}
