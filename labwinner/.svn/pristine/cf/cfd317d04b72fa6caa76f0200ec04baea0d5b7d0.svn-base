package com.labwinner.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Expert;
import com.labwinner.domain.ExpertAssist;
import com.labwinner.domain.HotSearch;
import com.labwinner.domain.JournalArticle;
import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.KnowledgeClassifyPostil;
import com.labwinner.domain.KnowledgeThumbsUp;
import com.labwinner.domain.Patent;
import com.labwinner.domain.ProjectMoments;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ExpertAssistService;
import com.labwinner.service.ExpertService;
import com.labwinner.service.HotSearchService;
import com.labwinner.service.JournalArticleService;
import com.labwinner.service.KnowledgePostilService;
import com.labwinner.service.KnowledgeThumbsUpService;
import com.labwinner.service.PatentService;
import com.labwinner.service.ProjectMomentsService;
import com.labwinner.service.SelfPaperService;
import com.labwinner.service.SysUserService;
import com.labwinner.vo.JournalArticleVo;
import com.labwinner.vo.KnowledgeVo;

@RestController
public class KnowledgeFirstController {
	
	private static Logger logger = LoggerFactory
			.getLogger(KnowledgeFirstController.class);

	
	@Autowired
	private JournalArticleService journalArticleService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SelfPaperService selfPaperService;
	
	@Autowired
	private PatentService patentService;
	
	
	@Autowired
	private ProjectMomentsService projectMomentsService;
	
	@Autowired
	private HotSearchService hotSearchService;
	
	@Autowired
	private ExpertService expertService;
	
	@Autowired
	private KnowledgePostilService knowledgePostilService;
	
	@Autowired
	private KnowledgeThumbsUpService knowledgeThumbsUpService;
	
	
	@Autowired
	private ExpertAssistService expertAssistService;
	
	@Value("${expert.url-path}")
	String imgPath;
	@Value("${web.url_path_pdf}")
	String pdfPath;
	@Value("${web.agency_pdf}")
	String agencyPdf;
	
	
	
	@Value("${sysUserPhone.url-path}")
	String userImage;
	
	@Value("${web.url_path_appImage}")
	String appImagePath;
	
	
	
