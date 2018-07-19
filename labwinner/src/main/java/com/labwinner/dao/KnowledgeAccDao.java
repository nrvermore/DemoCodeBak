package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.FellingExp;
import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.KnowledgeAcc;
/**
 * @Description 知识附件Dao
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface KnowledgeAccDao {
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
	KnowledgeAcc getById(Integer id);
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	KnowledgeAcc getKnowledgeAcc(@Param("knowledgeId")Integer knowledgeId,@Param("classifyId")Integer classifyId);

	/**
	 * @Description 保存对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	void save(KnowledgeAcc knowledgeAcc);

	/**
	 * @Description 更新对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	void update(KnowledgeAcc knowledgeAcc);

	/**
	 * @Description 删除对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	void delete(Integer id);
	/**
	 * @Description 根据主键获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	 List<KnowledgeAcc> getByName(String name);

	public List<KnowledgeAcc> getAllConversion();
}
