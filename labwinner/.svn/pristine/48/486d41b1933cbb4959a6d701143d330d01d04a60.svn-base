package com.labwinner.controller;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.LabConstans;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.dao.DeviceAppointmentDao;
import com.labwinner.dao.DeviceDao;
import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.DeviceAppointmentDate;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.DeviceState;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.MsgDetailtype;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.MsgType;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.SysRole;
import com.labwinner.domain.SysUser;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
import com.labwinner.service.*;


/**
 * 试验设备预约Controller
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 上午11:20:07
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class DeviceAppointmentController {
	private static Logger logger = LoggerFactory
			.getLogger(DeviceAppointmentController.class);
  
	@Autowired
	private DeviceAppointmentService deviceAppointmentService;
	@Autowired
	private DeviceStateService deviceStateService; 
	@Autowired
	private SysUserService sysUserService; 
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Autowired
	private DeviceLocationService deviceLocationService;
	
	@Autowired
	private MessageService  messageService;
	
	@Autowired
	private MessageRecipientsService messageRecipientsService;
	
	@Autowired
	private DeviceAppointmentDao deviceAppointmentDao;

	@Value("${device.upload-path}")
	String filePath;
	
	@Value("${device.url-path}")
    String urlPath;
	// 极光推送
	@Autowired
	private Jpush jpush;
	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:21:34
	 */
	 @RequestMapping(value = "/deviceAppointment", method = RequestMethod.GET)
	    public List<DeviceAppointment> getAll() {
		 LoginController login = new LoginController();
		 //获取登录用户
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 SysRole sysRole=sysUser.getSysRole();
		 String roleName =sysRole.getRoleName();
		 logger.error("roleName",roleName);
		 Integer userId=sysUser.getUserId();
		 List<DeviceAppointment> list = deviceAppointmentService.getAll(userId, roleName);
			if (list == null) {
				String message = "没有对象";
				logger.warn(message);
				
			}
			return list;
	    }
	 
	 /**
		 * @Description 根据关键字获取对象
		 * @author liuhq
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		@RequestMapping(value = "/deviceAppointmentPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
		@ResponseBody
		public ResultVo getAllPageable(@PathVariable Integer page,
				@PathVariable Integer pageSize,
				@PathVariable String keyword) {
			LoginController login = new LoginController();
			 //获取登录用户
			 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			 SysRole sysRole=sysUser.getSysRole();
			 String roleName =sysRole.getRoleName();
			 logger.error("roleName",roleName);
			 Integer userId=sysUser.getUserId();
			
			ResultVo resultVo = new ResultVo();
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				List<DeviceAppointment> deviceAppointments = deviceAppointmentService.getAllPageable(userId, roleName, keyword);
				if(deviceAppointments!=null && deviceAppointments.size()>0){
					for (DeviceAppointment deviceAppointment : deviceAppointments) {
						DeviceLocation location = deviceAppointment.getDevice().getDeviceLocation();
						if(location!=null && !"0".equals(location.getDeviceLocaPid())){
							String pname = getDevicePname(location.getDeviceLocaId());
							location.setParentName(pname);
						}
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(deviceAppointments));
				return resultVo;
			} else {
				List<DeviceAppointment> deviceAppointments = deviceAppointmentService.getAll(userId, roleName);
				if(deviceAppointments!=null && deviceAppointments.size()>0){
					for (DeviceAppointment deviceAppointment : deviceAppointments) {
						DeviceLocation location = deviceAppointment.getDevice().getDeviceLocation();
						if(location!=null && !"0".equals(location.getDeviceLocaPid())){
							String pname = getDevicePname(location.getDeviceLocaId());
							location.setParentName(pname);
						}
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(deviceAppointments));
				return resultVo;
			}  
			
		}
	 
		/**
		 * @Description APP分页获取预约中对象
		 * @author liuhq
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		@RequestMapping(value = "/deviceAppointmentPageableApp/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
		@ResponseBody
		public ResultVo getPageableApp(@PathVariable Integer page,
				@PathVariable Integer pageSize,
				@PathVariable String keyword) {
			LoginController login = new LoginController();
			//获取登录用户
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			SysRole sysRole=sysUser.getSysRole();
			String roleName =sysRole.getRoleName();
			logger.error("roleName",roleName);
			Integer userId=sysUser.getUserId();
			
			ResultVo resultVo = new ResultVo();
			
			// 获取当前日期
	  		 Calendar calendar = Calendar.getInstance();
	  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  		String nowDate=sdf.format(calendar.getTime());
	  		//Integer nowDate=Integer.valueOf(date);
	  		//Integer nowDate=Integer.parseInt(date);
	  		
	  		List<DeviceAppointment> deviceAppointmentStates=deviceAppointmentService.findByDeviceState();
	  		if(deviceAppointmentStates!=null && deviceAppointmentStates.size()>0){
				for (DeviceAppointment deviceAppointment : deviceAppointmentStates) {
					//获取所有设备预约的结束时间
					String endDate=sdf.format(deviceAppointment.getEndDate());
					//Integer endDate=Integer.valueOf(date);
					//Integer endDate=Integer.parseInt(EndDate);
					     if(endDate!=null&&!"".equals(endDate)){
					    	 if(java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(endDate))){
					    		 DeviceState deviceState =new DeviceState();
					    		 deviceState.setStateId(2);
					    		 deviceAppointment.setDeviceState(deviceState);
					    		 deviceAppointmentService.updateState(deviceAppointment);
					    	 }
					     }
					
				    }
				}
			/*if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}*/
			Integer endCount = page*pageSize;
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				List<DeviceAppointment> deviceAppointmentList=deviceAppointmentService.getTotalByKeyword(userId, keyword);
//				List<DeviceAppointment> deviceAppointments = deviceAppointmentService.getAllPageableAppByKeyword(userId, roleName, 0, endCount, keyword);
				if(deviceAppointmentList!=null && deviceAppointmentList.size()>0){
					for (DeviceAppointment deviceAppointment : deviceAppointmentList) {
						DeviceLocation location = deviceAppointment.getDevice().getDeviceLocation();
						if(location!=null && !"0".equals(location.getDeviceLocaPid())){
							String pname = getDevicePname(location.getDeviceLocaId());
							location.setParentName(pname);
						}
					}
				}
				int total = deviceAppointmentList.size();
				double c = (((double) total) /pageSize);
				int d = (int) Math.ceil(c);
				PageEntity pageEntity = new PageEntity();
				pageEntity.setPageNum(page);
				pageEntity.setPageSize(pageSize);
				pageEntity.setTotal(deviceAppointmentList.size());
				//pageEntity.setList(deviceAppointmentList);
				int num = deviceAppointmentList.size() > page * pageSize ? page * pageSize: deviceAppointmentList.size();
				if(page<=d){
					pageEntity.setList(deviceAppointmentList.subList(0, num));
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(pageEntity);
				return resultVo;
			} else {
				List<DeviceAppointment> deviceAppointmentList=deviceAppointmentService.getTotal(userId);
	//			List<DeviceAppointment> deviceAppointments = deviceAppointmentService.getAllPageableApp(userId, roleName, 0, endCount);
				if(deviceAppointmentList!=null && deviceAppointmentList.size()>0){
					for (DeviceAppointment deviceAppointment : deviceAppointmentList) {
						DeviceLocation location = deviceAppointment.getDevice().getDeviceLocation();
						if(location!=null && !"0".equals(location.getDeviceLocaPid())){
							String pname = getDevicePname(location.getDeviceLocaId());
							location.setParentName(pname);
						}
					}
				}
				int total = deviceAppointmentList.size();
				double c = (((double) total) /pageSize);
				int d = (int) Math.ceil(c);
				PageEntity pageEntity = new PageEntity();
				pageEntity.setPageNum(page);
				pageEntity.setPageSize(pageSize);
				pageEntity.setTotal(deviceAppointmentList.size());
		//		pageEntity.setList(deviceAppointmentList);
				int num = deviceAppointmentList.size() > page * pageSize ? page * pageSize: deviceAppointmentList.size();
				if(page<=d){
					pageEntity.setList(deviceAppointmentList.subList(0, num));
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(pageEntity);
				return resultVo;
			}  
			
		}
		/**
		 * @Description APP分页获取已经过期对象
		 * @author liuhq
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		@RequestMapping(value = "/deviceAppointmentExpirePageableApp/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
		@ResponseBody
		public ResultVo getExpirePageableApp(@PathVariable Integer page,
				@PathVariable Integer pageSize,
				@PathVariable String keyword) {
			LoginController login = new LoginController();
			//获取登录用户
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			SysRole sysRole=sysUser.getSysRole();
			String roleName =sysRole.getRoleName();
			logger.error("roleName",roleName);
			Integer userId=sysUser.getUserId();
			
			ResultVo resultVo = new ResultVo();
			
			// 获取当前日期
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			String nowDate=sdf.format(calendar.getTime());
	  		//Integer nowDate=Integer.valueOf(date);
	  		//Integer nowDate=Integer.parseInt(date);
			
			List<DeviceAppointment> deviceAppointmentStates=deviceAppointmentService.findByDeviceState();
			if(deviceAppointmentStates!=null && deviceAppointmentStates.size()>0){
				for (DeviceAppointment deviceAppointment : deviceAppointmentStates) {
					//获取所有设备预约的结束时间
						String endDate=sdf.format(deviceAppointment.getEndDate());
					//Integer endDate=Integer.parseInt(sdf.format(deviceAppointment.getEndDate().toString()));
					if(endDate!=null&&!"".equals(endDate)){
						if(java.sql.Date.valueOf(nowDate).after(java.sql.Date.valueOf(endDate))){
							DeviceState deviceState =new DeviceState();
							deviceState.setStateId(2);
							deviceAppointment.setDeviceState(deviceState);
							deviceAppointmentService.updateState(deviceAppointment);
						}
					}
					
				}
			}
			/*if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}*/
			Integer endCount = page*pageSize;
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				List<DeviceAppointment> deviceAppointmentList=deviceAppointmentService.getExpireTotalByKeyword(userId, keyword);
		//		List<DeviceAppointment> deviceAppointments = deviceAppointmentService.getExpirePageableAppByKeyword(userId, roleName, 0, endCount, keyword);
				if(deviceAppointmentList!=null && deviceAppointmentList.size()>0){
					for (DeviceAppointment deviceAppointment : deviceAppointmentList) {
						DeviceLocation location = deviceAppointment.getDevice().getDeviceLocation();
						if(location!=null && !"0".equals(location.getDeviceLocaPid())){
							String pname = getDevicePname(location.getDeviceLocaId());
							location.setParentName(pname);
						}
					}
				}
				int total = deviceAppointmentList.size();
				double c = (((double) total) /pageSize);
				int d = (int) Math.ceil(c);
				PageEntity pageEntity = new PageEntity();
				pageEntity.setPageNum(page);
				pageEntity.setPageSize(pageSize);
				pageEntity.setTotal(deviceAppointmentList.size());
		//		pageEntity.setList(deviceAppointmentList);
				int num = deviceAppointmentList.size() > page * pageSize ? page * pageSize: deviceAppointmentList.size();
				if(page<=d){
					pageEntity.setList(deviceAppointmentList.subList(0, num));
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(pageEntity);
				return resultVo;
			} else {
				List<DeviceAppointment> deviceAppointmentList=deviceAppointmentService.getExpireTotal(userId);
//				List<DeviceAppointment> deviceAppointments = deviceAppointmentService.getExpirePageableApp(userId, roleName, 0, endCount);
				if(deviceAppointmentList!=null && deviceAppointmentList.size()>0){
					for (DeviceAppointment deviceAppointment : deviceAppointmentList) {
						DeviceLocation location = deviceAppointment.getDevice().getDeviceLocation();
						if(location!=null && !"0".equals(location.getDeviceLocaPid())){
							String pname = getDevicePname(location.getDeviceLocaId());
							location.setParentName(pname);
						}
					}
				}
				int total = deviceAppointmentList.size();
				double c = (((double) total) /pageSize);
				int d = (int) Math.ceil(c);
				PageEntity pageEntity = new PageEntity();
				pageEntity.setPageNum(page);
				pageEntity.setPageSize(pageSize);
				pageEntity.setTotal(deviceAppointmentList.size());
	//			pageEntity.setList(deviceAppointmentList);
				int num = deviceAppointmentList.size() > page * pageSize ? page * pageSize: deviceAppointmentList.size();
				if(page<=d){
					pageEntity.setList(deviceAppointmentList.subList(0, num));
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(pageEntity);
				return resultVo;
			}  
			
		}
		
	 
	 /**
	  * 根据日期获取对象列表
	  * @Description TODO
	  * @author suhg
	  * @version V1.0
	  * @date 2017年5月19日 上午11:21:34
	  */
	 @RequestMapping(value = "/deviceAppointment/getByMaxDate/{id}", method = RequestMethod.GET)
	 public String getByMaxDate(@PathVariable("id") Integer id){
		 String date = deviceAppointmentService.getByMaxDate(id);
		 if (date == null) {
			 String message = "日期不存在";
			 logger.warn(message);
		 }
		 return date;
	 }
	 
	 /**
	  * @Description 根据设备主键获取可预约日期
	  * @author suhg
	  * @version V1.0
	  * @date 2017年5月19日 上午11:21:34
	  */
	 @RequestMapping(value = "/deviceAppointment/getAppointmentDates/{id}", method = RequestMethod.GET)
	 public DeviceAppointmentDate getAppointmentDates(@PathVariable("id") Integer id){
		 return deviceAppointmentService.getAppointmentDates(id);
	 }
	 
	/**
	 * 根据主键获取对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:21:45
	 */
	 @RequestMapping(value = "/deviceAppointment/{id}", method = RequestMethod.GET)
	    public DeviceAppointment getById(@PathVariable("id") Integer id) {
		 DeviceAppointment deviceAppointment = deviceAppointmentService.getById(id);
			if (deviceAppointment == null) {
				String message = "对象不存在(id:" + id + ")";
				logger.warn(message);
				
			}
			return deviceAppointment;
	    }
	 
	 /**
	  * 根据字段获取对象
	  * @Description TODO
	  * @author suhg
	  * @version V1.0
	  * @date 2017年5月19日 上午11:21:45
	  */
	 @RequestMapping(value = "/deviceAppointment/find/{keyword}", method = RequestMethod.GET)
	 public ResultVo findByDeviceName(@PathVariable("keyword") String keyword) {
		 List deviceAppointmentNames = deviceAppointmentService.findByDeviceName(keyword);
			ResultVo resultVo = new ResultVo();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(deviceAppointmentNames);
			
			if (deviceAppointmentNames == null) {
				String message = "对象不存在(deviceName:" + keyword + ")";
				logger.warn(message);
				
			}
			return resultVo;
	 }

	/**
	 * 保存对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:21:57
	 */
	 @RequestMapping(value = "/deviceAppointment", method = RequestMethod.POST)
	    public ResultVo save(@RequestBody DeviceAppointment deviceAppointment) {
		
		          ResultVo resultVo = new ResultVo();
		          
		       // 获取当前日期
		  		 Calendar calendar = Calendar.getInstance();
		  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd");
		          Device device1 =deviceAppointment.getDevice();
		          Integer id=device1.getDeviceId();
				  Integer numberPeoplet = deviceDao.getById(id).getNumberPeoplet();
	
	//   if(numberPeoplet > deviceAppointmentDao.isAppointment(id, sdf.format(calendar.getTime()))){
			    	
			    LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				
			
				DeviceState ds=new DeviceState();
				ds.setStateId(1);
				deviceAppointment.setDeviceState(ds);
				deviceAppointment.setCreateDate(new Date());
			//	deviceAppointment.setCreater(sysUser);
				deviceAppointment.setModifyDate(new Date());
				deviceAppointment.setSysUser(sysUser);
				deviceAppointmentService.save(deviceAppointment);
				//--------向message表插一条数据---------
				if(deviceAppointment.getFeedFromPeople().getUserId()!=null){
				int deviceId=deviceAppointment.getDevice().getDeviceId();
				Device device=deviceService.getById(deviceId);
				Message message=new Message();
				message.setMessageTitle(sysUser.getRealname()+"预约设备:"+device.getDeviceName()+"成功！");
				String content="";
				Date startDate=deviceAppointment.getStartDate();
				Date endDate=deviceAppointment.getEndDate();
				  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				  String start = formatter.format(startDate);
				  String end = formatter.format(endDate);
				content=sysUser.getRealname()+"预约了设备： "+device.getDeviceName()+" ; \r\n预约时段： "+start+"--"+end+" 。";
				message.setMessageContent(content);
				MsgType msgType=new MsgType();
				msgType.setMsgTypeId(2);
				message.setMsgType(msgType);
				MsgDetailtype msgDetailtype=new MsgDetailtype();
				msgDetailtype.setMsgDetailtypeId(8);
				message.setMsgDetailtype(msgDetailtype);
				//message.setMsgState(msgState);
				message.setSysUser(sysUser);
				message.setSenderDate(new Date());
				message.setFlag(0);
				message.setBussId(deviceAppointment.getAppointmentId());
				messageService.save(message);
				
				MessageRecipients messageRecipients=new MessageRecipients();
				MsgState msgState=new MsgState();
				msgState.setMsgId(1);
				messageRecipients.setMsgState(msgState);
				messageRecipients.setFlag(0);
				messageRecipients.setMessage(message);
				messageRecipients.setSysUser(deviceAppointment.getFeedFromPeople());
				messageRecipientsService.save(messageRecipients); 
				
				 List<Integer> idList=new ArrayList<Integer>();
				 idList.add(deviceAppointment.getFeedFromPeople().getUserId());
				//极光推送app端消息
				content="[设备预约] "+device.getDeviceName()+" ;预约时段： "+start+"--"+end+" 。";
				message.setMessageContent(content);
				JPushData pushData = getJPushData(message,idList);
				Map<String, Integer> extraMap=new HashMap<String, Integer>();
				extraMap.put("type", LabConstans.DEVICE_TYPE);
				extraMap.put("userId", sysUser.getUserId());
				extraMap.put("messageId", deviceAppointment.getAppointmentId());
				extraMap.put("messageType", 3);
				try {
					jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
				} catch (Exception e) {
					// TODO: handle exception
				}
				//jpush.sendMessageAndNotification_Pall(pushData, null);
				}
				 resultVo.setErrCode(0);
				 resultVo.setErrMsg("预约成功");
//			   }else {
//				   resultVo.setErrCode(1);
//				   resultVo.setErrMsg("此设备该时间段预约人数已达上限，请选择其他时间段");
//			}
			
			    return resultVo;
				
			}
	 /**
	  * 修改预约的方法
	  * @Description TODO
	  * @author suhg
	  * @version V1.0
	  * @date 2017年5月19日 上午11:21:57
	  */
	 @RequestMapping(value = "/deviceAppointment", method = RequestMethod.PUT)
	 public ResultVo update(@RequestBody DeviceAppointment deviceAppointment) {
		 
		 ResultVo resultVo = new ResultVo();
		 
		 // 获取当前日期
		 Calendar calendar = Calendar.getInstance();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd");
		 Device device1 =deviceAppointment.getDevice();
		 Integer id=device1.getDeviceId();
		 Integer numberPeoplet = deviceDao.getById(id).getNumberPeoplet();
		 
		 if(numberPeoplet > deviceAppointmentDao.isAppointment(id, sdf.format(calendar.getTime()))){
			 
			 LoginController login = new LoginController();
			 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			 
			 
			 DeviceState ds=new DeviceState();
			 ds.setStateId(1);
			 deviceAppointment.setDeviceState(ds);
			 deviceAppointment.setCreateDate(new Date());
			 //	deviceAppointment.setCreater(sysUser);
			 deviceAppointment.setModifyDate(new Date());
			 deviceAppointment.setSysUser(sysUser);
			 deviceAppointmentService.update(deviceAppointment);
			 //--------向message表插一条数据---------
			 if(deviceAppointment.getFeedFromPeople().getUserId()!=null){
				 int deviceId=deviceAppointment.getDevice().getDeviceId();
				 Device device=deviceService.getById(deviceId);
				 Message message=new Message();
				 message.setMessageTitle(sysUser.getRealname()+"预约设备:"+device.getDeviceName()+"成功！");
				 String content="";
				 Date startDate=deviceAppointment.getStartDate();
				 Date endDate=deviceAppointment.getEndDate();
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				 String start = formatter.format(startDate);
				 String end = formatter.format(endDate);
				 content=sysUser.getRealname()+"预约了设备： "+device.getDeviceName()+" ; \r\n预约时段： "+start+"--"+end+" 。";
				 message.setMessageContent(content);
				 MsgType msgType=new MsgType();
				 msgType.setMsgTypeId(2);
				 message.setMsgType(msgType);
				 MsgDetailtype msgDetailtype=new MsgDetailtype();
				 msgDetailtype.setMsgDetailtypeId(8);
				 message.setMsgDetailtype(msgDetailtype);
				// message.setMsgState(msgState);
				 message.setSysUser(sysUser);
				 message.setSenderDate(new Date());
				 message.setFlag(0);
				 messageService.save(message);
				 MessageRecipients messageRecipients=new MessageRecipients();
				 MsgState msgState=new MsgState();
				 msgState.setMsgId(1);
				 messageRecipients.setMsgState(msgState);
				 messageRecipients.setFlag(0);
				 messageRecipients.setMessage(message);
				 messageRecipients.setSysUser(deviceAppointment.getFeedFromPeople());
				 messageRecipientsService.save(messageRecipients); 
				 
				 List<Integer> idList=new ArrayList<Integer>();
				 idList.add(deviceAppointment.getFeedFromPeople().getUserId());
				 //极光推送app端消息
				 content="[设备预约] "+device.getDeviceName()+" ;预约时段： "+start+"--"+end+" 。";
				 message.setMessageContent(content);
				 JPushData pushData = getJPushData(message,idList);
				 Map<String, Integer> extraMap=new HashMap<String, Integer>();
				 extraMap.put("type", LabConstans.DEVICE_TYPE);
				 extraMap.put("userId", sysUser.getUserId());
				 extraMap.put("messageId", deviceAppointment.getAppointmentId());
				 extraMap.put("messageType", 3);
				 try {
					 jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
				 } catch (Exception e) {
					 // TODO: handle exception
				 }
				 //jpush.sendMessageAndNotification_Pall(pushData, null);
			 }
			 resultVo.setErrCode(0);
			 resultVo.setErrMsg("预约成功");
		 }else {
			 resultVo.setErrCode(1);
			 resultVo.setErrMsg("此设备该时间段预约人数已达上限，请选择其他时间段");
		 }
		 
		 return resultVo;
		 
	 }
		         
	
	 /**
	 * 更新对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	 @RequestMapping(value = "/deviceAppointment/{id}", method = RequestMethod.PUT)
	    public ResultVo update(@PathVariable("id")Integer id) {
		// 获取要取消预约的设备对象
		 DeviceAppointment olddeviceAppointment = deviceAppointmentService.getById(id);
		 DeviceState ds = deviceStateService.getById(2);
//			DeviceState ds = new DeviceState();// 创建设备状态
//			if(olddeviceAppointment!=null){
//			ds.setStateId(2);
//			ds.setDeviceState(deviceState);
			olddeviceAppointment.setDeviceState(ds);
//			}
			deviceAppointmentService.update(olddeviceAppointment);
			ResultVo resultVo = new ResultVo();
			resultVo.setErrCode(0);
			resultVo.setResultData(olddeviceAppointment);
			resultVo.setErrMsg("取消预约成功！");
		    return resultVo;
	    }
	 

	/**
	 * 删除对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	 @RequestMapping(value = "/deviceAppointment/{id}", method = RequestMethod.DELETE)
	    public ResultVo delete(@PathVariable("id") Integer id) {
		 ResultVo resultVo = new ResultVo();
		 try {
			 deviceAppointmentService.delete(id);
			 List<Integer> idList =new ArrayList<Integer>();
				LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				List<MessageRecipients> messageRecipients=messageRecipientsService.getRecipientsByUserId(sysUser.getUserId(),id, 8);
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
			 resultVo.setErrCode(0);
			 resultVo.setErrMsg("取消预约成功！");
				return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			 resultVo.setErrMsg("取消预约失败！");
		}
		return null;
	    }

	 
	 public JPushData getJPushData(Message msg,List<Integer> ids) {

			JPushData jPushData = new JPushData();
			List<String> usernames = new ArrayList<String>();
			if (msg != null) {
				if (ids != null && ids.size() > 0) {
					for (Integer id : ids) {
						SysUser sysUser = sysUserService.getById(Integer.valueOf(id).longValue());
						usernames.add(sysUser.getUsername()+"@"+sysUser.getSysSigningAgency().getAgencyId());
						//usernames.add(sysUser.getUsername());
					}
				}
				jPushData.setContent(msg.getMessageContent());
				jPushData.setTag("消息");
				jPushData.setTitle(msg.getMessageTitle());
				jPushData.setAlias(usernames);
				return jPushData;
			}
			return null;
		}
	 
	 public String getDevicePname( Integer id) {
		    try {
		      String ss="";
		    loop:for(int i=0;i<10;i++){
		      DeviceLocation deviceLocation =  deviceLocationService.getById(id);
		      if(deviceLocation.getDeviceLocaPid()!=null&&!"0".equals(deviceLocation.getDeviceLocaPid())){
		        if(i==0){
		          ss="";
		        }else if(i==1){
		          ss=deviceLocation.getLabel();
		        }else{
		          ss=deviceLocation.getLabel()+">"+ss;
		        }
		        id= Integer.valueOf(deviceLocation.getDeviceLocaPid());
		      }else{
		        if(!"".equals(ss)){
		          ss=deviceLocation.getLabel()+">"+ss;
		        }else{
		          ss=deviceLocation.getLabel();  
		        }
		        break loop;
		      }
		      }
		      return ss;
		    } catch (Exception e) {
		    	return null;
		      // TODO: handle exception
		    }
		  }
	 
}
