package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.SysUserRole;

/**
 * @Description 用户角色Dao
 * @author liuhq
 * @version V1.0
 * @date 2017年6月16日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface SysUserRoleDao {

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	public List<SysUserRole> getAll();
	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	public List<SysUserRole> getProAll();

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	SysUserRole getById(Long id);

	/**
	 * roleId获取用户信息
	 */
	public List<SysUserRole> getUser(Long id);
	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	void save(SysUserRole sysUserRole);

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	void update(SysUserRole sysUserRole);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	void delete(Long id);

	/**
	 * @Description 根据角色主键删除角色菜单
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	public void deleteByRoleId(Long id);

	/**
	 * @Description 根据角色主键获取角色菜单
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月16日
	 */
	public List<SysUserRole> getByRoleId(Long id);

}
