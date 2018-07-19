package com.labwinner.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.AssistComment;
import com.labwinner.domain.MarketAssist;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.MsgDetailtype;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.MsgType;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
import com.labwinner.service.AssistCommentService;
import com.labwinner.service.CommentLikeService;
import com.labwinner.service.MarketAssistService;
import com.labwinner.service.MessageRecipientsService;
import com.labwinner.service.MessageService;
import com.labwinner.service.SysUserService;

@RestController
public class AssistCommentController {
	
	private static Logger logger = LoggerFactory
			.getLogger(AssistCommentController.class);

	@Autowired
	private AssistCommentService assistCommentService;
	@Autowired
	private CommentLikeService commentLikeService;
	@Autowired
	SysUserService sysUserService;
	@Autowired
	private MarketAssistService marketAssistService;
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageRecipientsService messageRecipientsService;
	@Autowired
	private Jpush jpush;
	
	/**
	 * @Description 删除评论
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/assistComment/delete/{id}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo =  new ResultVo();
		AssistComment assistComment = assistCommentService.getById(id);
		if(assistComment!=null){
			if(assistComment.getFirstCommentId()==null ||assistComment.getFirstCommentId()==0){
				assistCommentService.deleteByFirstId(id);
				commentLikeService.deleteById(id);
				assistCommentService.delete(id);
			}else{
				assistCommentService.delete(id);
			}
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("删除成功");
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("无此评论");
		return resultVo;
	}
	
	/**
	 * @Description 新增评论
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/assistComment/save", method = RequestMethod.POST)
	public ResultVo save(@RequestBody AssistComment assistComment) {
		ResultVo resultVo =  new ResultVo();
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysSigningAgency agency= sysUser.getSysSigningAgency();
		
		MarketAssist marketAssist = new MarketAssist();
		marketAssist.setMarketAssistId(assistComment.getAssistMarketId());
		MarketAssist market=marketAssistService.getbyId(assistComment.getAssistMarketId());
		int flag=0;
		String jpushUser="";
		assistComment.setSysUser(sysUser);
		int userId=0;
		int type=0;
		List<Integer> userIdList=new ArrayList<Integer>();
		if(assistComment.getReplyCommentId()==null ||assistComment.getReplyCommentId() ==0 ){
			assistComment.setReplyCommentId(0);
			//userIdList.add(sysUser.getUserId());
			int agencyid=market.getSysSigningAgency().getAgencyId();
		
			if(agencyid-99==0){
				flag=1;
			}
			if(market.getSysUser().getUserId().intValue()==sysUser.getUserId().intValue()&&market.getSysSigningAgency().getAgencyId().intValue()==agency.getAgencyId()){
				
			}else{
				jpushUser=market.getSysUser().getUsername()+"@"+market.getSysSigningAgency().getAgencyId();
				userId=market.getSysUser().getUserId();
			}
		}else{
			assistComment.setReplyCommentId(assistComment.getReplyCommentId());
			AssistComment assist=assistCommentService.getById(assistComment.getReplyCommentId());
			//SysUser user=assist.getSysUser();
			//userIdList.add(assist.getSysUser().getUserId());
			int agencyid=assist.getSysSigningAgency().getAgencyId();
			if(agencyid-99==0){
				flag=1;
			}
			if(assist.getSysUser().getUserId().intValue()==sysUser.getUserId().intValue()&&assist.getSysSigningAgency().getAgencyId().intValue()==agency.getAgencyId()){
				
			}else{
				jpushUser=assist.getSysUser().getUsername()+"@"+assist.getSysSigningAgency().getAgencyId();
				userId=assist.getSysUser().getUserId();
			}
			//jpushUser=assist.getSysUser().getUsername()+"@"+assist.getSysSigningAgency().getAgencyId();
		}
		assistComment.setCommentDate(new Date());
		assistComment.setSysSigningAgency(agency);
		assistComment.setMarketAssist(marketAssist);
		
		assistCommentService.save(assistComment);
		if(!"".equals(jpushUser)){
		Message message = new Message();
		//Message message=new Message();
		MsgType msgType=new MsgType();
		msgType.setMsgTypeId(2);
		message.setMsgType(msgType);
		MsgDetailtype msgDetailtype=new MsgDetailtype();
		int keywordId=market.getKeywordId();
		if(keywordId==10){
			type=LabConstans.ASSIST_TYPE;
			msgDetailtype.setMsgDetailtypeId(12);
			message.setMessageContent("[求助市场]:"+assistComment.getCommentContent());
			message.setMessageTitle("[求助市场]:"+assistComment.getCommentContent());
		}else if(keywordId==11){
			type=LabConstans.PATENT_TYPE;
			msgDetailtype.setMsgDetailtypeId(14);
			message.setMessageContent("[专利求助]:"+assistComment.getCommentContent());
			message.setMessageTitle("[专利市场]:"+assistComment.getCommentContent());
		}else{
			type=LabConstans.KNOWLEDGE_TYPE;
			msgDetailtype.setMsgDetailtypeId(13);	
			message.setMessageContent("[文献市场]:"+assistComment.getCommentContent());
			message.setMessageTitle("[文献市场]:"+assistComment.getCommentContent());
		}
		
		message.setMsgDetailtype(msgDetailtype);
		message.setBussId(assistComment.getAssistMarketId());
		message.setFlag(0);
		//message.setMsgState(msgState);
		message.setSysUser(sysUser);
		message.setSenderDate(new Date());
		message.setFlag(0);
		message.setAgencyId(agency.getAgencyId());
		messageService.saveBase(message);
		
		MessageRecipients messageRecipients=new MessageRecipients();
		messageRecipients.setFlag(0);
		messageRecipients.setMessage(message);
		SysUser sysUser1=new SysUser();
		sysUser1.setUserId(userId);
		messageRecipients.setSysUser(sysUser1);
		messageRecipients.setAgencyId(Integer.valueOf(String.valueOf(jpushUser.split("@")[1])));
		MsgState msgState=new MsgState();
		msgState.setMsgId(1);
		messageRecipients.setMsgState(msgState);
		messageRecipientsService.saveBase(messageRecipients); 
		JPushData pushData =getJPushData(message,jpushUser);
		Map<String, Integer> extraMap=new HashMap<String, Integer>();
		extraMap.put("type", type);
		extraMap.put("userId", sysUser.getUserId());
		extraMap.put("messageId", assistComment.getAssistMarketId());
		try {
			jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",flag);
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save success");
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

}
