package com.labwinner.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Attachment;
import com.labwinner.domain.Calendars;
import com.labwinner.domain.CommentEntity;
import com.labwinner.domain.Expert;
import com.labwinner.domain.ExpertAssist;
import com.labwinner.domain.MarketAssist;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.MsgDetailtype;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.MsgType;
import com.labwinner.domain.ProjectAssist;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectNumber;
import com.labwinner.domain.ProjectPicture;
import com.labwinner.domain.SysUser;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
import com.labwinner.service.AssistCommentService;
import com.labwinner.service.AttachmentService;
import com.labwinner.service.CalendarsService;
import com.labwinner.service.CommentService;
import com.labwinner.service.ExpertAssistService;
import com.labwinner.service.ExpertService;
import com.labwinner.service.MarketAssistService;
import com.labwinner.service.MessageRecipientsService;
import com.labwinner.service.MessageService;
import com.labwinner.service.ProjectAssistService;
import com.labwinner.service.ProjectNumberService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.Base64Util;
import com.labwinner.util.BeanToMapUtil;
import com.labwinner.util.FileUtil;
import com.labwinner.vo.MessageVo;
import com.labwinner.vo.UnreadMessageVo;

@RestController
public class MessageController {

	private static Logger logger = LoggerFactory
			.getLogger(MessageController.class);
	
	

	@Autowired
	private MessageService messageService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private MessageRecipientsService messageRecipientsService;

	@Autowired
	SysUserService sysUserService;
	
	@Autowired
	private ExpertAssistService expertAssistService;
	@Autowired
	private ExpertService expertService;
	
	@Autowired
	private ProjectAssistService projectAssistService;
	
	@Autowired
	private AssistCommentService assistCommentService;
	
	@Autowired
	private ProjectNumberService projectNumberService;
	
	@Autowired
	private CalendarsService calendarsService;
	
	@Autowired
	private MarketAssistService marketAssistService;
	
	@Autowired
	private CommentService commentService;

	// 注入随手记极光推送
	@Autowired
	private Jpush jpush;

	@Value("${web.msgFile-path}")
	String filePath;
	@Value("${web.msgFileUrl-path}")
	private String urlPath;
	@Value("${sysUserPhone.url-path}")
	private String imagePath;
	@Value("${sysUserPhone.url-path}")
	private String userImageUrl;
	@Value("${projectPicture.url-path}")
	private String proImageUrl;

