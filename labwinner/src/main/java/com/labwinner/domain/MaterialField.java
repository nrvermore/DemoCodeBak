package com.labwinner.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * InventoryLocation entity. @author MyEclipse Persistence Tools
 */
/**
 * 材料分类表
 */
public class MaterialField implements java.io.Serializable {

	/**
	 * 材料分类主键
	 */
	private Integer cid;

	/**
	 * 材料分类父主键
	 */
	private Integer pid;

	/**
	 * 材料分类名称
	 */
	private String label;

	private String parentName;

	private List<MaterialField> children = new ArrayList<MaterialField>();

	public MaterialField() {
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<MaterialField> getChildren() {
		return children;
	}

	public void setChildren(List<MaterialField> children) {
		this.children = children;
	}

	

}