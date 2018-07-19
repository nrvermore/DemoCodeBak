package com.labwinner.domain;

import java.sql.Timestamp;
import java.util.Date;

	/**
	 * DeviceAppointment entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 试验设备预约表
	 */
public class DeviceAppointment implements java.io.Serializable {

	/**
	 * 预约主键
	 */
	private Integer appointmentId;
	
	/**
	 * 用户主键
	 */
	private SysUser sysUser;
	
	/**
	 * 试验基本信息主键
	 */
	private Reaction reaction;
	
	/**
	 * 设备状态主键
	 */
	private DeviceState deviceState;
	
	/**
	 * 设备主键
	 */
	private Device device;
	
	/**
	 * 开始时间
	 */
	private Timestamp startDate;
	
	/**
	 * 结束时间
	 */
	private Timestamp endDate;
	
	/**
	 * 反馈人信息
	 */
	private SysUser feedFromPeople;
	
	/**
	 * 预约时间
	 */
	private Timestamp appointmenttDate;
	
	/**
	 * 创建人
	 */
	private String creater;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 修改人
	 */
	private String modifier;
	
	/**
	 * 修改时间
	 */
	private Date modifyDate;

	// Constructors

	/** default constructor */
	public DeviceAppointment() {
	}

	/** minimal constructor */
	public DeviceAppointment(Timestamp startDate, Timestamp endDate,
			Timestamp appointmenttDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.appointmenttDate = appointmenttDate;
	}

	/** full constructor */
	public DeviceAppointment(SysUser sysUser, Reaction reaction,
			DeviceState deviceState, Device device, Timestamp startDate,
			Timestamp endDate, SysUser feedFromPeople,
			Timestamp appointmenttDate, String creater, Date createDate,
			String modifier, Date modifyDate) {
		this.sysUser = sysUser;
		this.reaction = reaction;
		this.deviceState = deviceState;
		this.device = device;
		this.startDate = startDate;
		this.endDate = endDate;
		this.feedFromPeople = feedFromPeople;
		this.appointmenttDate = appointmenttDate;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Reaction getReaction() {
		return reaction;
	}

	public void setReaction(Reaction reaction) {
		this.reaction = reaction;
	}

	public DeviceState getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(DeviceState deviceState) {
		this.deviceState = deviceState;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public SysUser getFeedFromPeople() {
		return feedFromPeople;
	}

	public void setFeedFromPeople(SysUser feedFromPeople) {
		this.feedFromPeople = feedFromPeople;
	}

	public Timestamp getAppointmenttDate() {
		return appointmenttDate;
	}

	public void setAppointmenttDate(Timestamp appointmenttDate) {
		this.appointmenttDate = appointmenttDate;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}