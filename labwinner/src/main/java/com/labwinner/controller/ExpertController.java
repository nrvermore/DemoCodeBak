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
import com.labwinner.domain.Expert;
import com.labwinner.domain.SysUser;
import com.labwinner.service.*;
import com.labwinner.util.Base64Util;

/**
 * 设备Controller
 * 
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午6:05:23
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class ExpertController {
	private static Logger logger = LoggerFactory
			.getLogger(ExpertController.class);

	@Autowired
	private ExpertService expertService;
	@Autowired
	private SysUserService sysUserService;

	@Value("${expert.upload-path}")
	String filePath;

	@Value("${expert.url-path}")
	String urlPath;
	
	@Value("${expert.url-path}")
	String imgPath;

	@Value("${sysUserPhone.url-path}")
	String userImage;
	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/expert", method = RequestMethod.GET)
	public List<Expert> getAll() {
		
		try {
			List<Expert> list = expertService.getAll();
				return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/expertUser", method = RequestMethod.GET)
	public List<Expert> getExpertUser() {
		
		try {
			List<Expert> list = expertService.getExpertUser();
				return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/expertByAngency", method = RequestMethod.GET)
	public List<Expert> getAllByAngency() {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		try {
			List<Expert> list = expertService.getAllByAngency(sysUser.getSysSigningAgency().getAgencyId());
				return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @Description 根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/expertPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable String keyword) {
		
		 ResultVo resultVo = new ResultVo();
			if (page != null && pageSize != null) {    
				PageHelper.startPage(page, pageSize); 
			}
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				List<Expert> experts = expertService.getAllPageable(keyword);
				for (Expert expert : experts) {
					String imgUrl=expert.getImgUrl();
					if(imgUrl==null ||"".equals(imgUrl)){
						expert.setImgUrl("");
					}else {
						expert.setImgUrl(urlPath+imgUrl);
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(experts));
				return resultVo;
			} else {
				List<Expert> experts = expertService.getAll();
				for (Expert expert : experts) {
					String imgUrl=expert.getImgUrl();
					if(imgUrl==null ||"".equals(imgUrl)){
						expert.setImgUrl("");
					}else {
						expert.setImgUrl(urlPath+imgUrl);
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(experts));
				return resultVo;
			}
	}

	
	/**
	 * @Description 根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/expertBase/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllExpertBase(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable String keyword) {
		 ResultVo resultVo = new ResultVo();
			if (page != null && pageSize != null) {    
				PageHelper.startPage(page, pageSize); 
			}
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				List<Expert> experts = expertService.getAllExpertBase(keyword);
				for (Expert expert : experts) {
					String url="";
					SysUser expertUser=sysUserService.getExpertUser(expert.getExpertId());
					if(expertUser!=null&&expertUser.getUserImage()!=null){
						url=userImage+expertUser.getUserImage();
					}else if(expert.getImgUrl()!=null){
						url=imgPath+expert.getImgUrl();
					}
					expert.setImgUrl(url);
			}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(experts));
				return resultVo;
			} else {
				List<Expert> experts = expertService.getAllBase();
				for (Expert expert : experts) {
					String url="";
					SysUser expertUser=sysUserService.getExpertUser(expert.getExpertId());
					if(expertUser!=null&&expertUser.getUserImage()!=null){
						url=userImage+expertUser.getUserImage();
					}else if(expert.getImgUrl()!=null){
						url=imgPath+expert.getImgUrl();
					}
					expert.setImgUrl(url);
			}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(experts));
				return resultVo;
			}
	}
	
	/**
	 * @Description 根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/expertKnowledge/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getExpertKnowledge(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable String keyword) {
		
		 ResultVo resultVo = new ResultVo();
			if (page != null && pageSize != null) {    
				PageHelper.startPage(page, pageSize); 
			}
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				List<Expert> experts = expertService.getAllPageableKnow(keyword);
				for (Expert expert : experts) {
					String imgUrl=expert.getImgUrl();
					if(imgUrl==null ||"".equals(imgUrl)){
						expert.setImgUrl("");
					}else {
						expert.setImgUrl(urlPath+imgUrl);
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(experts));
				return resultVo;
			} else {
				List<Expert> experts = expertService.getAllKnow();
				for (Expert expert : experts) {
					String imgUrl=expert.getImgUrl();
					if(imgUrl==null ||"".equals(imgUrl)){
						expert.setImgUrl("");
					}else {
						expert.setImgUrl(urlPath+imgUrl);
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(experts));
				return resultVo;
			}
	}

	/**
	 * 根据主键获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/expert/{id}", method = RequestMethod.GET)
	public Expert getById(@PathVariable("id") Integer id) {

		Expert expert = expertService.getById(id);
		String imgUrl=expert.getImgUrl();
		if(imgUrl==null ||"".equals(imgUrl)){
			expert.setImgUrl("");
		}else {
			expert.setImgUrl(urlPath+imgUrl);
		}
			if (expert == null) {
				String message = "对象不存在(id:" + id + ")";
				logger.warn(message);
				
			}
			return expert;
	}

	/**
	 * 保存对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/expert", method = RequestMethod.POST)
	public ResultVo save(@RequestBody Expert expert) {
		Base64Util base64Util = new Base64Util();
		ResultVo resultVo = new ResultVo();
		String url = expert.getUrl();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer agencyId=sysUser.getSysSigningAgency().getAgencyId();
		expert.setSource(agencyId);
		 if(url!=null ||!"".equals(url)){
			   url = url.substring(url.indexOf(",") + 1);
			   String fileName=base64Util.GenerateImage(url, filePath);
			   expert.setImgUrl(fileName);
			   expertService.save(expert);
			    resultVo.setErrCode(0);
				resultVo.setErrMsg("save successe");
				return resultVo;
		   }else if (url==null || "".equals(url)) {
				
			   expertService.save(expert);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save successe");
				return resultVo;
		}else {
			    resultVo.setErrCode(1);
			    resultVo.setErrMsg("save fail");
			    return resultVo;
		}

			
	}
	/**
	 * 更新对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	@RequestMapping(value = "/expert", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody Expert expert) {
		Base64Util base64Util = new Base64Util();
		ResultVo resultVo = new ResultVo();
		Integer expertId=expert.getExpertId();
		Expert expert2=expertService.getById(expertId);
		String url = expert.getUrl();
		 if (url==null || "".equals(url)) { // 不修改头像
	    	   String imgUrl=expert2.getImgUrl();
	    	    String fileName=imgUrl.substring(imgUrl.lastIndexOf("/")+1);
	    	    expert.setImgUrl(fileName);
	    	    expertService.update(expert);
		       resultVo.setErrCode(0);
		       resultVo.setErrMsg("update successe");
		       return resultVo;
	       } else if (url!=null || !"".equals(url)) { //修改头像及信息
		         
	    	    String imgUrl=expert2.getImgUrl();
		        if(imgUrl==null ||"".equals(imgUrl)){
		        	url = url.substring(url.indexOf(",") + 1);
		        	String fileName=base64Util.GenerateImage(url, filePath);
					expert.setImgUrl(fileName);
					expertService.update(expert);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("update successe");
					return resultVo;
		        }else{
		        	// 删除旧图片
		        	imgUrl=imgUrl.substring(imgUrl.lastIndexOf("/")+1);
					new File(filePath + imgUrl).delete();
					url = url.substring(url.indexOf(",") + 1);
					String fileName=base64Util.GenerateImage(url, filePath);
					expert.setImgUrl(fileName);
					expertService.update(expert);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("update successe");
					return resultVo;
					
				}
		       
				// 删除数据库中图片信息
//				url = url.substring(url.indexOf(",") + 1);
//				String fileName=base64Util.GenerateImage(url, filePath);
//				expert.setImgUrl(fileName);
//				expertService.update(expert);
//				resultVo.setErrCode(0);
//				resultVo.setErrMsg("update successe");
//				return resultVo;
			}else {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("update fail");
				return resultVo;
			}
	}
	
	/**
	 * 更新对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	@RequestMapping(value = "/expert/appUpdate", method = RequestMethod.POST)
	public ResultVo appUpdate(@RequestBody Expert expert) {
		
		ResultVo resultVo = new ResultVo();
		
		if(expert!=null){
			expertService.update(expert);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update success");
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("expert is null");
		return resultVo;
		
		
	}

	/**
	 * 删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/expert/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

		    try {
				expertService.delete(id);
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

	@RequestMapping(value = "/expertAllName", method = RequestMethod.GET)
	public List<Expert> getAllName() {
		 
			List<Expert> experts=expertService.getAllName();
			
		    return experts;
	}


}
