package com.labwinner.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @Description:富文本Html处理器，主要处理图片及编码
 * @author:LiaoFei
 * @date :2016-3-28 下午4:13:21
 * @version V1.0
 * 
 */
public class RichHtmlHandler {

	private Document doc = null;
	private String html;

	private String docSrcParent = "";
	private String docSrcLocationPrex = "";
	private String nextPartId;
	private String shapeidPrex;
	private String spidPrex;
	private String typeid;

	private String handledDocBodyBlock;
	private List<String> docBase64BlockResults = new ArrayList<String>();
	private List<String> xmlImgRefs = new ArrayList<String>();

	public String getDocSrcLocationPrex() {
		return docSrcLocationPrex;
	}

	public void setDocSrcLocationPrex(String docSrcLocationPrex) {
		this.docSrcLocationPrex = docSrcLocationPrex;
	}

	public String getNextPartId() {
		return nextPartId;
	}

	public void setNextPartId(String nextPartId) {
		this.nextPartId = nextPartId;
	}

	public String getHandledDocBodyBlock() {
		String raw=   WordHtmlGeneratorHelper.string2Ascii(doc.getElementsByTag("body").html());
		return raw.replace("=3D", "=").replace("=", "=3D");
	}
	
	public String getRawHandledDocBodyBlock() {
		String raw=  doc.getElementsByTag("body").html();
		return raw.replace("=3D", "=").replace("=", "=3D");
	}
	public List<String> getDocBase64BlockResults() {
		return docBase64BlockResults;
	}

	public List<String> getXmlImgRefs() {
		return xmlImgRefs;
	}

	public String getShapeidPrex() {
		return shapeidPrex;
	}

	public void setShapeidPrex(String shapeidPrex) {
		this.shapeidPrex = shapeidPrex;
	}

	public String getSpidPrex() {
		return spidPrex;
	}

