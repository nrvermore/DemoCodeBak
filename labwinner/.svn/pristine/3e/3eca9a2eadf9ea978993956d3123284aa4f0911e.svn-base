package com.labwinner.util;

import java.awt.Image; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.util.Properties;

import javax.imageio.ImageIO; 



public class videoToImage {
	/**
	   * 获取指定视频的帧并保存为图片至指定目录
	   * @param videofile  源视频文件路径
	   * @param framefile  截取帧的图片存放路径
	   * @throws Exception
	   */
	  public static void fetchFrame(String videofile, String framefile)
	          throws Exception {
		  
		  
		  Properties prop = System.getProperties();
	
		  String cmds="";
		  String a="";
		  String b="";
		  System.out.println("==========="+prop.getProperty("os.name"));
		  if(prop.getProperty("os.name").equals("Linux")){
			  cmds="ffmpeg -i "+videofile+" -y -f  image2  -ss 1 -vframes 1 "+framefile;
			  a="/bin/sh";
			  b="-c";
			  
		  }else if(prop.getProperty("os.name").indexOf("Windows")>=0){
			  cmds="ffmpeg -i "+videofile+" -y -f  image2  -ss 1 -vframes 1 "+framefile;
			  a="cmd";
			  b="/c";
		  }
		  String[] command= new String[]{ a, b, cmds };
		  
		  Runtime rt=Runtime.getRuntime();
		  Process proc=rt.exec(command);
//	      long start = System.currentTimeMillis();
////	      File targetFile = new File(framefile);
////	      FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile); 
////	      ff.start();
////	      int lenght = ff.getLengthInFrames();
////	      int i = 0;
////	      Frame f = null;
////	      while (i < lenght) {
////	          // 过滤前5帧，避免出现全黑的图片，依自己情况而定
////	          f = ff.grabFrame();
////	          if ((i > 5) && (f.image != null)) {
////	              break;
////	          }
////	          i++;
////	      }
////	      IplImage img = f.image;
////	      int owidth = img.width();
////	      int oheight = img.height();
////	      // 对截取的帧进行等比例缩放
////	      int width = 800;
////	      int height = (int) (((double) width / owidth) * oheight);
////	      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
////	      bi.getGraphics().drawImage(f.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH),
////	              0, 0, null);
////	      ImageIO.write(bi, "jpg", targetFile);
////	      //ff.flush();
////	      ff.stop();
////	      System.out.println(System.currentTimeMillis() - start);
//	      
//	      File targetFile = new File(framefile);
//	      FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(videofile);
//	      ff.start();
//	      int lenght = ff.getLengthInFrames();
//	      int i = 0;
//	      org.bytedeco.javacv.Frame f=null;
//	      while (i < lenght) {
//	          // 过滤前100帧
//	          f = ff.grabFrame();
//	          if ((i > 5) && (f.image != null)) {
//	              break;
//	          }
//	          i++;
//	      }
//	      Java2DFrameConverter converter = new Java2DFrameConverter();
//
//	      BufferedImage bi = converter.getBufferedImage(f);
//
//	      ImageIO.write(bi, "jpg",targetFile);

	      
	  }
}
