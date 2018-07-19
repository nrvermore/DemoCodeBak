package com.labwinner.domain;

public class MarketAssistImage implements java.io.Serializable{
	
	/**
	 * 求助图片主键
	 */
	private Integer imageId;
	
	/**
	 * 图片名称
	 */
	private String imageName;
	
	/**
	 * 市场求助
	 */
	private MarketAssist marketAssist;
	
	public MarketAssistImage(){}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public MarketAssist getMarketAssist() {
		return marketAssist;
	}

	public void setMarketAssist(MarketAssist marketAssist) {
		this.marketAssist = marketAssist;
	};
	
	

}
