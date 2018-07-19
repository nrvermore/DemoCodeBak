package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.ApprovalResult;
import com.labwinner.domain.Approvel;
import com.labwinner.domain.ChemicalParameter;
import com.labwinner.domain.JournalArticle;
import com.labwinner.domain.MaterialPurchase;
import com.labwinner.domain.OrderState;
import com.labwinner.domain.ProductSummary;
import com.labwinner.domain.Supplier;
import com.labwinner.domain.SysRole;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ApprovelService;
import com.labwinner.service.ChemicalParameterService;
import com.labwinner.service.MaterialPurchaseService;
import com.labwinner.service.ProductSummaryService;
import com.labwinner.service.SupplierService;
import com.labwinner.service.SysRoleService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.OrderNumberUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 附件Controller
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class MaterialPurchaseController {

	@Autowired
	private MaterialPurchaseService materialPurchaseService;

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private  SysRoleService sysRoleService;
	
	@Autowired
	private ProductSummaryService productSummaryService;
	@Autowired
	private ChemicalParameterService chemicalParameterService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private ApprovelService approvelService;
	
	
	@RequestMapping(value = "/materialPurchase/message/{id}", method = RequestMethod.GET)
	public ResultVo getAll(@PathVariable("id") Integer id ) {
		ResultVo res=new ResultVo();
		try {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(sysUser.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		ChemicalParameter cp=new ChemicalParameter();
		cp=chemicalParameterService.getById(id);
		ChemicalParameter chemicalParameter=new ChemicalParameter();
		chemicalParameter.setChineseName(cp.getChineseName());
		chemicalParameter.setEnglishName(cp.getEnglishName());
		List<MaterialPurchase> materialPurchaseList=new ArrayList<MaterialPurchase>();
		materialPurchaseList=materialPurchaseService.getByName(sysUser.getUserId(),id,roleName);
		List<MaterialPurchase> resList=new ArrayList<MaterialPurchase>();
		if(materialPurchaseList!=null&&materialPurchaseList.size()>0){
			for(int i=0;i<materialPurchaseList.size();i++){
				Integer productSummaryId=materialPurchaseList.get(i).getProductSummary().getProductSummaryId();
				Integer supId=materialPurchaseList.get(i).getSupplier().getSupId();
				List<MaterialPurchase> matList=materialPurchaseService.getMaterialList(productSummaryId,supId,roleName,sysUser.getUserId());
				if(matList!=null&&matList.size()>0){
					for(int k=0;k<matList.size();k++){
						resList.add(matList.get(k));
					}
				}else{
					resList.add(materialPurchaseList.get(i));
				}
			}
		}
			Map<String, Object> resMap=new HashMap<String, Object>();
			resMap.put("materialList", resList);
			resMap.put("sysRole", sysRole);
			resMap.put("chemicalParameter", chemicalParameter);
			res.setResultData(resMap);
			res.setErrCode(0);
			res.setErrMsg("获取信息成功");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("获取信息失败："+e.getMessage());
		}
		return res;
	}
	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/materialPurchase/messagePages/{id}/{page}/{pageSize}", method = RequestMethod.GET)
	public ResultVo getAllmessagePages(@PathVariable("id") Integer id ,@PathVariable("page") Integer page ,@PathVariable("pageSize") Integer pageSize ) {
		ResultVo res=new ResultVo();
		try {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(sysUser.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		ChemicalParameter cp=new ChemicalParameter();
		cp=chemicalParameterService.getById(id);
		ChemicalParameter chemicalParameter=new ChemicalParameter();
		chemicalParameter.setChineseName(cp.getChineseName());
		chemicalParameter.setEnglishName(cp.getEnglishName());
		List<MaterialPurchase> materialPurchaseList=new ArrayList<MaterialPurchase>();
		materialPurchaseList=materialPurchaseService.getByName(sysUser.getUserId(),id,roleName);
		List<MaterialPurchase> resList=new ArrayList<MaterialPurchase>();
		if(materialPurchaseList!=null&&materialPurchaseList.size()>0){
			for(int i=0;i<materialPurchaseList.size();i++){
				Integer productSummaryId=materialPurchaseList.get(i).getProductSummary().getProductSummaryId();
				Integer supId=materialPurchaseList.get(i).getSupplier().getSupId();
				List<MaterialPurchase> matList=materialPurchaseService.getMaterialList(productSummaryId,supId,roleName,sysUser.getUserId());
				if(matList!=null&&matList.size()>0){
					for(int k=0;k<matList.size();k++){
						resList.add(matList.get(k));
					}
				}else{
					resList.add(materialPurchaseList.get(i));
				}
			}
		}
		PageEntity pageEntity = new PageEntity();
		if(resList!=null){
			int total = resList.size();
			double c = (((double) total) /pageSize);
			int d = (int) Math.ceil(c);
			pageEntity.setPageNum(page+1);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			int num = resList.size() > page * pageSize ? page * pageSize: resList.size();
			if(page<=d){
				pageEntity.setList(resList.subList(0, num));
			}
		}
			Map<String, Object> resMap=new HashMap<String, Object>();
			resMap.put("materialList", pageEntity);
			resMap.put("sysRole", sysRole);
			resMap.put("chemicalParameter", chemicalParameter);
			res.setResultData(resMap);
			res.setErrCode(0);
			res.setErrMsg("获取信息成功");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("获取信息失败："+e.getMessage());
		}
		return res;
	}

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/materialPurchase/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		try {
			MaterialPurchase resData=  materialPurchaseService.getById(id);
			res.setResultData(resData);
			res.setErrCode(0);
			res.setErrMsg("获取信息成功");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("获取信息失败："+e.getMessage());
		}
		return res;
	}

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/materialPurchase/product/{page}/{pageSize}/{id}/{keyword}", method = RequestMethod.GET)
	public ResultVo getProductById(@PathVariable("id") Integer id,@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		ResultVo res=new ResultVo();
		try {
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			List<ProductSummary> list=new ArrayList<ProductSummary>();
			if(keyword == null ||  "".equals(keyword)
					|| "undefined".equals(keyword)){
				 list = productSummaryService.getAllBySupId(id);
			}else{
				list = productSummaryService.getByNameBySupId(id,keyword);
			}
			res.setErrCode(0);
			res.setErrMsg("find success!");
			res.setResultData(new PageInfo(list));
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("获取信息失败："+e.getMessage());
		}
		return res;
	}
	/**
	 * @Description 根据主键获取对象
	 * @author llwangi
	 * @version V1.0
	 * @date 2017年8月11日
	 */
	@RequestMapping(value = "/materialPurchase/getHotPurchase", method = RequestMethod.GET)
	public ResultVo getById() {
		ResultVo res=new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(sysUser.getSysRole().getRoleId())));
			List<Map<String, Object>> listPurchase=new ArrayList<Map<String,Object>>();
			listPurchase=  materialPurchaseService.getHotPurchase(sysUser.getUserId());
			
			Map<String, Object> resMap=new HashMap<String, Object>();
			resMap.put("listPurchase", listPurchase);
			resMap.put("sysRole", sysRole);
			res.setResultData(resMap);
			res.setErrCode(0);
			res.setErrMsg("获取信息成功");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("获取信息失败："+e.getMessage());
		}
		return res;
	}
	
	/**
	 * @Description 根据主键获取对象
	 * @author llwangi
	 * @version V1.0
	 * @date 2017年8月11日
	 */
	@RequestMapping(value = "/materialPurchase/getChemical/{keyword}", method = RequestMethod.GET)
	public ResultVo getChemicalByNmae(@PathVariable("keyword") String keyword) {
		ResultVo res=new ResultVo();
		try {
			List<String> listChemical=new ArrayList<String>();
			if(!"undefined".equals(keyword)){
				listChemical=  materialPurchaseService.getChemical(keyword);
			}
			List<String> resList=new ArrayList<String>();
			if(listChemical!=null&&listChemical.size()>0){
				for(int i=0;i<listChemical.size();i++ ){
					if(keyword.equals(listChemical.get(i))){
						resList.add(listChemical.get(i));
					}
				}
			}
			if(resList.size()==0){
				if(listChemical.size()>5){
				resList.add(listChemical.get(0));
				resList.add(listChemical.get(1));
				resList.add(listChemical.get(2));
				resList.add(listChemical.get(3));
				resList.add(listChemical.get(4));
				}else{
				resList=listChemical;
				}
			}else{
				if(listChemical.size()>5){
					resList.add(listChemical.get(1));
					resList.add(listChemical.get(2));
					resList.add(listChemical.get(3));
					resList.add(listChemical.get(4));
				}else{
					resList=listChemical;
				}
			}
			res.setResultData(resList);
			res.setErrCode(0);
			res.setErrMsg("获取信息成功");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("获取信息失败："+e.getMessage());
		}
		return res;
	}
	
	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/materialPurchase", method = RequestMethod.POST)
	public ResultVo save(@RequestBody MaterialPurchase materialPurchase) {
		ResultVo res=new ResultVo();
		//MaterialPurchase materialPurchaseForSave=materialPurchase;
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		try {
			if(materialPurchase.getProductSummary().getChemicalParameter().getChParId()==null){
				ChemicalParameter chemicalParameter=new ChemicalParameter();
				chemicalParameter.setChineseName(materialPurchase.getProductSummary().getChemicalParameter().getChineseName());
				chemicalParameter.setCas(materialPurchase.getProductSummary().getChemicalParameter().getCas());
				chemicalParameter.setCreater(sysUser.getRealname());
				chemicalParameter.setCreateDate(new Date());
				chemicalParameter.setFlag(1);
				chemicalParameterService.save(chemicalParameter);
				ProductSummary productSummary=materialPurchase.getProductSummary();
				productSummary.setChemicalParameter(chemicalParameter);
				Supplier supplier=materialPurchase.getSupplier();
				if(supplier.getSupId()==null){
					supplier.setCreater(sysUser.getRealname());
					supplier.setCreateDate(new Date());
					supplierService.save(supplier);
				}
				productSummary.setSupplier(supplier);
				productSummary.setQuotedDate(new Date());
				productSummaryService.save(productSummary); 
				materialPurchase.setProductSummary(productSummary);
			}else{
				ProductSummary productSummary=materialPurchase.getProductSummary();
				Supplier supplier=materialPurchase.getSupplier();
				if(supplier.getSupId()==null){
					supplier.setCreater(sysUser.getRealname());
					supplier.setCreateDate(new Date());
					supplierService.save(supplier);
					productSummary.setSupplier(supplier);
					productSummary.setQuotedDate(new Date());
					productSummaryService.save(productSummary); 
				}else{
					List<ProductSummary> prodList=productSummaryService.getByChParId(materialPurchase.getProductSummary().getChemicalParameter().getChParId(), supplier.getSupId());
					//ProductSummary prodList=productSummaryService.getById(materialPurchase.getProductSummary().getProductSummaryId());
					if(prodList!=null&&prodList.size()>0){
						for(int i=0;i<prodList.size();i++){
							if(prodList.get(i).getProductSummaryId()-materialPurchase.getProductSummary().getProductSummaryId()==0){
								productSummary=prodList.get(i);
							}
						}
					}else{
						productSummary.setSupplier(supplier);
						productSummaryService.save(productSummary); 
					}
				}
				materialPurchase.setProductSummary(productSummary);
			}
			OrderState orderState=new OrderState();
			orderState.setOrderStateId(1);
			OrderNumberUtil orderNumberUtil=new OrderNumberUtil();
			String orderNumber=orderNumberUtil.getOrderIdByUUId();
			materialPurchase.setOrderNumber(orderNumber);
			materialPurchase.setOrderState(orderState);
			materialPurchase.setSysUser(sysUser);
			materialPurchase.setCreater(sysUser.getRealname());
			materialPurchase.setCreateDate(new Date());
			materialPurchaseService.save(materialPurchase);
			Approvel approvel=new Approvel();
			ApprovalResult approvalResult=new ApprovalResult();
			approvalResult.setApprovalResultId(3);
			approvel.setApprovalResult(approvalResult);
			//materialPurchaseForSave.setMatPurId(12);
			approvel.setMaterialPurchase(materialPurchase);
			approvelService.save(approvel);
			res.setErrCode(0);
			res.setErrMsg("保存信息成功");
			
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("保存数据出错:"+e.getMessage());
		}
		return res;
		
	}

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/materialPurchase", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody MaterialPurchase materialPurchase) {
		ResultVo res=new ResultVo();
		try {
			materialPurchaseService.update(materialPurchase);
			//res.setResultData(resData);
			res.setErrCode(0);
			res.setErrMsg("保存信息成功");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("获取信息失败："+e.getMessage());
		}
		return res;
	}

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/materialPurchase/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		try {
			materialPurchaseService.delete(id);
			//res.setResultData(resData);
			res.setErrCode(0);
			res.setErrMsg("保存信息成功");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("获取信息失败："+e.getMessage());
		}
		return res;
		
	}
	
	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/materialPurchase/getOrders/{supId}/{productId}", method = RequestMethod.GET)
	public ResultVo getOrders(@PathVariable("supId") Integer supId,
			@PathVariable("productId") Integer productId) {
		ResultVo res=new ResultVo();
		try {
			List<MaterialPurchase> resData=  materialPurchaseService.getOrders(supId, productId);
			res.setResultData(resData);
			res.setErrCode(0);
			res.setErrMsg("获取信息成功");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("获取信息失败："+e.getMessage());
		}
		return res;
	}

}
