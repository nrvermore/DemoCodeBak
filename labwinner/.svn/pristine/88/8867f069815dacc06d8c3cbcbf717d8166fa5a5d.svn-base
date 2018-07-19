package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Inventory;


public interface InventoryDao {
	
	public void save(Inventory inventory);
	
	public void update(Inventory inventory);
	
	public void delete(Integer id);
	
	public void deleteByGroupId(Integer id);
	
	public List<Inventory> getByGroupId(Integer id);
	
	public List<Inventory> getAllInventorys();//团队负责人列表
	
	public List<Inventory> findUserInventorys(@Param("name")Integer name,@Param("keyword")String keyword);//团队负责人关键字查询
	
	public List<Inventory> getAll(Integer userId);//普通用户列表
	
	public Inventory getById(Integer id);
	
	public List<Inventory> getByKeyword(
			@Param("name")Integer name,
			@Param("keyword")String keyword,
			@Param("userId")Integer userId);//普通用户关键字查询
	
	public List<String> findByName(String keyword);
	
	public List<String> findNameByUser(@Param("keyword")String keyword,@Param("userId")Integer userId);
	
	public void updateInventory(Inventory inventory);
	
	public List<Inventory> getHotWords ();
	
	public List<String> getBarCodes();
	
	public Inventory findByBarCode(String barcode);

	public int getInventoryNum(@Param("roleName")String roleName,@Param("userId")Integer userId);

	public List<Inventory> getByLocationId(Integer id);
	
	public Inventory getReactions(Integer id);
	
	public Inventory getUserReactions(@Param("userId")Integer userId,@Param("id")Integer id);

	public List<Inventory> getBySupId(Integer id);
	

}
