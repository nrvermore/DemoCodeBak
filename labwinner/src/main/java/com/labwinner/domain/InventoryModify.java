package com.labwinner.domain;

import java.util.Date;

	/**
	 * InventoryModify entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 库存变更表
	 */
public class InventoryModify implements java.io.Serializable {

	/**
	 * 库存变更主键
	 */
	private Integer inventoryModifyId;
	
	
	
	/**
	 * 库存主键
	 */
	private Inventories inventories;
	
	/**
	 * 变更类型主键
	 */
	private ModifyType modifyType;
	
	/**
	 * 变更理由
	 */
	private String changeReason;
	
	/**
	 * 变更日期
	 */
	private Date changeDate;
	
	/**
	 * 变更前重量
	 */
	private Double modifyBefore;
	
	/**
	 * 变更后重量
	 */
	private Double modifyAfter;
	
	/**
	 * 变更试验
	 */
	private ReactionProcess modifyProcess;
	
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
	private SysUser modifier;
	
	private ModifyCode modifyCode;
	
	/**
	 * 修改日期
	 */
	private Date modifyDate;
	
	private Measurement measurement;

	// Constructors

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	/** default constructor */
	public InventoryModify() {
	}

	/** full constructor */
	public InventoryModify(ModifyType modifyType,
			String changeReason, Date changeDate, Double modifyBefore,
			Double modifyAfter, ReactionProcess modifyProcess, String creater,ModifyCode modifyCode,
			Date createDate, SysUser modifier, Date modifyDate,Measurement measurement) {
		this.modifyType = modifyType;
		this.changeReason = changeReason;
		this.changeDate = changeDate;
		this.modifyBefore = modifyBefore;
		this.modifyAfter = modifyAfter;
		this.modifyProcess = modifyProcess;
		this.modifyCode = modifyCode;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.measurement = measurement;
	}

	public Integer getInventoryModifyId() {
		return inventoryModifyId;
	}

	public void setInventoryModifyId(Integer inventoryModifyId) {
		this.inventoryModifyId = inventoryModifyId;
	}

	

	public ModifyType getModifyType() {
		return modifyType;
	}

	public void setModifyType(ModifyType modifyType) {
		this.modifyType = modifyType;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public Double getModifyBefore() {
		return modifyBefore;
	}

	public void setModifyBefore(Double modifyBefore) {
		this.modifyBefore = modifyBefore;
	}

	public Double getModifyAfter() {
		return modifyAfter;
	}

	public void setModifyAfter(Double modifyAfter) {
		this.modifyAfter = modifyAfter;
	}

	public ReactionProcess getModifyProcess() {
		return modifyProcess;
	}

	public void setModifyProcess(ReactionProcess modifyProcess) {
		this.modifyProcess = modifyProcess;
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

	public SysUser getModifier() {
		return modifier;
	}

	public void setModifier(SysUser modifier) {
		this.modifier = modifier;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public ModifyCode getModifyCode() {
		return modifyCode;
	}

	public void setModifyCode(ModifyCode modifyCode) {
		this.modifyCode = modifyCode;
	}

	public Inventories getInventories() {
		return inventories;
	}

	public void setInventories(Inventories inventories) {
		this.inventories = inventories;
	}
	
}