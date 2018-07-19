package com.labwinner.service;

import java.util.List;



















import com.labwinner.domain.ExpertAssist;
/**
 * 专家service接口
 * @Description TODO
 * @author llwangi
 * @version V1.0
 * @date 2017年11月6日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface ExpertAssistService {
	
	/**
	 * 保存对象
	 * @Description TODO
	 * @author llwangi
	 * @version V1.0
	 * @date 2017年11月6日 下午4:19:42
	 */
	public void save(ExpertAssist expertAssist) ;
    
	
	/**
	 * 删除对象
	 * @Description TODO
	 * @author llwangi
	 * @version V1.0
	 * @date 2017年11月6日 下午4:20:05
	 */
	public void delete(Integer id) ;
	
	
     
	/**
	 * 删除对象
	 * @Description TODO
	 * @author llwangi
	 * @version V1.0
	 * @date 2017年11月6日 下午4:20:05
	 */
	public List<ExpertAssist> getByExpertAndUser(Integer userId,Integer expertId,Integer agencyId,Integer deleteType) ;


	public List<ExpertAssist> getExpertContent(Integer userId,
			Integer expertId, Integer agencyId);


	public List<ExpertAssist> getUserContent(Integer userId, Integer agencyId,Integer expertId);


	public List<ExpertAssist> getAllUserContent(Integer userId, Integer agencyId);


	public void deleteByExpert(Integer userId, Integer expertId,
			Integer agencyId,Integer deleteType);


	public void updateIsRead(Integer userId,Integer agencyId,Integer userId1,Integer agencyId1, String readMan);


	public void updateByExpert(Integer userId, Integer expertId,
			Integer agencyId, Integer deleteType);


	public int getDeleteType(Integer userId, Integer expertId, Integer agencyId);


	public List<ExpertAssist> getAllAssist(Integer userId, Integer expertId,
			Integer agencyId);


	public List<ExpertAssist> getAllContentList(Integer userId,
			Integer agencyId, Integer userId1, Integer agencyId1,String deleteMan);


	public List<ExpertAssist> getAllAssistList(Integer userId, Integer agencyId,String deleteMan);


	public void updateDelete(Integer userId, Integer agencyId, Integer userId1,
			Integer agencyId1, String deleteMan);


	public int getUnReadNumber(Integer userId, int agencyId, String readMan);


	public int getUnRead(Integer userId, Integer agencyId,Integer userId1, Integer agencyId1, String readMan);


	public List<ExpertAssist> getNewAssistList(Integer userId,
			Integer agencyId, String deleteMan);


	public List<ExpertAssist> getAllMessageAssistList(Integer userId,
			Integer agencyId, String deleteMan);


	public List<ExpertAssist> getAllMessageContentList(Integer userId,
			Integer agencyId, Integer userId2, Integer agencyId2,
			String deleteMan);


	public void updateMessageDelete(Integer userId, Integer agencyId,
			Integer userId2, Integer agencyId2, String deleteMan);
	

	
}