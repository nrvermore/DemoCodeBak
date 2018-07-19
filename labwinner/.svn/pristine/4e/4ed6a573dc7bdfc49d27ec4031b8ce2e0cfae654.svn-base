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
import com.labwinner.domain.DesignDosage;
import com.labwinner.domain.ReactionDesign;
import com.labwinner.domain.ReactionDesignChemical;
import com.labwinner.domain.ReactionDesignParameter;
import com.labwinner.domain.ReactionDesignProcess;
import com.labwinner.domain.ReactionRecord;
import com.labwinner.service.ReactionRecordService;

@RestController
public class ReactionRecordController {
	
	private static Logger logger = LoggerFactory
			.getLogger(ReactionRecordController.class);

	@Autowired
	private ReactionRecordService reactionRecordService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/reactionRecord/list", method = RequestMethod.GET)
	public List<ReactionRecord> getAll() {
		List<ReactionRecord> list = reactionRecordService.getAll();
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
	@RequestMapping(value = "/reactionRecord/getById/{id}", method = RequestMethod.GET)
	public ReactionRecord getById(@PathVariable("id") Integer id) {
		ReactionRecord reactionRecord = reactionRecordService.getById(id);
		if (reactionRecord == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return reactionRecord;
	}
	
	@RequestMapping(value = "/reactionRecord/{name}", method = RequestMethod.GET)
	public List<ReactionRecord> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<ReactionRecord> reactionRecords = reactionRecordService.getByName(name);
		if (reactionRecords == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return reactionRecords;
	}
	

	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionRecord/add", method = RequestMethod.POST)
	public void saveOrUpdate(@RequestBody ReactionRecord reactionRecord) {
		try {
			//TODO 判断更新，增加
			Integer id = reactionRecord.getReactionRecordId();
			ReactionRecord reactionRecord2=reactionRecordService.getById(id);
			if (reactionRecord2 != null) {
				reactionRecordService.update(reactionRecord);
			} else {
				reactionRecordService.save(reactionRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionRecord", method = RequestMethod.POST)
	public ResultVo save(@RequestBody List<ReactionRecord> reactionRecords) {
		ResultVo resultVo = new ResultVo();

			try {
				if (null != reactionRecords && reactionRecords.size() > 0) {
					for (int i = 0; i < reactionRecords.size(); i++) {
						ReactionDesign reactionDesign=reactionRecords.get(i).getReactionDesign();
						reactionRecords.get(i).setReactionDesign(reactionDesign);
						reactionRecords.get(i).setReactionNum(i + 1 + "");
						reactionRecordService.save(reactionRecords.get(i));
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save successe");
				return resultVo;
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("save fail");
				return resultVo;
			}
		
		}
	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/reactionRecord", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody List<ReactionRecord> reactionRecords) {
		
		ResultVo resultVo = new ResultVo();
		
			try {
				if (null != reactionRecords && reactionRecords.size() > 0) {
					for (ReactionRecord reactionRecord : reactionRecords) {
						ReactionDesign reactionDesign=reactionRecord.getReactionDesign();
						Integer id=reactionDesign.getReactionDesignId();
						 reactionRecordService.delete(id);
					}
					for (int i = 0; i < reactionRecords.size(); i++) {
						ReactionDesign reactionDesign=reactionRecords.get(i).getReactionDesign();
						reactionRecords.get(i).setReactionDesign(reactionDesign);
						reactionRecords.get(i).setReactionNum(i + 1 + "");
						reactionRecordService.save(reactionRecords.get(i));
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update successe");
				return resultVo;
			} catch (Exception e) {
				resultVo.setErrCode(1);
				resultVo.setErrMsg("update fail");
				return resultVo;
			}
	}


	@RequestMapping(value = "/reactionRecord/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		reactionRecordService.delete(id);
	}


}
