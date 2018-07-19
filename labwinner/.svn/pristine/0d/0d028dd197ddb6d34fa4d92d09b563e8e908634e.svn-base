package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.ChemicalParameter;
import com.labwinner.domain.CompanyType;
import com.labwinner.domain.Inventory;
import com.labwinner.domain.InventoryGroup;
import com.labwinner.domain.MaterialPurchase;
import com.labwinner.domain.ProductSummary;
import com.labwinner.domain.ProductType;
import com.labwinner.domain.Supplier;
import com.labwinner.domain.SysRoleMenu;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ChemicalParameterService;
import com.labwinner.service.MaterialPurchaseService;
import com.labwinner.service.ProductSummaryService;
import com.labwinner.service.SupplierService;
import com.labwinner.service.SysRoleMenuService;
import com.labwinner.service.SysUserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 附件Controller
 * @author llwang
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class ProductSummaryController {

	@Autowired
	private ProductSummaryService productSummaryService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private ChemicalParameterService chemicalParameterService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private MaterialPurchaseService materialPurchaseService;
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * @Description 获取所有对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/productSummary/list/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAll(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		ResultVo res=new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		List<SysRoleMenu> sysRoles=sysRoleMenuService.getByRoleId(sysUser.getSysRole().getRoleId().longValue());
		List<Integer> roleIds=new ArrayList<Integer>();
		if(sysRoles!=null&&sysRoles.size()>0){
			for(SysRoleMenu sysRoleMenu:sysRoles){
				roleIds.add(sysRoleMenu.getSysMenu().getMenuId());
			}
		}
		if(roleIds.contains(6)){
			if(!roleIds.contains(604)){
				res.setErrCode(4);
				res.setErrMsg("find fail!");
			}else{
				try {
					if (page != null && pageSize != null) {
						PageHelper.startPage(page, pageSize);
					}
					List<ProductSummary> list=new ArrayList<ProductSummary>();
					if(keyword == null ||  "".equals(keyword)
							|| "undefined".equals(keyword)){
						 list = productSummaryService.getAll();
					}else{
						list = productSummaryService.getByName(keyword);
					}
					//if(list!=null&&list.size()>0){
						res.setResultData(new PageInfo(list));
					//}
					res.setErrCode(0);
					res.setErrMsg("find success!");
				} catch (Exception e) {
					res.setErrCode(1);
					res.setErrMsg("find fail!");
				}
			}	
		}else{
			res.setErrCode(4);
			res.setErrMsg("find fail!");
		}
		return res;
	}

	/**
	 * @Description 根据主键获取对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/productSummary/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		List<SysRoleMenu> sysRoles=sysRoleMenuService.getByRoleId(sysUser.getSysRole().getRoleId().longValue());
		List<Integer> roleIds=new ArrayList<Integer>();
		if(sysRoles!=null&&sysRoles.size()>0){
			for(SysRoleMenu sysRoleMenu:sysRoles){
				roleIds.add(sysRoleMenu.getSysMenu().getMenuId());
			}
		}
//		if(roleIds.contains(6)){
//			if(!roleIds.contains(604)){
//				res.setErrCode(4);
//				res.setErrMsg("find fail!");
//			}else{
				try {
					ProductSummary	productSummary=productSummaryService.getById(id);
					List<ProductSummary> list= productSummaryService.getAll();
					List<Integer> resList=new ArrayList<Integer>();
					if(list!=null&&list.size()>0){
						for(ProductSummary product:list){
							resList.add(product.getProductSummaryId());
						}
					}
					if(resList.contains(id)){
						res.setErrCode(0);
						res.setErrMsg("find success!");
						res.setResultData(productSummary);
					}else{
						res.setErrCode(4);
						res.setErrMsg("find fail!");
					}
				} catch (Exception e) {
					res.setErrCode(1);
					res.setErrMsg("find failed!");
					e.printStackTrace();
				}
//			}
//		}else{
//			res.setErrCode(4);
//			res.setErrMsg("find fail!");
//		}
		
		return res;
	}

	/**
	 * @Description 保存对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/productSummary", method = RequestMethod.POST)
	public Integer save(@RequestBody ProductSummary productSummary) {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		
		if(productSummary.getChemicalParameter().getChParId()!=null &&productSummary.getSupplier().getSupId()!=null
				&&productSummary.getCatalogueCode()!=null){
			List<ProductSummary> productSummaries =productSummaryService.getAllProduct();
			for (ProductSummary productSummary2 : productSummaries) {
				if(productSummary.getChemicalParameter().getChParId()-productSummary2.getChemicalParameter().getChParId()==0
						&& productSummary.getSupplier().getSupId()-productSummary2.getSupplier().getSupId()==0
						&&productSummary.getCatalogueCode().equals(productSummary2.getCatalogueCode())){
					return productSummary2.getProductSummaryId();
				}
			}
			
		}
		
		if(productSummary.getChemicalParameter().getChParId()==null){
			ChemicalParameter chemicalParameter=new ChemicalParameter();
			chemicalParameter.setChineseName(productSummary.getChemicalParameter().getChineseName());
			chemicalParameter.setCreater(sysUser.getRealname());
			chemicalParameter.setFlag(1);
			if(productSummary.getChemicalParameter().getCas()==null||"".equals(productSummary.getChemicalParameter().getCas())){
				chemicalParameter.setCas("暂无");
			}else{
				chemicalParameter.setCas(productSummary.getChemicalParameter().getCas());
			}
			chemicalParameterService.save(chemicalParameter);
			chemicalParameter.setCreateDate(new Date());
			productSummary.setChemicalParameter(chemicalParameter);
			
		}
		if(productSummary.getSupplier().getSupId()==null){
			Supplier supplier=new Supplier();
			supplier.setSuprName(productSummary.getSupplier().getSuprName());
//			supplier.setUrl(productSummary.getSupplier().getUrl());
			supplier.setCreater(sysUser.getRealname());
			supplier.setCreateDate(new Date());
			supplierService.save(supplier);
			productSummary.setSupplier(supplier);
		}
		return productSummaryService.save(productSummary);
	}

	
	/**
	 * @Description 根据主键获取对象
	 * @author llwangi
	 * @version V1.0
	 * @date 2017年8月11日
	 */
	@RequestMapping(value = "/productSummary/getChemical/{keyword}", method = RequestMethod.GET)
	public ResultVo getChemicalByNmae(@PathVariable("keyword") String keyword) {
		ResultVo res=new ResultVo();
		try {
			List<Map<String, Object>> listChemical=new ArrayList<Map<String, Object>>();
			if(!"undefined".equals(keyword)){
				listChemical=  productSummaryService.getChemical(keyword);
			}
//			List<Map<String, Object>> resList=new ArrayList<Map<String, Object>>();
//			if(listChemical!=null&&listChemical.size()>0){
//				for(int i=0;i<listChemical.size();i++ ){
//					if(keyword.equals(String.valueOf(listChemical.get(i).get("chinese_name")))){
//						resList.add(listChemical.get(i));
//					}
//				}
//			}
//			if(resList.size()==0){
//				if(listChemical.size()>5){
//				resList.add(listChemical.get(0));
//				resList.add(listChemical.get(1));
//				resList.add(listChemical.get(2));
//				resList.add(listChemical.get(3));
//				resList.add(listChemical.get(4));
//				}else{
//				resList=listChemical;
//				}
//			}else{
//				if(listChemical.size()>5){
//					resList.add(listChemical.get(1));
//					resList.add(listChemical.get(2));
//					resList.add(listChemical.get(3));
//					resList.add(listChemical.get(4));
//				}else{
//					resList=listChemical;
//				}
//			}
			res.setResultData(listChemical);
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
	@RequestMapping(value = "/productSummary/getSupplier/{keyword}", method = RequestMethod.GET)
	public ResultVo getSupplierByNmae(@PathVariable("keyword") String keyword) {
		ResultVo res=new ResultVo();
		try {
			List<Map<String, Object>> listSupplier=new ArrayList<Map<String, Object>>();
			if(!"undefined".equals(keyword)){
				listSupplier=  productSummaryService.getSupplier(keyword);
			}
			res.setResultData(listSupplier);
			res.setErrCode(0);
			res.setErrMsg("获取信息成功");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("获取信息失败："+e.getMessage());
		}
		return res;
	}
	
	/**
	 * @Description 化学品名称查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年8月11日
	 */
	@RequestMapping(value = "/productSummary/findByChemicalName/{page}/{pageSize}/{id}", method = RequestMethod.GET)
	public ResultVo findByChemical(@PathVariable("id") Integer id,
			@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize) {
		ResultVo res=new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		
		List<ProductSummary> list=productSummaryService.findByChemical(id);
		if(list!=null){
			res.setResultData(new PageInfo(list));
			res.setErrCode(0);
			res.setErrMsg("find success");
			return res;
		}
		res.setErrCode(1);
		res.setErrMsg("find is null");
		return res;
		
	}
	
	
	/**
	 * @Description 化学品名称查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年8月11日
	 */
	@RequestMapping(value = "/productSummary/findChemicalName/{keyword}", method = RequestMethod.GET)
	public ResultVo findChemicalName(@PathVariable("keyword") String keyword) {
		ResultVo res=new ResultVo();
		
		List<ProductSummary> list=productSummaryService.findByChemicalName(keyword);
		if(list!=null){
			res.setResultData(list);
			res.setErrCode(0);
			res.setErrMsg("find success");
			return res;
		}
		res.setErrCode(1);
		res.setErrMsg("find is null");
		return res;
		
	}
	
	/**
	 * @Description 化学品名称查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年8月11日
	 */
	@RequestMapping(value = "/productSummary/findAPPChemical/{page}/{pageSize}/{id}", method = RequestMethod.GET)
	public ResultVo findAPPChemical(@PathVariable("id") Integer id,
			@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize) {
		ResultVo res=new ResultVo();
		
		List<ProductSummary> list=productSummaryService.findByChemical(id);
		if(list!=null){
			int total = list.size();
			double c = (((double) total) /pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page+1);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			int num = list.size() > page * pageSize ? page * pageSize: list.size();
			if(page<=d){
				pageEntity.setList(list.subList(0, num));
			}
			res.setResultData(pageEntity);
			res.setErrCode(0);
			res.setErrMsg("find success");
			return res;
		}
		res.setErrCode(1);
		res.setErrMsg("find is null");
		return res;
		
	}
	
	/**
	 * @Description 化学品名称联想查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年8月11日
	 */
	@RequestMapping(value = "/productSummary/findByKeyword/{keyword}", method = RequestMethod.GET)
	public ResultVo findByKeyword(@PathVariable("keyword") String keyword) {
		ResultVo res=new ResultVo();
		List<ProductSummary> list=productSummaryService.findByKeyword(keyword);
		
		if(list!=null && list.size()>0){
			res.setResultData(list);
			res.setErrCode(0);
			res.setErrMsg("find success");
			return res;
		}
			res.setErrCode(2);
			res.setErrMsg("find is null");
			return res;
	}
	
	/**
	 * @Description 化学品名称联想查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年8月11日
	 */
	@RequestMapping(value = "/productSummary/findProjects/{supId}/{chParId}", method = RequestMethod.GET)
	public ResultVo findByKeyword(@PathVariable("supId") Integer supId,
			@PathVariable("chParId") Integer chParId) {
		ResultVo res=new ResultVo();
		
		List<ProductSummary> list=productSummaryService.getByChParId(chParId,supId);
		if(list!=null){
			res.setResultData(list);
			res.setErrCode(0);
			res.setErrMsg("find success");
			return res;
		}
		res.setErrCode(1);
		res.setErrMsg("find is null");
		return res;
	}
	
	
	/**
	 * @Description 更新对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/productSummary", method = RequestMethod.PUT)
	public void update(@RequestBody ProductSummary productSummary) {
		
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		if(productSummary.getSupplier().getSupId()==null){
			Supplier supplier=new Supplier();
			supplier.setSuprName(productSummary.getSupplier().getSuprName());
//			supplier.setUrl(productSummary.getSupplier().getUrl());
			supplier.setCreater(sysUser.getRealname());
			supplier.setCreateDate(new Date());
			supplierService.save(supplier);
			productSummary.setSupplier(supplier);
		}
//		else{
//			Supplier supplier=supplierService.getById(productSummary.getSupplier().getSupId());
//			supplier.setUrl(productSummary.getSupplier().getUrl());
//			supplierService.update(supplier);
//			productSummary.setSupplier(supplier);
//		}
		//supplierService.update(productSummary.getSupplier());
		productSummaryService.update(productSummary);
	}

	/**
	 * @Description 删除对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/productSummary/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		productSummaryService.delete(id);
	}
	
	
	/**
	 * @Description 删除对象方法
	 * @author llwang
	 * @version V1.0
	 * @date 2017年8月31日 上午11:52:47
	 */
	@RequestMapping(value = "/productSummary/isDeleteOrNot/{id}", method = RequestMethod.GET)
	public ResultVo isDeleteOrNot(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		try {
			Boolean flag=false;
			List<MaterialPurchase> listMP=materialPurchaseService.getByProductSummaryId(id);
			List<Integer> listInven=materialPurchaseService.getByProductId(id);
			if(listMP!=null&&listMP.size()>0){
				flag=true;
			}
			if(listInven!=null&&listInven.size()>0){
				flag=true;
			}
			if(flag){
				res.setErrCode(1);
				res.setErrMsg("不允许删除该产品！");
			}else{
				res.setErrCode(0);
				res.setErrMsg("可删除该产品！");
			}
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("程序错误："+e.getMessage());
		}
		return res;
		
	}
	
	
	@RequestMapping(value = "/productSummary/getProductType", method = RequestMethod.GET)
	public ResultVo getCompanyType() {
		ResultVo res=new ResultVo();
		try {
			List<ProductType> list=productSummaryService.getProductType();
			res.setErrCode(0);
			res.setErrMsg("find success!");
			res.setResultData(list);
//			}
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("find fail!");
			e.printStackTrace();
		}
		return res;
	}

}
