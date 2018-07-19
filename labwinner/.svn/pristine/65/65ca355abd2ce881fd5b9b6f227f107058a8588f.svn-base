package com.labwinner.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.labwinner.domain.KnowledgeClassifyReacRela;
import com.labwinner.domain.KnowledgeProRela;
import com.labwinner.domain.SysUser;
import com.labwinner.service.KnowledgeReacRelaService;
import com.labwinner.service.SysUserService;


@RestController
public class KnowledgeReacRelaController {
	
	private static Logger logger = LoggerFactory
			.getLogger(KnowledgeReacRelaController.class);

	@Autowired
	private KnowledgeReacRelaService knowledgeReacRelaService;
	
	@Autowired
	SysUserService sysUserService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/knowledgeReacRela/list", method = RequestMethod.GET)
	public List<KnowledgeClassifyReacRela> getAll() {
		List<KnowledgeClassifyReacRela> list = knowledgeReacRelaService.getAll();
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
	@RequestMapping(value = "/knowledgeReacRela/getById/{id}", method = RequestMethod.GET)
	public KnowledgeClassifyReacRela getById(@PathVariable("id") Integer id) {
		KnowledgeClassifyReacRela knowledgeReacRela = knowledgeReacRelaService.getById(id);
		if (knowledgeReacRela == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return knowledgeReacRela;
	}
	
	@RequestMapping(value = "/knowledgeReacRela/{name}", method = RequestMethod.GET)
	public List<KnowledgeClassifyReacRela> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<KnowledgeClassifyReacRela> knowledgeReacRelas = knowledgeReacRelaService.findByName(name);
		if (knowledgeReacRelas == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return knowledgeReacRelas;
	}
	

	/**
	 * @Description 保存/更新对象方法
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/knowledgeReacRela", method = RequestMethod.POST)
	public ResultVo saveOrUpdate(@RequestBody List<KnowledgeClassifyReacRela> knowledgeReacRelas) {
		ResultVo resultVo = new ResultVo();
		List<KnowledgeClassifyReacRela> res=new ArrayList<KnowledgeClassifyReacRela>();
		try {
			if(knowledgeReacRelas !=null && knowledgeReacRelas.size()>0){
				for (KnowledgeClassifyReacRela knowledgeClassifyReacRela : knowledgeReacRelas) {
					//TODO 判断更新，增加
					if (knowledgeClassifyReacRela != null) {
						Integer knowledgeId=knowledgeClassifyReacRela.getMappingKnowledgeIdl();
						Integer classId=knowledgeClassifyReacRela.getKnowledgeClassifyId();
						Integer reactionId=knowledgeClassifyReacRela.getReaction().getReactionId();
						LoginController login = new LoginController();
						SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
						KnowledgeClassifyReacRela reacRela=knowledgeReacRelaService.getKnowledgeProRela(knowledgeId,classId,reactionId);
							if(reacRela==null||reacRela.getKnowledgeClassifyReacRelaId()==null){
								//KnowledgeClassifyReacRela knowledgeReacRela=new KnowledgeClassifyReacRela();
								Integer userId = sysUser.getUserId();
								knowledgeClassifyReacRela.setCreater(userId+"");
								knowledgeClassifyReacRela.setCreateDate(new Date());
								knowledgeReacRelaService.save(knowledgeClassifyReacRela);
								res.add(knowledgeClassifyReacRela);
						}
					}
				
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			resultVo.setResultData(res);
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("save failed");
		}
		return resultVo;
	}


	@RequestMapping(value = "/knowledgeReacRela/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		knowledgeReacRelaService.delete(id);
	}
}
