package com.labwinner.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.MediaResource;
import com.labwinner.domain.SysUser;
import com.labwinner.service.MediaResourceService;
import com.labwinner.service.SysUserService;
import com.labwinner.vo.MediaResourceVo;

@RestController
public class MediaResourceController {
	
	private static Logger logger = LoggerFactory
			.getLogger(MediaResourceController.class);

	@Autowired
	private MediaResourceService mediaResourceService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Value("${web.url_path_appImage}")
	private String urlPath;
	
	@Value("${web.upload_path_appImage}")
	String filePath;
	
	private LoginController login = new LoginController();
	
	
	
	/**
	 * @Description 获取所有对象列表
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月20日 上午11:49:52
	 */
	
	@RequestMapping(value = "/mediaResource/list", method = RequestMethod.GET)
	public List<MediaResource> getAll() {
		List<MediaResource> list = mediaResourceService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}
	
	/**
	 * @Description 获取指定页对象列表
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月20日 上午11:49:52
	 */
	
	@RequestMapping(value = "/mediaResource/list/{page}/{pageSize}", method = RequestMethod.GET)
	public ResultVo getList(
			@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize) {
		ResultVo resultVo = new ResultVo();
		Integer pageCount = page*pageSize;
		List<MediaResource> list = mediaResourceService.getPageList(pageCount);
		if(list!=null && list.size()>0){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(list);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("find is null");
		return resultVo;
	}
	
	/**
	 * @Description 获取指定页对象列表
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月20日 上午11:49:52
	 */
	
	@RequestMapping(value = "/mediaResource/getPages/{page}/{pageSize}", method = RequestMethod.GET)
	public ResultVo getPages(
			@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize) {
		
		ResultVo resultVo = new ResultVo();
		
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		
		List<MediaResource> mediaResources = mediaResourceService.getAllUsers();
		
		if(mediaResources!=null && mediaResources.size()>0){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(mediaResources));
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("find is null");
		return resultVo;
	}

	/**
	 * @Description 根据主键获取对象
	 * @author wangll
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年7月20日 上午11:50:28
	 */
	@RequestMapping(value = "/mediaResource/getById/{id}", method = RequestMethod.GET)
	public MediaResource getById(@PathVariable("id") Integer id) throws IOException {
		MediaResource mediaResource = mediaResourceService.getById(id);
		if (mediaResource == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return mediaResource;
	}
	
	@RequestMapping(value = "/mediaResource/{name}", method = RequestMethod.GET)
	public List<MediaResource> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<MediaResource> mediaResources = mediaResourceService.getByName(name);
		if (mediaResources == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return mediaResources;
	}
	

	/**
	 * @Description 保存/更新对象方法
	 * @author wangll
	 * @version V1.0
	 * @date 2017年7月20日 上午11:51:21
	 */
	
	@RequestMapping(value = "/mediaResource/add", method = RequestMethod.POST)
	public void saveOrUpdate(@RequestBody MediaResource mediaResource) {
		try {
			//TODO 判断更新，增加
			Integer id = mediaResource.getMediaResourceId();
			mediaResource.setImageUrl(urlPath+"web_media.png");
			MediaResource mediaResource2=mediaResourceService.getById(id);
			if (mediaResource2 != null) {
				mediaResource.setSource(1);
				mediaResourceService.update(mediaResource);
			} else {
				mediaResource.setSource(1);
				mediaResourceService.save(mediaResource);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/mediaResource/addForApp", method = RequestMethod.POST)
	public ResultVo saveOrUpdateForApp(@RequestBody MediaResourceVo mediaResourceVo) {
		ResultVo res=new ResultVo();
		try {
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			MediaResource mediaResource=new MediaResource();
			mediaResource.setContent(mediaResourceVo.getContent());
			mediaResource.setCreater(sysUser.getRealname());
			mediaResource.setCreateDate(new Date());
			mediaResource.setSourceImage(mediaResourceVo.getSourceImage());
			mediaResource.setSourceTitle(mediaResourceVo.getSourceTitle());
			mediaResource.setTitle(mediaResourceVo.getTitle());
			mediaResource.setUploadTime(new Date());
			mediaResource.setSysUser(sysUser);
			mediaResource.setUrl(mediaResourceVo.getUrl());
			mediaResource.setUrlType(mediaResourceVo.getUrlType());
//			mediaResource.setImageUrl(mediaResourceVo.getImageUrl());
			mediaResource.setImageUrl(urlPath+"app_media.png");
			mediaResource.setSource(1);
			mediaResourceService.save(mediaResource);
			res.setErrCode(0);
			res.setErrMsg("Save success!");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("Save failed:"+e.getMessage());
			e.printStackTrace();
		}
		return res;
	}

	
	@RequestMapping(value = "/mediaResource/delete/{id}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo=new ResultVo();
		mediaResourceService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}

	

}
