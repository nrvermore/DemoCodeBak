package com.labwinner.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.DesignTechnology;
import com.labwinner.domain.Device;
/**
 * 设计工艺Service接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface DesignTechnologyService {
	
	public void save(DesignTechnology designTechnology) ;

	public void delete(Integer id) ;
     
	public DesignTechnology getById(Integer id);
      
	public List<DesignTechnology> getAll();
	
	public List<DesignTechnology> getTechnologyByreactionDesignId(Integer id);

	public List<DesignTechnology> getAllPageable(String keyword);

	public void update(DesignTechnology designTechnology);

	
}