package com.labwinner.service;

import java.util.List;
import java.util.Map;

import com.labwinner.domain.Patent;
/**
 * @Description 专利Service接口
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface PatentService {
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
	 * @Description 根据名字获取对象
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
	
	public List<Patent> getByNameForApp(int size, String keyword);
	public int getPatentNum();
	public int getMediaNum(Integer userId);
	public List<Patent> getAgencyPatent(Integer userId,
			String roleName, int angency);
	public List<Patent> getBasePatent();
	public List<Map<String, Object>> getAllBase(String keyword);
	public List<Map<String, Object>> getAllBaseByFiledId(String keyword,
			Integer filedId);
	public List<Map<String, Object>> getAllPerson(Integer userId,
			String roleName, Integer agencyId, String keyword);
	public List<Map<String, Object>> getAllPersonByFiledId(Integer userId,
			String roleName, Integer agencyId, String keyword, Integer filedId);
	public List<Map<String, Object>> getSelfKnowledge(Integer userId,
			Integer agencyId, String keyword);
	public List<Map<String, Object>> getAllBaseKnowByFiledId(String keyword,
			Integer filedId);
	
	public List<Map<String, Object>> getSelfKnowledgeForApp(Integer userId,
			String keyword);
	
	public List<Map<String, Object>> getAllPersonKnowlist(String keyword,
			Integer filedId);
	public List<Map<String, Object>> getKnowledgeFirst(String roleName,
			Integer userId);
	public List<Map<String, Object>> getKnowledgeFirstBase();
	
	
	public Patent getByAccId(Integer knowledgeAccId);
	public int getPersonKnoledgeNum(Integer userId);
	
	public int getMaterialNum(Integer cid);

}
