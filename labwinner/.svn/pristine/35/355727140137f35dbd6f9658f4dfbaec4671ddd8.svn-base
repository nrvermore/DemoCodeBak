package com.labwinner.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.AssistComment;
import com.labwinner.domain.CalendarAssist;
import com.labwinner.domain.Calendars;
import com.labwinner.domain.CommentLike;
import com.labwinner.domain.MarketAssist;
import com.labwinner.domain.MarketAssistImage;
import com.labwinner.domain.MarketKeyword;
import com.labwinner.domain.MediaResource;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
import com.labwinner.service.AssistCommentService;
import com.labwinner.service.CalendarAssistService;
import com.labwinner.service.CalendarsService;
import com.labwinner.service.CommentLikeService;
import com.labwinner.service.JournalArticleService;
import com.labwinner.service.MarketAssistImageService;
import com.labwinner.service.MarketAssistService;
import com.labwinner.service.MarketKeywordService;
import com.labwinner.service.MediaResourceService;
import com.labwinner.service.MessageRecipientsService;
import com.labwinner.service.MessageService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.FileUtil;
import com.labwinner.vo.AppFirstVo;


@RestController
public class MarketAssistController {

	private static Logger logger = LoggerFactory
			.getLogger(MarketAssistController.class);

	@Autowired
	private MarketAssistService marketAssistService;
	
	@Autowired
	private AssistCommentService assistCommentService;
	
	@Autowired
	private CommentLikeService commentLikeService;
	
	@Autowired
	private MarketAssistImageService marketAssistImageService;
	
	@Autowired
	private MarketKeywordService marketKeywordService;
	
	@Autowired
	private MediaResourceService mediaResourceService;
	
	@Autowired
	private MessageRecipientsService messageRecipientsService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	SysUserService sysUserService;
	
	@Autowired
	private JournalArticleService journalArticleService;
	
	@Autowired
	private CalendarsService calendarsService;
	@Autowired
	private CalendarAssistService calendarAssistService;
	
	@Value("${marketAssist.url-path}")
	private String urlPath;
	
	@Value("${marketAssist.upload-path}")
	String filePath;
	
	@Value("${sysUserPhone.url-path}")
	String userImageUrl;
	
	@Autowired
	private Jpush jpush;
	
