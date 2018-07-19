package com.labwinner.vo;

import java.util.List;

public class ImageVo {
	
	/*
	 * 库存Id集合
	 */
	private List<Integer> ids;
	
	/*
	 * 要删除的图片url
	 */
	private List<String> urls;
	
	/*
	 * 要上传的base64图片
	 */
	private List<String> imgs;
	
	public ImageVo(){}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
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
