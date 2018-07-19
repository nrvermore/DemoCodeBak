package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.InventoryGroup;

public interface InventoryGroupDao {
	
	public void save(InventoryGroup inventoryGroup);
	
	public void delete (Integer id);
	
	public void update(InventoryGroup inventoryGroup);
	
	public List<InventoryGroup> getUserInventorys(Integer userId);//普通用户库存列表
	
	public List<InventoryGroup> getByKeyword(@Param("name")Integer name,@Param("keyword")String keyword,@Param("userId")Integer userId);//普通用户关键字搜索
	
	public List<InventoryGroup> getAllInventorys();//团队负责人列表
	
	public List<InventoryGroup> getListByKeyword(@Param("keyword")String keyword,@Param("name")Integer name);//团队负责人关键字搜索

}
