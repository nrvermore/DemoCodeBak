package com.labwinner.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.PUT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Analytics;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.ExecuteChemical;
import com.labwinner.domain.ExecuteChemicalGroup;
import com.labwinner.domain.ExecuteSolution;
import com.labwinner.domain.InventoryGroups;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.JournalArticle;
import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.Measurement;
import com.labwinner.domain.Patent;
import com.labwinner.domain.Prototype;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionDevice;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.domain.SysUser;
import com.labwinner.service.AnalyticService;
import com.labwinner.service.DeviceLocationService;
import com.labwinner.service.ExecuteChemicalGroupService;
import com.labwinner.service.ExecuteChemicalService;
import com.labwinner.service.ExecuteSolutionService;
import com.labwinner.service.InventoryGroupsService;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.service.JournalArticleService;
import com.labwinner.service.KnowledgeAccService;
import com.labwinner.service.MeasurementService;
import com.labwinner.service.PatentService;
import com.labwinner.service.ReactionDesignService;
import com.labwinner.service.ReactionProcessService;
import com.labwinner.service.ReactionService;
import com.labwinner.service.ReactionStatusService;
import com.labwinner.service.SysUserService;
import com.labwinner.vo.ReactionVo;

@RestController
public class ReactonDesignAppController {

	private static Logger logger = LoggerFactory
			.getLogger(ReactonDesignAppController.class);

	@Autowired
	private ReactionDesignService reactionDesignService;

	@Autowired
	private ReactionService reactionService;
	
	@Autowired
	private ReactionProcessService reactionProcessService;
	
	@Autowired
	ExecuteChemicalService executeChemicalService;
	
	@Autowired
	ExecuteSolutionService executeSolutionService;
	
	@Autowired
	ReactionStatusService reactionStatusService;

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private AnalyticService analyticService;
	
	@Autowired
	private InventoryLocationService inventoryLocService;
	
	@Autowired
	private DeviceLocationService deviceLocationService;
	
	@Autowired
	private KnowledgeAccService knowledgeAccDaoService;
	
	@Autowired
	private ExecuteChemicalGroupService executeChemicalGroupService;
	
	@Autowired
	private InventoryGroupsService inventoryGroupsService;
	
	@Autowired
	private MeasurementService measurementService;
	
	@Autowired
	private JournalArticleService journalArticleService;
	
	@Autowired
	private PatentService patentService;
	
	@Value("${web.url_path_pdf}")
	String pdfUrlPath;
	
	@Value("${web.agency_pdf}")
	String agencyPdf;

