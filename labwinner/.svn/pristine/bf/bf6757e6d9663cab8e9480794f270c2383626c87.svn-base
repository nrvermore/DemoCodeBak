package com.labwinner.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.dao.KnowledgeAccDao;
import com.labwinner.domain.Analytics;
import com.labwinner.domain.Calendars;
import com.labwinner.domain.DesignDosage;
import com.labwinner.domain.DesignTechnology;
import com.labwinner.domain.DesignTechnologyDosage;
import com.labwinner.domain.DesignTechnologyProcess;
import com.labwinner.domain.DesignTechnologyRelation;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.ExecuteChemical;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.KnowledgeClassify;
import com.labwinner.domain.KnowledgeClassifyReacRela;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.MsgType;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectNumber;
import com.labwinner.domain.Prototype;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionDesignChemical;
import com.labwinner.domain.ReactionDesignParameter;
import com.labwinner.domain.ReactionDesignProcess;
import com.labwinner.domain.ReactionDesignSolution;
import com.labwinner.domain.ReactionDevice;
import com.labwinner.domain.ReactionExecuteParameter;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.domain.ReactionRecord;
import com.labwinner.domain.ReactionStatus;
import com.labwinner.domain.ReactionTest;
import com.labwinner.domain.SignIn;
import com.labwinner.domain.SolutionDesignDosage;
import com.labwinner.domain.SysUser;
import com.labwinner.domain.TestDevice;
import com.labwinner.jpush.JPushData;
import com.labwinner.jpush.Jpush;
import com.labwinner.service.AnalyticService;
import com.labwinner.service.AnalyticalAttachmentService;
import com.labwinner.service.AnalyticsDeviceService;
import com.labwinner.service.CalendarsService;
import com.labwinner.service.DesignDosageService;
import com.labwinner.service.DesignTechnologyDosageService;
import com.labwinner.service.DesignTechnologyProcessService;
import com.labwinner.service.DesignTechnologyRelationService;
import com.labwinner.service.DesignTechnologyService;
import com.labwinner.service.DeviceAppointmentService;
import com.labwinner.service.DeviceLocationService;
import com.labwinner.service.ExecuteChemicalService;
import com.labwinner.service.ExecuteParemeterService;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.service.KnowledgeAccService;
import com.labwinner.service.KnowledgeClassifyService;
import com.labwinner.service.KnowledgeReacRelaService;
import com.labwinner.service.MessageRecipientsService;
import com.labwinner.service.MessageService;
import com.labwinner.service.NoteAssistantService;
import com.labwinner.service.NoteService;
import com.labwinner.service.ProjectBasicInfoService;
import com.labwinner.service.ProjectNumberService;
import com.labwinner.service.ReactionDesignChemicalService;
import com.labwinner.service.ReactionDesignService;
import com.labwinner.service.ReactionDesignSolutionService;
import com.labwinner.service.ReactionImageService;
import com.labwinner.service.ReactionProcessService;
import com.labwinner.service.ReactionRecordService;
import com.labwinner.service.ReactionService;
import com.labwinner.service.ReactionStatusService;
import com.labwinner.service.ReactionTestService;
import com.labwinner.service.SolutionDesignDosageService;
import com.labwinner.service.SysUserService;
import com.labwinner.service.TestAttachementService;
import com.labwinner.service.TestDeviceService;
import com.labwinner.vo.CopyReactionVo;
import com.labwinner.vo.ReactionFirstPageVo;
import com.labwinner.vo.ReactionVo;

@RestController
public class ReactionController {

	private static Logger logger = LoggerFactory
			.getLogger(ReactionController.class);

	@Autowired
	private ReactionService reactionService;

	@Autowired
	private AnalyticService analyticService;

	@Autowired
	private AnalyticsDeviceService analyticsDeviceService;

	@Autowired
	private AnalyticalAttachmentService analyticalAttService;

	@Autowired
	private ReactionProcessService reactionProcessService;

	@Autowired
	private NoteService noteService;

	@Autowired
	ReactionImageService reactionImageService;

	@Autowired
	NoteAssistantService noteAssistantService;

	@Autowired
	private DeviceAppointmentService deviceAppointmentService;

	@Autowired
	KnowledgeReacRelaService KnowledgeClassifyReacRelaService;

	@Autowired
	KnowledgeClassifyService knowledgeClassifyService;

	@Autowired
	ProjectBasicInfoService projectBasicInfoService;

	@Autowired
	ReactionStatusService reactionStatusService;

	@Autowired
	ReactionRecordService reactionRecordService;

	@Autowired
	ReactionDesignService reactionDesignService;

	@Autowired
	ProjectNumberService projectNumberService;

	@Autowired
	ExecuteChemicalService executeChemicalService;

	@Autowired
	ExecuteParemeterService executeParemeterService;

	@Autowired
	ReactionTestService reactionTestService;

	@Autowired
	TestDeviceService testDeviceService;

	@Autowired
	TestAttachementService testAttachementService;
	
	@Autowired
	private KnowledgeAccService knowledgeAccDaoService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private InventoryLocationService inventoryLocService;
	
