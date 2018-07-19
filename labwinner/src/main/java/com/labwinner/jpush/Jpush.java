package com.labwinner.jpush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @Description 极光Controller调用类
 * @author liuhq
 * @version V1.0
 * @date 2017年7月7日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Component
public class Jpush extends BaseRemotePushTest {
	@Value("${jpush.environment}")
	 String environment;

	public void sendSimpleMessageAndNotification_Pall(String msg)
			throws Exception {
		 Boolean evn=false;
         if("line".equals(environment)){
         	evn=true;
         }
		PushPayload payload = PushPayload.newBuilder()
				.setPlatform(Platform.all()).setAudience(Audience.all())
				.setOptions(Options.newBuilder().setApnsProduction(evn).build())
				.setNotification(Notification.alert(msg))
				.setMessage(Message.content(msg)).build();
		PushClient _client = new PushClient(MASTER_SECRET, APP_KEY);

		PushResult result = _client.sendPush(payload);
	}
	
	public void sendMessageAndNotification_Pall(JPushData pushData, Map<String, String> extraMap)
			throws Exception {
		 Boolean evn=false;
         if("line".equals(environment)){
         	evn=true;
         }
		 Message message = Message.newBuilder().setMsgContent(pushData.getContent())  
	                .setTitle(pushData.getTitle()).setContentType(pushData.getTag()).build();  
		 PushPayload payload = PushPayload.newBuilder()
					.setPlatform(Platform.all())
					//.setAudience(Audience.all())
					.setAudience(Audience.alias(pushData.getAlias())) 
					.setOptions(Options.newBuilder().setApnsProduction(evn).build())
					.setNotification(Notification.alert(pushData.getContent()))
	                .setMessage(message)//使用自定义消息推送  
	                .build();  
		PushClient _client = new PushClient(MASTER_SECRET, APP_KEY);
		PushResult result = _client.sendPush(payload);
	}

	
	public void sendMessageAndNotification_Json(JPushData pushData, Map<String, String> extraMap,Map<String, Integer> jsonMap,String beanStr,int flag)
			throws Exception {
		 Boolean evn=false;
         if("line".equals(environment)){
         	evn=true;
         }
         JsonObject extra=new JsonObject();
         extra.addProperty("userId", jsonMap.get("userId"));
         extra.addProperty("agencyId", jsonMap.get("agencyId"));
         extra.addProperty("type", jsonMap.get("type"));
         extra.addProperty("messageId", jsonMap.get("messageId"));
         extra.addProperty("messageType", jsonMap.get("messageType"));
         JsonObject extra1=new JsonObject();
         extra1.addProperty("userId", jsonMap.get("userId"));
         extra1.addProperty("agencyId", jsonMap.get("agencyId"));
         extra1.addProperty("type", jsonMap.get("type"));
         extra1.addProperty("expertAssist", beanStr);
         extra1.addProperty("messageId", jsonMap.get("messageId"));
         extra1.addProperty("messageType", jsonMap.get("messageType"));
		 Message message = Message.newBuilder().setMsgContent(pushData.getContent())  
	                .setTitle(pushData.getTitle()).setContentType(pushData.getTag()).addExtras(toMap(extra1)).build();  
		 PushPayload payload = PushPayload.newBuilder()
				 .setPlatform(Platform.all())
				 .setAudience(Audience.alias(pushData.getAlias())) 
				 .setNotification(Notification.newBuilder()
				.addPlatformNotification(AndroidNotification.newBuilder().setAlert(pushData.getContent())
						.addExtra("EXTRA_EXTRA", extra)
						.build())
				.addPlatformNotification(IosNotification.newBuilder()
								.setAlert(pushData.getContent())
								.autoBadge()
								.addExtra("EXTRA_EXTRA", extra)
								.build())
								.build())
				.setMessage(message)
				.setOptions(Options.newBuilder()
				.setApnsProduction(evn)
				.build())
				.build();
		 PushClient _client=null;
		 if(flag-1==0){
			 _client= new PushClient(MASTER_SECRET1, APP_KEY1);
		 }else{
			 _client = new PushClient(MASTER_SECRET, APP_KEY);
		 }
		PushResult result = _client.sendPush(payload);
	}
	
	
	public void sendMessage_Json(JPushData pushData, Map<String, String> extraMap,Map<String, Integer> jsonMap,String beanStr,int flag)
			throws Exception {
		 Boolean evn=false;
         if("line".equals(environment)){
         	evn=true;
         }
         JsonObject extra=new JsonObject();
         extra.addProperty("userId", jsonMap.get("userId"));
         extra.addProperty("agencyId", jsonMap.get("agencyId"));
         extra.addProperty("type", jsonMap.get("type"));
         extra.addProperty("messageId", jsonMap.get("messageId"));
         extra.addProperty("messageType", jsonMap.get("messageType"));
         JsonObject extra1=new JsonObject();
         extra1.addProperty("userId", jsonMap.get("userId"));
         extra1.addProperty("agencyId", jsonMap.get("agencyId"));
         extra1.addProperty("type", jsonMap.get("type"));
         extra1.addProperty("expertAssist", beanStr);
         extra1.addProperty("messageId", jsonMap.get("messageId"));
         extra1.addProperty("messageType", jsonMap.get("messageType"));
		 Message message = Message.newBuilder().setMsgContent(pushData.getContent())  
	                .setTitle(pushData.getTitle()).setContentType(pushData.getTag()).addExtras(toMap(extra1)).build();  
		 PushPayload payload = PushPayload.newBuilder()
				 .setPlatform(Platform.all())
				 .setAudience(Audience.alias(pushData.getAlias())) 
//				 .setNotification(Notification.newBuilder()
//				.addPlatformNotification(AndroidNotification.newBuilder().setAlert(pushData.getContent())
//						.addExtra("EXTRA_EXTRA", extra)
//						.build())
//				.addPlatformNotification(IosNotification.newBuilder()
//								.setAlert(pushData.getContent())
//								.autoBadge()
//								.addExtra("EXTRA_EXTRA", extra)
//								.build())
//								.build())
				.setMessage(message)
				.setOptions(Options.newBuilder()
				.setApnsProduction(evn)
				.build())
				.build();
		 PushClient _client=null;
		 if(flag-1==0){
			 _client= new PushClient(MASTER_SECRET1, APP_KEY1);
		 }else{
			 _client = new PushClient(MASTER_SECRET, APP_KEY);
		 }
		PushResult result = _client.sendPush(payload);
	}
		//专家团求助即时通信的
	public void sendMessageAndNotification_JsonALL(JPushData pushData, Map<String, String> extraMap,Map<String, Integer> jsonMap,String beanStr,int flag)
			throws Exception {
		 Boolean evn=false;
         if("line".equals(environment)){
         	evn=true;
         }
         JsonObject extra=new JsonObject();
         extra.addProperty("userId", jsonMap.get("userId"));
         extra.addProperty("agencyId", jsonMap.get("agencyId"));
         extra.addProperty("type", jsonMap.get("type"));
         JsonObject extra1=new JsonObject();
         extra1.addProperty("userId", jsonMap.get("userId"));
         extra1.addProperty("agencyId", jsonMap.get("agencyId"));
         extra1.addProperty("type", jsonMap.get("type"));
         extra1.addProperty("expertAssist", beanStr);
		 Message message = Message.newBuilder().setMsgContent(pushData.getContent())  
	                .setTitle(pushData.getTitle()).setContentType(pushData.getTag()).addExtras(toMap(extra1)).build();  
		 PushPayload payload = PushPayload.newBuilder()
				 .setPlatform(Platform.all())
				 .setAudience(Audience.alias(pushData.getAlias())) 
				 .setNotification(Notification.newBuilder()
//				.addPlatformNotification(AndroidNotification.newBuilder().setAlert(pushData.getContent())
//						.addExtra("EXTRA_EXTRA", extra)
//						.build())
				.addPlatformNotification(IosNotification.newBuilder()
								.setAlert(pushData.getContent())
								.autoBadge()
								.addExtra("EXTRA_EXTRA", extra)
								.build())
								.build())
				.setMessage(message)
				.setOptions(Options.newBuilder()
				.setApnsProduction(evn)
				.build())
				.build();
		 PushClient _client=null;
		 if(flag-1==0){
			 _client= new PushClient(MASTER_SECRET1, APP_KEY1);
		 }else{
			 _client = new PushClient(MASTER_SECRET, APP_KEY);
		 }
		PushResult result = _client.sendPush(payload);
	}
	
