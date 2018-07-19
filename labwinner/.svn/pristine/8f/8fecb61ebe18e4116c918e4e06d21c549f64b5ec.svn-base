package com.labwinner.domain;

import java.util.Date;

/**
 * @Description 订单历史
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class OrderHistory implements java.io.Serializable {

	// Fields

	private Integer orderHistoryId;
	private MaterialPurchase materialPurchase;
	private Date orderHistoryDate;
	private String orderRemarks;

	// Constructors

	/** default constructor */
	public OrderHistory() {
	}

	/** full constructor */
	public OrderHistory(MaterialPurchase materialPurchase,
			Date orderHistoryDate, String orderRemarks) {
		this.materialPurchase = materialPurchase;
		this.orderHistoryDate = orderHistoryDate;
		this.orderRemarks = orderRemarks;
	}

	// Property accessors
	
	public Integer getOrderHistoryId() {
		return orderHistoryId;
	}

	public void setOrderHistoryId(Integer orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}

	public MaterialPurchase getMaterialPurchase() {
		return materialPurchase;
	}

	public void setMaterialPurchase(MaterialPurchase materialPurchase) {
		this.materialPurchase = materialPurchase;
	}

	public Date getOrderHistoryDate() {
		return orderHistoryDate;
	}

	public void setOrderHistoryDate(Date orderHistoryDate) {
		this.orderHistoryDate = orderHistoryDate;
	}

	public String getOrderRemarks() {
		return orderRemarks;
	}

	public void setOrderRemarks(String orderRemarks) {
		this.orderRemarks = orderRemarks;
	}

}