package com.labwinner.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.ChemicalImage;
import com.labwinner.domain.ChemicalParameter;
import com.labwinner.domain.Device;
import com.labwinner.domain.ExecuteChemical;
import com.labwinner.domain.Inventories;
import com.labwinner.domain.Inventory;
import com.labwinner.domain.InventoryGroup;
import com.labwinner.domain.InventoryGroups;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.InventoryModify;
import com.labwinner.domain.InventoryUser;
import com.labwinner.domain.Measurement;
import com.labwinner.domain.Message;
import com.labwinner.domain.MessageRecipients;
import com.labwinner.domain.ModifyCode;
import com.labwinner.domain.ModifyType;
import com.labwinner.domain.MsgDetailtype;
import com.labwinner.domain.MsgState;
import com.labwinner.domain.MsgType;
import com.labwinner.domain.Prototype;
import com.labwinner.domain.QrInfo;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.SysUser;
import com.labwinner.jpush.JPushData;
import com.labwinner.service.ChemicalImageService;
import com.labwinner.service.ChemicalParameterService;
import com.labwinner.service.DeviceService;
import com.labwinner.service.ExecuteChemicalService;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.InventoryGroupService;
import com.labwinner.service.InventoryGroupsService;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.service.InventoryModifyService;
import com.labwinner.service.InventoryUserService;
import com.labwinner.service.MaterialTypeService;
import com.labwinner.service.MeasurementService;
import com.labwinner.service.MessageRecipientsService;
import com.labwinner.service.MessageService;
import com.labwinner.service.ModifyCodeService;
import com.labwinner.service.ModifyTypeService;
import com.labwinner.service.PriceCurrencyService;
import com.labwinner.service.PrototypeService;
import com.labwinner.service.QrInfoService;
import com.labwinner.service.ReactionDesignChemicalService;
import com.labwinner.service.SecureRankService;
import com.labwinner.service.SupplierService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.Base64Util;
import com.labwinner.util.QRCreateAndParse;
import com.labwinner.vo.BarcodeVo;
import com.labwinner.vo.ImageVo;
import com.labwinner.vo.InventoryDataVo;
import com.labwinner.vo.InventoryPrototypeVo;
import com.labwinner.vo.InventoryReactionVo;
import com.labwinner.vo.InventoryVo;

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
public class InventoryAPPController {

	private static Logger logger = LoggerFactory
			.getLogger(InventoryAPPController.class);

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
	private QrInfoService qrInfoService;
	@Autowired
	private ReactionDesignChemicalService reactionDesignChemicalService;
	@Autowired
	private ExecuteChemicalService executeChemicalService;
	@Autowired
	private InventoryGroupsService inventoryGroupsService;
	@Autowired
	private ChemicalParameterService chemicalParameterService;
	
	@Autowired
	private PrototypeService prototypeService;
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private InventoryLocationService inventoryLocationService;
	
	@Autowired
	private MessageService  messageService;
	
	@Autowired
	private MessageRecipientsService messageRecipientsService;

	@Value("${web.upload-path}")
	String filePath;
	@Value("${web.qr-path}")
	String qrPath;
	@Value("${web.qrUrl-path}")
	private String urlPath; 
	@Value("${web.print_qr}")
	private String printQrPath;

