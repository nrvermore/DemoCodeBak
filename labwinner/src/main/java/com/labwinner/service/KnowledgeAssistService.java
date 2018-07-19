package com.labwinner.service;

import java.util.List;
import java.util.Map;

import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.KnowledgeAssist;
import com.labwinner.domain.ProjectAssist;

public interface KnowledgeAssistService {



	List<KnowledgeAssist> getAllKnowledgeAssist(Integer userId, Integer agencyId,int size);

	public void save(KnowledgeAssist KnowledgeAssist);

	List<KnowledgeAssist> getById(Integer userId, Integer agencyId,
			Integer knowledgeAssistId);

	void updateReadFlag(Integer knowledgeAssistId);
	
	public void delete(Integer id);

}
