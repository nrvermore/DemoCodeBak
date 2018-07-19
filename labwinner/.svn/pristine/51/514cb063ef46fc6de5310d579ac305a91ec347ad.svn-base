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
import com.labwinner.domain.Device;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.MaterialType;
import com.labwinner.domain.ModifyType;
import com.labwinner.service.MaterialTypeService;

@RestController
public class MaterialTypeController {
	
	private static Logger logger = LoggerFactory
			.getLogger(MaterialTypeController.class);
	
	@Autowired 
	private MaterialTypeService materialTypeService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/materialType/list", method = RequestMethod.GET)
	public List<MaterialType> getAll() {
		List<MaterialType> list = materialTypeService.getAll();
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
	@RequestMapping(value = "/materialTypePageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<MaterialType> materialTypes = materialTypeService.getAllPageable(keyword);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(materialTypes));
			return resultVo;
		} else {
			List<MaterialType> materialTypes = materialTypeService.getAll();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(materialTypes));
			return resultVo;
		}
	}
	
	
	/**
	 * 根据主键获取对象
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:21:45
	 */
	 @RequestMapping(value = "/materialType/getTree/{id}", method = RequestMethod.GET)
	    public ResultVo getTreeById(@PathVariable("id") Integer materialCategoryId) {
		 ResultVo resultVo = new ResultVo();
			try {
				MaterialType materialType = materialTypeService.getTree(materialCategoryId);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(materialType);
				return resultVo;
				// }
			} catch (Exception e) {
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find fail");
				return resultVo;
			}
			
	    }
	
	 @RequestMapping(value = "/materialType/getTree", method = RequestMethod.GET)
		public ResultVo getTree() {
			ResultVo resultVo = new ResultVo();
			try {
				MaterialType materialType = materialTypeService.getTree(1);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(materialType);
				return resultVo;
				// }
			} catch (Exception e) {
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find fail");
				return resultVo;
			}
			
	 }
	 
	 
	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/materialType/add", method = RequestMethod.POST)
	public ResultVo save(@RequestBody MaterialType materialType) {
		ResultVo resultVo = new ResultVo();
		try {
			materialTypeService.save(materialType);
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
	/**
	 * @Description 删除对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/materialType/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();

		MaterialType materialType= materialTypeService.getById(id);

		if (materialType.getInventories().size() > 0){
			resultVo.setErrCode(1);
			resultVo.setErrMsg("该原料类型存在库存中，不能被删除！！！");
		}else {
			
			materialTypeService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("删除成功！！！");
		}
		return resultVo;
	}
	/**
	 * @Description 更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/materialType", method = RequestMethod.PUT)
	public ResultVo Update(@RequestBody MaterialType materialType) {
		ResultVo resultVo = new ResultVo();
		try {
			materialTypeService.update(materialType);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe");
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}
	}
	/**
	 * @Description 查询对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/materialType/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();

		try {
			MaterialType materialType = materialTypeService.getById(id);
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(materialType);
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find fail");
			resultVo.setResultData(null);
			return resultVo;
		}
	}

}
