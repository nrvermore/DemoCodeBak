package com.labwinner.domain;

import java.util.HashSet;
import java.util.Set;


	/**
	 * 设备类型实体类
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月21日 下午5:32:03
	 * 
	 * @Company 西安博文同创信息技术有限公司
	 * @Copyright Copyright (c) 2017, All rights reserved.
	 */
	 
public class DeviceType implements java.io.Serializable {

	/**
	 * 设备类型主键
	 */
	private Integer deviceTypeId;
	
	/**
	 * 设备类型名称
	 */
	private String deviceTypeName;
	
	/**
	 * 试验设备表
	 */
	private Set<Device> devices = new HashSet<Device>(0);

	// Constructors

	/** default constructor */
	public DeviceType() {
	}

	/** full constructor */
	public DeviceType(String deviceTypeName, Set<Device> devices) {
		this.deviceTypeName = deviceTypeName;
		this.devices = devices;
	}

	public Integer getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

    public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

}