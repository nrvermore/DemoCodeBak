package com.labwinner.domain;

import java.util.Date;


	/**
	 * 签到实体类
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月17日 下午6:22:32
	 * 
	 * @Company 西安博文同创信息技术有限公司
	 * @Copyright Copyright (c) 2017, All rights reserved.
	 */
	
public class SignIn implements java.io.Serializable {

	/**
	 * 签到主键
	 */
	private Integer signInId;
	
	/**
	 * 签到人
	 */
	private SysUser sysUser;
	
	/**
	 * 签到日期
	 */
	private Date signDate;
	
	
	/**
	 * 签到纬度
	 */
    private String signLatitude;
    
    /**
     * 签到经度
     */
    private String signLongitude;

	/**
	 * 签到内容
	 */
	private String signContent;
	
	/**
	 * 签到状态
	 */
	private Integer signState;
	
	/**
	 * 签到地点
	 */
	private String signAddr;
	
	/**
	 * 是否迟到
	 */
	private Integer flag;
	
	/**
	 * 备用字段2
	 */
	private String reserveField2;

	public SignIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignIn(Integer signInId, SysUser sysUser, Date signDate,
			String signLatitude, String signLongitude, String signContent,
			Integer signState, String signAddr, String reserveField2) {
		super();
		this.signInId = signInId;
		this.sysUser = sysUser;
		this.signDate = signDate;
		this.signLatitude = signLatitude;
		this.signLongitude = signLongitude;
		this.signContent = signContent;
		this.signState = signState;
		this.signAddr = signAddr;
		this.reserveField2 = reserveField2;
	}

	public Integer getSignInId() {
		return signInId;
	}

	public void setSignInId(Integer signInId) {
		this.signInId = signInId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getSignLatitude() {
		return signLatitude;
	}

	public void setSignLatitude(String signLatitude) {
		this.signLatitude = signLatitude;
	}

	public String getSignLongitude() {
		return signLongitude;
	}

	public void setSignLongitude(String signLongitude) {
		this.signLongitude = signLongitude;
	}

	public String getSignContent() {
		return signContent;
	}

	public void setSignContent(String signContent) {
		this.signContent = signContent;
	}

	public Integer getSignState() {
		return signState;
	}

	public void setSignState(Integer signState) {
		this.signState = signState;
	}


	public String getSignAddr() {
		return signAddr;
	}

	public void setSignAddr(String signAddr) {
		this.signAddr = signAddr;
	}

	public String getReserveField2() {
		return reserveField2;
	}

	public void setReserveField2(String reserveField2) {
		this.reserveField2 = reserveField2;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}