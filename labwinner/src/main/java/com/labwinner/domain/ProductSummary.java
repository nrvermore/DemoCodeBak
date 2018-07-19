package com.labwinner.domain;

import java.util.Date;

/**
 * @Description 系统附件
 * @author llwangi
 * @version V1.0
 * @date 2017年8月11日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class ProductSummary implements java.io.Serializable {

	/**
	 * 主键
	 */
	private Integer productSummaryId;

	/**
	 * 供应商
	 */
	private Supplier supplier;

	/**
	 * 原材料规格
	 */
	private int materialSpecification;

	/**
	 * 度量单位
	 */
	private Measurement measurement;
	
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 价格单位
	 */
	private PriceCurrency priceCurrency;
	
	/**
	 * 报价时间
	 */
	private Date quotedDate;
	
	private ChemicalParameter chemicalParameter;
	/**
	 * 商品目录编码
	 * **/
	private String catalogueCode;
	/**
	 * 内部编码
	 * **/
	private String innerCode;
	
	
	/**
	 * 产品备注
	 * **/
	private String remark;
	
	/**
	 * 产品网址
	 * **/
	private String productUrl;
	
	private PackageType packageType;
	
	private ProductType productType;

	// Constructors

	/** default constructor */
	public ProductSummary() {
	}

	/** full constructor */
	public ProductSummary(Supplier supplier,int materialSpecification, Measurement measurement, 
		String price,PriceCurrency priceCurrency,Date quotedDate,ChemicalParameter chemicalParameter,
		String catalogueCode,String innerCode,String remark,String productUrl,PackageType packageType,ProductType productType) {
		this.supplier = supplier;
		this.materialSpecification = materialSpecification;
		this.measurement = measurement;
		this.priceCurrency = priceCurrency;
		this.quotedDate=quotedDate;
		this.chemicalParameter=chemicalParameter;
		this.catalogueCode=catalogueCode;
		this.innerCode=innerCode;
		this.remark=remark;
		this.productUrl=productUrl;
		this.packageType=packageType;
		this.productType=productType;
	}

	public Integer getProductSummaryId() {
		return productSummaryId;
	}

	public void setProductSummaryId(Integer productSummaryId) {
		this.productSummaryId = productSummaryId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}


	public int getMaterialSpecification() {
		return materialSpecification;
	}

	public void setMaterialSpecification(int materialSpecification) {
		this.materialSpecification = materialSpecification;
	}

	public PackageType getPackageType() {
		return packageType;
	}

	public void setPackageType(PackageType packageType) {
		this.packageType = packageType;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public PriceCurrency getPriceCurrency() {
		return priceCurrency;
	}

	public void setPriceCurrency(PriceCurrency priceCurrency) {
		this.priceCurrency = priceCurrency;
	}

	public Date getQuotedDate() {
		return quotedDate;
	}

	public void setQuotedDate(Date quotedDate) {
		this.quotedDate = quotedDate;
	}

	public ChemicalParameter getChemicalParameter() {
		return chemicalParameter;
	}

	public void setChemicalParameter(ChemicalParameter chemicalParameter) {
		this.chemicalParameter = chemicalParameter;
	}

	public String getCatalogueCode() {
		return catalogueCode;
	}

	public void setCatalogueCode(String catalogueCode) {
		this.catalogueCode = catalogueCode;
	}

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	
	
}