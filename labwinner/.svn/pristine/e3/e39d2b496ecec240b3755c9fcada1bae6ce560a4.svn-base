package com.labwinner.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Inventories;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.util.MergeUtil;
import com.labwinner.util.PdfUtil;
import com.labwinner.util.QRCreateAndParse;

@RestController
public class InventoryLocationController {

	private static Logger logger = LoggerFactory
			.getLogger(InventoryLocationController.class);

	@Autowired
	private InventoryLocationService inventoryLocationService;

	@Autowired
	private InventoriesService inventoryService;
	
	@Value("${web.qr-path}")
	String qrPath;
	@Value("${web.qrUrl-path}")
	private String qrUrlPath; 
	@Value("${web.upload_path_pdf}")
	private String pdfPath; 
	@Value("${web.agency_pdf}")
	private String pdfLine; 
	
	
	@RequestMapping(value = "/inventoryLocation/list", method = RequestMethod.GET)
	public List<InventoryLocation> getAll() {
		List<InventoryLocation> list = inventoryLocationService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}
    
	/**
	 * @Description 根据关键字分页获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/inventoryLocationPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<InventoryLocation> inventoryLocations = inventoryLocationService.getAllPageable(keyword);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(inventoryLocations));
			return resultVo;
		} else {
			List<InventoryLocation> inventoryLocations = inventoryLocationService.getAll();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(inventoryLocations));
			return resultVo;
		}
		
	}
	
	/**
	 * @Description 给之前位置加qr
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@RequestMapping(value = "/inventoryLocation/updateQrName", method = RequestMethod.GET)
	public ResultVo updateQrName() {
		 ResultVo resultVo = new ResultVo();
			try {
				List<InventoryLocation> list = inventoryLocationService.getAll();
				
				for (InventoryLocation inventoryLocation : list) {
					if(inventoryLocation.getBarcode()==null){
						String barCode = createBarcode();
						inventoryLocation.setBarcode(barCode);
						QRCreateAndParse qr = new QRCreateAndParse();
						String qrName = qr.createQr(barCode, qrPath,null);
						inventoryLocation.setQrPath(qrName);
						inventoryLocationService.updateQrName(inventoryLocation);
					}
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update successe");
				return resultVo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("save fail");
				return resultVo;
			}
	}
	
	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */

	@RequestMapping(value = "/inventoryLocation/add", method = RequestMethod.POST)
	public ResultVo save(@RequestBody InventoryLocation inventoryLocation) {
		 ResultVo resultVo = new ResultVo();
			try {
				if(inventoryLocation.getPid()==null){
					inventoryLocation.setPid(0);
				}
				
				String barCode = createBarcode();
				inventoryLocation.setBarcode(barCode);
				QRCreateAndParse qr = new QRCreateAndParse();
				String qrName = qr.createQr(barCode, qrPath,null);
				inventoryLocation.setQrPath(qrName);
				
				inventoryLocationService.save(inventoryLocation);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save successe");
				resultVo.setResultData(inventoryLocation.getCid());
				return resultVo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("save fail");
				return resultVo;
			}
	}
	
