package com.labwinner.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * InventoryLocation entity. @author MyEclipse Persistence Tools
 */
/**
 * 库存位置表
 */
public class InventoryLocation implements java.io.Serializable {

	/**
	 * 库存位置主键
	 */
	private Integer cid;

	/**
	 * 库存位置父主键
	 */
	private Integer pid;

	/**
	 * 位置名称
	 */
	private String label;

	private String parentName;
	
	private String barcode;
	
	private String qrPath;

	private List<InventoryLocation> children = new ArrayList<InventoryLocation>();

	public InventoryLocation() {
	}

	/**
	 * 库存信息表
	 */
	private Set<Inventories> inventories = new HashSet<Inventories>(0);

	public Set<Inventories> getInventories() {
		return inventories;
	}

	public void setInventories(Set<Inventories> inventories) {
		this.inventories = inventories;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<InventoryLocation> getChildren() {
		return children;
	}

	public void setChildren(List<InventoryLocation> children) {
		this.children = children;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getQrPath() {
		return qrPath;
	}

	public void setQrPath(String qrPath) {
		this.qrPath = qrPath;
	};
	
}