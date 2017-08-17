package com.swift.jrmt.tools;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ApplicationObjectSupport;

/**
 * 发送消息工具类（短信，极光推送消息，站内信）
 * 
 * @author denny.zhang
 */
public class JrmtMessageUtil extends ApplicationObjectSupport {
	private static Logger logger = LoggerFactory.getLogger(JrmtMessageUtil.class);
	
	/**短信服务域名**/
	//public final static String serverURL = "localhost";
	/**短信服务域名**/
	public final static String serverURL = "http://sms.api.56kuaiche.com";
	/**发送站内信url**/
	private final static String SEND_PCMSG_CODE_URL = "/message/sendpcmessage";
	/**业务-发送短信url**/
	private final static String SEND_SHORTMSG_CODE_URL = "/message/sendshortmessage";
	/**业务-发送语音验证码url**/
	private final static String SEND_VOICE_CODE_URL = "/message/sendvoicemessage";
	/**CRM-发送短信url**/
	private final static String SEND_SHORTMSG_AD_CODE_URL = "/message/sendshortmessagead";
	/**发送极光推送url**/
	private final static String SEND_JPUSH_CODE_URL = "/message/sendjpush";
	
	/**站内信**/
	public final static int SEND_PCMSG_CODE = 1;
	
	/**短信**/
	public final static int SEND_SHORTMSG_CODE = 2;
	
	/**极光推送**/
	public final static  int SEND_JPUSH_CODE = 3;
	
	
	
	
	public JrmtMessageUtil() {
	}

	
//	/**
//	 * 调用sms-api发送消息
//	 * 注：httpclient post 请求
//	 *
//	 * @param code
//	 * @param jsonStr
//	 * @return
//	 */
//	public static String sendMessage(int code,String jsonStr){
//		switch (code) {
//		//站内信
//		case SEND_PCMSG_CODE:
//			return HttpUtils.postJson(serverURL+SEND_PCMSG_CODE_URL, jsonStr);
//		//短消息
//		case SEND_SHORTMSG_CODE:
//			return HttpUtils.postJson(serverURL+SEND_SHORTMSG_CODE_URL, jsonStr);
//		//极光推送（通知）
//		case SEND_JPUSH_CODE:
//			return HttpUtils.postJson(serverURL+SEND_JPUSH_CODE_URL, jsonStr);
//		default:
//			return "";
//		}
//	}
	
	/**
	 * 发送通知给一个用户(极光推送)
	 * 
	 * @param  title 标题
	 * @param  alert 内容
	 * @param  extras 业务数据  
	 * state值
	 * 合同                             1 
	 * 确认卸货/驳回卸货 	   2  
	 * 支付                             4 
	 * 取消                             5 
	 * 认证                             6 
	 * 推送获得 求货id 跳转意向单页面    7
	 */
	public static void sendJPush(String title, String alert,Map<String, String> extras) {
		try {
			JPushMessageUtil.buildPushObjectAlltagAndAlertWithExtrasAndMessage(title, alert,extras);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	
