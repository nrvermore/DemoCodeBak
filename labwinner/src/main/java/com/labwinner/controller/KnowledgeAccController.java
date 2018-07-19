package com.labwinner.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.service.KnowledgeAccService;
import com.labwinner.util.FileUtil;

@RestController
public class KnowledgeAccController {
	
	private static Logger logger = LoggerFactory
			.getLogger(KnowledgeAccController.class);

	@Autowired
	private KnowledgeAccService knowledgeAccService;
	
	@Value("${web.agency_pdf}")
	String pdfAgencyPath;
	
	@Value("${web.url_path_pdf}")
	String basePath;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/knowledgeAcc/list", method = RequestMethod.GET)
	public List<KnowledgeAcc> getAll() {
		List<KnowledgeAcc> list = knowledgeAccService.getAll();
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
	@RequestMapping(value = "/knowledgeAcc/getById/{id}", method = RequestMethod.GET)
	public KnowledgeAcc getById(@PathVariable("id") Integer id) {
		KnowledgeAcc knowledgeAcc = knowledgeAccService.getById(id);
		String filePath=knowledgeAcc.getUploadFiles();
		String fileName=filePath.substring(filePath.indexOf("/pdfs/")+6, filePath.length());
		String type=filePath.substring(filePath.lastIndexOf(".")+1, filePath.length());
		String pdfUrl=null;
		if("pdf".equals(type)){
			int source=knowledgeAcc.getSource()==null?1:knowledgeAcc.getSource();
			if(source==1){
				pdfUrl=pdfAgencyPath+fileName;
			}else{
				pdfUrl=basePath+fileName;
			}
			
		}else{
			pdfUrl=knowledgeAcc.getPdfUrl();
		}
		knowledgeAcc.setPdfUrl(pdfUrl);
		if (knowledgeAcc == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		FileUtil fu=new FileUtil();
		String size=fu.getFileSize(filePath);
		knowledgeAcc.setImage(size);
		return knowledgeAcc;
	}
	
	@RequestMapping(value = "/knowledgeAcc/{name}", method = RequestMethod.GET)
	public List<KnowledgeAcc> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<KnowledgeAcc> knowledgeAccs = knowledgeAccService.getByName(name);
		if (knowledgeAccs == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return knowledgeAccs;
	}
	

	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/knowledgeAcc/add", method = RequestMethod.POST)
	public void saveOrUpdate(@RequestBody KnowledgeAcc knowledgeAcc) {
		try {
			//TODO 判断更新，增加
			Integer id = knowledgeAcc.getKnowledgeAccId();
			KnowledgeAcc knowledgeAcc2=knowledgeAccService.getById(id);
			if (knowledgeAcc2 != null) {
				knowledgeAccService.update(knowledgeAcc);
			} else {
				knowledgeAccService.save(knowledgeAcc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@RequestMapping(value = "/knowledgeAcc/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		knowledgeAccService.delete(id);
	}
	
	
	@RequestMapping(value = "/knowledgeAcc/getConversion", method = RequestMethod.GET)
	public List<KnowledgeAcc> getConversion() {
		List<KnowledgeAcc> list = knowledgeAccService.getAllConversion();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}


}
