package com.labwinner.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.activemq.NoteConsumer;
import com.labwinner.activemq.NoteProducer;
import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Attachment;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.MsgDetailtype;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.MsgType;
import com.labwinner.domain.Note;
import com.labwinner.domain.NoteAssistant;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectNumber;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionImage;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.domain.SysUser;
import com.labwinner.domain.SysUserRole;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
import com.labwinner.service.AttachmentService;
import com.labwinner.service.MessageRecipientsService;
import com.labwinner.service.MessageService;
import com.labwinner.service.NoteAssistantService;
import com.labwinner.service.NoteService;
import com.labwinner.service.ProjectNumberService;
import com.labwinner.service.ReactionImageService;
import com.labwinner.service.ReactionProcessService;
import com.labwinner.service.ReactionService;
import com.labwinner.service.SysUserRoleService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.FileUtil;
import com.labwinner.vo.ObjectVo;
import com.labwinner.vo.UserIdListVO;

@RestController
public class NoteController {

	private static Logger logger = LoggerFactory
			.getLogger(NoteController.class);

	@Autowired
	private NoteService noteService;

	@Autowired
	private ReactionImageService reactionImageService;

	@Autowired
	private NoteAssistantService noteAssistService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private ProjectNumberService projectNumberService;

	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Autowired
	private ReactionProcessService reactionProcessService;
	
	// 注入随手记队列
	@Bean
	public Queue queue() {
		return new ActiveMQQueue("labwinner.note.queue");
	}
		
	// 注入随手记消息生产者
	@Autowired  
	private NoteProducer noteProducer;
	
	// 注入随手记消息消费者
	@Autowired  
	private NoteConsumer noteConsumer;
	
	// 注入随手记极光推送
	@Autowired  
	private Jpush jpush;

	@Value("${web.msgFile-path}")
	String filePath;

	@Value("${web.msgFileUrl-path}")
	String urlPath;
	
	
	@Autowired
	private MessageService  messageService;
	
