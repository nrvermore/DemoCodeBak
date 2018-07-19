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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.CompanyType;
import com.labwinner.domain.Inventories;
import com.labwinner.domain.Inventory;
import com.labwinner.domain.MaterialPurchase;
import com.labwinner.domain.ProductSummary;
import com.labwinner.domain.Supplier;
import com.labwinner.domain.SysRoleMenu;
import com.labwinner.domain.SysUser;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.MaterialPurchaseService;
import com.labwinner.service.ProductSummaryService;
import com.labwinner.service.SupplierService;
import com.labwinner.service.SysRoleMenuService;
import com.labwinner.service.SysUserService;
@RestController
public class SupplierController {

	private static Logger logger = LoggerFactory
			.getLogger(SupplierController.class);

	@Autowired
	private SupplierService supplierService;
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private ProductSummaryService productSummaryService;
	
	@Autowired
	private MaterialPurchaseService materialPurchaseService;
	
	@Autowired
	private InventoriesService inventoriesService;
	
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	

	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/supplier/list/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
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
			if(!roleIds.contains(603)){
				res.setErrCode(4);
				res.setErrMsg("find fail!");
			}else{
				try {
					if (page != null && pageSize != null) {
						PageHelper.startPage(page, pageSize);
					}
					List<Supplier> list=new ArrayList<Supplier>();
					if(keyword == null ||  "".equals(keyword)
							|| "undefined".equals(keyword)){
						 list = supplierService.getAll();
					}else{
						list = supplierService.getByName(keyword);
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

	@RequestMapping(value = "/supplier/list", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo res=new ResultVo();
		try {
			
			List<Supplier> list=new ArrayList<Supplier>();
				 list = supplierService.getAll();
			if(list!=null&&list.size()>0){
				res.setResultData(new PageInfo(list));
			}
			res.setErrCode(0);
			res.setErrMsg("find success!");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("find fail!");
		}
		return res;
	}
	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	
	@RequestMapping(value = "/supplier/{id}", method = RequestMethod.GET)
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
		if(roleIds.contains(6)){
			if(!roleIds.contains(603)){
				res.setErrCode(4);
				res.setErrMsg("find fail!");
			}else{
				try {
					List<Supplier> list = supplierService.getAll();
					Supplier supplier = supplierService.getById(id);
					List<Integer> reList=new ArrayList<Integer>();
					if(list!=null&&list.size()>0){
						for(Supplier supp:list){
							reList.add(supp.getSupId());
						}
					}
					if(reList.contains(id)){
						res.setErrCode(0);
						res.setErrMsg("find success");
						res.setResultData(supplier);
					}else{
						res.setErrCode(4);
						res.setErrMsg("find fail!");
					}
					
				} catch (Exception e) {
					res.setErrCode(1);
					res.setErrMsg("find fail");
				}
			}
		}
		
		
		return res;
	}
	

	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/supplier/add", method = RequestMethod.POST)
	public void saveOrUpdate(@RequestBody Supplier supplier) {
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			if(supplier.getSupId()==null){
				supplier.setCreater(sysUser.getRealname());
				supplier.setCreateDate(new Date());
			supplierService.save(supplier);
			}else{
				supplier.setModifier(sysUser.getRealname());
				supplier.setModifyDate(new Date());
				supplierService.update(supplier);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description 删除对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:52:47
	 */
	@RequestMapping(value = "/supplier/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
			supplierService.delete(id);
	}
	
	/**
	 * @Description 删除对象方法
	 * @author llwang
	 * @version V1.0
	 * @date 2017年8月31日 上午11:52:47
	 */
	@RequestMapping(value = "/supplier/isDeleteOrNot/{id}", method = RequestMethod.GET)
	public ResultVo isDeleteOrNot(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		try {
			Boolean flag=false;
			List<ProductSummary> listPS=productSummaryService.getBySupId(id);
			if(listPS!=null&&listPS.size()>0){
				flag=true;
			}
			List<MaterialPurchase> listMP=materialPurchaseService.getBySupId(id);
			if(listMP!=null&&listMP.size()>0){
				flag=true;
			}
			List<Inventories> listInven=inventoriesService.getBySupId(id);
			if(listInven!=null&&listInven.size()>0){
				flag=true;
			}
			if(flag){
				res.setErrCode(1);
				res.setErrMsg("不允许删除该供应商！");
			}else{
				res.setErrCode(0);
				res.setErrMsg("可删除供应商！");
			}
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("程序错误："+e.getMessage());
		}
		return res;
		
	}
	
	@RequestMapping(value = "/supplier/getCompanyType", method = RequestMethod.GET)
	public ResultVo getCompanyType() {
		ResultVo res=new ResultVo();
		try {
			List<CompanyType> list=supplierService.getCompanyType();
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
