package com.labwinner.domain;

	/**
	 * 设计工艺用量实体类
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月17日 下午6:22:32
	 * 
	 * @Company 西安博文同创信息技术有限公司
	 * @Copyright Copyright (c) 2017, All rights reserved.
	 */
	
public class DesignTechnologyDosage implements java.io.Serializable {

	/**
	 * 设计工艺用量主键
	 */
	private Integer designTechnologyDosageId;
	
	/**
	 * 设计工艺步骤主键
	 */
	private DesignTechnologyProcess designTechnologyProcess;
	
	/**
	 * 度量单位主键
	 */
	private Measurement measurement;
	
	
	/**
	 * 设计工艺用量
	 */
    private String designTechnologyDosage;
    /**
     * 设计工艺参数名称
     */
    private String designParameterName;
    

	public DesignTechnologyDosage() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DesignTechnologyDosage(Integer designTechnologyDosageId,
			DesignTechnologyProcess designTechnologyProcess,
			Measurement measurement, String designTechnologyDosage,
			String designParameterName) {
		super();
		this.designTechnologyDosageId = designTechnologyDosageId;
		this.designTechnologyProcess = designTechnologyProcess;
		this.measurement = measurement;
		this.designTechnologyDosage = designTechnologyDosage;
		this.designParameterName = designParameterName;
	}


	public Integer getDesignTechnologyDosageId() {
		return designTechnologyDosageId;
	}


	public void setDesignTechnologyDosageId(Integer designTechnologyDosageId) {
		this.designTechnologyDosageId = designTechnologyDosageId;
	}


	public DesignTechnologyProcess getDesignTechnologyProcess() {
		return designTechnologyProcess;
	}


	public void setDesignTechnologyProcess(
			DesignTechnologyProcess designTechnologyProcess) {
		this.designTechnologyProcess = designTechnologyProcess;
	}


	public Measurement getMeasurement() {
		return measurement;
	}


	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}


	public String getDesignTechnologyDosage() {
		return designTechnologyDosage;
	}


	public void setDesignTechnologyDosage(String designTechnologyDosage) {
		this.designTechnologyDosage = designTechnologyDosage;
	}


	public String getDesignParameterName() {
		return designParameterName;
	}


	public void setDesignParameterName(String designParameterName) {
		this.designParameterName = designParameterName;
	}




}