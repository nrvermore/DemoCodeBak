package com.labwinner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.InventoryModify;


public interface InventoryModifyService {
	

	public void save(InventoryModify inventoryModify);

	public void delete(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public List<InventoryModify> getAll();
	
	public List<InventoryModify> getById(Integer id);
	
	public List<InventoryModify> getReactions(Integer id);
	
	public Integer getProcessCount(Integer processId,Integer inventoryId);
}