	@RequestMapping(value = "/knowledge/first", method = RequestMethod.GET)
	public ResultVo getKnowledgeFirsta() {
		ResultVo res=new ResultVo();
		Map<String, Object> resMap=new HashMap<String, Object>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		int angency=sysUser.getSysSigningAgency().getAgencyId();
		Integer userId = sysUser.getUserId();
		String roleName=sysUser.getSysRole().getRoleName();
		int journalNum=0;
		int selfPaperNum=0;
		int patentNum=0;
		int noteNum=0;
		int journalBaseNum=0;
		int journalSlefNum=0;
		int journalAgencyNum=0;
		try {
			journalNum=journalArticleService.getJournalNum(userId,roleName,angency);
			journalBaseNum=journalArticleService.journalBaseNum();
			journalSlefNum=journalArticleService.journalSlefNum(userId,angency);
			journalAgencyNum=journalArticleService.journalAgencyNum(userId,angency);
			selfPaperNum=selfPaperService.getSelfPaperNum(userId,roleName);
			patentNum=patentService.getPatentNum();
			noteNum=patentService.getMediaNum(userId);
			//热门搜索
			List<Map<String, Object>> hotSearchList1 = new ArrayList<Map<String, Object>>();
			hotSearchList1=journalArticleService.getHotSearch();
			List<Map<String, Object>> hotSearchList = new ArrayList<Map<String, Object>>();
			if(hotSearchList1.size()>8){
				hotSearchList=hotSearchList1.subList(0, 8);
			}else{
				hotSearchList=hotSearchList1;
			}
			List<JournalArticle> list=new ArrayList<JournalArticle>();
			list = journalArticleService.getAll(userId,roleName,angency);
			//List<Map<String, Object>> chineseName=journalArticleService.getExpertNameCh();
			List<Map<String, Object>> chineseName=journalArticleService.getExpertName();
			//List<Map<String, String>> englishName=journalArticleService.getExpertNameEn();
			//专家显示
			List<Map<String, Object>> expertList=new ArrayList<Map<String,Object>>();
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					String author=list.get(i).getAuthor();
					if(author!=null){
						String[] ss=author.split(",");
						List<String> authorList=Arrays.asList(ss);
						if(chineseName!=null&&chineseName.size()>0){
							for(int j=0;j<chineseName.size();j++){
								Object experName=chineseName.get(j).get("expert_name_ch")==null?chineseName.get(j).get("expert_name_en"):chineseName.get(j).get("expert_name_ch");
								String name=String.valueOf(experName);
								if(authorList.contains(name)){
									Integer expertId=Integer.valueOf(String.valueOf(chineseName.get(j).get("expert_id")));
									String url="";
								
									if((Integer)chineseName.get(j).get("source")-0==0){
										if(chineseName.get(j).get("img_url")!=null)
										url=imgPath+chineseName.get(j).get("img_url");
									}else{
										SysUser expertUser=sysUserService.getExpertUser(expertId);
										if(expertUser!=null&&expertUser.getUserImage()!=null){
											url=userImage+expertUser.getUserImage();
										}else{
											if(chineseName.get(j).get("img_url")!=null){
												url=imgPath+chineseName.get(j).get("img_url");
											}
										}
									}
									//Integer expertId=Integer.valueOf(id);
									//SysUser expertUser=sysUserService.getByExpertId(expertId);
								
									Map<String, Object> map=new HashMap<String, Object>();
									map.put("knowledgeId", list.get(i).getJournalArticleId());
									map.put("knowledgeName", list.get(i).getTitle());
									map.put("expertName", name);
									map.put("expertId", chineseName.get(j).get("expert_id"));
									map.put("imgUrl", url);
									expertList.add(map);
								}
							}
						}
//						if(englishName!=null&&englishName.size()>0){
//							for(int j=0;j<englishName.size();j++){
//								if(authorList.contains(englishName.get(j).get("expert_name_en"))){
//									Map<String, Object> map=new HashMap<String, Object>();
//									map.put("knowledgeId", list.get(i).getJournalArticleId());
//									map.put("knowledgeName", list.get(i).getTitle());
//									map.put("expertName", englishName.get(j).get("expert_name_en"));
//									String url=englishName.get(j).get("img_url")==null?"":imgPath+englishName.get(j).get("img_url");
//									map.put("imgUrl", url);
//									expertList.add(map);
//								}
//							}
//						}
						
					}
				}
			}
			//------------------------知识热度和数量---------------------------------- 
			List<Map<String, Object>> hotSearchAndKnowList = new ArrayList<Map<String, Object>>();
			if(hotSearchList1.size()>30){
				hotSearchAndKnowList=hotSearchList1.subList(0, 31);
			}else{
				hotSearchAndKnowList=hotSearchList1;
			}
			if(hotSearchAndKnowList.size()>0){
				for(int i=0;i<hotSearchAndKnowList.size();i++){
					int knowNum=journalArticleService.getKnowledgeNum((String)hotSearchAndKnowList.get(i).get("search_name"),userId,roleName);
					hotSearchAndKnowList.get(i).put("knowNum", knowNum);
				}
			}
			//------------------------知识上传和数量---------------------------------- 
			
			List<Map<String, Object>> UserJourKnowList = new ArrayList<Map<String, Object>>();
			UserJourKnowList=journalArticleService.getUserJourKnowList();
			
			List<Map<String, Object>> UserSelfList = new ArrayList<Map<String, Object>>();
			UserSelfList=journalArticleService.getUserSelfList();
			
			List<Map<String, Object>> UserPatentList = new ArrayList<Map<String, Object>>();
			UserPatentList=journalArticleService.getUserPatentList();
			
			List<Map<String, Object>> UserNoteList = new ArrayList<Map<String, Object>>();
			UserNoteList=journalArticleService.getUserNoteList();
			
