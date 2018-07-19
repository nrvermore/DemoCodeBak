package com.labwinner.domain;

	/**
	 * ReactionDevice entity. @author MyEclipse Persistence Tools
	 */
	/**
	 * 试验设备关系表
	 */
public class ReactionDevice implements java.io.Serializable {

	/**
	 * 试验设备主键
	 */
	private Integer reactionDeviceId;
	
	/**
	 * 设备主键
	 */
	private Device device;
	
	/**
	 * 试验记录主键
	 */
	private ReactionProcess reactionProcess;

	// Constructors

	/** default constructor */
	public ReactionDevice() {
	}

	/** full constructor */
	public ReactionDevice(Device device, ReactionProcess reactionProcess) {
		this.device = device;
		this.reactionProcess = reactionProcess;
	}

	public Integer getReactionDeviceId() {
		return reactionDeviceId;
	}

	public void setReactionDeviceId(Integer reactionDeviceId) {
		this.reactionDeviceId = reactionDeviceId;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public ReactionProcess getReactionProcess() {
		return reactionProcess;
	}

	public void setReactionProcess(ReactionProcess reactionProcess) {
		this.reactionProcess = reactionProcess;
	}

	

}