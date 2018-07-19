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
import com.labwinner.domain.MeasurementType;
import com.labwinner.service.MeasurementService;
import com.labwinner.service.MeasurementTypeService;

@RestController
public class MeasurementTypeController {
	
	private static Logger logger = LoggerFactory
			.getLogger(MeasurementTypeController.class);
	
	@Autowired
	private MeasurementTypeService measurementTypeService;
	
	@Autowired
	private MeasurementService measurementService;
	
	@RequestMapping(value = "/measurementType/list", method = RequestMethod.GET)
	public ResultVo getAll() {

		ResultVo resultVo = new ResultVo();
		List<MeasurementType> list = measurementTypeService.getAll();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find successe");
		resultVo.setResultData(list);
		return resultVo;
	}
	
	@RequestMapping(value = "/measurementType/getList", method = RequestMethod.GET)
	public ResultVo getList() {

		ResultVo resultVo = new ResultVo();
		List<MeasurementType> list = measurementTypeService.getList();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find successe");
		resultVo.setResultData(list);
		return resultVo;
	}
	
	@RequestMapping(value = "/measurementType/delete/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		Integer count  = measurementService.getByType(id);
		if(count==0){
			measurementTypeService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete successe");
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("此类型被使用，无法删除");
		return resultVo;
	}
	
	@RequestMapping(value = "/measurementType", method = RequestMethod.POST)
	public ResultVo save(@RequestBody MeasurementType measurementType) {

		ResultVo resultVo = new ResultVo();
		
			if(measurementType.getMeasureType()==null ||measurementType.getMeasureType()== 0){
				Integer count = measurementTypeService.getByName(measurementType.getTypeName());
				if(count==0 || count ==null){
					measurementType.setTypeSource(1);
					measurementTypeService.save(measurementType);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("save successe");
					return resultVo;	
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("此度量类型已存在");
				return resultVo;
			}else{
				Integer count = measurementTypeService.getCount(measurementType.getTypeName(),measurementType.getMeasureType());
				if(count==0 || count ==null){
					measurementType.setTypeSource(1);
					measurementTypeService.update(measurementType);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("update successe");
					return resultVo;	
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("此度量类型已存在");
				return resultVo;
			}
			
		
	}

}
