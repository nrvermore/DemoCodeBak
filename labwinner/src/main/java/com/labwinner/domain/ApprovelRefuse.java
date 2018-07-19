package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description 审批拒绝理由表
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class ApprovelRefuse implements java.io.Serializable {

	/**
	 * 拒绝主键主键
	 */
	private Integer refuseId;

	/**
	 * 拒绝理由
	 */
	private String refuseMemu;

	/**
	 * 备注
	 */
	private String refuseReason;

	
	private Integer flag;

	private Set<Approvel> approvels = new HashSet<Approvel>(
			0);
	
	public ApprovelRefuse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApprovelRefuse(Integer refuseId, String refuseMemu,
			String refuseReason, Integer flag, Set<Approvel> approvels) {
		super();
		this.refuseId = refuseId;
		this.refuseMemu = refuseMemu;
		this.refuseReason = refuseReason;
		this.flag = flag;
		this.approvels = approvels;
	}

	public Integer getRefuseId() {
		return refuseId;
	}

	public void setRefuseId(Integer refuseId) {
		this.refuseId = refuseId;
	}

	public String getRefuseMemu() {
		return refuseMemu;
	}

	public void setRefuseMemu(String refuseMemu) {
		this.refuseMemu = refuseMemu;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Set<Approvel> getApprovels() {
		return approvels;
	}

	public void setApprovels(Set<Approvel> approvels) {
		this.approvels = approvels;
	}



	
}