package com.labwinner.dao;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.labwinner.domain.SysDictionary;

/**
 * @Description 数据字典Dao
 * @author liuhq
 * @version V1.0
 * @date 2017年6月8日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface SysDictionaryDao {

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	public List<SysDictionary> getAll();
	
	/**
	 * @Description 根据字典类型获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	public List<SysDictionary> getByDictType(@PathVariable("dictType") String dictType);

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	SysDictionary getById(Long id);

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	void save(SysDictionary sysDictionary);

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	void update(SysDictionary sysDictionary);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	void delete(Long id);

}
