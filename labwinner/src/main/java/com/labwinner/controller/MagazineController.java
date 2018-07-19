package com.labwinner.controller;

import java.util.List;
import java.util.Map;

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
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.Magazine;
import com.labwinner.service.MagazineService;


@RestController
public class MagazineController {
	
	private static Logger logger = LoggerFactory
			.getLogger(MagazineController.class);

	@Autowired
	private MagazineService magazineService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月30日
	 */
	
	@RequestMapping(value = "/magazine/list", method = RequestMethod.GET)
	public List<Magazine> getAll() {
		List<Magazine> list = magazineService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}

	
	@RequestMapping(value = "/magazineAllName", method = RequestMethod.GET)
	public List<Magazine> getAllName() {
		List<Magazine> list = magazineService.getAllName();
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
	@RequestMapping(value = "/magazinePageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<Magazine> magazines = magazineService.getAllPageable(keyword);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(magazines));
			return resultVo;
		} else {
			List<Magazine> magazines = magazineService.getAll();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(magazines));
			return resultVo;
		}
		
	}
	
	
	/**
	 * @Description 根据关键字分页获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/magazine/getByName/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getByName(@PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<Magazine> magazines = magazineService.getAllPageable(keyword);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(magazines);
			return resultVo;
		} else {
			List<Magazine> magazines = magazineService.getAll();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(magazines);
			return resultVo;
		}
		
	}
	
	/**
	 * @Description 根据主键获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月30日 
	 */
	@RequestMapping(value = "/magazine/getById/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		 ResultVo resultVo = new ResultVo();
			try {
				Magazine magazine = magazineService.getById(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(magazine);
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
	 * @Description 根据名字和issn号获取杂志
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月30日
	 */
//	@RequestMapping(value = "/magazine/getByName", method = RequestMethod.POST)
//	public Magazine getByName(@RequestBody Map<String, String> name) {
//		//主表实体类
//		Magazine magazine =magazineService.getByName(name);
//		if (magazine == null) {
//			String message = "对象不存在(chName:" + name + ")";
//			logger.warn(message);
//		}
//		return magazine;
//	}
	

	/**
	 * @Description 保存对象方法
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月30日
	 */
	
	@RequestMapping(value = "/magazine/add", method = RequestMethod.POST)
	public ResultVo save(@RequestBody Magazine magazine) {
		ResultVo resultVo = new ResultVo();
		try {
			magazineService.save(magazine);
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
	 * 更新对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	 @RequestMapping(value = "/magazine", method = RequestMethod.PUT)
	    public ResultVo update(@RequestBody Magazine magazine) {
		 ResultVo resultVo = new ResultVo();
			try {
				magazineService.update(magazine);
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
	
	
	
	@RequestMapping(value = "/magazine/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		 ResultVo resultVo = new ResultVo();
		 Magazine magazine = magazineService.getById(id);
		      
		 if(magazine.getJournalArticles()!=null&&magazine.getJournalArticles().size()>0){
				resultVo.setErrCode(1);
				resultVo.setErrMsg("此杂志被使用，不能删除！！！"); 
		 }else {
			    magazineService.delete(id);
			    resultVo.setErrCode(0);
				resultVo.setErrMsg("删除成功！！！"); 
		}
		return resultVo;

	}
}