	/**
	 * @Description 获取详细信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/{id}/{number}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id,
			@PathVariable("number") Integer number) {
		ResultVo resultVo = new ResultVo();

		FileUtil fileUtil = new FileUtil();

		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();

		if (number == 1 || number == 3 || number == 4) {
			Message message = messageService.getByReceiverId(id, userId);
			if (message != null) {
				// 点击详情后成已读
			
//				message.setMsgState(msgState);
//				messageService.updateState(message);
				if(message.getMsgDetailtype()!=null){
					if(message.getMsgDetailtype().getMsgDetailtypeId()==2){
						String content=message.getMessageContent();
						String[] ss=content.split("@#&");
						content="今日已完成工作："+ss[0]+"\n";
						content=content+"今日未完成工作："+ss[1]+"\n";
						content=content+"需协调工作："+ss[2]+"\n";
						content=content+"备注："+ss[3]+"\n";
						message.setMessageContent(content);
					}
					if(message.getMsgDetailtype().getMsgDetailtypeId()==3){
						String content=message.getMessageContent();
						String[] ss=content.split("@#&");
						content="本周完成工作："+ss[0]+"\n";
						content=content+"本周工作总结："+ss[1]+"\n";
						content=content+"下周工作计划："+ss[2]+"\n";
						content=content+"需协调工作："+ss[3]+"\n";
						content=content+"备注："+ss[4]+"\n";
						message.setMessageContent(content);
					}
				}
				SysUser sysUser1 = message.getSysUser();
				if(sysUser1.getUserImage()!=null){
					sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
				}
				List<Attachment> attachments=attachmentService.getByMessageId(message.getMessageId());
				List<Attachment> attachmentRes=new ArrayList<Attachment>();
				if (attachments!= null && attachments.size() > 0) {
					for (Attachment attachment : attachments) {
						String fileName = attachment.getAttachmentName();
						String type=fileName.substring(fileName.lastIndexOf(".") + 1);
						attachment.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
						attachment.setAttachmentName(fileName);
						attachment.setUrlPath(urlPath + fileName);
						attachment.setSize(fileUtil.getFileSize(filePath+ fileName));
						String pdfUrl=null;
						if("pdf".equals(type)){
							pdfUrl=urlPath + fileName;
						}else{
							pdfUrl=attachment.getPdfUrl();
						}
						attachment.setPdfUrl(pdfUrl);
						attachmentRes.add(attachment);
					}
				}
				message.setAttachments(attachmentRes);
				List<MessageRecipients> messageRecipientses = message.getMessageRecipientses();
				
				if (messageRecipientses!= null && messageRecipientses.size() > 0) {
					for (MessageRecipients messageRecipient : messageRecipientses) {
						MsgState msgState = new MsgState();
						msgState.setMsgId(2);
						msgState.setMsgState("已读");
						messageRecipient.setMsgState(msgState);
						messageRecipientsService.update(messageRecipient);
						SysUser sysUser2 = messageRecipient.getSysUser();
						if(sysUser2.getUserImage()!=null){
							sysUser2.setUserImage(userImageUrl+sysUser2.getUserImage());
						}
					}
				}
				
				resultVo.setErrCode(0);
				resultVo.setErrMsg("get message success");
				resultVo.setResultData(message);
				return resultVo;
			}else{
				resultVo.setErrCode(1);
				resultVo.setErrMsg("message is null");
				return resultVo;
			}
			

		} else if (number == 2) {
			Message message = messageService.getById(id);
			if (message != null) {
				SysUser sysUser1 = message.getSysUser();
				if(sysUser1.getUserImage()!=null){
					sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
				}
				
				if(message.getMsgDetailtype()!=null){
					if(message.getMsgDetailtype().getMsgDetailtypeId()==2){
						String content=message.getMessageContent();
						String[] ss=content.split("@#&");
						content="今日已完成工作："+ss[0]+"\n";
						content=content+"今日未完成工作："+ss[1]+"\n";
						content=content+"需协调工作："+ss[2]+"\n";
						content=content+"备注："+ss[3]+"\n";
						message.setMessageContent(content);
					}
					if(message.getMsgDetailtype().getMsgDetailtypeId()==3){
						String content=message.getMessageContent();
						String[] ss=content.split("@#&");
						content="本周完成工作："+ss[0]+"\n";
						content=content+"本周工作总结："+ss[1]+"\n";
						content=content+"下周工作计划："+ss[2]+"\n";
						content=content+"需协调工作："+ss[3]+"\n";
						content=content+"备注："+ss[4]+"\n";
						message.setMessageContent(content);
					}
				}
				
//				List<Attachment> attachments = message.getAttachments();
//				if (attachments!= null && attachments.size() > 0) {
//					for (Attachment attachment : attachments) {
//						String fileName = attachment.getAttachmentName();
//						attachment.setFileType(fileName.substring(fileName
//								.lastIndexOf(".") + 1));
//						attachment.setAttachmentName(fileName);
//						attachment.setUrlPath(urlPath + fileName);
//						attachment.setSize(fileUtil
//								.getFileSize(filePath + fileName));
//					}
//				}
				List<Attachment> attachments=attachmentService.getByMessageId(message.getMessageId());
				List<Attachment> attachmentRes=new ArrayList<Attachment>();
				if (attachments!= null && attachments.size() > 0) {
					for (Attachment attachment : attachments) {
						String fileName = attachment.getAttachmentName();
						String type=fileName.substring(fileName.lastIndexOf(".") + 1);
						attachment.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
						attachment.setAttachmentName(fileName);
						attachment.setUrlPath(urlPath + fileName);
						attachment.setSize(fileUtil.getFileSize(filePath+ fileName));
						String pdfUrl=null;
						if("pdf".equals(type)){
							pdfUrl=urlPath + fileName;
						}else{
							pdfUrl=attachment.getPdfUrl();
						}
						attachment.setPdfUrl(pdfUrl);
						attachmentRes.add(attachment);
					}
				}
				message.setAttachments(attachmentRes);
				List<MessageRecipients> messageRecipientses = message.getMessageRecipientses();
				
				if (messageRecipientses!= null && messageRecipientses.size() > 0) {
					for (MessageRecipients messageRecipient : messageRecipientses) {
						MsgState msgState = new MsgState();
						msgState.setMsgId(2);
						msgState.setMsgState("已读");
						messageRecipient.setMsgState(msgState);
						messageRecipientsService.update(messageRecipient);
						SysUser sysUser2 = messageRecipient.getSysUser();
						if(sysUser2.getUserImage()!=null){
							sysUser2.setUserImage(userImageUrl+sysUser2.getUserImage());
						}
					}
				}
				
				resultVo.setErrCode(0);
				resultVo.setErrMsg("get message success");
				resultVo.setResultData(message);
				return resultVo;
			}else{
				resultVo.setErrCode(1);
				resultVo.setErrMsg("message is null");
				return resultVo;
			}
		} else {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("get message success");
			return resultVo;
		}
	}

	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/message/getAllByDay/{date}", method = RequestMethod.GET)
	public ResultVo getAllByDay(@PathVariable("date") String date) {
		ResultVo resultVo = new ResultVo();
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();

        List<MessageRecipients> list = messageRecipientsService.getByUserId(userId);
		
        MessageRecipients messageRecipients2=new MessageRecipients();
		List<MessageRecipients> list2=new ArrayList<MessageRecipients>();
        
		List<Object> dates=new ArrayList<Object>();
		
		if(list!=null && list.size()>0){
			for (MessageRecipients messageRecipients : list) {
				Message message=messageRecipients.getMessage();
				MsgState msgState=messageRecipients.getMsgState();
				SysUser sysUser1=messageRecipients.getSysUser();
				if(message!=null){
					List<Attachment> attachments=message.getAttachments();
					
							String content=message.getMessageContent();
							String[] ss=content.split("@#&");
							content="今日已完成工作："+ss[0]+"\n";
							content=content+"今日未完成工作："+ss[1]+"\n";
							content=content+"需协调工作："+ss[2]+"\n";
							content=content+"备注："+ss[3]+"\n";
							message.setMessageContent(content);
						
					Date date1=message.getSenderDate();
						
					SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
			        String date2 = sdf.format(date1);

					if(date.equals(date2)){
						if(message.getSysUser().getUserImage()!=null){
							message.getSysUser().setUserImage(userImageUrl+message.getSysUser().getUserImage());
						}
						
						
						messageRecipients2.setSysUser(sysUser1);
						
						
						
						messageRecipients2.setMessage(message);
						if(sysUser1.getUserImage()!=null){
							sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
						}
						messageRecipients2.setSysUser(sysUser1);
						
						if(attachments!=null && attachments.size()>0){
							for (Attachment attachment : attachments) {
								attachment.setAttachmentName(urlPath+attachment.getAttachmentName());
								message.setAttachments(attachments);;	
							}
						}
						messageRecipients2.setSysUser(sysUser1);
						if(msgState!=null){
							messageRecipients2.setMsgState(msgState);
						}
						list2.add(messageRecipients2);
					}
					    dates.add(date2);
					    for  ( int  i  =   0 ; i  <  dates.size()  -   1 ; i ++ )  {       
					        for  ( int  j  =  dates.size()  -   1 ; j  >  i; j -- )  {       
					             if  (dates.get(j).equals(dates.get(i)))  {       
					            	 dates.remove(j);       
					              }        
					          }        
					        }            
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultList(dates);
			resultVo.setResultData(list2);
			return resultVo;
		}else {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find null");
			resultVo.setResultData(null);
			return resultVo;
		}

	  }
	
	/**
	 * 根据type获取所有日报对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/messageDetails/getAllByDayAndType/{date}/{type}/{dtype}", method = RequestMethod.GET)
	public ResultVo getDetailsByDayAndType(@PathVariable("date") String date,
			@PathVariable("type") Integer type,@PathVariable("dtype") Integer dtype) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		//我收到的日报MessageRecipients
		List<MessageRecipients> list2=new ArrayList<MessageRecipients>();
		
		//我收到的日报Message
		List<Message> list3=new ArrayList<Message>();
		
		//我发出的日报Message
		List<Message> list4=new ArrayList<Message>();
		
		//List<Message> list = messageService.getAllByDayAndType();
		List<Object> dates=new ArrayList<Object>();

		//dtype=1是日报，dtype=2是周报
		if(dtype!=null && dtype==1){
			//type=1是收到的日报，type=2是我发出的日报
			  if(type!=null && type==1){
				  List<Message> messages1=messageService.getMessageByUserId(userId);
				 for (Message message : messages1) {
					 Date date1=message.getSenderDate();
						SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
						String date2 = sdf.format(date1);
						dates.add(date2);
						for  ( int  i  =   0 ; i  <  dates.size()  -   1 ; i ++ )  {       
							for  ( int  j  =  dates.size()  -   1 ; j  >  i; j -- )  {       
								if  (dates.get(j).equals(dates.get(i)))  {       
									dates.remove(j);       
								}        
							}        
						}   
					 if(date.equals(date2)){
						  
					 if(message.getSysUser().getUserImage()!=null){
							message.getSysUser().setUserImage(userImageUrl+message.getSysUser().getUserImage());
						}
					List<Attachment> attachments=message.getAttachments();
					
					if(attachments!=null && attachments.size()>0){
						for (Attachment attachment : attachments) {
							attachment.setAttachmentName(urlPath+attachment.getAttachmentName());
							message.setAttachments(attachments);;	
						}
					}	
					List<MessageRecipients> messageRecipientses=message.getMessageRecipientses();
					if(messageRecipientses!=null&&messageRecipientses.size()>0){
						for (MessageRecipients messageRecipients: messageRecipientses) {
							MsgState msgState = new MsgState();
							msgState.setMsgId(2);
							msgState.setMsgState("已读");
							messageRecipients.setMsgState(msgState);
							messageRecipientsService.update(messageRecipients);
	
							if(messageRecipients.getSysUser().getUserImage()!=null){
								messageRecipients.getSysUser().setUserImage(userImageUrl+messageRecipients.getSysUser().getUserImage());
							}
							}
						}

					 list3.add(message);	
				     }
				 } 
				     Map<String, Object> resMap=new HashMap<String, Object>();
				     if(list3!=null && list3.size()>0){
				     Message message1 = list3.get(0);
				     
				     if(message1!=null){
				    	 
				    	 List<CommentEntity> commentEntities = commentService.getByModuleId(message1.getMessageId(), 2);
				    	
				    	 for (CommentEntity commentEntity : commentEntities) {
				    		 SysUser sysUser1 = commentEntity.getSysUser();
								if(sysUser1.getUserImage()!=null){
									sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
									commentEntity.setSysUser(sysUser1);
								} 
						}
				    	 
				    	 message1.setCommentEntities(commentEntities);
 
				    	 String messageContent=message1.getMessageContent();
							String[] contents=messageContent.split("@#&");
							String firstContent="";
							String secondContent="";
							String thirdContent="";
							String fouthContent="";
							String remark="";
							if(dtype!=null && dtype==1){
								 firstContent=contents[0];
								 secondContent=contents[1];
								 thirdContent=contents[2];
								 remark=contents[3];
							}else if (dtype!=null && dtype==2){
								 firstContent=contents[0];
								 secondContent=contents[1];
								 thirdContent=contents[2];
								 fouthContent=contents[3];
								 remark=contents[4];
							}

							Integer mgsUserId = message1.getSysUser().getUserId();
							List<Integer> msgIdList = messageService.getDayPaperByUser(mgsUserId,userId);
							if(msgIdList.size()<=1){
								resMap.put("isFirst", "true");
								resMap.put("isLast","true");	
							}else{
								Integer thisOne = msgIdList.indexOf(message1.getMessageId());
								if(thisOne==0){
									resMap.put("lastone", msgIdList.get(1));
									resMap.put("isFirst", "true");
									resMap.put("isLast","false");
								}else if(thisOne ==msgIdList.size()-1 ){
									resMap.put("next", msgIdList.get(thisOne-1));
									resMap.put("isLast","true");
									resMap.put("isFirst", "false");
								}else{
									resMap.put("lastone", msgIdList.get(thisOne+1));
									resMap.put("next", msgIdList.get(thisOne-1));
									resMap.put("isLast","false");
									resMap.put("isFirst", "false");
								}
							}
							//type=2是周报
						resMap.put("message", message1);
						resMap.put("firstContent", firstContent);
						resMap.put("secondContent", secondContent);
						resMap.put("thirdContent", thirdContent);
						resMap.put("fouthContent", fouthContent);
						resMap.put("remark", remark);
						resultVo.setErrCode(0);
						resultVo.setResultList(dates);
						resultVo.setErrMsg("get message success");
						resultVo.setResultData(resMap);
						return resultVo;
				     }
				     }else {
				    	 resultVo.setErrCode(2);
						resultVo.setResultList(dates);
						resultVo.setErrMsg("get message null");
						resultVo.setResultData(null);
						return resultVo;
					}
					//type=1是收到的日报，type=2是我发出的日报
			}else if(type!=null && type==2){
				 List<Message> messages2=messageService.getMessageGiveByUserId(userId);
				
				for (Message message : messages2) {
					 Date date1=message.getSenderDate();
						SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
						String date2 = sdf.format(date1);
						dates.add(date2);
						for  ( int  i  =   0 ; i  <  dates.size()  -   1 ; i ++ )  {       
							for  ( int  j  =  dates.size()  -   1 ; j  >  i; j -- )  {       
								if  (dates.get(j).equals(dates.get(i)))  {       
									dates.remove(j);       
								}        
							}        
						}   
						 if(date.equals(date2)){
					
					
					if(message.getSysUser().getUserImage()!=null){
						message.getSysUser().setUserImage(userImageUrl+message.getSysUser().getUserImage());
					}
		            List<Attachment> attachments=message.getAttachments();
					
					if(attachments!=null && attachments.size()>0){
						for (Attachment attachment : attachments) {
							attachment.setAttachmentName(urlPath+attachment.getAttachmentName());
							message.setAttachments(attachments);;	
						}
					}
						
					List<MessageRecipients> messageRecipientses=message.getMessageRecipientses();
					if(messageRecipientses!=null&&messageRecipientses.size()>0){
						for (MessageRecipients messageRecipients: messageRecipientses) {
							MsgState msgState = new MsgState();
							msgState.setMsgId(2);
							msgState.setMsgState("已读");
							messageRecipients.setMsgState(msgState);
							messageRecipientsService.update(messageRecipients);
							if(messageRecipients.getSysUser().getUserImage()!=null){
								messageRecipients.getSysUser().setUserImage(userImageUrl+messageRecipients.getSysUser().getUserImage());
							}
						      }
					     
					      }
								list4.add(message);
						}
					}
				
				
				Map<String, Object> resMap=new HashMap<String, Object>();
				
				if(list4!=null && list4.size()>0){
				 Message message1 = list4.get(0);
				 
				
			     if(message1!=null){

			    	 List<CommentEntity> commentEntities = commentService.getByModuleId(message1.getMessageId(), 2);
			    	 for (CommentEntity commentEntity : commentEntities) {
			    		 SysUser sysUser1 = commentEntity.getSysUser();
							if(sysUser1.getUserImage()!=null){
								sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
								commentEntity.setSysUser(sysUser1);
							} 
					}  
			    	 message1.setCommentEntities(commentEntities);
			    	 
			    	 String messageContent=message1.getMessageContent();
						String[] contents=messageContent.split("@#&");
						String firstContent="";
						String secondContent="";
						String thirdContent="";
						String fouthContent="";
						String remark="";
						if(dtype!=null && dtype==1){
							 firstContent=contents[0];
							 secondContent=contents[1];
							 thirdContent=contents[2];
							 remark=contents[3];
						}else if (dtype!=null && dtype==2){
							 firstContent=contents[0];
							 secondContent=contents[1];
							 thirdContent=contents[2];
							 fouthContent=contents[3];
							 remark=contents[4];
						}

						Integer mgsUserId = message1.getSysUser().getUserId();
						List<Integer> msgIdList = messageService.getMySendDayPaper(userId);
						if(msgIdList.size()<=1){
							resMap.put("isFirst", "true");
							resMap.put("isLast","true");	
						}else{
							Integer thisOne = msgIdList.indexOf(message1.getMessageId());
							if(thisOne==0){
								resMap.put("lastone", msgIdList.get(1));
								resMap.put("isFirst", "true");
								resMap.put("isLast","false");
							}else if(thisOne ==msgIdList.size()-1 ){
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","true");
								resMap.put("isFirst", "false");
							}else{
								resMap.put("lastone", msgIdList.get(thisOne+1));
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","false");
								resMap.put("isFirst", "false");
							}
						}
						//type=2是周报
					resMap.put("message", message1);
					resMap.put("firstContent", firstContent);
					resMap.put("secondContent", secondContent);
					resMap.put("thirdContent", thirdContent);
					resMap.put("fouthContent", fouthContent);
					resMap.put("remark", remark);
					resultVo.setErrCode(0);
					resultVo.setResultList(dates);
					resultVo.setErrMsg("get message success");
					resultVo.setResultData(resMap);
					return resultVo;
			       }
			     }else {
			    	resultVo.setErrCode(2);
					resultVo.setResultList(dates);
					resultVo.setErrMsg("get message null");
					resultVo.setResultData(null);
					return resultVo;
				}
			}
			//dtype=1是日报，dtype=2是周报
		}else if (dtype!=null && dtype==2) {
			//type=1是收到的周报，type=2是我发出的周报
			if(type!=null && type==1){
				List<Message> messages1=messageService.getMessageWeekByUserId(userId);
				for (Message message : messages1) {
					 Date date1=message.getSenderDate();
						SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
						String date2 = sdf.format(date1);
						dates.add(date2);
						for  ( int  i  =   0 ; i  <  dates.size()  -   1 ; i ++ )  {       
							for  ( int  j  =  dates.size()  -   1 ; j  >  i; j -- )  {       
								if  (dates.get(j).equals(dates.get(i)))  {       
									dates.remove(j);       
								}        
							}        
						}   
						 if(date.equals(date2)){
					
					
					
					if(message.getSysUser().getUserImage()!=null){
						message.getSysUser().setUserImage(userImageUrl+message.getSysUser().getUserImage());
					}
					List<Attachment> attachments=message.getAttachments();
					
					if(attachments!=null && attachments.size()>0){
						for (Attachment attachment : attachments) {
							attachment.setAttachmentName(urlPath+attachment.getAttachmentName());
							message.setAttachments(attachments);;	
						}
					}	
					List<MessageRecipients> messageRecipientses=message.getMessageRecipientses();
					if(messageRecipientses!=null&&messageRecipientses.size()>0){
						for (MessageRecipients messageRecipients: messageRecipientses) {
							MsgState msgState = new MsgState();
							msgState.setMsgId(2);
							msgState.setMsgState("已读");
							messageRecipients.setMsgState(msgState);
							messageRecipientsService.update(messageRecipients);
							
							if(messageRecipients.getSysUser().getUserImage()!=null){
								messageRecipients.getSysUser().setUserImage(userImageUrl+messageRecipients.getSysUser().getUserImage());
							}	
						}
					}
					
						list3.add(message);	
					}
					
				}
				 
				 Map<String, Object> resMap=new HashMap<String, Object>();
				 if(list3!=null && list3.size()>0){
				 Message message1 = list3.get(0);
			     if(message1!=null){
			    	 
			    	 List<CommentEntity> commentEntities = commentService.getByModuleId(message1.getMessageId(), 2);
			    	 for (CommentEntity commentEntity : commentEntities) {
			    		 SysUser sysUser1 = commentEntity.getSysUser();
							if(sysUser1.getUserImage()!=null){
								sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
								commentEntity.setSysUser(sysUser1);
							} 
					}  
			    	 message1.setCommentEntities(commentEntities);
			    	 
			    	 String messageContent=message1.getMessageContent();
						String[] contents=messageContent.split("@#&");
						String firstContent="";
						String secondContent="";
						String thirdContent="";
						String fouthContent="";
						String remark="";
						if(dtype!=null && dtype==1){
							 firstContent=contents[0];
							 secondContent=contents[1];
							 thirdContent=contents[2];
							 remark=contents[3];
						}else if (dtype!=null && dtype==2){
							 firstContent=contents[0];
							 secondContent=contents[1];
							 thirdContent=contents[2];
							 fouthContent=contents[3];
							 remark=contents[4];
						}

						Integer mgsUserId = message1.getSysUser().getUserId();
						List<Integer> msgIdList = messageService.getWeekDayPaperByUser(mgsUserId, userId);
						if(msgIdList.size()<=1){
							resMap.put("isFirst", "true");
							resMap.put("isLast","true");	
						}else{
							Integer thisOne = msgIdList.indexOf(message1.getMessageId());
							if(thisOne==0){
								resMap.put("lastone", msgIdList.get(1));
								resMap.put("isFirst", "true");
								resMap.put("isLast","false");
							}else if(thisOne ==msgIdList.size()-1 ){
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","true");
								resMap.put("isFirst", "false");
							}else{
								resMap.put("lastone", msgIdList.get(thisOne+1));
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","false");
								resMap.put("isFirst", "false");
							}
						}
						//type=2是周报
					resMap.put("message", message1);
					resMap.put("firstContent", firstContent);
					resMap.put("secondContent", secondContent);
					resMap.put("thirdContent", thirdContent);
					resMap.put("fouthContent", fouthContent);
					resMap.put("remark", remark);
					resultVo.setErrCode(0);
					resultVo.setResultList(dates);
					resultVo.setErrMsg("get message success");
					resultVo.setResultData(resMap);
					return resultVo;
			     }
				 }else {
					resultVo.setErrCode(2);
					resultVo.setResultList(dates);
					resultVo.setErrMsg("get message null");
					resultVo.setResultData(null);
					return resultVo;
				}
				
				//type=1是收到的周报，type=2是我发出的周报
			}else if(type!=null && type==2){
				List<Message> messages2=messageService.getMessageWeekGiveByUserId(userId);
				
				for (Message message : messages2) {
					 Date date1=message.getSenderDate();
						SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
						String date2 = sdf.format(date1);
						dates.add(date2);
						for  ( int  i  =   0 ; i  <  dates.size()  -   1 ; i ++ )  {       
							for  ( int  j  =  dates.size()  -   1 ; j  >  i; j -- )  {       
								if  (dates.get(j).equals(dates.get(i)))  {       
									dates.remove(j);       
								}        
							}        
						}   
						if(date.equals(date2)){
							
							
					if(message.getSysUser().getUserImage()!=null){
						message.getSysUser().setUserImage(userImageUrl+message.getSysUser().getUserImage());
					}
					List<Attachment> attachments=message.getAttachments();
					
					if(attachments!=null && attachments.size()>0){
						for (Attachment attachment : attachments) {
							attachment.setAttachmentName(urlPath+attachment.getAttachmentName());
							message.setAttachments(attachments);;	
						}
					}
					List<MessageRecipients> messageRecipientses=message.getMessageRecipientses();
					if(messageRecipientses!=null&&messageRecipientses.size()>0){
						for (MessageRecipients messageRecipients: messageRecipientses) {
							MsgState msgState = new MsgState();
							msgState.setMsgId(2);
							msgState.setMsgState("已读");
							messageRecipients.setMsgState(msgState);
							messageRecipientsService.update(messageRecipients);
							if(messageRecipients.getSysUser().getUserImage()!=null){
								messageRecipients.getSysUser().setUserImage(userImageUrl+messageRecipients.getSysUser().getUserImage());
							}	
						}
					}
						list4.add(message);
						}
					}
				
				 Map<String, Object> resMap=new HashMap<String, Object>();
				 if(list4!=null && list4.size()>0){
				 Message message1 = list4.get(0);
			     if(message1!=null){

			    	 List<CommentEntity> commentEntities = commentService.getByModuleId(message1.getMessageId(), 2);
			    	 for (CommentEntity commentEntity : commentEntities) {
			    		 SysUser sysUser1 = commentEntity.getSysUser();
							if(sysUser1.getUserImage()!=null){
								sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
								commentEntity.setSysUser(sysUser1);
							} 
					}  
			    	 message1.setCommentEntities(commentEntities);
			    	 
			    	 String messageContent=message1.getMessageContent();
						String[] contents=messageContent.split("@#&");
						String firstContent="";
						String secondContent="";
						String thirdContent="";
						String fouthContent="";
						String remark="";
						if(dtype!=null && dtype==1){
							 firstContent=contents[0];
							 secondContent=contents[1];
							 thirdContent=contents[2];
							 remark=contents[3];
						}else if (dtype!=null && dtype==2){
							 firstContent=contents[0];
							 secondContent=contents[1];
							 thirdContent=contents[2];
							 fouthContent=contents[3];
							 remark=contents[4];
						}

						Integer mgsUserId = message1.getSysUser().getUserId();
					
						List<Integer> msgIdList = messageService.getWeekMySendDayPaper(userId);
						if(msgIdList.size()<=1){
							resMap.put("isFirst", "true");
							resMap.put("isLast","true");	
						}else{
							Integer thisOne = msgIdList.indexOf(message1.getMessageId());
							if(thisOne==0){
								resMap.put("lastone", msgIdList.get(1));
								resMap.put("isFirst", "true");
								resMap.put("isLast","false");
							}else if(thisOne ==msgIdList.size()-1 ){
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","true");
								resMap.put("isFirst", "false");
							}else{
								resMap.put("lastone", msgIdList.get(thisOne+1));
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","false");
								resMap.put("isFirst", "false");
							}
						}
						
					resMap.put("message", message1);
					resMap.put("firstContent", firstContent);
					resMap.put("secondContent", secondContent);
					resMap.put("thirdContent", thirdContent);
					resMap.put("fouthContent", fouthContent);
					resMap.put("remark", remark);
					resultVo.setErrCode(0);
					resultVo.setResultList(dates);
					resultVo.setErrMsg("get message success");
					resultVo.setResultData(resMap);
					return resultVo;
			       }
			     }else {
			    	resultVo.setErrCode(2);
					resultVo.setResultList(dates);
					resultVo.setErrMsg("get message null");
					resultVo.setResultData(null);
					return resultVo;
				}
				 }
		}
	
		
		return resultVo;
		}
	
	
	
	
	/**
	 * 根据type获取所有日报对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/message/getAllByDayAndType/{date}/{type}", method = RequestMethod.GET)
	public ResultVo getAllByDayAndType(@PathVariable("date") String date,@PathVariable("type") Integer type) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		//我收到的日报MessageRecipients
		List<MessageRecipients> list2=new ArrayList<MessageRecipients>();
		
		//我收到的日报Message
		List<Message> list3=new ArrayList<Message>();
		
		//我发出的日报Message
		List<Message> list4=new ArrayList<Message>();
		
		//List<Message> list = messageService.getAllByDayAndType();
		List<Object> dates=new ArrayList<Object>();

		//type=1是收到的日报，type=2是我发出的日报
		  if(type!=null && type==1){
			  List<Message> messages1=messageService.getMessageByUserId(userId);
			 for (Message message : messages1) {
				 SysUser sysUser1 = message.getSysUser();
					if(sysUser1!=null){
						if(sysUser1.getUserImage()!=null && !"".equals(sysUser1.getUserImage())){
							sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
							message.setSysUser(sysUser1);
						} 
					}
				List<Attachment> attachments=message.getAttachments();
				
				if(attachments!=null && attachments.size()>0){
					for (Attachment attachment : attachments) {
						attachment.setAttachmentName(urlPath+attachment.getAttachmentName());
						message.setAttachments(attachments);;	
					}
				}
				String content=message.getMessageContent();
				String[] ss=content.split("@#&");
				content="今日已完成工作："+ss[0]+"\n";
				content=content+"今日未完成工作："+ss[1]+"\n";
				content=content+"需协调工作："+ss[2]+"\n";
				content=content+"备注："+ss[3]+"\n";
				message.setMessageContent(content);
				
				List<MessageRecipients> messageRecipientses=message.getMessageRecipientses();
				if(messageRecipientses!=null&&messageRecipientses.size()>0){
					for (MessageRecipients messageRecipients: messageRecipientses) {
						SysUser sysUser2 = messageRecipients.getSysUser();
						if(sysUser2!=null){
							if(sysUser2.getUserImage()!=null && !"".equals(sysUser2.getUserImage())){
								sysUser2.setUserImage(userImageUrl+sysUser2.getUserImage());
								messageRecipients.setSysUser(sysUser2);
							} 
						}	
						}
					}
				Date date1=message.getSenderDate();
				SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
				String date2 = sdf.format(date1);
				
				dates.add(date2);
				for  ( int  i  =   0 ; i  <  dates.size()  -   1 ; i ++ )  {       
					for  ( int  j  =  dates.size()  -   1 ; j  >  i; j -- )  {       
						if  (dates.get(j).equals(dates.get(i)))  {       
							dates.remove(j);       
						}        
					}        
				}     

				if(date.equals(date2)){
				    list3.add(message);	
			     }
				
			 }
			    resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultList(dates);
				resultVo.setResultData(list3);
				return resultVo;
			
				//type=1是收到的日报，type=2是我发出的日报
		}else if(type!=null && type==2){
			 List<Message> messages2=messageService.getMessageGiveByUserId(userId);
			
			for (Message message : messages2) {
				 SysUser sysUser1 = message.getSysUser();
					if(sysUser1!=null){
						if(sysUser1.getUserImage()!=null && !"".equals(sysUser1.getUserImage())){
							sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
							message.setSysUser(sysUser1);
						} 
					}
	            List<Attachment> attachments=message.getAttachments();
				
				if(attachments!=null && attachments.size()>0){
					for (Attachment attachment : attachments) {
						attachment.setAttachmentName(urlPath+attachment.getAttachmentName());
						message.setAttachments(attachments);;	
					}
				}
				
				String content=message.getMessageContent();
				String[] ss=content.split("@#&");
				content="今日已完成工作："+ss[0]+"\n";
				content=content+"今日未完成工作："+ss[1]+"\n";
				content=content+"需协调工作："+ss[2]+"\n";
				content=content+"备注："+ss[3]+"\n";
				message.setMessageContent(content);
				
				List<MessageRecipients> messageRecipientses=message.getMessageRecipientses();
				if(messageRecipientses!=null&&messageRecipientses.size()>0){
					for (MessageRecipients messageRecipients: messageRecipientses) {
						SysUser sysUser2 = messageRecipients.getSysUser();
						if(sysUser2!=null){
							if(sysUser2.getUserImage()!=null && !"".equals(sysUser2.getUserImage())){
								sysUser2.setUserImage(userImageUrl+sysUser2.getUserImage());
								messageRecipients.setSysUser(sysUser2);
							} 
						}
				}
				   
				
				Date date1=message.getSenderDate();
				SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
				String date2 = sdf.format(date1);
				 dates.add(date2);
					for  ( int  i  =   0 ; i  <  dates.size()  -   1 ; i ++ )  {       
						for  ( int  j  =  dates.size()  -   1 ; j  >  i; j -- )  {       
							if  (dates.get(j).equals(dates.get(i)))  {       
								dates.remove(j);       
							}        
						}        
					}     
				
				
				if(date.equals(date2)){
							list4.add(message);
					}
				}
		     }
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultList(dates);
			resultVo.setResultData(list4);
			return resultVo;
		}
		return resultVo;
	
		  }
		
	/**
	 * 根据type获取所有周报对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/messageWeek/getAllByDayAndType/{date}/{type}", method = RequestMethod.GET)
	public ResultVo getWeekAllByDayAndType(@PathVariable("date") String date,@PathVariable("type") Integer type) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		//我收到的周报MessageRecipients
		List<MessageRecipients> list2=new ArrayList<MessageRecipients>();
		
		//我收到的周报Message
		List<Message> list3=new ArrayList<Message>();
		
		//我发出的周报Message
		List<Message> list4=new ArrayList<Message>();
		
		//List<Message> list = messageService.getAllByDayAndType();
		List<Object> dates=new ArrayList<Object>();
		
		//type=1是收到的周报，type=2是我发出的周报
		if(type!=null && type==1){
			List<Message> messages1=messageService.getMessageWeekByUserId(userId);
			for (Message message : messages1) {
				if(message.getSysUser().getUserImage()!=null){
					message.getSysUser().setUserImage(userImageUrl+message.getSysUser().getUserImage());
				}
				List<Attachment> attachments=message.getAttachments();
				
				if(attachments!=null && attachments.size()>0){
					for (Attachment attachment : attachments) {
						attachment.setAttachmentName(urlPath+attachment.getAttachmentName());
						message.setAttachments(attachments);;	
					}
				}
				String content=message.getMessageContent();
				String[] ss=content.split("@#&");
				content="今日已完成工作："+ss[0]+"\n";
				content=content+"今日未完成工作："+ss[1]+"\n";
				content=content+"需协调工作："+ss[2]+"\n";
				content=content+"备注："+ss[3]+"\n";
				message.setMessageContent(content);
				
				List<MessageRecipients> messageRecipientses=message.getMessageRecipientses();
				if(messageRecipientses!=null&&messageRecipientses.size()>0){
					for (MessageRecipients messageRecipients: messageRecipientses) {
						if(messageRecipients.getSysUser().getUserImage()!=null){
							messageRecipients.getSysUser().setUserImage(userImageUrl+messageRecipients.getSysUser().getUserImage());
						}
//						Integer userId2=messageRecipients.getSysUser().getUserId();
//						if(userId1.intValue()==userId2.intValue()){
//							//messageRecipients.setMessage(message);
//							list2.add(messageRecipients);	
					}
				}
				Date date1=message.getSenderDate();
				SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
				String date2 = sdf.format(date1);
				
				dates.add(date2);
				for  ( int  i  =   0 ; i  <  dates.size()  -   1 ; i ++ )  {       
					for  ( int  j  =  dates.size()  -   1 ; j  >  i; j -- )  {       
						if  (dates.get(j).equals(dates.get(i)))  {       
							dates.remove(j);       
						}        
					}        
				}     
				
				if(date.equals(date2)){
					list3.add(message);	
				}
				
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultList(dates);
			resultVo.setResultData(list3);
			return resultVo;
			
			//type=1是收到的周报，type=2是我发出的周报
		}else if(type!=null && type==2){
			List<Message> messages2=messageService.getMessageWeekGiveByUserId(userId);
			
			for (Message message : messages2) {
				
				if(message.getSysUser().getUserImage()!=null){
					message.getSysUser().setUserImage(userImageUrl+message.getSysUser().getUserImage());
				}
				List<Attachment> attachments=message.getAttachments();
				
				if(attachments!=null && attachments.size()>0){
					for (Attachment attachment : attachments) {
						attachment.setAttachmentName(urlPath+attachment.getAttachmentName());
						message.setAttachments(attachments);;	
					}
				}
				
				String content=message.getMessageContent();
				String[] ss=content.split("@#&");
				content="今日已完成工作："+ss[0]+"\n";
				content=content+"今日未完成工作："+ss[1]+"\n";
				content=content+"需协调工作："+ss[2]+"\n";
				content=content+"备注："+ss[3]+"\n";
				message.setMessageContent(content);
				
				List<MessageRecipients> messageRecipientses=message.getMessageRecipientses();
				if(messageRecipientses!=null&&messageRecipientses.size()>0){
					for (MessageRecipients messageRecipients: messageRecipientses) {
						messageRecipients.getSysUser().setUserImage(userImageUrl+messageRecipients.getSysUser().getUserImage());
					}
				}
				
				
				Date date1=message.getSenderDate();
				SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
				String date2 = sdf.format(date1);
				dates.add(date2);
				for  ( int  i  =   0 ; i  <  dates.size()  -   1 ; i ++ )  {       
					for  ( int  j  =  dates.size()  -   1 ; j  >  i; j -- )  {       
						if  (dates.get(j).equals(dates.get(i)))  {       
							dates.remove(j);       
						}        
					}        
				}     
				
				if(date.equals(date2)){
//						Integer userId2=message.getSysUser().getUserId();
//						if(userId1.intValue()==userId2.intValue()){
					list4.add(message);
//						}
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultList(dates);
			resultVo.setResultData(list4);
			return resultVo;
			
		}
		
		return resultVo;
		
	}
	

	
	
	
	/**
	 * 根据登录用户以及日期获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/message/getByUserIdAndDate/{date}", method = RequestMethod.GET)
	public ResultVo getByUserIdAndDate(@PathVariable("date") String date) {
		ResultVo resultVo = new ResultVo();
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		
		List<MessageRecipients> list = messageRecipientsService.getByUserId(userId);
		
		MessageRecipients messageRecipients2=new MessageRecipients();
	        
		List<Object> dates=new ArrayList<Object>(); 
		  
			if(list!=null && list.size()>0){
				for (MessageRecipients messageRecipients : list) {
					Message message=messageRecipients.getMessage();
					MsgState msgState=messageRecipients.getMsgState();
					SysUser sysUser1=messageRecipients.getSysUser();
					if(message!=null){
						List<Attachment> attachments=message.getAttachments();
						
						String content=message.getMessageContent();
						String[] ss=content.split("@#&");
						content="今日已完成工作："+ss[0]+"\n";
						content=content+"今日未完成工作："+ss[1]+"\n";
						content=content+"需协调工作："+ss[2]+"\n";
						content=content+"备注："+ss[3]+"\n";
						message.setMessageContent(content);
					
						
						Date date1=message.getSenderDate();
							
						SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
				        String date2 = sdf.format(date1);

						if(date.equals(date2)){
							messageRecipients2.setMessage(message);
							if(sysUser1.getUserImage()!=null){
								sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
							}
							messageRecipients2.setSysUser(sysUser1);
							if(attachments!=null && attachments.size()>0){
								for (Attachment attachment : attachments) {
									attachment.setAttachmentName(urlPath+attachment.getAttachmentName());
									message.setAttachments(attachments);;	
								}
							}
							if(msgState!=null){
								if(msgState.getMsgId()==1){
									MsgState msgState1=new MsgState();
									msgState1.setMsgId(2);
									messageRecipientsService.updateMsgState(messageRecipients);
									messageRecipients2.setMsgState(msgState1);
								}else {
									messageRecipients2.setMsgState(msgState);
								}
								
							}
						}
						  dates.add(date2);
						  for  ( int  i  =   0 ; i  <  dates.size()  -   1 ; i ++ )  {       
						        for  ( int  j  =  dates.size()  -   1 ; j  >  i; j -- )  {       
						             if  (dates.get(j).equals(dates.get(i)))  {       
						            	 dates.remove(j);       
						              }        
						          }        
						        }   
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultList(dates);
				resultVo.setResultData(messageRecipients2);
				return resultVo;
			}else {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find null");
				resultVo.setResultData(null);
				return resultVo;
			}

		
		
	}
	
	
	
	
	/**
	 * @Description 获取详细信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/messageReport/{id}/{type}/{gtype}", method = RequestMethod.GET)
	public ResultVo getByMessageId(@PathVariable("id") Integer id,@PathVariable("type") Integer type,@PathVariable("gtype") Integer gtype) {
		ResultVo resultVo = new ResultVo();

		FileUtil fileUtil = new FileUtil();

		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		
		   if(gtype!=null && gtype==1){
			 //我收到的
			   
				Message message = messageService.getByReceiverId(id, userId);
				
				
				
//				String messageContent=message.getMessageContent();
//				String[] contents=messageContent.split("@#&");
//				String firstContent="";
//				String secondContent="";
//				String thirdContent="";
//				String fouthContent="";
//				String remark="";
//				if(type==1){
//					 firstContent=contents[0];
//					 secondContent=contents[1];
//					 thirdContent=contents[2];
//					 remark=contents[3];
//				}else{
//					 firstContent=contents[0];
//					 secondContent=contents[1];
//					 thirdContent=contents[2];
//					 fouthContent=contents[3];
//					 remark=contents[4];
//				}
//			
				if (message != null) {
					
					List<CommentEntity> commentEntities = commentService.getByModuleId(message.getMessageId(), 2);
					for (CommentEntity commentEntity : commentEntities) {
			    		 SysUser sysUser1 = commentEntity.getSysUser();
							if(sysUser1.getUserImage()!=null){
								sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
								commentEntity.setSysUser(sysUser1);
							} 
					}
			    	 message.setCommentEntities(commentEntities);
					
					// 点击详情后成已读
					String messageContent=message.getMessageContent();
					String[] contents=messageContent.split("@#&");
					String firstContent="";
					String secondContent="";
					String thirdContent="";
					String fouthContent="";
					String remark="";
					if(type==1){
						 firstContent=contents[0];
						 secondContent=contents[1];
						 thirdContent=contents[2];
						 remark=contents[3];
					}else{
						 firstContent=contents[0];
						 secondContent=contents[1];
						 thirdContent=contents[2];
						 fouthContent=contents[3];
						 remark=contents[4];
					}
				
					
//					message.setMsgState(msgState);
//					messageService.updateState(message);
					
					SysUser sysUser1 = message.getSysUser();
					if(sysUser1.getUserImage()!=null){
						sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
						message.setSysUser(sysUser1);
					}
					List<Attachment> attachments = message.getAttachments();
					List<Attachment> attachmentRes=new ArrayList<Attachment>();
					if (attachments!= null && attachments.size() > 0) {
//						for (Attachment attachment : attachments) {
//							String fileName = attachment.getAttachmentName();
//							attachment.setFileType(fileName.substring(fileName
//									.lastIndexOf(".") + 1));
//							attachment.setAttachmentName(fileName);
//							attachment.setUrlPath(urlPath + fileName);
//							attachment.setSize(fileUtil.getFileSize(filePath
//									+ fileName));
//						}
						for (Attachment attachment : attachments) {
							String fileName = attachment.getAttachmentName();
							String filetype=fileName.substring(fileName.lastIndexOf(".") + 1);
							attachment.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
							attachment.setAttachmentName(fileName);
							attachment.setUrlPath(urlPath + fileName);
							attachment.setSize(fileUtil.getFileSize(filePath+ fileName));
							String pdfUrl=null;
							if("pdf".equals(filetype)){
								pdfUrl=urlPath + fileName;
							}else{
								pdfUrl=attachment.getPdfUrl();
							}
							attachment.setPdfUrl(pdfUrl);
							attachmentRes.add(attachment);
						}
					}
					message.setAttachments(attachmentRes);
					List<MessageRecipients> messageRecipientses = message.getMessageRecipientses();
					
					if (messageRecipientses!= null && messageRecipientses.size() > 0) {
						for (MessageRecipients messageRecipient : messageRecipientses) {
							if(messageRecipient.getSysUser().getUserId().intValue()==sysUser.getUserId().intValue()){
								MsgState msgState = new MsgState();
								msgState.setMsgId(2);
								msgState.setMsgState("已读");
								messageRecipient.setMsgState(msgState);
								messageRecipientsService.update(messageRecipient);
							}
							SysUser sysUser2 = messageRecipient.getSysUser();
							if(sysUser2.getUserImage()!=null){
								sysUser2.setUserImage(userImageUrl+sysUser2.getUserImage());
							}
						}
					}
					Map<String, Object> resMap=new HashMap<String, Object>();
					
					//type=1是日报
					if(type!=null && type==1){
					
						Integer mgsUserId = sysUser1.getUserId();
						List<Integer> msgIdList = messageService.getDayPaperByUser(mgsUserId,userId);
						if(msgIdList.size()<=1){
							resMap.put("isFirst", "true");
							resMap.put("isLast","true");
							
						}else{
							Integer thisOne = msgIdList.indexOf(id);
							if(thisOne==0){
								resMap.put("lastone", msgIdList.get(1));
								resMap.put("isFirst", "true");
								resMap.put("isLast","false");
							}else if(thisOne ==msgIdList.size()-1 ){
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","true");
								resMap.put("isFirst", "false");
							}else{
								resMap.put("lastone", msgIdList.get(thisOne+1));
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","false");
								resMap.put("isFirst", "false");
							}
						}
						//type=2是周报
					}else if(type!=null && type==2){
						Integer mgsUserId = sysUser1.getUserId();
						List<Integer> msgIdList = messageService.getWeekDayPaperByUser(mgsUserId, userId);
						if(msgIdList.size()<=1){
							resMap.put("isFirst", "true");
							resMap.put("isLast","true");
							
						}else{
							Integer thisOne = msgIdList.indexOf(id);
							if(thisOne==0){
								resMap.put("lastone", msgIdList.get(1));
								resMap.put("isFirst", "true");
								resMap.put("isLast","false");
							}else if(thisOne ==msgIdList.size()-1 ){
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","true");
								resMap.put("isFirst", "false");
							}else{
								resMap.put("lastone", msgIdList.get(thisOne+1));
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","false");
								resMap.put("isFirst", "false");
							}
						}
					}
					
					resMap.put("message", message);
					resMap.put("firstContent", firstContent);
					resMap.put("secondContent", secondContent);
					resMap.put("thirdContent", thirdContent);
					resMap.put("fouthContent", fouthContent);
					resMap.put("remark", remark);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("get message success");
					resultVo.setResultData(resMap);
					return resultVo;
				}
			   
			   
		   }else if (gtype!=null && gtype==2) {
			    //我发出的
				Message message = messageService.getById(id);
				
//				String messageContent=message.getMessageContent();
//				String[] contents=messageContent.split("@#&");
//				String firstContent="";
//				String secondContent="";
//				String thirdContent="";
//				String fouthContent="";
//				String remark="";
//				if(type==1){
//					 firstContent=contents[0];
//					 secondContent=contents[1];
//					 thirdContent=contents[2];
//					 remark=contents[3];
//				}else{
//					 firstContent=contents[0];
//					 secondContent=contents[1];
//					 thirdContent=contents[2];
//					 fouthContent=contents[3];
//					 remark=contents[4];
//				}
			
				if (message != null) {
					
					List<CommentEntity> commentEntities = commentService.getByModuleId(message.getMessageId(), 2);
					for (CommentEntity commentEntity : commentEntities) {
			    		 SysUser sysUser1 = commentEntity.getSysUser();
							if(sysUser1.getUserImage()!=null){
								sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
								commentEntity.setSysUser(sysUser1);
							} 
					}
			    	 message.setCommentEntities(commentEntities);
			    	 
					// 点击详情后成已读
					String messageContent=message.getMessageContent();
					String[] contents=messageContent.split("@#&");
					String firstContent="";
					String secondContent="";
					String thirdContent="";
					String fouthContent="";
					String remark="";
					if(type==1){
						 firstContent=contents[0];
						 secondContent=contents[1];
						 thirdContent=contents[2];
						 remark=contents[3];
					}else{
						 firstContent=contents[0];
						 secondContent=contents[1];
						 thirdContent=contents[2];
						 fouthContent=contents[3];
						 remark=contents[4];
					}
					
					
//					message.setMsgState(msgState);
//					messageService.updateState(message);
					
					SysUser sysUser1 = message.getSysUser();
					if(sysUser1.getUserImage()!=null){
						sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
						message.setSysUser(sysUser1);
					}
					List<Attachment> attachments = message.getAttachments();
					List<Attachment> attachmentRes=new ArrayList<Attachment>();
					if (attachments!= null && attachments.size() > 0) {
//						for (Attachment attachment : attachments) {
//							String fileName = attachment.getAttachmentName();
//							attachment.setFileType(fileName.substring(fileName
//									.lastIndexOf(".") + 1));
//							attachment.setAttachmentName(fileName);
//							attachment.setUrlPath(urlPath + fileName);
//							attachment.setSize(fileUtil.getFileSize(filePath
//									+ fileName));
//						}
						for (Attachment attachment : attachments) {
							String fileName = attachment.getAttachmentName();
							String filetype=fileName.substring(fileName.lastIndexOf(".") + 1);
							attachment.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1));
							attachment.setAttachmentName(fileName);
							attachment.setUrlPath(urlPath + fileName);
							attachment.setSize(fileUtil.getFileSize(filePath+ fileName));
							String pdfUrl=null;
							if("pdf".equals(filetype)){
								pdfUrl=urlPath + fileName;
							}else{
								pdfUrl=attachment.getPdfUrl();
							}
							attachment.setPdfUrl(pdfUrl);
							attachmentRes.add(attachment);
						}
					}
					message.setAttachments(attachmentRes);
					List<MessageRecipients> messageRecipientses = message.getMessageRecipientses();
					
					if (messageRecipientses!= null && messageRecipientses.size() > 0) {
						for (MessageRecipients messageRecipient : messageRecipientses) {
//							MsgState msgState = new MsgState();
//							msgState.setMsgId(2);
//							msgState.setMsgState("已读");
//							messageRecipient.setMsgState(msgState);
//							messageRecipientsService.update(messageRecipient);
							SysUser sysUser2 = messageRecipient.getSysUser();
							if(sysUser2.getUserImage()!=null){
								sysUser2.setUserImage(userImageUrl+sysUser2.getUserImage());
							}
						}
					}
					Map<String, Object> resMap=new HashMap<String, Object>();
					
					if(type!=null && type==1){
					
						Integer mgsUserId = sysUser1.getUserId();
						List<Integer> msgIdList = messageService.getMySendDayPaper(userId);
						if(msgIdList.size()<=1){
							resMap.put("isFirst", "true");
							resMap.put("isLast","true");
							
						}else{
							Integer thisOne = msgIdList.indexOf(id);
							if(thisOne==0){
								resMap.put("lastone", msgIdList.get(1));
								resMap.put("isFirst", "true");
								resMap.put("isLast","false");
							}else if(thisOne ==msgIdList.size()-1 ){
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","true");
								resMap.put("isFirst", "false");
							}else{
								resMap.put("lastone", msgIdList.get(thisOne+1));
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","false");
								resMap.put("isFirst", "false");
							}
						}
					}else if (type!=null && type==2) {
						Integer mgsUserId = sysUser1.getUserId();
						List<Integer> msgIdList = messageService.getWeekMySendDayPaper(userId);
						if(msgIdList.size()<=1){
							resMap.put("isFirst", "true");
							resMap.put("isLast","true");
							
						}else{
							Integer thisOne = msgIdList.indexOf(id);
							if(thisOne==0){
								resMap.put("lastone", msgIdList.get(1));
								resMap.put("isFirst", "true");
								resMap.put("isLast","false");
							}else if(thisOne ==msgIdList.size()-1 ){
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","true");
								resMap.put("isFirst", "false");
							}else{
								resMap.put("lastone", msgIdList.get(thisOne+1));
								resMap.put("next", msgIdList.get(thisOne-1));
								resMap.put("isLast","false");
								resMap.put("isFirst", "false");
							}
						}
					}
					
					resMap.put("message", message);
					resMap.put("firstContent", firstContent);
					resMap.put("secondContent", secondContent);
					resMap.put("thirdContent", thirdContent);
					resMap.put("fouthContent", fouthContent);
					resMap.put("remark", remark);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("get message success");
					resultVo.setResultData(resMap);
					return resultVo;
				}
		   }
		
			resultVo.setErrCode(1);
			resultVo.setErrMsg("message is null");
			return resultVo;

		} 

	
	/**
	 * @Description APP关键字搜索获取对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getByKeyword/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		Integer size = page * pageSize;

		List<Message> messages = messageService.getByKeyword(keyword, userId,
				size);
		if (messages != null && messages.size() > 0) {
			
			for (Message message : messages) {
				SysUser sysUser1 = message.getSysUser();
				if(sysUser1.getUserImage()!=null){
					sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
				}
				if(message.getMessageContent()!=null&&message.getMessageContent().indexOf("@#&")>0){
					String messageContent=message.getMessageContent();
					messageContent=messageContent.replace("@#&", ";");
					message.setMessageContent(messageContent);
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("get message success");
			resultVo.setResultData(messages);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("message is null");
		return resultVo;

	}
	
	
	/**
	 * @Description APP首页获取对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getAppMessages", method = RequestMethod.GET)
	public ResultVo getAppMessages() {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();

		List<Message> messages = messageService.getAppMessages(userId);
		if (messages != null && messages.size() > 0) {
			
			for (Message message : messages) {
				SysUser sysUser1 = message.getSysUser();
				if(sysUser1.getUserImage()!=null){
					sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
				}
			}
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("get message success");
			resultVo.setResultData(messages);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("message is null");
		return resultVo;

	}
	
	/**
	 * @Description web主页消息总数
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getTotalUnread", method = RequestMethod.GET)
	public ResultVo getTotalUnread() {
//		boolean flag=logger.isDebugEnabled();
//		if(flag){
//			logger.isDebugEnabled()=false;
//		}
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String userImage=sysUser.getUserImage();
		String url="";
		if(userImage!=null){
			url=imagePath+ sysUser.getUserImage();
			sysUser.setUserImage(url);
		}
		UnreadMessageVo unreadMsg = new UnreadMessageVo();
		unreadMsg.setUserMessages(messageService.getTotalUnread(userId,1));
		unreadMsg.setBusMessages(messageService.getTotalUnread(userId,2));
		unreadMsg.setSysMessages(messageService.getTotalUnread(userId,3));
		unreadMsg.setDailyMessage(messageService.getDailyOrWeekUnread(userId,2 ));
		unreadMsg.setWeeklyMessage(messageService.getDailyOrWeekUnread(userId,3));
		unreadMsg.setImageUrl(url);
		unreadMsg.setSysUser(sysUser);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("get message success");
		resultVo.setResultData(unreadMsg);
		return resultVo;

	}
	
	
	/**
	 * @Description 安卓关键字搜索获取对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getByKeywords/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getByKeywords(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		// 列表id
		if (keyword != null && keyword != ""
				&& !"undefined".equals(keyword)) {
			List<Message> messages = messageService.getByKeywords(keyword, userId);
			if(messages != null){
				
				for (Message message : messages) {
					SysUser sysUser1 = message.getSysUser();
					if(sysUser1.getUserImage()!=null){
						sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
					}
				}
				
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(messages));
				return resultVo;
			}else{
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;
			}
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("find is null");
		return resultVo;

	}

	/**
	 * @Description App获取类型对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getByType/{page}/{pageSize}/{id}", method = RequestMethod.GET)
	public ResultVo getByType(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		Integer size = page * pageSize;
		// 列表id
		if (id == 1) {
			Integer typeId = 1;
			List<Message> messages = messageService.getReceivers(typeId,
					userId, size);
			if (messages == null) {
				resultVo.setErrCode(2);
				resultVo.setErrMsg("messages is null");
				return resultVo;
			}
			
			for (Message message : messages) {
				SysUser sysUser1 = message.getSysUser();
				if(sysUser1.getUserImage()!=null){
					sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
				}
				if(message.getMessageContent()!=null&&message.getMessageContent().indexOf("@#&")>0){
					String messageContent=message.getMessageContent();
					messageContent=messageContent.replace("@#&", ";");
					message.setMessageContent(messageContent);
				}
			}
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("get message success");
			resultVo.setResultData(messages);
			return resultVo;
		}
		if (id == 2) {
			Integer typeId = 1;
			// 发件
			List<Message> messages = messageService.getSenders(typeId, userId,
					size);
			if (messages == null) {
				resultVo.setErrCode(2);
				resultVo.setErrMsg("messages is null");
				return resultVo;
			}
			
			for (Message message : messages) {
				SysUser sysUser1 = message.getSysUser();
				if(sysUser1.getUserImage()!=null){
					sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
				}
				if(message.getMessageContent()!=null&&message.getMessageContent().indexOf("@#&")>0){
					String messageContent=message.getMessageContent();
					messageContent=messageContent.replace("@#&", ";");
					message.setMessageContent(messageContent);
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("get message success");
			resultVo.setResultData(messages);
			return resultVo;
		}
		if (id == 3) {
			Integer typeId = 2;
			List<Message> messages = messageService.getReceivers(typeId,
					userId, size);
			if (messages == null) {
				resultVo.setErrCode(2);
				resultVo.setErrMsg("messages is null");
				return resultVo;
			}
			
			for (Message message : messages) {
				SysUser sysUser1 = message.getSysUser();
				if(sysUser1.getUserImage()!=null){
					sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("get message success");
			resultVo.setResultData(messages);
			return resultVo;
		}
		if (id == 4) {
			Integer typeId = 3;
			List<Message> messages = messageService.getReceivers(typeId,
					userId, size);
			if (messages == null) {
				resultVo.setErrCode(2);
				resultVo.setErrMsg("messages is null");
				return resultVo;
			}
			for (Message message : messages) {
				SysUser sysUser1 = message.getSysUser();
				if(sysUser1.getUserImage()!=null){
					sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("get message success");
			resultVo.setResultData(messages);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("not this id");
		return resultVo;
	}
	
	
	/**
	 * @Description web关键字查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getByType/{page}/{pageSize}/{id}/{keyword}", method = RequestMethod.GET)
	public ResultVo getByType(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("id") Integer id,
			@PathVariable("keyword") String keyword) {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		// 列表id
			if (id == 1) {
				Integer typeId = 1;
				getWebReceivesByKewords(typeId,userId,keyword,resultVo);
			}
			if (id == 2) {
				Integer typeId = 1;
				getWebSendersByKewords(typeId,userId,keyword,resultVo);
			}
			if (id == 3) {
				Integer typeId = 2;
				getWebReceivesByKewords(typeId,userId,keyword,resultVo);
			}
			if (id == 4) {
				Integer typeId = 3;
				getWebReceivesByKewords(typeId,userId,keyword,resultVo);
			}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("not this id");
		return resultVo;
	}

	/**
	 * @Description 删除对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/delete/{id}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		messageRecipientsService.deleteByMsgId(id);
		attachmentService.deleteByMsgId(id);
		messageService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("get message success");
		return resultVo;
	}
	
	
	/**
	 * @Description 删除对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/delete/{id}/{number}", method = RequestMethod.GET)
	public ResultVo updateByDelete(@PathVariable("id") Integer id,
			@PathVariable("number") Integer number) {
		ResultVo resultVo = new ResultVo();
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		
		if(number==1 || number==3 ||number==4){
			messageRecipientsService.updateMessageByDelete(id, userId, 1);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete message success");
			return resultVo;
		}
		//收件箱消息未删除的个数
		Integer count = messageRecipientsService.getByMsgId(id);
		if (count>0){
			messageService.updateMessageByDelete(id, 1);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete message success");
			return resultVo;
		}
		messageRecipientsService.deleteByMsgId(id);
		attachmentService.deleteByMsgId(id);
		messageService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete message success");
		return resultVo;
	}
	

	/**
	 * @Description 最近10条联系人列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getRecentContacter", method = RequestMethod.GET)
	public ResultVo getRecentContacter() {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		List<Message> messages = messageService.getRecentContacter(userId);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("get message success");
		resultVo.setResultData(messages);
		return resultVo;
	}

	/**
	 * @Description 最所有联系人列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getAllContacter", method = RequestMethod.GET)
	public ResultVo getAllContacter() {
		ResultVo resultVo = new ResultVo();
		List<SysUser> contacter = sysUserService.getContacter();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("get message success");
		resultVo.setResultData(contacter);
		return resultVo;
	}

	/**
	 * @Description 最所有联系人列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getAllUser", method = RequestMethod.GET)
	public ResultVo getAllUser() {
		ResultVo resultVo = new ResultVo();
		List<SysUser> contacter = sysUserService.getContacter();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		if(contacter!=null&&contacter.size()>0){
			for(int i=0;i<contacter.size();i++){
				if(sysUser.getUserId().equals(contacter.get(i).getUserId())){
					contacter.remove(i);
				}
			}
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("get message success");
		resultVo.setResultData(contacter);
		
		return resultVo;
	}
	/**
	 * @Description App新增用户信息
	 * @author xux
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public ResultVo save(
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			@RequestParam(value = "ids", required = false) String ids,
			@RequestParam(value = "messageTitle", required = false) String messageTitle,
			@RequestParam(value = "messageContent", required = false) String messageContent) throws IOException {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		if (messageTitle != null || messageContent != null) {
			Message message = new Message();

			MsgType msgType = new MsgType();
			msgType.setMsgTypeId(1);

			
			MsgDetailtype msgDetailtype=new MsgDetailtype();
			msgDetailtype.setMsgDetailtypeId(1);
			

			message.setMessageTitle(messageTitle);
			message.setMessageContent(messageContent);
			message.setMsgType(msgType);
			message.setMsgDetailtype(msgDetailtype);
			//message.setMsgState(msgState);
			message.setSenderDate(new Date());
			message.setSysUser(sysUser);
			message.setFlag(0);
			messageService.save(message);

			String[] idList = ids.split(",");
			if (ids != null && idList.length > 0) {
				for (String id : idList) {
					SysUser sysUser1 = new SysUser();
					sysUser1.setUserId(Integer.parseInt(id));
					MessageRecipients recipients = new MessageRecipients();
					MsgState msgState = new MsgState();
					msgState.setMsgId(1);
					recipients.setMessage(message);
					recipients.setSysUser(sysUser1);
					recipients.setFlag(0);
					recipients.setMsgState(msgState);
					messageRecipientsService.save(recipients);
				}
			}
			if (files != null && files.length > 0) {
				// 保存上传文件
				saveAttachment(message, files, filePath);
			}
			try {
				message.setMessageContent("[用户消息] "+messageContent);
				// TODO 激光推送
				JPushData pushData = getJPushData(message,ids);
				Map<String, Integer> extraMap=new HashMap<String, Integer>();
				extraMap.put("type", LabConstans.MESSAGE_TYPE);
				extraMap.put("userId", sysUser.getUserId());
				extraMap.put("userId", sysUser.getUserId());
				extraMap.put("messageId", message.getMessageId());
				extraMap.put("messageType", 1);
				try {
					jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("send message fail");
				return resultVo;
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save message success");
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("save message fail");
		return resultVo;
	}
	
	/**
	 * @Description App新增用户信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/saveDaily", method = RequestMethod.POST)
	public ResultVo saveDaily(
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			@RequestParam(value = "ids", required = false) String ids,
			@RequestParam(value = "messageTitle", required = false) String messageTitle,
			@RequestParam(value = "firstContent", required = false) String firstContent,
			@RequestParam(value = "secondContent", required = false) String secondContent,
			@RequestParam(value = "thirdContent", required = false) String thirdContent,
			@RequestParam(value = "fouthContent", required = false) String fouthContent,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "type", required = false) int type) {
		//type=1为日报   type=2为周报
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
	try {
		Message message = new Message();

		MsgType msgType = new MsgType();
	

		
		MsgDetailtype msgDetailtype=new MsgDetailtype();
		String messageContent="";
		String content="";
		if(type==1){
			msgDetailtype.setMsgDetailtypeId(2);//日报  
			if(firstContent!=null&&!"".equals(firstContent)){
				messageContent=messageContent+firstContent;
			}
			if(secondContent!=null&&!"".equals(secondContent)){
				messageContent=messageContent+"@#&"+secondContent;
			}else{
				messageContent=messageContent+"@#&"+"暂无";
			}
			if(thirdContent!=null&&!"".equals(thirdContent)){
				messageContent=messageContent+"@#&"+thirdContent;
			}else{
				messageContent=messageContent+"@#&"+"暂无";
			}
			if(remark!=null&&!"".equals(remark)){
				messageContent=messageContent+"@#&"+remark;
			}else{
				messageContent=messageContent+"@#&"+"暂无";
			}
			Date today=new Date();
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			  String day=sdf.format(today);
			  messageTitle=sysUser.getRealname()+day+"日报";
				msgType.setMsgTypeId(1);
				content="[日报提醒]"+sysUser.getRealname()+"向你发送了日报";
		}else{
			msgDetailtype.setMsgDetailtypeId(3);//周报
			if(firstContent!=null&&!"".equals(firstContent)){
				messageContent=messageContent+firstContent;
			}
			if(secondContent!=null&&!"".equals(secondContent)){
				messageContent=messageContent+"@#&"+secondContent;
			}else{
				messageContent=messageContent+"@#&"+"暂无";
			}
			if(thirdContent!=null&&!"".equals(thirdContent)){
				messageContent=messageContent+"@#&"+thirdContent;
			}else{
				messageContent=messageContent+"@#&"+"暂无";
			}
			if(fouthContent!=null&&!"".equals(fouthContent)){
				messageContent=messageContent+"@#&"+fouthContent;
			}else{
				messageContent=messageContent+"@#&"+"暂无";
			}
			if(remark!=null&&!"".equals(remark)){
				messageContent=messageContent+"@#&"+remark;
			}else{
				messageContent=messageContent+"@#&"+"暂无";
			}
			Date today=new Date();
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			  String day=sdf.format(today);
			  messageTitle=sysUser.getRealname()+day+"周报";
				msgType.setMsgTypeId(1);
				content="[周报提醒]"+sysUser.getRealname()+"向你发送了周报";
		}
		
		message.setMessageTitle(messageTitle);
		message.setMessageContent(messageContent);
		message.setMsgType(msgType);
		message.setMsgDetailtype(msgDetailtype);
		//message.setMsgState(msgState);
		message.setSenderDate(new Date());
		message.setSysUser(sysUser);
		message.setFlag(0);
		messageService.save(message);

		String[] idList = ids.split(",");
		if (ids != null && idList.length > 0) {
			for (String id : idList) {
				SysUser sysUser1 = new SysUser();
				sysUser1.setUserId(Integer.parseInt(id));
				MessageRecipients recipients = new MessageRecipients();
				MsgState msgState = new MsgState();
				msgState.setMsgId(1);
				recipients.setMessage(message);
				recipients.setSysUser(sysUser1);
				recipients.setFlag(0);
				recipients.setMsgState(msgState);
				messageRecipientsService.save(recipients);
			}
		}
		if (files != null && files.length > 0) {
			// 保存上传文件
			saveAttachment(message, files, filePath);
		}
		try {
			message.setMessageContent(content);
			// TODO 激光推送
			JPushData pushData = getJPushData(message,ids);
			Map<String, Integer> extraMap=new HashMap<String, Integer>();
			if(type==1){
				extraMap.put("type", LabConstans.DAIRY_TYPE);
			}else{
				extraMap.put("type", LabConstans.WEEK_TYPE);
			}
			extraMap.put("userId", sysUser.getUserId());
			extraMap.put("userId", sysUser.getUserId());
			extraMap.put("messageId", message.getMessageId());
			extraMap.put("messageType", 1);
			try {
				jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("send message fail");
			return resultVo;
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save message success");
		return resultVo;
	} catch (Exception e) {
		resultVo.setErrCode(2);
		resultVo.setErrMsg("save message success");
		return resultVo;
	}
			
	}
	
	/**
	 * @Description Web新增用户信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/WebSaveDaily", method = RequestMethod.POST)
	public ResultVo WebSaveDaily(@RequestBody MessageVo messageVo) {
		
		ResultVo resultVo = new ResultVo();
	
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer	type=messageVo.getType();
			 String messageTitle=messageVo.getMessageTitle();
			 String firstContent=messageVo.getFirstContent();
			 String secondContent=messageVo.getSecondContent();
			 String thirdContent=messageVo.getThirdContent();
			 String fouthContent=messageVo.getFouthContent();
			 String remark=messageVo.getRemark();
			 List<Integer> idList = messageVo.getIdList();
			 List<String> filenames=messageVo.getFilenames();
			 List<String> fileStrs=messageVo.getFileStrs();
				
			//type=1为日报   type=2为周报
		
			Message message = new Message();
			
			MsgType msgType = new MsgType();
			
			MsgDetailtype msgDetailtype=new MsgDetailtype();
			String messageContent="";
			String content="";
			if(type==1){
				msgDetailtype.setMsgDetailtypeId(2);//日报  
				if(firstContent!=null&&!"".equals(firstContent)){
					messageContent=messageContent+firstContent;
				}
				if(secondContent!=null&&!"".equals(secondContent)){
					messageContent=messageContent+"@#&"+secondContent;
				}else{
					messageContent=messageContent+"@#&"+"暂无";
				}
				if(thirdContent!=null&&!"".equals(thirdContent)){
					messageContent=messageContent+"@#&"+thirdContent;
				}else{
					messageContent=messageContent+"@#&"+"暂无";
				}
				if(remark!=null&&!"".equals(remark)){
					messageContent=messageContent+"@#&"+remark;
				}else{
					messageContent=messageContent+"@#&"+"暂无";
				}
				Date today=new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String day=sdf.format(today);
				messageTitle=sysUser.getRealname()+day+"日报";
				msgType.setMsgTypeId(1);
				content="[日报提醒]"+sysUser.getRealname()+"向你发送了日报";
			}else if(type==2){
				msgDetailtype.setMsgDetailtypeId(3);//周报
				if(firstContent!=null&&!"".equals(firstContent)){
					messageContent=messageContent+firstContent;
				}
				if(secondContent!=null&&!"".equals(secondContent)){
					messageContent=messageContent+"@#&"+secondContent;
				}else{
					messageContent=messageContent+"@#&"+"暂无";
				}
				if(thirdContent!=null&&!"".equals(thirdContent)){
					messageContent=messageContent+"@#&"+thirdContent;
				}else{
					messageContent=messageContent+"@#&"+"暂无";
				}
				if(fouthContent!=null&&!"".equals(fouthContent)){
					messageContent=messageContent+"@#&"+fouthContent;
				}else{
					messageContent=messageContent+"@#&"+"暂无";
				}
				if(remark!=null&&!"".equals(remark)){
					messageContent=messageContent+"@#&"+remark;
				}else{
					messageContent=messageContent+"@#&"+"暂无";
				}
				Date today=new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String day=sdf.format(today);
				messageTitle=sysUser.getRealname()+day+"周报";
				msgType.setMsgTypeId(1);
				content="[周报提醒]"+sysUser.getRealname()+"向你发送了周报";
			}
			
			message.setMessageTitle(messageTitle);
			message.setMessageContent(messageContent);
			message.setMsgType(msgType);
			message.setMsgDetailtype(msgDetailtype);
			//message.setMsgState(msgState);
			message.setSenderDate(new Date());
			message.setSysUser(sysUser);
			message.setFlag(0);
			messageService.save(message);
			
		
			if (idList != null && idList.size() > 0) {
				for (Integer id : idList) {
					SysUser sysUser1 = new SysUser();
					sysUser1.setUserId(id);
					MessageRecipients recipients = new MessageRecipients();
					MsgState msgState = new MsgState();
					msgState.setMsgId(1);
					recipients.setMessage(message);
					recipients.setSysUser(sysUser1);
					recipients.setFlag(0);
					recipients.setMsgState(msgState);
					messageRecipientsService.save(recipients);
				}
			}
			if (fileStrs != null && fileStrs.size() > 0) {
				// 保存上传文件
				saveFile(message, fileStrs,filenames);
			}
			
			try {
				message.setMessageContent(content);
				// TODO 激光推送
				JPushData pushData = getJPushData(message,idList);
				Map<String, Integer> extraMap=new HashMap<String, Integer>();
				if(type==1){
					extraMap.put("type", LabConstans.DAIRY_TYPE);
				}else{
					extraMap.put("type", LabConstans.WEEK_TYPE);
				}
				extraMap.put("userId", sysUser.getUserId());
				extraMap.put("userId", sysUser.getUserId());
				extraMap.put("messageId", message.getMessageId());
				extraMap.put("messageType", 1);
				try {
					jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("send message fail");
				return resultVo;
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save message success");
			return resultVo;
		
		
	}
	
	
	/**
	 * @Description 安卓回复转发信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/forward", method = RequestMethod.POST)
	public ResultVo forward(
			@RequestParam(value = "fileNames", required = false) String[] fileNames,
			@RequestParam(value = "ids", required = false) String ids,
			@RequestParam(value = "messageTitle", required = false) String messageTitle,
			@RequestParam(value = "messageContent", required = false) String messageContent) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		if (messageTitle != null || messageContent != null) {
			Message message = new Message();

			MsgType msgType = new MsgType();
			msgType.setMsgTypeId(1);

	
			
			MsgDetailtype msgDetailtype=new MsgDetailtype();
			msgDetailtype.setMsgDetailtypeId(1);
			message.setMsgDetailtype(msgDetailtype);

			message.setMessageTitle(messageTitle);
			message.setMessageContent(messageContent);
			message.setMsgType(msgType);
		//	message.setMsgState(msgState);
			message.setSenderDate(new Date());
			message.setSysUser(sysUser);
			message.setFlag(0);
			messageService.save(message);

			String[] idList = ids.split(",");
			if (ids != null && idList.length > 0) {
				for (String id : idList) {
					SysUser sysUser1 = new SysUser();
					sysUser1.setUserId(Integer.parseInt(id));
					MessageRecipients recipients = new MessageRecipients();
					MsgState msgState = new MsgState();
					msgState.setMsgId(1);
					recipients.setMessage(message);
					recipients.setSysUser(sysUser1);
					recipients.setFlag(0);
					recipients.setMsgState(msgState);
					messageRecipientsService.save(recipients);
				}
			}
			if (fileNames != null && fileNames.length > 0) {
				// 保存上传文件
				Attachment attachment = new Attachment();
				for (String fileName : fileNames) {
					attachment.setAttachmentName(fileName);
					attachment.setMessage(message);
					attachmentService.save(attachment);
				}
			}
			try {
				// TODO 激光推送
				message.setMessageContent("[用户消息] "+messageContent);
				JPushData pushData = getJPushData(message,ids);
				Map<String, Integer> extraMap=new HashMap<String, Integer>();
				extraMap.put("type", LabConstans.MESSAGE_TYPE);
				extraMap.put("userId", sysUser.getUserId());
				extraMap.put("messageId", message.getMessageId());
				extraMap.put("messageType", 1);
				try {
					//jpush.sendMessageAndNotification_Pall(pushData, null);
					jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("send message fail");
				return resultVo;
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save message success");
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("save message fail");
		return resultVo;
	}
	
	/**
	 * @Description web新增信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/save", method = RequestMethod.POST)
	public ResultVo saveMessage(@RequestBody MessageVo messageVo) {
		ResultVo resultVo = new ResultVo();
		String messageTitle = messageVo.getMessageTitle();
		String messageContent = messageVo.getMessageContent();
		List<Integer> idList = messageVo.getIdList();
		List<String> fileStrs = messageVo.getFileStrs();
		List<String> filenames = messageVo.getFilenames();
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		if (messageTitle != null || messageContent != null) {
			Message message = new Message();

			MsgType msgType = new MsgType();
			msgType.setMsgTypeId(1);

		
			MsgDetailtype msgDetailtype=new MsgDetailtype();
			msgDetailtype.setMsgDetailtypeId(1);
			message.setMsgDetailtype(msgDetailtype);

			message.setMessageTitle(messageTitle);
			message.setMessageContent(messageContent);
			message.setMsgType(msgType);
		//	message.setMsgState(msgState);
			message.setSenderDate(new Date());
			message.setSysUser(sysUser);
			message.setFlag(0);
			messageService.save(message);

			if (idList != null && idList.size() > 0) {
				for (Integer id : idList) {
					SysUser sysUser1 = new SysUser();
					sysUser1.setUserId(id);
					MessageRecipients recipients = new MessageRecipients();
					MsgState msgState = new MsgState();
					msgState.setMsgId(1);
					recipients.setMessage(message);
					recipients.setSysUser(sysUser1);
					recipients.setFlag(0);
					recipients.setMsgState(msgState);
					messageRecipientsService.save(recipients);
				}
			}
			if (fileStrs != null && fileStrs.size() > 0) {
				// 保存上传文件
				saveFile(message, fileStrs,filenames);
			}
			try {
				// TODO 激光推送
				message.setMessageContent("[用户消息] "+messageContent);
				JPushData pushData = getJPushData(message,idList);
				Map<String, Integer> extraMap=new HashMap<String, Integer>();
				extraMap.put("type", LabConstans.MESSAGE_TYPE);
				extraMap.put("userId", sysUser.getUserId());
				extraMap.put("messageId", message.getMessageId());
				extraMap.put("messageType", 1);
				try {
					//jpush.sendMessageAndNotification_Pall(pushData, null);
					jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("send message fail");
				return resultVo;
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save message success");
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("save message fail");
		return resultVo;
	}
	
	/**
	 * @Description web新增信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/message/systemSave", method = RequestMethod.POST)
	public ResultVo saveSysten(@RequestBody MessageVo messageVo) {
		ResultVo resultVo = new ResultVo();
		String messageTitle = messageVo.getMessageTitle();
		String messageContent = messageVo.getMessageContent();
		List<Integer> idList = messageVo.getIdList();
		List<String> fileStrs = messageVo.getFileStrs();
		List<String> filenames = messageVo.getFilenames();
		List<Integer> userList=new ArrayList<Integer>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		if (messageTitle != null || messageContent != null) {
			Message message = new Message();

			MsgType msgType = new MsgType();
			msgType.setMsgTypeId(3);

			
			MsgDetailtype msgDetailtype=new MsgDetailtype();
			msgDetailtype.setMsgDetailtypeId(10);
			message.setMsgDetailtype(msgDetailtype);
			
			message.setMessageTitle(messageTitle);
			message.setMessageContent(messageContent);
			message.setMsgType(msgType);
		//	message.setMsgState(msgState);
			message.setSenderDate(new Date());
			message.setSysUser(sysUser);
			message.setFlag(0);
			messageService.save(message);
			
			//获取所有用户
			List<SysUser> users = sysUserService.getUserName();
			for (SysUser sysUser2 : users) {
					MessageRecipients recipients = new MessageRecipients();
					MsgState msgState = new MsgState();
					msgState.setMsgId(1);
					recipients.setMessage(message);
					recipients.setSysUser(sysUser2);
					recipients.setFlag(0);
					recipients.setMsgState(msgState);
					messageRecipientsService.save(recipients);
					userList.add(sysUser2.getUserId());
			}
			if (fileStrs != null && fileStrs.size() > 0) {
				// 保存上传文件
				saveFile(message, fileStrs,filenames);
			}
			try {
				// TODO 激光推送
				message.setMessageContent("[系统消息] "+messageContent);
				JPushData pushData = getJPushData(message,userList);
				Map<String, Integer> extraMap=new HashMap<String, Integer>();
				extraMap.put("type", LabConstans.SYSTEM_TYPE);
				extraMap.put("userId", sysUser.getUserId());
				extraMap.put("messageId", message.getMessageId());
				extraMap.put("messageType", 4);
				try {
					//jpush.sendMessageAndNotification_Pall(pushData, null);
					jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("send message fail");
				return resultVo;
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save message success");
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("save message fail");
		return resultVo;
	}

	/**
	 * @Description App保存文件
	 * @author xux
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveAttachment(Message message, MultipartFile[] files,
			String path) throws IOException {

		// 保存上传图片
		List<String> fileNameList = new FileUtil().imageUpload(files, path);
		Attachment attachment = new Attachment();
		for (String fileName : fileNameList) {
			String type=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
			if(LabConstans.CONVERSION_TYPE.contains(type)){
				attachment.setPdfCount(0);
				attachment.setConversionCount(0);
			}else if("pdf".equals(type)){
				attachment.setPdfCount(2);
			}else{
				attachment.setPdfCount(99);
			}
			attachment.setAttachmentName(fileName);
			attachment.setMessage(message);
			attachmentService.save(attachment);
		}
	}

	private JPushData getJPushData(Message msg,String ids) {

		JPushData jPushData = new JPushData();
		List<String> usernames = new ArrayList<String>();
		if (msg != null) {
			String[] idList = ids.split(",");
			if (ids != null && idList.length > 0) {
				for (String id : idList) {
					SysUser sysUser = sysUserService.getById(Long.parseLong(id));
					usernames.add(sysUser.getUsername()+"@"+sysUser.getSysSigningAgency().getAgencyId());
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
	
	private JPushData getJPushData(Message msg,List<Integer> ids) {

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
			jPushData.setTag("消息");
			jPushData.setTitle(msg.getMessageTitle());
			jPushData.setAlias(usernames);
			return jPushData;
		}
		return null;
	}
	
	/**
	 * @Description Web保存文件
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveFile(Message message, List<String> fileStrs,List<String> filenames) {

		// 保存上传图片
		if (fileStrs != null && fileStrs.size() > 0) {
			// base64保存图片
			Base64Util base64Util = new Base64Util();
			for (int i =0;i<fileStrs.size();i++) {
				Attachment attachment = new Attachment();
				String fileStr = fileStrs.get(i).substring(fileStrs.get(i).indexOf(",") + 1);
				String AttacheName = base64Util.GenerateFile(fileStr,filePath,filenames.get(i));
				String type=AttacheName.substring(AttacheName.lastIndexOf(".")+1, AttacheName.length());
				if(LabConstans.CONVERSION_TYPE.contains(type)){
					attachment.setPdfCount(0);
					attachment.setConversionCount(0);
				}else if("pdf".equals(type)){
					attachment.setPdfCount(2);
				}else{
					attachment.setPdfCount(99);
				}
				attachment.setAttachmentName(AttacheName);
				attachment.setMessage(message);
				attachmentService.save(attachment);
			}
		}
	}
	
	/**
	 * @Description Web接受消息关键字列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private ResultVo getWebReceivesByKewords(Integer typeId,Integer userId,String keyword,ResultVo resultVo){
		if (keyword != null && keyword != ""
				&& !"undefined".equals(keyword)) {
			List<Message> messages = messageService.getReceiversByKeyword(typeId,
					userId, keyword);
			if(messages != null){
				for (Message message : messages) {
					SysUser sysUser1 = message.getSysUser();
					if(sysUser1.getUserImage()!=null){
						sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(messages));
				return resultVo;
			}
			resultVo.setErrCode(2);
			resultVo.setErrMsg("find is null");
			return resultVo;
			
		}else{
			List<Message> messages = messageService.getWebReceivers(typeId, userId);
			if(messages != null){
				for (Message message : messages) {
					SysUser sysUser1 = message.getSysUser();
					if(sysUser1.getUserImage()!=null){
						sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(messages));
				return resultVo;
			}
			resultVo.setErrCode(2);
			resultVo.setErrMsg("find is null");
			return resultVo;
		}
	}
	
	/**
	 * @Description Web发送消息关键字列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private ResultVo getWebSendersByKewords(Integer typeId,Integer userId,String keyword,ResultVo resultVo){
		if (keyword != null && keyword != ""
				&& !"undefined".equals(keyword)) {
			// 发件
			List<Message> messages = messageService.getSendersByKeyword(typeId, userId,
					keyword);
			if(messages != null){
				for (Message message : messages) {
					SysUser sysUser1 = message.getSysUser();
					if(sysUser1.getUserImage()!=null){
						sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(messages));
				return resultVo;
			}
			resultVo.setErrCode(2);
			resultVo.setErrMsg("find is null");
			return resultVo;
			
		}else{
			List<Message> messages = messageService.getWebSenders(typeId, userId);
			if(messages != null){
				for (Message message : messages) {
					SysUser sysUser1 = message.getSysUser();
					if(sysUser1.getUserImage()!=null){
						sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(messages));
				return resultVo;
			}
			
			resultVo.setErrCode(2);
			resultVo.setErrMsg("find is null");
			return resultVo;
		}
	}
	
	@RequestMapping(value = "/message/messageForAddProduct/{productName}/{userId}", method = RequestMethod.POST)
	public ResultVo messageForAddProduct(@PathVariable("productName") String productName,@PathVariable("userId") Integer userId) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		List<Integer> ids=new ArrayList<Integer>();
		if (productName != null && !"".equals(productName)) {
			String messageTitle=sysUser.getRealname()+"在新增入库时未搜索到"+productName+"的信息，请帮助添加";
			Message message = new Message();

			MsgType msgType = new MsgType();
			msgType.setMsgTypeId(2);
			MsgDetailtype msgDetailtype=new MsgDetailtype();
			msgDetailtype.setMsgDetailtypeId(11);
			message.setMsgDetailtype(msgDetailtype);
			message.setMessageTitle(messageTitle);
			message.setMessageContent(messageTitle);
			message.setMsgType(msgType);
			//message.setMsgState(msgState);
			message.setSenderDate(new Date());
			message.setSysUser(sysUser);
			message.setFlag(0);
			messageService.save(message);

			if (userId!=null) {
				
					SysUser sysUser1 = new SysUser();
					sysUser1.setUserId(userId);
					ids.add(userId);
					MessageRecipients recipients = new MessageRecipients();
					MsgState msgState = new MsgState();
					msgState.setMsgId(1);
					recipients.setMessage(message);
					recipients.setSysUser(sysUser1);
					recipients.setFlag(0);
					recipients.setMsgState(msgState);
					messageRecipientsService.save(recipients);
			}
			
			try {
				// TODO 激光推送
				message.setMessageContent("[业务消息] "+messageTitle);
				JPushData pushData = getJPushData(message,ids);
				Map<String, Integer> extraMap=new HashMap<String, Integer>();
				extraMap.put("type", LabConstans.MESSAGE_TYPE);
				extraMap.put("userId", sysUser.getUserId());
				extraMap.put("messageId", message.getMessageId());
				extraMap.put("messageType", 1);
				try {
					//jpush.sendMessageAndNotification_Pall(pushData, null);
					jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("send message fail");
				return resultVo;
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save message success");
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("save message fail");
		return resultVo;
	}

	
	/**
	 * @Description Web发送消息关键字列表
	 * @author llwangi
	 * @version V1.0
	 * @throws Exception 
	 * @date 2018年1月20日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getAllMessageMap", method = RequestMethod.GET)
	private ResultVo getAllMessageMap() throws Exception{
		ResultVo res=new ResultVo();
		int allCount=0;
		try {
			String isNotice = "false";
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			List<Map<String, Object>> resList=new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> userMessage = messageService.getUserMessage(1, sysUser.getUserId());
			List<Map<String, Object>> dairlyMessage=messageService.getNoteMessage(2, sysUser.getUserId());
			List<Map<String, Object>> weekMessage=messageService.getNoteMessage(3, sysUser.getUserId());
			List<Map<String, Object>> noteMessage=messageService.getNoteMessage(6, sysUser.getUserId());
			List<Map<String, Object>> reactionAssistMessage=messageService.getNoteMessage(7, sysUser.getUserId());
			List<Map<String, Object>> deviceAppointMessage=messageService.getNoteMessage(8, sysUser.getUserId());
			List<Map<String, Object>> calendarsMessage=messageService.getNoteMessage(9, sysUser.getUserId());
			List<Map<String, Object>> systemMessage=messageService.getNoteMessage(10, sysUser.getUserId());
			List<Map<String, Object>> marketMessage=messageService.getMarketMessage(13, sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId());
			List<Map<String, Object>> knowledgeMessage=messageService.getMarketMessage(12, sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId());
			List<Map<String, Object>> patentMessage=messageService.getMarketMessage(14, sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId());
			if(userMessage!=null&&userMessage.size()>0){
			
				for(int i=0;i<userMessage.size();i++){
					if(userMessage.get(i).get("user_image1")!=null&&!"".equals(userMessage.get(i).get("user_image1"))){
						userMessage.get(i).put("user_image1", imagePath+userMessage.get(i).get("user_image1"));
					}
					Integer userId=Integer.valueOf(userMessage.get(i).get("user_id1").toString());
					int detailTypeId=1;
					int loginUserId=sysUser.getUserId();
					int count=messageService.getUserMessageNum(detailTypeId,loginUserId,userId);
					userMessage.get(i).put("count", count);
					allCount=allCount+count;
					resList.add(userMessage.get(i));
				}
			}
			
			if(dairlyMessage!=null&&dairlyMessage.size()>0){
				int count=0;
				for(int i=0;i<dairlyMessage.size();i++){
//					if(String.valueOf(dairlyMessage.get(i).get("msg_id")).equals("1")){
//						count++;
//					}
					Integer userId=Integer.valueOf(dairlyMessage.get(i).get("user_id1").toString());
					int loginUserId=sysUser.getUserId();
					count=messageService.getUserMessageNum(2,loginUserId,userId);
				}
				
				dairlyMessage.get(0).put("count", count);
				allCount=allCount+count;
				resList.add(dairlyMessage.get(0));
			}
			if(weekMessage!=null&&weekMessage.size()>0){
				int count=0;
				for(int i=0;i<weekMessage.size();i++){
					Integer userId=Integer.valueOf(weekMessage.get(i).get("user_id1").toString());
					int loginUserId=sysUser.getUserId();
					count=messageService.getUserMessageNum(3,loginUserId,userId);
				
				}
				weekMessage.get(0).put("count", count);
				allCount=allCount+count;
				resList.add(weekMessage.get(0));
			}
			if(noteMessage!=null&&noteMessage.size()>0){
				int count=0;
				for(int i=0;i<noteMessage.size();i++){
					Integer userId=Integer.valueOf(noteMessage.get(i).get("user_id1").toString());
					int loginUserId=sysUser.getUserId();
					count=messageService.getUserMessageNum(6,loginUserId,userId);
				}
				noteMessage.get(0).put("count", count);
				allCount=allCount+count;
				resList.add(noteMessage.get(0));
			}
			if(reactionAssistMessage!=null&&reactionAssistMessage.size()>0){
				int count=0;
				for(int i=0;i<reactionAssistMessage.size();i++){
					Integer userId=Integer.valueOf(reactionAssistMessage.get(i).get("user_id1").toString());
					int loginUserId=sysUser.getUserId();
					count=messageService.getUserMessageNum(7,loginUserId,userId);
				}
				reactionAssistMessage.get(0).put("count", count);
				allCount=allCount+count;
				resList.add(reactionAssistMessage.get(0));
			}
			if(deviceAppointMessage!=null&&deviceAppointMessage.size()>0){
				int count=0;
				for(int i=0;i<deviceAppointMessage.size();i++){
					Integer userId=Integer.valueOf(deviceAppointMessage.get(i).get("user_id1").toString());
					int loginUserId=sysUser.getUserId();
					count=messageService.getUserMessageNum(8,loginUserId,userId);
				}
				deviceAppointMessage.get(0).put("count", count);
				allCount=allCount+count;
				resList.add(deviceAppointMessage.get(0));
				
			}
			if(calendarsMessage!=null&&calendarsMessage.size()>0){
				int count=0;
				for(int i=0;i<calendarsMessage.size();i++){
					Integer userId=Integer.valueOf(calendarsMessage.get(i).get("user_id1").toString());
					int loginUserId=sysUser.getUserId();
					count=messageService.getUserMessageNum(9,loginUserId,userId);
					//resList.add(calendarsMessage.get(i));
				}
				calendarsMessage.get(0).put("count", count);
				allCount=allCount+count;
				resList.add(calendarsMessage.get(0));
				
			}
			if(systemMessage!=null&&systemMessage.size()>0){
				int count=0;
				for(int i=0;i<systemMessage.size();i++){
					if(String.valueOf(systemMessage.get(i).get("msg_id")).equals("1")){
						count++;
					}
				}
				systemMessage.get(0).put("count", count);
				allCount=allCount+count;
				resList.add(systemMessage.get(0));
			}
			
			if(marketMessage!=null&&marketMessage.size()>0){
				int count=0;
				for(int i=0;i<marketMessage.size();i++){
					if(String.valueOf(marketMessage.get(i).get("msg_id")).equals("1")){
						count++;
					}
				}
				marketMessage.get(0).put("count", count);
				allCount=allCount+count;
				resList.add(marketMessage.get(0));
			}
			
			if(knowledgeMessage!=null&&knowledgeMessage.size()>0){
				int count=0;
				for(int i=0;i<knowledgeMessage.size();i++){
					if(String.valueOf(knowledgeMessage.get(i).get("msg_id")).equals("1")){
						count++;
					}
				}
				knowledgeMessage.get(0).put("count", count);
				allCount=allCount+count;
				resList.add(knowledgeMessage.get(0));
			}
			
			if(patentMessage!=null&&patentMessage.size()>0){
				int count=0;
				for(int i=0;i<patentMessage.size();i++){
					if(String.valueOf(patentMessage.get(i).get("msg_id")).equals("1")){
						count++;
					}
				}
				patentMessage.get(0).put("count", count);
				allCount=allCount+count;
				resList.add(patentMessage.get(0));
			}

			BeanToMapUtil btmu=new BeanToMapUtil();
			List<ExpertAssist> expertAssists=getAllExpertAssistList();
			if(expertAssists!=null&&expertAssists.size()>0){
				for(ExpertAssist expertAssist:expertAssists){
					Map<String, Object> map=btmu.objectToMap(expertAssist);
					map.put("count", expertAssist.getType());
					allCount=allCount+expertAssist.getType();
					map.put("msg_detailtype_id", 15);
					String dt=map.get("assistDate").toString();
					SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
					SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					map.put("sender_date", sdf2.format(sdf1.parse(dt)));
					resList.add(map);
				}
			}
			
			List<ProjectAssist> projectAssists=projectAssistService.getNewProjectAssist(sysUser.getUserId());
			if(projectAssists!=null&&projectAssists.size()>0){
				for(ProjectAssist projectAssist:projectAssists){
					if(projectAssist.getProjectBasicInfo().getProjectPictures()!=null&&projectAssist.getProjectBasicInfo().getProjectPictures().size()>0){
						ProjectPicture projectPicture=projectAssist.getProjectBasicInfo().getProjectPictures().get(0);
						projectPicture.setProjectPictureName(proImageUrl+projectPicture.getProjectPictureName());
						projectAssist.getProjectBasicInfo().getProjectPictures().remove(0);
						projectAssist.getProjectBasicInfo().getProjectPictures().add(projectPicture);
					}
					Map<String, Object> map=btmu.objectToMap(projectAssist);
					if(sysUser.getSysRole().getRoleId()==11){
						List<ProjectAssist> unreadProjectAssist=projectAssistService.getAllUnread(sysUser.getUserId(), projectAssist.getProjectBasicInfo().getProId());
						for (ProjectAssist projectAssist3 : unreadProjectAssist) {
							String noticeMan = projectAssist3.getNoticeMan();
							if(noticeMan!=null && noticeMan.contains(sysUser.getUserId().toString())){
								isNotice ="true";
							}
						}
						map.put("is_notice",isNotice);
						map.put("msg_detailtype_id", 16);
						map.put("count", unreadProjectAssist.size());
						allCount=allCount+unreadProjectAssist.size();
						List<ProjectNumber> list = projectNumberService.getAllNumberByProId(projectAssist.getProjectBasicInfo().getProId());
						map.put("projectNumber", list);
						String dt=map.get("assistDate").toString();
						SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
						SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						map.put("sender_date", sdf2.format(sdf1.parse(dt)));
						resList.add(map);
					}else{
						List<ProjectNumber> list = projectNumberService.getAllNumberByProId(projectAssist.getProjectBasicInfo().getProId());
						List<Integer> numList=new ArrayList<Integer>();
						if(list!=null&&list.size()>0){
							for(ProjectNumber projectNumber:list){
								numList.add(projectNumber.getSysUser().getUserId());
							}
						}
						if(numList.contains(sysUser.getUserId())){
							List<ProjectAssist> unreadProjectAssist=projectAssistService.getAllUnread(sysUser.getUserId(), projectAssist.getProjectBasicInfo().getProId());
							for (ProjectAssist projectAssist3 : unreadProjectAssist) {
								String noticeMan = projectAssist3.getNoticeMan();
								if(noticeMan!=null && noticeMan.contains(sysUser.getUserId().toString())){
									isNotice ="true";
								}
							}
							map.put("is_notice",isNotice);
							map.put("msg_detailtype_id", 16);
							map.put("count", unreadProjectAssist.size());
							allCount=allCount+unreadProjectAssist.size();
							map.put("projectNumber", list);
							String dt=map.get("assistDate").toString();
							SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
							SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							map.put("sender_date", sdf2.format(sdf1.parse(dt)));
							resList.add(map);
						}
						
					}
					
				}
			}

			listSortingForMapTypeElement(resList);
			
			List<ProjectAssist> teamAssists=projectAssistService.getNewTeamAssist(sysUser.getUserId());
			SysUser sysUser2 = sysUserService.getById(Long.valueOf(sysUser.getUserId()));
			
			if(teamAssists.size()==0){
				ProjectAssist projectAssist2 = new ProjectAssist();
				ProjectBasicInfo projectBasicInfo = new ProjectBasicInfo();
				projectBasicInfo.setProId(0);
				projectBasicInfo.setProName(sysUser2.getSysSigningAgency().getUserName()+"讨论组");
				projectAssist2.setProjectBasicInfo(projectBasicInfo);
				Map<String, Object> map1=btmu.objectToMap(projectAssist2);
				map1.put("msg_detailtype_id", 17);
				resList.add(map1);
			}else{
				for (ProjectAssist projectAssist2 : teamAssists) {
					
					ProjectBasicInfo projectBasicInfo = new ProjectBasicInfo();
					projectBasicInfo.setProId(0);
					projectBasicInfo.setProName(sysUser2.getSysSigningAgency().getUserName()+"讨论组");
					projectAssist2.setProjectBasicInfo(projectBasicInfo);
					Map<String, Object> map1=btmu.objectToMap(projectAssist2);
					List<ProjectAssist> unreadTeamAssist=projectAssistService.getAllUnread(sysUser.getUserId(), 0);
					for (ProjectAssist projectAssist3 : unreadTeamAssist) {
						String noticeMan = projectAssist3.getNoticeMan();
						if(noticeMan!=null && noticeMan.contains(sysUser.getUserId().toString())){
							isNotice ="true";
						}
					}
					
					map1.put("msg_detailtype_id", 17);
					map1.put("count", unreadTeamAssist.size());
					allCount=allCount+unreadTeamAssist.size();
					map1.put("is_notice",isNotice);
//					map1.put("teamName", sysUser2.getSysSigningAgency().getUserName()+"讨论组");
					String dt=map1.get("assistDate").toString();
					SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
					SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					map1.put("sender_date", sdf2.format(sdf1.parse(dt)));
					resList.add(map1);
				}
			}
			List<Integer> countList=new ArrayList<Integer>();
			countList.add(allCount);
			//removeSortKey(resList);
			res.setResultData(resList);
			res.setErrCode(0);
			res.setResultList(countList);
		} catch (Exception e) {
			
			res.setErrCode(2);
			res.setErrMsg("错误信息"+e.getMessage());
		}
		return res;
		
	}
	
	
//	/**
//	 * @Description Web发送消息关键字列表
//	 * @author llwangi
//	 * @version V1.0
//	 * @throws Exception 
//	 * @date 2018年1月20日 上午11:49:52
//	 */
//	@RequestMapping(value = "/message/getAllMessageUnreadNum", method = RequestMethod.GET)
//	private ResultVo getAllMessageUnreadNum() throws Exception{
//		ResultVo res=new ResultVo();
//		try {
//			LoginController login = new LoginController();
//			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
//			int count=0;
//			int messageNum1=messageService.getMessageUnread(sysUser.getUserId());
//			int messageNum2=messageService.getBaseMessageUnread(sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId());
//			count=messageNum1+messageNum2;
//			BeanToMapUtil btmu=new BeanToMapUtil();
//			List<ExpertAssist> expertAssists=getAllExpertAssistList();
//			if(expertAssists!=null&&expertAssists.size()>0){
//				for(ExpertAssist expertAssist:expertAssists){
//				count=count+expertAssist.getType();
//				}
//			}
//			
//			List<ProjectAssist> projectAssists=projectAssistService.getNewProjectAssist(sysUser.getUserId());
//			if(projectAssists!=null&&projectAssists.size()>0){
//				for(ProjectAssist projectAssist:projectAssists){
//				
//				
//					if(sysUser.getSysRole().getRoleId()==11){
//						List<ProjectAssist> unreadProjectAssist=projectAssistService.getAllUnread(sysUser.getUserId(), projectAssist.getProjectBasicInfo().getProId());
//						count=count+unreadProjectAssist.size();
//					}else{
//						List<ProjectNumber> list = projectNumberService.getAllNumberByProId(projectAssist.getProjectBasicInfo().getProId());
//						List<Integer> numList=new ArrayList<Integer>();
//						if(list!=null&&list.size()>0){
//							for(ProjectNumber projectNumber:list){
//								numList.add(projectNumber.getSysUser().getUserId());
//							}
//						}
//						if(numList.contains(sysUser.getUserId())){
//							List<ProjectAssist> unreadProjectAssist=projectAssistService.getAllUnread(sysUser.getUserId(), projectAssist.getProjectBasicInfo().getProId());
//							count=count+unreadProjectAssist.size();
//						}
//						
//					}
//					
//				}
//			}
//
//			
//			//removeSortKey(resList);
//			res.setResultData(count);
//			res.setErrCode(0);
//		} catch (Exception e) {
//			
//			res.setErrCode(2);
//			res.setErrMsg("错误信息"+e.getMessage());
//		}
//		return res;
//		
//	}
	
