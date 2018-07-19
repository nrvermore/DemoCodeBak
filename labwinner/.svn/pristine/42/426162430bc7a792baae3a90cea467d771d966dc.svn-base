package com.labwinner.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.InventoryGroup;
import com.labwinner.domain.MaterialPurchase;
import com.labwinner.domain.ProductSummary;
/**
 * @Description 心得体会Dao
 * @author wangll
 * @version V1.0
 * @date 2017年7月8日
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface MaterialPurchaseDao {
	
	/**
	 * @Description 保存对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月8日
	 */
	public void save(MaterialPurchase materialPurchase);
	/**
	 * @Description 更新对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月8日
	 */
	public void update(MaterialPurchase materialPurchase);
	/**
	 * @Description 删除对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月8日
	 */
	public void delete(Integer id);
	/**
	 * @Description 根据主键获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月8日
	 */
	public MaterialPurchase getById(Integer id);
	/**
	 * @Description 根据name获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月8日
	 */
	public List<MaterialPurchase> getByName(@Param("userId")Integer userId ,@Param("id")Integer id,@Param("roleName")String roleName);
	/**
	 * @Description 获取所有对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月8日
	 */
	//public List<MaterialPurchase> getAll();
	/**
	 * @Description 获取所有对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月15日
	 */
	public List<Map<String, Object>> getHotPurchase(Integer userId);
	
	
	public List<MaterialPurchase> getAll(@Param("userId")Integer userId, @Param("roleName")String roleName);
	/**
	 * @Description 获取化学品模糊查询
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月15日
	 */
	public List<String> getChemical(String keyword);
	
	
	public List<MaterialPurchase> getMaterialList(@Param("productSummaryId")Integer productSummaryId,
			@Param("supId")Integer supId, @Param("roleName")String roleName,@Param("userId")Integer userId);
	
	/**
	 * @Description 根据供应商查询订单
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月15日
	 */
	public List<MaterialPurchase> getBySupId(Integer id);
	
	
	public List<MaterialPurchase> getByProductSummaryId(Integer id);
	
	
	public List<MaterialPurchase> getOrders(@Param("supId")Integer supId,@Param("productId")Integer productId);
	
	
	public void updateOrders(@Param("orderId")Integer orderId,@Param("materialId")Integer materialId);
	
	public List<Integer> getByProductId(Integer id);
	
	
	

	
}
