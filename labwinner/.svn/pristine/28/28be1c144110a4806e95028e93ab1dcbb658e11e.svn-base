package com.labwinner.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


@Configuration  
@EnableWebMvcSecurity  
public class RegisterWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter{
	
 
	 @Override
     protected void configure(HttpSecurity http) throws Exception {
		 http  
         .antMatcher("/**")  
         .httpBasic();
		 
		 http.
		 authorizeRequests()
         .antMatchers("/message/getTotalUnread").permitAll().and()  
           .csrf().disable() ;
 }

}
