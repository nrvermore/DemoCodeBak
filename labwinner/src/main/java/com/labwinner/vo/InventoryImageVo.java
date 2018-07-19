package com.labwinner.vo;

import java.util.List;

public class InventoryImageVo {

	/*
	 * 库存Id集合
	 */
	private Integer groupId;
	
	/*
	 * 要删除的图片url
	 */
	private List<String> urls;
	
	/*
	 * 要上传的base64图片
	 */
	private List<String> imgs;
	
	public InventoryImageVo(){}


	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
	
	
}
