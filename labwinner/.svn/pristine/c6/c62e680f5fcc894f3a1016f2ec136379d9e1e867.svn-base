package com.labwinner.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Patent;
/**
 * @Description 专利基本信息Dao
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface PatentDao {
	/**
	 * @Description 保存对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void save(Patent patent);
	/**
	 * @Description 更新对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void update(Patent patent);
	/**
	 * @Description 删除对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void delete(Integer id);
	/**
	 * @Description 根据主键获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public Patent getById(Integer id);
	/**
	 * @Description 根据name获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public List<Patent> getByName(String name);
	/**
	 * @Description 获取所有对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public List<Patent> getAll();
	
	public List<Patent> getAllForApp(int size);
	
	public List<Patent> getByNameForApp(@Param("size")int size, @Param("keyword")String keyword);
	
	public int getPatentNum();
	
	public int getMediaNum(Integer userId);
	
	public List<Patent> getAgencyPatent(@Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("angency")int angency);
	
	public List<Patent> getBasePatent();
	
	public List<Map<String, Object>> getAllBase(@Param("keyword")String keyword);
	
	public List<Map<String, Object>> getAllBaseByFiledId(@Param("keyword")String keyword,
			@Param("filedId")Integer filedId);
	
	public List<Map<String, Object>> getAllPerson(@Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("agencyId")Integer agencyId,@Param("keyword")String keyword);
	
	public List<Map<String, Object>> getAllPersonByFiledId(@Param("userId")Integer userId,
			@Param("roleName")String roleName, @Param("agencyId")Integer agencyId,@Param("keyword")String keyword,@Param("filedId") Integer filedId);
	
	public List<Map<String, Object>> getSelfKnowledge(@Param("userId")Integer userId,
			@Param("agencyId")Integer agencyId, @Param("keyword")String keyword);
	
	public List<Map<String, Object>> getAllBaseKnowByFiledId(@Param("keyword")String keyword,
			@Param("filedId")Integer filedId);
	
	public List<Map<String, Object>> getSelfKnowledgeForApp(@Param("userId")Integer userId,
			@Param("keyword")String keyword);
	
	public List<Map<String, Object>> getAllPersonKnowlist(@Param("keyword")String keyword,
			@Param("filedId")Integer filedId);
	
	public List<Map<String, Object>> getKnowledgeFirst(@Param("keyword")String roleName,
			@Param("keyword")Integer userId);
	
	public List<Map<String, Object>> getKnowledgeFirstBase();
	
	
	public Patent getByAccId(Integer knowledgeAccId);
	
	
	public int getPersonKnoledgeNum(Integer userId);
	
	public int getMaterialNum(Integer cid);

}
