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
import com.labwinner.domain.DesignDosage;
import com.labwinner.service.DesignDosageService;

@RestController
public class DesignDosageController {
	
	private static Logger logger = LoggerFactory
			.getLogger(DesignDosageController.class);

	@Autowired
	private DesignDosageService designDosageService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/designDosag/list", method = RequestMethod.GET)
	public List<DesignDosage> getAll() {
		List<DesignDosage> list = designDosageService.getAll();
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
	@RequestMapping(value = "/designDosag/getById/{id}", method = RequestMethod.GET)
	public DesignDosage getById(@PathVariable("id") Integer id) {
		DesignDosage designDosage = designDosageService.getById(id);
		if ( designDosage == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return designDosage;
	}
	
	@RequestMapping(value = "/designDosag/{name}", method = RequestMethod.GET)
	public List<DesignDosage> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<DesignDosage> designDosages = designDosageService.getByName(name);
		if (designDosages == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return designDosages;
	}
	

	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/designDosag/add", method = RequestMethod.POST)
	public void saveOrUpdate(@RequestBody DesignDosage designDosage) {
		try {
			//TODO 判断更新，增加
			Integer id = designDosage.getDesignDosageId();
			DesignDosage DesignDosage2=designDosageService.getById(id);
			if (DesignDosage2 != null) {
				designDosageService.update(designDosage);
			} else {
				designDosageService.save(designDosage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@RequestMapping(value = "/designDosag/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		designDosageService.delete(id);
	}


}
