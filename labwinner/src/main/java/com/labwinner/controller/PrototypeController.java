package com.labwinner.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.Inventory;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.Prototype;
import com.labwinner.domain.SysUser;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.service.PrototypeService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.QRCreateAndParse;

@RestController
public class PrototypeController {

	private static Logger logger = LoggerFactory
			.getLogger(PrototypeController.class);

	@Autowired
	private PrototypeService prototypeService;
	@Autowired
	SysUserService sysUserService;
	@Autowired
	private InventoryLocationService inventoryLocService;
	
	@Value("${web.qr-path}")
	String qrPath;
	@Value("${web.qrUrl-path}")
	private String urlPath;
	
	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/prototype/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		Prototype prototype = prototypeService.getById(id);
		if (prototype != null) {
			//库存位置
			InventoryLocation location = prototype.getInventoryLocation();
			if(location!=null && location.getPid()!=0){
				String pName = getInventoryPname(location.getCid());
				location.setParentName(pName);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(prototype);
			return resultVo;
		}else{
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find fail");
			return resultVo;
		}
	}
	
	/**
	 * @Description 根据关键字获取对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/prototype/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {
		ResultVo resultVo = new ResultVo();
		
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != ""
				&& !"undefined".equals(keyword)) {
			List<Prototype> list = prototypeService.getKeyWord(keyword);

			if(list != null){
				for (Prototype prototype : list) {
					if(!new File(qrPath+prototype.getQrName()).exists()){
						QRCreateAndParse qr = new QRCreateAndParse();
						qr.createQr(prototype.getBarCode(), qrPath,prototype.getQrName());
					}
					prototype.setQrName(urlPath+prototype.getQrName());
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(list));
				return resultVo;
			}else{
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;
			}
		} else {
			List<Prototype> list = prototypeService.getAll();
			if(list != null){
				for (Prototype prototype : list) {
					if(!new File(qrPath+prototype.getQrName()).exists()){
						QRCreateAndParse qr = new QRCreateAndParse();
						qr.createQr(prototype.getBarCode(), qrPath,prototype.getQrName());
					}
					prototype.setQrName(urlPath+prototype.getQrName());
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(list));
				return resultVo;
			}else{
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;
			}
		}
	}
	
	@RequestMapping(value = "/prototypeAppList/{id}", method = RequestMethod.GET)
	public ResultVo prototypeAppList(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		List<Prototype> list = new ArrayList<Prototype>();
		
			list = prototypeService.getByProcessId(id);
		
				for (Prototype prototype : list) {
					if(!new File(qrPath+prototype.getQrName()).exists()){
						QRCreateAndParse qr = new QRCreateAndParse();
						qr.createQr(prototype.getBarCode(), qrPath,prototype.getQrName());
					}
					prototype.setQrName(urlPath+prototype.getQrName());
				}
				
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(list);
		return resultVo;
		
	}
	
	
	/**
	 * @Description 新增对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/prototype", method = RequestMethod.POST)
	public ResultVo save(@RequestBody Prototype prototype) {
		ResultVo resultVo = new ResultVo();
		if(prototype!=null){
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer userId = sysUser.getUserId();
			String barCode = createBarcode();
			prototype.setBarCode(barCode);
			QRCreateAndParse qr = new QRCreateAndParse();
			String qrName = qr.createQr(barCode, qrPath,null);
			prototype.setQrName(qrName);
			prototype.setComDate(new Date());
			prototype.setCreateDate(new Date());
			prototype.setCreater(userId+"");
			prototypeService.save(prototype);
			Integer id = prototype.getPrototypeId();
			Prototype prototype2 = prototypeService.getById(id);
			prototype2.setQrName(urlPath+prototype2.getQrName());
			//库存位置
			InventoryLocation location = prototype2.getInventoryLocation();
			if(location!=null&& location.getPid()!=0){
				String pName = getInventoryPname(location.getCid());
				location.setParentName(pName);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			resultVo.setResultData(prototype2);
			return resultVo;
		}else{
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save fail");
			return resultVo;
		}
		
	}
	
	/**
	 * @Description 更新对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/prototype", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody Prototype prototype) {
		ResultVo resultVo = new ResultVo();
		if(prototype!=null){
			LoginController login = new LoginController();
			SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
			Integer userId = sysUser.getUserId();
			prototype.setModifyDate(new Date());
			prototype.setModifier(userId+"");
			prototypeService.update(prototype);
			Integer id = prototype.getPrototypeId();
			Prototype prototype2 = prototypeService.getById(id);
			prototype2.setQrName(urlPath+prototype2.getQrName());
			//库存位置
			InventoryLocation location = prototype2.getInventoryLocation();
			if(location!=null&& location.getPid()!=0){
				String pName = getInventoryPname(location.getCid());
				location.setParentName(pName);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update success");
			resultVo.setResultData(prototype2);
			return resultVo;
		}else{
			resultVo.setErrCode(0);
			resultVo.setErrMsg("update fail");
			return resultVo;
		}
		
	}
	
	/**
	 * @Description 删除对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/prototype/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		Prototype prototype = prototypeService.getById(id);
		if(prototype!=null){
			String filename = prototype.getQrName();
			new File(qrPath+filename).delete();
		}
		prototypeService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("delete success");
		return resultVo;
		
	}
	
	/**
	 * @Description 扫描二维码
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/prototype/getByQR/{barcode}", method = RequestMethod.GET)
	public ResultVo scanQR(@PathVariable("barcode") String barcode) {

		ResultVo resultVo = new ResultVo();
		Prototype prototype = prototypeService.findByBarCode(barcode);
		if (null == prototype) {
			String message = "inventory is null";
			logger.error(message);
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find is null");
			return resultVo;
		}

		resultVo.setErrCode(0);
		resultVo.setErrMsg("find successe");
		resultVo.setResultData(prototype);
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

		String barCode = "XX" + a;
		List<String> barCodes = prototypeService.getBarCodes();
		boolean flat = true;
		flat = barCodes.contains(barCode);
		if (flat) {
			createBarcode();
		}
		return barCode;
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
}
