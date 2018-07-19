package com.labwinner.util;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
public class OtherToPdf {
	
	public JSONObject doPost(String method, JSONObject date,HttpServletRequest request) {
	    HttpClient client = HttpClients.createDefault();
	    // 将接口地址和接口方法拼接起来
	    String url = "http://47.98.173.123:8005/ConvertService.ashx";
	    HttpPost post = new HttpPost(url);
	    JSONObject response = null;
	    try {
	        StringEntity s = new StringEntity(date.toString());
	        //s.setContentEncoding("UTF-8");
	        s.setContentType("application/json");
	        post.setEntity(s);
	       // post.addHeader("content-type", "text/xml");
	        // 调用Fa接口
	        HttpResponse res = client.execute(post);
	        String response1 = EntityUtils.toString(res.getEntity());
	        System.out.println("************");
	        System.out.println(response1);
	        if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	            HttpEntity entity = res.getEntity();
	            String result = EntityUtils.toString(res.getEntity());// 返回json格式：
	            response = JSONObject.fromObject(result);
	            }
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	        return response;
	    }
	
	//post请求方法
	public  String sendPost(String url, String data) {
	   String response = null;
	   try {
	       CloseableHttpClient httpclient = null;
	       CloseableHttpResponse httpresponse = null;
	       try {
	           httpclient = HttpClients.createDefault();
	           HttpPost httppost = new HttpPost(url);
	           StringEntity stringentity = new StringEntity(data,
	                   ContentType.create("text/json", "UTF-8"));
	           httppost.setEntity(stringentity);
	           httpresponse = httpclient.execute(httppost);
	           response = EntityUtils.toString(httpresponse.getEntity());
	       } finally {
	           if (httpclient != null) {
	               httpclient.close();
	           }
	           if (httpresponse != null) {
	               httpresponse.close();
	           }
	       }
	   } catch (Exception e) {
	       e.printStackTrace();
	   }
	   return response;
	}
	
	

}
