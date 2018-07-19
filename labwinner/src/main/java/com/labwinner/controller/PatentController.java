package com.labwinner.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.LabConstans;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.JournalArticle;
import com.labwinner.domain.JournalUser;
import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.KnowledgeClassify;
import com.labwinner.domain.KnowledgeClassifyReacRela;
import com.labwinner.domain.KnowledgeField;
import com.labwinner.domain.KnowledgeProRela;
import com.labwinner.domain.Magazine;
import com.labwinner.domain.MarketAssist;
import com.labwinner.domain.MarketAssistImage;
import com.labwinner.domain.MaterialField;
import com.labwinner.domain.Patent;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.SysRole;
import com.labwinner.domain.SysUser;
import com.labwinner.service.JournalArticleService;
import com.labwinner.service.JournalUserService;
import com.labwinner.service.KnowledgeAccService;
import com.labwinner.service.KnowledgeFieldService;
import com.labwinner.service.KnowledgePostilService;
import com.labwinner.service.KnowledgeProRelaService;
import com.labwinner.service.KnowledgeReacRelaService;
import com.labwinner.service.KnowledgeThumbsUpService;
import com.labwinner.service.MagazineService;
import com.labwinner.service.MarketAssistService;
import com.labwinner.service.MaterialFieldService;
import com.labwinner.service.PatentService;
import com.labwinner.service.ReactionService;
import com.labwinner.service.SysRoleService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.Base64Util;
import com.labwinner.util.OtherToPdf;
import com.labwinner.util.PdfUtil;
import com.labwinner.vo.PatentVo;


@RestController
public class PatentController {
	
	private static Logger logger = LoggerFactory
			.getLogger(PatentController.class);



	@Autowired
	private PatentService patentService;
	@Autowired
	private KnowledgeAccService knowledgeAccService;
	@Autowired
	private KnowledgeProRelaService knowledgeProRelaService;
	@Autowired
	private KnowledgeReacRelaService knowledgeReacRelaService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private MagazineService magazineService;
	@Autowired
	private JournalArticleService journalArticleService;
	@Autowired
	private JournalUserService journalUserService;
	
	@Autowired
	private ReactionService reactionService;
	
	@Autowired
	private  KnowledgePostilService knowledgePostilService;
	
	@Autowired
	private  MaterialFieldService materialFieldService;
	

	@Autowired
	private KnowledgeFieldService knowledgeFieldService;
	
	@Autowired
	private MarketAssistService marketAssistService;
	
	@Autowired
	private KnowledgeThumbsUpService knowledgeThumbsUpService;
	
	@Value("${web.upload_path_pdf}")
	String filePath;
	@Value("${web.url_path_pdf}")
	String basePath;
	
	@Value("${web.agency_pdf}")
	String imgUrlPath;
	
	@Value("${sysUserPhone.url-path}")
	String userImage;

	
	@Value("${web.agency_pdf}")
	String pdfPath;
	
	@Value("${web.url_path_fieldImage}")
	private String fieldImage;
	
	@Value("${marketAssist.url-path}")
	private String urlPath;
	
	@Value("${pdfView.url-path}")
	private String pdfViewUrl;
	
