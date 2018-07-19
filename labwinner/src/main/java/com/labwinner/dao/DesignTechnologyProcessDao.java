package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.DesignTechnologyProcess;
/**
 * 设计工艺步骤DAO接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface DesignTechnologyProcessDao {
	
	
	public void save(DesignTechnologyProcess designTechnologyProcess) ;

	public void delete(Integer id) ;
     
	public DesignTechnologyProcess getById(Integer id);
      
	public List<DesignTechnologyProcess> getAll();

	public List<DesignTechnologyProcess> getAllPageable(String keyword);

	public void update(DesignTechnologyProcess designTechnologyProcess);

}