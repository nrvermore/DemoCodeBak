package com.labwinner.controller;

import java.util.ArrayList;
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
import com.labwinner.domain.DesignDosage;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionDesignChemical;
import com.labwinner.domain.ReactionDesignParameter;
import com.labwinner.domain.ReactionDesignProcess;
import com.labwinner.domain.ReactionDesignSolution;
import com.labwinner.domain.SolutionDesignDosage;
import com.labwinner.domain.SysUser;
import com.labwinner.service.DesignDosageService;
import com.labwinner.service.ReactionDesignChemicalService;
import com.labwinner.service.ReactionDesignService;
import com.labwinner.service.ReactionDesignSolutionService;
import com.labwinner.service.SolutionDesignDosageService;
import com.labwinner.service.SysUserService;
import com.labwinner.vo.ReactionDesignChemicalVo;


@RestController
public class ReactionDesignChemicalController {
	
	private static Logger logger = LoggerFactory
			.getLogger(ReactionDesignChemicalController.class);

	@Autowired
	private ReactionDesignChemicalService reactionDesignChemicalService;
	
	@Autowired
	private DesignDosageService designDosageService;
	
	@Autowired
	private ReactionDesignService reactionDesignService;
	
	@Autowired
	private ReactionDesignSolutionService reactionDesignSolutionService;
	
