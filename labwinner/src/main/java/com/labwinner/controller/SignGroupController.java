package com.labwinner.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.SignGroup;
import com.labwinner.domain.SysUser;
import com.labwinner.service.SignGroupService;
import com.labwinner.service.SysUserService;




@RestController
public class SignGroupController {

	private static Logger logger = LoggerFactory
			.getLogger(SignGroupController.class);


	@Autowired
	private SignGroupService signGroupService;
	
	@Autowired
	private SysUserService sysUserService;
	/**
	 * @Description 根据查询条件获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	@RequestMapping(value = "/sysUser/setSignGroup/{userList}", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo setSignGroup( @PathVariable String userList) {
		ResultVo resultVo = new ResultVo();
		try {
			signGroupService.deleteAll();
			if(userList!=null&&!"".equals(userList)){
				String[] ss=userList.split(",");
				for(int k=0;k<ss.length;k++){
					SignGroup signGroup=new SignGroup();
					signGroup.setCreateDate(new Date());
					SysUser sysUser=new SysUser();
					sysUser.setUserId(Integer.valueOf(ss[k]));
					signGroup.setSysUser(sysUser);
					signGroupService.save(signGroup);
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
		} catch (Exception e) {
			// TODO: handle exception
		}
			return resultVo;
		
	}
	
	
	/**
	 * @Description 根据查询条件获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	@RequestMapping(value = "/sysUser/getSignGroup", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getSignGroup( ) {
		ResultVo resultVo = new ResultVo();
		try {
			 List<SysUser> list = sysUserService.getAll();
			List<SignGroup> signGroups=signGroupService.getAll();
			Map<String, Object> resMap=new HashMap<String, Object>();
			resMap.put("userList",list);
			resMap.put("signGroups",signGroups);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(resMap);
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find failed");
		}
			return resultVo;
		
	}
	
	
	 
	
	
	
	

}
