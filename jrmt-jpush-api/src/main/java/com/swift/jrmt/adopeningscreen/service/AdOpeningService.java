package com.swift.jrmt.adopeningscreen.service;

import java.util.List;
import java.util.Map;

import com.swift.jrmt.adopeningscreen.model.AdOpeningScreen;
import com.swift.jrmt.common.service.BaseService;

public interface AdOpeningService extends BaseService<AdOpeningScreen> {

	/**
	 * 开屏广告列表
	 * @param pageNum  页数
	 * @param pageSize 一页多少条
	 * @return
	 */
	Map<Integer, List<AdOpeningScreen>> screenAdList(Integer pageNum,Integer pageSize);

	/**
	 * 添加开屏广告
	 * @param screenadName  开屏广告名称
	 * @param type        开屏广告类别:0:普通,1:活动
	 * @param picUrl      开屏广告图片地址
	 * @param adUrl       开屏广告链接
	 * @param displayTimeStart  开屏广告展示开始时间
	 * @param displayTimeEnd    开屏广告展示结束时间
	 * @param adCustomer        开屏广告客户
	 * @param manager           开屏广告负责人
	 * @param remark            开屏广告备注
	 */
	void addScreenAd(String screenadName, Integer type, String picUrl,
			String adUrl, String displayTimeStart, String displayTimeEnd,
			String adCustomer, String manager, String remark,Long adminUserId);

	/**
	 * 编辑开屏广告
	 * @param screenadId    开屏广告id
	 * @param screenadName  开屏广告名称
	 * @param type        开屏广告类别:0:普通,1:活动
	 * @param picUrl      开屏广告图片地址
	 * @param adUrl       开屏广告链接
	 * @param displayTimeStart  开屏广告展示开始时间
	 * @param displayTimeEnd    开屏广告展示结束时间
	 * @param adCustomer        开屏广告客户
	 * @param manager           开屏广告负责人
	 * @param remark            开屏广告备注
	 */
	void modifyScreenAd(Long screenadId, String screenadName, Integer type,
			String picUrl, String adUrl, String displayTimeStart,
			String displayTimeEnd, String adCustomer, String manager,
			String remark, Long adminUserId);

	/**
	 * 上架/下架
	 * @param screenadId  开屏广告id
	 * @param state       状态:0下架,4已发布
	 * @param adminUserId
	 */
	void upScreenAd(Long screenadId, Integer state,Long adminUserId);

	/**
	 * 开屏广告预览
	 * @param screenadId  开屏广告id
	 */
	AdOpeningScreen seeScreenAd(Long screenadId);

	/**
	 * 开屏广告审核
	 * @param screenadId 开屏广告id
	 * @param state   状态:2审核不通过，3审核通过待发布，4已发布
	 * @param rejectReason  驳回原因
	 * @param adminUserId
	 */
	void audScreenAd(Long screenadId, Integer state, String rejectReason,Long adminUserId);

}
