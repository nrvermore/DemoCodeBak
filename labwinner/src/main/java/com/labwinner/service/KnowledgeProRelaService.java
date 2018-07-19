package com.labwinner.service;

import java.util.List;
import java.util.Map;

import com.labwinner.domain.KnowledgeProRela;

public interface KnowledgeProRelaService {
	
	public void save(KnowledgeProRela knowledgeProRela);
	
	public void update(KnowledgeProRela knowledgeProRela);
	
	public void delete(Integer id);
	
	public KnowledgeProRela getById(Integer id);
	
	public List<KnowledgeProRela> findByName(String name);
	
	public List<KnowledgeProRela> getAll();
	/**
	 * @Description 附件Service实现
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月3日
	 * 
	 * @Company 西安博文同创信息技术有限公司
	 * @Copyright Copyright (c) 2017, All rights reserved.
	 */
	public List<KnowledgeProRela> getAllByKnowladge(Map<String, Object> Map);

	public KnowledgeProRela getKnowledgeProRela(Integer knowledgeId, Integer classId,
			Integer proId);

	public List<KnowledgeProRela> getKnowledgeProRelaByProId(int classfyId,
			int proId);

}
