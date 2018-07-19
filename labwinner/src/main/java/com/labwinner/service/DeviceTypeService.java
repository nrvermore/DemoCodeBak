package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.DeviceType;

/**
 * 设备类型Service接口 
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午4:57:51
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

public interface DeviceTypeService{

	/**
	 * 保存对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @return 
	 * @date 2017年5月19日 下午4:58:25
	 */
	public void save(DeviceType deviceType);
    
	
	/**
	 * 删除对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:58:59
	 */
	public void delete(java.lang.Integer id);
    
	
	/**
	 * 根据id获取对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:59:14
	 */
	public DeviceType getById(java.lang.Integer id);
    
	
	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:59:56
	 */
	public List<DeviceType> getAll() ;
   
	public List<DeviceType> getAllName() ;
	/**
	 * 获取所有对象分页列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:59:56
	 */
	public List<DeviceType> getAllPageable(String keyword) ;
	
	/**
	 * 修改对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:00:33
	 */
	public void update(DeviceType deviceType); 
}