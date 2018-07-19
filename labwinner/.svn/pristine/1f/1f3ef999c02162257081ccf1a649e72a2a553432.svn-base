package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;


	/**
	 * DeviceState entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 设备状态表
	 */
public class DeviceState implements java.io.Serializable {

	/**
	 * 设备状态主键
	 */
	private Integer stateId;
	
	/**
	 * 设备状态
	 */
	private String deviceState;
	
	/**
	 * 试验设备预约表
	 */
	private Set<DeviceAppointment> deviceAppointments = new HashSet<DeviceAppointment>(
			0);

	// Constructors

	/** default constructor */
	public DeviceState() {
	}

	/** full constructor */
	public DeviceState(String deviceState,
			Set<DeviceAppointment> deviceAppointments) {
		this.deviceState = deviceState;
		this.deviceAppointments = deviceAppointments;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(String deviceState) {
		this.deviceState = deviceState;
	}

	public Set<DeviceAppointment> getDeviceAppointments() {
		return deviceAppointments;
	}

	public void setDeviceAppointments(Set<DeviceAppointment> deviceAppointments) {
		this.deviceAppointments = deviceAppointments;
	}

}