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
import com.labwinner.domain.ReactionDesignSolution;
import com.labwinner.domain.SolutionDesignDosage;
import com.labwinner.service.SolutionDesignDosageService;

@RestController
public class SolutionDesignDosageController {
	
	private static Logger logger = LoggerFactory
			.getLogger(SolutionDesignDosageController.class);

	@Autowired
	private SolutionDesignDosageService solutionDesignDosageService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/solutionDesignDosage/list", method = RequestMethod.GET)
	public List<SolutionDesignDosage> getAll() {
		List<SolutionDesignDosage> list = solutionDesignDosageService.getAll();
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
	@RequestMapping(value = "/solutionDesignDosage/getById/{id}", method = RequestMethod.GET)
	public SolutionDesignDosage getById(@PathVariable("id") Integer id) {
		SolutionDesignDosage reactionDesignChemical = solutionDesignDosageService.getById(id);
		if (reactionDesignChemical == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return reactionDesignChemical;
	}
	
	@RequestMapping(value = "/solutionDesignDosage/{name}", method = RequestMethod.GET)
	public List<SolutionDesignDosage> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<SolutionDesignDosage> reactionDesignChemicals = solutionDesignDosageService.getByName(name);
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
	
	@RequestMapping(value = "/solutionDesignDosage", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<SolutionDesignDosage> solutionDesignDosages) {

        ResultVo resultVo = new ResultVo();

		try {
			for (SolutionDesignDosage solutionDesignDosage : solutionDesignDosages) {
				solutionDesignDosageService.save(solutionDesignDosage);
			
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
	
	@RequestMapping(value = "/solutionDesignDosage", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody List<SolutionDesignDosage> reactionDesignSolutions) {

		 ResultVo resultVo = new ResultVo();
			
		 
			try {
				for (SolutionDesignDosage solutionDesignDosage : reactionDesignSolutions) {
					
					//获取当前试验设计id
					ReactionDesignSolution reactionDesignSolution=solutionDesignDosage.getReactionDesignSolution();
					Integer id=reactionDesignSolution.getSolutionDesignId();
					solutionDesignDosageService.delete(id);
					solutionDesignDosageService.save(solutionDesignDosage);

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


		@RequestMapping(value = "/solutionDesignDosage/{id}", method = RequestMethod.DELETE)
		public void delete(@PathVariable("id") Integer id) {
			solutionDesignDosageService.delete(id);
		}

}
