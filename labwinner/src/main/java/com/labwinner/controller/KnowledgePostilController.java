package com.labwinner.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.KnowledgeClassifyPostil;
import com.labwinner.domain.KnowledgeThumbsUp;
import com.labwinner.domain.SysUser;
import com.labwinner.service.KnowledgePostilService;
import com.labwinner.service.KnowledgeThumbsUpService;
import com.labwinner.service.SysUserService;
import com.labwinner.vo.KnowledgePostilVo;

@RestController
public class KnowledgePostilController {
	
	private static Logger logger = LoggerFactory
			.getLogger(KnowledgePostilController.class);

	@Autowired
	private KnowledgePostilService  knowledgePostilService;
	@Autowired
	SysUserService sysUserService;
	@Autowired
	private KnowledgeThumbsUpService  knowledgeThumbsUpService;
	
	@Value("${sysUserPhone.url-path}")
	String userImageUrl;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/knowledgePostil/list", method = RequestMethod.GET)
	public List<KnowledgeClassifyPostil> getAll(@RequestBody KnowledgeAcc knowledgeAcc) {
		List<KnowledgeClassifyPostil> list = knowledgePostilService.getAll(knowledgeAcc);
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
	@RequestMapping(value = "/knowledgePostil/getById/{id}", method = RequestMethod.GET)
	public List<KnowledgePostilVo> getById(@PathVariable("id") Integer id) {
		List<KnowledgeClassifyPostil> knowledgePostil = knowledgePostilService.getById(id);
		List<KnowledgePostilVo> res=new ArrayList<KnowledgePostilVo>();
		if(knowledgePostil!=null){
			for(int i=0;i<knowledgePostil.size();i++){
				if(knowledgePostil.get(i).getReplyRecordId()==null){
					KnowledgePostilVo knowledgePostilVo=new KnowledgePostilVo();
					knowledgePostilVo.setKnowledgeClassifyPostil(knowledgePostil.get(i));
					res.add(knowledgePostilVo);
				}
			}
			
			for(int i=0;i<res.size();i++){
				Map<String, String> mapRes=new HashMap<String, String>();
				if(knowledgePostil!=null&&knowledgePostil.size()>0){
					KnowledgeClassifyPostil ksp=res.get(i).getKnowledgeClassifyPostil();
					List<KnowledgeClassifyPostil> ls=new ArrayList<KnowledgeClassifyPostil>();
					
					Map<String, List<KnowledgeClassifyPostil>> map=new HashMap<String, List<KnowledgeClassifyPostil>>();
					for(int k=0;k<knowledgePostil.size();k++){
						if(knowledgePostil.get(k).getPostilId()!=null&&knowledgePostil.get(k).getReplyRecordId()!=null
								&&knowledgePostil.get(k).getPostilId()-ksp.getKnowledgeClassifyPostilId()==0){
							String userTouser="";
							if(knowledgePostil.get(k).getReplyRecordId()-knowledgePostil.get(k).getPostilId()==0){
								userTouser=knowledgePostil.get(k).getSysUser().getRealname();
								mapRes.put(""+knowledgePostil.get(k).getKnowledgeClassifyPostilId(), userTouser);
							}else{
								for(int m=0;m<knowledgePostil.size();m++){
									if(knowledgePostil.get(m).getPostilId()!=null){
									if(knowledgePostil.get(m).getReplyRecordId()-knowledgePostil.get(k).getKnowledgeClassifyPostilId()==0){
										userTouser=knowledgePostil.get(m).getSysUser().getRealname()+"回复"+knowledgePostil.get(k).getSysUser().getRealname();
										mapRes.put(""+knowledgePostil.get(m).getKnowledgeClassifyPostilId(), userTouser);
									}
									}
									
								}
								
							}
							
							ls.add(knowledgePostil.get(k));
							map.put("replyContent",ls);
							res.get(i).setMap(map);
							res.get(i).setMapRes(mapRes);
						}
					}
					
				}
			}
			
			
		}else if (knowledgePostil == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}
		return res;
	}
	

	
	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/knowledgePostil/getByIdForApp/{id}", method = RequestMethod.GET)
	public ResultVo getByIdForApps(@PathVariable("id") Integer id) {
		ResultVo result=new ResultVo();
		List<KnowledgeClassifyPostil> knowledgePostil = knowledgePostilService.getById(id);
		List<KnowledgePostilVo> res=new ArrayList<KnowledgePostilVo>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		if(knowledgePostil!=null&&knowledgePostil.size()>0){
			for(int i=0;i<knowledgePostil.size();i++){
				if(knowledgePostil.get(i).getReplyRecordId()==null){
					KnowledgePostilVo knowledgePostilVo=new KnowledgePostilVo();
					String userImage=knowledgePostil.get(i).getSysUser().getUserImage();
					if(userImage!=null){
						userImage=userImageUrl+userImage;
					}
					knowledgePostil.get(i).getSysUser().setUserImage(userImage);
					knowledgePostilVo.setKnowledgeClassifyPostil(knowledgePostil.get(i));
					knowledgePostilVo.setLoginUser(userId);
					res.add(knowledgePostilVo);
					//knowledgePostil.remove(i);
				}
			}
			Collections.reverse(res);
			for(int i=0;i<res.size();i++){
				List<Map<String, Object>> appMap=new ArrayList<Map<String,Object>>();
				//Map<String, String> mapRes=new HashMap<String, String>();
				KnowledgeClassifyPostil ksp=res.get(i).getKnowledgeClassifyPostil();
				if(knowledgePostil!=null&&knowledgePostil.size()>0){
					for(int k=0;k<knowledgePostil.size();k++){
						if(knowledgePostil.get(k).getPostilId()!=null&&knowledgePostil.get(k).getReplyRecordId()!=null){
							if(knowledgePostil.get(k).getPostilId()-ksp.getKnowledgeClassifyPostilId()==0){
								String userTouser="";
								String postname="";
								if(knowledgePostil.get(k).getReplyRecordId()-knowledgePostil.get(k).getPostilId()==0){
									postname=knowledgePostil.get(k).getSysUser().getRealname();
								}else{
									postname=knowledgePostil.get(k).getSysUser().getRealname();
									Integer aa=knowledgePostil.get(k).getReplyRecordId();
									for(int m=0;m<knowledgePostil.size();m++){
										if(knowledgePostil.get(m).getPostilId()!=null){
											int bb=knowledgePostil.get(m).getKnowledgeClassifyPostilId();
										if(knowledgePostil.get(k).getReplyRecordId()-knowledgePostil.get(m).getKnowledgeClassifyPostilId()==0){
											userTouser=knowledgePostil.get(m).getSysUser().getRealname();
										}
										}
									}
									
								}
								Map<String, Object> map=new HashMap<String, Object>();
								map.put("postname", postname);
								map.put("userTouser", userTouser);
								map.put("knowledgePostil", knowledgePostil.get(k));
								appMap.add(map);
							}
						}
							
					}
					
			}
				res.get(i).setAppMap(appMap);
			}
			//res.a
			}
			
			
		
		result.setErrCode(0);
		result.setResultData(res);
		return result;
	}
	
	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/knowledgePostil/getByAccIdForApp/{id}", method = RequestMethod.GET)
	public ResultVo getByAccIdForApp(@PathVariable("id") Integer id) {
		ResultVo result=new ResultVo();
		List<KnowledgeThumbsUp> thumbsUpList=knowledgeThumbsUpService.getAll(id);
		LoginController login = new LoginController();
		SysUser ss = sysUserService.getByUsername(login.getPrincipal()).get(0);
		int countThumbs=0;
		String isUser="0";
		if(thumbsUpList!=null&&thumbsUpList.size()>0){
			countThumbs=thumbsUpList.size();
			for(int i=0;i<thumbsUpList.size();i++){
				if(ss.getUserId()-thumbsUpList.get(i).getSysUser().getUserId()==0){
					isUser="1";
				}
			}
			
		}
		List<KnowledgeClassifyPostil> knowledgePostil = knowledgePostilService.getById(id);
		List<KnowledgeClassifyPostil> listCount=new ArrayList<KnowledgeClassifyPostil>();
		
		if(knowledgePostil!=null&&knowledgePostil.size()>0){
			for(int i=0;i<knowledgePostil.size();i++){
				if(knowledgePostil.get(i).getPostilId()==null){
					//countPostil++;
					listCount.add(knowledgePostil.get(i));
				}
			}
			}
	
//	jour.setPdfCode(String.valueOf(listCount.size()));
//	jour.setVolume(String.valueOf(countThumbs));
//	jour.setIssue(isUser);
	
	Map<String, Object> reaMap=new HashMap<String, Object>();
	reaMap.put("PdfCode", String.valueOf(listCount.size()));
	reaMap.put("Volume", String.valueOf(countThumbs));
	reaMap.put("Issue", isUser);
	
		result.setErrCode(0);
		result.setResultData(reaMap);
		return result;
	}
	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/knowledgePostil/getPostNum/{id}", method = RequestMethod.GET)
	public ResultVo getPostNum(@PathVariable("id") Integer id) {
		ResultVo result=new ResultVo();
		List<KnowledgeClassifyPostil> knowledgePostil = knowledgePostilService.getById(id);
		//List<KnowledgePostilVo> res=new ArrayList<KnowledgePostilVo>();
		List<KnowledgeClassifyPostil> listCount=new ArrayList<KnowledgeClassifyPostil>();
		if(knowledgePostil!=null&&knowledgePostil.size()>0){
			for(int i=0;i<knowledgePostil.size();i++){
				if(knowledgePostil.get(i).getPostilId()==null){
					//countPostil++;
					listCount.add(knowledgePostil.get(i));
				}
			}
			}
		result.setErrCode(0);
		result.setResultData(listCount.size());
		return result;
	}
	
	@RequestMapping(value = "/knowledgePostil/{name}", method = RequestMethod.GET)
	public List<KnowledgeClassifyPostil> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<KnowledgeClassifyPostil> knowledgePostils = knowledgePostilService.getByName(name);
		if (knowledgePostils == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return knowledgePostils;
	}
	

	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/knowledgePostil/addApp", method = RequestMethod.POST)
	public ResultVo saveOrUpdate(@RequestBody com.labwinner.vo.PostilVo postilVo) {
		KnowledgeClassifyPostil knowledgePostilList=new KnowledgeClassifyPostil();
		 ResultVo res=new ResultVo();
		 try {
			 LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			 KnowledgeAcc knowledgeAcc=new KnowledgeAcc();
			 knowledgeAcc.setKnowledgeAccId(Integer.valueOf(postilVo.getKnowledgeAccId()));
			 knowledgePostilList.setKnowledgeAcc(knowledgeAcc);
			 knowledgePostilList.setSysUser(sysUser);
			 String ReplyContent=postilVo.getReplyContent();
		//	 ReplyContent= new String(ReplyContent.getBytes("UTF-8"), "UTF-8");
		//	 knowledgePostilList.setReplyContent(ReplyContent);
			 knowledgePostilList.setReplyContent(postilVo.getReplyContent());
			 if(postilVo.getReplyRecordId()!=null){
				 knowledgePostilList.setReplyRecordId(Integer.valueOf(postilVo.getReplyRecordId()));
			 }
			 if(postilVo.getPostilId()!=null){
				 knowledgePostilList.setPostilId(Integer.valueOf(postilVo.getPostilId()));
			 }
			 knowledgePostilList.setReplyDate(new Date());
			 knowledgePostilService.save(knowledgePostilList);
			 res.setErrCode(0);
			 res.setResultData(knowledgePostilList.getKnowledgeClassifyPostilId());
		} catch (Exception e) {
			// TODO: handle exception
			res.setErrCode(1);
			res.setErrMsg(e.getMessage());
		}
		return res;
	}
	
	
	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/knowledgePostil/addThumbs", method = RequestMethod.POST)
	public ResultVo saveaddThumbs(@RequestBody com.labwinner.vo.KnowledgeThumbsUpVo knowledgeThumbsUpVo) {
	//	KnowledgeClassifyPostil knowledgePostilList=new KnowledgeClassifyPostil();
		 ResultVo res=new ResultVo();
		 try {
			 LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				KnowledgeThumbsUp knowledgeThumbsUp=new KnowledgeThumbsUp();
				knowledgeThumbsUp.setSysUser(sysUser);
				KnowledgeAcc knowledgeAcc=new KnowledgeAcc();
				String aa=knowledgeThumbsUpVo.getKnowledgeAccId();
				knowledgeAcc.setKnowledgeAccId(Integer.valueOf(knowledgeThumbsUpVo.getKnowledgeAccId()));
				knowledgeThumbsUp.setKnowledgeAcc(knowledgeAcc);
				knowledgeThumbsUpService.save(knowledgeThumbsUp);
			 res.setErrCode(0);
			 res.setResultData(knowledgeThumbsUp.getKnowledgeThumbsUpId());
		} catch (Exception e) {
			// TODO: handle exception
			res.setErrCode(1);
			res.setErrMsg(e.getMessage());
		}
		return res;
	}
	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/knowledgePostil/add", method = RequestMethod.POST)
	public void saveOrUpdate(@RequestBody List<KnowledgeClassifyPostil> knowledgePostilList) {
		try {
			for(int i=0;i<knowledgePostilList.size();i++){
				//TODO 判断更新，增加
				Integer id = knowledgePostilList.get(i).getKnowledgeClassifyPostilId();
				if (id != null) {
					knowledgePostilService.update(knowledgePostilList.get(i));
				} else {
					knowledgePostilService.save(knowledgePostilList.get(i));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/knowledgePostil/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		 ResultVo res=new ResultVo();
		 try {
			 knowledgePostilService.deleteByPostileId(id);
			 res.setErrCode(0);
		} catch (Exception e) {
			// TODO: handle exception
			 res.setErrCode(1);
		}
		return res;
	}
	
	@RequestMapping(value = "/knowledgePostil/app/{id}", method = RequestMethod.GET)
	public ResultVo deleteApp(@PathVariable("id") Integer id) {
		 ResultVo res=new ResultVo();
		 try {
			 knowledgePostilService.delete(id);
			 res.setErrCode(0);
		} catch (Exception e) {
			// TODO: handle exception
			 res.setErrCode(1);
		}
		return res;
	}

	@RequestMapping(value = "/knowledgePostil/delete/{id}", method = RequestMethod.GET)
	public ResultVo deleteByPostileId(@PathVariable("id") Integer id) {
		 ResultVo res=new ResultVo();
		 try {
			 knowledgePostilService.deleteByPostileId(id);
			 res.setErrCode(0);
		} catch (Exception e) {
			// TODO: handle exception
			 res.setErrCode(1);
		}
		return res;
	}


}