	@Autowired
	private MessageRecipientsService messageRecipientsService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private ReactionService reactionService;
	

	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/note/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		Note note = noteService.getById(id);
		if (null != note) {
			List<ReactionImage> images = new ArrayList<ReactionImage>(
					note.getReactionImages());
			for (ReactionImage image : images) {
				image.setReactionImage(urlPath + image.getReactionImage());
			}
			LoginController login = new LoginController();
			 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			List<MessageRecipients> messageRecipients=messageRecipientsService.getRecipientsByBussId(sysUser.getUserId(),id,Integer.valueOf(6));
			if (messageRecipients!= null && messageRecipients.size() > 0) {
				for (MessageRecipients messageRecipient : messageRecipients) {
					MsgState msgState = new MsgState();
					msgState.setMsgId(2);
					//msgState.setMsgState("已读");
					messageRecipient.setMsgState(msgState);
					messageRecipientsService.update(messageRecipient);
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(note);
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("find fail");
		return resultVo;
	}
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/note/getByProcessId/{id}", method = RequestMethod.GET)
	public ResultVo getByProcessId(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List<Note> notes = noteService.getByProcessId(id);
		if(notes !=null && notes.size()>0){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(notes);
			return resultVo;
		}else{
			Integer reactionId = reactionProcessService.getById(id).getReaction().getReactionId();
			List<Note> noteList = noteService.getByReactionId(reactionId);
			if(noteList !=null && noteList.size()>0){
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(noteList);
				return resultVo;
			}
			resultVo.setErrCode(2);
			resultVo.setErrMsg("find is null");
			return resultVo;
		}
		
	}


	/**
	 * @Description 获取项目成员负责人
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/note/getUsers/{id}", method = RequestMethod.GET)
	public ResultVo getUser(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 Integer userId = sysUser.getUserId();
		 List<SysUser> sysUsers = new ArrayList<SysUser>();
		 ProjectBasicInfo project = null;
		Reaction reaction = reactionService.getByReactionId(id);
		if(reaction!=null){
			project = reaction.getProjectBasicInfo();
		}
		if(project==null){
			List<SysUserRole> userList = sysUserRoleService.getUser(11L);
			
			if(userList!=null && userList.size()>0){
				for(int i = 0 ;i<userList.size();i++){
					if(userList.get(i).getSysUser()!=null){
						if(userId != userList.get(i).getSysUser().getUserId()){
							sysUsers.add(userList.get(i).getSysUser());
						}
					}
				}
			}
		}else{
			List<ProjectNumber> numberList = projectNumberService.getByProId(project.getProId());
			if(numberList!=null && numberList.size()>0){
				for(int i = 0 ;i<numberList.size();i++){
					if(numberList.get(i).getSysUser()!=null){
					if(userId != numberList.get(i).getSysUser().getUserId()){
						sysUsers.add(numberList.get(i).getSysUser());
					}
					}
				}
			}
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(sysUsers);
		return resultVo;

	}

	/**
	 * @Description 保存随手记协助表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/note/saveUsers", method = RequestMethod.POST)
	public ResultVo getUser(@RequestBody UserIdListVO userIdList) {
		ResultVo resultVo = new ResultVo();
		List<Integer> idList = userIdList.getIdList();
		Integer id = userIdList.getId();
		NoteAssistant noteAssistant = new NoteAssistant();
		Note note = new Note();
		for (Integer userId : idList) {
			note.setNoteId(id);
			noteAssistant.setNote(note);
			noteAssistant.setProNumberId(userId);
			noteAssistService.save(noteAssistant);
		}
		//----------------------------消息推送开始--------------------------------------------------
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Note note1=noteService.getById(id);
		List<Map<String, String>> listMap=new ArrayList<Map<String,String>>();
//		listMap=teamAssistService.getByProcessId(processId);
		Message message=new Message();
		message.setMessageTitle(sysUser.getRealname()+"实验随手记向你求助");
		String noteContent=note1.getNoteContent()==null?"":note1.getNoteContent();
		String noteName=note1.getNoteName()==null?"":note1.getNoteName();
		String reactionName=note1.getReaction().getReactionName()==null?"":note1.getReaction().getReactionName();
		String processName=note1.getReactionProcess().getProcessName()==null?"":note1.getReactionProcess().getProcessName();
		String content="";
		content="随手记名称:"+noteName+"\r\n    实验名称:"+ reactionName
				+"\r\n   实验步骤:"+processName+"\r\n   求助内容:"+noteContent;
		message.setMessageContent(content);
		
		MsgType msgType=new MsgType();
		msgType.setMsgTypeId(2);
		message.setMsgType(msgType);
		MsgDetailtype msgDetailtype=new MsgDetailtype();
		msgDetailtype.setMsgDetailtypeId(6);
		message.setMsgDetailtype(msgDetailtype);
		
		message.setFlag(0);
	//	message.setMsgState(msgState);
		message.setSysUser(sysUser);
		message.setSenderDate(new Date());
		message.setFlag(0);
		message.setBussId(id);
	
//		MsgType msgType=new MsgType();
//		msgType.setMsgTypeId(2);
//		message.setMsgType(msgType);
//		MsgState msgState=new MsgState();
//		msgState.setMsgId(1);
//		message.setFlag(0);
//		message.setMsgState(msgState);
//		message.setSysUser(sysUser);
//		message.setSenderDate(new Date());
//		message.setFlag(0);
		messageService.save(message);
		List<String> reactionImage=reactionImageService.getById(id);
		if(reactionImage!=null&&reactionImage.size()>0){
			for(int i=0;i<reactionImage.size();i++){
				Attachment attachment=new Attachment();
				attachment.setMessage(message);
				attachment.setAttachmentName(reactionImage.get(i));
				attachmentService.save(attachment);
			}
		}
		if(idList!=null && idList.size()>0){
			for(Integer userId :idList){
				MessageRecipients messageRecipients=new MessageRecipients();
				MsgState msgState=new MsgState();
				msgState.setMsgId(1);
				messageRecipients.setFlag(0);
				messageRecipients.setMessage(message);
				SysUser sysUser1=sysUserService.getById(Long.valueOf(String.valueOf(userId)));
				messageRecipients.setSysUser(sysUser1);
				messageRecipients.setFlag(0);
				messageRecipients.setMsgState(msgState);
				messageRecipientsService.save(messageRecipients); 
			}
		}
		//极光推送app端消息
		//DeviceAppointmentController deviceAppointmentController=new DeviceAppointmentController();
		content="[实验随手记]"+"  随手记名称："+noteName+"  实验名称："+ reactionName
				+"  实验步骤 :"+processName+"  求助内容："+noteContent;
		message.setMessageContent(content);
		JPushData pushData =getJPushData(message,idList);
		Map<String, Integer> extraMap=new HashMap<String, Integer>();
		extraMap.put("type", LabConstans.NOTE_TYPE);
		extraMap.put("userId", sysUser.getUserId());
		extraMap.put("messageId",id);
		extraMap.put("messageType", 3);
		try {
			//jpush.sendMessageAndNotification_Pall(pushData, null);
			jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//			jpush.sendMessageAndNotification_Pall(pushData, null);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		//----------------------------消息推送结束------------------------------------------------------
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}

	/**
	 * @Description 根据主键删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/note/delete/{id}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		reactionImageService.delete(id);
		noteAssistService.delete(id);
		noteService.delete(id);
		List<Integer> idList =new ArrayList<Integer>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		List<MessageRecipients> messageRecipients=messageRecipientsService.getRecipientsByUserId(sysUser.getUserId(),id, 6);
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
		resultVo.setErrMsg("delete success");
		return resultVo;
	}

	
	/**
	 * @Description 根据主键删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/note/delete/{id}", method = RequestMethod.DELETE)
	public ResultVo deleteWeb(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		reactionImageService.delete(id);
		noteAssistService.delete(id);
		noteService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
		return resultVo;
	}
	/**
	 * @Description 项目负责人，项目成员下拉框
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
//	@RequestMapping(value = "/note/user", method = RequestMethod.GET)
//	public ResultVo getUser() {
//		ResultVo resultVo = new ResultVo();
//		List<Object> list = new ArrayList<Object>();
//		List<SysUser> sysUsers = sysUserService.getAll();
//		List<ProjectNumber> projectNumbers = projectNumberService.getAll();
//		list.add(projectNumbers);
//		list.add(sysUsers);
//		resultVo.setErrCode(0);
//		resultVo.setErrMsg("find success");
//		resultVo.setResultData(list);
//		return resultVo;
//	}

	/**
	 * @Description 获取对象List
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/note", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		//TODO 根据当前用户去查询全部随手记
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer id = sysUser.getUserId();
		List<Note> notes = noteService.getAll(id);
		if (notes == null) {
			String message = "对象不存在(id:" + notes + ")";
			logger.warn(message);
			resultVo.setErrCode(2);
			resultVo.setErrMsg("notes is null");
			resultVo.setResultData(notes);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(notes);
		return resultVo;
	}
	
	
	/**
	 * @Description 获取对象List
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/note/list/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAllList(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		ResultVo resultVo = new ResultVo();
		//TODO 根据当前用户去查询全部随手记
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer id = sysUser.getUserId();
		List<Note> notes=new ArrayList<Note>();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if(keyword==null||"".equals(keyword)||"undefined".equals(keyword)){
			notes= noteService.getAll(id);
		}else{
			notes=noteService.getByName(keyword,id);
		}
		
		if (notes == null) {
			String message = "对象不存在(id:" + notes + ")";
			logger.warn(message);
			resultVo.setErrCode(2);
			resultVo.setErrMsg("notes is null");
			resultVo.setResultData(notes);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(new  PageInfo(notes));
		return resultVo;
	}

	/**
	 * @Description 添加对象
	 * @author xux
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/note", method = RequestMethod.POST)
	public ResultVo save(
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			@RequestParam(value = "imgs", required = false) String[] imgs,
			@RequestParam(value = "noteId", required = false) String noteId,
			@RequestParam(value = "proId", required = false) String proId,
			@RequestParam(value = "reactionId", required = false) String reactionId,
			@RequestParam(value = "reactionProcessId", required = false) String reactionProcessId,
			@RequestParam("noteName") String noteName,
			@RequestParam(value = "noteContent", required = false) String noteContent) throws IOException {
		// TODO userId 从token中获取
		ResultVo resultVo = new ResultVo();

		ProjectBasicInfo projectBasicInfo = new ProjectBasicInfo();
		Reaction reaction = new Reaction();
		ReactionProcess reactionProcess = new ReactionProcess();

		if (proId != null) {
			projectBasicInfo.setProId(Integer.valueOf(proId));
		}
		if (reactionId != null) {
			reaction.setReactionId(Integer.valueOf(reactionId));
		}
		if (reactionProcessId != null) {
			reactionProcess.setReactionProcessId(Integer
					.valueOf(reactionProcessId));
		}
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		if (noteId == null || "".equals(noteId)) {
			Note note = new Note();
			note.setProjectBasicInfo(projectBasicInfo);
			note.setReaction(reaction);
			note.setReactionProcess(reactionProcess);
			note.setNoteName(noteName);
			note.setNoteContent(noteContent);
			note.setSysUser(sysUser);
			note.setCreateDate(new Date());
			noteService.save(note);
			saveNote(note, files, filePath);

			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			resultVo.setResultData(note.getNoteId());
			// resultVo.setResultData(note.getProjectBasicInfo().getProId());
			return resultVo;
		} else {
			Note note1 = new Note();
			note1.setProjectBasicInfo(projectBasicInfo);
			note1.setReaction(reaction);
			note1.setReactionProcess(reactionProcess);
			note1.setNoteName(noteName);
			note1.setNoteContent(noteContent);
			note1.setSysUser(sysUser);
			Date date = new Date();
			note1.setCreateDate(date);
			Integer id = Integer.valueOf(noteId);
			note1.setNoteId(id);

			if (null != files && files.length > 0) {
				saveNote(note1, files, filePath);
			}
			if (null != imgs && imgs.length > 0) {
				for (String img : imgs) {
					new File(filePath+img.substring(img.lastIndexOf("/") + 1)).delete();
					reactionImageService.deleteByImg(img.substring(img
							.lastIndexOf("/") + 1));
				}

			}
			noteService.update(note1);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			resultVo.setResultData(note1.getNoteId());
			return resultVo;
		}
	}

	/**
	 * @Description 根据url删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionImage/delete/{id}", method = RequestMethod.GET)
	public ResultVo deleteByUrl(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		String imageName = reactionImageService.getByImageId(id);
		if (null != imageName) {
			new File(filePath + imageName).delete();
			reactionImageService.deleteById(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			return resultVo;
		}else{
			resultVo.setErrCode(2);
			resultVo.setErrMsg("delete fail");
			return resultVo;
		}
	}

	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionImage/{id}", method = RequestMethod.GET)
	public List<String> getById2(@PathVariable("id") Integer id) {
		List<String> reactionImages = reactionImageService.getById(id);
		if (reactionImages == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return reactionImages;
	}

	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/noteAssistant/{id}", method = RequestMethod.GET)
	public List<NoteAssistant> getById3(@PathVariable("id") Integer id) {
		List<NoteAssistant> noteAssistants = noteAssistService.getById(id);
		if (noteAssistants == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return noteAssistants;
	}

	/**
	 * @Description 保存图片， 权限用户
	 * @author xux
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveNote(Note note, MultipartFile[] files, String path) throws IOException {

		Integer id = note.getNoteId();
		// 保存上传图片
		List<String> fileNameList = new FileUtil().imageUpload(files, path);
		ReactionImage reactionImage = new ReactionImage();
		for (String fileName : fileNameList) {
			reactionImage.setReactionImage(fileName);
			reactionImage.setNote(note);
			reactionImageService.save(reactionImage);
		}
	}
	
	/**
	 * @Description 生产随手记消息
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年7月5日
	 */
	@RequestMapping(value = "/note/sendMsg/{msg}", method = RequestMethod.POST)
	public ObjectVo sendMsg(@PathVariable String msg) {
		ObjectVo objectVo = new ObjectVo();
		try {
			noteProducer.send(msg);
			objectVo.setValue("1");
			objectVo.setText("true");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("发送随手记消息出错！");
			objectVo.setValue("0");
			objectVo.setText("false");
		}
		return objectVo;
	}
	
	/**
	 * @Description 消费随手记消息
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年7月5日
	 */
	@RequestMapping(value = "/note/receiveMsg", method = RequestMethod.GET)
//	@JmsListener(destination = "labwinner.note.queue")
	public String receiveQueue(String text) {
		System.out.println("-----------------------");
		System.out.println("NoteConsumer　receiveQueue: " + text);
		return text;
	}
	
	/**
	 * @Description 随手记极光推送
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年7月5日
	 */
	@RequestMapping(value = "/note/jpush/{msg}", method = RequestMethod.POST)
	public ObjectVo jpush(@PathVariable String msg) {
		ObjectVo objectVo = new ObjectVo();
		try {
			jpush.sendSimpleMessageAndNotification_Pall(msg);
			objectVo.setValue("1");
			objectVo.setText("true");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("发送随手记消息出错！");
			objectVo.setValue("0");
			objectVo.setText("false");
		}
		return objectVo;
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
				jPushData.setTag("随手记消息");
				jPushData.setTitle(msg.getMessageTitle());
				jPushData.setAlias(usernames);
				return jPushData;
			}
			return null;
		}
	
}
