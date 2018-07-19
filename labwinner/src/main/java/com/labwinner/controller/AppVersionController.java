package com.labwinner.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.AppVersion;
import com.labwinner.service.AppVersionService;

@RestController
public class AppVersionController {
	
	private static Logger logger = LoggerFactory
			.getLogger(AppVersionController.class);

	@Autowired
	private AppVersionService appVersionService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/appVersion/getLast", method = RequestMethod.GET)
	public ResultVo getLast() {
		ResultVo resultVo = new ResultVo();
		AppVersion appVersion = appVersionService.getByLast();
		if (appVersion != null) {
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(appVersion);
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("find fail");
		return resultVo;
	}

}
