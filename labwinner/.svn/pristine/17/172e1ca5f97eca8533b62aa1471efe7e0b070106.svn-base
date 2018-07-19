package com.labwinner.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import com.labwinner.domain.KnowledgeClassifyPostil;
import com.labwinner.domain.KnowledgeClassifyReacRela;
import com.labwinner.domain.KnowledgeProRela;
import com.labwinner.domain.KnowledgeThumbsUp;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.SecureRank;
import com.labwinner.domain.SelfPaper;
import com.labwinner.domain.SysRole;
import com.labwinner.domain.SysUser;
import com.labwinner.service.JournalArticleService;
import com.labwinner.service.JournalUserService;
import com.labwinner.service.KnowledgeAccService;
import com.labwinner.service.KnowledgePostilService;
import com.labwinner.service.KnowledgeProRelaService;
import com.labwinner.service.KnowledgeReacRelaService;
import com.labwinner.service.KnowledgeThumbsUpService;
import com.labwinner.service.ReactionService;
import com.labwinner.service.SelfPaperService;
import com.labwinner.service.SysRoleService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.Base64Util;
import com.labwinner.util.ListUtil;
import com.labwinner.util.OtherToPdf;
import com.labwinner.util.PdfUtil;
import com.labwinner.vo.SelfPaperVo;


@RestController
public class SelfPaperController {
	
	private static Logger logger = LoggerFactory
			.getLogger(SelfPaperController.class);

	

	@Autowired
	private JournalArticleService journalArticleService;
	@Autowired
	private SelfPaperService selfPaperService;
	@Autowired
	private KnowledgeAccService knowledgeAccService;
	@Autowired
	private KnowledgeProRelaService knowledgeProRelaService;
	@Autowired
	private KnowledgeReacRelaService knowledgeReacRelaService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private  SysRoleService sysRoleService;
	@Autowired
	private KnowledgeThumbsUpService knowledgeThumbsUpService;
	@Autowired
	private KnowledgePostilService knowledgePostilService;
	@Autowired
	private ReactionService reactionService;
	
	@Autowired
	private JournalUserService journalUserService;
	
	
	@Value("${web.upload_path_pdf}")
	String filePath;

	@Value("${web.agency_pdf}")
	String imgUrlPath;
	
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
	
