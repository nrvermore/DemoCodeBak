package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InventoryGroups {
	
	/**
	 * 库存主键
	 */
	private Integer groupId;
	
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
	 * 保密级别主键
	 */
	private SecureRank secureRank;
	
	/**
	 * 供应商主键
	 */
	private Supplier supplier;
	
	/**
	 * 化学品基础信息主键
	 */
	private ChemicalParameter chemicalParameter;
	
	/**
	 * 可用总量
	 */
	private Double totalWei;
	
	/**
	 * 批号
	 */
	private String batchNumber;
	
	/**
	 * 浓度纯度
	 */
	private String conPur;
	
	/**
	 * 库存数量
	 */
	private Integer inventoryNumber;
	
	/**
	 * 密度差异
	 */
	private String densityVariation;
	
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
	
	private Integer productSummaryId;
	
	/**
	 * 密度
	 */
	private String density;
	
	/**
	 * 密度单位
	 */
	private Measurement densityMeasurement;
	
	
	/**
	 * 库存权限表
	 */
	private List<InventoryUser> inventoryUsers = new ArrayList<InventoryUser>(
			0);
	
	/**
	 * 试验设计原料表
	 */
	private List<ReactionDesignChemical> reactionDesignChemicals = new ArrayList<ReactionDesignChemical>(
			0);
	
	/**
	 * 化学品基础信息图片表
	 */
	private List<ChemicalImage> chemicalImages = new ArrayList<ChemicalImage>(0);

	public InventoryGroups() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InventoryGroups(Integer groupId, MaterialType materialType,
			Measurement measurement, Measurement conPurMeasurement,
			SecureRank secureRank, Supplier supplier,
			ChemicalParameter chemicalParameter, Double totalWei,
			String batchNumber, String conPur, Integer inventoryNumber,
			String densityVariation, String description, String creater,
			Date createDate, String modifier, Date modifyDate,
			String inventoryName, Integer productSummaryId, String density,
			Measurement densityMeasurement, List<InventoryUser> inventoryUsers,
			List<ReactionDesignChemical> reactionDesignChemicals,
			List<ChemicalImage> chemicalImages) {
		super();
		this.groupId = groupId;
		this.materialType = materialType;
		this.measurement = measurement;
		this.conPurMeasurement = conPurMeasurement;
		this.secureRank = secureRank;
		this.supplier = supplier;
		this.chemicalParameter = chemicalParameter;
		this.totalWei = totalWei;
		this.batchNumber = batchNumber;
		this.conPur = conPur;
		this.inventoryNumber = inventoryNumber;
		this.densityVariation = densityVariation;
		this.description = description;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.inventoryName = inventoryName;
		this.productSummaryId = productSummaryId;
		this.density = density;
		this.densityMeasurement = densityMeasurement;
		this.inventoryUsers = inventoryUsers;
		this.reactionDesignChemicals = reactionDesignChemicals;
		this.chemicalImages = chemicalImages;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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

	public Measurement getConPurMeasurement() {
		return conPurMeasurement;
	}

	public void setConPurMeasurement(Measurement conPurMeasurement) {
		this.conPurMeasurement = conPurMeasurement;
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

	public ChemicalParameter getChemicalParameter() {
		return chemicalParameter;
	}

	public void setChemicalParameter(ChemicalParameter chemicalParameter) {
		this.chemicalParameter = chemicalParameter;
	}

	public Double getTotalWei() {
		return totalWei;
	}

	public void setTotalWei(Double totalWei) {
		this.totalWei = totalWei;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getConPur() {
		return conPur;
	}

	public void setConPur(String conPur) {
		this.conPur = conPur;
	}

	public Integer getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(Integer inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}

	public String getDensityVariation() {
		return densityVariation;
	}

	public void setDensityVariation(String densityVariation) {
		this.densityVariation = densityVariation;
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

	public Integer getProductSummaryId() {
		return productSummaryId;
	}

	public void setProductSummaryId(Integer productSummaryId) {
		this.productSummaryId = productSummaryId;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public Measurement getDensityMeasurement() {
		return densityMeasurement;
	}

	public void setDensityMeasurement(Measurement densityMeasurement) {
		this.densityMeasurement = densityMeasurement;
	}

	public List<InventoryUser> getInventoryUsers() {
		return inventoryUsers;
	}

	public void setInventoryUsers(List<InventoryUser> inventoryUsers) {
		this.inventoryUsers = inventoryUsers;
	}

	public List<ReactionDesignChemical> getReactionDesignChemicals() {
		return reactionDesignChemicals;
	}

	public void setReactionDesignChemicals(
			List<ReactionDesignChemical> reactionDesignChemicals) {
		this.reactionDesignChemicals = reactionDesignChemicals;
	}

	public List<ChemicalImage> getChemicalImages() {
		return chemicalImages;
	}

	public void setChemicalImages(List<ChemicalImage> chemicalImages) {
		this.chemicalImages = chemicalImages;
	}
	
	
}
