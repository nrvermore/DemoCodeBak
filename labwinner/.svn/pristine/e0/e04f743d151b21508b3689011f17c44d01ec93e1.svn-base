package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.labwinner.domain.Industry;
import com.labwinner.service.IndustryService;

import java.util.List;

/**
 * @Description 行业Controller
 * @author liuhq
 * @version V1.0
 * @date 2017年6月8日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class IndustryController {

	@Autowired
	private IndustryService industryService;

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/industry", method = RequestMethod.GET)
	public List<Industry> getAll() {
		return industryService.getAll();
	}

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/industry/{id}", method = RequestMethod.GET)
	public Industry getById(@PathVariable("id") Long id) {
		return industryService.getById(id);
	}

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/industry", method = RequestMethod.POST)
	public Integer save(@RequestBody Industry industry) {
		return industryService.save(industry);
	}

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/industry", method = RequestMethod.PUT)
	public void update(@RequestBody Industry industry) {
		industryService.update(industry);
	}

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/industry/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		industryService.delete(id);
	}

}