	@Autowired
	private SolutionDesignDosageService solutionDesignDosageService;
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/reactionDesignChemical/list", method = RequestMethod.GET)
	public List<ReactionDesignChemical> getAll() {
		List<ReactionDesignChemical> list = reactionDesignChemicalService.getAll();
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
	@RequestMapping(value = "/reactionDesignChemical/getById/{id}", method = RequestMethod.GET)
	public ReactionDesignChemical getById(@PathVariable("id") Integer id) {
		ReactionDesignChemical reactionDesignChemical = reactionDesignChemicalService.getById(id);
		if (reactionDesignChemical == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return reactionDesignChemical;
	}
	
	@RequestMapping(value = "/reactionDesignChemical/{name}", method = RequestMethod.GET)
	public List<ReactionDesignChemical> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<ReactionDesignChemical> reactionDesignChemicals = reactionDesignChemicalService.getByName(name);
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
	
	@RequestMapping(value = "/reactionDesignChemical", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<ReactionDesignChemical> reactionDesignChemicales) {

        ResultVo resultVo = new ResultVo();

		try {
			for (ReactionDesignChemical reactionDesignChemical : reactionDesignChemicales) {
				reactionDesignChemicalService.save(reactionDesignChemical);
				
				List<DesignDosage> designDosages = new ArrayList<DesignDosage>(reactionDesignChemical.getDesignDosages());
				for (DesignDosage designDosage : designDosages) {
					designDosage.setReactionDesignChemical(reactionDesignChemical);
					designDosageService.save(designDosage);
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
	 * @Description 根据是否存在使用设计Id保存或编辑原料和溶液的对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/chemicalAndSolution/addOrUpdate", method = RequestMethod.POST)
	public ResultVo save(@RequestBody ReactionDesignChemicalVo reactionDesignChemicalVo) {
		
		ResultVo resultVo = new ResultVo();
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		
		List<ReactionDesignChemical> reactionDesignChemicals=reactionDesignChemicalVo.getReactionDesignChemicals();
	    List<ReactionDesignSolution> reactionDesignSolutions=reactionDesignChemicalVo.getReactionDesignSolutions();
	    ReactionDesign reactionDesign=reactionDesignChemicalVo.getReactionDesign();
	    Integer type=reactionDesign.getType();
	    Integer id=reactionDesign.getReactionDesignId();
	    
	    
			//保存原料
	    	if(id==null){
	    		reactionDesign.setCreateDate(new Date());
	    		reactionDesign.setSysUser(sysUser);
	    		reactionDesign.setType(type);
	    		reactionDesignService.save(reactionDesign);
	    	   if(reactionDesignChemicals!=null){
				    for (ReactionDesignChemical reactionDesignChemical : reactionDesignChemicals) {
					 reactionDesignChemical.setReactionDesign(reactionDesign);
					 reactionDesignChemicalService.save(reactionDesignChemical);
					 List<DesignDosage> designDosages = new ArrayList<DesignDosage>(reactionDesignChemical.getDesignDosages());
						for (DesignDosage designDosage : designDosages) {
							designDosage.setReactionDesignChemical(reactionDesignChemical);
							designDosageService.save(designDosage);
							}
					    }
					}
			//保存溶液
			  if(reactionDesignSolutions!=null){
				  for (ReactionDesignSolution reactionDesignSolution : reactionDesignSolutions) {
					  reactionDesignSolution.setReactionDesign(reactionDesign);
					reactionDesignSolutionService.save(reactionDesignSolution);
					List<SolutionDesignDosage> solutionDesignDosages = new ArrayList<SolutionDesignDosage>(reactionDesignSolution.getSolutionDesignDosages());
					 for (SolutionDesignDosage solutionDesignDosage : solutionDesignDosages) {
						 solutionDesignDosage.setReactionDesignSolution(reactionDesignSolution);
						 solutionDesignDosageService.save(solutionDesignDosage);
					}
				}
			  } 
			       resultVo.setErrCode(0);
				   resultVo.setErrMsg("save successe");
				   resultVo.setResultData(reactionDesign.getReactionDesignId());
				   return resultVo;
			  
			  
	    	}else { 
	    		//删除该实验设计的原有原料以及用量
	    		reactionDesignChemicalService.delete(id);
	    		if(reactionDesignChemicals!=null){
					for (ReactionDesignChemical reactionDesignChemical : reactionDesignChemicals) {
					    reactionDesignChemical.setReactionDesign(reactionDesign);
						reactionDesignChemicalService.save(reactionDesignChemical);
						List<DesignDosage> designDosages = new ArrayList<DesignDosage>(reactionDesignChemical.getDesignDosages());
						for (DesignDosage designDosage : designDosages) {
							designDosage.setReactionDesignChemical(reactionDesignChemical);
							designDosageService.save(designDosage);
						}
					}
				}
	    		
	    		//删除该实验设计的原有溶液以及用量
				reactionDesignSolutionService.delete(id);
				//保存溶液
				if(reactionDesignSolutions!=null){
					for (ReactionDesignSolution reactionDesignSolution : reactionDesignSolutions) {
						reactionDesignSolution.setReactionDesign(reactionDesign);
						reactionDesignSolutionService.save(reactionDesignSolution);
						List<SolutionDesignDosage> solutionDesignDosages = new ArrayList<SolutionDesignDosage>(reactionDesignSolution.getSolutionDesignDosages());
						for (SolutionDesignDosage solutionDesignDosage : solutionDesignDosages) {
							solutionDesignDosage.setReactionDesignSolution(reactionDesignSolution);
							solutionDesignDosageService.save(solutionDesignDosage);
						}
					}
				} 
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update successe");
				resultVo.setResultData(reactionDesign.getReactionDesignId());
				return resultVo;
	    	}
		    
		}
		
	/**
	 * @Description 修改原料和溶液的对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/chemicalAndSolution/update", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody ReactionDesignChemicalVo reactionDesignChemicalVo) {
		
		ResultVo resultVo = new ResultVo();
		
		List<ReactionDesignChemical> reactionDesignChemicals=reactionDesignChemicalVo.getReactionDesignChemicals();
		List<ReactionDesignSolution> reactionDesignSolutions=reactionDesignChemicalVo.getReactionDesignSolutions();
		
		try {
			//保存原料
			if(reactionDesignChemicals!=null){
				for (ReactionDesignChemical reactionDesignChemical : reactionDesignChemicals) {
					//获取当前试验设计id
					ReactionDesign reactionDesign=reactionDesignChemical.getReactionDesign();
					Integer id=reactionDesign.getReactionDesignId();
					//删除该实验设计的原有原料以及用量
				    reactionDesignChemicalService.delete(id);
				    
					reactionDesignChemicalService.save(reactionDesignChemical);
					List<DesignDosage> designDosages = new ArrayList<DesignDosage>(reactionDesignChemical.getDesignDosages());
					for (DesignDosage designDosage : designDosages) {
						designDosage.setReactionDesignChemical(reactionDesignChemical);
						designDosageService.save(designDosage);
					}
				}
			}
			//保存溶液
			if(reactionDesignSolutions!=null){
				for (ReactionDesignSolution reactionDesignSolution : reactionDesignSolutions) {
					//获取当前试验设计id
					ReactionDesign reactionDesign=reactionDesignSolution.getReactionDesign();
					Integer id=reactionDesign.getReactionDesignId();
					//删除该实验设计的原有原料以及用量
					reactionDesignSolutionService.delete(id);
	
					reactionDesignSolutionService.save(reactionDesignSolution);
					List<SolutionDesignDosage> solutionDesignDosages = new ArrayList<SolutionDesignDosage>(reactionDesignSolution.getSolutionDesignDosages());
					for (SolutionDesignDosage solutionDesignDosage : solutionDesignDosages) {
						solutionDesignDosage.setReactionDesignSolution(reactionDesignSolution);
						solutionDesignDosageService.save(solutionDesignDosage);
					}
				}
			} 
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save successe");
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("save fail");
			return resultVo;
		}
	}
	
	/**
	 * @Description 更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionDesignChemical", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody List<ReactionDesignChemical> reactionDesignChemicales) {

		 ResultVo resultVo = new ResultVo();
			
		 
			try {
				for (ReactionDesignChemical reactionDesignChemical : reactionDesignChemicales) {
					
					//获取当前试验设计id
					ReactionDesign reactionDesign=reactionDesignChemical.getReactionDesign();
					Integer id=reactionDesign.getReactionDesignId();
				    reactionDesignChemicalService.delete(id);
					reactionDesignChemicalService.save(reactionDesignChemical);
					List<DesignDosage> designDosages = new ArrayList<DesignDosage>(reactionDesignChemical.getDesignDosages());
					
					for (DesignDosage designDosage : designDosages) {
						designDosage.setReactionDesignChemical(reactionDesignChemical);
						designDosageService.save(designDosage);
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


	@RequestMapping(value = "/reactionDesignChemical/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		
		 ResultVo resultVo=new ResultVo();
		try {
			reactionDesignChemicalService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete successe");
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("delete fail");
			return resultVo;
		}
	}
	
	/**
	 * @Description 根据设计原料Id删除方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/deleteByDesignChemicalId/{id}", method = RequestMethod.DELETE)
	public ResultVo deleteByDesignChemicalId(@PathVariable("id") Integer id) {
		ResultVo resultVo=new ResultVo();
		
		try {
			reactionDesignChemicalService.deleteByDesignChemicalId(id);
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
