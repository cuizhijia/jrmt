package com.swift.jrmt.tools;

import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送：向APP发送消息或通知
 * 
 * @author denny.zhang
 */
public class JPushMessageUtil {
	private static final String appKey = "94ff7ab1bbeee5bb2fc62fc2";// 注册应用的应用Key
	private static final String masterSecret = "15b2ad460b391ab3c0caa901";// 注册应用的主密码
	private static JPushClient jpush = new JPushClient(masterSecret, appKey,false,0);// 最大的尝试次数，设为3表示：跟服务器进行建立连接若失败会尝试再进行两次尝试

	public static void buildPushObjectAlltagAndAlertWithExtrasAndMessage(
			String title, String alert,Map<String, String> extras) {
		try {
			PushPayload pl = PushPayload
					.newBuilder()
					.setPlatform(Platform.all())//设置接受的平台 
					.setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到  
					.setNotification(
							Notification
							.newBuilder()
							.addPlatformNotification(
									IosNotification.newBuilder()
									.setAlert(alert)
									.incrBadge(1)
									.setContentAvailable(true)
									.setSound("Default").addExtras(extras)
									.build()).build())
									.setOptions(
											Options.newBuilder().setApnsProduction(false)//开发模式，生产模式下改为true
											.build()).build();
			jpush.sendPush(pl);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
}
