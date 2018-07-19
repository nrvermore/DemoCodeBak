package com.labwinner.service;

import java.util.List;
import java.util.Map;

import com.labwinner.domain.KnowledgeClassifyReacRela;
import com.labwinner.domain.KnowledgeProRela;

public interface KnowledgeReacRelaService {
	
	public void save(KnowledgeClassifyReacRela knowledgeClassifyReacRela);
	
	public void update(KnowledgeClassifyReacRela knowledgeClassifyReacRela);
	
	public void delete(Integer id);
	
	public KnowledgeClassifyReacRela getById(Integer id);
	
	public List<KnowledgeClassifyReacRela> getAll();
	
	public List<KnowledgeClassifyReacRela> findByName(String name);
	
	/**
	 * @Description 附件Service实现
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月3日
	 * 
	 * @Company 西安博文同创信息技术有限公司
	 * @Copyright Copyright (c) 2017, All rights reserved.
	 */
	public List<KnowledgeClassifyReacRela> getAllByKnowladge(Map<String, Object> Map);

	public KnowledgeClassifyReacRela getKnowledgeProRela(Integer knowledgeId,
			Integer classId, Integer reactionId);

}
