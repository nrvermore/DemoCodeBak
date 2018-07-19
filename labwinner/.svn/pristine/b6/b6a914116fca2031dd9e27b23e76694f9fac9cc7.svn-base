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
import org.springframework.core.env.SystemEnvironmentPropertySource;
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
import com.labwinner.domain.Expert;
import com.labwinner.domain.ExpertAssist;
import com.labwinner.domain.Message;
import com.labwinner.domain.ProjectAssist;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectMoments;
import com.labwinner.domain.ProjectMomentsRelation;
import com.labwinner.domain.ProjectNumber;
import com.labwinner.domain.SysRole;
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
public class ProjectAssistController {
	private static Logger logger = LoggerFactory
			.getLogger(ProjectAssistController.class);

	@Autowired
	private ProjectAssistService projectAssistService;

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private ProjectBasicInfoService projectBasicInfoService;
	
	@Autowired
	private ProjectNumberService projectNumberService;
	
	@Autowired
	private SysSigningAgencyService sysSigningAgencyService;

	@Value("${expert.upload-path}")
	String filePath;
	@Value("${web.url-path}")
	String imageUrl;
	@Value("${web.upload-path}")
	String uploadPath;
	
	@Value("${expert.url-path}")
	String urlPath;
	@Value("${sysUserPhone.url-path}")
	String userImage;

