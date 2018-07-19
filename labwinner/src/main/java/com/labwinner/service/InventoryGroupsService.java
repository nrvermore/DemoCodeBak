package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.InventoryGroups;

public interface InventoryGroupsService {

	public void save(InventoryGroups inventoryGroups);
	
	public void delete (Integer id);
	
	public void update(InventoryGroups inventoryGroups);
	
	public InventoryGroups getById(Integer id);
	
	public List<InventoryGroups> getUserInventorys(Integer userId);//普通用户库存列表
	
	public List<InventoryGroups> getByKeyword(String keyword,Integer userId,Integer type,Integer locId);//普通用户关键字搜索
	
	public List<InventoryGroups> getAppByKeyword(String keyword,Integer userId);//普通用户关键字搜索
	
	public List<InventoryGroups> getAllInventorys();//团队负责人列表
	
	public List<InventoryGroups> getListByKeyword(String keyword,Integer type,Integer locId);//团队负责人关键字搜索

	public List<InventoryGroups> getAppListByKeyword(String keyword);//团队负责人关键字搜索
}