	@Autowired
	private DeviceLocationService deviceLocationService;
	
	@Autowired
	private DesignTechnologyService designTechnologyService;
	
	@Autowired
	private DesignTechnologyRelationService designTechnologyRelationService;
	
	@Autowired
	private DesignTechnologyDosageService designTechnologyDosageService;
	
	@Autowired
	private DesignTechnologyProcessService designTechnologyProcessService;
	
	@Autowired
	private ReactionDesignSolutionService reactionDesignSolutionService;
	
	@Autowired
	private SolutionDesignDosageService solutionDesignDosageService;
	
	@Autowired
	private ReactionDesignChemicalService reactionDesignChemicalService;
	
	@Autowired
	private DesignDosageService designDosageService;
	
	
	
	@Value("${web.qr-path}")
	String qrPath;
	@Value("${web.qrUrl-path}")
	private String urlPath;
	


	/**
	 * @Description 对象详情
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		List<Integer> reactionIds1 = new ArrayList<Integer>();
		
		if (!roleName.equals("ROLE_TEAM")) {
			List<Reaction> list1 = reactionService.getUserList(userId);
			if(list1!=null && list1.size()>0){
				for (Reaction reaction : list1) {
					reactionIds1.add(reaction.getReactionId());
				}
				
			}
			if(!reactionIds1.contains(id)){
				resultVo.setErrCode(4);
				resultVo.setErrMsg("您无权访问");
				return resultVo;
			}
		}
		
		
			ReactionVo reactionVo = new ReactionVo();
			Reaction reaction = reactionService.getById(id);
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
						map.put("filePath", uploadFiles);
						map.put("knowledgeAccId", knowledgeAccId);
						map.put("pdfCount", pdfCount);
					}
				}
			}
			if(reaction!=null){
				List<ReactionProcess> reactionProcesses = reaction.getReactionProcesses();
				if(reactionProcesses!=null && reactionProcesses.size()>0){
					for (ReactionProcess reactionProcess : reactionProcesses) {
						
						List<ExecuteChemical> executeChemicals = reactionProcess.getExecuteChemicals();
						if(executeChemicals!=null && executeChemicals.size()>0){
							for (ExecuteChemical executeChemical : executeChemicals) {
								InventoryLocation location = executeChemical.getInventory().getInventoryLocation();
								if(location!=null && location.getPid()!=0){
									String pname = getInventoryPname(location.getCid());
									location.setParentName(pname);
								}
							}
						}
						
						List<ReactionDevice> testDevices = reactionProcess.getReactionDevices();
						
						if(testDevices!=null && testDevices.size()>0){
							for (ReactionDevice testDevice : testDevices) {
								DeviceLocation location = testDevice.getDevice().getDeviceLocation();
								if(location!=null && !"0".equals(location.getDeviceLocaPid())){
									String pname = getDevicePname(location.getDeviceLocaId());
									location.setParentName(pname);
								}
							}
						}
						
						List<Prototype> protoTypes = reactionProcess.getPrototypes();
						if(protoTypes!=null && protoTypes.size()>0){
							for (Prototype prototype : protoTypes) {
								prototype.setQrName(urlPath+prototype.getQrName());
								InventoryLocation location = prototype.getInventoryLocation();
								if(location!=null && location.getPid()!=0){
									String pname = getInventoryPname(location.getCid());
									location.setParentName(pname);
								}
							}
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
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find fail");
			return resultVo;
			
	}
	
	/**
	 * @Description 根据实验id获取实验步骤以及对应参数对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/getProcessByreactionId/{id}", method = RequestMethod.GET)
	public ResultVo getProcessByreactionId(@PathVariable("id") Integer id) {
		
		ResultVo resultVo = new ResultVo();
		Reaction reaction = reactionService.getProcessByreactionId(id);
		
		if(reaction!=null){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(reaction);
			return resultVo;
		}else {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find fail");
			return resultVo;
		}
	}

	/**
	 * @Description 随手记获取实验阶段对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/reactionProcessList/{id}", method = RequestMethod.GET)
	public ResultVo getProcessList(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		Reaction reaction = reactionService.getById(id);
		if (null == reaction) {
			String message = "reaction is null";
			logger.error(message);
		}
		List<ReactionProcess> reactionProcesses = new ArrayList<ReactionProcess>(
				reaction.getReactionProcesses());
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(reactionProcesses);
		return resultVo;
		
	}


	/**
	 * @Description 获取当前登陆用户全部实验列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/getByUserAll", method = RequestMethod.GET)
	public ResultVo getByUserAll() {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		List<Reaction> reactionList = reactionService.getByUserAll(userId);
		if (null == reactionList) {
			String message = "reaction is null";
			logger.error(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(reactionList);
		return resultVo;
	}
	
	
	/**
	 * @Description 关键字查询对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/getByKeyword/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {

		ResultVo resultVo = new ResultVo();
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
				List<Reaction> list = reactionService.getUserListByKeyword(keyword,userId);
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			} else {
				List<Reaction> list = reactionService.getByKeyword(keyword);
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				List<Reaction> list = reactionService.getUserList(userId);
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			} else {
				List<Reaction> list = reactionService.getAll();
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;
			}
		}
		
		
	}
	
	
	/**
	 * @Description 关键字查询对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/getAppByKeyword/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAppByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {

		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				List<Reaction> list = reactionService.getUserListByKeyword(keyword,userId);
				
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

			} else {
				List<Reaction> list = reactionService.getByKeyword(keyword);
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
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				List<Reaction> list = reactionService.getUserList(userId);
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

			} else {
				List<Reaction> list = reactionService.getAll();
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
		}
		
		
	}

	/**
	 * @Description 修改实验
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody Reaction reaction) {
		ResultVo resultVo = new ResultVo();
		
		if(reaction.getReactionStatus()!=null &&(reaction.getReactionStatus().getReactionStatusId()-3==0 ||
				reaction.getReactionStatus().getReactionStatusId()-4==0)){
			reaction.setReportDate(new Date());
		}
		reactionService.update(reaction);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}
	
	
	
	/**
	 * @Description 解封实验
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/unLockReaction/{id}", method = RequestMethod.GET)
	public ResultVo unLockReaction(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		Reaction reaction = new Reaction();
		reaction.setReactionId(id);
		reactionService.unLockReaction(reaction);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}


	/**
	 * @Description 关键字查询对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/getReportByKeyword/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getReportByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {

		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			if (!roleName.equals("ROLE_TEAM")) {
				List<Reaction> list = reactionService.getUserListReportByKeyword(keyword,userId);
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			} else {
				if (page != null && pageSize != null) {
					PageHelper.startPage(page, pageSize);
				}
				List<Reaction> list = reactionService.getReportByKeyword(keyword);
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			}
		} else {
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			if (!roleName.equals("ROLE_TEAM")) {
				List<Reaction> list = reactionService.getReportUserList(userId);
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			} else {
				if (page != null && pageSize != null) {
					PageHelper.startPage(page, pageSize);
				}
				List<Reaction> list = reactionService.getAllReport();
				if (list != null) {
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;
			}
		}
		
		
	}
	/**
	 * @Description 删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		try {
			// 删除实验
			reactionService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete success");
			return resultVo;
		} catch (Exception e) {
			logger.error("e", e);
			e.printStackTrace();
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("delete fail");
		return resultVo;
	}
	
	/**
	 * @Description 删除实验知识对应关系
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/reaction/{reactionId}/{classfiyId}/{knowledgeId}", method = RequestMethod.DELETE)
	public ResultVo deleteReaction(@PathVariable("reactionId") Integer reactionId,
					@PathVariable("classfiyId") Integer classfiyId,
					@PathVariable("knowledgeId") Integer knowledgeId) {

		ResultVo resultVo = new ResultVo();
		try {
			// 删除实验
			reactionService.deleteRelation(reactionId,classfiyId,knowledgeId);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete success");
			return resultVo;
		} catch (Exception e) {
			logger.error("e", e);
			e.printStackTrace();
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("delete fail");
		return resultVo;
	}


	/**
	 * @Description 生成实验
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/createReaction/{id}", method = RequestMethod.GET)
	public ResultVo createReactions(@PathVariable("id") Integer id) {
//		ResultVo resultVo = new ResultVo();
//		ReactionDesign reactionDesign = reactionDesignService.getById(id);
//		
//		if (null != reactionDesign) {
//			ReactionDesign reactionDesign2 = reactionDesignService.getChemicals(id);
//			reactionDesign.setReactionDesignChemicals(reactionDesign2.getReactionDesignChemicals());
//			createReaction(reactionDesign);
//			resultVo.setErrCode(0);
//			resultVo.setErrMsg("save success");
//			return resultVo;
//		} else {
//			resultVo.setErrCode(2);
//			resultVo.setErrMsg("reactionDesign is null");
//			return resultVo;
//		}
		
		ResultVo resultVo = new ResultVo();
		ReactionDesign reactionDesign = reactionDesignService.getReactionDesignById(id);
		ReactionDesign reactionDesign4 = reactionDesignService.getDesignTechnologyRelationById(id);
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
		
		if (null != reactionDesign) {
			createReactions(reactionDesign);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			return resultVo;
		} else {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("reactionDesign is null");
			return resultVo;
		}
	}

//	private void createReaction(ReactionDesign reactionDesign) {
//
//		// 得到实验记录
//		List<ReactionRecord> recordList = reactionDesign.getReactionRecords();
//
//		ProjectBasicInfo project = reactionDesign.getProjectBasicInfo();
//		ProjectNumber projectNumber = reactionDesign.getProjectNumber();
//		List<ReactionDesignProcess> designProcessList = reactionDesign
//				.getReactionDesignProcesses();
//		List<ReactionDesignChemical> designChemicalList = reactionDesign
//				.getReactionDesignChemicals();
//
//		// 生成实验基本信息
//		if (recordList != null && recordList.size() > 0) {
//
//			for (int i = 0; i < recordList.size(); i++) {
//				Reaction reaction = new Reaction();
//				reaction.setReactionName(reactionDesign.getReactionGroupName()+"-"+recordList.get(i).getReactionName());
//				reaction.setReactionDesign(reactionDesign);
//				reaction.setReactionStatus(reactionStatusService.getByType(1)
//						.get(0));
//				reaction.setProjectBasicInfo(project);
//				reaction.setProjectNumber(projectNumber);
//				reaction.setStartTime(reactionDesign.getPreStartTime());
//				reaction.setEndTime(reactionDesign.getPreEndTime());
//				reactionService.save(reaction);
//
//				if (designProcessList != null && designProcessList.size() > 0) {
//
//					for (int j = 0; j < designProcessList.size(); j++) {
//						// 生成实验记录
//						ReactionProcess reactionProcess = new ReactionProcess();
//						reactionProcess.setReaction(reaction);
//						reactionProcess.setProcess(designProcessList.get(j)
//								.getProcess());
//						reactionProcess.setRemark(designProcessList.get(j).getRemark());
//						reactionProcess.setProcessName(designProcessList.get(j)
//								.getProcessName());
//						if (j == 0) {
//							reactionProcess.setStartTime(reactionDesign
//									.getPreStartTime());
//							reactionProcess
//									.setReactionStatus(reactionStatusService
//											.getByType(2).get(4));
//
//						} else {
//							reactionProcess
//									.setReactionStatus(reactionStatusService
//											.getByType(2).get(0));
//						}
//						reactionProcessService.save(reactionProcess);
//
//						// 实验执行参数
//						List<ReactionDesignParameter> executeParameterList = designProcessList
//								.get(j).getReactionDesignParameters();
//						if (executeParameterList != null
//								&& executeParameterList.size() > 0) {
//
//							for (int l = 0; l < executeParameterList.size(); l++) {
//								ReactionExecuteParameter executeParameter = new ReactionExecuteParameter();
//								executeParameter
//										.setExecuteParameterDosage(executeParameterList
//												.get(l)
//												.getDesignParameterDosage());
//								executeParameter
//										.setMeasurement(executeParameterList
//												.get(l).getMeasurement());
//								executeParameter
//										.setReactionParameter(executeParameterList
//												.get(l).getReactionParameter());
//								executeParameter
//										.setReactionProcess(reactionProcess);
//								executeParemeterService.save(executeParameter);
//							}
//						}
//
//						// 实验执行原料
//
//						if (designChemicalList !=null && designChemicalList.size()>0) {
//						
//							for (int k = 0; k < designChemicalList.size(); k++) {
//								ExecuteChemical executeChemical = new ExecuteChemical();
//								List<DesignDosage> designDosageList = designChemicalList
//										.get(k).getDesignDosages();
//								executeChemical.setInventoryGroups(designChemicalList
//										.get(k).getInventoryGroups());
//								executeChemical.setReactionProcess(reactionProcess);
//								// TODO
//								executeChemical.setChemicalDosage(designDosageList
//										.get(i).getChemicalDosage());
//								executeChemical.setMeasurement(designDosageList
//										.get(i).getMeasurement());
//								executeChemicalService.save(executeChemical);
//							}
//						}
//
//					}
//				}
//				recordList.get(i).setExecute("true");
//				reactionRecordService.updateRecord(recordList.get(i));
//			}
//		}
//		reactionDesign.setExecute("true");
//		reactionDesignService.updateExecute(reactionDesign);
//	}
	
	  public String getInventoryPname( Integer id) {
		    try {
		      String ss="";
		    loop:for(int i=0;i<10;i++){
		      InventoryLocation inventoryLocation =  inventoryLocService.getById(id);
		      if(inventoryLocation.getPid()!=null&&inventoryLocation.getPid()!=0){
		        if(i==0){
		          ss="";
		        }else if(i==1){
		          ss=inventoryLocation.getLabel();
		        }else{
		          ss=inventoryLocation.getLabel()+">"+ss;
		        }
		        id=inventoryLocation.getPid();
		      }else{
		        if(!"".equals(ss)){
		          ss=inventoryLocation.getLabel()+">"+ss;
		        }else{
		          ss=inventoryLocation.getLabel();  
		        }
		        break loop;
		      }
		      }
		      return ss;
		    } catch (Exception e) {
		    	return null;
		      // TODO: handle exception
		    }
		  }
	  
	  public String getDevicePname( Integer id) {
		    try {
		      String ss="";
		    loop:for(int i=0;i<10;i++){
		      DeviceLocation deviceLocation =  deviceLocationService.getById(id);
		      if(deviceLocation.getDeviceLocaPid()!=null&&!"0".equals(deviceLocation.getDeviceLocaPid())){
		        if(i==0){
		          ss="";
		        }else if(i==1){
		          ss=deviceLocation.getLabel();
		        }else{
		          ss=deviceLocation.getLabel()+">"+ss;
		        }
		        id= Integer.valueOf(deviceLocation.getDeviceLocaPid());
		      }else{
		        if(!"".equals(ss)){
		          ss=deviceLocation.getLabel()+">"+ss;
		        }else{
		          ss=deviceLocation.getLabel();  
		        }
		        break loop;
		      }
		      }
		      return ss;
		    } catch (Exception e) {
		    	return null;
		      // TODO: handle exception
		    }
		  }
	 
	//----------------------------------实验新功能--------------------------------
	  
	  /**
		 * @Description 关键字我的报告列表
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:49:52
		 */
		@RequestMapping(value = "/reaction/getMyReportByKeyword/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
		public ResultVo getMyReportByKeyword(@PathVariable("page") Integer page,
				@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {

			ResultVo resultVo = new ResultVo();
			// 获取当前用户
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer userId = sysUser.getUserId();
			String roleName = sysUser.getSysRole().getRoleName();
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				if (page != null && pageSize != null) {
					PageHelper.startPage(page, pageSize);
				}
				
					List<Reaction> list = reactionService.getMyReportByKeyword(keyword,userId);
					if (list != null) {
						resultVo.setErrCode(0);
						resultVo.setErrMsg("find success");
						resultVo.setResultData(new PageInfo(list));
						return resultVo;
					}
					resultVo.setErrCode(2);
					resultVo.setErrMsg("find is null");
					return resultVo;
			} else {
				if (page != null && pageSize != null) {
					PageHelper.startPage(page, pageSize);
				}
				
					List<Reaction> list = reactionService.getMyReportUserList(userId);
					if (list != null) {
						resultVo.setErrCode(0);
						resultVo.setErrMsg("find success");
						resultVo.setResultData(new PageInfo(list));
						return resultVo;
					}
					resultVo.setErrCode(2);
					resultVo.setErrMsg("find is null");
					return resultVo;
			}
		}
		
		 /**
		 * @Description 实验首页
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:49:52
		 */
		@RequestMapping(value = "/reaction/getFirstReaction", method = RequestMethod.GET)
		public ResultVo getMyReportByKeyword(){
			
			ResultVo resultVo = new ResultVo();
			
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer userId = sysUser.getUserId();
			String roleName = sysUser.getSysRole().getRoleName();
			List<ReactionDesign> teamReactionlist =new ArrayList<ReactionDesign>();
			List<Reaction> teamlist = new ArrayList<Reaction>();
			
			//首页我的实验
			List<ReactionDesign> myDesignlist = reactionDesignService.getFirstMyDesigns(userId);
			for (ReactionDesign reactionDesign : myDesignlist) {
				String name=reactionDesign.getReactionGroupName();
				if(reactionDesign.getType()==0){
					List<Reaction> myreactions = reactionService.getByDesignId(reactionDesign.getReactionDesignId());
					reactionDesign.setReactions(myreactions);
				}
				
				if("".equals(name) || name==null){
					reactionDesign.setReactionGroupName("系统暂存的试验");
				}
			}
			
			//首页团队实验
			if (!roleName.equals("ROLE_TEAM")) {
				teamReactionlist = reactionDesignService.getFirstTeamUserDesign(userId);
			} else {
				teamReactionlist = reactionDesignService.getFirstTeamDesign();
			}
			for (ReactionDesign teamreactionDesign : teamReactionlist) {
				String name=teamreactionDesign.getReactionGroupName();
				if(teamreactionDesign.getType()==0){
					List<Reaction> myreactions = reactionService.getByDesignId(teamreactionDesign.getReactionDesignId());
					teamreactionDesign.setReactions(myreactions);
				}
				if("".equals(name) || name==null){
					teamreactionDesign.setReactionGroupName("系统暂存的试验");
				}
			}
			
			//首页我的报告
			List<Reaction> list = reactionService.getFirstMyReportUserList(userId);
			
			//首页团队报告
			if (!roleName.equals("ROLE_TEAM")) {
				teamlist = reactionService.getFirstReportUserList(userId);
			} else {
				teamlist = reactionService.getFirstAllReport();
			}
			ReactionFirstPageVo firstPageData = new ReactionFirstPageVo();
			firstPageData.setMyDesignlist(myDesignlist);
			firstPageData.setTeamReactionlist(teamReactionlist);
			firstPageData.setMyReprotlist(list);
			firstPageData.setTeamReprotlist(teamlist);
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find sucess");
			resultVo.setResultData(firstPageData);
			return resultVo;
		}
		
		
		/**
		 * @Description 批量删除对象
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:49:52
		 */
		@RequestMapping(value = "/reaction/deleteReactons", method = RequestMethod.POST)
		public ResultVo deleteReactons(@RequestBody List<Integer> ids) {

			ResultVo resultVo = new ResultVo();
			try {
				// 删除实验
				for (Integer id : ids) {
					reactionService.delete(id);
				}
				
				resultVo.setErrCode(0);
				resultVo.setErrMsg("delete success");
				return resultVo;
			} catch (Exception e) {
				logger.error("e", e);
				e.printStackTrace();
			}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("delete fail");
			return resultVo;
		}
		
		
		
		/**
		 * @Description 设计Id获取实验执行
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:49:52
		 */
		@RequestMapping(value = "/reaction/getByDesignId/{page}/{pageSize}/{id}", method = RequestMethod.GET)
		public ResultVo getByDesignId(@PathVariable("page") Integer page,
				@PathVariable("pageSize") Integer pageSize,
				@PathVariable("id") Integer id) {

			ResultVo resultVo = new ResultVo();
			// 获取当前用户
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer userId = sysUser.getUserId();
			String roleName = sysUser.getSysRole().getRoleName();
			
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			
			List<Reaction> reactions = reactionService.getByDesignId(id);
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(reactions));
			
			return resultVo;
			
		}
		
