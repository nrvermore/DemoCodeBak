package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.labwinner.domain.SysAttachment;
import com.labwinner.service.SysAttachmentService;

import java.util.List;

/**
 * @Description 附件Controller
 * @author liuhq
 * @version V1.0
 * @date 2017年6月9日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class SysAttachmentController {

	@Autowired
	private SysAttachmentService sysAttachmentService;

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysAttachment", method = RequestMethod.GET)
	public List<SysAttachment> getAll() {
		return sysAttachmentService.getAll();
	}

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysAttachment/{id}", method = RequestMethod.GET)
	public SysAttachment getById(@PathVariable("id") Long id) {
		return sysAttachmentService.getById(id);
	}

	/**
	 * @Description 保存对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysAttachment", method = RequestMethod.POST)
	public Integer save(@RequestBody SysAttachment sysAttachment) {
		return sysAttachmentService.save(sysAttachment);
	}

	/**
	 * @Description 更新对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysAttachment", method = RequestMethod.PUT)
	public void update(@RequestBody SysAttachment sysAttachment) {
		sysAttachmentService.update(sysAttachment);
	}

	/**
	 * @Description 删除对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/sysAttachment/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		sysAttachmentService.delete(id);
	}

}
