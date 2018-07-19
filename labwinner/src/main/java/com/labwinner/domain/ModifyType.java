package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * ModifyType entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 变更类型
	 */
public class ModifyType implements java.io.Serializable {

	/**
	 * 变更类型主键
	 */
	private Integer modifyTypeId;
	
	/**
	 * 变更类型
	 */
	private String modifyType;
	
	/**
	 * 库存变更表
	 */
	private Set<InventoryModify> inventoryModifies = new HashSet<InventoryModify>(
			0);

	// Constructors

	/** default constructor */
	public ModifyType() {
	}

	/** full constructor */
	public ModifyType(String modifyType, Set<InventoryModify> inventoryModifies) {
		this.modifyType = modifyType;
		this.inventoryModifies = inventoryModifies;
	}

	public Integer getModifyTypeId() {
		return modifyTypeId;
	}

	public void setModifyTypeId(Integer modifyTypeId) {
		this.modifyTypeId = modifyTypeId;
	}

	public String getModifyType() {
		return modifyType;
	}

	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}

	public Set<InventoryModify> getInventoryModifies() {
		return inventoryModifies;
	}

	public void setInventoryModifies(Set<InventoryModify> inventoryModifies) {
		this.inventoryModifies = inventoryModifies;
	}

	

}