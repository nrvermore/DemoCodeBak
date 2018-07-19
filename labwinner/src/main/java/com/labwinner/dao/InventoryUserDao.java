package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.InventoryUser;

public interface InventoryUserDao {
	
	public void save(InventoryUser inventoryUser);
	
	public void update(InventoryUser inventoryUser);
	
	public void delete(Integer id);
	
	public void batchRemove(List<Integer> ids);
	
	public List<InventoryUser> getById(Integer id);

}