	//项目组讨论即时通信的
		public void sendMessageAndNotification_JsonPro(JPushData pushData, Map<String, String> extraMap,Map<String, Object> jsonMap,String beanStr,int flag)
				throws Exception {
			 Boolean evn=false;
	         if("line".equals(environment)){
	         	evn=true;
	         }
	         JsonObject extra=new JsonObject();
	         extra.addProperty("userId",String.valueOf(jsonMap.get("userId")));
	         extra.addProperty("type", String.valueOf(jsonMap.get("type")));
	         extra.addProperty("proId", String.valueOf(jsonMap.get("proId")));
	         extra.addProperty("proName", String.valueOf(jsonMap.get("proName")));
	         Map<String, String> extraMaps=new HashMap<String, String>();
	         JsonObject extra1=new JsonObject();
	         extra1.addProperty("userId", String.valueOf(jsonMap.get("userId")));
	         String type=String.valueOf(jsonMap.get("type"));
	         Integer type1=Integer.valueOf(type);
	         extra1.addProperty("type", type1);
	         String proId=String.valueOf(jsonMap.get("proId"));
	         Integer proId1=Integer.valueOf(proId);
	         extra1.addProperty("proId", proId1);
	         extra1.addProperty("proName", String.valueOf(jsonMap.get("proName")));
	         extra1.addProperty("ProjectAssist", beanStr);
	         extraMaps.put("userId", String.valueOf(jsonMap.get("userId")));
	         extraMaps.put("type", type);
	         extraMaps.put("proId", proId);
	         extraMaps.put("proName", String.valueOf(jsonMap.get("proName")));
	         extraMaps.put("projectAssistId", String.valueOf(jsonMap.get("projectAssistId")));
	         extraMaps.put("ProjectAssist", beanStr);
			 Message message = Message.newBuilder().setMsgContent(pushData.getContent())  
		                .setTitle(pushData.getTitle()).setContentType(pushData.getTag())
		                .addExtras(extraMaps)
		                .build();  
			 PushPayload payload = PushPayload.newBuilder()
					 .setPlatform(Platform.all())
					 .setAudience(Audience.alias(pushData.getAlias())) 
					 .setNotification(Notification.newBuilder()
//					.addPlatformNotification(AndroidNotification.newBuilder().setAlert(pushData.getContent())
//							.addExtra("EXTRA_EXTRA", extra)
//							.build())
					.addPlatformNotification(IosNotification.newBuilder()
									.setAlert(pushData.getContent())
									.autoBadge()
									.addExtra("EXTRA_EXTRA", extra)
									.build())
									.build())
					.setMessage(message)
					.setOptions(Options.newBuilder()
					.setApnsProduction(evn)
					.build())
					.build();
			 PushClient _client=null;
			 if(flag-1==0){
				 _client= new PushClient(MASTER_SECRET1, APP_KEY1);
			 }else{
				 _client = new PushClient(MASTER_SECRET, APP_KEY);
			 }
			PushResult result = _client.sendPush(payload);
		}
	
	public static Map<String, String> toMap(JsonObject json){  
        Map<String, String> map = new HashMap<String, String>();  
        Set<Entry<String, JsonElement>> entrySet = json.entrySet();  
        for (Iterator<Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){  
            Entry<String, JsonElement> entry = iter.next();  
            String key = entry.getKey();  
            Object value = entry.getValue();  
            if(value instanceof JsonArray)  
                map.put((String) key, toList((JsonArray) value).toString());  
            else if(value instanceof JsonObject)  
                map.put((String) key, toMap((JsonObject) value).toString());  
            else  
                map.put((String) key, value.toString());  
        }  
        return map;  
    }  
	  public static List<Object> toList(JsonArray json){  
	        List<Object> list = new ArrayList<Object>();  
	        for (int i=0; i<json.size(); i++){  
	            Object value = json.get(i);  
	            if(value instanceof JsonArray){  
	                list.add(toList((JsonArray) value));  
	            }  
	            else if(value instanceof JsonObject){  
	                list.add(toMap((JsonObject) value));  
	            }  
	            else{  
	                list.add(value);  
	            }  
	        }  
	        return list;  
	    } 
	
}
