package com.labwinner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.SysAgencyLogo;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.service.SysAgencyLogoService;
import com.labwinner.service.SysUserService;

@RestController
public class SysAgencyLogoController {

	private static Logger logger = LoggerFactory
			.getLogger(SysAgencyLogoController.class);

	@Autowired
	private SysAgencyLogoService sysAgencyLogoService;
	@Autowired
	private SysUserService sysUserService;
	@Value("${logo.url-path}")
	private String urlPath;
	
	@Value("${logo.upload-path}")
	String filePath;
	
	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	
	@RequestMapping(value = "/sysAgencyLogo/getById", method = RequestMethod.GET)
	public ResultVo getById() {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysSigningAgency agency= sysUser.getSysSigningAgency();
		Integer agencyId = agency.getAgencyId();
		
		SysAgencyLogo sysAgencyLogo = sysAgencyLogoService.getById(agencyId);
		if(sysAgencyLogo!=null){
			if(sysAgencyLogo.getLogoName()!=null){
				sysAgencyLogo.setLogoName(urlPath+sysAgencyLogo.getLogoName());
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("get success");
			resultVo.setResultData(sysAgencyLogo);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("sysAgencyLogo is null");
		return resultVo;
		
	}
}
