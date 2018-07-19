package com.labwinner.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

	/**
	 * Inventory entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 库存信息表
	 */
public class Inventory implements java.io.Serializable {

	/**
	 * 库存主键
	 */
	private Integer inventoryId;
	
	/**
	 * 原料类型主键
	 */
	private MaterialType materialType;
	
	/**
	 * 度量单位主键
	 */
	private Measurement measurement;
	
	/**
	 * 纯度浓度单位主键
	 */
	private Measurement conPurMeasurement;
	
	/**
	 * 用户（负责人）
	 */
	private SysUser sysUser;
	
	/**
	 * 保密级别主键
	 */
	private SecureRank secureRank;
	
	/**
	 * 供应商主键
	 */
	private Supplier supplier;
	
	/**
	 * 样品主键
	 */
	private Prototype prototype;
	
	/**
	 * 库存位置主键
	 */
	private InventoryLocation inventoryLocation;
	
	/**
	 * 化学品基础信息主键
	 */
	private ChemicalParameter chemicalParameter;
	
	/**
	 * 币种主键
	 */
	private PriceCurrency priceCurrency;
	
	
	private QrInfo qrInfo;
	
	private InventoryGroup inventoryGroup;
	
	/**
	 * 实际可用重量
	 */
	private Double actAvaWei;
	
	/**
	 * 变更前重量
	 */
	private Double changeBefore;
	
	/**
	 * 批号
	 */
	private String batchNumber;
	
	/**
	 * 启用日期
	 */
	private Date comDate;
	
	/**
	 * 到期日期
	 */
	private Date maturityDate;
	
	/**
	 * 溶剂
	 */
	private String dissolvant;
	
	/**
	 * 价格
	 */
	private String price;
	
	/**
	 * 浓度纯度
	 */
	private String conPur;
	
	/**
	 * 库存数量
	 */
	private Integer InventoryNumber;
	
	/**
	 * 密度差异
	 */
	private String densityVariation;
	
	/**
	 * 系统条形码
	 */
	private String barCode;
	
	/**
	 * 描述
	 */
	private String description;
	
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
	 * 库存名称
	 */
	private String inventoryName;
	
	/**
	 * 设计原料表
	 */
	private Set<ReactionDesignChemical> reactionDesignChemicals = new HashSet<ReactionDesignChemical>(
			0);
	
	/**
	 * 执行原料表
	 */
	private Set<ExecuteChemical> executeChemicals = new HashSet<ExecuteChemical>(
			0);
	
	/**
	 * 库存变更表
	 */
	private Set<InventoryModify> inventoryModifies = new HashSet<InventoryModify>(
			0);
	
	/**
	 * 库存权限表
	 */
	private Set<InventoryUser> inventoryUsers = new HashSet<InventoryUser>(
			0);
	
	/**
	 * 化学品基础信息图片表
	 */
	private Set<ChemicalImage> chemicalImages = new HashSet<ChemicalImage>(0);

	// Constructors

	/** default constructor */
	public Inventory() {
	}

	/** full constructor */
	public Inventory(MaterialType materialType, Measurement measurement,
			SysUser sysUser, SecureRank secureRank, Supplier supplier,
			Prototype prototype, InventoryLocation inventoryLocation,
			ChemicalParameter chemicalParameter, PriceCurrency priceCurrency,
			Double actAvaWei, String batchNumber, Date comDate,
			Date maturityDate, String dissolvant, String price, String conPur,
			String densityVariation, String barCode, String description,
			String creater, Date createDate, String modifier, Date modifyDate,
			String inventoryName,Integer inventoryNumber,
			Set<ReactionDesignChemical> reactionDesignChemicals,
			Set<ExecuteChemical> executeChemicals,
			Set<InventoryUser> inventoryUsers,
			Set<InventoryModify> inventoryModifies,
			Set<ChemicalImage> chemicalImages) {
		this.materialType = materialType;
		this.measurement = measurement;
		this.sysUser = sysUser;
		this.secureRank = secureRank;
		this.supplier = supplier;
		this.prototype = prototype;
		this.inventoryLocation = inventoryLocation;
		this.chemicalParameter = chemicalParameter;
		this.priceCurrency = priceCurrency;
		this.actAvaWei = actAvaWei;
		this.batchNumber = batchNumber;
		this.comDate = comDate;
		this.maturityDate = maturityDate;
		this.dissolvant = dissolvant;
		this.price = price;
		this.conPur = conPur;
		this.densityVariation = densityVariation;
		this.barCode = barCode;
		this.InventoryNumber = inventoryNumber;
		this.description = description;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.inventoryName = inventoryName;
		this.reactionDesignChemicals = reactionDesignChemicals;
		this.executeChemicals = executeChemicals;
		this.inventoryUsers = inventoryUsers;
		this.inventoryModifies = inventoryModifies;
		this.chemicalImages = chemicalImages;
	}

