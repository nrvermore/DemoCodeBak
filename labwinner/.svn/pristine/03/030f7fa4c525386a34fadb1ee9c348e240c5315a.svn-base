package com.labwinner.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static String getText(String htmlStr) {
		if(htmlStr==null || "".equals(htmlStr)) return "";
		      String textStr ="";    
		      java.util.regex.Pattern pattern;    
		      java.util.regex.Matcher matcher;    
		     
		     try {
		      String regEx_remark = "<!--.+?-->";
		      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }    
		      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }    
		          String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式    
		          String regEx_html1 = "<[^>]+";
		          htmlStr = htmlStr.replaceAll("\n","");
		          htmlStr = htmlStr.replaceAll("\t","");
		          pattern=Pattern.compile(regEx_remark);//过滤注释标签
		          matcher=pattern.matcher(htmlStr);
		      htmlStr=matcher.replaceAll("")    ;
		       
		      pattern = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);    
		      matcher = pattern.matcher(htmlStr);    
		          htmlStr = matcher.replaceAll(""); //过滤script标签    




		          pattern = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);    
		          matcher = pattern.matcher(htmlStr);    
		          htmlStr = matcher.replaceAll(""); //过滤style标签    
		        
		          pattern = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);    
		          matcher = pattern.matcher(htmlStr);    
		          htmlStr = matcher.replaceAll(""); //过滤html标签    
		            
		          pattern = Pattern.compile(regEx_html1,Pattern.CASE_INSENSITIVE);    
		          matcher = pattern.matcher(htmlStr);    
		          htmlStr = matcher.replaceAll(""); //过滤html标签    
		          
		        
		       textStr = htmlStr.trim();    
		       
		   }catch(Exception e) {
		   System.out.println("获取HTML中的text出错:");
		   e.printStackTrace();
		    }    
		    
		  return textStr;//返回文本字符串
		}
	
	public String[] getImgs(String content) {
		String img = "";
		Pattern p_image;
		Matcher m_image;
		String str = "";
		String[] images = null;
		String regEx_img = "(<img.*src\\s*=\\s*(.*?)[^>]*?>)";
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(content);
		while (m_image.find()) {
		img = m_image.group();
		Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)")
		.matcher(img);
		while (m.find()) {
		String tempSelected = m.group(1);
		if ("".equals(str)) {
		str = tempSelected;
		} else {
		String temp = tempSelected;
		str = str + "," + temp;
		}
		}
		}
		if (!"".equals(str)) {
		images = str.split(",");
		}
		return images;
		}
}
