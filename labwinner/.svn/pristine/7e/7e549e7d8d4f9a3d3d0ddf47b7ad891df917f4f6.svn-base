package com.labwinner.domain;

import java.util.Date;

	/**
	 * ExecuteChemical entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 执行原料表
	 */
public class ExecuteChemical implements java.io.Serializable {

	/**
	 * 执行原料主键
	 */
	private Integer executeChemicalId;
	
	/**
	 * 度量单位主键
	 */
	private Measurement measurement;
	
	/**
	 * 库存主键
	 */
	private Inventories inventory;
	
	
	private Integer chemicalGroupId;
	
	private ExecuteChemicalGroup executeChemicalGroup;
	

	/**
	 * 试验记录主键
	 */
	private ReactionProcess reactionProcess;
	
	/**
	 * 原料用量
	 */
	private Double chemicalDosage;
	
	/**
	 * 原料用量
	 */
	private Double beforeDosage;
	
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

	// Constructors

	/** default constructor */
	public ExecuteChemical() {
	}

	/** full constructor */
	public ExecuteChemical(Measurement measurement, Inventories inventory,
			ReactionProcess reactionProcess, Double chemicalDosage,Double beforeDosage,
			String creater, Date createDate, String modifier, Date modifyDate) {
		this.measurement = measurement;
		this.reactionProcess = reactionProcess;
		this.chemicalDosage = chemicalDosage;
		this.beforeDosage = beforeDosage;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
	}

	public Integer getExecuteChemicalId() {
		return executeChemicalId;
	}

	public void setExecuteChemicalId(Integer executeChemicalId) {
		this.executeChemicalId = executeChemicalId;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public Inventories getInventory() {
		return inventory;
	}

	public void setInventory(Inventories inventory) {
		this.inventory = inventory;
	}

	public ReactionProcess getReactionProcess() {
		return reactionProcess;
	}

	public void setReactionProcess(ReactionProcess reactionProcess) {
		this.reactionProcess = reactionProcess;
	}

	public Double getChemicalDosage() {
		return chemicalDosage;
	}

	public void setChemicalDosage(Double chemicalDosage) {
		this.chemicalDosage = chemicalDosage;
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

	public Double getBeforeDosage() {
		return beforeDosage;
	}

	public void setBeforeDosage(Double beforeDosage) {
		this.beforeDosage = beforeDosage;
	}
	
	public Integer getChemicalGroupId() {
		return chemicalGroupId;
	}

	public void setChemicalGroupId(Integer chemicalGroupId) {
		this.chemicalGroupId = chemicalGroupId;
	}

	public ExecuteChemicalGroup getExecuteChemicalGroup() {
		return executeChemicalGroup;
	}

	public void setExecuteChemicalGroup(ExecuteChemicalGroup executeChemicalGroup) {
		this.executeChemicalGroup = executeChemicalGroup;
	}
	
}