	/**
	 * @Description 获取所有对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventory/getParameters", method = RequestMethod.GET)
	public ResultVo getParameters() {
		List<InventoryLocation> list = inventoryLocationService.getAllFirst();
		List<InventoryLocation> resList=new ArrayList<InventoryLocation>();
		List<Measurement> measurements =  new ArrayList<Measurement>();
		measurements.addAll(measurementService.getById(1));
		measurements.addAll(measurementService.getById(2));
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				InventoryLocation location = inventoryLocationService.getTree(list.get(i).getCid());
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


	@RequestMapping(value = "/inventory/barCodes", method = RequestMethod.GET)
	public List<String> getBarCodes() {
		// 主表实体类
		List<String> barCodes = inventoriesService.getBarCodes();
		if (barCodes == null) {
			String message = "对象不存在(inventoryName:" + barCodes + ")";
			logger.warn(message);
		}
		return barCodes;
	}

	/**
	 * 关键字搜索
	 * 
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value = "/inventory/find/{keyword}", method = RequestMethod.GET)
	public ResultVo findByName(@PathVariable("keyword") String keyword) {
		
		ResultVo resultVo =  new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		// 主表实体类
		if (roleName.equals("ROLE_TEAM")) {
			List<Inventories> inventoryNames = inventoriesService.findByName(keyword);
			if(inventoryNames!=null && inventoryNames.size()>0){
				for (Inventories inventories : inventoryNames) {
					String batchNumber = inventories.getInventoryGroups().getBatchNumber();
					String supplierName = inventories.getInventoryGroups().getSupplier().getSuprName();
					inventories.setInventoryName(supplierName+"-["+batchNumber+"]-"+inventories.getInventoryName());
				}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(inventoryNames);
			return resultVo;
			}
			resultVo.setErrCode(2);
			resultVo.setErrMsg("find is null");
			return resultVo;
		}else{
			List<Inventories> inventoryNames = inventoriesService.findNameByUser(keyword, userId);
			if(inventoryNames!=null && inventoryNames.size()>0){
				for (Inventories inventories : inventoryNames) {
					String batchNumber = inventories.getInventoryGroups().getBatchNumber();
					String supplierName = inventories.getInventoryGroups().getSupplier().getSuprName();
					inventories.setInventoryName(supplierName+"-["+batchNumber+"]-"+inventories.getInventoryName());
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find successe");
				resultVo.setResultData(inventoryNames);
				return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;
		}

	}

	
	/**
	 * @Description 热门搜索
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventory/getHotWords", method = RequestMethod.GET)
	public ResultVo getHotWords() {
		ResultVo resultVo = new ResultVo();
		List<Inventories> inventoryNames = new ArrayList();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		
		if(roleName.equals("ROLE_TEAM")){
			inventoryNames = inventoriesService.getHotWords();
			if(inventoryNames!=null && inventoryNames.size()>0){
				for (Inventories inventories : inventoryNames) {
					String batchNumber = inventories.getInventoryGroups().getBatchNumber();
					String supplierName = inventories.getInventoryGroups().getSupplier().getSuprName();
					inventories.setInventoryName(supplierName+"-["+batchNumber+"]-"+inventories.getInventoryName());
				}
			
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(inventoryNames);
			return resultVo;
		}else{
			inventoryNames = inventoriesService.getUserHotWords(userId);
			if(inventoryNames!=null && inventoryNames.size()>0){
				for (Inventories inventories : inventoryNames) {
					String batchNumber = inventories.getInventoryGroups().getBatchNumber();
					String supplierName = inventories.getInventoryGroups().getSupplier().getSuprName();
					inventories.setInventoryName(supplierName+"-["+batchNumber+"]-"+inventories.getInventoryName());
				}
			
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(inventoryNames);
			return resultVo;
		}
		
	}

	/**
	 * @Description 扫描二维码
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventory/getQR", method = RequestMethod.POST)
	public ResultVo getQR(@RequestBody BarcodeVo barcodeVo) {

		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		InventoryPrototypeVo inventoryPrototypeVo = new InventoryPrototypeVo();
		String barcode = barcodeVo.getBarcode();
		if(barcode.startsWith("XX")){
			Prototype prototype = prototypeService.findByBarCode(barcode);
			if (prototype==null) {
				String message = "inventory is null";
				logger.error(message);
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;
			}
			
			inventoryPrototypeVo.setPrototype(prototype);
			inventoryPrototypeVo.setType(2);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(inventoryPrototypeVo);
			return resultVo;
			
		}
		else if(barcode.startsWith("DX")){
			Device device = deviceService.getByBarcode(barcode);
			inventoryPrototypeVo.setDevice(device);
			inventoryPrototypeVo.setType(3);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(inventoryPrototypeVo);
			return resultVo;
		}else if(barcode.startsWith("LX")){
			inventoryPrototypeVo.setType(4);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(inventoryPrototypeVo);
			return resultVo;
		}
		else if(barcode.startsWith("SX")){
			inventoryPrototypeVo.setType(5);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			resultVo.setResultData(inventoryPrototypeVo);
			return resultVo;
		}
		else{
			Inventories inventory = inventoriesService.findByBarCode(barcode);
			
			if(inventory != null){
				 InventoryGroups inventoryGroups = inventoryGroupsService.getById(inventory.getInventoryGroups().getGroupId());
				 List<InventoryUser> users = new ArrayList<InventoryUser>(inventoryGroups.getInventoryUsers());
				 List<Integer> idList = new ArrayList<Integer>();
				 if(users!=null && users.size()>0){
					 for (InventoryUser inventoryUser : users) {
						 idList.add(inventoryUser.getSysUser().getUserId());
					 }
				 }
				//有权限的返回库存信息
				if(roleName.equals("ROLE_TEAM")||inventoryGroups.getSecureRank().getSecureRankId()==1 ||(inventoryGroups.getSecureRank().getSecureRankId()==2 && idList.contains(userId))){
					inventoryPrototypeVo.setInventory(inventory);
					inventoryPrototypeVo.setType(1);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find successe");
					resultVo.setResultData(inventoryPrototypeVo);
					return resultVo;
				}
			}
			resultVo.setErrCode(2);
			resultVo.setErrMsg("find is null");
			return resultVo;
		}

		
	}

	/**
	 * @Description 生成二维码
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventory/createQR", method = RequestMethod.POST)
	public ResultVo createQR(@RequestParam("barcode") String barcode) {

		try {
			QRCreateAndParse qr = new QRCreateAndParse();
			ResultVo resultVo = new ResultVo();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find successe");
			qr.createQr(barcode, qrPath,null);
			return resultVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description 搜索二维码
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventory/findQR", method = RequestMethod.POST)
	public ResultVo findQR(@RequestBody List<Integer> idList) {
		ResultVo resultVo = new ResultVo();
		List<QrInfo> qrInfoList = new ArrayList<QrInfo>();
		if (idList.size() <= 0) {
			String message = "idList is null";
			logger.error(message);
		}
		for (Integer id : idList) {
			QrInfo qrInfo = qrInfoService.getById(id);
			if (qrInfo!=null) {
				if(!new File(qrPath+qrInfo.getQrName()).exists()){
					Inventories inventories = inventoriesService.getById(qrInfo.getInventories().getInventoryId());
					QRCreateAndParse qr = new QRCreateAndParse();
					qr.createQr(inventories.getBarCode(), qrPath,qrInfo.getQrName());
				}
				qrInfo.setQrPath(urlPath + qrInfo.getQrName());
				qrInfoList.add(qrInfo);
			}
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find successe");
		resultVo.setResultData(qrInfoList);
		return resultVo;
	}
	
	/**
	 * @Description web打印组二维码
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/inventory/getGroupQR/{id}", method = RequestMethod.GET)
	public ResultVo getGroupQR(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List<QrInfo> qrInfoList = new ArrayList<QrInfo>();
		List<Integer> idList = new ArrayList<Integer>();
		
		List<Inventories> list = inventoriesService.getByGroupId(id,null);
		for (Inventories inventories : list) {
			idList.add(inventories.getInventoryId());
		}
		for (Integer inventoryId : idList) {
			QrInfo qrInfo = qrInfoService.getById(inventoryId);
			if (qrInfo!=null) {
				if(!new File(qrPath+qrInfo.getQrName()).exists()){
					Inventories inventories = inventoriesService.getById(qrInfo.getInventories().getInventoryId());
					QRCreateAndParse qr = new QRCreateAndParse();
					qr.createQr(inventories.getBarCode(), qrPath,qrInfo.getQrName());
				}
				qrInfo.setQrPath(urlPath + qrInfo.getQrName());
				qrInfoList.add(qrInfo);
			}
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find successe");
		resultVo.setResultData(qrInfoList);
		return resultVo;
	}
	
	/**
	 * @Description 发送打印组二维码消息
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventory/sendPrintMsg/{id}", method = RequestMethod.GET)
	public ResultVo sendPrintMsg(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		InventoryGroups inventorgGroups = inventoryGroupsService.getById(id);
		
		List<SysUser> roleUsers = sysUserService.getUsers("ROLE_ADMIN");
		
		printQrPath = printQrPath+id;
		
		Message message=new Message();
		message.setMessageTitle(sysUser.getRealname()+"保存库存:"+inventorgGroups.getInventoryName()+"成功,请打印二维码！");
		String content="";
		content=sysUser.getRealname()+"保存库存  "+inventorgGroups.getInventoryName()+"成功,请点击此处打印二维码！"+"<br>"+"<a style='text-decoration: underline;color: blue;' href="+printQrPath+">打印二维码</a>";
		message.setMessageContent(content);
		MsgType msgType=new MsgType();
		msgType.setMsgTypeId(2);
		message.setMsgType(msgType);
		MsgDetailtype msgDetailtype=new MsgDetailtype();
//		msgDetailtype.setMsgDetailtypeId(8);
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

}
