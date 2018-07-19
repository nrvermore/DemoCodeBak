package com.labwinner.controller;

import io.netty.util.ByteProcessor.IndexOfProcessor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
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
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.JournalArticle;
import com.labwinner.domain.JournalUser;
import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.KnowledgeClassify;
import com.labwinner.domain.KnowledgeClassifyPostil;
import com.labwinner.domain.KnowledgeClassifyReacRela;
import com.labwinner.domain.KnowledgeField;
import com.labwinner.domain.KnowledgeProRela;
import com.labwinner.domain.KnowledgeThumbsUp;
import com.labwinner.domain.Magazine;
import com.labwinner.domain.MarketAssist;
import com.labwinner.domain.MarketAssistImage;
import com.labwinner.domain.MaterialField;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.SecureRank;
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
import com.labwinner.service.ReactionService;
import com.labwinner.service.SysRoleService;
import com.labwinner.service.SysUserService;
import com.labwinner.service.pdfToImage;
import com.labwinner.util.Base64Util;
import com.labwinner.util.OtherToPdf;
import com.labwinner.util.PdfUtil;
import com.labwinner.vo.JournalArticleVo;

@RestController
public class JournalArticleController {
	
	private static Logger logger = LoggerFactory
			.getLogger(JournalArticleController.class);

	@Autowired
	private JournalArticleService journalArticleService;
	@Autowired
	private JournalUserService journalUserService;
	
	@Autowired
	private KnowledgeAccService knowledgeAccService;
	@Autowired
	private KnowledgeProRelaService knowledgeProRelaService;
	@Autowired
	private KnowledgeReacRelaService knowledgeReacRelaService;
	@Autowired
	private MagazineService magazineService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private  SysRoleService sysRoleService;
	@Autowired
	private KnowledgePostilService knowledgePostilService;
	@Autowired
	private ReactionService reactionService;

	@Autowired
	private MaterialFieldService materialFieldService;
	@Autowired
	private KnowledgeThumbsUpService knowledgeThumbsUpService;
	@Autowired HttpServletRequest request;
	
	@Autowired
	private MarketAssistService marketAssistService;
	
	@Autowired
	private KnowledgeFieldService knowledgeFieldService;
	
	@Value("${web.upload_path_pdf}")
	String filePath;
	@Value("${web.agency_pdf}")
	String pdfAgencyPath;
	@Value("${web.url_path_pdf}")
	String imgUrlPath;
	@Value("${sysUserPhone.url-path}")
	String userImage;
	
	@Value("${marketAssist.url-path}")
	private String urlPath;
	
	@Value("${web.url_path_fieldImage}")
	private String fieldImage;
	
	
	@Value("${pdfView.url-path}")
	private String pdfViewUrl;
	
	@Value("${pdfView.upload-path}")
	private String pdfViewUpload;


	
	private Boolean flag=false;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/journalArticle/list/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public 	ResultVo  getAll(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword)  {
	
		ResultVo resultVo = new ResultVo();
		SysUser ss=getSysUser();
		int angency=ss.getSysSigningAgency().getAgencyId();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		List<JournalArticle> list=new ArrayList<JournalArticle>();
		if(keyword == null ||  "".equals(keyword)
				|| "undefined".equals(keyword)){
			 list = journalArticleService.getAll(ss.getUserId(),roleName,angency);
		}else{
			list = journalArticleService.getByName(ss.getUserId(),keyword,roleName,angency);
		}
				for(JournalArticle jour:list){
					KnowledgeAcc acc=new KnowledgeAcc();
					if(jour.getKnowledgeAcc()!=null){
						acc.setKnowledgeAccId(jour.getKnowledgeAcc().getKnowledgeAccId()==null?0:jour.getKnowledgeAcc().getKnowledgeAccId());
						String uploadFiles=jour.getKnowledgeAcc().getUploadFiles()==null?"":jour.getKnowledgeAcc().getUploadFiles();
//						String uploadFiles="";
//						if(uf.indexOf("/pdf")>0){
//							uploadFiles=uf.substring(uf.indexOf("/pdf")+6, uf.length());
//						}
						acc.setUploadFiles(uploadFiles);
						acc.setPdfCount(jour.getKnowledgeAcc().getPdfCount());
					}
					jour.setKnowledgeAcc(acc);
				}
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(new PageInfo(list));
		return resultVo;
	}

	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/journalArticle/list/ToPdf", method = RequestMethod.GET)
	public 	ResultVo  ToPdf(HttpServletRequest request, HttpServletResponse response)  {
		ResultVo res=new ResultVo();
		 JSONObject json = new JSONObject();
		 json.put("async", false);
		 json.put("filetype", "docx");
		 json.put("key", "b87f70fb-zsxcrrtccvbn");
		 json.put("outputtype", "pdf");
		 json.put("title", "aaa");
		 json.put("url", "http://staticfile.labwinner.com/LabWinner/pdfs/b87f70fb-9998-4c6a-8a25-e313fcd3b319登录和下载.docx");
		 OtherToPdf otp=new OtherToPdf();
		// JSONObject result=otp.doPost("",json,request);
		 String result=otp.sendPost("http://47.98.173.123:8005/ConvertService.ashx",json.toString());
		 String pdfUrl=null;
		   if(result.indexOf("<FileUrl>")>0){
			   pdfUrl=result.substring(result.indexOf("<FileUrl>")+10,result.indexOf("</FileUrl>"));
           }
		 System.out.println(result);
		 return res;
	}
	
