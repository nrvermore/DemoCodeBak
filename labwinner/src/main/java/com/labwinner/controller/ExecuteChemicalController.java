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
import com.labwinner.domain.ExecuteChemical;
import com.labwinner.domain.ExecuteChemicalGroup;
import com.labwinner.domain.Inventories;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.InventoryModify;
import com.labwinner.domain.Measurement;
import com.labwinner.domain.ModifyType;
import com.labwinner.domain.ReactionExecuteParameter;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ExecuteChemicalGroupService;
import com.labwinner.service.ExecuteChemicalService;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.service.InventoryModifyService;
import com.labwinner.service.MeasurementService;
import com.labwinner.service.SysUserService;

@RestController
public class ExecuteChemicalController {

	private static Logger logger = LoggerFactory
			.getLogger(ExecuteChemicalController.class);

	@Autowired
	private ExecuteChemicalService executeChemicalService;
	@Autowired
	private ExecuteChemicalGroupService executeChemicalGroupService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private InventoriesService inventoriesService;
	@Autowired
	private InventoryModifyService inventoryModifyService;
	
	@Autowired
	private MeasurementService measurementService;
	
	@Autowired
	private InventoryLocationService inventoryLocService;
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeChemical/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List<ExecuteChemical> executeChemical = executeChemicalService.getById(id);
		if (executeChemical == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(executeChemical);
		return resultVo;
	}
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeChemical/getByGroupId/{id}", method = RequestMethod.GET)
	public ResultVo getByGroupId(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List<ExecuteChemical> executeChemical = executeChemicalService.getByGroupId(id);
		for (ExecuteChemical executeChemical2 : executeChemical) {
			if( executeChemical2.getInventory()!=null){
				InventoryLocation location = executeChemical2.getInventory().getInventoryLocation();
				if(location!=null && location.getPid()!=0){
					String pname = getInventoryPname(location.getCid());
					location.setParentName(pname);
				}
			}
			Measurement measurement = executeChemical2.getMeasurement();
			if(measurement!=null){
				Measurement measureMent = measurementService.getByMeasureUnitId(measurement.getMeasureUnitId());
				executeChemical2.setMeasurement(measureMent);
			}
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(executeChemical);
		return resultVo;
	}
	
	/**
	 * @Description 新增对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeChemical/appSaveOrUpdate", method = RequestMethod.POST)
	public ResultVo appSaveOrUpdate(@RequestBody ExecuteChemical executeChemical) {
		ResultVo resultVo = new ResultVo();
		
		ExecuteChemicalGroup executeChemicalGroup = executeChemicalGroupService.getById(executeChemical.getExecuteChemicalGroup().getChemicalGroupId());
		Double totalDosage = Double.valueOf(executeChemicalGroup.getGroupDosage());
		if(executeChemical.getExecuteChemicalId()==null){
			executeChemicalService.save(executeChemical);
			
			//计算实验库存组总量
			
			Inventories inventory = inventoriesService.getById(executeChemical.getInventory().getInventoryId());
			Double inventoryConversion = inventory.getMeasurement().getConversionRelation();
			ExecuteChemical executeChemical2 =executeChemicalService.getByChmicalId(executeChemical.getExecuteChemicalId());
			Double chemicalConversion = executeChemical2.getMeasurement().getConversionRelation();
			Double chemicalDosage =  executeChemical.getChemicalDosage()*(chemicalConversion/inventoryConversion);
			totalDosage = totalDosage+chemicalDosage;
			
			executeChemicalGroup.setGroupDosage(totalDosage.toString());
			executeChemicalGroupService.update(executeChemicalGroup);
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
		}else{
			
			Inventories inventory = inventoriesService.getById(executeChemical.getInventory().getInventoryId());
			ExecuteChemical chemical = executeChemicalService.getByChmicalId(executeChemical.getExecuteChemicalId());
			Double inventoryConversion = inventory.getMeasurement().getConversionRelation();
			Double oldhemicalConversion = chemical.getMeasurement().getConversionRelation();
			Double oldChemicalDosage =  chemical.getChemicalDosage()*(oldhemicalConversion/inventoryConversion);
			
			Measurement measurement = measurementService.getByMeasureUnitId(executeChemical.getMeasurement().getMeasureUnitId());
			
			Double newChemicalConversion =measurement.getConversionRelation();
			Double newChemicalDosage =  executeChemical.getChemicalDosage()*(newChemicalConversion/inventoryConversion);
			
			executeChemicalService.update(executeChemical);
			totalDosage = totalDosage+newChemicalDosage-oldChemicalDosage;
			executeChemicalGroup.setGroupDosage(totalDosage.toString());
			executeChemicalGroupService.update(executeChemicalGroup);
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
	@RequestMapping(value = "/executeChemical", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<ExecuteChemical> executeChemicals) {
		ResultVo resultVo = new ResultVo();
		if(executeChemicals.size()>0){
//			ReactionProcess reactionProcess = executeChemicals.get(0).getReactionProcess();
			ExecuteChemicalGroup executeChemicalGroup = executeChemicals.get(0).getExecuteChemicalGroup();
//			if(reactionProcess !=null){
//				Integer processId = reactionProcess.getReactionProcessId();
//				//删除步骤id
//				executeChemicalService.deleteByProcessId(processId);
//			}
			if(executeChemicalGroup!=null){
				Integer chemicalGroupId = executeChemicalGroup.getChemicalGroupId();
				//删除组id
				executeChemicalService.deleteByGroupId(chemicalGroupId);
			}
			for(ExecuteChemical executeChemical :executeChemicals){
				executeChemicalService.save(executeChemical);
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
	@RequestMapping(value = "/executeChemical/appSaveList", method = RequestMethod.POST)
	public ResultVo appSaveList(@RequestBody List<ExecuteChemical> executeChemicals) {
		ResultVo resultVo = new ResultVo();
		ExecuteChemicalGroup executeChemicalGroup = executeChemicalGroupService.getById(executeChemicals.get(0).getExecuteChemicalGroup().getChemicalGroupId());
		Double totalDosage = Double.valueOf(executeChemicalGroup.getGroupDosage());
		if(executeChemicals.size()>0){
			for(ExecuteChemical executeChemical :executeChemicals){
				executeChemicalService.save(executeChemical);
				//计算实验库存组总量
				//TODO 未完成
				//计算实验库存组总量
				Inventories inventory = inventoriesService.getById(executeChemical.getInventory().getInventoryId());
				Double inventoryConversion = inventory.getMeasurement().getConversionRelation();
				ExecuteChemical executeChemical2 =executeChemicalService.getByChmicalId(executeChemical.getExecuteChemicalId());
				Double chemicalConversion = executeChemical2.getMeasurement().getConversionRelation();
				Double chemicalDosage =  executeChemical.getChemicalDosage()*(chemicalConversion/inventoryConversion);
				totalDosage =totalDosage+chemicalDosage;
			}
			executeChemicalGroup.setGroupDosage(totalDosage.toString());
			executeChemicalGroupService.update(executeChemicalGroup);
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
	@RequestMapping(value = "/executeChemical", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody List<ExecuteChemical> executeChemicals) {
		ResultVo resultVo = new ResultVo();
		if(executeChemicals.size()>0){
			for(ExecuteChemical executeChemical :executeChemicals){
				executeChemicalService.update(executeChemical);
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
	@RequestMapping(value = "/executeChemical/updateInventory/{id}", method = RequestMethod.GET)
	public ResultVo updateInventory(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		try {
			List<ExecuteChemical> executeChemicals = executeChemicalService.getById(id);
			
			if(executeChemicals!=null && executeChemicals.size()>0){
				for(ExecuteChemical executeChemical :executeChemicals){
					
						Inventories inventory = inventoriesService.getById(executeChemical.getInventory().getInventoryId());
						inventory.setActAvaWei(inventory.getActAvaWei()-executeChemical.getChemicalDosage());
						inventory.setModifier(userId + "");
						inventory.setModifyDate(new Date());
						
						InventoryModify inventoryModify = new InventoryModify();
						ModifyType modifyType = new ModifyType();
						modifyType.setModifyTypeId(1);
						
						inventoryModify.setModifyType(modifyType);
						inventoryModify.setInventories(executeChemical.getInventory());
						inventoryModify.setMeasurement(executeChemical.getMeasurement());
						inventoryModify.setModifyProcess(executeChemical.getReactionProcess());
						inventoryModify.setChangeDate(new Date());
						inventoryModify.setModifier(sysUser);
						inventoryModify.setModifyDate(new Date());
						inventoryModify.setModifyAfter(inventory.getActAvaWei());
						
						inventoryModifyService.save(inventoryModify);
						inventoriesService.updateInventory(inventory);
	
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("更新失败");
			return resultVo;
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("更新成功");
		return resultVo;
	}
	
	/**
	 * @Description 删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeChemical/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		ExecuteChemical executeChemical = executeChemicalService.getByChmicalId(id);
		ExecuteChemicalGroup executeChemicalGroup = executeChemicalGroupService.getById(executeChemical.getChemicalGroupId());
		Double totalDosage = Double.valueOf(executeChemicalGroup.getGroupDosage());
		//计算实验库存组总量
		
		Inventories inventory = inventoriesService.getById(executeChemical.getInventory().getInventoryId());
		Double inventoryConversion = inventory.getMeasurement().getConversionRelation();
		Double chemicalConversion = executeChemical.getMeasurement().getConversionRelation();
		Double chemicalDosage =  executeChemical.getChemicalDosage()*(chemicalConversion/inventoryConversion);
		totalDosage = totalDosage-chemicalDosage;
		
		executeChemicalGroup.setGroupDosage(totalDosage.toString());
		executeChemicalGroupService.update(executeChemicalGroup);
		
		executeChemicalService.delete(id);
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
		return resultVo;
		
	}
	
	 public String getInventoryPname( Integer id) {
		    try {
		      String ss="";
		    loop:for(int i=0;i<10;i++){
		      InventoryLocation inventoryLocation =  inventoryLocService.getById(id);
		      if(inventoryLocation.getPid()!=null&&inventoryLocation.getPid()!=0){
		        if(i==0){
		          ss="";
		        }else if(i==1){
		          ss=inventoryLocation.getLabel();
		        }else{
		          ss=inventoryLocation.getLabel()+">"+ss;
		        }
		        id=inventoryLocation.getPid();
		      }else{
		        if(!"".equals(ss)){
		          ss=inventoryLocation.getLabel()+">"+ss;
		        }else{
		          ss=inventoryLocation.getLabel();  
		        }
		        break loop;
		      }
		      }
		      return ss;
		    } catch (Exception e) {
		    	return null;
		      // TODO: handle exception
		    }
		  }
}
