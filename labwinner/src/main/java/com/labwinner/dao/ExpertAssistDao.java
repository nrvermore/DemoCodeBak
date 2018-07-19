package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.Expert;
import com.labwinner.domain.ExpertAssist;
/**
 * 专家DAO接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface ExpertAssistDao {
	
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:19:42
	 */
	public void save(ExpertAssist expertAssist) ;
    
	
	/**
	 * 删除对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:05
	 */
	public void delete(Integer id) ;


	public List<ExpertAssist> getByExpertAndUser(@Param("userId")Integer userId,
			@Param("expertId")Integer expertId,@Param("agencyId")Integer agencyId,@Param("deleteType")Integer deleteType);


	public List<ExpertAssist> getExpertContent(@Param("userId")Integer userId,
			@Param("expertId")Integer expertId, @Param("agencyId")Integer agencyId);


	public List<ExpertAssist> getUserContent(@Param("userId")Integer userId, @Param("agencyId")Integer agencyId,@Param("expertId")Integer expertId);


	public List<ExpertAssist> getAllUserContent(@Param("userId")Integer userId, @Param("agencyId")Integer agencyId);


	public void deleteByExpert(@Param("userId")Integer userId, @Param("expertId")Integer expertId,
			@Param("agencyId")Integer agencyId,@Param("deleteType")Integer deleteType);


	public void updateIsRead(@Param("userId")Integer userId, @Param("agencyId")Integer agencyId, @Param("userId1")Integer userId1,@Param("agencyId1")Integer agencyId1,@Param("readMan")String readMan);


	public Integer getDeleteType(@Param("userId")Integer userId,  @Param("expertId")Integer expertId,
			@Param("agencyId")Integer agencyId);


	public void updateByExpert(@Param("userId")Integer userId, @Param("expertId")Integer expertId,
			@Param("agencyId")Integer agencyId,@Param("deleteType") Integer deleteType);


	public List<ExpertAssist> getAllAssist(@Param("userId")Integer userId, @Param("expertId")Integer expertId,
			@Param("agencyId")Integer agencyId);


	public List<ExpertAssist> getAllContentList(@Param("userId")Integer userId,
			@Param("agencyId")Integer agencyId, @Param("userId1")Integer userId1, @Param("agencyId1")Integer agencyId1,@Param("deleteMan")String deleteMan);


	public List<ExpertAssist> getAllAssistList(@Param("userId")Integer userId, @Param("agencyId")Integer agencyId,@Param("deleteMan")String deleteMan);


	public void updateDelete(@Param("userId")Integer userId, @Param("agencyId")Integer agencyId, @Param("userId1")Integer userId1,
			@Param("agencyId1")Integer agencyId1, @Param("deleteMan")String deleteMan);


	public int getUnReadNumber(@Param("userId")Integer userId, @Param("agencyId")int agencyId, @Param("readMan")String readMan);


	public int getUnRead(@Param("userId")Integer userId, @Param("agencyId")int agencyId,@Param("userId1")Integer userId1, @Param("agencyId1")int agencyId1, @Param("readMan")String readMan);


	public List<ExpertAssist> getNewAssistList(@Param("userId")Integer userId,
			@Param("agencyId")Integer agencyId, @Param("deleteMan")String deleteMan);


	public List<ExpertAssist> getAllMessageAssistList(@Param("userId")Integer userId,
			@Param("agencyId")Integer agencyId,@Param("deleteMan") String deleteMan);


	public List<ExpertAssist> getAllMessageContentList(@Param("userId")Integer userId,
			@Param("agencyId")Integer agencyId,@Param("userId2") Integer userId2, @Param("agencyId2")Integer agencyId2,
			@Param("deleteMan")String deleteMan);


	public void updateMessageDelete(@Param("userId")Integer userId, @Param("agencyId")Integer agencyId,
			@Param("userId2")Integer userId2, @Param("agencyId2")Integer agencyId2, @Param("deleteMan")String deleteMan);

	
}