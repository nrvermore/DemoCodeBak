package com.labwinner.vo;

import org.springframework.web.multipart.MultipartFile;

public class SysUserAppVo {
	
	/**
	 * 用户主键
	 */
	private Integer userId;

	/**
	 * 用户姓名
	 */
	private String realname;
	
	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 用户头像
	 */
	private String userImage;
	/**
	 * QQ号
	 */
	private String qq;

	/**
	 * 微信号
	 */
	private String weixin;

	/**
	 * 微博帐户
	 */
	private String blog;
	
	private MultipartFile file;

	private String testPassWord;

	public SysUserAppVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysUserAppVo(Integer userId, String realname, String password,
			String phone, String email, String userImage, String qq,
			String weixin, String blog, MultipartFile file, String testPassWord) {
		super();
		this.userId = userId;
		this.realname = realname;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.userImage = userImage;
		this.qq = qq;
		this.weixin = weixin;
		this.blog = blog;
		this.file = file;
		this.testPassWord = testPassWord;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}


	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getTestPassWord() {
		return testPassWord;
	}

	public void setTestPassWord(String testPassWord) {
		this.testPassWord = testPassWord;
	}

	
}
