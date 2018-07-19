package com.labwinner.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.KnowledgeProRela;

public interface KnowledgeProRelaDao {
	
	public void save(KnowledgeProRela knowledgeProRela);
	
	public void update(KnowledgeProRela knowledgeProRela);
	
	public void delete(Integer id);
	
	public KnowledgeProRela getById(Integer id);
	
	public List<KnowledgeProRela> findByName(String name);
	
	public List<KnowledgeProRela> getAll();

	public List<KnowledgeProRela> getAllByKnowladge(Map<String, Object> map);

	public KnowledgeProRela getKnowledgeProRela(@Param("knowledgeId")Integer knowledgeId,
			@Param("classId")Integer classId,@Param("proId") Integer proId);

	public List<KnowledgeProRela> getKnowledgeProRelaByProId(@Param("classfyId")int classfyId,
			@Param("proId")int proId);
	
}
