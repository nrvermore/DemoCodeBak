package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Approvel;
import com.labwinner.domain.ApprovelRefuse;
import com.labwinner.domain.MaterialPurchase;
import com.labwinner.domain.OrderState;
import com.labwinner.domain.PriceCurrency;
import com.labwinner.domain.SysRole;
import com.labwinner.domain.SysRoleMenu;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ApprovelService;
import com.labwinner.service.MaterialPurchaseService;
import com.labwinner.service.SysRoleMenuService;
import com.labwinner.service.SysRoleService;
import com.labwinner.service.SysUserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 采购审批Controller
 * @author llwang
 * @version V1.0
 * @date 2017年8月17日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class ApprovelController {

	@Autowired
	private ApprovelService approvelService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
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
	@RequestMapping(value = "/Approvel/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAll(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		ResultVo resultVo = new ResultVo();
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
			if(!roleIds.contains(602)){
				resultVo.setErrCode(4);
				resultVo.setErrMsg("find fail!");
			}else{
				try {
					List<Approvel> list=new ArrayList<Approvel>();
					SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(sysUser.getSysRole().getRoleId())));
					String roleName=sysRole.getRoleName();
					if (page != null && pageSize != null) {
						PageHelper.startPage(page, pageSize);
					}
					if(keyword == null ||  "".equals(keyword)
							|| "undefined".equals(keyword)){
						 list = approvelService.getAll(roleName,sysUser.getUserId());
					}else{
						list = approvelService.getByOrderNumber(roleName,sysUser.getUserId(),keyword);
					}
					//Map<String, Object> map=new HashMap<String, Object>();
					//map.put("approvelList", list);
					//map.put("sysRole", sysRole);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success!");
					resultVo.setResultData(new PageInfo(list));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}else{
			resultVo.setErrCode(4);
			resultVo.setErrMsg("find fail!");	
		}
		
		
		
		return resultVo;
	}

	/**
	 * @Description 根据主键获取对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/Approvel/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		List<SysRoleMenu> sysRoles=sysRoleMenuService.getByRoleId(sysUser.getSysRole().getRoleId().longValue());
		List<Integer> roleIds=new ArrayList<Integer>();
		if(sysRoles!=null&&sysRoles.size()>0){
			for(SysRoleMenu sysRoleMenu:sysRoles){
				roleIds.add(sysRoleMenu.getSysMenu().getMenuId());
			}
		}
		if(roleIds.contains(6)){
			if(!roleIds.contains(602)){
				resultVo.setErrCode(4);
				resultVo.setErrMsg("find fail!");
			}else{
				try {
					Approvel approvel=approvelService.getById(id);
					SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(sysUser.getSysRole().getRoleId())));
					String roleName=sysRole.getRoleName();
					 List<Approvel> list= approvelService.getAll(roleName,sysUser.getUserId());
					 List<Integer> appList=new ArrayList<Integer>();
					 if(list!=null&&list.size()>0){
						 for(Approvel app:list){
							 appList.add(app.getApproveId());
						 } 
					 }
					if(appList.contains(id)){
						if(approvel.getApprovelRefuse()!=null&&approvel.getApprovelRefuse().getRefuseId()-4==0){
							approvel.getApprovelRefuse().setRefuseMemu(approvel.getApprovalOpinion());
						}
						//SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(sysUser.getSysRole().getRoleId())));
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("approvel", approvel);
						map.put("sysRole", sysRole);
						resultVo.setErrCode(0);
						resultVo.setErrMsg("find success!");
						resultVo.setResultData(map);
					}else{
						resultVo.setErrCode(4);
						resultVo.setErrMsg("find fail!");
					}
					
				} catch (Exception e) {
					resultVo.setErrCode(1);
					resultVo.setErrMsg("find failed!");
				}
			}
		}else{
			resultVo.setErrCode(4);
			resultVo.setErrMsg("find fail!");
		}
		return resultVo;
	}
	
	/**
	 * @Description 根据主键获取对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/Approvel/orderState", method = RequestMethod.GET)
	public ResultVo getOrderState() {
		ResultVo resultVo = new ResultVo();
		try {
			List<Map<String, Object>> orderState=new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
			orderState=approvelService.getOrderState();
			for(int i=0;i<orderState.size();i++){
				if(!"1".equals(orderState.get(i).get("order_state_id").toString())&&!"3".equals(orderState.get(i).get("order_state_id").toString())){
					res.add(orderState.get(i));
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success!");
			resultVo.setResultData(res);
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find failed!");
		}
		return resultVo;
	}
	/**
	 * @Description 根据主键获取对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/Approvel/refuse", method = RequestMethod.GET)
	public ResultVo getRefuseList() {
		ResultVo resultVo = new ResultVo();
		try {
			List<Map<String, Object>> approvelRefuse=new ArrayList<Map<String, Object>>();
			//List<ApprovelRefuse> approvelRefuse=new ArrayList<ApprovelRefuse>();
			approvelRefuse=approvelService.getRefuseList();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success!");
			resultVo.setResultData(approvelRefuse);
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find failed!");
		}
		return resultVo;
	}

	/**
	 * @Description 保存对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/Approvel", method = RequestMethod.POST)
	public Integer save(@RequestBody Approvel Approvel) {
		
		return approvelService.save(Approvel);
	}

	/**
	 * @Description 更新对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/Approvel", method = RequestMethod.PUT)
	public void update(@RequestBody Approvel approvel) {
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			approvel.setAppSysUser(sysUser);
			approvel.setApprovelDate(new Date());
			approvelService.update(approvel);
			MaterialPurchase materialPurchase=materialPurchaseService.getById(approvel.getMaterialPurchase().getMatPurId());
			if(approvel.getApprovalResult().getApprovalResultId()-1==0){
				OrderState orderState=new OrderState();
				orderState.setOrderStateId(2);
				materialPurchase.setOrderState(orderState);
				materialPurchaseService.update(materialPurchase);
			}else if(approvel.getApprovalResult().getApprovalResultId()-2==0){
				OrderState orderState=new OrderState();
				orderState.setOrderStateId(3);
				materialPurchase.setOrderState(orderState);
				materialPurchaseService.update(materialPurchase);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @Description 更新对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/Approvel/cheeckForEdit", method = RequestMethod.PUT)
	public void cheeckForEdit(@RequestBody Approvel approvel) {
		try {
			MaterialPurchase materialPurchase=materialPurchaseService.getById(approvel.getMaterialPurchase().getMatPurId());
			PriceCurrency priceCurrency=new PriceCurrency();
			priceCurrency.setPriCurId(approvel.getMaterialPurchase().getMatPriceCurrency().getPriCurId());
			materialPurchase.setAllPrice(approvel.getMaterialPurchase().getAllPrice());
			materialPurchase.setTaxRate(approvel.getMaterialPurchase().getTaxRate());
			materialPurchase.setPurchaseRemarks(approvel.getMaterialPurchase().getPurchaseRemarks());
			materialPurchase.setMaterialPrice(approvel.getMaterialPurchase().getMaterialPrice());
			materialPurchase.setMatPriceCurrency(priceCurrency);
			materialPurchase.setCount(approvel.getMaterialPurchase().getCount());
			materialPurchase.setPurchaseDate(approvel.getMaterialPurchase().getPurchaseDate());
			materialPurchaseService.update(materialPurchase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @Description 更新对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/Approvel/materialPurchase", method = RequestMethod.PUT)
	public void updateMaterialPurchase(@RequestBody MaterialPurchase materialPurchase) {
		try {
			MaterialPurchase mp=materialPurchaseService.getById(materialPurchase.getMatPurId());
			mp.setOrderState(materialPurchase.getOrderState());
			materialPurchaseService.update(mp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @Description 删除对象
	 * @author llwang
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/Approvel/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		try {
			Approvel approvel=approvelService.getById(id);
			approvelService.delete(id);
			materialPurchaseService.delete(approvel.getMaterialPurchase().getMatPurId());
			res.setErrCode(0);
			res.setErrMsg("删除成功");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("删除失败");
		}
		return res;
	}	

}
