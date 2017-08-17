package com.swift.jrmt.constants;

import com.swift.jrmt.common.constants.StatusCode;

/**
 * 说明:全站状态代码<br>
 * 约定：<br>
 * 1.name使用小写，便于直观<br>
 * 2.所属模块消息，请使用模块名称开始，如car_xx、order_xxx 3.所有错误代码均为负数<br>
 * 4.所有正确代码均为正数<br>
 * 5.状态代码*_*_*_*_* 第一个*指代模块名,第二个*指代模块中具体的操作，最后一个*指代错误或者是成功，中间的*指代的是操作错误的原因
 * 2015年4月24日 下午3:23:56
 */
public class JpushStatusCode extends StatusCode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5950601961768119900L;

	protected JpushStatusCode(Integer code, String message) {
		super(code, message);
	}

	public static JpushStatusCode admin_request_param_error = new JpushStatusCode(
			-99999, "请求参数非法");

	/********************************************* 推送 start 10000~11000 *******************************/
	public static JpushStatusCode information_id_null = new JpushStatusCode(-10000, "资讯id为空");
	public static JpushStatusCode push_time_null = new JpushStatusCode(-10001, "预设推送时间为空");
	public static JpushStatusCode push_id_null = new JpushStatusCode(-10002, "推送id为空");

			
	


	/********************************************* 推送 end *********************************************/
	
	
	/***********************************************评论管理****************************************************/
	public static JpushStatusCode comment_save_fail = new JpushStatusCode(-40000,"评论失败");
	public static JpushStatusCode comment_save_prop_null_error = new JpushStatusCode(-40001, "评论属性为空");
	public static JpushStatusCode comment_save_unknow_error = new JpushStatusCode(-40002, "服务器发生未知错误");
	public static JpushStatusCode comment_getList_prop_null_error = new JpushStatusCode(-40003,"获取评论列表时属性为空");
	
	public static JpushStatusCode comment_save_succ = new JpushStatusCode(40000,"发送成功");

	
	
	/***************************************************************************************************/
}
