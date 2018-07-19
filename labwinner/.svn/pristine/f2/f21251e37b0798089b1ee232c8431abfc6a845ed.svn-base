package com.labwinner.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.Expert;
import com.labwinner.domain.ExpertAssist;
import com.labwinner.domain.ProjectAssist;
/**
 * 专家DAO接口
 * @Description TODO
 * @author llwangi
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface ProjectAssistDao {
	
	public List<Map<String,Integer>> getProNumber(@Param("userId")Integer userId,@Param("projectId")Integer projectId);

	public void save(ProjectAssist projectAssist);

	public List<ProjectAssist> getAllContentList(@Param("userId")Integer userId,@Param("projectId")Integer projectId);

	public void updateReadMan(@Param("readMan")String readMan,@Param("projectAssistId")Integer projectAssistId);

	public void updateDelete(@Param("projectAssistId")Integer projectAssistId,@Param("deleteMan") String deleteMan);

	public List<ProjectAssist> getAssistList(@Param("userId")Integer userId, @Param("roleName")String roleName,
			@Param("endCount")Integer endCount);

	public List<ProjectAssist> getAllUnread(@Param("userId")Integer userId, @Param("proId")Integer proId);

	public List<ProjectAssist> getByProjectAssistId(Integer projectAssistId);

	public List<ProjectAssist> getNewProjectAssist(Integer userId);
	
	public List<ProjectAssist> getNewTeamAssist(Integer userId);

	public List<ProjectAssist> getAllMessageContentList(@Param("userId")Integer userId,@Param("id")Integer id);

	public void updateMessage(@Param("msgDelete")String msgDelete, @Param("projectAssistId")Integer projectAssistId);

	
}