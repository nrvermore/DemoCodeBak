package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.Inventories;

public interface InventoriesService {

	public void save(Inventories inventories);
	
	public void update(Inventories inventories);
	
	public void delete(Integer id);
	
	public void deleteByGroupId(Integer id);
	
	public List<Inventories> getByGroupId(Integer id,Integer cid);
	
	public List<Inventories> getAllInventorys();//团队负责人列表
	
	public List<Inventories> findUserInventorys(String keyword);//团队负责人关键字查询
	
	public List<Inventories> getAll(Integer userId);//普通用户列表
	
	public Inventories getById(Integer id);
	
	public List<Inventories> getByKeyword(
			String keyword,
			Integer userId);//普通用户关键字查询
	
	public List<Inventories> findByName(String keyword);
	
	public List<Inventories> findNameByUser(String keyword,Integer userId);
	
	public void updateInventory(Inventories inventories);
	
	public List<Inventories> getHotWords ();
	
	public List<String> getBarCodes();
	
	public Inventories findByBarCode(String barcode);

	public int getInventoryNum(String roleName,Integer userId);

	public List<Inventories> getByLocationId(Integer id);
	
	public Inventories getReactions(Integer id);
	
	public Inventories getUserReactions(Integer userId,Integer id);

	public List<Inventories> getBySupId(Integer id);
	
	public List<Inventories> getUserHotWords (Integer userId);
	
	public List<Inventories> getByGroupIds (List<Integer> groupIds);
	
	public List<Inventories> getScreenInventorys();
}
