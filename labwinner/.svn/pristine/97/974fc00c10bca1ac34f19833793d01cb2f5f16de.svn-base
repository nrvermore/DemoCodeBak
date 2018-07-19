package com.labwinner.domain;

import java.util.ArrayList;
import java.util.List;


	/**
	 * 试验设计工艺实体类
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月17日 下午6:22:32
	 * 
	 * @Company 西安博文同创信息技术有限公司
	 * @Copyright Copyright (c) 2017, All rights reserved.
	 */
	
public class DesignTechnology implements java.io.Serializable {

	/**
	 * 设计工艺主键
	 */
	private Integer technologyId;
	
	/**
	 * 设计工艺名称
	 */
	private String technologyName;
	
	/**
	 * 设计工艺步骤个数
	 */
	private Integer processeesNum;
	
	/**
	 * 设计工艺步骤个数
	 */
	private Integer technologyNum;
	
	/**
	 * 设计工艺关系表
	 */
	private List<DesignTechnologyRelation> designTechnologyRelations = new ArrayList<DesignTechnologyRelation>(
			0);

	/**
	 * 设计工艺步骤表
	 */
	private List<DesignTechnologyProcess> designTechnologyProcesses = new ArrayList<DesignTechnologyProcess>(
			0);

	public DesignTechnology() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DesignTechnology(Integer technologyId, String technologyName,
			Integer processeesNum, Integer technologyNum,
			List<DesignTechnologyRelation> designTechnologyRelations,
			List<DesignTechnologyProcess> designTechnologyProcesses) {
		super();
		this.technologyId = technologyId;
		this.technologyName = technologyName;
		this.processeesNum = processeesNum;
		this.technologyNum = technologyNum;
		this.designTechnologyRelations = designTechnologyRelations;
		this.designTechnologyProcesses = designTechnologyProcesses;
	}

	public Integer getTechnologyId() {
		return technologyId;
	}

	public void setTechnologyId(Integer technologyId) {
		this.technologyId = technologyId;
	}

	public String getTechnologyName() {
		return technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	public Integer getProcesseesNum() {
		return processeesNum;
	}

	public void setProcesseesNum(Integer processeesNum) {
		this.processeesNum = processeesNum;
	}

	public Integer getTechnologyNum() {
		return technologyNum;
	}

	public void setTechnologyNum(Integer technologyNum) {
		this.technologyNum = technologyNum;
	}

	public List<DesignTechnologyRelation> getDesignTechnologyRelations() {
		return designTechnologyRelations;
	}

	public void setDesignTechnologyRelations(
			List<DesignTechnologyRelation> designTechnologyRelations) {
		this.designTechnologyRelations = designTechnologyRelations;
	}

	public List<DesignTechnologyProcess> getDesignTechnologyProcesses() {
		return designTechnologyProcesses;
	}

	public void setDesignTechnologyProcesses(
			List<DesignTechnologyProcess> designTechnologyProcesses) {
		this.designTechnologyProcesses = designTechnologyProcesses;
	}

	
	
}