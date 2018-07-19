package com.labwinner.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.ChemicalImage;
import com.labwinner.service.ChemicalImageService;

@RestController
public class ChemicalImageController {

	private static Logger logger = LoggerFactory
			.getLogger(ChemicalImageController.class);

	@Autowired
	private ChemicalImageService chemicalImageService;
	@Value("${web.url-path}")
	private String urlPath;
	
	@Value("${web.upload-path}")
	String filePath;

	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/chemicalImage/list/", method = RequestMethod.GET)
	public List<ChemicalImage> getAll() {
		List<ChemicalImage> list = chemicalImageService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}


//	/**
//	 * @Description 保存/更新对象方法
//	 * @author liuhq
//	 * @version V1.0
//	 * @date 2017年5月18日 上午11:51:21
//	 */
//	@RequestMapping(value = "/chemicalImage/add", method = RequestMethod.POST)
//	public String save(@PathVariable("uploadfile") List<MultipartFile> files ,Integer id) {
//			
//		byte[] imageBytes = null;
//	    
//	    try {
//	    		MultipartFile file = null;
//	    	    BufferedOutputStream stream = null;
//	    		for(int i = 0; i < files.size(); ++i){
//	    			file = files.get(i);
//	    			if (!files.isEmpty()) {
//					    imageBytes =file.getBytes();
//					    stream =new BufferedOutputStream(new FileOutputStream(new File("uploaded")));              
//			            stream.write(imageBytes);
//			            stream.close();
//			            
//			            ChemicalImage chemicalImage = new ChemicalImage();
//			            Inventory inventory = new Inventory();
//			            inventory.setInventoryId(id);
//			            chemicalImage.setDissolvantImage(imageBytes);
//			            chemicalImage.setInventory(inventory);
//			            chemicalImage.setDissolvantDescribe(file.getOriginalFilename());  
//						chemicalImageService.save(chemicalImage);
//		    			}
//	    			}
//				return "save success";
//		} catch (Exception e) {
//			e.printStackTrace();
//				return "save fail";
//		}
//	}
	
	/**
	 * get image method
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/chemicalImage/getImage/{id}", method = RequestMethod.GET)
	public ResultVo getList(@PathVariable("id") Integer id){
		ResultVo resultVo = new ResultVo();
		List<String> imageStrs = chemicalImageService.getByInventoryId(id);
		if(null==imageStrs){
			resultVo.setErrCode(2);
			resultVo.setErrMsg("get files success");
			return resultVo;
		}
		ArrayList<Object> urls = new ArrayList<Object>();
		for(String imagestr :imageStrs){
			String URL = urlPath+imagestr;
			urls.add(URL);
			
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("get files success");
		resultVo.setResultList(urls);;
		return resultVo;
		
	}
}
