package com.labwinner.scienjus.authorization.interceptor;

import com.labwinner.dao.UserDao;
import com.labwinner.domain.SysUser;
import com.labwinner.scienjus.authorization.annotation.Authorization;
import com.labwinner.scienjus.authorization.model.TokenModel;
import com.labwinner.scienjus.authorization.manager.TokenManager;
import com.labwinner.scienjus.config.Constants;
import com.labwinner.security.CustomUserService;
import com.labwinner.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 自定义拦截器，判断此次请求是否有权限
 * @see com.scienjus.authorization.annotation.Authorization
 * @author zwl
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    public TokenManager manager;
	@Autowired
	SysUserService sysUserService;
	@Autowired
	CustomUserService customUserService;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String authorization = request.getHeader(Constants.AUTHORIZATION);

        //验证token
        TokenModel model = manager.getToken(authorization);
        if(model!=null){
        	if (manager.checkToken(model)) {
                //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(Constants.CURRENT_USER_ID, authorization);
                String userName=authorization.substring(36, authorization.length());
                request.setAttribute("userName", authorization.substring(36, authorization.length()));
                //根据用户名username加载userDetails
                UserDetails userDetails = customUserService.loadUserByUsername(userName);

                //根据userDetails构建新的Authentication,这里使用了
                //PreAuthenticatedAuthenticationToken当然可以用其他token,如UsernamePasswordAuthenticationToken               
                PreAuthenticatedAuthenticationToken authentication = 
                      new PreAuthenticatedAuthenticationToken(userDetails, userDetails.getPassword(),userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return true;
            }else{
            	//response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }
        
        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if(authorization!=null){
        	if (method.getAnnotation(Authorization.class) != null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            	//response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }else{
            	 return true;
            }
        }
        return true;
    }
    
    
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            org.springframework.web.servlet.ModelAndView modelAndView)
            throws Exception {
        // 你自己的业务逻辑
    	int status=response.getStatus();
//    	System.out.println("=================================返回的状态为："+status);
        //下面这句话不要动，就这样放着。你在处理你的业务逻辑之后，spring会将你的请求和响应继续往容器传或者往客户端进行传递
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
	 * @Description 获取当前登录用户
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月1日
	 */
	public String getPrincipal() {
		String userName = null;
		Object principal=null;
		if(SecurityContextHolder.getContext()
				.getAuthentication()!=null){
			principal = SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
		}
		if(principal!=null){
			if (principal instanceof UserDetails) {
				userName = ((UserDetails) principal).getUsername();
			} else {
				userName = principal.toString();
			}
		}
		
		return userName;
	}
}
