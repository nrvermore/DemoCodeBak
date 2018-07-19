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
import com.labwinner.domain.MarketKeyword;
import com.labwinner.service.MarketKeywordService;

@RestController
public class MarketKeywordController {

	private static Logger logger = LoggerFactory
			.getLogger(MarketKeywordController.class);

	@Autowired
	private MarketKeywordService marketKeywordService;
	
	/**
	 * get image method
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/marketKeyword/getAll", method = RequestMethod.GET)
	public ResultVo getList(){
		ResultVo resultVo = new ResultVo();
		List<MarketKeyword> keywords = marketKeywordService.getAll();
		if(keywords!=null){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("get files success");
			resultVo.setResultData(keywords);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("get files null");
		return resultVo;
	}
	
	/**
	 * get image method
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/marketKeyword/save", method = RequestMethod.GET)
	public ResultVo save(@RequestBody MarketKeyword marketKeyword){
		ResultVo resultVo = new ResultVo();
		
		marketKeywordService.save(marketKeyword);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("get files success");
		return resultVo;
		
	}
	
	/**
	 * get image method
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/marketKeyword/update/{id}", method = RequestMethod.GET)
	public ResultVo update(@PathVariable("id") String keyword){
		ResultVo resultVo = new ResultVo();
		MarketKeyword marketKeyword = marketKeywordService.getByKeyword(keyword);
		if(marketKeyword!=null){
		marketKeyword.setSearchNum(marketKeyword.getSearchNum()+1);
		marketKeywordService.update(marketKeyword);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("update success");
		return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("marketKeyword is null");
		return resultVo;
	}
}
