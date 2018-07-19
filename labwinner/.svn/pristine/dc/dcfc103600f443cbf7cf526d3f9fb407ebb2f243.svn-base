package com.labwinner.controller;

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
import com.labwinner.domain.SysUser;
import com.labwinner.domain.TeamMomentsLike;
import com.labwinner.domain.TeamProjMomentsComment;
import com.labwinner.service.SysUserService;
import com.labwinner.service.TeamMomentsLikeService;
import com.labwinner.vo.TeamMomentsLikeVo;

@RestController
public class TeamMomentsLikeController {
	
	private static Logger logger = LoggerFactory
			.getLogger(TeamMomentsLikeController.class);

	@Autowired
	private TeamMomentsLikeService teamMomentsLikeService;
	@Autowired
	SysUserService sysUserService;
	
	
	/**
	 * @Description 取消点赞
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/projectMomentsLike/delete/{id}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo =  new ResultVo();
		teamMomentsLikeService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
		return resultVo;
	}
	
	
	/**
	 * @Description 新增点赞
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/projectMomentsLike", method = RequestMethod.POST)
	public ResultVo save(@RequestBody TeamMomentsLikeVo teamMomentsLikeVo) {
		ResultVo resultVo =  new ResultVo();
		if(teamMomentsLikeVo!=null){
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			
			TeamMomentsLike teamMomentsLike = new TeamMomentsLike();
			teamMomentsLike.setMomentsInfoId(teamMomentsLikeVo.getMomentsInfoId());
			teamMomentsLike.setMomentsType(teamMomentsLikeVo.getMomentsType());
			teamMomentsLike.setSysUser(sysUser);
			
			teamMomentsLikeService.save(teamMomentsLike);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			resultVo.setResultData(teamMomentsLike.getTeamMomentsLikeId());
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("save fail");
		return resultVo;
	}
	
	
	/**
	 * @Description 点赞集合
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/projectMomentsLike/{momentsType}/{momentsInfoId}", method = RequestMethod.GET)
	public ResultVo getList(@PathVariable("momentsType") Integer momentsType,
			@PathVariable("momentsInfoId") Integer momentsInfoId) {
		ResultVo resultVo =  new ResultVo();
		List<TeamMomentsLike> likeList= teamMomentsLikeService.getByMomentsId(momentsType, momentsInfoId);
		if(likeList!=null){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(likeList);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("find result is null");
		return resultVo;
		
	}

}
