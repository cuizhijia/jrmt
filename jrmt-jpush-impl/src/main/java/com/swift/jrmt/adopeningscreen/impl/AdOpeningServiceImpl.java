package com.swift.jrmt.adopeningscreen.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swift.jrmt.adopeningscreen.dao.AdOpeningScreenDao;
import com.swift.jrmt.adopeningscreen.model.AdOpeningScreen;
import com.swift.jrmt.adopeningscreen.service.AdOpeningService;
import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.page.PageJrmt;
import com.swift.jrmt.common.service.impl.BaseServiceImpl;
import com.swift.jrmt.common.utils.DateUtils;
import com.swift.jrmt.common.utils.IDGenerator;

@Service("adOpeningService")
public class AdOpeningServiceImpl extends BaseServiceImpl<AdOpeningScreen> implements AdOpeningService{

	@Resource
	public AdOpeningScreenDao adOpeningScreenDao;
	
	@Override
	public IBaseDao<AdOpeningScreen> getDao() {
		// TODO Auto-generated method stub
		return adOpeningScreenDao;
	}
	
	/**
	 * 开屏广告列表
	 * @param pageNum  页数
	 * @param pageSize 一页多少条
	 * @return
	 */
	@Override
	public Map<Integer, List<AdOpeningScreen>> screenAdList(Integer pageNum, Integer pageSize) {
		Map<Integer, List<AdOpeningScreen>> map = new HashMap<Integer, List<AdOpeningScreen>>();
		// 分页
		PageJrmt pageJrmt = new PageJrmt(pageNum, pageSize);
		pageJrmt.setOrderBy(" order by created desc ");
		List<AdOpeningScreen> mediaList = adOpeningScreenDao.queryAdOpeningScreenListPage(pageJrmt);
		map.put(pageJrmt.getTotalCount(), mediaList);
		return map;
	}  
	
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
	@Override
	public void addScreenAd(String screenadName, Integer type,String picUrl,String adUrl,String displayTimeStart,
			String displayTimeEnd,String adCustomer,String manager,String remark,Long adminUserId) {
		AdOpeningScreen adOS = new AdOpeningScreen();
		adOS.setId(IDGenerator.getUniqueID());
		adOS.setCreated(new Date());
		adOS.setDeleted(false);
		adOS.setUpdated(new Date());
		adOS.setUserId(adminUserId);
		adOS.setStatus(1);
		adOS.setAdType(0);
		adOS.setAdLinkUrl(adUrl);
		adOS.setPicUrl(picUrl);
		adOS.setAdCustomer(adCustomer);
		adOS.setAdManager(manager);
		adOS.setAdRemarks(remark);
		adOS.setAdName(screenadName);
		try {
			adOS.setDisplayTimeStart(DateUtils.parse(displayTimeStart,DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS));
			adOS.setDisplayTimeEnd(DateUtils.parse(displayTimeEnd,DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		adOpeningScreenDao.insertSelective(adOS);
	}

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
	@Override
	public void modifyScreenAd(Long screenadId,String screenadName, Integer type,String picUrl,String adUrl,String displayTimeStart,
			String displayTimeEnd,String adCustomer,String manager,String remark,Long adminUserId) {
		AdOpeningScreen adOS = adOpeningScreenDao.selectByPrimaryKey(screenadId);
		adOS.setUpdated(new Date());
		adOS.setUserId(adminUserId);
		adOS.setStatus(1);
		adOS.setAdType(0);
		adOS.setAdLinkUrl(adUrl);
		adOS.setPicUrl(picUrl);
		adOS.setAdCustomer(adCustomer);
		adOS.setAdManager(manager);
		adOS.setAdRemarks(remark);
		adOS.setAdName(screenadName);
		try {
			adOS.setDisplayTimeStart(DateUtils.parse(displayTimeStart,DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS));
			adOS.setDisplayTimeEnd(DateUtils.parse(displayTimeEnd,DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		adOpeningScreenDao.updateByPrimaryKeySelective(adOS);
	}
	
	/**
	 * 上架/下架
	 * @param screenadId  开屏广告id
	 * @param state       状态:0下架,4已发布
	 * @param adminUserId
	 */
	@Override
	public void upScreenAd(Long screenadId,Integer state,Long adminUserId){
		AdOpeningScreen adOS = adOpeningScreenDao.selectByPrimaryKey(screenadId);
		adOS.setUpdated(new Date());
		adOS.setPubUid(adminUserId);
		adOS.setStatus(state);
		adOpeningScreenDao.updateByPrimaryKeySelective(adOS);
	}
	
	/**
	 * 开屏广告预览
	 * @param screenadId  开屏广告id
	 */
	@Override
	public AdOpeningScreen seeScreenAd(Long screenadId){
		return adOpeningScreenDao.selectByPrimaryKey(screenadId);
	}
	
	@Override
	public void audScreenAd(Long screenadId,Integer state,String rejectReason,Long adminUserId){
		
	    AdOpeningScreen adOS = adOpeningScreenDao.selectByPrimaryKey(screenadId);
	    adOS.setUpdated(new Date());
	    adOS.setAuditorId(adminUserId);
	    if(state == 4){
	    	adOS.setPubUid(adminUserId);
	    }
	    adOS.setStatus(state);
	    adOS.setRejectReason(rejectReason);
	    adOpeningScreenDao.updateByPrimaryKeySelective(adOS);
	}
	
}