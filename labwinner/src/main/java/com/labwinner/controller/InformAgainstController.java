package com.labwinner.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Calendars;
import com.labwinner.domain.InformAgainst;
import com.labwinner.domain.Message;
import com.labwinner.domain.ProjectMoments;
import com.labwinner.domain.SysUser;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
import com.labwinner.service.InformAgainstService;
import com.labwinner.service.SysUserService;
import com.labwinner.vo.InformAgainstVo;


/**
 * @Description LabWinner Application
 * @author liuhq
 * @version V1.0
 * @date 2017年7月3日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class InformAgainstController {

	private static Logger logger = LoggerFactory
			.getLogger(InformAgainstController.class);

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private InformAgainstService informAgainstService;
	
	@Autowired
	private Jpush jpush;

	@RequestMapping(value = "/InformAgainst/add", method = RequestMethod.POST)
	public ResultVo upload(@RequestBody InformAgainstVo informAgainstVo) {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		InformAgainst informAgainst=new InformAgainst();
		informAgainst.setContents(informAgainstVo.getContents());
		informAgainst.setCreateDate(new Date());
		ProjectMoments projectMoments=new ProjectMoments();
		projectMoments.setProjectMomentsId(informAgainstVo.getId());
		informAgainst.setProjectMoments(projectMoments);
		informAgainst.setSysUser(sysUser);
		try {
			informAgainstService.save(informAgainst);
			List<SysUser> teamUsers=sysUserService.getTeamUsers();
			List<Integer> userIdList=new ArrayList<Integer>();
			if(teamUsers!=null&&teamUsers.size()>0){
				for(SysUser user:teamUsers){
					userIdList.add(user.getUserId());
				}
			}
			if(userIdList.size()>0){
				Message message=new Message();
				message.setMessageTitle(sysUser.getRealname()+"圈子向你举报");
				message.setMessageContent("[圈子] "+sysUser.getRealname()+"向你举报: "+informAgainstVo.getContents());
				JPushData pushData =getJPushData(message,userIdList);
				Map<String, Integer> extraMap=new HashMap<String, Integer>();
				extraMap.put("type", LabConstans.REACTION_TYPE);
				extraMap.put("userId", sysUser.getUserId());
				extraMap.put("messageId", informAgainstVo.getId());
				extraMap.put("messageType", 3);
				try {
					jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		ResultVo resultVo = new ResultVo();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("success");
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
			jPushData.setTag("举报信息");
			jPushData.setTitle(msg.getMessageTitle());
			jPushData.setAlias(usernames);
			return jPushData;
		}
		return null;
	}
	

}