	@Value("${pdfView.upload-path}")
	private String pdfViewUpload;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/patent/list/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAll(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		ResultVo res=new ResultVo();
		List<Patent> list=new ArrayList<Patent>();
		if(keyword==null||"".equals(keyword)||"undefined".equals(keyword)){
			 list = patentService.getAll();
		}else{
			list=patentService.getByName(keyword);
		}
		for(Patent jour:list){
			KnowledgeAcc acc=new KnowledgeAcc();
			if(jour.getKnowledgeAcc()!=null){
				acc.setKnowledgeAccId(jour.getKnowledgeAcc().getKnowledgeAccId()==null?0:jour.getKnowledgeAcc().getKnowledgeAccId());
				String uf=jour.getKnowledgeAcc().getUploadFiles()==null?"":jour.getKnowledgeAcc().getUploadFiles();
//				String uploadFiles="";
//				if(uf.indexOf("/pdf")>0){
//					uploadFiles=uf.substring(uf.indexOf("/pdf")+6, uf.length());
//				}
				acc.setUploadFiles(uf);
			}
			jour.setKnowledgeAcc(acc);
		}
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		res.setErrCode(0);
		res.setErrMsg("查询成功！");
		res.setResultData(new  PageInfo(list));
		return res;
	}

	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/patent/listForApp/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAllForApp(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		int size=page*pageSize;
		ResultVo res=new ResultVo();
		List<Patent> list=new ArrayList<Patent>();
		if(keyword == null ||  "".equals(keyword)
				|| "undefined".equals(keyword)){
			 list = patentService.getAllForApp(size);
		}else{
			list = patentService.getByNameForApp(size,keyword);
		}
		for(Patent jour:list){
			KnowledgeAcc acc=new KnowledgeAcc();
			if(jour.getKnowledgeAcc()!=null){
				acc.setKnowledgeAccId(jour.getKnowledgeAcc().getKnowledgeAccId()==null?0:jour.getKnowledgeAcc().getKnowledgeAccId());
				String uf=jour.getKnowledgeAcc().getUploadFiles()==null?"":jour.getKnowledgeAcc().getUploadFiles();
				String uploadFiles="";
				if(uf.indexOf("/pdf")>0){
					uploadFiles=uf.substring(uf.indexOf("/pdf")+6, uf.length());
					if(jour.getSource()-0!=0){
						uploadFiles=imgUrlPath+uploadFiles;
					}else{
						uploadFiles=pdfPath+uploadFiles;
					}
				}
				
				
				acc.setUploadFiles(uploadFiles);
			}
			jour.setKnowledgeAcc(acc);
		}
		if(list==null||list.size()==0){
			res.setErrCode(1);
		}else{
			res.setErrCode(0);
		}
		//res.setErrCode(0);
		res.setErrMsg("查询成功！");
		res.setResultData(list);
		return res;
	}
	

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/patent/getById/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		
		ResultVo res=new ResultVo();
		List<KnowledgeField> knowledgeFields=new ArrayList<KnowledgeField>();
		try {
			PatentVo patentVo=new PatentVo();
			SysUser ss=getSysUser();
			SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
			String roleName=sysRole.getRoleName();
			Patent patent = patentService.getById(id);
			if(patent.getMaterialField()!=null&&patent.getMaterialField().getCid()!=null){
				int fieldId=patent.getMaterialField().getCid();
				String field=getAllPname(fieldId);
				//field=field+">"+journalArticle.getMaterialField().getLabel();
				patent.getMaterialField().setParentName(field);
			}else{
				patent.setMaterialField(new MaterialField());
			}
			
			if(patent.getKnowledgeField()!=null&&!"".equals(patent.getKnowledgeField())){
				String[] fileds=patent.getKnowledgeField().split(",");
				for(int i=0;i<fileds.length;i++){
					KnowledgeField knowledgeField=new KnowledgeField();
					knowledgeField=knowledgeFieldService.getById(Integer.valueOf(fileds[i]));
					knowledgeFields.add(knowledgeField);
				}
			}
			if(patent!=null){
				String isEditOrNot="";
				if(patent.getSource()-0==0){
					isEditOrNot="false";
				}else{
					if(ss!=null&&patent.getSysUser()!=null){
						if(ss.getUserId()-patent.getSysUser().getUserId()==0){
							isEditOrNot="true";
						}else{
							isEditOrNot="false";
						}
					}
				}
				
				
				KnowledgeAcc  knowledgeAcc=new KnowledgeAcc();
				if(patent.getKnowledgeAcc()!=null){
					knowledgeAcc=knowledgeAccService.getById(patent.getKnowledgeAcc().getKnowledgeAccId());
				}
				
				Map<String, Object> mapPro=new HashMap<String, Object>();
				mapPro.put("journalActicleId", id);
				mapPro.put("knowledgeClassifyId", 4);
				mapPro.put("roleName",roleName);
				mapPro.put("userId",ss.getUserId());
				List<KnowledgeProRela> listPro=knowledgeProRelaService.getAllByKnowladge(mapPro);
				//List<KnowledgeClassifyReacRela> listReac=knowledgeReacRelaService.getAllByKnowladge(mapPro);
				List<KnowledgeClassifyReacRela> listReac=new ArrayList<KnowledgeClassifyReacRela>();
				if (!roleName.equals("ROLE_TEAM")) {
					List<Reaction> list = reactionService.getUserList(ss.getUserId());
					if (list != null) {
					for(int i=0;i<list.size();i++){
						mapPro.put("reactionId", list.get(i).getReactionId());
						List<KnowledgeClassifyReacRela> listAdd=knowledgeReacRelaService.getAllByKnowladge(mapPro);
						if(listAdd!=null&&listAdd.size()>0){
							listReac.addAll(listAdd);
						}
					}
				} 
				}else {
					List<Reaction> list = reactionService.getAll();
					if (list != null) {
						for(int i=0;i<list.size();i++){
							mapPro.put("reactionId", list.get(i).getReactionId());
							List<KnowledgeClassifyReacRela> listAdd=knowledgeReacRelaService.getAllByKnowladge(mapPro);
							if(listAdd!=null&&listAdd.size()>0){
								listReac.addAll(listAdd);
							}
						}
					}
					
				}
				
				String pdfName="";
				if(knowledgeAcc!=null&&knowledgeAcc.getUploadFiles()!=null){
					pdfName=knowledgeAcc.getUploadFiles().substring(knowledgeAcc.getUploadFiles().lastIndexOf("/")+1);
					knowledgeAcc.setImage(knowledgeAcc.getUploadFiles());
					//String url=imgUrlPath.substring(0,imgUrlPath.indexOf("/image/")+1);
					if(patent.getSource()-0==0){
						knowledgeAcc.setUploadFiles(basePath+pdfName);
					}else{
						knowledgeAcc.setUploadFiles(imgUrlPath+pdfName);
						
					}
				}
				patent.setKnowledgeAcc(knowledgeAcc);
				patentVo.setPdfName(pdfName);
				patentVo.setPatent(patent);
				patentVo.setKnowledgeProRela(listPro);
				patentVo.setKnowledgeClassifyReac(listReac);
				patentVo.setIsEditOrNot(isEditOrNot);
				patentVo.setKnowledgeFields(knowledgeFields);
				res.setErrCode(0);
				res.setErrMsg("find success");
				res.setResultData(patentVo);
			}else{
				res.setErrCode(4);
				res.setErrMsg("无此记录");
			}
		} catch (Exception e) {
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			res.setErrCode(1);
			res.setErrMsg(e.getMessage());
		}
		return res;
	}
	
	 public String getAllPname(Integer id) {
		    try {
		      String ss="";
		    loop:for(int i=0;i<10;i++){
		      MaterialField materialField =  materialFieldService.getByCid(id);
		      if(materialField.getPid()!=null&&materialField.getPid()!=0){
		        if(i==0){
		          ss="";
		        }else if(i==1){
		          ss=materialField.getLabel();
		        }else{
		          ss=materialField.getLabel()+">"+ss;
		        }
		        id=materialField.getPid();
		      }else{
		        if(!"".equals(ss)){
		          ss=materialField.getLabel()+">"+ss;
		        }else{
		          ss=materialField.getLabel();  
		        }
		        break loop;
		      }
		      }
		      return ss;
		    } catch (Exception e) {
		    	return null;
		      // TODO: handle exception
		    }
		  }
	
	@RequestMapping(value = "/patent/{name}", method = RequestMethod.GET)
	public List<Patent> getByName(@PathVariable("name") String name) {
		//主表实体类
		List<Patent> patents = patentService.getByName(name);
		if (patents == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return patents;
	}
	

	

	
	
	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @throws Exception 
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/patent/add", method = RequestMethod.POST)
	public ResultVo saveOrUpdate(@RequestBody PatentVo patentVo) throws Exception {
		ResultVo res=new ResultVo();
		try {
			//TODO 判断更新，增加
			Integer id = patentVo.getPatent().getPatentId();
			//patent patent=patentService.getById(id);
			SysUser ss=getSysUser();
			String pdfName=patentVo.getPdfName();
			Patent patent=patentVo.getPatent();
			SysUser sysUser=getSysUser();
			List<String> pdfStrs=patentVo.getPdfStrs();
			if(id==null){
				patent.setCreater(sysUser.getRealname());
				patent.setCreateDate(new Date());
				patent.setSource(ss.getSysSigningAgency().getAgencyId());
				patentService.save(patent);
			}
			id=patent.getPatentId();
			KnowledgeAcc knowledgeAcc=new KnowledgeAcc();
			Base64Util base64Util = new Base64Util();
			if(pdfStrs!=null&&pdfStrs.size()>0){//保存pdf文件
			
				knowledgeAcc=savePdfStr(knowledgeAcc,id,pdfStrs,pdfName,4,filePath);
			}
			patent.setKnowledgeAcc(knowledgeAcc);
			patent.setSysUser(sysUser);
			if(id!=null){
				patentService.update(patent);
			}
			
	
			List<KnowledgeProRela> knowledgeProRelaList=patentVo.getKnowledgeProRela();//知识项目
			List<KnowledgeClassifyReacRela> knowledgeClassifyReacRelaList=patentVo.getKnowledgeClassifyReac();//知识实验
			if(knowledgeProRelaList!=null&&knowledgeProRelaList.size()>0){//遍历保存关联项目
				for(int i=0;i<knowledgeProRelaList.size();i++){
					KnowledgeProRela knowledgeProRela=new KnowledgeProRela();
					knowledgeProRela.setMappingKnowledgeIdl(patent.getPatentId());
					ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();//项目基本信息
					projectBasicInfo.setProId(knowledgeProRelaList.get(i).getMappingKnowledgeIdl());
					knowledgeProRela.setProjectBasicInfo(projectBasicInfo);
					KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
					knowledgeClassify.setKnowledgeClassifyId(4);
					knowledgeProRela.setKnowledgeClassify(knowledgeClassify);
					knowledgeProRela.setCreater(patent.getSysUser());
					knowledgeProRela.setCreateDate(new Date());
					knowledgeProRelaService.save(knowledgeProRela);//保存关联项目表
				}
			}
			if(knowledgeClassifyReacRelaList!=null&&knowledgeClassifyReacRelaList.size()>0){//遍历保存关联实验
				for(int i=0;i<knowledgeClassifyReacRelaList.size();i++){
					KnowledgeClassifyReacRela knowledgeClassifyReacRela=new KnowledgeClassifyReacRela();
					knowledgeClassifyReacRela.setMappingKnowledgeIdl(patent.getPatentId());
					Reaction relation=new Reaction();  //实验基本信息
					relation.setReactionId(knowledgeClassifyReacRelaList.get(i).getKnowledgeClassifyReacRelaId());
					knowledgeClassifyReacRela.setReaction(relation);
					knowledgeClassifyReacRela.setKnowledgeClassifyId(4);
					knowledgeClassifyReacRela.setCreater(String.valueOf(patent.getSysUser().getId()));
					knowledgeClassifyReacRela.setCreateDate(new Date());
					knowledgeReacRelaService.save(knowledgeClassifyReacRela);//保存关联实验
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/patent/addPatent", method = RequestMethod.PUT)
	public void saveJournaleArtilce(@RequestBody Patent patent) {
		try {
			//TODO 判断更新，增加
			Integer id = patent.getPatentId();
			SysUser sysUser=getSysUser();
			Patent res=patentService.getById(id);
			res.setAbstract_(patent.getAbstract_());
			res.setApplicant(patent.getApplicant());
			res.setApplicantAddr(patent.getApplicantAddr());
			res.setApplicantPostcode(patent.getApplicantPostcode());
			res.setApplicationDate(patent.getApplicationDate());
			res.setApplicationNumber(patent.getApplicationNumber());
			res.setInventor(patent.getInventor());
			res.setIpcIndex(patent.getIpcIndex());
			res.setLinkUrl(patent.getLinkUrl());
			res.setPatentName(patent.getPatentName());
//			patent.setCreater(sysUser.getRealname());
//			patent.setCreateDate(new Date());
			res.setKnowledgeField(patent.getKnowledgeField());
			//res.setPatentStatus(patent.getPatentStatus());
			res.setPriorityNum(patent.getPriorityNum());
			res.setPriorotyDate(patent.getPriorotyDate());
			res.setPublicationDate(patent.getPublicationDate());
			res.setPublicationNumber(patent.getPublicationNumber());
			if(id==null){
				patentService.save(res);
			}else{
				patentService.update(res);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	/**
	 * @Description 保存关联实验对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/patent/addKnowledgeReacRela", method = RequestMethod.POST)
	public void saveKnowledgeReacRela(@RequestBody List<KnowledgeClassifyReacRela>  listReacRelaUpdate,@PathVariable("patentId") Integer patentId) {
		try {
			Map<String, Object> mapPro=new HashMap<String, Object>();
			SysUser ss=getSysUser();
			SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
			String roleName=sysRole.getRoleName();
			mapPro.put("roleName",roleName);
			mapPro.put("userId",ss.getUserId());
			mapPro.put("journalActicleId", patentId);
			mapPro.put("knowledgeClassifyId", 4);
			List<KnowledgeClassifyReacRela> listReac=new ArrayList<KnowledgeClassifyReacRela>();
			if (!roleName.equals("ROLE_TEAM")) {
				List<Reaction> list = reactionService.getUserList(ss.getUserId());
				if (list != null) {
				for(int i=0;i<list.size();i++){
					mapPro.put("reactionId", list.get(i).getReactionId());
					List<KnowledgeClassifyReacRela> listAdd=knowledgeReacRelaService.getAllByKnowladge(mapPro);
					if(listAdd!=null&&listAdd.size()>0){
						listReac.addAll(listAdd);
					}
				}
			} 
			}else {
				List<Reaction> list = reactionService.getAll();
				if (list != null) {
					for(int i=0;i<list.size();i++){
						mapPro.put("reactionId", list.get(i).getReactionId());
						List<KnowledgeClassifyReacRela> listAdd=knowledgeReacRelaService.getAllByKnowladge(mapPro);
						if(listAdd!=null&&listAdd.size()>0){
							listReac.addAll(listAdd);
						}
					}
				}
				
			}
			//List<KnowledgeClassifyReacRela> listPro=knowledgeReacRelaService.getAllByKnowladge(mapPro);
			for(int i=0;i<listReac.size();i++){
				knowledgeReacRelaService.delete(listReac.get(i).getKnowledgeClassifyReacRelaId());
			}
			for(int i=0;i<listReacRelaUpdate.size();i++){
				KnowledgeClassifyReacRela knowledgeReacRela=listReacRelaUpdate.get(i);
				knowledgeReacRela.setKnowledgeClassifyId(4);
				knowledgeReacRelaService.save(knowledgeReacRela);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * @Description 保存关联项目对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/patent/addKnowledgePro", method = RequestMethod.POST)
	public void saveKnowledgePro(@RequestBody List<KnowledgeProRela>  listProUpdate,@PathVariable("patentId") Integer patentId) {
		try {
			Map<String, Object> mapPro=new HashMap<String, Object>();
			SysUser ss=getSysUser();
			SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
			String roleName=sysRole.getRoleName();
			mapPro.put("roleName",roleName);
			mapPro.put("userId",ss.getUserId());
			mapPro.put("journalActicleId", patentId);
			mapPro.put("knowledgeClassifyId", 4);
			List<KnowledgeProRela> listPro=knowledgeProRelaService.getAllByKnowladge(mapPro);
			for(int i=0;i<listPro.size();i++){
				knowledgeProRelaService.delete(listPro.get(i).getKnowledgeProRelaId());
			}
			for(int i=0;i<listProUpdate.size();i++){
				KnowledgeProRela knowledgeProRela=listProUpdate.get(i);
				KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
				knowledgeClassify.setKnowledgeClassifyId(4);
				knowledgeProRela.setKnowledgeClassify(knowledgeClassify);
				knowledgeProRelaService.save(knowledgeProRela);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/**
	 * @Description 保存附件对象
	 * @author liuhq
	 * @version V1.0
	 * @throws Exception 
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/patent/addKnowAcc", method = RequestMethod.PUT)
	public ResultVo saveKnowAcc(@RequestBody PatentVo patentVo) throws Exception {
		ResultVo res=new ResultVo();
		try {
			Integer knowledgeAccId=patentVo.getPatent().getKnowledgeAcc().getKnowledgeAccId();
			KnowledgeAcc knowledgeAcc=knowledgeAccService.getById(knowledgeAccId);
			Base64Util base64Util = new Base64Util();
			knowledgeAcc=savePdfStr(knowledgeAcc,patentVo.getPatent().getPatentId(),patentVo.getPdfStrs(),patentVo.getPdfName(),4,filePath);
			Patent patent=patentService.getById(patentVo.getPatent().getPatentId());
			patent.setKnowledgeAcc(knowledgeAcc);
			patentService.update(patent);
			res.setErrCode(0);
			res.setErrMsg("保存数据成功！");
			res.setResultData(knowledgeAcc);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	
	/**
	 * 保存附件表和上传pdf
	 * @throws Exception 
	 * 
	 * 
	 * **/
	@Transactional(rollbackFor=Exception.class)
	public KnowledgeAcc savePdfStr(KnowledgeAcc knowledgeAcc,Integer id, List<String> pdfStrs,String pdfName,Integer knowledgeClassifyId,String filePath) throws Exception {
		//KnowledgeAcc knowledgeAcc = new KnowledgeAcc();
		try {
			if(pdfStrs!=null&&pdfStrs.size()>0){
				//base64保存图片
				Base64Util base64Util = new Base64Util();
				pdfName=base64Util.StringFilter(pdfName);
				pdfName=UUID.randomUUID().toString()+pdfName;
				KnowledgeClassify knowledgeClassify=new KnowledgeClassify(); 
				//目前设置科技论文的知识分类主键为1
				knowledgeClassify.setKnowledgeClassifyId(4);
				for(String pdfStr:pdfStrs){
					pdfStr = pdfStr.substring(pdfStr.indexOf(",")+1);
					if(knowledgeAcc==null||knowledgeAcc.getKnowledgeAccId()==null){
						knowledgeAcc=new KnowledgeAcc();
						String fileUrl=base64Util.GeneratePdf(pdfStr,filePath,pdfName);
						//String type=fileUrl.substring(fileUrl.indexOf(".")+1, fileUrl.length());
						String type=fileUrl.substring(fileUrl.lastIndexOf(".")+1, fileUrl.length());
						if(LabConstans.CONVERSION_TYPE.contains(type)){
							knowledgeAcc.setPdfCount(0);
						}else if("pdf".equals(type)){
							knowledgeAcc.setPdfCount(2);
						}else{
							knowledgeAcc.setPdfCount(99);
						}
					
						knowledgeAcc.setUploadFiles(fileUrl);
						knowledgeAcc.setKnowledgeId(id);
						//knowledgeAcc.set   还需要设置知识分类
						knowledgeAcc.setKnowledgeClassify(knowledgeClassify);
						knowledgeAccService.save(knowledgeAcc);
					}else{
						base64Util.DeleteFolder(knowledgeAcc.getUploadFiles());
						String fileUrl=base64Util.GeneratePdf(pdfStr,filePath,pdfName);
						String fileName=fileUrl.substring(fileUrl.indexOf("/pdfs/")+6, fileUrl.length());
						//String type=fileUrl.substring(fileUrl.indexOf(".")+1, fileUrl.length());
						String type=fileUrl.substring(fileUrl.lastIndexOf(".")+1, fileUrl.length());
						if(LabConstans.CONVERSION_TYPE.contains(type)){
							knowledgeAcc.setPdfCount(0);
						}else if("pdf".equals(type)){
							knowledgeAcc.setPdfCount(2);
						}else{
							knowledgeAcc.setPdfCount(99);
						}
						//knowledgeAcc.setPdfCount(0);  
						knowledgeAcc.setConversionCount(0);
						knowledgeAcc.setPdfUrl(null);
						knowledgeAcc.setUploadFiles(fileUrl);
						knowledgeAccService.update(knowledgeAcc);
						knowledgePostilService.deleteByAccId(knowledgeAcc.getKnowledgeAccId());
						knowledgeThumbsUpService.deleteByAccId(knowledgeAcc.getKnowledgeAccId());
					}
					
				}
				knowledgeAcc.setUploadFiles(imgUrlPath+pdfName);
//				Map<String, Object> pdfMap=padfToImage(pdfName,1);
//				knowledgeAcc.setPdfCount((Integer)pdfMap.get("pdfNun"));
//				knowledgeAccService.update(knowledgeAcc);
			}
		} catch (Exception e) {
			throw e;
		}
		return knowledgeAcc;
	}

	
	/**
	 * @Description PDF转为image
	 * @author wangll
	 * @version V1.0
	 * @throws IOException 
	 * @throws InvalidPasswordException 
	 * @date 2017年6月28日 
	 */
//	public Map<String,Object> padfToImage(@RequestParam("pdfName") String pdfName,@RequestParam("pdfNum")Integer pdfNum) throws InvalidPasswordException, IOException {
//		Map<String,Object> resMap=new HashMap<String,Object>();
//		String pdf=pdfName.substring(0,pdfName.indexOf("."));
//		int pageCounter = pdfNum-1;
//		Map<Integer, String> maps=new HashMap<Integer, String>();
//		List<Map<Integer, String>> imgMap=new ArrayList<Map<Integer,String>>();
//		List<String> imgAddr=new ArrayList<String>();
//			PDDocument document = PDDocument.load(new File(filePath+pdfName));
//			PDFRenderer pdfRenderer = new PDFRenderer(document);
//		for (PDPage page : document.getPages()){
//			if(pageCounter<document.getNumberOfPages()){
//					 BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 150, ImageType.RGB);
//					    String fileAddr=imgPath +pdf+ "/img" + (pageCounter++) + ".png";
//					    File outFile = new File( fileAddr);
//					    if (!outFile.getParentFile().exists()) {
//					    	outFile.getParentFile().mkdirs();
//					     }
//					    	 ImageIO.write(bim, "png", outFile);// 写图片 
//					    	 imgAddr.add(imgUrlPath+pdf+"/img" +(pageCounter-1)+ ".png");
//					    	 maps.put(pageCounter, imgUrlPath+pdf+"/img" +(pageCounter-1)+ ".png");
//			}
//			}
//			document.close();
//			resMap.put("imgAddr", maps);
//			resMap.put("pdfNun", document.getNumberOfPages());
//			return resMap;
//		}
	/***
 	* 获取当前登录人员信息
 	* 
 * */
public SysUser  getSysUser(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		SysUser sysUser = new SysUser();
	//	String username = getPrincipal();
		List<SysUser> list =  sysUserService.getByUsername(userName);
		if (list != null && list.size() > 0) {
			sysUser = list.get(0);
		}
		return sysUser;
} 

	@RequestMapping(value = "/patent/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		patentService.delete(id);
		journalArticleService.deletePro(4,id);
		journalArticleService.deleteReac(4,id);
	}
	
	
	@RequestMapping(value = "/patent/del/{id}", method = RequestMethod.DELETE)
	public void deleteForWeb(@PathVariable("id") Integer id) {
		patentService.delete(id);
		journalArticleService.deletePro(4,id);
		journalArticleService.deleteReac(4,id);
	}
	
	
	//专利新首页展示
	
	@RequestMapping(value = "/patent/fieldList", method = RequestMethod.GET)
	public 	ResultVo  getAllFieldList()  {
	
		ResultVo resultVo = new ResultVo();
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		int angency=ss.getSysSigningAgency().getAgencyId();
		int personKnoledgeNum=patentService.getPersonKnoledgeNum(ss.getUserId());
		List<MaterialField> list1 = materialFieldService.getAllFirst();
		List<KnowledgeField> knowledgeFieldList = knowledgeFieldService.getAll();
		if(knowledgeFieldList!=null&&knowledgeFieldList.size()>0){
			for(KnowledgeField knowledgeField:knowledgeFieldList){
				if(knowledgeField.getDefaultImage()!=null&&knowledgeField.getDefaultImage().getUrl()!=null){
					knowledgeField.getDefaultImage().setUrl(fieldImage+knowledgeField.getDefaultImage().getUrl());
				}
				int count=knowledgeFieldService.getCountByKnowledgeFieldForPatent(knowledgeField.getKnowledgeFieldId());
				knowledgeField.setKnowledgeNum(count);
			}
		}
		List<Object> materialFieldList=new ArrayList<Object>();
		if(list1!=null&&list1.size()>0){
			for(int i=0;i<list1.size();i++){
				MaterialField materialField = materialFieldService.getTree(list1.get(i).getCid());
				if(materialField!=null){
					materialFieldList.add(materialField);
				}
			}
		}
		List<Patent> personList=new ArrayList<Patent>();
		personList=patentService.getAgencyPatent(ss.getUserId(),roleName,angency);
		if(personList!=null&&personList.size()>0){
			for(Patent person:personList){
//				if(person.getKnowledgeField()!=null&&!"".equals(person.getKnowledgeField())){
//					String[] kf=person.getKnowledgeField().split(",");
//					String knowledgeFieldName="";
//					for(int i=0;i<kf.length;i++){
//						KnowledgeField knowledgeField=knowledgeFieldService.getById(Integer.valueOf(kf[i]));
//						if(i-0==0){
//							knowledgeFieldName=knowledgeFieldName+knowledgeField.getKnowledgeField();
//						}else{
//							knowledgeFieldName=knowledgeFieldName+","+knowledgeField.getKnowledgeField();
//						}
//						
//					}
//					person.setKnowledgeField(knowledgeFieldName);
//				}
				if(person.getSysUser().getUserImage()!=null&&!"".equals(person.getSysUser().getUserImage())){
					String url=person.getSysUser().getUserImage();
					String path=userImage+url;
					person.getSysUser().setUserImage(path);
				}
			}
		}
		List<Patent> baseUpList=new ArrayList<Patent>();
		baseUpList=patentService.getBasePatent();
		Map<String, Object> res=new HashMap<String, Object>();
		res.put("materialFieldList", materialFieldList);
		res.put("knowledgeFieldList", knowledgeFieldList);
		res.put("baseUpList", baseUpList);
		res.put("personList", personList);
		res.put("personKnoledgeNum", personKnoledgeNum);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(res);
		return resultVo;
	}
	
	/**
	 * @Description 根据文献分类和名字获取科技论文
	 * @author liuhq
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/patent/getBaseListByFieldId/{keyword}/{filedId}", method = RequestMethod.GET)
	public 	ResultVo  getBaseListByFieldId(@PathVariable("keyword") String keyword,@PathVariable("filedId") Integer filedId)  {
		ResultVo resultVo = new ResultVo();
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		List<MaterialField> materialFieldList=new ArrayList<MaterialField>();
		List<Object> resList=new ArrayList<Object>();
		List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
			materialFieldList=materialFieldService.getAllSamePid(filedId);
			if(materialFieldList!=null&&materialFieldList.size()>0){
				for(int i=0;i<materialFieldList.size();i++){
					MaterialField materialField = materialFieldService.getTree(materialFieldList.get(i).getCid());
					if(materialField!=null){
						resList.add(materialField);
					}
				}
			}
			if(filedId-0==0){
				knowledgeList = patentService.getAllBase(keyword);
			}else{
				knowledgeList = patentService.getAllBaseByFiledId(keyword,filedId);
			}
			Boolean baseKey=false;
		if(knowledgeList!=null&&knowledgeList.size()>7){
			baseKey=true;
		}
		Map<String, Object> res=new HashMap<String, Object>();		
		res.put("knowledgeList", knowledgeList);
		res.put("materialField", resList);
		res.put("baseKey", baseKey);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(res);
		return resultVo;
	}
	
	

	@RequestMapping(value = "/patent/getPersonListByFieldId/{page}/{pageSize}/{keyword}/{filedId}", method = RequestMethod.GET)
	public 	ResultVo  getPersonListByFieldId(@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword,@PathVariable("filedId") Integer filedId)  {
		ResultVo resultVo = new ResultVo();
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
		List<KnowledgeField> knowledgeFieldList=new ArrayList<KnowledgeField>();
		
			knowledgeFieldList = knowledgeFieldService.getAll();
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			if(filedId-0==0){
				knowledgeList=patentService.getAllPerson(ss.getUserId(), roleName, ss.getSysSigningAgency().getAgencyId(), keyword);
			}else{
				knowledgeList=patentService.getAllPersonByFiledId(ss.getUserId(), roleName, ss.getSysSigningAgency().getAgencyId(), keyword, filedId);
			}
		Map<String, Object> res=new HashMap<String, Object>();		
		res.put("knowledgeList", new PageInfo(knowledgeList));
		res.put("knowledgeFieldList", knowledgeFieldList);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(res);
		return resultVo;
	}
	

	

	@RequestMapping(value = "/patent/getBaseListByFieldId/{page}/{pageSize}/{keyword}/{filedId}", method = RequestMethod.GET)
	public 	ResultVo  getBaseListByFieldId(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword,
			@PathVariable("filedId") Integer filedId)  {
		ResultVo resultVo = new ResultVo();
//		List<Object> res=new ArrayList<Object>();
	//	Map<String, Object> reaMap=new HashMap<String, Object>();
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		int angency=ss.getSysSigningAgency().getAgencyId();
		List<MaterialField> list1 = materialFieldService.getAllFirst();
		List<Object> resList=new ArrayList<Object>();
		if(list1!=null&&list1.size()>0){
			for(int i=0;i<list1.size();i++){
				MaterialField materialField = materialFieldService.getTree(list1.get(i).getCid());
				if(materialField!=null){
					resList.add(materialField);
				}
			}
		}
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		if(filedId-0==0){
			list = patentService.getAllBase(keyword);
		}else{
			list = patentService.getAllBaseByFiledId(keyword,filedId);
		}
		Map<String, Object> res=new HashMap<String, Object>();
		res.put("knowledgeList", new PageInfo(list));
		res.put("materialFieldList", resList);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(res);
		return resultVo;
	}
	
	
	
	@RequestMapping(value = "/patent/getSelfKnowledge/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getSelfKnowledge(@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		ResultVo resuslt=new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		Integer roleId=sysUser.getSysRole().getRoleId();
		String roleName=sysUser.getSysRole().getRoleName();
		try {
			List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
				if (page != null && pageSize != null) {
					PageHelper.startPage(page, pageSize);
				}
					knowledgeList=patentService.getSelfKnowledge(sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId(),keyword);
					Map<String, Object> res=new HashMap<String, Object>();
					res.put("knowledgeList",  new PageInfo(knowledgeList));
					resuslt.setErrCode(0);
					resuslt.setResultData(res);
		
		} catch (Exception e) {
			resuslt.setErrCode(1);
			resuslt.setErrMsg("查询失败！"+e.getMessage());
		}
	
		return resuslt;
	}
	
	//----------------------------------------------------app专利接口----------------------------------------------------------------
	
	 /**
	 * @Description 根据文献分类和名字获取科技论文
	 * @author liuhq
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/patent/fieldListApp/{page}/{pageSize}/{keyword}/{filedId}", method = RequestMethod.GET)
	public 	ResultVo  getAllFieldListsForApp(@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword,@PathVariable("filedId") Integer filedId)  {
	
		ResultVo resultVo = new ResultVo();
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		List<MaterialField> materialFieldList=new ArrayList<MaterialField>();
		List<Object> resList=new ArrayList<Object>();
	
		if(filedId-0==0){
			materialFieldList = materialFieldService.getAllFirst();
			resList.add(materialFieldList);
		}else{
			materialFieldList=materialFieldService.getAllSamePid(filedId);
			if(materialFieldList!=null&&materialFieldList.size()>0){
				for(int i=0;i<materialFieldList.size();i++){
					MaterialField materialField = materialFieldService.getTree(materialFieldList.get(i).getCid());
					if(materialField!=null){
						resList.add(materialField);
					}
				}
			}
		}
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
		knowledgeList = patentService.getAllBaseKnowByFiledId(keyword,filedId);
		for(Map<String, Object> base:knowledgeList){
			if(base.get("upload_files")!=null){
				String url=(String)base.get("upload_files");
				if(url!=null&&!"".equals(url)){
					String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
					String uploadFiles=filePath+pdfName;
					base.put("pdfPath", uploadFiles);
				}
				
			}
		}
		Map<String, Object> res=new HashMap<String, Object>();		
		res.put("knowledgeList", new  PageInfo(knowledgeList));
		res.put("materialField", resList);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(res);
		return resultVo;
	}
	
	
	
	@RequestMapping(value = "/patent/fieldListApp", method = RequestMethod.GET)
	public 	ResultVo  getAllFieldListForApp()  {
	
		ResultVo resultVo = new ResultVo();
//		List<Object> res=new ArrayList<Object>();
	//	Map<String, Object> reaMap=new HashMap<String, Object>();
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		int angency=ss.getSysSigningAgency().getAgencyId();
		int personKnoledgeNum=patentService.getPersonKnoledgeNum(ss.getUserId());
		List<MaterialField> list1 = materialFieldService.getAllFirst();
		List<Map<String, Object>> materialList=new ArrayList<Map<String, Object>>();
		if(list1!=null&&list1.size()>0){
			for(int i=0;i<list1.size();i++){
				Map<String, Object> map=new HashMap<String, Object>();
				int count =patentService.getMaterialNum(list1.get(i).getCid());
				map.put("materialField", list1.get(i));
				map.put("materialNum", count);
				materialList.add(map);
			}
		}
		List<KnowledgeField> knowledgeFieldList=new ArrayList<KnowledgeField>();
		
		knowledgeFieldList = knowledgeFieldService.getAll();
		if(knowledgeFieldList!=null&&knowledgeFieldList.size()>0){
			for(KnowledgeField knowledgeField:knowledgeFieldList){
				if(knowledgeField.getDefaultImage()!=null&&knowledgeField.getDefaultImage().getUrl()!=null){
					knowledgeField.getDefaultImage().setUrl(fieldImage+knowledgeField.getDefaultImage().getUrl());
				}
				int count=knowledgeFieldService.getCountByKnowledgeFieldForPatent(knowledgeField.getKnowledgeFieldId());
				knowledgeField.setKnowledgeNum(count);
			}
		}
		List<Map<String, Object>> listKnowAgency=new ArrayList<Map<String,Object>>();
		listKnowAgency=patentService.getKnowledgeFirst(roleName,ss.getUserId());
		if(listKnowAgency!=null&&listKnowAgency.size()>0){
			for(int i=0;i<listKnowAgency.size();i++){
				if(listKnowAgency.get(i).get("url")!=null){
					listKnowAgency.get(i).put("url", userImage+listKnowAgency.get(i).get("url"));
				}
				if(listKnowAgency.get(i).get("upload_files")!=null){
					//Integer source=Integer.valueOf(listKnow.get(i).get("source").toString());
					
//					if(source-0==0){
//						String url=listKnow.get(i).get("upload_files").toString();
//						String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
//						listKnow.get(i).put("upload_files", imgUrlPath+pdfName);
//					}else{
						String url=listKnowAgency.get(i).get("upload_files").toString();
						String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
						listKnowAgency.get(i).put("upload_files", imgUrlPath+pdfName);
				//	}
					
				}
			}
		}
		List<Map<String, Object>> listKnowBase=new ArrayList<Map<String,Object>>();
		listKnowBase=patentService.getKnowledgeFirstBase();
		if(listKnowBase!=null&&listKnowBase.size()>0){
			for(int i=0;i<listKnowBase.size();i++){
				if(listKnowBase.get(i).get("url")!=null){
					listKnowBase.get(i).put("url", userImage+listKnowBase.get(i).get("url"));
				}
				if(listKnowBase.get(i).get("upload_files")!=null){
						String url=listKnowBase.get(i).get("upload_files").toString();
						String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
						listKnowBase.get(i).put("upload_files", basePath+pdfName);
				}
			}
		}
		List<MarketAssist> marketAssists = marketAssistService.getByKeywordId(11, 0, 5);
		if(marketAssists!=null&& marketAssists.size()>0){
			for (MarketAssist marketAssist:marketAssists) {
				
				SysUser sysUser = marketAssist.getSysUser();
				if(sysUser.getUserImage()!=null){
					sysUser.setUserImage(userImage+sysUser.getUserImage());
				}
				
				List<MarketAssistImage> images = marketAssist.getMarketAssistImages();
				if(images!=null &&images.size()>0 ){}
				for (MarketAssistImage marketAssistImage : images) {
					marketAssistImage.setImageName(urlPath+marketAssistImage.getImageName());
				}
			}
		}
		Map<String, Object> res=new HashMap<String, Object>();
		int agencyNum=0;
		if(listKnowAgency!=null){
			agencyNum=listKnowAgency.size();
		}
		int baseNum=listKnowBase.size();
		res.put("agencyNum", agencyNum);
		res.put("baseNum", baseNum);
		if(agencyNum>4){
			res.put("listKnowAgency", listKnowAgency.subList(0, 4));
		}else{
			res.put("listKnowAgency", listKnowAgency);
		}
		if(baseNum>4){
			res.put("listKnowBase", listKnowBase.subList(0, 4));
		}else{
			res.put("listKnowBase", listKnowBase);	
		}
		
//		res.put("listKnowAgency", listKnowAgency);
//		res.put("listKnowBase", listKnowBase);
		res.put("marketAssists", marketAssists);
		res.put("materialField", materialList);
		res.put("knowledgeFieldList", knowledgeFieldList);
		res.put("personKnoledgeNum", personKnoledgeNum);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(res);
		return resultVo;
	
	}
	
	
	
	@RequestMapping(value = "/patent/getSelfKnowledgeForApp/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getSelfKnowledgeForApp(@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		ResultVo resuslt=new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		try {
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
			knowledgeList=patentService.getSelfKnowledgeForApp(userId,keyword);
					for(Map<String, Object> base:knowledgeList){
						if(base.get("upload_files")!=null){
							String url=(String)base.get("upload_files");
							if(url!=null&&!"".equals(url)){
								String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
								String uploadFiles=imgUrlPath+pdfName;
								base.put("pdfPath", uploadFiles);
							}
							
						}
					}
					Map<String, Object> res=new HashMap<String, Object>();
					res.put("knowledgeList", new  PageInfo(knowledgeList));
					resuslt.setErrCode(0);
					resuslt.setResultData(res);
		
		} catch (Exception e) {
			resuslt.setErrCode(1);
			resuslt.setErrMsg("查询失败！"+e.getMessage());
		}
	
		return resuslt;
	}
	
	 /**
	 * @Description 根据文献分类和名字获取科技论文
	 * @author liuhq
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/patent/PersonListApp/{page}/{pageSize}/{keyword}/{filedId}", method = RequestMethod.GET)
	public 	ResultVo  getPersonListApp(@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword,@PathVariable("filedId") Integer filedId)  {
	
		ResultVo resultVo = new ResultVo();
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
		knowledgeList = patentService.getAllPersonKnowlist(keyword,filedId);
			for(Map<String, Object> base:knowledgeList){
				if(base.get("upload_files")!=null){
					String url=(String)base.get("upload_files");
					if(url!=null&&!"".equals(url)){
						String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
						String uploadFiles=imgUrlPath+pdfName;
						base.put("pdfPath", uploadFiles);
					}
				}
			}
		
		Map<String, Object> res=new HashMap<String, Object>();		
		res.put("knowledgeList", new  PageInfo(knowledgeList));
		resultVo.setErrCode(0);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(res);
		return resultVo;
	}

}
