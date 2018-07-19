package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.ModuleType;
/**
 * 模块类型DAO接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface ModuleTypeDao {
	
	/**
	 * 保存对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:19:42
	 */
	public void save(ModuleType moduleType) ;
    
	
	/**
	 * 删除对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:05
	 */
	public void delete(Integer id) ;
     
	
	/**
	 * 根据id查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:29
	 */
	public ModuleType getById(Integer id);
	
	/**
	 * 根据id查找对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:20:29
	 */
//	public List<ModuleType> getByIdAllDesc(Integer id);
	
     /**
      * 显示所有对象
      * @Description TODO
      * @author suhg
      * @version V1.0
      * @date 2017年5月18日 下午4:20:54
      */
	public List<ModuleType> getAll();
	
	public List<ModuleType> getAllName();
	
	public List<ModuleType> getAllByType(Integer id);
	
	//获取除热门标签的列表
	public List<ModuleType> getByType();
	
	/**
	 * 修改对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午4:50:32
	 */
	public void update(ModuleType moduleType);
}