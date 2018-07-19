package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.KnowledgeClassifyPostil;

public interface KnowledgePostilDao {
	
	public void save(KnowledgeClassifyPostil knowledgeClassifyPostil);
	
	public void update(KnowledgeClassifyPostil knowledgeClassifyPostil);
	
	public void delete(Integer id);
	
	public List<KnowledgeClassifyPostil> getById(Integer id);
	
	public List<KnowledgeClassifyPostil> getAll(KnowledgeAcc knowledgeAcc);
	
	public List<KnowledgeClassifyPostil> getByName(String name);

	public void deleteByPostileId(Integer id);

	public void deleteByAccId(Integer knowledgeAccId);

}
