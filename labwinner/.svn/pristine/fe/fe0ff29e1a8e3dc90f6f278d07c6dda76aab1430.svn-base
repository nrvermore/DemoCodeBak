package com.labwinner.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.Measurement;
import com.labwinner.service.MeasurementService;

@RestController
public class MeasurementController {
	
	private static Logger logger = LoggerFactory
			.getLogger(MeasurementController.class);

	@Autowired
	private MeasurementService measurementService;
	
	@RequestMapping(value = "/measurement/list", method = RequestMethod.GET)
	public ResultVo getAll() {
		List list = measurementService.findAll();
		
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
	
	@RequestMapping(value = "/measurement/getBySource", method = RequestMethod.GET)
	public ResultVo getBySource() {
		
		ResultVo resultVo = new ResultVo();
		List list = measurementService.getBySourceId();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find successe");
		resultVo.setResultData(list);
		return resultVo;
		
	}
	
	@RequestMapping(value = "/measurement/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		List list = measurementService.getById(id);
		
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
	
	
	@RequestMapping(value = "/measurement/getWeight", method = RequestMethod.GET)
	public ResultVo getWeight() {
		List list = measurementService.getById(1);
		
		ResultVo resultVo = new ResultVo();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find successe");
		resultVo.setResultData(list);
		return resultVo;
		
	}


	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/measurement", method = RequestMethod.POST)
	public ResultVo saveOrUpdate(@RequestBody Measurement measurement) {
		ResultVo resultVo = new ResultVo();
		
			if(measurement.getMeasureUnitId()== null || measurement.getMeasureUnitId()==0 ){
				Integer count = measurementService.getByUnit(measurement.getMeasureUnit());
				if(count==0){
					measurement.setMeasureResource(1);
					measurement.setFlag(0);
					measurementService.save(measurement);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("save success");
					return resultVo;
				}else{
					resultVo.setErrCode(2);
					resultVo.setErrMsg("此度量单位已存在");
					return resultVo;
				}
			}else{
				Integer count = measurementService.getCount(measurement.getMeasureUnit(), measurement.getMeasureUnitId());
				if(count==0){
					measurementService.update(measurement);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("save success");
					return resultVo;
				}else{
					resultVo.setErrCode(2);
					resultVo.setErrMsg("此度量单位已存在");
					return resultVo;
				}
			}
			
		
		
		
	}
	/**
	 * @Description 删除对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/measurement/delete/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		measurementService.deleteFlag(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
		return resultVo;
	}
	
	@RequestMapping(value = "/getByMeasureType", method = RequestMethod.GET)
	public ResultVo getByMeasureType() {
		
		    ResultVo resultVo = new ResultVo();
		     
			ArrayList<Object> datas=new ArrayList<Object>();
			try {
				List<Measurement> list1=measurementService.getById(1);
				List<Measurement> list2=measurementService.getById(2);
				if(list1!=null&&list2!=null){
					datas.add(list1); 
					datas.add(list2);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find successe");
					resultVo.setResultData(datas);
					return resultVo;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultVo;
		
	}
	
	@RequestMapping(value = "/getAllMeasures", method = RequestMethod.GET)
	public ResultVo getAllMeasures() {
		
		    ResultVo resultVo = new ResultVo();
		     
			ArrayList<Object> datas=new ArrayList<Object>();
			try {
				List<Integer> measureTypes= measurementService.getMeasureTypes();
				
				if(measureTypes!=null && measureTypes.size()>0){
					
					for (Integer measureType : measureTypes) {
						List<Measurement> measures = measurementService.getById(measureType);
						datas.add(measures);
					}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find successe");
					resultVo.setResultData(datas);
					return resultVo;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultVo;
		
	}
	
	/**
	 * @Description 根据单位类型id获取所属单位集合
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/getMeasurementByMeasureType/{id}", method = RequestMethod.GET)
	public ResultVo getMeasurementByMeasureType(@PathVariable("id") Integer id) {
		
		ResultVo resultVo = new ResultVo();
		
		List<Measurement> measurements=measurementService.getMeasurementByMeasureType(id);
		if(measurements!=null){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(measurements);
			return resultVo;
		}else {
			resultVo.setErrCode(0);
			resultVo.setErrMsg("无此单位类型数据");
			resultVo.setResultData(null);
			return resultVo;
		}
	}
	

}
