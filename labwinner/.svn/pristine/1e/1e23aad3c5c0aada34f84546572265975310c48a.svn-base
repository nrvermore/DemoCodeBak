package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.JournalUser;
/**
 * @Description 科技论文权限Dao
 * @author wangll
 * @version V1.0
 * @date 2017年6月28日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface JournalUserDao {
	/**
	 * @Description 保存所有对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月28日
	 */
	public void save(JournalUser journalUser);
	/**
	 * @Description 更新对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月28日
	 */
	public void update(JournalUser journalUser);
	/**
	 * @Description 删除对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月28日
	 */
	public void delete(@Param("id")Integer id,@Param("classId")Integer classId);
	/**
	 * @Description 根据id获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月28日
	 */
	public List<JournalUser> getById(@Param("id")Integer id,@Param("classId")Integer classId);

}
