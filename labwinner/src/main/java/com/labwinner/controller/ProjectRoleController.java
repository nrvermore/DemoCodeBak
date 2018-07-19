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
import com.labwinner.domain.ProjectRole;
import com.labwinner.service.ProjectRoleService;
/**
 * 项目角色Service接口
 * @Description TODO
 * @author xux
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */

@RestController
public class ProjectRoleController {
	private static Logger logger = LoggerFactory
			.getLogger(ProjectRoleController.class);
  
	
	@Autowired
	private ProjectRoleService projectRoleService;
	
	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	 @RequestMapping(value = "/projectRole/list", method = RequestMethod.GET)
	    public List<ProjectRole> getAll() {
		 List<ProjectRole> list = projectRoleService.getAll();
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
	 @RequestMapping(value = "/projectRole/{id}", method = RequestMethod.GET)
	    public ProjectRole getById(@PathVariable("id") Integer id) {
		 ProjectRole projectRole = projectRoleService.getById(id);
		 if (projectRole == null) {
				String message = "对象不存在(id:" + id + ")";
				logger.warn(message);
			
			}
			return projectRole;
	    }
	 
	 /**
		 * 保存对象
		 * @Description TODO
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 下午6:36:23
		 */
	 @RequestMapping(value = "/projectRole/add", method = RequestMethod.POST)
	    public void save(@RequestBody ProjectRole projectRole) {
		 projectRoleService.save(projectRole);
	 }
	 /**
		 * 更新对象的方法
		 * @Description TODO
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:57
		 */
		 @RequestMapping(value = "/projectRole", method = RequestMethod.PUT)
		    public void update(@RequestBody ProjectRole projectRole) {
			 projectRoleService.update(projectRole);
		    }
       
		 /**
		 * 删除对象的方法
		 * @Description TODO
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:08
		 */
		 @RequestMapping(value = "/projectRole/delete/{id}", method = RequestMethod.DELETE)
		    public void delete(@PathVariable("id") Integer id) {
			 projectRoleService.delete(id);
		    }
 
}