	/**
	 * @Description 根据关键字获取我的实验对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionAppMyDesign/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAppMyDesigns(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		// 获取当前用户
		ResultVo resultVo = new ResultVo();
		List<ReactionDesign> list = new ArrayList<ReactionDesign>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			list = reactionDesignService.getAppMyDesignsByKeyword(keyword,
					userId);
		} else {
			list = reactionDesignService.getAppMyList(userId);
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
		resultVo.setErrMsg("find success");
		resultVo.setResultData(pageEntity);
		return resultVo;

	}

	/**
	 * @Description 根据关键字获取我的实验对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionAppMySingleDesign/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
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
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			list = reactionDesignService.getAppMySingleDesignsByKeyword(
					keyword, userId,0);
		} else {
			list = reactionDesignService.getAppMySingleList(userId,0);
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
		resultVo.setErrMsg("find success");
		resultVo.setResultData(pageEntity);
		return resultVo;

	}

	/**
	 * @Description 根据关键字获取团队实验对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionAppTeamDesign/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAppTeamDesigns(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		// 获取当前用户
		ResultVo resultVo = new ResultVo();
		List<ReactionDesign> list = new ArrayList<ReactionDesign>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionDesignService.getAppUserListByKeyword(keyword,
						userId);

			} else {
				list = reactionDesignService.getAppAllPageable(keyword);

			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionDesignService.getAppUserList(userId);

			} else {
				list = reactionDesignService.getAppAll();
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
		resultVo.setErrMsg("find success");
		resultVo.setResultData(pageEntity);
		return resultVo;

	}
	/**
	 * @Description App根据登录人关键字获取组实验对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionTeamApp/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getReactionTeam(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		// 获取当前用户
		ResultVo resultVo = new ResultVo();
		List<Reaction> list = new ArrayList<Reaction>();
		
		List<Reaction> reactions = new ArrayList<Reaction>();
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionService.getProcessyByUserIdAndKeyword(keyword, userId);
				
			} else {
				list = reactionService.getProcessByKeyword(keyword);
				
			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionService.getProcessyByUserIdNoKeyword(userId);
				
			} else {
				list = reactionService.getProcessNoKeyword();
			}
		}
		
		if(list!=null&&list.size()>0){
			for (Reaction reaction : list) {
				List<ReactionProcess> reactionProcesses=reaction.getReactionProcesses();
				if(reactionProcesses!=null&&reactionProcesses.size()>0){
					reactions.add(reaction);
				}
			}
		int total = reactions.size();
		double c = (((double) total) / pageSize);
		int d = (int) Math.ceil(c);
		PageEntity pageEntity = new PageEntity();
		pageEntity.setPageNum(page);
		pageEntity.setPageSize(pageSize);
		pageEntity.setPages(d);
		pageEntity.setTotal(total);
		int num = reactions.size() > page * pageSize ? page * pageSize : reactions.size();
		if (page <= d) {
			pageEntity.setList(reactions.subList(0, num));
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(pageEntity);
		return resultVo;
	}else{
		resultVo.setErrCode(1);
		resultVo.setErrMsg("无数据");
		resultVo.setResultData(null);
		return resultVo;
	     }
	}
	/**
	 * @Description App根据登录人关键字获取单一实验对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionPrivateApp/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getReactionPrivate(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		// 获取当前用户
		ResultVo resultVo = new ResultVo();
		List<Reaction> list = new ArrayList<Reaction>();
		
		List<Reaction> reactions = new ArrayList<Reaction>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionService.getPrivateProcessyByUserIdAndKeyword(keyword, userId);
				
			} else {
				list = reactionService.getPrivateProcessByKeyword(keyword);
				
			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionService.getPrivateProcessyByUserIdNoKeyword(userId);
				
			} else {
				list = reactionService.getPrivateProcessNoKeyword();
			}
		}
		
		if(list!=null&&list.size()>0){
			for (Reaction reaction : list) {
				List<ReactionProcess> reactionProcesses=reaction.getReactionProcesses();
				if(reactionProcesses!=null&&reactionProcesses.size()>0){
					reactions.add(reaction);
				}
			}
			int total = reactions.size();
			double c = (((double) total) / pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			int num = reactions.size() > page * pageSize ? page * pageSize : reactions.size();
			if (page <= d) {
				pageEntity.setList(reactions.subList(0, num));
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
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
	 * @Description 根据关键字获取团队实验对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionAppTeamSingleDesign/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
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
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionDesignService.getAppUserSingleByKeyword(keyword,
						userId,0);

			} else {
				list = reactionDesignService.getAppAllSinglePageable(keyword,0);

			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionDesignService.getAppUserSingleList(userId,0);

			} else {
				list = reactionDesignService.getAppAllSingle(0);
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
		resultVo.setErrMsg("find success");
		resultVo.setResultData(pageEntity);
		return resultVo;

	}

	/**
	 * @Description 关键字查询对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/getAppTeamReportByKeyword/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAppTeamReportByKeyword(
			@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {

		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		List<Reaction> list = new ArrayList<Reaction>();
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {

			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionService.getUserListReportByKeyword(keyword,
						userId);

			} else {

				list = reactionService.getReportByKeyword(keyword);
			}
		} else {

			if (!roleName.equals("ROLE_TEAM")) {
				list = reactionService.getReportUserList(userId);
			} else {

				list = reactionService.getAllReport();
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
		resultVo.setErrMsg("find success");
		resultVo.setResultData(pageEntity);
		return resultVo;

	}

	/**
	 * @Description 关键字我的报告列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/getAppMyReportByKeyword/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAppMyReportByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {

		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();

		List<Reaction> list = new ArrayList<Reaction>();

		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {

			list = reactionService.getMyReportByKeyword(keyword, userId);
		} else {
			list = reactionService.getMyReportUserList(userId);
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
		resultVo.setErrMsg("find success");
		resultVo.setResultData(pageEntity);
		return resultVo;
	}

	@RequestMapping(value = "/reaction/getAppDesignData", method = RequestMethod.GET)
	public ResultVo getAppDesignData() {
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();

		List<Reaction> teamReports = new ArrayList<Reaction>();
		List<ReactionDesign> teamDesigns = new ArrayList<ReactionDesign>();
		List<ReactionDesign> teamSingleDesigns = new ArrayList<ReactionDesign>();

		Integer myDesignsNum = 0;
		Integer mySingleDesignsNum = 0;
		Integer teamDesignsNum = 0;
		Integer teamSingleDesignsNum = 0;

		// 我的实验报告
		List<Reaction> myReports = reactionService.getMyReportUserList(userId);
		Integer myReportsNum = myReports.size();

		// 团队实验报告
		if (!roleName.equals("ROLE_TEAM")) {
			teamReports = reactionService.getReportUserList(userId);
		} else {
			teamReports = reactionService.getAllReport();
		}
		Integer teamReportsNum = teamReports.size();

		// 我的分组实验
		List<ReactionDesign> myDesigns = reactionDesignService
				.getAppMyList(userId);
		if (myDesigns.size() > 0) {
			myDesignsNum = myDesigns.size();
		}

		// 我的单一实验
		List<ReactionDesign> mySingleDesigns = reactionDesignService
				.getAppMySingleList(userId,0);
		if (mySingleDesigns.size() > 0) {
			mySingleDesignsNum = mySingleDesigns.size();
		}

		Integer MyDesignNum = myDesignsNum + mySingleDesignsNum;

		// 团队分组实验
		if (!roleName.equals("ROLE_TEAM")) {
			teamDesigns = reactionDesignService.getAppUserList(userId);

		} else {
			teamDesigns = reactionDesignService.getAppAll();
		}

		if (teamDesigns.size() > 0) {
			teamDesignsNum = teamDesigns.size();
		}

		// 团队单一实验
		if (!roleName.equals("ROLE_TEAM")) {
			teamSingleDesigns = reactionDesignService.getAppUserSingleList(userId,0);

		} else {
			teamSingleDesigns = reactionDesignService.getAppAllSingle(0);
		}

		if (teamSingleDesigns.size() > 0) {
			teamSingleDesignsNum = teamSingleDesigns.size();
		}

		Integer teamDesings = teamDesignsNum + teamSingleDesignsNum;

		Map<String, Integer> designMaps = new HashMap<String, Integer>();
		designMaps.put("myReportsNum", myReportsNum);
		designMaps.put("teamReportsNum", teamReportsNum);
		designMaps.put("MyDesignNum", MyDesignNum);
		designMaps.put("teamDesings", teamDesings);

		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(designMaps);
		return resultVo;
	}

	/**
	 * @Description 单个保存非项目试验对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */

	@RequestMapping(value = "/reactionDesign/AppAddOrUpdate", method = RequestMethod.POST)
	public ResultVo appAddOrUpdate(@RequestBody ReactionDesign reactionDesign) {

		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer id = reactionDesign.getReactionDesignId();

		if (id == null) {
			reactionDesign.setType(0);
			reactionDesign.setSysUser(sysUser);
			reactionDesign.setExecute("true");
			reactionDesignService.saveNoProject(reactionDesign);
			
			Reaction reaction = new Reaction();
			reaction.setReactionName(reactionDesign.getReactionGroupName());
			reaction.setStartTime(reactionDesign.getPreStartTime());
			reaction.setEndTime(reactionDesign.getPreEndTime());
			reaction.setReactionStatus(reactionStatusService.getByType(1).get(0));
			reaction.setReactionDesign(reactionDesign);
			reaction.setSysUser(sysUser);
			reactionService.save(reaction);
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save successe");
			resultVo.setResultData(reaction.getReactionId());
			return resultVo;
		} else {
			reactionDesign.setSysUser(sysUser);
			reactionDesignService.updateNoProject(reactionDesign);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe");
			resultVo.setResultData(reactionDesign.getReactionDesignId());
			return resultVo;
		}
	}
	
	
	/**
	 * @Description 单个实验设计详情
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/getByReactionId/{id}", method = RequestMethod.GET)
	public ResultVo getAppSingleDesignById(
			@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List<ExecuteChemicalGroup> executeChemicalGroupList = new ArrayList<ExecuteChemicalGroup>();
		List<ExecuteSolution> executeSolutionsList = new ArrayList<ExecuteSolution>();
		Reaction reaction = reactionService.getByReactionId(id);
		
		if(reaction!=null){
		
			List<Analytics> analyticses = analyticService.getByReactionId(id);
			
			List<Integer> processIds = reactionProcessService.getByReactionId(id);
			for (Integer processId : processIds) {
				
				//添加实验用到原料
				List<ExecuteChemicalGroup> executeChemicalGroups = executeChemicalGroupService.getByProcessId(processId);
				if(executeChemicalGroups!=null && executeChemicalGroups.size()>0){
					for (ExecuteChemicalGroup executeChemicalGroup : executeChemicalGroups) {
						if(executeChemicalGroup.getInventoryGroups()!=null){
							InventoryGroups inventoryGroups = inventoryGroupsService.getById(executeChemicalGroup.getInventoryGroups().getGroupId());
							if(inventoryGroups!=null){
								Measurement measure = inventoryGroups.getMeasurement();
								if(measure!=null){
									Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
									inventoryGroups.setMeasurement(measureMent);
								}
								executeChemicalGroup.setInventoryGroups(inventoryGroups);
							}
						}
						
					}
					executeChemicalGroupList.addAll(executeChemicalGroups);
				}
				
				//添加实验用到溶液
				List<ExecuteSolution> executeSolutions = executeSolutionService.getById(processId);
				if(executeSolutions!=null && executeSolutions.size()>0){
					executeSolutionsList.addAll(executeSolutions);
				}
			}
			
			reaction.setAnalyticses(analyticses);
			reaction.setExecuteChemicalGroups(executeChemicalGroupList);
			reaction.setExecuteSolutions(executeSolutionsList);
		}
		
		
		ReactionVo reactionVo = new ReactionVo();
		List<Map<String,Object>> knowledges = reactionService.getArticleById(id);
		if(knowledges !=null && knowledges.size()>0){
			for (Map<String, Object> map : knowledges) {
				Integer knowledgeId =(Integer)map.get("knowledgeId");
				Integer classifyId = (Integer)map.get("knowledge_classify_id");
				KnowledgeAcc knowledgeAcc = knowledgeAccDaoService.getKnowledgeAcc(knowledgeId, classifyId);
				if(knowledgeAcc !=null){
					String uploadFiles = knowledgeAcc.getUploadFiles();
					Integer knowledgeAccId = knowledgeAcc.getKnowledgeAccId();
					Integer pdfCount = knowledgeAcc.getPdfCount();
					map.put("filename", uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));
//					map.put("pdfUrlPath",urlPath+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));
					 
				          if(knowledgeAcc.getKnowledgeClassify().getKnowledgeClassifyId()==1){
				            JournalArticle journalArticle=journalArticleService.getById(knowledgeAcc.getKnowledgeId());
				            if(journalArticle.getSource()==0){
				              map.put("pdfUrlPath", pdfUrlPath+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));  
				            }else{
				              map.put("pdfUrlPath", agencyPdf+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));  
				            }
				          
				          }else if(knowledgeAcc.getKnowledgeClassify().getKnowledgeClassifyId()==4){
				            Patent patent=patentService.getById(knowledgeAcc.getKnowledgeId());
				            if(patent.getSource()==0){
				              map.put("pdfUrlPath", pdfUrlPath+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));  
				            }else{
				              map.put("pdfUrlPath", agencyPdf+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));  
				            }
				          }else{
				            map.put("pdfUrlPath", pdfUrlPath+uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));
				          }
					map.put("filePath", uploadFiles);
					map.put("knowledgeAccId", knowledgeAccId);
					map.put("pdfCount", pdfCount);
				}
			}
		}
		
			reactionVo.setKnowledges(knowledges);
			reactionVo.setReaction(reaction);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(reactionVo);
			return resultVo;

	}
	/**
	 * @Description APP根据实验ID获取该实验的试验步骤以及参数
	 * @author shg
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/getProcessByReactionId/{id}", method = RequestMethod.GET)
	public ResultVo getProcessByReactionId(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		
		Reaction reaction = reactionService.getProcessByReactionId(id);
		
		if(reaction!=null){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(reaction);
			return resultVo;
		}else {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("无数据");
			resultVo.setResultData(null);
			return resultVo;
		}
	}
	
	/**
	 * @Description 获取当前登陆用户全部实验列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reactionDesign/getAllSingleNames", method = RequestMethod.GET)
	public ResultVo getAllSingleNames() {
		ResultVo resultVo = new ResultVo();
		List<String> names = reactionDesignService.getSingleNames();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(names);
		return resultVo;
	}
	
	
	/**
	 * @Description 获取试验下原料列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/getChemicals/{page}/{pageSize}/{id}", method = RequestMethod.GET)
	public ResultVo getChemicals(
			@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List<ExecuteChemicalGroup> executeChemicalGroupList = new ArrayList<ExecuteChemicalGroup>();
		
		List<Integer> processIds = reactionProcessService.getByReactionId(id);
		for (Integer processId : processIds) {
			
			//添加实验用到原料
			List<ExecuteChemicalGroup> executeChemicalGroups = executeChemicalGroupService.getByProcessId(processId);
			if(executeChemicalGroups!=null && executeChemicalGroups.size()>0){
				for (ExecuteChemicalGroup executeChemicalGroup : executeChemicalGroups) {
					InventoryGroups inventoryGroups = inventoryGroupsService.getById(executeChemicalGroup.getInventoryGroups().getGroupId());
					Measurement measure = inventoryGroups.getMeasurement();
					if(measure!=null){
						Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
						inventoryGroups.setMeasurement(measureMent);
					}
					executeChemicalGroup.setInventoryGroups(inventoryGroups);
					
				}
				executeChemicalGroupList.addAll(executeChemicalGroups);
			}
		}
		
		int total = executeChemicalGroupList.size();
		double c = (((double) total) / pageSize);
		int d = (int) Math.ceil(c);
		PageEntity pageEntity = new PageEntity();
		pageEntity.setPageNum(page);
		pageEntity.setPageSize(pageSize);
		pageEntity.setPages(d);
		pageEntity.setTotal(total);
		int num = executeChemicalGroupList.size() > page * pageSize ? page * pageSize : executeChemicalGroupList.size();
		if (page <= d) {
			pageEntity.setList(executeChemicalGroupList.subList(0, num));
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(pageEntity);
		return resultVo;
		
	}
	
	
	/**
	 * @Description 获取试验下溶液列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/getSolutions/{page}/{pageSize}/{id}", method = RequestMethod.GET)
	public ResultVo getSolutions(
			@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List<ExecuteSolution> executeSolutionsList = new ArrayList<ExecuteSolution>();
		//添加实验用到溶液
		List<Integer> processIds = reactionProcessService.getByReactionId(id);
		for (Integer processId : processIds) {

			List<ExecuteSolution> executeSolutions = executeSolutionService.getById(processId);
			if(executeSolutions!=null && executeSolutions.size()>0){
				executeSolutionsList.addAll(executeSolutions);
			}
		}
		
		int total = executeSolutionsList.size();
		double c = (((double) total) / pageSize);
		int d = (int) Math.ceil(c);
		PageEntity pageEntity = new PageEntity();
		pageEntity.setPageNum(page);
		pageEntity.setPageSize(pageSize);
		pageEntity.setPages(d);
		pageEntity.setTotal(total);
		int num = executeSolutionsList.size() > page * pageSize ? page * pageSize : executeSolutionsList.size();
		if (page <= d) {
			pageEntity.setList(executeSolutionsList.subList(0, num));
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(pageEntity);
		return resultVo;
		
	}
}
