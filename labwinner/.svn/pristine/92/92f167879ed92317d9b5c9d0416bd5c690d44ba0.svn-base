package com.labwinner.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

	/**
	 * MaterialPurchase entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 原料采购表
	 */
public class MaterialPurchase implements java.io.Serializable {

	/**
	 * 原料采购主键
	 */
	private Integer matPurId;
	
	/**
	 * 规格单位
	 */
	private Measurement measurement;
	
	/**
	 * 用户（采购人）
	 */
	private SysUser sysUser;
	
	/**
	 * 供应商主键
	 */
	private Supplier supplier;
	
	/**
	 * 采购渠道主键
	 */
	private ProcChannelParameter procChannelParameter;
	
	
	
	/**
	 * 价格币种主键
	 */
	private PriceCurrency matPriceCurrency;
	
	/**
	 * 订单状态主键
	 */
	private OrderState orderState;
	
	/**
	 * 采购日期
	 */
	private Date purchaseDate;
	
	/**
	 * 单价
	 */
	private Double materialPrice;
	
	/**
	 * 采购数量
	 */
	private Integer count;
	
	/**
	 * 税率
	 */
	private String taxRate;
	
	/**
	 * 采购备注
	 */
	private String purchaseRemarks;
	
	/**
	 * 采购订单号
	 */
	private String orderNumber;
	
	/**
	 * 是否含税
	 */
	private Boolean containTax;
	
	/**
	 * 原料规格
	 */
	private String materialSpecification;
	
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
	 * 联系人姓名
	 */
	private String contacterName;
	
	/**
	 * 联系人电话
	 */
	private String contacterPhone;
	
	/**
	 * 审批表
	 */
	private Approvel approvel ;
	
	private ProductSummary productSummary;
	
	/**
	 * 总价
	 */
	private String allPrice;
	
	private PackageType packageType;
	
	private ProductType productType;
	

	// Constructors

	/** default constructor */
	public MaterialPurchase() {
	}

	/** full constructor */
	public MaterialPurchase(Measurement measurement, SysUser sysUser,
			Supplier supplier, ProcChannelParameter procChannelParameter,
			PriceCurrency matPriceCurrency,
			OrderState orderState, Date purchaseDate, Double materialPrice,
			Integer count, String taxRate, String purchaseRemarks,
			Boolean containTax, String materialSpecification, String creater,
			Date createDate, String modifier, Date modifyDate,
			String contacterName, String contacterPhone,
			Approvel approvel,ProductSummary productSummary,String orderNumber,
			String allPrice,PackageType packageType,ProductType productType) {
		this.measurement = measurement;
		this.sysUser = sysUser;
		this.supplier = supplier;
		this.procChannelParameter = procChannelParameter;
		this.matPriceCurrency = matPriceCurrency;
		this.orderState = orderState;
		this.purchaseDate = purchaseDate;
		this.materialPrice = materialPrice;
		this.count = count;
		this.taxRate = taxRate;
		this.purchaseRemarks = purchaseRemarks;
		this.containTax = containTax;
		this.materialSpecification = materialSpecification;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.contacterName = contacterName;
		this.contacterPhone = contacterPhone;
		this.approvel = approvel;
		this.productSummary=productSummary;
		this.orderNumber=orderNumber;
		this.allPrice=allPrice;
		this.packageType=packageType;
	}

	public Integer getMatPurId() {
		return matPurId;
	}

	public void setMatPurId(Integer matPurId) {
		this.matPurId = matPurId;
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public ProcChannelParameter getProcChannelParameter() {
		return procChannelParameter;
	}

	public void setProcChannelParameter(ProcChannelParameter procChannelParameter) {
		this.procChannelParameter = procChannelParameter;
	}




	public PriceCurrency getMatPriceCurrency() {
		return matPriceCurrency;
	}

	public void setMatPriceCurrency(PriceCurrency matPriceCurrency) {
		this.matPriceCurrency = matPriceCurrency;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getMaterialPrice() {
		return materialPrice;
	}

	public void setMaterialPrice(Double materialPrice) {
		this.materialPrice = materialPrice;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}


	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getPurchaseRemarks() {
		return purchaseRemarks;
	}

	public void setPurchaseRemarks(String purchaseRemarks) {
		this.purchaseRemarks = purchaseRemarks;
	}

	public Boolean getContainTax() {
		return containTax;
	}

	public void setContainTax(Boolean containTax) {
		this.containTax = containTax;
	}

	

	public String getMaterialSpecification() {
		return materialSpecification;
	}

	public void setMaterialSpecification(String materialSpecification) {
		this.materialSpecification = materialSpecification;
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

	public String getContacterName() {
		return contacterName;
	}

	public void setContacterName(String contacterName) {
		this.contacterName = contacterName;
	}

	public String getContacterPhone() {
		return contacterPhone;
	}

	public void setContacterPhone(String contacterPhone) {
		this.contacterPhone = contacterPhone;
	}


	public Approvel getApprovel() {
		return approvel;
	}

	public void setApprovel(Approvel approvel) {
		this.approvel = approvel;
	}

	public ProductSummary getProductSummary() {
		return productSummary;
	}

	public void setProductSummary(ProductSummary productSummary) {
		this.productSummary = productSummary;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(String allPrice) {
		this.allPrice = allPrice;
	}

	public PackageType getPackageType() {
		return packageType;
	}

	public void setPackageType(PackageType packageType) {
		this.packageType = packageType;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}


}