package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * ProcChannelParameter entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 采购渠道参数表
	 */
public class ProcChannelParameter implements java.io.Serializable {

	/**
	 * 采购渠道主键
	 */
	private Integer procChannelId;
	/**
	 * 采购渠道名称
	 */
	private String procChannelName;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 原料采购表
	 */
	private Set<MaterialPurchase> materialPurchases = new HashSet<MaterialPurchase>(
			0);

	// Constructors

	/** default constructor */
	public ProcChannelParameter() {
	}

	/** full constructor */
	public ProcChannelParameter(String procChannelName, String remarks,
			Set<MaterialPurchase> materialPurchases) {
		this.procChannelName = procChannelName;
		this.remarks = remarks;
		this.materialPurchases = materialPurchases;
	}

	public Integer getProcChannelId() {
		return procChannelId;
	}

	public void setProcChannelId(Integer procChannelId) {
		this.procChannelId = procChannelId;
	}

	public String getProcChannelName() {
		return procChannelName;
	}

	public void setProcChannelName(String procChannelName) {
		this.procChannelName = procChannelName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set<MaterialPurchase> getMaterialPurchases() {
		return materialPurchases;
	}

	public void setMaterialPurchases(Set<MaterialPurchase> materialPurchases) {
		this.materialPurchases = materialPurchases;
	}

	

}