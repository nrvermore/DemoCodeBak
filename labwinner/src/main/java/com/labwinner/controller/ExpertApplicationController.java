package com.labwinner.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Expert;
import com.labwinner.domain.ExpertApplication;
import com.labwinner.domain.ExpertAssist;
import com.labwinner.domain.Feedback;
import com.labwinner.domain.Message;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.jpush.JPushData;
import com.labwinner.service.*;
import com.labwinner.util.Base64Util;
import com.labwinner.util.FileUtil;

/**
 * 设备Controller
 * 
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午6:05:23
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class ExpertApplicationController {
	private static Logger logger = LoggerFactory
			.getLogger(ExpertApplicationController.class);

	@Autowired
	private ExpertApplicationService expertApplicationService;
	@Autowired
	private SysUserService sysUserService;

	@Value("${expert.upload-path}")
	String filePath;

	@Value("${expert.url-path}")
	String urlPath;
	
	@Value("${expert.url-path}")
	String imgPath;

	@Value("${sysUserPhone.url-path}")
	String userImage;
	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	/**
	 * @Description 新增信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/ExpertApplication", method = RequestMethod.POST)
	public ResultVo save(@RequestBody Expert expert
//			@RequestParam(value = "expertNameCh", required = false) String expertNameCh,
//			@RequestParam(value = "expertNameEn", required = false) String expertNameEn,
//			@RequestParam(value = "company", required = false) String company,
//			@RequestParam(value = "record", required = false) String record,      //简介
//			@RequestParam(value = "professionalTitle", required = false) String professionalTitle,  //职称
//			@RequestParam(value = "researchArea", required = false) String researchArea,  //研究领域
//			@RequestParam(value = "achievement", required = false) String achievement //成就
			) {		
		ResultVo resultVo =  new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			ExpertApplication expertApplication=new ExpertApplication();
			expertApplication.setAchievement(expert.getAchievement());
			expertApplication.setAgency(sysUser.getSysSigningAgency().getAgencyId());
			expertApplication.setUserId(sysUser.getUserId());
			expertApplication.setExpertNameCh(expert.getExpertNameCh());
			expertApplication.setExpertNameEn(expert.getExpertNameEn());
			expertApplication.setCompany(expert.getCompany());
			expertApplication.setRecord(expert.getRecord());
			expertApplication.setProfessionalTitle(expert.getProfessionalTitle());
			expertApplication.setResearchArea(expert.getResearchArea());
			expertApplication.setType(0);
			expertApplicationService.save(expertApplication);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save  success");
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("save fail!");
		}
		return resultVo;
		
	}
	
	
	
	@RequestMapping(value = "/ExpertApplication/isExpert", method = RequestMethod.GET)
	public ResultVo getBaseUser() {		
		ResultVo resultVo =  new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			SysUser baseuser=sysUserService.getByBasUser(new Long((long)sysUser.getUserId()),sysUser.getSysSigningAgency().getAgencyId());
			if(baseuser!=null&&baseuser.getExpert()!=null){
				resultVo.setErrCode(1);
				resultVo.setErrMsg("您已经是专家，无需申请");
			}else{
				ExpertApplication expertApplication=expertApplicationService.getByUser(sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId());
				if(expertApplication!=null){
					resultVo.setErrCode(3);
					resultVo.setErrMsg("申请中,等待系统管理员审批");
				}else{
					resultVo.setErrCode(0);
					resultVo.setErrMsg("");
				}
				
				
			}
			
		} catch (Exception e) {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("系统错误,请稍后重试或通过问题反馈联系我们");
		}
		return resultVo;
		
	}
	


}
