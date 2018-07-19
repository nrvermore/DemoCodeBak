package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.InventoryModify;


public interface InventoryModifyDao {
	
	public void save(InventoryModify inventoryModify);
	
	public void update(InventoryModify inventoryModify);
	
	public void delete(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public List<InventoryModify> getAll();
	
	public  List<InventoryModify> getById(Integer id);
	
	public List<InventoryModify> getReactions(Integer id);
	
	public Integer getProcessCount(@Param("processId")Integer processId,@Param("inventoryId")Integer inventoryId);
	
}
