package com.labwinner.controller;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.DesignTechnology;
import com.labwinner.domain.DesignTechnologyDosage;
import com.labwinner.domain.DesignTechnologyProcess;
import com.labwinner.domain.DesignTechnologyRelation;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.SysUser;
import com.labwinner.service.*;
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
public class DesignTechnologyRelationController {
	private static Logger logger = LoggerFactory
			.getLogger(DesignTechnologyRelationController.class);

	@Autowired
	private DesignTechnologyRelationService designTechnologyRelationService;
	
	@Autowired
	private DesignTechnologyService designTechnologyService;
	
	@Autowired
	private DesignTechnologyProcessService designTechnologyProcessService;
	
	@Autowired
	private DesignTechnologyDosageService designTechnologyDosageService;

	@Autowired
	private ReactionDesignService reactionDesignService;
	
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
	@RequestMapping(value = "/designTechnologyRelation", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		List<DesignTechnologyRelation> list = designTechnologyRelationService.getAll();
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
	@RequestMapping(value = "/designTechnologyRelationPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable String keyword) {
		
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<DesignTechnologyRelation> designTechnologyRelations = designTechnologyRelationService.getAllPageable(keyword);
			if(designTechnologyRelations!=null && designTechnologyRelations.size()>0){

			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(designTechnologyRelations));
			return resultVo;
		}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find fail");
			return resultVo;
			
	}
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
	@RequestMapping(value = "/designTechnologyRelation/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

		List<DesignTechnologyRelation> designTechnologyRelations = designTechnologyRelationService.getById(id);
		if(designTechnologyRelations!=null){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(designTechnologyRelations);
			return resultVo;
		}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find fail");
			resultVo.setResultData(designTechnologyRelations);
			return resultVo;
		}
	/**
	 * 保存或新增对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/designTechnologyRelation/addOrUpdate", method = RequestMethod.POST)
	public ResultVo save(@RequestBody DesignTechnologyVo designTechnologyVo) {
		
		ResultVo resultVo = new ResultVo();
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		
		
		List<DesignTechnology> designTechnologies=designTechnologyVo.getDesignTechnologies();
		ReactionDesign reactionDesign=designTechnologyVo.getReactionDesign();
		Integer type=reactionDesign.getType();
		Integer id=reactionDesign.getReactionDesignId();
		
		if(id==null){
			reactionDesign.setCreateDate(new Date());
			reactionDesign.setSysUser(sysUser);
			reactionDesign.setType(type);
			reactionDesignService.save(reactionDesign);
		
			 if(designTechnologies!=null){
				   for (DesignTechnology designTechnology : designTechnologies) {
					   designTechnologyService.save(designTechnology);
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
					   
					DesignTechnologyRelation designTechnologyRelation = new DesignTechnologyRelation();
					designTechnologyRelation.setReactionDesign(reactionDesign);
					designTechnologyRelation.setDesignTechnology(designTechnology);
					designTechnologyRelationService.save(designTechnologyRelation);
				}
			}
			    resultVo.setErrCode(0);
				resultVo.setErrMsg("save successe");
				resultVo.setResultData(reactionDesign.getReactionDesignId());
				return resultVo;
		}else{
			// designTechnologyRelationService.delete(id);
			if(designTechnologies!=null){
				 for (DesignTechnology designTechnology : designTechnologies) {
					   designTechnologyService.save(designTechnology);
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
					   DesignTechnologyRelation designTechnologyRelation = new DesignTechnologyRelation();
						designTechnologyRelation.setReactionDesign(reactionDesign);
						designTechnologyRelation.setDesignTechnology(designTechnology);
						designTechnologyRelationService.save(designTechnologyRelation);
					}
				}
			 }
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe");
			resultVo.setResultData(reactionDesign.getReactionDesignId());
			return resultVo;		
		}


	/**
	 * 更新对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	@RequestMapping(value = "/designTechnologyRelation", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody DesignTechnologyRelation designTechnologyRelation) {
		
		ResultVo resultVo = new ResultVo();
		
		try {
			designTechnologyRelationService.update(designTechnologyRelation);
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
	@RequestMapping(value = "/designTechnologyRelation/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

			try {
				designTechnologyRelationService.delete(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("删除成功！！！");
				return resultVo;
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("删除失败！！！");
				return resultVo;
			}
		}
	/**
	 * 根据工艺ID删除工艺对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/deleteByTechnologyId/{id}", method = RequestMethod.DELETE)
	public ResultVo deleteByTechnologyId(@PathVariable("id") Integer id) {
		
		ResultVo resultVo = new ResultVo();
		
		try {
			designTechnologyRelationService.deleteByTechnologyId(id);
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
