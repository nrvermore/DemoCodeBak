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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.DesignDosage;
import com.labwinner.domain.DesignTechnology;
import com.labwinner.domain.DesignTechnologyProcess;
import com.labwinner.domain.DesignTechnologyRelation;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionDesignChemical;
import com.labwinner.domain.ReactionDesignParameter;
import com.labwinner.domain.ReactionDesignProcess;
import com.labwinner.domain.ReactionDesignSolution;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.domain.ReactionRecord;
import com.labwinner.domain.SolutionDesignDosage;
import com.labwinner.domain.SysUser;
import com.labwinner.service.DesignDosageService;
import com.labwinner.service.DesignTechnologyRelationService;
import com.labwinner.service.DeviceAppointmentService;
import com.labwinner.service.ReactionDesignChemicalService;
import com.labwinner.service.ReactionDesignParameterService;
import com.labwinner.service.ReactionDesignProcessService;
import com.labwinner.service.ReactionDesignService;
import com.labwinner.service.ReactionDesignSolutionService;
import com.labwinner.service.ReactionRecordService;
import com.labwinner.service.ReactionService;
import com.labwinner.service.SolutionDesignDosageService;
import com.labwinner.service.SysUserService;
import com.labwinner.vo.PageVo;
import com.labwinner.vo.ReactionDesignVo;

@RestController
public class ReactionDesignController {

	private static Logger logger = LoggerFactory
			.getLogger(ReactionDesignController.class);

	@Autowired
	private ReactionDesignService reactionDesignService;
	
	@Autowired
	private ReactionService reactionService;

	@Autowired
	private ReactionDesignChemicalService reactionDesignChemicalService;

	@Autowired
	private ReactionDesignParameterService reactionDesignParameterService;

	@Autowired
	private DesignDosageService designDosageService;

	@Autowired
	private ReactionRecordService reactionRecordService;

	@Autowired
	private ReactionDesignProcessService reactionDesignProcessService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private ReactionDesignSolutionService reactionDesignSolutionService;
	
	@Autowired
	private SolutionDesignDosageService solutionDesignDosageService;
	
	@Autowired
	private DesignTechnologyRelationService designTechnologyRelationService;
	
	@Autowired
	private DeviceAppointmentService deviceAppointmentService;

	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */

	@RequestMapping(value = "/reactionDesign/list", method = RequestMethod.GET)
	public ResultVo getAll() {

		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		List<ReactionDesign> list = reactionDesignService.getAll();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find successe");
		resultVo.setResultData(list);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return resultVo;

	}

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionDesign/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		
		ResultVo resultVo = new ResultVo();
		
		ReactionDesign reactionDesign = reactionDesignService.getReactionDesignById(id);
		if(reactionDesign!=null){
			List<Reaction> reactions = reactionService.getByDesignId(id);
			ReactionDesign reactionDesign2 = reactionDesignService.getChemicals(id);
			ReactionDesign reactionDesign3 = reactionDesignService.getSolutionById(id);
			ReactionDesign reactionDesign4 = reactionDesignService.getDesignTechnologyRelationById(id);
			
			if(reactions!=null){
				reactionDesign.setReactions(reactions);
			}
            if(reactionDesign2!=null){
            	reactionDesign.setReactionDesignChemicals(reactionDesign2.getReactionDesignChemicals());
			}
            if(reactionDesign3!=null){
    			reactionDesign.setReactionDesignSolutions(reactionDesign3.getReactionDesignSolutions());
            }
			if(reactionDesign4!=null){
				List<DesignTechnologyRelation> designTechnologyRelations=reactionDesign4.getDesignTechnologyRelations();
				if(designTechnologyRelations!=null){
					for (DesignTechnologyRelation designTechnologyRelation : designTechnologyRelations) {
						DesignTechnology designTechnology=designTechnologyRelation.getDesignTechnology();
						List<DesignTechnologyProcess> designTechnologyProcesses=designTechnology.getDesignTechnologyProcesses();
						if(designTechnologyProcesses!=null){
							designTechnology.setProcesseesNum(designTechnologyProcesses.size());
						}	
					}
				}
				reactionDesign.setDesignTechnologyRelations(reactionDesign4.getDesignTechnologyRelations());
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(reactionDesign);
			return resultVo;
		}else {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find null");
			resultVo.setResultData(null);
			return resultVo;
		}
		
	}

