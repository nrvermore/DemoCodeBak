package com.labwinner.controller;

import java.io.File;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.ChemicalImage;
import com.labwinner.domain.ExecuteChemical;
import com.labwinner.domain.ExecuteChemicalGroup;
import com.labwinner.domain.Inventories;
import com.labwinner.domain.InventoryGroups;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.InventoryModify;
import com.labwinner.domain.Measurement;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.ModifyType;
import com.labwinner.domain.MsgDetailtype;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.MsgType;
import com.labwinner.domain.SolutionDosages;
import com.labwinner.domain.SolutionEntity;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ExecuteSolutionService;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.InventoryGroupsService;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.service.InventoryModifyService;
import com.labwinner.service.MeasurementService;
import com.labwinner.service.MessageRecipientsService;
import com.labwinner.service.MessageService;
import com.labwinner.service.SolutionDosagesService;
import com.labwinner.service.SolutionService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.MergeUtil;
import com.labwinner.util.PdfUtil;
import com.labwinner.util.QRCreateAndParse;

/**
 * @Description 溶液Controller
 * @author xux
 * @version V1.0
 * @date 2017年6月6日 上午10:36:15
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class SolutionController {
	
	private static Logger logger = LoggerFactory
			.getLogger(SolutionController.class);

	@Autowired
	private SolutionService solutionService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private InventoriesService inventoriesService;
	@Autowired
	private InventoryGroupsService inventoryGroupsService;
	@Autowired
	private InventoryModifyService inventoryModifyService;
	@Autowired
	private MeasurementService measurementService;
	@Autowired
	private ExecuteSolutionService executeSolutionService;
	
	@Autowired
	private SolutionDosagesService solutionDosagesService;
	
	@Autowired
	private InventoryLocationService inventoryLocationService;
	
	@Autowired
	private MessageService  messageService;
	
	@Autowired
	private MessageRecipientsService messageRecipientsService;
	
	@Value("${web.qr-path}")
	String qrPath;
	@Value("${web.qrUrl-path}")
	private String urlPath; 
	@Value("${web.upload_path_pdf}")
	private String pdfPath; 
	@Value("${web.agency_pdf}")
	private String pdfLine; 
	@Value("${solution.print_qr}")
	private String printQrPath;
	@Value("${web.url-path}")
	private String chemicalUrlPath;
	
	
	/**
	 * @Description 条件模糊查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/solution/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {
		ResultVo resultVo = new ResultVo();
		
		// 获取当前用户
//		LoginController login = new LoginController();
//		SysUser sysUser = sysUserService
//				.getByUsername(login.getPrincipal()).get(0);
//		Integer userId = sysUser.getUserId();
//		String roleName = sysUser.getSysRole().getRoleName();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<SolutionEntity> list = solutionService.getBykeyword(keyword);
			for (SolutionEntity solutionEntity : list) {
				
				InventoryGroups soluteInventoryGroups = inventoryGroupsService.getById(solutionEntity.getSoluteInventories().getGroupId());
				if(soluteInventoryGroups!=null){
					Measurement measure = soluteInventoryGroups.getMeasurement();
					if(measure!=null){
						Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
						soluteInventoryGroups.setMeasurement(measureMent);
					}
					solutionEntity.setSoluteInventories(soluteInventoryGroups);
				}
				InventoryGroups solventInventoryGroups = inventoryGroupsService.getById(solutionEntity.getSolventInventories().getGroupId());
				if(solventInventoryGroups!=null){
					Measurement measure = solventInventoryGroups.getMeasurement();
					if(measure!=null){
						Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
						solventInventoryGroups.setMeasurement(measureMent);
					}
					solutionEntity.setSolventInventories(solventInventoryGroups);
				}
				if(solutionEntity.getQrName()!=null){
					if(!new File(qrPath+solutionEntity.getQrName()).exists()){
						QRCreateAndParse qr = new QRCreateAndParse();
						qr.createQr(solutionEntity.getBarcode(), qrPath,solutionEntity.getQrName());
					}
					solutionEntity.setQrName(urlPath+solutionEntity.getQrName());
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(list));
			return resultVo;
		}
		else {
			List<SolutionEntity> list = solutionService.getAll();
			for (SolutionEntity solutionEntity : list) {
				
				InventoryGroups soluteInventoryGroups = inventoryGroupsService.getById(solutionEntity.getSoluteInventories().getGroupId());
				if(soluteInventoryGroups!=null){
					Measurement measure = soluteInventoryGroups.getMeasurement();
					if(measure!=null){
						Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
						soluteInventoryGroups.setMeasurement(measureMent);
					}
					solutionEntity.setSoluteInventories(soluteInventoryGroups);
				}
				InventoryGroups solventInventoryGroups = inventoryGroupsService.getById(solutionEntity.getSolventInventories().getGroupId());
				if(solventInventoryGroups!=null){
					Measurement measure = solventInventoryGroups.getMeasurement();
					if(measure!=null){
						Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
						solventInventoryGroups.setMeasurement(measureMent);
					}
					solutionEntity.setSolventInventories(solventInventoryGroups);
				}
				if(solutionEntity.getQrName()!=null){
					if(!new File(qrPath+solutionEntity.getQrName()).exists()){
						QRCreateAndParse qr = new QRCreateAndParse();
						qr.createQr(solutionEntity.getBarcode(), qrPath,solutionEntity.getQrName());
					}
					solutionEntity.setQrName(urlPath+solutionEntity.getQrName());
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(list));
			return resultVo;
		}
	}
	
	
	/**
	 * @Description 条件模糊查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/solutionAppList/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAppListByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {
		ResultVo resultVo = new ResultVo();
		
		// 获取当前用户
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<SolutionEntity> list = solutionService.getBykeyword(keyword);
			
			for (SolutionEntity solutionEntity : list) {
				
				InventoryGroups soluteInventoryGroups = inventoryGroupsService.getById(solutionEntity.getSoluteInventories().getGroupId());
				if(soluteInventoryGroups!=null){
					Measurement measure = soluteInventoryGroups.getMeasurement();
					if(measure!=null){
						Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
						soluteInventoryGroups.setMeasurement(measureMent);
					}
					solutionEntity.setSoluteInventories(soluteInventoryGroups);
				}
				InventoryGroups solventInventoryGroups = inventoryGroupsService.getById(solutionEntity.getSolventInventories().getGroupId());
				if(solventInventoryGroups!=null){
					Measurement measure = solventInventoryGroups.getMeasurement();
					if(measure!=null){
						Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
						solventInventoryGroups.setMeasurement(measureMent);
					}
					solutionEntity.setSolventInventories(solventInventoryGroups);
				}
				if(solutionEntity.getQrName()!=null){
					if(!new File(qrPath+solutionEntity.getQrName()).exists()){
						QRCreateAndParse qr = new QRCreateAndParse();
						qr.createQr(solutionEntity.getBarcode(), qrPath,solutionEntity.getQrName());
					}
					solutionEntity.setQrName(urlPath+solutionEntity.getQrName());
				}
				
				if(solutionEntity!=null&&solutionEntity.getSolutionLocation()!=null){
					InventoryLocation location = solutionEntity.getSolutionLocation();
					String pName = getAllPname(location.getCid());
					location.setParentName(pName);
					location.setLabel(location.getParentName()+">"+location.getLabel());
				}
			}
			int total = list.size();
			double c = (((double) total) / pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page + 1);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			int num = list.size() > page * pageSize ? page * pageSize
					: list.size();
			if (page <= d) {
				pageEntity.setList(list.subList(0, num));
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(pageEntity);
			return resultVo;
		}
		else {
			List<SolutionEntity> list = solutionService.getAll();
			
			for (SolutionEntity solutionEntity : list) {
				
				InventoryGroups soluteInventoryGroups = inventoryGroupsService.getById(solutionEntity.getSoluteInventories().getGroupId());
				if(soluteInventoryGroups!=null){
					Measurement measure = soluteInventoryGroups.getMeasurement();
					if(measure!=null){
						Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
						soluteInventoryGroups.setMeasurement(measureMent);
					}
					solutionEntity.setSoluteInventories(soluteInventoryGroups);
				}
				InventoryGroups solventInventoryGroups = inventoryGroupsService.getById(solutionEntity.getSolventInventories().getGroupId());
				if(solventInventoryGroups!=null){
					Measurement measure = solventInventoryGroups.getMeasurement();
					if(measure!=null){
						Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
						solventInventoryGroups.setMeasurement(measureMent);
					}
					solutionEntity.setSolventInventories(solventInventoryGroups);
				}
				if(solutionEntity.getQrName()!=null){
					if(!new File(qrPath+solutionEntity.getQrName()).exists()){
						QRCreateAndParse qr = new QRCreateAndParse();
						qr.createQr(solutionEntity.getBarcode(), qrPath,solutionEntity.getQrName());
					}
					solutionEntity.setQrName(urlPath+solutionEntity.getQrName());
				}
				
				if(solutionEntity!=null&&solutionEntity.getSolutionLocation()!=null){
					InventoryLocation location = solutionEntity.getSolutionLocation();
					String pName = getAllPname(location.getCid());
					location.setParentName(pName);
					location.setLabel(location.getParentName()+">"+location.getLabel());
				}
			}
			int total = list.size();
			double c = (((double) total) / pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page + 1);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			int num = list.size() > page * pageSize ? page * pageSize
					: list.size();
			if (page <= d) {
				pageEntity.setList(list.subList(0, num));
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(pageEntity);
			return resultVo;
		}
	}
	
	
	/**
	 * 详情接口
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/solution/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		
		SolutionEntity solutionEntity =  solutionService.getById(id);
		if(solutionEntity!=null){
			InventoryGroups soluteInventoryGroups = inventoryGroupsService.getById(solutionEntity.getSoluteInventories().getGroupId());
			if(soluteInventoryGroups!=null){
				
				List<ChemicalImage> images = soluteInventoryGroups.getChemicalImages();
				if(images!=null && images.size()>0){
					for (ChemicalImage chemicalImage : images) {
						chemicalImage.setDissolvantDescribe(chemicalUrlPath+chemicalImage.getDissolvantDescribe());
					}
				}
				
				Measurement solutionMeasure =solutionEntity.getSolutionMeasurement();
				if(solutionMeasure!=null){
					Measurement measureMent = measurementService.getByMeasureUnitId(solutionMeasure.getMeasureUnitId());
					solutionEntity.setSolutionMeasurement(measureMent);
				}
				
				Measurement measure = soluteInventoryGroups.getMeasurement();
				if(measure!=null){
					Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
					soluteInventoryGroups.setMeasurement(measureMent);
				}
				solutionEntity.setSoluteInventories(soluteInventoryGroups);
			}
			InventoryGroups solventInventoryGroups = inventoryGroupsService.getById(solutionEntity.getSolventInventories().getGroupId());
			if(solventInventoryGroups!=null){
				Measurement measure = solventInventoryGroups.getMeasurement();
				if(measure!=null){
					Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
					solventInventoryGroups.setMeasurement(measureMent);
				}
				solutionEntity.setSolventInventories(solventInventoryGroups);
			}
			if(solutionEntity.getQrName()!=null){
				if(!new File(qrPath+solutionEntity.getQrName()).exists()){
					QRCreateAndParse qr = new QRCreateAndParse();
					qr.createQr(solutionEntity.getBarcode(), qrPath,solutionEntity.getQrName());
				}
				solutionEntity.setQrName(urlPath+solutionEntity.getQrName());
			}
			
			if(solutionEntity!=null&&solutionEntity.getSolutionLocation()!=null){
				InventoryLocation location = solutionEntity.getSolutionLocation();
				String pName = getAllPname(location.getCid());
				location.setParentName(pName);
				location.setLabel(location.getParentName()+">"+location.getLabel());
			}
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(solutionEntity);
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("find is null");
		return resultVo;
		
	}
	
	/**
	 * delete
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/solution/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		SolutionEntity solutionEntity =  solutionService.getById(id);
		List<Integer> solutionIds = executeSolutionService.getSolutions();
		if(solutionEntity!=null){
			Integer creater = solutionEntity.getCreater();
			Integer cid = creater;
			if ((cid-userId!=0) && !roleName.equals("ROLE_TEAM")) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("您无权删除此溶液");
				return resultVo;
			}
			else if(solutionIds.contains(id)){
				resultVo.setErrCode(1);
				resultVo.setErrMsg("实验用到无法删除溶液");
				return resultVo;
			}
			else{
				String qrName = solutionEntity.getQrName();
				new File(qrPath + qrName).delete();
				solutionDosagesService.delete(id);
				solutionService.delete(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("溶液删除成功");
				return resultVo;
			}
			
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("无此溶液 ");
		return resultVo;
	}
	
	
	/**
	 * 新增or更新
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/solution", method = RequestMethod.POST)
	public ResultVo save(@RequestBody SolutionEntity solutionEntity) {
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		Double oldConversion =0.0;
		Double newConversion =0.0;
		if(solutionEntity.getSolutionId()==null ||solutionEntity.getSolutionId()==0 ){
			String barCode = createBarcode();
			solutionEntity.setBarcode(barCode);
			QRCreateAndParse qr = new QRCreateAndParse();
			String qrName = qr.createQr(barCode, qrPath,null);
			solutionEntity.setQrName(qrName);
			solutionEntity.setCreater(userId);
			solutionEntity.setCreateDate(new Date());
			solutionService.save(solutionEntity);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success!");
		}else{
			SolutionEntity oldSolutionEntity = solutionService.getById(solutionEntity.getSolutionId());
			
			Measurement oldSolutionMeasurement = oldSolutionEntity.getSolutionMeasurement();
			Measurement oldMeasureMent = measurementService.getByMeasureUnitId(oldSolutionMeasurement.getMeasureUnitId());
			oldConversion = oldMeasureMent.getConversionRelation();
			
			Measurement newSolutionMeasurement = solutionEntity.getSolutionMeasurement();
			Measurement newMeasureMent = measurementService.getByMeasureUnitId(newSolutionMeasurement.getMeasureUnitId());
			newConversion = newMeasureMent.getConversionRelation();
			solutionEntity.setSolutionTotal(oldSolutionEntity.getSolutionTotal()+solutionEntity.getSolutionTotal()*(newConversion/oldConversion));
			solutionEntity.setSolutionMeasurement(oldSolutionMeasurement);
			solutionEntity.setModifier(userId);
			solutionEntity.setModifyDate(new Date());
			solutionService.update(solutionEntity);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update success!");
		}
		saveSolutionInventory(solutionEntity);
		updateInventory(solutionEntity);
		
		resultVo.setResultData(solutionEntity.getSolutionId());
		return resultVo;
		
	}
	
	
	@RequestMapping(value = "/solution", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody SolutionEntity solutionEntity) {
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		solutionEntity.setModifier(userId);
		solutionEntity.setModifyDate(new Date());
		solutionService.update(solutionEntity);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("update success!");
		return resultVo;
	}
	
	/**
	 * 扫码获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/solution/getByBarcode/{barcode}", method = RequestMethod.GET)
	public ResultVo getByBarcode(@PathVariable("barcode") String barcode) {

		ResultVo resultVo = new ResultVo();
		SolutionEntity solutionEntity = solutionService.getByBarcode(barcode);
		
		if(solutionEntity!=null){
			InventoryGroups soluteInventoryGroups = inventoryGroupsService.getById(solutionEntity.getSoluteInventories().getGroupId());
			if(soluteInventoryGroups!=null){
				
				List<ChemicalImage> images = soluteInventoryGroups.getChemicalImages();
				if(images!=null && images.size()>0){
					for (ChemicalImage chemicalImage : images) {
						chemicalImage.setDissolvantDescribe(chemicalUrlPath+chemicalImage.getDissolvantDescribe());
					}
				}
				
				Measurement solutionMeasure =solutionEntity.getSolutionMeasurement();
				if(solutionMeasure!=null){
					Measurement measureMent = measurementService.getByMeasureUnitId(solutionMeasure.getMeasureUnitId());
					solutionEntity.setSolutionMeasurement(measureMent);
				}
				
				Measurement measure = soluteInventoryGroups.getMeasurement();
				if(measure!=null){
					Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
					soluteInventoryGroups.setMeasurement(measureMent);
				}
				solutionEntity.setSoluteInventories(soluteInventoryGroups);
			}
			InventoryGroups solventInventoryGroups = inventoryGroupsService.getById(solutionEntity.getSolventInventories().getGroupId());
			if(solventInventoryGroups!=null){
				Measurement measure = solventInventoryGroups.getMeasurement();
				if(measure!=null){
					Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
					solventInventoryGroups.setMeasurement(measureMent);
				}
				solutionEntity.setSolventInventories(solventInventoryGroups);
			}
			if(solutionEntity.getQrName()!=null){
				if(!new File(qrPath+solutionEntity.getQrName()).exists()){
					QRCreateAndParse qr = new QRCreateAndParse();
					qr.createQr(solutionEntity.getBarcode(), qrPath,solutionEntity.getQrName());
				}
				solutionEntity.setQrName(urlPath+solutionEntity.getQrName());
			}
			
			if(solutionEntity!=null&&solutionEntity.getSolutionLocation()!=null){
				InventoryLocation location = solutionEntity.getSolutionLocation();
				String pName = getAllPname(location.getCid());
				location.setParentName(pName);
				location.setLabel(location.getParentName()+">"+location.getLabel());
			}
			
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(solutionEntity);
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("find is null");
		return resultVo;
		
	}
	
	
	
	/**
	 * @Description 生成条形码
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	public String createBarcode() {

		Random random = new Random();
		int a = random.nextInt(999999999) + 1;

		String barCode = "SX" + a;
		List<String> barCodes = solutionService.getBarCodes();
		boolean flat = true;
		flat = barCodes.contains(barCode);
		if (flat) {
			createBarcode();
		}
		return barCode;
	}
	
	/**
	 * @Description 溶液库存保存
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	public void saveSolutionInventory(SolutionEntity solutionEntity) {
		List<SolutionDosages> soluteDosages = solutionEntity.getSolutionDosages();
		List<Integer> inventoryList = new ArrayList<Integer>();
		
		List<SolutionDosages> oldSolutionDosages = solutionDosagesService.getBySolutionId(solutionEntity.getSolutionId());
		for (SolutionDosages solutionDosages1 : oldSolutionDosages) {
			inventoryList.add(solutionDosages1.getInventoryId());
		}
		
		for (SolutionDosages solutionDosages : soluteDosages) {
			if(!inventoryList.contains(solutionDosages.getInventoryId())){
				solutionDosages.setSolutionId(solutionEntity.getSolutionId());
				solutionDosagesService.save(solutionDosages);
			}
			
		}
	}
	
	/**
	 * @Description 实验用量变更库存
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	public void updateInventory(SolutionEntity solutionEntity) {

		List<SolutionDosages> soluteDosages = solutionEntity.getSolutionDosages();
		Double soluteConversion =0.0;
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		try {
				for (SolutionDosages solutionDosages : soluteDosages) {
					Inventories inventory = inventoriesService.getById(solutionDosages.getInventoryId());
					Double inventoryConversion = inventory.getMeasurement().getConversionRelation();
					Measurement measure = solutionDosages.getMeasurement();
					if(measure!=null){
						Measurement measureMent = measurementService.getByMeasureUnitId(measure.getMeasureUnitId());
						soluteConversion = measureMent.getConversionRelation();
					}
					
					Double soluteDosage =  Double.valueOf(solutionDosages.getDosage())*(soluteConversion/inventoryConversion);
					inventory.setActAvaWei(inventory.getActAvaWei()- soluteDosage);
					inventory.setModifier(userId + "");
					inventory.setModifyDate(new Date());
					
					InventoryModify inventoryModify = new InventoryModify();
					ModifyType modifyType = new ModifyType();
					modifyType.setModifyTypeId(1);

					inventoryModify.setModifyType(modifyType);
					inventoryModify.setInventories(inventory);
					inventoryModify.setMeasurement(solutionDosages.getMeasurement());
					inventoryModify.setChangeReason("溶液变更");
					inventoryModify.setChangeDate(new Date());
					inventoryModify.setModifier(sysUser);
					inventoryModify.setModifyDate(new Date());
					inventoryModify.setModifyAfter(inventory.getActAvaWei());

					inventoryModifyService.save(inventoryModify);
					inventoriesService.updateInventory(inventory);
					//变更库存组重量
					InventoryGroups inventoryGroups = inventory.getInventoryGroups();
					inventoryGroups.setTotalWei(inventoryGroups.getTotalWei()-soluteDosage);
					inventoryGroupsService.update(inventoryGroups);
					
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	 @RequestMapping(value = "/solutionPdfForPrint/{id}", method = RequestMethod.GET)
		public ResultVo createPdfForPrint(@PathVariable("id") Integer id) throws Exception {
			ResultVo resultVo = new ResultVo();
				List<String> list=new ArrayList<String>();
				SolutionEntity solution = solutionService.getById(id);
				if(solution!=null&&solution.getSolutionLocation()!=null){
					InventoryLocation location = solution.getSolutionLocation();
					String pName = getAllPname(location.getCid());
					location.setParentName(pName);
					location.setLabel(location.getParentName()+">"+location.getLabel());
				}
				if(solution!=null&&solution.getQrName()!=null){
					if(!new File(qrPath+solution.getQrName()).exists()){
						QRCreateAndParse qr = new QRCreateAndParse();
						qr.createQr(solution.getBarcode(), qrPath,solution.getQrName());
					}
					solution.setQrName(urlPath+solution.getQrName());
				}
					
				PdfUtil pdfUtil=new PdfUtil();
				Map<String, Object> map =new HashMap<String, Object>();
				map.put("name", solution.getSolutionName());
				map.put("startTime", solution.getComDate());
				map.put("endTime", solution.getMaturityDate());
				
				if(solution.getConcentration()!=null){
					map.put("concentration", solution.getConcentration()+" "+solution.getConcentrationMeasurement().getMeasureUnit());
				}
				
				if(solution.getQrName()!=null){
					String path=solution.getQrName();
					String picPath=qrPath+path.substring(path.lastIndexOf("/")+1,path.length());
					map.put("iamgePath", picPath);
				}
				String filename="print"+UUID.randomUUID().toString()+".pdf";
				String outUrl=pdfPath+filename;
				if(pdfUtil.exportPdf(map, outUrl, "solution.pdf")){
					resultVo.setErrCode(0);
					resultVo.setErrMsg("生成pdf成功");
					resultVo.setResultData(pdfLine+filename);
					
				}else{
					resultVo.setErrCode(1);	
					resultVo.setErrMsg("生成pdf报错1");
					return resultVo;
				}
					
			return resultVo;
		}
	 
	 
	 /**
		 * @Description 发送打印组二维码消息
		 * @author xux
		 * @version V1.0
		 * @date 2017年5月18日 上午11:49:52
		 */
		@RequestMapping(value = "/solution/sendPrintMsg/{id}", method = RequestMethod.GET)
		public ResultVo sendPrintMsg(@PathVariable("id") Integer id) {
			ResultVo resultVo = new ResultVo();
			
			// 获取当前用户
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			SolutionEntity solution = solutionService.getById(id);
			
			List<SysUser> roleUsers = sysUserService.getUsers("ROLE_ADMIN");
			
			printQrPath = printQrPath+id;
			
			Message message=new Message();
			message.setMessageTitle(sysUser.getRealname()+"新增溶液:"+solution.getSolutionName()+"成功,请打印二维码！");
			String content="";
			content=sysUser.getRealname()+"新增溶液"+solution.getSolutionName()+"成功,请点击此处打印二维码！"+"<br>"+"<a style='text-decoration: underline;color: blue;' href="+printQrPath+">打印二维码</a>";
			message.setMessageContent(content);
			MsgType msgType=new MsgType();
			msgType.setMsgTypeId(2);
			message.setMsgType(msgType);
			MsgDetailtype msgDetailtype=new MsgDetailtype();
//			msgDetailtype.setMsgDetailtypeId(8);
			message.setMsgDetailtype(msgDetailtype);
			message.setSysUser(roleUsers.get(0));
			message.setSenderDate(new Date());
			message.setFlag(0);
			messageService.save(message);
			
			MessageRecipients messageRecipients=new MessageRecipients();
			MsgState msgState=new MsgState();
			msgState.setMsgId(1);
			messageRecipients.setMsgState(msgState);
			messageRecipients.setFlag(0);
			messageRecipients.setMessage(message);
			messageRecipients.setSysUser(sysUser);
			messageRecipientsService.save(messageRecipients); 
			
			 List<Integer> idList=new ArrayList<Integer>();
			 idList.add(sysUser.getUserId());
			resultVo.setErrCode(0);
			resultVo.setErrMsg("send mes successe");
			return resultVo;
		
		}
	 
	 
	 public String getAllPname(Integer id) {
		    try {
		      String ss="";
		    loop:for(int i=0;i<10;i++){
		      InventoryLocation inventoryLocation =  inventoryLocationService.getById(id);
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


}
