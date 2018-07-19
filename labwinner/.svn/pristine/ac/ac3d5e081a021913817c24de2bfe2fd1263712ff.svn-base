package com.labwinner.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Attachment;
import com.labwinner.domain.Device;
import com.labwinner.domain.DevicePicture;
import com.labwinner.domain.JournalArticle;
import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.KnowledgeProRela;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.MsgDetailtype;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.MsgType;
import com.labwinner.domain.Patent;
import com.labwinner.domain.ProjectAssist;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectDocuments;
import com.labwinner.domain.ProjectMomentsRelation;
import com.labwinner.domain.ProjectNumber;
import com.labwinner.domain.ProjectPicture;
import com.labwinner.domain.ProjectPlan;
import com.labwinner.domain.ProjectRole;
import com.labwinner.domain.ProjectSchedule;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionDesignChemical;
import com.labwinner.domain.ReactionRecord;
import com.labwinner.domain.SysRole;
import com.labwinner.domain.SysUser;
import com.labwinner.jpush.JPushData;
import com.labwinner.service.*;
import com.labwinner.util.Base64Util;
import com.labwinner.util.FileUtil;
import com.labwinner.vo.ProjectBasicInfoVo;
import com.labwinner.vo.ProjectDocumentsVo;
import com.labwinner.vo.ProjectNumberVo;

