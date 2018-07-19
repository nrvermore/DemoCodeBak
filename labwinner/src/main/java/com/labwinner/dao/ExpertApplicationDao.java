package com.labwinner.dao;


import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.ExpertApplication;
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
public interface ExpertApplicationDao {
	
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:19:42
	 */
	public void save(ExpertApplication expertApplication) ;

	public ExpertApplication getByUser(@Param("userId")Integer userId, @Param("agencyId")Integer agencyId);
    
	
	

	
}