package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.DesignTechnology;
/**
 * 设计工艺DAO接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface DesignTechnologyDao {
	
	
	public void save(DesignTechnology designTechnology) ;

	public void delete(Integer id) ;
     
	public DesignTechnology getById(Integer id);
      
	public List<DesignTechnology> getAll();
	
	public List<DesignTechnology> getTechnologyByreactionDesignId(Integer id);

	public List<DesignTechnology> getAllPageable(String keyword);

	public void update(DesignTechnology designTechnology);

}