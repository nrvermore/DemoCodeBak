package com.labwinner.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.labwinner.domain.Device;

public class DeviceVo {
	
	private Device device;
	
	private List<MultipartFile> files;
	
	private List<String> imgStrs;

	private List<String> urls;
	
	//要上传的视频
	private List<String> videos;
	
	//要删除的视频
	private List<String> videoUrls;
	
	private String videoName;
	/**
	 * 操作类型（1：新增；2：修改；3：未修改保存）
	 */
	private String opsType;

	public DeviceVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeviceVo(Device device, List<MultipartFile> files,
			List<String> imgStrs, List<String> urls, List<String> videos,
			List<String> videoUrls, String videoName, String opsType) {
		super();
		this.device = device;
		this.files = files;
		this.imgStrs = imgStrs;
		this.urls = urls;
		this.videos = videos;
		this.videoUrls = videoUrls;
		this.videoName = videoName;
		this.opsType = opsType;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public List<String> getImgStrs() {
		return imgStrs;
	}

	public void setImgStrs(List<String> imgStrs) {
		this.imgStrs = imgStrs;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public List<String> getVideos() {
		return videos;
	}

	public void setVideos(List<String> videos) {
		this.videos = videos;
	}

	public List<String> getVideoUrls() {
		return videoUrls;
	}

	public void setVideoUrls(List<String> videoUrls) {
		this.videoUrls = videoUrls;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getOpsType() {
		return opsType;
	}

	public void setOpsType(String opsType) {
		this.opsType = opsType;
	}
	
}
