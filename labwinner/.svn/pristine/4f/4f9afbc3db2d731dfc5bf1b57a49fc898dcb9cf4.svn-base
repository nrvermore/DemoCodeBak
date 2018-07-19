package com.labwinner.configuration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.core.Ordered;

/**
 * @Description 使用一个过滤器（Filter）来通过向所有的请求发送相同的CORS响应来充分实现<br/>
 *              允许根据跨域资源共享协议来对其进行访问。
 * @author liuhq
 * @version V1.0
 * @date 2017年6月5日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class MyCorsFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods",
				"POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "x-auth-token, x-requested-with, authorization, Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		response.setHeader("BeginTimestamp", sdf.format(date));
		response.setHeader("EndTimestamp", sdf.format(DateUtils.addSeconds(date, 86400)));
		if (!"OPTIONS".equals(request.getMethod())) {
			chain.doFilter(req, res);
		} else {
		}
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}