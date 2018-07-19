package com.labwinner.controller;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Approvel;
import com.labwinner.domain.Calendars;
import com.labwinner.domain.Device;
import com.labwinner.domain.Inventory;
import com.labwinner.domain.MediaResource;
import com.labwinner.domain.ProjectBasicInfo;
import com.labwinner.domain.ProjectMoments;
import com.labwinner.domain.ProjectPlan;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.domain.SignIn;
import com.labwinner.domain.SysAttachment;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.domain.TeamMoments;
import com.labwinner.domain.TeamMomentsImage;
import com.labwinner.service.ApprovelService;
import com.labwinner.service.CalendarsService;
import com.labwinner.service.DeviceService;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.JournalArticleService;
import com.labwinner.service.MediaResourceService;
import com.labwinner.service.ProjectBasicInfoService;
import com.labwinner.service.ProjectMomentsService;
import com.labwinner.service.ProjectPlanService;
import com.labwinner.service.ReactionProcessService;
import com.labwinner.service.ReactionService;
import com.labwinner.service.SignInService;
import com.labwinner.service.SysAttachmentService;
import com.labwinner.service.SysSigningAgencyService;
import com.labwinner.service.SysUserService;
import com.labwinner.service.TeamMomentsService;
import com.labwinner.vo.HomePageProVo;
import com.labwinner.vo.HomePageReacVo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description 附件Controller
 * @author llwang
 * @version V1.0
 * @date 2017年7月21日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class HomePageController {
	
	private static Logger logger = LoggerFactory
			.getLogger(HomePageController.class);
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ProjectBasicInfoService projectBasicInfoService;
	@Autowired
	private ProjectPlanService projectPlanService;
	
	@Autowired
	private ReactionService reactionService;
	
	@Autowired
	private ReactionProcessService reactionProcessService;
	
	@Autowired
	private JournalArticleService journalArticleService;
	@Autowired
	private  DeviceService deviceService;
	
	@Autowired
	private InventoriesService inventoryService;
	
	
	@Autowired
	private SignInService signInService;
	
	@Autowired
	private MediaResourceService mediaResourceService;
	
	@Autowired
	private CalendarsService calendarsService;
	
	@Autowired
	private ProjectMomentsService projectMomentsService;
	
	@Autowired
	private TeamMomentsService teamMomentsService;
	
	@Autowired
	private ApprovelService approvelService;
	
	@Autowired
	private SysSigningAgencyService sysSigningAgencyService;

	
	@Value("${note.url-path}")
	String urlPath;
	
	@Value("${sysUserPhone.url-path}")
	String userImageUrl;
	@Value("${web.url_path_appImage}")
	String newsImage;
	@Value("${common.user-image}")
	private String commonImageUrl;
	
	@Value("${moments.url-path}")
	String momentsImage;
	
	/**
	 * @Description 获取所有对象列表
	 * @author llwang
	 * @version V1.0
	 * @date 2017年7月21日 上午11:49:52
	 */

	@RequestMapping(value = "/HomePage/first", method = RequestMethod.GET)
	public ResultVo getAll() {
		ResultVo resultVo = new ResultVo();
		Map<String, Object> res=new HashMap<String, Object>();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysSigningAgency sysSigningAgency = sysSigningAgencyService.getById(Long.valueOf(sysUser.getSysSigningAgency().getAgencyId()));
		int proNum=0;
		int reacNum=0;
		int knowNum=0;
		int singinCount = 0;
		String center="";
		Integer userId = sysUser.getUserId();
		Integer roleId=sysUser.getSysRole().getRoleId();
		String roleName=sysUser.getSysRole().getRoleName();
		List<Reaction> list=new ArrayList<Reaction>();
		if (!roleName.equals("ROLE_TEAM")) {
			list= reactionService.getUserList(userId);
		}else{
			list=reactionService.getAll();
		}
		reacNum=list.size();
		 List<ProjectBasicInfo> projectBasicInfos = projectBasicInfoService.getAll(userId, roleName);
		 if(projectBasicInfos!=null&&projectBasicInfos.size()>0){
			 proNum=projectBasicInfos.size();
		 }
		try {
			//------------项目和实验-------------------------------------------------
			List<HomePageProVo> projectListLs=new ArrayList<HomePageProVo>();
			projectListLs=projectBasicInfoService.getProAndReacALL(userId, roleName);
			List<HomePageProVo> projectList=new ArrayList<HomePageProVo>();
			if(projectListLs!=null&&projectListLs.size()>0){
				for(int i=0;i<projectListLs.size();i++){
					if(projectListLs.get(i).getProNum()>0){
						int proId=projectListLs.get(i).getProId();
							List<ProjectPlan> projectPlans=new ArrayList<ProjectPlan>();
							projectPlans=projectPlanService.getByProId(proId);
							Map<String, Integer> plansNum=new HashMap<String, Integer>();
							int start=0;
							int reStart=0;
							int over=0;
							int stop=0;
							if(projectPlans.size()>0){
								for(int k=0;k<projectPlans.size();k++){
									if("未开始".equals(projectPlans.get(k).getProjectSchedule().getProScheduleName())){
										reStart++;
									}
									if("进行中".equals(projectPlans.get(k).getProjectSchedule().getProScheduleName())){
										start++;
									}
									if("已完成".equals(projectPlans.get(k).getProjectSchedule().getProScheduleName())){
										over++;
									}
									if("已终止".equals(projectPlans.get(k).getProjectSchedule().getProScheduleName())){
										stop++;
									}
								}
							}
							plansNum.put("start", start);
							plansNum.put("reStart", reStart);
							plansNum.put("over", over);
							plansNum.put("stop", stop);
							projectListLs.get(i).setPlansNum(plansNum);
							projectListLs.get(i).setProjectPlans(projectPlans);

							projectList.add(projectListLs.get(i));
					}
				}		

			}

			//-----------------------知识展示--------------------------------------
			List<Map<String, Object>> listKnow=new ArrayList<Map<String,Object>>();
			listKnow=journalArticleService.getKnowledge(roleName,userId);
			List<Map<String, Object>> listKnowledge=new ArrayList<Map<String,Object>>();
			int count=0;
			int count1=0;
			int count2=0;
			if(listKnow!=null&&listKnow.size()>0){
			for(int i=0;i<listKnow.size();i++){
					if("1".equals(String.valueOf(listKnow.get(i).get("classify")))){
						count++;
						if(count<3){
							listKnowledge.add(listKnow.get(i));
						}
					}
					if("2".equals(String.valueOf(listKnow.get(i).get("classify")))){
						count1++;
						if(count1<3){
							listKnowledge.add(listKnow.get(i));
						}
					}
					if("4".equals(String.valueOf(listKnow.get(i).get("classify")))){
						count2++;
						if(count2<2){
							listKnowledge.add(listKnow.get(i));
						}
					}
				}
			if(listKnowledge.size()<5){
				if(listKnow.size()>=5){
					listKnowledge=listKnow.subList(0, 5);
				}else{
					listKnowledge=listKnow;
				}
			}
				knowNum=listKnow.size();
			}
			res.put("knowledge", listKnowledge);
			//------------------------设备总数-------------------------------------

			 int deviceNum=journalArticleService.getDeviceAppointment(roleName,userId);
			 
			 //-----------------------库存总数-------------------------------------
			 int inventoryNum=inventoryService.getInventoryNum(roleName,userId);
			
			 
			 //-----------------------采购总数----------------------------------------
			 int teamNum=journalArticleService.getTeamNum(roleName,userId);
			 //-----------------------签到信息---------------------------------------
			 List<SignIn>  listSign=new ArrayList<SignIn>();
			 listSign=signInService.getSignForFirt(roleName,userId);
			 
			 
			 Date date = new Date();
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
			 String nowDate = dateFormat.format(date);
			 
			 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			 String sysDate = sdf.format(date);
			 String sysSignTime = sysDate+" "+sysSigningAgency.getSysSignTime();
			 
			 if(listSign!=null&&listSign.size()>0){
				 for(int i=0;i<listSign.size();i++){
					 SignIn signIn = listSign.get(i);
					Integer flag = isLate(sysSignTime,signIn);
					signIn.setFlag(flag);
						
					 String fileName = listSign.get(i).getSysUser().getUserImage();
					 if(fileName!=null&&!"".equals(fileName)){
						 fileName=userImageUrl+fileName;
					 }else{
						 fileName=commonImageUrl+"morentouxiang.png";
					 }
					 listSign.get(i).getSysUser().setUserImagePath(fileName);
				 }
			 }
			 
			List<SignIn> teamList = signInService.getAllByDay(nowDate);
			if(teamList!=null && teamList.size()>0){
				singinCount = teamList.size();
			}
			
			 
			
			 //-----------------------新闻信息------------------------------------------
			 List<MediaResource> newsList =new ArrayList<MediaResource>();
			 newsList= mediaResourceService.getFirst();
			 
			 List<MediaResource> newsLists=new ArrayList<MediaResource>();
			 int con=0;
			 if(newsList!=null&&newsList.size()>0){
				 int newSize=newsList.size();
				 if(newsList.size()>=5){
					 for(int i=0;i<5;i++){
						 newsLists.add(newsList.get(i));
					 } 
				 }else{
					 newsLists=newsList;
					for(int i=0;i<5;i++){
						con++;
						if(newSize+con<=5){
							 MediaResource media=new MediaResource(); 
							 media.setTitle("《测绘学报》荣获“百种中国杰出学术期刊”称号");
							 media.setUrl("http://www.china-k.net/qikan/xueshu/xueshudongtai/20160407/2386.html");
							 newsLists.add(media);
						}
					}
					 
				 }
				
			 } else{
				 for(int i=0;i<5;i++){
							 MediaResource media=new MediaResource(); 
							 media.setTitle("《测绘学报》荣获“百种中国杰出学术期刊”称号");
							 media.setUrl("http://www.china-k.net/qikan/xueshu/xueshudongtai/20160407/2386.html");
							 newsLists.add(media);
						}
			 }
			 
			 
			
			 //------------------------签约机构的经纬度----------------------------------
			 center=journalArticleService.getCrnter(userId);
			 String centerLong="";
				String centerLati="";
			 if(center!=null&&center.indexOf(",")>0){
				 String[] ss=center.split(",");
				 if(ss.length>1){
					centerLong=center.split(",")[1];
					centerLati=center.split(",")[0];
				 }else{
					 centerLong="";
					centerLati="";
				 } 
			 }
			//----------------------------日历日程----------------------------------------
				List<Calendars> listCal=new ArrayList<Calendars>();
				listCal=calendarsService.getOneDay(sysUser.getUserId());
				if(listCal!=null&&listCal.size()>0){
					for(int i=0;i<listCal.size();i++){
						List<Integer>  listUser=new ArrayList<Integer>();
						List<String> className=new ArrayList<String>();
						String assistPeople=listCal.get(i).getAssistPeople();
						String class_=listCal.get(i).getClass_();
						if(assistPeople!=null&&!"".equals(assistPeople)){
							String[] ss=assistPeople.split(",");
							if(ss!=null&&ss.length>0){
								for(int k=0;k<ss.length;k++){
									listUser.add(Integer.valueOf(ss[k]));
								}
							}else{
								listUser=null;
							}
							
						}else{
							listUser=null;
						}
						if(class_!=null&&class_.length()>0){
							String[] ss=class_.split(",");
							if(ss!=null&&ss.length>0){
								for(int k=0;k<ss.length;k++){
									className.add(ss[k]);
								}
							}
						}
						listCal.get(i).setListUser(listUser);
						listCal.get(i).setClassName(className);
					}
				}
				//----------------------------采购信息-------------------------------------
				List<Map<String, Object>> approvelList = new ArrayList<Map<String, Object>>();
				approvelList=approvelService.getAllForFirst(roleName,sysUser.getUserId());
				
				if(approvelList!=null&&approvelList.size()>0){
					if(approvelList.size()>5){
						approvelList=approvelList.subList(0, 5);
					}
					for(int i=0;i<approvelList.size();i++){
						if((int)approvelList.get(i).get("product_type_id1")-1==0){
							String price=approvelList.get(i).get("material_price")==null?"":(String)approvelList.get(i).get("material_price").toString();
							String cur_name=(String)approvelList.get(i).get("curName")==null?"":(String)approvelList.get(i).get("curName");
							String measure_unit=(String)approvelList.get(i).get("measure_unit1")==null?"":(String)approvelList.get(i).get("measure_unit1");
							String univalent=price+	cur_name+"/"+measure_unit;
							approvelList.get(i).put("univalent", univalent);
						}else if((int)approvelList.get(i).get("product_type_id1")-2==0){
							String price=approvelList.get(i).get("material_price")==null?"":(String)approvelList.get(i).get("material_price").toString();
							String cur_name=(String)approvelList.get(i).get("curName")==null?"":(String)approvelList.get(i).get("curName");
							String package_type_name1=(String)approvelList.get(i).get("package_type_name1")==null?"":(String)approvelList.get(i).get("package_type_name1");
							String univalent=price+cur_name+"/"+package_type_name1;
							approvelList.get(i).put("univalent", univalent);
						}
					}
					
				}
				//----------------------------项目圈----------------------------------------
				List<ProjectMoments> projectMoments = new ArrayList<ProjectMoments>();
				projectMoments=projectMomentsService.getAll(userId,roleName,0,5);
				if(projectMoments!=null&&projectMoments.size()>6){
					projectMoments=projectMoments.subList(0, 6);
				}
				if(projectMoments!=null&&projectMoments.size()>0){
					for(int i=0;i<projectMoments.size();i++){
						String userImage=projectMoments.get(i).getSysUser().getUserImage();
						if(!"".equals(userImage)&&userImage!=null){
							projectMoments.get(i).getSysUser().setUserImage(userImageUrl+userImage);
						}else{
							projectMoments.get(i).getSysUser().setUserImage(commonImageUrl+"morentouxiang.png");
						}
					}
				}
//
//				//-------------------------------知识no1------------------------
			List<Map<String, Object>> upKnowledge=journalArticleService.getUpKnowledge();
			String knowledgeUser="";
			if(upKnowledge!=null&&upKnowledge.size()>0){
				knowledgeUser=String.valueOf(upKnowledge.get(0).get("realname"));
			}
//			//-------------------------------实验no1-----------------------------
			List<Map<String, Object>> upProject=journalArticleService.getUpProject();
			String reactionUser="";
			if(upProject!=null&&upProject.size()>0){
				reactionUser=String.valueOf(upProject.get(0).get("realname"));
			}
			//----------------------------------分享no1-----------------------------------------------------------------
			String shareUser=journalArticleService.getShare();
			if(shareUser==null){
				
			}
			//----------------------------------------------------------赋值--------------------------------------------------
			res.put("reactionUser", reactionUser);//实验NO1
			res.put("knowledgeUser", knowledgeUser);//知识NO1
			res.put("newsList", newsLists);//热点新闻
			res.put("listSign", listSign);//签到列表
			res.put("singinCount", singinCount);
			res.put("proAndReac",projectList);//项目和实验
			res.put("proNum",proNum);		//项目总数
			res.put("reacNum",reacNum);		//实验总数
			res.put("knowNum",knowNum);		//知识总数
			res.put("inventoryNum", inventoryNum);//库存总数
			res.put("teamNum", teamNum);		//团队个数
			res.put("deviceNum", deviceNum);   //预约总数
			res.put("centerLong", centerLong);//签约机构的经度
			res.put("centerLati", centerLati);//签约机构的纬度
			res.put("calendars", listCal);
			res.put("proMonList", projectMoments);
			res.put("shareUser", shareUser);
			res.put("approvelList", approvelList);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(res);
		} catch (Exception e) {
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("错误信息"+e.getMessage());
			logger.error(e.getMessage());
		}
		
		return resultVo;
	}
	
	
	@RequestMapping(value = "/HomePage/getNews", method = RequestMethod.GET)
	public ResultVo getNewsSecond() {
		ResultVo res=new ResultVo();
		try {
			List<MediaResource> newsList = mediaResourceService.getAllByDay();
			if(newsList!=null&&newsList.size()>0){
				for(MediaResource mediaResource:newsList){
					if(mediaResource.getImageUrl()==null||"".equals(mediaResource.getImageUrl())){
						mediaResource.setImageUrl(newsImage+"web_media.png");
					 }
					if(mediaResource.getSource()-0==0){
						SysUser sysuser=new SysUser();
						sysuser.setRealname("Labwinner");
						mediaResource.setSysUser(sysuser);
					}
				}
			}
			 res.setErrCode(0);
			 res.setErrMsg("find success!");
			 res.setResultData(newsList);
		} catch (Exception e) {
			e.printStackTrace();
			 res.setErrCode(1);
			 res.setErrMsg("find loss!"+e.getMessage());
		}
		return res;
		
	}
	
	
	@RequestMapping(value = "/HomePage/getProjectMoments", method = RequestMethod.GET)
	public ResultVo getProjectMomentsSecond() {
		ResultVo res=new ResultVo();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer userId = sysUser.getUserId();
			 List<ProjectMoments> momentsList = projectMomentsService.getAllByDay();
			 List<Map<String,Object>> resMap=new ArrayList<Map<String,Object>>();
			 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			 long day=0;
			 Date today=new Date();
			List<Long> dayList=new ArrayList<Long>();
			if(momentsList!=null&&momentsList.size()>0){
				 for(int i=0;i<momentsList.size();i++){
					 day=(today.getTime()-momentsList.get(i).getPublishTime().getTime())/(24*60*60*1000);  
					 dayList.add(day);
					 if(momentsList.get(i).getTeamMomentsImages().size()>0){
						 for(int j=0;j<momentsList.get(i).getTeamMomentsImages().size();j++){
							 momentsList.get(i).getTeamMomentsImages().get(j).setImage(momentsImage+momentsList.get(i).getTeamMomentsImages().get(j).getImage());
						 }
					 }
					 String fileName = momentsList.get(i).getSysUser().getUserImage();
					 if(fileName!=null&&!"".equals(fileName)){
						 fileName=userImageUrl+fileName;
					 }else{
						 fileName=commonImageUrl+"morentouxiang.png";
					 }
					 momentsList.get(i).getSysUser().setUserImage(fileName);
				 }
				 List<Long> tempList= new ArrayList<Long>();  
				    for(Long i:dayList){  
				        if(!tempList.contains(i)){  
				            tempList.add(i);  
				        }  
				    }  
				    for(int k=0;k<tempList.size();k++){
				    	 Map<String,Object> mapList=new HashMap<String, Object>();
				    	 String newDay="";
				    	 List<ProjectMoments> listPm=new ArrayList<ProjectMoments>();
				    	 for(int i=0;i<momentsList.size();i++){
				    		 day=(today.getTime()-momentsList.get(i).getPublishTime().getTime())/(24*60*60*1000); 
							if(day-tempList.get(k)==0L){
								newDay=new SimpleDateFormat("yyyy-MM-dd").format(momentsList.get(i).getPublishTime());
								listPm.add(momentsList.get(i));
							}
						 }
				    	 mapList.put("Time", newDay);
				    	 mapList.put("newsDay", listPm);
				    	 resMap.add(mapList);
				    }
			}
			 res.setErrCode(0);
			 res.setErrMsg("find success!");
			 res.setResultData(resMap);
		} catch (Exception e) {
			 res.setErrCode(1);
			 res.setErrMsg("find loss!"+e.getMessage());
		}
		return res;
		
	}
	
	@RequestMapping(value = "/HomePage/getReaction/{proId}/{number}", method = RequestMethod.GET)
	public ResultVo getReaction(@PathVariable("proId") Integer proId,@PathVariable("number") Integer number) {
		ResultVo res=new ResultVo();
		//Map<String, Object> resMap=new HashMap<String, Object>();
		try {
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			int userId=sysUser.getUserId();
			String roleName=sysUser.getSysRole().getRoleName();
			List<String> proRole=journalArticleService.getProRole(proId,sysUser.getUserId());
			String proRoleName="";
			if(proRole!=null&&proRole.size()>0){
				proRoleName=proRole.get(0);
			}
//			HomePageProVo homePageProVo=new HomePageProVo();
//			List<HomePageProVo> projectListLs=new ArrayList<HomePageProVo>();
//			projectListLs=projectBasicInfoService.getProAndReacByNum(userId, roleName,proId);
			//projectListLs.add(homePageProVo);
			List<HomePageReacVo> homePageReac=new ArrayList<HomePageReacVo>();
			homePageReac=reactionService.getReactionByNumber(proId,userId,roleName,proRoleName,number-1);
			if(homePageReac!=null&&homePageReac.size()>0){
				for(int j=0;j<homePageReac.size();j++){
					int reactionId=homePageReac.get(j).getReactionId();
					List<ReactionProcess> rs=reactionProcessService.getProcessByReactionId(reactionId);
					homePageReac.get(j).setReactionProcess(rs);
				}
				
//				projectListLs.get(0).setHomePageReacVo(homePageReac);
//				List<ProjectPlan> projectPlans=new ArrayList<ProjectPlan>();
//				projectPlans=projectPlanService.getByProId(proId);
//				Map<String, Integer> plansNum=new HashMap<String, Integer>();
//				int start=0;
//				int reStart=0;
//				int over=0;
//				int stop=0;
//				if(projectPlans.size()>0){
//					for(int k=0;k<projectPlans.size();k++){
//						if("未开始".equals(projectPlans.get(k).getProjectSchedule().getProScheduleName())){
//							reStart++;
//						}
//						if("进行中".equals(projectPlans.get(k).getProjectSchedule().getProScheduleName())){
//							start++;
//						}
//						if("已完成".equals(projectPlans.get(k).getProjectSchedule().getProScheduleName())){
//							over++;
//						}
//						if("已终止".equals(projectPlans.get(k).getProjectSchedule().getProScheduleName())){
//							stop++;
//						}
//					}
//				}
//				plansNum.put("start", start);
//				plansNum.put("reStart", reStart);
//				plansNum.put("over", over);
//				plansNum.put("stop", stop);
//				projectListLs.get(0).setPlansNum(plansNum);
//				projectListLs.get(0).setProjectPlans(projectPlans);
			}
			//resMap.put("proAndReac", projectListLs);
			 res.setErrCode(0);
			 res.setErrMsg("find success!");
			 res.setResultData(homePageReac);
		} catch (Exception e) {
			 res.setErrCode(1);
			 res.setErrMsg("find loss!"+e.getMessage());
		}
		return res;
		
	}
	
	
public Integer isLate(String date,SignIn signIn){
		
		// 设置传入的时间格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		Date sysSignInDate = null;
		try {
			sysSignInDate = dateFormat.parse(date);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		long a = signIn.getSignDate().getTime()-sysSignInDate.getTime();
		if(a<0){
			return 0;
		}else{
			return 1;
		}
		
	}
	
	
  
}
