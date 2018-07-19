package com.labwinner.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.KnowledgeClassifyReacRela;
import com.labwinner.domain.KnowledgeProRela;

public interface KnowledgeReacRelaDao {
	
	public void save(KnowledgeClassifyReacRela knowledgeClassifyReacRela);
	
	public void update(KnowledgeClassifyReacRela knowledgeClassifyReacRela);
	
	public void delete(Integer id);
	
	public KnowledgeClassifyReacRela getById(Integer id);
	
	public List<KnowledgeClassifyReacRela> getAll();
	
	public List<KnowledgeClassifyReacRela> findByName(String name);

	public List<KnowledgeClassifyReacRela> getAllByKnowledge(
			Map<String, Object> map);

	public KnowledgeClassifyReacRela getKnowledgeProRela(@Param("knowledgeId")Integer knowledgeId,
			@Param("classId")Integer classId, @Param("reactionId")Integer reactionId);

}
