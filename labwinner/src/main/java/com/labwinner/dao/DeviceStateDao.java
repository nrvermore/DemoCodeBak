package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceState;

/**
 * 设备状态DAO接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午3:32:50
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface DeviceStateDao {
	
    /**
     * 保存的方法
     * @Description TODO
     * @author suhg
     * @version V1.0
     * @date 2017年5月19日 下午3:33:35
     */
	public void save(DeviceState deviceState);
	
	
    /**
     * 删除对象的方法
     * @Description TODO
     * @author suhg
     * @version V1.0
     * @date 2017年5月19日 下午3:35:36
     */
	public void delete(java.lang.Integer id);
    
	
	/**
	 * 根据id查找对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午3:36:03
	 */
	public DeviceState getById(java.lang.Integer id);
    
	
	/**
	 * 获取所有对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午3:36:40
	 */
	public List<DeviceState> getAll(); 
    
	/**
	 * 分页显示所有对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:54
	 */
	public List<DeviceState> getAllPageable(String keyword);
	
	
	
	/**
	 * 修改多选的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午3:37:01
	 */
	public void update(DeviceState deviceState); 

	
}