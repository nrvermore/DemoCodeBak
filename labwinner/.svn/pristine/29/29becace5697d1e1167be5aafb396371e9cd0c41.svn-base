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
import com.labwinner.domain.KnowledgeClassify;
import com.labwinner.service.KnowledgeClassifyService;

@RestController
public class KnowledgeClassifyController {

	private static Logger logger = LoggerFactory
			.getLogger(KnowledgeClassifyController.class);

	@Autowired
	private KnowledgeClassifyService knowledgeClassifyService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/knowledgeClassify/list", method = RequestMethod.GET)
	public List<KnowledgeClassify> getAll() {
		List<KnowledgeClassify> list = knowledgeClassifyService.getAll();
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
	@RequestMapping(value = "/knowledgeClassify/getById/{id}", method = RequestMethod.GET)
	public KnowledgeClassify getById(@PathVariable("id") Integer id) {
		KnowledgeClassify knowledgeClassify = knowledgeClassifyService.getById(id);
		if (knowledgeClassify == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return knowledgeClassify;
	}
	
	@RequestMapping(value = "/knowledgeClassify/{name}", method = RequestMethod.GET)
	public List<KnowledgeClassify> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<KnowledgeClassify> reactionRecords = knowledgeClassifyService.getByName(name);
		if (reactionRecords == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return reactionRecords;
	}
	

	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/knowledgeClassify/add", method = RequestMethod.POST)
	public ResultVo save(@RequestBody KnowledgeClassify knowledgeClassify) {
		ResultVo resultVo=new ResultVo();
				try {
					knowledgeClassifyService.save(knowledgeClassify);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resultVo.setErrCode(0);
					resultVo.setErrMsg("保存成功");
					return resultVo;
				}
				return null;
				
	}
	/**
	 * @Description 更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/knowledgeClassify/update", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody KnowledgeClassify knowledgeClassify) {
		
		
		ResultVo resultVo=new ResultVo();
		try {
			knowledgeClassifyService.update(knowledgeClassify);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("修改成功");
			return resultVo;
		}
		return null;
		
	}


	@RequestMapping(value = "/knowledgeClassify/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {

		ResultVo resultVo=new ResultVo();
		try {
			knowledgeClassifyService.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("修改成功");
			return resultVo;
		}
		return null;
	}

}
