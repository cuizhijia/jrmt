package com.swift.jrmt.information.constants;

import com.swift.jrmt.common.constants.StatusCode;

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
public class InformationStatusCode extends StatusCode {

	protected InformationStatusCode(Integer code, String message) {
		super(code, message);
	}

	public static InformationStatusCode admin_request_param_error = new InformationStatusCode (-99999,"请求参数非法");
	public static InformationStatusCode information_file_upload_success = new InformationStatusCode (10014,"文件上传成功");
	public static InformationStatusCode information_file_upload_error = new InformationStatusCode (-10014,"文件上传失败");
	public static InformationStatusCode information_check_over_success = new InformationStatusCode (10015,"审核操作成功");
	public static InformationStatusCode information_check_over_error = new InformationStatusCode (-10015,"审核操作成功");
	public static InformationStatusCode information_up_shelf_success = new InformationStatusCode (10016,"上架成功");
	public static InformationStatusCode information_up_shelf_error = new InformationStatusCode (-10016,"上架失败");
	public static InformationStatusCode information_down_shelf_success = new InformationStatusCode (10017,"下架成功");
	public static InformationStatusCode information_down_shelf_error = new InformationStatusCode (-10017,"下架失败");	
	
	/*********************************************资讯 start 10000~11000*******************************/
	public static InformationStatusCode information_tag_save_success = new InformationStatusCode (10000,"添加标签成功");
	public static InformationStatusCode information_tag_save_error = new InformationStatusCode (-10000,"添加标签失败");
	public static InformationStatusCode information_tag_update_success = new InformationStatusCode (10001,"修改标签成功");
	public static InformationStatusCode information_tag_update_error = new InformationStatusCode (-10001,"修改标签失败");
	public static InformationStatusCode information_tag_findList_success = new InformationStatusCode (10002,"标签列表查询成功");
	public static InformationStatusCode information_tag_findList_error = new InformationStatusCode (-10002,"标签列表查询失败");
	public static InformationStatusCode information_keyWord_save_success = new InformationStatusCode (10003,"添加关键词成功");
	public static InformationStatusCode information_keyWord_save_error = new InformationStatusCode (-10003,"添加关键词失败");
	public static InformationStatusCode information_keyWord_update_success = new InformationStatusCode (10004,"修改关键词成功");
	public static InformationStatusCode information_keyWord_update_error = new InformationStatusCode (-10004,"修改关键词失败");
	public static InformationStatusCode information_keyWord_findList_success = new InformationStatusCode (10005,"关键词列表查询成功");
	public static InformationStatusCode information_keyWord_findList_error = new InformationStatusCode (-10005,"关键词列表查询失败");
	public static InformationStatusCode information_channel_save_success = new InformationStatusCode (10006,"添加频道成功");
	public static InformationStatusCode information_channel_save_error = new InformationStatusCode (-10006,"添加频道失败");
	public static InformationStatusCode information_channel_update_success = new InformationStatusCode (10007,"修改频道成功");
	public static InformationStatusCode information_channel_update_error = new InformationStatusCode (-10007,"修改频道失败");
	public static InformationStatusCode information_channel_findList_success = new InformationStatusCode (10008,"频道列表查询成功");
	public static InformationStatusCode information_channel_findList_error = new InformationStatusCode (-10008,"频道列表查询失败");
	public static InformationStatusCode information_channel_frozen_success = new InformationStatusCode (10009,"频道冻结成功");
	public static InformationStatusCode information_channel_frozen_error = new InformationStatusCode (-10009,"频道冻结失败");
	public static InformationStatusCode information_channel_unfrozen_success = new InformationStatusCode (10010,"频道解冻成功");
	public static InformationStatusCode information_channel_unfrozen_error = new InformationStatusCode (-10010,"频道解冻失败");
	public static InformationStatusCode information_audio_save_success = new InformationStatusCode (10011,"添加电台成功");
	public static InformationStatusCode information_audio_save_error = new InformationStatusCode (-10011,"添加电台失败");
	public static InformationStatusCode information_audio_update_success = new InformationStatusCode (10012,"修改电台成功");
	public static InformationStatusCode information_audio_update_error = new InformationStatusCode (-10012,"修改电台失败");
	public static InformationStatusCode information_audio_findList_success = new InformationStatusCode (10013,"电台列表查询成功");
	public static InformationStatusCode information_audio_findList_error = new InformationStatusCode (-10013,"电台列表查询失败");
	public static InformationStatusCode information_new_save_success = new InformationStatusCode (10018,"添加资讯成功");
	public static InformationStatusCode information_new_save_error = new InformationStatusCode (-10018,"添加资讯失败");
	public static InformationStatusCode information_find_list_success = new InformationStatusCode (10019,"资讯列表查询成功");
	public static InformationStatusCode information_find_list_error = new InformationStatusCode (-10019,"资讯列表查询失败");
	public static InformationStatusCode information_update_data_success = new InformationStatusCode (10020,"资讯编辑成功");
	public static InformationStatusCode information_update_data_error = new InformationStatusCode (-10020,"资讯编辑失败");
	public static InformationStatusCode information_update_top_success = new InformationStatusCode (10021,"更改置顶成功");
	public static InformationStatusCode information_update_top_error = new InformationStatusCode (-10021,"更改置顶失败");
	public static InformationStatusCode information_top_list_success = new InformationStatusCode (10022,"置顶列表查询成功");
	public static InformationStatusCode information_top_list_error = new InformationStatusCode (-10022,"置顶列表查询失败");
	public static InformationStatusCode information_update_untop_success = new InformationStatusCode (10023,"取消置顶成功");
	public static InformationStatusCode information_update_untop_error = new InformationStatusCode (-10023,"取消置顶失败");
	public static InformationStatusCode information_find_model_success = new InformationStatusCode (10024,"资讯信息查询成功");
	public static InformationStatusCode information_find_model_error = new InformationStatusCode (-10024,"资讯信息查询失败");
	public static InformationStatusCode information_find_model_content_success = new InformationStatusCode (10025,"资讯信息内容查询成功");
	public static InformationStatusCode information_find_model_content_error = new InformationStatusCode (-10025,"资讯信息内容查询失败");
	
	/*********************************************资讯 end *********************************************/
}
