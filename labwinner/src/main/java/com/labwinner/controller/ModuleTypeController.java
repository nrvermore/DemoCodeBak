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
import com.labwinner.domain.ModuleType;
import com.labwinner.domain.Questions;
import com.labwinner.service.*;

/**
 * 模块类型Controller
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
public class ModuleTypeController {
	private static Logger logger = LoggerFactory
			.getLogger(ModuleTypeController.class);

	@Autowired
	private ModuleTypeService moduleTypeService;
	
	@Autowired
	private QuestionsService questionsService;

	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/ModuleType", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		List<ModuleType> list = moduleTypeService.getAll();
		if(list!=null && list.size()>0){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(list);
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("find is null");
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
	@RequestMapping(value = "/ModuleType/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

		try {
			ModuleType moduleType = moduleTypeService.getById(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(moduleType);
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find is null");
			return resultVo;
			
		}
	}
	/**
	 * 获取所有热门标签对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:45
	 */
	@RequestMapping(value = "/ModuleType/getAllDescQuestions", method = RequestMethod.GET)
	public ResultVo getByIdAllDesc() {
		
		ResultVo resultVo = new ResultVo();
		try {
			List<Questions> list = questionsService.getAllDescQuestions();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(list);
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find is null");
			return resultVo;
			
		}
	}

	/**
	 * 保存对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/ModuleType", method = RequestMethod.POST)
	public ResultVo save(@RequestBody ModuleType moduleType) {
		try {
			ResultVo resultVo = new ResultVo();
			
			moduleTypeService.save(moduleType);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("sava successe");
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	@RequestMapping(value = "/ModuleType", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody ModuleType moduleType) {
		try {
			ResultVo resultVo = new ResultVo();
			
			moduleTypeService.update(moduleType);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("sava successe");
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/ModuleType/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		moduleTypeService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("删除成功！！！");
			return resultVo;
		}
	/**
	 * 获取所有常见问题对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:45
	 */
	@RequestMapping(value = "/ModuleType/getAllByType", method = RequestMethod.GET)
	public List<ModuleType> getAllByType() {
		List<ModuleType> list = moduleTypeService.getAllByType(7);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
	}
	/**
	 * 获取所有试验管理对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:45
	 */
	@RequestMapping(value = "/ModuleType/getAllReactionByType", method = RequestMethod.GET)
	public List<ModuleType> getAllReactionByType() {
		List<ModuleType> list = moduleTypeService.getAllByType(1);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
	}
	/**
	 * 获取所有知识管理对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:45
	 */
	@RequestMapping(value = "/ModuleType/getAllKnowledgeByType", method = RequestMethod.GET)
	public List<ModuleType> getAllKnowledgeByType() {
		List<ModuleType> list = moduleTypeService.getAllByType(2);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
	}
	/**
	 * 获取所有项目管理对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:45
	 */
	@RequestMapping(value = "/ModuleType/getAllProjectByType", method = RequestMethod.GET)
	public List<ModuleType> getAllProjectByType() {
		List<ModuleType> list = moduleTypeService.getAllByType(3);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
	}
	/**
	 * 获取所有库存管理对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:45
	 */
	@RequestMapping(value = "/ModuleType/getAllInventoryByType", method = RequestMethod.GET)
	public List<ModuleType> getAllInventoryByType() {
		List<ModuleType> list = moduleTypeService.getAllByType(4);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
	}
	/**
	 * 获取所有采购管理对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:45
	 */
	@RequestMapping(value = "/ModuleType/getAllMaterialByType", method = RequestMethod.GET)
	public List<ModuleType> getAllMaterialByType() {
		List<ModuleType> list = moduleTypeService.getAllByType(5);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
	}
	/**
	 * 获取所有设备管理对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:45
	 */
	@RequestMapping(value = "/ModuleType/getAllDeviceByType", method = RequestMethod.GET)
	public List<ModuleType> getAllDeviceByType() {
		List<ModuleType> list = moduleTypeService.getAllByType(6);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
	}
    /**
	 * 获取所有出来热门标签对象列表
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 下午5:41:45
	 */
	@RequestMapping(value = "/ModuleType/getByType", method = RequestMethod.GET)
	public List<ModuleType> getByType() {
		List<ModuleType> list = moduleTypeService.getByType();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
	}

}
