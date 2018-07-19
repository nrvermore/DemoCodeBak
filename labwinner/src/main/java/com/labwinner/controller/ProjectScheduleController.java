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
import com.labwinner.domain.ProjectSchedule;
import com.labwinner.service.ProjectScheduleService;
/**
 * 项目进度状态Service接口
 * @Description TODO
 * @author xux
 * @version V1.0
 * @date 2017年5月18日 下午4:16:55
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class ProjectScheduleController {
	private static Logger logger = LoggerFactory
			.getLogger(ProjectScheduleController.class);
  
	
	@Autowired
	private ProjectScheduleService projectScheduleService;
	
	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	 @RequestMapping(value = "/ProjectSchedule/list", method = RequestMethod.GET)
	    public List<ProjectSchedule> getAll() {
		 List<ProjectSchedule> list = projectScheduleService.getAll();
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
	 @RequestMapping(value = "/ProjectSchedule/{id}", method = RequestMethod.GET)
	    public ProjectSchedule getById(@PathVariable("id") Integer id) {
		 ProjectSchedule ProjectSchedule = projectScheduleService.getById(id);
		 if (ProjectSchedule == null) {
				String message = "对象不存在(id:" + id + ")";
				logger.warn(message);
			
			}
			return ProjectSchedule;
	    }
	 
	 /**
		 * 保存对象
		 * @Description TODO
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 下午6:36:23
		 */
	 @RequestMapping(value = "/ProjectSchedule/add", method = RequestMethod.POST)
	    public void save(@RequestBody ProjectSchedule ProjectSchedule) {
		 projectScheduleService.save(ProjectSchedule);
	 }
	 /**
		 * 更新对象的方法
		 * @Description TODO
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:57
		 */
		 @RequestMapping(value = "/ProjectSchedule", method = RequestMethod.PUT)
		    public void update(@RequestBody ProjectSchedule ProjectSchedule) {
			 projectScheduleService.update(ProjectSchedule);
		    }
       
		 /**
		 * 删除对象的方法
		 * @Description TODO
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:08
		 */
		 @RequestMapping(value = "/ProjectSchedule/delete/{id}", method = RequestMethod.DELETE)
		    public void delete(@PathVariable("id") Integer id) {
			 projectScheduleService.delete(id);
		    }
 
	
	
}