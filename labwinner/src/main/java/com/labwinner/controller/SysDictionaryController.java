package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.labwinner.domain.SysDictionary;
import com.labwinner.service.SysDictionaryService;

import java.util.List;

/**
 * @Description 字典Controller
 * @author liuhq
 * @version V1.0
 * @date 2017年6月7日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class SysDictionaryController {

	@Autowired
	private SysDictionaryService sysDictionaryService;

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysDictionary", method = RequestMethod.GET)
	public List<SysDictionary> getAll() {
		return sysDictionaryService.getAll();
	}
	
	/**
	 * @Description 根据字典类型获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysDictionary/getByDictType/{dictType}", method = RequestMethod.GET)
	public List<SysDictionary> getByDictType(@PathVariable("dictType") String dictType) {
		return sysDictionaryService.getByDictType(dictType);
	}

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysDictionary/{id}", method = RequestMethod.GET)
	public SysDictionary getById(@PathVariable("id") Long id) {
		return sysDictionaryService.getById(id);
	}

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysDictionary", method = RequestMethod.POST)
	public Integer save(@RequestBody SysDictionary sysDictionary) {
		return sysDictionaryService.save(sysDictionary);
	}

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysDictionary", method = RequestMethod.PUT)
	public void update(@RequestBody SysDictionary sysDictionary) {
		sysDictionaryService.update(sysDictionary);
	}

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysDictionary/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		sysDictionaryService.delete(id);
	}

}
