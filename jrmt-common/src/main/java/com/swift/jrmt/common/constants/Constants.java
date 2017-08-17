package com.swift.jrmt.common.constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 */
public  class Constants {
	static Properties pps = new Properties();
	static {
		try {//需要挪到配置文件中的文件均可以在配置文件中配置
			String path = Constants.class.getClassLoader().getResource("").getPath();
			pps.load(new FileInputStream(path+"config.properties"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**地址搜索索引库**/
	public static final String INDEX_ADDRESS	= "/data/repository/index/address";

	public static final String ROOT 			= "/data/repository/file";
	
	/**文件服务器**/
	public static   String SERVER_PATH 		= pps.getProperty("FileServerPath");//"http://dev.fileserver.56kuaiche.com";
	
	public static void main(String[] args) {
		System.out.println(SERVER_PATH);
	}
	/**司机端APP相关H5界面访问路径**/
	public static String SERVER_PATH_APP_CMS = pps.getProperty("Server_Path_App_CMS");
	
	/**易宝网银支付回调地址**/
	public static final String YEEPAY_AFFIRMRECHARGE = pps.getProperty("yeepay_affirmrecharge"); //"http://test.pay.56kuaiche.com/yeepay/affirmRecharge";
	/** kuaiche-finance服务的地址**/
    public static final String FINANCE_SERVER_URL = pps.getProperty("finance_server_url");
    /** kuaiche-pay-api服务的地址**/
    public static final String PAY_API_SERVER_URL = pps.getProperty("finance_pay_api_url");
//	public static final String YEEPAY_AFFIRMRECHARGE = "http://dev.56kuaiche.com/yeepay/affirmRecharge";web端
	/**易宝一键支付回调地址**/
	public static final String YEEPAY_DECRYPTCALLBACKDATA = pps.getProperty("yeepay_decryptcallbackdata"); //"http://test.pay.56kuaiche.com/yeepay/decryptCallbackData";
	
	/** 易煤意向单和运单回调地址 */
	public static final String YM_CONTRACT_CALLBACK = pps.getProperty("YM_CONTRACT_CALLBACK");
	/** 易煤特殊字段 */
	public static final String YM_USER_SECURITY = pps.getProperty("YM_USER_SECURITY");
	/** 易煤意向单匹配后取消接口地址 */
	public static final String YM_CONTRACT_CANCEL = pps.getProperty("YM_CONTRACT_CANCEL");
	
	/** 今日煤炭服务的地址 **/
    public static final String JRMT_SERVER_URL = pps.getProperty("JRMT_SERVER_URL");
    
	/**用户文件空间**/
	public static final int MAX_SPACE 			= 10000;
	
	/**Token失效时间（天）**/
	public static final int AUTH_TOKEN_EXPIRE 	= 100;//天
	
	/******************************* 消息推送文案 *******************************/
	//安信捷
	//public static final String MSG_56KUAICHE_TAG = "【56快车】";
	//一拓
	public static final String MSG_56KUAICHE_TAG = "回TD退订。【56快车】";
	public static final String MSG_TITLE = "今日煤炭消息通知";
	//---------------------------------物流端-------------------------------
	//认证通过
	public static final String MSG_TO_SHIPPER_AUTH_PASS = "恭喜您，您的公司认证已经通过。";
	//认证不通过
	public static final String MSG_TO_SHIPPER_AUTH_NOPASS = "非常抱歉，您提交的认证信息未通过审核，原因为：";
//	//绑定成功
//	public static final String MSG_TO_SHIPPER_BOUND_SUCCESS = "恭喜您，您已经成为丰矿物流的成员";
//	//改变权限
//	public static final String MSG_TO_SHIPPER_CHANGE_AUTH = "您已经拥有丰矿物流";
//	//取消所有权限
//	public static final String MSG_TO_SHIPPER_CANCELALL_AUTH = "您已经被丰矿物流管理员取消了所有权限";
//	//解除绑定关系
//	public static final String MSG_TO_SHIPPER_RELEASE_BOUND = "您已经和丰矿物流解除绑定关系";
	//订单被抢
	public static final String MSG_TO_SHIPPER_GRAB_ORDER = "您的订单已被抢单";
	//装车
	public static final String MSG_TO_SHIPPER_LOAD = "您的订单已装车";
	//卸货
	public static final String MSG_TO_SHIPPER_UNLOAD = "您的订单已卸货，请尽快确认";
	//确认卸货,给货主端支付权限人提醒
	public static final String MSG_TO_SHIPPER_CONFIRM_UNLOAD = "您的订单已确认卸货，请尽快支付";
	//取消订单
	public static final String MSG_TO_SHIPPER_CANCEL_ORDER= "您的订单已被取消";
	//充值成功
	public static final String MSG_TO_SHIPPER_RECHARGE_SUCCESS = "恭喜您，充值成功，充值金额";
	//充值手机号
	public static final String MSG_TO_SHIPPER_RESET_MOBILE = "您已重置手机号为";
	//反馈回复
	public static final String MSG_TO_SHIPPER_FEEDBACK_END = "您的反馈已经处理完毕，处理结果：";
	
	//---------------------------------司机端-------------------------------
	//认证通过
	public static final String MSG_TO_DRIVER_AUTH_PASS = "恭喜您，您的认证已经通过。";
	//认证不通过
	public static final String MSG_TO_DRIVER_AUTH_NOPASS = "非常抱歉，您提交的认证信息未通过审核，原因为：";
	//已确认卸货,给司机提醒
	public static final String MSG_TO_DRIVER_CONFIRM_UNLOAD = "您的订单已确认卸货，等待付款";
	//已确认付款
	public static final String MSG_TO_DRIVER_PAY = "您的订单已付款，请注意查收";
	//驳回卸货
	public static final String MSG_TO_DRIVER_BACK_LOAD = "您的订单已被驳回卸货，原因：";
	//取消订单
	public static final String MSG_TO_DRIVER_CANCEL_ORDER = "您的订单已被取消，原因：";
	//替装车
	public static final String MSG_TO_DRIVER_LOADBYSHIPPER = "确认装车";
	//替卸货
	public static final String MSG_TO_DRIVER_UNLOADBYSHIPPER = "上传磅单，请及时核对信息";
	//提现申请
	public static final String MSG_TO_DRIVER_GETCASH_REQUEST = "您的提现申请已提交，最晚下个工作日16点前到账！";
	//提现失败
	public static final String MSG_TO_DRIVER_GETCASH_FAILURE = "元已退回到您的钱包，请重新绑定该卡（确保姓名和卡号正确）后重新提现。";
	//提现到账
	public static final String MSG_TO_DRIVER_GETCASH_TOACCOUNT = "您的提现金额已到账，到账金额";
	//投诉处理完成
	public static final String MSG_TO_DRIVER_COMPLAINT_END = "您的投诉已经处理完毕，处理结果：";
	
	/**
	 * 标准模板
	 */
	public static final String TEMPLATE_STANDARD = "template/standard";
	/**
	 * 底部模板
	 */
	public static final String TEMPLATE_FOOTER = "template/footer";
	
	/**
	 * 个人中心模板
	 */
	public static final String TEMPLATE_PERSONAL = "template/personal";
	
	
	/**登录用户存入Session**/
	public static final String LOGIN__USER_SCOPE = "__login_user__";
	
	/**登录用户存入Session**/
	public static final String LOGIN_USER_REMEMBER_COOKIE_KEY = "__r_u__";
	
	/**系统管理员ID**/
	public static final Long SYS_ADMIN = 0L;
	
	public static final String REGIST_USER_SCOPE="_regist_user";
	public static final int PAGE_SIZE = 10;
	

	
	
	/**
	 * rsa public key  private key
	 */
	public static  String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuzMtCRpmx7VVdx9S9ILxZ1rmq0QPfmn9cWVjeV43gcazjTEad8KhUcw6q3Nx2+2fj/s6irsakVDtHivQq/wIGa5uPOlQ2xclHF+dKinJ3iTbFiJtjBmmM7jKev3Wl6nXW0tJJZSsVf7lFxeb1xJ+c1v3y7W1uooceTxXtmMzF/QIDAQAB";
	public static String privateKeyStr = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAK7My0JGmbHtVV3H1L0gvFnWuarRA9+af1xZWN5XjeBxrONMRp3wqFRzDqrc3Hb7Z+P+zqKuxqRUO0eK9Cr/AgZrm486VDbFyUcX50qKcneJNsWIm2MGaYzuMp6/daXqddbS0kllKxV/uUXF5vXEn5zW/fLtbW6ihx5PFe2YzMX9AgMBAAECgYAexB72cFS28232DqkMDMn6Cggt35IN9jR6faNdpw1qtrdWXmODgwstqf6n5i3Lp15Wy8905MxDuiug4P0u4Clrywk1EHyVOPTAhtOB4DURuZmQG+qtpcHd3ULME0EtyP1Tgw1Obor5H431gSAYnUZvXt5zCZxo1L+H1imU/p4/5QJBAOmoQA+DxS4adqdQIyNCTTPYsdj8vnikb6w3BXSAb/sYEESxNlKjWb7uMVZZVofUP/sMTq8knSSXTtsBaqnK4VcCQQC/g8NsNB7SVyPOqs03OsZ4iZq+cxZDQW1wfD78rSXoX3kuTW2f3tmArMjdmLl2NRl+5CwcB7zZQRdu5liKONrLAkBB1+vVc2iJSWPOWkLMmM5wocvcPbze+leNzCN5ZQgpVeqfsOCCAQ41Q6IblylPNXyiHiG+MS8nNIxAIvIPzuAZAkBvpyIHs1tExgjaNDnwORP8sjzFS9Nu4nYrXkRai5fOIdtEY1/GFOY5XoK8ro1xnQOANwaAUQKIpqpCvYhhDUSBAkBe7REnVAXzZ33XeMEO0x8K0LCpHQUnaQKHuH1BpZaoB8OoCVKtYxeZREwk7YAddeB1oIUuKTlGTFVhbF0JyUfh";

	
	/**
	 * 百度天气查询key
	 */
	public static String WEATHER_KEY_BAIDU = "ef0d846449600abcf6551e5d8e24e591";
	public static String WEATHER_KEY_CHANNEL_BAIDU = "baidu";
	public static String WEATHER_KEY_CHANNEL_JUHE = "juhe";
	//-----------------------------------消息推送，获取资源ID---------------------------------
	/**发货资源ID,注意：web和app都有一个ID，但是对应的是同一个人，所以查询时查询物流APP即可**/
	public static String SHIPPER_PUBLISH_CARGO_RESOURCE = pps.getProperty("SHIPPER_PUBLISH_CARGO_RESOURCE");
	/**确认卸货资源ID**/
	public static String SHIPPER_CONFIRM_UNLOAD_RESOURCE = pps.getProperty("SHIPPER_CONFIRM_UNLOAD_RESOURCE");
	/**确认支付资源ID**/
	public static String SHIPPER_CONFIRM_PAY_RESOURCE = pps.getProperty("SHIPPER_CONFIRM_PAY_RESOURCE");
	//----------------------------------短信通道---------------------------------
	/**短信通道 0:33得久 1:安信捷**/
	public static final String SHORTMESSAGE_CHANNEL = pps.getProperty("SHORTMESSAGE_CHANNEL");
	
	//----------------------------------短信收费-物流端-我的车辆-批量发短信----------------------------------
	public static final String MYCAR_SMS_PRICE = pps.getProperty("MYCAR_SMS_PRICE");
	
	/**
	 * 56快车，司机端APP，android端，v2.0.0接口版本号
	 */
	public static final String KAUICHE_APP_DRIVER_ANDROID_APIVERSION_V200 = "20951";
	
	/**
	 * 56快车，司机端APP，android端，v2.0.1接口版本号
	 */
	public static final String KAUICHE_APP_DRIVER_ANDROID_APIVERSION_V201 = "V2.0.1";//从2.0.1开始启用Vx.x.x形式
	
	/**
	 * 56快车，司机端APP，android端，v2.0.2接口版本号
	 */
	public static final String KAUICHE_APP_DRIVER_ANDROID_APIVERSION_V202 = "V2.0.2";
	/**
	 * 56快车，物流端APP，android端，v2.0.2接口版本号
	 */
	public static final String KAUICHE_APP_SHIPPER_ANDROID_APIVERSION_V202 = "V2.0.2";
	/**
	 * 56快车，物流端APP，ios端，v2.0.2接口版本号
	 */
	public static final String KAUICHE_APP_SHIPPER_IOS_APIVERSION_V202 = "V2.0.2";
	
	//前后端分离，物流端APP、WEB统一接口，入参fromType：app端
	public static final String KAUICHE_APP_SHIPPER_FROM_TYPE_APP = "app";
	
	//前后端分离，物流端APP、WEB统一接口，入参fromType：web端
	public static final String KAUICHE_APP_SHIPPER_FROM_TYPE_WEB = "web";
	
	
}
