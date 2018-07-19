package com.labwinner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.ExecuteChemical;
import com.labwinner.domain.ExecuteChemicalGroup;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.Prototype;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.ReactionDevice;
import com.labwinner.domain.ReactionProcess;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.service.DeviceLocationService;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.service.KnowledgeAccService;
import com.labwinner.service.ReactionProcessService;
import com.labwinner.service.ReactionService;
import com.labwinner.service.SysSigningAgencyService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.FileUtil;
import com.labwinner.util.Html2ImageUtil;
import com.labwinner.util.HtmlUtil;
import com.labwinner.util.ImageToPdf;
import com.labwinner.util.RichHtmlHandler;
import com.labwinner.util.WordGeneratorWithFreemarker;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description 采购审批Controller
 * @author llwang
 * @version V1.0
 * @date 2017年8月17日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class LabReportController {

	@Autowired
	private ReactionService reactionService;
	@Autowired
	private KnowledgeAccService knowledgeAccDaoService;
	@Autowired
	private InventoryLocationService inventoryLocService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysSigningAgencyService sysSigningAgencyService;
	@Autowired
	private DeviceLocationService deviceLocationService;
	
	@Autowired
	private ReactionProcessService reactionProcessService;
	
	@Value("${web.qrUrl-path}")
	private String urlPath;
	@Value("${web.url_path_report}")
	private String reportPath;
	@Value("${web.upload_path_report}")
	private String uploadReport;
	
	@Value("${web.msgFile-path}")
	private String msgFile;
	@Value("${web.msgFileUrl-path}")
	private String imageFile;


	/**
	 * @Description 获取所有对象
	 * @author llwang
	 * @version V1.0
	 * @throws Exception 
	 * @date 2017年6月8日
	 */
	@RequestMapping(value = "/createWord/{id}", method = RequestMethod.GET)
	public ResultVo getAll(@PathVariable("id") Integer id) throws Exception {
		ResultVo resultVo = new ResultVo();
	    String template = "wordtest.ftl";  //模板文件的地址  2016word版
	    LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
	    String fileName="report"+sysUser.getSysSigningAgency().getAgencyId()+"_"+id+".doc";
	    createDir(uploadReport);
	    String docFilePath = uploadReport+fileName;//生成的word文档的输出地址  
	    String lastPath=reportPath+fileName;
	    //try {
			Reaction reaction = reactionService.getByReactionId(id);
			List<Integer> reactionProcessesIds =reactionProcessService.getByReactionId(id);
			List<ReactionProcess> reactionProcesses=new ArrayList<ReactionProcess>();
			if(reactionProcessesIds!=null&&reactionProcessesIds.size()>0){
				for(Integer ProcessesId:reactionProcessesIds){
					ReactionProcess rp=reactionProcessService.getByProcessId(ProcessesId);
					reactionProcesses.add(rp);
				}
			}
			reaction.setReactionProcesses(reactionProcesses);
			List<Map<String,Object>> knowledges = reactionService.getArticleById(id);
			if(knowledges !=null && knowledges.size()>0){
				for (Map<String, Object> map : knowledges) {
					Integer knowledgeId =(Integer)map.get("knowledgeId");
					Integer classifyId = (Integer)map.get("knowledge_classify_id");
					KnowledgeAcc knowledgeAcc = knowledgeAccDaoService.getKnowledgeAcc(knowledgeId, classifyId);
					if(knowledgeAcc !=null){
						String uploadFiles = knowledgeAcc.getUploadFiles();
						Integer knowledgeAccId = knowledgeAcc.getKnowledgeAccId();
						Integer pdfCount = knowledgeAcc.getPdfCount();
						map.put("filename", uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));
						map.put("filePath", uploadFiles);
						map.put("knowledgeAccId", knowledgeAccId);
						map.put("pdfCount", pdfCount);
					}
				}
			}
			if(reaction!=null){
				//List<ReactionProcess> reactionProcesses = reaction.getReactionProcesses();
				if(reactionProcesses!=null && reactionProcesses.size()>0){
					for (ReactionProcess reactionProcess : reactionProcesses) {
						
//						List<ExecuteChemicalGroup> executeChemicalGroup = reactionProcess.getExecuteChemicalGroups();
//						if(executeChemicalGroup!=null && executeChemicalGroup.size()>0){
//							for (ExecuteChemicalGroup executeChemical : executeChemicalGroup) {
//								//InventoryLocation location = executeChemical.getInventory().getInventoryLocation();
//								if(location!=null && location.getPid()!=0){
//									String pname = getInventoryPname(location.getCid());
//									location.setParentName(pname);
//								}
//							}
//						}
						
						List<ReactionDevice> testDevices = reactionProcess.getReactionDevices();
						
						if(testDevices!=null && testDevices.size()>0){
							for (ReactionDevice testDevice : testDevices) {
								DeviceLocation location = testDevice.getDevice().getDeviceLocation();
								if(location!=null && !"0".equals(location.getDeviceLocaPid())){
									String pname = getDevicePname(location.getDeviceLocaId());
									location.setParentName(pname);
								}
							}
						}
						
						List<Prototype> protoTypes = reactionProcess.getPrototypes();
						if(protoTypes!=null && protoTypes.size()>0){
							for (Prototype prototype : protoTypes) {
								prototype.setQrName(urlPath+prototype.getQrName());
								InventoryLocation location = prototype.getInventoryLocation();
								if(location!=null && location.getPid()!=0){
									String pname = getInventoryPname(location.getCid());
									location.setParentName(pname);
								}
							}
						}
					}
				}
				
				Map<String, Object> root = initData(knowledges,reaction);  //数据源对象  \
			        System.out.println(docFilePath);
			        File f = new File(docFilePath);
					if (!f.getParentFile().exists()) {
						f.getParentFile().mkdirs();
					}
			        OutputStream out;
//			        try {
			        
			            out = new FileOutputStream(f);
			            WordGeneratorWithFreemarker.createDoc(root, template, out);
//			        } catch (FileNotFoundException e) {
//
//			        } catch (MalformedTemplateNameException e) {
//			            e.printStackTrace();
//			        } catch (ParseException e) {
//			            e.printStackTrace();
//			        } catch (IOException e) {
//			            e.printStackTrace();
//			        }
			        
				resultVo.setErrCode(0);
				resultVo.setResultData(lastPath);
				FileUtil fileUtil = new FileUtil();
				String size=fileUtil.getFileSize(uploadReport+fileName);
				resultVo.setErrMsg(size+","+fileName);
			}
		//} catch (Exception e) {
//			resultVo.setErrCode(1);
//			resultVo.setErrMsg("create fail");
//		}
		return resultVo;
	}
	
	// 创建目录
			public static boolean createDir(String destDirName) {
				File dir = new File(destDirName);
				if (dir.exists()) {// 判断目录是否存在
					System.out.println("创建目录失败，目标目录已存在！");
					return false;
				}
				if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
					destDirName = destDirName + File.separator;
				}
				if (dir.mkdirs()) {// 创建目标目录
					System.out.println("创建目录成功！" + destDirName);
					return true;
				} else {
					System.out.println("创建目录失败！");
					return false;
				}
			}

	 public String getInventoryPname( Integer id) {
		    try {
		      String ss="";
		    loop:for(int i=0;i<10;i++){
		      InventoryLocation inventoryLocation =  inventoryLocService.getById(id);
		      if(inventoryLocation.getPid()!=null&&inventoryLocation.getPid()!=0){
		        if(i==0){
		          ss="";
		        }else if(i==1){
		          ss=inventoryLocation.getLabel();
		        }else{
		          ss=inventoryLocation.getLabel()+">"+ss;
		        }
		        id=inventoryLocation.getPid();
		      }else{
		        if(!"".equals(ss)){
		          ss=inventoryLocation.getLabel()+">"+ss;
		        }else{
		          ss=inventoryLocation.getLabel();  
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

	 
	  public String getDevicePname( Integer id) {
		    try {
		      String ss="";
		    loop:for(int i=0;i<10;i++){
		      DeviceLocation deviceLocation =  deviceLocationService.getById(id);
		      if(deviceLocation.getDeviceLocaPid()!=null&&!"0".equals(deviceLocation.getDeviceLocaPid())){
		        if(i==0){
		          ss="";
		        }else if(i==1){
		          ss=deviceLocation.getLabel();
		        }else{
		          ss=deviceLocation.getLabel()+">"+ss;
		        }
		        id= Integer.valueOf(deviceLocation.getDeviceLocaPid());
		      }else{
		        if(!"".equals(ss)){
		          ss=deviceLocation.getLabel()+">"+ss;
		        }else{
		          ss=deviceLocation.getLabel();  
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
	  
	  private   Map<String, Object> initData(List<Map<String, Object>> knowledges, Reaction reaction) throws Exception {  
		  LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			SysSigningAgency sysSigningAgency = sysSigningAgencyService.getById( Integer.valueOf(sysUser.getSysSigningAgency().getAgencyId()).longValue());
		    Map<String, Object> root = new HashMap<String, Object>(); 
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		    //一、	试验原料表
		    List<Map<String, Object>> chemicalList=new ArrayList<Map<String,Object>>();
		    
		    List<Map<String, Object>> solutionList=new ArrayList<Map<String,Object>>();
		   
		    List<Map<String, Object>> processList=new ArrayList<Map<String,Object>>();
		    List<Map<String, Object>> recordList=new ArrayList<Map<String,Object>>();
		    if(reaction.getReactionProcesses()!=null&&reaction.getReactionProcesses().size()>0){
		    	int count1=1;
		    	int countPro=1;
		    	for(int i=0;i<reaction.getReactionProcesses().size();i++){
		    		Map<String, Object> mapPro=new HashMap<String, Object>();
		    		mapPro.put("number", (countPro++)+".");
		    		mapPro.put("name", reaction.getReactionProcesses().get(i).getProcessName());
		    		String start ="";
		    		if(reaction.getReactionProcesses().get(i).getStartTime()!=null){
		    			start=sdf.format(reaction.getReactionProcesses().get(i).getStartTime());
		    		}
		    		mapPro.put("start", start );
		    		String end ="";
		    		if(reaction.getReactionProcesses().get(i).getEndTime()!=null){
		    			end=sdf.format(reaction.getReactionProcesses().get(i).getEndTime());
		    		}
		    		
		    		mapPro.put("end", end );
//		    		mapPro.put("start", sdf.format(reaction.getReactionProcesses().get(i).getStartTime()) );
//		    		mapPro.put("end", sdf.format(reaction.getReactionProcesses().get(i).getStartTime()) );
		    		mapPro.put("status", reaction.getReactionProcesses().get(i).getReactionStatus().getReactionStatus() );
		    		mapPro.put("remark", reaction.getReactionProcesses().get(i).getRemark());
		    		if(reaction.getReactionProcesses().get(i).getExecuteChemicalGroups()!=null&&reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().size()>0){
		    			for(int j=0;j<reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().size();j++){
		    				Map<String, Object> map=new HashMap<String, Object>();
				    	   	 map.put("number", (count1++)+".");
				    	  	 String count="";
				    	  	if(reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getGroupDosage()!=null){
				    	   		count=count+reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getGroupDosage();
				    	   	 }
				    	   	 if(reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getInventoryGroups()!=null){
				    	   		 map.put("name", reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getInventoryGroups().getInventoryName());
				    	   		 if(reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getInventoryGroups().getMeasurement()!=null){
				    	   			count=count+reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getInventoryGroups().getMeasurement().getMeasureUnit();
				    	   		 }
				    	   	 }
				    	 
					    	 //map.put("count", reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getGroupDosage()+reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getInventoryGroups().getMeasurement().getMeasureUnit());
					    	 map.put("processName", reaction.getReactionProcesses().get(i).getProcessName());
//					    	 if(reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation()!=null){
//					    		 int cid=reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getCid();
//					    		 String inventory=getAllPname(cid);
//					    		 inventory=inventory+">"+reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel();
//					    		 map.put("inventory", inventory); 
//					    	 }
//					    	 if(reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation()!=null){
//					    		 map.put("inventory", reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel()==null?"":reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel()); 
//					    	 }
					    	 chemicalList.add(map);
		    			}
		    		}
		    		
		    		if(reaction.getReactionProcesses().get(i).getExecuteSolutions()!=null&&reaction.getReactionProcesses().get(i).getExecuteSolutions().size()>0){
		    			for(int j=0;j<reaction.getReactionProcesses().get(i).getExecuteSolutions().size();j++){
		    				Map<String, Object> map=new HashMap<String, Object>();
				    	   	 map.put("number", (count1++)+".");
				    	   	 if(reaction.getReactionProcesses().get(i).getExecuteSolutions().get(j).getSolution()!=null){
				    	   		map.put("name", reaction.getReactionProcesses().get(i).getExecuteSolutions().get(j).getSolution().getSolutionName());
				    	   	 }
					    	 if(reaction.getReactionProcesses().get(i).getExecuteSolutions().get(j).getMeasurement()!=null){
					    		 map.put("count", reaction.getReactionProcesses().get(i).getExecuteSolutions().get(j).getSolutionDosage()+reaction.getReactionProcesses().get(i).getExecuteSolutions().get(j).getMeasurement().getMeasureUnit());
					    	 }
					    	 map.put("processName", reaction.getReactionProcesses().get(i).getProcessName());
//					    	 if(reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation()!=null){
//					    		 int cid=reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getCid();
//					    		 String inventory=getAllPname(cid);
//					    		 inventory=inventory+">"+reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel();
//					    		 map.put("inventory", inventory); 
//					    	 }
//					    	 if(reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation()!=null){
//					    		 map.put("inventory", reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel()==null?"":reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel()); 
//					    	 }
					    	 solutionList.add(map);
		    			}
		    		}
		    		 List<Map<String, Object>> paramterlList=new ArrayList<Map<String,Object>>();
		    		 Map<String, Object> paramterMap=new HashMap<String, Object>();
		    		 int count2=1;
		    		if(reaction.getReactionProcesses().get(i).getReactionExecuteParameters()!=null&&reaction.getReactionProcesses().get(i).getReactionExecuteParameters().size()>0){
		    			for(int j=0;j<reaction.getReactionProcesses().get(i).getReactionExecuteParameters().size();j++){
		    				Map<String, Object> map=new HashMap<String, Object>();
				    	   	 map.put("number", (count2++)+".");
					    	 map.put("name", reaction.getReactionProcesses().get(i).getReactionExecuteParameters().get(j).getReactionParameter());
					    	 if(reaction.getReactionProcesses().get(i).getReactionExecuteParameters().get(j).getMeasurement()!=null){
					    		 map.put("unit", reaction.getReactionProcesses().get(i).getReactionExecuteParameters().get(j).getMeasurement().getMeasureUnit());
					    	 }
					    	 map.put("count", reaction.getReactionProcesses().get(i).getReactionExecuteParameters().get(j).getExecuteParameterDosage());
					    	 paramterlList.add(map);
		    			}
		    		}
		    		mapPro.put("paramter", paramterlList);
		    		 int count3=1;
		    		 List<Map<String, Object>> devicelList=new ArrayList<Map<String,Object>>();
		    		 Map<String, Object> deviceMap=new HashMap<String, Object>();
		    		if(reaction.getReactionProcesses().get(i).getReactionDevices()!=null&&reaction.getReactionProcesses().get(i).getReactionDevices().size()>0){
		    			for(int j=0;j<reaction.getReactionProcesses().get(i).getReactionDevices().size();j++){
		    				Map<String, Object> map=new HashMap<String, Object>();
				    	   	 map.put("number", (count3++)+".");
					    	 map.put("name", reaction.getReactionProcesses().get(i).getReactionDevices().get(j).getDevice().getDeviceName());
					    	 if(reaction.getReactionProcesses().get(i).getReactionDevices().get(j).getDevice().getDeviceLocation()!=null){
					    		 int cid=reaction.getReactionProcesses().get(i).getReactionDevices().get(j).getDevice().getDeviceLocation().getDeviceLocaId();
						    	 String ss=getAllDevicePname(cid);
						    	 map.put("location", ss+">"+reaction.getReactionProcesses().get(i).getReactionDevices().get(j).getDevice().getDeviceLocation().getLabel());
					    	 }
					    	// map.put("location", reaction.getReactionProcesses().get(i).getReactionDevices().get(j).getDevice().getDeviceLocation().getLabel());
					    	 devicelList.add(map);
		    			}
		    		}
		    		mapPro.put("device", devicelList);
		   		 List<Map<String, Object>> protolList=new ArrayList<Map<String,Object>>();
	    		 Map<String, Object> protoMap=new HashMap<String, Object>();
	    		 int count4=1;
	    		if(reaction.getReactionProcesses().get(i).getPrototypes()!=null&&reaction.getReactionProcesses().get(i).getPrototypes().size()>0){
	    			for(int j=0;j<reaction.getReactionProcesses().get(i).getPrototypes().size();j++){
	    				Map<String, Object> map=new HashMap<String, Object>();
			    	   	 map.put("number", (count4++)+".");
				    	 map.put("name", reaction.getReactionProcesses().get(i).getPrototypes().get(j).getPrototypeName());
				    	 if(reaction.getReactionProcesses().get(i).getPrototypes().get(j).getMeasurement()!=null){
				    		 map.put("dosage", reaction.getReactionProcesses().get(i).getPrototypes().get(j).getPrototypeDosage()+reaction.getReactionProcesses().get(i).getPrototypes().get(j).getMeasurement().getMeasureUnit());
				    	 }
				    	 map.put("count", reaction.getReactionProcesses().get(i).getPrototypes().get(j).getPrototypeNum());
				    	 map.put("barCode", reaction.getReactionProcesses().get(i).getPrototypes().get(j).getBarCode());
				    	 if(reaction.getReactionProcesses().get(i).getPrototypes().get(j).getInventoryLocation()!=null){
				    		 int cid=reaction.getReactionProcesses().get(i).getPrototypes().get(j).getInventoryLocation().getCid();
				    		 String inventory=getAllPname(cid);
				    		 map.put("location", inventory+">"+reaction.getReactionProcesses().get(i).getPrototypes().get(j).getInventoryLocation().getLabel());
				    	 }else{
				    		 map.put("location", ""); 
				    	 }
				    	
				    	 protolList.add(map);
	    			}
	    		}
	    		mapPro.put("proto", protolList);
	    		
//	    		List<Map<String, Object>> testlList=new ArrayList<Map<String,Object>>();
//	    		 Map<String, Object> testMap=new HashMap<String, Object>();
//	    		 int count5=1;
//	    		if(reaction.getReactionProcesses().get(i).getReactionTests()!=null&&reaction.getReactionProcesses().get(i).getReactionTests().size()>0){
//	    			for(int j=0;j<reaction.getReactionProcesses().get(i).getReactionTests().size();j++){
//	    				Map<String, Object> map=new HashMap<String, Object>();
//			    	   	if(reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestName()!=null){
//			    	   		map.put("name", reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestName()==null?"":reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestName());
//					    	 //map.put("content", reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestContent());
//					    	 List<String> list=htmlConvert(reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestContent());
//			    			 map.put("content", list.get(0)==null?"":list.get(0));
//			    			 map.put("imagesBase64String", list.get(1));
//			    			 map.put("imagesXmlHrefString", list.get(2));
//					    	 testlList.add(map);
//	    				}
//				    	 
//	    			}
//	    		}
//	    		mapPro.put("test", testlList);
	    		//processList.add(testMap);
	    		
	    		List<Map<String, Object>> analyticeslList=new ArrayList<Map<String,Object>>();
	    		 Map<String, Object> analyticesMap=new HashMap<String, Object>();
	    		 int count6=1;
	    		if(reaction.getReactionProcesses().get(i).getAnalyticses()!=null&&reaction.getReactionProcesses().get(i).getAnalyticses().size()>0){
	    			for(int j=0;j<reaction.getReactionProcesses().get(i).getAnalyticses().size();j++){
	    				Map<String, Object> map=new HashMap<String, Object>();
			    	   	 map.put("number", (count6++));
				    	 map.put("name", reaction.getReactionProcesses().get(i).getAnalyticses().get(j).getAnalyticsName()==null?"":reaction.getReactionProcesses().get(i).getAnalyticses().get(j).getAnalyticsName());
				    	// map.put("content", reaction.getReactionProcesses().get(i).getAnalyticses().get(j).getAnalyticsContent());
				    	 String content=reaction.getReactionProcesses().get(i).getAnalyticses().get(j).getAnalyticsContent();
				    	 List<String> list=htmlConvert(reaction.getReactionProcesses().get(i).getAnalyticses().get(j).getAnalyticsContent());
		    			 map.put("content", list.get(0)==null?"":list.get(0));
		    			 map.put("imagesBase64String", list.get(1));
		    			 map.put("imagesXmlHrefString", list.get(2));
				    	 
				    	 analyticeslList.add(map);
	    			}
	    		}
	    		mapPro.put("analytices", analyticeslList);
	    		//processList.add(analyticesMap);
	    		List<Map<String, Object>> recordlList=new ArrayList<Map<String,Object>>();
		    		if(reaction.getReactionProcesses().get(i).getReactionProcess()!=null&&!"".equals(reaction.getReactionProcesses().get(i).getReactionProcess())){
		    			Map<String, Object> map=new HashMap<String, Object>();
		    			map.put("process", reaction.getReactionProcesses().get(i).getProcessName());
		    			 List<String> list=htmlConvert(reaction.getReactionProcesses().get(i).getReactionProcess());
		    			 map.put("content", list.get(0)==null?"":list.get(0));
		    			 map.put("imagesBase64String", list.get(1));
		    			 map.put("imagesXmlHrefString", list.get(2));
		    			 recordlList.add(map);
		    		}
		    		mapPro.put("record", recordlList);
		    		processList.add(mapPro);
		    		
		    	}
		    	
		    }
		    List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
		    if(knowledges!=null&&knowledges.size()>0){
		    	int count=1;
		    	for(int i=0;i<knowledges.size();i++){
		    		Map<String, Object> knowMap=new HashMap<String, Object>();
		    		knowMap.put("number", count++);
		    		knowMap.put("title", knowledges.get(i).get("title"));
		    		knowMap.put("author", knowledges.get(i).get("author"));
		    		knowMap.put("createDate", knowledges.get(i).get("wtime"));
		    		knowledgeList.add(knowMap);
		    	}
		    }
		   
		    Date reportDate=reaction.getReportDate();
		    String str="";
		    if(reportDate!=null){
		    	str=sdf.format(reportDate);  
		    }
		    root.put("signAngency", sysSigningAgency.getUserName());
		    if(reaction.getReactionDesign().getType()==0){
		    	root.put("reactionName", reaction.getReactionDesign().getReactionGroupName()==null?"":reaction.getReactionDesign().getReactionGroupName());
		    	 root.put("groupName", "");
		    }else{
		    	root.put("reactionName", reaction.getReactionName()==null?"":reaction.getReactionName());
		    	 root.put("groupName", reaction.getReactionDesign().getReactionGroupName()==null?"":reaction.getReactionDesign().getReactionGroupName());
		    }
		    if(reaction.getProjectBasicInfo()!=null){
		    	  root.put("proName", reaction.getProjectBasicInfo().getProName());
		    }else{
		    	root.put("proName", "");
		    }
		    root.put("responPeople", reaction.getSysUser().getRealname());
		    root.put("reportDate", str);
		    root.put("chemicalList", chemicalList);
		    root.put("solutionList", solutionList);
		    root.put("processList", processList);
		    root.put("knowledgeList", knowledgeList);
		    return root;  
		  }  
	  
	  
	  private  List<String> htmlConvert(String htmlString) throws Exception { 
		  List<String> list=new ArrayList<String>();
		  htmlString="<div>"+htmlString+"</div>";
		  RichHtmlHandler handler = new RichHtmlHandler(htmlString);
//	        handler.setDocSrcLocationPrex("file:///C:/2A89CAB4");  //2016word版
//	        handler.setDocSrcParent("report.files");
	  
	        handler.setDocSrcLocationPrex("file:///C:/2A89CAB4");  //2003-2007word版
	        handler.setDocSrcParent("report.files");
	        handler.setNextPartId("01D3445D.82D39CF0");
	        handler.setShapeidPrex("_x56fe__x7247__x0020");
	        handler.setSpidPrex("_x0000_i");
	        handler.setTypeid("#_x0000_t75");

	        
	        handler.handledHtml(false);

	        String bodyBlock = handler.getHandledDocBodyBlock();
	        System.out.println("bodyBlock:\n"+bodyBlock);
	        list.add(bodyBlock);
	        String handledBase64Block = "";
	        if (handler.getDocBase64BlockResults() != null
	                && handler.getDocBase64BlockResults().size() > 0) {
	            for (String item : handler.getDocBase64BlockResults()) {
	                handledBase64Block += item + "\n";
	            }
	        }
	        list.add(handledBase64Block);
	        String xmlimaHref = "";
	        if (handler.getXmlImgRefs() != null
	                && handler.getXmlImgRefs().size() > 0) {
	            for (String item : handler.getXmlImgRefs()) {
	                xmlimaHref += item + "\n";
	            }
	        }
	        list.add(xmlimaHref);
	        return list;

	  }
	  
	  
	  
	  /**
		 * @Description 获取所有对象
		 * @author llwang
		 * @version V1.0
		 * @throws Exception 
		 * @date 2017年6月8日
		 */
		@RequestMapping(value = "/firstMeaasge/{id}", method = RequestMethod.GET)
		public ResultVo getAllMeaasge(@PathVariable("id") Integer id) throws Exception {
			ResultVo resultVo=new ResultVo();
		    try {
		    	// 获取当前用户
				LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				Integer userId = sysUser.getUserId();
				String roleName = sysUser.getSysRole().getRoleName();
				List<Integer> reactionIds1 = new ArrayList<Integer>();
				
				if (!roleName.equals("ROLE_TEAM")) {
					List<Reaction> list1 = reactionService.getUserList(userId);
					if(list1!=null && list1.size()>0){
						for (Reaction reaction : list1) {
							reactionIds1.add(reaction.getReactionId());
						}
						
					}
					if(!reactionIds1.contains(id)){
						resultVo.setErrCode(4);
						resultVo.setErrMsg("您没有权限");
						return resultVo;
					}
				}
				
				//Reaction reaction = reactionService.getById(id);
				Reaction reaction = reactionService.getByReactionId(id);
				List<Integer> reactionProcessesIds =reactionProcessService.getByReactionId(id);
				List<ReactionProcess> reactionProcesses=new ArrayList<ReactionProcess>();
				if(reactionProcessesIds!=null&&reactionProcessesIds.size()>0){
					for(Integer ProcessesId:reactionProcessesIds){
						ReactionProcess rp=reactionProcessService.getByProcessId(ProcessesId);
						reactionProcesses.add(rp);
					}
				}
				reaction.setReactionProcesses(reactionProcesses);
				List<Map<String,Object>> knowledges=new ArrayList<Map<String,Object>>();
				knowledges = reactionService.getArticleById(id);
				if(knowledges !=null && knowledges.size()>0){
					for (Map<String, Object> map : knowledges) {
						Integer knowledgeId =(Integer)map.get("knowledgeId");
						Integer classifyId = (Integer)map.get("knowledge_classify_id");
						KnowledgeAcc knowledgeAcc = knowledgeAccDaoService.getKnowledgeAcc(knowledgeId, classifyId);
						if(knowledgeAcc !=null){
							String uploadFiles = knowledgeAcc.getUploadFiles();
							Integer knowledgeAccId = knowledgeAcc.getKnowledgeAccId();
							Integer pdfCount = knowledgeAcc.getPdfCount();
							map.put("filename", uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));
							map.put("filePath", uploadFiles);
							map.put("knowledgeAccId", knowledgeAccId);
							map.put("pdfCount", pdfCount);
						}
					}
				}
				if(reaction!=null){
					//List<ReactionProcess> reactionProcesses = reaction.getReactionProcesses();
					if(reactionProcesses!=null && reactionProcesses.size()>0){
						for (ReactionProcess reactionProcess : reactionProcesses) {
							
//							List<ExecuteChemical> executeChemicals = reactionProcess.getExecuteChemicals();
//							if(executeChemicals!=null && executeChemicals.size()>0){
//								for (ExecuteChemical executeChemical : executeChemicals) {
//									InventoryLocation location = executeChemical.getInventory().getInventoryLocation();
//									if(location!=null && location.getPid()!=0){
//										String pname = getInventoryPname(location.getCid());
//										location.setParentName(pname);
//									}
//								}
//							}
							
							List<ReactionDevice> testDevices = reactionProcess.getReactionDevices();
							
							if(testDevices!=null && testDevices.size()>0){
								for (ReactionDevice testDevice : testDevices) {
									DeviceLocation location = testDevice.getDevice().getDeviceLocation();
									if(location!=null && !"0".equals(location.getDeviceLocaPid())){
										String pname = getDevicePname(location.getDeviceLocaId());
										location.setParentName(pname);
									}
								}
							}
							
							List<Prototype> protoTypes = reactionProcess.getPrototypes();
							if(protoTypes!=null && protoTypes.size()>0){
								for (Prototype prototype : protoTypes) {
									prototype.setQrName(urlPath+prototype.getQrName());
									InventoryLocation location = prototype.getInventoryLocation();
									if(location!=null && location.getPid()!=0){
										String pname = getInventoryPname(location.getCid());
										location.setParentName(pname);
									}
								}
							}
						}
					}
					
					Map<String, Object> root = initMessageData(knowledges,reaction);  //数据源对象  \
				       
					resultVo.setErrCode(0);
					resultVo.setErrMsg("get success");
					resultVo.setResultData(root);
				}
			} catch (Exception e) {
				System.out.println("errMessage=================================================================================="+e.getMessage());
				resultVo.setErrCode(1);
				resultVo.setErrMsg("create fail");
			}
			return resultVo;
		}
	  
		 private   Map<String, Object> initMessageData(List<Map<String, Object>> knowledges, Reaction reaction) throws Exception {
			 LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				SysSigningAgency sysSigningAgency = sysSigningAgencyService.getById( Integer.valueOf(sysUser.getSysSigningAgency().getAgencyId()).longValue());
			    Map<String, Object> root = new HashMap<String, Object>(); 
			    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			    SimpleDateFormat sdfs=new SimpleDateFormat("yyyy-MM-dd");
			    //一、	试验原料表
			    List<Map<String, Object>> chemicalList=new ArrayList<Map<String,Object>>();
			    List<Map<String, Object>> solutionList=new ArrayList<Map<String,Object>>();
			    List<Map<String, Object>> processList=new ArrayList<Map<String,Object>>();
			    List<Map<String, Object>> recordList=new ArrayList<Map<String,Object>>();
				//Map<String, Object> record=new HashMap<String, Object>();
			    if(reaction.getReactionProcesses()!=null&&reaction.getReactionProcesses().size()>0){
			    	int count1=1;
			    	int countPro=1;
			    	for(int i=0;i<reaction.getReactionProcesses().size();i++){
			    		Map<String, Object> mapPro=new HashMap<String, Object>();
			    		mapPro.put("number", (countPro++)+".");
			    		mapPro.put("name", reaction.getReactionProcesses().get(i).getProcessName());
			    		String start ="";
			    		if(reaction.getReactionProcesses().get(i).getStartTime()!=null){
			    			start=sdfs.format(reaction.getReactionProcesses().get(i).getStartTime());
			    		}
			    		mapPro.put("start", start );
			    		String end ="";
			    		if(reaction.getReactionProcesses().get(i).getEndTime()!=null){
			    			end=sdfs.format(reaction.getReactionProcesses().get(i).getEndTime());
			    		}
			    		
			    		mapPro.put("end", end );
			    		mapPro.put("status", reaction.getReactionProcesses().get(i).getReactionStatus().getReactionStatus() );
			    		String remark=reaction.getReactionProcesses().get(i).getRemark()==null?"":reaction.getReactionProcesses().get(i).getRemark();
			    		mapPro.put("remark", remark);
			    		
			    		if(reaction.getReactionProcesses().get(i).getExecuteChemicalGroups()!=null&&reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().size()>0){
			    			for(int j=0;j<reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().size();j++){
			    				Map<String, Object> map=new HashMap<String, Object>();
					    	   	 map.put("number", (count1++)+".");
					    	   	 if(reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getInventoryGroups()!=null){
					    	   		 if(reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getInventoryGroups().getMeasurement()!=null){
					    	   			map.put("measureUnit",reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getInventoryGroups().getMeasurement().getMeasureUnit());
					    	   		 }
					    	   		map.put("name", reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getInventoryGroups().getInventoryName());
					    	   	 }
						    	 map.put("count", reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getGroupDosage());
						    	 map.put("processName", reaction.getReactionProcesses().get(i).getProcessName());
//						    	 if(reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation()!=null){
//						    		 int cid=reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getCid();
//						    		 String inventory=getAllPname(cid);
//						    		 inventory=inventory+">"+reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel();
//						    		 map.put("inventory", inventory); 
//						    	 }
//						    	 if(reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation()!=null){
//						    		 map.put("inventory", reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel()==null?"":reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel()); 
//						    	 }
						    	 chemicalList.add(map);
			    			}
			    		}
			    		
			    		if(reaction.getReactionProcesses().get(i).getExecuteSolutions()!=null&&reaction.getReactionProcesses().get(i).getExecuteSolutions().size()>0){
			    			for(int j=0;j<reaction.getReactionProcesses().get(i).getExecuteSolutions().size();j++){
			    				Map<String, Object> map=new HashMap<String, Object>();
					    	   	 map.put("number", (count1++)+".");
					    	   	 if(reaction.getReactionProcesses().get(i).getExecuteSolutions().get(j).getMeasurement()!=null){
					    	   		map.put("measureUnit",reaction.getReactionProcesses().get(i).getExecuteSolutions().get(j).getMeasurement().getMeasureUnit());
					    	   	 }
						    	 map.put("name", reaction.getReactionProcesses().get(i).getExecuteSolutions().get(j).getSolution().getSolutionName());
						    	 map.put("count", reaction.getReactionProcesses().get(i).getExecuteSolutions().get(j).getSolutionDosage());
						    	 map.put("processName", reaction.getReactionProcesses().get(i).getProcessName());
//						    	 if(reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation()!=null){
//						    		 int cid=reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getCid();
//						    		 String inventory=getAllPname(cid);
//						    		 inventory=inventory+">"+reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel();
//						    		 map.put("inventory", inventory); 
//						    	 }
//						    	 if(reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation()!=null){
//						    		 map.put("inventory", reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel()==null?"":reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel()); 
//						    	 }
						    	 solutionList.add(map);
			    			}
			    		}
//			    		if(reaction.getReactionProcesses().get(i).getExecuteChemicals()!=null&&reaction.getReactionProcesses().get(i).getExecuteChemicals().size()>0){
//			    			for(int j=0;j<reaction.getReactionProcesses().get(i).getExecuteChemicals().size();j++){
//			    				Map<String, Object> map=new HashMap<String, Object>();
//					    	   	 map.put("number", (count1++)+".");
//					    	   	map.put("measureUnit", reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getInventoryGroups().getMeasurement().getMeasureUnit());
//						    	// map.put("name", reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryName());
//						    	 map.put("count", reaction.getReactionProcesses().get(i).getExecuteChemicalGroups().get(j).getGroupDosage());
//						    	 map.put("processName", reaction.getReactionProcesses().get(i).getProcessName());
////						    	 if(reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation()!=null){
////						    		 int cid=reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getCid();
////						    		 String inventory=getAllPname(cid);
////						    		 inventory=inventory+">"+reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel();
////						    		 map.put("inventory", inventory); 
////						    	 }
//						    	 chemicalList.add(map);
//			    			}
//			    		}
			    		 List<Map<String, Object>> paramterlList=new ArrayList<Map<String,Object>>();
			    		 Map<String, Object> paramterMap=new HashMap<String, Object>();
			    		 int count2=1;
			    		if(reaction.getReactionProcesses().get(i).getReactionExecuteParameters()!=null&&reaction.getReactionProcesses().get(i).getReactionExecuteParameters().size()>0){
			    			for(int j=0;j<reaction.getReactionProcesses().get(i).getReactionExecuteParameters().size();j++){
			    				Map<String, Object> map=new HashMap<String, Object>();
					    	   	 map.put("number", (count2++)+".");
						    	 map.put("name", reaction.getReactionProcesses().get(i).getReactionExecuteParameters().get(j).getReactionParameter());
						    	 if(reaction.getReactionProcesses().get(i).getReactionExecuteParameters().get(j).getMeasurement()!=null){
						    		 map.put("unit", reaction.getReactionProcesses().get(i).getReactionExecuteParameters().get(j).getMeasurement().getMeasureUnit()); 
						    	 }
						    	 map.put("count", reaction.getReactionProcesses().get(i).getReactionExecuteParameters().get(j).getExecuteParameterDosage());
						    	 paramterlList.add(map);
			    			}
			    		}
			    		mapPro.put("paramter", paramterlList);
			    		//processList.add(paramterMap);
			    		 int count3=1;
			    		 List<Map<String, Object>> devicelList=new ArrayList<Map<String,Object>>();
			    		 Map<String, Object> deviceMap=new HashMap<String, Object>();
			    		if(reaction.getReactionProcesses().get(i).getReactionDevices()!=null&&reaction.getReactionProcesses().get(i).getReactionDevices().size()>0){
			    			for(int j=0;j<reaction.getReactionProcesses().get(i).getReactionDevices().size();j++){
			    				Map<String, Object> map=new HashMap<String, Object>();
					    	   	 map.put("number", (count3++)+".");
						    	 map.put("name", reaction.getReactionProcesses().get(i).getReactionDevices().get(j).getDevice().getDeviceName());
						    	 int cid=reaction.getReactionProcesses().get(i).getReactionDevices().get(j).getDevice().getDeviceLocation().getDeviceLocaId();
						    	 String ss=getAllDevicePname(cid);
						    	 map.put("location", ss+">"+reaction.getReactionProcesses().get(i).getReactionDevices().get(j).getDevice().getDeviceLocation().getLabel());
						    	 devicelList.add(map);
			    			}
			    		}
			    		mapPro.put("device", devicelList);
			    		//processList.add(deviceMap);
			    		
			   		 List<Map<String, Object>> protolList=new ArrayList<Map<String,Object>>();
		    		 Map<String, Object> protoMap=new HashMap<String, Object>();
		    		 int count4=1;
		    		if(reaction.getReactionProcesses().get(i).getPrototypes()!=null&&reaction.getReactionProcesses().get(i).getPrototypes().size()>0){
		    			for(int j=0;j<reaction.getReactionProcesses().get(i).getPrototypes().size();j++){
		    				Map<String, Object> map=new HashMap<String, Object>();
				    	   	 map.put("number", (count4++)+".");
					    	 map.put("name", reaction.getReactionProcesses().get(i).getPrototypes().get(j).getPrototypeName());
					    	 if(reaction.getReactionProcesses().get(i).getPrototypes().get(j).getPrototypeDosage()==null||reaction.getReactionProcesses().get(i).getPrototypes().get(j).getMeasurement()==null){
					    		 map.put("dosage", ""); 
					    	 }else{
					    		 map.put("dosage", reaction.getReactionProcesses().get(i).getPrototypes().get(j).getPrototypeDosage()+reaction.getReactionProcesses().get(i).getPrototypes().get(j).getMeasurement().getMeasureUnit()); 
					    	 }
					    	 map.put("count", reaction.getReactionProcesses().get(i).getPrototypes().get(j).getPrototypeNum());
					    	 map.put("barCode", reaction.getReactionProcesses().get(i).getPrototypes().get(j).getBarCode());
					    	 if(reaction.getReactionProcesses().get(i).getPrototypes().get(j).getInventoryLocation()!=null){
					    		 int cid=reaction.getReactionProcesses().get(i).getPrototypes().get(j).getInventoryLocation().getCid();
					    		 String inventory=getAllPname(cid);
					    		 map.put("location", inventory+">"+reaction.getReactionProcesses().get(i).getPrototypes().get(j).getInventoryLocation().getLabel());
					    	 }else{
					    		 map.put("location", ""); 
					    	 }
					    	
					    	 protolList.add(map);
		    			}
		    		}
		    		mapPro.put("proto", protolList);
		    		//processList.add(protoMap);
		    		
		    		List<Map<String, Object>> testlList=new ArrayList<Map<String,Object>>();
		    		 Map<String, Object> testMap=new HashMap<String, Object>();
		    		 int count5=1;
		    		if(reaction.getReactionProcesses().get(i).getReactionTests()!=null&&reaction.getReactionProcesses().get(i).getReactionTests().size()>0){
		    			for(int j=0;j<reaction.getReactionProcesses().get(i).getReactionTests().size();j++){
		    				Map<String, Object> map=new HashMap<String, Object>();
		    				if(reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestName()!=null){
		    					map.put("number", (count5++)+".");
						    	 map.put("name", reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestName());
						    	 map.put("content", reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestContent());
						    	 testlList.add(map);
		    				}
					    	
		    			}
		    		}
		    		mapPro.put("test", testlList);
		    		//processList.add(testMap);
		    		
		    		List<Map<String, Object>> analyticeslList=new ArrayList<Map<String,Object>>();
		    		 Map<String, Object> analyticesMap=new HashMap<String, Object>();
		    		 int count6=1;
		    		if(reaction.getReactionProcesses().get(i).getAnalyticses()!=null&&reaction.getReactionProcesses().get(i).getAnalyticses().size()>0){
		    			for(int j=0;j<reaction.getReactionProcesses().get(i).getAnalyticses().size();j++){
		    				Map<String, Object> map=new HashMap<String, Object>();
				    	   	 map.put("number", (count6++)+".");
					    	 map.put("name", reaction.getReactionProcesses().get(i).getAnalyticses().get(j).getAnalyticsName());
					    	 map.put("content", reaction.getReactionProcesses().get(i).getAnalyticses().get(j).getAnalyticsContent());
					    	 analyticeslList.add(map);
		    			}
		    		}
		    		mapPro.put("analytices", analyticeslList);
		    		//processList.add(analyticesMap);
		    		List<Map<String, Object>> recordlList=new ArrayList<Map<String,Object>>();
		    		Map<String, Object> map=new HashMap<String, Object>();
			    		if(reaction.getReactionProcesses().get(i).getReactionProcess()!=null&&!"".equals(reaction.getReactionProcesses().get(i).getReactionProcess())){
			    			map.put("process", reaction.getReactionProcesses().get(i).getProcessName());
			    			 map.put("content", reaction.getReactionProcesses().get(i).getReactionProcess());
			    			// recordlList.add(map);
			    		}
			    		if(map.get("process")!=null){
			    			mapPro.put("record", map);
			    		}else{
			    			mapPro.put("record", null);
			    		}
			    		
			    		processList.add(mapPro);
			    		
			    	}
			    	
			    }
			    List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
			    List<Map<String, Object>> selfPaperList=new ArrayList<Map<String,Object>>();
			    List<Map<String, Object>> patentList=new ArrayList<Map<String,Object>>();
			    if(knowledges!=null&&knowledges.size()>0){
			    	for(int i=0;i<knowledges.size();i++){
			    		int calssfy=Integer.parseInt(String.valueOf(knowledges.get(i).get("knowledge_classify_id")));
			    		if(calssfy-1==0){
			    			Map<String, Object> knowMap=new HashMap<String, Object>();
				    		knowMap.put("title", knowledges.get(i).get("title"));
				    		knowMap.put("author", knowledges.get(i).get("author"));
				    		knowMap.put("createDate", knowledges.get(i).get("wtime"));
				    		knowledgeList.add(knowMap);
			    		}else if(calssfy-2==0){
			    			Map<String, Object> selfMap=new HashMap<String, Object>();
			    			selfMap.put("title", knowledges.get(i).get("title"));
			    			selfMap.put("author", knowledges.get(i).get("author"));
			    			selfMap.put("createDate", knowledges.get(i).get("wtime"));
			    			selfPaperList.add(selfMap);
			    		}else{
			    			Map<String, Object> patentMap=new HashMap<String, Object>();
			    			patentMap.put("title", knowledges.get(i).get("title"));
			    			patentMap.put("author", knowledges.get(i).get("author"));
			    			patentMap.put("createDate", knowledges.get(i).get("wtime"));
			    			patentList.add(patentMap);
			    		}
			    		
			    	}
			    }
			   
			    Date reportDate=reaction.getReportDate();
			    String str="";
			    if(reportDate!=null){
			    	str=sdf.format(reportDate);  
			    }  
			    root.put("signAngency", sysSigningAgency.getUserName());
			    root.put("type", reaction.getReactionDesign().getType());
			    root.put("reactionGroupName", reaction.getReactionDesign().getReactionGroupName());
			    root.put("reactionName", reaction.getReactionName());
			    if(reaction.getProjectBasicInfo()!=null){
			    	root.put("proName", reaction.getProjectBasicInfo().getProName());
			    }
			    root.put("responPeople", reaction.getSysUser().getRealname());
			    root.put("reportDate", str);
			    root.put("chemicalList", chemicalList);
			    root.put("solutionList", solutionList);
			    root.put("processList", processList);
			    root.put("knowledgeList", knowledgeList);
			    root.put("selfPaperList", selfPaperList);
			    root.put("patentList", patentList);
			    return root;  
			  }  
	
		 
		 public String getAllPname(Integer id) {
			    try {
			      String ss="";
			    loop:for(int i=0;i<10;i++){
			      InventoryLocation inventoryLocation =  inventoryLocService.getById(id);
			      if(inventoryLocation.getPid()!=null&&inventoryLocation.getPid()!=0){
			        if(i==0){
			          ss="";
			        }else if(i==1){
			          ss=inventoryLocation.getLabel();
			        }else{
			          ss=inventoryLocation.getLabel()+">"+ss;
			        }
			        id=inventoryLocation.getPid();
			      }else{
			        if(!"".equals(ss)){
			          ss=inventoryLocation.getLabel()+">"+ss;
			        }else{
			          ss=inventoryLocation.getLabel();  
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
		 
		 public String getAllDevicePname(Integer id) {
			    try {
			      String ss="";
			    loop:for(int i=0;i<10;i++){
			      DeviceLocation deviceLocation =  deviceLocationService.getById(id);
			      if(deviceLocation.getDeviceLocaPid()!=null&&!"0".equals(deviceLocation.getDeviceLocaPid())){
			        if(i==0){
			          ss="";
			        }else if(i==1){
			          ss=deviceLocation.getLabel();
			        }else{
			          ss=deviceLocation.getLabel()+">"+ss;
			        }
			        id= Integer.valueOf(deviceLocation.getDeviceLocaPid());
			      }else{
			        if(!"".equals(ss)){
			          ss=deviceLocation.getLabel()+">"+ss;
			        }else{
			          ss=deviceLocation.getLabel();  
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
			 * @Description 获取所有对象
			 * @author llwang
			 * @version V1.0
			 * @throws Exception 
			 * @date 2017年6月8日
			 */
			@RequestMapping(value = "/createAppWord/{id}", method = RequestMethod.GET)
			public ResultVo getAllForApp(@PathVariable("id") Integer id) throws Exception {
				ResultVo resultVo = new ResultVo();
			    String template = "wordtest.ftl";  //模板文件的地址  2016word版
			    LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			    String fileName="report"+sysUser.getSysSigningAgency().getAgencyId()+"_"+id+".doc";
			    createDir(uploadReport);
			    String docFilePath = uploadReport+fileName;//生成的word文档的输出地址  
			    String lastPath=reportPath+fileName;
			    try {
					Reaction reaction = reactionService.getById(id);
					List<Map<String,Object>> knowledges = reactionService.getArticleById(id);
					int count=0;
					 
					if(knowledges !=null && knowledges.size()>0){
						for (Map<String, Object> map : knowledges) {
							Integer knowledgeId =(Integer)map.get("knowledgeId");
							Integer classifyId = (Integer)map.get("knowledge_classify_id");
							KnowledgeAcc knowledgeAcc = knowledgeAccDaoService.getKnowledgeAcc(knowledgeId, classifyId);
							if(knowledgeAcc !=null){
								String uploadFiles = knowledgeAcc.getUploadFiles();
								Integer knowledgeAccId = knowledgeAcc.getKnowledgeAccId();
								Integer pdfCount = knowledgeAcc.getPdfCount();
								map.put("number", "["+(count++)+"].");
								map.put("filename", uploadFiles.substring(uploadFiles.lastIndexOf("/")+1));
								map.put("filePath", uploadFiles);
								map.put("knowledgeAccId", knowledgeAccId);
								map.put("pdfCount", pdfCount);
							}
						}
					}
					if(reaction!=null){
						List<ReactionProcess> reactionProcesses = reaction.getReactionProcesses();
						if(reactionProcesses!=null && reactionProcesses.size()>0){
							for (ReactionProcess reactionProcess : reactionProcesses) {
								
								List<ExecuteChemical> executeChemicals = reactionProcess.getExecuteChemicals();
								if(executeChemicals!=null && executeChemicals.size()>0){
									for (ExecuteChemical executeChemical : executeChemicals) {
										InventoryLocation location = executeChemical.getInventory().getInventoryLocation();
										if(location!=null && location.getPid()!=0){
											String pname = getInventoryPname(location.getCid());
											location.setParentName(pname);
										}
									}
								}
								
								List<ReactionDevice> testDevices = reactionProcess.getReactionDevices();
								
								if(testDevices!=null && testDevices.size()>0){
									for (ReactionDevice testDevice : testDevices) {
										DeviceLocation location = testDevice.getDevice().getDeviceLocation();
										if(location!=null && !"0".equals(location.getDeviceLocaPid())){
											String pname = getDevicePname(location.getDeviceLocaId());
											location.setParentName(pname);
										}
									}
								}
								
								List<Prototype> protoTypes = reactionProcess.getPrototypes();
								if(protoTypes!=null && protoTypes.size()>0){
									for (Prototype prototype : protoTypes) {
										prototype.setQrName(urlPath+prototype.getQrName());
										InventoryLocation location = prototype.getInventoryLocation();
										if(location!=null && location.getPid()!=0){
											String pname = getInventoryPname(location.getCid());
											location.setParentName(pname);
										}
									}
								}
							}
						}
						
						Map<String, Object> root = initAPPData(knowledges,reaction);  //数据源对象  \
						 HtmlUtil hu=new HtmlUtil();
				         createDir("src/main/resources/template/");
				         String outUrl="src/main/resources/template/"+UUID.randomUUID().toString()+".html";
				         hu.CreateHtml(root, outUrl, "reactionForApp.html");
				         Html2ImageUtil hi=new Html2ImageUtil();
				         String imageName=UUID.randomUUID().toString()+".png";
				         String imagePath=msgFile+imageName;
				         hi.HtmlToImage(outUrl,imagePath);
//				         Html2ImageUtil hi=new Html2ImageUtil();
//				         String imageName=UUID.randomUUID().toString()+".png";
//				         String imagePath="src/main/resources/template/"+imageName;
//				         hi.HtmlToImage(outUrl,imagePath);
//				         ImageToPdf it=new ImageToPdf();
//				         String pdfName=UUID.randomUUID().toString()+".pdf";
//				         it.creatPDF(imagePath,msgFile+pdfName);
						resultVo.setErrCode(0);
						resultVo.setErrMsg("create success");
						resultVo.setResultData(imageFile+imageName);
						
					}
				} catch (Exception e) {
					resultVo.setErrCode(1);
					resultVo.setErrMsg("create fail:"+e.getMessage());
				}
				return resultVo;
			}
			
			
			 private   Map<String, Object> initAPPData(List<Map<String, Object>> knowledges, Reaction reaction) throws Exception {  
				  LoginController login = new LoginController();
					SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
					SysSigningAgency sysSigningAgency = sysSigningAgencyService.getById( Integer.valueOf(sysUser.getSysSigningAgency().getAgencyId()).longValue());
				    Map<String, Object> root = new HashMap<String, Object>(); 
				    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				    //一、	试验原料表
				    List<Map<String, Object>> chemicalList=new ArrayList<Map<String,Object>>();
				   
				    List<Map<String, Object>> processList=new ArrayList<Map<String,Object>>();
				    List<Map<String, Object>> recordList=new ArrayList<Map<String,Object>>();
				    if(reaction.getReactionProcesses()!=null&&reaction.getReactionProcesses().size()>0){
				    	int count1=1;
				    	int countPro=1;
				    	for(int i=0;i<reaction.getReactionProcesses().size();i++){
				    		Map<String, Object> mapPro=new HashMap<String, Object>();
				    		mapPro.put("number", (countPro++)+".");
				    		mapPro.put("name", reaction.getReactionProcesses().get(i).getProcessName());
				    		String start ="";
				    		if(reaction.getReactionProcesses().get(i).getStartTime()!=null){
				    			start=sdf.format(reaction.getReactionProcesses().get(i).getStartTime());
				    		}
				    		mapPro.put("start", start );
				    		String end ="";
				    		if(reaction.getReactionProcesses().get(i).getEndTime()!=null){
				    			end=sdf.format(reaction.getReactionProcesses().get(i).getEndTime());
				    		}
				    		
				    		mapPro.put("end", end );
				    		mapPro.put("status", reaction.getReactionProcesses().get(i).getReactionStatus().getReactionStatus() );
				    		String remark=reaction.getReactionProcesses().get(i).getRemark()==null?"":reaction.getReactionProcesses().get(i).getRemark();
				    		mapPro.put("remark", remark);
				    		if(reaction.getReactionProcesses().get(i).getExecuteChemicals()!=null&&reaction.getReactionProcesses().get(i).getExecuteChemicals().size()>0){
				    			for(int j=0;j<reaction.getReactionProcesses().get(i).getExecuteChemicals().size();j++){
				    				Map<String, Object> map=new HashMap<String, Object>();
						    	   	 map.put("number", (count1++)+".");
							    	 map.put("name", reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryName());
							    	 map.put("count", reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getChemicalDosage()+reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getMeasurement().getMeasureUnit());
							    	 map.put("processName", reaction.getReactionProcesses().get(i).getProcessName());
							    	 if(reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation()!=null){
							    		 int cid=reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getCid();
							    		 String inventory=getAllPname(cid);
							    		 inventory=inventory+">"+reaction.getReactionProcesses().get(i).getExecuteChemicals().get(j).getInventory().getInventoryLocation().getLabel();
							    		 map.put("inventory", inventory); 
							    	 }
							    	 chemicalList.add(map);
				    			}
				    		}
				    		 List<Map<String, Object>> paramterlList=new ArrayList<Map<String,Object>>();
				    		 Map<String, Object> paramterMap=new HashMap<String, Object>();
				    		 int count2=1;
				    		if(reaction.getReactionProcesses().get(i).getReactionExecuteParameters()!=null&&reaction.getReactionProcesses().get(i).getReactionExecuteParameters().size()>0){
				    			for(int j=0;j<reaction.getReactionProcesses().get(i).getReactionExecuteParameters().size();j++){
				    				Map<String, Object> map=new HashMap<String, Object>();
						    	   	 map.put("number", (count2++)+".");
							    	 map.put("name", reaction.getReactionProcesses().get(i).getReactionExecuteParameters().get(j).getReactionParameter());
							    	 if(reaction.getReactionProcesses().get(i).getReactionExecuteParameters().get(j).getMeasurement()!=null){
							    		 map.put("unit", reaction.getReactionProcesses().get(i).getReactionExecuteParameters().get(j).getMeasurement().getMeasureUnit());
							    	 }
							    	 map.put("count", reaction.getReactionProcesses().get(i).getReactionExecuteParameters().get(j).getExecuteParameterDosage());
							    	 paramterlList.add(map);
				    			}
				    		}
				    		mapPro.put("paramter", paramterlList);
				    		 int count3=1;
				    		 List<Map<String, Object>> devicelList=new ArrayList<Map<String,Object>>();
				    		 Map<String, Object> deviceMap=new HashMap<String, Object>();
				    		if(reaction.getReactionProcesses().get(i).getReactionDevices()!=null&&reaction.getReactionProcesses().get(i).getReactionDevices().size()>0){
				    			for(int j=0;j<reaction.getReactionProcesses().get(i).getReactionDevices().size();j++){
				    				Map<String, Object> map=new HashMap<String, Object>();
						    	   	 map.put("number", (count3++)+".");
							    	 map.put("name", reaction.getReactionProcesses().get(i).getReactionDevices().get(j).getDevice().getDeviceName());
							    	 int cid=reaction.getReactionProcesses().get(i).getReactionDevices().get(j).getDevice().getDeviceLocation().getDeviceLocaId();
							    	 String ss=getAllDevicePname(cid);
							    	 map.put("location", ss+">"+reaction.getReactionProcesses().get(i).getReactionDevices().get(j).getDevice().getDeviceLocation().getLabel());
							    	// map.put("location", reaction.getReactionProcesses().get(i).getReactionDevices().get(j).getDevice().getDeviceLocation().getLabel());
							    	 devicelList.add(map);
				    			}
				    		}
				    		mapPro.put("device", devicelList);
				   		 List<Map<String, Object>> protolList=new ArrayList<Map<String,Object>>();
			    		 Map<String, Object> protoMap=new HashMap<String, Object>();
			    		 int count4=1;
			    		if(reaction.getReactionProcesses().get(i).getPrototypes()!=null&&reaction.getReactionProcesses().get(i).getPrototypes().size()>0){
			    			for(int j=0;j<reaction.getReactionProcesses().get(i).getPrototypes().size();j++){
			    				Map<String, Object> map=new HashMap<String, Object>();
					    	   	 map.put("number", (count4++)+".");
						    	 map.put("name", reaction.getReactionProcesses().get(i).getPrototypes().get(j).getPrototypeName());
						    	 map.put("dosage", reaction.getReactionProcesses().get(i).getPrototypes().get(j).getPrototypeDosage()+reaction.getReactionProcesses().get(i).getPrototypes().get(j).getMeasurement().getMeasureUnit());
						    	 map.put("count", reaction.getReactionProcesses().get(i).getPrototypes().get(j).getPrototypeNum());
						    	 map.put("barCode", reaction.getReactionProcesses().get(i).getPrototypes().get(j).getBarCode());
						    	 if(reaction.getReactionProcesses().get(i).getPrototypes().get(j).getInventoryLocation()!=null){
						    		 int cid=reaction.getReactionProcesses().get(i).getPrototypes().get(j).getInventoryLocation().getCid();
						    		 String inventory=getAllPname(cid);
						    		 map.put("location", inventory+">"+reaction.getReactionProcesses().get(i).getPrototypes().get(j).getInventoryLocation().getLabel());
						    	 }else{
						    		 map.put("location", ""); 
						    	 }
						    	
						    	 protolList.add(map);
			    			}
			    		}
			    		mapPro.put("proto", protolList);
			    		
			    		List<Map<String, Object>> testlList=new ArrayList<Map<String,Object>>();
			    		 Map<String, Object> testMap=new HashMap<String, Object>();
			    		 int count5=1;
			    		if(reaction.getReactionProcesses().get(i).getReactionTests()!=null&&reaction.getReactionProcesses().get(i).getReactionTests().size()>0){
			    			for(int j=0;j<reaction.getReactionProcesses().get(i).getReactionTests().size();j++){
			    				Map<String, Object> map=new HashMap<String, Object>();
					    	   	if(reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestName()!=null){
					    	   		map.put("name", reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestName()==null?"":reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestName());
					    	   		String content=reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestContent()==null?"":reaction.getReactionProcesses().get(i).getReactionTests().get(j).getTestContent();
					    	   		map.put("content", content);
							    	 testlList.add(map);
			    				}
						    	 
			    			}
			    		}
			    		mapPro.put("test", testlList);
			    		
			    		List<Map<String, Object>> analyticeslList=new ArrayList<Map<String,Object>>();
			    		 Map<String, Object> analyticesMap=new HashMap<String, Object>();
			    		 int count6=1;
			    		if(reaction.getReactionProcesses().get(i).getAnalyticses()!=null&&reaction.getReactionProcesses().get(i).getAnalyticses().size()>0){
			    			for(int j=0;j<reaction.getReactionProcesses().get(i).getAnalyticses().size();j++){
			    				Map<String, Object> map=new HashMap<String, Object>();
					    	   	 map.put("number", (count6++));
						    	 map.put("name", reaction.getReactionProcesses().get(i).getAnalyticses().get(j).getAnalyticsName()==null?"":reaction.getReactionProcesses().get(i).getAnalyticses().get(j).getAnalyticsName());
						    	 String content=reaction.getReactionProcesses().get(i).getAnalyticses().get(j).getAnalyticsContent()==null?"":reaction.getReactionProcesses().get(i).getAnalyticses().get(j).getAnalyticsContent();
				    			 map.put("content", content);
						    	 analyticeslList.add(map);
			    			}
			    		}
			    		mapPro.put("analytices", analyticeslList);
			    		//processList.add(analyticesMap);
			    		List<Map<String, Object>> recordlList=new ArrayList<Map<String,Object>>();
				    		if(reaction.getReactionProcesses().get(i).getReactionProcess()!=null&&!"".equals(reaction.getReactionProcesses().get(i).getReactionProcess())){
				    			Map<String, Object> map=new HashMap<String, Object>();
				    			map.put("process", reaction.getReactionProcesses().get(i).getProcessName());
				    			 map.put("content", reaction.getReactionProcesses().get(i).getReactionProcess());
				    			 recordlList.add(map);
				    		}
				    		mapPro.put("record", recordlList);
				    		processList.add(mapPro);
				    		
				    	}
				    	
				    }
				    List<Map<String, Object>> knowledgeList=new ArrayList<Map<String,Object>>();
				    if(knowledges!=null&&knowledges.size()>0){
				    	int count=1;
				    	for(int i=0;i<knowledges.size();i++){
				    		Map<String, Object> knowMap=new HashMap<String, Object>();
				    		knowMap.put("number", "["+count+++"]、");
				    		knowMap.put("title", knowledges.get(i).get("title"));
				    		knowMap.put("author", knowledges.get(i).get("author"));
				    		knowMap.put("createDate", knowledges.get(i).get("wtime"));
				    		knowledgeList.add(knowMap);
				    	}
				    }
				   
				    Date reportDate=reaction.getReportDate();
				    String str="";
				    if(reportDate!=null){
				    	str=sdf.format(reportDate);  
				    }
				    root.put("signAngency", sysSigningAgency.getUserName());
				    root.put("reactionName", reaction.getReactionName());
				    root.put("proName", reaction.getProjectBasicInfo().getProName());
				    root.put("responPeople", reaction.getProjectNumber().getSysUser().getRealname());
				    root.put("reportDate", str);
				    root.put("chemicalList", chemicalList);
				    root.put("processList", processList);
				    root.put("knowledgeList", knowledgeList);
				    return root;  
				  }
	 
}
