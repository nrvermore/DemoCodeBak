package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.SelfPaper;
import com.labwinner.domain.SysAttachment;

/**
 * @Description 自主论文Service接口
 * @author wangll
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface SelfPaperService {
	/**
	 * @Description 保存对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void save(SelfPaper selfPaper);
	/**
	 * @Description 更新对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void update(SelfPaper selfPaper);
	/**
	 * @Description 删除对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void delete(Integer id);
	/**
	 * @Description 根据主键获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public SelfPaper getById(Integer id);
	/**
	 * @Description 根据名字获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public List<SelfPaper> getByName(Integer id,String name,String roleName);
	/**
	 * @Description 获取所有对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public List<SelfPaper> getAll(Integer id,String roleName);
	public List<SelfPaper> getAllForApp(Integer userId, int size,String roleName);
	public List<SelfPaper> getByNameForApp(Integer userId, String keyword,
			int size,String roleName);
	public int getSelfPaperNum(Integer userId, String roleName);
	
	public List<SelfPaper> getByUserId(Integer userId, int id, String roleName);

	
}
