package com.labwinner.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	/**
	 * ReactionDesignChemical entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 设计原料表
	 */
public class ReactionDesignChemical implements java.io.Serializable {

	/**
	 * 设计原料主键
	 */
	private Integer designChemicalId;
	
	/**
	 * 试验设计主键
	 */
	private ReactionDesign reactionDesign;
	
	/**
	 * 材料库存
	 */
	private InventoryGroups inventoryGroups;
	
	/**
	 * 是否可变
	 */
	private Boolean changeable;
	
	/**
	 * 设计用量表
	 */
	private List<DesignDosage> designDosages = new ArrayList<DesignDosage>(0);

	// Constructors

	/** default constructor */
	public ReactionDesignChemical() {
	}

	public ReactionDesignChemical(Integer designChemicalId,
			ReactionDesign reactionDesign, InventoryGroups inventoryGroups,
			Boolean changeable, List<DesignDosage> designDosages) {
		super();
		this.designChemicalId = designChemicalId;
		this.reactionDesign = reactionDesign;
		this.inventoryGroups = inventoryGroups;
		this.changeable = changeable;
		this.designDosages = designDosages;
	}

	public Integer getDesignChemicalId() {
		return designChemicalId;
	}

	public void setDesignChemicalId(Integer designChemicalId) {
		this.designChemicalId = designChemicalId;
	}

	public ReactionDesign getReactionDesign() {
		return reactionDesign;
	}

	public void setReactionDesign(ReactionDesign reactionDesign) {
		this.reactionDesign = reactionDesign;
	}

	public InventoryGroups getInventoryGroups() {
		return inventoryGroups;
	}

	public void setInventoryGroups(InventoryGroups inventoryGroups) {
		this.inventoryGroups = inventoryGroups;
	}

	public Boolean getChangeable() {
		return changeable;
	}

	public void setChangeable(Boolean changeable) {
		this.changeable = changeable;
	}

	public List<DesignDosage> getDesignDosages() {
		return designDosages;
	}

	public void setDesignDosages(List<DesignDosage> designDosages) {
		this.designDosages = designDosages;
	}

	
}