package com.labwinner.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.labwinner.domain.DesignTechnology;
import com.labwinner.domain.DesignTechnologyDosage;
import com.labwinner.domain.DesignTechnologyProcess;
import com.labwinner.domain.DesignTechnologyRelation;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.SysUser;
import com.labwinner.service.*;
import com.labwinner.vo.DesignTechnologyRelationVo;
import com.labwinner.vo.DesignTechnologyVo;

/**
 * 设备Controller
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
public class DesignTechnologyController {
	private static Logger logger = LoggerFactory
			.getLogger(DesignTechnologyController.class);

	@Autowired
	private DesignTechnologyService designTechnologyService;

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private DesignTechnologyDosageService designTechnologyDosageService;
	
	@Autowired
	private DesignTechnologyProcessService designTechnologyProcessService;
	
	@Autowired
	private DesignTechnologyRelationService designTechnologyRelationService;
	

	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/designTechnology", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		List<DesignTechnology> list = designTechnologyService.getAll();
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
	 * @Description 根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/designTechnologyPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable String keyword) {
		
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<DesignTechnology> designTechnologies = designTechnologyService.getAllPageable(keyword);
			if(designTechnologies!=null && designTechnologies.size()>0){

			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(designTechnologies));
			return resultVo;
		}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find fail");
			return resultVo;
			
	}
		return resultVo;
		
	}
	/**
	 * @Description web端分页获取工艺对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/getTechnologyByreactionDesignId/{page}/{pageSize}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getTechnologyByreactionDesignId(@PathVariable Integer page,@PathVariable Integer pageSize,
			@PathVariable Integer id) {
		     ResultVo resultVo = new ResultVo();
			List<DesignTechnology> designTechnologies = designTechnologyService.getTechnologyByreactionDesignId(id);
			if(designTechnologies!=null && designTechnologies.size()>0){
			      for (DesignTechnology designTechnology : designTechnologies) {
					List<DesignTechnologyProcess> designTechnologyProcesses=designTechnology.getDesignTechnologyProcesses();
					   if(designTechnologyProcesses!=null){
						    designTechnology.setProcesseesNum(designTechnologyProcesses.size());
					   }
				}
				int total = designTechnologies.size();
				double c = (((double) total) / pageSize);
				int d = (int) Math.ceil(c);
				PageEntity pageEntity = new PageEntity();
				pageEntity.setPageNum(page);
				pageEntity.setPageSize(pageSize);
				pageEntity.setPages(d);
				pageEntity.setTotal(total);
				int num = designTechnologies.size() > page * pageSize ? page * pageSize : designTechnologies.size();
				if (page <= d) {
					pageEntity.setList(designTechnologies.subList((page-1)*pageSize, num));
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(pageEntity);
				return resultVo;
			}
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find fail");
				return resultVo;
		}
	
	/**
	 * @Description APP分页获取工艺对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/getAppTechnologyByreactionDesignId/{page}/{pageSize}/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAppTechnologyByreactionDesignId(@PathVariable Integer page,@PathVariable Integer pageSize,
			@PathVariable Integer id) {
		ResultVo resultVo = new ResultVo();
		List<DesignTechnology> designTechnologies = designTechnologyService.getTechnologyByreactionDesignId(id);
		if(designTechnologies!=null && designTechnologies.size()>0){
			for (DesignTechnology designTechnology : designTechnologies) {
				List<DesignTechnologyProcess> designTechnologyProcesses=designTechnology.getDesignTechnologyProcesses();
				if(designTechnologyProcesses!=null){
					designTechnology.setProcesseesNum(designTechnologyProcesses.size());
				}
			}
			int total = designTechnologies.size();
			double c = (((double) total) / pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			int num = designTechnologies.size() > page * pageSize ? page * pageSize : designTechnologies.size();
			if (page <= d) {
				pageEntity.setList(designTechnologies.subList(0, num));
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(pageEntity);
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("find fail");
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
	@RequestMapping(value = "/designTechnology/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

		DesignTechnology designTechnology = designTechnologyService.getById(id);
		if(designTechnology!=null){
			List<DesignTechnologyProcess> designTechnologyProcesses=designTechnology.getDesignTechnologyProcesses();
			if(designTechnologyProcesses!=null){
				designTechnology.setProcesseesNum(designTechnologyProcesses.size());
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(designTechnology);
			return resultVo;
		}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find fail");
			resultVo.setResultData(designTechnology);
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
	@RequestMapping(value = "/designTechnology", method = RequestMethod.POST)
	public ResultVo save(@RequestBody DesignTechnologyRelationVo designTechnologyRelationVo) {
		
		ResultVo resultVo = new ResultVo();
		
		try {
			ReactionDesign reactionDesign=designTechnologyRelationVo.getReactionDesign();
			DesignTechnology designTechnology=designTechnologyRelationVo.getDesignTechnology();
			
			if(designTechnology!=null){
				designTechnologyService.save(designTechnology);
				List<DesignTechnologyProcess> designTechnologyProcesses=new ArrayList<DesignTechnologyProcess>(designTechnology.getDesignTechnologyProcesses());
				if(designTechnologyProcesses!=null){
					for (DesignTechnologyProcess designTechnologyProcess : designTechnologyProcesses) {
						designTechnologyProcess.setDesignTechnology(designTechnology);
						designTechnologyProcessService.save(designTechnologyProcess);
						List<DesignTechnologyDosage> designTechnologyDosages = new ArrayList<DesignTechnologyDosage>(designTechnologyProcess.getDesignTechnologyDosages());
						if(designTechnologyDosages!=null){
							for (DesignTechnologyDosage designTechnologyDosage : designTechnologyDosages) {
								designTechnologyDosage.setDesignTechnologyProcess(designTechnologyProcess);
								designTechnologyDosageService.save(designTechnologyDosage);
							}
						}
					}
				}
			}
			    DesignTechnologyRelation designTechnologyRelation = new DesignTechnologyRelation();
			    if(reactionDesign!=null){
				 designTechnologyRelation.setReactionDesign(reactionDesign);
				}
				designTechnologyRelation.setDesignTechnology(designTechnology);
				designTechnologyRelationService.save(designTechnologyRelation);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("sava successe");
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("sava fail");
			return resultVo;
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
	@RequestMapping(value = "/designTechnology", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody DesignTechnology designTechnology) {
		
		ResultVo resultVo = new ResultVo();
			Integer id=designTechnology.getTechnologyId();
		try {
			DesignTechnology designTechnology2=designTechnologyService.getById(id);
			if(designTechnology2!=null){
			List<DesignTechnologyProcess> designTechnologyProcesses2=designTechnology2.getDesignTechnologyProcesses();
			if(designTechnologyProcesses2!=null){
				for (DesignTechnologyProcess designTechnologyProcess2 : designTechnologyProcesses2) {
					Integer id2=designTechnologyProcess2.getProcessId();
					List<DesignTechnologyDosage> designTechnologyDosages2=designTechnologyProcess2.getDesignTechnologyDosages();
					if(designTechnologyDosages2!=null){
						for (DesignTechnologyDosage designTechnologyDosage2 : designTechnologyDosages2) {
							designTechnologyDosageService.delete(id2);
						}	          
					}
					designTechnologyProcessService.delete(id);
				}	
			  }
			}
			designTechnologyService.update(designTechnology);
			List<DesignTechnologyProcess> designTechnologyProcesses=new ArrayList<DesignTechnologyProcess>(designTechnology.getDesignTechnologyProcesses());
			if(designTechnologyProcesses!=null){
				 for (DesignTechnologyProcess designTechnologyProcess : designTechnologyProcesses) {
					     designTechnologyProcess.setDesignTechnology(designTechnology);
						designTechnologyProcessService.save(designTechnologyProcess);
						List<DesignTechnologyDosage> designTechnologyDosages=new ArrayList<DesignTechnologyDosage>(designTechnologyProcess.getDesignTechnologyDosages());
						if(designTechnologyDosages!=null){
							for (DesignTechnologyDosage designTechnologyDosage : designTechnologyDosages) {
								designTechnologyDosage.setDesignTechnologyProcess(designTechnologyProcess);
								designTechnologyDosageService.save(designTechnologyDosage);
							}
						}
						
					}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe");
			return resultVo;
		} catch (Exception e) {
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
	@RequestMapping(value = "/designTechnology/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

			try {
				designTechnologyService.delete(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("删除成功！！！");
				return resultVo;
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("删除失败！！！");
				return resultVo;
			}
		}
	}
