package com.labwinner.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.labwinner.common.ResultVo;
import com.labwinner.domain.ProjectMoments;
import com.labwinner.domain.SysUser;
import com.labwinner.domain.TeamMoments;
import com.labwinner.domain.TeamMomentsImage;
import com.labwinner.service.MomentsCommentService;
import com.labwinner.service.SysUserService;
import com.labwinner.service.TeamMomentsImageService;
import com.labwinner.service.TeamMomentsLikeService;
import com.labwinner.service.TeamMomentsService;
import com.labwinner.util.FileUtil;
import com.labwinner.vo.MomentsVo;

@RestController
public class TeamMomentsController {
	
	private static Logger logger = LoggerFactory
			.getLogger(TeamMomentsController.class);

	@Autowired
	private TeamMomentsService teamMomentsService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private TeamMomentsImageService momentsImageService;
	
	@Autowired
    private MomentsCommentService momentsCommentService;
	
	@Autowired
	private TeamMomentsLikeService teamMomentsLikeService;
	
	@Value("${note.upload-path}")
	String filePath;

	@Value("${note.url-path}")
	String urlPath;
	
	
	/**
	 * @Description 新增信息
	 * @author xux
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/teamMoments", method = RequestMethod.POST)
	public ResultVo save(
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "urlTitle", required = false) String urlTitle,
			@RequestParam(value = "urlIcon", required = false) String urlIcon,
			@RequestParam(value = "publishContent", required = false) String publishContent) throws IOException
			{
		ResultVo resultVo =  new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		
		TeamMoments teamMoments = new TeamMoments();
		teamMoments.setPublishContent(publishContent);
		teamMoments.setPublishTime(new Date());
		teamMoments.setSysUser(sysUser);
		teamMoments.setUrl(url);
		teamMoments.setUrlIcon(urlIcon);
		teamMoments.setUrlTitle(urlTitle);
		teamMomentsService.save(teamMoments);
		
		//保存图片表
		if(files!=null && files.length>0){
			saveMomentsImage(teamMoments, files, filePath);
		}
		return resultVo;
		
	}
	
	/**
	 * @Description 新增信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/teamMoments/saveUrl", method = RequestMethod.POST)
	public ResultVo saveUrl(@RequestBody MomentsVo momentsVo)
			{
		ResultVo resultVo =  new ResultVo();
		
		String publishContent = momentsVo.getPublishContent();
		String url = momentsVo.getUrl();
		String urlIcon = momentsVo.getUrlIcon();
		String urlTitle = momentsVo.getUrlTitle();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		
		TeamMoments teamMoments = new TeamMoments();
		teamMoments.setPublishContent(publishContent);
		teamMoments.setPublishTime(new Date());
		teamMoments.setSysUser(sysUser);
		teamMoments.setUrl(url);
		teamMoments.setUrlIcon(urlIcon);
		teamMoments.setUrlTitle(urlTitle);
		teamMomentsService.save(teamMoments);
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save success");
		return resultVo;
		
	}
	
	
	/**
	 * @Description 朋友圈列表分页
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/teamMoments/{page}/{pageSize}", method = RequestMethod.GET)
	public ResultVo getprojectMoments(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize){
		ResultVo resultVo =  new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		
		Integer userId = sysUser.getUserId();
		// 列表id
		Integer startCount = (page-1)*pageSize;
		Integer endCount = page*pageSize;
		List<TeamMoments> TeamMomentsList = teamMomentsService.getAll(userId,startCount,endCount);
		if(TeamMomentsList!= null && TeamMomentsList.size()>0){
			for (TeamMoments teamMoments : TeamMomentsList) {
				List<TeamMomentsImage> teamMomentsImages = new ArrayList<TeamMomentsImage>(teamMoments.getTeamMomentsImages());
				if(teamMomentsImages!=null && teamMomentsImages.size()>0){
					for (TeamMomentsImage teamMomentsImage : teamMomentsImages) {
						teamMomentsImage.setImage(urlPath+teamMomentsImage.getImage());
					}
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save moments success");
			resultVo.setResultData(TeamMomentsList);
			return resultVo;
		}else{
			resultVo.setErrCode(2);
			resultVo.setErrMsg("save moments fail");
			return resultVo;
		}
		
	}
	
	/**
	 * @Description 删除信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/teamMoments/delete/{id}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo =  new ResultVo();
		momentsImageService.deleteByMomentsId(id, 2);
		momentsCommentService.deleteByMomentsId(id,2);
		teamMomentsLikeService.deleteByMomentsId(id,2);
		teamMomentsService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
		return resultVo;
		
	}
	
	
	/**
	 * @Description 保存图片
	 * @author xux
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveMomentsImage(TeamMoments teamMoments, MultipartFile[] files, String path) throws IOException {

		// 保存上传图片
		List<String> fileNameList = new FileUtil().imageUpload(files, path);
		TeamMomentsImage momentsImage = new TeamMomentsImage();
		for (String fileName : fileNameList) {
			momentsImage.setImage(fileName);
			momentsImage.setMomentsInfoId(teamMoments.getTeamMomentsId());
			momentsImage.setMomentsType(2);
			momentsImageService.save(momentsImage);
		}
	}


}
