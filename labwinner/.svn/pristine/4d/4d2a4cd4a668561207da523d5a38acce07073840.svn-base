package com.labwinner.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description 签约机构
 * @author liuhq
 * @version V1.0
 * @date 2017年6月7日 上午9:47:52
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class SysSigningAgency implements java.io.Serializable {

	/**
	 * 主键
	 */
	private Integer agencyId;

	/**
	 * 行业主键
	 */
	private Industry industry;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 通讯地址
	 */
	private String postalAddress;

	/**
	 * 联系电话
	 */
	private String phone;

	/**
	 * 授权类型
	 */
	private String authType;

	/**
	 * 负责人姓名
	 */
	private String contactPerson;

	/**
	 * 机构类型
	 */
	private String agencyType;

	/**
	 * 最大用户数
	 */
	private Integer maxUsers;

	/**
	 * 签约开始时间
	 */
	private Date signStartDate;

	/**
	 * 签约结束时间
	 */
	private Date signDeadline;

	/**
	 * 创建日期
	 */
	private Timestamp createDate;

	/**
	 * 修改人
	 */
	private Integer modifier;

	/**
	 * 修改日期
	 */
	private Timestamp modifyDate;

	/**
	 * 创建人
	 */
	private Integer creater;

	/**
	 * 备用字段1
	 */
	private String reserveField1;

	/**
	 * 备用字段2
	 */
	private String reserveField2;

	/**
	 * 备用字段3
	 */
	private String reserveField3;

	/**
	 * 备用字段4
	 */
	private Timestamp reserveField4;

	/**
	 * 备用字段5
	 */
	private String sysSignTime;

	/**
	 * 经纬度
	 */
	private String latitudeLongitude;

	/**
	 * 用户表
	 */
	private List<SysUser> sysUsers = new ArrayList<SysUser>(0);
	
	/**
	 * 反馈信息表
	 */
	private List<Feedback> feedbacks = new ArrayList<Feedback>(0);

	// Constructors

	/** default constructor */
	public SysSigningAgency() {
	}

	public SysSigningAgency(Integer agencyId, Industry industry,
			String userName, String postalAddress, String phone,
			String authType, String contactPerson, String agencyType,
			Integer maxUsers, Date signStartDate, Date signDeadline,
			Timestamp createDate, Integer modifier, Timestamp modifyDate,
			Integer creater, String reserveField1, String reserveField2,
			String reserveField3, Timestamp reserveField4,
			String sysSignTime, String latitudeLongitude,
			List<SysUser> sysUsers, List<Feedback> feedbacks) {
		super();
		this.agencyId = agencyId;
		this.industry = industry;
		this.userName = userName;
		this.postalAddress = postalAddress;
		this.phone = phone;
		this.authType = authType;
		this.contactPerson = contactPerson;
		this.agencyType = agencyType;
		this.maxUsers = maxUsers;
		this.signStartDate = signStartDate;
		this.signDeadline = signDeadline;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.creater = creater;
		this.reserveField1 = reserveField1;
		this.reserveField2 = reserveField2;
		this.reserveField3 = reserveField3;
		this.reserveField4 = reserveField4;
		this.sysSignTime = sysSignTime;
		this.latitudeLongitude = latitudeLongitude;
		this.sysUsers = sysUsers;
		this.feedbacks = feedbacks;
	}

	public Integer getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Integer agencyId) {
		this.agencyId = agencyId;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getAgencyType() {
		return agencyType;
	}

	public void setAgencyType(String agencyType) {
		this.agencyType = agencyType;
	}

	public Integer getMaxUsers() {
		return maxUsers;
	}

	public void setMaxUsers(Integer maxUsers) {
		this.maxUsers = maxUsers;
	}

	public Date getSignStartDate() {
		return signStartDate;
	}

	public void setSignStartDate(Date signStartDate) {
		this.signStartDate = signStartDate;
	}

	public Date getSignDeadline() {
		return signDeadline;
	}

	public void setSignDeadline(Date signDeadline) {
		this.signDeadline = signDeadline;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public String getReserveField1() {
		return reserveField1;
	}

	public void setReserveField1(String reserveField1) {
		this.reserveField1 = reserveField1;
	}

	public String getReserveField2() {
		return reserveField2;
	}

	public void setReserveField2(String reserveField2) {
		this.reserveField2 = reserveField2;
	}

	public String getReserveField3() {
		return reserveField3;
	}

	public void setReserveField3(String reserveField3) {
		this.reserveField3 = reserveField3;
	}

	public Timestamp getReserveField4() {
		return reserveField4;
	}

	public void setReserveField4(Timestamp reserveField4) {
		this.reserveField4 = reserveField4;
	}

	public String getSysSignTime() {
		return sysSignTime;
	}

	public void setSysSignTime(String sysSignTime) {
		this.sysSignTime = sysSignTime;
	}

	public String getLatitudeLongitude() {
		return latitudeLongitude;
	}

	public void setLatitudeLongitude(String latitudeLongitude) {
		this.latitudeLongitude = latitudeLongitude;
	}

	public List<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}



	
}