	/**
	 * @Description 根据文献分类和名字获取科技论文
	 * @author liuhq
	 * @version V1.0
	 * @throws IOException 
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/journalArticle/fieldList", method = RequestMethod.GET)
	public 	ResultVo  getAllFieldList()  {
	
		ResultVo resultVo = new ResultVo();
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		int angency=ss.getSysSigningAgency().getAgencyId();
		int personKnoledgeNum=journalArticleService.getPersonKnoledgeNum(ss.getUserId());
		List<MaterialField> list1 = materialFieldService.getAllFirst();
		List<KnowledgeField> knowledgeFieldList = knowledgeFieldService.getAll();
		if(knowledgeFieldList!=null&&knowledgeFieldList.size()>0){
			for(KnowledgeField knowledgeField:knowledgeFieldList){
				if(knowledgeField.getDefaultImage()!=null&&knowledgeField.getDefaultImage().getUrl()!=null){
					knowledgeField.getDefaultImage().setUrl(fieldImage+knowledgeField.getDefaultImage().getUrl());
				}
				int count=knowledgeFieldService.getCountByKnowledgeField(knowledgeField.getKnowledgeFieldId());
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
		List<JournalArticle> personList=new ArrayList<JournalArticle>();
		personList=journalArticleService.getAgencyJournalArticle(ss.getUserId(),roleName,angency);
		if(personList!=null&&personList.size()>0){
			for(JournalArticle person:personList){
				if(person.getSysUser().getUserImage()!=null&&!"".equals(person.getSysUser().getUserImage())){
					String url=person.getSysUser().getUserImage();
					String path=userImage+url;
					person.getSysUser().setUserImage(path);
				}
			}
		}
		List<JournalArticle> baseUpList=new ArrayList<JournalArticle>();
		baseUpList=journalArticleService.getBaseJournalArticle(ss.getUserId(),roleName,angency);
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
	
	@RequestMapping(value = "/journalArticle/getListByFieldId/{keyword}/{filedId}", method = RequestMethod.GET)
	public 	ResultVo  getAllFieldListForApp(@PathVariable("keyword") String keyword,@PathVariable("filedId") Integer filedId)  {
	
		ResultVo resultVo = new ResultVo();
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		List<MaterialField> materialFieldList=new ArrayList<MaterialField>();
		List<Object> resList=new ArrayList<Object>();
		List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
		List<KnowledgeField> knowledgeFieldList = knowledgeFieldService.getAll();
		if(filedId-0==0){
			materialFieldList = materialFieldService.getAllFirst();
			resList.add(materialFieldList);
			knowledgeList = journalArticleService.getAllKnowlist(ss.getUserId(),roleName,keyword);
			for(Map<String, Object> base:knowledgeList){
				if(base.get("upload_files")!=null){
					String url=(String)base.get("upload_files");
					String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
					String uploadFiles="";
					if((Integer)base.get("source")-0!=0){
						uploadFiles=pdfAgencyPath+pdfName;
					}else{
						uploadFiles=imgUrlPath+pdfName;
					}
					base.put("pdfPath", uploadFiles);
				}
			}
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
			knowledgeList = journalArticleService.getAllBaseKnowByFiledId(ss.getUserId(),roleName,keyword,filedId);
			for(Map<String, Object> base:knowledgeList){
				if(base.get("upload_files")!=null){
					String url=(String)base.get("upload_files");
					String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
					String uploadFiles="";
					if((Integer)base.get("source")-0!=0){
						uploadFiles=pdfAgencyPath+pdfName;
					}else{
						uploadFiles=imgUrlPath+pdfName;
					}
					base.put("pdfPath", uploadFiles);
				}
			}
		}
		Map<String, Object> res=new HashMap<String, Object>();		
		res.put("knowledgeList", knowledgeList);
		res.put("materialField", resList);
		res.put("knowledgeFieldList", knowledgeFieldList);
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
	
	@RequestMapping(value = "/journalArticle/getBaseListByFieldId/{keyword}/{filedId}", method = RequestMethod.GET)
	public 	ResultVo  getBaseListByFieldId(@PathVariable("keyword") String keyword,@PathVariable("filedId") Integer filedId)  {
	
		ResultVo resultVo = new ResultVo();
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
//		List<MaterialField> materialFieldList=new ArrayList<MaterialField>();
//		List<Object> resList=new ArrayList<Object>();
//		
//			materialFieldList=materialFieldService.getAllSamePid(filedId);
//			if(materialFieldList!=null&&materialFieldList.size()>0){
//				for(int i=0;i<materialFieldList.size();i++){
//					MaterialField materialField = materialFieldService.getTree(materialFieldList.get(i).getCid());
//					if(materialField!=null){
//						resList.add(materialField);
//					}
//				}
//			}
			if(filedId-0==0){
				knowledgeList = journalArticleService.getAllBase(ss.getUserId(),roleName,keyword);
			}else{
				knowledgeList = journalArticleService.getAllBaseByFiledId(ss.getUserId(),roleName,keyword,filedId);
			}
			Boolean baseKey=false;
		if(knowledgeList!=null&&knowledgeList.size()>7){
			baseKey=true;
			knowledgeList=knowledgeList.subList(0, 7);
		}
		Map<String, Object> res=new HashMap<String, Object>();		
		res.put("knowledgeList", knowledgeList);
		//res.put("materialField", resList);
		res.put("baseKey", baseKey);
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
	
	@RequestMapping(value = "/journalArticle/getPersonListByFieldId/{page}/{pageSize}/{keyword}/{filedId}", method = RequestMethod.GET)
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
				knowledgeList=journalArticleService.getAllPerson(ss.getUserId(), roleName, ss.getSysSigningAgency().getAgencyId(), keyword);
			}else{
				knowledgeList=journalArticleService.getAllPersonByFiledId(ss.getUserId(), roleName, ss.getSysSigningAgency().getAgencyId(), keyword, filedId);
			}
		Map<String, Object> res=new HashMap<String, Object>();		
		res.put("knowledgeList", new PageInfo(knowledgeList));
		res.put("knowledgeFieldList", knowledgeFieldList);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(res);
		return resultVo;
	}
	
	
	@RequestMapping(value = "/journalArticle/fieldListApp", method = RequestMethod.GET)
	public 	ResultVo  getAllFieldListForApp()  {
	
		ResultVo resultVo = new ResultVo();
//		List<Object> res=new ArrayList<Object>();
	//	Map<String, Object> reaMap=new HashMap<String, Object>();
		Map<String, Object> res=new HashMap<String, Object>();
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		int angency=ss.getSysSigningAgency().getAgencyId();
		int personKnoledgeNum=journalArticleService.getPersonKnoledgeNum(ss.getUserId());
		List<MaterialField> list1 = materialFieldService.getAllFirst();
		List<Map<String, Object>> materialList=new ArrayList<Map<String, Object>>();
		if(list1!=null&&list1.size()>0){
			for(int i=0;i<list1.size();i++){
				Map<String, Object> map=new HashMap<String, Object>();
				int count =journalArticleService.getMaterialNum(list1.get(i).getCid());
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
				int count=knowledgeFieldService.getCountByKnowledgeField(knowledgeField.getKnowledgeFieldId());
				knowledgeField.setKnowledgeNum(count);
			}
		}
		List<Map<String, Object>> listKnowAgency=new ArrayList<Map<String,Object>>();
		listKnowAgency=journalArticleService.getKnowledgeFirst(roleName,ss.getUserId());
		int agencyNum=0;
		if(listKnowAgency!=null){
			agencyNum=listKnowAgency.size();
		}
		if(agencyNum>4){
			listKnowAgency=listKnowAgency.subList(0, 4);
		}
		if(listKnowAgency!=null&&listKnowAgency.size()>0){
			for(int i=0;i<listKnowAgency.size();i++){
				if(listKnowAgency.get(i).get("url")!=null){
					listKnowAgency.get(i).put("url", userImage+listKnowAgency.get(i).get("url"));
				}
				if(listKnowAgency.get(i).get("upload_files")!=null){
						String url=listKnowAgency.get(i).get("upload_files").toString();
						String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
						listKnowAgency.get(i).put("upload_files", pdfAgencyPath+pdfName);
				}
			}
		}
		
		List<Map<String, Object>> listKnowBase=new ArrayList<Map<String,Object>>();
		listKnowBase=journalArticleService.getKnowledgeFirstBase();
		int baseNum=0;
		if(listKnowBase!=null){
			baseNum=listKnowBase.size();
		}
		if(baseNum>4){
			listKnowBase=listKnowBase.subList(0, 4);
		}
		if(listKnowBase!=null&&listKnowBase.size()>0){
			for(int i=0;i<listKnowBase.size();i++){
				if(listKnowBase.get(i).get("url")!=null){
					listKnowBase.get(i).put("url", userImage+listKnowBase.get(i).get("url"));
				}
				if(listKnowBase.get(i).get("upload_files")!=null){
						String url=listKnowBase.get(i).get("upload_files").toString();
						String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
						listKnowBase.get(i).put("upload_files", imgUrlPath+pdfName);
				}
			}
		}
		
		List<MarketAssist> marketAssists = marketAssistService.getByKeywordId(10, 0, 5);
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
		
	

		res.put("agencyNum", agencyNum);
		res.put("baseNum", baseNum);
		res.put("listKnowAgency", listKnowAgency);
		res.put("listKnowBase", listKnowBase);	
		res.put("marketAssists", marketAssists);
		res.put("materialField", materialList);
		res.put("knowledgeFieldList", knowledgeFieldList);
		res.put("personKnoledgeNum", personKnoledgeNum);
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(res);
		return resultVo;
	
	}
	
	@RequestMapping(value = "/journalArticle/getBaseListByFieldId/{page}/{pageSize}/{keyword}/{filedId}", method = RequestMethod.GET)
	public 	ResultVo  getBaseListByFieldId(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword,
			@PathVariable("filedId") Integer filedId)  {
		ResultVo resultVo = new ResultVo();
		SysUser ss=getSysUser();
		List<MaterialField> list1 = materialFieldService.getAllFirst();
		List<MaterialField> resList=new ArrayList<MaterialField>();
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
				list = journalArticleService.getAllBase(ss.getUserId(),"",keyword);
		}else{
				list = journalArticleService.getAllBaseByFiledId(ss.getUserId(),"",keyword,filedId);
		}
		Map<String, Object> res=new HashMap<String, Object>();
		res.put("knowledgeList", new PageInfo(list));
		res.put("materialFieldList", resList);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(res);
		return resultVo;
	}
	
	

	@RequestMapping(value = "/journalArticle/listForApp/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public 	ResultVo  getAllForApps(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword)   {
	
		ResultVo resultVo = new ResultVo();
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		String roleName=sysRole.getRoleName();
		Integer size = page * pageSize;
		List<JournalArticle> list=new ArrayList<JournalArticle>();
		if(keyword == null ||  "".equals(keyword)
				|| "undefined".equals(keyword)){
			list = journalArticleService.getAllForApp(ss.getUserId(),size,roleName);
		}else{
			list = journalArticleService.getByNameForApp(ss.getUserId(),keyword,size,roleName);
		}
				for(JournalArticle jour:list){
					int countPostil=0;
					int countThumbs=0;
					String abstract_=jour.getAbstract_();
					abstract_=abstract_.replace("\r\r\r\r\r\r\r\r\r\r\n", "");
					jour.setAbstract_(abstract_);
					KnowledgeAcc acc=new KnowledgeAcc();
					String isUser="0";
					List<KnowledgeClassifyPostil> listCount=new ArrayList<KnowledgeClassifyPostil>();
					if(jour.getKnowledgeAcc()!=null){
						acc.setKnowledgeAccId(jour.getKnowledgeAcc().getKnowledgeAccId()==null?0:jour.getKnowledgeAcc().getKnowledgeAccId());
						String uf=jour.getKnowledgeAcc().getUploadFiles()==null?"":jour.getKnowledgeAcc().getUploadFiles();
						String uploadFiles="";
						String image="";
						if(uf.indexOf("/pdf")>0){
							String pdfName=uf.substring(uf.indexOf("/pdfs/")+6, uf.length());
							if(jour.getSource()-0!=0){
								uploadFiles=pdfAgencyPath+pdfName;
							}else{
								uploadFiles=imgUrlPath+pdfName;
							}
							
							image=uf.substring(uf.indexOf("/pdf")+6, uf.length());
						}
						acc.setUploadFiles(uploadFiles);
						acc.setImage(image);
						List<KnowledgeThumbsUp> thumbsUpList=knowledgeThumbsUpService.getAll(jour.getKnowledgeAcc().getKnowledgeAccId());
						
						if(thumbsUpList!=null&&thumbsUpList.size()>0){
							countThumbs=thumbsUpList.size();
							for(int i=0;i<thumbsUpList.size();i++){
								if(ss.getUserId()==thumbsUpList.get(i).getSysUser().getUserId()){
									isUser="1";
								}
							}
							
						}
						List<KnowledgeClassifyPostil> knowledgePostil = knowledgePostilService.getById(jour.getKnowledgeAcc().getKnowledgeAccId());
						
						
						if(knowledgePostil!=null&&knowledgePostil.size()>0){
							for(int i=0;i<knowledgePostil.size();i++){
								if(knowledgePostil.get(i).getPostilId()==null){
									//countPostil++;
									listCount.add(knowledgePostil.get(i));
								}
							}
							}
					}
					jour.setPdfCode(String.valueOf(listCount.size()));
					jour.setVolume(String.valueOf(countThumbs));
					jour.setIssue(isUser);
					jour.setKnowledgeAcc(acc);
				}
		if(list==null||list.size()==0){
			resultVo.setErrCode(1);
		}else{
			resultVo.setErrCode(0);
		}
//		Map<String, Object> resMap=new HashMap<String, Object>();
//		resMap.put("list", list);
		//resMap.put(key, value);
		resultVo.setErrMsg("查询成功！");
		resultVo.setResultData(list);
		return resultVo;
	}
	
	/**
	 * @Description 根据主键获取对象
	 * @author liuhq
	 * @version V1.0
	 * @throws UnsupportedEncodingException 
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/journalArticle/getById/{id}", method = RequestMethod.GET)
	public JournalArticleVo getById(@PathVariable("id") Integer id) throws UnsupportedEncodingException  {
		SysUser ss=getSysUser();
		SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
		List<Integer> listIds = journalArticleService.getAllIds(ss.getUserId(),sysRole.getRoleName(),ss.getSysSigningAgency().getAgencyId());
		Integer result=0;
		JournalArticleVo journalArticleVo=new JournalArticleVo();
		List<KnowledgeField> knowledgeFields=new ArrayList<KnowledgeField>();
		if(listIds.contains(id)){
			result=1;
			JournalArticle journalArticle = journalArticleService.getById(id);
			if(journalArticle.getMaterialField()!=null&&journalArticle.getMaterialField().getCid()!=null){
				int fieldId=journalArticle.getMaterialField().getCid();
				String field=getAllPname(fieldId);
				//field=field+">"+journalArticle.getMaterialField().getLabel();
				journalArticle.getMaterialField().setParentName(field);
			}else{
				journalArticle.setMaterialField(new MaterialField());
			}
			
			if(journalArticle.getKnowledgeField()!=null&&!"".equals(journalArticle.getKnowledgeField())){
				String[] fileds=journalArticle.getKnowledgeField().split(",");
				for(int i=0;i<fileds.length;i++){
					KnowledgeField knowledgeField=new KnowledgeField();
					knowledgeField=knowledgeFieldService.getById(Integer.valueOf(fileds[i]));
					knowledgeFields.add(knowledgeField);
				}
			}
			String roleName=sysRole.getRoleName();
			String isEditOrNot="false";
			if(journalArticle.getSource()-0>0){
				if(ss!=null&&journalArticle.getSysUser()!=null){
					if(ss.getUserId()-journalArticle.getSysUser().getUserId()==0){
						isEditOrNot="true";
					}else{
						isEditOrNot="false";
					}
				}
			}else{
				isEditOrNot="false";
			}
		List<JournalUser> listUser=journalUserService.getById(id,1);
			List<Map<String, Object>> userMap=new ArrayList<Map<String,Object>>();
			String scureRankIds="";
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
			KnowledgeAcc  knowledgeAcc=new KnowledgeAcc();
			if(journalArticle.getKnowledgeAcc()!=null){
				knowledgeAcc=knowledgeAccService.getById(journalArticle.getKnowledgeAcc().getKnowledgeAccId());
				if(knowledgeAcc.getUploadFiles()!=null){
					String url=knowledgeAcc.getUploadFiles();
					String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
					String uploadFiles="";
					if(journalArticle.getSource()-0!=0){
						uploadFiles=pdfAgencyPath+pdfName;
					}else{
						uploadFiles=imgUrlPath+pdfName;
					}
					knowledgeAcc.setUploadFiles(uploadFiles);
					knowledgeAcc.setImage(url);
				}
			}
		
			Map<String, Object> mapPro=new HashMap<String, Object>();
			mapPro.put("journalActicleId", id);
			mapPro.put("knowledgeClassifyId", 1);
			mapPro.put("roleName",roleName);
			mapPro.put("userId",ss.getUserId());
			List<KnowledgeProRela> listProject=knowledgeProRelaService.getAllByKnowladge(mapPro);
			List<KnowledgeProRela> listPro=new ArrayList<KnowledgeProRela>();
			if(listProject!=null&&listProject.size()>0){
				for(KnowledgeProRela knowledgeProRela:listProject){
					if(knowledgeProRela.getProjectBasicInfo()!=null){
						listPro.add(knowledgeProRela);
					}
				}
			}
			List<KnowledgeClassifyReacRela> listReac=new ArrayList<KnowledgeClassifyReacRela>();
			if (!roleName.equals("ROLE_TEAM")) {
				List<Reaction> list = reactionService.getUserList(ss.getUserId());
				if (list != null) {
				for(int i=0;i<list.size();i++){
					mapPro.put("reactionId", list.get(i).getReactionId());
					List<KnowledgeClassifyReacRela> listAdd=knowledgeReacRelaService.getAllByKnowladge(mapPro);
					if(listAdd!=null&&listAdd.size()>0){
						for(KnowledgeClassifyReacRela knowledgeClassifyReacRela:listAdd){
//							if(knowledgeClassifyReacRela.getReaction().getReactionDesign().getType()==0){
//								Reaction reaction=knowledgeClassifyReacRela.getReaction();
//								reaction.setReactionName(knowledgeClassifyReacRela.getReaction().getReactionDesign().getReactionGroupName());
//								ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();
//								projectBasicInfo.setProName(knowledgeClassifyReacRela.getReaction().getReactionDesign().getProjectBasicInfo().getProName());
//								reaction.setProjectBasicInfo(projectBasicInfo);
//							}
						}
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
							for(KnowledgeClassifyReacRela knowledgeClassifyReacRela:listAdd){
//								if(knowledgeClassifyReacRela.getReaction().getReactionDesign().getType()==0){
//									Reaction reaction=knowledgeClassifyReacRela.getReaction();
//									reaction.setReactionName(knowledgeClassifyReacRela.getReaction().getReactionDesign().getReactionGroupName());
//									ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();
//									projectBasicInfo.setProName(knowledgeClassifyReacRela.getReaction().getReactionDesign().getProjectBasicInfo().getProName());
//									reaction.setProjectBasicInfo(projectBasicInfo);
//								}
							}
							listReac.addAll(listAdd);
						}
					}
				}
				
			}
			//List<KnowledgeClassifyReacRela> listReac=knowledgeReacRelaService.getAllByKnowladge(mapPro);
			String abs=journalArticle.getAbstract_();
			journalArticle.setAbstract_(abs);
			journalArticleVo.setIsEditOrNot(isEditOrNot);
			journalArticleVo.setJournalArticle(journalArticle);
			journalArticleVo.setScureRankIds(scureRankIds);
			journalArticleVo.setKnowledgeAcc(knowledgeAcc);
			journalArticleVo.setKnowledgeProRela(listPro);
			journalArticleVo.setKnowledgeClassifyReac(listReac);
			journalArticleVo.setUserList(userMap);
			journalArticleVo.setKnowledgeFields(knowledgeFields);
			 journalArticleVo.setResult(0);
			if (journalArticle == null) {
				String message = "对象不存在(id:" + id + ")";
				logger.warn(message);
			}
			String pdfName="";
				if(knowledgeAcc!=null&&knowledgeAcc.getUploadFiles()!=null){

					pdfName=knowledgeAcc.getUploadFiles().substring(knowledgeAcc.getUploadFiles().lastIndexOf("/")+1);
				}
			
			
			journalArticleVo.setPdfName(pdfName);
		}else{
			 journalArticleVo.setResult(4);
		}
		return journalArticleVo;
	}
	

	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @throws Exception 
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/journalArticle/add", method = RequestMethod.POST)
	public ResultVo saveOrUpdate(@RequestBody JournalArticleVo journalArticleVo) throws Exception {
		ResultVo res=new ResultVo();
		Map<String, Object> pdfMap=new HashMap<String, Object>();
		try {
			//TODO 判断更新，增加
			Integer id = journalArticleVo.getJournalArticle().getJournalArticleId();
			//JournalArticle journalArticle=journalArticleService.getById(id);
			String pdfName=journalArticleVo.getPdfName();
			SysUser sysUser=getSysUser();
			int angency=sysUser.getSysSigningAgency().getAgencyId();
			JournalArticle journalArticle=journalArticleVo.getJournalArticle();
			Magazine magazine=journalArticle.getMagazine();
			String issn=magazine.getIssn();
			String title=journalArticle.getTitle();
			String author=journalArticle.getAuthor();
			String writeDate=journalArticle.getWriteDate();
			Integer secure=0;
			Boolean flag=false;
			if(journalArticleVo.getScureRankIds()!=null&&!"".equals(journalArticleVo.getScureRankIds())&&!"null".equals(journalArticleVo.getScureRankIds())){//保存保密权限
				secure=2;
			}else{
				secure=1;
			}
			flag=journalArticleService.getResult(issn,title,author,writeDate,sysUser.getUserId(),secure);
			if(flag){
				journalArticle.setMagazine(magazine);
				journalArticle.setSource(angency);
				journalArticle.setCreater(sysUser.getRealname());
				journalArticle.setCreateDate(new Date());
				List<String> pdfStrs=journalArticleVo.getPdfStrs();
				
				if(id==null){
					journalArticleService.save(journalArticle);
				}
				id=journalArticle.getJournalArticleId();
				KnowledgeAcc knowledgeAcc=new KnowledgeAcc();
				Base64Util base64Util = new Base64Util();
				if(pdfStrs!=null&&pdfStrs.size()>0){//保存pdf文件
					 pdfMap=savePdfStr(knowledgeAcc,id,pdfStrs,pdfName,1,filePath);
					knowledgeAcc=(KnowledgeAcc)pdfMap.get("knowledgeAcc");
				}
				journalArticle.setKnowledgeAcc(knowledgeAcc);
				journalArticle.setSysUser(sysUser);
				if(journalArticleVo.getScureRankIds()!=null&&!"".equals(journalArticleVo.getScureRankIds())&&!"null".equals(journalArticleVo.getScureRankIds())){//保存保密权限
					JournalUser journalUser=new JournalUser();
					String[] ss=journalArticleVo.getScureRankIds().split(",");
						for(int i=0;i<ss.length;i++){
						int ScureRankId=Integer.parseInt(ss[i]);
						SysUser user=new SysUser();
						user.setUserId(ScureRankId);
						journalUser.setSysUser(user);
						journalUser.setJournalArticleId(id);
						KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
						knowledgeClassify.setKnowledgeClassifyId(1);
						journalUser.setKnowledgeClassify(knowledgeClassify);
							journalUserService.save(journalUser);
						}
					}
				SecureRank secureRank=new SecureRank();
				secureRank.setSecureRankId(secure);
				journalArticle.setSecureRank(secureRank);
				if(id!=null){
					journalArticleService.update(journalArticle);
				}
				List<KnowledgeProRela> knowledgeProRelaList=journalArticleVo.getKnowledgeProRela();//知识项目
				List<KnowledgeClassifyReacRela> knowledgeClassifyReacRelaList=journalArticleVo.getKnowledgeClassifyReac();//知识实验
				if(knowledgeProRelaList!=null&&knowledgeProRelaList.size()>0){//遍历保存关联项目
					for(int i=0;i<knowledgeProRelaList.size();i++){
						KnowledgeProRela knowledgeProRela=new KnowledgeProRela();
						knowledgeProRela.setMappingKnowledgeIdl(journalArticle.getJournalArticleId());
						ProjectBasicInfo projectBasicInfo=new ProjectBasicInfo();//项目基本信息
						projectBasicInfo.setProId(knowledgeProRelaList.get(i).getMappingKnowledgeIdl());
						knowledgeProRela.setProjectBasicInfo(projectBasicInfo);
						KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
						knowledgeClassify.setKnowledgeClassifyId(1);
						knowledgeProRela.setKnowledgeClassify(knowledgeClassify);
						knowledgeProRela.setCreater(journalArticle.getSysUser());
						knowledgeProRela.setCreateDate(new Date());
						knowledgeProRelaService.save(knowledgeProRela);//保存关联项目表
					}
				}
				if(knowledgeClassifyReacRelaList!=null&&knowledgeClassifyReacRelaList.size()>0){//遍历保存关联实验
					for(int i=0;i<knowledgeClassifyReacRelaList.size();i++){
						KnowledgeClassifyReacRela knowledgeClassifyReacRela=new KnowledgeClassifyReacRela();
						knowledgeClassifyReacRela.setMappingKnowledgeIdl(journalArticle.getJournalArticleId());
						Reaction relation=new Reaction();  //实验基本信息
						relation.setReactionId(knowledgeClassifyReacRelaList.get(i).getKnowledgeClassifyReacRelaId());
						knowledgeClassifyReacRela.setReaction(relation);
						knowledgeClassifyReacRela.setKnowledgeClassifyId(1);
						knowledgeClassifyReacRela.setCreater(String.valueOf(journalArticle.getSysUser().getId()));
						knowledgeClassifyReacRela.setCreateDate(new Date());
						knowledgeReacRelaService.save(knowledgeClassifyReacRela);//保存关联实验
					}
				}	
				res.setResultData(pdfMap.get("pdfNum"));
				res.setErrCode(0);
				res.setErrMsg("新增数据成功！");
			}else{
				res.setErrCode(1);
				res.setErrMsg("数据重复！");
			}
			
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("保存数据出错:"+e.getMessage());
			e.printStackTrace();
			throw e;
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return res;
	}

	public Map<String, Object> savePdfStr(KnowledgeAcc knowledgeAcc,Integer id, List<String> pdfStrs,String pdfName,Integer knowledgeClassifyId,String filePath) throws InvalidPasswordException, IOException {
		//KnowledgeAcc knowledgeAcc = new KnowledgeAcc();
		Map<String, Object> map=new HashMap<String, Object>();
	
		try {
			if(pdfStrs!=null&&pdfStrs.size()>0){
				//base64保存图片
				Base64Util base64Util = new Base64Util();
				pdfName=base64Util.StringFilter(pdfName);
				pdfName=UUID.randomUUID().toString()+pdfName;
				KnowledgeClassify knowledgeClassify=new KnowledgeClassify(); 
				//目前设置科技论文的知识分类主键为1
				knowledgeClassify.setKnowledgeClassifyId(knowledgeClassifyId);
				for(String pdfStr:pdfStrs){
					pdfStr = pdfStr.substring(pdfStr.indexOf(",")+1);
					if(knowledgeAcc==null||knowledgeAcc.getKnowledgeAccId()==null){
						String fileUrl=base64Util.GeneratePdf(pdfStr,filePath,pdfName);
//						String type=fileUrl.substring(fileUrl.indexOf(".")+1, fileUrl.length());
						String type=fileUrl.substring(fileUrl.lastIndexOf(".")+1, fileUrl.length());
						knowledgeAcc=new KnowledgeAcc();
						if(LabConstans.CONVERSION_TYPE.contains(type)){
							knowledgeAcc.setPdfCount(0);
						}else if("pdf".equals(type)){
							knowledgeAcc.setPdfCount(2);
						}else{
							knowledgeAcc.setPdfCount(99);
						}
						knowledgeAcc.setUploadFiles(fileUrl);
						knowledgeAcc.setKnowledgeId(id);
						knowledgeAcc.setKnowledgeClassify(knowledgeClassify);
						knowledgeAccService.save(knowledgeAcc);
					}else{
						base64Util.DeleteFolder(knowledgeAcc.getUploadFiles());
						String fileUrl=base64Util.GeneratePdf(pdfStr,filePath,pdfName);
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
						knowledgeAcc.setUploadFiles(fileUrl);
						knowledgeAcc.setPdfUrl(null);
						knowledgeAccService.update(knowledgeAcc);
						knowledgePostilService.deleteByAccId(knowledgeAcc.getKnowledgeAccId());
						knowledgeThumbsUpService.deleteByAccId(knowledgeAcc.getKnowledgeAccId());
					}
					
				}
			}
//			Map<String, Object> pdfMap=padfToImage(pdfName,1);
//			knowledgeAcc.setPdfCount((Integer)pdfMap.get("pdfNun"));
			//knowledgeAccService.update(knowledgeAcc);
			knowledgeAcc.setUploadFiles(pdfAgencyPath+pdfName);
			map.put("knowledgeAcc", knowledgeAcc);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return map;
	}

	
	
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

	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/journalArticle/addArticle", method = RequestMethod.PUT)
	public void saveJournaleArtilce(@RequestBody JournalArticle journalArticle) {
		try {
			//TODO 判断更新，增加
			Integer id = journalArticle.getJournalArticleId();
			JournalArticle res=journalArticleService.getById(id);
			res.setTitle(journalArticle.getTitle());
			res.setAbstract_(journalArticle.getAbstract_());
			res.setAuthor(journalArticle.getAuthor());
			res.setKeyWords(journalArticle.getKeyWords());
			res.setWriteDate(journalArticle.getWriteDate());
			res.setMagazine(journalArticle.getMagazine());
			res.setKnowledgeField(journalArticle.getKnowledgeField());
			SysUser sysUser=getSysUser();
			res.setModifier(sysUser.getRealname());
			res.setSource(sysUser.getSysSigningAgency().getAgencyId());
			res.setModifyDate(new Date());
//			Magazine magazine=new Magazine();
//			magazine=journalArticle.getMagazine();
//			if(magazine.getMagazineId()!=null){
//				magazineService.update(magazine);
//			}else{
//				magazineService.save(magazine);
//				journalArticle.setMagazine(magazine);
//			}
			if(id==null){
				journalArticleService.save(res);
			}else{
				journalArticleService.update(res);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
	@RequestMapping(value = "/journalArticle/addKnowAcc", method = RequestMethod.PUT)
	public ResultVo saveKnowAcc(@RequestBody JournalArticleVo journalArticleVo) throws Exception {
		ResultVo res=new ResultVo();
		KnowledgeAcc knowledgeAcc=null;
		try {
			Integer journalArticleId=journalArticleVo.getJournalArticle().getKnowledgeAcc().getKnowledgeAccId();
			knowledgeAcc=knowledgeAccService.getById(journalArticleId);
			Boolean flag=true;
			if(knowledgeAcc!=null&&knowledgeAcc.getKnowledgeAccId()!=null){
				flag=false;
			}
			Map<String, Object> map=savePdfStr(knowledgeAcc,journalArticleVo.getJournalArticle().getJournalArticleId(),journalArticleVo.getPdfStrs(),journalArticleVo.getPdfName(),1,filePath);
			if(flag){
				JournalArticle journalArticle=journalArticleService.getById(journalArticleVo.getJournalArticle().getJournalArticleId());
				journalArticle.setKnowledgeAcc((KnowledgeAcc)map.get("knowledgeAcc"));
				journalArticleService.update(journalArticle);
			}
			res.setResultData((KnowledgeAcc)map.get("knowledgeAcc"));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
	@RequestMapping(value = "/journalArticle/addJournalUser", method = RequestMethod.PUT)
	public void saveJournalUser(@RequestParam("scureRankIds") String   scureRankIds,@RequestParam("journalArticleId") Integer   journalArticleId) {
		try {
			
			journalUserService.delete(journalArticleId,1);
			JournalArticle journalArticle=journalArticleService.getById(journalArticleId);
			if(!"".equals(scureRankIds)){
				String[] ss=scureRankIds.split(",");
				for(int i=0;i<ss.length;i++){
					JournalUser journalUser=new JournalUser();
				int ScureRankId=Integer.parseInt(ss[i]);
				KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
				knowledgeClassify.setKnowledgeClassifyId(1);
				journalUser.setKnowledgeClassify(knowledgeClassify);
				SysUser user=new SysUser();
				user.setUserId(ScureRankId);
				journalUser.setSysUser(user);
				journalUser.setJournalArticleId(journalArticleId);
					journalUserService.save(journalUser);
				}
				SecureRank secureRank=new SecureRank();
				secureRank.setSecureRankId(2);
				journalArticle.setSecureRank(secureRank);
			}else{
				SecureRank secureRank=new SecureRank();
				secureRank.setSecureRankId(1);
				journalArticle.setSecureRank(secureRank);
			}
			journalArticleService.update(journalArticle);
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
	@RequestMapping(value = "/journalArticle/addKnowledgeReacRela", method = RequestMethod.POST)
	public void saveKnowledgeReacRela(@RequestBody List<KnowledgeClassifyReacRela>  listReacRelaUpdate,@PathVariable("journalArticleId") Integer journalArticleId) {
		try {
			SysUser ss=getSysUser();
			SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
			String roleName=sysRole.getRoleName();
			Map<String, Object> mapPro=new HashMap<String, Object>();
			mapPro.put("journalActicleId", journalArticleId);
			mapPro.put("knowledgeClassifyId", 1);
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
			//	KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
			//	knowledgeClassify.setKnowledgeClassifyId(1);
				knowledgeReacRela.setKnowledgeClassifyId(1);
				knowledgeReacRelaService.save(knowledgeReacRela);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	/**
	 * @Description 保存关联项目对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class) 
	@RequestMapping(value = "/journalArticle/addKnowledgePro", method = RequestMethod.POST)
	public void saveKnowledgePro(@RequestBody List<KnowledgeProRela>  listProUpdate,@PathVariable("journalArticleId") Integer journalArticleId) {
		try {
			SysUser ss=getSysUser();
			SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
			String roleName=sysRole.getRoleName();
			Map<String, Object> mapPro=new HashMap<String, Object>();
			mapPro.put("journalActicleId", journalArticleId);
			mapPro.put("knowledgeClassifyId", 1);
			mapPro.put("roleName",roleName);
			mapPro.put("userId",ss.getUserId());
			List<KnowledgeProRela> listPro=knowledgeProRelaService.getAllByKnowladge(mapPro);
			for(int i=0;i<listPro.size();i++){
				knowledgeProRelaService.delete(listPro.get(i).getKnowledgeProRelaId());
			}
			for(int i=0;i<listProUpdate.size();i++){
				KnowledgeProRela knowledgeProRela=listProUpdate.get(i);
				KnowledgeClassify knowledgeClassify=new KnowledgeClassify();
				knowledgeClassify.setKnowledgeClassifyId(1);
				knowledgeProRela.setKnowledgeClassify(knowledgeClassify);
				knowledgeProRelaService.save(knowledgeProRela);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}
	@RequestMapping(value = "/journalArticle/del/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		
		journalArticleService.delete(id);
		journalArticleService.deletePro(1,id);
		journalArticleService.deleteReac(1,id);
	}
	
	
	/**
	 * @Description 批量上传
	 * @author wangll
	 * @version V1.0
	 * @throws Exception 
	 * @date 2017年6月28日 
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/journalArticle/batch", method = RequestMethod.POST)
	public List<Map<String,Object>> saveByBatch(@RequestBody String nameString,@RequestParam("scureRankIds") String scureRankIds,@RequestParam("knowledgeField") String knowledgeField ) throws Exception {
		List<JournalArticle> listRes=new ArrayList<JournalArticle>();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			if(!"".equals(nameString)){
				String[] ss=nameString.split("\r\n\r\n");
			for(int i=0;i<ss.length;i++){
				
				String[] res=ss[i].split("\r\n");
			
				  String[] autjoraa=null;
				  String authorUnit="";
				  String Issue="";
				  String issn="";
				  String chName="";
				  String enName="";
				  String name="";
				  String keyWord="";
				  String writeDate="";
				  String title="";
				  String volume="";
				  String callNumber="";
				  String databaseSources="";
				  String pageNum="";
				  StringBuffer auth=new StringBuffer();
				  String author="";
				if(res.length>0&&ss[i].indexOf("%")>=0){
					Map<String,Object> map=new HashMap<String, Object>();
					int a1=ss[i].indexOf("%X");
					//int a2 =ss[i].indexOf("%",a1+3);
					//ss[i].indexOf
					String abstract_="";
					if(a1>0){
						if(ss[i].indexOf("%",a1+3)>0){
							if(ss[i].indexOf("%P")>0){
								abstract_=ss[i].substring(ss[i].indexOf("%X")+3,ss[i].indexOf("%P"));
							}else{
								if(ss[i].indexOf("%W")>0){
									abstract_=ss[i].substring(ss[i].indexOf("%X")+3,ss[i].indexOf("%W"));	
								}
							}
						}else{
							abstract_=ss[i].substring(ss[i].indexOf("%X")+3);
						}
					}
					
					  for(int j=0;j<res.length;j++){
						  if(res[j].indexOf("%T")>=0){
							  title=res[j].substring(res[j].indexOf("%T")+3,res[j].length());
						  }
						  if(res[j].indexOf("%A")>=0){
							  auth.append(res[j].substring(res[j].indexOf("%A")+3,res[j].length())).append(",");
						  }
						  author=auth.toString();
						  if(author.endsWith(",")){
							  author= author.substring(0,author.length()-1);
						  }
						  if(res[j].indexOf("%@")>=0){
							  issn=res[j].substring(res[j].indexOf("%@")+3,res[j].length());
						  }
						  if(res[j].indexOf("%N")>=0){
							  Issue=res[j].substring(res[j].indexOf("%N")+3,res[j].length());
						  }
						  if(res[j].indexOf("%J")>=0){
							  name=res[j].substring(res[j].indexOf("%J")+3,res[j].length());
						  }
						  if(name.matches("[\\u4E00-\\u9FA5]+")){
							  chName=name;
						  }else{
							  enName=name;
						  }
						  if(res[j].indexOf("%K")>=0){
							  keyWord=res[j].substring(res[j].indexOf("%K")+3,res[j].length());
						  }
						  if(res[j].indexOf("%D")>=0){
							  writeDate=res[j].substring(res[j].indexOf("%D")+3,res[j].length());
						  }  
//						  if(res[j].indexOf("%X")>=0){
//							  abstract_=res[j].substring(res[j].indexOf("%X")+3,res[j].length());
//						  }  
						  if(res[j].indexOf("%+")>=0){
							  authorUnit=res[j].substring(res[j].indexOf("%+")+3,res[j].length());
						  }  
						  if(res[j].indexOf("%W")>=0){
							  databaseSources=res[j].substring(res[j].indexOf("%W")+3,res[j].length());
						  }  
						  if(res[j].indexOf("%L")>=0){
							  callNumber=res[j].substring(res[j].indexOf("%L")+3,res[j].length());
						  }  
						  if(res[j].indexOf("%P")>=0){
							  pageNum=res[j].substring(res[j].indexOf("%P")+3,res[j].length());
						  } 
						  }
					  JournalArticle journalArticle=new JournalArticle();
					   SysUser loginUser=getSysUser();
						Magazine magazine=new Magazine();
						journalArticle.setTitle(title);
						journalArticle.setKeyWords(keyWord);
						journalArticle.setWriteDate(writeDate);
						journalArticle.setAbstract_(abstract_);
						journalArticle.setAuthor(author);
						journalArticle.setAuthorUnit(authorUnit);
						journalArticle.setIssue(Issue);
						journalArticle.setCallNumber(callNumber);
						journalArticle.setPageNum(pageNum);
						journalArticle.setDatabaseSources(databaseSources);
						journalArticle.setSysUser(loginUser);
						journalArticle.setCreater(loginUser.getRealname());
						journalArticle.setCreateDate(new Date());
						journalArticle.setSource(loginUser.getSysSigningAgency().getAgencyId());
						Map<String, String> inputMap =new HashMap<String, String>();
						inputMap.put("issn", issn);
						inputMap.put("enName",enName);
						inputMap.put("chName",chName);
						if("".equals(issn)&&"".equals(enName)&&"".equals(enName)){
							Map<String, String> mapElse =new HashMap<String, String>();
							mapElse.put("issn", "999999999");
							magazine=magazineService.getByName(mapElse).get(0);
						}else{
							//int magazineId=magazineService.getMaxMagazineId();
							List<Magazine> magazineSearch=magazineService.getByName(inputMap);	
								if(magazineSearch!=null&&magazineSearch.size()>0){
									magazine=magazineSearch.get(0);
							}else{
								//Magazine magazineSave=new Magazine();
								if(!"".equals(enName)){
									magazine.setEnName(enName);
								}if(!"".equals(chName)){
									magazine.setChName(chName);
								}
								magazineService.save(magazine);
//								Map<String, String> mapElse =new HashMap<String, String>();
//								mapElse.put("issn", "999999999");
//								magazine=magazineService.getByName(mapElse).get(0);
							}
						}
					
						journalArticle.setMagazine(magazine);
						Integer secure=0;
						//保存科技论文主表后取对应的主键
						if(scureRankIds!=null&&!"".equals(scureRankIds)&&!"null".equals(scureRankIds)){//保存保密权限
							
								secure=2;
							}else{
								secure=1;
							}
						Boolean flag=journalArticleService.getResult(issn,title,author,writeDate,loginUser.getUserId(),secure);
						if(flag){
							if(scureRankIds!=null&&!"".equals(scureRankIds)&&!"null".equals(scureRankIds)){//保存保密权限
								JournalUser journalUser=new JournalUser();
								String[] sRank=scureRankIds.split(",");
									for(int j=0;j<sRank.length;j++){
									int ScureRankId=Integer.parseInt(sRank[j]);
									SysUser user=new SysUser();
									user.setUserId(ScureRankId);
									journalUser.setSysUser(user);
									journalUser.setJournalArticleId(journalArticle.getJournalArticleId());//设置科技论文对应的主键
										journalUserService.save(journalUser);
									}
								}
							SecureRank secureRank=new SecureRank();
							secureRank.setSecureRankId(secure);
							MaterialField materialField=new MaterialField();
//							materialField.setCid(cid);
							journalArticle.setSecureRank(secureRank);
							journalArticle.setKnowledgeField(knowledgeField);
							//listRes.add(journalArticle);
							try {
							journalArticleService.save(journalArticle);
							map.put("resDate", journalArticle);
							map.put("flag", 1);//导入成功
							} catch (Exception e) {
							journalArticleService.save(journalArticle);
							map.put("resDate", journalArticle);
							map.put("flag", 3);//未知错误
							}	
						}else{
							map.put("resDate", journalArticle);
							map.put("flag", 2);//数据重复
						}
						list.add(map);	
				}
			
				  }
		
		}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return list;
	}

	
	
	/**
	 * @Description PDF转为image
	 * @author wangll
	 * @version V1.0
	 * @throws IOException 
	 * @throws InvalidPasswordException 
	 * @date 2017年6月28日 
	 */

