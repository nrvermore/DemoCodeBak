package com.labwinner.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	/**
	 * DeviceLocation entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 设备位置表
	 */
public class DeviceLocation implements java.io.Serializable {

	/**
	 * 设备位置主键
	 */
	private Integer deviceLocaId;
	
	/**
	 * 设备位置父主键
	 */
	private String deviceLocaPid;

	/**
	 * 位置名称
	 */
	private String label;

	
	private String parentName;

	private List<DeviceLocation> children = new ArrayList<DeviceLocation>();

	
	/**
	 * 试验设备表
	 */
	private Set<Device> devices = new HashSet<Device>(0);


	public DeviceLocation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DeviceLocation(Integer deviceLocaId, String deviceLocaPid,
			String label, String parentName, List<DeviceLocation> children,
			Set<Device> devices) {
		super();
		this.deviceLocaId = deviceLocaId;
		this.deviceLocaPid = deviceLocaPid;
		this.label = label;
		this.parentName = parentName;
		this.children = children;
		this.devices = devices;
	}


	public Integer getDeviceLocaId() {
		return deviceLocaId;
	}


	public void setDeviceLocaId(Integer deviceLocaId) {
		this.deviceLocaId = deviceLocaId;
	}


	public String getDeviceLocaPid() {
		return deviceLocaPid;
	}


	public void setDeviceLocaPid(String deviceLocaPid) {
		this.deviceLocaPid = deviceLocaPid;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	public List<DeviceLocation> getChildren() {
		return children;
	}


	public void setChildren(List<DeviceLocation> children) {
		this.children = children;
	}


	public Set<Device> getDevices() {
		return devices;
	}


	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}


	

}