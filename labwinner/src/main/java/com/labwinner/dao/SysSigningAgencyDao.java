package com.labwinner.dao;

import java.util.List;

import com.labwinner.domain.SysMenu;
import com.labwinner.domain.SysSigningAgency;

/**
 * @Description 签约机构Dao
 * @author liuhq
 * @version V1.0
 * @date 2017年6月7日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface SysSigningAgencyDao {

	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月7日
	 */
	public List<SysSigningAgency> getAll();

	/**
	 * @Description 根据查询条件获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月7日
	 */
	public List<SysSigningAgency> getAllPageable(String filter);
	
	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月7日 下午4:24:48
	 */
	SysSigningAgency getById(Long id);

	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月7日 下午4:24:55
	 */
	void save(SysSigningAgency sysSigningAgency);

	/**
	 * @Description 更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月7日 下午4:25:01
	 */
	void update(SysSigningAgency sysSigningAgency);

	/**
	 * @Description 删除对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月7日 下午4:25:06
	 */
	void delete(Long id);

}
