package com.labwinner.jpush;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

public class JPushManager {  
	  
	
	@Value("${jpush.environment}")
	static String environment;
	
    // 日志  
    private static final Logger log = Logger.getLogger(JPushManager.class);  
    // 推送客户端  
    private static JPushClient buyerJpushClient = null;  
    static {  
        String buyerAppKey = null; // 推送app key  
        String buyerMasterSecret = null;// 推送app主密码  
        // int buyerMaxRetryTimes = 3;// 推送最大重发次数  
  
        buyerAppKey = "91c6e62d5760c47e304d3624";
        buyerMasterSecret = "a488a4b77faf9637038142fa"; 
        Boolean evn=false;
        if("line".equals(environment)){
        	evn=true;
        }
        ClientConfig config = ClientConfig.getInstance();  
        config.setMaxRetryTimes(5);  
        config.setApnsProduction(evn);    // development env  
        buyerJpushClient = new JPushClient(buyerMasterSecret, buyerAppKey,  
                null, config);  
    }  
  
    public static void sendClient(JPushData pushData,  
            Map<String, String> extraMap) {  
        PushPayload payload = buildPushObject_all_alias_alert(pushData,  
                extraMap);  
        try {  
            PushResult result = buyerJpushClient.sendPush(payload); 
            log.info("Got result - " + result);  
        } catch (APIConnectionException e) {  
            log.error("Connection error, should retry later", e);  
        } catch (APIRequestException e) {  
            log.error("Should review the error, and fix the request", e);  
            log.info("HTTP Status: " + e.getStatus());  
            log.info("Error Code: " + e.getErrorCode());  
            log.info("Error Message: " + e.getErrorMessage());  
        }  
    }  
  
    public static PushPayload buildPushObject_all_alias_alert(  
            JPushData pushData, Map<String, String> extraMap) {  
    	 Boolean evn=false;
         if("line".equals(environment)){
         	evn=true;
         }
        Message message = Message.newBuilder().setMsgContent(pushData.getContent())  
                .setTitle(pushData.getTitle()).setContentType(pushData.getTag()).build();  
        return PushPayload.newBuilder().setPlatform(Platform.all())
        		.setOptions(Options.newBuilder().setApnsProduction(evn).build())
                .setAudience(Audience.all())  
                .setMessage(message)//使用自定义消息推送  
                .build();  
    }  
}  
