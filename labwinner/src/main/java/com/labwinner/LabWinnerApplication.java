package com.labwinner;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.domain.SysUser;

/**
 * @Description LabWinner Application
 * @author liuhq
 * @version V1.0
 * @date 2017年6月3日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@SpringBootApplication
@RestController
@EnableRedisHttpSession
@EnableJms
public class LabWinnerApplication {

	/**
	 * @Description 主方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月3日
	 */
	public static void main(String[] args) {
		SpringApplication.run(LabWinnerApplication.class, args);
	}

	/**
	 * @Description 根目录显示信息
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月3日
	 */
	@RequestMapping("/")
	public Message home() {
		return new Message("Welcome to LabWinner Web!");
	}

	/**
	 * 获取当前登录用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Principal user(Principal user) {
		return user;
	}

	/**
	 * 获取令牌
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public Map<String, String> token(HttpSession session) {
		return Collections.singletonMap("token", session.getId());
	}

	/**
	 * 从资源服务器提取自定义令牌
	 * 
	 * @return
	 */

	@Bean
	HeaderHttpSessionStrategy sessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}

}

class Message {
	private String id = UUID.randomUUID().toString();
	private String content;

	Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

}
