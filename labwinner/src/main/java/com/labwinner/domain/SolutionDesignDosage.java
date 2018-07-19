package com.labwinner.domain;

	/**
	 * SolutionDesignDosage entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 溶液设计用量表
	 */
public class SolutionDesignDosage implements java.io.Serializable {

	/**
	 * 溶液设计用量主键
	 */
	private Integer solutionDesignDosageId;
	
	/**
	 * 溶液设计主键
	 */	
	private ReactionDesignSolution reactionDesignSolution;
	
	/**
	 * 度量单位主键
	 */
	private Measurement measurement;
	
	/**
	 * 试验序号
	 */
	private Integer reactionNum;
	
	/**
	 * 溶液用量
	 */
	private Double solutionDosage;
	/**
	 * 溶液用量百分比
	 */
	private Double percentage;

	public SolutionDesignDosage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SolutionDesignDosage(Integer solutionDesignDosageId,
			ReactionDesignSolution reactionDesignSolution,
			Measurement measurement, Integer reactionNum,
			Double solutionDosage, Double percentage) {
		super();
		this.solutionDesignDosageId = solutionDesignDosageId;
		this.reactionDesignSolution = reactionDesignSolution;
		this.measurement = measurement;
		this.reactionNum = reactionNum;
		this.solutionDosage = solutionDosage;
		this.percentage = percentage;
	}

	public Integer getSolutionDesignDosageId() {
		return solutionDesignDosageId;
	}

	public void setSolutionDesignDosageId(Integer solutionDesignDosageId) {
		this.solutionDesignDosageId = solutionDesignDosageId;
	}

	public ReactionDesignSolution getReactionDesignSolution() {
		return reactionDesignSolution;
	}

	public void setReactionDesignSolution(
			ReactionDesignSolution reactionDesignSolution) {
		this.reactionDesignSolution = reactionDesignSolution;
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

	public Double getSolutionDosage() {
		return solutionDosage;
	}

	public void setSolutionDosage(Double solutionDosage) {
		this.solutionDosage = solutionDosage;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	

}