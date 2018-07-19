package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Analytics;
import com.labwinner.domain.ApprovelRefuse;
import com.labwinner.domain.Device;

public interface ApprovelRefuseDao {
	

	public void save(ApprovelRefuse approvelRefuse) ;
    
	public void delete(Integer id) ;
     
	public ApprovelRefuse getById(Integer id);
  
	public List<ApprovelRefuse> getAll();
	
	public List<ApprovelRefuse> getAllName();
	
	public List<ApprovelRefuse> getFlagAll();
	
	public List<ApprovelRefuse> getAllPageable(String keyword);
	
	public void update(ApprovelRefuse approvelRefuse);
	

}
