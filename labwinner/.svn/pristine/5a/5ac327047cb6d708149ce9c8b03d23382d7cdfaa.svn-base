package com.labwinner.jpush;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;

import cn.jpush.api.JPushClient;

/**
 * @Description 极光基础测试类
 * @author liuhq
 * @version V1.0
 * @date 2017年7月7日
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
public abstract class BaseTest {
	//个人版激光的key
	protected static final String APP_KEY1 = "91c6e62d5760c47e304d3624";
	protected static final String MASTER_SECRET1 = "a488a4b77faf9637038142fa";
	//专业版激光的key
	protected static final String APP_KEY = "3a570e0bfb37a861d5f4976c";
	protected static final String MASTER_SECRET = "50a1dfb27fb85d858f940d07";
	protected static final String GROUP_MASTER_SECRET = "b11314807507e2bcfdeebe2e";
	protected static final String GROUP_PUSH_KEY = "2c88a01e073a0fe4fc7b167c";

	public static final String ALERTN = "Notification : JPush Test - alert";
	public static final String ALERTM = "Message : JPush Test - alert";
	public static final String MSG_CONTENT = "JPush Test - msgContent";

	public static final String REGISTRATION_ID1 = "1104a897929ef4d8c90";
	public static final String REGISTRATION_ID2 = "1104a897929ef4d8c90";
	public static final String REGISTRATION_ID3 = "1104a897929ef4d8c90";

	protected JPushClient jpushClient = null;

	@Before
	public void before() {
		jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);

	}

}
