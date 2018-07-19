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

import com.labwinner.domain.ReactionDesignParameter;
import com.labwinner.service.ReactionDesignParameterService;


@RestController
public class ReactionDesignParameterController {
	
	private static Logger logger = LoggerFactory
			.getLogger(ReactionDesignParameterController.class);

	@Autowired
	private ReactionDesignParameterService reactionDesignParameterService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/reactionDesignParameter/list", method = RequestMethod.GET)
	public List<ReactionDesignParameter> getAll() {
		List<ReactionDesignParameter> list = reactionDesignParameterService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/reactionDesignParameter/getById/{id}", method = RequestMethod.GET)
	public ReactionDesignParameter getById(@PathVariable("id") Integer id) {
		ReactionDesignParameter reactionDesignParameter = reactionDesignParameterService.getById(id);
		if (reactionDesignParameter == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return reactionDesignParameter;
	}
	
	@RequestMapping(value = "/reactionDesignParameter/{name}", method = RequestMethod.GET)
	public List<ReactionDesignParameter> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<ReactionDesignParameter> reactionDesignParameters = reactionDesignParameterService.getByName(name);
		if (reactionDesignParameters == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return reactionDesignParameters;
	}
	

	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionDesignParameter/add", method = RequestMethod.POST)
	public void saveOrUpdate(@RequestBody ReactionDesignParameter reactionDesignParameter) {
		try {
			//TODO 判断更新，增加
			Integer id = reactionDesignParameter.getDesignParameterId();
			ReactionDesignParameter reactionDesignParameter2=reactionDesignParameterService.getById(id);
			if (reactionDesignParameter2 != null) {
				reactionDesignParameterService.update(reactionDesignParameter);
			} else {
				reactionDesignParameterService.save(reactionDesignParameter);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@RequestMapping(value = "/reactionDesignParameter/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		reactionDesignParameterService.delete(id);
	}


}
