package com.labwinner.common;

import java.io.File;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Value;

public class SessionListener implements HttpSessionListener {
	
	@Value("${web.upload_path_pdf_img}")
	String imgPath;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("==== Session is created ====");
		arg0.getSession().setMaxInactiveInterval(5*60);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		try {
			deleteFile(imgPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public  void deleteFile(String url) throws FileNotFoundException{
		try{
		File file=new File(url);
			if(!file.isDirectory()){
			file.delete();
			}else if(file.isDirectory()){
			File[] fileList =file.listFiles();
			for(int i=0;i<fileList.length;i++){
				File delfile=fileList[i];
				if(!delfile.isDirectory()){
					delfile.delete();
				}else if(delfile.isDirectory()){
					deleteFile(fileList[i].getPath());
				}
				}
			}
			file.delete();
		}catch(FileNotFoundException e){
		System.out.println("deletefile()   Exception:"+e.getMessage());
		}
    }
}
