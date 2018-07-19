package com.labwinner.controller;

import java.util.Date;
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
import com.labwinner.domain.Inventories;
import com.labwinner.domain.Inventory;
import com.labwinner.domain.InventoryGroups;
import com.labwinner.domain.InventoryModify;
import com.labwinner.domain.Measurement;
import com.labwinner.domain.ModifyCode;
import com.labwinner.domain.ModifyType;
import com.labwinner.domain.SysUser;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.InventoryGroupsService;
import com.labwinner.service.InventoryModifyService;
import com.labwinner.service.SysUserService;
import com.labwinner.vo.InventoryModifyVo;

@RestController
public class InventoryModifyController {

	private static Logger logger = LoggerFactory
			.getLogger(InventoryModifyController.class);

	@Autowired
	private InventoryModifyService inventoryModifyService;
	@Autowired
	private InventoriesService inventoriesService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private InventoryGroupsService inventoryGroupsService;

	/**
	 * @Description 获取所有对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventoryModify/list", method = RequestMethod.GET)
	public List<InventoryModify> getAll() {
		List<InventoryModify> list = inventoryModifyService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}

	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */

	@RequestMapping(value = "/inventoryModify/getById/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List inventoryModifies = inventoryModifyService.getById(id);
		if (inventoryModifies == null) {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("无此变更");
			return resultVo;
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("更新成功");
		resultVo.setResultList(inventoryModifies);
		return resultVo;
	}

	/**
	 * @Description 根据库存主键获取实验
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */

	@RequestMapping(value = "/inventoryModify/getReactionProcess/{processId}/{inventoryId}", method = RequestMethod.GET)
	public ResultVo getReactionProcess(@PathVariable("processId") Integer processId,
			@PathVariable("inventoryId") Integer inventoryId) {
		ResultVo resultVo = new ResultVo();
		Integer count = inventoryModifyService.getProcessCount(processId, inventoryId);
		if (count != null) {
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(count);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("inventoryModifies is null");
		return resultVo;

	}

	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */

	@RequestMapping(value = "/inventoryModify/add", method = RequestMethod.POST)
	public ResultVo saveOrUpdate(
			@RequestBody InventoryModifyVo inventoryModifyVo) {

		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService
					.getByUsername(login.getPrincipal()).get(0);
			Integer userId = sysUser.getUserId();

			// TODO 判断更新，增加
			if (inventoryModifyVo != null) {
				InventoryModify inventoryModify = new InventoryModify();
				Integer id = inventoryModifyVo.getInventoryId();
				Inventories inventories = new Inventories();
				Measurement measurement = new Measurement();
				ModifyCode modifyCode = new ModifyCode();
				Inventories inventories2 = inventoriesService.getById(id);
				if (null == inventoryModifyVo.getMeasureUnitId()) {
					
					measurement = inventories2.getMeasurement();
				}

				measurement.setMeasureUnitId(inventoryModifyVo.getMeasureUnitId());
				modifyCode.setModifyCodeId(inventoryModifyVo.getModifyCodeId());
				inventories.setActAvaWei(inventoryModifyVo.getModifyAfter());
				inventories.setInventoryId(inventoryModifyVo.getInventoryId());
				inventories.setMeasurement(measurement);
				inventories.setModifier(userId + "");
				inventories.setModifyDate(new Date());
				inventoryModify.setChangeDate(new Date());
				ModifyType modifyType = new ModifyType();
				modifyType.setModifyTypeId(1);

				inventoryModify.setModifyType(modifyType);
				inventoryModify.setInventories(inventories);
				inventoryModify.setMeasurement(measurement);
				inventoryModify.setModifyCode(modifyCode);
				inventoryModify.setModifyAfter(inventoryModifyVo.getModifyAfter());
				inventoryModify.setChangeReason(inventoryModifyVo.getChangeReason());
				inventoryModify.setModifier(sysUser);
				inventoryModify.setModifyDate(new Date());
				inventoryModifyService.save(inventoryModify);
				inventoriesService.updateInventory(inventories);
				
				//变更库存组重量
				InventoryGroups inventoryGroups = inventoriesService.getById(id).getInventoryGroups();
				inventoryGroups.setTotalWei(inventoryGroups.getTotalWei()+inventoryModifyVo.getModifyAfter()-inventories2.getActAvaWei());
				inventoryGroupsService.update(inventoryGroups);

				ResultVo resultVo = new ResultVo();
				resultVo.setErrCode(0);
				resultVo.setErrMsg("更新成功");
				return resultVo;
			}
		} catch (Exception e) {
			String message = "更新失败";
			logger.warn(message);
			e.printStackTrace();
		}
		ResultVo resultVo = new ResultVo();
		resultVo.setErrCode(1);
		resultVo.setErrMsg("更新失败");
		return resultVo;

	}

	/**
	 * @Description 保存/更新对象方法
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */

	@RequestMapping(value = "/inventoryModify/saveByReaction", method = RequestMethod.POST)
	public ResultVo updateByReaction(
			@RequestBody InventoryModify inventoryModify) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		// TODO 判断更新，增加
		if (inventoryModify != null) {

			// ModifyCode modifyCode = new ModifyCode();
			Inventories inventories = inventoryModify.getInventories();
			inventories.setActAvaWei(inventoryModify.getModifyAfter());
			inventories.setMeasurement(inventoryModify.getMeasurement());

			ModifyType modifyType = new ModifyType();
			modifyType.setModifyTypeId(1);

			inventoryModify.setModifyType(modifyType);
			// inventoryModify.setModifyCode(modifyCode);
			inventoryModify.setChangeDate(new Date());
			inventoryModify.setModifier(sysUser);
			inventoryModify.setModifyDate(new Date());
			inventoryModifyService.save(inventoryModify);
			inventoriesService.updateInventory(inventories);

			resultVo.setErrCode(0);
			resultVo.setErrMsg("更新成功");
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("更新失败");
		return resultVo;

	}

	/**
	 * @Description 删除对象方法
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:52:47
	 */
	@RequestMapping(value = "/inventoryModify/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		inventoryModifyService.delete(id);
	}

}
