package com.labwinner.domain;

/**
 * AnalyticsDevice entity. @author MyEclipse Persistence Tools
 */
    /**
     * 分析设备关系表
     */
public class AnalyticsDevice implements java.io.Serializable {
    
	/**
	 * 分析设备表主键
	 */
	private Integer analyticsDeviceId;
	
	/**
	 * 设备表主键
	 */
	private Device device;
	
	/**
	 *分析表主键 
	 */
	private Analytics analytics;


	/** default constructor */
	public AnalyticsDevice() {
	}

	/** full constructor */
	public AnalyticsDevice(Device device, Analytics analytics) {
		this.device = device;
		this.analytics = analytics;
	}

	public Integer getAnalyticsDeviceId() {
		return analyticsDeviceId;
	}

	public void setAnalyticsDeviceId(Integer analyticsDeviceId) {
		this.analyticsDeviceId = analyticsDeviceId;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Analytics getAnalytics() {
		return analytics;
	}

	public void setAnalytics(Analytics analytics) {
		this.analytics = analytics;
	}

	

}