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
import com.labwinner.domain.ExecuteSolution;
import com.labwinner.domain.Inventories;
import com.labwinner.domain.InventoryModify;
import com.labwinner.domain.ModifyType;
import com.labwinner.domain.ReactionExecuteParameter;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ExecuteSolutionService;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.InventoryModifyService;
import com.labwinner.service.SysUserService;

@RestController
public class ExecuteSolutionController {

	private static Logger logger = LoggerFactory
			.getLogger(ExecuteSolutionController.class);

	@Autowired
	private ExecuteSolutionService executeSolutionService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private InventoriesService inventoriesService;
	@Autowired
	private InventoryModifyService inventoryModifyService;
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeSolution/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List<ExecuteSolution> executeSolution = executeSolutionService.getById(id);
		if (executeSolution == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(executeSolution);
		return resultVo;
	}
	
	
	/**
	 * @Description 新增对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeSolution/appSaveOrUpdate", method = RequestMethod.POST)
	public ResultVo appSaveOrUpdate(@RequestBody ExecuteSolution executeSolution) {
		ResultVo resultVo = new ResultVo();
		
		if(executeSolution.getExecuteSolutionId()==null){
			executeSolutionService.save(executeSolution);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
		}else{
			executeSolutionService.update(executeSolution);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
		}
		return resultVo;
	}
	
	/**
	 * @Description 新增对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeSolution", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<ExecuteSolution> executeSolutions) {
		ResultVo resultVo = new ResultVo();
		if(executeSolutions.size()>0){
			ReactionProcess reactionProcess = executeSolutions.get(0).getReactionProcess();
			if(reactionProcess !=null){
				Integer processId = reactionProcess.getReactionProcessId();
				//删除步骤id
				executeSolutionService.deleteByProcessId(processId);
			}
			for(ExecuteSolution executeSolution :executeSolutions){
				executeSolutionService.save(executeSolution);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			return resultVo;
		}else{
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save fail");
			return resultVo;
		}
		
	}
	
	
	/**
	 * @Description 新增对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeSolution/appSave", method = RequestMethod.POST)
	public ResultVo appSave(@RequestBody List<ExecuteSolution> executeSolutions) {
		ResultVo resultVo = new ResultVo();
		if(executeSolutions.size()>0){
			for(ExecuteSolution executeSolution :executeSolutions){
				executeSolutionService.save(executeSolution);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			return resultVo;
		}else{
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save fail");
			return resultVo;
		}
		
	}
	
	/**
	 * @Description 更新对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeSolution", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody List<ExecuteSolution> executeSolutions) {
		ResultVo resultVo = new ResultVo();
		if(executeSolutions.size()>0){
			for(ExecuteSolution executeSolution :executeSolutions){
				executeSolutionService.update(executeSolution);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update success");
			return resultVo;
		}else{
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}
		
	}
	
	
	/**
	 * @Description 更新库存对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
//	@RequestMapping(value = "/executeSolution/updateInventory/{id}", method = RequestMethod.GET)
//	public ResultVo updateInventory(@PathVariable("id") Integer id) {
//		ResultVo resultVo = new ResultVo();
//		LoginController login = new LoginController();
//		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
//		Integer userId = sysUser.getUserId();
//		try {
//			List<ExecuteSolution> executeSolutions = executeSolutionService.getById(id);
//			
//			if(executeSolutions!=null && executeSolutions.size()>0){
//				for(ExecuteSolution executeSolution :executeSolutions){
//					
//						Solution solution = inventoriesService.getById(executeSolution.getInventory().getInventoryId());
//						inventory.setActAvaWei(inventory.getActAvaWei()-executeSolution.getChemicalDosage());
//						inventory.setModifier(userId + "");
//						inventory.setModifyDate(new Date());
//						
//						InventoryModify inventoryModify = new InventoryModify();
//						ModifyType modifyType = new ModifyType();
//						modifyType.setModifyTypeId(1);
//						
//						inventoryModify.setModifyType(modifyType);
//						inventoryModify.setInventories(executeSolution.getInventory());
//						inventoryModify.setMeasurement(executeSolution.getMeasurement());
//						inventoryModify.setModifyProcess(executeSolution.getReactionProcess());
//						inventoryModify.setChangeDate(new Date());
//						inventoryModify.setModifier(sysUser);
//						inventoryModify.setModifyDate(new Date());
//						inventoryModify.setModifyAfter(inventory.getActAvaWei());
//						
//						inventoryModifyService.save(inventoryModify);
//						inventoriesService.updateInventory(inventory);
//	
//					}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			resultVo.setErrCode(1);
//			resultVo.setErrMsg("更新失败");
//			return resultVo;
//		}
//		resultVo.setErrCode(0);
//		resultVo.setErrMsg("更新成功");
//		return resultVo;
//	}
	
	/**
	 * @Description 删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeSolution/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		executeSolutionService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("update success");
		return resultVo;
		
	}
}
