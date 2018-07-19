package com.labwinner.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.JournalUser;
import com.labwinner.service.JournalUserService;

@RestController
public class JournalUserController {
	
	private static Logger logger = LoggerFactory
			.getLogger(JournalUserController.class);

	@Autowired
	private JournalUserService journalUserService;
	
	@RequestMapping(value = "/journalUser/{id}/{classId}", method = RequestMethod.GET)
	public List<JournalUser> getById(@PathVariable("id") Integer id,@PathVariable("classId") Integer classId) {
		List<JournalUser> journalUsers = journalUserService.getById(id,classId);
		if (journalUsers == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return journalUsers;
	}
	

	@RequestMapping(value = "/journalUser", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<JournalUser> journalUsers) {
		ResultVo resultVo = new ResultVo();
		try{
			for(JournalUser journalUser :journalUsers){
				journalUserService.save(journalUser);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("保存成功");
			return resultVo;
		}
		catch(Exception e){
			String message = "save fail";
			logger.error(message);
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/journalUser/{id}/{classId}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id")Integer id,@PathVariable("classId") Integer classId) {
		ResultVo resultVo = new ResultVo();
		try{
			
				journalUserService.delete(id,classId);
		
			resultVo.setErrCode(0);
			resultVo.setErrMsg("保存成功");
			return resultVo;
		}
		catch(Exception e){
			String message = "save fail";
			logger.error(message);
			e.printStackTrace();
		}
		return null;
	}
}
