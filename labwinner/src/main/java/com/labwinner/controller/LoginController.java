package com.labwinner.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.domain.SysUser;
import com.labwinner.service.SysUserService;

/**
 * @Description 登录
 * @author liuhq
 * @version V1.0
 * @date 2017年6月1日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class LoginController {

	private static Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	SysUserService sysUserService;
	@Value("${sysUserPhone.url-path}")
	String userImageUrl;

	/**
	 * @Description 登录
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月1日
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public SysUser login() {
		SysUser sysUser = new SysUser();
		String username = getPrincipal();
		logger.debug("当前登录用户：" + username);
		List<SysUser> list = sysUserService.getByUsername(username);
		if (list != null && list.size() > 0) {
			sysUser = list.get(0);
			if(sysUser.getUserImage()!=null){
				sysUser.setUserImage(userImageUrl+sysUser.getUserImage());
			}
		}
		return sysUser;
	}

	/**
	 * @Description 获取当前登录用户
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月1日
	 */
	public String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}