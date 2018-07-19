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
import com.labwinner.domain.RepresentationMap;
import com.labwinner.service.RepresentationMapService;

@RestController
public class RepresentationMapController {

	private static Logger logger = LoggerFactory
			.getLogger(RepresentationMapController.class);
	
	@Autowired
	private RepresentationMapService representationMapService;
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/representationMap/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		RepresentationMap RepresentationMap = representationMapService.getById(id);
		if (RepresentationMap == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(RepresentationMap);
		return resultVo;
	}
	
	/**
	 * @Description 获取全部对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/representationMap", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		List<RepresentationMap> RepresentationMaps = representationMapService.getAll();
		if (RepresentationMaps == null) {
			String message = "对象不存在(id:" + RepresentationMaps + ")";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(RepresentationMaps);
		return resultVo;
	}
	
	
	/**
	 * @Description 删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/representationMap/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		representationMapService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}
	
	/**
	 * @Description 删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/representationMap/deleteUrl/{url}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("url") String url) {
		ResultVo resultVo = new ResultVo();
		representationMapService.deleteByUrl(url);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}
	
	/**
	 * @Description 新增对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/representationMap", method = RequestMethod.POST)
	public ResultVo save(@RequestBody RepresentationMap representationMap) {
		ResultVo resultVo = new ResultVo();
		representationMapService.save(representationMap);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}
	
	/**
	 * @Description 新增对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/representationMap", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody RepresentationMap representationMap) {
		ResultVo resultVo = new ResultVo();
		representationMapService.update(representationMap);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}
}
