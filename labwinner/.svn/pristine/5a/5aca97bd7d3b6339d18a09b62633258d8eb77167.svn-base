package com.labwinner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.SysUser;

/**
 * @Description 用户Dao
 * @author liuhq
 * @version V1.0
 * @date 2017年5月17日 上午11:48:36
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface SysUserDao {

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:11
	 */
	public List<SysUser> getAll();
	
	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:11
	 */
	public List<SysUser> getProList();
	
	/**
	 * @Description 获取所有项目负责人对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:11
	 */
	public List<SysUser> getProRoleAll();

	/**
	 * @Description 获取所有对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:11
	 */
	public List<SysUser> getContacter();

	/**
	 * @Description 根据查询条件获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:11
	 */
	public List<SysUser> getAllPageable(String filter);
	
	/**
	 * @Description 获取user对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:11
	 */
	public List<SysUser> getUser(@Param("proId")Integer proId,@Param("roleId")Integer roleId);

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	SysUser getById(Long id);

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	void save(SysUser user);
	
	public List<SysUser> getUserName();
	
	public void saveNoImage(SysUser sysUser);

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	void update(SysUser user);
	
	/**
	 * @Description 更新对象状态
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	public void updateState(SysUser sysUser);
	/**
	 * @Description APP个人设置
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	public void updateAppPersonal(SysUser sysUser);
	/**
	 * @Description 修改密码
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	void updatePassWord(SysUser sysUser);
	
	/**
	 * @Description 个人设置
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	void updatePersonal(SysUser sysUser);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	void delete(Long userId);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	void delete(SysUser user);
	
	/**
	 * @Description 根据用户名获取用户对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	public List<SysUser> getByUsername(String username);
	
	/**
	 * @Description 根据用户名和密码获取用户对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月2日
	 */
	public List<SysUser> getByUsernameAndPassword(
			@Param("username") String username,
			@Param("password") String password);

	public SysUser getByExpertId(Integer expertId);

	public List<SysUser> getAllPhone();

	public List<SysUser> getByNameOrPho(String username);
	
	public Integer findByRoleId(Long roleId);

	public SysUser getByBasUser(@Param("userId")Long userId,@Param("agencyId")Integer agencyId);

	public SysUser getExpertUser(Integer expertId);

	public List<SysUser> getTeamUsers();
	
	public List<SysUser> getByKeyword(String username);
	
	public List<SysUser> getUsers(String roleName);

	public Integer getAllUser();
}
