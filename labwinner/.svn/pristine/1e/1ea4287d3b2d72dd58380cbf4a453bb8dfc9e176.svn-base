package com.labwinner.controller;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.DesignTechnologyProcess;
import com.labwinner.domain.DesignTechnologyDosage;
import com.labwinner.service.*;

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
public class DesignTechnologyProcessController {
	private static Logger logger = LoggerFactory
			.getLogger(DesignTechnologyProcessController.class);

	@Autowired
	private DesignTechnologyProcessService designTechnologyProcessService;
	
	@Autowired
	private DesignTechnologyDosageService designTechnologyDosageService;

	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/designTechnologyProcess", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		List<DesignTechnologyProcess> list = designTechnologyProcessService.getAll();
		if(list!=null && list.size()>0){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(list);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("list is null");
		return resultVo;
	}

	/**
	 * @Description 根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/designTechnologyProcessPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize,
			@PathVariable String keyword) {
		
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<DesignTechnologyProcess> designTechnologyProcesses = designTechnologyProcessService.getAllPageable(keyword);
			if(designTechnologyProcesses!=null && designTechnologyProcesses.size()>0){

			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(designTechnologyProcesses));
			return resultVo;
		}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find fail");
			return resultVo;
			
	}
		return resultVo;
		
	}
	
	/**
	 * 根据主键获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/designTechnologyProcess/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

		DesignTechnologyProcess designTechnologyProcess = designTechnologyProcessService.getById(id);
		if(designTechnologyProcess!=null){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(designTechnologyProcess);
			return resultVo;
		}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find fail");
			resultVo.setResultData(designTechnologyProcess);
			return resultVo;
		}
	/**
	 * 保存对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/designTechnologyProcess", method = RequestMethod.POST)
	public ResultVo save(@RequestBody DesignTechnologyProcess designTechnologyProcess) {
		
		ResultVo resultVo = new ResultVo();
		
		try {
			designTechnologyProcessService.save(designTechnologyProcess);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("sava successe");
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("sava fail");
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
	@RequestMapping(value = "/designTechnologyProcess", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody DesignTechnologyProcess designTechnologyProcess) {
		
		ResultVo resultVo = new ResultVo();
			Integer id=designTechnologyProcess.getProcessId();
		try {
			designTechnologyProcessService.update(designTechnologyProcess);
			DesignTechnologyProcess designTechnologyProcess2=designTechnologyProcessService.getById(id);
			
			if(designTechnologyProcess2!=null){
				List<DesignTechnologyDosage> designTechnologyDosages2=designTechnologyProcess2.getDesignTechnologyDosages();
				if(designTechnologyDosages2!=null){
					for (DesignTechnologyDosage designTechnologyDosage2 : designTechnologyDosages2) {
						designTechnologyDosageService.delete(id);
					}	          
				}
			}
			
			List<DesignTechnologyDosage> designTechnologyDosages=new ArrayList<DesignTechnologyDosage>(designTechnologyProcess.getDesignTechnologyDosages());
			if(designTechnologyDosages!=null){
				for (DesignTechnologyDosage designTechnologyDosage : designTechnologyDosages) {
					designTechnologyDosage.setDesignTechnologyProcess(designTechnologyProcess);
					designTechnologyDosageService.save(designTechnologyDosage);
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe");
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}

	}

	/**
	 * 删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/designTechnologyProcess/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

			try {
				designTechnologyProcessService.delete(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("删除成功！！！");
				return resultVo;
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("删除失败！！！");
				return resultVo;
			}
		}
	}
