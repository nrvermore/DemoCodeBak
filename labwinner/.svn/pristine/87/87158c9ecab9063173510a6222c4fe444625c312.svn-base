package com.labwinner.domain;

	/**
	 * ChemicalImage entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 化学品基础信息图片表
	 */
public class ChemicalImage implements java.io.Serializable {

	/**
     * 图片表主键
     */
	private Integer chemicalImageId;
	
	/**
     * 库存表主键
     */
	private Inventory inventory;
	
	private InventoryGroups inventoryGroups;
	
	/**
     * 图片
     */
	private byte[] dissolvantImage;
	
	/**
     * 图片名称
     */
	private String dissolvantDescribe;

	// Constructors

	/** default constructor */
	public ChemicalImage() {
	}

	/** full constructor */
	public ChemicalImage(Inventory inventory, byte[] dissolvantImage,
			String dissolvantDescribe) {
		this.inventory = inventory;
		this.dissolvantImage = dissolvantImage;
		this.dissolvantDescribe = dissolvantDescribe;
	}

	public Integer getChemicalImageId() {
		return chemicalImageId;
	}

	public void setChemicalImageId(Integer chemicalImageId) {
		this.chemicalImageId = chemicalImageId;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public byte[] getDissolvantImage() {
		return dissolvantImage;
	}

	public void setDissolvantImage(byte[] dissolvantImage) {
		this.dissolvantImage = dissolvantImage;
	}

	public String getDissolvantDescribe() {
		return dissolvantDescribe;
	}

	public void setDissolvantDescribe(String dissolvantDescribe) {
		this.dissolvantDescribe = dissolvantDescribe;
	}

	public InventoryGroups getInventoryGroups() {
		return inventoryGroups;
	}

	public void setInventoryGroups(InventoryGroups inventoryGroups) {
		this.inventoryGroups = inventoryGroups;
	}

}