package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.InventoryLocation;


public interface InventoryLocationService{
	
	
	public void save(InventoryLocation inventoryLocation); 

	public List<InventoryLocation> getAll();
	
	public List<InventoryLocation> getAllPageable(String keyword);
	
	public InventoryLocation getLocation(Integer cid);
	
	public List<InventoryLocation>  getLocations(Integer pid);
	
	public InventoryLocation getTree(Integer cid);
	
	public void  delete(Integer pid);

	public void update(InventoryLocation inventoryLocation);
	
	public void updateQrName(InventoryLocation inventoryLocation);
	
	public InventoryLocation getById(Integer id);
	
	public InventoryLocation getByBarcode(String barcode);

	public void deleteByLocationId(Integer id);

	public List<InventoryLocation> getAllLocation(Integer id);

	public List<InventoryLocation> getAllFirst();
	
	public List<String> getBarCodes();
	
}