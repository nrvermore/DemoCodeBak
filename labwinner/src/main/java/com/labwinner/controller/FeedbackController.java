package com.labwinner.controller;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Feedback;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.service.*;

/**
 *信息反馈Controller
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
public class FeedbackController {
	private static Logger logger = LoggerFactory
			.getLogger(FeedbackController.class);

	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 获取所有对象列表
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:37:23
	 */
	@RequestMapping(value = "/Feedback", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		List<Feedback> feedbacks = feedbackService.getAll();
		try {
			if(feedbacks!=null && feedbacks.size()>0){
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(feedbacks);
				return resultVo;
				}
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("feedbacks is null");
			return resultVo;
		}
		return resultVo;

		}

	
	/**
	 * @Description APP根据关键字获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/feedbackPageable/{page}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageableAPP(@PathVariable Integer page,@PathVariable Integer pageSize) {
		
		ResultVo resultVo = new ResultVo();
		
			List<Feedback> feedbacks = feedbackService.getAllPageable();
		
			if(feedbacks!=null&&feedbacks.size()>0){
				int total = feedbacks.size();
				double c = (((double) total) / pageSize);
				int d = (int) Math.ceil(c);
				PageEntity pageEntity = new PageEntity();
				pageEntity.setPageNum(page);
				pageEntity.setPageSize(pageSize);
				pageEntity.setPages(d);
				pageEntity.setTotal(total);
				int num = feedbacks.size() > page * pageSize ? page * pageSize : feedbacks.size();
				if (page <= d) {
					pageEntity.setList(feedbacks.subList((page-1)*pageSize, num));
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(pageEntity);
				return resultVo;
			}else {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("无数据");
				resultVo.setResultData(null);
				return resultVo;
			}
	}

	/**
	 * 根据主键获取对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/feedback/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

		Feedback feedback = feedbackService.getById(id);
		if (feedback == null) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("feedback is null");
			return resultVo;
		}else {
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(feedback);
			return resultVo;
		}
		
		}
	/**
	 * 保存对象
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月18日 下午6:36:23
	 */
	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	public ResultVo save(@RequestBody Feedback feedback) {
//			@RequestParam(value = "name", required = false) String name,
//			@RequestParam(value = "submitFeedback", required = false) String submitFeedback,
//			@RequestParam(value = "contacts", required = false) String contacts
//		
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		
		SysSigningAgency sysSigningAgency=sysUser.getSysSigningAgency(); 
	//	Feedback feedback=new Feedback();
		
		try {
//			feedback.setName(name);
//			feedback.setSubmitFeedback(submitFeedback);
//			feedback.setContacts(contacts);
			feedback.setSysSigningAgency(sysSigningAgency);
			feedback.setSysUser(sysUser);
			feedback.setFeedbackTime(new Date());
			feedbackService.save(feedback);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("sava successe");
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("sava fail");
			return resultVo;
		}
	}

	/**
	 * 更新对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	@RequestMapping(value = "/feedback", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody Feedback feedback) {
		try {

			ResultVo resultVo = new ResultVo();
		
			feedbackService.update(feedback);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update successe");
			return resultVo;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除对象的方法
	 * 
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:08
	 */
	@RequestMapping(value = "/feedback/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();

			feedbackService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("删除成功！！！");
			return resultVo;
		}

	}

