package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.KnowledgeThumbsUp;


/**
 * @Description 附件Service接口
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface KnowledgeThumbsUpService {

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public List<KnowledgeThumbsUp> getAll(Integer id);

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public Integer getById(Integer id);

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public Integer save(KnowledgeThumbsUp KnowledgeThumbsUp);

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public void update(KnowledgeThumbsUp knowledgeThumbsUp);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public void delete(Integer id);

	public void deleteByAccId(Integer knowledgeAccId);

}