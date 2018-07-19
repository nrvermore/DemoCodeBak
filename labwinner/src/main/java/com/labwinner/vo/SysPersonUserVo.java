package com.labwinner.vo;

import com.labwinner.domain.SysUser;

public class SysPersonUserVo {
	
	private String username;

	private String password;
	
	private String verCode;


	public SysPersonUserVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysPersonUserVo(String username,String password,String verCode) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerCode() {
		return verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}

	
	
	
}
