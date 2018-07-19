package com.labwinner.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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









import com.labwinner.common.ResultVo;
import com.labwinner.domain.SysUser;
import com.labwinner.service.JournalArticleService;
import com.labwinner.service.SysUserService;
;



@RestController
public class FindPortController {

	private static Logger logger = LoggerFactory
			.getLogger(FindPortController.class);

	@Autowired
	private JournalArticleService journalArticleService;
	
	@Autowired
	private  SysUserService sysUserService;

	/**
	 * @Description 根据实验主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/findPort", method = RequestMethod.GET)
	public ResultVo getByProcessId(@RequestParam("webPort") String webPort) {

		ResultVo resultVo = new ResultVo();
		List<Map<String, Object>> port = journalArticleService.findPort(webPort);
		if (port == null) {
			String message = "对象不存在(id:" + webPort + ")";
			logger.warn(message);
			return null;
		}

		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(port);
		return resultVo;
	}
	
	
	/**
	 * @Description 根据实验主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/getAgencyContents", method = RequestMethod.GET)
	public ResultVo getAgencyContents() {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		int angencyId=sysUser.getSysSigningAgency().getAgencyId();
		List<Map<String, Object>> port = journalArticleService.getAgencyContents(angencyId);
		if (port == null) {
			String message = "对象不存在";
			logger.warn(message);
			return null;
		}

		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(port);
		return resultVo;
	}
	
	
	/**
	 * @Description 根据实验主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/getAgencyContentsWeb", method = RequestMethod.GET)
	public ResultVo getAgencyContentsWeb() {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		int angencyId=sysUser.getSysSigningAgency().getAgencyId();
		List<Map<String, Object>> port = journalArticleService.getAgencyContents(angencyId);
		List<Map<String, Object>> maps=new ArrayList<Map<String,Object>>();
		if (port == null) {
			String message = "对象不存在";
			logger.warn(message);
			return null;
		}else{
			String testfile="";
			String agencyName="";
			String userImage="";
			String download_path_pdf="";
			String url_path_pdf="";
			String deviceImg="";
			String test_analyze="";
			String proDoc="";
			String projeLogo="";
			String expertImg="";
			for(Map<String, Object> map:port){
				if(map.get("contents_name").equals("testfile")){
					testfile=map.get("contents_value").toString();
				}
				if(map.get("contents_name").equals("agencyName")){
					agencyName=map.get("contents_value").toString();
				}
				if(map.get("contents_name").equals("user_Image")){
					userImage=map.get("contents_value").toString();
				}
				if(map.get("contents_name").equals("pdf_Path")){
					download_path_pdf=map.get("contents_value").toString();
				}
				if(map.get("contents_name").equals("image_path")){
					url_path_pdf=map.get("contents_value").toString();
				}
				if(map.get("contents_name").equals("device_image")){
					deviceImg=map.get("contents_value").toString();
				}
				if(map.get("contents_name").equals("analyze_test")){
					test_analyze=map.get("contents_value").toString();
				}
				if(map.get("contents_name").equals("pro_doc")){
					proDoc=map.get("contents_value").toString();
				}
				if(map.get("contents_name").equals("pro_logo")){
					projeLogo=map.get("contents_value").toString();
				}
				if(map.get("contents_name").equals("expert_iamge")){
					expertImg=map.get("contents_value").toString();
				}
			}
		
			
			if(userImage!=null){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("contents_name", "userImage");
				map.put("contents_value", "http://" +testfile+"/"+agencyName+userImage);
				maps.add(map);
			}
			
			if(download_path_pdf!=null){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("contents_name", "download_path_pdf");
				map.put("contents_value","http://" +testfile+"/"+agencyName+download_path_pdf);
				maps.add(map);
			}
			if(url_path_pdf!=null){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("contents_name", "url_path_pdf");
				map.put("contents_value", "http://" +testfile+"/"+agencyName+url_path_pdf);
				maps.add(map);
			}
			
			if(deviceImg!=null){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("contents_name", "deviceImg");
				map.put("contents_value", "http://" +testfile+"/"+agencyName+deviceImg);
				maps.add(map);
			}
			if(test_analyze!=null){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("contents_name", "test_analyze");
				map.put("contents_value", "http://" +testfile+"/"+agencyName+test_analyze);
				maps.add(map);
			}
			if(proDoc!=null){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("contents_name", "proDoc");
				map.put("contents_value", testfile+"/"+agencyName+proDoc);
				maps.add(map);
			}
			if(projeLogo!=null){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("contents_name", "projeLogo");
				map.put("contents_value", "http://" +testfile+"/"+agencyName+projeLogo);
				maps.add(map);
			}
			
			if(expertImg!=null){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("contents_name", "expertImg");
				map.put("contents_value","http://" + testfile+"/"+agencyName+expertImg);
				maps.add(map);
			}
			
		}

		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(maps);
		return resultVo;
	}

	/**
	 * @Description 根据实验主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/getStaticFile", method = RequestMethod.GET)
	public ResultVo getStaticFile() {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		int angencyId=sysUser.getSysSigningAgency().getAgencyId();
		Map<String, Object> port = journalArticleService.getStaticFile();

		

		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(port);
		return resultVo;
	}
	
	
	/**
	 * @Description 根据实验主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/findPort/getAgencyPic", method = RequestMethod.GET)
	public ResultVo getAgencyPic(@RequestParam("pageUrl") String  pageUrl) {

		ResultVo resultVo = new ResultVo();
		Map<String, Object> port = journalArticleService.getAgencyPic(pageUrl);
		if(port!=null){
			resultVo.setErrMsg("find success");
			resultVo.setResultData(port);
			resultVo.setErrCode(0);
		}else{
			resultVo.setErrMsg("find fail");
			resultVo.setErrCode(1);
		}
		
		return resultVo;
	}
	
	

}
