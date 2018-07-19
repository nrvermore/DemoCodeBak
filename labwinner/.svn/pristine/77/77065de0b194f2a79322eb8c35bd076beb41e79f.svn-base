package com.labwinner.domain;

import java.util.Date;

/**
 * Approvel entity. @author MyEclipse Persistence Tools
 */
    /**
     * 审批表
     */
public class Approvel implements java.io.Serializable {

    /**
     * 审批表主键
     */
	private Integer approveId;
	
	/**
     * 原料采购表主键
     */
	private MaterialPurchase materialPurchase;

	/**
     * 审批人用户表主键
     */
	private SysUser appSysUser;
	
	/**
     * 审批日期
     */
	private Date approvelDate;
	
	/**
     * 审批意见
     */
	private String approvalOpinion;
	
	/**
     * 审批结果
     */
	private String refuseReason;
	/**
     * 拒绝理由
     */
	private ApprovelRefuse approvelRefuse;

	private ApprovalResult approvalResult;
	/** default constructor */
	public Approvel() {
	}

	/** full constructor */
	public Approvel(MaterialPurchase materialPurchase, SysUser appSysUser,String refuseReason,
			Date approvelDate, String approvalOpinion,ApprovelRefuse approvelRefuse,ApprovalResult approvalResult) {
		this.materialPurchase = materialPurchase;
		this.appSysUser = appSysUser;
		this.approvelDate = approvelDate;
		this.approvalOpinion = approvalOpinion;
		this.refuseReason = refuseReason;
		this.approvelRefuse=approvelRefuse;
		this.approvalResult=approvalResult;
	}

	public Integer getApproveId() {
		return approveId;
	}

	public void setApproveId(Integer approveId) {
		this.approveId = approveId;
	}

	public MaterialPurchase getMaterialPurchase() {
		return materialPurchase;
	}

	public void setMaterialPurchase(MaterialPurchase materialPurchase) {
		this.materialPurchase = materialPurchase;
	}

	

	public SysUser getAppSysUser() {
		return appSysUser;
	}

	public void setAppSysUser(SysUser appSysUser) {
		this.appSysUser = appSysUser;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public Date getApprovelDate() {
		return approvelDate;
	}

	public void setApprovelDate(Date approvelDate) {
		this.approvelDate = approvelDate;
	}

	public String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}



	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseMemu(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public ApprovelRefuse getApprovelRefuse() {
		return approvelRefuse;
	}

	public void setApprovelRefuse(ApprovelRefuse approvelRefuse) {
		this.approvelRefuse = approvelRefuse;
	}

	public ApprovalResult getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(ApprovalResult approvalResult) {
		this.approvalResult = approvalResult;
	}


	

}