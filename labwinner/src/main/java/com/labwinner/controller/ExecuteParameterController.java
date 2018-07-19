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
import com.labwinner.domain.ReactionExecuteParameter;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.service.ExecuteParemeterService;

@RestController
public class ExecuteParameterController {

	private static Logger logger = LoggerFactory
			.getLogger(ExecuteParameterController.class);

	@Autowired
	private ExecuteParemeterService executeParemeterService;
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeParemeter/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		ReactionExecuteParameter executeParameter = executeParemeterService.getById(id);
		if (executeParameter == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(executeParameter);
		return resultVo;
	}
	
	
	/**
	 * @Description 新增对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeParemeter", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<ReactionExecuteParameter> executeParameters) {
		ResultVo resultVo = new ResultVo();
		if(executeParameters.size()>0){
			ReactionProcess reactionProcess = executeParameters.get(0).getReactionProcess();
			if(reactionProcess !=null){
				Integer processId = reactionProcess.getReactionProcessId();
				//删除步骤id
				executeParemeterService.deleteByProcessId(processId);
			}
			for(ReactionExecuteParameter executeParameter :executeParameters){
				executeParemeterService.save(executeParameter);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			return resultVo;
		}else{
			resultVo.setErrCode(1);
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
	@RequestMapping(value = "/executeParemeter/appSaveOrUpdate", method = RequestMethod.POST)
	public ResultVo appSaveOrUpdate(@RequestBody ReactionExecuteParameter executeParameter) {
		ResultVo resultVo = new ResultVo();
		
		if(executeParameter.getParameterId()==null){
			executeParemeterService.save(executeParameter);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
		}else{
			executeParemeterService.update(executeParameter);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
		}
		return resultVo;
	}
	
	/**
	 * @Description 更新对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeParemeter", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody List<ReactionExecuteParameter> executeParameters) {
		ResultVo resultVo = new ResultVo();
		if(executeParameters.size()>0){
			for(ReactionExecuteParameter executeParameter :executeParameters){
				executeParemeterService.update(executeParameter);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update success");
			return resultVo;
		}else{
			resultVo.setErrCode(1);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}
		
	}
	
	/**
	 * @Description 删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/executeParemeter/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id")Integer id) {
		ResultVo resultVo = new ResultVo();
			executeParemeterService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete success");
			return resultVo;
	}
}
