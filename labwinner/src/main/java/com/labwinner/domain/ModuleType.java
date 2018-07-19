package com.labwinner.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
	/**
	 * 模块类型实体类
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月21日 下午5:32:03
	 * 
	 * @Company 西安博文同创信息技术有限公司
	 * @Copyright Copyright (c) 2017, All rights reserved.
	 */
	 
public class ModuleType implements java.io.Serializable {

	/**
	 * 模块类型主键
	 */
	private Integer moduleTypeId;
	
	/**
	 * 模块类型名称
	 */
	private String moduleTypeName;
	
	
	private Integer flag;
	
	
	private List<Questions> questions = new ArrayList<Questions>(0);

	public ModuleType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ModuleType(Integer moduleTypeId, String moduleTypeName,
			Integer flag, List<Questions> questions) {
		super();
		this.moduleTypeId = moduleTypeId;
		this.moduleTypeName = moduleTypeName;
		this.flag = flag;
		this.questions = questions;
	}

	public Integer getModuleTypeId() {
		return moduleTypeId;
	}

	public void setModuleTypeId(Integer moduleTypeId) {
		this.moduleTypeId = moduleTypeId;
	}

	public String getModuleTypeName() {
		return moduleTypeName;
	}

	public void setModuleTypeName(String moduleTypeName) {
		this.moduleTypeName = moduleTypeName;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	
	
}