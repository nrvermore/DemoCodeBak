package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * OrderState entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 订单状态参数表
	 */
public class OrderState implements java.io.Serializable {

	/**
	 * 订单状态主键
	 */
	private Integer orderStateId;
	
	/**
	 * 订单状态
	 */
	private String orderState;
	
	/**
	 * 原料采购表
	 */
	private Set<MaterialPurchase> materialPurchases = new HashSet<MaterialPurchase>(
			0);

	// Constructors

	/** default constructor */
	public OrderState() {
	}

	/** full constructor */
	public OrderState(String orderState, Set<MaterialPurchase> materialPurchases) {
		this.orderState = orderState;
		this.materialPurchases = materialPurchases;
	}

	public Integer getOrderStateId() {
		return orderStateId;
	}

	public void setOrderStateId(Integer orderStateId) {
		this.orderStateId = orderStateId;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public Set<MaterialPurchase> getMaterialPurchases() {
		return materialPurchases;
	}

	public void setMaterialPurchases(Set<MaterialPurchase> materialPurchases) {
		this.materialPurchases = materialPurchases;
	}

}