	@Autowired
	private Jpush jpush;
	

	
	
	
	/**
	 * @Description 新增信息
	 * @author llwangi
	 * @version V1.0
	 * @date 2017年12月13日 上午11:49:52
	 */
	@RequestMapping(value = "/ProjectAssist", method = RequestMethod.POST)
	public ResultVo save(
			@RequestParam(value = "files", required = false) MultipartFile files,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "noticeMan", required = false) String noticeMan,
			@RequestParam(value = "projectId", required = false) int projectId
			) {
		ResultVo resultVo =  new ResultVo();
		LoginController login = new LoginController();
		SysUser loginUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysSigningAgency agency = sysSigningAgencyService.getById(new Long((long)(loginUser.getSysSigningAgency().getAgencyId())));
		String url=null;
		try {
			
			List<Integer> userIdList = new ArrayList<Integer>();
			List<Map<String,Integer>> userMap=new ArrayList<Map<String,Integer>>();
			userMap=projectAssistService.getProNumber(loginUser.getUserId(),projectId);
			
			
			if(projectId==0){
				//团队讨论组
				List<SysUser> teamUsers=sysUserService.getAll();
				if(teamUsers!=null&&teamUsers.size()>0){
		    		for(SysUser User:teamUsers){
		    			if(loginUser.getUserId()-User.getUserId()!=0){
		    				userIdList.add(User.getUserId());
		    			}
		    		}
		    	}
				
			}else{
				//项目讨论组
				if(userMap!=null&&userMap.size()>0){
					for(Map<String,Integer> user:userMap){
						userIdList.add(user.get("user_id"));
					}
					
				}
			}
			
			ProjectBasicInfo projectBasicInfo=projectBasicInfoService.getById(projectId);
			//projectBasicInfo.setProId(projectId);
			//userIdList.add(userId);
			ProjectAssist projectAssist=new ProjectAssist();
			projectAssist.setAssistDate(new Date());
			projectAssist.setContent(content);
			projectAssist.setNoticeMan(noticeMan);
			projectAssist.setSysUser(loginUser);
			if(projectId!=0){
				projectAssist.setProjectBasicInfo(projectBasicInfo);
			}else{
				ProjectBasicInfo project = new ProjectBasicInfo();
				project.setProId(projectId);
				projectAssist.setProjectBasicInfo(project);
			}
			
			
		
			//赋值
		
			//保存图片表
			String fileName ="";
			if(files!=null ){
				fileName=new FileUtil().uploadFile(files, uploadPath);
				projectAssist.setUrl(fileName);
				url=imageUrl+fileName;
			}
			projectAssistService.save(projectAssist);
			//极光推送
			
		
			if(userIdList.size()>0){
				Message message=new Message();
			
				String jpushContent="";
				if(content!=null){
					content="[团队互动]"+loginUser.getRealname()+":"+content;
				}else{
					content="[团队互动]"+loginUser.getRealname()+":[图片]";
				}
				
				message.setMessageContent(content);
				JPushData pushData =getJPushData(message,userIdList);
				//JsonObject extra=new JsonObject();
				Map<String, Object> jsonMap=new HashMap<String, Object>();
				jsonMap.put("userId", loginUser.getUserId());
				jsonMap.put("type", LabConstans.PROJECT_TYPE);
				jsonMap.put("proId", projectId);
				
				if(projectId != 0){
					jsonMap.put("proName", projectBasicInfo.getProName());
				}else{
					jsonMap.put("proName", agency.getUserName()+"讨论组");
				}
				
				jsonMap.put("projectAssistId", projectAssist.getProjectAssistId());
				Map<String, Object> assist=new HashMap<String, Object>();
				assist.put("projectAssistId", projectAssist.getProjectAssistId());
				assist.put("content", projectAssist.getContent());
				assist.put("assistDate", projectAssist.getAssistDate());
				assist.put("deleteMan", projectAssist.getDeleteMan());
				assist.put("readMan", projectAssist.getReadMan());
				assist.put("url", url);
				Map<String, Object> sysUserMap=new HashMap<String, Object>();
				sysUserMap.put("userId", loginUser.getUserId());
				sysUserMap.put("realname", loginUser.getRealname());
				sysUserMap.put("userImage", loginUser.getUserImage());
				assist.put("sysUser", sysUserMap);
				Map<String, Object> projectMap=new HashMap<String, Object>();
				projectMap.put("proId",projectId);
				assist.put("projectBasicInfo", projectMap);
				  JSONObject map = JSONObject.fromObject(assist);
				String beanStr = map.toString(); 
				//extra.addProperty("expertAssist",beanStr);
				//extra.add("expertAssist", (JsonElement)expertAssist);
				try {
					jpush.sendMessageAndNotification_JsonPro(pushData, null,jsonMap,beanStr,0);
				} catch (Exception e) {
					resultVo.setErrCode(2);
					resultVo.setErrMsg("send fail!");
				}
			}
			
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save  success");
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("save fail!");
		}
		return resultVo;
		
	}
	
	 public JPushData getJPushData(Message msg,List<Integer> ids) {

			JPushData jPushData = new JPushData();
			List<String> usernames = new ArrayList<String>();
			if (msg != null) {
				if (ids != null && ids.size() > 0) {
					for (Integer id : ids) {
						SysUser sysUser = sysUserService.getById(Integer.valueOf(id).longValue());
						usernames.add(sysUser.getUsername()+"@"+sysUser.getSysSigningAgency().getAgencyId());
					}
				}
				jPushData.setContent(msg.getMessageContent());
				jPushData.setTag("团队互动");
				jPushData.setTitle(msg.getMessageTitle());
				jPushData.setAlias(usernames);
				return jPushData;
			}
			return null;
		}

	 /**
		 * 详情页
		 * 
		 * @Description TODO
		 * @author llwangi
		 * @version V1.0
		 * @date 2017年12月13日 上午11:22:08
		 */
	 @RequestMapping(value = "/ProjectAssistContent/{projectId}", method = RequestMethod.GET)
		public ResultVo getByExpertAndUser(@PathVariable("projectId") Integer projectId) {
			ResultVo resultVo = new ResultVo();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			try {
				List<ProjectAssist> projectAssists=projectAssistService.getAllContentList(sysUser.getUserId(),projectId);
				if(projectAssists!=null&&projectAssists.size()>0){
					for(ProjectAssist projectAssist:projectAssists){
						if(projectAssist.getUrl()!=null){
							projectAssist.setUrl(imageUrl+projectAssist.getUrl());
						}
						if(projectAssist.getReadMan()==null){
							projectAssistService.updateReadMan(sysUser.getUserId().toString(),projectAssist.getProjectAssistId());
						}else {
							String[] ss=projectAssist.getReadMan().split(",");
							List<String> readMan=Arrays.asList(ss);
							if(!readMan.contains(sysUser.getUserId().toString())){
								projectAssistService.updateReadMan(projectAssist.getReadMan()+","+sysUser.getUserId(),projectAssist.getProjectAssistId());
							}
						}
						if(projectAssist.getSysUser().getUserImage()!=null){
						
							projectAssist.getSysUser().setUserImage(userImage+projectAssist.getSysUser().getUserImage());
						}
						
					}
				}
					resultVo.setErrCode(0);
					resultVo.setResultData(projectAssists);
				
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find failed!");
			}
			    return resultVo;
		}
	 
	 /**
		 * 详情页
		 * 
		 * @Description TODO
		 * @author llwangi
		 * @version V1.0
		 * @date 2017年12月13日 上午11:22:08
		 */
	 @RequestMapping(value = "/ProjectAssistNewContent/{projectAssistId}", method = RequestMethod.GET)
		public ResultVo getByProjectAssistId(@PathVariable("projectAssistId") Integer projectAssistId) {
			ResultVo resultVo = new ResultVo();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			try {
				List<ProjectAssist> projectAssists=projectAssistService.getByProjectAssistId(projectAssistId);
				if(projectAssists!=null&&projectAssists.size()>0){
					for(ProjectAssist projectAssist:projectAssists){
						if(projectAssist.getUrl()!=null){
							projectAssist.setUrl(imageUrl+projectAssist.getUrl());
						}
						if(projectAssist.getReadMan()==null){
							projectAssistService.updateReadMan(sysUser.getUserId().toString(),projectAssist.getProjectAssistId());
						}else {
							String[] ss=projectAssist.getReadMan().split(",");
							List<String> readMan=Arrays.asList(ss);
							if(!readMan.contains(sysUser.getUserId().toString())){
								projectAssistService.updateReadMan(projectAssist.getReadMan()+","+sysUser.getUserId(),projectAssist.getProjectAssistId());
							}
						}
						if(projectAssist.getSysUser().getUserImage()!=null){
						
							projectAssist.getSysUser().setUserImage(userImage+projectAssist.getSysUser().getUserImage());
						}
						
					}
				}
					resultVo.setErrCode(0);
					resultVo.setResultData(projectAssists);
				
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find failed!");
			}
			    return resultVo;
		}
	 
	 /**
		 * 删除对象的方法
		 * 
		 * @Description TODO
		 * @author llwangi
		 * @version V1.0
		 * @date 2017年12月13日 上午11:22:08
		 */
		@RequestMapping(value = "/ProjectAssist/{projectId}", method = RequestMethod.GET)
		public ResultVo delete(@PathVariable("projectId") Integer projectId) {
			ResultVo resultVo = new ResultVo();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			    try {
					List<ProjectAssist> projectAssists=projectAssistService.getAllContentList(sysUser.getUserId(),projectId);
			    	for(ProjectAssist projectAssist:projectAssists){
			    		String deleteMan=null;
			    		if(projectAssist.getDeleteMan()==null){
			    			deleteMan=sysUser.getUserId().toString();
			    			
			    		}else{
			    			deleteMan=projectAssist.getDeleteMan()+","+sysUser.getUserId();
			    		}
			    		projectAssistService.updateDelete(projectAssist.getProjectAssistId(),deleteMan);
			    	}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("删除成功！！！");
					return resultVo;
				} catch (Exception e) {
					e.printStackTrace();
					resultVo.setErrCode(1);
					resultVo.setErrMsg("删除失败！！！");
					return resultVo;
				}
			}
		
		 /**
		 * 列表
		 * 
		 * @Description TODO
		 * @author llwangi
		 * @version V1.0
		 * @date 2017年12月13日 上午11:22:08
		 */
		@RequestMapping(value = "/ProjectAssistList/{page}/{pageSize}", method = RequestMethod.GET)
		public ResultVo getProjectAssistList(@PathVariable Integer page,@PathVariable Integer pageSize) {
			ResultVo resultVo = new ResultVo();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			 SysRole sysRole=sysUser.getSysRole();
			 String roleName =sysRole.getRoleName();
			 Integer endCount = page*pageSize;
			    try {
			    	//List<ProjectBasicInfo> projectBasicInfos = projectBasicInfoService.getUserList(sysUser.getUserId(), roleName,endCount);
					List<ProjectAssist> projectAssists=projectAssistService.getAssistList(sysUser.getUserId(),roleName,endCount);
			    	for(ProjectAssist projectAssist:projectAssists){
			    		String deleteMan=null;
			    		if(projectAssist.getDeleteMan()==null){
			    			deleteMan=sysUser.getUserId().toString();
			    			
			    		}else{
			    			deleteMan=projectAssist.getDeleteMan()+","+sysUser.getUserId();
			    		}
			    		projectAssistService.updateDelete(projectAssist.getProjectAssistId(),deleteMan);
			    	}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("删除成功！！！");
					return resultVo;
				} catch (Exception e) {
					e.printStackTrace();
					resultVo.setErrCode(1);
					resultVo.setErrMsg("删除失败！！！");
					return resultVo;
				}
			}
		
		
		 /**
		 * 列表
		 * 
		 * @Description TODO
		 * @author llwangi
		 * @version V1.0
		 * @date 2017年12月13日 上午11:22:08
		 */
		@RequestMapping(value = "/getProjectNumbers/{projectId}", method = RequestMethod.GET)
		public ResultVo getProjectNumber(@PathVariable Integer projectId) {
			ResultVo resultVo = new ResultVo();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			 
			    try {
			    	//List<ProjectBasicInfo> projectBasicInfos = projectBasicInfoService.getUserList(sysUser.getUserId(), roleName,endCount);
			    	List<ProjectNumber> list = projectNumberService.getAllNumberByProId(projectId);
			    	List<Integer> userList=new ArrayList<Integer>();
			    	if(list!=null&&list.size()>0){
			    		for(ProjectNumber projectNumber:list){
			    			if(projectNumber.getSysUser().getUserImage()!=null){
			    				projectNumber.getSysUser().setUserImage(userImage+projectNumber.getSysUser().getUserImage());
							}
			    			userList.add(projectNumber.getSysUser().getUserId());
			    		}
			    	}
			    	List<SysUser> teamUsers=sysUserService.getTeamUsers();
			    	if(teamUsers!=null&&teamUsers.size()>0){
			    		for(SysUser User:teamUsers){
			    			if(!userList.contains(User.getUserId())){
			    				ProjectNumber projectNumber=new ProjectNumber();
					    		if(User.getUserImage()!=null){
					    			User.setUserImage(userImage+User.getUserImage());
					    		}
					    		projectNumber.setSysUser(User);
					    		ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();
					    		projectBasicInfo.setProId(projectId);
					    		projectBasicInfo.setProName(list.get(0).getProjectBasicInfo().getProName());
					    		projectNumber.setProjectBasicInfo(projectBasicInfo);
					    		list.add(projectNumber);
			    			}
			    		}
			    	}
			    	
//			    	if(!userList.contains(sysUser.getUserId())){
//			    		ProjectNumber projectNumber=new ProjectNumber();
//			    		if(sysUser.getUserImage()!=null){
//			    			sysUser.setUserImage(userImage+sysUser.getUserImage());
//			    		}
//			    		projectNumber.setSysUser(sysUser);
//			    		ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();
//			    		projectBasicInfo.setProId(projectId);
//			    		projectBasicInfo.setProName(list.get(0).getProjectBasicInfo().getProName());
//			    		projectNumber.setProjectBasicInfo(projectBasicInfo);
//			    		list.add(projectNumber);
//			    	}
			    	resultVo.setResultData(list);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("查询成功！！！");
					return resultVo;
				} catch (Exception e) {
					e.printStackTrace();
					resultVo.setErrCode(1);
					resultVo.setErrMsg("查询失败！！！");
					return resultVo;
				}
			}
}
