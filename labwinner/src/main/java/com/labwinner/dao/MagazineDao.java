package com.labwinner.dao;

import java.util.List;
import java.util.Map;

import com.labwinner.domain.Magazine;
import com.labwinner.domain.Supplier;
/**
 * @Description 心得体会Dao
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface MagazineDao {
	
	/**
	 * @Description 保存对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void save(Magazine magazine);
	/**
	 * @Description 更新对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void update(Magazine magazine);
	/**
	 * @Description 删除对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void delete(Integer id);
	/**
	 * @Description 根据主键获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public Magazine getById(Integer id);
	/**
	 * @Description 根据name获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public List<Magazine> getByName(Map<String, String> map);
	/**
	 * @Description 获取所有对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public List<Magazine> getAll();
	
	public List<Magazine> getAllName();
	
	public List<Magazine> getAllPageable(String keyword);
	
	public Integer getMaxMagazineId();

	
}
