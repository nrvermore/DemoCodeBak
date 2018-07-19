package com.labwinner.controller;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectDocuments;
import com.labwinner.service.*;

/**
 *项目文档Controller
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午6:05:23
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class ProjectDocumentsController {
	private static Logger logger = LoggerFactory
			.getLogger(ProjectDocumentsController.class);
  
	
	@Autowired
	private ProjectDocumentsService projectDocumentsService;
	
	@Autowired
	private ProjectBasicInfoService projectBasicInfoService;
	
	@Value("${projectDocuments.url-path}")
	private String urlPath;
	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	 @RequestMapping(value = "/ProjectDocuments/list", method = RequestMethod.GET)
	    public List<ProjectDocuments> getAll() {
		 List<ProjectDocuments> list = projectDocumentsService.getAll();
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
	 * @date 2017年5月18日 下午6:36:23
	 */
	 @RequestMapping(value = "/ProjectDocuments/{id}", method = RequestMethod.GET)
	    public ResultVo getList(@PathVariable("id") Integer proId) {
		
		 ResultVo resultVo = new ResultVo();
		 List<String> imageStrs = projectDocumentsService.getByProId(proId);
		 if (null== imageStrs) {
				String message = "对象不存在(id:" + proId + ")";
				logger.warn(message);
			
			} 
		 ArrayList<Object> urls = new ArrayList<Object>();
			for(String imageStr :imageStrs){
				String URL = urlPath+imageStr;
				urls.add(URL);
				
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("get files success");
			resultVo.setResultList(urls);
			return resultVo;
	       
	 }
	 
	 /**
		 * 保存对象
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月18日 下午6:36:23
		 */
	 @RequestMapping(value = "/ProjectDocuments/add", method = RequestMethod.POST)
	    public void save(@RequestBody ProjectDocuments projectDocuments) {
		 projectDocumentsService.save(projectDocuments);
	 }
	 /**
		 * 更新对象的方法
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:57
		 */
		 @RequestMapping(value = "/ProjectDocuments", method = RequestMethod.PUT)
		    public void update(@RequestBody ProjectDocuments projectDocuments) {
			 
			 projectDocumentsService.update(projectDocuments);
		    }
       
		 /** 
		 * 删除对象的方法
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:08
		 */
		 @RequestMapping(value = "/ProjectDocuments/delete/{id}", method = RequestMethod.DELETE)
		    public void delete(@PathVariable("id") Integer id) {
			 projectDocumentsService.delete(id);
		    }
 
}
