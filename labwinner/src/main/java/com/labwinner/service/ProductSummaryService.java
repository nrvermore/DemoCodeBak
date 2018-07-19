package com.labwinner.service;

import java.util.List;
import java.util.Map;

import com.labwinner.domain.JournalArticle;
import com.labwinner.domain.ProductSummary;
import com.labwinner.domain.ProductType;

/**
 * @Description 采购产品接口
 * @author llwang
 * @version V1.0
 * @date 2017年8月12日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface ProductSummaryService {

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
	public ProductSummary getById(Integer id);

	/**
	 * @Description 保存对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年8月12日
	 */
	public Integer save(ProductSummary productSummary);

	/**
	 * @Description 更新对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年8月12日
	 */
	public void update(ProductSummary productSummary);

	/**
	 * @Description 删除对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年8月12日
	 */
	public void delete(Integer id);

	public List<ProductSummary> getByName(String keyword);

	public List<Map<String, Object>> getChemical(String keyword);

	public List<Map<String, Object>> getSupplier(String keyword);

	public List<ProductSummary> getAllBySupId(Integer id);

	public List<ProductSummary> getByNameBySupId(Integer id, String keyword);

	public List<ProductSummary> getByChParId(Integer chParId, int supId);

	public List<ProductSummary> getBySupId(Integer id);
	
	public List<ProductSummary> findByChemical(Integer id);
	
	public List<ProductSummary> findByKeyword(String keyword);

	public List<ProductType> getProductType();
	
	public List<ProductSummary> findByChemicalName(String keyword);
	
	public List<ProductSummary> getAllProduct();

}