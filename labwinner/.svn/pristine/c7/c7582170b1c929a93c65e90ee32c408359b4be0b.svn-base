package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	/**
	 * Supplier entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 供应商表
	 */
public class Supplier implements java.io.Serializable {

	/**
	 * 供应商主键
	 */
	private Integer supId;
	
	/**
	 * 供应商名称
	 */
	private String suprName;
	
	/**
	 * 供应商地址 
	 */
	private String supAddress;
	
	/**
	 * 供应商电话
	 */
	private String supPhone;
	
	/**
	 * 供应商联系人
	 */
	private String supContacter;
	
	/**
	 * URL
	 */
	private String url;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 备用字段2
	 */
	private String reserveField2;
	
	/**
	 * 备用字段3
	 */
	private String reserveField3;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 创建人
	 */
	private String creater;
	
	/**
	 * 修改人
	 */
	private String modifier;
	
	/**
	 * 备用字段
	 */
	private String reserveField;
	
	
	/**
	 * 部门
	 */
	private String department;
	
	
	/**
	 * 传真
	 */
	private String fax;
	
	
	/**
	 * 传真
	 */
	private String remark;
	
	/**
	 * 修改日期
	 */
	private Date modifyDate;
	
	
	
	/**
	 * 原料采购表
	 */
	private Set<MaterialPurchase> materialPurchases = new HashSet<MaterialPurchase>(
			0);
	
	/**
	 * 库存信息表
	 */
	private Set<Inventory> inventories = new HashSet<Inventory>(0);
	
	/**
	 * 产品信息表
	 */
	private List<ProductSummary> productSummary = new ArrayList<ProductSummary>();
	
	private Integer productNum;
	private CompanyType companyType;
	// Constructors

	/** default constructor */
	public Supplier() {
	}

	/** full constructor */
	public Supplier(String suprName, String supAddress, String supPhone,
			String supContacter, String url, Date createDate,
			String reserveField2, String reserveField3, String email,
			String creater, String modifier, String reserveField,
			Date modifyDate, Set<MaterialPurchase> materialPurchases,
			Set<Inventory> inventories,List<ProductSummary> productSummary,Integer productNum,CompanyType companyType,String department,String remark,String fax) {
		this.suprName = suprName;
		this.supAddress = supAddress;
		this.supPhone = supPhone;
		this.supContacter = supContacter;
		this.url = url;
		this.createDate = createDate;
		this.reserveField2 = reserveField2;
		this.reserveField3 = reserveField3;
		this.email = email;
		this.creater = creater;
		this.modifier = modifier;
		this.reserveField = reserveField;
		this.modifyDate = modifyDate;
		this.materialPurchases = materialPurchases;
		this.inventories = inventories;
		this.productSummary=productSummary;
		this.productNum=productNum;
		this.companyType=companyType;
		this.department=department;
		this.remark=remark;
		this.fax=fax;
	}

	public Integer getSupId() {
		return supId;
	}

	public void setSupId(Integer supId) {
		this.supId = supId;
	}

	public String getSuprName() {
		return suprName;
	}

	public void setSuprName(String suprName) {
		this.suprName = suprName;
	}

	public String getSupAddress() {
		return supAddress;
	}

	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}

	public String getSupPhone() {
		return supPhone;
	}

	public void setSupPhone(String supPhone) {
		this.supPhone = supPhone;
	}

	public String getSupContacter() {
		return supContacter;
	}

	public void setSupContacter(String supContacter) {
		this.supContacter = supContacter;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getReserveField2() {
		return reserveField2;
	}

	public void setReserveField2(String reserveField2) {
		this.reserveField2 = reserveField2;
	}

	public String getReserveField3() {
		return reserveField3;
	}

	public void setReserveField3(String reserveField3) {
		this.reserveField3 = reserveField3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getReserveField() {
		return reserveField;
	}

	public void setReserveField(String reserveField) {
		this.reserveField = reserveField;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Set<MaterialPurchase> getMaterialPurchases() {
		return materialPurchases;
	}

	public void setMaterialPurchases(Set<MaterialPurchase> materialPurchases) {
		this.materialPurchases = materialPurchases;
	}

	public Set<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}

	

	public List<ProductSummary> getProductSummary() {
		return productSummary;
	}

	public void setProductSummary(List<ProductSummary> productSummary) {
		this.productSummary = productSummary;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

}