package com.swift.jrmt.jpush.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.swift.jrmt.common.service.BaseService;
import com.swift.jrmt.jpush.model.Push;

public interface PushService extends BaseService<Push> {

	/**
	 * 推送列表
	 * @param pageSize  一页多少行
	 * @param pageNum  页数
	 * @return 
	 */
	public Map<Integer, List<Push>> pushList(Integer pageNum,Integer pageSize);
	
	/**
	 * 添加推送
	 * @param informationId 资讯Id
	 * @param pushTime  预设推送时间
	 */
	public void addPush(Long informationId,String pushTime,Long adminUserId,String informationTile);
	
	/**
	 * 编辑推送
	 * @param informationId  资讯Id
	 * @param pushTime    预设推送时间
	 * @param pushId     推送Id
	 */
	public void modifyPush(Long informationId,Date pushTime,Long pushId,Long adminUserId);
	
	/**
	 * 取消推送
	 * @param pushId 推送Id
	 */
	public void deletedPush(Long pushId,Long adminUserId);

	/**
	 * 通过预定发布时间查询推送
	 * @return
	 */
	List<Push> queryPushByPt();



	
}
