package com.labwinner.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.CommentEntity;
import com.labwinner.domain.CommentType;
import com.labwinner.domain.SysUser;
import com.labwinner.service.CommentService;
import com.labwinner.service.SysUserService;

@RestController
public class CommentController {

	private static Logger logger = LoggerFactory
			.getLogger(CommentController.class);

	@Autowired
	private CommentService commentService;
	
	@Autowired
	SysUserService sysUserService;
	
	
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public ResultVo save(@RequestBody CommentEntity commentEntity) {

		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		
		if(commentEntity!=null && commentEntity.getCommentId()==null){
			commentEntity.setCommentDate(new Date());
			if(commentEntity.getCommentType()==null){
				CommentType commontType = new CommentType();
				commontType.setTypeId(commentEntity.getTypeId());
				commentEntity.setCommentType(commontType);
			}
			commentEntity.setSysUser(sysUser);
			commentService.save(commentEntity);
		}else{
			if(commentEntity.getCommentType()==null){
				CommentType commontType = new CommentType();
				commontType.setTypeId(commentEntity.getTypeId());
				commentEntity.setCommentType(commontType);
			}
			commentEntity.setModifyDate(new Date());
			commentEntity.setModifier(sysUser.getUserId()+"");
			commentService.update(commentEntity);
		}
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save success");
		return resultVo;
	}
	
	
	@RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		
		commentService.delete(id);
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
		return resultVo;
		
	}
	
	
	@RequestMapping(value = "/comment/{moduleId}/{typeId}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("moduleId") Integer moduleId,
			@PathVariable("typeId") Integer typeId) {

		ResultVo resultVo = new ResultVo();
		
		commentService.deleteByModuleId(moduleId,typeId);
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
		return resultVo;
		
	}
	
	
	@RequestMapping(value = "/comment/{moduleId}/{typeId}", method = RequestMethod.GET)
	public ResultVo getByModuleId(@PathVariable("moduleId") Integer moduleId,
			@PathVariable("typeId") Integer typeId) {

		ResultVo resultVo = new ResultVo();
		List<CommentEntity> comments = commentService.getByModuleId(moduleId, typeId);
		
		resultVo.setErrCode(0);
		resultVo.setResultData(comments);
		resultVo.setErrMsg("get success");
		return resultVo;
	
	}
	
}
