package com.labwinner.domain;

/**
 * @Description 系统附件
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class SysAttachment implements java.io.Serializable {

	/**
	 * 附件主键
	 */
	private Integer attaId;

	/**
	 * 附件名称
	 */
	private String attaName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 附件内容
	 */
	private String attaContent;
	
	/**
	 * 附件路径
	 */
	private String attaPath;

	// Constructors

	/** default constructor */
	public SysAttachment() {
	}

	/** full constructor */
	public SysAttachment(String attaName, String remark, String attaContent, String attaPath) {
		this.attaName = attaName;
		this.remark = remark;
		this.attaContent = attaContent;
		this.attaPath = attaPath;
	}

	public Integer getAttaId() {
		return attaId;
	}

	public void setAttaId(Integer attaId) {
		this.attaId = attaId;
	}

	public String getAttaName() {
		return attaName;
	}

	public void setAttaName(String attaName) {
		this.attaName = attaName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAttaContent() {
		return attaContent;
	}

	public void setAttaContent(String attaContent) {
		this.attaContent = attaContent;
	}

	public String getAttaPath() {
		return attaPath;
	}

	public void setAttaPath(String attaPath) {
		this.attaPath = attaPath;
	}
	
}