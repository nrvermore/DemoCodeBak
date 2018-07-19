package com.labwinner.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.el.parser.ParseException;
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
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Inventory;
import com.labwinner.domain.SignIn;
import com.labwinner.domain.SignLocation;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.service.*;
import com.labwinner.util.ExcelData;
import com.labwinner.util.ExportExcelUtils;
import com.labwinner.vo.SignInVo;

/**
 * 设备Controller
 * 
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月18日 下午6:05:23
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class SignInController {
	private static Logger logger = LoggerFactory
			.getLogger(SignInController.class);

	@Autowired
	private SignInService signInService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysSigningAgencyService sysSigningAgencyService;

	@Value("${sysUserPhone.url-path}")
	private String userImageUrl;
	
	@Value("${common.user-image}")
	private String commonImageUrl;
	
	@Value("${excel.upload-path}")
	private String excelPath;
	
	@Value("${excel.url-path}")
	private String excelUrl;
	

	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		List<SignIn> list = signInService.getAll(userId);
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(list);
		return resultVo;

	}

	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/signIn/getAllByDay/{date}", method = RequestMethod.GET)
	public ResultVo getAllByDay(@PathVariable("date") String date) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser1 = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		SysSigningAgency sysSigningAgency = sysSigningAgencyService
				.getById(Long.valueOf(sysUser1.getSysSigningAgency()
						.getAgencyId()));
		String sysSignTime = date+" "+sysSigningAgency.getSysSignTime();
		List<Object> data = new ArrayList<Object>();
		List curSignList = new ArrayList<>();
		List<SignIn> list = signInService.getAllByDay(date);
		for (SignIn signIn : list) {
			
			Integer flag = isLate(sysSignTime,signIn);
			signIn.setFlag(flag);
			
			SysUser sysUser = signIn.getSysUser();
			if (sysUser.getUserImage() != null) {
				sysUser.setUserImage(userImageUrl + sysUser.getUserImage());
			}else{
				
				sysUser.setUserImage(commonImageUrl + "morentouxiang.png");
			}
			if (sysUser1.getUserId() - sysUser.getUserId() == 0) {
				curSignList.add(signIn);
			}
		}
		data.add(sysSigningAgency.getLatitudeLongitude());
		data.add(list);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultList(curSignList);
		resultVo.setResultData(data);
		return resultVo;

	}

	@RequestMapping(value = "/signIn/getSingFirst", method = RequestMethod.GET)
	public ResultVo getSingFirst() {
		ResultVo resultVo = new ResultVo();
		List listDate = new ArrayList<>();
		LoginController login = new LoginController();
		SysUser sysUser1 = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		List curSignList = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		String nowDate = dateFormat.format(date);
		List<SignIn> teamList = signInService.getAllByDay(nowDate);
		int constantCount = getWeekConstantCount();
		
		for (SignIn signIn : teamList) {
			SysUser sysUser = signIn.getSysUser();
			if (sysUser.getUserImage() != null) {
				sysUser.setUserImage(userImageUrl + sysUser.getUserImage());
			}else{
				
				sysUser.setUserImage(commonImageUrl + "morentouxiang.png");
			}
			if (sysUser1.getUserId() - sysUser.getUserId() == 0) {
				curSignList.add(signIn);
			}
		}
		
		listDate.add(teamList);
		listDate.add(constantCount);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(listDate);
		resultVo.setResultList(curSignList);
		return resultVo;

	}

	/**
	 * 获取最近一周对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/signIn/getWeekList", method = RequestMethod.GET)
	public ResultVo getWeekList() {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		List<SignIn> list = signInService.getWeekList(userId);
		if (list != null && list.size() > 0) {
			SignInVo signInVo = new SignInVo();
			signInVo.setSignInList(list);
			signInVo.setSysDate(new Date());
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(signInVo);
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("list is null");
		return resultVo;

	}

	/**
	 * 根据主键获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/signIn/{id}", method = RequestMethod.GET)
	public SignIn getById(@PathVariable("id") Integer id) {

		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);

		Integer userId = sysUser.getUserId();

		SignIn signIn = signInService.getById(userId);

		if (signIn == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return signIn;
	}

	/**
	 * 保存对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public ResultVo save(@RequestBody SignIn signIn) {

		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();

		Integer count = signInService.getSignCount(userId);
		if (count >= 1) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("今日已签到");
			resultVo.setResultData(count);
			return resultVo;
		}
		signIn.setSysUser(sysUser);
		signIn.setSignDate(new Date());
		signInService.save(signIn);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("签到成功");
		return resultVo;
	}

	@RequestMapping(value = "/signIn/getSignCount", method = RequestMethod.GET)
	public ResultVo getSignCount() {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		Integer count = signInService.getSignCount(userId);
		if (count >= 1) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("今日已签到");
			resultVo.setResultData(count);
			return resultVo;
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("今日未签到");
		resultVo.setResultData(count);
		return resultVo;
	}

	/**
	 * 删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/signIn/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

		signInService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete successe");
		return resultVo;

	}

	/**
	 * 获取最近一周连续签到次数
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	public int getWeekConstantCount() {
		int signinCount = 0;
		LoginController login = new LoginController();
		List<String> dataList = new ArrayList<String>();

		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		List<SignIn> list = signInService.getWeekList(userId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		for (SignIn signIn : list) {
			long time = signIn.getSignDate().getTime();
			String date1 = dateFormat.format(time);
			// System.out.println("data1"+date1);
			dataList.add(date1.toString());
		}
		if (dataList != null && dataList.size() > 0) {
			signinCount = getConstantSignCount(dataList);
		}
		return signinCount;

	}

	public int getConstantSignCount(List<String> dateList) {

		List<Calendar> data1 = new ArrayList<>();
		int count = 1;
		// 设置传入的时间格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		Date startDate = null;
		Date now = new Date();
		try {
			for (int i = 0; i < dateList.size(); i++) {
				Calendar c = Calendar.getInstance();
				startDate = dateFormat.parse(dateList.get(i));
				// 对 calendar 设置为 date 所定的日期
				c.setTime(startDate);
				c.set(Calendar.HOUR_OF_DAY, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.SECOND, 0);
				data1.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		if ((cal.getTimeInMillis() - data1.get(0).getTimeInMillis())/ (1000 * 60 * 60 * 24) > 1) {
			count = 0;
		} else {
			for (int i = 0; i < data1.size() - 1; i++) {
				// 判断一周连续
				if ((data1.get(i).getTimeInMillis() - data1.get(i + 1)
						.getTimeInMillis()) / (1000 * 60 * 60 * 24) == 1) {
					if (data1.get(i).get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
						count++;
						if (count == 7) {
							break;
						}
					} else {
						count = 1;
						break;
					}
				} else {
					break;
				}
			}
		}
		return count;
	}
	
	
	public Integer isLate(String date,SignIn signIn){
		
		// 设置传入的时间格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		Date sysSignInDate = null;
//		Calendar c = Calendar.getInstance();
		try {
			sysSignInDate = dateFormat.parse(date);
//			// 对 calendar 设置为 date 所定的日期
//			c.setTime(startDate);
//			c.set(Calendar.HOUR_OF_DAY, 9);
//			c.set(Calendar.MINUTE, 0);
//			c.set(Calendar.SECOND, 0);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		long a = signIn.getSignDate().getTime()-sysSignInDate.getTime();
		if(a<0){
			return 0;
		}else{
			return 1;
		}
		
	}
	
	
	@RequestMapping(value = "/signIn/createExcel/{startDate}/{endDate}", method = RequestMethod.GET)
	public ResultVo createSinginExcel(@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {
		ResultVo resultVo = new ResultVo();
		String fileName =startDate+"至"+endDate+"签到表.xlsx"; 
		String path = excelPath+fileName;
		 ExcelData data = new ExcelData();
	        data.setName("我的签到");
	        List<String> titles = new ArrayList();
	        titles.add("姓名");
	        titles.add("签到地点");
	        titles.add("签到时间");
	        titles.add("签到内容");
	        data.setTitles(titles);

	        List<SignIn> list = signInService.getExcelList(startDate+" 00:00:00", endDate+" 23:59:59");
	        
	        List<List<Object>> rows = new ArrayList();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
	        
	        for(SignIn signIn :list){
	        	List<Object> row = new ArrayList();
	        	long time = signIn.getSignDate().getTime();
				String date1 = dateFormat.format(time);
	 	        row.add(signIn.getSysUser().getRealname());
	 	        row.add(signIn.getSignAddr());
	 	        row.add(date1);
	 	        row.add(signIn.getSignContent());
	 	        rows.add(row);
	        }
	        
	        data.setRows(rows);


	        //生成本地
	        try {
				ExportExcelUtils.exportExcel(path,data);
				 resultVo.setErrCode(0);
			     resultVo.setErrMsg("生成excel成功");
			     resultVo.setResultData(excelUrl+fileName);
			     resultVo.setResultData1(path);
			     return resultVo;
			} catch (Exception e) {
				
				e.printStackTrace();
				resultVo.setErrCode(1);
			    resultVo.setErrMsg("生成excel失败");
			     return resultVo;
			}
	       
	    }
		
}
