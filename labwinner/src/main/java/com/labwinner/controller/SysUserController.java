package com.labwinner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.SysUser;
import com.labwinner.service.JournalArticleService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.Base64Util;
import com.labwinner.util.FileUtil;
import com.labwinner.util.MD5Util;
import com.labwinner.vo.SysUserVo;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description 用户Controller
 * @author liuhq
 * @version V1.0
 * @date 2017年5月18日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class SysUserController {

	private static Logger logger = LoggerFactory
			.getLogger(SysUserController.class);

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private JournalArticleService journalArticleService;

	@Value("${sysUserPhone.upload-path}")
	String filePath;

	@Value("${sysUserPhone.url-path}")
	String urlPath;

	/**
	 * 服务端文件上传路径
	 */
	@Value("${server.upload.path}")
	String uploadPath;
	
	/*@Value("${sysUserPhone.url-path}")
	String sysUserPath;*/
	

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	@RequestMapping(value = "/sysUser", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUser> getAll() {
		 List<SysUser> list = sysUserService.getAll();
			if (list == null) {
				String message = "没有对象";
				logger.warn(message);
				
			}
			return list;
	}
	
	@RequestMapping(value = "/sysUser/appGetAll", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo appGetAll() {
		ResultVo resultVo = new ResultVo();
		 List<SysUser> list = sysUserService.getAll();
			for (SysUser sysUser : list) {
				if(sysUser.getUserImage()!=null && !"".equals(sysUser.getUserImage())){
					sysUser.setUserImage(urlPath+sysUser.getUserImage());
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(list);
			return resultVo;
	}
	
	/**
	 * @Description 获取所有对象名称
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	@RequestMapping(value = "/sysUser/getUserName", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUser> getUserName() {
		List<SysUser> list = sysUserService.getUserName();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
			
		}
		return list;
	}

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	@RequestMapping(value = "/sysUserGetProList", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUser> getProList() {
		return sysUserService.getProList();
	}
	
	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	@RequestMapping(value = "/sysUserGetProRoleAll", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUser> getProRoleAll() {
		return sysUserService.getProRoleAll();
	}

	/**
	 * @Description 获取所有对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	@RequestMapping(value = "/sysUserAllPhone", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUser> getAllPhone() {
		return sysUserService.getAllPhone();
	}

	/**
	 * @Description 根据查询条件获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	@RequestMapping(value = "/sysUserPageable/{page}/{pageSize}/{filter}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String filter) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (filter != null && filter != "" && !"undefined".equals(filter)) {
			List<SysUser> sysUsers = sysUserService.getAllPageable(filter);
			for (SysUser sysUser : sysUsers) {
				String fileName=sysUser.getUserImage();
				if(fileName!=null&&!"".equals(fileName)){
					sysUser.setUserImage(urlPath+fileName);
				}
				
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(sysUsers));
			return resultVo;
		} else {
			List<SysUser> sysUsers = sysUserService.getAll();
			for (SysUser sysUser : sysUsers) {
				String fileName=sysUser.getUserImage();
				if(fileName!=null&&!"".equals(fileName)){
					sysUser.setUserImage(urlPath+fileName);
				}
				//sysUser.setUserImage(urlPath+fileName);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(sysUsers));
			return resultVo;
		}
	}

	@RequestMapping(value = "/sysUser/{id}", method = RequestMethod.GET)
	public SysUser getById(@PathVariable("id") Long id) {
		SysUser sysUser=sysUserService.getById(id);
		String fileName=sysUser.getUserImage();
		if(fileName!=null&&!"".equals(fileName)){
			sysUser.setUserImage(urlPath+fileName);
		}else{
			sysUser.setUserImage(urlPath+"appmoren.png");
		}
		return sysUser;
	}
	
	@RequestMapping(value = "/sysUserWeb/{id}", method = RequestMethod.GET)
	public SysUser getByIdWeb(@PathVariable("id") Long id) {
		SysUser sysUser=sysUserService.getById(id);
		String fileName=sysUser.getUserImage();
		if(fileName!=null&&!"".equals(fileName)){
			sysUser.setUserImage(urlPath+fileName);
		}
		return sysUser;
	}

	@RequestMapping(value = "/sysUser", method = RequestMethod.POST)
	public ResultVo save(@RequestBody SysUserVo sysUserVo) {
		ResultVo resultVo = new ResultVo();
			Base64Util base64Util = new Base64Util();
			MD5Util md5Util = new MD5Util();
			SysUser sysUser = sysUserVo.getSysUser();
			sysUser.setCreateDate(new Date());
		    String passWord=sysUser.getPassword();
			String userState=sysUser.getUserState();
			String url = sysUserVo.getUrl();
			
			   if(url==null || "".equals(url)){
				  sysUser.setPassword(md5Util.encode(passWord));
				   sysUserService.saveNoImage(sysUser);
				   Integer userId=sysUser.getUserId();
				    resultVo.setErrCode(0);
					resultVo.setErrMsg("save successe");
					resultVo.setResultData(userId);
					return resultVo;
			   }else if (url!=null && !"".equals(url)) {
				   url = url.substring(url.indexOf(",") + 1);
					String fileName=base64Util.GenerateImage(url, filePath);
					sysUser.setUserState(userState);
					sysUser.setUserImage(fileName);
					sysUser.setPassword(md5Util.encode(passWord));
					sysUserService.save(sysUser);
					Integer userId=sysUser.getUserId();
					resultVo.setErrCode(0);
					resultVo.setErrMsg("save successe");
					resultVo.setResultData(userId);
					return resultVo;
			}else {
				    resultVo.setErrCode(1);
				    resultVo.setErrMsg("save fail");
				    return resultVo;
			}
				
			
 }

	
	@RequestMapping(value = "/sysUser", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody SysUserVo sysUserVo) {
		
		ResultVo resultVo = new ResultVo();

		Base64Util base64Util = new Base64Util();
		SysUser sysUser = sysUserVo.getSysUser();
		Long userId=Long.valueOf(String.valueOf(sysUser.getUserId()));
		SysUser sysUser2=sysUserService.getById(userId);
		String oldPssWord=sysUser2.getPassword();
		String url = sysUserVo.getUrl();
		MD5Util md5Util = new MD5Util();
	    String passWord=sysUser.getPassword();
		     if (url==null || "".equals(url)) { // 不修改头像
		    	   String userImage=sysUser.getUserImage();
		    	   String fileName=null;
		    	   if(fileName!=null&&!"".equals(fileName)){
		    		   fileName=userImage.substring(userImage.lastIndexOf("/")+1);  
		    	   }
		    	   sysUser.setUserImage(fileName);
//		    	   if(passWord.equals(oldPssWord)){
		    	 if(passWord==null || "".equals(passWord)){
		    		   sysUser.setPassword(oldPssWord);
		    	   }else {
		    		   sysUser.setPassword(md5Util.encode(passWord));
				   }
			       sysUserService.update(sysUser);
			       resultVo.setErrCode(0);
			       resultVo.setErrMsg("update successe");
			       return resultVo;
		    } else if (url!=null && !"".equals(url)) { //修改头像及信息
			// 删除旧图片
			        String userImage=sysUser.getUserImage();
			        if(userImage!=null && !"".equals(userImage)){
			        	 userImage=userImage.substring(userImage.lastIndexOf("/")+1);
							new File(filePath + userImage).delete();
			        }
			       
					// 删除数据库中图片信息
					url = url.substring(url.indexOf(",") + 1);
					String fileName=base64Util.GenerateImage(url, filePath);
					sysUser.setUserImage(fileName);
//					if(passWord.equals(oldPssWord)){
					if(passWord==null || "".equals(passWord)){
			    		sysUser.setPassword(oldPssWord);
			    	}else {
			    		sysUser.setPassword(md5Util.encode(passWord));
					}
					sysUserService.update(sysUser);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("update successe");
					return resultVo;
				}else {
					resultVo.setErrCode(1);
					resultVo.setErrMsg("update fail");
					return resultVo;
				}
			// 保存上传图片
//		String fileName=base64Util.GenerateImage(url, filePath);
//		sysUser.setUserImage(urlPath+fileName);
//		sysUserService.update(sysUser);
	} 

	@RequestMapping(value = "/sysUser/updateState", method = RequestMethod.PUT)
	public ResultVo updateState(@RequestBody SysUser sysUser) {
		
		ResultVo resultVo = new ResultVo();
		Integer tag=sysUser.getTag();
		    
		   if(tag!=null && tag==1){
			    sysUser.setUserState("1");
				sysUserService.updateState(sysUser);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("此用户可正常使用！！！");
				return resultVo;
		   }else if (tag!=null && tag==2) {
			    sysUser.setUserState("2");
				sysUserService.updateState(sysUser);
				resultVo.setErrCode(1);
				resultVo.setErrMsg("此用户已禁用！！！");
				return resultVo;
		   }else {
			    resultVo.setErrCode(2);
				resultVo.setErrMsg("改变用户状态失败！！！");
				return resultVo;
		}
		
				
			
	} 
	@RequestMapping(value = "/sysUser/updatePersonal", method = RequestMethod.PUT)
	public ResultVo updatePersonal(@RequestBody SysUserVo sysUserVo) {
		
		ResultVo resultVo = new ResultVo();
			MD5Util md5Util=new MD5Util();
		Base64Util base64Util = new Base64Util();
		SysUser sysUser = sysUserVo.getSysUser();
		String url = sysUserVo.getUrl();
//		String password=sysUser.getPassword();
//		String newPassWord=md5Util.encode(password);
		     if (url==null || "".equals(url)) { // 不修改头像
		    	   String userImage=sysUser.getUserImage();
		    	   String fileName=null;
		    	   if(userImage!=null&&!"".equals(userImage)){
		    		   fileName=userImage.substring(userImage.lastIndexOf("/")+1);
		    	   }
		    	   sysUser.setUserImage(fileName);
//		    	   sysUser.setPassword(newPassWord);
			       sysUserService.updatePersonal(sysUser);
			       resultVo.setErrCode(0);
			       resultVo.setErrMsg("update successe");
			       return resultVo;
		    } else if (url!=null && !"".equals(url)) { //修改头像及信息
			// 删除旧图片
			        String userImage=sysUser.getUserImage();
			        if(userImage!=null &&!"".equals(userImage)){
			        	 userImage=userImage.substring(userImage.lastIndexOf("/")+1);
							new File(filePath + userImage).delete();
			        }
			       
					// 删除数据库中图片信息
					url = url.substring(url.indexOf(",") + 1);
					String fileName=base64Util.GenerateImage(url, filePath);
					sysUser.setUserImage(fileName);
//					sysUser.setPassword(newPassWord);
					sysUserService.updatePersonal(sysUser);
					resultVo.setErrCode(0);
					resultVo.setErrMsg("update successe");
					return resultVo;
				}else {
					resultVo.setErrCode(1);
					resultVo.setErrMsg("update fail");
					return resultVo;
				}
		
	}
		
		
	@RequestMapping(value = "/sysUser/updateAppPersonal", method = RequestMethod.POST)
	public SysUser updateAppPersonal(
			@RequestParam(value = "file", required = false) MultipartFile file ,
			@RequestParam(value = "realname", required = false) String realname,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "userImage", required = false) String userImage,
			@RequestParam(value = "qq", required = false) String qq,
			@RequestParam(value = "weixin", required = false) String weixin, 
	        @RequestParam(value = "blog", required = false) String blog){
		
//		ResultVo resultVo = new ResultVo();
		FileUtil fileUtil=new FileUtil();
		 LoginController login = new LoginController();
		 //获取登录用户
		 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Long userId = Long.valueOf(String.valueOf(sysUser.getUserId()));
		//SysUser sysUser=sysUserService.getById(userId);
//		MultipartFile file = sysUserAppVo.getFile();
//	    String realname=sysUserAppVo.getRealname();
//        String phone=sysUserAppVo.getPhone();
//        String email=sysUserAppVo.getEmail();
//        String userImage=sysUserAppVo.getUserImage();
//        String qq=sysUserAppVo.getQq();
//        String weixin=sysUserAppVo.getWeixin();
//        String blog=sysUserAppVo.getBlog();
//		
		if (file==null || "".equals(file)) { // 不修改头像
			sysUser.setRealname(realname);
			sysUser.setPhone(phone);
			sysUser.setEmail(email);
			sysUser.setQq(qq);
			sysUser.setWeixin(weixin);
			sysUser.setBlog(blog);
			sysUserService.updateAppPersonal(sysUser);
//			resultVo.setErrCode(0);
//			resultVo.setErrMsg("update successe");
//			resultVo.setResultData(sysUser);
	//		return sysUser;
		} else if (file!=null && !"".equals(file)) { //修改头像及信息
			// 删除旧图片
			String userImage2=sysUser.getUserImage();
			if(userImage2!=null && !"".equals(userImage2)){
				// userImage=userImage.substring(userImage.indexOf("Phone")+6);
				new File(filePath + userImage2).delete();
			}
			
			// 删除数据库中图片信息
			//file = file.substring(file.indexOf(",") + 1);
			String fileName= fileUtil.uploadFile(file, filePath);
			//String fileName=base64Util.GenerateImage(file, filePath);
			sysUser.setUserImage(fileName);
			sysUser.setRealname(realname);
			sysUser.setPhone(phone);
			sysUser.setEmail(email);
			sysUser.setQq(qq);
			sysUser.setWeixin(weixin);
			sysUser.setBlog(blog);
			sysUserService.updateAppPersonal(sysUser);
//			resultVo.setErrCode(0);
//			resultVo.setErrMsg("update successe");
//			resultVo.setResultData(sysUser);
			return sysUser;
		}
		return sysUser;
		
		
	}
	
	
		
		/*    try {
				sysUserService.updatePersonal(sysUser);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("个人设置成功！！！");
				return resultVo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("个人设置失败！！！");
				return resultVo;
			}	
	} */
	
	
	@RequestMapping(value = "/sysUser/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Long id) {
		ResultVo resultVo = new ResultVo();
		
		try {
			sysUserService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("删除成功！！！");
			return resultVo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("删除失败！！！");
			return resultVo;
		}
	}
	

	@RequestMapping(value = "/sysUser/updatePassWord", method = RequestMethod.PUT)
	public ResultVo updatePassWord(@RequestBody SysUser sysUser) {
		
		        ResultVo resultVo = new ResultVo();
		
		        MD5Util md5Util=new MD5Util();
		        //要修改的对象Id
				Long userId=Long.valueOf(String.valueOf(sysUser.getUserId()));
				//根据userId获取原始对象
				SysUser sysUser2=sysUserService.getById(userId);
			    //获取原始密码
				String oldPassWord=sysUser2.getPassword();
				//前段输入的原始密码
				String testPassWord=sysUser.getTestPassWord();
				
				String testPassWord2=md5Util.encode(testPassWord);
		       //修改的新密码
				String passWord=sysUser.getPassword();
				passWord=md5Util.encode(passWord);
		if(testPassWord2.equals(oldPassWord)){
			if(passWord.equals(oldPassWord)){
				resultVo.setErrCode(2);
				resultVo.setErrMsg("新密码不能与原始密码相同！");
				return resultVo;
			}else {
				sysUser.setPassword(passWord);
				sysUserService.updatePassWord(sysUser);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("密码修改成功！");
				return resultVo;
			}
			
		}else {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("原始密码输入不正确，请重新输入......");
			return resultVo;
		}
			
		
	}
	@RequestMapping(value = "/sysUser/updateAppPassWord", method = RequestMethod.POST)
	public ResultVo updateAppPassWord(@RequestBody SysUser sysUser) {
		
		ResultVo resultVo = new ResultVo();
		MD5Util md5Util=new MD5Util();
		//要修改的对象Id
		Long userId=Long.valueOf(String.valueOf(sysUser.getUserId()));
		//根据userId获取原始对象
		SysUser sysUser2=sysUserService.getById(userId);
	    //获取原始密码
		String oldPassWord=sysUser2.getPassword();
		//前段输入的原始密码
		String testPassWord=sysUser.getTestPassWord();
		
		String testPassWord2=md5Util.encode(testPassWord);
		//修改的新密码
		String newPassword=sysUser.getPassword();
		newPassword=md5Util.encode(newPassword);
		if(testPassWord2.equals(oldPassWord)){
			if(newPassword.equals(oldPassWord)){
				resultVo.setErrCode(2);
				resultVo.setErrMsg("新密码不能与原始密码相同！");
				return resultVo;
			}else {
				sysUser.setPassword(newPassword);
				sysUserService.updatePassWord(sysUser);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("密码修改成功！");
				return resultVo;
			}
		}else {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("原始密码输入不正确，请重新输入......");
			return resultVo;
		}
		
		
	}

	/**
	 * @param 下载用户头像
	 * @param response
	 * @param fileUploadVo
	 * @return
	 */
/*	@RequestMapping(value = "/sysUser/download", method = RequestMethod.GET)
	public String downloadFile(){
			org.apache.catalina.servlet4preview.http.HttpServletRequest request,
			HttpServletResponse response, @RequestBody FileUploadVo fileUploadVo) {
		SysUser sysUser = sysUserService.getById(fileUploadVo.getId());
		response.setContentType("application/force-download");
		response.addHeader("Content-Disposition", "attachment;fileName="+(filePath+sysUser.getUserImage()));
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		String fileName =filePath+sysUser.getUserImage();
		return fileName;
	
	}*/

	/**
	 * @param byte 转文件
	 * @param filePath
	 * @param fileName
	 * @return
	 */
	public static File byte2File(byte[] buf, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			if (!dir.exists() && dir.isDirectory()) {
				dir.mkdirs();
			}
			file = new File(filePath + File.separator + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(buf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}
	
	
	
	@RequestMapping(value = "/sysUser/getProductUserList/{keyword}", method = RequestMethod.GET)
	public ResultVo getProductUserList(@PathVariable("keyword") String keyword) {
		
		        ResultVo resultVo = new ResultVo();
		        LoginController login = new LoginController();
				 //获取登录用户
				 SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		        try {
		        	List<Map<String, Object>> productUserList=new ArrayList<Map<String,Object>>();
		        	if(keyword != null && keyword != "" && !"undefined".equals(keyword)){
		        		productUserList=journalArticleService.getUserByKeywordList(keyword);
		        	}else{
		        		productUserList= journalArticleService.getProductUserList();
		        	}
		        	
		        	resultVo.setErrCode(0);
		        	resultVo.setResultData(productUserList);
				} catch (Exception e) {
					resultVo.setErrCode(1);
				}
		
		        return resultVo;
			
		
	}
	
	/**
	 * @Description 根据查询条件获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日
	 */
	@RequestMapping(value = "/sysUser/getBykeyword/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getUserByKeyword( @PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<SysUser> sysUsers = sysUserService.getByKeyword(keyword);
			for (SysUser sysUser : sysUsers) {
				String fileName=sysUser.getUserImage();
				if(fileName!=null&&!"".equals(fileName)){
					sysUser.setUserImage(urlPath+fileName);
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(sysUsers);
			return resultVo;
		} else {
			List<SysUser> sysUsers = sysUserService.getAll();
			for (SysUser sysUser : sysUsers) {
				String fileName=sysUser.getUserImage();
				if(fileName!=null&&!"".equals(fileName)){
					sysUser.setUserImage(urlPath+fileName);
				}
				//sysUser.setUserImage(urlPath+fileName);
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(sysUsers);
			return resultVo;
		}
	}
	

}
