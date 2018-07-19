package com.labwinner.jpush;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @Description 极光单元测试类
 * @author liuhq
 * @version V1.0
 * @date 2017年7月7日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public class SingleTest extends BaseRemotePushTest {

	// 所有平台，所有设备，内容为 Notification : JPush Test - alert 的通知
//	@Test
//	public void buildPushObject_all_all_alert() throws Exception {
//		PushPayload payload = PushPayload
//				.alertAll("Notification : JPush Test - alert liuhq 2017/7/12 18:18");
//		PushResult result = _client.sendPush(payload);
//		assertTrue(result.isResultOK());
//	}

	// 所有平台，推送目标是别名为 test123456，通知内容为 Notification : JPush Test - alert。
	 @Test
	 public void buildPushObject_all_alias_alert() throws Exception {
	 PushPayload payload = PushPayload.newBuilder()
	 .setPlatform(Platform.all())
	 .setAudience(Audience.alias("test123456")) // 别名
	 .setNotification(Notification.alert("Notification : JPush Test - alert liuhq 2017/7/12 18:18"))
	 .build();
	 PushResult result = _client.sendPush(payload);
	 assertTrue(result.isResultOK());
	 }

	// 所有平台，推送目标是别名为 test123456，消息内容为 Message : JPush Test - alert。
	// @Test
	// public void buildPushObject_ios_audienceMore_messageWithExtras()
	// throws Exception {
	// PushPayload payload = PushPayload.newBuilder()
	// .setPlatform(Platform.all())
	// .setAudience(Audience.alias("test123456"))
	// .setNotification(Notification.alert("Message : JPush Test - alert liuhq 2017/7/12 18:18")).build();
	// PushResult result = _client.sendPush(payload);
	// assertTrue(result.isResultOK());
	// }

}