	/**
	 * @Description 关键字查询求助集合
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssist/getById/{id}", method = RequestMethod.GET)
	public ResultVo getByKeyword(@PathVariable("id") Integer id){
		
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUserLogin = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUserLogin.getUserId();
		SysSigningAgency agency= sysUserLogin.getSysSigningAgency();
		Integer agencyId = agency.getAgencyId();
		MarketAssist marketAssist = marketAssistService.getbyId(id);
		if(marketAssist!=null){
			List<MarketAssistImage> images = marketAssist.getMarketAssistImages();
			if(images!=null &&images.size()>0 ){}
			for (MarketAssistImage marketAssistImage : images) {
				marketAssistImage.setImageName(urlPath+marketAssistImage.getImageName());
			}
			
			List<AssistComment> assistComments = new ArrayList<AssistComment>();
			//用户头像
			SysUser sysUser = marketAssist.getSysUser();
			if(sysUser!=null){
			if(sysUser.getUserImage()!=null){
				sysUser.setUserImage(userImageUrl+sysUser.getUserImage());
			}
			}
			List<AssistComment> firstComments  = assistCommentService.getFirstComments(marketAssist.getMarketAssistId());
			if (firstComments!=null && firstComments.size()>0){
				for (AssistComment assistComment : firstComments) {
					//用户头像
					SysUser sysUser2 = assistComment.getSysUser();
					if(sysUser2!=null){
						if(sysUser2.getUserImage()!=null){
							sysUser2.setUserImage(userImageUrl+sysUser2.getUserImage());
						}
					}
					List<AssistComment> chirdren = assistCommentService.getComments(assistComment.getAssistCommentId());
					if(chirdren!=null && chirdren.size()>0){
						//用户头像
						for (AssistComment assistComment2 : chirdren) {
							SysUser sysUser3 = assistComment2.getSysUser();
							if(sysUser3!=null){
								if(sysUser3.getUserImage()!=null){
									sysUser3.setUserImage(userImageUrl+sysUser3.getUserImage());
								}
							}
						}
						
					}
					assistComment.setChildren(chirdren);
					Integer likeNum = commentLikeService.getNum(assistComment.getAssistCommentId());
					assistComment.setLikeNum(likeNum);
					CommentLike commentLike = commentLikeService.getByUser(userId, agencyId, assistComment.getAssistCommentId());
					if(commentLike==null){
						assistComment.setIsLike(1);
					}else{
						assistComment.setIsLike(0);
					}
					assistComments.add(assistComment);
				}
			}
			marketAssist.setAssistComments(assistComments);
			Integer commentNum = assistCommentService.getNumById(marketAssist.getMarketAssistId());
			marketAssist.setCommentNum(commentNum);
			List<MessageRecipients> messageRecipients=messageRecipientsService.getRecipientsMarketAssist(sysUserLogin.getUserId(),id,sysUserLogin.getSysSigningAgency().getAgencyId());
			if (messageRecipients!= null && messageRecipients.size() > 0) {
				for (MessageRecipients messageRecipient : messageRecipients) {
					MsgState msgState = new MsgState();
					msgState.setMsgId(2);
					//msgState.setMsgState("已读");
					messageRecipient.setMsgState(msgState);
					messageRecipientsService.updateBase(messageRecipient);
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(marketAssist);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("find is null");
		return resultVo;
	}
	
	/**
	 * @Description 关键字查询求助集合
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssist/getAll/{page}/{pageSize}/{keywordId}", method = RequestMethod.GET)
	public ResultVo getByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keywordId") Integer keywordId) {
		
		ResultVo resultVo =  new ResultVo();
		Integer endCount = page*pageSize;
		if(keywordId==0){
			List<MarketAssist> marketAssists = marketAssistService.getAll(0, endCount);
			if(marketAssists!=null&& marketAssists.size()>0){
			for (MarketAssist marketAssist:marketAssists) {
				Integer commentNum = assistCommentService.getNumById(marketAssist.getMarketAssistId());
				marketAssist.setCommentNum(commentNum);
				
				SysUser sysUser = marketAssist.getSysUser();
				List<Map<String, Object>> port = journalArticleService.getAgencyContents(marketAssist.getSysSigningAgency().getAgencyId());
				String agencyName="";
				String staticPath="";
				String imagePath="";
				for(Map<String, Object> content:port){
					if("agencyName".equals(content.get("contents_name").toString())){
						agencyName=content.get("contents_value").toString();
					}
					if("testfile".equals(content.get("contents_name").toString())){
						staticPath=content.get("contents_value").toString();
					}
					if("user_Image".equals(content.get("contents_name").toString())){
						imagePath=content.get("contents_value").toString();
					}
				}
				if(sysUser.getUserImage()!=null){
					sysUser.setUserImage("http://"+staticPath+"/"+agencyName+imagePath+sysUser.getUserImage());
				}
				
				List<MarketAssistImage> images = marketAssist.getMarketAssistImages();
				if(images!=null &&images.size()>0 ){}
				for (MarketAssistImage marketAssistImage : images) {
					marketAssistImage.setImageName(urlPath+marketAssistImage.getImageName());
				}
				
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(marketAssists);
			return resultVo;
			}
			resultVo.setErrCode(2);
			resultVo.setErrMsg("find is null");
			return resultVo;
		}else{
			
			MarketKeyword marketKeyword = marketKeywordService.getById(keywordId);
			if(marketKeyword!=null){
				marketKeyword.setSearchNum(marketKeyword.getSearchNum()+1);
				marketKeywordService.update(marketKeyword);
			}
			
			List<MarketAssist> marketAssists = marketAssistService.getByKeywordId(keywordId, 0, endCount);
			if(marketAssists!=null&& marketAssists.size()>0){
				for (MarketAssist marketAssist:marketAssists) {
					Integer commentNum = assistCommentService.getNumById(marketAssist.getMarketAssistId());
					marketAssist.setCommentNum(commentNum);
					
					SysUser sysUser = marketAssist.getSysUser();
					if(sysUser.getUserImage()!=null){
						sysUser.setUserImage(userImageUrl+sysUser.getUserImage());
					}
					
					List<MarketAssistImage> images = marketAssist.getMarketAssistImages();
					if(images!=null &&images.size()>0 ){}
					for (MarketAssistImage marketAssistImage : images) {
						marketAssistImage.setImageName(urlPath+marketAssistImage.getImageName());
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(marketAssists);
				return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;
		}
	}
	
	/**
	 * @Description App首页
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssist/getFirstPage", method = RequestMethod.GET)
	public ResultVo getFirstPage(){
		ResultVo resultVo =  new ResultVo();
		AppFirstVo appFirstVo = new AppFirstVo();
		
		LoginController login = new LoginController();
		 SysUser sysUser1 = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		 String calendarDate=sdf.format(new Date()); 
		
		//首页求助市场
		List<MarketAssist> marketAssists = marketAssistService.getAll(0, 5);
		//热点新闻
		List<MediaResource> mediaResources = mediaResourceService.getPageList(5);
		
			for (MarketAssist marketAssist:marketAssists) {
				Integer commentNum = assistCommentService.getNumById(marketAssist.getMarketAssistId());
				marketAssist.setCommentNum(commentNum);
				
				SysUser sysUser = marketAssist.getSysUser();
				List<Map<String, Object>> port = journalArticleService.getAgencyContents(marketAssist.getSysSigningAgency().getAgencyId());
				String agencyName="";
				String staticPath="";
				String imagePath="";
				for(Map<String, Object> content:port){
					if("agencyName".equals(content.get("contents_name").toString())){
						agencyName=content.get("contents_value").toString();
					}
					if("testfile".equals(content.get("contents_name").toString())){
						staticPath=content.get("contents_value").toString();
					}
					if("user_Image".equals(content.get("contents_name").toString())){
						imagePath=content.get("contents_value").toString();
					}
				}
				if(sysUser.getUserImage()!=null){
					sysUser.setUserImage("http://"+staticPath+"/"+agencyName+imagePath+sysUser.getUserImage());
				}
				
//				if(sysUser.getUserImage()!=null){
//					sysUser.setUserImage(userImageUrl+sysUser.getUserImage());
//				}
				
				List<MarketAssistImage> images = marketAssist.getMarketAssistImages();
				if(images!=null &&images.size()>0 ){}
				for (MarketAssistImage marketAssistImage : images) {
					marketAssistImage.setImageName(urlPath+marketAssistImage.getImageName());
				}
				
			}
			
			List<Calendars> calendars=calendarsService.getUnStartCalendars(sysUser1.getUserId());
			List<Calendars> calendarsList = null;
			if(calendars!=null&&calendars.size()>5){
				calendarsList = calendars.subList(0, 5);
			}else{
				calendarsList = calendars;
			}

			
			 if(calendars!=null&&calendarsList.size()>0){
				 for(Calendars calendar:calendarsList){
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
			
			appFirstVo.setMarketAssists(marketAssists);
			appFirstVo.setMediaResources(mediaResources);
			appFirstVo.setCalendars(calendarsList);
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(appFirstVo);
			return resultVo;
		
	}
	
	/**
	 * @Description 用户求助集合
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssist/getUserAssist/{page}/{pageSize}", method = RequestMethod.GET)
	public ResultVo getByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize) {
		
		ResultVo resultVo =  new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		SysSigningAgency agency= sysUser.getSysSigningAgency();
		Integer agencyId = agency.getAgencyId();
		Integer endCount = page*pageSize;
	
		List<MarketAssist> marketAssists = marketAssistService.getUser(userId, agencyId, 0, endCount);
		
		if(marketAssists!=null&& marketAssists.size()>0){
			for (MarketAssist marketAssist:marketAssists) {
				Integer commentNum = assistCommentService.getNumById(marketAssist.getMarketAssistId());
				marketAssist.setCommentNum(commentNum);
				
//				List<Map<String, Object>> port = journalArticleService.getAgencyContents(marketAssist.getSysSigningAgency().getAgencyId());
//				String agencyName="";
//				String staticPath="";
//				String imagePath="";
//				for(Map<String, Object> content:port){
//					if("agencyName".equals(content.get("contents_name").toString())){
//						agencyName=content.get("contents_value").toString();
//					}
//					if("testfile".equals(content.get("contents_name").toString())){
//						staticPath=content.get("contents_value").toString();
//					}
//					if("user_Image".equals(content.get("contents_name").toString())){
//						imagePath=content.get("contents_value").toString();
//					}
//				}
//				if(sysUser.getUserImage()!=null){
//					sysUser.setUserImage("http://"+staticPath+"/"+agencyName+imagePath+sysUser.getUserImage());
//				}
				
				if(sysUser.getUserImage()!=null){
					sysUser.setUserImage(userImageUrl+sysUser.getUserImage());
				}
				
				List<MarketAssistImage> images = marketAssist.getMarketAssistImages();
				if(images!=null &&images.size()>0 ){}
				for (MarketAssistImage marketAssistImage : images) {
					marketAssistImage.setImageName(urlPath+marketAssistImage.getImageName());
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(marketAssists);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("find is null");
		return resultVo;
		
	}
	
	
	/**
	 * @Description 删除评论
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssist/delete/{id}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo =  new ResultVo();
		List<AssistComment> firstComments  = assistCommentService.getFirstComments(id);
		if(firstComments!=null && firstComments.size()>0){
			for (AssistComment assistComment : firstComments) {
				commentLikeService.deleteById(assistComment.getAssistCommentId());
			}
		}
		assistCommentService.deleteById(id);
		marketAssistImageService.delete(id);
		marketAssistService.delete(id);
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		SysSigningAgency agency= sysUser.getSysSigningAgency();
		Integer agencyId = agency.getAgencyId();
		List<MessageRecipients> messageRecipients=messageRecipientsService.getRecipientsMarketAssist(userId,id,agencyId);
		if(messageRecipients!=null&&messageRecipients.size()>0){
			for(MessageRecipients mr:messageRecipients){
				messageRecipientsService.deleteBase(mr.getMsgRecipientsId());
				messageService.deleteBase(mr.getMessage().getMessageId());
			}
		}
		String jpushUser=sysUser.getUsername()+"@"+agencyId;
		Message message=new Message();
		message.setMessageContent("delete");
		message.setMessageTitle("delete");
		JPushData pushData =getJPushData(message,jpushUser);
		Map<String, Integer> extraMap=new HashMap<String, Integer>();
		extraMap.put("type", 99);
		extraMap.put("userId", sysUser.getUserId());
		int flag=0;
		if(agencyId-99==0){
			flag=1;
		}
		try {
			jpush.sendMessage_Json(pushData, null,extraMap,"",flag);
		} catch (Exception e) {
			// TODO: handle exception
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
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
	 * @Description 新增求助
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssist/save", method = RequestMethod.POST)
	public ResultVo save(
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			@RequestParam(value = "keywordId", required = false) Integer keywordId,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "assistContent", required = false) String assistContent) {
		
		ResultVo resultVo =  new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysSigningAgency agency= sysUser.getSysSigningAgency();
		
		MarketAssist marketAssist = new MarketAssist();
		marketAssist.setAssistContent(assistContent);
		marketAssist.setTitle(title);
		marketAssist.setIsSolve("false");
		marketAssist.setSysUser(sysUser);
		marketAssist.setKeywordId(keywordId);
		marketAssist.setSysSigningAgency(agency);
		marketAssist.setCreateDate(new Date());
		marketAssistService.save(marketAssist);
		
		if (null != files && files.length > 0) {
			saveImage(marketAssist, files, filePath);
		}
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save success");
		return resultVo;
	}
	
	
	
	/**
	 * @Description 求助已解决
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssist/update/{id}", method = RequestMethod.GET)
	public ResultVo update(@PathVariable("id") Integer id) {
		
		ResultVo resultVo =  new ResultVo();
		
		MarketAssist marketAssist = new MarketAssist();
		marketAssist.setMarketAssistId(id);
		marketAssist.setIsSolve("true");
		marketAssistService.update(marketAssist);
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("update success");
		return resultVo;
	}
	
	
	/**
	 * @Description 保存图片， 权限用户
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveImage(MarketAssist marketAssist, MultipartFile[] files, String path) {

		// 保存上传图片
		List<String> fileNameList = new FileUtil().imageUpload(files, path);
		MarketAssistImage marketAssistImage = new MarketAssistImage();
		marketAssistImage.setMarketAssist(marketAssist);
		for (String fileName : fileNameList) {
			marketAssistImage.setImageName(fileName);
			marketAssistImageService.save(marketAssistImage);
		}
	}

}
