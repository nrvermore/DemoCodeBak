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

import com.github.pagehelper.PageHelper;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.Note;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectMoments;
import com.labwinner.domain.ProjectMomentsRelation;
import com.labwinner.domain.ReactionImage;
import com.labwinner.domain.SysRole;
import com.labwinner.domain.SysUser;
import com.labwinner.domain.TeamMomentsImage;
import com.labwinner.domain.TeamMomentsLike;
import com.labwinner.domain.TeamProjMomentsComment;
import com.labwinner.service.MomentsCommentService;
import com.labwinner.service.ProjectMomentsRelationService;
import com.labwinner.service.ProjectMomentsService;
import com.labwinner.service.SysUserService;
import com.labwinner.service.TeamMomentsImageService;
import com.labwinner.service.TeamMomentsLikeService;
import com.labwinner.util.FileUtil;
import com.labwinner.vo.MomentsVo;

@RestController
public class ProjectMomentsController {
	
	private static Logger logger = LoggerFactory
			.getLogger(ProjectMomentsController.class);

	@Autowired
	private ProjectMomentsService projectMomentsService;
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	ProjectMomentsRelationService momentsRelationService;
	
	@Autowired
	TeamMomentsImageService momentsImageService;
	
	@Autowired
    MomentsCommentService momentsCommentService;
	
	@Autowired
	private TeamMomentsLikeService teamMomentsLikeService;
	
	@Value("${moments.upload-path}")
	String filePath;

	@Value("${moments.url-path}")
	String urlPath;
	
	@Value("${sysUserPhone.url-path}")
	String userImageUrl;
	

