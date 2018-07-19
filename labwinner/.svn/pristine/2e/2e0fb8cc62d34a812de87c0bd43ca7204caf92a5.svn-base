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
import com.labwinner.domain.PersonalReactionTemplateParameter;
import com.labwinner.service.PersonalReactionTemplateParameterService;

/**
 * 个人试验模板参数Controller
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午4:22:48
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class PersonalReactionTemplateParameterController {

	private static Logger logger = LoggerFactory
			.getLogger(PersonalReactionTemplateParameterController.class);
	@Autowired
	private PersonalReactionTemplateParameterService personalReactionTemplateParameterService; 

	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:16
	 */
	 @RequestMapping(value = "/personalReactionTemplateParameterService", method = RequestMethod.GET)
	    public List<PersonalReactionTemplateParameter> getAll() {
		 List<PersonalReactionTemplateParameter> list = personalReactionTemplateParameterService.getAll();
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
	 @RequestMapping(value = "/personalReactionTemplateParameter/{id}", method = RequestMethod.GET)
	    public PersonalReactionTemplateParameter getById(@PathVariable("id") Integer id) {
		 PersonalReactionTemplateParameter personalReactionTemplateParameter = personalReactionTemplateParameterService.getById(id);
			if (personalReactionTemplateParameter == null) {
				String message = "对象不存在(id:" + id + ")";
				logger.warn(message);
				
			}
			return personalReactionTemplateParameter;
	    }

	/**
	 * 保存对象方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:40
	 */
	 @RequestMapping(value = "/personalReactionTemplateParameter", method = RequestMethod.POST)
	    public void save(@RequestBody PersonalReactionTemplateParameter personalReactionTemplateParameter) {
		 personalReactionTemplateParameterService.save(personalReactionTemplateParameter);
	 }
	
	 /**
		 * 更新对象的方法
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:57
		 */
		 @RequestMapping(value = "/personalReactionTemplateParameter", method = RequestMethod.PUT)
		    public void update(@RequestBody PersonalReactionTemplateParameter personalReactionTemplateParameter) {
			 personalReactionTemplateParameterService.update(personalReactionTemplateParameter);
		    }
	 
	/**
	 * 删除对象方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午4:23:58
	 */
		 @RequestMapping(value = "/personalReactionTemplateParameter/{id}", method = RequestMethod.DELETE)
		    public void delete(@PathVariable("id") Integer id) {
			 personalReactionTemplateParameterService.delete(id);
		    }

}
