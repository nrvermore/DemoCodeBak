package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labwinner.dao.OrderHistoryDao;
import com.labwinner.domain.OrderHistory;
import com.labwinner.service.OrderHistoryService;

/**
 * @Description 订单历史Service实现
 * @author liuhq
 * @version V1.0
 * @date 2017年6月27日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
	private static final Logger log = LoggerFactory
			.getLogger(OrderHistoryServiceImpl.class);

	@Autowired
	private OrderHistoryDao orderHistoryDao;

	/**
	 * {@inheritDoc}
	 */
	public List<OrderHistory> getAll() {
		log.debug("finding all OrderHistory instances");
		try {
			return orderHistoryDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public OrderHistory getById(Long id) {
		log.debug("getting OrderHistory instance with id: " + id);
		try {

			OrderHistory orderHistory = (OrderHistory) orderHistoryDao
					.getById(id);
			return orderHistory;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(OrderHistory orderHistory) {
		log.debug("saving OrderHistory instance");
		try {
			log.debug("save successful");
			orderHistoryDao.save(orderHistory);
			return orderHistory.getOrderHistoryId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(OrderHistory orderHistory) {
		log.debug("saving OrderHistory instance");
		try {
			orderHistoryDao.update(orderHistory);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Long id) {
		log.debug("deleting OrderHistory instance");
		try {
			orderHistoryDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

}