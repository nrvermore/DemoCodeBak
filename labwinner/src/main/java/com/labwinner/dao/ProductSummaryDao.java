package com.labwinner.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.ProductSummary;

/**
 * @Description 采购产品Dao
 * @author llwang
 * @version V1.0
 * @date 2017年8月12日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface ProductSummaryDao {

	/**
	 * @Description 获取所有对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年8月12日
	 */
	public List<ProductSummary> getAll();

	/**
	 * @Description 根据主键获取对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年8月12日
	 */
	ProductSummary getById(Integer id);

	/**
	 * @Description 保存对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年8月12日
	 */
	void save(ProductSummary productSummary);

	/**
	 * @Description 更新对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年8月12日
	 */
	void update(ProductSummary productSummary);

	/**
	 * @Description 删除对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年8月12日
	 */
	void delete(Integer id);

	public List<ProductSummary> getByName(String keyword);

	public List<Map<String, Object>> getChemical(String keyword);

	public List<Map<String, Object>> getSupplier(String keyword);

	public List<ProductSummary> getAllBySupId(Integer id);

	public List<ProductSummary> getByNameBySupId(@Param("id")Integer id, @Param("keyword")String keyword);

	public List<ProductSummary> getByChParId(@Param("chParId")Integer chParId, @Param("supId")int supId);

	public List<ProductSummary> getBySupId(Integer id);
	
	public List<ProductSummary> findByChemical(Integer id);
	
	public List<ProductSummary> findByKeyword(String keyword);

	public List<com.labwinner.domain.ProductType> getProductType();
	
	public List<ProductSummary> findByChemicalName(String keyword);
	
	/**
	 * @Description 获取所有对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年8月12日
	 */
	public List<ProductSummary> getAllProduct();
	
}
