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
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionDesignParameter;
import com.labwinner.domain.ReactionDesignProcess;
import com.labwinner.service.ReactionDesignParameterService;
import com.labwinner.service.ReactionDesignProcessService;
import com.labwinner.service.ReactionDesignService;

@RestController
public class ReactionDesignProcessController {
	
	private static Logger logger = LoggerFactory
			.getLogger(ReactionDesignProcessController.class);

	@Autowired
	private ReactionDesignProcessService reactionDesignProcessService;
	
	@Autowired
	private ReactionDesignService reactionDesignService;
	
	@Autowired
	private ReactionDesignParameterService reactionDesignParameterService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/reactionDesignProcess/list", method = RequestMethod.GET)
	public List<ReactionDesignProcess> getAll() {
		List<ReactionDesignProcess> list = reactionDesignProcessService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionDesignProcess/getById/{id}", method = RequestMethod.GET)
	public ReactionDesignProcess getById(@PathVariable("id") Integer id) {
		ReactionDesignProcess reactionDesignProcess = reactionDesignProcessService.getById(id);
		if (reactionDesignProcess == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return reactionDesignProcess;
	}
	
	@RequestMapping(value = "/reactionDesignProcess/{name}", method = RequestMethod.GET)
	public List<ReactionDesignProcess> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<ReactionDesignProcess> reactionDesignProcessList = reactionDesignProcessService.getByName(name);
		if (reactionDesignProcessList == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return reactionDesignProcessList;
	}
	

	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionDesignProcess", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<ReactionDesignProcess> reactionDesignProcesses) {
		
		        ResultVo resultVo = new ResultVo();
		
				try {
					for (ReactionDesignProcess reactionDesignProcess : reactionDesignProcesses) {
						reactionDesignProcessService.save(reactionDesignProcess);
						
						List<ReactionDesignParameter> reactionDesignParameters = new ArrayList<ReactionDesignParameter>(reactionDesignProcess.getReactionDesignParameters());
						for (ReactionDesignParameter reactionDesignParameter : reactionDesignParameters) {
							reactionDesignParameter.setReactionDesignProcess(reactionDesignProcess);
							reactionDesignParameterService.save(reactionDesignParameter);
					}
						resultVo.setErrCode(0);
						resultVo.setErrMsg("save successe");
						return resultVo;
						}
					} catch (Exception e) {
						resultVo.setErrCode(1);
						resultVo.setErrMsg("save fail");
						return resultVo;
					}
				return resultVo;
			}
			
	/**
	 * @Description 更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionDesignProcess", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody List<ReactionDesignProcess> reactionDesignProcesses) {

		 ResultVo resultVo = new ResultVo();
			
		 
			try {
				for (ReactionDesignProcess reactionDesignProcess : reactionDesignProcesses) {
					
					//获取当前试验设计id
					ReactionDesign reactionDesign=reactionDesignProcess.getReactionDesign();
					Integer id=reactionDesign.getReactionDesignId();
						    reactionDesignProcessService.delete(id);
				  
					reactionDesignProcessService.save(reactionDesignProcess);
					
					List<ReactionDesignParameter> reactionDesignParameters = new ArrayList<ReactionDesignParameter>(reactionDesignProcess.getReactionDesignParameters());
					for (ReactionDesignParameter reactionDesignParameter : reactionDesignParameters) {
						reactionDesignParameter.setReactionDesignProcess(reactionDesignProcess);
						reactionDesignParameterService.save(reactionDesignParameter);
				}

					resultVo.setErrCode(0);
					resultVo.setErrMsg("save successe");
					return resultVo;
					}
				} catch (Exception e) {
					resultVo.setErrCode(1);
					resultVo.setErrMsg("save fail");
					return resultVo;
				}
			return resultVo;
		}


	@RequestMapping(value = "/reactionDesignProcess/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		reactionDesignProcessService.delete(id);
	}


}