	@RequestMapping(value = "/journalArticle/pdfToImage", method = RequestMethod.POST)
	public Map<String,Object> padfToImage(@RequestParam("pdfName") String pdfName,@RequestParam("pdfNum")Integer pdfNum) throws InvalidPasswordException, IOException {
		Map<String,Object> resMap=new HashMap<String,Object>();
//		PDDocument document=null;
//		try {
//			String pdf=pdfName.substring(0,pdfName.indexOf("."));
//			int pageCounter = pdfNum-1;
//			Map<Integer, String> maps=new HashMap<Integer, String>();
//			List<Map<Integer, String>> imgMap=new ArrayList<Map<Integer,String>>();
//			List<String> imgAddr=new ArrayList<String>();
//				document = PDDocument.load(new File(filePath+pdfName));
//				PDDocumentCatalog dc=document.getDocumentCatalog();
//				String ss=dc.getLanguage();
//				PDFRenderer pdfRenderer = new PDFRenderer(document);
//			for (PDPage page : document.getPages()){
//				if(pageCounter<document.getNumberOfPages()){
//						 BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 150, ImageType.RGB);
//						    String fileAddr=imgPath +pdf+ "/img" + (pageCounter++) + ".png";
//						    File outFile = new File( fileAddr);
//						    if (!outFile.getParentFile().exists()) {
//						    	outFile.getParentFile().mkdirs();
//						     }
//						    	 ImageIO.write(bim, "png", outFile);// 写图片 
//						    	 imgAddr.add(imgUrlPath+pdf+"/img" +(pageCounter-1)+ ".png");
//						    	 maps.put(pageCounter, imgUrlPath+pdf+"/img" +(pageCounter-1)+ ".png");
//				}
//				}
//				resMap.put("imgAddr", maps);
//				resMap.put("pdfNun", document.getNumberOfPages());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			document.close();
//		}
			return resMap;
		}
		
	


