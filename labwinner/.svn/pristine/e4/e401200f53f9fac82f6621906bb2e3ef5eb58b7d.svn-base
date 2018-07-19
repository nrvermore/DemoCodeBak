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
import com.labwinner.domain.Inventory;
import com.labwinner.domain.QrInfo;
import com.labwinner.service.QrInfoService;

/**
 * @Description 库存Controller
 * @author xux
 * @version V1.0
 * @date 2017年6月6日 上午10:36:15
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class QrController {

	private static Logger logger = LoggerFactory
			.getLogger(QrController.class);

	@Autowired
	private QrInfoService qrInfoService;
	
	@RequestMapping(value = "/qrInfo/{id}", method = RequestMethod.GET)
	public QrInfo getById(@PathVariable("id") Integer id) {
		QrInfo qrList = qrInfoService.getById(id);
		if (qrList == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return qrList;
	}
	
	@RequestMapping(value = "/qrInfo/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		
		qrInfoService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
		return resultVo;
	}
	
	
	@RequestMapping(value = "/qrInfo", method = RequestMethod.POST)
	public ResultVo save(@RequestBody QrInfo qrInfo) {
		ResultVo resultVo = new ResultVo();
		
		qrInfoService.save(qrInfo);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save success");
		return resultVo;
	}

}
