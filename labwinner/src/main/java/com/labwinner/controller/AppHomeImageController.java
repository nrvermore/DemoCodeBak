package com.labwinner.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.AppHomeImage;
import com.labwinner.service.AppHomeImageService;

@RestController
public class AppHomeImageController {

	private static Logger logger = LoggerFactory
			.getLogger(AppHomeImageController.class);

	
	@Autowired
	private AppHomeImageService appHomeImageService;
	
	@Value("${web.url_path_appImage}")
	String appImagePath;
	
	
	/**
	 * @Description 每9s获取图片接口方法
	 * @author xux
	 * @version V1.0
	 * @throws Exception 
	 * @date 2017年5月18日 上午11:51:21
	 */
	@RequestMapping(value = "/appHomeImage/getByNumber", method = RequestMethod.GET)
	public ResultVo getByShowNumber() {
		ResultVo res=new ResultVo();
		Date date = new Date();
		long now = date.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(date);
		Date date2;
		try {
			int showNumber = appHomeImageService.getMaxShowNumber();
			int imageSize = appHomeImageService.getByShowNumber(0).size();
			
			date2 = sdf.parse(s);
			long nowDay = date2.getTime();
//			int a = (int)(now - nowDay)/1000/9%6;
			int a = (int)(now - nowDay)/1000/(3*imageSize)%(showNumber+1);
			List<AppHomeImage> images = appHomeImageService.getByShowNumber(a);
			for (AppHomeImage appHomeImage : images) {
				appHomeImage.setImageName(appImagePath+appHomeImage.getImageName());
			}
			res.setErrCode(0);
			res.setErrMsg("find success");
			res.setResultData(images);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setErrCode(1);
			res.setErrMsg("find failed");
		}
		return res;
	}
		
}