	/**
	 * @Description 新增信息
	 * @author xux
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/projectMoments", method = RequestMethod.POST)
	public ResultVo save(
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			@RequestParam(value = "ids", required = false) String ids,
			@RequestParam(value = "shareType", required = false) Integer shareType,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "urlTitle", required = false) String urlTitle,
			@RequestParam(value = "urlIcon", required = false) String urlIcon,
			@RequestParam(value = "urlType", required = false) Integer urlType,
			@RequestParam(value = "publishContent", required = false) String publishContent) throws IOException {
		
		ResultVo resultVo =  new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		//保存项目圈信息
		ProjectMoments projectMoments = new ProjectMoments();
		projectMoments.setPublishContent(publishContent);
		projectMoments.setUrlIcon(urlIcon);;
		projectMoments.setUrlTitle(urlTitle);
		projectMoments.setUrlType(urlType);
		projectMoments.setUrl(url);
		projectMoments.setShareType(shareType);
		projectMoments.setPublishTime(new Date());
		projectMoments.setSysUser(sysUser);
		projectMoments.setCreateDate(new Date());
		projectMoments.setCreater(sysUser.getUserId()+"");
		projectMomentsService.save(projectMoments);
		
		//保存关系表
		if(ids!=null){
			String[] idList = ids.split(",");
			if (ids != null && idList.length > 0) {
				for (String id : idList) {
					ProjectBasicInfo project = new ProjectBasicInfo();
					project.setProId(Integer.parseInt(id));
					ProjectMomentsRelation momentsRelation = new ProjectMomentsRelation();
					momentsRelation.setProjectBasicInfo(project);
					momentsRelation.setProjectMoments(projectMoments);
					momentsRelationService.save(momentsRelation);
				}
			}
		}
		//保存图片表
		if(files!=null && files.length>0){
			saveMomentsImage(projectMoments, files, filePath);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save moments success");
		return resultVo;
		
	}
	
	/**
	 * @Description 新增信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/projectMoments/saveUrl", method = RequestMethod.POST)
	public ResultVo saveUrl(@RequestBody MomentsVo momentsVo) {
		
		ResultVo resultVo =  new ResultVo();
		
		String ids = momentsVo.getIds();
		String publishContent = momentsVo.getPublishContent();
		String url = momentsVo.getUrl();
		String urlIcon = momentsVo.getUrlIcon();
		String urlTitle = momentsVo.getUrlTitle();
		Integer shareType = momentsVo.getShareType();
		Integer urlType = momentsVo.getUrlType();
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		//保存项目圈信息
		ProjectMoments projectMoments = new ProjectMoments();
		projectMoments.setPublishContent(publishContent);
		projectMoments.setUrl(url);
		projectMoments.setShareType(shareType);
		projectMoments.setUrlIcon(urlIcon);
		projectMoments.setUrlTitle(urlTitle);
		projectMoments.setUrlType(urlType);
		projectMoments.setPublishTime(new Date());
		projectMoments.setSysUser(sysUser);
		projectMoments.setCreateDate(new Date());
		projectMoments.setCreater(sysUser.getUserId()+"");
		projectMomentsService.save(projectMoments);
		
		//保存关系表
		if(ids!=null){
			String[] idList = ids.split(",");
			if (idList != null && idList.length > 0) {
				for (String id : idList) {
					ProjectBasicInfo project = new ProjectBasicInfo();
					project.setProId(Integer.parseInt(id));
					ProjectMomentsRelation momentsRelation = new ProjectMomentsRelation();
					momentsRelation.setProjectBasicInfo(project);
					momentsRelation.setProjectMoments(projectMoments);
					momentsRelationService.save(momentsRelation);
				}
			}
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save moments success");
		return resultVo;
		
	}
	
	
	/**
	 * @Description 朋友圈列表分页
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/projectMoments/{page}/{pageSize}", method = RequestMethod.GET)
	public ResultVo getprojectMoments(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize){
		ResultVo resultVo =  new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 SysRole sysRole=sysUser.getSysRole();
		 String roleName =sysRole.getRoleName();
		sysUser.setUserImage(userImageUrl+sysUser.getUserImage());
		List<Object> sysUsers = new ArrayList<Object>();
		sysUsers.add(sysUser);
		
		Integer userId = sysUser.getUserId();
		// 列表id
		Integer startCount = (page-1)*pageSize;
		Integer endCount = page*pageSize;
		List<ProjectMoments> projectMoments = projectMomentsService.getAll(userId,roleName,startCount,endCount);
		Integer totalCount = projectMomentsService.getTotalCount(userId);
		if(projectMoments!= null && projectMoments.size()>0){
			for (ProjectMoments projectMoments2 : projectMoments) {
				projectMoments2.setTotalCount(totalCount);
				SysUser sysUser1 = projectMoments2.getSysUser();
				if(sysUser1!=null){
					sysUser1.setUserImage(userImageUrl+sysUser1.getUserImage());
				}
				
				List<TeamMomentsImage> teamMomentsImages = projectMoments2.getTeamMomentsImages();
				if(teamMomentsImages!=null && teamMomentsImages.size()>0){
					for (TeamMomentsImage teamMomentsImage : teamMomentsImages) {
						teamMomentsImage.setImage(urlPath+teamMomentsImage.getImage());
					}
				}
				List<TeamMomentsLike> teamMomentsLikes = projectMoments2.getTeamMomentsLikes();
				if(teamMomentsLikes!=null && teamMomentsLikes.size()>0){
					for (TeamMomentsLike teamMomentsLike : teamMomentsLikes) {
						SysUser sysUser2 = teamMomentsLike.getSysUser();
						if(sysUser2!=null){
							sysUser2.setUserImage(userImageUrl+sysUser2.getUserImage());
						}
					}
				}
				List<TeamProjMomentsComment> teamProjMomentsComments = projectMoments2.getTeamProjMomentsComments();
				if(teamProjMomentsComments!=null && teamProjMomentsComments.size()>0){
					for (TeamProjMomentsComment teamProjMomentsComment : teamProjMomentsComments) {
						SysUser sysUser3 = teamProjMomentsComment.getCommentUser();
						if(sysUser3!=null){
							sysUser3.setUserImage(userImageUrl+sysUser3.getUserImage());
						}
					
						SysUser sysUser4 = teamProjMomentsComment.getReplyUser();
						if(sysUser4!=null){
							sysUser4.setUserImage(userImageUrl+sysUser4.getUserImage());
						}
					}
				}
				
			}
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("get moments success");
			resultVo.setResultData(projectMoments);
			resultVo.setResultList(sysUsers);
			return resultVo;
		}else{
			resultVo.setErrCode(2);
			resultVo.setErrMsg("get moments fail");
			resultVo.setResultList(sysUsers);
			return resultVo;
		}
		
	}
			
	
	/**
	 * @Description 删除信息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/projectMoments/delete/{id}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo =  new ResultVo();
		momentsRelationService.delete(id);
		momentsImageService.deleteByMomentsId(id, 1);
		momentsCommentService.deleteByMomentsId(id,1);
		teamMomentsLikeService.deleteByMomentsId(id,1);
		projectMomentsService.delete(id);
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
	private void saveMomentsImage(ProjectMoments projectMoments, MultipartFile[] files, String path) throws IOException {

		// 保存上传图片
		List<String> fileNameList = new FileUtil().imageUpload(files, path);
		TeamMomentsImage momentsImage = new TeamMomentsImage();
		for (String fileName : fileNameList) {
			momentsImage.setImage(fileName);
			momentsImage.setMomentsInfoId(projectMoments.getProjectMomentsId());
			momentsImage.setMomentsType(1);
			momentsImageService.save(momentsImage);
		}
	}

}
