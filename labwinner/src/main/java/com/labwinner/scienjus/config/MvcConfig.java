package com.labwinner.scienjus.config;

import com.labwinner.scienjus.authorization.interceptor.AuthorizationInterceptor;
import com.labwinner.scienjus.authorization.resolvers.CurrentUserMethodArgumentResolver;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置类，增加自定义拦截器和解析器
 * @see com.scienjus.authorization.resolvers.CurrentUserMethodArgumentResolver
 * @see com.scienjus.authorization.interceptor.AuthorizationInterceptor
 * @author zwl
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).excludePathPatterns("/message/getTotalUnread");//不拦截的请求;
       registry.addInterceptor(authorizationInterceptor).excludePathPatterns("/register/**");//不拦截的请求;
       registry.addInterceptor(authorizationInterceptor).excludePathPatterns("/findPort/**");//不拦截的请求;
       registry.addInterceptor(authorizationInterceptor).excludePathPatterns("/CountCicle/**");//不拦截的请求;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }
   
}