	@RequestMapping(value = "/journalArticle/pdfToImageThread", method = RequestMethod.POST)
	public Map<String,Object> padfToImageThread(@RequestParam("pdfName") String pdfName,@RequestParam("pdfNum")Integer pdfNum,@RequestParam("pdfCount")Integer pdfCount) throws InvalidPasswordException, IOException, InterruptedException {
		Map<String,Object> resMap=new HashMap<String,Object>();
//		String pdf=pdfName.substring(pdfName.indexOf("/pdfs")+6,pdfName.indexOf(".pdf"));
//		List<String> imgAddr=new ArrayList<String>();
//		List<Map<Integer, String>> imgMap=new ArrayList<Map<Integer,String>>();
//		Map<Integer, String> maps=new HashMap<Integer, String>();
//		String testAddr="";
//	    String test[];
//	    int pngSize=0;
//	    Map<String,Object> map=new HashMap<String, Object>();
//	    List<String> testPng=new ArrayList<String>();
//	  for(int i=0;i<pdfCount;i++){
//	    	testAddr =imgPath +pdf+ "/img" +0+ ".png";
//	    	 File testFile = new File(testAddr);
//	    	 if(testFile.exists()){
//	    		 maps.put(i+1,imgUrlPath+pdf+"/img" +i+ ".png"); 
//	    	 }
//	    }
//	  resMap.put("imgAddr", maps);
//			int pageCounter = 0;
//			if(pngSize<pdfCount){
//				if(pngSize>pdfNum){
//					pageCounter=pngSize;
//				}else{
//					pageCounter=pdfNum-6;
//				}
//				ExecutorService exec=Executors.newCachedThreadPool();
//				ArrayList<Future<Map<String,Object>>> results=new ArrayList<Future<Map<String,Object>>>();
//						try {
//							exec.submit(new pdfToImage(pdfNum,pageCounter,pdfName,imgPath,imgUrlPath,filePath));
//						} catch (Exception e) {
//										e.printStackTrace();
//						}finally{
//							flag=true;
//							exec.shutdown();	
//						}
//				
//			}
////		}
		
		return resMap;
	}
	
