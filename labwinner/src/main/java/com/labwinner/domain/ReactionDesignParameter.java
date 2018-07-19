package com.labwinner.domain;

	/**
	 * ReactionDesignParameter entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 试验设计参数表
	 */
public class ReactionDesignParameter implements java.io.Serializable {

	/**
	 * 设计参数主键
	 */
	private Integer designParameterId;
	
	/**
	 * 度量单位主键
	 */
	private Measurement measurement;
	
	/**
	 * 试验设计步骤主键
	 */
	private ReactionDesignProcess reactionDesignProcess;
	
	/**
	 * 试验参数主键
	 */
	private String reactionParameter;
	
	/**
	 * 设计参数用量
	 */
	private String designParameterDosage;

	// Constructors

	/** default constructor */
	public ReactionDesignParameter() {
	}

	/** full constructor */
	public ReactionDesignParameter(Measurement measurement,
			ReactionDesignProcess reactionDesignProcess,
			String reactionParameter, String designParameterDosage) {
		this.measurement = measurement;
		this.reactionDesignProcess = reactionDesignProcess;
		this.reactionParameter = reactionParameter;
		this.designParameterDosage = designParameterDosage;
	}

	public Integer getDesignParameterId() {
		return designParameterId;
	}

	public void setDesignParameterId(Integer designParameterId) {
		this.designParameterId = designParameterId;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public ReactionDesignProcess getReactionDesignProcess() {
		return reactionDesignProcess;
	}

	public void setReactionDesignProcess(ReactionDesignProcess reactionDesignProcess) {
		this.reactionDesignProcess = reactionDesignProcess;
	}

	public String getReactionParameter() {
		return reactionParameter;
	}

	public void setReactionParameter(String reactionParameter) {
		this.reactionParameter = reactionParameter;
	}

	public String getDesignParameterDosage() {
		return designParameterDosage;
	}

	public void setDesignParameterDosage(String designParameterDosage) {
		this.designParameterDosage = designParameterDosage;
	}

	

}