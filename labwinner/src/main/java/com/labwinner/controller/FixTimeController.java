package com.labwinner.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;








































import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.AnalyticalAttachment;
import com.labwinner.domain.Attachment;
import com.labwinner.domain.CalendarAssist;
import com.labwinner.domain.City;
import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.Message;
import com.labwinner.domain.ProjectDocuments;
import com.labwinner.domain.SignGroup;
import com.labwinner.domain.SignIn;
import com.labwinner.domain.SysUser;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
import com.labwinner.service.AnalyticalAttachmentService;
import com.labwinner.service.AttachmentService;
import com.labwinner.service.CalendarAssistService;
import com.labwinner.service.JournalArticleService;
import com.labwinner.service.KnowledgeAccService;
import com.labwinner.service.ProjectDocumentsService;
import com.labwinner.service.SignGroupService;
import com.labwinner.service.SignInService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.OtherToPdf;
import com.labwinner.util.PdfUtil;
;



@RestController
public class FixTimeController {

	private static Logger logger = LoggerFactory
			.getLogger(FixTimeController.class);

	@Autowired
	private JournalArticleService journalArticleService;
	
	@Autowired
	private  SysUserService sysUserService;
	
	@Autowired
	private SignInService signInService;
	
	@Autowired
	private KnowledgeAccService knowledgeAccService;
	

	@Autowired
	private CalendarAssistService calendarAssistService;
	
	@Autowired
	private SignGroupService signGroupService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private AnalyticalAttachmentService analyticalAttachmentService;
	
	@Autowired
	private ProjectDocumentsService projectDocumentsService;
	
	@Autowired
	private Jpush jpush;
	
	@Value("${web.agency_pdf}")
	String pdfAgencyPath;
	
	@Value("${pdfView.url-path}")
	private String pdfViewUrl;
	
	@Value("${pdfView.upload-path}")
	private String pdfViewUpload;
	
	
	@Value("${web.msgFile-path}")
	String msgUpload;
	@Value("${web.msgFileUrl-path}")
	private String msgPath;
	
	@Value("${reaction.url-path}")
	private String reactionPath;
	
	@Value("${projectDocuments.url-path}")
	private String documentsPath;
	
	

