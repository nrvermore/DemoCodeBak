package com.labwinner.dao;

import java.util.List;






import com.labwinner.domain.MaterialField;


public interface MaterialFieldDao {
	

	
	public List<MaterialField> getAll();
	
	
	public MaterialField getTree(Integer cid);


	public MaterialField getLocation(Integer cid);


	public List<MaterialField> getLocations(Integer cid);


	public List<MaterialField> getAllFirst();


	public MaterialField getByCid(Integer id);


	public List<MaterialField> getAllSamePid(Integer filedId);
	
	

}
