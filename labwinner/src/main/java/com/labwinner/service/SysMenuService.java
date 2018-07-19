package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.SysMenu;
import com.labwinner.domain.SysUser;

/**
 * @Description 菜单Service接口
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface SysMenuService {

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public List<SysMenu> getAll();
	
	/**
	 * @Description 根据查询条件获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public List<SysMenu> getAllPageable(String filter);

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public SysMenu getById(Long id);

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public Integer save(SysMenu sysMenu);

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public void update(SysMenu sysMenu);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public void delete(Long id);
	
	/**
	 * @Description 逻辑删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	void updateFlag(Long id,Integer flag);

	public List<SysMenu> getAllFirst();

	public SysMenu getTree(Integer menuId);

}