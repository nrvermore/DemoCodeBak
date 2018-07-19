package com.labwinner.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.labwinner.domain.KnowledgeAcc;
import com.labwinner.domain.KnowledgeClassify;
import com.labwinner.domain.SysUser;
import com.labwinner.service.KnowledgeAccService;
import com.labwinner.service.SysUserService;
import com.owtelse.codec.Base64;
  
public class Base64Util   
{  
    //图片转化成base64字符串  
    public String getImageStr(File imgFile)  
    {
    	//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()]; 
            in.read(data);  
           String imgStr =  Base64.encode(data);
            in.close();  
            return imgStr;
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
       
			return null;
    }  
    
    //base64字符串转化成图片  
    public  String GenerateImage(String imgStr,String filePath)  
    {   
    	//对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null){ 
        //图像数据为空  
         //   return "imgStr is null";  
        }
        try   
        {  
            //Base64解码  
            byte[] b = Base64.decode(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String suffixName = ".png";
		     // 文件上传路径
		     // 解决中文问题，liunx下中文路径，图片显示问题
		     String fileName = UUID.randomUUID() + suffixName;
		     String imgFilePath = filePath + fileName;
		     File dest = new File(imgFilePath);
		     // 检测是否存在目录
		     if (!dest.getParentFile().exists()) {
		         dest.getParentFile().mkdirs();
		     }
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return fileName;  
        }   
        catch (Exception e)   
        {  
        	e.printStackTrace();
            return "exception";  
        }  
    }  
    
    
    //base64字符串转化成图片  
    public  String GenerateTiffImage(String imgStr,String filePath)  
    {   
    	//对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null){ 
        //图像数据为空  
         //   return "imgStr is null";  
        }
        try   
        {  
            //Base64解码  
            byte[] b = Base64.decode(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String suffixName = ".tif";
            UUID uuId = UUID.randomUUID();
		     // 文件上传路径
		     // 解决中文问题，liunx下中文路径，图片显示问题
		     String fileName = uuId + suffixName;
		     
		     String pngName = uuId +".png";
		     
		     String pngFilePath = filePath + pngName;
		     
		     String imgFilePath = filePath + fileName;
		     File dest = new File(imgFilePath);
		     // 检测是否存在目录
		     if (!dest.getParentFile().exists()) {
		         dest.getParentFile().mkdirs();
		     }
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            final BufferedImage tif = ImageIO.read(new File(imgFilePath));
            ImageIO.write(tif, "png", new File(pngFilePath));
            return pngName;  
        }   
        catch (Exception e)   
        {  
        	e.printStackTrace();
            return "exception";  
        }  
    }  
    
