package com.labwinner.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.labwinner.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 自定义UserDetailsService接口
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class CustomUserService implements UserDetailsService {
	
	private static final Logger log = LoggerFactory
			.getLogger(CustomUserService.class);

	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		SysUser user = new SysUser();
		List<SysUser> list = userDao.findByUserName(username);
		if (list == null||list.size()==0) {
			throw new UsernameNotFoundException("用户名不存在");
		} else {
			user = list.get(0);
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (SysRole role : user.getRoles()) {
//			authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//			log.info(role.getName());
		}
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), authorities);
	}
}
