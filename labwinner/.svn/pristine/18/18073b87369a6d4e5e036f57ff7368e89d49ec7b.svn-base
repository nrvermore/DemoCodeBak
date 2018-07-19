package com.labwinner.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.JournalArticle;
import com.labwinner.domain.KnowledgeField;
import com.labwinner.domain.Magazine;
import com.labwinner.domain.MaterialField;
import com.labwinner.service.JournalArticleService;
import com.labwinner.service.KnowledgeFieldService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.Base64Util;

@RestController
public class KnowledgeFieldController {

	private static Logger logger = LoggerFactory
			.getLogger(KnowledgeFieldController.class);

	@Autowired
	private KnowledgeFieldService knowledgeFieldService;
	@Autowired
	private  SysUserService sysUserService;
	@Autowired
	private JournalArticleService journalArticleService;
	@Value("${web.url_path_fieldImage}")
	private String fieldImage;
	@Value("${web.upload_path_fieldImage}")
	private String fieldUpload;
	
	
	
	@RequestMapping(value = "/knowledgeField/list", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo res=new ResultVo();
		try {
			List<KnowledgeField> list = knowledgeFieldService.getAll();
			if(list!=null&&list.size()>0){
				for(KnowledgeField knowledgeField:list){
					if(knowledgeField.getDefaultImage()!=null&&knowledgeField.getDefaultImage().getUrl()!=null){
						knowledgeField.getDefaultImage().setUrl(fieldImage+knowledgeField.getDefaultImage().getUrl());
					}
				}
			}
			res.setErrCode(0);
			res.setResultData(list);
		} catch (Exception e) {
			res.setErrCode(1);
		}
		
		return res;
	}
	
	
	@RequestMapping(value = "/knowledgeField/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable Integer id) {
		ResultVo res=new ResultVo();
		try {
			KnowledgeField knowledgeField = knowledgeFieldService.getById(id);
			if(knowledgeField!=null){
					if(knowledgeField.getDefaultImage()!=null&&knowledgeField.getDefaultImage().getUrl()!=null){
						knowledgeField.getDefaultImage().setUrl(fieldImage+knowledgeField.getDefaultImage().getUrl());
					}
			}
			res.setErrCode(0);
			res.setResultData(knowledgeField);
		} catch (Exception e) {
			res.setErrCode(1);
		}
		
		return res;
	}

	@RequestMapping(value = "/knowledgeField/defaultImageList", method = RequestMethod.GET)
	public ResultVo getDefaultImageList() {
		ResultVo res=new ResultVo();
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			list=knowledgeFieldService.getDefaultImageList(fieldImage);
			res.setErrCode(0);
			res.setResultData(list);
		} catch (Exception e) {
			res.setErrCode(1);
		}
		
		return res;
	}
	
	
	@RequestMapping(value = "/knowledgeFieldPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<KnowledgeField> knowledgeFields = knowledgeFieldService.getAllPageable(keyword);
			if(knowledgeFields!=null&&knowledgeFields.size()>0){
				for(KnowledgeField knowledgeField:knowledgeFields){
					if(knowledgeField.getDefaultImage()!=null&&knowledgeField.getDefaultImage().getUrl()!=null){
						knowledgeField.getDefaultImage().setUrl(fieldImage+knowledgeField.getDefaultImage().getUrl());
					}
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(knowledgeFields));
			return resultVo;
		} else {
			List<KnowledgeField> knowledgeFields = knowledgeFieldService.getAll();
			if(knowledgeFields!=null&&knowledgeFields.size()>0){
				for(KnowledgeField knowledgeField:knowledgeFields){
					if(knowledgeField.getDefaultImage()!=null&&knowledgeField.getDefaultImage().getUrl()!=null){
						knowledgeField.getDefaultImage().setUrl(fieldImage+knowledgeField.getDefaultImage().getUrl());
					}
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(knowledgeFields));
			return resultVo;
		}
		
	}
	
	@RequestMapping(value = "/knowledgeField/save", method = RequestMethod.POST)
	public ResultVo save(@RequestBody KnowledgeField knowledgeField) {
		ResultVo resultVo = new ResultVo();
		try {
			if(knowledgeField.getKnowledgeFieldId()!=null){
				knowledgeFieldService.update(knowledgeField);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update successe");
			}else{
				knowledgeFieldService.save(knowledgeField);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save successe");
			}
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("sava fail");
			return resultVo;
		}
	}
	
//	 @RequestMapping(value = "/knowledgeField/update", method = RequestMethod.PUT)
//	    public ResultVo update(@RequestBody KnowledgeField knowledgeField) {
//		 ResultVo resultVo = new ResultVo();
//			try {
//				
//				resultVo.setErrCode(0);
//				resultVo.setErrMsg("update successe");
//				return resultVo;
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				resultVo.setErrCode(1);
//				resultVo.setErrMsg("update fail");
//				return resultVo;
//			}
//		 
//	    }
	 
		@RequestMapping(value = "/knowledgeField/{id}", method = RequestMethod.DELETE)
		public ResultVo delete(@PathVariable("id") Integer id) {
			 ResultVo resultVo = new ResultVo();
			 List<JournalArticle> journalArticles = journalArticleService.getByKnowledgeFieldId(id);
			      
			 if(journalArticles!=null&&journalArticles.size()>0){
					resultVo.setErrCode(1);
					resultVo.setErrMsg("此分类已被使用，不能删除！！！"); 
			 }else {
				 try {
					 knowledgeFieldService.delete(id);
					    resultVo.setErrCode(0);
						resultVo.setErrMsg("删除成功！！！"); 
				} catch (Exception e) {
					 resultVo.setErrCode(2);
					resultVo.setErrMsg("系统错误！！！"+e.getMessage()); 
				}
				
			}
			return resultVo;
		}
 
		

}
