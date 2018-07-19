package com.labwinner.domain;

import java.util.ArrayList;
import java.util.List;


	/**
	 * 试验设计工艺步骤实体类
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月17日 下午6:22:32
	 * 
	 * @Company 西安博文同创信息技术有限公司
	 * @Copyright Copyright (c) 2017, All rights reserved.
	 */
	
public class DesignTechnologyProcess implements java.io.Serializable {

	/**
	 * 设计工艺步骤主键
	 */
	private Integer processId;
	
	/**
	 * 设计工艺主键
	 */
	private DesignTechnology designTechnology;
	
	/**
	 * 设计工艺步骤序号
	 */
	private Integer processNumber;
	
	
	/**
	 * 设计工艺步骤名称
	 */
    private String processName;

	
	/**
	 * 设计工艺步骤备注
	 */
	private String remark;
	
	/**
	 * 设计工艺用量表
	 */
	private List<DesignTechnologyDosage> designTechnologyDosages = new ArrayList<DesignTechnologyDosage>(
			0);

	public DesignTechnologyProcess() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DesignTechnologyProcess(Integer processId,
			DesignTechnology designTechnology, Integer processNumber,
			String processName, String remark,
			List<DesignTechnologyDosage> designTechnologyDosages) {
		super();
		this.processId = processId;
		this.designTechnology = designTechnology;
		this.processNumber = processNumber;
		this.processName = processName;
		this.remark = remark;
		this.designTechnologyDosages = designTechnologyDosages;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public DesignTechnology getDesignTechnology() {
		return designTechnology;
	}

	public void setDesignTechnology(DesignTechnology designTechnology) {
		this.designTechnology = designTechnology;
	}

	public Integer getProcessNumber() {
		return processNumber;
	}

	public void setProcessNumber(Integer processNumber) {
		this.processNumber = processNumber;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<DesignTechnologyDosage> getDesignTechnologyDosages() {
		return designTechnologyDosages;
	}

	public void setDesignTechnologyDosages(
			List<DesignTechnologyDosage> designTechnologyDosages) {
		this.designTechnologyDosages = designTechnologyDosages;
	}
	
    
	
}