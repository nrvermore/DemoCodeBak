package com.labwinner.vo;

public class LocationPdfVo {
	
	/**
	 * 位置名称
	 */
	private String label;
	
	/**
	 * 二维码路径
	 */
	private String iamgePath;
	
	public LocationPdfVo(){}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIamgePath() {
		return iamgePath;
	}

	public void setIamgePath(String iamgePath) {
		this.iamgePath = iamgePath;
	};
	

}
