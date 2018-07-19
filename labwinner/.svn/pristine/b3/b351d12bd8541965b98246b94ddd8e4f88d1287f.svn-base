package com.labwinner.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HtmlUtil {
	public static void CreateHtml(Map<String, Object> data,String outPath,String filename)  { 
		try {  
            //创建一个合适的Configration对象  
            Configuration configuration = new Configuration();  
           // configuration.setDirectoryForTemplateLoading(new File("/ftl"));  
            configuration.setClassForTemplateLoading(
    				WordGeneratorWithFreemarker.class,
    				"/ftl");
            configuration.setObjectWrapper(new DefaultObjectWrapper());  
            configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  
            //获取或创建一个模版。  
            Template template = configuration.getTemplate(filename);  
            Writer writer  = new OutputStreamWriter(new FileOutputStream(outPath),"UTF-8");  
            template.process(data, writer);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        }  
	}
	
	
}
