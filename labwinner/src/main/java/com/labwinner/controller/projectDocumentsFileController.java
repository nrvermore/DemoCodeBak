package com.labwinner.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.DevicePicture;
import com.labwinner.domain.Device;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectDocuments;
import com.labwinner.service.DevicePictureService;
import com.labwinner.service.ProjectDocumentsService;

@RestController
public class projectDocumentsFileController {

	private static Logger logger = LoggerFactory
			.getLogger(projectDocumentsFileController.class);
	@Autowired
	private ProjectDocumentsService projectDocumentsService;
	@Value("${projectDocuments.upload-path}")
	String filePath;
	
		@RequestMapping(value = "/uploadProjectDocumentsImage", method = RequestMethod.POST)
		public ResultVo upload(
				@RequestParam("files") MultipartFile[] files,
				@RequestParam("id")	Integer id) {
			if(files.length==0 ||files.length<0){
				return null;
			}
			if (null == id || 0 == id){
				return null;
			}
			
			//删除文件夹中图片文件
			List<String> filePathList = projectDocumentsService.getByProId(id);
			if(filePathList.size()<=0){
				String msg = "the filelist is null";
				logger.error(msg);
			}
			for(String fileName :filePathList){
				new File(filePath + fileName).delete();
			}
			
			//删除数据库中文件信息
			projectDocumentsService.delete(id);
			
		     for(MultipartFile file :files){
			     if (file.isEmpty()) {
			        String message = "file is null";
			        logger.error(message);
			     }
			     // 获取文件名
			     String fileName = file.getOriginalFilename();
			     logger.info("上传的文件名为：" + fileName);
			     // 获取文件的后缀名
			     String suffixName = fileName.substring(fileName.lastIndexOf("."));
			     logger.info("上传的后缀名为：" + suffixName);
			     // 文件上传路径
			   //  String filePath = "d:/roncoo/ttt/";
			     // 解决中文问题，liunx下中文路径，图片显示问题
			     fileName = UUID.randomUUID() + suffixName;
			     File dest = new File(filePath + fileName);
			     // 检测是否存在目录
			     if (!dest.getParentFile().exists()) {
			         dest.getParentFile().mkdirs();
			     }
			     try {
			
			         file.transferTo(dest);
			         //保存名字到数据库
			         ProjectDocuments  projectDocuments= new ProjectDocuments();
			         ProjectBasicInfo projectBasicInfo= new ProjectBasicInfo();
			         projectBasicInfo.setProId(id);
			         projectDocuments.setProjectBasicInfo(projectBasicInfo);
			         projectDocuments.setDocumentName(fileName);  
			         projectDocumentsService.save(projectDocuments);
				     
			     } catch (IllegalStateException e) {
			         e.printStackTrace();
			         
			     } catch (IOException e) {
			         e.printStackTrace();
			     }
		     }
		     ResultVo resultVo = new ResultVo();
	         resultVo.setErrCode(0);
	         resultVo.setErrMsg("success");
	         return resultVo;
		
		}

}
