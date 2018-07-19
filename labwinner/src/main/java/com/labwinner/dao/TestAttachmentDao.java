package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.TestAttachment;

public interface TestAttachmentDao {

	public void save(TestAttachment testAttachment);
	
	public void delete(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public List<TestAttachment> getById(Integer id);
	
	public void deleteByUrl(String filename);

}
