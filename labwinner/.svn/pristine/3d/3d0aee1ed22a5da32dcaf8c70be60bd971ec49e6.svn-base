 package com.labwinner.controller;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.ProjectPicture;
import com.labwinner.service.*;
/**
 *项目图片Controller
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午6:05:23
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class ProjectPictureController {
	private static Logger logger = LoggerFactory
			.getLogger(ProjectPictureController.class);
  
	
	@Autowired
	private ProjectPictureService projectPictureService;
	@Value("${projectPicture.url-path}")
	private String urlPath;
	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	 @RequestMapping(value = "/projectPicture", method = RequestMethod.GET)
	    public List<ProjectPicture> getAll() {
		 List<ProjectPicture> list = projectPictureService.getAll();
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
	 @RequestMapping(value = "/projectPicture/getImage/{id}", method = RequestMethod.GET)
		public ResultVo getList(@PathVariable("id") Integer id){
			ResultVo resultVo = new ResultVo();
			List<String> imageStrs = projectPictureService.getByProId(id);
			if(null==imageStrs){
				String message = "没有对象";
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

}
