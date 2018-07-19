package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * PriceCurrency entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 价格币种表
	 */
public class PriceCurrency implements java.io.Serializable {

	/**
	 * 价格币种主键
	 */
	private Integer priCurId;
	
	/**
	 * 价格币种
	 */
	private String curType;
		
	/**
	 * 价格币种
	 */
	private String curName;
	
	/**
	 * 库存信息表
	 */
	private Set<Inventory> inventories = new HashSet<Inventory>(0);
	
	/**
	 * 原料采购表
	 */
	private Set<MaterialPurchase> materialPurchases = new HashSet<MaterialPurchase>(
			0);

	public PriceCurrency() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PriceCurrency(Integer priCurId, String curType, String curName,
			Set<Inventory> inventories, Set<MaterialPurchase> materialPurchases) {
		super();
		this.priCurId = priCurId;
		this.curType = curType;
		this.curName = curName;
		this.inventories = inventories;
		this.materialPurchases = materialPurchases;
	}

	public Integer getPriCurId() {
		return priCurId;
	}

	public void setPriCurId(Integer priCurId) {
		this.priCurId = priCurId;
	}

	public String getCurType() {
		return curType;
	}

	public void setCurType(String curType) {
		this.curType = curType;
	}

	public String getCurName() {
		return curName;
	}

	public void setCurName(String curName) {
		this.curName = curName;
	}

	public Set<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Set<MaterialPurchase> getMaterialPurchases() {
		return materialPurchases;
	}

	public void setMaterialPurchases(Set<MaterialPurchase> materialPurchases) {
		this.materialPurchases = materialPurchases;
	}

	
}