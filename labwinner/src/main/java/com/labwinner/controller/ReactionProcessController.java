package com.labwinner.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Analytics;
import com.labwinner.domain.Calendars;
import com.labwinner.domain.CommentEntity;
import com.labwinner.domain.DesignTechnologyDosage;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.ExecuteChemical;
import com.labwinner.domain.ExecuteChemicalGroup;
import com.labwinner.domain.ExecuteSolution;
import com.labwinner.domain.IndustryReactionTemplate;
import com.labwinner.domain.IndustryReactionTemplateParameter;
import com.labwinner.domain.Inventories;
import com.labwinner.domain.InventoryGroups;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.InventoryModify;
import com.labwinner.domain.Measurement;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.ModifyType;
import com.labwinner.domain.MsgDetailtype;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.MsgType;
import com.labwinner.domain.PersonalReactionTemplateParameter;
import com.labwinner.domain.PersonalReactionTemplateProcess;
import com.labwinner.domain.PersonalTemplate;
import com.labwinner.domain.ProfessionProcess;
import com.labwinner.domain.Prototype;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionDevice;
import com.labwinner.domain.ReactionExecuteParameter;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.domain.ReactionStatus;
import com.labwinner.domain.SolutionEntity;
import com.labwinner.domain.SysUser;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
import com.labwinner.service.AnalyticService;
import com.labwinner.service.AnalyticalAttachmentService;
import com.labwinner.service.AnalyticsDeviceService;
import com.labwinner.service.CalendarsService;
import com.labwinner.service.CommentService;
import com.labwinner.service.DeviceLocationService;
import com.labwinner.service.ExecuteChemicalGroupService;
import com.labwinner.service.ExecuteChemicalService;
import com.labwinner.service.ExecuteParemeterService;
import com.labwinner.service.ExecuteSolutionService;
import com.labwinner.service.IndustryReactionTemplateService;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.InventoryGroupsService;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.service.InventoryModifyService;
import com.labwinner.service.MeasurementService;
import com.labwinner.service.MessageRecipientsService;
import com.labwinner.service.MessageService;
import com.labwinner.service.PersonalTemplateService;
import com.labwinner.service.PrototypeService;
import com.labwinner.service.ReactionDeviceService;
import com.labwinner.service.ReactionProcessService;
import com.labwinner.service.ReactionService;
import com.labwinner.service.ReactionStatusService;
import com.labwinner.service.SolutionService;
import com.labwinner.service.SysUserService;
import com.labwinner.service.TeamAssistService;
import com.labwinner.util.Base64Util;
import com.labwinner.vo.ReactionProcessAddVo;
import com.labwinner.vo.ReactionProcessCopyVo;
import com.labwinner.vo.ReactionProcessVo;

@RestController
public class ReactionProcessController {

	private static Logger logger = LoggerFactory
			.getLogger(ReactionProcessController.class);

	@Autowired
	private ReactionProcessService reactionProcessService;

	@Autowired
	private ExecuteChemicalService executeChemicalService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private InventoriesService inventoriesService;
	@Autowired
	private InventoryGroupsService inventoryGroupsService;
	@Autowired
	private InventoryModifyService inventoryModifyService;

	@Autowired
	private InventoryLocationService inventoryLocService;
	
	@Autowired
	private DeviceLocationService deviceLocationService;
	
	@Autowired
	private CalendarsService calendarsService;
	
	@Autowired
	private MessageService  messageService;
	
	@Autowired
	ReactionDeviceService reactionDeviceService;

	@Autowired
	private MessageRecipientsService messageRecipientsService;
	

	@Autowired
	private ReactionService reactionService;
	
	@Autowired
	private ExecuteParemeterService executeParemeterService;
	
	@Autowired
	private ExecuteSolutionService executeSolutionService;
	
	@Autowired
	private ExecuteChemicalGroupService executeChemicalGroupService;
	
	@Autowired
	PrototypeService prototypeService;
	
	@Autowired
	TeamAssistService teamAssistService;
	
	@Autowired
	private AnalyticService analyticService;
	
	@Autowired
	private AnalyticsDeviceService analyticsDeviceService;

	@Autowired
	private AnalyticalAttachmentService analyticalAttachmentService;
	
	@Autowired
	private IndustryReactionTemplateService industryReactionTemplateService;
	
	@Autowired
	private PersonalTemplateService personalTemplateService; 
	
	@Autowired
	ReactionStatusService reactionStatusService;
	