			//-------------------------------------知识更新显示----------------------------------
			List<Map<String, Object>> listKnow=new ArrayList<Map<String,Object>>();
			listKnow=journalArticleService.getKnowledgeFirst(roleName,userId);
			if(listKnow!=null&&listKnow.size()>0){
				if(listKnow.size()>4){
					listKnow=listKnow.subList(0, 4);
				}
				for(int i=0;i<listKnow.size();i++){
					if(listKnow.get(i).get("url")!=null){
						listKnow.get(i).put("url", userImage+listKnow.get(i).get("url"));
					}
				}
			}
			resMap.put("expertNum", chineseName.size());
//			if(expertList.size()>4){
//				expertList=expertList.subList(0, 4);
//			}
			
			//-------------------------------------赋值------------------------------------------
			resMap.put("journalNum", journalNum);
			resMap.put("journalBaseNum", journalBaseNum);
			resMap.put("journalSlefNum", journalSlefNum);
			resMap.put("journalAgencyNum", journalAgencyNum);
			resMap.put("selfPaperNum", selfPaperNum);
			resMap.put("patentNum", patentNum);
			resMap.put("mediaNum", noteNum);
			resMap.put("hotSearch",hotSearchList);
			resMap.put("expertList",expertList);
			resMap.put("listKnow",listKnow);
			resMap.put("hotSearchAndKnowList",hotSearchAndKnowList);
			resMap.put("UserJourKnowList",UserJourKnowList);
			resMap.put("UserSelfList",UserSelfList);
			resMap.put("UserPatentList",UserPatentList);
			resMap.put("UserNoteList",UserNoteList);
			res.setErrCode(0);
			res.setResultData(resMap);
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg(e.getMessage());
		}
		return res;
	}
	
	

	
	@RequestMapping(value = "/knowledge/getByHotSearch/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getByHotSearch(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		ResultVo res=new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer userId = sysUser.getUserId();
			String roleName=sysUser.getSysRole().getRoleName();
			HotSearch hotSearch=hotSearchService.getByName(keyword);
			if(hotSearch!=null&&hotSearch.getHotSearchId()!=null){
				hotSearch.setSearchNum(hotSearch.getSearchNum()+1);
				hotSearchService.update(hotSearch);
			}else{
				HotSearch hotSearchNew=new HotSearch();
				hotSearchNew.setSearchName(keyword);
				hotSearchNew.setSearchNum(1);
				hotSearchService.save(hotSearchNew);
			}
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			List<Map<String, Object>> listKnow=new ArrayList<Map<String,Object>>();
			listKnow=journalArticleService.getByHotSearch(keyword,userId,roleName);
			res.setErrCode(0);
			res.setErrMsg("查询成功！");
			res.setResultData(new PageInfo(listKnow));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}
	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @throws Exception 
	 * @date 2017年5月18日 上午11:51:21
	 */
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/knowledge/share", method = RequestMethod.POST)
	public ResultVo saveShare(@RequestBody KnowledgeVo knowledgeVo) throws Exception {
		ResultVo res=new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		ProjectMoments projectMoments=new ProjectMoments();
		projectMoments.setCreateDate(new Date());
		projectMoments.setPublishTime(new Date());
		projectMoments.setCreater(String.valueOf(userId));
		projectMoments.setUrlTitle(knowledgeVo.getContent());
		projectMoments.setKnowledgeMes(knowledgeVo.getKnowledgeAccId());
		String url=knowledgeVo.getUrl();
		String pdfUrl="";
//		int classfy=Integer.valueOf(knowledgeVo.getClassfy());
//		if(classfy==2){
//			pdfUrl=pdfPath+url;
//		}else if(classfy==1){
			JournalArticle journalArticle=journalArticleService.getByAccId(knowledgeVo.getKnowledgeAccId());
			if(journalArticle!=null){
				if(journalArticle.getSource()==0){
					pdfUrl=pdfPath+url;
				}else{
					pdfUrl=agencyPdf+url;
				}
			}
			
//		}else if(classfy==4){
//				Patent patent=patentService.getByAccId(knowledgeVo.getKnowledgeAccId());
//				if(patent.getSource()==0){
//					pdfUrl=pdfPath+url;
//				}else{
//					pdfUrl=agencyPdf+url;
//				}
//		}
		
		projectMoments.setUrl(pdfUrl);
		projectMoments.setShareType(1);
		projectMoments.setUrlType(1);
		projectMoments.setUrlIcon("1");
		projectMoments.setSysUser(sysUser);
		projectMomentsService.save(projectMoments);
		return res;
	}
	
	@RequestMapping(value = "/knowledge/expertAndKnow/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getExpertAndKnow(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("keyword") String keyword) {
		ResultVo res=new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		int angency=sysUser.getSysSigningAgency().getAgencyId();
		Integer userId = sysUser.getUserId();
		String roleName=sysUser.getSysRole().getRoleName();
		try {
			List<JournalArticle> list=new ArrayList<JournalArticle>();
			if(!"undefined".equals(keyword)){
				list = journalArticleService.getAllByTitleAndExpert(userId,roleName,angency,keyword);
			}else{
				list = journalArticleService.getAll(userId,roleName,angency);
			}
			
			List<Map<String, Object>> chineseName=journalArticleService.getExpertNameCh();
			//List<Map<String, String>> englishName=journalArticleService.getExpertNameEn();
			
			//专家显示
			List<Map<String, Object>> expertList=new ArrayList<Map<String,Object>>();
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					String author=list.get(i).getAuthor();
					if(author!=null){
						String[] ss=author.split(",");
						List<String> authorList=Arrays.asList(ss);
						if(chineseName!=null&&chineseName.size()>0){
							for(int j=0;j<chineseName.size();j++){
								Object experName=chineseName.get(j).get("expert_name_ch")==null?chineseName.get(j).get("expert_name_en"):chineseName.get(j).get("expert_name_ch");
								String name=String.valueOf(experName);
								if(authorList.contains(name)){
									Integer expertId=Integer.valueOf(String.valueOf(chineseName.get(j).get("expert_id")));
									//Integer expertId=Integer.valueOf(id);
									SysUser expertUser=sysUserService.getByExpertId(expertId);
									Map<String, Object> map=new HashMap<String, Object>();
									map.put("knowledgeId", list.get(i).getJournalArticleId());
									map.put("knowledgeName", list.get(i).getTitle());
									map.put("abstract", list.get(i).getAbstract_());
									map.put("writeDate", list.get(i).getCreateDate());
									map.put("expertName", name);
									map.put("expertId", chineseName.get(j).get("expert_id"));
									String url="";
									if(expertUser!=null&&expertUser.getUserImage()!=null){
										url=userImage+expertUser.getUserImage();
									}else{
										url=chineseName.get(j).get("img_url")==null?"":imgPath+chineseName.get(j).get("img_url");
									}
									
									map.put("imgUrl", url);
									expertList.add(map);
								}
							}
						}
						
					}
				}
			}
			
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			List<Map<String, Object>> expertAllList=expertList;
			int total=expertAllList.size();
			double c = (((double)total)/10);
			  int d = (int) Math.ceil(c);
			Map<String, Object> resMap=new HashMap<String, Object>();
			resMap.put("pageNum", page);
			resMap.put("pages", d);
			resMap.put("total", total);
			int num=expertAllList.size()>page*pageSize?page*pageSize:expertAllList.size();
			resMap.put("list", expertAllList.subList((page-1)*pageSize, num));
			res.setErrCode(0);
			res.setResultData(resMap);
		} catch (Exception e) {
			res.setErrCode(1);
		}
		
		return res;
	}
	
	
	@RequestMapping(value = "/knowledge/expert", method = RequestMethod.GET)
	public ResultVo getExpert() {
		ResultVo res=new ResultVo();
		Map<String, Object> resMap=new HashMap<String, Object>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName=sysUser.getSysRole().getRoleName();
		int agencyId=sysUser.getSysSigningAgency().getAgencyId();
		String readMan=userId+"_"+agencyId;
		try {
			int unReadNumber=expertAssistService.getUnReadNumber(userId,agencyId,readMan);
			List<JournalArticle> list=new ArrayList<JournalArticle>();
			//list = journalArticleService.getAll(userId,roleName);
			//list = journalArticleService.getAllForPerson();
			int angency=sysUser.getSysSigningAgency().getAgencyId();
			list = journalArticleService.getAll(userId,roleName,angency);
			//List<Map<String, Object>> chineseName=journalArticleService.getExpertNameCh();
			List<Map<String, Object>> chineseName=journalArticleService.getUserExpert();
			List<Map<String, Object>> expertList=new ArrayList<Map<String,Object>>();
			//专家显示
			if(chineseName!=null&&chineseName.size()>0){
				for(int j=0;j<chineseName.size();j++){
					Map<String, Object> map=new HashMap<String, Object>();
					List<Map<String, Object>> jourList=new ArrayList<Map<String,Object>>();
					map.put("expertName", chineseName.get(j).get("expert_name_ch")==null?chineseName.get(j).get("expert_name_en"):chineseName.get(j).get("expert_name_ch"));
					map.put("expertId", chineseName.get(j).get("expert_id"));
					map.put("achievement", chineseName.get(j).get("achievement"));
					map.put("researchArea", chineseName.get(j).get("research_area"));
					Integer expertId=Integer.valueOf(String.valueOf(chineseName.get(j).get("expert_id")));
					//Integer expertId=Integer.valueOf(id);
					SysUser expertUser=sysUserService.getByExpertId(expertId);
					//map.put("userId", chineseName.get(j).get("user_id"));
					map.put("isExpert", 1);
					String url="";
					if(sysUser.getExpert()!=null){
						if(sysUser.getExpert().getExpertId()-expertId==0){
							map.put("isExpert", 1);
						}else{
							map.put("isExpert", 0);
						}
					}else{
						map.put("isExpert", 0);
					}
					if(expertUser!=null){
						map.put("userId", expertUser.getUserId());
						map.put("agencyId", expertUser.getSysSigningAgency().getAgencyId());
						List<Map<String, Object>> port = journalArticleService.getAgencyContents(expertUser.getSysSigningAgency().getAgencyId());
						String agencyName="";
						String staticPath="";
						String imagePath="";
						for(Map<String, Object> content:port){
							if("agencyName".equals(content.get("contents_name").toString())){
								agencyName=content.get("contents_value").toString();
							}
							if("testfile".equals(content.get("contents_name").toString())){
								staticPath=content.get("contents_value").toString();
							}
							if("user_Image".equals(content.get("contents_name").toString())){
								imagePath=content.get("contents_value").toString();
							}
						}
					
						if(expertUser.getUserImage()!=null){
							url="http://"+staticPath+"/"+agencyName+imagePath+expertUser.getUserImage();
						}else{
							url=chineseName.get(j).get("img_url")==null?"":imgPath+chineseName.get(j).get("img_url");
						}
					}else{
						url=chineseName.get(j).get("img_url")==null?"":imgPath+chineseName.get(j).get("img_url");
					}
					
					map.put("imgUrl", url);
					if(list!=null&&list.size()>0){
						for(int i=0;i<list.size();i++){
						
							String author=list.get(i).getAuthor();
							if(author!=null){
								String[] ss=author.split(",");
								if(ss.length>0){
								List<String> authorList=Arrays.asList(ss);
								Map<String, Object> knowMap=new HashMap<String, Object>();
								if(authorList.contains(chineseName.get(j).get("expert_name_ch"))){
									knowMap.put("knowledgeId", list.get(i).getJournalArticleId());
									knowMap.put("knowledgeName", list.get(i).getTitle());
									jourList.add(knowMap);
								}
								}
							}
						}
					}
					//map.put("jourList", jourList);
					map.put("knowledgeNum", jourList.size());
					//if(jourList.size()>0){
						expertList.add(map);
					//}
					
				}
			}

			//匿名实现Comparator接口进行排序
			Collections.sort(expertList, new Comparator<Map<String,Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			//进行判断
			return ((Integer)o2.get("knowledgeNum")).compareTo((Integer)o1.get("knowledgeNum"));
			}
			});
