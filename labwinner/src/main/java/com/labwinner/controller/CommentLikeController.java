package com.labwinner.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.AssistComment;
import com.labwinner.domain.CommentLike;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.service.CommentLikeService;
import com.labwinner.service.SysUserService;

@RestController
public class CommentLikeController {

	private static Logger logger = LoggerFactory
			.getLogger(CommentLikeController.class);

	@Autowired
	private CommentLikeService commentLikeService;
	@Autowired
	SysUserService sysUserService;
	
	/**
	 * @Description 取消点赞
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/commentLike/saveOrDelete/{id}", method = RequestMethod.GET)
	public ResultVo saveOrDelete(@PathVariable("id") Integer id) {
		ResultVo resultVo =  new ResultVo();
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysSigningAgency agency= sysUser.getSysSigningAgency();
		
		CommentLike like = commentLikeService.getByUser(sysUser.getUserId(), agency.getAgencyId(), id);
		if(like==null){
			AssistComment assistComment = new AssistComment();
			assistComment.setAssistCommentId(id);
			
			CommentLike commentLike = new CommentLike();
			commentLike.setSysUser(sysUser);
			commentLike.setSysSigningAgency(agency);
			commentLike.setAssistComment(assistComment);
			
			commentLikeService.save(commentLike);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			return resultVo;
		}else{
			commentLikeService.delete(like.getLikeId());
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete success");
			return resultVo;
		}
	}
	
	
	/**
	 * @Description 点赞集合
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/commentLike/getNum/{id}", method = RequestMethod.GET)
	public ResultVo getList(@PathVariable("id") Integer id) {
		
		ResultVo resultVo =  new ResultVo();
		Integer likeNum= commentLikeService.getNum(id);
		logger.error("likeNum",likeNum);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(likeNum);
		return resultVo;

	}
	
	
	/**
	 * @Description 新增点赞
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/commentLike/save/{id}", method = RequestMethod.GET)
	public ResultVo save(@PathVariable("id") Integer id) {
		
		ResultVo resultVo =  new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysSigningAgency agency= sysUser.getSysSigningAgency();
		
		AssistComment assistComment = new AssistComment();
		assistComment.setAssistCommentId(id);
		
		CommentLike commentLike = new CommentLike();
		commentLike.setSysUser(sysUser);
		commentLike.setSysSigningAgency(agency);
		commentLike.setAssistComment(assistComment);
		
		commentLikeService.save(commentLike);
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save success");
		return resultVo;

	}
	
	
}
