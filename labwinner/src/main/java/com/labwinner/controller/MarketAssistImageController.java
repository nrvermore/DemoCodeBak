package com.labwinner.controller;

import java.util.List;

import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.MarketAssist;
import com.labwinner.domain.MarketAssistImage;
import com.labwinner.service.MarketAssistImageService;
import com.labwinner.util.FileUtil;


@RestController
public class MarketAssistImageController {
	
	private static Logger logger = LoggerFactory
			.getLogger(MarketAssistImageController.class);

	@Autowired
	private MarketAssistImageService marketAssistImageService;
	
	@Value("${marketAssist.url-path}")
	private String urlPath;
	
	@Value("${marketAssist.upload-path}")
	String filePath;

	
	/**
	 * @Description 删除图片
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssistImage/delete/{id}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo =  new ResultVo();
		marketAssistImageService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
		return resultVo;
	}
	
	/**
	 * @Description 新增图片
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssistImage/save", method = RequestMethod.POST)
	public ResultVo save(
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			@RequestParam(value = "id", required = false) Integer id) {
		ResultVo resultVo =  new ResultVo();
		
		MarketAssist marketAssist =new MarketAssist();
		marketAssist.setMarketAssistId(id);
		
		if (null != files && files.length > 0) {
			saveNote(marketAssist, files, filePath);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("file is null");
		return resultVo;
		
	}
	
	/**
	 * @Description 保存图片， 权限用户
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveNote(MarketAssist marketAssist, MultipartFile[] files, String path) {

		// 保存上传图片
		List<String> fileNameList = new FileUtil().imageUpload(files, path);
		MarketAssistImage marketAssistImage = new MarketAssistImage();
		marketAssistImage.setMarketAssist(marketAssist);
		for (String fileName : fileNameList) {
			marketAssistImage.setImageName(fileName);
			marketAssistImageService.save(marketAssistImage);
		}
	}

}
