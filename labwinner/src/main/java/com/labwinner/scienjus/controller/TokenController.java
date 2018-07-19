package com.labwinner.scienjus.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.SysUser;
import com.labwinner.reids.IUserRedisService;
import com.labwinner.scienjus.authorization.annotation.Authorization;
import com.labwinner.scienjus.authorization.annotation.CurrentUser;
import com.labwinner.scienjus.authorization.manager.TokenManager;
import com.labwinner.scienjus.authorization.model.TokenModel;
import com.labwinner.scienjus.domain.User;
import com.labwinner.scienjus.model.ResultModel;
import com.labwinner.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
 * @author zwl
 */
@RestController
public class TokenController {

    @Autowired
    private SysUserService sysUserService;
	@Autowired  
	private IUserRedisService iUserRedisService;

    @Autowired
    public TokenManager tokenManager;

    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    public ResponseEntity<ResultModel> login(HttpSession session) {
		String username = getPrincipal();
		SysUser user = sysUserService.getByUsername(username).get(0);
        //生成一个token，保存用户登录状态
		String uuid=UUID.randomUUID().toString();
        TokenModel model = tokenManager.createToken(uuid+username,uuid+username);
        return new ResponseEntity<>(ResultModel.ok(model), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    public ResponseEntity<ResultModel> logout(@CurrentUser User user) {
        tokenManager.deleteToken(user.getId());
        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }


    
    
    @RequestMapping(value = "/tokens/findAll", method = RequestMethod.GET)
    public ResultVo findAll() {
    	ResultVo res=new ResultVo();
    	List<String> token=iUserRedisService.findAll();
    	res.setResultData(token);
    	return res;
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
