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
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.ExecuteChemical;
import com.labwinner.domain.ExecuteChemicalGroup;
import com.labwinner.domain.Inventories;
import com.labwinner.domain.InventoryGroups;
import com.labwinner.domain.Measurement;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.service.ExecuteChemicalGroupService;
import com.labwinner.service.ExecuteChemicalService;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.InventoryGroupService;
import com.labwinner.service.InventoryGroupsService;
import com.labwinner.service.MeasurementService;

@RestController
public class ExecuteChemicalGroupController {
	
	private static Logger logger = LoggerFactory
			.getLogger(ExecuteChemicalGroupController.class);

	@Autowired
	private ExecuteChemicalGroupService executeChemicalGroupService;
	
	@Autowired
	private ExecuteChemicalService executeChemicalService;
	
	@Autowired
	private InventoryGroupsService inventoryGroupsService;
	
	@Autowired
	private MeasurementService measurementService;
	@Autowired
	private InventoriesService inventoriesService;
	

	/**
	 * @Description 根据实验主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeChemicalGroup/getByProcessId/{id}", method = RequestMethod.GET)
	public ResultVo getByProcessId(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		List<ExecuteChemicalGroup> list = executeChemicalGroupService.getByProcessId(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(list);
		return resultVo;
	}
	
	/**
	 * @Description 根据实验主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeChemicalGroup/save", method = RequestMethod.POST)
	public ResultVo save(@RequestBody ExecuteChemicalGroup executeChemicalGroup) {

		ResultVo resultVo = new ResultVo();
		
		if(executeChemicalGroup.getChemicalGroupId()==null){
			executeChemicalGroupService.save(executeChemicalGroup);
		}else{
			executeChemicalGroupService.update(executeChemicalGroup);
		}
	
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save success");
		return resultVo;
	}
	
	/**
	 * @Description 根据主键删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeChemicalGroup/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		executeChemicalService.deleteByGroupId(id);
		executeChemicalGroupService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}
	
	/**
	 * @Description 根据主键删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeChemicalGroup/deleteByProcessId/{id}", method = RequestMethod.DELETE)
	public ResultVo deleteByProcessId(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		List<ExecuteChemicalGroup> executeChemicalGroups = executeChemicalGroupService.getByProcessId(id);
		if(executeChemicalGroups!=null && executeChemicalGroups.size()>0){
			executeChemicalService.delete(id);
		}
		executeChemicalGroupService.deleteByProcessId(id);
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
	@RequestMapping(value = "/executeChemicalGroup/saveList", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<ExecuteChemicalGroup> executeChemicalGroups) {
		ResultVo resultVo = new ResultVo();
		List<ExecuteChemicalGroup> executeChemicalGroupList = new ArrayList<ExecuteChemicalGroup>();
		if(executeChemicalGroups.size()>0){
			ReactionProcess reactionProcess = executeChemicalGroups.get(0).getReactionProcess();
			if(reactionProcess !=null){
				Integer processId = reactionProcess.getReactionProcessId();
				//删除步骤id
				executeChemicalGroupService.deleteByProcessId(processId);
			}
			for(ExecuteChemicalGroup executeChemicalGroup :executeChemicalGroups){
				if(executeChemicalGroup.getChemicalGroupId()==null){
					executeChemicalGroupService.save(executeChemicalGroup);
				}else{
					executeChemicalGroupService.saveOlder(executeChemicalGroup);
				}
				if(executeChemicalGroup.getInventoryGroups()!=null){
					InventoryGroups inventoryGroups = inventoryGroupsService.getById(executeChemicalGroup.getInventoryGroups().getGroupId());
					Measurement measure = inventoryGroups.getMeasurement();
					if(measure!=null){
						Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
						inventoryGroups.setMeasurement(measureMent);
					}
					executeChemicalGroup.setInventoryGroups(inventoryGroups);
				}
				executeChemicalGroupList.add(executeChemicalGroup);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			resultVo.setResultData(executeChemicalGroupList);
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
	@RequestMapping(value = "/executeChemicalGroup/appSaveList", method = RequestMethod.POST)
	public ResultVo appSaveList(@RequestBody ExecuteChemicalGroup executeChemicalGroup) {
		ResultVo resultVo = new ResultVo();
		if(executeChemicalGroup!=null){
			Double totalDosage = 0.0;
			if(executeChemicalGroup.getChemicalGroupId()==null){
				Double groupDosage = 0.0;	
				executeChemicalGroup.setGroupDosage(groupDosage.toString());
				executeChemicalGroupService.save(executeChemicalGroup);
			}
			List<ExecuteChemical> executeChemicals = executeChemicalGroup.getExecuteChemicals();
			for (ExecuteChemical executeChemical : executeChemicals) {
				executeChemical.setExecuteChemicalGroup(executeChemicalGroup);
				executeChemicalService.save(executeChemical);
				
				//计算实验库存组总量
				Inventories inventory = inventoriesService.getById(executeChemical.getInventory().getInventoryId());
				Double inventoryConversion = inventory.getMeasurement().getConversionRelation();
				ExecuteChemical executeChemical2 =executeChemicalService.getByChmicalId(executeChemical.getExecuteChemicalId()); 
				Double chemicalConversion = executeChemical2.getMeasurement().getConversionRelation();
				Double chemicalDosage =  executeChemical.getChemicalDosage()*(chemicalConversion/inventoryConversion);
				totalDosage = totalDosage+chemicalDosage;
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
	
	

}
