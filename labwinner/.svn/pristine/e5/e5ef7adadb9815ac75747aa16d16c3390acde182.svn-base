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
import com.labwinner.domain.ApprovelRefuse;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.Magazine;
import com.labwinner.service.ApprovelRefuseService;
import com.labwinner.service.MagazineService;

@RestController
public class ApprovelRefuseController {

	private static Logger logger = LoggerFactory
			.getLogger(ApprovelRefuseController.class);

	@Autowired
	private ApprovelRefuseService approvelRefuseService;

	/**
	 * @Description 获取所有对象列表
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月30日
	 */

	@RequestMapping(value = "/ApprovelRefuse/list", method = RequestMethod.GET)
	public List<ApprovelRefuse> getAll() {
		List<ApprovelRefuse> list = approvelRefuseService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}
	
	@RequestMapping(value = "/ApprovelRefuseAllName", method = RequestMethod.GET)
	public List<ApprovelRefuse> getAllName() {
		List<ApprovelRefuse> list = approvelRefuseService.getAllName();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}
	@RequestMapping(value = "/ApprovelRefuse/getFlagAll", method = RequestMethod.GET)
	public List<ApprovelRefuse> getFlagAll() {
		List<ApprovelRefuse> list = approvelRefuseService.getFlagAll();
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
	@RequestMapping(value = "/ApprovelRefusePageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<ApprovelRefuse> approvelRefuses = approvelRefuseService.getAllPageable(keyword);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(approvelRefuses));
			return resultVo;
		} else {
			List<ApprovelRefuse> approvelRefuses = approvelRefuseService.getFlagAll();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(approvelRefuses));
			return resultVo;
		}

	}

	/**
	 * @Description 根据主键获取对象
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月30日
	 */
	@RequestMapping(value = "/ApprovelRefuse/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		try {
			ApprovelRefuse approvelRefuse = approvelRefuseService.getById(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(approvelRefuse);
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
	 * @author wangll
	 * @version V1.0
	 * @date 2017年6月30日
	 */

	@RequestMapping(value = "/ApprovelRefuse/add", method = RequestMethod.POST)
	public ResultVo save(@RequestBody ApprovelRefuse approvelRefuse) {
		ResultVo resultVo = new ResultVo();
		try {
			approvelRefuse.setFlag(0);
			approvelRefuseService.save(approvelRefuse);
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
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	@RequestMapping(value = "/ApprovelRefuse", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody ApprovelRefuse approvelRefuse) {
		ResultVo resultVo = new ResultVo();
		try {
			approvelRefuseService.update(approvelRefuse);
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

	@RequestMapping(value = "/ApprovelRefuse/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		ApprovelRefuse approvelRefuse = approvelRefuseService.getById(id);

		
		    if(approvelRefuse.getApprovels()!=null && approvelRefuse.getApprovels().size()>0){
		    	resultVo.setErrCode(1);
				resultVo.setErrMsg("此拒绝理由被使用，不能删除！！！");
				return resultVo;
		    }else {
		    	approvelRefuseService.delete(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("删除成功！！！");
				return resultVo;
			
			}
				
	}
}
