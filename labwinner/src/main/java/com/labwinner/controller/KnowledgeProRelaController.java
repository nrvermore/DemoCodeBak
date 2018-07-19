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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.KnowledgeClassify;
import com.labwinner.domain.KnowledgeClassifyReacRela;
import com.labwinner.domain.KnowledgeProRela;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.SysUser;
import com.labwinner.service.KnowledgeProRelaService;
import com.labwinner.service.SysUserService;


@RestController
public class KnowledgeProRelaController {

	private static Logger logger = LoggerFactory
			.getLogger(KnowledgeProRelaController.class);

	@Autowired
	private KnowledgeProRelaService knowledgeProRelaService;
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/knowledgeProRela/list", method = RequestMethod.GET)
	public List<KnowledgeProRela> getAll() {
		List<KnowledgeProRela> list = knowledgeProRelaService.getAll();
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
	@RequestMapping(value = "/knowledgeProRela/getById/{id}", method = RequestMethod.GET)
	public KnowledgeProRela getById(@PathVariable("id") Integer id) {
		KnowledgeProRela knowledgeProRela = knowledgeProRelaService.getById(id);
		if (knowledgeProRela == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return knowledgeProRela;
	}
	
	@RequestMapping(value = "/knowledgeProRela/{name}", method = RequestMethod.GET)
	public List<KnowledgeProRela> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<KnowledgeProRela> knowledgeProRelas = knowledgeProRelaService.findByName(name);
		if (knowledgeProRelas == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return knowledgeProRelas;
	}
	

	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/knowledgeProRela", method = RequestMethod.POST)
	public ResultVo saveOrUpdate(@RequestBody List<KnowledgeProRela> knowledgeProRelas) {
		ResultVo resultVo = new ResultVo();
		List<KnowledgeProRela> res =new ArrayList<KnowledgeProRela>();
		try {
			if(knowledgeProRelas !=null && knowledgeProRelas.size()>0){
				for (KnowledgeProRela knowledgeProRela : knowledgeProRelas) {
					//TODO 判断更新，增加
					if (knowledgeProRela != null) {
						Integer knowledgeId=knowledgeProRela.getMappingKnowledgeIdl();
						Integer classId=knowledgeProRela.getKnowledgeClassify().getKnowledgeClassifyId();
						Integer proId=knowledgeProRela.getProjectBasicInfo().getProId();
						LoginController login = new LoginController();
						SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
						KnowledgeProRela proRela=knowledgeProRelaService.getKnowledgeProRela(knowledgeId,classId,proId);
						if(proRela==null||proRela.getKnowledgeProRelaId()==null){
							Integer userId = sysUser.getUserId();
							knowledgeProRela.setCreater(sysUser);
							knowledgeProRela.setCreateDate(new Date());
							knowledgeProRelaService.save(knowledgeProRela);
							res.add(knowledgeProRela);
						}
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save success");
				resultVo.setResultData(res);
			}
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("save fail");
		}
		return resultVo;
		
	}
	
	
	
	
	@RequestMapping(value = "/knowledgeProRela/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		knowledgeProRelaService.delete(id);
	}

	
	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/knowledgeProRela/saveOrUpdateForApp", method = RequestMethod.POST)
	public ResultVo saveOrUpdateForApp(	@RequestParam(value = "proId", required = false) int proId,
			@RequestParam(value = "classfyId", required = false) int classfyId,
			@RequestParam(value = "knowledgeIds", required = false) String knowledgeIds) {
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		List<KnowledgeProRela> proRelas=knowledgeProRelaService.getKnowledgeProRelaByProId(classfyId,proId);
		List<Integer> knowList=new ArrayList<Integer>();
		if(proRelas!=null&&proRelas.size()>0){
			for(KnowledgeProRela knowledgeProRela:proRelas){
				knowList.add(knowledgeProRela.getMappingKnowledgeIdl());
			}
		}
		try {
			if(knowList.size()>0){
				String[] ss=knowledgeIds.split(",");
				for(int i=0;i<ss.length;i++){
					if(!knowList.contains(Integer.parseInt(ss[i]))){
						ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();
						projectBasicInfo.setProId(proId);
						KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
						knowledgeClassify.setKnowledgeClassifyId(classfyId);
						KnowledgeProRela knowledgeProRela=new KnowledgeProRela();
						knowledgeProRela.setCreateDate(new Date());
						knowledgeProRela.setCreater(sysUser);
						knowledgeProRela.setMappingKnowledgeIdl(Integer.parseInt(ss[i]));
						knowledgeProRela.setProjectBasicInfo(projectBasicInfo);
						knowledgeProRela.setKnowledgeClassify(knowledgeClassify);
						knowledgeProRelaService.save(knowledgeProRela);
					}
				}
			}else{
				if(knowledgeIds!=null&&!"".equals(knowledgeIds)){
					String[] ss=knowledgeIds.split(",");
					for(int i=0;i<ss.length;i++){
						ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();
						projectBasicInfo.setProId(proId);
						KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
						knowledgeClassify.setKnowledgeClassifyId(classfyId);
						KnowledgeProRela knowledgeProRela=new KnowledgeProRela();
						knowledgeProRela.setCreateDate(new Date());
						knowledgeProRela.setCreater(sysUser);
						knowledgeProRela.setMappingKnowledgeIdl(Integer.parseInt(ss[i]));
						knowledgeProRela.setProjectBasicInfo(projectBasicInfo);
						knowledgeProRela.setKnowledgeClassify(knowledgeClassify);
						knowledgeProRelaService.save(knowledgeProRela);
					}
				}
			}
			
		
			
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("save fail");
		}
		return resultVo;
		
	}

}