	public Set<InventoryUser> getInventoryUsers() {
		return inventoryUsers;
	}

	public void setInventoryUsers(Set<InventoryUser> inventoryUsers) {
		this.inventoryUsers = inventoryUsers;
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public MaterialType getMaterialType() {
		return materialType;
	}

	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
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

	public SecureRank getSecureRank() {
		return secureRank;
	}

	public void setSecureRank(SecureRank secureRank) {
		this.secureRank = secureRank;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Prototype getPrototype() {
		return prototype;
	}

	public void setPrototype(Prototype prototype) {
		this.prototype = prototype;
	}

	public InventoryLocation getInventoryLocation() {
		return inventoryLocation;
	}

	public void setInventoryLocation(InventoryLocation inventoryLocation) {
		this.inventoryLocation = inventoryLocation;
	}

	public ChemicalParameter getChemicalParameter() {
		return chemicalParameter;
	}

	public void setChemicalParameter(ChemicalParameter chemicalParameter) {
		this.chemicalParameter = chemicalParameter;
	}

	public PriceCurrency getPriceCurrency() {
		return priceCurrency;
	}

	public void setPriceCurrency(PriceCurrency priceCurrency) {
		this.priceCurrency = priceCurrency;
	}

	public Double getActAvaWei() {
		return actAvaWei;
	}

	public void setActAvaWei(Double actAvaWei) {
		this.actAvaWei = actAvaWei;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
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

	public String getDissolvant() {
		return dissolvant;
	}

	public void setDissolvant(String dissolvant) {
		this.dissolvant = dissolvant;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getConPur() {
		return conPur;
	}

	public void setConPur(String conPur) {
		this.conPur = conPur;
	}

	public String getDensityVariation() {
		return densityVariation;
	}

	public void setDensityVariation(String densityVariation) {
		this.densityVariation = densityVariation;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public Set<ReactionDesignChemical> getReactionDesignChemicals() {
		return reactionDesignChemicals;
	}

	public void setReactionDesignChemicals(
			Set<ReactionDesignChemical> reactionDesignChemicals) {
		this.reactionDesignChemicals = reactionDesignChemicals;
	}

	public Set<ExecuteChemical> getExecuteChemicals() {
		return executeChemicals;
	}

	public void setExecuteChemicals(Set<ExecuteChemical> executeChemicals) {
		this.executeChemicals = executeChemicals;
	}

	public Set<InventoryModify> getInventoryModifies() {
		return inventoryModifies;
	}

	public void setInventoryModifies(Set<InventoryModify> inventoryModifies) {
		this.inventoryModifies = inventoryModifies;
	}

	public Set<ChemicalImage> getChemicalImages() {
		return chemicalImages;
	}

	public void setChemicalImages(Set<ChemicalImage> chemicalImages) {
		this.chemicalImages = chemicalImages;
	}

	public Measurement getConPurMeasurement() {
		return conPurMeasurement;
	}

	public void setConPurMeasurement(Measurement conPurMeasurement) {
		this.conPurMeasurement = conPurMeasurement;
	}

	public Integer getInventoryNumber() {
		return InventoryNumber;
	}

	public void setInventoryNumber(Integer inventoryNumber) {
		InventoryNumber = inventoryNumber;
	}

	public QrInfo getQrInfo() {
		return qrInfo;
	}

	public void setQrInfo(QrInfo qrInfo) {
		this.qrInfo = qrInfo;
	}

	public Double getChangeBefore() {
		return changeBefore;
	}

	public void setChangeBefore(Double changeBefore) {
		this.changeBefore = changeBefore;
	}

	public InventoryGroup getInventoryGroup() {
		return inventoryGroup;
	}

	public void setInventoryGroup(InventoryGroup inventoryGroup) {
		this.inventoryGroup = inventoryGroup;
	}
	
	
	
}