	@RequestMapping(value = "/journalArticle/getSelfKnowledge/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
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
					knowledgeList=journalArticleService.getSelfKnowledge(sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId(),keyword);
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
	
	@RequestMapping(value = "/journalArticle/getknowledge", method = RequestMethod.GET)
	public ResultVo knowledge() {
		ResultVo resuslt=new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		Integer roleId=sysUser.getSysRole().getRoleId();
		String roleName=sysUser.getSysRole().getRoleName();
		try {
			List<Map<String, Object>> listKnowledge=journalArticleService.getKnowledge(roleName,userId);
			List<Map<String, Object>> res=new ArrayList<Map<String,Object>>();
			if(listKnowledge!=null&&listKnowledge.size()>0){
				for(int i=0;i<listKnowledge.size();i++){
					if(listKnowledge.get(i).get("upload_files")!=null&&listKnowledge.get(i).get("knowledgeAccId")!=null){
						String uf=String.valueOf(listKnowledge.get(i).get("upload_files"));
						String image="";
						String uploadFiles="";
						if(uf.indexOf("/pdf")>0){
							String pdfName=uf.substring(uf.indexOf("/pdfs/")+6, uf.length());
							uploadFiles=imgUrlPath+pdfName;
							image=uf.substring(uf.indexOf("/pdf")+6, uf.length());
						}
						List<KnowledgeThumbsUp> thumbsUpList=knowledgeThumbsUpService.getAll(Integer.valueOf(String.valueOf(listKnowledge.get(i).get("knowledgeAccId"))));
						String isUser="0";
						if(thumbsUpList!=null&&thumbsUpList.size()>0){
							for(int k=0;k<thumbsUpList.size();k++){
								if(sysUser.getUserId()==thumbsUpList.get(k).getSysUser().getUserId()){
									isUser="1";
								}
							}
							
						}
						listKnowledge.get(i).put("isUser", isUser);
						listKnowledge.get(i).put("uploadFiles", uploadFiles);
						listKnowledge.get(i).put("image", image);
						res.add(listKnowledge.get(i));
						
					}
				}
		}
			resuslt.setErrCode(0);
			resuslt.setErrMsg("查询成功！");
			resuslt.setResultData(res);
		} catch (Exception e) {
			resuslt.setErrCode(1);
			resuslt.setErrMsg("查询失败！"+e.getMessage());
		}
	
		return resuslt;
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
	 
	 
	 
	 /**
		 * @Description 根据文献分类和名字获取科技论文
		 * @author liuhq
		 * @version V1.0
		 * @throws IOException 
		 * @date 2017年5月18日 上午11:49:52
		 */
		
		@RequestMapping(value = "/journalArticle/fieldListApp/{page}/{pageSize}/{keyword}/{filedId}", method = RequestMethod.GET)
		public 	ResultVo  getAllFieldListsForApp(@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize,
				@PathVariable("keyword") String keyword,@PathVariable("filedId") Integer filedId)  {
		
			ResultVo resultVo = new ResultVo();
			SysUser ss=getSysUser();
			SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
			String roleName=sysRole.getRoleName();
			List<MaterialField> materialFieldList=new ArrayList<MaterialField>();
			List<Object> resList=new ArrayList<Object>();
			List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
			if(filedId-0==0){
				materialFieldList = materialFieldService.getAllFirst();
				resList.add(materialFieldList);
				if (page != null && pageSize != null) {
					PageHelper.startPage(page, pageSize);
				}
				knowledgeList = journalArticleService.getAllKnowlist(ss.getUserId(),roleName,keyword);
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
				if (page != null && pageSize != null) {
					PageHelper.startPage(page, pageSize);
				}
				knowledgeList = journalArticleService.getAllBaseKnowByFiledId(ss.getUserId(),roleName,keyword,filedId);
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
			}
			Map<String, Object> res=new HashMap<String, Object>();		
			res.put("knowledgeList", new  PageInfo(knowledgeList));
			res.put("materialField", resList);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("查询成功！");
			resultVo.setResultData(res);
			return resultVo;
		}
		
		
		@RequestMapping(value = "/journalArticle/getSelfKnowledgeForApp/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
		public ResultVo getSelfKnowledgeForApp(@PathVariable("page") Integer page,@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
			ResultVo resuslt=new ResultVo();
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer userId = sysUser.getUserId();
			Integer roleId=sysUser.getSysRole().getRoleId();
			String roleName=sysUser.getSysRole().getRoleName();
			try {
				if (page != null && pageSize != null) {
					PageHelper.startPage(page, pageSize);
				}
				List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
				knowledgeList=journalArticleService.getSelfKnowledgeForApp(sysUser.getUserId(),sysUser.getSysSigningAgency().getAgencyId(),keyword);
						for(Map<String, Object> base:knowledgeList){
							if(base.get("upload_files")!=null){
								String url=(String)base.get("upload_files");
								if(url!=null&&!"".equals(url)){
									String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
									String uploadFiles=pdfAgencyPath+pdfName;
									base.put("pdfPath", uploadFiles);
								}
								
							}
						}
						Map<String, Object> res=new HashMap<String, Object>();
						res.put("knowledgeList",  new  PageInfo(knowledgeList));
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
		
		@RequestMapping(value = "/journalArticle/PersonListApp/{page}/{pageSize}/{keyword}/{filedId}", method = RequestMethod.GET)
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
			knowledgeList = journalArticleService.getAllPersonKnowlist(ss.getUserId(),roleName,keyword,filedId);
				for(Map<String, Object> base:knowledgeList){
					if(base.get("upload_files")!=null){
						String url=(String)base.get("upload_files");
						if(url!=null&&!"".equals(url)){
							String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
							String uploadFiles=pdfAgencyPath+pdfName;
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
		
		
		
		 /**
		 * @Description 根据文献分类和名字获取科技论文
		 * @author liuhq
		 * @version V1.0
		 * @throws IOException 
		 * @date 2017年5月18日 上午11:49:52
		 */
		
		@RequestMapping(value = "/document/getForProAndReac/{page}/{pageSize}/{type}/{keyword}", method = RequestMethod.GET)
		public 	ResultVo  getForProAndReac(@PathVariable("page") int page,@PathVariable("pageSize") int pageSize,@PathVariable("type") int type,@PathVariable("keyword") String keyword)  {
		
			ResultVo resultVo = new ResultVo();
			int highSize=page*pageSize;
			int flag=0;
			SysUser ss=getSysUser();
			SysRole sysRole=sysRoleService.getById(Long.valueOf(String.valueOf(ss.getSysRole().getRoleId())));
			String roleName=sysRole.getRoleName();
			List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
			if(type==1){
				knowledgeList = journalArticleService.getAllJournals(ss.getUserId(),roleName,keyword);
				if(knowledgeList!=null&&knowledgeList.size()>0){
					for(Map<String, Object> base:knowledgeList){
						if(base.get("upload_files")!=null){
							String url=(String)base.get("upload_files");
							Integer source=(Integer)base.get("source");
							if(source!=0){
								if(url!=null&&!"".equals(url)){
									String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
									String uploadFiles=pdfAgencyPath+pdfName;
									base.put("pdfPath", uploadFiles);
								}
							}else{
								String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
								String uploadFiles=imgUrlPath+pdfName;
								base.put("pdfPath", uploadFiles);
							}
							
						}
					}
				}
				
				
			}
			if(type==2){
				knowledgeList = journalArticleService.getAllSelf(ss.getUserId(),roleName,keyword);
				if(knowledgeList!=null&&knowledgeList.size()>0){
					for(Map<String, Object> base:knowledgeList){
						if(base.get("upload_files")!=null){
							String url=(String)base.get("upload_files");
							String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
							String uploadFiles=imgUrlPath+pdfName;
							base.put("pdfPath", uploadFiles);
						}
					}
				}
			}
			if(type==4){
				knowledgeList = journalArticleService.getAllPatent(ss.getUserId(),roleName,keyword);
				if(knowledgeList!=null&&knowledgeList.size()>0){
					for(Map<String, Object> base:knowledgeList){
						if(base.get("upload_files")!=null){
							String url=(String)base.get("upload_files");
							Integer source=(Integer)base.get("source");
							if(source!=0){
								if(url!=null&&!"".equals(url)){
									String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
									String uploadFiles=pdfAgencyPath+pdfName;
									base.put("pdfPath", uploadFiles);
								}
							}else{
								String pdfName=url.substring(url.indexOf("/pdfs/")+6, url.length());
								String uploadFiles=imgUrlPath+pdfName;
								base.put("pdfPath", uploadFiles);
							}
							
						}
					}
				}
			}
			if(knowledgeList!=null&&knowledgeList.size()>0){
				if(knowledgeList.size()>highSize){
					flag=1;
					knowledgeList=knowledgeList.subList(0, highSize);
				}else{
					knowledgeList=knowledgeList.subList(0, knowledgeList.size());
				}
			}
			
			Map<String, Object> res=new HashMap<String, Object>();		
			res.put("knowledgeList", knowledgeList);
			res.put("flag", flag);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("查询成功！");
			resultVo.setResultData(res);
			return resultVo;
		}
		
		
		
		
		
		
		
}
