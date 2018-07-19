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

import com.labwinner.domain.ProjectType;
import com.labwinner.service.ProjectTypeService;
/**
 * 项目类型Service接口
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class ProjectTypeController {
	private static Logger logger = LoggerFactory
			.getLogger(ProjectTypeController.class);
  
	
	@Autowired
	private ProjectTypeService projectTypeService;
	
	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	 @RequestMapping(value = "/projectType/list", method = RequestMethod.GET)
	    public List<ProjectType> getAll() {
		 List<ProjectType> list = projectTypeService.getAll();
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
	 @RequestMapping(value = "/projectType/{id}", method = RequestMethod.GET)
	    public ProjectType getById(@PathVariable("id") Integer id) {
		 ProjectType projectType = projectTypeService.getById(id);
		 if (projectType == null) {
				String message = "对象不存在(id:" + id + ")";
				logger.warn(message);
			
			}
			return projectType;
	    }
	 
	 /**
		 * 保存对象
		 * @Description TODO
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 下午6:36:23
		 */
	 @RequestMapping(value = "/projectType/add", method = RequestMethod.POST)
	    public void save(@RequestBody ProjectType projectType) {
		 projectTypeService.save(projectType);
	 }
	 /**
		 * 更新对象的方法
		 * @Description TODO
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:57
		 */
		 @RequestMapping(value = "/projectType", method = RequestMethod.PUT)
		    public void update(@RequestBody ProjectType projectType) {
			 projectTypeService.update(projectType);
		    }
       
		 /**
		 * 删除对象的方法
		 * @Description TODO
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:08
		 */
		 @RequestMapping(value = "/projectType/delete/{id}", method = RequestMethod.DELETE)
		    public void delete(@PathVariable("id") Integer id) {
			 projectTypeService.delete(id);
		    }
	
}