package com.labwinner.service;

import java.util.List;
import com.labwinner.domain.Industry;

/**
 * @Description 行业Service接口
 * @author liuhq
 * @version V1.0
 * @date 2017年6月8日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface IndustryService {

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	public List<Industry> getAll();

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	public Industry getById(Long id);

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	public Integer save(Industry industry);

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	public void update(Industry industry);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	public void delete(Long id);

}