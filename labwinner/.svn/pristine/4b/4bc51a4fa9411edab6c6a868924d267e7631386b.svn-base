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
import com.labwinner.domain.ReactionDesignSolution;
import com.labwinner.domain.SolutionDesignDosage;
import com.labwinner.service.ReactionDesignService;
import com.labwinner.service.ReactionDesignSolutionService;
import com.labwinner.service.SolutionDesignDosageService;


@RestController
public class ReactionDesignSolutionController {
	
	private static Logger logger = LoggerFactory
			.getLogger(ReactionDesignSolutionController.class);

	@Autowired
	private ReactionDesignSolutionService reactionDesignSolutionService;
	
	@Autowired
	private SolutionDesignDosageService solutionDesignDosageService;
	
	
	@Autowired
	private ReactionDesignService reactionDesignService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/reactionDesignSolution/list", method = RequestMethod.GET)
	public List<ReactionDesignSolution> getAll() {
		List<ReactionDesignSolution> list = reactionDesignSolutionService.getAll();
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
	@RequestMapping(value = "/reactionDesignSolution/getById/{id}", method = RequestMethod.GET)
	public ReactionDesignSolution getById(@PathVariable("id") Integer id) {
		ReactionDesignSolution reactionDesignChemical = reactionDesignSolutionService.getById(id);
		if (reactionDesignChemical == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return reactionDesignChemical;
	}
	
	@RequestMapping(value = "/reactionDesignSolution/{name}", method = RequestMethod.GET)
	public List<ReactionDesignSolution> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<ReactionDesignSolution> reactionDesignChemicals = reactionDesignSolutionService.getByName(name);
		if (reactionDesignChemicals == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return reactionDesignChemicals;
	}
	

	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionDesignSolution", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<ReactionDesignSolution> reactionDesignSolutions) {

        ResultVo resultVo = new ResultVo();

		try {
			for (ReactionDesignSolution reactionDesignSolution : reactionDesignSolutions) {
				reactionDesignSolutionService.save(reactionDesignSolution);
				
				List<SolutionDesignDosage> solutionDesignDosages = new ArrayList<SolutionDesignDosage>(reactionDesignSolution.getSolutionDesignDosages());
				for (SolutionDesignDosage solutionDesignDosage : solutionDesignDosages) {
					solutionDesignDosage.setReactionDesignSolution(reactionDesignSolution);
					solutionDesignDosageService.save(solutionDesignDosage);
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
	
	@RequestMapping(value = "/reactionDesignSolution", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody List<ReactionDesignSolution> reactionDesignSolutions) {

		 ResultVo resultVo = new ResultVo();
			
		 
			try {
				for (ReactionDesignSolution reactionDesignSolution : reactionDesignSolutions) {
					
					//获取当前试验设计id
					ReactionDesign reactionDesign=reactionDesignSolution.getReactionDesign();
					Integer id=reactionDesign.getReactionDesignId();
					reactionDesignSolutionService.delete(id);
					reactionDesignSolutionService.save(reactionDesignSolution);
					List<SolutionDesignDosage> solutionDesignDosages = new ArrayList<SolutionDesignDosage>(reactionDesignSolution.getSolutionDesignDosages());
					
					for (SolutionDesignDosage solutionDesignDosage : solutionDesignDosages) {
						solutionDesignDosage.setReactionDesignSolution(reactionDesignSolution);
						solutionDesignDosageService.save(solutionDesignDosage);
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


	@RequestMapping(value = "/reactionDesignSolution/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		reactionDesignSolutionService.delete(id);
	}
	
	@RequestMapping(value = "/deleteSolutionDesignId/{id}", method = RequestMethod.DELETE)
	public ResultVo deleteSolutionDesignId(@PathVariable("id") Integer id) {
		
		ResultVo resultVo = new ResultVo();
		
		try {
			reactionDesignSolutionService.deleteSolutionDesignId(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete successe");
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("delete fail");
			return resultVo;
		}

	}

}
