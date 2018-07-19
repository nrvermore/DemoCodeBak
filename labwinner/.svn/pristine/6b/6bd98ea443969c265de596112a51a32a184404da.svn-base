package com.labwinner.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

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
import com.labwinner.domain.Inventories;
import com.labwinner.domain.Inventory;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.MaterialField;
import com.labwinner.domain.SecureRank;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.service.MaterialFieldService;

@RestController
public class MaterialFieldController {

	private static Logger logger = LoggerFactory
			.getLogger(MaterialFieldController.class);

	@Autowired
	private MaterialFieldService materialFieldService;

	
	@RequestMapping(value = "/materialField/list", method = RequestMethod.GET)
	public List<MaterialField> getAll() {
		List<MaterialField> list = materialFieldService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}
    
	
		@RequestMapping(value = "/materialField/getTree", method = RequestMethod.GET)
	public ResultVo getTree() {
		ResultVo resultVo = new ResultVo();
		try {
			List<MaterialField> list = materialFieldService.getAllFirst();
			List<MaterialField> resList=new ArrayList<MaterialField>();
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					MaterialField materialField = materialFieldService.getTree(list.get(i).getCid());
					if(materialField!=null){
						resList.add(materialField);
					}
				}
			}
			//MaterialField materialField = materialFieldService.getTree(1);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(resList);
			return resultVo;
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("find fail");
		return resultVo;
	}

 

}
