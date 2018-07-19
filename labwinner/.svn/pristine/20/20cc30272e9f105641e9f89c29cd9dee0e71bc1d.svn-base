package com.labwinner.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;

import com.labwinner.common.ResultVo;

public class pdfToImage implements Callable<Map<String, Object>>{ 


	private String pdfName;
	private Integer pdfNum;
	private String imgPath;
	private String imgUrlPath;
	private String filePath;
	private Integer pageCounter;
	public  pdfToImage(Integer pdfNum,Integer pageCounter,String pdfName,String imgPath,String imgUrlPath,String filePath){
		this.pdfName=pdfName;
		this.pdfNum=pdfNum;
		this.imgPath=imgPath;
		this.imgUrlPath=imgUrlPath;
		this.filePath=filePath;
		this.pageCounter=pageCounter;
		//this.pdf=pdf;
	}

	@Override
	public Map<String,Object> call() throws Exception {
		Map<String,Object> resMap=new HashMap<String,Object>();
		String pdf=pdfName.substring(0,pdfName.indexOf("."));
		PDDocument document = PDDocument.load(new File(filePath+pdfName));
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		List<String> imgAddr=new ArrayList<String>();
		int testNum=pageCounter;
		if(pageCounter-pdfNum>6){
			testNum=pageCounter;
		}else{
			testNum=pdfNum;
		}
	loop:for (PDPage page : document.getPages()){
		if(pageCounter<0){
			pageCounter=0;
		}
		if(pageCounter<document.getNumberOfPages()){
			 BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 150, ImageType.RGB);
			    String fileAddr=imgPath +pdf+ "/img" + (pageCounter++) + ".png";
			    File outFile = new File( fileAddr);
			    if (!outFile.getParentFile().exists()) {
			    	outFile.getParentFile().mkdirs();
			     }
			    if(pageCounter-testNum<5){
			    	 ImageIO.write(bim, "png", outFile);// 写图片 
			    	 imgAddr.add(imgUrlPath+pdf+"/img" +(pageCounter-1)+ ".png");
			    }else{
			    	break loop;
			    }
		}
		}
		document.close();
		//resMap.put("imgAddr", imgAddr);
		resMap.put("pdfNum", document.getNumberOfPages());
		return resMap;
}
}
