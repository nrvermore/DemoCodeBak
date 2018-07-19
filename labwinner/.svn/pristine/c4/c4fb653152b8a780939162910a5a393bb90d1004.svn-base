package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Inventories;

public interface InventoriesDao {

	public void save(Inventories inventories);
	
	public void update(Inventories inventories);
	
	public void delete(Integer id);
	
	public void deleteByGroupId(Integer id);
	
	public List<Inventories> getByGroupId(@Param("id")Integer id,@Param("cid")Integer cid);
	
	public List<Inventories> getAllInventorys();//团队负责人列表
	
	public List<Inventories> findUserInventorys(@Param("keyword")String keyword);//团队负责人关键字查询
	
	public List<Inventories> getAll(Integer userId);//普通用户列表
	
	public Inventories getById(Integer id);
	
	public List<Inventories> getByKeyword(
			@Param("keyword")String keyword,
			@Param("userId")Integer userId);//普通用户关键字查询
	
	public List<Inventories> findByName(String keyword);
	
	public List<Inventories> findNameByUser(@Param("keyword")String keyword,@Param("userId")Integer userId);
	
	public void updateInventory(Inventories inventories);
	
	public List<Inventories> getHotWords ();
	
	public List<String> getBarCodes();
	
	public Inventories findByBarCode(String barcode);

	public int getInventoryNum(@Param("roleName")String roleName,@Param("userId")Integer userId);

	public List<Inventories> getByLocationId(Integer id);
	
	public Inventories getReactions(Integer id);
	
	public Inventories getUserReactions(@Param("userId")Integer userId,@Param("id")Integer id);

	public List<Inventories> getBySupId(Integer id);
	
	public List<Inventories> getUserHotWords (Integer userId);
	
	public List<Inventories> getByGroupIds (List<Integer> groupIds);
	
	public List<Inventories> getScreenInventorys();
}