  //base64字符串转化成文件  
    public  String GenerateFile(String fileStr,String filePath,String filename)  
    {   
    	List<String> imgType = new ArrayList<String>();
		imgType.add(".png");
		imgType.add(".PNG");
		imgType.add(".jpg");
		imgType.add(".JPG");
		imgType.add(".JPEG");
		imgType.add(".jpeg");
		imgType.add(".gif");
		imgType.add(".GIF");
		imgType.add(".BMP");
		imgType.add(".bmp");
		
    	//对字节数组字符串进行Base64解码并生成图片  
        if (fileStr == null){ 
        //图像数据为空  
         //   return "imgStr is null";  
        }
        try   
        {  
            //Base64解码  
            byte[] b = Base64.decode(fileStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String suffixName = filename.substring(filename.lastIndexOf("."));
            if(imgType.contains(suffixName)){
				// 文件上传路径
				// 解决中文问题，liunx下中文路径，图片显示问题
            	filename = UUID.randomUUID() + suffixName;
			}
            else
            {
            	filename = UUID.randomUUID()+ filename;
            }
		     // 文件上传路径
		     // 解决中文问题，liunx下中文路径，图片显示问题
		     String imgFilePath = filePath + filename;
		     File dest = new File(imgFilePath);
		     // 检测是否存在目录
		     if (!dest.getParentFile().exists()) {
		         dest.getParentFile().mkdirs();
		     }
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return filename;  
        }   
        catch (Exception e)   
        {  
        	e.printStackTrace();
            return "exception";  
        }  
    }  
    
    //base64字符串转化成图片  
    public  String GeneratePdf(String pdfStr,String filePath,String pdfName)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        if (pdfStr == null){ //图像数据为空  
         //   return "imgStr is null";  
        }
        try   
        {  
            //Base64解码  
            byte[] b = Base64.decode(pdfStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
//            String imgFilePath = "d://QR//5.jpg";//新生成的图片  
            //String suffixName = ".pdf";
		     // 文件上传路径
		   //  String filePath = "d:/roncoo/ttt/";
		     // 解决中文问题，liunx下中文路径，图片显示问题
		     String fileName = pdfName ;
		     String pdfFilePath = filePath + fileName;
		     File dest = new File(pdfFilePath);
		     // 检测是否存在目录
		     if (!dest.getParentFile().exists()) {
		         dest.getParentFile().mkdirs();
		     }
            OutputStream out = new FileOutputStream(pdfFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return pdfFilePath;  
        }   
        catch (Exception e)   
        {  
        	e.printStackTrace();
            return "exception";  
        }  
    }  
    
    
    //base64字符串转化成视频  
    public  String GenerateVideo(String videoStr,String filePath,String videoName)  
    {   //对字节数组字符串进行Base64解码并生成视频  
    	if (videoStr == null){ //图像数据为空  
    		   return "videoStr is null";  
    	}
    	try   
    	{  
    		//Base64解码  
    		byte[] b = Base64.decode(videoStr);  
    		for(int i=0;i<b.length;++i)  
    		{  
    			if(b[i]<0)  
    			{//调整异常数据  
    				b[i]+=256;  
    			}  
    		}  
    		//生成jpeg图片  
//            String imgFilePath = "d://QR//5.jpg";//新生成的图片  
    		//String suffixName = ".pdf";
    		// 文件上传路径
    		//  String filePath = "d:/roncoo/ttt/";
    		// 解决中文问题，liunx下中文路径，图片显示问题
    		String fileName = UUID.randomUUID().toString()+videoName;
    		String videoFilePath = filePath + fileName;
    		File dest = new File(videoFilePath);
    		// 检测是否存在目录
    		if (!dest.getParentFile().exists()) {
    			dest.getParentFile().mkdirs();
    		}
    		OutputStream out = new FileOutputStream(videoFilePath);      
    		out.write(b);  
    		out.flush();  
    		out.close();  
    		return fileName;  
    	}   
    	catch (Exception e)   
    	{  
    		e.printStackTrace();
    		return "exception";  
    	}  
    }  
    
  //base64字符串转化成文件  视频
    public  String GenerateFileVideo(String fileVideoStr,String fileVideoPath,String fileVideoName)  
    {   
    	List<String> imgType = new ArrayList<String>();
		imgType.add(".mp4");
		imgType.add(".3gp");
		imgType.add(".asx");
		imgType.add(".asf");
		imgType.add(".mpg");
		imgType.add(".wmv");
		imgType.add(".mov");
		imgType.add(".avi");
		imgType.add(".flv");
		
		
    	//对字节数组字符串进行Base64解码并生成图片  
        if (fileVideoStr == null){ 
        //图像数据为空  
         //   return "imgStr is null";  
        }
        try   
        {  
            //Base64解码  
            byte[] b = Base64.decode(fileVideoStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String suffixName = fileVideoName.substring(fileVideoName.lastIndexOf("."));
            if(imgType.contains(suffixName)){
				// 文件上传路径
				// 解决中文问题，liunx下中文路径，图片显示问题
            	fileVideoName = UUID.randomUUID() + suffixName;
			}
            else
            {
            	fileVideoName = UUID.randomUUID()+ fileVideoName;
            }
		     // 文件上传路径
		     // 解决中文问题，liunx下中文路径，图片显示问题
		     String imgFilePath = fileVideoPath + fileVideoName;
		     File dest = new File(imgFilePath);
		     // 检测是否存在目录
		     if (!dest.getParentFile().exists()) {
		         dest.getParentFile().mkdirs();
		     }
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return fileVideoName;  
        }   
        catch (Exception e)   
        {  
        	e.printStackTrace();
            return "exception";  
        }  
    }  

    //base64字符串转化成图片  
    public  String GenerateVideo(String videoStr,String filePath)  
    {   
    	//对字节数组字符串进行Base64解码并生成图片  
        if (videoStr == null){ 
        //图像数据为空  
         //   return "imgStr is null";  
        }
        try   
        {  
            //Base64解码  
            byte[] b = Base64.decode(videoStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String suffixName = ".mp4";
		     // 文件上传路径
		     // 解决中文问题，liunx下中文路径，图片显示问题
		     String fileName = UUID.randomUUID() + suffixName;
		     String videoFilePath = filePath + fileName;
		     File dest = new File(videoFilePath);
		     // 检测是否存在目录
		     if (!dest.getParentFile().exists()) {
		         dest.getParentFile().mkdirs();
		     }
            OutputStream out = new FileOutputStream(videoFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return fileName;  
        }   
        catch (Exception e)   
        {  
        	e.printStackTrace();
            return "exception";  
        }  
    }  
    
    	/** 
 		*根据路径删除指定的目录或文件，无论存在与否 
 		*@param sPath要删除的目录或文件 
 		*@return 删除成功返回 true，否则返回 false。 
 		*/
    public Boolean DeleteFolder(String sPath) {
    	Boolean flag=false;
    	File file =new File(sPath);
    	// 判断目录或文件是否存在
    	if(!file.exists()){// 不存在返回 false
    		flag=false;
    	}else{
    		// 判断是否为文件
    		if(file.isFile()&&file.exists()){// 为文件时调用删除文件方法
    			file.delete();
    			flag=true;
    		}
    	}
		return flag;
    }
    
    
    public static String StringFilter(String str) throws PatternSyntaxException { 
    	// 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]"; 
    	// 清除掉所有特殊字符 
    	String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"; 
    	Pattern p = Pattern.compile(regEx); 
    	Matcher m = p.matcher(str);
    	return m.replaceAll("").trim();
    	} 
    
    

				
}  