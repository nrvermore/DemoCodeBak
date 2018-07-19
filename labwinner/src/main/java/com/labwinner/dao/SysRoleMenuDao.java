package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.SysRoleMenu;

/**
 * @Description 角色菜单Dao
 * @author liuhq
 * @version V1.0
 * @date 2017年6月15日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface SysRoleMenuDao {

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	public List<SysRoleMenu> getAll();

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	SysRoleMenu getById(Long id);

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	void save(SysRoleMenu sysRoleMenu);

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	void update(SysRoleMenu sysRoleMenu);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	void delete(Long id);

	/**
	 * @Description 根据角色主键删除角色菜单
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	public void deleteByRoleId(Long id);

	/**
	 * @Description 根据角色主键获取角色菜单
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月15日
	 */
	public List<SysRoleMenu> getByRoleId(Long id);
	
}