//			for(Map<String,Object> m:expertList){
//			System.out.println("Map[expertName="+m.get("expertName")+"expertId="+m.get("expertId")+"achievement="+m.get("achievement")+"nativePlace="+m.get("nativePlace")+"]");
//			}
			resMap.put("expertList",expertList);
			resMap.put("unReadNumber",unReadNumber);
			res.setErrCode(0);
			res.setResultData(resMap);
		} catch (Exception e) {
			res.setErrCode(1);
			res.setResultData("专家团报错"+e.getMessage());
		}
		return res;
	}
	
	
	
	@RequestMapping(value = "/knowledge/getKnowledgeByExpert/{page}/{pageSize}/{keyword}/{expertName}", method = RequestMethod.GET)
	public ResultVo getKnowledgeByExpert(@PathVariable("page") int page,@PathVariable("pageSize") int pageSize,
			@PathVariable("keyword") String keyword,@PathVariable("expertName") String expertName) {
		ResultVo res=new ResultVo();
		Map<String, Object> resMap=new HashMap<String, Object>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUser.getUserId();
		String roleName=sysUser.getSysRole().getRoleName();
		int count =page*pageSize;
		try {
			
			List<JournalArticle> list=new ArrayList<JournalArticle>();
			if(keyword.equals("undefined")){
				//list = journalArticleService.getAll(userId,roleName);
				//list = journalArticleService.getAllForApp(userId,1000,roleName);
				list = journalArticleService.getAllForPerson(expertName);
				}else{
					//list = journalArticleService.getByName(userId,keyword,roleName);
					//list = journalArticleService.getByNameForApp(userId,keyword,1000,roleName);
					list = journalArticleService.getAllForPersonByName(keyword,expertName);
				}
		
//			List<JournalArticle> resultList=new ArrayList<JournalArticle>();
//			//专家显示
//					if(list!=null&&list.size()>0){
//						for(int i=0;i<list.size();i++){
//						
//							String author=list.get(i).getAuthor();
//							if(author!=null){
//								String[] ss=author.split(",");
//								if(ss!=null){
//									if(ss.length>0){
//										List<String> authorList=Arrays.asList(ss);
//										if(authorList.contains(expertName)){
//											resultList.add(list.get(i));
//										}
//									}
//								}
//								
//							}
//						}
//					}
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
								uploadFiles=pdfPath+pdfName;
								image=uf.substring(uf.indexOf("/pdf")+6, uf.length());
							}
							acc.setUploadFiles(uploadFiles);
							//acc.setImage(image);
							List<KnowledgeThumbsUp> thumbsUpList=knowledgeThumbsUpService.getAll(jour.getKnowledgeAcc().getKnowledgeAccId());
							
							if(thumbsUpList!=null&&thumbsUpList.size()>0){
								countThumbs=thumbsUpList.size();
								for(int i=0;i<thumbsUpList.size();i++){
									if(sysUser.getUserId()-thumbsUpList.get(i).getSysUser().getUserId()==0){
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
					if(list.size()<count){
						res.setErrCode(0);
						res.setResultData(list);
					}else{
						list=list.subList(0, count);
						res.setErrCode(0);
						res.setResultData(list);
					}
		} catch (Exception e) {
			res.setErrCode(1);
		}
		return res;
	}
	
	
	
	@RequestMapping(value = "/getFistImages", method = RequestMethod.GET)
	public ResultVo getFistImages() {
		ResultVo res=new ResultVo();
		List<String> list=new ArrayList<String>();
		list.add(appImagePath+"first.png");
		list.add(appImagePath+"second.png");
		list.add(appImagePath+"third.png");
		Map<String, List<String>> map=new HashMap<String, List<String>>();
		map.put("imagePath", list);
		res.setErrCode(0);
		res.setErrMsg("查询成功！");
		res.setResultData(map);
		return res;
	}

	
	
	@RequestMapping(value = "/expertById/{id}", method = RequestMethod.GET)
	public ResultVo getExpert(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Map<String, Object> expert=journalArticleService.getExpertById(id);
			SysUser user=sysUserService.getByExpertId(id);
			expert.put("userId", user.getUserId());
			expert.put("agencyId", user.getSysSigningAgency().getAgencyId());
			res.setErrCode(0);
			res.setResultData(expert);
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("查询报错"+e.getMessage());
		}
		return res;
	}
	
	
	@RequestMapping(value = "/expertGetById/{id}", method = RequestMethod.GET)
	public ResultVo getExpertById(@PathVariable("id") Integer id) {
		ResultVo res=new ResultVo();
		try {
			Map<String, Object> result=new HashMap<String, Object>();
			SysUser userExpert=sysUserService.getExpertUser(id);
			Map<String, Object> expert=journalArticleService.getExpertById(id);
			String url="";
			if(userExpert!=null&&userExpert.getUserImage()!=null){
				url=userImage+userExpert.getUserImage();
			}else if(expert.get("img_url")!=null){
				url=imgPath+expert.get("img_url");
			}
			expert.put("img_url", url);
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer userId = sysUser.getUserId();
			String roleName=sysUser.getSysRole().getRoleName();
			int angency=sysUser.getSysSigningAgency().getAgencyId();
			//List<JournalArticle> list=new ArrayList<JournalArticle>();
			List<JournalArticle> resList=new ArrayList<JournalArticle>();
			List<JournalArticle> list=new ArrayList<JournalArticle>();
			///list = journalArticleService.getAll(userId,roleName);
			Object experName=expert.get("expert_name_ch")==null?expert.get("expert_name_en"):expert.get("expert_name_ch");
			String name=String.valueOf(experName);
			resList= journalArticleService.getAllByExpert(userId,roleName,name,angency);
			if(resList.size()>10){
				list=resList.subList(0, 10);
			}else{
				list=resList;
			}
			result.put("expert", expert);
			result.put("list", list);
			result.put("size", resList.size());
			res.setErrCode(0);
			res.setResultData(result);
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("查询报错"+e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "/expertGetById/{page}/{pageSize}/{id}/{keyword}", method = RequestMethod.GET)
	public ResultVo getExpertById(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,@PathVariable("id") Integer id,@PathVariable("keyword") String keyword) {
		ResultVo res=new ResultVo();
		try {
			Map<String, Object> expert=journalArticleService.getExpertById(id);
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer userId = sysUser.getUserId();
			String roleName=sysUser.getSysRole().getRoleName();
			int angency=sysUser.getSysSigningAgency().getAgencyId();
			//List<JournalArticle> list=new ArrayList<JournalArticle>();
			if (page != null && pageSize != null) {
				PageHelper.startPage(page, pageSize);
			}
			List<JournalArticle> resList=new ArrayList<JournalArticle>();
			///list = journalArticleService.getAll(userId,roleName);
			Object experName=expert.get("expert_name_ch")==null?expert.get("expert_name_en"):expert.get("expert_name_ch");
			String name=String.valueOf(experName);
			if(keyword == null ||  "".equals(keyword)
					|| "undefined".equals(keyword)){
				resList= journalArticleService.getAllByExpert(userId,roleName,name,angency);
			}else{
				resList= journalArticleService.getAllByExpertWord(userId,roleName,name,keyword,angency);
			}
			
			res.setErrCode(0);
			res.setResultData(new PageInfo(resList));
		} catch (Exception e) {
			res.setErrCode(1);
			res.setErrMsg("查询报错"+e.getMessage());
		}
		return res;
	}
}
