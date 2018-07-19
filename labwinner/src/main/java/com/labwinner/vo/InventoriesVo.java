package com.labwinner.vo;

import java.util.Date;
import java.util.List;

import com.labwinner.domain.ChemicalParameter;
import com.labwinner.domain.InventoryGroup;
import com.labwinner.domain.InventoryGroups;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.MaterialType;
import com.labwinner.domain.Measurement;
import com.labwinner.domain.PriceCurrency;
import com.labwinner.domain.QrInfo;
import com.labwinner.domain.SecureRank;
import com.labwinner.domain.Supplier;
import com.labwinner.domain.SysUser;

public class InventoriesVo {
	
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
	
	private InventoryGroups inventoryGroups;
	
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
	private Integer inventoryNumber;
	
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
	 * 库存名称
	 */
	private String inventoryName;
	
	private List<String> imgStrs;
	
	private List<Integer> inventoryList;
	
	private Integer flag;
	
	private List<Integer> idList;
	
	private Integer matPurId;
	
	private Integer productSummaryId;
	
	public InventoriesVo(){}

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

	public Measurement getConPurMeasurement() {
		return conPurMeasurement;
	}

	public void setConPurMeasurement(Measurement conPurMeasurement) {
		this.conPurMeasurement = conPurMeasurement;
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

	public QrInfo getQrInfo() {
		return qrInfo;
	}

	public void setQrInfo(QrInfo qrInfo) {
		this.qrInfo = qrInfo;
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

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public List<String> getImgStrs() {
		return imgStrs;
	}

	public void setImgStrs(List<String> imgStrs) {
		this.imgStrs = imgStrs;
	}

	public List<Integer> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<Integer> inventoryList) {
		this.inventoryList = inventoryList;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public Integer getMatPurId() {
		return matPurId;
	}

	public void setMatPurId(Integer matPurId) {
		this.matPurId = matPurId;
	}

	public Integer getProductSummaryId() {
		return productSummaryId;
	}

	public void setProductSummaryId(Integer productSummaryId) {
		this.productSummaryId = productSummaryId;
	}
	
}
