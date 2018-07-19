package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.KnowledgeClassify;

public interface KnowledgeClassifyService {
	
	public void save(KnowledgeClassify knowledgeClassify);
	
	public void update(KnowledgeClassify knowledgeClassify);
	
	public void delete(Integer id);
	
	public KnowledgeClassify getById(Integer id);
	
	public List<KnowledgeClassify> getByName(String name);
	
	public List<KnowledgeClassify> getAll();

}
