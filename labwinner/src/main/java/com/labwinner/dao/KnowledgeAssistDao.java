package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.KnowledgeAssist;


/**
 * @Description 知识附件Dao
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface KnowledgeAssistDao {

	List<KnowledgeAssist> getAllKnowledgeAssist(@Param("userId")Integer userId, @Param("agencyId")Integer agencyId,@Param("size")Integer size);

	void save(KnowledgeAssist knowledgeAssist);

	List<KnowledgeAssist> getById(@Param("userId")Integer userId, @Param("agencyId")Integer agencyId,
			@Param("knowledgeAssistId")Integer knowledgeAssistId);

	void updateReadFlag(Integer knowledgeAssistId);
	
	public void delete(Integer id);
	
}
