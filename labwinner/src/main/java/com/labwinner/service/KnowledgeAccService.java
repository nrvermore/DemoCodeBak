package com.labwinner.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.KnowledgeAcc;

/**
 * @Description 附件Service接口
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface KnowledgeAccService {

	/**
	 * @Description 获取所有对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public List<KnowledgeAcc> getAll();

	/**
	 * @Description 根据主键获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public KnowledgeAcc getById(Integer id);
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public KnowledgeAcc getKnowledgeAcc(Integer knowledgeId,Integer classifyId);

	/**
	 * @Description 保存对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public Integer save(KnowledgeAcc knowledgeAcc);

	/**
	 * @Description 更新对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void update(KnowledgeAcc knowledgeAcc);

	/**
	 * @Description 删除对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void delete(Integer id);
	/**
	 * @Description 根据文件名获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public List<KnowledgeAcc> getByName(String name);

	public List<KnowledgeAcc> getAllConversion();

}