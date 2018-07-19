package com.labwinner.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Industry;
import com.labwinner.domain.PersonalTemplate;

/**
 * @Description 个人模板Service
 * @author liuhq
 * @version V1.0
 * @date 2017年6月8日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface PersonalTemplateService {

	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	public List<PersonalTemplate> getAll(Integer userId);
	
	//获取所有模板名称
    public List<PersonalTemplate> getAllTemplateName(Integer userId);

	public List<PersonalTemplate> getMyAll(Integer userId);
	
	public List<PersonalTemplate> getAllPageable(Integer userId,String keyword);

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	PersonalTemplate getById(Integer id);

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	void save(PersonalTemplate personalTemplate);

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	void update(PersonalTemplate personalTemplate);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	void delete(Integer id);

}
