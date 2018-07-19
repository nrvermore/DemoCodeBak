package com.labwinner.service;

import java.util.List;

import com.labwinner.domain.OrderHistory;

/**
 * @Description 订单历史Service接口
 * @author liuhq
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public interface OrderHistoryService {

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public List<OrderHistory> getAll();

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public OrderHistory getById(Long id);

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public Integer save(OrderHistory orderHistory);

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void update(OrderHistory orderHistory);

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月27日
	 */
	public void delete(Long id);

}