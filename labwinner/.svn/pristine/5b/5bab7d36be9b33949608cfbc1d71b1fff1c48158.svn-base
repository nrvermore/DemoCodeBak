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
import com.labwinner.domain.Measurement;
import com.labwinner.domain.ModifyCode;
import com.labwinner.domain.ModifyType;
import com.labwinner.service.MeasurementService;
import com.labwinner.service.ModifyCodeService;
import com.labwinner.vo.ModifyDataVo;

@RestController
public class ModifyCodeController {

	private static Logger logger = LoggerFactory
			.getLogger(ModifyCodeController.class);

	@Autowired
	private ModifyCodeService modifyCodeService;
	@Autowired
	private MeasurementService measurementService;
	
	@RequestMapping(value = "/modifyCode/list", method = RequestMethod.GET)
	public ResultVo getAll() {
		ArrayList<Object> datas = new ArrayList<Object>();
		List<ModifyCode> list = modifyCodeService.getAll();
		List<Measurement> list2 =measurementService.getById(1);
		
		ModifyDataVo modifyDataVo = new ModifyDataVo();
		modifyDataVo.setCodeList(list);
		modifyDataVo.setMeasurementList(list2);
		datas.add(list);
		datas.add(list2);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		ResultVo resultVo = new ResultVo();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find successe");
		resultVo.setResultData(modifyDataVo);
		return resultVo;
	}
	
	@RequestMapping(value = "/modifyCode", method = RequestMethod.GET)
	public ResultVo getCodes() {
		List<ModifyCode> list = modifyCodeService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		ResultVo resultVo = new ResultVo();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find successe");
		resultVo.setResultData(list);
		return resultVo;
	}
	
	@RequestMapping(value = "/modifyCodeAllName", method = RequestMethod.GET)
	public List<ModifyCode> getAllName() {
		List<ModifyCode> list = modifyCodeService.getAllName();
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
	@RequestMapping(value = "/modifyCodePageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<ModifyCode> modifyCodes = modifyCodeService.getAllPageable(keyword);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(modifyCodes));
			return resultVo;
		} else {
			List<ModifyCode> modifyCodes = modifyCodeService.getFlagAll();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(modifyCodes));
			return resultVo;
		}
	}
	
	
	@RequestMapping(value = "/modifyCode/{id}", method = RequestMethod.GET)
	public ModifyCode getById(@PathVariable("id") Integer id) {
		ModifyCode modifyCode= modifyCodeService.getById(id);
		if (modifyCode == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return modifyCode;
	}
	
	@RequestMapping(value = "/modifyCode/add", method = RequestMethod.POST)
	public ResultVo save(@RequestBody ModifyCode modifyCode) {
		ResultVo resultVo = new ResultVo();
		try {
			modifyCode.setFlag(0);
			modifyCodeService.save(modifyCode);
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
	
	@RequestMapping(value = "/modifyCode", method = RequestMethod.PUT)
	public ResultVo Update(@RequestBody ModifyCode modifyCode) {
		ResultVo resultVo = new ResultVo();
		try {
			modifyCodeService.update(modifyCode);
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
	
	@RequestMapping(value = "/modifyCode/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		
		ModifyCode modifyCode=modifyCodeService.getById(id);
		
		if(modifyCode.getInventoryModifies()!=null&&modifyCode.getInventoryModifies().size()>0){
			modifyCodeService.delete(id);
			resultVo.setErrCode(1);
			resultVo.setErrMsg("此变更原因使用中，不能删除！");
			return resultVo;
		}else {
			modifyCodeService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("删除成功");
			return resultVo;
		}	
	}
}
