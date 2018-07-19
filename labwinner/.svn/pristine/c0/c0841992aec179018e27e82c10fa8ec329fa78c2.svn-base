package com.labwinner.controller;
import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Device;
import com.labwinner.domain.DevicePicture;
import com.labwinner.domain.Questions;
import com.labwinner.service.*;
import com.labwinner.util.Base64Util;
import com.labwinner.vo.QuestionsVo;

/**
 * 设备类型Controller
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 下午5:41:35
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class QuestionsController {
  
	private static Logger logger = LoggerFactory
			.getLogger(QuestionsController.class);
	@Autowired
	private QuestionsService questionsService;
	
	@Value("${questions.upload-path}")
	String filePath;
	@Value("${questions.url-path}")
	String urlPath;

	/**
	 * 获取所有对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:45
	 */
	@RequestMapping(value = "/Questions", method = RequestMethod.GET)
    public List<Questions> getAll() {
		List<Questions> list = questionsService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
    }
	/**
	 * 获取所有排序对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:45
	 */
	@RequestMapping(value = "/Questions/getAllDesc", method = RequestMethod.GET)
	public List<Questions> getAllDesc() {
		List<Questions> list = questionsService.getAllDesc();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
	}
	
	@RequestMapping(value = "/QuestionsAllName", method = RequestMethod.GET)
	public List<Questions> getAllName() {
		List<Questions> list = questionsService.getAllName();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
	}
	
	
	/**
	 * @Description 根据关键字分页获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/QuestionsPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<Questions> questions = questionsService.getAllPageable(keyword);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(questions));
			return resultVo;
		} else {
			List<Questions> questions = questionsService.getAll();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(questions));
			return resultVo;
		}
	}
	/**
	 * @Description 根据关键字排序分页获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/QuestionsPageableDesc/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllDescPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<Questions> questions = questionsService.getAllDescPageable(keyword);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(questions));
			return resultVo;
		} else {
			List<Questions> questions = questionsService.getAllDesc();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(questions));
			return resultVo;
		}
	}

	/**
	 * 根据主键获取对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:53
	 */
	@RequestMapping(value = "/Questions/{id}", method = RequestMethod.GET)
    public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();

		     
			Questions questions = questionsService.getById(id);
			    Integer time=questions.getTime();
			try {
				time++;
				questions.setTime(time);
				questionsService.questions(questions);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(questions);
				return resultVo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find fail");
				resultVo.setResultData(null);
				return resultVo; 
			}		
    }

	/**
	 * @Description 点击次数自增
	 * @author shg
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void questions(Questions questions) {

		questionsService.questions(questions);
	}
	
	
	
	
	/**
	 * 保存对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:42:03
	 */
	@RequestMapping(value = "/Questions", method = RequestMethod.POST)
    public ResultVo save(@RequestBody Questions questions) {
		ResultVo resultVo = new ResultVo();
		try {
			questions.setTime(0);
			questionsService.save(questions);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("sava successe");
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("sava fail");
			return resultVo;
		}
 }
	/**
	 * 保存图片的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:42:03
	 */
	@RequestMapping(value = "/Questions/uploadImage", method = RequestMethod.POST)
	public ResultVo uploadImage(@RequestBody String img) {
		ResultVo resultVo = new ResultVo();
		// 保存上传图片
		
			if(img!=null){
			img = img.substring(img.indexOf(",") + 1);
			String filename = new Base64Util().GenerateImage(img,filePath);
			String url = urlPath+filename;
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			resultVo.setResultData(url);
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("upload is null");
		return resultVo;
	}

	
	 /**
		 * 更新对象的方法
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月19日 上午11:22:57
		 */
		 @RequestMapping(value = "/Questions", method = RequestMethod.PUT)
		    public ResultVo update(@RequestBody QuestionsVo questionsVo) {
			 ResultVo resultVo = new ResultVo();
			 
			 Questions questions=questionsVo.getQuestions();
			 List<String> urls=questionsVo.getUrls();
				try {
					questionsService.update(questions);
					//删除文件夹中要删的url图片
					if (null != urls && urls.size() > 0) {
						for (String url : urls) {
							new File(filePath+url.substring(url.lastIndexOf("/") + 1)).delete();
						}
					}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("update successe");
					return resultVo;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultVo.setErrCode(1);
					resultVo.setErrMsg("update fail");
					return resultVo;
				}
		    }
      

	/**
	 * 删除对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:42:13
	 */
		 @RequestMapping(value = "/Questions/{id}", method = RequestMethod.DELETE)
		    public ResultVo delete(@PathVariable("id") Integer id) {
			 ResultVo resultVo = new ResultVo();
					try {
						questionsService.delete(id);
						resultVo.setErrCode(0);
						resultVo.setErrMsg("删除成功！！！");
						return resultVo;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						resultVo.setErrCode(1);
						resultVo.setErrMsg("删除失败！！！");
						return resultVo;
					}
		    }

}
