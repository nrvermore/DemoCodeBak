package com.labwinner.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectNumber;
import com.labwinner.domain.ProjectPlan;
import com.labwinner.domain.ProjectSchedule;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ProjectBasicInfoService;
import com.labwinner.service.ProjectPlanService;

@RestController
public class ProjectPlanController {

	private static Logger logger = LoggerFactory
			.getLogger(ProjectPlanController.class);
  
	
	@Autowired
	private ProjectPlanService projectPlanService;
	@Autowired
	private ProjectBasicInfoService projectBasicInfoService;
	
	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	 @RequestMapping(value = "/projectPlan/list", method = RequestMethod.GET)
	    public List<ProjectPlan> getAll() {
		 List<ProjectPlan> list = projectPlanService.getAll();
			if (list == null) {
				String message = "没有对象";
				logger.warn(message);
			}
			return list;
	    }

	/**
	 * 根据主键获取对象
	 * @Description TODO
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	 @RequestMapping(value = "/projectPlan/{id}", method = RequestMethod.GET)
	    public ProjectPlan getById(@PathVariable("id") Integer id) {
		 ProjectPlan projectPlan = projectPlanService.getById(id);
		 if (projectPlan == null) {
				String message = "对象不存在(id:" + id + ")";
				logger.warn(message);
			
			}
			return projectPlan;
	    }
	 
	 /**
		 * 保存对象
		 * @Description TODO
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 下午6:36:23
		 */
	 @RequestMapping(value = "/projectPlan/add", method = RequestMethod.POST)
	    public void save(@RequestBody ProjectPlan projectPlan) {
		 projectPlanService.save(projectPlan);
	 }
	 /**
		 * 更新对象的方法
		 * @Description TODO
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:57
		 */
		 @RequestMapping(value = "/projectPlan", method = RequestMethod.PUT)
		    public ResultVo update(@RequestBody List<ProjectPlan> projectPlans) {
			 ResultVo resultVo = new ResultVo();
//			 List<Integer>	listStatus=new ArrayList<Integer>();
//			 Integer proId=0;
//			 for(int i=0;i<projectPlan.size();i++){
//				 proId=projectPlan.get(i).getProjectBasicInfo().getProId();
//				 projectPlanService.update(projectPlan.get(i));
//				 listStatus.add(projectPlan.get(i).getProjectSchedule().getProScheduleId());
//			 }
//			 Integer proStatus=1;
//			 if(listStatus.contains(2)){
//				 proStatus=2; 
//			 }else if(listStatus.contains(3)&&!listStatus.contains(2)&&!listStatus.contains(1)){
//				 proStatus=3; 
//			 }else if(listStatus.contains(0)&&!listStatus.contains(2)&&!listStatus.contains(3)){
//				 proStatus=1; 
//			 }
//			 ProjectBasicInfo projectBasicInfo=projectBasicInfoService.getById(proId);
//			 ProjectSchedule projectSchedule=new ProjectSchedule();
//			 projectSchedule.setProScheduleId(proStatus);
//			 projectBasicInfo.setProjectSchedule(projectSchedule);
//			 projectBasicInfoService.updateProStatus(projectBasicInfo);
			 try {
					if(projectPlans !=null && projectPlans.size()>0){
						ProjectBasicInfo projectBasicInfo = projectPlans.get(0).getProjectBasicInfo();
						if(projectBasicInfo!=null)
						{
							Integer proId = projectBasicInfo.getProId();
							projectPlanService.delete(proId);
						}
					
						for (ProjectPlan projectPlan : projectPlans) {
							projectPlanService.save(projectPlan);
							resultVo.setErrCode(0);
							resultVo.setErrMsg("编辑成功");
						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultVo.setErrCode(1);
					resultVo.setErrMsg("编辑失败");
				}
				return resultVo;
		 }
       
		 /**
		 * 删除对象的方法
		 * @Description TODO
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:08
		 */
		 @RequestMapping(value = "/projectPlan/delete/{id}", method = RequestMethod.DELETE)
		    public void delete(@PathVariable("id") Integer id) {
			 projectPlanService.delete(id);
		    }
		 
		 
		 
		 /**
			 * @Description App新增用户信息
			 * @author llwangi
			 * @version V1.0
			 * @date 2018年2月6日 上午11:49:52
			 */
			@RequestMapping(value = "/projectPlan/saveOrUpdateForApp", method = RequestMethod.POST)
			public ResultVo saveForApp(
					@RequestParam(value = "proPlanId", required = false) int proPlanId,
					@RequestParam(value = "proId", required = false) int proId,
					@RequestParam(value = "proPlanName", required = false) String proPlanName,
					@RequestParam(value = "proScheduleId", required = false) int proScheduleId,
					@RequestParam(value = "proPlanStarttime", required = false) String proPlanStarttime,
					@RequestParam(value = "proPlanDeadline", required = false) String proPlanDeadline
					) {
				ResultVo resultVo = new ResultVo();
				List<ProjectPlan> projectPlans=projectPlanService.getByProId(proId);
				List<String> proPlanNames=new ArrayList<String>();
				if(projectPlans!=null&&projectPlans.size()>0){
					for(ProjectPlan projectPlan:projectPlans){
						proPlanNames.add(projectPlan.getProPlanName());
					}
				}
			try {
				if(proPlanId==0){
					//if(!proPlanNames.contains(proPlanName)){
						ProjectPlan projectPlan=new ProjectPlan();
						projectPlan.setProPlanName(proPlanName);
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						if(proPlanStarttime!=null){
							Date Starttime=sdf.parse(proPlanStarttime);
							projectPlan.setProPlanStarttime(Starttime);
						}
						if(proPlanDeadline!=null){
							Date Deadline=sdf.parse(proPlanDeadline);
							projectPlan.setProPlanDeadline(Deadline);
						}
						ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();
						projectBasicInfo.setProId(proId);
						if(proScheduleId!=0){
							ProjectSchedule projectSchedule=new ProjectSchedule();
							projectSchedule.setProScheduleId(proScheduleId);
							projectPlan.setProjectSchedule(projectSchedule);
						}else{
							ProjectSchedule projectSchedule=new ProjectSchedule();
							projectSchedule.setProScheduleId(1);
							projectPlan.setProjectSchedule(projectSchedule);
						}
						projectPlan.setProjectBasicInfo(projectBasicInfo);
						projectPlanService.save(projectPlan);
					//}
				}else{
					//if(!proPlanNames.contains(proPlanName)){
						ProjectPlan projectPlan=projectPlanService.getById(proPlanId);
						if(proPlanName!=null){
							projectPlan.setProPlanName(proPlanName);
						}
						
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						if(proPlanStarttime!=null){
							Date Starttime=sdf.parse(proPlanStarttime);
							projectPlan.setProPlanStarttime(Starttime);
						}
						if(proPlanDeadline!=null){
							Date Deadline=sdf.parse(proPlanDeadline);
							projectPlan.setProPlanDeadline(Deadline);
						}
						ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();
						projectBasicInfo.setProId(proId);
						projectPlan.setProjectBasicInfo(projectBasicInfo);
						if(proScheduleId!=0){
							ProjectSchedule projectSchedule=new ProjectSchedule();
							projectSchedule.setProScheduleId(proScheduleId);
							projectPlan.setProjectSchedule(projectSchedule);
						}
						projectPlanService.update(projectPlan);
					//}
				}
			} catch (Exception e) {
				resultVo.setErrCode(2);
				resultVo.setErrMsg("save ProjectBasicInfo failed");
			}
					
			return resultVo;
			
			}
			
			
			 /**
			 * 删除对象的方法
			 * @Description TODO
			 * @author xux
			 * @version V1.0
			 * @date 2017年5月19日 上午11:22:08
			 */
			 @RequestMapping(value = "/projectPlan/deleteForApp/{id}", method = RequestMethod.POST)
			    public void deleteForApp(@PathVariable("id") Integer id) {
				 projectPlanService.deleteByPlanId(id);
			    }
			 
			 
			 /**
				 * 删除对象的方法
				 * @Description TODO
				 * @author xux
				 * @version V1.0
				 * @date 2017年5月19日 上午11:22:08
				 */
				 @RequestMapping(value = "/projectPlan/deleteForAppIos/{id}", method = RequestMethod.POST)
				    public ResultVo deleteForAppIos(@PathVariable("id") Integer id) {
					 ResultVo res=new ResultVo();
					 try {
						 projectPlanService.deleteByPlanId(id);
						 res.setErrCode(0);
						 res.setErrMsg("删除成功");
					} catch (Exception e) {
						 res.setErrCode(1);
						 res.setErrMsg("删除失败");
					}
					return res;
				    }
	
			/**
			 * 根据项目id获取该项目的项目计划最小开始时间以及最大结束时间
			 * @Description TODO
			 * @author xux
			 * @version V1.0
			 * @date 2017年5月19日 上午11:22:08
			 */
			@RequestMapping(value = "/getProjectPlanTimeByProId/{id}", method = RequestMethod.GET)
			public ResultVo getProjectPlanTimeByProId(@PathVariable("id") Integer id) {
				ResultVo res=new ResultVo();
				try {
					ProjectPlan projectPlan=projectPlanService.getProjectPlanTimeByProId(id);
					res.setErrCode(0);
					res.setErrMsg("查找成功");
					res.setResultData(projectPlan);
				} catch (Exception e) {
					res.setErrCode(1);
					res.setErrMsg("无数据");
					res.setResultData(null);
				}
				return res;
			}
			
			}
