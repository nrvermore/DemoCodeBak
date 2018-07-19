package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.KnowledgeAcc;

public interface KnowledgeAccServicebak {
	
	public void save(KnowledgeAcc knowledgeAcc);
	
	public void update(KnowledgeAcc knowledgeAcc);
	
	public void delete(Integer id);
	
	public KnowledgeAcc getById(Integer id);
	
	public List<KnowledgeAcc> getAll();
	
	public List<KnowledgeAcc> getByName(String name);

}