	 /**
	 * 更新对象的方法
	 * @Description TODO
	 * @author suhg
	 * @version V1.0
	 * @date 2017年5月19日 上午11:22:57
	 */
	 @RequestMapping(value = "/inventoryLocation", method = RequestMethod.PUT)
	    public ResultVo update(@RequestBody InventoryLocation inventoryLocation) {
		 ResultVo resultVo = new ResultVo();
			try {
				if(inventoryLocation.getPid()==null){
					inventoryLocation.setPid(0);
				}
				inventoryLocationService.update(inventoryLocation);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update successe");
				return resultVo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("update fail");
				return resultVo;
			}
		
	 }
		@RequestMapping(value = "/inventoryLocation/getTree", method = RequestMethod.GET)
	public ResultVo getTree() {
		ResultVo resultVo = new ResultVo();
		try {
			InventoryLocation location = inventoryLocationService.getTree(1);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(location);
			return resultVo;
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		resultVo.setErrCode(1);
		resultVo.setErrMsg("find fail");
		return resultVo;
	}

 
/**
 * 删除对象的方法
 * @Description TODO
 * @author suhg
 * @version V1.0
 * @date 2017年5月19日 上午11:22:08
 */
	 @RequestMapping(value = "/inventoryLocation/{id}", method = RequestMethod.DELETE)
	    public ResultVo delete(@PathVariable("id") Integer id) {
		 ResultVo resultVo = new ResultVo();

		 InventoryLocation inventoryLocation =  inventoryLocationService.getById(id);

			if (inventoryLocation.getInventories().size() > 0){
				resultVo.setErrCode(1);
				resultVo.setErrMsg("该库存位置存在库存中，不能被删除！！！");
			}else {
				
				inventoryLocationService.delete(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("删除成功！！！");
			}
			return resultVo;
	 }
	 /**
	  * 删除对象的方法
	  * @Description TODO
	  * @author llwang
	  * @version V1.0
	  * @date 2017年8月9日 上午11:22:08
	  */
	 @RequestMapping(value = "/inventoryLocation/delete/{id}", method = RequestMethod.DELETE)
	    public ResultVo deleteWeb(@PathVariable("id") Integer id) {
		 ResultVo res=new ResultVo();
		 try {
			 List<Inventories> list=inventoryService.getByLocationId(id);
				if(list!=null&&list.size()>0){
					res.setErrCode(1);
					res.setErrMsg("该库存位置已被使用，不允许删除！");
				}else{
					List<InventoryLocation> listLocation=inventoryLocationService.getAllLocation(id);
					//inventoryLocationService.deleteByLocationId(id);
					if(listLocation!=null){
						for(int i=0;i<listLocation.size();i++){
							 new File(qrPath + listLocation.get(i).getQrPath()).delete();
							inventoryLocationService.delete(listLocation.get(i).getCid());
						}
					}
					res.setErrCode(0);
					res.setErrMsg("删除成功！");
				}
		} catch (Exception e) {
			res.setErrCode(2);
			res.setErrMsg("程序错误："+e.getMessage());
		}
		return res;
	 }
	 
	 /**
		 * 根据主键获取对象
		 * @Description TODO
		 * @author suhg
		 * @version V1.0
		 * @date 2017年5月19日 上午11:21:45
		 */
		 @RequestMapping(value = "/inventoryLocation/getTree/{id}", method = RequestMethod.GET)
		    public ResultVo getTreeById(@PathVariable("id") Integer cid) {
			 ResultVo resultVo = new ResultVo();
				try {
					InventoryLocation location = inventoryLocationService.getTree(cid);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(location);
					return resultVo;
					// }
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find fail");
				return resultVo;
			 
			 
			 
			 
			/* InventoryLocation inventoryLocation = inventoryLocationService.getById(id);
				if (inventoryLocation == null) {
					String message = "对象不存在(id:" + id + ")";
					logger.warn(message);
				
				}
				return inventoryLocation;*/
		    }
		 
		 
			@RequestMapping(value = "/inventoryLocation/listForEdit", method = RequestMethod.GET)
			public ResultVo getAllForEdit() {
//				ResultVo res=new ResultVo();
//				try {
//					List<InventoryLocation> list = inventoryLocationService.getAll();
//					List<InventoryLocation> invenlist=new ArrayList<InventoryLocation>();
//					//List<InventoryLocation> reslist=new ArrayList<InventoryLocation>();
//					if (list == null) {
//						res.setErrCode(1);
//						res.setErrMsg("没有数据！");
//					}else{
//						for(int i=0;i<list.size();i++){
//							if(list.get(i).getParentName()==null){
//								InventoryLocation inventory=new InventoryLocation();
//								inventory=list.get(i);
//								invenlist.add(inventory);
//							}
//						}
//						for(int i=0;i<invenlist.size();i++){
//							List<InventoryLocation> child=new ArrayList<InventoryLocation>();
//							for(int m=0;m<list.size();m++){
//								if(list.get(m).getPid()==invenlist.get(i).getCid()){
//									child.add(list.get(m));
//									invenlist.get(i).setChildren(child);;
//								}
//							}
//							
//						}
//						for(int i=0;i<invenlist.size();i++){
//							for(int k=0;k<invenlist.get(i).getChildren().size();k++){
//								List<InventoryLocation> child=new ArrayList<InventoryLocation>();
//								for(int m=0;m<list.size();m++){
//								if(list.get(m).getPid()==invenlist.get(i).getChildren().get(k).getCid()){
//									child.add(list.get(m));
//									invenlist.get(i).getChildren().get(k).setChildren(child);
//								}
//							}
//							
//						}
//						}
//						for(int i=0;i<invenlist.size();i++){
//							for(int k=0;k<invenlist.get(i).getChildren().size();k++){
//								for(int g=0;g<invenlist.get(i).getChildren().get(k).getChildren().size();g++){
//									List<InventoryLocation> child=new ArrayList<InventoryLocation>();
//									for(int m=0;m<list.size();m++){
//										if(list.get(m).getPid()==invenlist.get(i).getChildren().get(k).getChildren().get(g).getCid()){
//											child.add(list.get(m));
//											invenlist.get(i).getChildren().get(k).getChildren().get(g).setChildren(child);
//										}
//									}
//							}
//						}
//					}
//						res.setResultData(invenlist);
//					}
//					
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//				
//				return res;
				ResultVo res=new ResultVo();
				ResultVo resultVo = new ResultVo();
				try {
					List<InventoryLocation> list = inventoryLocationService.getAllFirst();
					List<InventoryLocation> resList=new ArrayList<InventoryLocation>();
					if(list!=null&&list.size()>0){
						for(int i=0;i<list.size();i++){
							InventoryLocation location = inventoryLocationService.getTree(list.get(i).getCid());
							if(location!=null){
								resList.add(location);
							}
						}
					}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(resList);
					return resultVo;
					// }
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find fail");
				return resultVo;
			}
			
			
			
			@RequestMapping(value = "/inventoryLocation/listTreeForEdit", method = RequestMethod.GET)
			public ResultVo getAllTreeForEdit() {
				ResultVo res=new ResultVo();
				ResultVo resultVo = new ResultVo();
				try {
					List<InventoryLocation> list = inventoryLocationService.getAllFirst();
					List<InventoryLocation> resList=new ArrayList<InventoryLocation>();
					if(list!=null&&list.size()>0){
						for(int i=0;i<list.size();i++){
							InventoryLocation location = inventoryLocationService.getTree(list.get(i).getCid());
							if(location!=null){
								resList.add(location);
							}
						}
					}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(resList);
					return resultVo;
					// }
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find fail");
				return resultVo;
			}
			
			 @RequestMapping(value = "/locationPdfForPrint/{cid}", method = RequestMethod.GET)
				public ResultVo createPdfForPrint(@PathVariable("cid") Integer cid) throws Exception {
					ResultVo resultVo = new ResultVo();
						List<String> list=new ArrayList<String>();
						InventoryLocation location = inventoryLocationService.getById(cid);
						if(location!=null&&location.getPid()!=0){
							String pName = getAllPname(location.getCid());
							location.setParentName(pName);
							location.setLabel(location.getParentName()+">"+location.getLabel());
						}
						if(location!=null&&location.getQrPath()!=null){
							if(!new File(qrPath+location.getQrPath()).exists()){
								QRCreateAndParse qr = new QRCreateAndParse();
								qr.createQr(location.getBarcode(), qrPath,location.getQrPath());
							}
							location.setQrPath(qrUrlPath+location.getQrPath());
						}
							
						PdfUtil pdfUtil=new PdfUtil();
						// SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");  
						Map<String, Object> map =new HashMap<String, Object>();
						map.put("label", location.getLabel());
						if(location.getQrPath()!=null){
							String path=location.getQrPath();
							//if(location.getQrPath().indexOf("http")>=0){
								String picPath=qrPath+path.substring(path.lastIndexOf("/")+1,path.length());
								map.put("iamgePath", picPath);
							//}
						}
						String filename="print"+UUID.randomUUID().toString()+".pdf";
						String outUrl="src/main/resources/template/"+filename;
						if(pdfUtil.exportPdf(map, outUrl, "库存位置.pdf")){
							list.add(outUrl);
						}else{
							resultVo.setErrCode(1);	
							resultVo.setErrMsg("生成pdf报错1");
							return resultVo;
						}
							
					
						MergeUtil mergeUtil=new MergeUtil();
						String endName=UUID.randomUUID().toString()+".pdf";
						Boolean flag=mergeUtil.mergePdfFiles(list, pdfPath+"/"+endName);
						if(flag){

							String out=pdfLine+endName;
							map.put("out", out);
							map.put("qrPath", location.getQrPath());
							resultVo.setErrCode(0);
							resultVo.setResultData(map);
//							resultVo.setResultData(out);
						}else{
							resultVo.setErrCode(1);
							resultVo.setErrMsg("生成pdf报错2");
							return resultVo;
						}
					return resultVo;
				}
			 
			
			/**
			 * @Description 生成条形码
			 * @author xux
			 * @version V1.0
			 * @date 2017年5月18日 上午11:49:52
			 */
			public String createBarcode() {

				Random random = new Random();
				int a = random.nextInt(999999999) + 1;

				String barCode = "LX" + a;
				List<String> barCodes = inventoryLocationService.getBarCodes();
				boolean flat = true;
				flat = barCodes.contains(barCode);
				if (flat) {
					createBarcode();
				}
				return barCode;
			}
			
			public String getAllPname(Integer id) {
			    try {
			      String ss="";
			    loop:for(int i=0;i<10;i++){
			      InventoryLocation inventoryLocation =  inventoryLocationService.getById(id);
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

}