	public void setSpidPrex(String spidPrex) {
		this.spidPrex = spidPrex;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getDocSrcParent() {
		return docSrcParent;
	}

	public void setDocSrcParent(String docSrcParent) {
		this.docSrcParent = docSrcParent;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public RichHtmlHandler(String html) {
		this.html = html;
		doc = Jsoup.parse(wrappHtml(this.html));
	}
	
	public void re_init(String html){
		doc=null;
		doc = Jsoup.parse(wrappHtml(html));
		docBase64BlockResults.clear();
		xmlImgRefs.clear();
	}
	
	/**
	 * @Description: 获得已经处理过的HTML文件
	 * @param @return
	 * @return String
	 * @throws Exception 
	 * @throws
	 * @author:LiaoFei
	 * @date:2016-3-28 下午4:16:34
	 */
	public void handledHtml(boolean isWebApplication)
			throws Exception {
		Elements imags = doc.getElementsByTag("img");
		System.out.println("doc:\n"+doc);
		if (imags == null || imags.size() == 0) {
			// 返回编码后字符串
			return ;
			//handledDocBodyBlock = WordHtmlGeneratorHelper.string2Ascii(html);
		}

		// 转换成word mht 能识别图片标签内容，去替换html中的图片标签

		for (Element item : imags) {
			// 把文件取出来
			String src = item.attr("src");
			String srcRealPath = src;
			
//			String thepaths = RichHtmlHandler.class.getClassLoader().getResource("").toString();
//			System.out.println("src="+src+"     thepaths="+thepaths);
			if (isWebApplication) {
//				String contentPath=RequestResponseContext.getRequest().getContextPath();
//				if(!StringUtils.isEmpty(contentPath)){
//					if(src.startsWith(contentPath)){
//						src=src.substring(contentPath.length());
//					}
//				}
//				
//				srcRealPath = RequestResponseContext.getRequest().getSession()
//						.getServletContext().getRealPath(src);
				
			}
			if(srcRealPath.indexOf("http")>=0){
				//srcRealPath=getImageLoad(srcRealPath);
				srcRealPath=srcRealPath.replace("http://staticfile.labwinner.com", "/var/lib/tomcat7/webapps");
				srcRealPath=srcRealPath.replace("http://192.168.1.107:8081", "D:/apache-tomcat-7.0.69/webapps");
				srcRealPath=srcRealPath.replace("http://testfile.labwinner.com", "/var/lib/tomcat7/webapps");
			}
			File imageFile = new File(srcRealPath);
			String imageFielShortName = imageFile.getName();
			String fileTypeName = WordImageConvertor.getFileSuffix(srcRealPath);

			String docFileName = "image" + UUID.randomUUID().toString() + "."+ fileTypeName;
			String srcLocationShortName = docSrcParent + "/" + docFileName;

			String styleAttr = item.attr("style"); // 样式
			//高度
			String imagHeightStr=item.attr("height");
			if(StringUtils.isEmpty(imagHeightStr)){
				imagHeightStr = getStyleAttrValue(styleAttr, "height");
			}
			//宽度
			String imagWidthStr=item.attr("width");;
			if(StringUtils.isEmpty(imagWidthStr)){
				imagWidthStr = getStyleAttrValue(styleAttr, "width");
			}
	
			imagHeightStr = imagHeightStr.replace("px", "");
			imagWidthStr = imagWidthStr.replace("px", "");
			if(StringUtils.isEmpty(imagHeightStr)){
				//去得到默认的文件高度
				imagHeightStr="0";
			}
			if(StringUtils.isEmpty(imagWidthStr)){
				imagWidthStr="0";
			}
			int imageHeight = Integer.parseInt("200");
			int imageWidth = Integer.parseInt("160");
			
			// 得到文件的word mht的body块
			String handledDocBodyBlock = WordImageConvertor.toDocBodyBlock(srcRealPath,
					imageFielShortName, imageHeight, imageWidth,styleAttr,
					srcLocationShortName, shapeidPrex, spidPrex, typeid);

			//这里的顺序有点问题：应该是替换item，而不是整个后面追加
			//doc.rreplaceAll(item.toString(), handledDocBodyBlock);
			item.after(handledDocBodyBlock);
//			item.parent().append(handledDocBodyBlock);
			item.remove();
			// 去替换原生的html中的imag

			String base64Content = WordImageConvertor.imageToBase64(srcRealPath);
			//System.out.println(base64Content+"===========================================");
			String contextLoacation = docSrcLocationPrex + "/" + docSrcParent + "/" + docFileName;

			String docBase64BlockResult = WordImageConvertor.generateImageBase64Block(nextPartId, contextLoacation,
							fileTypeName, base64Content);
			docBase64BlockResults.add(docBase64BlockResult);

			String imagXMLHref = "<o:File HRef=3D\"" + docFileName + "\"/>";
			xmlImgRefs.add(imagXMLHref);

		}
		 

	}

	private String getStyleAttrValue(String style, String attributeKey) {
		if (StringUtils.isEmpty(style)) {
			return "";
		}

		// 以";"分割
		String[] styleAttrValues = style.split(";");
		for (String item : styleAttrValues) {
			// 在以 ":"分割
			String[] keyValuePairs = item.split(":");
			if (attributeKey.equals(keyValuePairs[0])) {
				return keyValuePairs[1];
			}
		}

		return "";
	}
	
	public static String getImageLoad(String imageUrl) throws Exception{
		URL url = new URL(imageUrl);
	    //打开网络输入流
	    DataInputStream dis = new DataInputStream(url.openStream());
	 //   FileOutputStream fos1 = new FileOutputStream(new File("e:/labwinnerImages"));
	    //deleteDir(new File("src/main/resources/template"));
	    createDir("src/main/resources/template/");
	    String newImageName="src/main/resources/template/"+UUID.randomUUID().toString()+".jpg";
	    FileOutputStream fos = new FileOutputStream(new File(newImageName));
	    byte[] buffer = new byte[1024];
	    
	    int length; 			
					try {
					    
					    //开始填充数据
					    while((length = dis.read(buffer))>0){
					    fos.write(buffer,0,length);
					    }
					} catch (Exception e) {
						// TODO: handle exception
					}finally{
						 dis.close();
						    fos.close();
						   // fos1.close();
					}
				    return newImageName;
	}
	
	private String wrappHtml(String html){
		// 因为传递过来都是不完整的doc
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<body>");
		sb.append(html);

		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}	
	
	  private static boolean deleteDir(File dir) {
	        if (dir.isDirectory()) {
	            String[] children = dir.list();
	            //递归删除目录中的子目录下
	            for (int i=0; i<children.length; i++) {
	                boolean success = deleteDir(new File(dir, children[i]));
	                if (!success) {
	                    return false;
	                }
	            }
	        }
	        // 目录此时为空，可以删除
	        return dir.delete();
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
		
		public static void main(String[] args) throws IOException {
			String url1="http://staticfile.labwinner.com/LabWinner/images/72be4390-6ad7-485c-a144-0c8900769a7c.png";
			
			URL url = new URL(url1);
		    //打开网络输入流
		    DataInputStream dis = new DataInputStream(url.openStream());
		 //   FileOutputStream fos1 = new FileOutputStream(new File("e:/labwinnerImages"));
		    //deleteDir(new File("src/main/resources/template"));
		    createDir("src/main/resources/template/");
		    String newImageName="src/main/resources/template/"+UUID.randomUUID().toString()+".jpg";
		    FileOutputStream fos = new FileOutputStream(new File(newImageName));
		    byte[] buffer = new byte[1024];
		    
		    int length; 			
						try {
						    
						    //开始填充数据
						    while((length = dis.read(buffer))>0){
						    fos.write(buffer,0,length);
						    }
						} catch (Exception e) {
							// TODO: handle exception
						}finally{
							 dis.close();
							    fos.close();
							   // fos1.close();
						}
		}
}