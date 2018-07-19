package com.labwinner.common;



import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service//将这个对象加入Spring容器中
public class RedisCleaner {

   // private static final Logger LOGGER = LoggerFactory.getLogger(RedisCleaner.class);
//	@Value("${web.upload_path_pdf_img}")
//	String imgPath;
//    @Autowired
//    private RedisTemplate redisTemplate;

    @Scheduled(cron ="0 0 2 * * ?")
   // @Scheduled(cron ="0 * 15 * * ?")
    public void demoSchedule() {
    	try {
			deleteFile("src/main/resources/template/");
			createDir("src/main/resources/template/");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public static  void deleteFile(String url) throws FileNotFoundException{
		try{
			System.out.println("========================================================删除文件开始============================================");
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
    public static void main(String[] args) throws FileNotFoundException {
    	deleteFile("src/main/resources/template/");
	}
}
