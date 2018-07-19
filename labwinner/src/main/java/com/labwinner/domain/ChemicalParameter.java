package com.labwinner.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


	/**
	 * ChemicalParameter entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 化学品基础信息表
	 */
public class ChemicalParameter implements java.io.Serializable {

	/**
     * 化学品基础信息表主键
     */
	private Integer chParId;
	
	/**
     * WinID
     */
	private String winId;
	
	/**
     * 中文名称
     */
	private String chineseName;
	
	/**
     * 英文名称
     */
	private String englishName;

	/**
     * 别名名称
     */
	private String otherName;
	
	/**
     * 更多别名
     */
	private String moreName;
	
	/**
     * 分子式
     */
	private String molecularFormula;
	
	/**
     * 分子量
     */
	private String molecularWeight;
	
	/**
     * CAS号
     */
	private String cas;
	
	/**
     * MDL号
     */
	private String mdl;
	
	/**
     * EINECS号
     */
	private String einecs;
	
	/**
     * RTECS号
     */
	private String rtecs;
	
	/**
     * BTECS号
     */
	private String btecs;
	
	/**
     * PUBCHEM号
     */
	private String pubchem;
	
	/**
     * 物性数据
     */
	private String physicalData;
	
	/**
     * 毒理学数据
     */
	private String toxicologicalData;
	
	/**
     * 生态学数据
     */
	private String ecologicalData;
	
	/**
     * 分子结构数据
     */
	private String molStrData;
	
	/**
     * 计算化学数据
     */
	private String calChData;
	
	/**
     * 性质与稳定性
     */
	private String proAndSta;
	
	/**
     * 贮存方法
     */
	private String storageMethod;
	
	/**
     * 合成方法
     */
	private String synthesisMethod;
	
	/**
     * 用途
     */
	private String application;
	
	/**
     * 安全信息
     */
	private String secInfo;
	
	/**
     * BRN号
     */
	private String brn;
	
	private String chemicalIamge;
	
	private String representationMap;
	
	/**
     * 备用字段1
     */
	private String isLock;
	
	/**
     * 备用字段2
     */
	private String reserveField2;
	
	/**
     * 备用字段3
     */
	private String reserveField3;
	
	/**
     * 备用字段4
     */
	private String reserveField4;
	
	/**
     * 备用字段5
     */
	private String reserveField5;
	
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
	
	private Integer flag;
	
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
     * 库存信息表
     */
	private Set<MolecularImage> molecularImages = new HashSet<MolecularImage>(0);
	
	/**
     * 库存信息表
     */
	private Set<RepresentationMap> representationMaps = new HashSet<RepresentationMap>(0);

	// Constructors

	/** default constructor */
	public ChemicalParameter() {
	}

	/** full constructor */
	public ChemicalParameter(Set<RepresentationMap> representationMaps,
			String winId, String chineseName, String englishName,
			String otherName, String moreName, String molecularFormula,
			String molecularWeight, String cas, String mdl, String einecs,
			String rtecs, String btecs, String pubchem, String physicalData,
			String toxicologicalData, String ecologicalData, String molStrData,
			String calChData, String proAndSta, String storageMethod,
			String synthesisMethod, String application, String secInfo,
			String brn, String isLock, String reserveField2,
			String reserveField3, String reserveField4, String reserveField5,
			String creater, Date createDate, String modifier, Date modifyDate,
			Set<MaterialPurchase> materialPurchases, Set<Inventory> inventories) {
		this.representationMaps = representationMaps;
		this.winId = winId;
		this.chineseName = chineseName;
		this.englishName = englishName;
		this.otherName = otherName;
		this.moreName = moreName;
		this.molecularFormula = molecularFormula;
		this.molecularWeight = molecularWeight;
		this.cas = cas;
		this.mdl = mdl;
		this.einecs = einecs;
		this.rtecs = rtecs;
		this.btecs = btecs;
		this.pubchem = pubchem;
		this.physicalData = physicalData;
		this.toxicologicalData = toxicologicalData;
		this.ecologicalData = ecologicalData;
		this.molStrData = molStrData;
		this.calChData = calChData;
		this.proAndSta = proAndSta;
		this.storageMethod = storageMethod;
		this.synthesisMethod = synthesisMethod;
		this.application = application;
		this.secInfo = secInfo;
		this.brn = brn;
		this.isLock = isLock;
		this.reserveField2 = reserveField2;
		this.reserveField3 = reserveField3;
		this.reserveField4 = reserveField4;
		this.reserveField5 = reserveField5;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.materialPurchases = materialPurchases;
		this.inventories = inventories;
	}

