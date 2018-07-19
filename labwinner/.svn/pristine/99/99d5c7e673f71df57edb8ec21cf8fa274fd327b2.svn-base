package com.labwinner.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Inventory;
import com.labwinner.domain.InventoryGroup;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ChemicalImageService;
import com.labwinner.service.ExecuteChemicalService;
import com.labwinner.service.InventoryGroupService;
import com.labwinner.service.InventoryModifyService;
import com.labwinner.service.InventoryUserService;
import com.labwinner.service.QrInfoService;
import com.labwinner.service.ReactionDesignChemicalService;
import com.labwinner.service.SysUserService;

@RestController
public class InventoryGropControlller {

	private static Logger logger = LoggerFactory
			.getLogger(InventoryGropControlller.class);

	@Autowired
	private InventoryGroupService inventoryGroupService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ReactionDesignChemicalService reactionDesignChemicalService;
	@Autowired
	private ExecuteChemicalService executeChemicalService;
	@Autowired
	private InventoryUserService inventoryUserService;
	@Autowired
	private ChemicalImageService chemicalImageService;
	@Autowired
	private QrInfoService qrInfoService;
	@Autowired
	private InventoryModifyService inventoryModifyService;

	/**
	 * @Description 条件模糊查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventoryGroup/{page}/{pageSize}/{name}/{keyword}", method = RequestMethod.GET)
	public ResultVo getByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("name") Integer name,
			@PathVariable("keyword") String keyword) {
		ResultVo resultVo = new ResultVo();

		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				List<InventoryGroup> list = inventoryGroupService.getByKeyword(
						name, keyword, userId);
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			} else {
				List<InventoryGroup> list = inventoryGroupService
						.getListByKeyword(keyword, name);
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				List list = inventoryGroupService.getUserInventorys(userId);
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			} else {
				List<InventoryGroup> list = inventoryGroupService
						.getAllInventorys();
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;
			}
		}
	}

	

}
