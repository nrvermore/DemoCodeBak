package com.labwinner.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.jpush.api.push.model.notification.Notification;

import com.google.gson.JsonObject;
import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Attachment;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.ExecuteChemical;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.MsgDetailtype;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.MsgType;
import com.labwinner.domain.Prototype;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionDevice;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.domain.SysUser;
import com.labwinner.domain.TeamAssist;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
import com.labwinner.service.AttachmentService;
import com.labwinner.service.DeviceLocationService;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.service.MessageRecipientsService;
import com.labwinner.service.MessageService;
import com.labwinner.service.ProjectNumberService;
import com.labwinner.service.ReactionProcessService;
import com.labwinner.service.ReactionService;
import com.labwinner.service.SysUserService;
import com.labwinner.service.TeamAssistService;
import com.labwinner.util.Html2ImageUtil;
import com.labwinner.util.HtmlUtil;
import com.labwinner.vo.TeamAssistVo;

@RestController
public class TeamAssistController {

	private static Logger logger = LoggerFactory
			.getLogger(TeamAssistController.class);

	@Autowired
	private TeamAssistService teamAssistService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private MessageService  messageService;
	
	@Autowired
	private MessageRecipientsService messageRecipientsService;
	
	@Autowired
	private ReactionProcessService reactionProcessService;
	
	@Autowired
	private InventoriesService inventoriesService;
	
	@Autowired
	private DeviceLocationService deviceLocationService;
	
	@Autowired
	private InventoryLocationService inventoryLocService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private ReactionService reactionService;
	
