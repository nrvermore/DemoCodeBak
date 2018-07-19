package com.labwinner.controller;

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
import com.labwinner.domain.ReactionDevice;
import com.labwinner.domain.ReactionExecuteParameter;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.service.ReactionDeviceService;

@RestController
public class ReactionDeviceController {
	
	private static Logger logger = LoggerFactory
			.getLogger(ReactionDeviceController.class);

	@Autowired
	private ReactionDeviceService reactionDeviceService;
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionDevice/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		ReactionDevice reactionDevice = reactionDeviceService.getById(id);
		if (reactionDevice == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(reactionDevice);
		return resultVo;
	}
	
	/**
	 * @Description 新增对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionDevice/addOrUpdate", method = RequestMethod.POST)
	public ResultVo appSaveOrUpdate(@RequestBody ReactionDevice reactionDevice) {
		ResultVo resultVo = new ResultVo();
		
		if(reactionDevice.getReactionDeviceId()==null){
			reactionDeviceService.save(reactionDevice);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
		}else{
			reactionDeviceService.update(reactionDevice);
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
	@RequestMapping(value = "/reactionDevice", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<ReactionDevice> reactionDevices) {
		ResultVo resultVo = new ResultVo();
		if(reactionDevices.size()>0){
			ReactionProcess reactionProcess = reactionDevices.get(0).getReactionProcess();
			if(reactionProcess !=null){
				Integer processId = reactionProcess.getReactionProcessId();
				//删除步骤id
				reactionDeviceService.deleteByProcessId(processId);
			}
			for(ReactionDevice reactionDevice :reactionDevices){
				reactionDeviceService.save(reactionDevice);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			return resultVo;
		}else{
			resultVo.setErrCode(2);
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
	@RequestMapping(value = "/reactionDevice", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody List<ReactionDevice> ReactionDevices) {
		ResultVo resultVo = new ResultVo();
		if(ReactionDevices.size()>0){
			for(ReactionDevice ReactionDevice :ReactionDevices){
				reactionDeviceService.update(ReactionDevice);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update success");
			return resultVo;
		}else{
			resultVo.setErrCode(2);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}
		
	}
	
	/**
	 * @Description 删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年7月31日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionDevice/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		reactionDeviceService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("update success");
		return resultVo;
	}

}
