package com.labwinner.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.Sms;
import com.labwinner.domain.SysRole;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.service.SmsService;
import com.labwinner.service.SysUserService;
import com.labwinner.smsSend.SmsSendUtil;
import com.labwinner.util.FileUtil;
import com.labwinner.util.MD5Util;
import com.labwinner.vo.SmsAppVo;
import com.labwinner.vo.SysPersonUserVo;

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
public class RegisterController {

	private static Logger logger = LoggerFactory
			.getLogger(RegisterController.class);

	@Autowired
	SysUserService sysUserService;
	
	@Autowired
	SmsService smsService;
	
	@Value("${yunpian.apikey}")
	String apikey;

	@RequestMapping(value = "/register/addAppPersonal", method = RequestMethod.POST)
	public ResultVo updateAppPersonal(
			@RequestBody SysPersonUserVo sysUser)
			{
		ResultVo resultVo = new ResultVo();
		String verCode=sysUser.getVerCode();
		Sms sms=smsService.findVerByName(verCode);
		if(sms==null){
			SysUser sysUserApp=new SysUser();
			SysRole sysRole=new SysRole();
			SysSigningAgency sysSigningAgency=new SysSigningAgency();
			sysSigningAgency.setAgencyId(2);
			sysRole.setRoleId(2);
			sysUserApp.setUsername(sysUser.getUsername());
			MD5Util md=new MD5Util();
			String password=md.encode(sysUser.getPassword());
			sysUserApp.setPassword(password);
			sysUserApp.setPhone(sysUser.getPassword());
			sysUserApp.setSysRole(sysRole);
			sysUserApp.setUserState("1");
			sysUserApp.setRealname(sysUser.getPassword());
			sysUserApp.setSysSigningAgency(sysSigningAgency);
			try {
				sysUserService.save(sysUserApp);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("注册成功！");
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("服务器异常！");
			}
		}else{
			resultVo.setErrCode(2);
			resultVo.setErrMsg("验证码错误！");
		}
		return resultVo;
		
	}
	
	
	
	@RequestMapping(value = "/register/add", method = RequestMethod.POST)
	public ResultVo addAppPersonal(){
		ResultVo resultVo = new ResultVo();
		SysUser sysUserApp=new SysUser();
		sysUserApp.setUsername("1111");
		sysUserApp.setPassword("22222");
		try {
			sysUserService.save(sysUserApp);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("保存成功");
		} catch (Exception e) {
			// TODO: handle exception
			
			resultVo.setErrCode(1);
			resultVo.setErrMsg("保存失败");
		}
		
		return resultVo;
		
	}
	
	
	@RequestMapping(value = "/register/SendSms", method = RequestMethod.POST)
	public ResultVo SendSms(@RequestBody SmsAppVo smsAppVo) throws IOException{
		ResultVo resultVo = new ResultVo();
		List<SysUser> sysUser=sysUserService.getByUsername(smsAppVo.getPhoneNumer());
		if(sysUser!=null&&sysUser.size()>0){
			resultVo.setErrCode(2);
			resultVo.setErrMsg("此号码已注册!");
		}else{
			SmsSendUtil smsSendUtil=new SmsSendUtil();
			String encode=getEncode();
			String text= "【新材赢家】您的验证码是"+encode;
			try {
				Sms sms=new Sms();
				sms.setPhone(smsAppVo.getPhoneNumer());
				sms.setVerification(encode);
				smsService.save(sms);
				String res=smsSendUtil.sendSms(apikey, text, smsAppVo.getPhoneNumer());
				String code=res.substring(res.indexOf(":")+1,res.indexOf(":")+2);
				if("0".equals(code)){
					resultVo.setErrCode(0);
					resultVo.setErrMsg("send success");
				}else{
					resultVo.setErrCode(1);
					resultVo.setErrMsg("服务器异常!");
				}
				
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("服务器异常!");
			}
		}
		
		return resultVo;
		
	}

	@RequestMapping(value = "/register/sendVercode", method = RequestMethod.POST)
	public ResultVo sendVercode(@RequestBody SysPersonUserVo sysUser){
		ResultVo resultVo = new ResultVo();
		List<SysUser> users=sysUserService.getByNameOrPho(sysUser.getUsername());
		if(users!=null&&users.size()>0){
			SmsSendUtil smsSendUtil=new SmsSendUtil();
			String encode=getEncode();
			String text= "【新材赢家】您的验证码是"+encode;
			try {
				Sms sms=new Sms();
				sms.setPhone(users.get(0).getPhone());
				sms.setVerification(encode);
				sms.setCreateDate(new Date());
				smsService.save(sms);
				String res=smsSendUtil.sendSms(apikey, text, users.get(0).getPhone());
				String code=res.substring(res.indexOf(":")+1,res.indexOf(":")+2);
				if("0".equals(code)){
					resultVo.setErrCode(0);
					resultVo.setErrMsg("send success");
				}else{
					resultVo.setErrCode(2);
					resultVo.setErrMsg("服务器异常!");
				}
				
			} catch (Exception e) {
				resultVo.setErrCode(2);
				resultVo.setErrMsg("服务器异常!");
			}
		}else{
			resultVo.setErrCode(1);
			resultVo.setErrMsg("输入的账号或者手机号错误!");
		}
		return resultVo;
		
	}
	
	
	@RequestMapping(value = "/register/updatePassword", method = RequestMethod.POST)
	public ResultVo updatePassword(@RequestBody SysPersonUserVo sysUser){
		ResultVo resultVo = new ResultVo();
		List<SysUser> users=sysUserService.getByNameOrPho(sysUser.getUsername());
		if(users!=null&&users.size()>0){
			try {
				SysUser user=users.get(0);
				Sms sms=smsService.getSms(user.getPhone(),sysUser.getVerCode());
				if(sms!=null){
					 Date date = new Date();
				        //设置要获取到什么样的时间
				     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        //获取String类型的时间
				     String now = sdf.format(date);
				     String createDate = sdf.format(sms.getCreateDate());
				    Long num=getTimeNum(createDate,now);
				    if(num<300L){
					MD5Util md=new MD5Util();
					String password=md.encode(sysUser.getPassword());
					user.setPassword(password);
					sysUserService.update(user);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("修改成功");
				    }else{
				    	resultVo.setErrCode(1);
						resultVo.setErrMsg("验证码过期!");
				    }
				}else{
					resultVo.setErrCode(2);
					resultVo.setErrMsg("验证码错误!");
				}
				
			} catch (Exception e) {
				resultVo.setErrCode(3);
				resultVo.setErrMsg("服务器异常!");
			}
		}
		return resultVo;
		
	}
	

	private String getEncode() {
		Random random = new Random();
		String result="";
		for(int i=0;i<6;i++){
		result+=random.nextInt(10);
		}
		return result;
	}
	
	
	private long getTimeNum(String now,String create) throws ParseException {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date begin=dfs.parse(now);
		Date end = dfs.parse(create);
		long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
//		long day1=between/(24*3600);
//		long hour1=between%(24*3600)/3600;
	//	long minute=between%3600/60;
		return between;
	}

}