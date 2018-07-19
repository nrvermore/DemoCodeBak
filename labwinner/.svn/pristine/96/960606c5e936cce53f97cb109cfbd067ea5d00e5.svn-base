package com.labwinner.controller;

import java.util.ArrayList;
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
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectNumber;
import com.labwinner.domain.SysUser;
import com.labwinner.service.*;
import com.labwinner.vo.ProjectNumberVo;

/**
 * 项目成员Controller
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
public class ProjectNumberController {
	private static Logger logger = LoggerFactory
			.getLogger(ProjectNumberController.class);

	@Autowired
	private ProjectNumberService projectNumberService;
	
	@Autowired
	private ReactionDesignService reactionDesignService;
	
	@Autowired
	private ReactionService reactionService;
	
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/projectNumber/list", method = RequestMethod.GET)
	public List<ProjectNumber> getAll() {

		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
        if(roleName.equals("ROLE_TEAM")){
        	List<ProjectNumber> list = projectNumberService.getAllProjects();
        	return list;
        }else{
			List<ProjectNumber> list = projectNumberService.getAll(userId);
			return list;
        }
	}
	
	/**
	 * 获取当前用户所在项目
	 * 
	 * @Description TODO
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/projectNumber/getProjects", method = RequestMethod.GET)
	public ResultVo getProjects() {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();

		List<ProjectNumber> list = projectNumberService.getProjects(userId);
		if(list!=null && list.size()>0){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(list);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("list is null");
		return resultVo;
	}

	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/projectNumber/getPrincipals", method = RequestMethod.GET)
	public List<ProjectNumber> getPrincipals() {

		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();

		List<ProjectNumber> list = projectNumberService.getPrincipals(userId);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}

	/**
	 * 根据项目id获取对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/projectNumber/getByProId/{id}", method = RequestMethod.GET)
	public List<ProjectNumber> getByProId(@PathVariable("id") Integer id) {

		List<ProjectNumber> list = projectNumberService.getByProId(id);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}

	/**
	 * 根据主键获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/ProjectNumber/{id}", method = RequestMethod.GET)
	public ProjectNumber getById(@PathVariable("id") Integer id) {
		ProjectNumber projectNumber = projectNumberService.getById(id);
		if (projectNumber == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);

		}
		return projectNumber;
	}

	/**
	 * 保存对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/ProjectNumber/add", method = RequestMethod.POST)
	public void save(@RequestBody ProjectNumber projectNumber) {
		projectNumber.setFlag(0);
		projectNumberService.save(projectNumber);
	}

	/**
	 * 更新对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	@RequestMapping(value = "/ProjectNumber", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody List<ProjectNumber> projectNumbers) {
		ResultVo resultVo = new ResultVo();
		try {
			if(projectNumbers !=null && projectNumbers.size()>0){
				ProjectBasicInfo projectBasicInfo = projectNumbers.get(0).getProjectBasicInfo();
				if(projectBasicInfo!=null)
				{
					Integer proId = projectBasicInfo.getProId();
					projectNumberService.delete(proId);
				}
			
				for (ProjectNumber projectNumber : projectNumbers) {
					projectNumberService.save(projectNumber);
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
	 * 更新项目成员方法
	 * 
	 * @Description TODO
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	@RequestMapping(value = "/ProjectNumber/updateNumbers", method = RequestMethod.PUT)
	public ResultVo updateNumbers(@RequestBody ProjectNumberVo projectNumberVo) {
		
		ResultVo resultVo = new ResultVo();
		try {
			ProjectBasicInfo projectBasicInfo = new ProjectBasicInfo();
			Integer proId = projectNumberVo.getProId();
			projectBasicInfo.setProId(proId);
			List<ProjectNumber> newNumbers = projectNumberVo.getProjectNumbers();
			List<ProjectNumber> oldNumbers = projectNumberService.getByProId(proId);
			List<Integer> oldIds = new ArrayList<Integer>();
			List<Integer> newIds = new ArrayList<Integer>();
			if(oldNumbers!=null){
				for (ProjectNumber oldNumber : oldNumbers) {
					oldIds.add(oldNumber.getProNumberId());
			}
			}
			if(newNumbers!=null){
				for (ProjectNumber newNumber : newNumbers) {
					//保存没主键的项目成员
					if(newNumber.getProNumberId()==null){
						//TODO新增判断数据库是否有数据有update，无save
						Integer roleId = newNumber.getProjectRole().getProRoleId();
						Integer userId = newNumber.getSysUser().getUserId();
						ProjectNumber projectNumber = projectNumberService.getProjectNumber(roleId,userId,1, proId);
						if(projectNumber==null){
							newNumber.setFlag(0);
							newNumber.setProjectBasicInfo(projectBasicInfo);
							projectNumberService.save(newNumber);
						}else{
							projectNumber.setFlag(0);
							projectNumberService.update(projectNumber);
							newIds.add(projectNumber.getProNumberId());
						}
					}else{
						newIds.add(newNumber.getProNumberId());
					}
				}
			}
			//判断是否有负责实验或实验设计，有update，无delete
			for (Integer oldId : oldIds) {
				if(!newIds.contains(oldId)){
					ProjectNumber projectNumber=projectNumberService.getById(oldId);
					if(reactionDesignService.getCount(projectNumber.getSysUser().getUserId())>0 || reactionService.getCount(projectNumber.getSysUser().getUserId())>0){
						projectNumberService.updateByDelete(oldId,1,2);
					}else{
						projectNumberService.deleteById(oldId);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("update fail");
			return resultVo;
			
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("update success");
		return resultVo;
	}

	/**
	 * 删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/ProjectNumber/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		projectNumberService.delete(id);
	}
	
	
	/**
	 * 删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/ProjectNumber/deleteApp/{id}", method = RequestMethod.POST)
	public void deleteApp(@PathVariable("id") Integer id) {
		ProjectNumber projectNumber=projectNumberService.getById(id);
		
		if(reactionDesignService.getCount(projectNumber.getSysUser().getUserId())>0 || reactionService.getCount(projectNumber.getSysUser().getUserId())>0){
			projectNumberService.updateByDelete(id,1,2);
		}else{
			projectNumberService.deleteById(id);
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
	@RequestMapping(value = "/ProjectNumber/deleteAppIos/{id}", method = RequestMethod.POST)
	public ResultVo deleteAppIos(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		try {
			ProjectNumber projectNumber=projectNumberService.getById(id);
			
			if(reactionDesignService.getCount(projectNumber.getSysUser().getUserId())>0 || reactionService.getCount(projectNumber.getSysUser().getUserId())>0){
				projectNumberService.updateByDelete(id,1,2);
			}else{
				projectNumberService.deleteById(id);
			}
			res.setErrCode(0);
			res.setErrMsg("删除成功");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("删除失败");
		}
		return res;
		
	}

}
