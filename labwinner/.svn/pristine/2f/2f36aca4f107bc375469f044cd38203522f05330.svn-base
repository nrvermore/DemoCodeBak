package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.DeviceState;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.service.SysSigningAgencyService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 签约机构Controller
 * @author liuhq
 * @version V1.0
 * @date 2017年6月7日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class SysSigningAgencyController {

	@Autowired
	private SysSigningAgencyService sysSigningAgencyService;

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysSigningAgency", method = RequestMethod.GET)
	public List<SysSigningAgency> getAll() {
		return sysSigningAgencyService.getAll();
	}

	/**
	 * @Description 根据查询条件获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysSigningAgency/{page}/{pageSize}/{filter}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String filter) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (filter != null && filter != "" && !"undefined".equals(filter)) {
			List<SysSigningAgency> signingAgencies = sysSigningAgencyService.getAllPageable(filter);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(signingAgencies));
			return resultVo;
		} else {
			List<SysSigningAgency> signingAgencies = sysSigningAgencyService.getAll();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(signingAgencies));
			return resultVo;
		}
	}
	
	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysSigningAgency/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Long id) {
		ResultVo resultVo = new ResultVo();
		try {
			SysSigningAgency sysSigningAgency = sysSigningAgencyService.getById(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(sysSigningAgency);
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
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysSigningAgency", method = RequestMethod.POST)
	public ResultVo save(@RequestBody SysSigningAgency sysSigningAgency) {
		 ResultVo resultVo = new ResultVo();
			try {
				sysSigningAgencyService.save(sysSigningAgency);
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
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysSigningAgency", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody SysSigningAgency sysSigningAgency) {
		 ResultVo resultVo = new ResultVo();
			try {
				sysSigningAgencyService.update(sysSigningAgency);
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
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysSigningAgency/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Long id) {
		 ResultVo resultVo = new ResultVo();

		        try {
					sysSigningAgencyService.delete(id);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("删除成功！！！");
					return resultVo;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultVo.setErrCode(1);
					resultVo.setErrMsg("删除失败！！！");
					return resultVo;
				}
	}

}
