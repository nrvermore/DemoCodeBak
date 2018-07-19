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

import com.labwinner.domain.FellingExp;
import com.labwinner.service.FellingExpService;

@RestController
public class FellingExpController {
	
	private static Logger logger = LoggerFactory
			.getLogger(FellingExpController.class);

	@Autowired
	private FellingExpService fellingExpService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/fellingExp/list", method = RequestMethod.GET)
	public List<FellingExp> getAll() {
		List<FellingExp> list = fellingExpService.getAll();
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
	@RequestMapping(value = "/fellingExp/getById/{id}", method = RequestMethod.GET)
	public FellingExp getById(@PathVariable("id") Integer id) {
		FellingExp fellingExp = fellingExpService.getById(id);
		if (fellingExp == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return fellingExp;
	}
	
	@RequestMapping(value = "/fellingExp/{name}", method = RequestMethod.GET)
	public List<FellingExp> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<FellingExp> selfPapers = fellingExpService.getByName(name);
		if (selfPapers == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return selfPapers;
	}
	

	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/fellingExp/add", method = RequestMethod.POST)
	public void saveOrUpdate(@RequestBody FellingExp fellingExp) {
		try {
			//TODO 判断更新，增加
			Integer id = fellingExp.getFellingExpId();
			FellingExp fellingExp2=fellingExpService.getById(id);
			if (fellingExp2 != null) {
				fellingExpService.update(fellingExp);
			} else {
				fellingExpService.save(fellingExp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@RequestMapping(value = "/fellingExp/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		fellingExpService.delete(id);
	}


}
