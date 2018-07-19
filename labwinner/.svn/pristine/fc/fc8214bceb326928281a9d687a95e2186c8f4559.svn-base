package com.labwinner.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Message;
import com.labwinner.domain.ProjectMoments;
import com.labwinner.domain.SysUser;
import com.labwinner.domain.TeamProjMomentsComment;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
import com.labwinner.service.MomentsCommentService;
import com.labwinner.service.ProjectMomentsService;
import com.labwinner.service.SysUserService;
import com.labwinner.vo.TeamProjMomentsCommentVo;

@RestController
public class MomentsCommentController {
	
	private static Logger logger = LoggerFactory
			.getLogger(MomentsCommentController.class);

	@Autowired
	private MomentsCommentService momentsCommentService;
	@Autowired
	SysUserService sysUserService;
	@Autowired
	private ProjectMomentsService projectMomentsService;
	
	@Autowired
	private Jpush jpush;
	
	/**
	 * @Description 取消评论
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/momentsComment/delete/{id}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo =  new ResultVo();
		momentsCommentService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
		return resultVo;
	}
	
	
	/**
	 * @Description 新增评论
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/momentsComment", method = RequestMethod.POST)
	public ResultVo save(@RequestBody TeamProjMomentsCommentVo momentsCommentVo) {
		ResultVo resultVo =  new ResultVo();
		if(momentsCommentVo!=null){
			
			LoginController login = new LoginController();
			TeamProjMomentsComment momentsComment = new TeamProjMomentsComment();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			List<Integer> userList=new ArrayList<Integer>();
			if(momentsCommentVo.getReplyUserId()!=null){
				SysUser replyUser = new SysUser();
				replyUser.setUserId(momentsCommentVo.getReplyUserId());
				momentsComment.setReplyUser(replyUser);
				userList.add(momentsCommentVo.getReplyUserId());
			}else{
				ProjectMoments projectMoments=projectMomentsService.getById(momentsCommentVo.getMomentsInfoId());
				if(projectMoments.getSysUser().getUserId().intValue()!=sysUser.getUserId().intValue()){
					userList.add(projectMoments.getSysUser().getUserId());
				}
			}
			
			momentsComment.setCommentContent(momentsCommentVo.getCommentContent());
			momentsComment.setMomentsInfoId(momentsCommentVo.getMomentsInfoId());
			momentsComment.setMomentsType(momentsCommentVo.getMomentsType());
			momentsComment.setReplyContent(momentsCommentVo.getReplyContent());
			momentsComment.setCommentUser(sysUser);
			
			momentsCommentService.save(momentsComment);
//			List<TeamProjMomentsComment> commentList= momentsCommentService.getByMomentsId(momentsType, momentsInfoId);
//			if(userList.size()>0){
//				Message message = new Message();
//				message.setMessageContent("[圈子]:"+momentsComment.getCommentContent());
//				message.setMessageTitle("[圈子]:"+momentsComment.getCommentContent());
//				JPushData pushData =getJPushData(message,userList);
//				Map<String, Integer> extraMap=new HashMap<String, Integer>();
//				extraMap.put("type", LabConstans.MOMENTS_TYPE);
//				extraMap.put("userId", sysUser.getUserId());
//				try {
//					jpush.sendMessageAndNotification_Json(pushData, null,extraMap,"",0);
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			resultVo.setResultData(momentsComment.getTeamProjMomentsCommentId());
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("save fail");
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
	/**
	 * @Description 评论集合
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/momentsComment/{momentsType}/{momentsInfoId}", method = RequestMethod.GET)
	public ResultVo getList(@PathVariable("momentsType") Integer momentsType,
			@PathVariable("momentsInfoId") Integer momentsInfoId) {
		ResultVo resultVo =  new ResultVo();
		List<TeamProjMomentsComment> commentList= momentsCommentService.getByMomentsId(momentsType, momentsInfoId);
		if(commentList!=null){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(commentList);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("find result is null");
		return resultVo;
		
	}


}