	/**
	 * @Description 根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionDesignPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				List<ReactionDesign> list = reactionDesignService.getUserListByKeyword(keyword,userId);
				return new PageInfo(list);
			} else {
				List<ReactionDesign> list = reactionDesignService.getAllPageable(keyword);
				return new PageInfo(list);
			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				List<ReactionDesign> list = reactionDesignService.getUserList(userId);
				return new PageInfo(list);
			} else {
				List<ReactionDesign> list = reactionDesignService.getAll();
				return new PageInfo(list);
			}
		}
	}
	
	
	/**
	 * @Description 实验设计，实验下拉框
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionDesignList", method = RequestMethod.GET)
	public ResultVo getReactionDesignList() {
		
		ResultVo resultVo = new ResultVo();
		
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		
			if (!roleName.equals("ROLE_TEAM")) {
				List<ReactionDesign> list = reactionDesignService.getUserDesignsList(userId);
				if(list!=null){
					for (ReactionDesign reactionDesign : list) {
						Integer num = reactionDesign.getReactions().size();
						reactionDesign.setReactionNum(num);
					}
					
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find successe");
					resultVo.setResultData(list);
					return resultVo;
					}
					resultVo.setErrCode(2);
					resultVo.setErrMsg("find is null");
					return resultVo;
			} else {
				Integer count = reactionDesignService.getAllCount();
				List<ReactionDesign> list = reactionDesignService.getAllDesignsList();
				if(list!=null){
					for (ReactionDesign reactionDesign : list) {
						Integer num = reactionDesign.getReactions().size();
						reactionDesign.setReactionNum(num);
					}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find successe");
					resultVo.setResultData(list);
					return resultVo;
					}
					resultVo.setErrCode(2);
					resultVo.setErrMsg("find is null");
					return resultVo;
			
		}
	}
	
	/**
	 * @Description 实验设计，单一实验下拉框
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/getPrivateReactionByUserId", method = RequestMethod.GET)
	public ResultVo getPrivateReactionByUserId() {
		
		ResultVo resultVo = new ResultVo();
		
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		
		if (!roleName.equals("ROLE_TEAM")) {
			    List<ReactionDesign> list = reactionDesignService.getPrivateReactionByUserId(userId);
				
			    if(list!=null&&list.size()>0){
			    	resultVo.setErrCode(0);
					resultVo.setErrMsg("find successe");
					resultVo.setResultData(list);
					return resultVo;
			     }
				    resultVo.setErrCode(1);
					resultVo.setErrMsg("find is null");
					resultVo.setResultData(null);
					return resultVo;
			}else {
				List<ReactionDesign> list = reactionDesignService.getPrivateReaction();
				if(list!=null&&list.size()>0){
			    	resultVo.setErrCode(0);
					resultVo.setErrMsg("find successe");
					resultVo.setResultData(list);
					return resultVo;
			     }
					resultVo.setErrCode(1);
					resultVo.setErrMsg("find is null");
					resultVo.setResultData(null);
					return resultVo;
			}
		}
	
	/**
	 * @Description 实验设计，组实验下拉框
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/getTeamReactionByUserId", method = RequestMethod.GET)
	public ResultVo getTeamReactionByUserId() {
		
		ResultVo resultVo = new ResultVo();
		
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		
		if (!roleName.equals("ROLE_TEAM")) {
			List<ReactionDesign> list = reactionDesignService.getTeamReactionByUserId(userId);
			
			if(list!=null&&list.size()>0){
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find successe");
				resultVo.setResultData(list);
				return resultVo;
			}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find is null");
			resultVo.setResultData(null);
			return resultVo;
		}else {
			List<ReactionDesign> list = reactionDesignService.getTeamReaction();
			if(list!=null&&list.size()>0){
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find successe");
				resultVo.setResultData(list);
				return resultVo;
			}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find is null");
			resultVo.setResultData(null);
			return resultVo;
		}
	}
	
	
	
	/**
	 * @Description 实验执行根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionDesignByKeyword/{keyword}/{page}/{pageSize}", method = RequestMethod.GET)
	public ResultVo getDesignByKeyword(@PathVariable String keyword,
			@PathVariable Integer page,
			@PathVariable Integer pageSize) {
		
		ResultVo resultVo = new ResultVo();
		PageVo pageVo = new PageVo();
		Integer startCount = (page-1)*pageSize;
		Integer endCount = pageSize;
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				Integer count  = reactionDesignService.getUserDesignsCount(userId, keyword);
				List<ReactionDesign> list = reactionDesignService.getUserDesignsByKeyword(userId,keyword,startCount,endCount);
				if(list!=null){
					for (ReactionDesign reactionDesign : list) {
						Integer num = reactionDesign.getReactions().size();
						reactionDesign.setReactionNum(num);
					}
					pageVo.setTotalCount(count);
					pageVo.setData(list);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find successe");
					resultVo.setResultData(pageVo);
					return resultVo;
					}
					resultVo.setErrCode(2);
					resultVo.setErrMsg("find is null");
					return resultVo;
			} else {
				Integer count = reactionDesignService.getAllDesignsCount(keyword);
				List<ReactionDesign> list = reactionDesignService.getAllDesignsByKeyword(keyword,startCount,endCount);
				if(list!=null){
					for (ReactionDesign reactionDesign : list) {
						Integer num = reactionDesign.getReactions().size();
						reactionDesign.setReactionNum(num);
					}
					pageVo.setTotalCount(count);
					pageVo.setData(list);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find successe");
					resultVo.setResultData(pageVo);
					return resultVo;
					}
					resultVo.setErrCode(2);
					resultVo.setErrMsg("find is null");
					return resultVo;
			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				Integer count = reactionDesignService.getUserCount(userId);
				List<ReactionDesign> list = reactionDesignService.getUserDesigns(userId,startCount,endCount);
				if(list!=null){
					for (ReactionDesign reactionDesign : list) {
						Integer num = reactionDesign.getReactions().size();
						reactionDesign.setReactionNum(num);
					}
					pageVo.setTotalCount(count);
					pageVo.setData(list);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find successe");
					resultVo.setResultData(pageVo);
					return resultVo;
					}
					resultVo.setErrCode(2);
					resultVo.setErrMsg("find is null");
					return resultVo;
			} else {
				Integer count = reactionDesignService.getAllCount();
				List<ReactionDesign> list = reactionDesignService.getAllDesigns(startCount,endCount);
				if(list!=null){
					for (ReactionDesign reactionDesign : list) {
						Integer num = reactionDesign.getReactions().size();
						reactionDesign.setReactionNum(num);
					}
					pageVo.setTotalCount(count);
					pageVo.setData(list);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find successe");
					resultVo.setResultData(pageVo);
					return resultVo;
					}
					resultVo.setErrCode(2);
					resultVo.setErrMsg("find is null");
					return resultVo;
			}
		}
	}
	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */

	@RequestMapping(value = "/reactionDesign", method = RequestMethod.POST)
	public ResultVo save(@RequestBody ReactionDesignVo reactionDesignVo) {

		ResultVo resultVo = new ResultVo();
		ReactionDesign reactionDesign = reactionDesignVo.getReactionDesign();
		Integer id=reactionDesign.getReactionDesignId();
		
		if(id == null ){
		reactionDesign.setCreateDate(new Date());
		reactionDesignService.save(reactionDesign);
		List<ReactionDesignChemical> reactionDesignChemicals = reactionDesignVo.getReactionDesignChemicals();
		
		List<ReactionDesignProcess> reactionDesignProcesses = reactionDesignVo.getReactionDesignProcesses();
		
		List<ReactionRecord> reactionRecords = reactionDesignVo.getReactionRecords();

			for (ReactionDesignChemical reactionDesignChemical : reactionDesignChemicals) {
				reactionDesignChemical.setReactionDesign(reactionDesign);
				reactionDesignChemicalService.save(reactionDesignChemical);
				
				List<DesignDosage> designDosages = new ArrayList<DesignDosage>(reactionDesignChemical.getDesignDosages());
				for (DesignDosage designDosage : designDosages) {
					designDosage.setReactionDesignChemical(reactionDesignChemical);
					designDosageService.save(designDosage);
				}
			}

			for (ReactionDesignProcess reactionDesignProcess : reactionDesignProcesses) {
				reactionDesignProcess.setReactionDesign(reactionDesign);
				reactionDesignProcessService.save(reactionDesignProcess);
			
				List<ReactionDesignParameter> reactionDesignParameters = new ArrayList<ReactionDesignParameter>(reactionDesignProcess.getReactionDesignParameters());
				for (ReactionDesignParameter reactionDesignParameter : reactionDesignParameters) {
					reactionDesignParameter.setReactionDesignProcess(reactionDesignProcess);
					reactionDesignParameterService.save(reactionDesignParameter);
				}
			}
			
			if (null != reactionRecords && reactionRecords.size() > 0) {
				for (int i = 0; i < reactionRecords.size(); i++) {
					reactionRecords.get(i).setReactionDesign(reactionDesign);
					reactionRecords.get(i).setReactionNum(i + 1 + "");
					reactionRecordService.save(reactionRecords.get(i));
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save successe");
			resultVo.setResultData(reactionDesign.getReactionDesignId());
			return resultVo;
		}else{
			reactionDesign.setCreateDate(new Date());
			reactionDesignService.update(reactionDesign);
			
			List<ReactionDesignChemical> reactionDesignChemicals = reactionDesignVo.getReactionDesignChemicals();
			List<ReactionDesignProcess> reactionDesignProcesses = reactionDesignVo.getReactionDesignProcesses();
			List<ReactionRecord> reactionRecords = reactionDesignVo.getReactionRecords();
                       reactionDesignChemicalService.delete(id);
				for (ReactionDesignChemical reactionDesignChemical : reactionDesignChemicals) {
					reactionDesignChemical.setReactionDesign(reactionDesign);
					reactionDesignChemicalService.save(reactionDesignChemical);
					
					List<DesignDosage> designDosages =  new ArrayList<DesignDosage>(reactionDesignChemical.getDesignDosages());
					for (DesignDosage designDosage : designDosages) {
						designDosage.setReactionDesignChemical(reactionDesignChemical);
						designDosageService.save(designDosage);
					}
				}
                       reactionDesignProcessService.delete(id);
				for (ReactionDesignProcess reactionDesignProcess : reactionDesignProcesses) {
					reactionDesignProcess.setReactionDesign(reactionDesign);
					reactionDesignProcessService.save(reactionDesignProcess);

					List<ReactionDesignParameter> reactionDesignParameters =  new ArrayList<ReactionDesignParameter>(reactionDesignProcess.getReactionDesignParameters());
					for (ReactionDesignParameter reactionDesignParameter : reactionDesignParameters) {
						reactionDesignParameter.setReactionDesignProcess(reactionDesignProcess);
						reactionDesignParameterService.save(reactionDesignParameter);
					}
				}
				
				         reactionRecordService.delete(id);
				if (null != reactionRecords && reactionRecords.size() > 0) {
					
					for (int i = 0; i < reactionRecords.size(); i++) {
						reactionRecords.get(i).setReactionDesign(reactionDesign);
						reactionRecords.get(i).setReactionNum(i + 1 + "");
						reactionRecordService.save(reactionRecords.get(i));
					}
				}
				
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update successe");
				resultVo.setResultData(reactionDesign.getReactionDesignId());
				return resultVo;
		}

	}
	/**
	 * @Description 单个保存项目试验对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionDesign/Add", method = RequestMethod.POST)
	public ResultVo save(@RequestBody ReactionDesign reactionDesign) {
		
		ResultVo resultVo = new ResultVo();

			try {
				reactionDesign.setCreateDate(new Date());
				reactionDesign.setType(1);
				reactionDesignService.save(reactionDesign);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save successe");
				resultVo.setResultData(reactionDesign.getReactionDesignId());
				return resultVo;
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("save fail");
				return resultVo;
			}
		     
		}
	/**
	 * @Description 单个保存非项目试验对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionDesign/AddOrUpdateByType", method = RequestMethod.POST)
	public ResultVo AddOrUpdateByType(@RequestBody ReactionDesign reactionDesign) {
		
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
				LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				Integer type=reactionDesign.getType();
				Integer id=reactionDesign.getReactionDesignId();
				SysUser sysUser2=reactionDesign.getSysUser();
		
		if(id==null){
					if(type==0){
						reactionDesign.setCreateDate(new Date());
						reactionDesign.setType(0);
						reactionDesign.setSysUser(sysUser);
						reactionDesignService.saveNoProject(reactionDesign);
					}else if (type==1) {
						if(sysUser2!=null){
							reactionDesign.setSysUser(sysUser2);
						}
						reactionDesign.setCreateDate(new Date());
						reactionDesign.setType(1);
						reactionDesign.setSysUser(sysUser2);
						reactionDesignService.save(reactionDesign);
					}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("save successe");
					resultVo.setResultData(reactionDesign.getReactionDesignId());
					return resultVo;
		      }else {
		    	  if(type==0){
						reactionDesign.setSysUser(sysUser);
						reactionDesignService.updateNoProject(reactionDesign);
					}else if (type==1) {
						if(sysUser2!=null){
							reactionDesign.setSysUser(sysUser2);
						}
						reactionDesignService.update(reactionDesign);
					}
		    	    resultVo.setErrCode(0);
					resultVo.setErrMsg("update successe");
					resultVo.setResultData(reactionDesign.getReactionDesignId());
					return resultVo;
			  }
		}
		
	/**
	 * @Description 单个编辑项目试验对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionDesign/Update", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody ReactionDesign reactionDesign) {
		
		ResultVo resultVo = new ResultVo();
		
		try {
			reactionDesignService.update(reactionDesign);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe");
			resultVo.setResultData(reactionDesign.getReactionDesignId());
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}
		
	}
	/**
	 * @Description 单个编辑非项目试验对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionDesign/updateNoProject", method = RequestMethod.PUT)
	public ResultVo updateNoProject(@RequestBody ReactionDesign reactionDesign) {
		
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
				LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		try {
			reactionDesign.setSysUser(sysUser);
			reactionDesignService.updateNoProject(reactionDesign);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe");
			resultVo.setResultData(reactionDesign.getReactionDesignId());
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}
		
	}

	/**
	 * @Description 更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionDesign", method = RequestMethod.GET)
	public void updateExecute(@RequestBody ReactionDesign reactionDesign) {

		reactionDesignService.updateExecute(reactionDesign);
	}

	@RequestMapping(value = "/reactionDesign/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		
		ResultVo resultVo = new ResultVo();
		ReactionDesign reactionDesign=reactionDesignService.getReactionDesignById(id);
		ReactionDesign reactionDesign1=reactionDesignService.getChemicals(id);
		ReactionDesign reactionDesign2=reactionDesignService.getSolutionById(id);
		ReactionDesign reactionDesign3=reactionDesignService.getDesignTechnologyRelationById(id);
		List<Reaction> reactions = reactionService.getByDesignId(id);
		List<Reaction> reactions2 = reactionService.getAppointmentByReactionDesignId(id);
		
		try {
			if(reactionDesign1!=null){
			List<ReactionDesignChemical> reactionDesignChemicals=reactionDesign1.getReactionDesignChemicals();
			if(reactionDesignChemicals!=null){
					reactionDesignChemicalService.delete(id);
			}
			}
			if(reactionDesign2!=null){
			List<ReactionDesignSolution> reactionDesignSolutions=reactionDesign2.getReactionDesignSolutions();
			if(reactionDesignSolutions!=null){
				   reactionDesignSolutionService.delete(id);
			}
			}
			if(reactionDesign3!=null){
				List<DesignTechnologyRelation> designTechnologyRelations=reactionDesign3.getDesignTechnologyRelations();
				if(designTechnologyRelations!=null){
					designTechnologyRelationService.delete(id);
				}
			}
			
			if(reactions2!=null){
			for (Reaction reaction : reactions2) {
				List<DeviceAppointment> deviceAppointments =reaction.getDeviceAppointments();
				if(deviceAppointments!=null){
					for (DeviceAppointment deviceAppointment : deviceAppointments) {
						deviceAppointmentService.delete(deviceAppointment.getAppointmentId());
					}
				}
			  }
			}
			if(reactions!=null){
				for (Reaction reaction : reactions) {
					reactionService.delete(reaction.getReactionId());
				}
			}
			if(reactionDesign!=null){
				reactionDesignService.delete(id);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete success");
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("delete fail");
			return resultVo;
		}
	}
	/**
	 * @Description 根据实验设计id获取原料列表方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@RequestMapping(value = "/getChemicalsByReactionDesignId/{id}", method = RequestMethod.GET)
	public ResultVo getChemicalsByReactionDesignId(@PathVariable("id") Integer id) {
		
		ResultVo resultVo = new ResultVo();
		ReactionDesign reactionDesign=reactionDesignService.getChemicals(id);
		
		if(reactionDesign!=null){
			List<ReactionDesignChemical> reactionDesignChemicals=reactionDesign.getReactionDesignChemicals();
			if(reactionDesignChemicals!=null){
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(reactionDesignChemicals);
				return resultVo;
			}else {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("此实验设计无原料");
				resultVo.setResultData(null);
				return resultVo;
			}
		}
		return resultVo;
	}
	/**
	 * @Description 根据实验设计id获取溶液列表方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@RequestMapping(value = "/getSolutionByReactionDesignId/{id}", method = RequestMethod.GET)
	public ResultVo getSolutionByReactionDesignId(@PathVariable("id") Integer id) {
		
		ResultVo resultVo = new ResultVo();
		ReactionDesign reactionDesign=reactionDesignService.getSolutionById(id);
		
		if(reactionDesign!=null){
			List<ReactionDesignSolution> reactionDesignSolutions=reactionDesign.getReactionDesignSolutions();
			if(reactionDesignSolutions!=null){
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(reactionDesignSolutions);
				return resultVo;
			}else {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("此实验设计无溶液");
				resultVo.setResultData(null);
				return resultVo;
			}
		}
		return resultVo;
	}
	/**
	 * @Description 根据实验设计id获取溶液和原料列表方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@RequestMapping(value = "/getSolutionAndChemicalsByReactionDesignId/{id}", method = RequestMethod.GET)
	public ResultVo getSolutionAndChemicalsByReactionDesignId(@PathVariable("id") Integer id) {
		
		ResultVo resultVo = new ResultVo();
		ReactionDesign reactionDesign1=reactionDesignService.getSolutionById(id);
		ReactionDesign reactionDesign2=reactionDesignService.getChemicals(id);
		ReactionDesign reactionDesign=new ReactionDesign();
		
		  try {
			if(reactionDesign1!=null){
				List<ReactionDesignSolution> reactionDesignSolutions=reactionDesign1.getReactionDesignSolutions();
				if(reactionDesignSolutions!=null){
					reactionDesign.setReactionDesignSolutions(reactionDesignSolutions);
				  }
			   }
			  if(reactionDesign2!=null){
				  List<ReactionDesignChemical> reactionDesignChemicals=reactionDesign2.getReactionDesignChemicals();
					if(reactionDesignChemicals!=null){
						reactionDesign.setReactionDesignChemicals(reactionDesignChemicals);
					}
			  }
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(reactionDesign);
					return resultVo;
		} catch (Exception e) {
					resultVo.setErrCode(1);
					resultVo.setErrMsg("find fail");
					resultVo.setResultData(null);
					return resultVo;
		}
		
	}
	
	
	//---------------------------------新实验设计接口------------------------------
	
	/**
	 * @Description 根据关键字获取团队实验对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionTeamDesign/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getTeamDesigns(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		// 获取当前用户
		ResultVo resultVo = new ResultVo();
		List<ReactionDesign> list = new ArrayList<ReactionDesign>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionDesignService.getUserListByKeyword(keyword,userId);
				
			} else {
				list = reactionDesignService.getAllPageable(keyword);
				
			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionDesignService.getUserList(userId);
				
			} else {
				list = reactionDesignService.getAll();
			}
		}
		if(list!=null && list.size()>0){
			for (ReactionDesign reactionDesign : list) {
				if(reactionDesign.getReactionDesignId()!=null){
					Integer num = reactionService.getByDesignId(reactionDesign.getReactionDesignId()).size();
					reactionDesign.setReactionNum(num);
				}
				if("".equals(reactionDesign.getReactionGroupName())||reactionDesign.getReactionGroupName()==null){
					reactionDesign.setReactionGroupName("系统暂存的试验");
				}
			}
		}
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(new PageInfo(list));
		return resultVo;
		
	}
	
	/**
	 * @Description 根据关键字获取团队实验对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionWebTeamSingleDesign/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAppTeamSingleDesigns(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		// 获取当前用户
		ResultVo resultVo = new ResultVo();
		List<ReactionDesign> list = new ArrayList<ReactionDesign>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionDesignService.getAppUserSingleByKeyword(keyword,
						userId,1);

			} else {
				list = reactionDesignService.getAppAllSinglePageable(keyword,1);

			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionDesignService.getAppUserSingleList(userId,1);

			} else {
				list = reactionDesignService.getAppAllSingle(1);
			}
		}
		if(list!=null && list.size()>0){
			for (ReactionDesign reactionDesign : list) {
				if(reactionDesign.getReactionDesignId()!=null){
					Integer num = reactionService.getByDesignId(reactionDesign.getReactionDesignId()).size();
					reactionDesign.setReactionNum(num);
				}
				if("".equals(reactionDesign.getReactionGroupName())||reactionDesign.getReactionGroupName()==null){
					reactionDesign.setReactionGroupName("系统暂存的试验");
				}
			}
		}

		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(new PageInfo(list));
		return resultVo;

	}
	
	/**
	 * @Description 根据关键字获取我的实验对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionWebMySingleDesign/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAppMySingleDesigns(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		// 获取当前用户
		ResultVo resultVo = new ResultVo();
		List<ReactionDesign> list = new ArrayList<ReactionDesign>();
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			list = reactionDesignService.getAppMySingleDesignsByKeyword(
					keyword, userId,1);
		} else {
			list = reactionDesignService.getAppMySingleList(userId,1);
		}

		if(list!=null && list.size()>0){
			for (ReactionDesign reactionDesign : list) {
				if(reactionDesign.getReactionDesignId()!=null){
					Integer num = reactionService.getByDesignId(reactionDesign.getReactionDesignId()).size();
					reactionDesign.setReactionNum(num);
				}
				if("".equals(reactionDesign.getReactionGroupName())||reactionDesign.getReactionGroupName()==null){
					reactionDesign.setReactionGroupName("系统暂存的试验");
				}
			}
		}

		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(new PageInfo(list));
		return resultVo;

	}
	/**
	 * @Description 根据关键字获取我的实验过滤掉无工艺对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionWebHaveReactionMySingleDesign/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getNoTechnologyAppMySingleDesigns(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		// 获取当前用户
		ResultVo resultVo = new ResultVo();
		List<ReactionDesign> list = new ArrayList<ReactionDesign>();
		
		List<ReactionDesign> reactionDesigns = new ArrayList<ReactionDesign>();
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionDesignService.getNoTechnologyAppMySingleDesignsByKeyword(keyword, userId, 1);

			} else {
				list = reactionDesignService.getNoTechnologyAppMySingleDesignsByNoUserIdKeyword(keyword, 1);

			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionDesignService.getNoTechnologyAppMySingleList(userId, 1);

			} else {
				list = reactionDesignService.getNoTechnologyAppMySingleListByNoUserId();
			}
		}
		if(list!=null && list.size()>0){
			for (ReactionDesign reactionDesign : list) {
				if(reactionDesign.getReactionGroupName()==null||"".equals(reactionDesign.getReactionGroupName())){
					reactionDesign.setReactionGroupName("系统暂存的试验");
				}
				if(reactionDesign.getReactionDesignId()!=null){
					Integer num = reactionService.getByDesignId(reactionDesign.getReactionDesignId()).size();
					reactionDesign.setReactionNum(num);
				}
				List<Reaction> reactions=reactionDesign.getReactions();
				if(reactions!=null){
					for (Reaction reaction : reactions) {
						List<ReactionProcess> reactionProcesses=reaction.getReactionProcesses();
						if(reactionProcesses!=null){
							reaction.setProcessSize(reactionProcesses.size());
						}
					}
					reactionDesigns.add(reactionDesign);
				}
			}
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(new PageInfo(reactionDesigns));
		return resultVo;
		
	}
	
	
	/**
	 * @Description 根据关键字获取我的实验对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionUserDesign/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getUserDesigns(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		// 获取当前用户
		ResultVo resultVo = new ResultVo();
		List<ReactionDesign> list = new ArrayList<ReactionDesign>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				list = reactionDesignService.getMyDesignsByKeyword(keyword,userId);
		} else {	
				list = reactionDesignService.getMyList(userId);
		}
		
		if(list!=null && list.size()>0){
			for (ReactionDesign reactionDesign : list) {
				if(reactionDesign.getReactionDesignId()!=null){
					Integer num = reactionService.getByDesignId(reactionDesign.getReactionDesignId()).size();
					reactionDesign.setReactionNum(num);
				}
				if("".equals(reactionDesign.getReactionGroupName())||reactionDesign.getReactionGroupName()==null){
					reactionDesign.setReactionGroupName("系统暂存的试验");
				}
			}
		}
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(new PageInfo(list));
		return resultVo;
		
	}
	
	/**
	 * @Description 根据主键删除实验设计
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionDesign/delete/{id}", method = RequestMethod.DELETE)
	public void deleteDesign(@PathVariable("id") Integer id) {
		
		//reactionDesignParameterService.delete(id);
		//reactionDesignProcessService.delete(id);
		ReactionDesign reactionDesign=reactionDesignService.getSolutionById(id);
		List<ReactionDesignSolution> reactionDesignSolutions=reactionDesign.getReactionDesignSolutions();
		if(reactionDesignSolutions!=null){
			for (ReactionDesignSolution reactionDesignSolution : reactionDesignSolutions) {
				List<SolutionDesignDosage> solutionDesignDosages=reactionDesignSolution.getSolutionDesignDosages();
				    Integer id2=reactionDesignSolution.getSolutionDesignId();
				for (SolutionDesignDosage solutionDesignDosage : solutionDesignDosages) {
					solutionDesignDosageService.delete(id2);
				}
				reactionDesignSolutionService.delete(id);
			}
		}
		reactionDesignChemicalService.delete(id);
		designTechnologyRelationService.delete(id);
		reactionRecordService.delete(id);
		reactionDesignService.delete(id);
		List<Reaction> reactions = reactionService.getByDesignId(id);
		for (Reaction reaction : reactions) {
			reactionService.delete(reaction.getReactionId());
		}
	}
	/**
	 * @Description 获取单一试验设计名称
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/getNoPorjectReactionGropNameByType", method = RequestMethod.GET)
	public ResultVo getNoPorjectReactionGropNameByType() {
		
		ResultVo resultVo = new ResultVo();
		
		List<ReactionDesign> reactionDesigns=new ArrayList<ReactionDesign>();
		
		List<ReactionDesign> list = reactionDesignService.getReactionGropNameByType(0);
		if(list!=null&&list.size()>0){
			for (ReactionDesign reactionDesign : list) {
				if(reactionDesign.getReactionGroupName()!=null||"".equals(reactionDesign.getReactionGroupName())){
					reactionDesigns.add(reactionDesign);
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(reactionDesigns);
			return resultVo;
		}else {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("无数据");
			resultVo.setResultData(null);
			return resultVo;
		}
	}
	/**
	 * @Description 获取组试验设计名称
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/getPorjectReactionGropNameByType", method = RequestMethod.GET)
	public ResultVo getPorjectReactionGropNameByType() {
		
		ResultVo resultVo = new ResultVo();
		
		List<ReactionDesign> reactionDesigns=new ArrayList<ReactionDesign>();
		
		List<ReactionDesign> list = reactionDesignService.getReactionGropNameByType(1);
		if(list!=null&&list.size()>0){
			for (ReactionDesign reactionDesign : list) {
				if(reactionDesign.getReactionGroupName()!=null||"".equals(reactionDesign.getReactionGroupName())){
					reactionDesigns.add(reactionDesign);
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(reactionDesigns);
			return resultVo;
		}else {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("无数据");
			resultVo.setResultData(null);
			return resultVo;
		}
	}
	
	
	/**
	 * @Description web端根据当前登录人获取所有组实验对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */

	@RequestMapping(value = "/reactionDesigns/getTreamTechnologyByUserId/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getDesignTechnologyByUserId(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {

		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		
		List<ReactionDesign> reactionDesigns = new ArrayList<ReactionDesign>();
		
		List<ReactionDesign> list=new ArrayList<ReactionDesign>();
	/*	if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}*/
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				reactionDesigns = reactionDesignService.getDesignTechnologyByUserId(userId, keyword);

			} else {
				reactionDesigns = reactionDesignService.getDesignTechnologyNoUserIdBykeyword(keyword);
			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				reactionDesigns = reactionDesignService.getDesignTechnologyByUserIdNokeyword(userId);

			} else {
				reactionDesigns = reactionDesignService.getDesignTechnologyNoUserId();
			}
		}
		if(reactionDesigns!=null){
			   for (ReactionDesign reactionDesign : reactionDesigns) {
				   List<DesignTechnologyRelation> designTechnologyRelations=reactionDesign.getDesignTechnologyRelations();
				   if(designTechnologyRelations!=null){
					   for (DesignTechnologyRelation designTechnologyRelation : designTechnologyRelations) {
						DesignTechnology designTechnology=designTechnologyRelation.getDesignTechnology();
						if(designTechnology!=null){
							designTechnology.setTechnologyNum(designTechnologyRelations.size());
						}
					}
				   }
				/*String name=reactionDesign.getReactionGroupName();
				if(name!=null&&!"".equals(name)){
					list.add(reactionDesign);
				}*/
				if(reactionDesign.getReactionGroupName()!=null&&!"".equals(reactionDesign.getReactionGroupName())){
					list.add(reactionDesign);
				}
					/*if("".equals(reactionDesign.getReactionGroupName())||reactionDesign.getReactionGroupName()==null){
						reactionDesign.setReactionGroupName("系统暂存的试验");
						list.add(reactionDesign);
					}*/
			}
			int total = list.size();
			double c = (((double) total) / pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			int num = list.size() > page * pageSize ? page * pageSize : list.size();
			if (page <= d) {
				pageEntity.setList(list.subList((page-1)*pageSize, num));
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(pageEntity);
			return resultVo;
			
		}else {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("无数据");
			resultVo.setResultData(null);
			return resultVo;
		}

	}
	/**
	 * @Description App端根据当前登录人获取所有组实验对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/reactionDesigns/getAppTreamTechnologyByUserId/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAppDesignTechnologyByUserId(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		
		List<ReactionDesign> reactionDesigns = new ArrayList<ReactionDesign>();
		
		List<ReactionDesign> list=new ArrayList<ReactionDesign>();
		/*	if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}*/
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			reactionDesigns = reactionDesignService.getDesignTechnologyByUserId(userId, keyword);
		} else {	
			reactionDesigns = reactionDesignService.getDesignTechnologyByUserIdNokeyword(userId);
		}
		if(reactionDesigns!=null){
			for (ReactionDesign reactionDesign : reactionDesigns) {
				List<DesignTechnologyRelation> designTechnologyRelations=reactionDesign.getDesignTechnologyRelations();
				if(designTechnologyRelations!=null){
					for (DesignTechnologyRelation designTechnologyRelation : designTechnologyRelations) {
						DesignTechnology designTechnology=designTechnologyRelation.getDesignTechnology();
						if(designTechnology!=null){
							designTechnology.setTechnologyNum(designTechnologyRelations.size());
						}
					}
				}
//				String name=reactionDesign.getReactionGroupName();
//				if(name!=null&&!"".equals(name)){
//					list.add(reactionDesign);
//				}
				if("".equals(reactionDesign.getReactionGroupName())||reactionDesign.getReactionGroupName()==null){
					reactionDesign.setReactionGroupName("系统暂存的试验");
					list.add(reactionDesign);
				}
	
			}
			int total = list.size();
			double c = (((double) total) / pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			int num = list.size() > page * pageSize ? page * pageSize : list.size();
			if (page <= d) {
				pageEntity.setList(list.subList(0, num));
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(pageEntity);
			return resultVo;
			
		}else {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("无数据");
			resultVo.setResultData(null);
			return resultVo;
		}
		
	}
	/**
	 * @Description Web端根据当前登录人获取所有单一对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/reactionDesigns/getTechnologyByUserId/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getTechnologyByUserId(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		
		List<ReactionDesign> reactionDesigns = new ArrayList<ReactionDesign>();
		
		List<ReactionDesign> list=new ArrayList<ReactionDesign>();
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			reactionDesigns = reactionDesignService.getTechnologyByUserIdAndKeyword(userId, keyword);
		} else {	
			reactionDesigns = reactionDesignService.getTechnologyByUserId(userId);
		}
		if(reactionDesigns!=null&&reactionDesigns.size()>0){
			for (ReactionDesign reactionDesign : reactionDesigns) {
//				String name=reactionDesign.getReactionGroupName();
//				if(name!=null&&!"".equals(name)){
//					list.add(reactionDesign);
//				}
				if("".equals(reactionDesign.getReactionGroupName())||reactionDesign.getReactionGroupName()==null){
					reactionDesign.setReactionGroupName("系统暂存的试验");
					list.add(reactionDesign);
				}
	
			}
			int total = list.size();
			double c = (((double) total) / pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			int num = list.size() > page * pageSize ? page * pageSize : list.size();
			if (page <= d) {
				pageEntity.setList(list.subList((page-1)*pageSize, num));
			}
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(pageEntity);
			return resultVo;
		}else {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("无数据");
			resultVo.setResultData(null);
			return resultVo;
		}
		
	}
	/**
	 * @Description App端根据当前登录人获取所有单一对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/reactionDesigns/getAppTechnologyByUserId/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAppTechnologyByUserId(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		
		List<ReactionDesign> reactionDesigns = new ArrayList<ReactionDesign>();
		
		List<ReactionDesign> list=new ArrayList<ReactionDesign>();
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			reactionDesigns = reactionDesignService.getTechnologyByUserIdAndKeyword(userId, keyword);
		} else {	
			reactionDesigns = reactionDesignService.getTechnologyByUserId(userId);
		}
		if(reactionDesigns!=null&&reactionDesigns.size()>0){
			for (ReactionDesign reactionDesign : reactionDesigns) {
//				String name=reactionDesign.getReactionGroupName();
//				if(name!=null&&!"".equals(name)){
//					list.add(reactionDesign);
//				}
				if("".equals(reactionDesign.getReactionGroupName())||reactionDesign.getReactionGroupName()==null){
					reactionDesign.setReactionGroupName("系统暂存的试验");
					list.add(reactionDesign);
				}
				
				
			}
			int total = list.size();
			double c = (((double) total) / pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			int num = list.size() > page * pageSize ? page * pageSize : list.size();
			if (page <= d) {
				pageEntity.setList(list.subList(0, num));
			}
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(pageEntity);
			return resultVo;
		}else {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("无数据");
			resultVo.setResultData(null);
			return resultVo;
		}
		
	}

	

}
