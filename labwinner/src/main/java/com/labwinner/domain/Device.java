package com.labwinner.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


	/**
	 * 试验设备实体类
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月17日 下午6:22:32
	 * 
	 * @Company 西安博文同创信息技术有限公司
	 * @Copyright Copyright (c) 2017, All rights reserved.
	 */
	
public class Device implements java.io.Serializable {

	/**
	 * 设备主键
	 */
	private Integer deviceId;
	
	/**
	 * 设备名称
	 */
	private String deviceName;
	
	/**
	 * 设备位置主键
	 */
	private DeviceLocation deviceLocation;
	
	
	/**
	 * 设备类型主键
	 */
    private DeviceType deviceType;

	
	/**
	 * 设备厂商
	 */
	private String vendor;
	
	/**
	 * 设备型号
	 */
	private String model;
	
	/**
	 * 同时使用设备人数
	 */
	private Integer numberPeoplet;
	
	/**
	 * 关键设备
	 */
	private String keyEquipment;
	
	/**
	 * 创建人
	 */
	private String creater;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 修改人
	 */
	private String modifier;
	
	private String barCode;
	
	private String qrName;
	
	/**
	 * 菜单权限
	 */
	private String menuDeviceOpt;
	
	/**
	 * 菜单权限
	 */
	private String menuAppointOpt;

	/**
	 * 修改日期
	 */
	private Date modifyDate;
	
	/**
	 * 分析设备关系表
	 */
	private Set<AnalyticsDevice> analyticsDevices = new HashSet<AnalyticsDevice>(
			0);
	
	/**
	 * 试验设备预约表
	 */
	private Set<DeviceAppointment> deviceAppointments = new HashSet<DeviceAppointment>(
			0);
	
	/**
	 * 试验设备关系表
	 */
	private Set<ReactionDevice> reactionDevices = new HashSet<ReactionDevice>(0);
	
	/**
	 * 试验设备关系表
	 */
	private Set<TestDevice> testDevices = new HashSet<TestDevice>(0);
	
	/**
	 * 设备图片表
	 */
	private List<DevicePicture> devicePictures = new ArrayList<DevicePicture>(0);
	
	/**
	 * 设备视频表
	 */
	private List<DeviceVideo> deviceVideos = new ArrayList<DeviceVideo>(0);

	public Device() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Device(Integer deviceId, String deviceName,
			DeviceLocation deviceLocation, DeviceType deviceType,
			String vendor, String model, Integer numberPeoplet,
			String keyEquipment, String creater, Date createDate,
			String modifier, String barCode, String qrName,
			String menuDeviceOpt, String menuAppointOpt, Date modifyDate,
			Set<AnalyticsDevice> analyticsDevices,
			Set<DeviceAppointment> deviceAppointments,
			Set<ReactionDevice> reactionDevices, Set<TestDevice> testDevices,
			List<DevicePicture> devicePictures, List<DeviceVideo> deviceVideos) {
		super();
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.deviceLocation = deviceLocation;
		this.deviceType = deviceType;
		this.vendor = vendor;
		this.model = model;
		this.numberPeoplet = numberPeoplet;
		this.keyEquipment = keyEquipment;
		this.creater = creater;
		this.createDate = createDate;
		this.modifier = modifier;
		this.barCode = barCode;
		this.qrName = qrName;
		this.menuDeviceOpt = menuDeviceOpt;
		this.menuAppointOpt = menuAppointOpt;
		this.modifyDate = modifyDate;
		this.analyticsDevices = analyticsDevices;
		this.deviceAppointments = deviceAppointments;
		this.reactionDevices = reactionDevices;
		this.testDevices = testDevices;
		this.devicePictures = devicePictures;
		this.deviceVideos = deviceVideos;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public DeviceLocation getDeviceLocation() {
		return deviceLocation;
	}

	public void setDeviceLocation(DeviceLocation deviceLocation) {
		this.deviceLocation = deviceLocation;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getNumberPeoplet() {
		return numberPeoplet;
	}

	public void setNumberPeoplet(Integer numberPeoplet) {
		this.numberPeoplet = numberPeoplet;
	}

	public String getKeyEquipment() {
		return keyEquipment;
	}

	public void setKeyEquipment(String keyEquipment) {
		this.keyEquipment = keyEquipment;
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

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getQrName() {
		return qrName;
	}

	public void setQrName(String qrName) {
		this.qrName = qrName;
	}

	public String getMenuDeviceOpt() {
		return menuDeviceOpt;
	}

	public void setMenuDeviceOpt(String menuDeviceOpt) {
		this.menuDeviceOpt = menuDeviceOpt;
	}

	public String getMenuAppointOpt() {
		return menuAppointOpt;
	}

	public void setMenuAppointOpt(String menuAppointOpt) {
		this.menuAppointOpt = menuAppointOpt;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Set<AnalyticsDevice> getAnalyticsDevices() {
		return analyticsDevices;
	}

	public void setAnalyticsDevices(Set<AnalyticsDevice> analyticsDevices) {
		this.analyticsDevices = analyticsDevices;
	}

	public Set<DeviceAppointment> getDeviceAppointments() {
		return deviceAppointments;
	}

	public void setDeviceAppointments(Set<DeviceAppointment> deviceAppointments) {
		this.deviceAppointments = deviceAppointments;
	}

	public Set<ReactionDevice> getReactionDevices() {
		return reactionDevices;
	}

	public void setReactionDevices(Set<ReactionDevice> reactionDevices) {
		this.reactionDevices = reactionDevices;
	}

	public Set<TestDevice> getTestDevices() {
		return testDevices;
	}

	public void setTestDevices(Set<TestDevice> testDevices) {
		this.testDevices = testDevices;
	}

	public List<DevicePicture> getDevicePictures() {
		return devicePictures;
	}

	public void setDevicePictures(List<DevicePicture> devicePictures) {
		this.devicePictures = devicePictures;
	}

	public List<DeviceVideo> getDeviceVideos() {
		return deviceVideos;
	}

	public void setDeviceVideos(List<DeviceVideo> deviceVideos) {
		this.deviceVideos = deviceVideos;
	}

	

	
}