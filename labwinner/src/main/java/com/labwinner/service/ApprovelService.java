package com.labwinner.service;

import java.util.List;
import java.util.Map;

import com.labwinner.domain.Approvel;
import com.labwinner.domain.ApprovelRefuse;

/**
 * @Description 附件Service接口
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface ApprovelService {

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public List<Approvel> getAll(String roleName,Integer userId);

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public Approvel getById(Integer id);

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public Integer save(Approvel approvel);

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public void update(Approvel approvel);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月9日
	 */
	public void delete(Integer id);

	public List<Approvel> getByOrderNumber(String roleName, Integer userId,
			String keyword);

	public List<Map<String, Object>> getRefuseList();

	public List<Map<String, Object>> getOrderState();

	public List<Map<String, Object>> getAllForFirst(String roleName, Integer userId);
	
	public List<Approvel> getScreenOrder();
	
	public Integer getAllOrders();

}