/**
 * 项目基本信息Controller
 * 
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午6:05:23
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class ProjectBasicInfoController {
	private static Logger logger = LoggerFactory
			.getLogger(ProjectBasicInfoController.class);

	@Autowired
	private ProjectBasicInfoService projectBasicInfoService;

	@Autowired
	private ProjectPictureService projectPictureService;

	@Autowired
	private ProjectMomentsRelationService projectMomentsRelationService;
	
	@Autowired
	private ProjectNumberService projectNumberService;

	@Autowired
	private ProjectPlanService projectPlanService;

	@Autowired
	private ProjectDocumentsService projectDocumentsService;

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private KnowledgeProRelaService knowledgeProRelaService;
	
	@Autowired
	private KnowledgeAccService knowledgeAccDaoService;
	
	@Autowired
	private ReactionService reactionService;
	
	@Autowired
	private ReactionDesignService reactionDesignService;
	
	@Autowired
	private ProjectAssistService projectAssistService;
	
	@Autowired
	private JournalArticleService journalArticleService;
	
	@Autowired
	private PatentService patentService;

	@Value("${projectPicture.upload-path}")
	String filePath;

	@Value("${projectPicture.url-path}")
	String urlPath;

	@Value("${projectDocuments.upload-path}")
	String pfilePath;

	@Value("${projectDocuments.url-path}")
	String purlPath;
	
	@Value("${web.url_path_pdf}")
	String pdfUrlPath;
	
	
	@Value("${web.agency_pdf}")
	String agencyPdf;


	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/projectBasicInfo", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		 LoginController login = new LoginController();
		 //获取登录用户
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 SysRole sysRole=sysUser.getSysRole();
		 String roleName =sysRole.getRoleName();
		 Integer userId=sysUser.getUserId();
		List<ProjectBasicInfo> list = projectBasicInfoService.getAll(userId,roleName);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(list);
		return resultVo;
	}
	/**
	 * 获取所有对象名称列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/projectBasicInfo/getProNameAll", method = RequestMethod.GET)
	public ResultVo getProNameAll() {
		ResultVo resultVo = new ResultVo();
		List<ProjectBasicInfo> list = projectBasicInfoService.getProNameAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(list);
		return resultVo;
	}
	/**
	 * 获取所有对象名称列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/projectBasicInfo/getShareProject", method = RequestMethod.GET)
	public ResultVo getShareProject() {
		ResultVo resultVo = new ResultVo();
		 LoginController login = new LoginController();
		 //获取登录用户
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 Integer userId=sysUser.getUserId();
		List<ProjectBasicInfo> list = projectBasicInfoService.getShareProject(userId);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(list);
		return resultVo;
	}

	/**
	 * 根据主键获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/projectBasicInfo/{id}", method = RequestMethod.GET)
	public ProjectBasicInfo getById(@PathVariable("id") Integer id) {
		ProjectBasicInfo projectBasicInfo = projectBasicInfoService.getById(id);
		if (projectBasicInfo == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);

		}
		return projectBasicInfo;
	}
	/**
	 * 根据项目id获取该项目的项目计划最小开始开始时以及最大结束时间
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/getProjectPlanTime/{id}", method = RequestMethod.GET)
	public ResultVo getProjectPlanTime(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		ProjectPlan projectPlan=new ProjectPlan();
		try {
			ProjectBasicInfo projectBasicInfo = projectBasicInfoService.getProjectPlanTime(id);
			List<ProjectPlan> projectPlans=projectBasicInfo.getProjectPlans();
			List<Date> startDates=new ArrayList<Date>();
			List<Date> endDates=new ArrayList<Date>();
			if(projectPlans!=null&&projectPlans.size()>0){
				for (ProjectPlan projectPlan2 : projectPlans) {
					Date s=projectPlan2.getProPlanStarttime();
					Date e=projectPlan2.getProPlanDeadline();
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
				    String s1 = sdf.format(s);
					Date sdate=sdf.parse(s1);  
					  
					String e1 = sdf.format(e);
					Date edate=sdf.parse(e1);  
					
					startDates.add(sdate);
					endDates.add(edate);
				}
			}
		
			projectPlan.setProjectBasicInfo(projectBasicInfo);
			projectPlan.setProPlanStarttime(Collections.min(startDates));
			projectPlan.setProPlanDeadline(Collections.max(endDates));
			
			res.setErrCode(0);
			res.setErrMsg("查找成功");
			res.setResultData(projectPlan);
			return res;
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("无数据");
			res.setResultData(null);
			return res;
		}
		
	}

	/**
	 * 根据主键获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/projectBasicInfo/getProById/{id}", method = RequestMethod.GET)
	public ProjectBasicInfoVo getProById(@PathVariable("id") Integer id) {
		List<Integer> proIds = new ArrayList<Integer>();
		LoginController login = new LoginController();
		ProjectBasicInfoVo res = new ProjectBasicInfoVo();
		 //获取登录用户
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 SysRole sysRole=sysUser.getSysRole();
		 String roleName =sysRole.getRoleName();
		 logger.error("roleName",roleName);
		 Integer userId=sysUser.getUserId();
		 List<ProjectBasicInfo> projectBasicInfos = projectBasicInfoService.getAll(userId, roleName);
		 if(projectBasicInfos!=null && projectBasicInfos.size()>0){
			 for (ProjectBasicInfo projectBasicInfo : projectBasicInfos) {
				 proIds.add(projectBasicInfo.getProId());
			}
			
		 }
		 if(!proIds.contains(id)){
			 ProjectBasicInfo projectBasicInfo = new ProjectBasicInfo();
			 projectBasicInfo.setResult(4);
			 res.setProjectBasicInfo(projectBasicInfo);
			 return res;
		 }
		
		
		ProjectBasicInfo projectBasicInfo = projectBasicInfoService
				.getNumbers(id);
		if (projectBasicInfo!=null) {
			List<ProjectPicture> logo = new ArrayList<ProjectPicture>(projectBasicInfo.getProjectPictures());
			if(logo!=null && logo.size()>0){
				for (ProjectPicture projectPicture : logo) {
					projectPicture.setProjectPictureName(urlPath+projectPicture.getProjectPictureName());
				}
			}
		List<ProjectDocuments> projectDocuments = new ArrayList<ProjectDocuments>(
				projectBasicInfo.getProjectDocumentses());
		if (projectDocuments != null && projectDocuments.size() > 0) {
			for (ProjectDocuments projectDocument : projectDocuments) {
				if (projectDocuments != null) {
					String fileName=projectDocument.getDocumentName();
					String type=fileName.substring(fileName.lastIndexOf(".") + 1);
					String pdfUrl=null;
					if("pdf".equals(type)){
						pdfUrl=purlPath + fileName;
					}else{
						pdfUrl=projectDocument.getPdfUrl();
					}
					projectDocument.setDocumentUrl(purlPath+projectDocument
							.getDocumentName());
					projectDocument.setPdfUrl(pdfUrl);
				}
			}
		}
//		List<ProjectNumber> projectNumbers=projectBasicInfo.getProjectNumbers();
//		if(projectNumbers!=null&&projectNumbers.size()>0){
//			for(ProjectNumber projectNumber:projectNumbers){
//				if(projectNumber.getProjectRole().getProRoleId()==1){
//					List<Reaction> reactions = reactionService.getUserReactions(userId,projectNumber.getSysUser().getUserId(), id, roleName);
//					projectBasicInfo.setReactions(reactions);
//				}
//			}
//		}
		 
		 
		List<Map<String, Object>> list = projectBasicInfoService
				.getArticleById(id);
		if (list != null && list.size() > 0) {
			
			for (Map<String, Object> map : list) {
				Integer knowledgeId =(Integer)map.get("knowledgeId");
				Integer classifyId = (Integer)map.get("knowledge_classify_id");
				KnowledgeAcc knowledgeAcc = knowledgeAccDaoService.getKnowledgeAcc(knowledgeId, classifyId);
//				if(knowledgeAcc !=null){
//					String uploadFiles = knowledgeAcc.getUploadFiles();
//					Integer knowledgeAccId = knowledgeAcc.getKnowledgeAccId();
//					Integer pdfCount = knowledgeAcc.getPdfCount();
//					map.put("filename", pdfUrlPath+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));
//					map.put("filePath", uploadFiles);
//					map.put("knowledgeAccId", knowledgeAccId);
//					map.put("pdfCount", pdfCount);
//				}
				
				if(knowledgeAcc !=null){
					String uploadFiles = knowledgeAcc.getUploadFiles();
					Integer knowledgeAccId = knowledgeAcc.getKnowledgeAccId();
					if(knowledgeAcc.getKnowledgeClassify().getKnowledgeClassifyId()==1){
						JournalArticle journalArticle=journalArticleService.getById(knowledgeAcc.getKnowledgeId());
						if(journalArticle.getSource()==0){
							map.put("filename", pdfUrlPath+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));	
						}else{
							map.put("filename", agencyPdf+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));	
						}
					
					}else if(knowledgeAcc.getKnowledgeClassify().getKnowledgeClassifyId()==4){
						Patent patent=patentService.getById(knowledgeAcc.getKnowledgeId());
						if(patent.getSource()==0){
							map.put("filename", pdfUrlPath+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));	
						}else{
							map.put("filename", agencyPdf+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));	
						}
					}else{
						map.put("filename", pdfUrlPath+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));
					}
					Integer pdfCount = knowledgeAcc.getPdfCount();
					map.put("filePath", uploadFiles);
					map.put("knowledgeAccId", knowledgeAccId);
					map.put("pdfCount", pdfCount);
				}
			}
			
			res.setListKnowledge(list);
		}
		res.setProjectBasicInfo(projectBasicInfo);
		return res;
		}
		return null;

	}

	/**
	 * 根据主键获取实验
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/projectBasicInfo/getReactionList/{id}", method = RequestMethod.GET)
	public ResultVo getReactionList(@PathVariable("id") Integer id) {
		/*ResultVo resultVo = new ResultVo();
		ProjectBasicInfo projectBasicInfo = projectBasicInfoService.getById(id);
		if (projectBasicInfo == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		List<Reaction> reactionList =projectBasicInfo.getReactions();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(reactionList);
		return resultVo;*/
		
		ResultVo resultVo  = new ResultVo();
		ProjectBasicInfo projectBasicInfo = projectBasicInfoService.getNumbers(id);
	    List<ProjectNumber> projectNumbers=projectBasicInfo.getProjectNumbers();
	    SysUser sysUser1=null;
	    if(projectNumbers!=null){
	    	 for (ProjectNumber projectNumber : projectNumbers) {
	 	    	if (projectNumber.getProjectRole().getProRoleId()==1) {
	 	    		//获取此项目的项目经理对象
	 				 sysUser1=projectNumber.getSysUser();	
	 			}	
	 		}
	    }
	    Integer pmUserId=sysUser1.getUserId();
		
		 //获取登录用户
		 LoginController login = new LoginController();
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 SysRole sysRole=sysUser.getSysRole();
		 String roleName =sysRole.getRoleName();
		 logger.error("roleName",roleName);
		 Integer userId=sysUser.getUserId();
		 
		 List<Reaction> reactionList = reactionService.getUserReactions(userId,pmUserId, id, roleName);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(reactionList);
		return resultVo;
	
	

	}

	/**
	 * @Description 根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/projectBasicInfoPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable String keyword) {
		
		LoginController login = new LoginController();
		 //获取登录用户
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 SysRole sysRole=sysUser.getSysRole();
		 String roleName =sysRole.getRoleName();
		 logger.error("roleName",roleName);
		 Integer userId=sysUser.getUserId();
		 
		 ResultVo resultVo = new ResultVo();
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				List<ProjectBasicInfo> projectBasicInfos = projectBasicInfoService.getAllPageable(userId, roleName, keyword);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(projectBasicInfos));
				return resultVo;
			} else {
				List<ProjectBasicInfo> projectBasicInfos = projectBasicInfoService.getAll(userId, roleName);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(projectBasicInfos));
				return resultVo;
			}
	}
	
	/**
	 * @Description 根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/projectBasicInfoApp/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAppProjects(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable String keyword) {
		
		 ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		 //获取登录用户
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 SysRole sysRole=sysUser.getSysRole();
		 String roleName =sysRole.getRoleName();
		 List<Object> resList=new ArrayList<Object>();
		 resList.add(roleName);
		 logger.error("roleName",roleName);
		 Integer userId=sysUser.getUserId();
		 Integer endCount = page*pageSize;
		
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				List<ProjectBasicInfo> projectBasicInfos = projectBasicInfoService.getBykeyword(userId, roleName, keyword,endCount);
				if(projectBasicInfos!=null&&projectBasicInfos.size()>0){
					for(ProjectBasicInfo projectBasicInfo:projectBasicInfos){
						ProjectBasicInfo projectBasic = projectBasicInfoService.getNumbers(projectBasicInfo.getProId());
						String proNumber=null;
					    List<ProjectNumber> projectNumbers=projectBasic.getProjectNumbers();
					    List<Integer> userList=new ArrayList<Integer>();
					    if(projectNumbers!=null&&projectNumbers.size()>0){
					    	for(ProjectNumber projectNumber:projectNumbers){
					    		userList.add(projectNumber.getSysUser().getUserId());
					    	}
					    }
					    if(!userList.contains(userId)){
					    	proNumber="0";
					    }else{
					    	  List<ProjectAssist> projectAssists=projectAssistService.getAllUnread(userId,projectBasicInfo.getProId());
					    	  if(projectAssists!=null&&projectAssists.size()>0){
					    		  proNumber="1";  
					    	  }else{
					    			proNumber="0"; 
					    	  }
					    }
					    projectBasicInfo.setProNumber(proNumber);
					    if(projectNumbers.size()>1){
					    	projectBasicInfo.setResult(1);
					    }else{
					    	projectBasicInfo.setResult(0);	
					    }
					}
				}
				
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(projectBasicInfos);
				return resultVo;
			} else {
				List<ProjectBasicInfo> projectBasicInfos = projectBasicInfoService.getUserList(userId, roleName,endCount);
				if(projectBasicInfos!=null&&projectBasicInfos.size()>0){
					for(ProjectBasicInfo projectBasicInfo:projectBasicInfos){
						ProjectBasicInfo projectBasic = projectBasicInfoService.getNumbers(projectBasicInfo.getProId());
						String proNumber=null;
					    List<ProjectNumber> projectNumbers=projectBasic.getProjectNumbers();
					    List<Integer> userList=new ArrayList<Integer>();
					    if(projectNumbers!=null&&projectNumbers.size()>0){
					    	for(ProjectNumber projectNumber:projectNumbers){
					    		userList.add(projectNumber.getSysUser().getUserId());
					    	}
					    }
					    if(!userList.contains(userId)){
					    	proNumber="0";
					    }else{
					    	  List<ProjectAssist> projectAssists=projectAssistService.getAllUnread(userId,projectBasicInfo.getProId());
					    	  if(projectAssists!=null&&projectAssists.size()>0){
					    		  proNumber="1";  
					    	  }else{
					    			proNumber="0"; 
					    	  }
					    }
					    projectBasicInfo.setProNumber(proNumber);
					    if(projectNumbers.size()>1){
					    	projectBasicInfo.setResult(1);
					    }else{
					    	projectBasicInfo.setResult(0);	
					    }
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(projectBasicInfos);
				resultVo.setResultList(resList);
				return resultVo;
			}
	}
	
	
	/**
	 * @Description 项目试验分页显示
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/proReaPageable/{page}/{pageSize}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo getPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable Integer id) {
		
		ProjectBasicInfo projectBasicInfo = projectBasicInfoService.getNumbers(id);
	    List<ProjectNumber> projectNumbers=projectBasicInfo.getProjectNumbers();
	    SysUser sysUser1=null;
	    for (ProjectNumber projectNumber : projectNumbers) {
	    	if (projectNumber.getProjectRole().getProRoleId()==1) {
	    		//获取此项目的项目经理对象
				 sysUser1=projectNumber.getSysUser();	
			}	
		}
	    Integer pmUserId=sysUser1.getUserId();
		
		 //获取登录用户
		 LoginController login = new LoginController();
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 SysRole sysRole=sysUser.getSysRole();
		 String roleName =sysRole.getRoleName();
		 logger.error("roleName",roleName);
		 Integer userId=sysUser.getUserId();
		 
		 if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
		 
		 List<Reaction> reactions = reactionService.getUserReactions(userId,pmUserId, id, roleName);
		 return new PageInfo(reactions);
		
		
	}
	
	
	/**
	 * @Description 项目试验分页显示
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/getAppReactions/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAppReactions(@PathVariable Integer id) {
		ResultVo resultVo  = new ResultVo();
		ProjectBasicInfo projectBasicInfo = projectBasicInfoService.getNumbers(id);
	    List<ProjectNumber> projectNumbers=projectBasicInfo.getProjectNumbers();
	    SysUser sysUser1=null;
	    for (ProjectNumber projectNumber : projectNumbers) {
	    	if (projectNumber.getProjectRole().getProRoleId()==1) {
	    		//获取此项目的项目经理对象
				 sysUser1=projectNumber.getSysUser();	
			}	
		}
	    Integer pmUserId=sysUser1.getUserId();
		
		 //获取登录用户
		 LoginController login = new LoginController();
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 SysRole sysRole=sysUser.getSysRole();
		 String roleName =sysRole.getRoleName();
		 logger.error("roleName",roleName);
		 Integer userId=sysUser.getUserId();
		 
		 List<Reaction> reactions = reactionService.getUserReactions(userId,pmUserId, id, roleName);
		 if(reactions!=null && reactions.size()>0){
			 resultVo.setErrCode(0);
			 resultVo.setErrMsg("find success!");
			 resultVo.setResultData(reactions);
			 return resultVo;
		 }
		 resultVo.setErrCode(2);
		 resultVo.setErrMsg("find is null!");
		 return resultVo;
	}
	
	
	
	
	/**
	 * @Description 变更附件
	 * @author 
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/projectBasicInfo/updateFile", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo updaateFile(@RequestBody ProjectDocumentsVo projectDocumentsVo) {
		ResultVo resultVo = new ResultVo();
		List<String> strFiles=projectDocumentsVo.getStrFiles();
		//ProjectDocuments projectDocuments=projectDocumentsVo.getProjectDocuments();
		ProjectBasicInfo projectBasicInfo=projectDocumentsVo.getProjectBasicInfo();
		Integer proId=projectBasicInfo.getProId();
		List<String> filenames=projectDocumentsVo.getFilenames();
		
		if(strFiles != null && strFiles.size()>0){
			
			// 删除文件夹中图片文件
			List<String> filePathList=projectDocumentsService.getByProId(proId);
			
			if(filePathList.size()>0){
				for (String fileName : filePathList) {
					new File(pfilePath + fileName).delete();
				}
			}
			// 删除数据库中图片信息
			projectDocumentsService.delete(proId);
			
			ProjectBasicInfo proBasicInfo = new ProjectBasicInfo();
			proBasicInfo.setProId(proId);
			Base64Util base64Util = new Base64Util();
			ProjectDocuments projectDocuments = new ProjectDocuments();
			for (int i = 0;i<strFiles.size();i++) {
//				for (String fileName : filenames) {
//					fileName=UUID.randomUUID().toString()+fileName;
//				}
				String fileName=UUID.randomUUID().toString()+filenames.get(i);
				String strFile = strFiles.get(i).substring(strFiles.get(i).indexOf(",") + 1);
				base64Util.GeneratePdf(strFile, pfilePath,fileName);
				String type=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
				if(LabConstans.CONVERSION_TYPE.contains(type)){
					projectDocuments.setPdfCount(0);
					projectDocuments.setConversionCount(0);
				}else if("pdf".equals(type)){
					projectDocuments.setPdfCount(2);
				}else{
					projectDocuments.setPdfCount(99);
				}
				projectDocuments.setProjectBasicInfo(proBasicInfo);
				projectDocuments.setDocumentName(fileName);
				projectDocumentsService.save(projectDocuments);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update success");
				return resultVo;
			}
				
		}
				resultVo.setErrCode(1);
				resultVo.setErrMsg("update fail");
				return resultVo;
	}

	
	/**
	 * @Description APP修改图片
	 * @author shg
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/updateprojectBasicInfoPicture", method = RequestMethod.POST)
	public ResultVo updatePicture(@RequestParam(value = "file", required = false) MultipartFile file,
			                      @RequestParam(value = "proId", required = false) Integer proId) {
             ResultVo resultVo = new ResultVo();
             ProjectBasicInfo projectBasicInfo=projectBasicInfoService.getProById(proId);
		    List<ProjectPicture> projectPictures=projectBasicInfo.getProjectPictures();
		    FileUtil fileUtil=new FileUtil();
         	if (projectPictures.size() > 0) {
         			for (ProjectPicture projectPicture : projectPictures) {
						 String fileName=projectPicture.getProjectPictureName();
					     new File(filePath + fileName).delete();
				}
         		// 删除数据库中图片信息
         		projectPictureService.deleteByPro(proId);	
			}
		
		// 保存上传图片
		if (!"".equals(file)||file!=null) {
			// base64保存图片
		//	Base64Util base64Util = new Base64Util();
			ProjectPicture projectPicture = new ProjectPicture();
				projectPicture.setProjectBasicInfo(projectBasicInfo);
				String fileName= fileUtil.uploadFile(file,filePath);
				projectPicture.setProjectPictureName(fileName);
				projectPictureService.save(projectPicture);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update successe");
				
			}
		return resultVo;
		}

	
	
	/**
	 * 保存对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/ProjectBasicInfo", method = RequestMethod.POST)
	public ResultVo save(@RequestBody ProjectBasicInfoVo projectBasicInfoVo) {

		ResultVo resultVo = new ResultVo();
		ProjectBasicInfo projectBasicInfo = projectBasicInfoVo.getProjectBasicInfo();

		List<ProjectNumber> projectNumbers = projectBasicInfoVo.getProjectNumbers();
	//	List<ProjectPlan> projectPlans = projectBasicInfoVo.getProjectPlans();
		//List<String> proDocFiles = projectBasicInfoVo.getProDocFiles();

	//	List<String> projectLogos = projectBasicInfoVo.getProjectLogos();
	//	String fileName = projectBasicInfoVo.getFileName();
		LoginController login = new LoginController();
		 //获取登录用户
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		ProjectSchedule projectSchedule = new ProjectSchedule();
		projectBasicInfo.setCreateDate(new Date());
		projectSchedule.setProScheduleId(1);
		projectBasicInfo.setProjectSchedule(projectSchedule);
		projectBasicInfo.setCreater(sysUser);
		projectBasicInfoService.save(projectBasicInfo);
	//	projectBasicInfo.getProId();
	//	saveProjectPicture(projectBasicInfo, projectLogos);
       if(projectNumbers!=null){
		 for (ProjectNumber projectNumber : projectNumbers) {
			projectNumber.setProjectBasicInfo(projectBasicInfo);
			projectNumber.setFlag(0);
			projectNumberService.save(projectNumber);
		  }
      }
	//	for (ProjectPlan projectPlan : projectPlans) {
	//		projectPlan.setProjectBasicInfo(projectBasicInfo);
	//		projectSchedule.setProScheduleId(1);
	//		projectPlan.setProjectSchedule(projectSchedule);
	//		projectPlanService.save(projectPlan);
	//	}

	//	saveProjectFile(projectBasicInfo, proDocFiles, fileName);

		resultVo.setErrCode(0);
		resultVo.setErrMsg("sava successe");
		return resultVo;

	}

	/**
	 * @Description 保存图片
	 * @author shg
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveProjectPicture(ProjectBasicInfo projectBasicInfo,
			List<String> projectLogos) {

		// 保存上传图片
		if (projectLogos.size() > 0) {
			// base64保存图片
			Base64Util base64Util = new Base64Util();
			ProjectPicture projectPicture = new ProjectPicture();
			for (String projectLogo : projectLogos) {
				projectLogo = projectLogo
						.substring(projectLogo.indexOf(",") + 1);
				projectPicture.setProjectBasicInfo(projectBasicInfo);
				;
				projectPicture.setProjectPictureName(base64Util.GenerateImage(
						projectLogo, filePath));
				projectPictureService.save(projectPicture);
			}
		}
	}

	/**
	 * @Description 上传项目附件
	 * @author shg
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveProjectFile(ProjectBasicInfo projectBasicInfo,
			List<String> proDocFiles, String fileName) {

		// 保存上传图片
		if (proDocFiles.size() > 0) {
			// base64保存图片
			Base64Util base64Util = new Base64Util();
			ProjectDocuments projectDocuments = new ProjectDocuments();
			// ProjectDocuments projectDocuments = new ProjectDocuments();
			for (String proDocFile : proDocFiles) {
				if(proDocFile!=null){
					proDocFile = proDocFile.substring(proDocFile.indexOf(",") + 1);
					projectDocuments.setProjectBasicInfo(projectBasicInfo);
					base64Util.GeneratePdf(proDocFile, pfilePath, fileName);
					String type=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
					if(LabConstans.CONVERSION_TYPE.contains(type)){
						projectDocuments.setPdfCount(0);
						projectDocuments.setConversionCount(0);
					}else if("pdf".equals(type)){
						projectDocuments.setPdfCount(2);
					}else{
						projectDocuments.setPdfCount(99);
					}
					projectDocuments.setDocumentName(fileName);
					projectDocumentsService.save(projectDocuments);
				}
			}
		}
	}

	/**
	 * 更新对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	@RequestMapping(value = "/ProjectBasicInfo", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody ProjectBasicInfo projectBasicInfo) {
		ResultVo resultVo = new ResultVo();
		
		try {
			projectBasicInfoService.update(projectBasicInfo);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe"); 
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}
	}
	@RequestMapping(value = "/ProjectBasicInfo/updateProName", method = RequestMethod.PUT)
	public ResultVo updateProName(@RequestBody ProjectBasicInfoVo projectBasicInfoVo) {
		ResultVo resultVo = new ResultVo();
		
		try {
			ProjectBasicInfo projectBasicInfo = projectBasicInfoVo.getProjectBasicInfo();
			Integer id = projectBasicInfo.getProId();
			List<String> projectLogos = projectBasicInfoVo.getProjectLogos();
			List<String> urls = projectBasicInfoVo.getUrls();
			// 操作类型
			String opsType = projectBasicInfoVo.getOpsType();
			if ("1".equals(opsType)) { // 新增
				saveProjectPicture(projectBasicInfo, projectLogos);
			} else if ("2".equals(opsType)) { // 修改
				// 删除旧图片
				if (urls.size() > 0) {
					for (String fileName : urls) {
						fileName=fileName.substring(fileName.lastIndexOf("/")+1);
						new File(filePath + fileName).delete();
						// 删除数据库中图片信息
						fileName = fileName.replace(urlPath, "");
						projectPictureService.delete(id, fileName);
					}
				}
				// 保存上传图片
				if (projectLogos != null && projectLogos.size() > 0) {
					saveProjectPicture(projectBasicInfo, projectLogos);
				}
			}
			
			projectBasicInfoService.updateProName(projectBasicInfo);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe");
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}
	}
	@RequestMapping(value = "/ProjectBasicInfo/updateProInt", method = RequestMethod.PUT)
	public ResultVo updateProInt(@RequestBody ProjectBasicInfo projectBasicInfo) {
		ResultVo resultVo = new ResultVo();
		
		try {
			projectBasicInfoService.updateProInt(projectBasicInfo);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe");
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}
	}
	@RequestMapping(value = "/ProjectBasicInfo/updateProStatus", method = RequestMethod.PUT)
	public ResultVo updateProStatus(@RequestBody ProjectBasicInfo projectBasicInfo) {
		ResultVo resultVo = new ResultVo();
		
		try {
			projectBasicInfoService.updateProStatus(projectBasicInfo);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe");
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}
	}

	/**
	 * 删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/ProjectBasicInfo/delete/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		ProjectBasicInfo projectBasicInfo = projectBasicInfoService.getProById(id);
	    List<ProjectNumber> projectNumbers=projectBasicInfo.getProjectNumbers();
	    
		List<KnowledgeProRela> knowledgeProRelas = new ArrayList<KnowledgeProRela>(projectBasicInfo.getKnowledgeProRelas());
		List<ProjectMomentsRelation> projectMomentsRelations = new ArrayList<ProjectMomentsRelation>(projectBasicInfo.getProjectMomentsRelations());
		List<ProjectPlan> projectPlans = new ArrayList<ProjectPlan>(projectBasicInfo.getProjectPlans());		
		List<ProjectPicture> projectPictures = new ArrayList<ProjectPicture>(projectBasicInfo.getProjectPictures());		
		List<ProjectDocuments> projectDocumentes = new ArrayList<ProjectDocuments>(projectBasicInfo.getProjectDocumentses());
			
//	    List<KnowledgeProRela> knowledgeProRelas=(List<KnowledgeProRela>) projectBasicInfo.getKnowledgeProRelas();
//	    List<ProjectMomentsRelation> projectMomentsRelations=(List<ProjectMomentsRelation>) projectBasicInfo.getProjectMomentsRelations();
//	    List<ProjectPlan> projectPlans=projectBasicInfo.getProjectPlans();
//	    List<ProjectPicture> projectPictures=(List<ProjectPicture>) projectBasicInfo.getProjectPictures();
//	    List<ProjectDocuments> projectDocumentes=(List<ProjectDocuments>) projectBasicInfo.getProjectDocumentses();
	    SysUser sysUser1=null;
	    if(projectNumbers!=null){
	    	 for (ProjectNumber projectNumber : projectNumbers) {
	 	    	if (projectNumber.getProjectRole().getProRoleId()==1) {
	 	    		//获取此项目的项目经理对象
	 				 sysUser1=projectNumber.getSysUser();	
	 			}	
	 		}
	    }
	    Integer uid1=sysUser1.getUserId();
		 LoginController login = new LoginController();
		 //获取登录用户
		 SysUser sysUser2 = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 Integer sid=sysUser2.getSysRole().getRoleId();
		 Integer uid2=sysUser2.getUserId();
		 /*
			if(projectBasicInfo.getReactions().size()<=0 && (uid1==uid2) || (projectBasicInfo.getReactions().size()<=0 &&(sid==11))){
				projectBasicInfoService.delete(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("删除成功！！！");
				return resultVo;
			}else if(projectBasicInfo.getReactions().size()<=0 && ((uid1!=uid2) && (sid!=11))){
				resultVo.setErrCode(1);
				resultVo.setErrMsg("您无权删除该项目！！！");
				return resultVo;
			}else if(projectBasicInfo.getReactions().size()>0){
				resultVo.setErrCode(2);
				resultVo.setErrMsg("该项目关联试验不能被删除！！！");
				return resultVo;	
		}*/
		 
		    if((uid1-uid2==0)||(sid-11==0)){
			    if(projectBasicInfo.getReactions().size()<=0){
			    	if(projectNumbers!=null){
			    		for (ProjectNumber projectNumber : projectNumbers) {
							projectNumberService.delete(id);
						}
			    	}
			    	for (KnowledgeProRela knowledgeProRela : knowledgeProRelas) {
						knowledgeProRelaService.delete(id);
					}
			    	for (ProjectMomentsRelation projectMomentsRelation : projectMomentsRelations) {
			    		projectMomentsRelationService.delete(id);
					}
			    	for (ProjectPlan projectPlan : projectPlans) {
						projectPlanService.delete(id);
					}
			    	for (ProjectPicture projectPicture : projectPictures) {
						projectPictureService.deleteByPro(id);
					}
			    	for (ProjectDocuments projectDocument : projectDocumentes) {
						  projectDocumentsService.delete(id);
					}
				    projectBasicInfoService.delete(id);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("删除成功！！！");
					return resultVo;
		         }else if(projectBasicInfo.getReactions().size()>0){
					resultVo.setErrCode(2);
					resultVo.setErrMsg("此项目关联试验，不能被删除！！！");
					return resultVo;
			      }
		    }else if((uid1-uid2!=0)&&(sid-11!=0)){
					resultVo.setErrCode(1);
					resultVo.setErrMsg("您无权删除该项目！！！");
					return resultVo;
			}
		      return resultVo;
	}
	/**
	 * 根据主键获取实验
	 * 
	 * @Description TODO
	 * @author llwang
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/projectBasicInfo/getArticleList/{id}", method = RequestMethod.GET)
	public ResultVo getArticleList(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List<Map<String, Object>> list = projectBasicInfoService
				.getArticleById(id);
		if (list != null && list.size() > 0) {
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(list);
		} else {
			resultVo.setErrCode(0);
			resultVo.setErrMsg("无数据");
			// resultVo.setResultData(list);
		}

		return resultVo;

	}

	/**
	 * 删除项目知识关系表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/ProjectBasicInfo/deleteProKno/{proId}/{classId}/{knowId}", method = RequestMethod.DELETE)
	public ResultVo deleteKno(@PathVariable("proId") Integer proId,
			@PathVariable("classId") Integer classId,
			@PathVariable("knowId") Integer knowId) {
		ResultVo resultVo = new ResultVo();
		 //获取登录用户
		 LoginController login = new LoginController();
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 Integer userId=sysUser.getUserId();
		 KnowledgeProRela knowledgeProRela=knowledgeProRelaService.getKnowledgeProRela(proId, classId, knowId);
		 
		  //  projectBasicInfoService.deleteKno(proId, classId, knowId);
		try {
			projectBasicInfoService.deleteKno(proId, classId, knowId);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("取消关联成功！");
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("取消关联失败！");
			return resultVo;
		}
	}
	
	//=========================================================app 保存修改接口===========================================================================================
	
	/**
	 * 根据主键获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/projectBasicInfo/getProByIdForApp/{id}", method = RequestMethod.GET)
	public ProjectBasicInfoVo getProByIdForApp(@PathVariable("id") Integer id) {
		List<Integer> proIds = new ArrayList<Integer>();
		LoginController login = new LoginController();
		ProjectBasicInfoVo res = new ProjectBasicInfoVo();
		 //获取登录用户
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 SysRole sysRole=sysUser.getSysRole();
		 String roleName =sysRole.getRoleName();
		 res.setRolename(roleName);
		 res.setUserId(sysUser.getUserId());
		 logger.error("roleName",roleName);
		 Integer userId=sysUser.getUserId();
		 List<ProjectBasicInfo> projectBasicInfos = projectBasicInfoService.getAll(userId, roleName);
		 if(projectBasicInfos!=null && projectBasicInfos.size()>0){
			 for (ProjectBasicInfo projectBasicInfo : projectBasicInfos) {
				 proIds.add(projectBasicInfo.getProId());
			}
			
		 }
		 if(!proIds.contains(id)){
			 ProjectBasicInfo projectBasicInfo = new ProjectBasicInfo();
			 projectBasicInfo.setResult(4);
			 res.setProjectBasicInfo(projectBasicInfo);
			 return res;
		 }
		
		
		ProjectBasicInfo projectBasicInfo = projectBasicInfoService
				.getNumbers(id);
		if (projectBasicInfo!=null) {
			List<ProjectPicture> logo = new ArrayList<ProjectPicture>(projectBasicInfo.getProjectPictures());
			if(logo!=null && logo.size()>0){
				for (ProjectPicture projectPicture : logo) {
					projectPicture.setProjectPictureName(urlPath+projectPicture.getProjectPictureName());
				}
			}
		List<ProjectDocuments> projectDocuments = new ArrayList<ProjectDocuments>(
				projectBasicInfo.getProjectDocumentses());
		if (projectDocuments != null && projectDocuments.size() > 0) {
			for (ProjectDocuments projectDocument : projectDocuments) {
//				if (projectDocuments != null) {
//					projectDocument.setDocumentUrl(purlPath+projectDocument
//							.getDocumentName());
//				}
				if (projectDocuments != null) {
					String fileName=projectDocument.getDocumentName();
					String type=fileName.substring(fileName.lastIndexOf(".") + 1);
					String pdfUrl=null;
					if("pdf".equals(type)){
						pdfUrl=purlPath + fileName;
					}else{
						pdfUrl=projectDocument.getPdfUrl();
					}
					projectDocument.setDocumentUrl(purlPath+projectDocument
							.getDocumentName());
					projectDocument.setPdfUrl(pdfUrl);
				}
			}
		}

		List<Map<String, Object>> list = projectBasicInfoService
				.getArticleById(id);
		if (list != null && list.size() > 0) {
			
			for (Map<String, Object> map : list) {
				Integer knowledgeId =(Integer)map.get("knowledgeId");
				Integer classifyId = (Integer)map.get("knowledge_classify_id");
				KnowledgeAcc knowledgeAcc = knowledgeAccDaoService.getKnowledgeAcc(knowledgeId, classifyId);
				if(knowledgeAcc !=null){
					String uploadFiles = knowledgeAcc.getUploadFiles();
					Integer knowledgeAccId = knowledgeAcc.getKnowledgeAccId();
					if(knowledgeAcc.getKnowledgeClassify().getKnowledgeClassifyId()==1){
						JournalArticle journalArticle=journalArticleService.getById(knowledgeAcc.getKnowledgeId());
						if(journalArticle.getSource()==0){
							map.put("filename", pdfUrlPath+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));	
						}else{
							map.put("filename", agencyPdf+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));	
						}
					
					}else if(knowledgeAcc.getKnowledgeClassify().getKnowledgeClassifyId()==4){
						Patent patent=patentService.getById(knowledgeAcc.getKnowledgeId());
						if(patent.getSource()==0){
							map.put("filename", pdfUrlPath+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));	
						}else{
							map.put("filename", agencyPdf+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));	
						}
					}else{
						map.put("filename", pdfUrlPath+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));
					}
					Integer pdfCount = knowledgeAcc.getPdfCount();
					map.put("filePath", uploadFiles);
					map.put("knowledgeAccId", knowledgeAccId);
					map.put("pdfCount", pdfCount);
				}
			}
			
			res.setListKnowledge(list);
		}
		res.setProjectBasicInfo(projectBasicInfo);
		 List<ProjectNumber> projectNumbers=projectBasicInfo.getProjectNumbers();
		    SysUser sysUser1=null;
		    for (ProjectNumber projectNumber : projectNumbers) {
		    	if (projectNumber.getProjectRole().getProRoleId()==1) {
		    		//获取此项目的项目经理对象
					 sysUser1=projectNumber.getSysUser();	
				}	
			}
		    Integer pmUserId=sysUser1.getUserId();
		 List<Reaction> reactionList = reactionService.getUserReactions(userId,pmUserId, id, roleName);
		 if(reactionList!=null&&reactionList.size()>0){
			 res.setReacNum(reactionList.size());
		 }else{
			 res.setReacNum(0);
		 }
		return res;
		}
		return null;

	}
	
	/**
	 * @Description App新增用户信息
	 * @author llwangi
	 * @version V1.0
	 * @date 2018年2月6日 上午11:49:52
	 */
	@RequestMapping(value = "/ProjectBasicInfo/saveForApp", method = RequestMethod.POST)
	public ResultVo saveForApp(
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			@RequestParam(value = "proName", required = false) String proName,
			@RequestParam(value = "proNumber", required = false) String proNumber,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end,
			@RequestParam(value = "managerId", required = false) int managerId
			) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
	try {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date proStartTime=sdf.parse(start);
		Date proDeadline=sdf.parse(end);
		ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();
		projectBasicInfo.setProName(proName);
		if(proNumber!=null&&!"".equals(proNumber)){
			projectBasicInfo.setProNumber(proNumber);
		}
		ProjectSchedule projectSchedule=new ProjectSchedule();
		projectSchedule.setProScheduleId(1);
		projectBasicInfo.setProjectSchedule(projectSchedule);
		projectBasicInfo.setProStartTime(proStartTime);
		projectBasicInfo.setProDeadline(proDeadline);
		projectBasicInfo.setCreater(sysUser);
		projectBasicInfo.setCreateDate(new Date());
		projectBasicInfoService.save(projectBasicInfo);
		if(files!=null&&files.length>0){
			saveAttachment(projectBasicInfo,files,filePath);
		}
		SysUser sysUser1=new SysUser();
		sysUser1.setUserId(managerId);
		ProjectRole projectRole=new ProjectRole();
		projectRole.setProRoleId(1);
		ProjectNumber projectNumber=new ProjectNumber();
		projectNumber.setSysUser(sysUser1);
		projectNumber.setProjectRole(projectRole);
		projectNumber.setProjectBasicInfo(projectBasicInfo);
		projectNumber.setFlag(0);
		projectNumberService.save(projectNumber);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save ProjectBasicInfo success");
	} catch (Exception e) {
		resultVo.setErrCode(2);
		resultVo.setErrMsg("save ProjectBasicInfo failed");
	}
			
	return resultVo;
	
	}
	
	/**
	 * @Description App新增用户信息
	 * @author llwangi
	 * @version V1.0
	 * @date 2018年2月6日 上午11:49:52
	 */
	@RequestMapping(value = "/ProjectBasicInfo/UpdateForApp", method = RequestMethod.POST)
	public ResultVo UpdateForApp(
			@RequestParam(value = "proId", required = false) int proId,
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			@RequestParam(value = "proName", required = false) String proName,
			@RequestParam(value = "proNumber", required = false) String proNumber,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end,
			@RequestParam(value = "proScheduleId", required = false) int proScheduleId
			) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
	try {
		ProjectBasicInfo projectBasicInfo=projectBasicInfoService.getById(proId);
		int createrId=projectBasicInfoService.getCreaterIdByProId(proId);
		SysUser creater=new SysUser();
		creater.setUserId(createrId);
		projectBasicInfo.setCreater(creater);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(start!=null&&!"".equals(start)){
			Date proStartTime=sdf.parse(start);
			projectBasicInfo.setProStartTime(proStartTime);
		}
		if(end!=null&&!"".equals(end)){
			Date proDeadline=sdf.parse(end);
			projectBasicInfo.setProDeadline(proDeadline);
		}
		if( proName!=null&&!"".equals(proName)){
			projectBasicInfo.setProName(proName);
		}
		if(proNumber!=null&&!"".equals(proNumber)){
			projectBasicInfo.setProNumber(proNumber);
		}
		if(proScheduleId!=0){
			ProjectSchedule projectSchedule=new ProjectSchedule();
			projectSchedule.setProScheduleId(proScheduleId);
			projectBasicInfo.setProjectSchedule(projectSchedule);
		}
		projectBasicInfoService.update(projectBasicInfo);
		if(files!=null&&files.length>0){
			List<String> imageStrs = projectPictureService.getByProId(proId);
			if(imageStrs!=null&&imageStrs.size()>0){
				String fileName=imageStrs.get(0);
				new File(filePath + fileName).delete();
			}
			projectPictureService.deleteByPro(proId);
			saveAttachment(projectBasicInfo,files,filePath);
		}
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save ProjectBasicInfo success");
	} catch (Exception e) {
		resultVo.setErrCode(2);
		resultVo.setErrMsg("save ProjectBasicInfo failed");
	}
			
	return resultVo;
	
	}
	
	/**
	 * @Description App新增用户信息
	 * @author llwangi
	 * @version V1.0
	 * @date 2018年2月6日 上午11:49:52
	 */
	@RequestMapping(value = "/ProjectBasicInfo/saveProjectNumbers", method = RequestMethod.POST)
	public ResultVo saveProjectNumbers(
			@RequestParam(value = "proId", required = false) int proId,
			@RequestParam(value = "managerId", required = false) int managerId,
			@RequestParam(value = "proNumberIds", required = false) String proNumberIds
			) {
		ResultVo resultVo = new ResultVo();
	try{
		List<ProjectNumber> oldNumbers = projectNumberService.getByProId(proId);
		List<Integer> userList=new ArrayList<Integer>();
		if(managerId!=0){
			if(oldNumbers!=null&&oldNumbers.size()>0){
				for(int i=0;i<oldNumbers.size();i++){
					userList.add(oldNumbers.get(i).getSysUser().getUserId());
				}
			}
			if(userList.contains(managerId)){
				ProjectNumber projectNumber=projectNumberService.getByUser(managerId,proId);
				if(reactionDesignService.getCount(managerId)>0 || reactionService.getCount(managerId)>0){
					projectNumberService.updateByDelete(projectNumber.getProNumberId(),1,2);
			}else{
				projectNumberService.deleteById(projectNumber.getProNumberId());
				}
			}
			SysUser sysUser=new SysUser();
			sysUser.setUserId(managerId);
			ProjectRole projectRole=new ProjectRole();
			projectRole.setProRoleId(1);
			ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();
			projectBasicInfo.setProId(proId);
			ProjectNumber projectNumber=new ProjectNumber();
			projectNumber.setSysUser(sysUser);
			projectNumber.setProjectRole(projectRole);
			projectNumber.setProjectBasicInfo(projectBasicInfo);
			projectNumber.setFlag(0);
			projectNumberService.save(projectNumber);
			for (ProjectNumber oldNumber : oldNumbers) {
					if(oldNumber.getProjectRole().getProRoleId()==1){
						if(reactionDesignService.getCount(oldNumber.getSysUser().getUserId())>0 || reactionService.getCount(oldNumber.getSysUser().getUserId())>0){
								projectNumberService.updateByDelete(oldNumber.getProNumberId(),1,1);
						}else{
								projectNumberService.deleteById(oldNumber.getProNumberId());
							}
						}
				
				}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save projectNumber success");
		}else{
			if(proNumberIds!=null&&!"".equals(proNumberIds)){
				if(oldNumbers!=null&&oldNumbers.size()>0){
					for(int i=0;i<oldNumbers.size();i++){
						userList.add(oldNumbers.get(i).getSysUser().getUserId());
					}
				}
				if(userList.size()>0){
					String[] ss=proNumberIds.split(",");
					for(int i=0;i<ss.length;i++){
						if(!userList.contains(Integer.parseInt(ss[i]))){
							SysUser sysUser=new SysUser();
							sysUser.setUserId(Integer.parseInt(ss[i]));
							ProjectRole projectRole=new ProjectRole();
							projectRole.setProRoleId(2);
							ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();
							projectBasicInfo.setProId(proId);
							ProjectNumber projectNumber=new ProjectNumber();
							projectNumber.setSysUser(sysUser);
							projectNumber.setProjectRole(projectRole);
							projectNumber.setProjectBasicInfo(projectBasicInfo);
							projectNumber.setFlag(0);
							projectNumberService.save(projectNumber);
						}
					}
				}else{
					String[] ss=proNumberIds.split(",");
					for(int i=0;i<ss.length;i++){
						SysUser sysUser=new SysUser();
						sysUser.setUserId(Integer.parseInt(ss[i]));
						ProjectRole projectRole=new ProjectRole();
						projectRole.setProRoleId(2);
						ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();
						projectBasicInfo.setProId(proId);
						ProjectNumber projectNumber=new ProjectNumber();
						projectNumber.setSysUser(sysUser);
						projectNumber.setProjectRole(projectRole);
						projectNumber.setProjectBasicInfo(projectBasicInfo);
						projectNumber.setFlag(0);
						projectNumberService.save(projectNumber);
					}
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save projectNumber success");
		}

	} catch (Exception e) {
		resultVo.setErrCode(2);
		resultVo.setErrMsg("save projectNumber failed");
	}
	return resultVo;
	}
	
	/**
	 * @Description App新增用户信息
	 * @author llwangi
	 * @version V1.0
	 * @date 2018年2月6日 上午11:49:52
	 */
	@RequestMapping(value = "/ProjectBasicInfo/saveProjectIntroduce", method = RequestMethod.POST)
	public ResultVo saveProjectIntroduce(
			@RequestParam(value = "proId", required = false) int proId,
			@RequestParam(value = "projectIntroduce", required = false) String projectIntroduce
			) {
		ResultVo resultVo = new ResultVo();
	try{
		projectBasicInfoService.updateProjectIntroduce(proId,projectIntroduce);

	} catch (Exception e) {
		resultVo.setErrCode(2);
		resultVo.setErrMsg("save ProjectIntroduce failed");
	}
	return resultVo;
	}
	
		// 保存上传图片
	private void saveAttachment(ProjectBasicInfo projectBasicInfo, MultipartFile[] files,String path) throws IOException {
		List<String> fileNameList = new FileUtil().imageUpload(files, path);
		for (String fileName : fileNameList) {
			ProjectPicture projectPicture=new ProjectPicture();
			projectPicture.setProjectBasicInfo(projectBasicInfo);
			projectPicture.setProjectPictureName(fileName);
			projectPictureService.save(projectPicture);
		}
	}
	
	
	/**
	 * 删除项目知识关系表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/ProjectBasicInfo/deleteProKnoApp/{proId}/{classId}/{knowId}", method = RequestMethod.POST)
	public ResultVo deleteProKnoApp(@PathVariable("proId") Integer proId,
			@PathVariable("classId") Integer classId,
			@PathVariable("knowId") Integer knowId) {
		ResultVo resultVo = new ResultVo();
		 //获取登录用户
		 LoginController login = new LoginController();
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 Integer userId=sysUser.getUserId();
		 KnowledgeProRela knowledgeProRela=knowledgeProRelaService.getKnowledgeProRela(proId, classId, knowId);
		 
		  //  projectBasicInfoService.deleteKno(proId, classId, knowId);
		try {
			projectBasicInfoService.deleteKno(proId, classId, knowId);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("取消关联成功！");
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("取消关联失败！");
			return resultVo;
		}
	}
	
	
	
	/**
	 * 删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/ProjectBasicInfo/deleteApp/{id}", method = RequestMethod.POST)
	public ResultVo deleteApp(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		ProjectBasicInfo projectBasicInfo = projectBasicInfoService.getProById(id);
	    List<ProjectNumber> projectNumbers=projectBasicInfo.getProjectNumbers();
	    
		List<KnowledgeProRela> knowledgeProRelas = new ArrayList<KnowledgeProRela>(projectBasicInfo.getKnowledgeProRelas());
		List<ProjectMomentsRelation> projectMomentsRelations = new ArrayList<ProjectMomentsRelation>(projectBasicInfo.getProjectMomentsRelations());
		List<ProjectPlan> projectPlans = new ArrayList<ProjectPlan>(projectBasicInfo.getProjectPlans());		
		List<ProjectPicture> projectPictures = new ArrayList<ProjectPicture>(projectBasicInfo.getProjectPictures());		
		List<ProjectDocuments> projectDocumentes = new ArrayList<ProjectDocuments>(projectBasicInfo.getProjectDocumentses());
	    SysUser sysUser1=null;
	    for (ProjectNumber projectNumber : projectNumbers) {
	    	if (projectNumber.getProjectRole().getProRoleId()==1) {
	    		//获取此项目的项目经理对象
				 sysUser1=projectNumber.getSysUser();	
			}	
		}
	    Integer uid1=sysUser1.getUserId();
		 LoginController login = new LoginController();
		 //获取登录用户
		 SysUser sysUser2 = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 Integer sid=sysUser2.getSysRole().getRoleId();
		 Integer uid2=sysUser2.getUserId();
	
		 
		    if((uid1-uid2==0)||(sid-11==0)){
			    if(projectBasicInfo.getReactions().size()<=0){
			    	for (ProjectNumber projectNumber : projectNumbers) {
						projectNumberService.delete(id);
					}
			    	for (KnowledgeProRela knowledgeProRela : knowledgeProRelas) {
						knowledgeProRelaService.delete(id);
					}
			    	for (ProjectMomentsRelation projectMomentsRelation : projectMomentsRelations) {
			    		projectMomentsRelationService.delete(id);
					}
			    	for (ProjectPlan projectPlan : projectPlans) {
						projectPlanService.delete(id);
					}
			    	for (ProjectPicture projectPicture : projectPictures) {
						projectPictureService.deleteByPro(id);
					}
			    	for (ProjectDocuments projectDocument : projectDocumentes) {
						  projectDocumentsService.delete(id);
					}
				    projectBasicInfoService.delete(id);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("删除成功！！！");
					return resultVo;
		         }else if(projectBasicInfo.getReactions().size()>0){
					resultVo.setErrCode(2);
					resultVo.setErrMsg("此项目关联试验，不能被删除！！！");
					return resultVo;
			      }
		    }else if((uid1-uid2!=0)&&(sid-11!=0)){
					resultVo.setErrCode(1);
					resultVo.setErrMsg("您无权删除该项目！！！");
					return resultVo;
			}
		      return resultVo;
	}
}