		@RequestMapping(value = "/reaction/getAppDesignByDesignId/{page}/{pageSize}/{id}", method = RequestMethod.GET)
		public ResultVo getAppDesignByDesignId(@PathVariable("page") Integer page,
				@PathVariable("pageSize") Integer pageSize,
				@PathVariable("id") Integer id) {

			ResultVo resultVo = new ResultVo();
			// 获取当前用户
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			
			List<Reaction> reactions = reactionService.getByDesignId(id);
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
			
		}
		
		
		private void createReactions(ReactionDesign reactionDesign) {

			// 得到实验记录
			List<DesignTechnologyRelation> designTechnologyRelations = reactionDesign.getDesignTechnologyRelations();

			ProjectBasicInfo project = reactionDesign.getProjectBasicInfo();

			// 生成实验基本信息
			if (designTechnologyRelations != null && designTechnologyRelations.size() > 0) {

				for (int i = 0; i < designTechnologyRelations.size(); i++) {
					Reaction reaction = new Reaction();
					reaction.setReactionName(designTechnologyRelations.get(i).getDesignTechnology().getTechnologyName());
					reaction.setReactionDesign(reactionDesign);
					reaction.setReactionStatus(reactionStatusService.getByType(1)
							.get(0));
					reaction.setProjectBasicInfo(project);
					reaction.setSysUser(reactionDesign.getSysUser());
					reaction.setStartTime(reactionDesign.getPreStartTime());
					reaction.setEndTime(reactionDesign.getPreEndTime());
					reactionService.save(reaction);
					
					List<DesignTechnologyProcess> designTechnologyProcesses = designTechnologyRelations.get(i).getDesignTechnology().getDesignTechnologyProcesses();

					if (designTechnologyProcesses != null && designTechnologyProcesses.size() > 0) {

						for (int j = 0; j < designTechnologyProcesses.size(); j++) {
							// 生成实验记录
							ReactionProcess reactionProcess = new ReactionProcess();
							reactionProcess.setReaction(reaction);
							reactionProcess.setProcess(j+1);
							reactionProcess.setRemark(designTechnologyProcesses.get(j).getRemark());
							reactionProcess.setProcessName(designTechnologyProcesses.get(j).getProcessName());
							
							reactionProcess.setStartTime(reactionDesign.getPreStartTime());
							reactionProcess.setReactionStatus(reactionStatusService.getByType(2).get(3));

							reactionProcessService.save(reactionProcess);

							// 实验执行参数
							List<DesignTechnologyDosage> designTechnologyDosageList = designTechnologyProcesses
									.get(j).getDesignTechnologyDosages();
							if (designTechnologyDosageList != null
									&& designTechnologyDosageList.size() > 0) {

								for (int l = 0; l < designTechnologyDosageList.size(); l++) {
									ReactionExecuteParameter executeParameter = new ReactionExecuteParameter();
									executeParameter.setExecuteParameterDosage(designTechnologyDosageList.get(l).getDesignTechnologyDosage().toString());
									executeParameter.setMeasurement(designTechnologyDosageList.get(l).getMeasurement());
									executeParameter.setReactionParameter(designTechnologyDosageList.get(l).getDesignParameterName());
									executeParameter
											.setReactionProcess(reactionProcess);
									executeParemeterService.save(executeParameter);
								}
							}

						}
					}
				}
			}
			reactionDesign.setExecute("true");
			reactionDesignService.updateExecute(reactionDesign);
		}
		
		
		/**
		 * @Description 复制设计保存配方
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:49:52
		 */
		public void copyDesignChemical(Integer reactionId,ReactionDesign newDesign){
			Reaction reaction = reactionService.getProcessByreactionId(reactionId);
			ReactionDesign reactionDesign = reactionDesignService.getById(reaction.getReactionDesign().getReactionDesignId());
			Integer designId = reactionDesign.getReactionDesignId();
			ReactionDesign reactionDesign1=reactionDesignService.getSolutionById(designId);
			ReactionDesign reactionDesign2=reactionDesignService.getChemicals(designId);
			
			if(reactionDesign1!=null){
				List<ReactionDesignSolution> reactionDesignSolutions=reactionDesign1.getReactionDesignSolutions();
				if(reactionDesignSolutions!=null){
					for (ReactionDesignSolution reactionDesignSolution : reactionDesignSolutions) {
						
						ReactionDesignSolution newReactionDesignSolution = new ReactionDesignSolution();
						newReactionDesignSolution.setReactionDesign(newDesign);
						newReactionDesignSolution.setSolutionEntity(reactionDesignSolution.getSolutionEntity());
						
						reactionDesignSolutionService.save(newReactionDesignSolution);
						
						List<SolutionDesignDosage> solutionDesignDosages =reactionDesignSolution.getSolutionDesignDosages();
						for (SolutionDesignDosage solutionDesignDosage : solutionDesignDosages) {
							SolutionDesignDosage newSolutionDesignDosage = new SolutionDesignDosage();
							newSolutionDesignDosage.setReactionDesignSolution(newReactionDesignSolution);
							newSolutionDesignDosage.setMeasurement(solutionDesignDosage.getMeasurement());
							newSolutionDesignDosage.setSolutionDosage(solutionDesignDosage.getSolutionDosage());
							newSolutionDesignDosage.setPercentage(solutionDesignDosage.getPercentage());
							solutionDesignDosageService.save(newSolutionDesignDosage);
						}
						
					}
				 }
			 }
			if(reactionDesign2!=null){
				List<ReactionDesignChemical> reactionDesignChemicals=reactionDesign2.getReactionDesignChemicals();
					if(reactionDesignChemicals!=null){
						for (ReactionDesignChemical reactionDesignChemical : reactionDesignChemicals) {
							ReactionDesignChemical newReactionDesignChemical = new ReactionDesignChemical();
							newReactionDesignChemical.setInventoryGroups(reactionDesignChemical.getInventoryGroups());
							newReactionDesignChemical.setReactionDesign(newDesign);
							reactionDesignChemicalService.save(newReactionDesignChemical);
							List<DesignDosage> designDosages= reactionDesignChemical.getDesignDosages();
							for (DesignDosage designDosage : designDosages) {
								DesignDosage newDesignDosage = new DesignDosage();
								newDesignDosage.setChemicalDosage(designDosage.getChemicalDosage());
								newDesignDosage.setMeasurement(designDosage.getMeasurement());
								newDesignDosage.setPercentage(designDosage.getPercentage());
								newDesignDosage.setReactionDesignChemical(newReactionDesignChemical);
								designDosageService.save(newDesignDosage);
							}
						
						}
					}
			}
		}
		
