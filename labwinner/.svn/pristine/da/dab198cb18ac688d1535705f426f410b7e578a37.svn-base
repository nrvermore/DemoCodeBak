package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Inventories {
	
	/**
	 * 库存主键
	 */
	private Integer inventoryId;
	
	/**
	 * 库存名称
	 */
	private String inventoryName;
	
	/**
	 * 度量单位主键
	 */
	private Measurement measurement;
	
	/**
	 * 度量单位主键
	 */
	private Integer measureUnitId;
	
	
	/**
	 * 用户（负责人）
	 */
	private SysUser sysUser;
	
	
	/**
	 * 库存位置主键
	 */
	private InventoryLocation inventoryLocation;
	
	/**
	 * 系统条形码
	 */
	private String barCode;
	
	/**
	 * 二维码图片
	 */
	private String qrName;
	
	/**
	 * 库存分组
	 */
	private InventoryGroups inventoryGroups;
	
	private QrInfo qrInfo;
	
	/**
	 * 实际可用重量
	 */
	private Double actAvaWei;
	
	/**
	 * 变更前重量
	 */
	private Double changeBefore;
	
	/**
	 * 启用日期
	 */
	private Date comDate;
	
	/**
	 * 到期日期
	 */
	private Date maturityDate;
	

	/**
	 * 价格
	 */
	private String price;
	
	/**
	 * 币种主键
	 */
	private PriceCurrency priceCurrency;
	
	/**
	 * 创建人
	 */
	private String creater;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 修改人
	 */
	private String modifier;
	
	/**
	 * 修改日期
	 */
	private Date modifyDate;
	
	
	/**
	 * 设计原料表
	 */
	private Set<ReactionDesignChemical> reactionDesignChemicals = new HashSet<ReactionDesignChemical>(
			0);
	
	/**
	 * 执行原料表
	 */
	private List<ExecuteChemical> executeChemicals = new ArrayList<ExecuteChemical>(
			0);
	
	/**
	 * 库存变更表
	 */
	private Set<InventoryModify> inventoryModifies = new HashSet<InventoryModify>(
			0);
	
	public Inventories (){}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}
	
	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public InventoryLocation getInventoryLocation() {
		return inventoryLocation;
	}

	public void setInventoryLocation(InventoryLocation inventoryLocation) {
		this.inventoryLocation = inventoryLocation;
	}

	public String getQrName() {
		return qrName;
	}

	public void setQrName(String qrName) {
		this.qrName = qrName;
	}

	public InventoryGroups getInventoryGroups() {
		return inventoryGroups;
	}

	public void setInventoryGroups(InventoryGroups inventoryGroups) {
		this.inventoryGroups = inventoryGroups;
	}

	public Double getActAvaWei() {
		return actAvaWei;
	}

	public void setActAvaWei(Double actAvaWei) {
		this.actAvaWei = actAvaWei;
	}

	public Double getChangeBefore() {
		return changeBefore;
	}

	public void setChangeBefore(Double changeBefore) {
		this.changeBefore = changeBefore;
	}

	public Date getComDate() {
		return comDate;
	}

	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Set<ReactionDesignChemical> getReactionDesignChemicals() {
		return reactionDesignChemicals;
	}

	public void setReactionDesignChemicals(
			Set<ReactionDesignChemical> reactionDesignChemicals) {
		this.reactionDesignChemicals = reactionDesignChemicals;
	}

	public List<ExecuteChemical> getExecuteChemicals() {
		return executeChemicals;
	}

	public void setExecuteChemicals(List<ExecuteChemical> executeChemicals) {
		this.executeChemicals = executeChemicals;
	}

	public Set<InventoryModify> getInventoryModifies() {
		return inventoryModifies;
	}

	public void setInventoryModifies(Set<InventoryModify> inventoryModifies) {
		this.inventoryModifies = inventoryModifies;
	}

	public Integer getMeasureUnitId() {
		return measureUnitId;
	}

	public void setMeasureUnitId(Integer measureUnitId) {
		this.measureUnitId = measureUnitId;
	}

	public QrInfo getQrInfo() {
		return qrInfo;
	}

	public void setQrInfo(QrInfo qrInfo) {
		this.qrInfo = qrInfo;
	}
	
	public PriceCurrency getPriceCurrency() {
		return priceCurrency;
	}

	public void setPriceCurrency(PriceCurrency priceCurrency) {
		this.priceCurrency = priceCurrency;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
