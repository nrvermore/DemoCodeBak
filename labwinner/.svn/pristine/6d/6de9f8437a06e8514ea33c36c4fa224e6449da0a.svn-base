package com.labwinner.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.UUID;

//import org.xhtmlrenderer.pdf.ITextFontResolver;
//import org.xhtmlrenderer.pdf.ITextRenderer;




















import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;














public class ImageToPdf {

	public static void main(String[] args) throws Exception {
		convertHtmlToPdf("src/main/resources/template/"+"ac83891e-2fed-499e-ac86-25674fd37045.html","src/main/resources/template/"+"ccc.pdf");

	}

	public static boolean convertHtmlToPdf(String inputFile, String outputFile)
		    throws Exception {
		        
//		        OutputStream os = new FileOutputStream(outputFile);     
//		        ITextRenderer renderer = new ITextRenderer();     
//		        String url = new File(inputFile).toURI().toURL().toString(); 
//		        renderer.setDocument(url);   
//		        
//		        // 解决中文支持问题     
//		        ITextFontResolver fontResolver = renderer.getFontResolver();    
//		        fontResolver.addFont("/ftl/msyh.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//		      
//		        renderer.getSharedContext().setBaseURL("src/main/resources/template/");
//		        renderer.layout();    
//		        renderer.createPDF(os);
//		        
//		        os.flush();
//		        os.close();
		        return true;
		    }
	 public static boolean createPDF(String inputFile, String outputFile) throws Exception {  
	        // 输出路径  
//	        String uuid = UUID.randomUUID().toString();  
//	            try {  
//	                //生成html  
//	                Rectangle rect =new Rectangle(1120,600);  
//	                Document document = new Document(rect);  
//	                //生成pdf  
//	                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputFile));  
//	                document.open();  
//	                CSSResolver cssResolver = new StyleAttrCSSResolver();  
//	                CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream("body {font-family: Microsoft YaHei}".getBytes()));  
//	                cssResolver.addCss(cssFile);  
//	           
//	                // 将ChunkCssApplier 设置为自定义的  
//	                CssAppliers cssAppliers = new CssAppliersImpl();  
//	                cssAppliers.setChunkCssAplier(new MyChunkCssApplier());  
//	                HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);  
//	                htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());  
//	           
//	                // Pipelines  
//	                PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);  
//	                HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);  
//	                CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);  
//	           
//	                // XML Worker  
//	                XMLWorker worker = new XMLWorker(css, true);  
//	                XMLParser p = new XMLParser(worker);  
//	                p.parse(new FileInputStream(inputFile), Charset.forName("UTF-8"));  
//	                // step 5  
//	                document.close();  
//	          
//	            } catch (Exception e) {  
//	                System.out.println(e.getMessage());  
//	            }  
	            return true;
	     }  
	 
	 public void creatPDF(String imgName,String pdfName)  
	    {  
	    //创建一个文档对象   
	    Document doc = new Document();  
	    try {     
	        //定义输出文件的位置     
	        PdfWriter.getInstance(doc, new FileOutputStream(pdfName));  
	        //开启文档     
	        doc.open();     
	        //设定字体 为的是支持中文     
	        //BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);     
	       // Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);     
	        //向文档中加入图片   
	        String path = "src/main/resources/template/";  
	        //遍历文件下的文件  
	        File file = new File(path+imgName);  
	       // File [] files=file.listRoots();
	     
	             //File file1 = files[i];  
	            //根据后缀判断是否是图片  
	             String[] imgTrue = file.getName().split("\\.");  
	             if("png".equals(imgTrue[1])){  
	                //取得图片~~~图片格式：  
	                 System.out.println("---"+file.getName());  
	                Image jpg1 = Image.getInstance(path+"/"+imgTrue[0]+".png"); //原来的图片的路径  
	                //获得图片的高度  
	                float heigth=jpg1.getHeight();  
	                float width=jpg1.getWidth();  
	                System.out.println("heigth："+imgTrue[0]+"----"+heigth);  
	                System.out.println("width："+imgTrue[0]+"-----"+width);  
	                //合理压缩，h>w，按w压缩，否则按w压缩  
	                //int percent=getPercent(heigth, width);  
	                //统一按照宽度压缩  
	                int percent=getPercent2(heigth, width);  
	                System.out.println("--"+percent);  
	                //设置图片居中显示  
	                jpg1.setAlignment(Image.MIDDLE);  
	                //直接设置图片的大小~~~~~~~第三种解决方案，按固定比例压缩  
	                //jpg1.scaleAbsolute(210.0f, 297.0f);  
	                //按百分比显示图片的比例  
	                jpg1.scalePercent(percent);//表示是原来图像的比例;  
	                //可设置图像高和宽的比例  
	                //jpg1.scalePercent(50, 100);  
	                doc.add(jpg1);  
	             }  
	        
	        //关闭文档并释放资源     
	        doc.close();     
	    } catch (FileNotFoundException e) {     
	        e.printStackTrace();     
	    } catch (DocumentException e) {     
	        e.printStackTrace();     
	    } catch (IOException e) {     
	        e.printStackTrace();     
	    }    
	    }  
	 
	  public int getPercent2(float h,float w)  
	    {  
	        int p=0;  
	        float p2=0.0f;  
	        p2=530/w*100;  
	        System.out.println("--"+p2);  
	        p=Math.round(p2);  
	        return p;  
	    }  
	
	 
}
