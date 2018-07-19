package com.labwinner.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;






import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.ChemicalImage;
import com.labwinner.domain.Inventory;

public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static byte[] getBytes(File file) {
		byte[] buffer = null;
		if (file == null) {
			return buffer;
		} else {
			try {
				FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
				byte[] b = new byte[1000];
				int n;
				while ((n = fis.read(b)) != -1) {
					bos.write(b, 0, n);
				}
				fis.close();
				bos.close();
				buffer = bos.toByteArray();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer;
	}

	public static File byteToFile(byte[] buf, String filePath, String fileName) {

		BufferedOutputStream bufferOut = null;
		FileOutputStream fileOut = null;
		File file = null;
		try {
			File resFile = new File(filePath);
			if (!resFile.exists()) {
				resFile.mkdirs();
			}
			file = new File(filePath + fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileOut = new FileOutputStream(file);
			bufferOut = new BufferedOutputStream(fileOut);
			bufferOut.write(buf);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferOut != null) {
				try {
					bufferOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;

	}

	public List<String> imageUpload(MultipartFile[] files, String path) {
		
		List<String> imgType = new ArrayList<String>();
		imgType.add(".png");
		imgType.add(".jpg");
		imgType.add(".JPEG");
		imgType.add(".gif");
		imgType.add(".BMP");
		try {
			InputStream inputStream = files[0].getInputStream();
			String ss=convertStreamToString(inputStream);
			System.out.println(ss);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
//		 CommonsMultipartFile cf= (CommonsMultipartFile)files[0]; 
//	     DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
//        InputStream inputStream = fi.getInputStream();
		
		if (files.length == 0 || files.length < 0) {
			String message = "file is null";
			logger.error(message);
		}
		List<String> fileNameList = new ArrayList<String>();
		
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
			if(imgType.contains(suffixName)){
				// 文件上传路径
				// 解决中文问题，liunx下中文路径，图片显示问题
				fileName = UUID.randomUUID() + suffixName;
			}else{
				fileName = UUID.randomUUID() +fileName;
			}
			File dest = new File(path + fileName);
			// 检测是否存在目录
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			try {

				file.transferTo(dest);
				// 保存名字到数据库
				fileNameList.add(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileNameList;
	}
	
	
	  public String convertStreamToString(InputStream is) {    

		   BufferedReader reader = new BufferedReader(new InputStreamReader(is));    

		        StringBuilder sb = new StringBuilder();    

		     

		        String line = null;    

		        try {    

		            while ((line = reader.readLine()) != null) {    

		                sb.append(line + "/n");    

		            }    

		        } catch (IOException e) {    

		            e.printStackTrace();    

		        } finally {    

		            try {    

		                is.close();    

		            } catch (IOException e) {    

		                e.printStackTrace();    

		            }    

		        }    

		     

		        return sb.toString();    

		    }    

	
	public String uploadFile(MultipartFile file, String path) {
		List<String> imgType = new ArrayList<String>();
		imgType.add(".png");
		imgType.add(".jpg");
		imgType.add(".JPEG");
		imgType.add(".gif");
		imgType.add(".BMP");
		
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
		if(imgType.contains(suffixName)){
			// 文件上传路径
			// 解决中文问题，liunx下中文路径，图片显示问题
			fileName = UUID.randomUUID() + suffixName;
		}
		File dest = new File(path + fileName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {

			file.transferTo(dest);
			// 保存名字到数据库
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public String uploadVideoFile(MultipartFile file, String filePath) throws IOException {
		List<String> videoType = new ArrayList<String>();
		
//		 CommonsMultipartFile cf= (CommonsMultipartFile)file; 
//	     DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
		try {
			 InputStream inputStream = file.getInputStream();
			 getFile(inputStream,"e:\\aaa.txt");
		} catch (Exception e) {
			// TODO: handle exception
		}
       
		
		videoType.add(".mp4");
		videoType.add(".3gp");
		videoType.add(".asx");
		videoType.add(".asf");
		videoType.add(".mpg");
		videoType.add(".wmv");
		videoType.add(".mov");
		videoType.add(".avi");
		videoType.add(".flv");
		videoType.add(".amr");
		videoType.add(".m4a");
		videoType.add(".mp3");
		videoType.add(".wmr");

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
		if(videoType.contains(suffixName)){
			// 文件上传路径
			// 解决中文问题，liunx下中文路径，图片显示问题
			fileName = UUID.randomUUID() + suffixName;
		}
		File dest = new File(filePath + fileName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			
			file.transferTo(dest);
			// 保存名字到数据库
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public static void getFile(InputStream is,String fileName) throws IOException{
	    int index;
	    byte[] bytes = new byte[1024];
	    FileOutputStream downloadFile = new FileOutputStream(fileName);
	    while ((index = is.read(bytes)) != -1) {
	      downloadFile.write(bytes, 0, index);
	      downloadFile.flush();
	    }
	    downloadFile.close();
	    is.close();

	  }
	
	//信息广场语音上传
	public String uploadVoiceFile(MultipartFile file, String filePath) {
		List<String> voiceType = new ArrayList<String>();
		
		voiceType.add(".mp3");
		
		
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
		if(voiceType.contains(suffixName)){
			// 文件上传路径
			// 解决中文问题，liunx下中文路径，图片显示问题
			fileName = UUID.randomUUID() + suffixName;
		}
		File dest = new File(filePath + fileName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			
			file.transferTo(dest);
			// 保存名字到数据库
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public String uploadAppFile(MultipartFile file, String filePath) throws IOException {
		 CommonsMultipartFile cf= (CommonsMultipartFile)file; 
	     DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
        InputStream inputStream = fi.getInputStream();
		List<String> imgType = new ArrayList<String>();
		imgType.add(".png");
		imgType.add(".jpg");
		imgType.add(".JPEG");
		imgType.add(".gif");
		imgType.add(".BMP");
		
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
		if(imgType.contains(suffixName)){
			// 文件上传路径
			// 解决中文问题，liunx下中文路径，图片显示问题
			fileName = UUID.randomUUID() + suffixName;
		}
		File dest = new File(filePath + fileName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			
			file.transferTo(dest);
			// 保存名字到数据库
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public String getFileSize(String fpath){
        File path=new File(fpath);
        if(path.exists()){
            DecimalFormat df = new DecimalFormat("#.00");
            String sizeStr="";
            long size=0;
            try {
                FileInputStream fis = new FileInputStream(path);
                size=fis.available();
                fis.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "未知大小";
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "未知大小";
            }
            if(size<1024){
                sizeStr=size+"B";
            }else if(size<1048576){
                sizeStr=df.format(size/(double)1024)+"KB";
            }else if(size<1073741824){
                sizeStr=df.format(size/(double)1048576)+"MB";
            }else{
                sizeStr=df.format(size/(double)1073741824)+"GB";
            }
            return sizeStr;
        }else{
            return "未知大小";
        }
    }

}
