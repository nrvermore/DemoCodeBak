package com.labwinner.vo;

public class DevicePdfVo {
	
	/**
	 * 设备名称
	 */
	private String deviceName;
	
	/**
	 * 设备类型名称
	 */
	private String deviceTypeName;
	
	/**
	 * 设备型号
	 */
	private String model;
	
	/**
	 * 设备厂商
	 */
	private String vendor;
	
	/**
	 * 二维码路径
	 */
	private String iamgePath;
	
	public DevicePdfVo(){}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getIamgePath() {
		return iamgePath;
	}

	public void setIamgePath(String iamgePath) {
		this.iamgePath = iamgePath;
	}

}
