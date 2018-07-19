package com.labwinner.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.AssistStatus;
import com.labwinner.domain.CalendarAssist;
import com.labwinner.domain.Calendars;
import com.labwinner.domain.Expert;
import com.labwinner.domain.ExpertAssist;
import com.labwinner.domain.KnowledgeAssist;
import com.labwinner.domain.KnowledgeAttachment;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.MsgDetailtype;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.MsgType;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectMoments;
import com.labwinner.domain.ProjectMomentsRelation;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.domain.TeamMomentsImage;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
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
public class KnowledgeAssistController {
	private static Logger logger = LoggerFactory
			.getLogger(KnowledgeAssistController.class);
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired 
	private  KnowledgeAssistService knowledgeAssistService;
	

	
	 @Value("${agency.name}")  
	    private String agencyName ; 
	 
	 @Value("${sql.name}")  
	    private String sqlName ; 
	 
	 @Value("${web.url_path_knowledgeAssist}")  
	 private String assistPath ;
	 
	 @Value("${web.upload_path_knowledgeAssist}")  
	 private String assistFpath ;
	 
	

	@RequestMapping(value = "/KnowledgeAssist", method = RequestMethod.POST)
	public ResultVo save(@RequestBody KnowledgeAssist knowledgeAssist) {
		ResultVo res=new ResultVo();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			knowledgeAssist.setSysUser(sysUser);
			knowledgeAssist.setCreateDate(new Date());
			knowledgeAssist.setFinishFlag(0);
			knowledgeAssist.setAgencyName(sqlName);
			knowledgeAssist.setFileName(agencyName);
			knowledgeAssistService.save(knowledgeAssist);
			res.setErrCode(0);
			return res;
	}
	
	
	@RequestMapping(value = "/KnowledgeAssist/{page}/{pageSize}", method = RequestMethod.GET)
	public ResultVo getAllKnowledgeAssist(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize) {
		ResultVo res=new ResultVo();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer size = page * pageSize;
			List<KnowledgeAssist> knowledgeAssists=knowledgeAssistService.getAllKnowledgeAssist(sysUser.getUserId(), sysUser.getSysSigningAgency().getAgencyId(),size);
			res.setErrCode(0);
			res.setResultData(knowledgeAssists);
			return res;
	}
	
	@RequestMapping(value = "/KnowledgeAssist/getById/{knowledgeAssistId}", method = RequestMethod.GET)
	public ResultVo getReplyById(@PathVariable("knowledgeAssistId") Integer knowledgeAssistId) {
		ResultVo res=new ResultVo();
			LoginController login = new LoginController();
			FileUtil fileUtil = new FileUtil();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			List<KnowledgeAssist> knowledgeAssists=knowledgeAssistService.getById(sysUser.getUserId(), sysUser.getSysSigningAgency().getAgencyId(),knowledgeAssistId);
			if(knowledgeAssists!=null){
				KnowledgeAssist knowledgeAssist=knowledgeAssists.get(0);
				if(knowledgeAssist.getReplyDate()!=null){
					knowledgeAssistService.updateReadFlag(knowledgeAssist.getKnowledgeAssistId());
				}
				List<KnowledgeAttachment> knowledgeAttachments=knowledgeAssist.getKnowledgeAttachments();
				if(knowledgeAttachments!=null){
					for(KnowledgeAttachment knowledgeAttachment:knowledgeAttachments){
						knowledgeAttachment.setFileUrl(assistPath+knowledgeAttachment.getAttachmentName());
						String size = fileUtil.getFileSize(assistFpath+knowledgeAttachment.getAttachmentName());
						knowledgeAttachment.setSize(size);
					}
				}
				res.setErrCode(0);
				res.setResultData(knowledgeAssists.get(0));
				
			}else{
				res.setErrCode(1);
				res.setErrMsg("没有查询到相关数据");
			}
			
			return res;
	}
	
	
	@RequestMapping(value = "/KnowledgeAssist/delete/{id}", method = RequestMethod.GET)
	public ResultVo deleteKnowledgeAssist(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			knowledgeAssistService.delete(id);
			res.setErrCode(0);
			res.setErrMsg("删除成功");
			return res;
	}
	
	

}
