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
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Expert;
import com.labwinner.domain.ExpertAssist;
import com.labwinner.domain.Message;
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
public class ExpertAssistController {
	private static Logger logger = LoggerFactory
			.getLogger(ExpertAssistController.class);

	@Autowired
	private ExpertAssistService expertAssistService;
	@Autowired
	private ExpertService expertService;
	@Autowired
	private SysUserService sysUserService;

	@Value("${expert.upload-path}")
	String filePath;
	@Value("${web.expertAssistUrl-path}")
	String imageUrl;
	@Value("${web.expertAssistUpload-path}")
	String uploadPath;
	
	@Value("${expert.url-path}")
	String urlPath;
	@Value("${sysUserPhone.url-path}")
	String userImage;

	@Autowired
	private Jpush jpush;
	

	
	
	
	/**
	 * @Description 新增信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/ExpertAssist", method = RequestMethod.POST)
	public ResultVo save(
			@RequestParam(value = "files", required = false) MultipartFile files,
			@RequestParam(value = "expertId", required = false) Integer expertId,
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "expertContent", required = false) String expertContent,
			@RequestParam(value = "agencyId", required = false) Integer agencyId
			) {
		
		
		ResultVo resultVo =  new ResultVo();
		try {
			List<Integer> userIdList = new ArrayList<Integer>();
			userIdList.add(userId);
			ExpertAssist expertAssist=new ExpertAssist();
			Expert expert=new Expert();
			expert.setExpertId(expertId);
			SysUser sysUser=new SysUser();
			sysUser.setUserId(userId);
			SysSigningAgency sysSigningAgency=new SysSigningAgency();
			sysSigningAgency.setAgencyId(agencyId);
			sysUser.setSysSigningAgency(sysSigningAgency);
			SysUser user=sysUserService.getByBasUser(Long.valueOf(String.valueOf(userId)),agencyId);
			String jpushUser="";
			int flag=0;
			if(agencyId-99==0){
				flag=1;
			}
			LoginController login = new LoginController();
			SysUser loginUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			jpushUser=user.getUsername()+"@"+user.getSysSigningAgency().getAgencyId();
			//赋值
			expertAssist.setExpertContent(expertContent);
			expertAssist.setAssistDate(new Date());
			//expertAssist.setExpert(expert);
			expertAssist.setSysSigningAgency(loginUser.getSysSigningAgency());
			expertAssist.setSysUser(loginUser);
			expertAssist.setSysSigningAgency1(sysUser.getSysSigningAgency());
			expertAssist.setSysUser1(sysUser);
			expertAssist.setExpertContent(expertContent);
			expertAssist.setIsRead(0);
			//保存图片表
			String fileName ="";
			if(files!=null ){
				fileName=new FileUtil().uploadFile(files, uploadPath);
				expertAssist.setUrl(fileName);
			}
			expertAssistService.save(expertAssist);
			//极光推送
			
		
			if(userIdList.size()>0){
				Message message=new Message();
				String title="userId="+userId+"&expertId="+expertId+"&agencyId="+agencyId;
				message.setMessageTitle(title);
				String content="";
				if(expertContent!=null){
					content="[专家求助]"+loginUser.getRealname()+":"+expertContent;
				}else{
					content="[专家求助]"+loginUser.getRealname()+":[图片]";
				}
				
				message.setMessageContent(content);
				JPushData pushData =getJPushData(message,jpushUser);
				//JsonObject extra=new JsonObject();
				Map<String, Integer> jsonMap=new HashMap<String, Integer>();
				jsonMap.put("userId", userId);
				jsonMap.put("expertId", expertId);
				jsonMap.put("agencyId", agencyId);
				jsonMap.put("type", 1);
				List<ExpertAssist> expertAssists=getAllContentList(userId,agencyId);
				ExpertAssist expertAss=expertAssists.get(expertAssists.size()-1);
				expertAss.getSysUser().setSysSigningAgency(null);
				expertAss.getSysUser1().setSysSigningAgency(null);
				Map<String, Object> assist=new HashMap<String, Object>();
				assist.put("expertAssistId", expertAss.getExpertAssistId());
				assist.put("expertContent", expertAss.getExpertContent());
				assist.put("assistDate", expertAss.getAssistDate());
				assist.put("isRead", expertAss.getIsRead());
				assist.put("targetId", expertAss.getTargetId());
				assist.put("targetAgencyId", expertAss.getTargetAgencyId());
				Map<String, Object> sysUserMap=new HashMap<String, Object>();
				sysUserMap.put("userId", expertAss.getSysUser().getUserId());
				sysUserMap.put("realname", expertAss.getSysUser().getRealname());
				sysUserMap.put("userImage", expertAss.getSysUser().getUserImage());
				Map<String, Object> sysUserMap1=new HashMap<String, Object>();
				sysUserMap1.put("userId", expertAss.getSysUser1().getUserId());
				sysUserMap1.put("realname", expertAss.getSysUser1().getRealname());
				sysUserMap1.put("userImage", expertAss.getSysUser1().getUserImage());
				assist.put("sysUser1", sysUserMap1);
				Map<String, Object> agencyMap=new HashMap<String, Object>();
				agencyMap.put("agencyId", expertAss.getSysSigningAgency().getAgencyId());
				assist.put("sysSigningAgency", agencyMap);
				Map<String, Object> agencyMap1=new HashMap<String, Object>();
				agencyMap1.put("agencyId", expertAss.getSysSigningAgency1().getAgencyId());
				assist.put("sysSigningAgency", agencyMap1);
				  JSONObject map = JSONObject.fromObject(assist);
				String beanStr = map.toString(); 
				//extra.addProperty("expertAssist",beanStr);
				//extra.add("expertAssist", (JsonElement)expertAssist);
				try {
					jpush.sendMessageAndNotification_JsonALL(pushData, null,jsonMap,beanStr,flag);
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
	
	 public JPushData getJPushData(Message msg,String jpushUser) {

			JPushData jPushData = new JPushData();
			List<String> usernames = new ArrayList<String>();
			if (msg != null) {
				if (jpushUser != null ||!"".equals(jpushUser)) {
					usernames.add(jpushUser);
				}
				jPushData.setContent(msg.getMessageContent());
				jPushData.setTag("expertAssist");
				jPushData.setTitle(msg.getMessageTitle());
				jPushData.setAlias(usernames);
				return jPushData;
			}
			return null;
		}
	
	/**
	 * 删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/ExpertAssist/{id}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

		    try {
		    	expertAssistService.delete(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("删除成功！！！");
				return resultVo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("删除失败！！！");
				return resultVo;
			}
		}

	/**
	 * 删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/ExpertAssist/{userId}/{agencyId}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("userId") Integer userId,@PathVariable("agencyId") Integer agencyId) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		//int deleteType=expertAssistService.getDeleteType(userId,expertId,agencyId);
		String deleteMan=sysUser.getUserId()+"_"+sysUser.getSysSigningAgency().getAgencyId();
		    try {
		    	List<ExpertAssist> expertAssists=expertAssistService.getAllContentList(userId,agencyId,sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId(),deleteMan);
		    	for(ExpertAssist expertAssist:expertAssists){
		    		if(expertAssist.getDeleteMan()==null){
		    			//String deleteMan=sysUser.getUserId()+"_"+sysUser.getSysSigningAgency().getAgencyId();
		    			expertAssistService.updateDelete(userId,agencyId,sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId(),deleteMan);
		    		}else{
		    			//String deleteMan=sysUser.getUserId()+"_"+sysUser.getSysSigningAgency().getAgencyId();
		    			if(!deleteMan.equals(expertAssist.getDeleteMan())){
		    				expertAssistService.delete(expertAssist.getExpertAssistId());
		    			}
		    		}
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

	
	@RequestMapping(value = "/ExpertAssist/DeleteMessage/{userId}/{agencyId}", method = RequestMethod.GET)
	public ResultVo deleteMessage(@PathVariable("userId") Integer userId,@PathVariable("agencyId") Integer agencyId) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		//int deleteType=expertAssistService.getDeleteType(userId,expertId,agencyId);
		String deleteMan=sysUser.getUserId()+"_"+sysUser.getSysSigningAgency().getAgencyId();
		    try {
		    	List<ExpertAssist> expertAssists=expertAssistService.getAllMessageContentList(userId,agencyId,sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId(),deleteMan);
		    	for(ExpertAssist expertAssist:expertAssists){
		    		if(expertAssist.getMsgDelete()!=null){
		    			//String deleteMan=sysUser.getUserId()+"_"+sysUser.getSysSigningAgency().getAgencyId();
		    			String[] ss=expertAssist.getMsgDelete().split(",");
		    			List<String> msgDeletes=Arrays.asList(ss);
		    			if(!msgDeletes.contains(deleteMan)){
		    				deleteMan=expertAssist.getMsgDelete()+","+deleteMan;
		    				expertAssistService.updateMessageDelete(userId,agencyId,sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId(),deleteMan);
		    			}
		    		}else{
		    			expertAssistService.updateMessageDelete(userId,agencyId,sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId(),deleteMan);
		    		}
		    		
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
	
	
	@RequestMapping(value = "/ExpertAssistContent/{userId}/{agencyId}", method = RequestMethod.GET)
	public ResultVo getByExpertAndUser(@PathVariable("userId") Integer userId,@PathVariable("agencyId") Integer agencyId) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		//Integer agencyId=sysUser.getSysSigningAgency().getAgencyId();
		Integer userId1=sysUser.getUserId();
		Integer agencyId1=sysUser.getSysSigningAgency().getAgencyId();
		String readMan=userId1+"_"+agencyId1;
		String deleteMan =userId1+"_"+agencyId1;
		String readMan1=userId+"_"+agencyId;
		try {
			List<ExpertAssist> expertAssists=expertAssistService.getAllContentList(userId,agencyId,userId1,agencyId1,deleteMan);
			if(expertAssists!=null&&expertAssists.size()>0){
				for(ExpertAssist expertAssist:expertAssists){
					if(expertAssist.getUrl()!=null){
						expertAssist.setUrl(imageUrl+expertAssist.getUrl());
					}
					if(expertAssist.getReadMan()==null){
						expertAssistService.updateIsRead(userId,agencyId,userId1,agencyId1,readMan);
					}else if(!expertAssist.getReadMan().equals("all")){
						if(!expertAssist.getReadMan().equals(readMan)){
							expertAssistService.updateIsRead(userId,agencyId,userId1,agencyId1,"all");
						}
					}
					if(expertAssist.getSysUser().getUserImage()==null){
						SysUser user=sysUserService.getByBasUser(Long.valueOf(expertAssist.getSysUser().getUserId()),expertAssist.getSysSigningAgency().getAgencyId());
						if(user.getExpert()!=null){
							Expert expert=expertService.getById(user.getExpert().getExpertId());
							if(expert.getImgUrl()!=null){
								expertAssist.getSysUser().setUserImage(urlPath+expert.getImgUrl());
							}
							String name=expert.getExpertNameCh()==null?expert.getExpertNameEn():expert.getExpertNameCh();
							expertAssist.getSysUser().setRealname(name);
						}
					}else{
						expertAssist.getSysUser().setUserImage(userImage+expertAssist.getSysUser().getUserImage());
					}
					if(expertAssist.getSysUser1().getUserImage()==null){
						SysUser user=sysUserService.getByBasUser(Long.valueOf(expertAssist.getSysUser1().getUserId()),expertAssist.getSysSigningAgency1().getAgencyId());
						if(user.getExpert()!=null){
							Expert expert=expertService.getById(user.getExpert().getExpertId());
							if(expert.getImgUrl()!=null){
								expertAssist.getSysUser1().setUserImage(urlPath+expert.getImgUrl());
							}
							String name=expert.getExpertNameCh()==null?expert.getExpertNameEn():expert.getExpertNameCh();
							expertAssist.getSysUser1().setRealname(name);
						}
					}else{
						expertAssist.getSysUser1().setUserImage(userImage+expertAssist.getSysUser1().getUserImage());
					}
				}
			}
				resultVo.setErrCode(0);
				resultVo.setResultData(expertAssists);
			
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find failed!");
		}
		    return resultVo;
	}

	
	
	public List<ExpertAssist> getAllContentList( Integer userId,Integer agencyId) {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		//Integer agencyId=sysUser.getSysSigningAgency().getAgencyId();
		Integer userId1=sysUser.getUserId();
		Integer agencyId1=sysUser.getSysSigningAgency().getAgencyId();
		//expertAssistService.updateIsRead(userId,agencyId,userId1,agencyId1,1);
		String deleteMan=sysUser.getUserId()+"_"+agencyId;
		List<ExpertAssist> expertAssists=expertAssistService.getAllContentList(userId,agencyId,userId1,agencyId1,deleteMan);
		try {
			if(expertAssists!=null&&expertAssists.size()>0){
				for(ExpertAssist expertAssist:expertAssists){
					if(expertAssist.getSysUser().getUserImage()==null){
						SysUser user=sysUserService.getByBasUser(Long.valueOf(String.valueOf(userId)),agencyId);
						if(user.getExpert()!=null){
							Expert expert=expertService.getById(user.getExpert().getExpertId());
							if(expert.getImgUrl()!=null){
								expertAssist.getSysUser().setUserImage(urlPath+user.getExpert().getImgUrl());
							}
						}
					}else{
						expertAssist.getSysUser().setUserImage(userImage+expertAssist.getSysUser().getUserImage());
					}
					if(expertAssist.getSysUser1().getUserImage()==null){
						SysUser user=sysUserService.getByBasUser(Long.valueOf(String.valueOf(userId1)),agencyId1);
						if(user.getExpert()!=null){
							Expert expert=expertService.getById(user.getExpert().getExpertId());
							if(expert.getImgUrl()!=null){
								expertAssist.getSysUser1().setUserImage(urlPath+user.getExpert().getImgUrl());
							}
						}
					}else{
						expertAssist.getSysUser1().setUserImage(userImage+expertAssist.getSysUser().getUserImage());
					}
				}
			}
				
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		    return expertAssists;
	}

	
	@RequestMapping(value = "/ExpertAssistList/{expertId}", method = RequestMethod.GET)
	public ResultVo getExpertAssistList(@PathVariable("expertId") Integer expertId) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer agencyId=sysUser.getSysSigningAgency().getAgencyId();
		try {
			List<ExpertAssist> expertAssists=new ArrayList<ExpertAssist>();
			
			
			if(sysUser.getExpert()!=null){
				Expert expert=expertService.getById(sysUser.getExpert().getExpertId());
					if(expert.getExpertId()-expertId==0){
						expertAssists=expertAssistService.getExpertContent(sysUser.getUserId(),sysUser.getExpert().getExpertId(),agencyId);
						if(expertAssists!=null&&expertAssists.size()>0){
						for(int i=0;i<expertAssists.size();i++){
							if(expertAssists.get(i).getSysUser().getUserImage()!=null||!"".equals(expertAssists.get(i).getSysUser().getUserImage())){
								expertAssists.get(i).setReplyContent(userImage+expertAssists.get(i).getSysUser().getUserImage());
							}
						}	
						}
					}else{
						expertAssists=expertAssistService.getUserContent(sysUser.getUserId(),agencyId,expertId);
						if(expertAssists!=null&&expertAssists.size()>0){
							for(int i=0;i<expertAssists.size();i++){
								SysUser expertUser=sysUserService.getByExpertId(expertAssists.get(i).getExpert().getExpertId());
								if(expertUser.getUserImage()!=null||!"".equals(expertUser)){
									expertAssists.get(i).setReplyContent(userImage+expertUser.getUserImage());
								}else{
									expertAssists.get(i).setReplyContent(urlPath+expertAssists.get(i).getExpert().getImgUrl());
								}
							}	
							}
					}	
				
			}else{
				expertAssists=expertAssistService.getUserContent(sysUser.getUserId(),agencyId,expertId);
				if(expertAssists!=null&&expertAssists.size()>0){
					for(int i=0;i<expertAssists.size();i++){
						SysUser expertUser=sysUserService.getByExpertId(expertAssists.get(i).getExpert().getExpertId());
						if(expertUser.getUserImage()!=null||!"".equals(expertUser)){
							expertAssists.get(i).setReplyContent(userImage+expertUser.getUserImage());
						}else{
							expertAssists.get(i).setReplyContent(urlPath+expertAssists.get(i).getExpert().getImgUrl());
						}
					}	
					}
			}
			if(expertAssists!=null&&expertAssists.size()>0){
				resultVo.setErrCode(0);
				resultVo.setResultData(expertAssists);
			}else{
				resultVo.setErrCode(1);
				resultVo.setErrMsg("暂无数据");
			}
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find failed!");
		}
		    return resultVo;
	}
	
	
	@RequestMapping(value = "/ExpertAssistList", method = RequestMethod.GET)
	public ResultVo getAllExpertAssistList() {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer agencyId=sysUser.getSysSigningAgency().getAgencyId();
		String readMan=sysUser.getUserId()+"_"+agencyId;
		try {
			List<ExpertAssist> expertAssists=new ArrayList<ExpertAssist>();
			String deleteMan=sysUser.getUserId()+"_"+agencyId;
			expertAssists=expertAssistService.getAllAssistList(sysUser.getUserId(),agencyId,deleteMan);
			if(expertAssists!=null&&expertAssists.size()>0){
				for(ExpertAssist expertAssist:expertAssists){
					int userId1;
					int agencyId1;
					if(expertAssist.getSysUser().getUserId()-sysUser.getUserId()==0&&expertAssist.getSysSigningAgency().getAgencyId()-agencyId==0){
						userId1=expertAssist.getSysUser1().getUserId();
						agencyId1=expertAssist.getSysSigningAgency1().getAgencyId();
					}else{
						userId1=expertAssist.getSysUser().getUserId();
						agencyId1=expertAssist.getSysSigningAgency().getAgencyId();
					}
					int unReadNumber=expertAssistService.getUnRead(sysUser.getUserId(),agencyId,userId1,agencyId1,readMan);
					expertAssist.setType(unReadNumber);
					if(expertAssist.getReadMan()!=null){
						if(expertAssist.getReadMan().equals(readMan)||"all".equals(expertAssist.getReadMan())){
							expertAssist.setIsRead(1);
						}
					}else{
						if(expertAssist.getSysUser().getUserId()-sysUser.getUserId()==0&&expertAssist.getSysSigningAgency().getAgencyId()-agencyId==0){
							expertAssist.setIsRead(1);
						}else{
							expertAssist.setIsRead(0);
						}
						expertAssist.setIsRead(0);
					}
					if(expertAssist.getSysUser().getUserImage()==null){
						SysUser user=sysUserService.getByBasUser(Long.valueOf(expertAssist.getSysUser().getUserId()),expertAssist.getSysSigningAgency().getAgencyId());
						if(user.getExpert()!=null){
							Expert expert=expertService.getById(user.getExpert().getExpertId());
							if(expert.getImgUrl()!=null){
								expertAssist.getSysUser().setUserImage(urlPath+expert.getImgUrl());
							}
							String name=expert.getExpertNameCh()==null?expert.getExpertNameEn():expert.getExpertNameCh();
							expertAssist.getSysUser().setRealname(name);
						}
					}else{
						expertAssist.getSysUser().setUserImage(userImage+expertAssist.getSysUser().getUserImage());
					}
					if(expertAssist.getSysUser1().getUserImage()==null){
						SysUser user=sysUserService.getByBasUser(Long.valueOf(expertAssist.getSysUser1().getUserId()),expertAssist.getSysSigningAgency1().getAgencyId());
						if(user.getExpert()!=null){
							Expert expert=expertService.getById(user.getExpert().getExpertId());
							if(expert.getImgUrl()!=null){
								expertAssist.getSysUser1().setUserImage(urlPath+expert.getImgUrl());
							}
							String name=expert.getExpertNameCh()==null?expert.getExpertNameEn():expert.getExpertNameCh();
							expertAssist.getSysUser1().setRealname(name);
						}
					}else{
						expertAssist.getSysUser1().setUserImage(userImage+expertAssist.getSysUser1().getUserImage());
					}
				}
			}
			if(expertAssists!=null&&expertAssists.size()>0){
				resultVo.setErrCode(0);
				resultVo.setResultData(expertAssists);
			}else{
				resultVo.setErrCode(1);
				resultVo.setErrMsg("暂无数据");
			}
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find failed!");
		}
		    return resultVo;
	}

}
