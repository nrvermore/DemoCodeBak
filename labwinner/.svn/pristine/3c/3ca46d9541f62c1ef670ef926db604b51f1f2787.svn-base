package com.labwinner.service;

import java.util.List;
import java.util.Map;

import com.labwinner.domain.KnowledgeField;






public interface KnowledgeFieldService{

	List<KnowledgeField> getAll();

	List<KnowledgeField> getAllPageable(String keyword);

	void save(KnowledgeField knowledgeField);

	void update(KnowledgeField knowledgeField);

	void delete(Integer id);
	
	KnowledgeField getById(int id);

	List<Map<String, Object>> getDefaultImageList(String fieldImage);

	int getCountByKnowledgeField(Integer knowledgeFieldId);

	int getCountByKnowledgeFieldForPatent(Integer knowledgeFieldId);
	

	
	
	
	
	
}