package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

	/**
	 * RepresentationMap entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 化学品基础信息表征图谱
	 */
public class RepresentationMap implements java.io.Serializable {

	/**
	 * 表征图谱主键
	 */
	private Integer repMapId;
	
	/**
	 * 表征名称
	 */
	private String repName;
	
	/**
	 * 表征图片
	 */
	private String repImage;
	
	/**
	 * 化学品基础信息表
	 */
	private ChemicalParameter chemicalParameter;

	// Constructors

	/** default constructor */
	public RepresentationMap() {
	}

	/** full constructor */
	public RepresentationMap(String repName, String repImage,
			ChemicalParameter chemicalParameter) {
		this.repName = repName;
		this.repImage = repImage;
		this.chemicalParameter = chemicalParameter;
	}

	public Integer getRepMapId() {
		return repMapId;
	}

	public void setRepMapId(Integer repMapId) {
		this.repMapId = repMapId;
	}

	public String getRepName() {
		return repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	public String getRepImage() {
		return repImage;
	}

	public void setRepImage(String repImage) {
		this.repImage = repImage;
	}

	public ChemicalParameter getChemicalParameter() {
		return chemicalParameter;
	}

	public void setChemicalParameters(ChemicalParameter chemicalParameter) {
		this.chemicalParameter = chemicalParameter;
	}

	

}