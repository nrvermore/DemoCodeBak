package com.labwinner.controller;

import java.util.ArrayList;
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
import com.labwinner.domain.MolecularImage;
import com.labwinner.domain.Note;
import com.labwinner.domain.ReactionImage;
import com.labwinner.service.MolecularImageService;
import com.labwinner.service.NoteService;

@RestController
public class MolecularImageController {
	
	private static Logger logger = LoggerFactory
			.getLogger(MolecularImageController.class);
	
	@Autowired
	private MolecularImageService molecularImageService;
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/molecularImage/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		MolecularImage molecularImage = molecularImageService.getById(id);
		if (molecularImage == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(molecularImage);
		return resultVo;
	}
	
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/molecularImage", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		List<MolecularImage> molecularImages = molecularImageService.getAll();
		if (molecularImages == null) {
			String message = "对象不存在(id:" + molecularImages + ")";
			logger.warn(message);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(molecularImages);
		return resultVo;
	}
	
	/**
	 * @Description保存对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/molecularImage", method = RequestMethod.POST)
	public ResultVo save(@RequestBody MolecularImage molecularImage) {
		ResultVo resultVo = new ResultVo();
		 molecularImageService.save(molecularImage);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}
	
	/**
	 * @Description更新对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/molecularImage", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody MolecularImage molecularImage) {
		ResultVo resultVo = new ResultVo();
		 molecularImageService.update(molecularImage);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}

	/**
	 * @Description删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/molecularImage/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		 molecularImageService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}
	
	/**
	 * @Description删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/molecularImage/{url}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("url") String url) {
		ResultVo resultVo = new ResultVo();
		 molecularImageService.deleteByUrl(url);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		return resultVo;
	}


}
