package com.labwinner.domain;

	/**
	 * DevicePicture entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 设备图片表
	 */
public class DevicePicture implements java.io.Serializable {

	/**
	 * 设备图片主键
	 */
	private Integer picturesId;
	
	/**
	 * 设备主键
	 */
	private Device device;
	
	/**
	 * 图片
	 */
	private String picture;
	
	/**
	 * 图片备注
	 */
	private String pictureNote;

	// Constructors

	/** default constructor */
	public DevicePicture() {
	}

	/** full constructor */
	public DevicePicture(Device device, String picture, String pictureNote) {
		this.device = device;
		this.picture = picture;
		this.pictureNote = pictureNote;
	}

	public Integer getPicturesId() {
		return picturesId;
	}

	public void setPicturesId(Integer picturesId) {
		this.picturesId = picturesId;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPictureNote() {
		return pictureNote;
	}

	public void setPictureNote(String pictureNote) {
		this.pictureNote = pictureNote;
	}

}