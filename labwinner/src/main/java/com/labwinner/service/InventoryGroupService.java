package com.labwinner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.InventoryGroup;

public interface InventoryGroupService {

	public void save(InventoryGroup inventoryGroup);
	
	public void update(InventoryGroup inventoryGroup);
	
	public void delete (Integer id);
	
	public List<InventoryGroup> getUserInventorys(Integer userId);//普通用户库存列表
	
	public List<InventoryGroup> getByKeyword(Integer name,String keyword,Integer userId);//普通用户关键字搜索
	
	public List<InventoryGroup> getAllInventorys();//团队负责人列表
	
	public List<InventoryGroup> getListByKeyword(String keyword,Integer name);//团队负责人关键字搜索

}
