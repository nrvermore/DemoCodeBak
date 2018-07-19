package com.labwinner.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.ChemicalImage;
import com.labwinner.domain.ExecuteChemical;
import com.labwinner.domain.Inventories;
import com.labwinner.domain.InventoryGroups;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.InventoryModify;
import com.labwinner.domain.InventoryUser;
import com.labwinner.domain.Measurement;
import com.labwinner.domain.ModifyCode;
import com.labwinner.domain.ModifyType;
import com.labwinner.domain.QrInfo;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.domain.SignIn;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ChemicalImageService;
import com.labwinner.service.ChemicalParameterService;
import com.labwinner.service.ExecuteChemicalService;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.InventoryGroupsService;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.service.InventoryModifyService;
import com.labwinner.service.InventoryUserService;
import com.labwinner.service.MaterialPurchaseService;
import com.labwinner.service.MaterialTypeService;
import com.labwinner.service.MeasurementService;
import com.labwinner.service.ModifyCodeService;
import com.labwinner.service.ModifyTypeService;
import com.labwinner.service.PriceCurrencyService;
import com.labwinner.service.QrInfoService;
import com.labwinner.service.ReactionDesignChemicalService;
import com.labwinner.service.SecureRankService;
import com.labwinner.service.SolutionDosagesService;
import com.labwinner.service.SupplierService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.Base64Util;
import com.labwinner.util.ExcelData;
import com.labwinner.util.ExportExcelUtils;
import com.labwinner.util.FileUtil;
import com.labwinner.util.MergeUtil;
import com.labwinner.util.PdfUtil;
import com.labwinner.util.QRCreateAndParse;
import com.labwinner.vo.InventoriesVo;
import com.labwinner.vo.InventoryDataVo;
import com.labwinner.vo.InventoryImageVo;
import com.labwinner.vo.InventoryReactionVo;
import com.labwinner.vo.PrintPdfVo;

