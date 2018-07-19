package com.labwinner.vo;

public class InventoryModifyVo {
	
	private Integer inventoryId;
	
	 private Double modifyAfter;
	 
	 private Integer modifyCodeId;
	 
	 private String changeReason;
	 
	 private Integer measureUnitId;
	 
	 public InventoryModifyVo (){};

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Double getModifyAfter() {
		return modifyAfter;
	}

	public void setModifyAfter(Double modifyAfter) {
		this.modifyAfter = modifyAfter;
	}

	public Integer getModifyCodeId() {
		return modifyCodeId;
	}

	public void setModifyCodeId(Integer modifyCodeId) {
		this.modifyCodeId = modifyCodeId;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public Integer getMeasureUnitId() {
		return measureUnitId;
	}

	public void setMeasureUnitId(Integer measureUnitId) {
		this.measureUnitId = measureUnitId;
	}
	 

}
