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
import com.labwinner.domain.IndustryReactionTemplateParameter;
import com.labwinner.service.IndustryReactionTemplateParameterService;

/**
 * 行业试验模板参数Controller
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午4:22:48
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class IndustryReactionTemplateParameterController {

	private static Logger logger = LoggerFactory
			.getLogger(IndustryReactionTemplateParameterController.class);
	@Autowired
	private IndustryReactionTemplateParameterService industryReactionTemplateParameterService; 

	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:16
	 */
	 @RequestMapping(value = "/industryReactionTemplateParameter", method = RequestMethod.GET)
	    public List<IndustryReactionTemplateParameter> getAll() {
		 List<IndustryReactionTemplateParameter> list = industryReactionTemplateParameterService.getAll();
			if (list == null) {
				String message = "没有对象";
				logger.warn(message);
				
			}
			return list;
	    }

	/**
	 * 根据主键获取对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:26
	 */
	 @RequestMapping(value = "/industryReactionTemplateParameter/{id}", method = RequestMethod.GET)
	    public IndustryReactionTemplateParameter getById(@PathVariable("id") Integer id) {
		 IndustryReactionTemplateParameter industryReactionTemplateParameter = industryReactionTemplateParameterService.getById(id);
			if (industryReactionTemplateParameter == null) {
				String message = "对象不存在(id:" + id + ")";
				logger.warn(message);
				
			}
			return industryReactionTemplateParameter;
	    }

	/**
	 * 保存对象方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:40
	 */
	 @RequestMapping(value = "/industryReactionTemplateParameter", method = RequestMethod.POST)
	    public void save(@RequestBody IndustryReactionTemplateParameter industryReactionTemplateParameter) {
		 industryReactionTemplateParameterService.save(industryReactionTemplateParameter);
	 }
	
	 /**
		 * 更新对象的方法
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:57
		 */
		 @RequestMapping(value = "/industryReactionTemplateParameter", method = RequestMethod.PUT)
		    public void update(@RequestBody IndustryReactionTemplateParameter industryReactionTemplateParameter) {
			 industryReactionTemplateParameterService.update(industryReactionTemplateParameter);
		    }
	 
	/**
	 * 删除对象方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:58
	 */
		 @RequestMapping(value = "/industryReactionTemplateParameter/{id}", method = RequestMethod.DELETE)
		    public void delete(@PathVariable("id") Integer id) {
			 industryReactionTemplateParameterService.delete(id);
		    }

}