	public Integer getChParId() {
		return chParId;
	}

	public void setChParId(Integer chParId) {
		this.chParId = chParId;
	}

	public Set<MolecularImage> getMolecularImages() {
		return molecularImages;
	}

	public void setMolecularImages(Set<MolecularImage> molecularImages) {
		this.molecularImages = molecularImages;
	}

	public Set<RepresentationMap> getRepresentationMaps() {
		return representationMaps;
	}

	public void setRepresentationMaps(Set<RepresentationMap> representationMaps) {
		this.representationMaps = representationMaps;
	}

	public String getWinId() {
		return winId;
	}

	public void setWinId(String winId) {
		this.winId = winId;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getMoreName() {
		return moreName;
	}

	public void setMoreName(String moreName) {
		this.moreName = moreName;
	}

	public String getMolecularFormula() {
		return molecularFormula;
	}

	public void setMolecularFormula(String molecularFormula) {
		this.molecularFormula = molecularFormula;
	}

	public String getMolecularWeight() {
		return molecularWeight;
	}

	public void setMolecularWeight(String molecularWeight) {
		this.molecularWeight = molecularWeight;
	}

	public String getCas() {
		return cas;
	}

	public void setCas(String cas) {
		this.cas = cas;
	}

	public String getMdl() {
		return mdl;
	}

	public void setMdl(String mdl) {
		this.mdl = mdl;
	}

	public String getEinecs() {
		return einecs;
	}

	public void setEinecs(String einecs) {
		this.einecs = einecs;
	}

	public String getRtecs() {
		return rtecs;
	}

	public void setRtecs(String rtecs) {
		this.rtecs = rtecs;
	}

	public String getBtecs() {
		return btecs;
	}

	public void setBtecs(String btecs) {
		this.btecs = btecs;
	}

	public String getPubchem() {
		return pubchem;
	}

	public void setPubchem(String pubchem) {
		this.pubchem = pubchem;
	}

	public String getPhysicalData() {
		return physicalData;
	}

	public void setPhysicalData(String physicalData) {
		this.physicalData = physicalData;
	}

	public String getToxicologicalData() {
		return toxicologicalData;
	}

	public void setToxicologicalData(String toxicologicalData) {
		this.toxicologicalData = toxicologicalData;
	}

	public String getEcologicalData() {
		return ecologicalData;
	}

	public void setEcologicalData(String ecologicalData) {
		this.ecologicalData = ecologicalData;
	}

	public String getMolStrData() {
		return molStrData;
	}

	public void setMolStrData(String molStrData) {
		this.molStrData = molStrData;
	}

	public String getCalChData() {
		return calChData;
	}

	public void setCalChData(String calChData) {
		this.calChData = calChData;
	}

	public String getProAndSta() {
		return proAndSta;
	}

	public void setProAndSta(String proAndSta) {
		this.proAndSta = proAndSta;
	}

	public String getStorageMethod() {
		return storageMethod;
	}

	public void setStorageMethod(String storageMethod) {
		this.storageMethod = storageMethod;
	}

	public String getSynthesisMethod() {
		return synthesisMethod;
	}

	public void setSynthesisMethod(String synthesisMethod) {
		this.synthesisMethod = synthesisMethod;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getSecInfo() {
		return secInfo;
	}

	public void setSecInfo(String secInfo) {
		this.secInfo = secInfo;
	}

	public String getBrn() {
		return brn;
	}

	public void setBrn(String brn) {
		this.brn = brn;
	}


	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
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

	public String getReserveField4() {
		return reserveField4;
	}

	public void setReserveField4(String reserveField4) {
		this.reserveField4 = reserveField4;
	}

	public String getReserveField5() {
		return reserveField5;
	}

	public void setReserveField5(String reserveField5) {
		this.reserveField5 = reserveField5;
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

	public String getChemicalIamge() {
		return chemicalIamge;
	}

	public void setChemicalIamge(String chemicalIamge) {
		this.chemicalIamge = chemicalIamge;
	}

	public String getRepresentationMap() {
		return representationMap;
	}

	public void setRepresentationMap(String representationMap) {
		this.representationMap = representationMap;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}