	@Autowired
	private Jpush jpush;
	
	
	@Value("${web.qrUrl-path}")
	private String qrUlPath;
	@Value("${web.msgFile-path}")
	private String msgFile;
	
	
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/teamAssist/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		TeamAssist teamAssist = teamAssistService.getById(id);
		if (teamAssist == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(teamAssist);
		return resultVo;
	}
	
	
	/**
	 * @Description 新增对象
	 * @author xux
	 * @version V1.0
	 * @throws Exception 
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/teamAssist", method = RequestMethod.POST)
	public ResultVo save(@RequestBody TeamAssistVo teamAssistVo) throws Exception {
		ResultVo resultVo = new ResultVo();
		ReactionProcess reactionProcess = teamAssistVo.getReactionProcess();
		List<Integer> userIdList = teamAssistVo.getUserIdList();
		String assistContent = teamAssistVo.getAssistContent();
		Integer processId =null;
		if(reactionProcess !=null){
			processId= reactionProcess.getReactionProcessId();
			//删除步骤id
			teamAssistService.deleteByProcessId(processId);
		}
		if(userIdList!=null && userIdList.size()>0){
			String assistPeople="";
			for(Integer userId :userIdList){
				TeamAssist teamAssist = new TeamAssist();
				SysUser sysUser = new SysUser();
				sysUser.setUserId(userId);
				teamAssist.setAssistContent(assistContent);
				teamAssist.setReactionProcess(reactionProcess);
				teamAssist.setSysUser(sysUser);
				teamAssistService.save(teamAssist);
				
				SysUser sysUser1=sysUserService.getById(Long.valueOf(String.valueOf(userId)));
				if(assistPeople.indexOf(",")>0){
					assistPeople=assistPeople+","+sysUser1.getRealname();
				}else{
					assistPeople=assistPeople+sysUser1.getRealname();
				}
				
			}
			//----------------------------消息推送开始--------------------------------------------------
			if(userIdList!=null && userIdList.size()>0){
			LoginController login = new LoginController();
			ReactionProcess res=getByProcessId(processId);
			 createDir("src/main/resources/template/");
			String imagePath=createImage(res);
			
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			List<Map<String, String>> listMap=new ArrayList<Map<String,String>>();
			listMap=teamAssistService.getByProcessId(processId);
			Message message=new Message();
			message.setMessageTitle(sysUser.getRealname()+"实验执行向你求助");
			String content="";
			String reactionName=listMap.get(0).get("reaction_name")==null?"":listMap.get(0).get("reaction_name");
			String processName=listMap.get(0).get("process_name")==null?"":listMap.get(0).get("process_name");
			assistContent=assistContent==null?"":assistContent;
			content=sysUser.getRealname()+"实验执行向你求助;\r\n  "+"实验名称："+reactionName+";\r\n 实验步骤："+processName+";\r\n 求助内容："+assistContent
					+"\r\n \r\n"+"实验步骤详情见附件！";
			message.setMessageContent(content);
			MsgType msgType=new MsgType();
			msgType.setMsgTypeId(2);
			message.setMsgType(msgType);
			MsgDetailtype msgDetailtype=new MsgDetailtype();
			msgDetailtype.setMsgDetailtypeId(7);
			message.setMsgDetailtype(msgDetailtype);
			message.setFlag(0);
		//	message.setMsgState(msgState);
			message.setSysUser(sysUser);
			message.setSenderDate(new Date());
			message.setFlag(0);
			messageService.save(message);
			Attachment attachment=new Attachment();
			attachment.setMessage(message);
			attachment.setAttachmentName(imagePath.substring(imagePath.indexOf("/msgFile")+9, imagePath.length()));
			attachmentService.save(attachment);
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
			}
			//极光推送app端消息
			//DeviceAppointmentController deviceAppointmentController=new DeviceAppointmentController();
			content="[实验执行] "+"实验名称："+reactionName+"; 实验步骤："+processName+"; 求助内容："+assistContent;
			//content="[日程管理] "+sysUser.getRealname()+"日程向你请求协助;  "+"日程名称："+calendars.getTitle()+";  请求协助内容："+CalendarContent;
			message.setMessageContent(content);
			SysUser loginUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			JPushData pushData =getJPushData(message,userIdList);
			Map<String, Integer> extraMap=new HashMap<String, Integer>();
			extraMap.put("type", LabConstans.REACTION_TYPE);
			extraMap.put("userId", loginUser.getUserId());
			extraMap.put("messageId", processId);
			extraMap.put("messageType", 3);
			try {
				jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
			} catch (Exception e) {
				// TODO: handle exception
			}
			}
			//----------------------------消息推送结束------------------------------------------------------
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			return resultVo;
		}else{
			resultVo.setErrCode(2);
			resultVo.setErrMsg("save fail");
			return resultVo;
		}
		
	}
	
	public ReactionProcess getByProcessId( Integer id) {
		ReactionProcess reactionProcess = reactionProcessService.getByProcessId(id);
		if (reactionProcess == null) {
			return reactionProcess;
		}
		List<ExecuteChemical> executeChemicals = reactionProcess.getExecuteChemicals();
		if(executeChemicals!=null && executeChemicals.size()>0){
			for (ExecuteChemical executeChemical : executeChemicals) {
				InventoryLocation location = executeChemical.getInventory().getInventoryLocation();
				if(location!=null && location.getPid()!=0){
					String pname = getInventoryPname(location.getCid());
					location.setParentName(pname);
				}
			}
		}
		
		List<ReactionDevice> testDevices = reactionProcess.getReactionDevices();
		
		if(testDevices!=null && testDevices.size()>0){
			for (ReactionDevice testDevice : testDevices) {
				DeviceLocation location = testDevice.getDevice().getDeviceLocation();
				if(location!=null && !"0".equals(location.getDeviceLocaPid())){
					String pname = getDevicePname(location.getDeviceLocaId());
					location.setParentName(pname);
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
	
		return reactionProcess;
	}

	private String createImage(ReactionProcess reactionProcess) throws IOException, InterruptedException {
		
		Reaction reaction = reactionService.getByReactionId(reactionProcess.getReaction().getReactionId());
		reactionProcess=reactionProcessService.getByProcessId(reactionProcess.getReactionProcessId());
		 Map<String, Object> paramMap = new HashMap<String, Object>();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//		 ReactionProcess reactionProcess=(ReactionProcess)res.getResultData();
         paramMap.put("reactionName",reactionProcess.getReaction().getReactionName());
         paramMap.put("processName", reactionProcess.getProcessName());
         paramMap.put("record", reactionProcess.getReactionProcess()==null?"":reactionProcess.getReactionProcess());
         paramMap.put("remark", reactionProcess.getRemark()==null?"":reactionProcess.getRemark());
         String start="";
         if(reactionProcess.getStartTime()!=null){
        	 start= sdf.format(reactionProcess.getStartTime());
         }
         paramMap.put("start", start);
         String end="";
         if(reactionProcess.getEndTime()!=null){
        	 end=sdf.format(reactionProcess.getEndTime());
         }
         paramMap.put("end", end);
         String proStart="";  
         String proEnd="";
         if(reaction.getProjectBasicInfo()!=null){
        	 if(reaction.getProjectBasicInfo().getProName()!=null){
        		 paramMap.put("proName", reaction.getProjectBasicInfo().getProName()); 
        	 }else{
        		 paramMap.put("proName","");
        	 }
        	 if(reaction.getProjectBasicInfo().getProStartTime()!=null){
            	 proStart=sdf.format(reaction.getProjectBasicInfo().getProStartTime());
             }
        	 if(reaction.getProjectBasicInfo().getProDeadline()!=null){
            	 proEnd=sdf.format(reaction.getProjectBasicInfo().getProDeadline());
             }
         }else{
        	 paramMap.put("proName", ""); 
         }
         paramMap.put("proStart", proStart);
         paramMap.put("proEnd", proEnd);
         String reacStart="";
         if(reaction.getStartTime()!=null){
        	 reacStart=sdf.format(reaction.getStartTime());
          }
         paramMap.put("reacStart", reacStart);
         String reacEnd="";
        if(reaction.getEndTime()!=null){
        	reacEnd=sdf.format(reaction.getEndTime());
        }
        paramMap.put("reacEnd", reacEnd);
        
         paramMap.put("reactionStatus",reaction.getReactionStatus().getReactionStatus() );
    	 String assistPeople="";
         for(int i=0;i<reactionProcess.getTeamAssists().size();i++){
        	 assistPeople=assistPeople+reactionProcess.getTeamAssists().get(i).getSysUser().getRealname();
         }
         paramMap.put("assistPeople", assistPeople);
         paramMap.put("assistContent", reactionProcess.getTeamAssists().get(0).getAssistContent());
         List<Map<String, Object>> paramList=new ArrayList<Map<String,Object>>();
         if(reactionProcess.getReactionExecuteParameters()!=null&&reactionProcess.getReactionExecuteParameters().size()>0){
        	 for(int i=0;i<reactionProcess.getReactionExecuteParameters().size();i++){
        		 Map<String, Object> param = new HashMap<String, Object>(); 
                 param.put("name", reactionProcess.getReactionExecuteParameters().get(i).getReactionParameter());
                 if(reactionProcess.getReactionExecuteParameters().get(i).getMeasurement()!=null){
                	  param.put("unint", reactionProcess.getReactionExecuteParameters().get(i).getMeasurement().getMeasureUnit());
                 }else{
                	 param.put("unint","");
                 }
               
                 param.put("count", reactionProcess.getReactionExecuteParameters().get(i).getExecuteParameterDosage()==null?"":reactionProcess.getReactionExecuteParameters().get(i).getExecuteParameterDosage());
                 paramList.add(param);
        	 }
        	 
         }
         paramMap.put("paramList", paramList);
         List<Map<String, Object>> chemicalList=new ArrayList<Map<String,Object>>();
        
         if(reactionProcess.getExecuteChemicalGroups()!=null&&reactionProcess.getExecuteChemicalGroups().size()>0){
        	 for(int i=0;i<reactionProcess.getExecuteChemicalGroups().size();i++){
        		 Map<String, Object> chemical = new HashMap<String, Object>(); 
        		 if(reactionProcess.getExecuteChemicalGroups().get(i).getInventoryGroups()!=null){
        			 chemical.put("name", reactionProcess.getExecuteChemicalGroups().get(i).getInventoryGroups().getInventoryName());
                     chemical.put("batchNumber", reactionProcess.getExecuteChemicalGroups().get(i).getInventoryGroups().getBatchNumber());
                    
                      chemical.put("supplier", reactionProcess.getExecuteChemicalGroups().get(i).getInventoryGroups().getSupplier().getSuprName());
        		 }else{
        			 chemical.put("name", "");
                     chemical.put("batchNumber", "");
                     chemical.put("supplier", "");
        		 }
                 if(reactionProcess.getExecuteChemicalGroups().get(i).getInventoryGroups().getMeasurement()!=null){
                	 chemical.put("unint", reactionProcess.getExecuteChemicalGroups().get(i).getInventoryGroups().getMeasurement().getMeasureUnit());
                 }else{
                	 chemical.put("unint","");
                 }
                 if(reactionProcess.getExecuteChemicalGroups().get(i).getGroupDosage()!=null){
                	 chemical.put("count", reactionProcess.getExecuteChemicalGroups().get(i).getGroupDosage());
                 }else{
                	 chemical.put("count", "");
                 }
                 chemicalList.add(chemical);
        	 }
        	 
         }
         paramMap.put("chemicalList", chemicalList);
         
         List<Map<String, Object>> solutionList=new ArrayList<Map<String,Object>>();
         
         if(reactionProcess.getExecuteSolutions()!=null&&reactionProcess.getExecuteSolutions().size()>0){
        	 for(int i=0;i<reactionProcess.getExecuteSolutions().size();i++){
        		 Map<String, Object> solution = new HashMap<String, Object>(); 
        		 if(reactionProcess.getExecuteSolutions().get(i).getSolution()!=null){
        			 solution.put("name", reactionProcess.getExecuteSolutions().get(i).getSolution().getSolutionName());
        			 if(reactionProcess.getExecuteSolutions().get(i).getSolution().getSoluteInventories()!=null){
        				 solution.put("solute", reactionProcess.getExecuteSolutions().get(i).getSolution().getSoluteInventories().getInventoryName()); 
        			 }else{
        				 solution.put("solute", ""); 
        			 }
            		 
                    if(reactionProcess.getExecuteSolutions().get(i).getSolution().getSolventName()!=null){
                    	solution.put("solvent", reactionProcess.getExecuteSolutions().get(i).getSolution().getSolventName());
                    }else{
                    	if( reactionProcess.getExecuteSolutions().get(i).getSolution().getSoluteInventories()!=null){
                    		solution.put("solvent", solution.put("solute", reactionProcess.getExecuteSolutions().get(i).getSolution().getSoluteInventories().getInventoryName())==null);	
                    	}else{
                    		solution.put("solvent","");
                    	}
                    	
                    }
        		 }else{
        			 solution.put("name", "");
            		 solution.put("solute", "");
                     solution.put("solvent", "");
        		 }
                 if(reactionProcess.getExecuteSolutions().get(i).getMeasurement()!=null){
                	 solution.put("unint", reactionProcess.getExecuteSolutions().get(i).getMeasurement().getMeasureUnit());
                 }else{
                	 solution.put("unint","");
                 }
                 if(reactionProcess.getExecuteSolutions().get(i).getSolutionDosage()!=null){
                	 solution.put("count", reactionProcess.getExecuteSolutions().get(i).getSolutionDosage()); 
                 }else{
                	 solution.put("count", ""); 
                 }
                 solutionList.add(solution);
        	 }
        	 
         }
         paramMap.put("solutionList", solutionList);
         
         List<Map<String, Object>> deviceList=new ArrayList<Map<String,Object>>();
         if(reactionProcess.getReactionDevices()!=null&&reactionProcess.getReactionDevices().size()>0){
        	 for(int i=0;i<reactionProcess.getReactionDevices().size();i++){
        		 Map<String, Object> device = new HashMap<String, Object>(); 
        		 if(reactionProcess.getReactionDevices()!=null){
        			 device.put("name", reactionProcess.getReactionDevices().get(i).getDevice().getDeviceName());
        			 String parentName=null;
        			 if(reactionProcess.getReactionDevices().get(i).getDevice().getDeviceLocation()!=null){
        				 parentName=reactionProcess.getReactionDevices().get(i).getDevice().getDeviceLocation().getParentName();
        			 }
                     String local="";
                     if(parentName!=null){
                    	 local=parentName+">"+reactionProcess.getReactionDevices().get(i).getDevice().getDeviceLocation().getLabel();
                     }else{
                    	 local=reactionProcess.getReactionDevices().get(i).getDevice().getDeviceLocation().getLabel();
                     }
                     device.put("local", local);
        		 }
                 deviceList.add(device);
        	 }
        	 
         }
         paramMap.put("deviceList", deviceList);
         List<Map<String, Object>> protoList=new ArrayList<Map<String,Object>>();
         if(reactionProcess.getPrototypes()!=null&&reactionProcess.getPrototypes().size()>0){
        	 if(reactionProcess.getPrototypes().get(0).getPrototypeName()!=null){
        		 for(int i=0;i<reactionProcess.getPrototypes().size();i++){
            		 Map<String, Object> proto = new HashMap<String, Object>(); 
            		 proto.put("name", reactionProcess.getPrototypes().get(i).getPrototypeName());
            		 String parentName=null;
            		 if(reactionProcess.getPrototypes().get(i).getInventoryLocation()!=null){
            			 parentName=reactionProcess.getPrototypes().get(i).getInventoryLocation().getParentName();
            		 }
                     String local="";
                     if(parentName!=null){
                    	 local=parentName+">"+reactionProcess.getPrototypes().get(i).getInventoryLocation().getLabel();
                     }else{
                    	 if(reactionProcess.getPrototypes().get(i).getInventoryLocation()!=null){
                    		 local= reactionProcess.getPrototypes().get(i).getInventoryLocation().getLabel();
                    	 }
                     }
                     proto.put("local", local);
                     proto.put("height", reactionProcess.getPrototypes().get(i).getPrototypeDosage());
                     if(reactionProcess.getPrototypes().get(i).getMeasurement()!=null){
                    	 proto.put("unint", reactionProcess.getPrototypes().get(i).getMeasurement().getMeasureUnit()); 
                     }else{
                    	 proto.put("unint", ""); 
                     }
                     proto.put("count", reactionProcess.getPrototypes().get(i).getPrototypeNum());
                     proto.put("code", reactionProcess.getPrototypes().get(i).getBarCode());
                     protoList.add(proto);
            	 } 
        	 }
        	
        	 
         }
         paramMap.put("protoList", protoList);
         HtmlUtil hu=new HtmlUtil();
         createDir("src/main/resources/template/");
         String outUrl="src/main/resources/template/"+UUID.randomUUID().toString()+".html";
         hu.CreateHtml(paramMap, outUrl, "message.html");
         Html2ImageUtil hi=new Html2ImageUtil();
         String imagePath=msgFile+UUID.randomUUID().toString()+".png";
         hi.HtmlToImage(outUrl,imagePath);
		return imagePath;
	}

	// 创建目录
				public static boolean createDir(String destDirName) {
					File dir = new File(destDirName);
					if (dir.exists()) {// 判断目录是否存在
						System.out.println("创建目录失败，目标目录已存在！");
						return false;
					}
					if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
						destDirName = destDirName + File.separator;
					}
					if (dir.mkdirs()) {// 创建目标目录
						System.out.println("创建目录成功！" + destDirName);
						return true;
					} else {
						System.out.println("创建目录失败！");
						return false;
					}
				}
	
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
	 * @Description 更新对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/teamAssist", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody List<TeamAssist> teamAssists) {
		ResultVo resultVo = new ResultVo();
		if(teamAssists.size()>0){
			for(TeamAssist teamAssist :teamAssists){
				teamAssistService.update(teamAssist);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update success");
			return resultVo;
		}else{
			resultVo.setErrCode(2);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}
		
	}
	
	/**
	 * @Description 删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/teamAssist/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		
		teamAssistService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("update success");
		return resultVo;
	}
	
	/**
	 * @Description 负责人列表，成员列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/teamAssist/getManager/{proId}", method = RequestMethod.GET)
	public ResultVo getManager(@PathVariable("proId") Integer proId) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser loginUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		List<SysUser> users = sysUserService.getUser(proId,11);
		for (int i = 0; i<users.size();i++) {
			if(users.get(i).getUserId()-loginUser.getUserId()==0){
				users.remove(i);
			}
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("update success");
		resultVo.setResultData(users);
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
				jPushData.setTag("实验求助消息");
				jPushData.setTitle(msg.getMessageTitle());
				jPushData.setAlias(usernames);
				return jPushData;
			}
			return null;
		}
}
