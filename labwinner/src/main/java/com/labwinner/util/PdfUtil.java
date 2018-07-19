package com.labwinner.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.UUID;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;



public class PdfUtil {

	
	
	 
	 
	 public static Boolean exportPdf(Map<String, Object> data,String outPath,String filename) throws UnsupportedEncodingException, FileNotFoundException { 
		 // String filename="test.pdf";
		 Boolean res=false;
		  String path="src/main/resources/ftl";
		  File file = new File(outPath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
		  //createDir(outPath.substring(0, outPath.indexOf("/print")));
		OutputStream os = new FileOutputStream(file);
	    PdfStamper ps = null;
	    PdfReader reader = null;
	    AcroFields form=null;
		 try {
	         //os = response.getOutputStream();
	         // 2 读入pdf表单
	         //reader = new PdfReader(path+ "/"+filename);
	         reader = new PdfReader("/ftl/"+filename);
	         // 3 根据表单生成一个新的pdf
	        ps=new PdfStamper(reader, os);
	         // 4 获取pdf表单
	        form = ps.getAcroFields();
	         // 5给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
	         BaseFont bf = BaseFont.createFont("/ftl/simsun.ttc,1",
	                 BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	       // BaseFont bf =BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
	         form.addSubstitutionFont(bf);

	         for (String key : data.keySet()) {
	        	 if(data.get(key)!=null&&!"".equals(data.get(key))){
	        		 form.setField(key,data.get(key).toString());
	        	 }
	            
	     }
	         ps.setFormFlattening(true);
	          //-----------------------------pdf 添加图片----------------------------------
	         // 通过域名获取所在页和坐标，左下角为起点
	         System.out.println("pdf 添加图片");
	         String imgpath=(String) data.get("iamgePath");
	         if(imgpath!=null){
	        	 int pageNo = form.getFieldPositions("image").get(0).page;
		         Rectangle signRect = form.getFieldPositions("image").get(0).position;
		         float x = signRect.getLeft();
		         float y = signRect.getBottom();
		         // 读图片
		         Image image = Image.getInstance(imgpath);
		         // 获取操作的页面
		         PdfContentByte under = ps.getOverContent(pageNo);
		         // 根据域的大小缩放图片
		         image.scaleToFit(signRect.getWidth(), signRect.getHeight());
		         // 添加图片
		         image.setAbsolutePosition(x, y);
		         under.addImage(image); 
	         }
	                        
	         //-------------------------------------------------------------
	         res=true;
	        
	         System.out.println(outPath+"===============PDF导出成功=============");
	     } catch (Exception e) {
	    	 res=false;
	         System.out.println(outPath+"===============PDF导出失败=============");
	         e.printStackTrace();
	     } finally {
	         try {
	             ps.close();
	             reader.close();
	             os.close();
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	     }
		 return res;
	 }
	 
	 
	 public static String getImageLoad(String imageUrl,String outUrl) throws Exception{
			URL url = new URL(imageUrl);
			 URLConnection conn = url.openConnection();  
	         conn.setReadTimeout(10000);  
	         conn.setConnectTimeout(10000);  
	         conn.connect(); 
	         DataInputStream dis = new DataInputStream(conn.getInputStream());
		    //打开网络输入流
		   // DataInputStream dis = new DataInputStream(url.openStream());
		 //   FileOutputStream fos1 = new FileOutputStream(new File("e:/labwinnerImages"));
//		    deleteDir(new File(outUrl));
//		    createDir(outUrl);
	         String newImageName=outUrl+UUID.randomUUID().toString()+".jpg";
		    File dest = new File(newImageName);
		    // 检测是否存在目录
		     if (!dest.getParentFile().exists()) {
		         dest.getParentFile().mkdirs();
		     }
		    
		    FileOutputStream fos = new FileOutputStream(new File(newImageName));
		    byte[] buffer = new byte[1024];
		    
		    int length; 			
						try {
						    
						    //开始填充数据
						    while((length = dis.read(buffer))>0){
						    fos.write(buffer,0,length);
						    }
						} catch (Exception e) {
							// TODO: handle exception
						}finally{
							 dis.close();
							    fos.close();
							   // fos1.close();
						}
						
					   
					    return newImageName;
		}
	 
	 
	 public static String getPdfLoad(String pdfUrl,String outUrl) throws Exception{
			URL url = new URL(pdfUrl);
			 URLConnection conn = url.openConnection();  
	         conn.setReadTimeout(10000);  
	         conn.setConnectTimeout(10000);  
	         conn.connect(); 
	         DataInputStream dis = new DataInputStream(conn.getInputStream());
	         String name=UUID.randomUUID().toString()+".pdf";
	         File dest = new File(outUrl+name);
			    // 检测是否存在目录
			     if (!dest.getParentFile().exists()) {
			         dest.getParentFile().mkdirs();
			     }
			     
		    String newPdfName=outUrl+name;
		    FileOutputStream fos = new FileOutputStream(new File(newPdfName));
		    byte[] buffer = new byte[1024];
		    
		    int length; 			
						try {
						    
						    //开始填充数据
						    while((length = dis.read(buffer))>0){
						    fos.write(buffer,0,length);
						    }
						} catch (Exception e) {
							// TODO: handle exception
						}finally{
							 dis.close();
							    fos.close();
							   // fos1.close();
						}
						
					   
					    return name;
		}
	 
	 
	 private static boolean deleteDir(File dir) {
	        if (dir.isDirectory()) {
	            String[] children = dir.list();
	            //递归删除目录中的子目录下
	            for (int i=0; i<children.length; i++) {
	                boolean success = deleteDir(new File(dir, children[i]));
	                if (!success) {
	                    return false;
	                }
	            }
	        }
	        // 目录此时为空，可以删除
	        return dir.delete();
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
		
		
		public  static void main(String[] args) throws Exception {
			String pdfName="http://47.98.173.123:8005/2018-04-09-15-58/cache/files/conv_9a9fc1a6-c5a6-4271-bea5-5673141f24b7_pdf/output.pdf/output.pdf?md5=_74zCzIXIh8ncAl8NAMuVg==&amp;expires=1524542416&amp;disposition=attachment&amp;ooname=output.pdf";
			getPdfLoad(pdfName,"C:/apache-tomcat-7.0.69/webapps/changshaligong/pdfView/");
		}
}