	/**
	 * @Description 根据实验主键获取对象
	 * @author xux
	 * @version V1.0
	 * @throws Exception 
	 * @date 2017年5月18日 上午11:50:28
	 */
	@Scheduled(cron="0 0 9 ? * MON-FRI") //每个周一、周二、周三、周四、周五的9：15触发
	//@Scheduled(cron="0/30 * * * * ?")
	public ResultVo getByFixTime() {
		ResultVo resultVo = new ResultVo();
		List<SysUser> users=sysUserService.getUsers("ROLE_TEAM");
		List<Integer> userIdList=new ArrayList<Integer>();
		if(users!=null&&users.size()>0){
			for(SysUser user:users){
				userIdList.add(user.getUserId());
			}
		}
		if(userIdList.size()>0){
			Message message=new Message();
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			SimpleDateFormat sdf1=new SimpleDateFormat("HH:mm:ss");  
			message.setMessageTitle(sdf.format(date)+"签到情况");
			List<SignIn> signIns=signInService.getGroupDayList();
			String content="";
			List<SignGroup> signGroups=signGroupService.getAllGroup();
			int unCount=signGroups.size()-signIns.size()>=0?signGroups.size()-signIns.size():0;
			content="今日"+signIns.size()+"人已签到，"+unCount+"人未签到";
			message.setMessageContent("[今日签到]："+content);
			JPushData pushData =getJPushData(message,userIdList);
			Map<String, Integer> extraMap=new HashMap<String, Integer>();
			extraMap.put("type", LabConstans.SIGNIN_TYPE);
			extraMap.put("userId", 0);
			extraMap.put("messageType", 3);
			try {
				jpush.sendMessageAndNotification_JsonALL(pushData, null,extraMap,"",0);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
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
				jPushData.setTag("签到信息");
				jPushData.setTitle(msg.getMessageTitle());
				jPushData.setAlias(usernames);
				return jPushData;
			}
			return null;
		}
	 
	 
	 
	
		@Scheduled(cron="0/30 * * * * ?")
		public ResultVo conversionPdf() throws Exception {
			ResultVo resultVo = new ResultVo();
			List<KnowledgeAcc> knowledgeAccs=new ArrayList<KnowledgeAcc>();
			knowledgeAccs=knowledgeAccService.getAllConversion();
			List<Attachment> attachments=attachmentService.getAllConversion();
			List<AnalyticalAttachment> analyticalAttachments=analyticalAttachmentService.getAllConversion();
			List<ProjectDocuments> projectDocuments=projectDocumentsService.getAllConversion();
			OtherToPdf otp=new OtherToPdf();
			if(knowledgeAccs!=null&&knowledgeAccs.size()>0){
				for(KnowledgeAcc knowledgeAcc:knowledgeAccs){
					knowledgeAcc.setPdfCount(1);
					knowledgeAccService.update(knowledgeAcc);
				}
				for(KnowledgeAcc knowledgeAcc:knowledgeAccs){
					String fileName=knowledgeAcc.getUploadFiles().substring(knowledgeAcc.getUploadFiles().indexOf("/pdfs/")+6, knowledgeAcc.getUploadFiles().length());
					String type=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
					String pdfUrl=null;
					int conversionCount=knowledgeAcc.getConversionCount()==null?0:knowledgeAcc.getConversionCount();
					
					if(LabConstans.CONVERSION_TYPE.contains(type)){
						 JSONObject json = new JSONObject();
						 json.put("async", false);
						 json.put("filetype", type);
						 json.put("key", fileName.substring(0, 36));
						 json.put("outputtype", "pdf");
						 json.put("title", "LabWinner");
						 json.put("url", pdfAgencyPath+fileName);
						 //json.put("url", "http://staticfile.labwinner.com/LabWinner/pdfs/6811144e-a3ef-4a4f-8deb-683a4b4dc7bf新材料研发平台用户指引手册1.pptx");
					try {
						 String result=otp.sendPost("http://118.31.18.178:8005/ConvertService.ashx",json.toString());
						   if(result.indexOf("<FileUrl>")>0){
							   pdfUrl=result.substring(result.indexOf("<FileUrl>")+9,result.indexOf("</FileUrl>"));
							   knowledgeAcc.setPdfCount(2);
							   conversionCount++;
				           }else{
				        	   result=otp.sendPost("http://118.31.18.178:8005/ConvertService.ashx",json.toString());
				        	   if(result.indexOf("<FileUrl>")>0){
				        		   pdfUrl=result.substring(result.indexOf("<FileUrl>")+9,result.indexOf("</FileUrl>"));
				        		   knowledgeAcc.setPdfCount(2);
				        		   conversionCount++;
				        	   }else{
				        		   knowledgeAcc.setPdfCount(0);
				        		   conversionCount++;
				        	   }
				           }
						   if(pdfUrl!=null){
								 PdfUtil pu=new PdfUtil();
								 String viewName=pu.getPdfLoad("http://118.31.18.178:8005/2018-04-09-15-58/"+pdfUrl.substring(pdfUrl.indexOf("/cache")+1, pdfUrl.length()), pdfViewUpload);
								 knowledgeAcc.setPdfUrl(pdfViewUrl+viewName);
						   }
						   knowledgeAcc.setConversionCount(conversionCount);
						   if(conversionCount>5){
							   knowledgeAcc.setPdfCount(98);
						   }
//						   knowledgeAcc.setPdfUrl(pdfUrl);   
						   
					} catch (Exception e) {
						// TODO: handle exception
						knowledgeAcc.setPdfCount(98);
						 
					}
						
					}else{
						  knowledgeAcc.setPdfCount(99);
					}
					KnowledgeAcc knowledgeAccNew=knowledgeAccService.getById(knowledgeAcc.getKnowledgeAccId());
					if(knowledgeAccNew.getUploadFiles().equals(knowledgeAcc.getUploadFiles())){
						knowledgeAccService.update(knowledgeAcc);
					}
				}
				
			}
			if(attachments!=null&&attachments.size()>0){
				for(Attachment attachment:attachments){
					attachmentService.updatePdfCount(attachment.getAttachmentId());
				}
				for(Attachment attachment:attachments){
					String fileName=attachment.getAttachmentName();
					String type=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
					String pdfUrl=null;
					int conversionCount=attachment.getConversionCount()==null?0:attachment.getConversionCount();
					if(LabConstans.CONVERSION_TYPE.contains(type)){
						 JSONObject json = new JSONObject();
						 json.put("async", false);
						 json.put("filetype", type);
						 json.put("key", fileName.substring(0, 36));
						 json.put("outputtype", "pdf");
						 json.put("title", "LabWinner");
						 json.put("url", msgPath+fileName);
						 //json.put("url", "http://staticfile.labwinner.com/LabWinner/pdfs/6811144e-a3ef-4a4f-8deb-683a4b4dc7bf新材料研发平台用户指引手册1.pptx");
					try {
						 String result=otp.sendPost("http://118.31.18.178:8005/ConvertService.ashx",json.toString());
						   if(result.indexOf("<FileUrl>")>0){
							   pdfUrl=result.substring(result.indexOf("<FileUrl>")+9,result.indexOf("</FileUrl>"));
							   attachment.setPdfCount(2);
							   conversionCount++;
				           }else{
				        	   result=otp.sendPost("http://118.31.18.178:8005/ConvertService.ashx",json.toString());
				        	   if(result.indexOf("<FileUrl>")>0){
				        		   pdfUrl=result.substring(result.indexOf("<FileUrl>")+9,result.indexOf("</FileUrl>"));
				        		   attachment.setPdfCount(2);
				        		   conversionCount++;
				        	   }else{
				        		   attachment.setPdfCount(0);
				        		   conversionCount++;
				        	   }
				           }
						   if(pdfUrl!=null){
								 PdfUtil pu=new PdfUtil();
								 String viewName=pu.getPdfLoad("http://118.31.18.178:8005/2018-04-09-15-58/"+pdfUrl.substring(pdfUrl.indexOf("/cache")+1, pdfUrl.length()), pdfViewUpload);
								 attachment.setPdfUrl(pdfViewUrl+viewName);
						   }
						   attachment.setConversionCount(conversionCount);
						   if(conversionCount>5){
							   attachment.setPdfCount(98);
						   }
//						   knowledgeAcc.setPdfUrl(pdfUrl);   
						   
					} catch (Exception e) {
						// TODO: handle exception
						attachment.setPdfCount(98);
						 
					}
						
					}else{
						attachment.setPdfCount(99);
					}
					Attachment attachmentNew=attachmentService.getById(attachment.getAttachmentId());
					if(attachmentNew.getAttachmentName().equals(attachment.getAttachmentName())){
						attachmentService.update(attachment);
					}
				}
			}
			
			
			if(analyticalAttachments!=null&&analyticalAttachments.size()>0){
				for(AnalyticalAttachment analyticalAttachment:analyticalAttachments){
					analyticalAttachmentService.updatePdfCount(analyticalAttachment.getAnaAttachmentId());
				}
				for(AnalyticalAttachment analyticalAttachment:analyticalAttachments){
					String fileName=analyticalAttachment.getAnaAttachmentName();
					String type=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
					String pdfUrl=null;
					int conversionCount=analyticalAttachment.getConversionCount()==null?0:analyticalAttachment.getConversionCount();
					if(LabConstans.CONVERSION_TYPE.contains(type)){
						 JSONObject json = new JSONObject();
						 json.put("async", false);
						 json.put("filetype", type);
						 json.put("key", fileName.substring(0, 36));
						 json.put("outputtype", "pdf");
						 json.put("title", "LabWinner");
						 json.put("url", reactionPath+fileName);
						 //json.put("url", "http://staticfile.labwinner.com/LabWinner/pdfs/6811144e-a3ef-4a4f-8deb-683a4b4dc7bf新材料研发平台用户指引手册1.pptx");
					try {
						 String result=otp.sendPost("http://118.31.18.178:8005/ConvertService.ashx",json.toString());
						   if(result.indexOf("<FileUrl>")>0){
							   pdfUrl=result.substring(result.indexOf("<FileUrl>")+9,result.indexOf("</FileUrl>"));
							   analyticalAttachment.setPdfCount(2);
							   conversionCount++;
				           }else{
				        	   result=otp.sendPost("http://118.31.18.178:8005/ConvertService.ashx",json.toString());
				        	   if(result.indexOf("<FileUrl>")>0){
				        		   pdfUrl=result.substring(result.indexOf("<FileUrl>")+9,result.indexOf("</FileUrl>"));
				        		   analyticalAttachment.setPdfCount(2);
				        		   conversionCount++;
				        	   }else{
				        		   analyticalAttachment.setPdfCount(0);
				        		   conversionCount++;
				        	   }
				           }
						   if(pdfUrl!=null){
								 PdfUtil pu=new PdfUtil();
								 String viewName=pu.getPdfLoad("http://118.31.18.178:8005/2018-04-09-15-58/"+pdfUrl.substring(pdfUrl.indexOf("/cache")+1, pdfUrl.length()), pdfViewUpload);
								 analyticalAttachment.setPdfUrl(pdfViewUrl+viewName);
						   }
						   analyticalAttachment.setConversionCount(conversionCount);
						   if(conversionCount>5){
							   analyticalAttachment.setPdfCount(98);
						   }
//						   knowledgeAcc.setPdfUrl(pdfUrl);   
						   
					} catch (Exception e) {
						// TODO: handle exception
						analyticalAttachment.setPdfCount(98);
						 
					}
						
					}else{
						analyticalAttachment.setPdfCount(99);
					}
					AnalyticalAttachment analyticalAttachmentNew=analyticalAttachmentService.getByAttachmentId(analyticalAttachment.getAnaAttachmentId());
					if(analyticalAttachmentNew.getAnaAttachmentName().equals(analyticalAttachment.getAnaAttachmentName())){
						analyticalAttachmentService.update(analyticalAttachment);
					}
					
				}
			}
			if(projectDocuments!=null&&projectDocuments.size()>0){
				for(ProjectDocuments projectDocument:projectDocuments){
					projectDocumentsService.updatePdfCount(projectDocument.getProDocId());
				}
				for(ProjectDocuments projectDocument:projectDocuments){
					String fileName=projectDocument.getDocumentName();
					String type=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
					String pdfUrl=null;
					int conversionCount=projectDocument.getConversionCount()==null?0:projectDocument.getConversionCount();
					if(LabConstans.CONVERSION_TYPE.contains(type)){
						 JSONObject json = new JSONObject();
						 json.put("async", false);
						 json.put("filetype", type);
						 json.put("key", fileName.substring(0, 36));
						 json.put("outputtype", "pdf");
						 json.put("title", "LabWinner");
						 json.put("url", documentsPath+fileName);
						 //json.put("url", "http://staticfile.labwinner.com/LabWinner/pdfs/6811144e-a3ef-4a4f-8deb-683a4b4dc7bf新材料研发平台用户指引手册1.pptx");
					try {
						 String result=otp.sendPost("http://118.31.18.178:8005/ConvertService.ashx",json.toString());
						   if(result.indexOf("<FileUrl>")>0){
							   pdfUrl=result.substring(result.indexOf("<FileUrl>")+9,result.indexOf("</FileUrl>"));
							   projectDocument.setPdfCount(2);
							   conversionCount++;
				           }else{
				        	   result=otp.sendPost("http://118.31.18.178:8005/ConvertService.ashx",json.toString());
				        	   if(result.indexOf("<FileUrl>")>0){
				        		   pdfUrl=result.substring(result.indexOf("<FileUrl>")+9,result.indexOf("</FileUrl>"));
				        		   projectDocument.setPdfCount(2);
				        		   conversionCount++;
				        	   }else{
				        		   projectDocument.setPdfCount(0);
				        		   conversionCount++;
				        	   }
				           }
						   if(pdfUrl!=null){
								 PdfUtil pu=new PdfUtil();
								 String viewName=pu.getPdfLoad("http://118.31.18.178:8005/2018-04-09-15-58/"+pdfUrl.substring(pdfUrl.indexOf("/cache")+1, pdfUrl.length()), pdfViewUpload);
								 projectDocument.setPdfUrl(pdfViewUrl+viewName);
						   }
						   projectDocument.setConversionCount(conversionCount);
						   if(conversionCount>5){
							   projectDocument.setPdfCount(98);
						   }
//						   knowledgeAcc.setPdfUrl(pdfUrl);   
						   
					} catch (Exception e) {
						// TODO: handle exception
						projectDocument.setPdfCount(98);
						 
					}
						
					}else{
						projectDocument.setPdfCount(99);
					}
					ProjectDocuments projectDocumentNew=projectDocumentsService.getById(projectDocument.getProDocId());
					if(projectDocumentNew.getDocumentName().equals(projectDocument.getDocumentName())){
						projectDocumentsService.update(projectDocument);	
					}
					
				}
			}
			return resultVo;
			
		}
	 
		
		/**
		 * @Description 根据实验主键获取对象
		 * @author xux
		 * @version V1.0
		 * @throws Exception 
		 * @date 2017年5月18日 上午11:50:28
		 */
		@Scheduled(cron="0 0 9 * * ?") //每天9点触发
		public ResultVo remindClendarUnFinish() {
			ResultVo resultVo = new ResultVo();
			List<CalendarAssist> calendarAssists=calendarAssistService.getAllUnFinish();
			
			if(calendarAssists!=null&&calendarAssists.size()>0){
				for(CalendarAssist calendarAssist:calendarAssists){
					List<Integer> userList=new ArrayList<Integer>();
					if(calendarAssist.getAssistStatus().getAssistStatusId()==1){
						userList.add(calendarAssist.getSysUser().getUserId());
					}
					if(userList!=null && userList.size()>0){
						//DeviceAppointmentController deviceAppointmentController=new DeviceAppointmentController();
					String	content="[任务提醒] "+calendarAssist.getCalendars().getTitle()+"还没完成，请尽快处理";
					Message message=new Message();
						message.setMessageContent(content);
						JPushData pushData =getJPushData(message,userList);
						Map<String, Integer> extraMap=new HashMap<String, Integer>();
						extraMap.put("type", LabConstans.CALENDARS_TYPE);
						extraMap.put("userId", calendarAssist.getSysUser().getUserId());
						extraMap.put("messageId", calendarAssist.getCalendars().getCalendarId());
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
				}
			}
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			return resultVo;
		}
		
		
		
		@RequestMapping(value = "/api/test", method = RequestMethod.GET)
	    public ResultVo findTest() {
			ResultVo res=new ResultVo();
			Attachment attachmentNew=attachmentService.getById(171);
			ProjectDocuments projectDocumentNew=projectDocumentsService.getById(3);
			AnalyticalAttachment analyticalAttachmentNew=analyticalAttachmentService.getByAttachmentId(13);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("attachmentNew", attachmentNew);
			map.put("projectDocumentNew", projectDocumentNew);
			map.put("analyticalAttachmentNew", analyticalAttachmentNew);
			res.setResultData(map);
			return res;
	   
	    }
	 
	
	
	
	

}
