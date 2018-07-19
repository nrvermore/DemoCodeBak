package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.InventoryGroups;

public interface InventoryGroupsDao {

	public void save(InventoryGroups inventoryGroups);
	
	public void delete (Integer id);
	
	public void update(InventoryGroups inventoryGroups);
	
	public InventoryGroups getById(Integer id);
	
	public List<InventoryGroups> getUserInventorys(Integer userId);//普通用户库存列表
	
	public List<InventoryGroups> getByKeyword(@Param("keyword")String keyword,@Param("userId")Integer userId,
			@Param("type")Integer type,@Param("locId")Integer locId);//普通用户关键字搜索
	
	public List<InventoryGroups> getAppByKeyword(@Param("keyword")String keyword,@Param("userId")Integer userId);//普通用户关键字搜索
	
	public List<InventoryGroups> getAllInventorys();//团队负责人列表
	
	public List<InventoryGroups> getListByKeyword(@Param("keyword")String keyword,@Param("type")Integer type,@Param("locId")Integer locId);//团队负责人关键字搜索
	
	public List<InventoryGroups> getAppListByKeyword(String keyword);//团队负责人关键字搜索
}