	@Autowired
	private MeasurementService measurementService;
	
	@Autowired
	private SolutionService solutionService;
	
	@Autowired
	private CommentService commentService;
	

	@Value("${web.upload-path}")
	String filePath;
	@Value("${web.url-path}")
	String urlPath;
	@Value("${web.qr-path}")
	String qrPath;
	@Value("${web.qrUrl-path}")
	private String qrUlPath;
	@Autowired
	private Jpush jpush;

	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionProcess/{id}", method = RequestMethod.GET)
	public ReactionProcess getById(@PathVariable("id") Integer id) {
		ReactionProcess reactionProcess = reactionProcessService.getById(id);
		if (reactionProcess == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return reactionProcess;
	}
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/getprocessByReactionId/{id}", method = RequestMethod.GET)
	public ResultVo getByReactionId(@PathVariable("id") Integer id) {
		
		ResultVo resultVo = new ResultVo();
		
		List<ReactionProcess> reactionProcessList = reactionProcessService.getProcessByReactionId(id);
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(reactionProcessList);
		
		return resultVo;
	}
	
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionProcess/getByProcessId/{id}", method = RequestMethod.GET)
	public ResultVo getByProcessId(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		ReactionProcess reactionProcess = reactionProcessService.getByProcessId(id);
		if (reactionProcess == null) {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("reactionProcess is null");
			return resultVo;
		}
		
		List<ExecuteChemicalGroup> executeChemicalGroups = reactionProcess.getExecuteChemicalGroups();
		for (ExecuteChemicalGroup executeChemicalGroup : executeChemicalGroups) {
			if(executeChemicalGroup.getInventoryGroups()!=null){
				InventoryGroups inventoryGroups = inventoryGroupsService.getById(executeChemicalGroup.getInventoryGroups().getGroupId());
				if(inventoryGroups!=null){
					Measurement measure = inventoryGroups.getMeasurement();
					if(measure!=null){
						Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
						inventoryGroups.setMeasurement(measureMent);
					}
					executeChemicalGroup.setInventoryGroups(inventoryGroups);
				}
				
			}
		}
		
		List<ReactionDevice> testDevices = reactionProcess.getReactionDevices();
		
		if(testDevices!=null && testDevices.size()>0){
			for (ReactionDevice testDevice : testDevices) {
				if(testDevice.getDevice()!=null){
					DeviceLocation location = testDevice.getDevice().getDeviceLocation();
					if(location!=null && !"0".equals(location.getDeviceLocaPid())){
						String pname = getDevicePname(location.getDeviceLocaId());
						location.setParentName(pname);
					}
				}
				
			}
		}
		
		List<Prototype> protoTypes = reactionProcess.getPrototypes();
		if(protoTypes!=null && protoTypes.size()>0){
			for (Prototype prototype : protoTypes) {
				prototype.setQrName(qrUlPath+prototype.getQrName());
				InventoryLocation location = prototype.getInventoryLocation();
				if(location!=null && location.getPid()!=0){
					String pname = getInventoryPname(location.getCid());
					location.setParentName(pname);
				}
			}
		}
		
		List<CommentEntity> commentEntities = commentService.getByModuleId(id, 1);
		
		reactionProcess.setCommentEntities(commentEntities);
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find sucess");
		resultVo.setResultData(reactionProcess );
		return resultVo;
	}

	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionProcess", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();

		List<ReactionProcess> reactionProcessList = reactionProcessService
				.getAll();
		if (reactionProcessList == null) {
			String message = "对象不存在(id:" + reactionProcessList + ")";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find sucess");
		resultVo.setResultData(reactionProcessList);
		return resultVo;
	}

	/**
	 * @Description 保存/更新对象方法
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */

	@RequestMapping(value = "/reactionProcess", method = RequestMethod.POST)
	public ResultVo saveOrUpdate(@RequestBody ReactionProcess reactionProcess) {

		ResultVo resultVo = new ResultVo();
		reactionProcessService.save(reactionProcess);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save success");
		return resultVo;

	}

	@RequestMapping(value = "/reactionProcess", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody ReactionProcess reactionProcess) {
		ResultVo resultVo = new ResultVo();
		Integer process = reactionProcess.getProcess();
		Date date = reactionProcess.getEndTime();
		ReactionStatus reactionStatus = reactionProcess.getReactionStatus();
		Integer reactionId = reactionProcess.getReaction().getReactionId();
		List<Object> chemicalList = new ArrayList<Object>();
		
		if (reactionProcess.getReaction() != null && reactionStatus != null
				&& (reactionStatus.getReactionStatusId() == 7||reactionStatus.getReactionStatusId() ==8)) {
			
			//得到库存总量小于设计用量的集合
			List<Inventories> inventoryList =  getInventoryList(reactionProcess.getReactionProcessId());
			
			//得到溶液总量小于实验用量的集合
			List<SolutionEntity> solutionList =getSolutionList(reactionProcess.getReactionProcessId()); 
			if(inventoryList!=null && inventoryList.size()>0){
				chemicalList.add(inventoryList);
			}
			if(solutionList!=null && solutionList.size()>0){
				chemicalList.add(solutionList);
			}
//			if(inventoryList.size()>0){
//				resultVo.setErrCode(1);
//				resultVo.setErrMsg("库存不足，更新失败");
//				resultVo.setResultData(inventoryList);
//				return resultVo;
//			}else{
				updateInventory(reactionProcess.getReactionProcessId());
//			}
			
//			if(solutionList.size()>0){
//				resultVo.setErrCode(1);
//				resultVo.setErrMsg("库存不足，更新失败");
//				resultVo.setResultData(solutionList);
//				return resultVo;
//			}else{
				updateSolution(reactionProcess.getReactionProcessId());
//			}
		}
		
		reactionProcessService.update(reactionProcess);

//		if (reactionProcess.getReaction() != null && reactionStatus != null
//				&& reactionStatus.getReactionStatusId() == 7) {
//
//			reactionProcessService.updateProcessStatus(date, process + 1,
//					reactionId, 9);
//		}
		
		Reaction reaction=reactionService.getBasicReaction(reactionId);
		
		//对比步骤完成时间是否超过实验结束时间
		if(date!=null && date.getTime()-reaction.getEndTime().getTime()>0){
			reaction.setEndTime(date);
			reactionService.update(reaction);
		}
		
		getReactionStatus(reactionId);
		
		if (reactionProcess.getReaction() != null && reactionStatus != null
				&& reactionStatus.getReactionStatusId()-6==0) {
			try {
				
				ReactionProcess reacProcess=reactionProcessService.getById(reactionProcess.getReactionProcessId());
						
				LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
//				Calendars calendars=new Calendars();
//				calendars.setTitle("实验："+reaction.getReactionName()+"  的实验步骤:"+reacProcess.getProcessName()+" 已经变更为开始了");
//				calendars.setAllDay(1);
//				calendars.setStart(reactionProcess.getStartTime());
//				calendars.setEnd(reactionProcess.getEndTime());
//				calendars.setClass_("label-normal");
//				calendars.setSysUser(sysUser);
//				calendars.setCreater(sysUser.getRealname());
//				calendars.setCreateDate(new Date());
//				int res=calendarsService.save(calendars);
				
				//----------------------------消息推送开始--------------------------------------------------
				
					List<Map<String, String>> listMap=new ArrayList<Map<String,String>>();
					Message message=new Message();
					message.setMessageTitle("实验："+reaction.getReactionName()+"  的实验步骤:"+reacProcess.getProcessName()+" 已经变更为开始了");
					String content="";
					message.setMessageContent(content);
					MsgType msgType=new MsgType();
					msgType.setMsgTypeId(1);
					message.setMsgType(msgType);
					MsgDetailtype msgDetailtype=new MsgDetailtype();
					msgDetailtype.setMsgDetailtypeId(1);
					message.setMsgDetailtype(msgDetailtype);
					message.setFlag(0);
					//message.setMsgState(msgState);
					message.setSysUser(sysUser);
					message.setSenderDate(new Date());
					message.setFlag(0);
					messageService.save(message);
					List<Integer> userIdList=new ArrayList<Integer>();
					userIdList.add(sysUser.getUserId());
					if(userIdList!=null && userIdList.size()>0){
						for(Integer userId :userIdList){
							MessageRecipients messageRecipients=new MessageRecipients();
							MsgState msgState=new MsgState();
							msgState.setMsgId(1);
							messageRecipients.setFlag(0);
							messageRecipients.setMessage(message);
							SysUser sysUser1=sysUserService.getById(Long.valueOf(String.valueOf(userId)));
							messageRecipients.setSysUser(sysUser1);
							messageRecipients.setMsgState(msgState);
							messageRecipientsService.save(messageRecipients); 
							
						}
						//极光推送app端消息
						//DeviceAppointmentController deviceAppointmentController=new DeviceAppointmentController();
						content="实验："+reaction.getReactionName()+"  的实验步骤:"+reacProcess.getProcessName()+" 已经变更为开始了";
						message.setMessageContent(content);
						JPushData pushData =getJPushData(message,userIdList);
						Map<String, Integer> extraMap=new HashMap<String, Integer>();
						extraMap.put("type", LabConstans.MESSAGE_TYPE);
						extraMap.put("userId", sysUser.getUserId());
						extraMap.put("messageId", reactionId);
						extraMap.put("messageType", 3);
						try {
							//jpush.sendMessageAndNotification_Pall(pushData, null);
							jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			//----------------------------消息推送结束------------------------------------------------------
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save success");
		resultVo.setResultData(chemicalList);
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
				jPushData.setTag("实验步骤更新");
				jPushData.setTitle(msg.getMessageTitle());
				jPushData.setAlias(usernames);
				return jPushData;
			}
			return null;
		}
	 
	 
	@RequestMapping(value = "/reactionProcess/upateProcess", method = RequestMethod.PUT)
	public ResultVo updateProcess(
			@RequestBody ReactionProcessVo reactionProcessVo) {
		ResultVo resultVo = new ResultVo();
		if (reactionProcessVo != null) {
			String reactionProcess = reactionProcessVo.getReactionProcess();
			Integer id = reactionProcessVo.getId();
			reactionProcessService.updateProcess(reactionProcess, id);

			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("reactionProcessVo is null");
		return resultVo;

	}

	@RequestMapping(value = "/reactionProcess/uploadImage", method = RequestMethod.POST)
	public ResultVo uploadImage(@RequestBody String img) {
		ResultVo resultVo = new ResultVo();
		String filename = "";
		// 保存上传图片

		if (img != null) {
			String imgHead = img.substring(0, img.indexOf(","));
			img = img.substring(img.indexOf(",") + 1);
			if(imgHead.contains("tiff")){
				filename = new Base64Util().GenerateTiffImage(img, filePath);
			}else{
				filename = new Base64Util().GenerateImage(img, filePath);
			}
			
			
			String url = urlPath + filename;
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			resultVo.setResultData(url);
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("upload is null");
		return resultVo;
	}

	@RequestMapping(value = "/reactionProcess/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		
		ReactionProcess reactionProcess  = reactionProcessService.getById(id);
		Reaction reaction = new Reaction();
		if(reactionProcess !=null){
			reaction =reactionProcess.getReaction();
			
		}
		
		// 删除执行原料表
		List<ExecuteChemicalGroup> executeChemicalGroups  = executeChemicalGroupService.getByProcessId(id);
		for (ExecuteChemicalGroup executeChemicalGroup : executeChemicalGroups) {
			executeChemicalService.deleteByGroupId(executeChemicalGroup.getChemicalGroupId());
		}
		
		//TODO  未完成
		// 删除样品表
		prototypeService.deleteByProcessId(id);
		// 删除执行原料表
		executeChemicalGroupService.deleteByProcessId(id);
		//删除执行溶液表
		executeSolutionService.deleteByProcessId(id);
		// 删除试验设备表
		reactionDeviceService.deleteByProcessId(id);
		// 删除执行参数表
		executeParemeterService.deleteByProcessId(id);
		// 删除团队协助表
		teamAssistService.deleteByProcessId(id);
		// 删除批注表
		commentService.deleteByModuleId(id, 1);
		
		//删除分析测试
		List<Analytics>  analyticList = analyticService.getByProcessId(id);
		if(analyticList!=null && analyticList.size()>0){
			for (Analytics analytics : analyticList) {
				analyticalAttachmentService.delete(analytics.getAnalyticsId());
				analyticsDeviceService.delete(analytics.getAnalyticsId());
				analyticService.delete(analytics.getAnalyticsId());
			}
			
		}
		
		reactionProcessService.delete(id);
		
		Reaction reaction2 = reactionService.getByReactionId(reaction.getReactionId());
		List<ReactionProcess> processes = reaction2.getReactionProcesses();
		if(processes!=null && processes.size()>0){
			for(int i = 0;i<processes.size();i++){
				ReactionProcess reactionProcess2 = processes.get(i);
				reactionProcess2.setProcess(i+1);
				reactionProcessService.updateProcessNumber(reactionProcess2);
			}
		}
		
		getReactionStatus(reaction.getReactionId());
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
		return resultVo;
	}
	
	
	  /**
		 * @Description 单个新增试验步骤方法
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:51:21
		 */

		@RequestMapping(value = "/reactionProcess/Add", method = RequestMethod.POST)
		public ResultVo saveReactionProcess(@RequestBody ReactionProcessAddVo reactionProcessAddVo) {

			ResultVo resultVo = new ResultVo();
			
			 try {
				Reaction reaction=reactionProcessAddVo.getReaction();
				 ReactionProcess reactionProcess=reactionProcessAddVo.getReactionProcess();
				 
				 if(reactionProcess!=null){
					 Integer p=reactionProcess.getProcess();
					 if(reaction!=null){
						 List<ReactionProcess> reactionProcesses=reactionProcessService.getProcessByReactionId(reaction.getReactionId());
						 if(reactionProcesses!=null){
							 for (ReactionProcess reactionProcess2 : reactionProcesses) {
								Integer i=reactionProcess2.getProcess();
								if(i!=null&&i>=p){
									i++;
									reactionProcess2.setProcess(i);
									reactionProcessService.updateProcessNumber(reactionProcess2);
								}
							}
						 }
						 ReactionStatus reactionStatus=new ReactionStatus();
						 reactionStatus.setReactionStatusId(9);
						 reactionProcess.setReactionStatus(reactionStatus);
						 reactionProcess.setReaction(reaction);
						 reactionProcessService.save(reactionProcess);
						 getReactionStatus(reaction.getReactionId());
					 }
					
				 }
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save success");
				return resultVo;
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("save fail");
				return resultVo;
			}

		}
		/**
		 * @Description 从试验导入试验步骤方法
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:51:21
		 */
		
		@RequestMapping(value = "/reactionProcess/Copy", method = RequestMethod.POST)
		public ResultVo saveReactionProcessCopy(@RequestBody ReactionProcessCopyVo reactionProcessCopyVo) {
			
			ResultVo resultVo = new ResultVo();

			try {
				Reaction reaction=reactionProcessCopyVo.getReaction();
				List<ReactionProcess> reactionProcesses=reactionProcessCopyVo.getReactionProcesses();
				if(reaction!=null){
					 List<ReactionProcess> reactionProcesses1=reactionProcessService.getProcessByReactionId(reaction.getReactionId());
					   if(reactionProcesses1!=null&&reactionProcesses1.size()>0){
						   for (ReactionProcess reactionProcess : reactionProcesses1) {
								reactionProcessService.deleteByReactionId(reactionProcess.getReaction().getReactionId());
							}
					   }      
					
					if(reactionProcesses!=null&&reactionProcesses.size()>0){
						for (ReactionProcess reactionProcess : reactionProcesses) {
						ReactionStatus reactionStatus=new ReactionStatus();
						reactionStatus.setReactionStatusId(9);
						reactionProcess.setReactionStatus(reactionStatus);
						reactionProcess.setCreateDate(new Date());
						reactionProcess.setReaction(reaction);
						reactionProcessService.save(reactionProcess);
						List<ReactionExecuteParameter> reactionExecuteParameters=new ArrayList<ReactionExecuteParameter>(reactionProcess.getReactionExecuteParameters());
						   if(reactionExecuteParameters!=null&&reactionExecuteParameters.size()>0){
							   for (ReactionExecuteParameter reactionExecuteParameter : reactionExecuteParameters) {
								   reactionExecuteParameter.setCreateDate(new Date());
								   reactionExecuteParameter.setReactionProcess(reactionProcess);
								   executeParemeterService.save(reactionExecuteParameter);
							}
						   }
						
			          }
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save success");
				return resultVo;
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("save fail");
				return resultVo;
			}
			
		}
	  
		/**
		 * @Description 单个编辑试验步骤方法
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:51:21
		 */
		
		@RequestMapping(value = "/reactionProcess/Update", method = RequestMethod.PUT)
		public ResultVo updateReactionProcess(@RequestBody ReactionProcess reactionProcess) {
			
			ResultVo resultVo = new ResultVo();
			try {
				if(reactionProcess!=null){
						reactionProcessService.updateOneProcess(reactionProcess);
					}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update success");
				return resultVo;
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("update fail");
				return resultVo;
			}
			
		}
		
		
		/**
		 * @Description 单个编辑导入模板
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:51:21
		 */
		@RequestMapping(value = "/reactionProcess/saveTemplate", method = RequestMethod.POST)
		public ResultVo saveTemplate(
				@RequestParam(value = "reactionId", required = false) Integer reactionId,
				@RequestParam(value = "publicTemplateId", required = false) Integer publicTemplateId,
				@RequestParam(value = "privateTemplateId", required = false) Integer privateTemplateId) {
			
			ResultVo resultVo = new ResultVo();
			Reaction reaction = reactionService.getByReactionId(reactionId);
			List<Integer> processIdList = reactionProcessService.getByReactionId(reactionId);
			if(processIdList !=null && processIdList.size()>0){
				// 删除执行参数表
				executeParemeterService.batchRemove(processIdList);
				
			}
			//删除原有步骤
			reactionProcessService.deleteByReactionId(reactionId);
			
			try {
				if(publicTemplateId!=null && privateTemplateId==null){
					
					//行业模板详情
					IndustryReactionTemplate industryReactionTemplate = industryReactionTemplateService.getById(publicTemplateId);
					
					if(industryReactionTemplate!=null){
						List<ProfessionProcess> professionProcesses = industryReactionTemplate.getProfessionProcesses();
						for (int j = 0; j < professionProcesses.size(); j++) {
							// 生成实验记录
							ReactionProcess reactionProcess = new ReactionProcess();
							reactionProcess.setReaction(reaction);
							reactionProcess.setProcess(j+1);
							reactionProcess.setProcessName(professionProcesses.get(j).getProcessName());
							reactionProcess.setReactionStatus(reactionStatusService.getByType(2).get(3));

							reactionProcessService.save(reactionProcess);

							// 实验执行参数
							List<IndustryReactionTemplateParameter> industryReactionTemplateParameters = professionProcesses.get(j).getIndustryReactionTemplateParameters();
							if (industryReactionTemplateParameters != null
									&& industryReactionTemplateParameters.size() > 0) {

								for (int l = 0; l < industryReactionTemplateParameters.size(); l++) {
									ReactionExecuteParameter executeParameter = new ReactionExecuteParameter();
									executeParameter.setExecuteParameterDosage(industryReactionTemplateParameters.get(l).getReferenceValue().toString());
									executeParameter.setMeasurement(industryReactionTemplateParameters.get(l).getMeasurement());
									executeParameter.setReactionParameter(industryReactionTemplateParameters.get(l).getReactionParameter());
									executeParameter.setReactionProcess(reactionProcess);
									executeParemeterService.save(executeParameter);
								}
							}
						}
					}
					
					
				}else if(privateTemplateId!=null&&publicTemplateId==null){
					
					//个人模板详情
					PersonalTemplate personalTemplate= personalTemplateService.getById(privateTemplateId);
					if(personalTemplate!=null){
						 List<PersonalReactionTemplateProcess> personalProcesses = personalTemplate.getPersonalReactionTemplateProcesses();
						 for (int j = 0; j < personalProcesses.size(); j++) {
							// 生成实验记录
								ReactionProcess reactionProcess = new ReactionProcess();
								reactionProcess.setReaction(reaction);
								reactionProcess.setProcess(j+1);
								reactionProcess.setRemark(personalProcesses.get(j).getRemark());
								reactionProcess.setProcessName(personalProcesses.get(j).getPersonalProcessName());
								reactionProcess.setReactionStatus(reactionStatusService.getByType(2).get(3));

								reactionProcessService.save(reactionProcess);

								// 实验执行参数
								List<PersonalReactionTemplateParameter> personalParameters = personalProcesses.get(j).getPersonalReactionTemplateParameters();
								if (personalParameters != null
										&& personalParameters.size() > 0) {

									for (int l = 0; l < personalParameters.size(); l++) {
										ReactionExecuteParameter executeParameter = new ReactionExecuteParameter();
										executeParameter.setExecuteParameterDosage(personalParameters.get(l).getReferenceValue().toString());
										executeParameter.setMeasurement(personalParameters.get(l).getMeasurement());
										executeParameter.setReactionParameter(personalParameters.get(l).getReactionParameter());
										executeParameter.setReactionProcess(reactionProcess);
										executeParemeterService.save(executeParameter);
									}
								}
						}
					}
					
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save success");
				
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("save fail");
			}
			
			return resultVo;
			
		}
		
		
		/**
		 * @Description 获取溶液不足的集合
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		public List<SolutionEntity> getSolutionList(Integer processId){
			List<SolutionEntity> solutionList = new ArrayList<SolutionEntity>();
			List<ExecuteSolution> executeSolutions = executeSolutionService.getById(processId);
			for (ExecuteSolution executeSolution : executeSolutions) {
				if(executeSolution.getSolution()!=null){
					SolutionEntity solution = solutionService.getById(executeSolution.getSolution().getSolutionId());
					
					Double solutionConversion = solution.getSolutionMeasurement().getConversionRelation();
					Double chemicalConversion = executeSolution.getMeasurement().getConversionRelation();
					Double chemicalDosage =  Double.valueOf(executeSolution.getSolutionDosage())*(chemicalConversion/solutionConversion);
					if(solution.getSolutionTotal()- chemicalDosage<0.0){
						solutionList.add(solution);
					}
				}
				
			}
			return solutionList;
		}
		
		/**
		 * @Description 实验用量变更库存
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		public void updateSolution(Integer processId) {
			List<ExecuteSolution> executeSolutions = executeSolutionService.getById(processId);
			for (ExecuteSolution executeSolution : executeSolutions) {
				if(executeSolution.getSolution()!=null){
					SolutionEntity solution = solutionService.getById(executeSolution.getSolution().getSolutionId());
					
					Double solutionConversion = solution.getSolutionMeasurement().getConversionRelation();
					Double chemicalConversion = executeSolution.getMeasurement().getConversionRelation();
					Double chemicalDosage =  Double.valueOf(executeSolution.getSolutionDosage())*(chemicalConversion/solutionConversion);
					solution.setSolutionTotal(solution.getSolutionTotal()-chemicalDosage);
					solutionService.update(solution);
				}
				
			}
		}
		
		/**
		 * @Description 获取库存不足的集合
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		public List<Inventories> getInventoryList(Integer processId){
			
			List<Inventories> invnetoryList = new ArrayList<Inventories>();
			List<ExecuteChemical> executeChemicals = new ArrayList<ExecuteChemical>();
			
			List<ExecuteChemicalGroup> chemicalGroups = executeChemicalGroupService.getByProcessId(processId);
			for (ExecuteChemicalGroup executeChemicalGroup : chemicalGroups) {
				if(executeChemicalGroup.getChemicalGroupId()!=null){
					List<ExecuteChemical> executeChemicalList = executeChemicalService.getByGroupId(executeChemicalGroup.getChemicalGroupId());
					executeChemicals.addAll(executeChemicalList);
				}
			}
			
//			List<ExecuteChemical> executeChemicals = executeChemicalService.getById(processId);
			if (executeChemicals != null && executeChemicals.size() > 0) {
				for (ExecuteChemical executeChemical : executeChemicals) {

					Inventories inventory = inventoriesService.getById(executeChemical.getInventory().getInventoryId());
					Double inventoryConversion = inventory.getMeasurement().getConversionRelation();
					Double chemicalConversion = executeChemical.getMeasurement().getConversionRelation();
					Double chemicalDosage =  executeChemical.getChemicalDosage()*(chemicalConversion/inventoryConversion);
					if(inventory.getActAvaWei()- chemicalDosage<0.0){
						invnetoryList.add(inventory);
					}
				}
			}
			return invnetoryList;
			
		}

		/**
		 * @Description 实验用量变更库存
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		public void updateInventory(Integer processId) {

//			List<ExecuteChemical> executeChemicals = executeChemicalService.getById(processId);
			
			List<ExecuteChemical> executeChemicals = new ArrayList<ExecuteChemical>();
			
			List<ExecuteChemicalGroup> chemicalGroups = executeChemicalGroupService.getByProcessId(processId);
			for (ExecuteChemicalGroup executeChemicalGroup : chemicalGroups) {
				if(executeChemicalGroup.getChemicalGroupId()!=null){
					List<ExecuteChemical> executeChemicalList = executeChemicalService.getByGroupId(executeChemicalGroup.getChemicalGroupId());
					executeChemicals.addAll(executeChemicalList);
				}
			}
			
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
					.get(0);
			Integer userId = sysUser.getUserId();
			try {
				if (executeChemicals != null && executeChemicals.size() > 0) {
					for (ExecuteChemical executeChemical : executeChemicals) {

						Inventories inventory = inventoriesService.getById(executeChemical.getInventory().getInventoryId());
						Double inventoryConversion = inventory.getMeasurement().getConversionRelation();
						Double chemicalConversion = executeChemical.getMeasurement().getConversionRelation();
						Double chemicalDosage =  executeChemical.getChemicalDosage()*(chemicalConversion/inventoryConversion);
//						inventory.setActAvaWei(inventory.getActAvaWei()- executeChemical.getChemicalDosage());
						inventory.setActAvaWei(inventory.getActAvaWei()- chemicalDosage);
						inventory.setModifier(userId + "");
						inventory.setModifyDate(new Date());

						InventoryModify inventoryModify = new InventoryModify();
						ModifyType modifyType = new ModifyType();
						modifyType.setModifyTypeId(1);
						
						ReactionProcess reactionProcess = new ReactionProcess();
						reactionProcess.setReactionProcessId(processId);

						inventoryModify.setModifyType(modifyType);
						inventoryModify.setInventories(executeChemical.getInventory());
						inventoryModify.setMeasurement(executeChemical.getMeasurement());
						inventoryModify.setModifyProcess(reactionProcess);
						inventoryModify.setChangeDate(new Date());
						inventoryModify.setModifier(sysUser);
						inventoryModify.setModifyDate(new Date());
						inventoryModify.setModifyAfter(inventory.getActAvaWei());

						inventoryModifyService.save(inventoryModify);
						inventoriesService.updateInventory(inventory);
						//变更库存组重量
						InventoryGroups inventoryGroups = inventory.getInventoryGroups();
//						inventoryGroups.setTotalWei(inventoryGroups.getTotalWei()- executeChemical.getChemicalDosage());
						inventoryGroups.setTotalWei(inventoryGroups.getTotalWei()-chemicalDosage);
						inventoryGroupsService.update(inventoryGroups);

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		 /**
		 * @Description 获取库存位置父名称
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		  public String getInventoryPname( Integer id) {
			    try {
			      String ss="";
			    loop:for(int i=0;i<10;i++){
			      InventoryLocation inventoryLocation =  inventoryLocService.getById(id);
			      if(inventoryLocation.getPid()!=null&&inventoryLocation.getPid()!=0){
			        if(i==0){
			          ss="";
			        }else if(i==1){
			          ss=inventoryLocation.getLabel();
			        }else{
			          ss=inventoryLocation.getLabel()+">"+ss;
			        }
			        id=inventoryLocation.getPid();
			      }else{
			        if(!"".equals(ss)){
			          ss=inventoryLocation.getLabel()+">"+ss;
			        }else{
			          ss=inventoryLocation.getLabel();  
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
		  
		  
		  /**
			 * @Description 获取设备名称
			 * @author xux
			 * @version V1.0
			 * @date 2017年5月18日 上午11:50:28
			 */
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
		  
		  /**
			 * @Description 判断实验状态
			 * @author xux
			 * @version V1.0
			 * @date 2017年5月18日 上午11:50:28
			 */
		  public void getReactionStatus(Integer id) {
			  
			 List<ReactionProcess> processList =  reactionProcessService.getProcessByReactionId(id);
			 Integer endNum = 0;
			 Integer finishNum = 0;
			 Integer startNum = 0;
			 Reaction reaction = new Reaction();
			 reaction.setReactionId(id);
			 ReactionStatus status = new ReactionStatus();
			 
			 for (ReactionProcess reactionProcess : processList) {
				ReactionStatus reactionStatus =  reactionProcess.getReactionStatus();
				if(reactionStatus!=null){
					if(reactionStatus.getReactionStatusId()-8==0){
						//终止步骤个数
						endNum++;
					}
					if(reactionStatus.getReactionStatusId()-7==0){
						//完成步骤个数
						finishNum++;
					}
					if(reactionStatus.getReactionStatusId()-9==0){
						//就绪步骤个数
						startNum++;
					}
				}
			}
			 
			if(endNum ==0 && (finishNum-processList.size()==0)){
				 status.setReactionStatusId(3);
				 reaction.setReportDate(new Date());
				 reaction.setReactionStatus(status);
				 
			 }else if(endNum ==0 && (startNum-processList.size()==0)){
				 status.setReactionStatusId(1);
				 reaction.setReactionStatus(status);
				 
			 } else if(endNum ==0 && (finishNum-processList.size()<0)){
				 status.setReactionStatusId(2);
				 reaction.setReactionStatus(status);
				
			 }
			 else if(endNum ==0&& (startNum-processList.size()<0)){
				 status.setReactionStatusId(2);
				 reaction.setReactionStatus(status);
				
			 }
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        		 else if(endNum>0){
				 status.setReactionStatusId(4);
				 reaction.setReportDate(new Date());
				 reaction.setReactionStatus(status);
			 }
			 reactionService.update(reaction); 
		  }
		 
		

}
