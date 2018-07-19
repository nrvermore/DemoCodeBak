package com.labwinner.vo;

import org.springframework.web.multipart.MultipartFile;

public class SmsAppVo {
	
	/**
	 * 用户主键
	 */
	private String phoneNumer;


	public SmsAppVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SmsAppVo(String phoneNumer) {
		super();
		this.phoneNumer = phoneNumer;
		
	}

	public String getPhoneNumer() {
		return phoneNumer;
	}

	public void setPhoneNumer(String phoneNumer) {
		this.phoneNumer = phoneNumer;
	}

	

	
}
