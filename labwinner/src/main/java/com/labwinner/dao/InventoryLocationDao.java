package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.Inventory;
import com.labwinner.domain.InventoryLocation;


public interface InventoryLocationDao {
	
	public void save (InventoryLocation inventoryLocation);
	
	public List<InventoryLocation> getAll();
	
	public List<InventoryLocation> getAllPageable(String keyword);
	
	public InventoryLocation getLocation(Integer cid);
	
	public void  delete(Integer id);

	public void update(InventoryLocation inventoryLocation);
	
	public InventoryLocation getById(Integer id);
	
	public List<InventoryLocation>  getLocations(Integer pid);

	public void deleteByLocationId(Integer id);

	public List<InventoryLocation> getAllLocation(Integer id);

	public List<InventoryLocation> getAllFirst();
	
	public List<String> getBarCodes();
	
	public void updateQrName(InventoryLocation inventoryLocation);
	
	public InventoryLocation getByBarcode(String barcode);

}
