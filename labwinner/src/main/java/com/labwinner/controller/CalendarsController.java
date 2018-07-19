package com.labwinner.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.AssistStatus;
import com.labwinner.domain.CalendarAssist;
import com.labwinner.domain.Calendars;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.MsgDetailtype;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.MsgType;
import com.labwinner.domain.SysUser;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
import com.labwinner.service.CalendarAssistService;
import com.labwinner.service.CalendarsService;
import com.labwinner.service.MessageRecipientsService;
import com.labwinner.service.MessageService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.DifArray;
import com.labwinner.util.TimeContainsUtil;
import com.labwinner.vo.CalendarsForAppVo;
import com.labwinner.vo.CalendarsVo;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description 附件Controller
 * @author wangll
 * @version V1.0
 * @date 2017年8月7日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class CalendarsController {

	@Autowired
	private CalendarsService calendarsService;
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private MessageService  messageService;
	

	@Autowired
	private MessageRecipientsService messageRecipientsService;
	
	@Autowired
	private CalendarAssistService calendarAssistService;
	
	@Autowired
	private Jpush jpush;

	/**
	 * @Description 获取所有对象
	 * @author  wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	@RequestMapping(value = "/Calendars", method = RequestMethod.GET)
	public ResultVo  getAll() {
		ResultVo res=new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			List<Calendars> listRes=calendarsService.getAllForApp(sysUser.getUserId());
			List<Calendars> list=new ArrayList<Calendars>();
			if(listRes!=null&&listRes.size()>0){
			for(int i=0;i<listRes.size();i++){
				String assist=listRes.get(i).getAssistPeople();
				if(assist!=null&&!"".equals(assist)){
					List<String> tar=new ArrayList<String>();
					String[] ass=null;
					if(assist.indexOf(",")>0){
						ass=assist.split(",");
						for(int k=0;k<ass.length;k++){
							tar.add(ass[k]);
						}
					}else{
						tar.add(assist);
					}
					if(tar.contains(String.valueOf(sysUser.getUserId()))){
						list.add(listRes.get(i));
					}
				}
				if(listRes.get(i).getSysUser().getUserId().equals(sysUser.getUserId())){
					list.add(listRes.get(i));
				}
			}
			}
			List<Calendars> resList=new ArrayList<Calendars>();
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					List<String> unFinish=new ArrayList<String>();
					List<Integer>  listUser=new ArrayList<Integer>();
					List<String> className=new ArrayList<String>();
					List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(list.get(i).getCalendarId());
					if(calendarAssists!=null&&calendarAssists.size()>0){
						for(CalendarAssist calendarAssist:calendarAssists){
							listUser.add(calendarAssist.getSysUser().getUserId());
							if(calendarAssist.getAssistStatus().getAssistStatusId()==1){
								unFinish.add(calendarAssist.getSysUser().getRealname());
							}
						}
					}else{
						listUser=null;
					}
					String class_=list.get(i).getClass_();
					if(class_!=null&&class_.length()>0){
						String[] ss=class_.split(",");
						for(int k=0;k<ss.length;k++){
							className.add(ss[k]);
						}
					}
					int flag=0;
					if(listUser!=null){
						if(listUser.contains(sysUser.getUserId())){
							flag=1;
						}
					}
					if(unFinish.size()>0){
					String content=	StringUtils.join(unFinish, ",");  
					list.get(i).setModifier(content+"未完成");
					}else{
						list.get(i).setModifier("无人完成");
					}
					list.get(i).setFlag(flag);
					list.get(i).setListUser(listUser);
					list.get(i).setClassName(className);
				}
			}
			
			res.setErrCode(0);
			res.setErrMsg("find success!");
			res.setResultData(list);
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("find fail!");
			e.printStackTrace();
		}
		return res;
	}

	
	/**
	 * @Description 获取所有对象
	 * @author  wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	@RequestMapping(value = "/CalendarsForAnd/{page}/{pageSize}", method = RequestMethod.GET)
	public ResultVo  getAllForAppAnd(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize) {
		ResultVo res=new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			List<Calendars> listRes=calendarsService.getAllForApp(sysUser.getUserId());
			List<Calendars> list=new ArrayList<Calendars>();
			if(listRes!=null&&listRes.size()>0){
			for(int i=0;i<listRes.size();i++){
				String assist=listRes.get(i).getAssistPeople();
				List<String> tar=new ArrayList<String>();
				String[] ass=null;
				if(assist.indexOf(",")>0){
					ass=assist.split(",");
					for(int k=0;k<ass.length;k++){
						tar.add(ass[k]);
					}
				}else{
					tar.add(assist);
				}
				if(tar.contains(String.valueOf(sysUser.getUserId()))){
					list.add(listRes.get(i));
				}
				if(listRes.get(i).getSysUser().getUserId().equals(sysUser.getUserId())){
					list.add(listRes.get(i));
				}
			}
			}
			res.setErrCode(0);
			res.setErrMsg("find success!");
			res.setResultData(list);
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("find fail!");
			e.printStackTrace();
		}
		return res;
	}
	
	
	
	/**
	 * @Description 获取所有对象
	 * @author  wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	@RequestMapping(value = "/CalendarsApp", method = RequestMethod.GET)
	public ResultVo  getAllForApps() {
		ResultVo res=new ResultVo();
		try {
			CalendarsForAppVo calendarsForApp=new CalendarsForAppVo();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			List<Calendars> listRes=calendarsService.getAllForApp(sysUser.getUserId());
			List<Calendars> list=new ArrayList<Calendars>();
			if(listRes!=null&&listRes.size()>0){
			for(int i=0;i<listRes.size();i++){
				String assist=listRes.get(i).getAssistPeople();
				List<String> tar=new ArrayList<String>();
				String[] ass=null;
				if(assist!=null){
					if(assist.indexOf(",")>0){
						ass=assist.split(",");
						for(int k=0;k<ass.length;k++){
							tar.add(ass[k]);
						}
					}else{
						tar.add(assist);
					}
				}
				
				if(tar.contains(String.valueOf(sysUser.getUserId()))){
					list.add(listRes.get(i));
				}
				if(listRes.get(i).getSysUser().getUserId().equals(sysUser.getUserId())){
					list.add(listRes.get(i));
				}
			}
			}
			List<Map<String, Calendars>> calMap=new ArrayList<Map<String,Calendars>>();
			List<String> dateList=new ArrayList<String>();
			if(list!=null&&list.size()>0){
			loop:for(int i=0;i<list.size();i++){
				 List<Map<String, Object>> userMap=new ArrayList<Map<String,Object>>();
					List<Integer>  listUser=new ArrayList<Integer>();
					List<String> className=new ArrayList<String>();
					String assistPeople=list.get(i).getAssistPeople();
					String class_=list.get(i).getClass_();
					if(assistPeople!=null&&!"".equals(assistPeople)){
						String[] ss=assistPeople.split(",");
						for(int k=0;k<ss.length;k++){
							listUser.add(Integer.valueOf(ss[k]));
							SysUser user=sysUserService.getById(Long.valueOf(ss[k]));
							Map<String,Object> map=new HashMap<String, Object>();
							if(user!=null){
								map.put("name", user.getRealname());
								map.put("id", user.getUserId());
							}
							userMap.add(map);
						}
					}else{
						listUser=null;
					}
					if(class_!=null&&class_.length()>0){
						String[] ss=class_.split(",");
						for(int k=0;k<ss.length;k++){
							className.add(ss[k]);
						}
					}
					int flag=0;
					if(listUser!=null){
						if(listUser.contains(sysUser.getUserId())){
							flag=1;
						}	
					}
					
					list.get(i).setFlag(flag);
					list.get(i).setListUser(listUser);
					list.get(i).setClassName(className);
					list.get(i).setUserMap(userMap);
					Date start= list.get(i).getStart();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
					String date=sdf.format(start);  
					Map<String, Calendars> map =new HashMap<String, Calendars>();
					map.put(date, list.get(i));
					calMap.add(map);
					dateList.add(date);
				}
			}
			
			 List<String> newList = new  ArrayList<String>(); 
	         for (String cd:dateList) {
	            if(!newList.contains(cd)){
	                newList.add(cd);
	            }
	        }
			List<Map<String, List<Calendars>>> resultList=new ArrayList<Map<String,List<Calendars>>>();
			List<Map<String, Object>> resultAndList=new ArrayList<Map<String,Object>>();
			for(int k=0;k<newList.size();k++){
				Map<String, List<Calendars>> resultMap =new HashMap<String, List<Calendars>>();
				Map<String, Object> resultAndMap =new HashMap<String, Object>();
				List<Calendars> listMap=new ArrayList<Calendars>();
				  for(int i=0;i<calMap.size();i++){
					  Map<String, Calendars> maps=calMap.get(i);
					  for(String key : maps.keySet()){ 
						  if(newList.get(k).equals(key)){
							  Calendars cal=  maps.get(key);
							  listMap.add(cal);
						  }
				        }
				  }
				  resultAndMap.put("calendarsTime", newList.get(k));
				  resultAndMap.put("calendarsList", listMap);
				  resultMap.put(newList.get(k), listMap);
				  resultList.add(resultMap);
				  resultAndList.add(resultAndMap);
			}
			calendarsForApp.setCalendarsMap(resultList);
			calendarsForApp.setCalendarsAndMap(resultAndList);
			res.setErrCode(0);
			res.setErrMsg("find success!");
			res.setResultData(calendarsForApp);
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("find fail!");
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * @Description 保存对象
	 * @author  wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	@RequestMapping(value = "/Calendars", method = RequestMethod.POST)
	public Integer save(@RequestBody Calendars calendars) {
		Integer res=0;
		try {
			Calendars calen=new Calendars();
			//-------接收对象转化为表中对象-------
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			String assistPeople="";
			if(calendars.getListUser()!=null&&calendars.getListUser().size()>0){
				calendars.setCalendarType(1);
				for(int i=0;i<calendars.getListUser().size();i++){
					if(i==0){
						assistPeople=assistPeople+calendars.getListUser().get(i);
					}else{
						assistPeople=assistPeople+","+calendars.getListUser().get(i);
					}
					
				}
			}else{
				calendars.setCalendarType(0);
			}
			String class_=new String();
			if(calendars.getClassName()!=null&&calendars.getClassName().size()>0){
				for(int i=0;i<calendars.getClassName().size();i++){
					if(i==0){
						class_=class_+calendars.getClassName().get(i);
					}else{
						class_=class_+","+calendars.getClassName().get(i);
					}
					
				}
			}
			//-------------------------------对象赋值-----------------------------------
			AssistStatus Status=new AssistStatus();
			Status.setAssistStatusId(1);
			calen.setAssistStatus(Status);
			calen.setCalendarType(calendars.getCalendarType());
			calen.setSysUser(sysUser);
			calen.setTitle(calendars.getTitle());
			calen.setCalendarContent(calendars.getCalendarContent());
			calen.setAssistPeople(assistPeople);
			calen.setClass_(class_);
			calen.setCreater(sysUser.getRealname());
			calen.setCreateDate(new Date());
			calen.setStart(calendars.getStart());
			if(calendars.getEnd()!=null){
				calen.setEnd(calendars.getEnd());
			}else{
				Date start=calendars.getStart();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
				SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
				Date end=time.parse(sdf.format(start)+" 23:59:59");
				calen.setEnd(end);
			}
			
			calen.setAllDay(calendars.getAllDay());
			//calen.setClassName(className);
			if(calendars.getCalendarId()!=null&&!"".equals(calendars.getCalendarId())){
				calen.setCalendarId(calendars.getCalendarId());
				calendarsService.update(calen);
				res=calendars.getCalendarId();
				calendarAssistService.deleteByCalendarId(res);
				if(calendars.getListUser()!=null&&calendars.getListUser().size()>0){
					for(int i=0;i<calendars.getListUser().size();i++){
						CalendarAssist calendarAssist=new CalendarAssist();
						calendarAssist.setCalendars(calen);
						AssistStatus assistStatus=new AssistStatus();
						assistStatus.setAssistStatusId(1);
						calendarAssist.setAssistStatus(assistStatus);
						SysUser sysuser=new SysUser();
						sysuser.setUserId(calendars.getListUser().get(i));
						calendarAssist.setSysUser(sysuser);
						calendarAssistService.save(calendarAssist);
						
					}
				}
			}else{
				res=calendarsService.save(calen);
				if(calendars.getListUser()!=null&&calendars.getListUser().size()>0){
					for(int i=0;i<calendars.getListUser().size();i++){
						CalendarAssist calendarAssist=new CalendarAssist();
						calendarAssist.setCalendars(calen);
						AssistStatus assistStatus=new AssistStatus();
						assistStatus.setAssistStatusId(1);
						calendarAssist.setAssistStatus(assistStatus);
						SysUser sysuser=new SysUser();
						sysuser.setUserId(calendars.getListUser().get(i));
						calendarAssist.setSysUser(sysuser);
						calendarAssistService.save(calendarAssist);
						
					}
				}
			}
			
			//----------------------------消息推送开始--------------------------------------------------
			if(calen.getAssistPeople()!=null&&!"".equals(calen.getAssistPeople())){
			List<Map<String, String>> listMap=new ArrayList<Map<String,String>>();
			Message message=new Message();
			message.setMessageTitle(sysUser.getRealname()+"向你分派任务");
			String content="";
			String CalendarContent=calendars.getCalendarContent()==null?"":calendars.getCalendarContent();
			content=sysUser.getRealname()+"向你分派任务;\r\n  "+"任务名称："+calendars.getTitle()+";\r\n 任务内容："+CalendarContent;
			message.setMessageContent(content);
			MsgType msgType=new MsgType();
			msgType.setMsgTypeId(2);
			message.setMsgType(msgType);
			MsgDetailtype msgDetailtype=new MsgDetailtype();
			msgDetailtype.setMsgDetailtypeId(9);
			message.setMsgDetailtype(msgDetailtype);
			message.setBussId(calen.getCalendarId());
			message.setFlag(0);
			//message.setMsgState(msgState);
			message.setSysUser(sysUser);
			message.setSenderDate(new Date());
			message.setFlag(0);
			messageService.save(message);
			List<Integer> userIdList=calendars.getListUser();
			if(userIdList!=null && userIdList.size()>0){
				for(Integer userId :userIdList){
					MessageRecipients messageRecipients=new MessageRecipients();
					messageRecipients.setFlag(0);
					messageRecipients.setMessage(message);
					SysUser sysUser1=sysUserService.getById(Long.valueOf(String.valueOf(userId)));
					messageRecipients.setSysUser(sysUser1);
					MsgState msgState=new MsgState();
					msgState.setMsgId(1);
					messageRecipients.setMsgState(msgState);
					messageRecipientsService.save(messageRecipients); 
				}
			}
			//极光推送app端消息
			//DeviceAppointmentController deviceAppointmentController=new DeviceAppointmentController();
//			content="[任务提醒] "+"任务名称："+calendars.getTitle()+";  任务内容："+CalendarContent;
			content="[任务提醒] "+sysUser.getRealname()+"给您分配了任务。任务名称："+calendars.getTitle();
			if(CalendarContent!=null&&!"".equals(CalendarContent)){
				content=content+";  任务内容："+CalendarContent+" >>请点击查看";
			}
			message.setMessageContent(content);
			JPushData pushData =getJPushData(message,userIdList);
			Map<String, Integer> extraMap=new HashMap<String, Integer>();
			extraMap.put("type", LabConstans.CALENDARS_TYPE);
			extraMap.put("userId", sysUser.getUserId());
			extraMap.put("messageId", calen.getCalendarId());
			extraMap.put("messageType", 3);
			try {
				//jpush.sendMessageAndNotification(pushData, extraMap);
				jpush.sendMessageAndNotification_Json(pushData,null,extraMap,"",0);
			} catch (Exception e) {
				// TODO: handle exception
			}
			//----------------------------消息推送结束------------------------------------------------------
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}

	
	/**
	 * @Description 保存对象
	 * @author  wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	@RequestMapping(value = "/Calendars/saveForApp", method = RequestMethod.POST)
	public ResultVo saveForApp(@RequestBody CalendarsVo calendarsVo) {
		ResultVo res=new ResultVo();
		try {
			int resu=0;
			
			Calendars calen=new Calendars();
			//-------接收对象转化为表中对象-------
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			
			//-------------------------------对象赋值-----------------------------------
			calen.setSysUser(sysUser);
			calen.setTitle(calendarsVo.getTitle());
			calen.setCalendarContent(calendarsVo.getCalendarContent());
			calen.setAssistPeople(calendarsVo.getAssistPeople());
			calen.setClass_(calendarsVo.getClass_());
			calen.setCreater(sysUser.getRealname());
			calen.setCreateDate(new Date());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date start=sdf.parse(calendarsVo.getStart());
			calen.setStart(start);
			String startTime=sdf.format(start);
			int allDay=0;
			if(calendarsVo.getEnd()!=null&&!"".equals(calendarsVo.getEnd())){
				if(calendarsVo.getEnd().indexOf("00:00")>0&&calendarsVo.getStart().indexOf("00:00")>0){
					allDay=1;
				}
				calen.setEnd(sdf.parse(calendarsVo.getEnd()));
			}
			calen.setAllDay(allDay);
			AssistStatus Status=new AssistStatus();
			Status.setAssistStatusId(1);
			calen.setAssistStatus(Status);
			//calen.setAllDay(calendarsVo.getAllDay());
			if(calendarsVo.getCalendarId()!=null&&!"".equals(calendarsVo.getCalendarId())){
				
				calen.setCalendarId(calendarsVo.getCalendarId());
				Calendars calendars=calendarsService.getById(calendarsVo.getCalendarId());
				if(calendars.getCalendarType()==0){
					calendarAssistService.deleteByCalendarId(calendarsVo.getCalendarId());
					if(calendarsVo.getAssistPeople()!=null){
						String[] ss=calendarsVo.getAssistPeople().split(",");
						for(int k=0;k<ss.length;k++){
							CalendarAssist calendarAssist=new CalendarAssist();
							calendarAssist.setCalendars(calen);
							AssistStatus assistStatus=new AssistStatus();
							assistStatus.setAssistStatusId(1);
							calendarAssist.setAssistStatus(assistStatus);
							SysUser sysuser=new SysUser();
							sysuser.setUserId(Integer.valueOf(ss[k]));
							calendarAssist.setSysUser(sysuser);
							calendarAssistService.save(calendarAssist);
						}
						calen.setCalendarType(1);
					}else{
						calen.setCalendarType(0);
					}
				}else{
					calen.setCalendarType(1);
				}
				calendarsService.update(calen);
				resu=calendarsVo.getCalendarId();

			}else{
				if(calendarsVo.getAssistPeople()!=null&&!"".equals(calendarsVo.getAssistPeople())){
					calen.setCalendarType(1);
				}else{
					calen.setCalendarType(0);
				}
				resu=calendarsService.save(calen);
				if(calendarsVo.getAssistPeople()!=null&&!"".equals(calendarsVo.getAssistPeople())){
					String[] ss=calendarsVo.getAssistPeople().split(",");
					for(int k=0;k<ss.length;k++){
						CalendarAssist calendarAssist=new CalendarAssist();
						calendarAssist.setCalendars(calen);
						AssistStatus assistStatus=new AssistStatus();
						assistStatus.setAssistStatusId(1);
						calendarAssist.setAssistStatus(assistStatus);
						SysUser sysuser=new SysUser();
						sysuser.setUserId(Integer.valueOf(ss[k]));
						calendarAssist.setSysUser(sysuser);
						calendarAssistService.save(calendarAssist);
					}
				}
			}
			
//----------------------------消息推送开始--------------------------------------------------
			if(calendarsVo.getAssistPeople()!=null&&!"".equals(calendarsVo.getAssistPeople())){
				List<Map<String, String>> listMap=new ArrayList<Map<String,String>>();
				Message message=new Message();
				message.setMessageTitle(sysUser.getRealname()+"向你分派任务");
				String content="";
				String CalendarContent=calendarsVo.getCalendarContent()==null?"":calendarsVo.getCalendarContent();
				content="任务名称："+calendarsVo.getTitle()+";\r\n 任务内容："+CalendarContent;
				//content=sysUser.getRealname()+"任务向你求助;\r\n  "+"任务名称："+calendarsVo.getTitle()+";\r\n 求助内容："+calendarsVo.getCalendarContent();
				message.setMessageContent(content);
				MsgType msgType=new MsgType();
				msgType.setMsgTypeId(2);
				message.setMsgType(msgType);
				MsgDetailtype msgDetailtype=new MsgDetailtype();
				msgDetailtype.setMsgDetailtypeId(9);
				message.setMsgDetailtype(msgDetailtype);
				message.setFlag(0);
			//	message.setMsgState(msgState);
				message.setSysUser(sysUser);
				message.setSenderDate(new Date());
				message.setFlag(0);
				message.setBussId(calen.getCalendarId());
				messageService.save(message);
				String assistPeople=calendarsVo.getAssistPeople();
				String[] ss=null;
				if(assistPeople!=null){
						ss= assistPeople.split(",");
				}
				List<String> listB=new ArrayList<String>();
				List<Integer> userIdList=new ArrayList<Integer>();
				if(ss!=null){
				    
		            List<String> listA = Arrays.asList(ss);
		            listB = new ArrayList<String>(listA);
				}
				if(listB.size()>0){
					for(int i=0;i<listB.size();i++){
						userIdList.add(Integer.valueOf(listB.get(i)));
					}
				}
				if(userIdList!=null && userIdList.size()>0){
					for(Integer userId :userIdList){
						MessageRecipients messageRecipients=new MessageRecipients();
						messageRecipients.setFlag(0);
						messageRecipients.setMessage(message);
						SysUser sysUser1=sysUserService.getById(Long.valueOf(String.valueOf(userId)));
						messageRecipients.setSysUser(sysUser1);
						MsgState msgState=new MsgState();
						msgState.setMsgId(1);
						messageRecipients.setMsgState(msgState);
						messageRecipientsService.save(messageRecipients); 
						
					}
					//极光推送app端消息
					//DeviceAppointmentController deviceAppointmentController=new DeviceAppointmentController();
					content="[任务提醒] "+sysUser.getRealname()+"给您分配了任务。任务名称："+calendarsVo.getTitle();
					if(CalendarContent!=null&&!"".equals(CalendarContent)){
						content=content+";  任务内容："+CalendarContent+" >>请点击查看";
					}
					message.setMessageContent(content);
					JPushData pushData =getJPushData(message,userIdList);
					Map<String, Integer> extraMap=new HashMap<String, Integer>();
					extraMap.put("type", LabConstans.CALENDARS_TYPE);
					extraMap.put("userId", sysUser.getUserId());
					extraMap.put("messageId", calen.getCalendarId());
					extraMap.put("messageType", 3);
					try {
						jpush.sendMessageAndNotification_Json(pushData,null,extraMap,"",0);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			
			//----------------------------消息推送结束------------------------------------------------------
			res.setErrCode(0);
			res.setErrMsg("save success!");
			res.setResultData(resu);
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("save failed!");
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * @Description 更新对象
	 * @author  wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	@RequestMapping(value = "/Calendars", method = RequestMethod.PUT)
	public void update(@RequestBody Calendars Calendars) {
		calendarsService.update(Calendars);
	}
	@RequestMapping(value = "/Calendars/sysUser", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUser> getAllUser() {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		List<SysUser> res=sysUserService.getAll();
		if(res!=null&&res.size()>0){
			for(int i=0;i<res.size();i++){
				if(sysUser.getUserId().equals(res.get(i).getUserId())){
					res.remove(i);
				}
			}
		}
		return  res;
	}
	
	@RequestMapping(value = "/Calendars/sysUserApp", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllUserForApp() {
		ResultVo res=new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			List<SysUser> resUser=sysUserService.getAll();
			List<SysUser> resList=new ArrayList<SysUser>();
			if(resUser!=null&&resUser.size()>0){
				for(int i=0;i<resUser.size();i++){
					resUser.get(i).setUserImage(null);
					if(sysUser.getUserId()-resUser.get(i).getUserId()!=0){
						resList.add(resUser.get(i));
					}
					
				}
			}
			res.setErrCode(0);
			res.setErrMsg("find success!");
			res.setResultData(resList);
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("find fail!");
			e.printStackTrace();
		}
		return  res;
	}
	
	/**
	 * @Description 删除对象
	 * @author  wangll
	 * @version V1.0
	 * @date 2017年8月7日
	 */
	@RequestMapping(value = "/Calendars/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		try {
			calendarsService.delete(id);
			List<Integer> idList =new ArrayList<Integer>();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			List<MessageRecipients> messageRecipients=messageRecipientsService.getRecipientsByUserId(sysUser.getUserId(),id, 9);
			if(messageRecipients!=null&&messageRecipients.size()>0){
				Integer messageId=messageRecipients.get(0).getMessage().getMessageId();
				messageRecipientsService.deleteByMsgId(messageId);
				messageService.delete(messageId);
				for(MessageRecipients mr:messageRecipients){
					idList.add(mr.getSysUser().getUserId());
				}
				Message message=new Message();
				message.setMessageContent("delete");
				message.setMessageTitle("delete");
				JPushData pushData =getJPushData(message,idList);
				Map<String, Integer> extraMap=new HashMap<String, Integer>();
				extraMap.put("type", 99);
				extraMap.put("userId", sysUser.getUserId());

				try {
					//jpush.sendMessageAndNotification_Pall(pushData, null);
					jpush.sendMessage_Json(pushData, null,extraMap,"",0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			
		}
	}

	@RequestMapping(value = "/Calendars/delete/{id}", method = RequestMethod.GET)
	public ResultVo deleteForApp(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		try {
			calendarsService.delete(id);
			calendarAssistService.deleteByCalendarId(id);
			List<Integer> idList =new ArrayList<Integer>();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			List<MessageRecipients> messageRecipients=messageRecipientsService.getRecipientsByUserId(sysUser.getUserId(),id, 9);
			if(messageRecipients!=null&&messageRecipients.size()>0){
				Integer messageId=messageRecipients.get(0).getMessage().getMessageId();
				messageRecipientsService.deleteByMsgId(messageId);
				messageService.delete(messageId);
				for(MessageRecipients mr:messageRecipients){
					idList.add(mr.getSysUser().getUserId());
				}
				Message message=new Message();
				message.setMessageContent("delete");
				message.setMessageTitle("delete");
				JPushData pushData =getJPushData(message,idList);
				Map<String, Integer> extraMap=new HashMap<String, Integer>();
				extraMap.put("type", 99);
				extraMap.put("userId", sysUser.getUserId());

				try {
					//jpush.sendMessageAndNotification_Pall(pushData, null);
					jpush.sendMessage_Json(pushData, null,extraMap,"",0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			res.setErrCode(0);
			res.setErrMsg("DELETE SUCCESS!");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("DELETE FAIL!");
		}
		return res;
	}
	@RequestMapping(value = "/Calendars/user", method = RequestMethod.GET)
	public ResultVo getUserList() {
		ResultVo res=new ResultVo();
		List<SysUser> users=new ArrayList<SysUser>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		try {
			users=sysUserService.getAll();
			for(int i=0;i<users.size();i++){
				if(sysUser.getUserId()-users.get(i).getUserId()==0){
					users.remove(i);
				}
				users.get(i).setUserImage(null);
			}
			res.setErrCode(0);
			res.setErrMsg("find success");
			res.setResultData(users);
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("find fail");
		}
		return res;
	}
	/**
	 * @Description 更新任务状态
	 * @author  wangll
	 * @version V1.0
	 * @date 2018年1月29日
	 */
	@RequestMapping(value = "/Calendars/updateRead/{id}", method = RequestMethod.GET)
	public ResultVo updateRead(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		try {
			LoginController login = new LoginController();
			 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			 int manyFlag=0;
			 int calendarAssistId=0;
			List<MessageRecipients> messageRecipients=messageRecipientsService.getRecipientsByBussId(sysUser.getUserId(),id,Integer.valueOf(9));
			if (messageRecipients!= null && messageRecipients.size() > 0) {
				for (MessageRecipients messageRecipient : messageRecipients) {
					MsgState msgState = new MsgState();
					msgState.setMsgId(2);
					//msgState.setMsgState("已读");
					messageRecipient.setMsgState(msgState);
					messageRecipientsService.update(messageRecipient);
				}
			}
			Map<String, Object> map=new HashMap<String, Object>();
			//if(map.get("buss_id")!=null){
				//int bussId=Integer.valueOf(map.get("buss_id").toString());
				Calendars calendars=calendarsService.getById(id);
				int flag=1;
				if(sysUser.getUserId()-calendars.getSysUser().getUserId()==0){
					flag=0;
				}
				int count=calendarAssistService.getFinishCount(id);
				if(calendars!=null){
					List<Map<String, Object>> userMap=new ArrayList<Map<String,Object>>();
					List<Integer>  listUser=new ArrayList<Integer>();
					List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(id);
					if(calendarAssists!=null&&calendarAssists.size()>0){
						for(CalendarAssist calendarAssist:calendarAssists){
							Map<String,Object> mapUser=new HashMap<String, Object>();
								mapUser.put("name", calendarAssist.getSysUser().getRealname());
								mapUser.put("id", calendarAssist.getSysUser().getUserId());
								userMap.add(mapUser);
								listUser.add(calendarAssist.getSysUser().getUserId());
								if(calendarAssist.getSysUser().getUserId()-sysUser.getUserId()==0){
									if(calendarAssist.getAssistStatus().getAssistStatusId()==1){
										manyFlag=1;
									}
									calendarAssistId=calendarAssist.getCalendarAssistId();
								}
						}
					}else{
						listUser=null;
					}
					if(flag==0){
						calendars.setFlag(flag);
						calendars.setUserMap(userMap);
						calendars.setListUser(listUser);
						map.put("calendars", calendars);
						map.put("finishCount", count);
						map.put("manyFlag", manyFlag);
						map.put("calendarAssistId", calendarAssistId);
						res.setErrCode(0);
						res.setErrMsg("更新已读成功");
						res.setResultData(map);
					}
					if(flag==1&&!listUser.contains(sysUser.getUserId())){
						res.setErrCode(3);
						res.setErrMsg("您已被移除任务，请重新加载页面");
					}else{
						calendars.setFlag(flag);
						calendars.setUserMap(userMap);
						calendars.setListUser(listUser);
						map.put("calendars", calendars);
						map.put("finishCount", count);
						map.put("manyFlag", manyFlag);
						map.put("calendarAssistId", calendarAssistId);
						res.setErrCode(0);
						res.setErrMsg("更新已读成功");
						res.setResultData(map);
					}
					
					
				}else{
					res.setErrCode(1);
				}
			//}
			
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("更新已读失败");
		}
		return res;
	}
	
	
	
	/**
	 * @Description 更新任务状态
	 * @author  wangll
	 * @version V1.0
	 * @date 2018年1月29日
	 */
	@RequestMapping(value = "/Calendars/getById/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		try {
			LoginController login = new LoginController();
			 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Map<String, Object> map=new HashMap<String, Object>();
			
				Calendars calendars=calendarsService.getById(id);
				int count=calendarAssistService.getFinishCount(id);
				if(calendars!=null){
					List<Map<String, Object>> userMap=new ArrayList<Map<String,Object>>();
					List<Integer>  listUser=new ArrayList<Integer>();
					if(calendars.getAssistPeople()!=null&&!"".equals(calendars.getAssistPeople())){
						String[] ss=calendars.getAssistPeople().split(",");
						for(int k=0;k<ss.length;k++){
							listUser.add(Integer.valueOf(ss[k]));
							SysUser user=sysUserService.getById(Long.valueOf(ss[k]));
							Map<String,Object> mapUser=new HashMap<String, Object>();
							if(user!=null){
								mapUser.put("name", user.getRealname());
								mapUser.put("id", user.getUserId());
							}
							userMap.add(mapUser);
						}
					}else{
						listUser=null;
					}
					int flag=0;
					if(listUser!=null){
						if(listUser.contains(sysUser.getUserId())){
							flag=1;
						}	
					}
					calendars.setFlag(flag);
					calendars.setUserMap(userMap);
					calendars.setListUser(listUser);
					map.put("calendars", calendars);
					map.put("finishCount", count);
					res.setErrCode(0);
					res.setErrMsg("更新已读成功");
					res.setResultData(map);
				}else{
					res.setErrCode(1);
				}
			//}
			
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("更新已读失败");
		}
		return res;
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
				jPushData.setTag("任务求助消息");
				jPushData.setTitle(msg.getMessageTitle());
				jPushData.setAlias(usernames);
				return jPushData;
			}
			return null;
		}
	 
	 //==========================================================任务===================================================================
	 /**
		 * @Description 获取所有对象
		 * @author  wangll
		 * @version V1.0
	 * @throws ParseException 
		 * @date 2017年8月7日
		 */
		@RequestMapping(value = "/CalendarsApp/{type}/{calendarTime}", method = RequestMethod.GET)
		public ResultVo  getCalendarsApp(@PathVariable("type") Integer type,@PathVariable("calendarTime") String calendarTime) throws ParseException {
			ResultVo res=new ResultVo();
			LoginController login = new LoginController();
			 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 String calendarDate=null;
			 if(!"undefined".equals(calendarTime)){
				 calendarDate=calendarTime;
			 }else{
				 calendarDate=sdf.format(new Date()); 
			 }
			 List<Calendars> calendarsType=calendarsService.getCalendarsForTime(type,sysUser.getUserId());
			 TimeContainsUtil tcu=new TimeContainsUtil();
			 Set<String> h=new HashSet<String>();
			 if(calendarsType!=null&&calendarsType.size()>0){
				 for(Calendars calendars:calendarsType){
					 List<Date> dateDay=tcu.findDates(calendars.getStart(), calendars.getEnd());
//					 int size=0;
//					 if(dateDay.size()>=2){
//						 size= dateDay.size()-1;
//					 }else{
//						 size= dateDay.size();
//					 }
					 for(int i=0;i<dateDay.size();i++){
						 h.add(sdf.format(dateDay.get(i)));
					 }
				 }
			 }
			 List<String> result = new ArrayList<>(h);
			 Collections.sort(result);
			 Map<String, Object> resMap=new HashMap<String, Object>();
			 resMap.put("loginUser", sysUser);
			 resMap.put("dateList", result);
			try {
				List<Calendars> calendars=new ArrayList<Calendars>();
				if(type==1){
					 calendars=calendarsService.getAllUnStartCalendars(calendarDate,sysUser.getUserId());
					 if(calendars!=null&&calendars.size()>0){
						 for(Calendars calendar:calendars){
								List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(calendar.getCalendarId());
								calendar.setCalendarAssists(calendarAssists);
								if(calendarAssists!=null&&calendarAssists.size()>0){
									int count=0;
									for(CalendarAssist calendarAssist:calendarAssists){
										if(calendarAssist.getAssistStatus().getAssistStatusId()==2){
											count++;
										}
									}
									calendar.setFlag(count);
								}else{
									calendar.setFlag(0);
								}
						 }
					 }
			
				}else if(type==2){
					 calendars=calendarsService.getAllStartCalendars(calendarDate,sysUser.getUserId());
					 if(calendars!=null&&calendars.size()>0){
						 for(Calendars calendar:calendars){
							 int userFlag=0;
									List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(calendar.getCalendarId());
									calendar.setCalendarAssists(calendarAssists);
									if(calendarAssists!=null&&calendarAssists.size()>0){
										int count=0;
										for(CalendarAssist calendarAssist:calendarAssists){
											if(calendarAssist.getAssistStatus().getAssistStatusId()==2){
												count++;
											}
											if(calendarAssist.getSysUser().getUserId()-sysUser.getUserId()==0){
												if(calendarAssist.getAssistStatus().getAssistStatusId()==1){
													userFlag=1;
												}
											}
										}
										calendar.setFlag(count);
									}else{
										calendar.setFlag(0);
									}
									calendar.setManyFlag(userFlag);
						 }
					 }
				}else if(type==3){
					 calendars=calendarsService.getAllMySendCalendars(calendarDate,sysUser.getUserId());
					 if(calendars!=null&&calendars.size()>0){
						 for(Calendars calendar:calendars){
								List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(calendar.getCalendarId());
								calendar.setCalendarAssists(calendarAssists);
								if(calendarAssists!=null&&calendarAssists.size()>0){
									int count=0;
									for(CalendarAssist calendarAssist:calendarAssists){
										if(calendarAssist.getAssistStatus().getAssistStatusId()==2){
											count++;
										}
									}
									calendar.setFlag(count);
								}else{
									calendar.setFlag(0);
								}
						 }
					 }
				}else if(type==4){
					 calendars=calendarsService.getAllMyDoCalendars(calendarDate,sysUser.getUserId());
					 if(calendars!=null&&calendars.size()>0){
						 for(Calendars calendar:calendars){
							 int userFlag=0;
								List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(calendar.getCalendarId());
								calendar.setCalendarAssists(calendarAssists);
								if(calendarAssists!=null&&calendarAssists.size()>0){
									int count=0;
									for(CalendarAssist calendarAssist:calendarAssists){
										if(calendarAssist.getAssistStatus().getAssistStatusId()==2){
											count++;
										}
										if(calendarAssist.getSysUser().getUserId()-sysUser.getUserId()==0){
											if(calendarAssist.getAssistStatus().getAssistStatusId()==1){
												userFlag=1;
											}
										}
									}
									calendar.setFlag(count);
								}else{
									calendar.setFlag(0);
								}
								calendar.setManyFlag(userFlag);	
						 }
					 }
				}else if(type==6){
					calendars=calendarsService.getAllMyCalendars(calendarDate,sysUser.getUserId());
				}else if(type==5){
					 calendars=calendarsService.getAllEndCalendars(calendarDate,sysUser.getUserId());
					 if(calendars!=null&&calendars.size()>0){
						 for(Calendars calendar:calendars){
								List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(calendar.getCalendarId());
								calendar.setCalendarAssists(calendarAssists);
								if(calendarAssists!=null&&calendarAssists.size()>0){
									int count=0;
									for(CalendarAssist calendarAssist:calendarAssists){
										if(calendarAssist.getAssistStatus().getAssistStatusId()==2){
											count++;
										}
									}
									calendar.setFlag(count);
								}else{
									calendar.setFlag(0);
								}
						 }
					 }
				}
				resMap.put("calendars", calendars);
				res.setErrCode(0);
				res.setResultData(resMap);
			} catch (Exception e) {
				res.setErrCode(1);
				res.setErrMsg("find fail!");
				e.printStackTrace();
			}
			return res;
		}
		
		
		@RequestMapping(value = "/CalendarsApp/getCalendarAssist/{id}", method = RequestMethod.GET)
		public ResultVo  getCalendarAssist(@PathVariable("id") Integer id) throws ParseException {
			ResultVo res=new ResultVo();
			try {
			
					CalendarAssist calendars=calendarAssistService.getById(id);
					res.setErrCode(0);
					res.setResultData(calendars);
				
			} catch (Exception e) {
				res.setErrCode(1);
				res.setErrMsg("find fail!");
				e.printStackTrace();
			}
			return res;
		}
		
		 /**
		 * @Description 关闭任务
		 * @author  wangll
		 * @version V1.0
		 * @throws ParseException 
		 * @date 2017年8月7日
		 */
		@RequestMapping(value = "/CalendarsApp/CalendarToEnd/{id}", method = RequestMethod.POST)
		public ResultVo  CalendarToEnd(@PathVariable("id") Integer id) throws ParseException {
			ResultVo res=new ResultVo();
			try {
				Calendars calendar=calendarsService.getById(id);
				AssistStatus assistStatus=new AssistStatus();
				assistStatus.setAssistStatusId(3);
				calendar.setAssistStatus(assistStatus);
				calendarsService.update(calendar);
				List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(id);
				if(calendarAssists!=null&&calendarAssists.size()>0){
					for(CalendarAssist calendarAssist:calendarAssists){
						if(calendarAssist.getAssistStatus().getAssistStatusId()==1){
							AssistStatus assistStatus1=new AssistStatus();
							assistStatus1.setAssistStatusId(3);
							calendarAssist.setAssistStatus(assistStatus1);
							calendarAssistService.update(calendarAssist);
						}
					}
				}
					//CalendarAssist calendars=calendarAssistService.getById(id);
					res.setErrCode(0);
					//res.setResultData(calendars);
				
			} catch (Exception e) {
				res.setErrCode(1);
				res.setErrMsg("find fail!");
				e.printStackTrace();
			}
			return res;
		}
		
		
		 /**
		 * @Description未完成任务提醒
		 * @author  wangll
		 * @version V1.0
		 * @throws ParseException 
		 * @date 2017年8月7日
		 */
		@RequestMapping(value = "/CalendarsApp/CalendarRemind/{id}", method = RequestMethod.POST)
		public ResultVo  CalendarRemind(@PathVariable("id") Integer id) throws ParseException {
			ResultVo res=new ResultVo();
			try {
				LoginController login = new LoginController();
				 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				Calendars calendars=calendarsService.getById(id);
				List<Integer> userList=new ArrayList<Integer>();
				List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(id);
				if(calendarAssists!=null&&calendarAssists.size()>0){
					for(CalendarAssist calendarAssist:calendarAssists){
						if(calendarAssist.getAssistStatus().getAssistStatusId()==1){
							userList.add(calendarAssist.getSysUser().getUserId());
						}
					}
				}
				
					if(userList!=null && userList.size()>0){
						//DeviceAppointmentController deviceAppointmentController=new DeviceAppointmentController();
					String	content="[任务提醒] "+calendars.getTitle()+"还没完成，请尽快处理";
					Message message=new Message();
						message.setMessageContent(content);
						JPushData pushData =getJPushData(message,userList);
						Map<String, Integer> extraMap=new HashMap<String, Integer>();
						extraMap.put("type", LabConstans.CALENDARS_TYPE);
						extraMap.put("userId", sysUser.getUserId());
						extraMap.put("messageId", id);
						extraMap.put("messageType", 3);
						try {
							//极光推送app端消息
							//jpush.sendMessageAndNotification(pushData, extraMap);
							jpush.sendMessageAndNotification_Json(pushData,null,extraMap,"",0);
						} catch (Exception e) {
							// TODO: handle exception
						}
						//----------------------------消息推送结束------------------------------------------------------
					}
					res.setErrCode(0);
				
			} catch (Exception e) {
				res.setErrCode(1);
				res.setErrMsg("find fail!");
				e.printStackTrace();
			}
			return res;
		}
		
		

		 /**
		 * @Description 标记任务完成
		 * @author  wangll
		 * @version V1.0
		 * @throws ParseException 
		 * @date 2017年8月7日
		 */
		@RequestMapping(value = "/CalendarsApp/SetCalendarFinish/{id}", method = RequestMethod.POST)
		public ResultVo  SetCalendarFinish(@PathVariable("id") Integer id) throws ParseException {
			ResultVo res=new ResultVo();
			try {
				LoginController login = new LoginController();
				 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				CalendarAssist calendarAssist=calendarAssistService.getById(id);
				if(calendarAssist!=null){
					if(calendarAssist.getAssistStatus().getAssistStatusId()==1){
						AssistStatus assistStatus=new AssistStatus();
						assistStatus.setAssistStatusId(2);
						calendarAssist.setAssistStatus(assistStatus);
						calendarAssistService.update(calendarAssist);
						List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(calendarAssist.getCalendars().getCalendarId());
						List<Integer> calList=new ArrayList<Integer>();
						for(CalendarAssist calAssist:calendarAssists){
							calList.add(calAssist.getAssistStatus().getAssistStatusId());
						}
						if(!calList.contains(1)&&!calList.contains(3)){
							Calendars calendars=calendarsService.getById(calendarAssist.getCalendars().getCalendarId());
							AssistStatus status=new AssistStatus();
							status.setAssistStatusId(2);
							calendars.setAssistStatus(status);
							calendarsService.update(calendars);
						}
						Calendars calendars=calendarsService.getById(calendarAssist.getCalendars().getCalendarId());
						List<Integer> userIdList=new ArrayList<Integer>();
						userIdList.add(calendars.getSysUser().getUserId());
						if(userIdList!=null && userIdList.size()>0){
							//极光推送app端消息
							//DeviceAppointmentController deviceAppointmentController=new DeviceAppointmentController();
							String content="[任务提醒] "+sysUser.getRealname()+"已完成任务："+calendars.getTitle();
							Message message=new Message();
							message.setMessageContent(content);
							JPushData pushData =getJPushData(message,userIdList);
							Map<String, Integer> extraMap=new HashMap<String, Integer>();
							extraMap.put("type", LabConstans.CALENDARS_TYPE);
							extraMap.put("userId", sysUser.getUserId());
							extraMap.put("messageId", calendars.getCalendarId());
							extraMap.put("messageType", 3);
							try {
								jpush.sendMessageAndNotification_Json(pushData,null,extraMap,"",0);
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						res.setErrCode(0);
						res.setErrMsg("update success!");
					}else{
						res.setErrCode(2);
						res.setErrMsg("此条数据已变更状态，请刷新页面!");	
					}
				}else{
					res.setErrCode(3);
					res.setErrMsg("您已被移除任务，请刷新页面");	
				}
			
				
			} catch (Exception e) {
				res.setErrCode(1);
				res.setErrMsg("update fail!");
				e.printStackTrace();
			}
			return res;
		}
		
		 /**
		 * @Description 标记任务完成或者设置任务未完成
		 * @author  wangll
		 * @version V1.0
		 * @throws ParseException 
		 * @date 2017年8月7日
		 */
		@RequestMapping(value = "/CalendarsApp/SetCalendarFinishOrNot/{id}/{type}/{record}", method = RequestMethod.POST)
		public ResultVo  SetCalendarUnFinish(@PathVariable("id") Integer id,@PathVariable("type") Integer type,
				@PathVariable("record") String record) throws ParseException {
			ResultVo res=new ResultVo();
			try {
				LoginController login = new LoginController();
				 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				CalendarAssist calendarAssist=calendarAssistService.getById(id);
				Calendars calendars=calendarsService.getById(calendarAssist.getCalendars().getCalendarId());
				if(calendarAssist!=null){
					AssistStatus assistStatus=new AssistStatus();
					assistStatus.setAssistStatusId(type);
					calendarAssist.setAssistStatus(assistStatus);
					if(type==1){
						if(!"undefined".equals(record)){
							calendarAssist.setAllocateRecord(record);
						}
						
					}else if(type==2){
						if(!"undefined".equals(record)){
							calendarAssist.setExecuteRecord(record);
						}
						List<Integer> userIdList=new ArrayList<Integer>();
						userIdList.add(calendars.getSysUser().getUserId());
						if(userIdList!=null && userIdList.size()>0){
							//极光推送app端消息
							//DeviceAppointmentController deviceAppointmentController=new DeviceAppointmentController();
							String content="[任务提醒] "+sysUser.getRealname()+"已完成任务："+calendars.getTitle();
							Message message=new Message();
							message.setMessageContent(content);
							JPushData pushData =getJPushData(message,userIdList);
							Map<String, Integer> extraMap=new HashMap<String, Integer>();
							extraMap.put("type", LabConstans.CALENDARS_TYPE);
							extraMap.put("userId", sysUser.getUserId());
							extraMap.put("messageId", calendars.getCalendarId());
							extraMap.put("messageType", 3);
							try {
								jpush.sendMessageAndNotification_Json(pushData,null,extraMap,"",0);
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					}
					calendarAssistService.update(calendarAssist);
					if(type==2){
						List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(calendarAssist.getCalendars().getCalendarId());
						List<Integer> calList=new ArrayList<Integer>();
						for(CalendarAssist calAssist:calendarAssists){
							calList.add(calAssist.getAssistStatus().getAssistStatusId());
						}
						if(!calList.contains(1)&&!calList.contains(3)){
							AssistStatus status=new AssistStatus();
							status.setAssistStatusId(2);
							calendars.setAssistStatus(status);
							calendarsService.update(calendars);
						}
					}
					res.setErrCode(0);
					res.setErrMsg("update success!");
				}else{
					res.setErrCode(3);
					res.setErrMsg("您已被移除任务，请重新加载页面!");	
				}
				
			} catch (Exception e) {
				res.setErrCode(1);
				res.setErrMsg("update fail!");
				e.printStackTrace();
			}
			return res;
		}
		
		
		 /**
		 * @Description 关闭任务
		 * @author  wangll
		 * @version V1.0
		 * @throws ParseException 
		 * @date 2017年8月7日
		 */
		@RequestMapping(value = "/CalendarsApp/CalendarsUserList/{id}", method = RequestMethod.GET)
		public ResultVo  CalendarsUserList(@PathVariable("id") Integer id) throws ParseException {
			ResultVo res=new ResultVo();
			Map<String, Object> ResMap =new HashMap<String, Object>();
			try {
				LoginController login = new LoginController();
				 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				 Calendars calendars=calendarsService.getById(id);
				 int flag=1;
				 if(sysUser.getUserId()-calendars.getSysUser().getUserId()==0){
					 flag=0;
				 }
				 
				List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(id);
				List<CalendarAssist> finishUser=new ArrayList<CalendarAssist>();
				List<CalendarAssist> unFinishUser=new ArrayList<CalendarAssist>();
				if(calendarAssists!=null&&calendarAssists.size()>0){
					for(CalendarAssist calendarAssist:calendarAssists){
						if(calendarAssist.getAssistStatus().getAssistStatusId()==1){
							unFinishUser.add(calendarAssist);
						}else if(calendarAssist.getAssistStatus().getAssistStatusId()==2){
							finishUser.add(calendarAssist);
						}
					}
				}
				ResMap.put("finishUser", finishUser);
				ResMap.put("unFinishUser", unFinishUser);
				ResMap.put("calendars", calendars);
				ResMap.put("flag", flag);
				ResMap.put("loginUser", sysUser);
				res.setErrCode(0);
				res.setErrMsg("update success!");
				res.setResultData(ResMap);
			} catch (Exception e) {
				res.setErrCode(1);
				res.setErrMsg("update fail!");
				e.printStackTrace();
			}
			return res;
		}
		
		 /**
		 * @Description 关闭任务
		 * @author  wangll
		 * @version V1.0
		 * @throws ParseException 
		 * @date 2017年8月7日
		 */
		@RequestMapping(value = "/CalendarsApp/CalendarsAddUserList/{id}", method = RequestMethod.GET)
		public ResultVo  CalendarsAddUserList(@PathVariable("id") Integer id) throws ParseException {
			ResultVo res=new ResultVo();
			Map<String, Object> ResMap =new HashMap<String, Object>();
			try {
				List<SysUser> users=new ArrayList<SysUser>();
				List<SysUser> userList=new ArrayList<SysUser>();
				LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				
				 Calendars calendars=calendarsService.getById(id);
				List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(id);
				List<Integer> unFinishUser=new ArrayList<Integer>();
				List<Integer> finishUser=new ArrayList<Integer>();
				if(calendarAssists!=null&&calendarAssists.size()>0){
					for(CalendarAssist calendarAssist:calendarAssists){
						if(calendarAssist.getAssistStatus().getAssistStatusId()==1){
							unFinishUser.add(calendarAssist.getSysUser().getUserId());
						}else if(calendarAssist.getAssistStatus().getAssistStatusId()==2){
							finishUser.add(calendarAssist.getSysUser().getUserId());
						}
					}
				}
				users=sysUserService.getAll();
				for(int i=0;i<users.size();i++){
					if(unFinishUser.contains(users.get(i).getUserId())){
						users.get(i).setTag(1);
					}else{
						users.get(i).setTag(0);
					}
					users.get(i).setUserImage(null);
					if(sysUser.getUserId()-users.get(i).getUserId()!=0&&!finishUser.contains(users.get(i).getUserId())){
						userList.add(users.get(i));
					}
				}
				res.setErrCode(0);
				res.setErrMsg("update success!");
				res.setResultData(userList);
			} catch (Exception e) {
				res.setErrCode(1);
				res.setErrMsg("update fail!");
				e.printStackTrace();
			}
			return res;
		}
		
		
		 /**
		 * @Description 关闭任务
		 * @author  wangll
		 * @version V1.0
		 * @throws ParseException 
		 * @date 2017年8月7日
		 */
		@RequestMapping(value = "/CalendarsApp/CalendarsUpdateUser/{userList}/{id}", method = RequestMethod.POST)
		public ResultVo  CalendarsUpdateUser(@PathVariable("userList") String userList,@PathVariable("id") int id) throws ParseException {
			ResultVo res=new ResultVo();
			try {
				List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(id);
				List<String> ListUser=new ArrayList<String>();
				if(calendarAssists!=null&&calendarAssists.size()>0){
					for(CalendarAssist calendarAssist:calendarAssists){
						if(calendarAssist.getAssistStatus().getAssistStatusId()==1){
							ListUser.add(String.valueOf(calendarAssist.getSysUser().getUserId()));
						}
					}
				}
				List<String> listDelete=new ArrayList<String>();
				if(ListUser.size()>0){
					String[] a1=(String[])ListUser.toArray(new String[ListUser.size()]);
					//String[] a1=(String[])ListUser.toArray(new String[ListUser.size()]); 
					String[] a2=userList.split(",");
					DifArray da=new DifArray();
					listDelete=da.compare(a2, a1);
				}
				if(listDelete.size()>0){
					for(int i=0;i<listDelete.size();i++){
						List<MessageRecipients> messageRecipients=messageRecipientsService.getRecipientsByUser(Integer.valueOf(listDelete.get(i)),id, 9);
						if(messageRecipients!=null&&messageRecipients.size()>0){
							List<Integer> idList=new ArrayList<Integer>();
							messageRecipientsService.delete(messageRecipients.get(0).getMsgRecipientsId());
							idList.add(messageRecipients.get(0).getSysUser().getUserId());
							Message message=new Message();
							message.setMessageContent("delete");
							message.setMessageTitle("delete");
							JPushData pushData =getJPushData(message,idList);
							Map<String, Integer> extraMap=new HashMap<String, Integer>();
							extraMap.put("type", 99);
							extraMap.put("userId", Integer.valueOf(listDelete.get(i)));
							try {
								//jpush.sendMessageAndNotification_Pall(pushData, null);
								jpush.sendMessage_Json(pushData, null,extraMap,"",0);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}	
					}
				}
				calendarAssistService.deleteByCalendarId(id);
				if(userList!=null&&!"".equals(userList)){
					String[] ss=userList.split(",");
					Calendars calendars=calendarsService.getById(id);
					if(calendars.getCalendarType()==0){
						calendarsService.updateStatus(id);
					}
					calendars.setCalendarId(id);
					for(int k=0;k<ss.length;k++){
						CalendarAssist calendarAssist=new CalendarAssist();
						calendarAssist.setCalendars(calendars);
						AssistStatus assistStatus=new AssistStatus();
						assistStatus.setAssistStatusId(1);
						calendarAssist.setAssistStatus(assistStatus);
						SysUser sysuser=new SysUser();
						sysuser.setUserId(Integer.valueOf(ss[k]));
						calendarAssist.setSysUser(sysuser);
						calendarAssistService.save(calendarAssist);
					}
				}
				res.setErrCode(0);
				res.setErrMsg("update success!");
			} catch (Exception e) {
				res.setErrCode(1);
				res.setErrMsg("update fail!");
				e.printStackTrace();
			}
			return res;
		}
		
		
		
	 
		
}
