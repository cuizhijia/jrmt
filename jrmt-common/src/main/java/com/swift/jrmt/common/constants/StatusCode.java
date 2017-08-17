package com.swift.jrmt.common.constants;

import java.io.Serializable;

/**
 * 说明:全站状态代码<br>
 * 约定：<br>
 * 1.name使用小写，便于直观<br>
 * 2.所属模块消息，请使用模块名称开始，如car_xx、order_xxx
 * 3.所有错误代码均为负数<br>
 * 4.所有正确代码均为正数<br>
 * 5.状态代码*_*_*_*_* 第一个*指代模块名,第二个*指代模块中具体的操作，最后一个*指代错误或者是成功，中间的*指代的是操作错误的原因
 * 2015年4月24日 下午3:23:56
 */
public class StatusCode implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7059712931676997250L;
	public static StatusCode success = new StatusCode(Integer.MAX_VALUE, 				"成功");
	public static StatusCode failed = new StatusCode(Integer.MIN_VALUE, 				"失败");

	//------------------------- 通用性错误  -------------------------/
		public static StatusCode common_error_parameter_error		= new StatusCode(-1, 	"传递的参数为空");
		public static StatusCode common_error_no_data_error			= new StatusCode(-2, 	"无有效数据");
		public static StatusCode  common_error_has_data_error			= new StatusCode(-3, 	"数据已存在");
		public static StatusCode common_error_name_or_password		= new StatusCode(-4, 	"用户名或密码错误");
		public static StatusCode common_error_old_password		    = new StatusCode(-5, 	"原密码错误");
		public static StatusCode common_error_unknown_error			= new StatusCode(-8,	"网络不给力，请检查您的网络设置");//服务器内部错误
		public static StatusCode common_error_illegal_error			= new StatusCode(-9,	"非法操作");
		public static StatusCode common_error_authentication_failed	= new StatusCode(-30,	"认证失败");
		public static StatusCode common_error_net_connection_errror	= new StatusCode(-40, 	"网络错误");
		public static StatusCode common_error_db_connection_errror	= new StatusCode(-50, 	"数据库连接错误");
		public static StatusCode common_error_captcha_not_match		= new StatusCode(-80,	"验证码错误");
		public static StatusCode common_error_sms_often				= new StatusCode(-84,	"获取验证码太频繁");
		public static StatusCode common_error_date_empty				= new StatusCode(-87,	"日期为空");
		public static StatusCode common_info_sms_sned_success		= new StatusCode(81,	"发送短信成功");
		public static StatusCode common_info_smscode_sned_success	= new StatusCode(811,	"发送短信验证码成功");
		public static StatusCode common_info_voicecode_send_success	= new StatusCode(81111,	"发送语音验证码成功");
		public static StatusCode common_error_sms_sned_failed		= new StatusCode(-82,	"发送短信失败");
		public static StatusCode common_error_smscode_sned_failed	= new StatusCode(-822,	"发送短信验证码失败");
		public static StatusCode common_send_voicecode_failed		= new StatusCode(-8222,	"发送语音验证码失败");
		public static StatusCode common_message_del_sucess			= new StatusCode(83,	"删除成功");
		public static StatusCode common_message_del_error			= new StatusCode(-83,	"删除失败");
		public static StatusCode common_error_sms_mobile_black_error		= new StatusCode(-85,	"当前手机号已添加进黑名单，请联系客服");
		public static StatusCode common_error_sms_mobile_fast_error		= new StatusCode(-86,	"获取验证码过于频繁，请稍后再试");
		public static StatusCode common_error_sms_fast_picture_error  = new StatusCode(8111,	"获取验证码过于频繁,弹出图片验证码");
		public static StatusCode common_fromtype_notsupport_error			= new StatusCode(-8666,	"入参：from_type的值不支持");
		public static StatusCode common_message_error				= new StatusCode(-8777, "手机号不合法");
		
		//公有
		public static StatusCode person_notexist_error						= new StatusCode(-441,	"用户不存在");
		public static StatusCode person_mobile_notexist_error				= new StatusCode(-591,	"手机号不存在");
		public static StatusCode person_mobile_repeated_error					= new StatusCode(-592,"联系人手机号已存在");
		public static StatusCode person_login_error_lock					    = new StatusCode(-593,	"该用户已被锁定");
		public static StatusCode person_login_overtime_error				 	= new StatusCode(-506,	"登录超时");
		
		//变量
		public static StatusCode person_param_identifycode_invalid_error		= new StatusCode(-410, 	"验证码无效");
		public static StatusCode person_param_identifycode_invalid_success_sx= new StatusCode(4111, 	"图形验证码校验正确");
		public static StatusCode person_param_identifycode_invalid_success_send_sms= new StatusCode(4112, "图形验证码校验正确,短信已发送");
		public static StatusCode person_param_identifycode_invalid_error_sx	= new StatusCode(-411, 	"图形验证码错误，请重新输入");
		public static StatusCode person_param_password_empty_error			= new StatusCode(-415, 	"密码为空");
		public static StatusCode person_param_password_safelevel_empty_error = new StatusCode(-416, 	"密码安全级别参数为空");
		public static StatusCode person_param_password_invalid_error  		= new StatusCode(-417, 	"密码无效");
		public static StatusCode person_param_mobile_empty_error		    	= new StatusCode(-401, 	"手机号为空");
		public static StatusCode person_param_nickname_empty_error			= new StatusCode(-402, 	"昵称为空");
		public static StatusCode person_role_resouces_error					= new StatusCode(-403, 	"该用户没有权限");
		public static StatusCode person_param_role_empty_error				= new StatusCode(-408, 	"角色为空");
		public static StatusCode person_param_no_userid_error			  	= new StatusCode(-535,	"未传递用户ID");//?
		public static StatusCode person_param_no_access_token_error	  		= new StatusCode(-536,	"未传递access_token令牌");//?
		public static StatusCode person_param_no_refresh_token_error	  		= new StatusCode(-537,	"未传递refresh_token令牌");//?
		
		//移动客户端认证
		public static StatusCode person_app_auth_success					  	= new StatusCode(530,	"授权通过");//?
		public static StatusCode person_app_auth_expire_access_token_error 	= new StatusCode(-540,	"access_token令牌已过期");//?
		public static StatusCode person_app_auth_access_token_invalid_error	= new StatusCode(-541,	"您的账号已在其他设备登录");//access_token无效
		public static StatusCode person_app_auth_access_token_forgery_error	= new StatusCode(-542,	"access_token被篡改");
		
		//修改密码
		public static StatusCode person_change_password_success		      	= new StatusCode(580,	"密码修改成功");
		public static StatusCode person_change_password_old_error	      	= new StatusCode(-581,	"旧密码错误");
		public static StatusCode person_change_password_failed		      	= new StatusCode(-589,	"修改密码操作失败 ");
		
		//更新
		public static StatusCode person_update_avatar_success				= new StatusCode(570, 	"更新头像成功");
		public static StatusCode person_change_realname_success      	 	= new StatusCode(571, 	"修改用户名成功");	
		
	private Integer code;
	private String message;
	
	protected StatusCode(Integer code, String message){
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode(){return this.code;}
	public String getMessage(){return this.message;}
	public void setMessage(String message){this.message = message;}
	
	@Override
	public String toString(){
		return "{\"code\":" + getCode() + ",\"message\":\"" + getMessage() + "\"}";
	}
	
}