		/**
		 * @Description 复制实验工艺
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:49:52
		 */
		public void copyReaction(Integer i,Reaction reaction,List<ReactionProcess> processList,ReactionDesign reactionDesign){
			
			
			try {
					//保存工艺表
					DesignTechnology designTechnology=new DesignTechnology();
					designTechnology.setTechnologyName(reaction.getReactionName()+"-"+i);
					designTechnologyService.save(designTechnology);
					
					Reaction reaction1 = new Reaction();
					reaction1.setReactionName(designTechnology.getTechnologyName());
					reaction1.setReactionDesign(reactionDesign);
					reaction1.setReactionStatus(reactionStatusService.getByType(1).get(0));
					reaction1.setProjectBasicInfo(reaction.getProjectBasicInfo());
					reaction1.setSysUser(reactionDesign.getSysUser());
					reaction1.setStartTime(reaction.getStartTime());
					reaction1.setEndTime(reaction.getEndTime());
					reactionService.save(reaction1);
					
					
					for (ReactionProcess reactionProcess : processList) {
						DesignTechnologyProcess designTechnologyProcess =  new DesignTechnologyProcess();
						designTechnologyProcess.setProcessName(reactionProcess.getProcessName());
						designTechnologyProcess.setProcessNumber(reactionProcess.getProcess());
						designTechnologyProcess.setDesignTechnology(designTechnology);
						//保存工艺步骤表
						designTechnologyProcessService.save(designTechnologyProcess);
						
						
						// 生成实验记录
						ReactionProcess reactionProcess1 = new ReactionProcess();
						reactionProcess1.setReaction(reaction1);
						reactionProcess1.setProcess(reactionProcess.getProcess());
						reactionProcess1.setRemark(reactionProcess.getRemark());
						reactionProcess1.setProcessName(reactionProcess.getProcessName());
						reactionProcess1.setReactionStatus(reactionStatusService.getByType(2).get(3));
						reactionProcessService.save(reactionProcess1);
						
						
						List<ReactionExecuteParameter> reactionExecuteParameters=reactionProcess.getReactionExecuteParameters();
						for (ReactionExecuteParameter reactionExecuteParameter : reactionExecuteParameters) {
							DesignTechnologyDosage designTechnologyDosage =new DesignTechnologyDosage();
							designTechnologyDosage.setDesignParameterName(reactionExecuteParameter.getReactionParameter());
							designTechnologyDosage.setDesignTechnologyDosage(reactionExecuteParameter.getExecuteParameterDosage());
							designTechnologyDosage.setDesignTechnologyProcess(designTechnologyProcess);
							designTechnologyDosage.setMeasurement(reactionExecuteParameter.getMeasurement());
							//保存工艺参数表
							designTechnologyDosageService.save(designTechnologyDosage);
							
							ReactionExecuteParameter executeParameter1 = new ReactionExecuteParameter();
							executeParameter1.setExecuteParameterDosage(reactionExecuteParameter.getExecuteParameterDosage());
							executeParameter1.setMeasurement(reactionExecuteParameter.getMeasurement());
							executeParameter1.setReactionParameter(reactionExecuteParameter.getReactionParameter());
							executeParameter1.setReactionProcess(reactionProcess1);
							executeParemeterService.save(executeParameter1);
						}
					}
					
					
				DesignTechnologyRelation designTechnologyRelation = new DesignTechnologyRelation();
				if(reactionDesign!=null){
					designTechnologyRelation.setReactionDesign(reactionDesign);
				}
				designTechnologyRelation.setDesignTechnology(designTechnology);
				//保存工艺中间表
				designTechnologyRelationService.save(designTechnologyRelation);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		
		/**
		 * @Description 复制实验
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:49:52
		 */
		@RequestMapping(value = "/reaction/copyReactions", method = RequestMethod.POST)
		public ResultVo copyReactions(@RequestBody CopyReactionVo copyReactionVo){
			ResultVo resultVo = new ResultVo();
			
			// 获取当前用户
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			
			Integer reactionNum = copyReactionVo.getReactionNum();
			Integer reactionId = copyReactionVo.getReactionId();
			ReactionDesign reactionDesign = copyReactionVo.getReactionDesign();
			Reaction reaction = reactionService.getProcessByreactionId(reactionId);
			List<ReactionProcess> processList = reaction.getReactionProcesses();
			Integer copynewDesign = copyReactionVo.getCopynewDesign();
			if(reactionDesign.getType()==0){
				
				Integer index = getDesignCopyName(reaction.getReactionName());
				
				for(int i =1;i<=reactionNum;i++){
					reactionDesign.setReactionGroupName(reaction.getReactionName()+"-"+(i+index));
//					reactionDesign.setSysUser(sysUser);
					reactionDesign.setProjectBasicInfo(reactionDesign.getProjectBasicInfo());
					reactionDesign.setExecute("true");
					reactionDesign.setCreateDate(new Date());
					reactionDesignService.save(reactionDesign);
					
					// 复制配方
					copyDesignChemical(reactionId,reactionDesign);
					
					// 复制实验，工艺
					copyReaction(i,reaction,processList,reactionDesign);
				}
				
			}else{
				
				if(copynewDesign == 0){
					Integer designId = reactionDesign.getReactionDesignId();
					String reactionName = reaction.getReactionName();
					Integer index = getCopyName(reactionName,designId,reactionId);
					for(Integer i =1;i<=reactionNum; i++){
						// 复制实验，工艺
						copyReaction(i+index,reaction,processList,reactionDesign);
					}
				
				}else{
					
					reactionDesign.setReactionGroupName(reactionDesign.getReactionGroupName());
					reactionDesign.setProjectBasicInfo(reactionDesign.getProjectBasicInfo());
//					reactionDesign.setSysUser(sysUser);
					reactionDesign.setExecute("true");
					reactionDesign.setCreateDate(new Date());
					reactionDesignService.save(reactionDesign);
					
					// 复制配方
					copyDesignChemical(reactionId,reactionDesign);
					for(Integer i =1;i<=reactionNum; i++){
						// 复制实验，工艺
						copyReaction(i,reaction,processList,reactionDesign);
					}
					
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("copy success");
			return resultVo;
		}
		
		/**
		 * @Description 复制实验名称起始index
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:49:52
		 */
		public Integer getCopyName(String name,Integer designId,Integer reactionId){
			List<Reaction> reactions = reactionService.getByDesignId(designId);
			List<Integer> indexs = new ArrayList<Integer>();
			for (Reaction reaction : reactions) {
				if(reaction.getReactionId()!=reactionId){
					String reactionName = reaction.getReactionName();
					if(reactionName!=null && reactionName.startsWith(name)){
						String lastStr = reactionName.substring(reactionName.lastIndexOf("-")+1);
						if(isNumeric(lastStr)){
							indexs.add(Integer.valueOf(lastStr));
						}
					}
				}
			}
			if(indexs!=null && indexs.size()>0){
				Collections.sort(indexs);
				return indexs.get(indexs.size()-1);
			}else{
				return 0;
			}
			
		}
		public Integer getDesignCopyName(String name){
			List<String> names = reactionDesignService.getSingleNames();
			List<Integer> indexs = new ArrayList<Integer>();
			for (String designName : names) {
				if(designName!=null && designName.startsWith(name)){
					String lastStr = designName.substring(designName.lastIndexOf("-")+1);
					if(isNumeric(lastStr)){
						indexs.add(Integer.valueOf(lastStr));
					}
				}
			}
			if(indexs!=null && indexs.size()>0){
				Collections.sort(indexs);
				return indexs.get(indexs.size()-1);
			}else{
				return 0;
			}
			
		}
		
		public static boolean isNumeric(String str) {
			try {
			new BigDecimal(str);
			} catch (Exception e) {
			return false;//异常 说明包含非数字。
			}
			return true;
			}
		
}
