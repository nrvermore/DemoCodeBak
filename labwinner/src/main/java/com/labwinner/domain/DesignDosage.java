package com.labwinner.domain;

	/**
	 * DesignDosage entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 设计用量表
	 */
public class DesignDosage implements java.io.Serializable {

	/**
	 * 设计用量主键
	 */
	private Integer designDosageId;
	
	/**
	 * 设计原料主键
	 */	
	private ReactionDesignChemical reactionDesignChemical;
	
	/**
	 * 度量单位主键
	 */
	private Measurement measurement;
	
	/**
	 * 试验序号
	 */
	private Integer reactionNum;
	
	/**
	 * 材料用量
	 */
	private Double chemicalDosage;
	
	/**
	 * 材料用量百分比
	 */
	private Double percentage;

	// Constructors

	/** default constructor */
	public DesignDosage() {
	}

	public DesignDosage(Integer designDosageId,
			ReactionDesignChemical reactionDesignChemical,
			Measurement measurement, Integer reactionNum,
			Double chemicalDosage, Double percentage) {
		super();
		this.designDosageId = designDosageId;
		this.reactionDesignChemical = reactionDesignChemical;
		this.measurement = measurement;
		this.reactionNum = reactionNum;
		this.chemicalDosage = chemicalDosage;
		this.percentage = percentage;
	}

	public Integer getDesignDosageId() {
		return designDosageId;
	}

	public void setDesignDosageId(Integer designDosageId) {
		this.designDosageId = designDosageId;
	}

	public ReactionDesignChemical getReactionDesignChemical() {
		return reactionDesignChemical;
	}

	public void setReactionDesignChemical(
			ReactionDesignChemical reactionDesignChemical) {
		this.reactionDesignChemical = reactionDesignChemical;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public Integer getReactionNum() {
		return reactionNum;
	}

	public void setReactionNum(Integer reactionNum) {
		this.reactionNum = reactionNum;
	}

	public Double getChemicalDosage() {
		return chemicalDosage;
	}

	public void setChemicalDosage(Double chemicalDosage) {
		this.chemicalDosage = chemicalDosage;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	

}