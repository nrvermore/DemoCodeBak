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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.DesignDosage;
import com.labwinner.domain.IndustryReactionTemplate;
import com.labwinner.domain.IndustryReactionTemplateParameter;
import com.labwinner.domain.PersonalTemplate;
import com.labwinner.domain.ProfessionProcess;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ReactionDesignChemical;
import com.labwinner.domain.SysRole;
import com.labwinner.domain.SysUser;
import com.labwinner.service.IndustryReactionTemplateParameterService;
import com.labwinner.service.IndustryReactionTemplateService;
import com.labwinner.service.ProfessionProcessService;
import com.labwinner.vo.IndustryReactionTemplateVo;

/**
 * 行业试验模板Controller
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午4:22:48
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class IndustryReactionTemplateController {

	private static Logger logger = LoggerFactory
			.getLogger(IndustryReactionTemplateController.class);
	@Autowired
	private IndustryReactionTemplateService industryReactionTemplateService; 

	@Autowired
	private ProfessionProcessService professionProcessService; 
	
	@Autowired
	private IndustryReactionTemplateParameterService industryReactionTemplateParameterService; 
	
	
	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:16
	 */
	 @RequestMapping(value = "/industryReactionTemplate", method = RequestMethod.GET)
	    public ResultVo getAll() {
		 List<IndustryReactionTemplate> list = industryReactionTemplateService.getAll();
		 ResultVo resultVo = new ResultVo();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(list);
			if (list == null) {
				String message = "没有对象";
				logger.warn(message);
			}
			return resultVo;
	    }


		/**
		 * @Description 根据关键字获取对象
		 * @author liuhq
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		@RequestMapping(value = "/industryReactionTemplate/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
		@ResponseBody
		public ResultVo getAllPageable(@PathVariable Integer page,
				@PathVariable Integer pageSize,
				@PathVariable String keyword) {
			
			 ResultVo resultVo = new ResultVo();
				if (page != null && pageSize != null) {
					PageHelper.startPage(page, pageSize);
				}
				if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
					List<IndustryReactionTemplate> industryReactionTemplates = industryReactionTemplateService.getAllPageable(keyword);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(industryReactionTemplates));
					return resultVo;
				} else {
					List<IndustryReactionTemplate> industryReactionTemplates = industryReactionTemplateService.getAll();
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(industryReactionTemplates));
					return resultVo;
				}
		}
		
	 
		/**
		 * @Description 带搜索APP列表分页
		 * @author liuhq
		 * @version V1.0
		 * @date 2017年5月18日 上午11:50:28
		 */
		@RequestMapping(value = "/IndustryReactionTemplateAppPageableAll/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
		@ResponseBody
		public ResultVo getAPPPageable(@PathVariable Integer page,
				@PathVariable Integer pageSize,@PathVariable String keyword) {
			
			ResultVo resultVo = new ResultVo();
			/*if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}*/
			List<IndustryReactionTemplate> industryReactionTemplates = new ArrayList<IndustryReactionTemplate>();
			
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				industryReactionTemplates = industryReactionTemplateService.getAllPageable(keyword);
			} else {
				industryReactionTemplates = industryReactionTemplateService.getAll();
			}
			if(industryReactionTemplates!=null&&industryReactionTemplates.size()>0){
				int total = industryReactionTemplates.size();
				double c = (((double) total) / pageSize);
				int d = (int) Math.ceil(c);
				PageEntity pageEntity = new PageEntity();
				pageEntity.setPageNum(page);
				pageEntity.setPageSize(pageSize);
				pageEntity.setPages(d);
				pageEntity.setTotal(total);
				int num = industryReactionTemplates.size() > page * pageSize ? page * pageSize : industryReactionTemplates.size();
				if (page <= d) {
					pageEntity.setList(industryReactionTemplates.subList(0, num));
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
	 @RequestMapping(value = "/industryReactionTemplate/{id}", method = RequestMethod.GET)
	    public IndustryReactionTemplate getById(@PathVariable("id") Integer id) {
		 IndustryReactionTemplate industryReactionTemplate = industryReactionTemplateService.getById(id);
			if (industryReactionTemplate == null) {
				String message = "对象不存在(id:" + id + ")";
				logger.warn(message);
				
			}
			return industryReactionTemplate;
	    }

	/**
	 * 保存对象方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:40
	 */
	 @RequestMapping(value = "/industryReactionTemplate", method = RequestMethod.POST)
	    public ResultVo saveOrUpdate(@RequestBody IndustryReactionTemplateVo industryReactionTemplateVo) {
		 ResultVo resultVo = new ResultVo();
		 IndustryReactionTemplate industryReactionTemplate=industryReactionTemplateVo.getIndustryReactionTemplate();
		 Integer id=industryReactionTemplate.getIndustryTemplateId();
		 if(id==null){
			 industryReactionTemplateService.save(industryReactionTemplate);
			 List<ProfessionProcess> professionProcesses =industryReactionTemplateVo.getProfessionProcesses();
			 for (ProfessionProcess professionProcess : professionProcesses) {
				 professionProcess.setIndustryReactionTemplate(industryReactionTemplate);
				 professionProcessService.save(professionProcess);
					
					List<IndustryReactionTemplateParameter> industryReactionTemplateParameters = new ArrayList<IndustryReactionTemplateParameter>(professionProcess.getIndustryReactionTemplateParameters());
					for (IndustryReactionTemplateParameter industryReactionTemplateParameter : industryReactionTemplateParameters) {
						industryReactionTemplateParameter.setProfessionProcess(professionProcess);
						industryReactionTemplateParameterService.save(industryReactionTemplateParameter);
					}
				}
		 }else {
			 industryReactionTemplateService.update(industryReactionTemplate);
			 List<ProfessionProcess> professionProcesses =industryReactionTemplateVo.getProfessionProcesses();
			 List<ProfessionProcess> professionProcesses2 = industryReactionTemplate.getProfessionProcesses();
			for (ProfessionProcess professionProcess : professionProcesses2) {
				 professionProcessService.delete(id);
			}
			 for (ProfessionProcess professionProcess : professionProcesses) {
				 professionProcess.setIndustryReactionTemplate(industryReactionTemplate);
				 professionProcessService.save(professionProcess);
					
					List<IndustryReactionTemplateParameter> industryReactionTemplateParameters = new ArrayList<IndustryReactionTemplateParameter>(professionProcess.getIndustryReactionTemplateParameters());
					for (IndustryReactionTemplateParameter industryReactionTemplateParameter : industryReactionTemplateParameters) {
						industryReactionTemplateParameter.setProfessionProcess(professionProcess);
						industryReactionTemplateParameterService.save(industryReactionTemplateParameter);
					}
				} 
		}
		    
		return resultVo;
	 }
	
	 /**
		 * 更新对象的方法
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:57
		 */
		 @RequestMapping(value = "/industryReactionTemplate", method = RequestMethod.PUT)
		    public void update(@RequestBody IndustryReactionTemplate industryReactionTemplate) {
			 industryReactionTemplateService.update(industryReactionTemplate);
		    }
	 
	/**
	 * 删除对象方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:58
	 */
		 @RequestMapping(value = "/industryReactionTemplate/{id}", method = RequestMethod.DELETE)
		    public void delete(@PathVariable("id") Integer id) {
			 industryReactionTemplateService.delete(id);
		    }

}
