package com.labwinner.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.PersonalReactionTemplateParameter;
import com.labwinner.domain.PersonalReactionTemplateProcess;
import com.labwinner.domain.PersonalTemplate;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ShareProject;
import com.labwinner.domain.SysRole;
import com.labwinner.domain.SysUser;
import com.labwinner.service.IndustryReactionTemplateService;
import com.labwinner.service.PersonalReactionTemplateParameterService;
import com.labwinner.service.PersonalReactionTemplateProcessService;
import com.labwinner.service.PersonalTemplateService;
import com.labwinner.service.ProjectBasicInfoService;
import com.labwinner.service.ShareProjectService;
import com.labwinner.service.SysUserService;
import com.labwinner.vo.PersonalTemplateVo;

/**
 * 个人模板Controller
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午4:22:48
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class PersonalTemplateController {

	private static Logger logger = LoggerFactory
			.getLogger(PersonalTemplateController.class);
	@Autowired
	private PersonalTemplateService personalTemplateService; 

	@Autowired
	private PersonalReactionTemplateProcessService personalReactionTemplateProcessService; 	
	
	@Autowired
	private PersonalReactionTemplateParameterService personalReactionTemplateParameterService; 
	
	@Autowired
	private ShareProjectService shareProjectService; 
	
	@Autowired
	private ProjectBasicInfoService projectBasicInfoService; 
	
	@Autowired
	private SysUserService  sysUserService; 
	
	@Autowired
	private IndustryReactionTemplateService industryReactionTemplateService; 
	
	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:16
	 */
	 @RequestMapping(value = "/personalTemplate", method = RequestMethod.GET)
	    public List<PersonalTemplate> getAll(Integer userId) {
		 List<PersonalTemplate> list = personalTemplateService.getAll(userId);
			if (list == null) {
				String message = "没有对象";
				logger.warn(message);
				
			}
			return list;
	    }
	 
	 
	 
	 
	 /**
		 * @Description 列表分页
		 * @author liuhq
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		@RequestMapping(value = "/personalTemplatePageable/{page}/{pageSize}", method = RequestMethod.GET)
		@ResponseBody
		public PageInfo getAllPageable(@PathVariable Integer page,
				@PathVariable Integer pageSize) {
			
			LoginController login = new LoginController();
			 //获取登录用户
			 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			 Integer userId=sysUser.getUserId();
			 
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			List<PersonalTemplate> list = new ArrayList<PersonalTemplate>();
				list = personalTemplateService.getAll(userId);
		
			return new PageInfo(list);
		}
	 
		/**
		 * @Description 搜索列表分页
		 * @author liuhq
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		@RequestMapping(value = "/personalTemplatePageableAll/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
		@ResponseBody
		public ResultVo getPageable(@PathVariable Integer page,
				@PathVariable Integer pageSize,@PathVariable String keyword) {
			
			LoginController login = new LoginController();
			//获取登录用户
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer userId=sysUser.getUserId();
		
			 ResultVo resultVo = new ResultVo();
				if (page != null && pageSize != null) {
					PageHelper.startPage(page, pageSize);
				}
				if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
					List<PersonalTemplate> personalTemplates = personalTemplateService.getAllPageable(userId,keyword);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(personalTemplates));
					return resultVo;
				} else {
					List<PersonalTemplate> personalTemplates = personalTemplateService.getMyAll(userId);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(personalTemplates));
					return resultVo;
				}
		}
		
		/**
		 * @Description 带搜索APP列表分页
		 * @author liuhq
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		@RequestMapping(value = "/personalTemplateAppPageableAll/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
		@ResponseBody
		public ResultVo getAPPPageable(@PathVariable Integer page,
				@PathVariable Integer pageSize,@PathVariable String keyword) {
			
			LoginController login = new LoginController();
			//获取登录用户
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer userId=sysUser.getUserId();
			
			ResultVo resultVo = new ResultVo();
			/*if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}*/
			List<PersonalTemplate> personalTemplates = new ArrayList<PersonalTemplate>();
			
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				 personalTemplates = personalTemplateService.getAllPageable(userId,keyword);
			} else {
	             personalTemplates = personalTemplateService.getMyAll(userId);
			}
			if(personalTemplates!=null&&personalTemplates.size()>0){
				int total = personalTemplates.size();
				double c = (((double) total) / pageSize);
				int d = (int) Math.ceil(c);
				PageEntity pageEntity = new PageEntity();
				pageEntity.setPageNum(page);
				pageEntity.setPageSize(pageSize);
				pageEntity.setPages(d);
				pageEntity.setTotal(total);
				int num = personalTemplates.size() > page * pageSize ? page * pageSize : personalTemplates.size();
				if (page <= d) {
					pageEntity.setList(personalTemplates.subList(0, num));
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(pageEntity);
				return resultVo;
			}else {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("无数据");
				resultVo.setResultData(null);
				return resultVo;
			}
		}
		

	/**
	 * 根据主键获取对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:26
	 */
	 @RequestMapping(value = "/personalTemplate/{id}", method = RequestMethod.GET)
	    public PersonalTemplate getById(@PathVariable("id") Integer id) {
		 
		 LoginController login = new LoginController();
		 List<Integer> templateIds = new ArrayList<Integer>();
		 PersonalTemplate personalTemplate =new PersonalTemplate();
		 //获取登录用户
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		 Integer userId=sysUser.getUserId();
		 List<PersonalTemplate> personalTemplates = personalTemplateService.getAll(userId);
		 if(personalTemplates!=null && personalTemplates.size()>0){
			 for (PersonalTemplate personalTemplate2 : personalTemplates) {
				 templateIds.add(personalTemplate2.getPersonalTemplateId());
			}
		 }
		 if(templateIds.contains(id)){
			 personalTemplate = personalTemplateService.getById(id);
				if (personalTemplate == null) {
					String message = "对象不存在(id:" + id + ")";
					logger.warn(message);
					
				}
				return personalTemplate;
		 }
		 personalTemplate.setResult(4);
		 return personalTemplate;
		 
	    }

	/**
	 * 保存对象方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @param shareScope 
	 * @param sysUser 
	 * @date 2017年5月19日 下午4:23:40
	 */
	 @RequestMapping(value = "/personalTemplate", method = RequestMethod.POST)
	    public ResultVo save(@RequestBody PersonalTemplateVo personalTemplateVo) {
 
              ResultVo resultVo = new ResultVo();
              LoginController login = new LoginController();
     		 //获取登录用户
     		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
             
     		// ProjectBasicInfo projectBasicInfo=personalTemplateVo.getProjectBasicInfo();
              //个人模板
            PersonalTemplate personalTemplate=personalTemplateVo.getPersonalTemplate();
            
            Integer shareScope=personalTemplate.getShareScope();
            
            List<PersonalReactionTemplateProcess> personalReactionTemplateProcesses = personalTemplateVo.getPersonalReactionTemplateProcesses();
	          
            List<Integer> integers = personalTemplateVo.getProjectIds();
            
          
					if(shareScope!=null&&shareScope==2){
					      personalTemplate.setShareScope(2);
					      personalTemplate.setCreater(sysUser);
					      personalTemplate.setCreateDate(new Date());
						  personalTemplateService.save(personalTemplate);
						  //个人试验模板步骤
						for(PersonalReactionTemplateProcess personalReactionTemplateProcess :personalReactionTemplateProcesses){
							   personalReactionTemplateProcess.setPersonalTemplate(personalTemplate);
							  personalReactionTemplateProcessService.save(personalReactionTemplateProcess);
							  
							  List<PersonalReactionTemplateParameter> personalReactionTemplateParameters =  personalReactionTemplateProcess.getPersonalReactionTemplateParameters();
							    
							   //个人试验模板参数
							   for(PersonalReactionTemplateParameter personalReactionTemplateParameter:personalReactionTemplateParameters){
								   personalReactionTemplateParameter.setPersonalReactionTemplateProcess(personalReactionTemplateProcess);
								   personalReactionTemplateParameterService.save(personalReactionTemplateParameter);   
							   }
						  }
						 ShareProject shareProject=new ShareProject();
						 if(integers!=null&&integers.size()>0){
							 for (Integer integer : integers) {	
									shareProject.setProjectBasicInfo(projectBasicInfoService.getProById(integer));
									shareProject.setPersonalTemplate(personalTemplate);
									shareProjectService.save(shareProject);
								}
						 }
					        resultVo.setErrCode(0);
							resultVo.setErrMsg("save successe"); 
					        return resultVo;
					}else if (shareScope!=null&&shareScope==1){
						
						 personalTemplate.setShareScope(1);
						 personalTemplate.setCreater(sysUser);
						 personalTemplate.setCreateDate(new Date());
						 personalTemplateService.save(personalTemplate);
						  //个人试验模板步骤
						for(PersonalReactionTemplateProcess personalReactionTemplateProcess :personalReactionTemplateProcesses){
							   personalReactionTemplateProcess.setPersonalTemplate(personalTemplate);
							  personalReactionTemplateProcessService.save(personalReactionTemplateProcess);
							  
							  List<PersonalReactionTemplateParameter> personalReactionTemplateParameters =  personalReactionTemplateProcess.getPersonalReactionTemplateParameters();
							    
							   //个人试验模板参数
							   for(PersonalReactionTemplateParameter personalReactionTemplateParameter:personalReactionTemplateParameters){
								   personalReactionTemplateParameter.setPersonalReactionTemplateProcess(personalReactionTemplateProcess);
								   personalReactionTemplateParameterService.save(personalReactionTemplateParameter);   
							   }
						  }
						    resultVo.setErrCode(0);
							resultVo.setErrMsg("save successe"); 
					        return resultVo;
						}else if (shareScope!=null&&shareScope==3){
							
							personalTemplate.setShareScope(3);
							 personalTemplate.setCreater(sysUser);
							 personalTemplate.setCreateDate(new Date());
							 personalTemplateService.save(personalTemplate);
							  //个人试验模板步骤
							for(PersonalReactionTemplateProcess personalReactionTemplateProcess :personalReactionTemplateProcesses){
								   personalReactionTemplateProcess.setPersonalTemplate(personalTemplate);
								  personalReactionTemplateProcessService.save(personalReactionTemplateProcess);
								  
								  List<PersonalReactionTemplateParameter> personalReactionTemplateParameters =  personalReactionTemplateProcess.getPersonalReactionTemplateParameters();
								    
								   //个人试验模板参数
								   for(PersonalReactionTemplateParameter personalReactionTemplateParameter:personalReactionTemplateParameters){
									   personalReactionTemplateParameter.setPersonalReactionTemplateProcess(personalReactionTemplateProcess);
									   personalReactionTemplateParameterService.save(personalReactionTemplateParameter);   
								   }
							  }
							    resultVo.setErrCode(0);
								resultVo.setErrMsg("save successe"); 
						        return resultVo;
                  }else {
                	        resultVo.setErrCode(1);
						    resultVo.setErrMsg("save fail"); 
				            return resultVo;
				      }
					
				} 

	 /**
		 * 更新对象的方法
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:57
		 */ 
		 @RequestMapping(value = "/personalTemplate", method = RequestMethod.PUT)
		    public ResultVo update(@RequestBody PersonalTemplateVo personalTemplateVo) {
			 
			 ResultVo resultVo = new ResultVo();
			 
			 LoginController login = new LoginController();
     		 //获取登录用户
     		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
     		 Integer userId1=sysUser.getUserId();
               //个人模板
            PersonalTemplate personalTemplate=personalTemplateVo.getPersonalTemplate();
            
            Integer shareScope=personalTemplate.getShareScope();
            
            Integer pid=personalTemplate.getPersonalTemplateId();
            
            List<Integer> integers = personalTemplateVo.getProjectIds();
            
            PersonalTemplate oldpersonalTemplate=personalTemplateService.getById(pid);
//            SysUser sysUser2=oldpersonalTemplate.getCreater();
//            Integer userId2= sysUser2.getUserId();
            List<PersonalReactionTemplateProcess> personalReactionTemplateProcesses = personalTemplateVo.getPersonalReactionTemplateProcesses();	 
            List<PersonalReactionTemplateProcess> personalReactionTemplateProcesses2 = oldpersonalTemplate.getPersonalReactionTemplateProcesses();	 
            
//            if(userId1==userId2){
            	
                if(shareScope!=null&&shareScope==2){
			      personalTemplate.setShareScope(2);
			      personalTemplate.setModifier(sysUser);
			      personalTemplate.setModifyDate(new Date());
				  personalTemplateService.update(personalTemplate);
				  //个人试验模板步骤
				  for (PersonalReactionTemplateProcess personalReactionTemplateProcess : personalReactionTemplateProcesses2) {
						 Integer personalProcessId=personalReactionTemplateProcess.getPersonalProcessId();
						  List<PersonalReactionTemplateParameter> personalReactionTemplateParameters2 =  personalReactionTemplateProcess.getPersonalReactionTemplateParameters();
						     for (PersonalReactionTemplateParameter personalReactionTemplateParameter : personalReactionTemplateParameters2) {
						    	 Integer persReacTempParaId=personalReactionTemplateParameter.getPersReacTempParaId();
								  personalReactionTemplateParameterService.delete(persReacTempParaId);
							}
						     personalReactionTemplateProcessService.delete(personalProcessId);
					}
				  
				for(PersonalReactionTemplateProcess personalReactionTemplateProcess :personalReactionTemplateProcesses){
					   personalReactionTemplateProcess.setPersonalTemplate(personalTemplate);
					  personalReactionTemplateProcessService.save(personalReactionTemplateProcess);
					  
					  List<PersonalReactionTemplateParameter> personalReactionTemplateParameters =  personalReactionTemplateProcess.getPersonalReactionTemplateParameters();
					    
					   //个人试验模板参数
					   for(PersonalReactionTemplateParameter personalReactionTemplateParameter:personalReactionTemplateParameters){
						   personalReactionTemplateParameter.setPersonalReactionTemplateProcess(personalReactionTemplateProcess);
						   personalReactionTemplateParameterService.save(personalReactionTemplateParameter);   
					   }
				  }
				
				 List<ShareProject>  shareProjects = oldpersonalTemplate.getShareProjects();
				          for (ShareProject shareProject : shareProjects) {
							    Integer shareProjectId=shareProject.getShareProjectId();
							    shareProjectService.delete(shareProjectId);
						}
				 
				 ShareProject shareProject=new ShareProject();
				 if(integers!=null&&integers.size()>0){
					 for (Integer integer : integers) {	
							shareProject.setProjectBasicInfo(projectBasicInfoService.getProById(integer));
							shareProject.setPersonalTemplate(personalTemplate);
							shareProjectService.save(shareProject);
						}
				 }
			        resultVo.setErrCode(0);
					resultVo.setErrMsg("save successe"); 
			        return resultVo;
			}else if (shareScope!=null&&shareScope==1){
				
				 personalTemplate.setShareScope(1);
				 personalTemplate.setModifier(sysUser);
				 personalTemplate.setModifyDate(new Date());
				 personalTemplateService.update(personalTemplate);
				 for (PersonalReactionTemplateProcess personalReactionTemplateProcess : personalReactionTemplateProcesses2) {
					 Integer personalProcessId=personalReactionTemplateProcess.getPersonalProcessId();
					  List<PersonalReactionTemplateParameter> personalReactionTemplateParameters2 =  personalReactionTemplateProcess.getPersonalReactionTemplateParameters();
					     for (PersonalReactionTemplateParameter personalReactionTemplateParameter : personalReactionTemplateParameters2) {
					    	 Integer persReacTempParaId=personalReactionTemplateParameter.getPersReacTempParaId();
							  personalReactionTemplateParameterService.delete(persReacTempParaId);
						}
					     personalReactionTemplateProcessService.delete(personalProcessId);
				}
				 
				 
				  //个人试验模板步骤
				for(PersonalReactionTemplateProcess personalReactionTemplateProcess :personalReactionTemplateProcesses){
					   personalReactionTemplateProcess.setPersonalTemplate(personalTemplate);
					  personalReactionTemplateProcessService.save(personalReactionTemplateProcess);
					  
					  List<PersonalReactionTemplateParameter> personalReactionTemplateParameters =  personalReactionTemplateProcess.getPersonalReactionTemplateParameters();
					    
					   //个人试验模板参数
					   for(PersonalReactionTemplateParameter personalReactionTemplateParameter:personalReactionTemplateParameters){
						   personalReactionTemplateParameter.setPersonalReactionTemplateProcess(personalReactionTemplateProcess);
						   personalReactionTemplateParameterService.save(personalReactionTemplateParameter);   
					   }
				  }
				    resultVo.setErrCode(0);
					resultVo.setErrMsg("save successe"); 
			        return resultVo;
				}else if (shareScope!=null&&shareScope==3){
					
					personalTemplate.setShareScope(3);
					 personalTemplate.setModifier(sysUser);
					 personalTemplate.setModifyDate(new Date());
					 personalTemplateService.update(personalTemplate);
					 
					 for (PersonalReactionTemplateProcess personalReactionTemplateProcess : personalReactionTemplateProcesses2) {
						 Integer personalProcessId=personalReactionTemplateProcess.getPersonalProcessId();
						  List<PersonalReactionTemplateParameter> personalReactionTemplateParameters2 =  personalReactionTemplateProcess.getPersonalReactionTemplateParameters();
						     for (PersonalReactionTemplateParameter personalReactionTemplateParameter : personalReactionTemplateParameters2) {
						    	 Integer persReacTempParaId=personalReactionTemplateParameter.getPersReacTempParaId();
								  personalReactionTemplateParameterService.delete(persReacTempParaId);
							}
						     personalReactionTemplateProcessService.delete(personalProcessId);
					}
					 
					  //个人试验模板步骤
					for(PersonalReactionTemplateProcess personalReactionTemplateProcess :personalReactionTemplateProcesses){
						   personalReactionTemplateProcess.setPersonalTemplate(personalTemplate);
						  personalReactionTemplateProcessService.save(personalReactionTemplateProcess);
						  
						  List<PersonalReactionTemplateParameter> personalReactionTemplateParameters =  personalReactionTemplateProcess.getPersonalReactionTemplateParameters();
						    
						   //个人试验模板参数
						   for(PersonalReactionTemplateParameter personalReactionTemplateParameter:personalReactionTemplateParameters){
							   personalReactionTemplateParameter.setPersonalReactionTemplateProcess(personalReactionTemplateProcess);
							   personalReactionTemplateParameterService.save(personalReactionTemplateParameter);   
						   }
					  }
					    resultVo.setErrCode(0);
						resultVo.setErrMsg("修改模板成功！！！"); 
				        return resultVo;
            
            }else {
            	resultVo.setErrCode(1);
			    resultVo.setErrMsg("修改模板失败！！！"); 
	            return resultVo;
			   }
            
            
		 
			//            if(userId1==userId2){
//            
//             personalTemplate.setModifier(sysUser);
//			 personalTemplate.setModifyDate(new Date());
//			 personalTemplateService.update(personalTemplate);
//			  //个人试验模板步骤
//			 
//			 for (PersonalReactionTemplateProcess personalReactionTemplateProcess : personalReactionTemplateProcesses2) {
//				 Integer personalProcessId=personalReactionTemplateProcess.getPersonalProcessId();
//				  List<PersonalReactionTemplateParameter> personalReactionTemplateParameters2 =  personalReactionTemplateProcess.getPersonalReactionTemplateParameters();
//				     for (PersonalReactionTemplateParameter personalReactionTemplateParameter : personalReactionTemplateParameters2) {
//				    	 Integer persReacTempParaId=personalReactionTemplateParameter.getPersReacTempParaId();
//						  personalReactionTemplateParameterService.delete(persReacTempParaId);
//					}
//				     personalReactionTemplateProcessService.delete(personalProcessId);
//			}
//			
//			for(PersonalReactionTemplateProcess personalReactionTemplateProcess :personalReactionTemplateProcesses){
//				   personalReactionTemplateProcess.setPersonalTemplate(personalTemplate);
//				  personalReactionTemplateProcessService.save(personalReactionTemplateProcess);
//				  
//				  List<PersonalReactionTemplateParameter> personalReactionTemplateParameters =  personalReactionTemplateProcess.getPersonalReactionTemplateParameters();
//				    
//				   //个人试验模板参数
//				   for(PersonalReactionTemplateParameter personalReactionTemplateParameter:personalReactionTemplateParameters){
//					   personalReactionTemplateParameter.setPersonalReactionTemplateProcess(personalReactionTemplateProcess);
//					   personalReactionTemplateParameterService.save(personalReactionTemplateParameter);   
//				   }
//			  }
//			    resultVo.setErrCode(0);
//				resultVo.setErrMsg("修改成功！！！"); 
//		        return resultVo; 
//            }else {
//            	 resultVo.setErrCode(1);
// 				resultVo.setErrMsg("您无权修改此模板！！！"); 
// 		        return resultVo; 
//			}
            
		    }
	 
	/**
	 * 删除对象方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:58
	 */
		 @RequestMapping(value = "/personalTemplate/{id}", method = RequestMethod.DELETE)
		    public ResultVo delete(@PathVariable("id") Integer id) {
			 ResultVo resultVo = new ResultVo();
			 LoginController login = new LoginController();
     		 //获取登录用户
     		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
     		 Integer userId1=sysUser.getUserId();
            
            PersonalTemplate personalTemplate=personalTemplateService.getById(id);
            //获取
            SysUser sysUser2=personalTemplate.getCreater();
            Integer userId2= sysUser2.getUserId();
			 
            List<PersonalReactionTemplateProcess> personalReactionTemplateProcesses=personalTemplate.getPersonalReactionTemplateProcesses();
            
        	  
			 if((userId1!=null&&userId2!=null)&&(userId1.intValue()==userId2.intValue())){
				 for (PersonalReactionTemplateProcess personalReactionTemplateProcess : personalReactionTemplateProcesses) {
					 Integer pid=personalReactionTemplateProcess.getPersonalProcessId();
					 List<PersonalReactionTemplateParameter> personalReactionTemplateParameters=personalReactionTemplateProcess.getPersonalReactionTemplateParameters();
			            for (PersonalReactionTemplateParameter personalReactionTemplateParameter : personalReactionTemplateParameters) {
			            	Integer persReacTempParaId=personalReactionTemplateParameter.getPersReacTempParaId();
							personalReactionTemplateParameterService.delete(persReacTempParaId);
					} 
			            personalReactionTemplateProcessService.delete(pid);
				    }
				 personalTemplateService.delete(id);
				 resultVo.setErrCode(0);
				 resultVo.setErrMsg("删除成功！！！"); 
			     return resultVo; 
			 }else {
				 resultVo.setErrCode(1);
				 resultVo.setErrMsg("您无权限删除此模板 ！！！"); 
			     return resultVo; 
			}
			
		    }
		 /**
		  * 获取所有个人模板名称方法
		  * @Description TODO
		  * @author suhg
		  * @version V1.0
		  * @date 2017年5月19日 下午4:23:58
		  */
		 @RequestMapping(value = "/getAllTemplateName", method = RequestMethod.GET)
		 public ResultVo getAllTemplateName() {
			 ResultVo resultVo = new ResultVo();
			 
			 LoginController login = new LoginController();
     		 //获取登录用户
     		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
     		 Integer userId=sysUser.getUserId();
			 
			 List<PersonalTemplate> personalTemplates=personalTemplateService.getAllTemplateName(userId);
			 
		     if(personalTemplates!=null){
				 resultVo.setErrCode(0);
				 resultVo.setErrMsg("find successe!");
				 resultVo.setResultData(personalTemplates);
				 return resultVo;
			 }else {
				 resultVo.setErrCode(1);
				 resultVo.setErrMsg("find null!"); 
				 return resultVo; 
			 }
			 
		 }

}
