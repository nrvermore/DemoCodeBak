package com.labwinner.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	/**
	 * MaterialType entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 原料类型表
	 */
public class MaterialType implements java.io.Serializable {

	/**
	 * 原料类型主键
	 */
	private Integer materialTypeId;
	
	/**
	 * 原料类型
	 */
	private String label;
	
	/**
	 * 原料类型子节点
	 */
	private Integer materialTypeCodeId;
	
	/**
	 * 原料类型主节点
	 */
	private Integer materialCategoryId;
	
	private String parentName;
	
	private List<MaterialType> children = new ArrayList<MaterialType>();
	
	/**
	 * 库存信息表
	 */
	private Set<Inventory> inventories = new HashSet<Inventory>(0);

	public MaterialType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaterialType(Integer materialTypeId, String label,
			Integer materialTypeCodeId, Integer materialCategoryId,
			String parentName, List<MaterialType> children,
			Set<Inventory> inventories) {
		super();
		this.materialTypeId = materialTypeId;
		this.label = label;
		this.materialTypeCodeId = materialTypeCodeId;
		this.materialCategoryId = materialCategoryId;
		this.parentName = parentName;
		this.children = children;
		this.inventories = inventories;
	}

	public Integer getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(Integer materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getMaterialTypeCodeId() {
		return materialTypeCodeId;
	}

	public void setMaterialTypeCodeId(Integer materialTypeCodeId) {
		this.materialTypeCodeId = materialTypeCodeId;
	}

	public Integer getMaterialCategoryId() {
		return materialCategoryId;
	}

	public void setMaterialCategoryId(Integer materialCategoryId) {
		this.materialCategoryId = materialCategoryId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<MaterialType> getChildren() {
		return children;
	}

	public void setChildren(List<MaterialType> children) {
		this.children = children;
	}

	public Set<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}

	

}