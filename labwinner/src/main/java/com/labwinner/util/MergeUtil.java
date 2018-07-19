package com.labwinner.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;



public class MergeUtil {
	 public static boolean mergePdfFiles(List<String> files, String newfile) {  
	        boolean retValue = false;  
	        Document document = null;  
	        try {  
	        	File file = new File(newfile);
	        	// 检测是否存在目录
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
	            document = new Document(new PdfReader(files.get(0)).getPageSize(1)); 
	            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));  
	            document.open();  
	            for (int i = 0; i < files.size(); i++) {  
	                PdfReader reader = new PdfReader(files.get(i));  
	                int n = reader.getNumberOfPages();  
	                for (int j = 1; j <= n; j++) {  
	                    document.newPage();  
	                    PdfImportedPage page = copy.getImportedPage(reader, j);  
	                    copy.addPage(page);  
	                }  
	            }  
	            retValue = true;  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            document.close();  
	        }  
	        return retValue;  
	    } 
}
