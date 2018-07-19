package com.labwinner.util;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Html2ImageUtil {
	//html文件生成图片
	public static void HtmlToImage(String inFile,String outFile) throws IOException, InterruptedException{
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		File file=new File(inFile);
	    FileInputStream fis = new FileInputStream(file);
	    InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); 
	    //将file文件内容转成字符串
	    BufferedReader bf = new BufferedReader(isr);
	    String content = "";
	    StringBuilder sb = new StringBuilder();
	    while (content!= null) {
	        content = bf.readLine();
	        if (content == null) {
	            break;
	        }
	        sb.append(content.trim());
	    }
	    bf.close();
	    	String fileStr = sb.toString();
		  imageGenerator.loadHtml(fileStr);
		  BufferedImage img=imageGenerator.getBufferedImage();
		  img.flush(); 
		  Thread.sleep(3000);
		  File dest = new File(outFile);
		  if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
		imageGenerator.saveAsImage(outFile);
	}
}
