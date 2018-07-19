package com.labwinner.service;

import java.util.List;
import java.util.Map;

import com.labwinner.domain.ProjectAssist;

public interface ProjectAssistService {

	List<Map<String,Integer>> getProNumber(Integer userId,Integer projectId);

	void save(ProjectAssist projectAssist);

	List<ProjectAssist> getAllContentList(Integer userId, Integer projectId);

	void updateReadMan(String readMan,Integer projectAssistId);

	void updateDelete(Integer projectAssistId, String deleteMan);

	List<ProjectAssist> getAssistList(Integer userId, String roleName,
			Integer endCount);

	List<ProjectAssist> getAllUnread(Integer userId, Integer proId);

	List<ProjectAssist> getByProjectAssistId(Integer projectAssistId);

	List<ProjectAssist> getNewProjectAssist(Integer userId);
	
	public List<ProjectAssist> getNewTeamAssist(Integer userId);

	List<ProjectAssist> getAllMessageContentList(Integer userId, Integer id);

	void updateMessage(String msgDelete, Integer projectAssistId);

}
