package com.labwinner.service;

import java.util.List;






import java.util.Map;

import com.labwinner.domain.InventoryGroup;
import com.labwinner.domain.MaterialPurchase;
import com.labwinner.domain.ProductSummary;

public interface MaterialPurchaseService {
	

	public void save(MaterialPurchase materialPurchase);
	
	public void update(MaterialPurchase materialPurchase);
	
	public void delete(Integer id);
	
	public MaterialPurchase getById(Integer id);
	
	public List<MaterialPurchase> getByName(Integer userId,Integer id,String roleName);
	
	public List<MaterialPurchase> getAll(Integer userId, String roleName);

	public List<Map<String, Object>> getHotPurchase(Integer userId);

	public List<String> getChemical(String keyword);

	public List<MaterialPurchase> getMaterialList(Integer productSummaryId,
			Integer supId, String roleName,Integer userId);

	public List<MaterialPurchase> getBySupId(Integer id);

	public List<MaterialPurchase> getByProductSummaryId(Integer id);
	
	public List<MaterialPurchase> getOrders(Integer supId,Integer productId);
	
	public void updateOrders(Integer orderId,Integer materialId);

	public List<Integer> getByProductId(Integer id);

	

}
