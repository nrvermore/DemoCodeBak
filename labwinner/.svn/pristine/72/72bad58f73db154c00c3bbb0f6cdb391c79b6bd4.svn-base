package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.SysMenu;
import com.labwinner.domain.SysUser;
import com.labwinner.service.SysMenuService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 菜单Controller
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysMenu", method = RequestMethod.GET)
	public List<SysMenu> getAll() {
		return sysMenuService.getAll();
	}
	
	
	@RequestMapping(value = "/sysMenu/tree", method = RequestMethod.GET)
	public ResultVo getAllTree() {
		ResultVo res=new ResultVo();
		try {
			List<SysMenu> list = sysMenuService.getAllFirst();
			List<SysMenu> resList=new ArrayList<SysMenu>();
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					SysMenu sysMenu = sysMenuService.getTree(list.get(i).getMenuId());
					if(sysMenu!=null){
						resList.add(sysMenu);
					}
				}
			}
			res.setErrCode(0);
			res.setErrMsg("find success");
			res.setResultData(resList);
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("find failed! message:"+e.getMessage());
		}
		return res;
	}
	/**
	 * @Description 根据查询条件获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysMenu/{page}/{pageSize}/{filter}", method = RequestMethod.GET)
	@ResponseBody
	public PageInfo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String filter) {
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		List<SysMenu> list = new ArrayList<SysMenu>();
		if (filter != null && filter != "" && !"undefined".equals(filter)) {
			list = sysMenuService.getAllPageable(filter);
		} else {
			list = sysMenuService.getAll();
		}
		return new PageInfo(list);
	}
	

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysMenu/{id}", method = RequestMethod.GET)
	public SysMenu getById(@PathVariable("id") Long id) {
		return sysMenuService.getById(id);
	}

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysMenu", method = RequestMethod.POST)
	public Integer save(@RequestBody SysMenu sysMenu) {
		sysMenu.setFlag(0);
		return sysMenuService.save(sysMenu);
	}

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysMenu", method = RequestMethod.PUT)
	public void update(@RequestBody SysMenu sysMenu) {
		sysMenuService.update(sysMenu);
	}

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysMenu/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
//		sysMenuService.delete(id);
		sysMenuService.updateFlag(id, 1);
	}

}
