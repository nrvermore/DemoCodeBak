package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.SelfPaper;
import com.labwinner.domain.SysAttachment;
/**
 * @Description 自写论文Dao
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface SelfPaperDao {
	/**
	 * @Description 保存对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void save(SelfPaper selfPaper);
	/**
	 * @Description 更新对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void update(SelfPaper selfPaper);
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
	public SelfPaper getById(Integer id);
	/**
	 * @Description 根据name获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public List<SelfPaper> getByName(@Param("id")Integer id,@Param("name")String name,@Param("roleName")String roleName);
	/**
	 * @Description 获取所有对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public List<SelfPaper> getAll(@Param("id")Integer id,@Param("roleName")String roleName);
	
	public List<SelfPaper> getAllForApp(@Param("userId")Integer userId, @Param("size")int size,@Param("roleName")String roleName);
	
	
	public List<SelfPaper> getByNameForApp(@Param("userId")Integer userId, @Param("keyword")String keyword,
			@Param("size") int size,@Param("roleName")String roleName);
	public int getSelfPaperNum(@Param("userId")Integer userId, @Param("roleName")String roleName);
	
	public List<SelfPaper> getByUserId(@Param("userId")Integer userId, @Param("id")int id, @Param("roleName")String roleName);
	
	
}
