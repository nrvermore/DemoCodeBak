package com.labwinner.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.ChemicalImage;
import com.labwinner.domain.Inventories;
import com.labwinner.domain.Inventory;
import com.labwinner.domain.InventoryGroups;
import com.labwinner.service.ChemicalImageService;
import com.labwinner.vo.FileUploadVo;

/**
 * @Description LabWinner Application
 * @author liuhq
 * @version V1.0
 * @date 2017年7月3日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@RestController
public class FileController {

	private static Logger logger = LoggerFactory
			.getLogger(FileController.class);

	@Autowired
	private ChemicalImageService chemicalImageService;

	@Value("${web.upload-path}")
	String filePath;

	/**
	 * 服务端文件上传路径
	 */
	@Value("${server.upload.path}")
	String uploadPath;

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResultVo upload(@RequestParam("files") MultipartFile[] files,
			@RequestParam("id") Integer id) {
		if (files.length == 0 || files.length < 0) {
			return null;
		}
		if (null == id || 0 == id) {
			return null;
		}

		// 删除文件夹中图片文件
		List<String> filePathList = chemicalImageService.getByInventoryId(id);
		if (filePathList.size() <= 0) {
			String msg = "the filelist is null";
			logger.error(msg);
		}
		for (String fileName : filePathList) {
			new File(filePath + fileName).delete();
		}

		// 删除数据库中图片信息
		chemicalImageService.deleteById(id);

		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				String message = "file is null";
				logger.error(message);
			}
			// 获取文件名
			String fileName = file.getOriginalFilename();
			logger.info("上传的文件名为：" + fileName);
			// 获取文件的后缀名
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			logger.info("上传的后缀名为：" + suffixName);
			// 文件上传路径
			// String filePath = "d:/roncoo/ttt/";
			// 解决中文问题，liunx下中文路径，图片显示问题
			fileName = UUID.randomUUID() + suffixName;
			File dest = new File(filePath + fileName);
			// 检测是否存在目录
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			try {

				file.transferTo(dest);
				// 保存名字到数据库
				ChemicalImage chemicalImage = new ChemicalImage();
				InventoryGroups inventoryGroups = new InventoryGroups();
				inventoryGroups.setGroupId(id);
				chemicalImage.setInventoryGroups(inventoryGroups);
				chemicalImage.setDissolvantDescribe(fileName);
				chemicalImageService.save(chemicalImage);

			} catch (IllegalStateException e) {
				e.printStackTrace();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ResultVo resultVo = new ResultVo();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("success");
		return resultVo;

	}

	// 文件上传相关代码
	@RequestMapping(value = "/upload", headers = "content-type=multipart/*", method = RequestMethod.POST)
	@ResponseBody
	public FileUploadVo upload(@RequestParam("file") MultipartFile file) {
		FileUploadVo fileUploadVo = new FileUploadVo();
		if (file.isEmpty()) {
			fileUploadVo.setCode(0);
			fileUploadVo.setMsg("文件为空");
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传的文件名为：" + fileName);
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);
		// 文件上传后的路径
		String filePath = uploadPath;
		// 解决中文问题，liunx下中文路径，图片显示问题
		fileName = UUID.randomUUID() + "";
		File dest = new File(filePath + fileName + suffixName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			fileUploadVo.setCode(1);
			fileUploadVo.setFilePath(fileName + suffixName);
			fileUploadVo.setMsg("上传成功");
			return fileUploadVo;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileUploadVo.setCode(2);
		fileUploadVo.setMsg("上传失败");
		return fileUploadVo;
	}

	// 文件下载相关代码
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	// @ResponseBody
	public String downloadFile(
			org.apache.catalina.servlet4preview.http.HttpServletRequest request,
			HttpServletResponse response, @RequestBody FileUploadVo fileUploadVo) {
		// request.getParameter("fileUploadVo");
		String fileName = fileUploadVo.getFileName();
		if (fileName != null) {
			// 当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
			// String realPath =
			// request.getServletContext().getRealPath("//WEB-INF//");
			File file = new File(uploadPath, fileUploadVo.getFilePath());
			if (file.exists()) {
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.addHeader("Content-Disposition",
						"attachment;fileName=" + fileName);// 设置文件名
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
					logger.info("success");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return null;
	}

	@RequestMapping("/downloadpath")
	public HttpServletResponse download(HttpServletResponse response) {
		try {
			String path = "D:/labwinner/uploads/4dd76d29-90ee-4782-9b8d-89a45ed63e3d.docx";
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 取得文件的后缀名。
			String ext = filename.substring(filename.lastIndexOf(".") + 1)
					.toUpperCase();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return response;
	}

	/**
	 * 下载本地文件
	 * 
	 * @param response
	 * @throws FileNotFoundException
	 */
	// 文件下载相关代码
	@RequestMapping("/downloadLocal")
	public void downloadLocal(HttpServletResponse response)
			throws FileNotFoundException {
		// 下载本地文件
		String fileName = "Operator.doc".toString(); // 文件的默认保存名
		// 读到流中
		InputStream inStream = new FileInputStream(
				"D:/labwinner/uploads/4dd76d29-90ee-4782-9b8d-89a45ed63e3d.docx");// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 文件下载相关代码
	@RequestMapping("/view")
	public String view(
			org.apache.catalina.servlet4preview.http.HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String fileName = "Chrysanthemum.jpg";
		if (fileName != null) {
			// 当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
			// String realPath = request.getServletContext().getRealPath(
			// "//WEB-INF//");
			String realPath = uploadPath;
			File file = new File(realPath, fileName);
			if (file.exists()) {
				response.setContentType("text/html; charset=UTF-8");
				response.setContentType("image/jpeg");
				String newpath = new String(fileName.getBytes("ISO-8859-1"),
						"UTF-8");
				String absolutePath = realPath + newpath;
				FileInputStream fis = new FileInputStream(absolutePath);
				OutputStream os = response.getOutputStream();
				try {
					int count = 0;
					byte[] buffer = new byte[1024 * 1024];
					while ((count = fis.read(buffer)) != -1)
						os.write(buffer, 0, count);
					os.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (os != null)
						os.close();
					if (fis != null)
						fis.close();
				}
			}
		}
		return null;
	}

	// 多文件上传方法一 下载到该工程的所在目录下
	@RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request)
				.getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(
							new File(file.getOriginalFilename())));
					stream.write(bytes);
					stream.close();

				} catch (Exception e) {
					stream = null;
					return "You failed to upload " + i + " => "
							+ e.getMessage();
				}
			} else {
				return "You failed to upload " + i
						+ " because the file was empty.";
			}
		}
		return "upload successful";
	}

	// 多文件上传方法二
	@RequestMapping(value = "/batchupload", method = RequestMethod.POST)
	@ResponseBody
	public String batchFileUp(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request)
				.getFiles("file");
		// String contextPath =
		// request.getSession().getServletContext().getRealPath("/")+
		// "\\uploadsTest";//可以将文件存放在这个目录下 是当前工程下的uploadtest目录下
		// logger.info(contextPath);
		// File tempFile = new File(contextPath);
		// if(!tempFile.exists())
		// {
		// tempFile.mkdir();
		// }
		for (int i = 0; i < files.size(); i++) {
			MultipartFile file = files.get(i);
			logger.info(file.getContentType());
			String fileName = file.getOriginalFilename();
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			String fullfilePath = uploadPath + fileName;// 自己设定的文件目录
			logger.info("fullfilePath:", fullfilePath);
			if (!file.isEmpty()) {
				try {
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(fullfilePath)));
					stream.write(file.getBytes());
					stream.close();
				} catch (Exception e) {
					return "Failed to Upload" + fileName + "=>"
							+ e.getMessage();
				}
			} else {
				return "Failed to Upload " + fileName
						+ "because the file was empty";
			}

		}
		return "Upload Success!";
	}
	
	
	    @RequestMapping(value = "/download.do")
	    public void downloadAction(String fileName,String filePath,HttpServletRequest request, HttpServletResponse response) throws Exception {
		  response.setContentType("text/html;charset=utf-8");
	        response.setCharacterEncoding("utf-8");
	        try {
	            // 得到要下载的文件
	            File file = new File(filePath);
	            //如果文件不存在，则显示下载失败
	            if (!file.exists()) {
	                request.setAttribute("message", "下载失败");
	                return;
	            } else {
	                // 设置相应头，控制浏览器下载该文件，这里就是会出现当你点击下载后，出现的下载地址框
	                response.setHeader("content-disposition",
	                        "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
	                // 读取要下载的文件，保存到文件输入流
	                FileInputStream in = new FileInputStream(filePath);
	                // 创建输出流
	                OutputStream out = response.getOutputStream();
	                // 创建缓冲区
	                byte buffer[] = new byte[1024];
	                int len = 0;
	                // 循环将输入流中的内容读取到缓冲区中
	                while ((len = in.read(buffer)) > 0) {
	                    // 输出缓冲区内容到浏览器，实现文件下载
	                    out.write(buffer, 0, len);
	                }
	                // 关闭文件流
	                in.close();
	                // 关闭输出流
	                out.close();
	            }
	        } catch (Exception e) {
	            // TODO: handle exception

	        }
	    }

}