/**
 * @Description 库存Controller
 * @author xux
 * @version V1.0
 * @date 2017年6月6日 上午10:36:15
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class InventoriesController {

	private static Logger logger = LoggerFactory
			.getLogger(InventoriesController.class);

	@Autowired
	private InventoriesService inventoriesService;
	@Autowired
	private InventoryModifyService inventoryModifyService;
	@Autowired
	private ModifyTypeService modifyTypeService;
	@Autowired
	private ModifyCodeService modifyCodeService;
	@Autowired
	private MeasurementService measurementService;
	@Autowired
	private PriceCurrencyService priceCurrencyService;
	@Autowired
	private InventoryLocationService inventoryLocService;
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private MaterialTypeService materialTypeService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SecureRankService secureRankService;
	@Autowired
	private InventoryUserService inventoryUserService;
	@Autowired
	private ChemicalImageService chemicalImageService;
	@Autowired
	private ChemicalParameterService chemicalParameterService;
	@Autowired
	private InventoryGroupsService inventoryGroupsService;
	@Autowired
	private QrInfoService qrInfoService;
	@Autowired
	private ReactionDesignChemicalService reactionDesignChemicalService;
	@Autowired
	private ExecuteChemicalService executeChemicalService;
	@Autowired
	private MaterialPurchaseService materialPurchaseService;
	@Autowired
	private SolutionDosagesService solutionDosagesService;
	
	@Value("${web.upload-path}")
	String filePath;
	@Value("${web.url-path}")
	private String chemicalUrlPath;
	@Value("${web.qr-path}")
	String qrPath;
	@Value("${web.qrUrl-path}")
	private String urlPath; 
	@Value("${web.upload_path_pdf}")
	private String pdfPath; 
	@Value("${web.agency_pdf}")
	private String pdfLine; 
	@Value("${excel.upload-path}")
	private String excelPath;
	
	@Value("${excel.url-path}")
	private String excelUrl;
	
	
	
	
	/**
	 * @Description 获取库存相关实验
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventories/getProcesses/{id}/{page}/{pageSize}", method = RequestMethod.GET)
	public ResultVo getReactions(@PathVariable("id") Integer id,
			@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize) {
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		
//		List<Reaction> reactionList = new ArrayList<Reaction>();
		List<ReactionProcess> processList = new ArrayList<ReactionProcess>();
		if (roleName.equals("ROLE_TEAM")) {
			Inventories inventoryList = inventoriesService.getReactions(id);
			if (inventoryList == null) {
				resultVo.setErrCode(2);
				resultVo.setErrMsg("inventory is null");
				return resultVo;
			}
			List<ExecuteChemical> executeChemicals = inventoryList.getExecuteChemicals();
			if(executeChemicals!=null && executeChemicals.size()>0){
				for (ExecuteChemical executeChemical : executeChemicals) {
					ReactionProcess process = executeChemical.getExecuteChemicalGroup().getReactionProcess();
					processList.add(process);
				}
			}
			
			int total = processList.size();
			double c = (((double) total) / pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			int num = processList.size() > page * pageSize ? page * pageSize
					: processList.size();
			if (page <= d) {
				pageEntity
						.setList(processList.subList((page - 1) * pageSize, num));
			}
			
//			inventoryReactionVo.setInventoryId(id);
//			inventoryReactionVo.setReactionList(reactionList);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(pageEntity);
			return resultVo;
		}else{
			Inventories inventoryList = inventoriesService.getUserReactions(userId,id);
			if (inventoryList == null ) {
				resultVo.setErrCode(2);
				resultVo.setErrMsg("inventory is null");
				return resultVo;
			}
			List<ExecuteChemical> executeChemicals = new ArrayList<ExecuteChemical>(inventoryList.getExecuteChemicals());
			
			if(executeChemicals!=null && executeChemicals.size()>0){
				for (ExecuteChemical executeChemical : executeChemicals) {
					ReactionProcess process = executeChemical.getExecuteChemicalGroup().getReactionProcess();
					processList.add(process);
				}
			}
			
			int total = processList.size();
			double c = (((double) total) / pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			int num = processList.size() > page * pageSize ? page * pageSize
					: processList.size();
			if (page <= d) {
				pageEntity
						.setList(processList.subList((page - 1) * pageSize, num));
			}
			
//			inventoryReactionVo.setReactionList(pageEntity);
//			inventoryReactionVo.setInventoryId(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(pageEntity);
			return resultVo;
		}
	}
	
	
	/**
	 * @Description 条件模糊查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventories/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {
		ResultVo resultVo = new ResultVo();
		
			// 获取当前用户
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService
					.getByUsername(login.getPrincipal()).get(0);
			Integer userId = sysUser.getUserId();
			String roleName = sysUser.getSysRole().getRoleName();
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			
			if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
				if (!roleName.equals("ROLE_TEAM")) {
					List<Inventories> list = inventoriesService.getByKeyword(keyword, userId);
					if (list != null) {
						for (Inventories inventory : list) {
							//库存位置
							InventoryLocation location = inventory.getInventoryLocation();
							if(location!=null&&location.getPid()!=0){
								String pName = getAllPname(location.getCid());
								location.setParentName(pName);
							}
						}
						resultVo.setErrCode(0);
						resultVo.setErrMsg("find success");
						resultVo.setResultData(new PageInfo(list));
						return resultVo;
					}
					resultVo.setErrCode(2);
					resultVo.setErrMsg("find is null");
					return resultVo;

				} else {
					List<Inventories> list = inventoriesService.findUserInventorys(keyword);
					if (list != null) {
						for (Inventories inventory : list) {
							//库存位置
							InventoryLocation location = inventory.getInventoryLocation();
							if(location!=null&&location.getPid()!=0){
								String pName = getAllPname(location.getCid());
								location.setParentName(pName);
							}
						}
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
					List<Inventories> list = inventoriesService.getAll(userId);
					if (list != null) {
						for (Inventories inventory : list) {
							//库存位置
							InventoryLocation location = inventory.getInventoryLocation();
							if(location!=null&&location.getPid()!=0){
								String pName = getAllPname(location.getCid());
								location.setParentName(pName);
							}
						}
						resultVo.setErrCode(0);
						resultVo.setErrMsg("find success");
						resultVo.setResultData(new PageInfo(list));
						return resultVo;
					}
					resultVo.setErrCode(2);
					resultVo.setErrMsg("find is null");
					return resultVo;

				} else {
					List<Inventories> list = inventoriesService.getAllInventorys();
					if (list != null) {
						for (Inventories inventory : list) {
							//库存位置
							InventoryLocation location = inventory.getInventoryLocation();
							if(location!=null&&location.getPid()!=0){
								String pName = getAllPname(location.getCid());
								location.setParentName(pName);
							}
						}
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
	 * @Description 获取所有对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventories/getParameters", method = RequestMethod.GET)
	public ResultVo getParameters() {
		List<InventoryLocation> list = inventoryLocService.getAllFirst();
		List<InventoryLocation> resList=new ArrayList<InventoryLocation>();
		List<Measurement> measurements =  new ArrayList<Measurement>();
		measurements.addAll(measurementService.getById(1));
		measurements.addAll(measurementService.getById(2));
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				InventoryLocation location = inventoryLocService.getTree(list.get(i).getCid());
				if(location!=null){
					resList.add(location);
				}
			}
		}
		InventoryDataVo inventoryDateVo = new InventoryDataVo();
		inventoryDateVo.setTypes(materialTypeService.getTree(1));
		inventoryDateVo.setPriceCurrencyList(priceCurrencyService.findAll());
		inventoryDateVo.setgList(measurements);
		inventoryDateVo.setMolList(measurementService.getById(3));
		inventoryDateVo.setLocation(resList);
		inventoryDateVo.setSupplierList(supplierService.getAll());
		inventoryDateVo.setUserList(sysUserService.getAll());
		inventoryDateVo.setRankList(secureRankService.getAll());
		ResultVo resultVo = new ResultVo();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find successe");
		resultVo.setResultData(inventoryDateVo);
		return resultVo;
	}
	
	
	/**
	 * @Description 库存分组主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventories/getByGroupId/{id}/{cid}", method = RequestMethod.GET)
	public ResultVo getByGroupId(@PathVariable("id") Integer id,
			@PathVariable("cid") String cid) {
		List<Inventories> inventoryList = new ArrayList<Inventories>();
		ResultVo resultVo = new ResultVo();
		Integer locid =0;
		if(cid==null || cid.equals("null")){
			inventoryList = inventoriesService.getByGroupId(id,null);
		}else{
			locid = Integer.valueOf(cid);
			//TODO 位置节点下所有子节点库存
			List<Integer> ids = getTree(locid);
			for (Integer locaId : ids) {
				List<Inventories> inventorys = inventoriesService.getByGroupId(id,locaId);
				if (inventorys != null && inventorys.size() > 0) {
					inventoryList.addAll(inventorys);
				}
			}
			
		}
	

		if (inventoryList == null) {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("inventory is null");
			return resultVo;
		}
		for (Inventories inventories : inventoryList) {
			//库存位置
			InventoryLocation location = inventories.getInventoryLocation();
			if(location!=null && location.getPid()!=0){
				String pName = getAllPname(location.getCid());
				location.setParentName(pName);
			}
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(inventoryList);
		return resultVo;
	}
	
	
	
	/**
	 * @Description 库存分组主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventories/getByGroupIdList", method = RequestMethod.POST)
	public ResultVo getByGroupIds(@RequestBody List<Integer> ids) {
		List<Inventories> inventoryList = new ArrayList<Inventories>();
		ResultVo resultVo = new ResultVo();
		
		inventoryList = inventoriesService.getByGroupIds(ids);
		
		if (inventoryList == null) {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("inventory is null");
			return resultVo;
		}
		
		String fileName ="库存数据.xlsx"; 
		String path = excelPath+fileName;
		 ExcelData data = new ExcelData();
	        data.setName("库存数据");
	        List<String> titles = new ArrayList();
	        titles.add("库存名称");
	        titles.add("可用量");
	        titles.add("批号");
	        titles.add("储存位置");
	        titles.add("负责人");
	        titles.add("供应商");
	        data.setTitles(titles);

	        
	        List<List<Object>> rows = new ArrayList();
	        
	        for(Inventories inventories :inventoryList){
	        	List<Object> row = new ArrayList();
	        	
	        	InventoryLocation location = inventories.getInventoryLocation();
				if(location!=null && location.getPid()!=0){
					String pName = getAllPname(location.getCid());
					location.setParentName(pName);
					location.setLabel(pName+">"+location.getLabel());
				}
				
	 	        row.add(inventories.getInventoryName());
	 	        row.add(inventories.getActAvaWei()+inventories.getMeasurement().getMeasureUnit());
	 	        row.add(inventories.getInventoryGroups().getBatchNumber());
	 	        row.add(inventories.getInventoryLocation().getLabel());
	 	        row.add(inventories.getSysUser().getRealname());
	 	        row.add(inventories.getInventoryGroups().getSupplier().getSuprName());
	 	        rows.add(row);
	        }
	        
	        data.setRows(rows);


	        //生成本地
	        try {
				ExportExcelUtils.exportExcel(path,data);
				 resultVo.setErrCode(0);
			     resultVo.setErrMsg("生成excel成功");
			     resultVo.setResultData(excelUrl+fileName);
			     resultVo.setResultData1(path);
			     return resultVo;
			} catch (Exception e) {
				
				e.printStackTrace();
				resultVo.setErrCode(1);
			    resultVo.setErrMsg("生成excel失败");
			     return resultVo;
			}
	       
	}
	
	
	
	/**
	 * @Description 主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventories/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List<Integer> inventoryIds = new ArrayList<Integer>();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (!roleName.equals("ROLE_TEAM")) {
			List<Inventories> list = inventoriesService.getAll(userId);
			if(list!=null && list.size()>0){
				for (Inventories inventories : list) {
					inventoryIds.add(inventories.getInventoryId());
				}
				
			}
			if(!inventoryIds.contains(id)){
				resultVo.setErrCode(4);
				resultVo.setErrMsg("您无权访问");
				return resultVo;
			}
		}
		
		Inventories inventories = inventoriesService.getById(id);

		if (inventories == null) {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("inventory is null");
			return resultVo;
		}
		if(inventories.getInventoryGroups()!=null){
		List<ChemicalImage> chemicalImages = new ArrayList<ChemicalImage>(inventories.getInventoryGroups().getChemicalImages());
			for (ChemicalImage chemicalImage : chemicalImages) {
				if(chemicalImage.getDissolvantDescribe()!=null){
					chemicalImage.setDissolvantDescribe(chemicalUrlPath+chemicalImage.getDissolvantDescribe());
				}
			}
		}
		//二维码
		QrInfo qrInfo = inventories.getQrInfo();
		if(inventories.getQrInfo()!=null){
			if(!new File(qrPath+inventories.getQrInfo().getQrName()).exists()){
				QRCreateAndParse qr = new QRCreateAndParse();
				qr.createQr(inventories.getBarCode(), qrPath,inventories.getQrInfo().getQrName());
			}
			qrInfo.setQrName(urlPath+qrInfo.getQrName());
		}
		
		//库存位置
		InventoryLocation location = inventories.getInventoryLocation();
		if(location!=null&&location.getPid()!=0){
			String pName = getAllPname(location.getCid());
			location.setParentName(pName);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(inventories);
		return resultVo;
	}

	/**
	 * @Description 新增图片
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventories/saveImage", method = RequestMethod.POST)
	public ResultVo saveImage(@RequestBody InventoryImageVo imageVo) {
		ResultVo resultVo = new ResultVo();

		Integer groupId = imageVo.getGroupId();
		List<String> urls = imageVo.getUrls();
		List<String> imgs = imageVo.getImgs();

		InventoryGroups inventoryGroups = new InventoryGroups();
		inventoryGroups.setGroupId(groupId);

		// 保存图片
		saveImage(inventoryGroups, imgs);
		// 删除要删的url图片
		if (null != urls && urls.size() > 0) {
			for (String url : urls) {
				new File(filePath+ url.substring(url.lastIndexOf("/") + 1)).delete();
				chemicalImageService.deleteByUrl(
						url.substring(url.lastIndexOf("/") + 1), groupId);
				}
				}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save successe");
		return resultVo;

	}
	
	/**
	 * @Description 新增库存
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventories", method = RequestMethod.POST)
	public ResultVo save(@RequestBody InventoriesVo inventoriesVo) {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		List<Integer> idList = new ArrayList<Integer>();
		String inventoryName = inventoriesVo.getInventoryName();
		
		//待上传图片
		List<String> imgs = inventoriesVo.getImgStrs();
		
		// 库存权限加入自己
		List<Integer> sysUsers = inventoriesVo.getInventoryList();
		if (sysUsers != null && sysUsers.size() > 0) {
			sysUsers.add(userId);
		}

		try {
			InventoryGroups inventoryGroups = null;
			Inventories inventories = getInventories(inventoriesVo);
			
			if (inventoriesVo.getInventoryGroups()!=null&& inventoriesVo.getInventoryGroups().getGroupId()!=null){
				
				inventoryGroups = inventoryGroupsService.getById(inventoriesVo.getInventoryGroups().getGroupId());
				inventoryGroups.setTotalWei(inventoriesVo.getActAvaWei()*inventoriesVo.getInventoryNumber()+inventoryGroups.getTotalWei());
				inventoryGroups.setInventoryNumber(inventoryGroups.getInventoryNumber()+inventoriesVo.getInventoryNumber());
				inventoryGroupsService.update(inventoryGroups);
				
			}else{
				// 保存库存分组
				inventoryGroups = getInventoryGroups(inventoriesVo);
				inventoryGroupsService.save(inventoryGroups);
				// 保存权限表
				saveInventoryGroups(inventoryGroups, sysUsers);
			}
			
			// 保存图片
			saveImage(inventoryGroups, imgs);
			
			inventories.setInventoryGroups(inventoryGroups);
			if (inventoriesVo.getInventoryNumber() == 1) {
				// 生成条形码
				String barCode = createBarcode();
				inventories.setBarCode(barCode);
				inventories.setInventoryName(inventoryName);
				// 保存库存信息
				inventoriesService.save(inventories);
				// 生成二维码保存数据
				idList.add(inventories.getInventoryId());

				QRCreateAndParse qr = new QRCreateAndParse();
				String qrName = qr.createQr(barCode, qrPath,null);
				QrInfo qrInfo = new QrInfo();
				qrInfo.setQrName(qrName);
				qrInfo.setInventories(inventories);
				qrInfoService.save(qrInfo);
				
				// 保存库存历史表
				saveInventoryModify(inventories, sysUser);
			} else {
				for (int i = 0; i < inventoriesVo.getInventoryNumber(); i++) {
					// 生成条形码
					String barCode = createBarcode();
					inventories.setBarCode(barCode);
					inventories.setInventoryName(inventoryName + "-" + (i + 1));

					// 保存库存信息
					inventoriesService.save(inventories);
					// 生成二维码保存数据
					idList.add(inventories.getInventoryId());

					QRCreateAndParse qr = new QRCreateAndParse();
					String qrName = qr.createQr(barCode, qrPath,null);
					QrInfo qrInfo = new QrInfo();
					qrInfo.setQrName(qrName);
					qrInfo.setInventories(inventories);
					qrInfoService.save(qrInfo);
					// 保存库存历史表
					ModifyCode modifyCode = modifyCodeService.getById(4);
					// 库存变更对象
					InventoryModify inventoryModify = new InventoryModify();
					int len = modifyTypeService.getAll().size();
					ModifyType modifyType = modifyTypeService.getAll().get(len - 1);
					inventoryModify.setMeasurement(inventories.getMeasurement());
					inventoryModify.setModifyAfter(inventories.getActAvaWei());
					inventoryModify.setInventories(inventories);
					inventoryModify.setModifyType(modifyType);
					inventoryModify.setModifyCode(modifyCode);
					inventoryModify.setChangeDate(new Date());
					inventoryModify.setModifier(sysUser);
					inventoryModifyService.save(inventoryModify);
				}
			}
			
			//修改订单状态
			Integer matPurId = inventoriesVo.getMatPurId();
			materialPurchaseService.updateOrders(7, matPurId);
			
			InventoriesVo inventoriesVo2 = new InventoriesVo();
			inventoriesVo2.setIdList(idList);
			inventoriesVo2.setInventoryGroups(inventoryGroups);
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save successe");
			resultVo.setResultData(inventoriesVo2);
			return resultVo;
		} catch (Exception e) {
			logger.error("inventory e", e);
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("save fail");
			return resultVo;
		}
	}
	
	/**
	 * @Description 更新库存
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventoriesMany", method = RequestMethod.PUT)
	public ResultVo updateMany(@RequestBody InventoriesVo inventoriesVo) {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();

		Inventories inventories = getInventories(inventoriesVo);
		InventoryGroups inventoryGroups = getInventoryGroups(inventoriesVo);
		
		List<Integer> sysUsers = inventoriesVo.getInventoryList();
		if (sysUsers != null && sysUsers.size() > 0) {
			sysUsers.add(userId);
		}

		List<Integer> idList = inventoriesVo.getIdList();
		String inventoryName = inventoriesVo.getInventoryName();

		try {
			// 更新库存分组
			inventoryGroupsService.update(inventoryGroups);

			if (inventoriesVo.getInventoryNumber() == 1) {
				Integer id = idList.get(0);
				inventories.setInventoryId(id);
				inventories.setModifier(userId+"");
				inventories.setModifyDate(new Date());
				inventories.setInventoryName(inventoryName);

				// 删除库存权限成员
				inventoryUserService.delete(id);
				// 保存权限表
				saveInventoryGroups(inventoryGroups, sysUsers);
				inventoriesService.update(inventories);
				// 保存库存历史表
				if(inventories.getChangeBefore()!=null && 
						(inventories.getActAvaWei() - inventories.getChangeBefore() != 0.0)) {
					saveInventoryModify(inventories, sysUser);
				}

			} else {
				for (int i = 0; i < inventoriesVo.getInventoryNumber(); i++) {
					Integer id = idList.get(i);
					inventories.setInventoryId(id);
					inventories.setModifier(userId+"");
					inventories.setModifyDate(new Date());
					inventories.setInventoryName(inventoryName + "-" + (i + 1));

					// 删除库存权限成员
					inventoryUserService.delete(id);

					// 保存权限表
					saveInventoryGroups(inventoryGroups, sysUsers);
					inventoriesService.update(inventories);
					// 保存库存历史表
					if(inventories.getChangeBefore()!=null && 
							(inventories.getActAvaWei() - inventories.getChangeBefore() != 0.0)) {
						saveInventoryModify(inventories, sysUser);
					}
				}
			}
			InventoriesVo inventoriesVo2 = new InventoriesVo();
			inventoriesVo2.setIdList(idList);
			inventoriesVo2.setInventoryGroups(inventoryGroups);

			resultVo.setErrCode(0);
			resultVo.setErrMsg("save successe");
			resultVo.setResultData(inventoriesVo2);
			return resultVo;
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("save fail");
			return resultVo;
		}
	}

	/**
	 * @Description 更新库存
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventories", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody Inventories inventories) {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		Integer id = inventories.getInventoryId();
		Inventories inventories2 = inventoriesService.getById(id);
		try {
			if (inventories != null) {
				
				inventories.setModifier(userId+"");
				inventories.setModifyDate(new Date());
				inventoriesService.update(inventories);
				
				// 保存库存历史表
				if (inventories.getChangeBefore()!=null 
						&&(inventories.getActAvaWei() - inventories.getChangeBefore() != 0.0)) {
					saveInventoryModify(inventories, sysUser);
				}
				//变更库存组重量
				InventoryGroups inventoryGroups = inventories2.getInventoryGroups();
				inventoryGroups.setTotalWei(inventoryGroups.getTotalWei()+inventories.getActAvaWei()-inventories2.getActAvaWei());
				inventoryGroupsService.update(inventoryGroups);
				
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe");
			return resultVo;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("e", e);
			resultVo.setErrCode(1);
			resultVo.setErrMsg("save fail");
			return resultVo;
		}
	}

	/**
	 * @Description 库存分组补充库存
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventories/supply", method = RequestMethod.POST)
	public ResultVo saveInventories(@RequestBody Inventories inventories){
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();

		if(inventories!=null){
			inventories.setCreater(userId+"");
			inventories.setCreateDate(new Date());
			// 生成条形码
			String barCode = createBarcode();
			inventories.setBarCode(barCode);
			inventoriesService.save(inventories);
			QRCreateAndParse qr = new QRCreateAndParse();
			String qrName = qr.createQr(barCode, qrPath,null);
			QrInfo qrInfo = new QrInfo();
			qrInfo.setQrName(qrName);
			qrInfo.setInventories(inventories);
			qrInfoService.save(qrInfo);
			
			Inventories inventories2 = inventoriesService.getById(inventories.getInventoryId());
			InventoryGroups inventoryGroups = inventories2.getInventoryGroups();
			inventoryGroups.setTotalWei(inventories.getActAvaWei()+inventoryGroups.getTotalWei());
			inventoryGroups.setInventoryNumber(inventoryGroups.getInventoryNumber()+1);
			inventoryGroupsService.update(inventoryGroups);
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save successe");
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("inventories is null");
		return resultVo;
	}
	
	/**
	 * @Description 补充单个库存接口
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/updateInventories", method = RequestMethod.POST)
	public ResultVo updateInventory(@RequestBody Inventories inventories) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		
		if(inventories!=null){
			Inventories inventories2 = inventoriesService.getById(inventories.getInventoryId());
			InventoryGroups inventoryGroups = inventories2.getInventoryGroups();
			inventoryGroups.setTotalWei(inventories.getActAvaWei()+inventoryGroups.getTotalWei());
			
			inventories.setModifier(userId+"");
			inventories.setModifyDate(new Date());
			if(inventories2!=null){
			inventories.setActAvaWei(inventories.getActAvaWei()+inventories2.getActAvaWei());
			}
			
			//变更库存重量
			inventoriesService.updateInventory(inventories);
			
			
			inventoryGroupsService.update(inventoryGroups);
			
			InventoryModify inventoryModify = new InventoryModify();
			inventoryModify.setChangeDate(new Date());
			ModifyType modifyType = new ModifyType();
			modifyType.setModifyTypeId(3);
			ModifyCode modifyCode = new ModifyCode();
			modifyCode.setModifyCodeId(1);
			inventoryModify.setModifyType(modifyType);
			inventoryModify.setInventories(inventories);
			inventoryModify.setMeasurement(inventories.getMeasurement());
			inventoryModify.setModifyCode(modifyCode);
			inventoryModify.setModifyAfter(inventories.getActAvaWei());
			inventoryModify.setModifier(sysUser);
			inventoryModify.setModifyDate(new Date());
			
			//新增表更历史
			inventoryModifyService.save(inventoryModify);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("supply success");
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("inventories is null");
		return resultVo;
	}
	
	/**
	 * @Description 删除库存接口
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventories/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();

		Inventories inventories = inventoriesService.getById(id);
		List<Integer> inventorys = reactionDesignChemicalService.getInventorys();
		List<Integer> inventoryList = executeChemicalService.getInventorys();
		List<Integer> solutionInventoryList = solutionDosagesService.getAllInventories();
		
		if (inventories != null) {
			Integer groupId = inventories.getInventoryGroups().getGroupId();
			Integer cid = inventories.getSysUser().getUserId();
			if ((cid-userId!=0) && !roleName.equals("ROLE_TEAM")) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("您无权删除库存");
				return resultVo;
			}
			else if (inventoryList.contains(id)) {
				resultVo.setErrCode(3);
				resultVo.setErrMsg("实验用到，您无权删除库存");
				return resultVo;
			} 
			else if(solutionInventoryList.contains(id)){
				resultVo.setErrCode(6);
				resultVo.setErrMsg("溶液用到，您无权删除库存");
				return resultVo;
			}
			else {

				// 库存历史变更表
				inventoryModifyService.delete(id);
				// 二维码信息表
				qrInfoService.delete(id);
				// 库存基本信息表
				inventoriesService.delete(id);
				List<Inventories> list = inventoriesService.getByGroupId(groupId,null);
				if (list == null || list.size() == 0||inventorys.contains(groupId) ) {
					inventoryGroupsService.delete(groupId);
				}else{
					InventoryGroups inventoryGroups = inventories.getInventoryGroups();
					inventoryGroups.setInventoryNumber(inventoryGroups.getInventoryNumber()-1);
					inventoryGroups.setTotalWei(inventoryGroups.getTotalWei()-inventories.getActAvaWei());
					inventoryGroupsService.update(inventoryGroups);
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("delete success");
				return resultVo;
			}
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("无此库存");
		return resultVo;

	}
	
	/**
	 * @Description 编辑图片
	 * @author xux
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/updat/images", method = RequestMethod.POST)
	public ResultVo save(
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			@RequestParam(value = "imgs", required = false) String[] imgs,
			@RequestParam(value = "id", required = false) Integer id) throws IOException{
		ResultVo resultVo =  new ResultVo();
		InventoryGroups inventoryGroups = new InventoryGroups();
		inventoryGroups.setGroupId(id);
		if (null != files && files.length > 0) {
			saveChemicalImage(inventoryGroups, files, filePath);
		}
		if (null != imgs && imgs.length > 0) {
			for (String img : imgs) {
				new File(filePath+img.substring(img.lastIndexOf("/") + 1)).delete();
				chemicalImageService.deleteByUrl(img.substring(img.lastIndexOf("/") + 1),id);
			}

		}
		return resultVo;
	}
 
	
	/**
	 * @Description 保存库存历史
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	public String createBarcode(){

		Random random = new Random();
		int a = random.nextInt(999999999) + 1;

		String barCode = "" + a;
		List<String> barCodes = inventoriesService.getBarCodes();
		boolean flat = true;
		flat = barCodes.contains(barCode);
		if (flat) {
			createBarcode();
		}
		return barCode;
	}
	
	/**
	 * @Description 获取库存
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	public Inventories getInventories(InventoriesVo inventoriesVo) {

		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		Inventories inventories = new Inventories();
		inventories.setActAvaWei(inventoriesVo.getActAvaWei());
		inventories.setComDate(inventoriesVo.getComDate());
		inventories.setMaturityDate(inventoriesVo.getMaturityDate());
		inventories.setPrice(inventoriesVo.getPrice());
		inventories.setPriceCurrency(inventoriesVo.getPriceCurrency());
		inventories.setInventoryLocation(inventoriesVo.getInventoryLocation());
		inventories.setMeasurement(inventoriesVo.getMeasurement());
		inventories.setSysUser(inventoriesVo.getSysUser());
		inventories.setCreater(userId+"");
		inventories.setCreateDate(new Date());
		return inventories;
	}
	
	/**
	 * @Description 获取库存分组
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	public InventoryGroups getInventoryGroups(InventoriesVo inventoriesVo) {
		
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		InventoryGroups inventoryGroups = new InventoryGroups();
		if(inventoriesVo.getInventoryGroups()!=null&&inventoriesVo.getInventoryGroups().getGroupId()!=null){
			inventoryGroups.setGroupId(inventoriesVo.getInventoryGroups().getGroupId());
		}
		inventoryGroups.setInventoryName(inventoriesVo.getInventoryName());
//		inventoryGroups.setSupplier(inventoriesVo.getSupplier());
//		inventoryGroups.setBatchNumber(inventoriesVo.getBatchNumber());
		inventoryGroups.setInventoryNumber(inventoriesVo.getInventoryNumber());
//		inventoryGroups.setChemicalParameter(inventoriesVo.getChemicalParameter());
		inventoryGroups.setConPur(inventoriesVo.getConPur());
		inventoryGroups.setTotalWei(inventoriesVo.getActAvaWei()*inventoriesVo.getInventoryNumber());
		inventoryGroups.setConPurMeasurement(inventoriesVo.getConPurMeasurement());
		inventoryGroups.setDensityVariation(inventoriesVo.getDensityVariation());
		inventoryGroups.setDescription(inventoriesVo.getDescription());
		inventoryGroups.setSecureRank(inventoriesVo.getSecureRank());
		inventoryGroups.setMeasurement(inventoriesVo.getMeasurement());
		inventoryGroups.setProductSummaryId(inventoriesVo.getProductSummaryId());
		inventoryGroups.setMaterialType(inventoriesVo.getMaterialType());
		inventoryGroups.setCreater(userId+"");
		inventoryGroups.setCreateDate(new Date());
		return inventoryGroups;
	}

	
	/**
	 * @Description 保存图片
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveImage(InventoryGroups inventoryGroups, List<String> imgStrs) {

		// 保存上传图片
		if (imgStrs != null && imgStrs.size() > 0) {
			// base64保存图片
			Base64Util base64Util = new Base64Util();
			ChemicalImage image = new ChemicalImage();
			for (String imgStr : imgStrs) {
				imgStr = imgStr.substring(imgStr.indexOf(",") + 1);
				image.setInventoryGroups(inventoryGroups);
				image.setDissolvantDescribe(base64Util.GenerateImage(imgStr,
						filePath));
				chemicalImageService.save(image);
			}
		}
	}
	
	/**
	 * @Description 保存权限用户
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveInventoryGroups(InventoryGroups inventoryGroups, List<Integer> sysUsers) {

		// 保存系统权限用户信息
		if (inventoryGroups.getSecureRank().getSecureRankId() == 2) {
			InventoryUser inventoryUser = new InventoryUser();
			SysUser sysUser = new SysUser();
			if (sysUsers != null) {
				for (Integer userId : sysUsers) {
					inventoryUser.setInventoryGroups(inventoryGroups);
					sysUser.setUserId(userId);
					inventoryUser.setSysUser(sysUser);
					inventoryUserService.save(inventoryUser);
				}
			}
		}
	}
	
	/**
	 * @Description 保存库存历史
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveInventoryModify(Inventories inventories, SysUser sysUser) {

		ModifyCode modifyCode = modifyCodeService.getById(1);
		// 库存变更对象
		InventoryModify inventoryModify = new InventoryModify();
		int len = modifyTypeService.getAll().size();
		ModifyType modifyType = modifyTypeService.getAll().get(len - 1);
		inventoryModify.setMeasurement(inventories.getMeasurement());
		inventoryModify.setModifyAfter(inventories.getActAvaWei());
		inventoryModify.setInventories(inventories);
		inventoryModify.setModifyType(modifyType);
		inventoryModify.setModifyCode(modifyCode);
		inventoryModify.setChangeDate(new Date());
		inventoryModify.setModifier(sysUser);
		inventoryModifyService.save(inventoryModify);
	}

	
	 public String getAllPname(Integer id) {
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
	
	 /**
		 * @Description 生成二维码打印
		 * @author llwangi
		 * @version V1.0
		 * @date 2017年10月27日 上午11:49:52
		 */
	 @RequestMapping(value = "/createPdfForPrint", method = RequestMethod.POST)
		public ResultVo createPdfForPrint(@RequestBody List<PrintPdfVo> printPdf) {
			ResultVo resultVo = new ResultVo();
			try {
				List<String> list=new ArrayList<String>();
				if(printPdf!=null&&printPdf.size()>0){
					for(int i=0;i<printPdf.size();i++){
						PdfUtil pdfUtil=new PdfUtil();
						// SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");  
						Map<String, Object> map =new HashMap<String, Object>();
						map.put("batchNum", printPdf.get(i).getBatchNumber());
						map.put("startTime", printPdf.get(i).getComDate());
						map.put("name", printPdf.get(i).getInventoryName());
						map.put("endTime", printPdf.get(i).getMaturityDate());
						map.put("supplier", printPdf.get(i).getSuprName());
						if(printPdf.get(i).getQrPath()!=null){
							//if(printPdf.get(i).getQrPath().indexOf("http")>=0){
							String path=printPdf.get(i).getQrPath();
							String picPath=qrPath+path.substring(path.lastIndexOf("/")+1,path.length());
							map.put("iamgePath", picPath);
								//String qrPath=pdfUtil.getImageLoad(printPdf.get(i).getQrPath(), "src/main/resources/template/");
								//map.put("iamgePath", qrPath);
							//}
						}
						String filename="print"+UUID.randomUUID().toString()+".pdf";
						String outUrl="src/main/resources/template/"+filename;
						if(pdfUtil.exportPdf(map, outUrl, "models.pdf")){
							list.add(outUrl);
						}else{
							resultVo.setErrCode(1);	
							resultVo.setErrMsg("生成pdf报错1");
							return resultVo;
						}
					}
				}
				MergeUtil mergeUtil=new MergeUtil();
				String endName=UUID.randomUUID().toString()+".pdf";
				Boolean flag=mergeUtil.mergePdfFiles(list, pdfPath+"/"+endName);
				if(flag){
					String out=pdfLine+endName;
					resultVo.setErrCode(0);
					resultVo.setResultData(out);
				}else{
					resultVo.setErrCode(1);
					resultVo.setErrMsg("生成pdf报错2");
					return resultVo;
				}
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("生成pdf报错3"+e.getMessage());
				return resultVo;
			}
			return resultVo;
		}
	 
	 /**
		 * @Description 保存图片
		 * @author xux
		 * @version V1.0
	 * @throws IOException 
		 * @date 2017年5月18日 上午11:49:52
		 */
		private void saveChemicalImage(InventoryGroups inventoryGroups, MultipartFile[] files, String path) throws IOException {

			// 保存上传图片
			List<String> fileNameList = new FileUtil().imageUpload(files, path);
			ChemicalImage chemicalImage = new ChemicalImage();
			for (String fileName : fileNameList) {
				chemicalImage.setDissolvantDescribe(fileName);
				chemicalImage.setInventoryGroups(inventoryGroups);
				chemicalImageService.save(chemicalImage);
			}
		}
		
		public List<Integer> getTree(@PathVariable("cid") Integer cid) {
			// public List<Integer> getTree(Integer cid) {

			List<Integer> locIdList = new ArrayList<Integer>();

			// 根据cid获取节点对象(SELECT * FROM tb_tree t WHERE t.cid=?)
			InventoryLocation node = inventoryLocService.getLocation(cid);
			if (node != null) {
				locIdList.add(cid);
				// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
				List<InventoryLocation> childTreeNodes = inventoryLocService
						.getLocations(cid);
				if (childTreeNodes != null && childTreeNodes.size() > 0) {
					// 遍历子节点
					if (childTreeNodes != null && childTreeNodes.size() > 0) {
						for (InventoryLocation child : childTreeNodes) {
							locIdList.addAll(getTree(child.getCid()));
						}
					}
				}

			}
			return locIdList;

		}
	 
}
