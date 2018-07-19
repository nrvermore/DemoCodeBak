package com.labwinner.vo;

import com.labwinner.domain.SysUser;

public class UnreadMessageVo {
	
	private Integer userMessages;
	private Integer busMessages;
	private Integer sysMessages;
	private Integer dailyMessage;
	private Integer weeklyMessage;
	private String imageUrl;
	private SysUser sysUser;
	
	public UnreadMessageVo(){}

	public Integer getUserMessages() {
		return userMessages;
	}

	public void setUserMessages(Integer userMessages) {
		this.userMessages = userMessages;
	}

	public Integer getBusMessages() {
		return busMessages;
	}

	public void setBusMessages(Integer busMessages) {
		this.busMessages = busMessages;
	}

	public Integer getSysMessages() {
		return sysMessages;
	}

	public void setSysMessages(Integer sysMessages) {
		this.sysMessages = sysMessages;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Integer getDailyMessage() {
		return dailyMessage;
	}

	public void setDailyMessage(Integer dailyMessage) {
		this.dailyMessage = dailyMessage;
	}

	public Integer getWeeklyMessage() {
		return weeklyMessage;
	}

	public void setWeeklyMessage(Integer weeklyMessage) {
		this.weeklyMessage = weeklyMessage;
	}
	
	
}
