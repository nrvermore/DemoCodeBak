package com.labwinner.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.domain.ReactionStatus;
import com.labwinner.service.ReactionStatusService;

@RestController
public class ReactionStatusController {

	private static Logger logger = LoggerFactory
			.getLogger(ReactionStatusController.class);

	@Autowired
	private ReactionStatusService reactionStatusService;
	
	@RequestMapping(value = "/reactionStatus", method = RequestMethod.GET)
	@ResponseBody
	public List<ReactionStatus> getAll() {
		List<ReactionStatus> list = reactionStatusService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}
	
	@RequestMapping(value = "/reactionStatus/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ReactionStatus getById(@PathVariable("id") Integer id) {
		ReactionStatus reactionStatus= reactionStatusService.getById(id);
		if (reactionStatus == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return reactionStatus;
	}
	
	@RequestMapping(value = "/reactionStatus/add", method = RequestMethod.POST)
	public void save(@RequestBody ReactionStatus reactionStatus) {
		
		reactionStatusService.save(reactionStatus);
			
	}
	
	@RequestMapping(value = "/reactionStatus", method = RequestMethod.PUT)
	public void Update(@RequestBody ReactionStatus reactionStatus) {
		
		reactionStatusService.update(reactionStatus);
		
            }
	@RequestMapping(value = "/reactionStatus/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		try {
			reactionStatusService.delete(id);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
