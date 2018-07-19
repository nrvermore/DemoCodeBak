package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.labwinner.domain.OrderHistory;
import com.labwinner.service.OrderHistoryService;

import java.util.List;

/**
 * @Description 订单历史Controller
 * @author liuhq
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService orderHistoryService;

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
	public List<OrderHistory> getAll() {
		return orderHistoryService.getAll();
	}

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/orderHistory/{id}", method = RequestMethod.GET)
	public OrderHistory getById(@PathVariable("id") Long id) {
		return orderHistoryService.getById(id);
	}

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/orderHistory", method = RequestMethod.POST)
	public Integer save(@RequestBody OrderHistory orderHistory) {
		return orderHistoryService.save(orderHistory);
	}

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/orderHistory", method = RequestMethod.PUT)
	public void update(@RequestBody OrderHistory orderHistory) {
		orderHistoryService.update(orderHistory);
	}

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/orderHistory/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		orderHistoryService.delete(id);
	}

}
