package com.labwinner.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 二维码生成解析类
 * @author labwinner
 *
 */
public class QRCreateAndParse {

	
	public static void  main(String [] args) {  
		
//		String filePath="D:/QR/";
//		String content = "123456218"; 
//		QRCreateAndParse cpCode = new QRCreateAndParse();  
// //       cpCode.createCode(content,filePath); 
//        cpCode.createQr(content, filePath);
//        cpCode.parseCode(new File("D:/QR/0b60f847-ffee-4d5f-965e-a4b048a1b75f.png"));  
          
    }
	
	public String createQr(String content,String path,String name){
		 
	    // 二维码的图片格式  
	    String format = "png";  
	    String fileName = "";
		/**
		 * 设置二维码的参数
		 */ 
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix;
		try {
			bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,80,80,hints);
			//1.1去白边
			int[] rec = bitMatrix.getEnclosingRectangle();  
			int resWidth = rec[2] + 1;  
			int resHeight = rec[3] + 1;  
			BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);  
			resMatrix.clear();  
			for (int i = 0; i < resWidth; i++) {  
			    for (int j = 0; j < resHeight; j++) {  
			        if (bitMatrix.get(i + rec[0], j + rec[1])) { 
			             resMatrix.set(i, j); 
			        } 
			    }  
			}  
			//2
			int width = resMatrix.getWidth();
			int height = resMatrix.getHeight();
			BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
			for (int x = 0; x < width; x++) {
			    for (int y = 0; y < height; y++) {
			        image.setRGB(x, y, resMatrix.get(x, y) == true ? 
			        Color.BLACK.getRGB():Color.WHITE.getRGB());
			    }
			}
			//3
			if(name!=null && !name.equals("")){
				fileName = name;
			}else{
				String suffixName = ".png";
	            fileName = UUID.randomUUID() + suffixName;
				}
			
		     String imgFilePath = path + fileName;
            // 生成二维码  
            File outputFile = new File(imgFilePath);  
            if (!outputFile.getParentFile().exists()) {
            	outputFile.getParentFile().mkdirs();
		     }
			
			ImageIO.write(image,format, new File(imgFilePath));
			
			return fileName;
		}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
	}
	
	
	
	 public String createCode(String content,String path){  
		 
	         
	        int width = 150;  
	        int height = 150;  
	        // 二维码的图片格式  
	        String format = "png";  
	        /** 
	         * 设置二维码的参数 
	         */  
	        HashMap<EncodeHintType ,String> hints = new HashMap<EncodeHintType,String>();  
	        // 内容所使用编码  
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
	        try {  
	            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height,hints);  
	            String suffixName = ".png";
	            String fileName = UUID.randomUUID() + suffixName;
			     String imgFilePath = path + fileName;
	            // 生成二维码  
	            File outputFile = new File(imgFilePath);  
	            if (!outputFile.getParentFile().exists()) {
	            	outputFile.getParentFile().mkdirs();
			     }
	            MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);  
	             
	            return fileName;
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	          return null;
	    }  
	    /** 
	     * 二维码的解析 
	     * 
	     * @param file 
	     */  
	    public Result parseCode(File file)  
	    {  
	        try  
	        {  
	            MultiFormatReader formatReader = new MultiFormatReader();  
	   
	            if (!file.exists())  
	            {  
	                throw new Exception("the file not exists");  
	            }  
	   
	            BufferedImage image = ImageIO.read(file);  
	   
	            LuminanceSource source = new BufferedImageLuminanceSource(image);  
	            Binarizer binarizer = new HybridBinarizer(source);  
	            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
	   
	            Map hints = new HashMap();  
	            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
	   
	            Result result = formatReader.decode(binaryBitmap, hints);  
	   
//	            System.out.println("解析结果 = " + result.toString());  
//	            System.out.println("二维码格式类型 = " + result.getBarcodeFormat());  
//	            System.out.println("二维码文本内容 = " + result.getText());  
	            return result;
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	            
	        }
			return null;  
	    }  
	

}