	/**
	 * @Description Web发送消息关键字列表
	 * @author llwangi
	 * @version V1.0
	 * @throws Exception 
	 * @date 2018年1月20日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getAllMessageUnreadNum", method = RequestMethod.GET)
	private ResultVo getAllMessageUnreadNum() throws Exception{
		ResultVo res=new ResultVo();
		try {
			
			ResultVo result=getAllMessageMap();
			int count=(int) result.getResultList().get(0);
			//removeSortKey(resList);
			res.setResultData(count);
			res.setErrCode(0);
		} catch (Exception e) {
			
			res.setErrCode(2);
			res.setErrMsg("错误信息"+e.getMessage());
		}
		return res;
		
	}
	
	private void listSortingForMapTypeElement(List<Map<String, Object>> columns) throws ParseException{  
		if (null != columns && columns.size()>0) {
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   Collections.sort(columns,new Comparator<Map>() {
			    @Override
			    public int compare(Map o1, Map o2) {
			     int ret = 0;
			     try {
			    	 Date dt2=df.parse(o2.get("sender_date").toString());
			    	 Date dt1=df.parse(o1.get("sender_date").toString());
			    	 if(dt1.getTime()>dt2.getTime())//比较时间大小,如果dt1大于dt2  
			            {  
			    		 ret=-1;
			          }else if(dt1.getTime()==dt2.getTime()){
			             ret=0;
			          }else{
			        	  ret=1;  
			          }
			    	 
			      //比较两个对象的顺序，如果前者小于、等于或者大于后者，则分别返回-1/0/1
			     // ret = df.parse(o2.get("sender_date").toString()).compareTo(df.parse(o1.get("sender_date").toString()));
			     } catch (ParseException e) {
			      e.printStackTrace();
			     }
			     return ret;
			    }
			   });
	    
		}
	}  
	
	private void removeSortKey(List<Map<String, Object>> columns) {  
	    for (Map<String, Object> column : columns) {  
	        column.remove("sender_date");  
	    }  
	}  
	
	 public  int compare_date(String DATE1, String DATE2) {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	                System.out.println("dt1 在dt2前");
	                return 1;
	            } else if (dt1.getTime() < dt2.getTime()) {
	                System.out.println("dt1在dt2后");
	                return 0;
	            } else {
	                return 0;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return 0;
	    }
	/**
	 * @Description Web发送消息关键字列表
	 * @author llwangi
	 * @version V1.0
	 * @throws Exception 
	 * @date 2018年1月23日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getMessageList/{msgDetailtypeId}", method = RequestMethod.GET)
	private ResultVo getMessageList(@PathVariable("msgDetailtypeId") Integer msgDetailtypeId) {
		ResultVo res =new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			List<Map<String, Object>> resList=new ArrayList<Map<String,Object>>();
			if(msgDetailtypeId==8){
				List<Map<String, Object>> deviceList=messageService.getMessageList(msgDetailtypeId, sysUser.getUserId());
				if(deviceList!=null&&deviceList.size()>0){
					for(Map<String, Object> map:deviceList){
						if(map.get("buss_id")!=null){
							int bussId=Integer.valueOf(map.get("buss_id").toString());
							Map<String, Object> deviceAppointment=messageService.getAppoint(bussId);
							if(deviceAppointment!=null){
								map.put("device_name", deviceAppointment.get("device_name"));
								map.put("start_date", deviceAppointment.get("start_date"));
								map.put("end_date", deviceAppointment.get("end_date"));
								map.put("reaction_name", deviceAppointment.get("reaction_name"));
								map.put("reaction_group_name", deviceAppointment.get("reaction_group_name"));
								if(map.get("user_image1")!=null&&!"".equals("user_image1")){
									map.put("user_image1", imagePath+map.get("user_image1"));
								}
								resList.add(map);
							}
						}
						
						int messageId=Integer.valueOf(map.get("message_id").toString());
						List<MessageRecipients> messageRecipientses=messageRecipientsService.getByMeeasgeId(messageId);
						if (messageRecipientses!= null && messageRecipientses.size() > 0) {
							for (MessageRecipients messageRecipient : messageRecipientses) {
								MsgState msgState = new MsgState();
								msgState.setMsgId(2);
								msgState.setMsgState("已读");
								messageRecipient.setMsgState(msgState);
								messageRecipientsService.update(messageRecipient);
							}
						}
					}
					
				}
				
			}
			else if(msgDetailtypeId==9){
				resList=messageService.getMessageList(msgDetailtypeId, sysUser.getUserId());
				for(Map<String, Object> map:resList){
					if(map.get("buss_id")!=null){
						int bussId=Integer.valueOf(map.get("buss_id").toString());
						Calendars calendars=calendarsService.getById(bussId);
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
						}else{
							map.put("calendars", null);
						}
					}
					if(map.get("user_image1")!=null&&!"".equals("user_image1")){
						map.put("user_image1", imagePath+map.get("user_image1"));
					}
				}
			}else if(msgDetailtypeId==12||msgDetailtypeId==13||msgDetailtypeId==14){
				List<Map<String, Object>> deviceList=messageService.getMarketMessageList(msgDetailtypeId, sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId());
				if(deviceList!=null&&deviceList.size()>0){
					for(int i=0;i<deviceList.size();i++){
						if(deviceList.get(i).get("buss_id")!=null){
							int bussId=Integer.valueOf(deviceList.get(i).get("buss_id").toString());
							MarketAssist  marketAssist=marketAssistService.getbyId(bussId);
							if(marketAssist!=null){
								if(deviceList.get(i).get("user_image")!=null&&!"".equals(deviceList.get(i).get("user_image"))){
									deviceList.get(i).put("user_image1", imagePath+deviceList.get(i).get("user_image"));
								}
								resList.add(deviceList.get(i));
							}
						}
						
					}
					
				}
			}else{
				resList=messageService.getMessageList(msgDetailtypeId, sysUser.getUserId());
				if(resList!=null&&resList.size()>0){
					for(int i=0;i<resList.size();i++){
						if(resList.get(i).get("user_image1")!=null&&!"".equals(resList.get(i).get("user_image1"))){
							resList.get(i).put("user_image1", imagePath+resList.get(i).get("user_image1"));
						}
					}
					
				}
			}
			if(resList!=null&&resList.size()>0){
				res.setErrCode(0);
				res.setResultData(resList);
			}else{
				res.setErrCode(1);
				res.setErrMsg("没有数据");
			}
			return res;
		} catch (Exception e) {
			res.setErrCode(2);
			res.setErrMsg("系统错误:"+e.getMessage());
			return res;
		}
	}
	
	/**
	 * @Description Web发送消息关键字列表
	 * @author llwangi
	 * @version V1.0
	 * @throws Exception 
	 * @date 2018年1月23日 上午11:49:52
	 */
	@RequestMapping(value = "/message/getUserMessageList/{msgDetailtypeId}/{userId}", method = RequestMethod.GET)
	private ResultVo getUserMessageList(@PathVariable("msgDetailtypeId") Integer msgDetailtypeId,@PathVariable("userId") Integer userId) {
		ResultVo res =new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			int loginUserId=sysUser.getUserId();
			List<Map<String, Object>> resList=new ArrayList<Map<String,Object>>();
			if(msgDetailtypeId==1){
				resList=messageService.getUserMessageList(msgDetailtypeId, loginUserId,userId);
				if(resList!=null&&resList.size()>0){
					for(int i=0;i<resList.size();i++){
						if(resList.get(i).get("user_image1")!=null&&!"".equals(resList.get(i).get("user_image1"))){
							resList.get(i).put("user_image1", imagePath+resList.get(i).get("user_image1"));
						}
					}
					
				}
			}
			if(resList!=null&&resList.size()>0){
				res.setErrCode(0);
				res.setResultData(resList);
			}else{
				res.setErrCode(1);
				res.setErrMsg("没有数据");
			}
			return res;
		} catch (Exception e) {
			res.setErrCode(2);
			res.setErrMsg("系统错误:"+e.getMessage());
			return res;
		}
	}
	
	/**
	 * @Description app业务消息List删除
	 * @author llwangi
	 * @version V1.0
	 * @throws Exception 
	 * @date 2018年1月23日 上午11:49:52
	 */
	@RequestMapping(value = "/message/deleteMessageList/{msgDetailtypeId}/{id}", method = RequestMethod.GET)
	private ResultVo deleteMessageList(@PathVariable("msgDetailtypeId") Integer msgDetailtypeId,@PathVariable("id") Integer id) {
		ResultVo res =new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			if(msgDetailtypeId==1){
				messageService.updateReviceMessage(sysUser.getUserId(),id);
			}else if(msgDetailtypeId==12||msgDetailtypeId==13||msgDetailtypeId==14){
				messageService.updateMarketList(msgDetailtypeId, sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId());
			}else if(msgDetailtypeId!=1&&msgDetailtypeId!=16&&msgDetailtypeId!=12&&msgDetailtypeId!=13&&msgDetailtypeId!=14){
				messageService.updateList(msgDetailtypeId, sysUser.getUserId());
			}else{
				List<ProjectAssist> projectAssists=projectAssistService.getAllMessageContentList(sysUser.getUserId(),id);
				if(projectAssists!=null&&projectAssists.size()>0){
					for(ProjectAssist projectAssist:projectAssists){
						String msgDelete="";
						if(projectAssist.getMsgDelete()!=null&&!"".equals(projectAssist.getMsgDelete())){
							msgDelete=projectAssist.getMsgDelete()+","+sysUser.getUserId();
						}else{
							msgDelete=sysUser.getUserId().toString();
						}
						projectAssistService.updateMessage(msgDelete,projectAssist.getProjectAssistId());
					}
				}
				//res.setResultData(projectAssists);
			}
			res.setErrCode(0);
		} catch (Exception e) {
			res.setErrCode(2);
			res.setErrMsg("系统错误:"+e.getMessage());
			
		}
		return res;
	}
	
	/**
	 * @Description app业务消息单条删除
	 * @author llwangi
	 * @version V1.0
	 * @throws Exception 
	 * @date 2018年1月23日 上午11:49:52
	 */
	@RequestMapping(value = "/message/deleteMessage/{msgDetailtypeId}/{id}", method = RequestMethod.GET)
	private ResultVo deleteMessage(@PathVariable("msgDetailtypeId") Integer msgDetailtypeId,@PathVariable("id") Integer id) {
		ResultVo res =new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			if(msgDetailtypeId==12||msgDetailtypeId==13||msgDetailtypeId==14){
				messageService.updateMarket(sysUser.getUserId(),id,sysUser.getSysSigningAgency().getAgencyId());
			}else{
				messageService.updateMessageForDelete(id, sysUser.getUserId());
			}
			res.setErrCode(0);
//			else{
//				List<ProjectAssist> projectAssists=projectAssistService.getAllMessageContentList(sysUser.getUserId(),id);
//				if(projectAssists!=null&&projectAssists.size()>0){
//					for(ProjectAssist projectAssist:projectAssists){
//						String msgDelete="";
//						if(projectAssist.getMsgDelete()!=null&&!"".equals(projectAssist.getMsgDelete())){
//							msgDelete=projectAssist.getMsgDelete()+","+sysUser.getUserId();
//						}else{
//							msgDelete=sysUser.getUserId().toString();
//						}
//						projectAssistService.updateMessage(msgDelete,projectAssist.getProjectAssistId());
//					}
//				}
//				res.setResultData(projectAssists);
//			}
		} catch (Exception e) {
			res.setErrCode(2);
			res.setErrMsg("系统错误:"+e.getMessage());
			
		}
		return res;
	}
	
	public List<ExpertAssist> getAllExpertAssistList() {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer agencyId=sysUser.getSysSigningAgency().getAgencyId();
		String readMan=sysUser.getUserId()+"_"+agencyId;
		try {
			List<ExpertAssist> expertAssists=new ArrayList<ExpertAssist>();
			String deleteMan=sysUser.getUserId()+"_"+agencyId;
			
			expertAssists=expertAssistService.getAllAssistList(sysUser.getUserId(),agencyId,deleteMan);
			List<ExpertAssist> resList=new ArrayList<ExpertAssist>();
			if(expertAssists!=null&&expertAssists.size()>0){
				for(ExpertAssist expertAssist:expertAssists){
					List<String> msgDeletes=new ArrayList<String>();
					if(expertAssist.getMsgDelete()!=null){
		    			//String deleteMan=sysUser.getUserId()+"_"+sysUser.getSysSigningAgency().getAgencyId();
		    			String[] ss=expertAssist.getMsgDelete().split(",");
		    			msgDeletes=Arrays.asList(ss);
		    		}
					if(msgDeletes.size()==0||!msgDeletes.contains(deleteMan)){
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
						if(expertAssist.getSysUser().getUserImage()==null&&!"".equals(expertAssist.getSysUser().getUserImage())){
							SysUser user=sysUserService.getByBasUser(Long.valueOf(expertAssist.getSysUser().getUserId()),expertAssist.getSysSigningAgency().getAgencyId());
							if(user.getExpert()!=null){
								Expert expert=expertService.getById(user.getExpert().getExpertId());
								if(expert.getImgUrl()!=null&&!"".equals(expert.getImgUrl())){
									expertAssist.getSysUser().setUserImage(urlPath+expert.getImgUrl());
								}
								String name=expert.getExpertNameCh()==null?expert.getExpertNameEn():expert.getExpertNameCh();
								expertAssist.getSysUser().setRealname(name);
							}
						}else{
							expertAssist.getSysUser().setUserImage(userImageUrl+expertAssist.getSysUser().getUserImage());
						}
						if(expertAssist.getSysUser1().getUserImage()==null&&!"".equals(expertAssist.getSysUser1().getUserImage())){
							SysUser user=sysUserService.getByBasUser(Long.valueOf(expertAssist.getSysUser1().getUserId()),expertAssist.getSysSigningAgency1().getAgencyId());
							if(user.getExpert()!=null){
								Expert expert=expertService.getById(user.getExpert().getExpertId());
								if(expert.getImgUrl()!=null&&!"".equals(expert.getImgUrl())){
									expertAssist.getSysUser1().setUserImage(urlPath+expert.getImgUrl());
								}
								String name=expert.getExpertNameCh()==null?expert.getExpertNameEn():expert.getExpertNameCh();
								expertAssist.getSysUser1().setRealname(name);
							}
						}else{
							expertAssist.getSysUser1().setUserImage(userImageUrl+expertAssist.getSysUser1().getUserImage());
						}
						resList.add(expertAssist);
					}
					
				}
			}
			if(resList!=null&&resList.size()>0){
				 return resList;
			}else{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		   
	}
	
	
	
	/**
	 * @Description 获取详细信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
//	@RequestMapping(value = "/messageDaily/{id}", method = RequestMethod.GET)
//	public ResultVo getByMessages(@PathVariable("id") Integer id) {
//		
//		ResultVo resultVo = new ResultVo();
//
//		LoginController login = new LoginController();
//		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
//				.get(0);
//		Integer userId = sysUser.getUserId();
//
//		List list = new ArrayList();
//		List<Integer> msgIdList = messageService.getDayPaperByUser(id,userId);
//		
//		for (Integer messageId : msgIdList) {
//			
//			FileUtil fileUtil = new FileUtil();
//
//			   Message message = messageService.getByReceiverId(messageId, userId);
//				String messageContent=message.getMessageContent();
//				String[] contents=messageContent.split("@#&");
//				String firstContent="";
//				String secondContent="";
//				String thirdContent="";
//				String fouthContent="";
//				String remark="";
//				
//					 firstContent=contents[0];
//					 secondContent=contents[1];
//					 thirdContent=contents[2];
//					 remark=contents[3];
//				
//			
//				if (message != null) {
//					// 点击详情后成已读
//				
//					SysUser sysUser1 = message.getSysUser();
//					if(sysUser1.getUserImage()!=null){
//						sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
//						message.setSysUser(sysUser1);
//					}
//					List<Attachment> attachments = message.getAttachments();
//					if (attachments!= null && attachments.size() > 0) {
//						for (Attachment attachment : attachments) {
//							String fileName = attachment.getAttachmentName();
//							attachment.setFileType(fileName.substring(fileName
//									.lastIndexOf(".") + 1));
//							attachment.setAttachmentName(fileName);
//							attachment.setUrlPath(urlPath + fileName);
//							attachment.setSize(fileUtil.getFileSize(filePath
//									+ fileName));
//						}
//					}
//					
//					List<MessageRecipients> messageRecipientses = message.getMessageRecipientses();
//					
//					if (messageRecipientses!= null && messageRecipientses.size() > 0) {
//						for (MessageRecipients messageRecipient : messageRecipientses) {
//							MsgState msgState = new MsgState();
//							msgState.setMsgId(2);
//							msgState.setMsgState("已读");
//							messageRecipient.setMsgState(msgState);
//							messageRecipientsService.update(messageRecipient);
//							SysUser sysUser2 = messageRecipient.getSysUser();
//							if(sysUser2.getUserImage()!=null){
//								sysUser2.setUserImage(userImageUrl+sysUser2.getUserImage());
//							}
//						}
//					}
//					Map<String, Object> resMap=new HashMap<String, Object>();
//					
//					resMap.put("message", message);
//					resMap.put("firstContent", firstContent);
//					resMap.put("secondContent", secondContent);
//					resMap.put("thirdContent", thirdContent);
//					resMap.put("fouthContent", fouthContent);
//					resMap.put("remark", remark);
//					list.add(resMap);
//				}
//		} 
//		resultVo.setErrCode(0);
//		resultVo.setErrMsg("get message success");
//		resultVo.setResultData(list);
//		resultVo.setResultList(msgIdList);
//		return resultVo;
//	}
	
	
	

}
