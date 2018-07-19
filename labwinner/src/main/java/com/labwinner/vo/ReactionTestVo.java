package com.labwinner.vo;

import java.util.List;

import com.labwinner.domain.ReactionTest;

public class ReactionTestVo {

	private ReactionTest reactionTest;
	
	private List<Integer> deviceList;
	
	private List<String> files;
	
	private List<String> filenames;
	
	private List<String> urls;
	
	public ReactionTestVo (){}

	public ReactionTest getReactionTest() {
		return reactionTest;
	}

	public void setReactionTest(ReactionTest reactionTest) {
		this.reactionTest = reactionTest;
	}

	public List<Integer> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<Integer> deviceList) {
		this.deviceList = deviceList;
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

	public List<String> getFilenames() {
		return filenames;
	}

	public void setFilenames(List<String> filenames) {
		this.filenames = filenames;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
}
