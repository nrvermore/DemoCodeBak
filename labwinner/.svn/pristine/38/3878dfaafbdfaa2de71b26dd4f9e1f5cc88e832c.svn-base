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
import com.labwinner.common.ResultVo;
import com.labwinner.domain.ShareProject;
import com.labwinner.service.*;

/**
 *共享项目Controller
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午6:05:23
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class ShareProjectController {
	private static Logger logger = LoggerFactory
			.getLogger(ShareProjectController.class);
  
	
	@Autowired
	private ShareProjectService shareProjectService;

	
	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	 @RequestMapping(value = "/shareProject", method = RequestMethod.GET)
	    public List<ShareProject> getAll() {
		 List<ShareProject> list = shareProjectService.getAll();
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
	 @RequestMapping(value = "/ShareProject/{id}", method = RequestMethod.GET)
	    public ShareProject getById(@PathVariable("id") Integer id) {
		 ShareProject shareProject = shareProjectService.getById(id);
		 if (shareProject == null) {
				String message = "对象不存在(id:" + id + ")";
				logger.warn(message);
			
			}
			return shareProject;
	    }
	 /**
		 * 保存对象
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月18日 下午6:36:23
		 */
	 @RequestMapping(value = "/shareProject", method = RequestMethod.POST)
	    public ResultVo save(@RequestBody ShareProject shareProject) {
		try {
			ResultVo resultVo = new ResultVo();
			 shareProjectService.save(shareProject);
			 resultVo.setErrCode(0);
			 resultVo.setErrMsg("sava successe");
				return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
	 }
	 
	
	 
	 /**
		 * 更新对象的方法
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:57
		 */
		 @RequestMapping(value = "/shareProject", method = RequestMethod.PUT)
		    public ResultVo update(@RequestBody ShareProject shareProject) {
			 try {
				 ResultVo resultVo = new ResultVo();
					shareProjectService.update(shareProject);
					 resultVo.setErrCode(0);
					 resultVo.setErrMsg("update successe");
						return resultVo;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return null;
		    }
       
		 /**
		 * 删除对象的方法
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:08
		 */
		 @RequestMapping(value = "/shareProject/{id}", method = RequestMethod.DELETE)
		    public ResultVo delete(@PathVariable("id") Integer id) {
			 try {
				ResultVo resultVo = new ResultVo();
				 shareProjectService.delete(id);
				    resultVo.setErrCode(0);
					resultVo.setErrMsg("delete successe");
					return resultVo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		    }
 
}