	@RequestMapping(value = "/selfPaper/list/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAll(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(sysUser.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		ResultVo resultVo=new ResultVo();
		List<SelfPaper> list=new ArrayList<SelfPaper>();
		if(keyword == null ||  "".equals(keyword)
				|| "undefined".equals(keyword)){
			 list = selfPaperService.getAll(sysUser.getUserId(),roleName);
		}else{
			list=selfPaperService.getByName(sysUser.getUserId(),keyword,roleName);
		}
		
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		for(SelfPaper self:list){
			KnowledgeAcc acc=new KnowledgeAcc();
			if(self.getKnowledgeAcc()!=null){
				acc.setKnowledgeAccId(self.getKnowledgeAcc().getKnowledgeAccId()==null?0:self.getKnowledgeAcc().getKnowledgeAccId());
				String uf=self.getKnowledgeAcc().getUploadFiles()==null?"":self.getKnowledgeAcc().getUploadFiles();
//				String uploadFiles="";
//				if(uf.indexOf("/pdf")>0){
//					String pdfName=uf.substring(uf.indexOf("/pdfs/")+6, uf.length());
//					uploadFiles=imgUrlPath.substring(0, imgUrlPath.indexOf("/image"))+"/"+pdfName;
//				}
				acc.setUploadFiles(uf);
			}
			self.setKnowledgeAcc(acc);
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(new  PageInfo(list));
		return resultVo;
	}

	
	@RequestMapping(value = "/selfPaper/listForApp/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getAllForApp(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(sysUser.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		int size=page*pageSize;
		ResultVo resultVo=new ResultVo();
		List<SelfPaper> list=new ArrayList<SelfPaper>();
		if(keyword == null ||  "".equals(keyword)
				|| "undefined".equals(keyword)){
			 list = selfPaperService.getAllForApp(sysUser.getUserId(),size,roleName);
		}else{
			list=selfPaperService.getByNameForApp(sysUser.getUserId(),keyword,size,roleName);
		}
		
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		
		for(SelfPaper self:list){
			String isUser="0";
			int countThumbs=0;
			int countPostil=0;
			KnowledgeAcc acc=new KnowledgeAcc();
			if(self.getKnowledgeAcc()!=null){
				acc.setKnowledgeAccId(self.getKnowledgeAcc().getKnowledgeAccId()==null?0:self.getKnowledgeAcc().getKnowledgeAccId());
				String uf=self.getKnowledgeAcc().getUploadFiles()==null?"":self.getKnowledgeAcc().getUploadFiles();
				String uploadFiles="";
				String image="";
				if(uf.indexOf("/pdf")>0){
					String pdfName=uf.substring(uf.indexOf("/pdfs/")+6, uf.length());
					uploadFiles=imgUrlPath+pdfName;
					image=uf.substring(uf.indexOf("/pdf")+6, uf.length());
				}
				acc.setUploadFiles(uploadFiles);
				acc.setImage(image);
				List<KnowledgeThumbsUp> thumbsUpList=knowledgeThumbsUpService.getAll(self.getKnowledgeAcc().getKnowledgeAccId());
				if(thumbsUpList!=null&&thumbsUpList.size()>0){
					countThumbs=thumbsUpList.size();
					for(int i=0;i<thumbsUpList.size();i++){
						if(sysUser.getUserId()==thumbsUpList.get(i).getSysUser().getUserId()){
							isUser="1";
						}
					}
					
				}
				List<KnowledgeClassifyPostil> knowledgePostil = knowledgePostilService.getById(self.getKnowledgeAcc().getKnowledgeAccId());
				if(knowledgePostil!=null&&knowledgePostil.size()>0){
					for(int i=0;i<knowledgePostil.size();i++){
						if(knowledgePostil.get(i).getPostilId()==null){
						countPostil++;
						}
					}
					}
			}
			self.setCreater(String.valueOf(countPostil));
			self.setModifier(String.valueOf(countThumbs)+":"+isUser);
			self.setKnowledgeAcc(acc);
			
		}
		if(list==null||list.size()==0){
			resultVo.setErrCode(1);
		}else{
			resultVo.setErrCode(0);
		}
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(list);
		return resultVo;
	}

	@RequestMapping(value = "/selfPaper/listForAppAll/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getForApp(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(sysUser.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		List<SelfPaper> listAll=new ArrayList<SelfPaper>();
		listAll= selfPaperService.getAll(sysUser.getUserId(),roleName);
		List<Object> userNames=new ArrayList<Object>();
		if(listAll.size()>0){
			for(SelfPaper self:listAll){
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("username", self.getSysUser().getRealname());
				map.put("userId", self.getSysUser().getUserId());
				userNames.add(map);
			}
		}
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		ResultVo resultVo=new ResultVo();
		List<SelfPaper> list=new ArrayList<SelfPaper>();
		if(keyword == null ||  "".equals(keyword)
				|| "undefined".equals(keyword)){
			 list = selfPaperService.getAll(sysUser.getUserId(),roleName);
		}else{
			list=selfPaperService.getByName(sysUser.getUserId(),keyword,roleName);
		}
	
	
		if(list.size()>0){
			for(SelfPaper self:list){
				KnowledgeAcc acc=new KnowledgeAcc();
				if(self.getKnowledgeAcc()!=null){
					acc.setKnowledgeAccId(self.getKnowledgeAcc().getKnowledgeAccId()==null?0:self.getKnowledgeAcc().getKnowledgeAccId());
					String uf=self.getKnowledgeAcc().getUploadFiles()==null?"":self.getKnowledgeAcc().getUploadFiles();
					String uploadFiles="";
					String image="";
					if(uf.indexOf("/pdf")>0){
						String pdfName=uf.substring(uf.indexOf("/pdfs/")+6, uf.length());
						uploadFiles=imgUrlPath+pdfName;
					}
					acc.setUploadFiles(uploadFiles);
					//acc.setUploadFiles(uf);
				}
				self.setKnowledgeAcc(acc);
//				Map<String, Object> map=new HashMap<String, Object>();
//				map.put("username", self.getSysUser().getRealname());
//				map.put("userId", self.getSysUser().getUserId());
//				userNames.add(map);
			}
		}
		ListUtil lu=new ListUtil();
		resultVo.setErrCode(0);
		resultVo.setResultData(new  PageInfo(list));
		resultVo.setErrMsg("查询成功！");
		if(userNames.size()>0){
			resultVo.setResultList(lu.removeDuplicate(userNames));
		}else{
			resultVo.setResultData(userNames);
		}
		return resultVo;
	}

	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/selfPaper/getById/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		
		ResultVo res=new ResultVo();
		try {
			SelfPaperVo selfPaperVo=new SelfPaperVo();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(sysUser.getSysRole().getRoleId())));
			 List<SelfPaper> allList= selfPaperService.getAll(sysUser.getUserId(),sysRole.getRoleName());
			 List<Integer> idList=new ArrayList<Integer>();
			 if(allList!=null&&allList.size()>0){
				 for(SelfPaper selfPaper:allList){
					 idList.add(selfPaper.getSelfPaperId());
				 }
			 }
			 if(idList.contains(id)){
				 SelfPaper selfPaper = selfPaperService.getById(id);
					SysUser ss=getSysUser();
					//SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
					String roleName=sysRole.getRoleName();
					String isEditOrNot="";
					if(ss!=null&&selfPaper.getSysUser()!=null){
						if(ss.getUserId()-selfPaper.getSysUser().getUserId()==0){
							isEditOrNot="true";
						}else{
							isEditOrNot="false";
						}
					}
					KnowledgeAcc  knowledgeAcc=new KnowledgeAcc();
					if(selfPaper.getKnowledgeAcc()!=null){
						knowledgeAcc=knowledgeAccService.getById(selfPaper.getKnowledgeAcc().getKnowledgeAccId());
					}
					List<JournalUser> listUser=journalUserService.getById(id,2);
					String scureRankIds="";
					List<Map<String, Object>> userMap=new ArrayList<Map<String,Object>>();
					if(listUser!=null){
						for(int i=0;i<listUser.size();i++){
							Map<String, Object> map=new HashMap<String, Object>();
							if(listUser.get(i).getSysUser()!=null){
								map.put("id", listUser.get(i).getSysUser().getUserId());
								map.put("name", listUser.get(i).getSysUser().getRealname());
								if(i>0){
									scureRankIds=scureRankIds+","+listUser.get(i).getSysUser().getRealname();
									}
									else{
										scureRankIds=listUser.get(i).getSysUser().getRealname()+"";
									}
							}
							userMap.add(map);
						}
					}
					
					selfPaper.setKnowledgeAcc(knowledgeAcc);
					Map<String, Object> mapPro=new HashMap<String, Object>();
					mapPro.put("journalActicleId", id);
					mapPro.put("knowledgeClassifyId", 2);
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
						knowledgeAcc.setImage(knowledgeAcc.getUploadFiles());
						pdfName=knowledgeAcc.getUploadFiles().substring(knowledgeAcc.getUploadFiles().lastIndexOf("/")+1);
						knowledgeAcc.setUploadFiles(imgUrlPath+pdfName);
						
					}
					selfPaperVo.setPdfName(pdfName);
					selfPaperVo.setSelfPaper(selfPaper);
					selfPaperVo.setKnowledgeProRela(listPro);
					selfPaperVo.setKnowledgeClassifyReac(listReac);
					selfPaperVo.setIsEditOrNot(isEditOrNot);
					selfPaperVo.setScureRankIds(scureRankIds);
					selfPaperVo.setUserList(userMap);
					res.setErrCode(0);
					res.setErrMsg("find success");
					res.setResultData(selfPaperVo);
			 }else{
					res.setErrCode(4);
					res.setErrMsg("无权限操作！");
					res.setResultData(selfPaperVo);
			 }
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg(e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "/selfPaper/{name}", method = RequestMethod.GET)
	public List<SelfPaper> getByName(@PathVariable("name") String name) {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(sysUser.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		//主表实体类
		List<SelfPaper> selfPapers = selfPaperService.getByName(sysUser.getUserId(),name,roleName);
		if (selfPapers == null) {
			String message = "对象不存在(inventoryName:" + name + ")";
			logger.warn(message);
		}
		return selfPapers;
	}
	
	@RequestMapping(value = "/selfPaper/getByUserName/{page}/{pageSize}/{id}", method = RequestMethod.GET)
	public ResultVo getByUserName(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("id") int id) {
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(sysUser.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		ResultVo resultVo=new ResultVo();
		try {
			//主表实体类
			List<SelfPaper> selfPapers = new ArrayList<SelfPaper>();
			selfPapers=selfPaperService.getByUserId(sysUser.getUserId(),id,roleName);
			if(selfPapers.size()>0){
				for(SelfPaper self:selfPapers){
					KnowledgeAcc acc=new KnowledgeAcc();
					if(self.getKnowledgeAcc()!=null){
						acc.setKnowledgeAccId(self.getKnowledgeAcc().getKnowledgeAccId()==null?0:self.getKnowledgeAcc().getKnowledgeAccId());
						String uf=self.getKnowledgeAcc().getUploadFiles()==null?"":self.getKnowledgeAcc().getUploadFiles();
						String uploadFiles="";
						String image="";
						if(uf.indexOf("/pdf")>0){
							String pdfName=uf.substring(uf.indexOf("/pdfs/")+6, uf.length());
							uploadFiles=imgUrlPath+pdfName;
						}
						acc.setUploadFiles(uploadFiles);
						//acc.setUploadFiles(uf);
					}
					self.setKnowledgeAcc(acc);
//					Map<String, Object> map=new HashMap<String, Object>();
//					map.put("username", self.getSysUser().getRealname());
//					map.put("userId", self.getSysUser().getUserId());
//					userNames.add(map);
				}
			}
			resultVo.setErrCode(0);
			resultVo.setResultData(new  PageInfo(selfPapers));
		} catch (Exception e) {
			resultVo.setErrCode(1);
		}
		return resultVo;
	}
	

	/**
	 * @Description 保存/更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @throws Exception 
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/selfPaper/add", method = RequestMethod.POST)
	public ResultVo saveOrUpdate(@RequestBody SelfPaperVo selfPaperVo) throws Exception {
		ResultVo res=new ResultVo();
		try {
			//TODO 判断更新，增加
			Integer id = selfPaperVo.getSelfPaper().getSelfPaperId();
			//selfPaper selfPaper=selfPaperService.getById(id);
			String pdfName=selfPaperVo.getPdfName();
			SelfPaper selfPaper=selfPaperVo.getSelfPaper();
			List<String> pdfStrs=selfPaperVo.getPdfStrs();
			if(id==null){
				selfPaperService.save(selfPaper);
			}
			Integer secure=1;
			if(selfPaperVo.getScureRankIds()!=null&&!"".equals(selfPaperVo.getScureRankIds())&&!"null".equals(selfPaperVo.getScureRankIds())){//保存保密权限
				JournalUser journalUser=new JournalUser();
				String[] ss=selfPaperVo.getScureRankIds().split(",");
					for(int i=0;i<ss.length;i++){
					int ScureRankId=Integer.parseInt(ss[i]);
					SysUser user=new SysUser();
					user.setUserId(ScureRankId);
					journalUser.setSysUser(user);
					journalUser.setJournalArticleId(selfPaper.getSelfPaperId());
					KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
					knowledgeClassify.setKnowledgeClassifyId(2);
					journalUser.setKnowledgeClassify(knowledgeClassify);
						journalUserService.save(journalUser);
					}
					secure=2;
				}
			SecureRank secureRank=new SecureRank();
			secureRank.setSecureRankId(secure);
			selfPaper.setSecureRank(secureRank);
			id=selfPaper.getSelfPaperId();
			KnowledgeAcc knowledgeAcc=new KnowledgeAcc();
			Base64Util base64Util = new Base64Util();
			if(pdfStrs!=null&&pdfStrs.size()>0){//保存pdf文件
				knowledgeAcc=savePdfStr(knowledgeAcc,id,pdfStrs,pdfName,2,filePath);
			}
			selfPaper.setKnowledgeAcc(knowledgeAcc);
			//Magazine magazine=new Magazine();
			SysUser sysUser=getSysUser();
			selfPaper.setSysUser(sysUser);
			selfPaper.setCreater(sysUser.getRealname());
			selfPaper.setCreateDate(new Date());
			if(id!=null){
				selfPaperService.update(selfPaper);
			}
			List<KnowledgeProRela> knowledgeProRelaList=selfPaperVo.getKnowledgeProRela();//知识项目
			List<KnowledgeClassifyReacRela> knowledgeClassifyReacRelaList=selfPaperVo.getKnowledgeClassifyReac();//知识实验
			if(knowledgeProRelaList!=null&&knowledgeProRelaList.size()>0){//遍历保存关联项目
				for(int i=0;i<knowledgeProRelaList.size();i++){
					KnowledgeProRela knowledgeProRela=new KnowledgeProRela();
					knowledgeProRela.setMappingKnowledgeIdl(selfPaper.getSelfPaperId());
					ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();//项目基本信息
					projectBasicInfo.setProId(knowledgeProRelaList.get(i).getMappingKnowledgeIdl());
					knowledgeProRela.setProjectBasicInfo(projectBasicInfo);
					KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
					knowledgeClassify.setKnowledgeClassifyId(2);
					knowledgeProRela.setKnowledgeClassify(knowledgeClassify);
					knowledgeProRela.setCreater(selfPaper.getSysUser());
					knowledgeProRela.setCreateDate(new Date());
					knowledgeProRelaService.save(knowledgeProRela);//保存关联项目表
				}
			}
			if(knowledgeClassifyReacRelaList!=null&&knowledgeClassifyReacRelaList.size()>0){//遍历保存关联实验
				for(int i=0;i<knowledgeClassifyReacRelaList.size();i++){
					KnowledgeClassifyReacRela knowledgeClassifyReacRela=new KnowledgeClassifyReacRela();
					knowledgeClassifyReacRela.setMappingKnowledgeIdl(selfPaper.getSelfPaperId());
					Reaction relation=new Reaction();  //实验基本信息
					relation.setReactionId(knowledgeClassifyReacRelaList.get(i).getKnowledgeClassifyReacRelaId());
					knowledgeClassifyReacRela.setReaction(relation);
					knowledgeClassifyReacRela.setKnowledgeClassifyId(2);
					knowledgeClassifyReacRela.setCreater(String.valueOf(selfPaper.getSysUser().getId()));
					knowledgeClassifyReacRela.setCreateDate(new Date());
					knowledgeReacRelaService.save(knowledgeClassifyReacRela);//保存关联实验
				}
			}
			res.setErrCode(0);
			res.setErrMsg("保存成功！");
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("保存失败:"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	
	
	/**
	 * @Description 保存保密人员对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/selfPaper/addJournalUser", method = RequestMethod.PUT)
	public void saveJournalUser(@RequestParam("scureRankIds") String   scureRankIds,@RequestParam("selfPaperId") Integer   selfPaperId) {
		try {
			
			journalUserService.delete(selfPaperId,2);
			SelfPaper selfPaper=selfPaperService.getById(selfPaperId);
			if(!"".equals(scureRankIds)){
				String[] ss=scureRankIds.split(",");
				for(int i=0;i<ss.length;i++){
					JournalUser journalUser=new JournalUser();
				int ScureRankId=Integer.parseInt(ss[i]);
				KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
				knowledgeClassify.setKnowledgeClassifyId(2);
				journalUser.setKnowledgeClassify(knowledgeClassify);
				SysUser user=new SysUser();
				user.setUserId(ScureRankId);
				journalUser.setSysUser(user);
				journalUser.setJournalArticleId(selfPaperId);
					journalUserService.save(journalUser);
				}
				SecureRank secureRank=new SecureRank();
				secureRank.setSecureRankId(2);
				selfPaper.setSecureRank(secureRank);
			}else{
				SecureRank secureRank=new SecureRank();
				secureRank.setSecureRankId(1);
				selfPaper.setSecureRank(secureRank);
			}
			selfPaperService.update(selfPaper);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/selfPaper/addSelf", method = RequestMethod.PUT)
	public ResultVo saveJournaleArtilce(@RequestBody SelfPaper selfPaper) {
		ResultVo res=new ResultVo();;
		try {
			//TODO 判断更新，增加
			Integer id = selfPaper.getSelfPaperId();
			SelfPaper selfRes=selfPaperService.getById(id);
			selfRes.setAbstract_(selfPaper.getAbstract_());
			selfRes.setKeyWords(selfPaper.getKeyWords());
			selfRes.setPublishTime(selfPaper.getPublishTime());
			selfRes.setTitle(selfPaper.getTitle());
			SysUser sysUser=getSysUser();
			selfRes.setModifier(sysUser.getRealname());
			selfRes.setModifyDate(new Date());
			if(id==null){
				selfPaperService.save(selfRes);
			}else{
				selfPaperService.update(selfRes);
			}
			res.setErrCode(0);
			res.setErrMsg("保存成功");
		} catch (Exception e) {
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			res.setErrCode(1);
			res.setErrMsg("保存失败："+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	/**
	 * @Description 保存关联实验对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/selfPaper/addKnowledgeReacRela", method = RequestMethod.PUT)
	public ResultVo saveKnowledgeReacRela(@RequestBody List<KnowledgeClassifyReacRela>  listReacRelaUpdate,@PathVariable("selfPaperId") Integer selfPaperId) {
		ResultVo res= new ResultVo();
		try {
			SysUser ss=getSysUser();
			SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
			String roleName=sysRole.getRoleName();
			Map<String, Object> mapPro=new HashMap<String, Object>();
			mapPro.put("journalActicleId", selfPaperId);
			mapPro.put("knowledgeClassifyId", 2);
			mapPro.put("roleName",roleName);
			mapPro.put("userId",ss.getUserId());
			//List<KnowledgeClassifyReacRela> listPro=knowledgeReacRelaService.getAllByKnowladge(mapPro);
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
			for(int i=0;i<listReac.size();i++){
				knowledgeReacRelaService.delete(listReac.get(i).getKnowledgeClassifyReacRelaId());
			}
			for(int i=0;i<listReacRelaUpdate.size();i++){
				KnowledgeClassifyReacRela knowledgeReacRela=listReacRelaUpdate.get(i);
				knowledgeReacRela.setKnowledgeClassifyId(2);
				knowledgeReacRelaService.save(knowledgeReacRela);
			}
			res.setErrCode(0);
			res.setErrMsg("保存数据成功！");
		} catch (Exception e) {
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			res.setErrCode(1);
			res.setErrMsg("保存数据失败："+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	
	/**
	 * @Description 保存关联项目对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/selfPaper/addKnowledgePro", method = RequestMethod.PUT)
	public ResultVo saveKnowledgePro(@RequestBody List<KnowledgeProRela>  listProUpdate,@PathVariable("selfPaperId") Integer selfPaperId) {
		ResultVo res= new ResultVo();
		try {
			SysUser ss=getSysUser();
			SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
			String roleName=sysRole.getRoleName();
			Map<String, Object> mapPro=new HashMap<String, Object>();
			mapPro.put("journalActicleId", selfPaperId);
			mapPro.put("knowledgeClassifyId", 2);
			mapPro.put("roleName",roleName);
			mapPro.put("userId",ss.getUserId());
			List<KnowledgeProRela> listPro=knowledgeProRelaService.getAllByKnowladge(mapPro);
			for(int i=0;i<listPro.size();i++){
				knowledgeProRelaService.delete(listPro.get(i).getKnowledgeProRelaId());
			}
			for(int i=0;i<listProUpdate.size();i++){
				KnowledgeProRela knowledgeProRela=listProUpdate.get(i);
				KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
				knowledgeClassify.setKnowledgeClassifyId(2);
				knowledgeProRela.setKnowledgeClassify(knowledgeClassify);
				knowledgeProRelaService.save(knowledgeProRela);
			}
			res.setErrCode(0);
			res.setErrMsg("保存数据成功！");
		} catch (Exception e) {
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			res.setErrCode(1);
			res.setErrMsg("保存数据失败："+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	
	
	/**
	 * @Description 保存附件对象
	 * @author liuhq
	 * @version V1.0
	 * @throws Exception 
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/selfPaper/addKnowAcc", method = RequestMethod.PUT)
	public ResultVo saveKnowAcc(@RequestBody SelfPaperVo selfPaperVo) throws Exception {
		ResultVo res= new ResultVo();
		try {
			Integer selfPaperId=selfPaperVo.getSelfPaper().getKnowledgeAcc().getKnowledgeAccId();
			KnowledgeAcc knowledgeAcc=knowledgeAccService.getById(selfPaperId);
			Base64Util base64Util = new Base64Util();
			knowledgeAcc=savePdfStr(knowledgeAcc,selfPaperVo.getSelfPaper().getSelfPaperId(),selfPaperVo.getPdfStrs(),selfPaperVo.getPdfName(),2,filePath);
			SelfPaper selfPaper=selfPaperService.getById(selfPaperVo.getSelfPaper().getSelfPaperId());
			selfPaper.setKnowledgeAcc(knowledgeAcc);
			selfPaperService.update(selfPaper);
			res.setErrCode(0);
			res.setErrMsg("保存数据成功！");
			res.setResultData(knowledgeAcc);
		} catch (Exception e) {
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			res.setErrCode(1);
			res.setErrMsg("保存数据失败："+e.getMessage());
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
				knowledgeClassify.setKnowledgeClassifyId(2);
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
					}
					
				}
				knowledgeAcc.setUploadFiles(imgUrlPath+pdfName);
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

//	@RequestMapping(value = "/self/pdfToImage", method = RequestMethod.POST)
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

	@RequestMapping(value = "/selfPaper/del/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		try {
			selfPaperService.delete(id);
			journalArticleService.deletePro(2,id);
			journalArticleService.deleteReac(2,id);
			res.setErrCode(0);
			res.setErrMsg("删除成功！");
		} catch (Exception e) {
			res.setErrCode(0);
			res.setErrMsg("删除失败："+e.getMessage());
			throw e;
		}
		return res;
	}


}
