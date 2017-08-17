package com.swift.jrmt.jpush.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.page.PageJrmt;
import com.swift.jrmt.common.service.impl.BaseServiceImpl;
import com.swift.jrmt.common.utils.DateUtils;
import com.swift.jrmt.common.utils.IDGenerator;
import com.swift.jrmt.jpush.dao.PushDao;
import com.swift.jrmt.jpush.model.Push;
import com.swift.jrmt.jpush.service.PushService;

@Service("pushService")
public class PushServiceImpl extends BaseServiceImpl<Push> implements PushService{

	@Resource
	public PushDao pushDao;
	

	@Override
	public IBaseDao<Push> getDao() {
		// TODO Auto-generated method stub
		return pushDao;
	}
	
	/**
	 * 推送列表
	 * @param pageSize  一页多少行
	 * @param pageNum  页数
	 * @return 
	 */
	@Override
	public Map<Integer, List<Push>> pushList(Integer pageNum, Integer pageSize) {
		Map<Integer, List<Push>> map = new HashMap<Integer, List<Push>>();
		// 分页
		PageJrmt pageJrmt = new PageJrmt(pageNum, pageSize);
		pageJrmt.setOrderBy(" order by created desc ");
		List<Push> mediaList = pushDao.queryPushListPage(pageJrmt);
		map.put(pageJrmt.getTotalCount(), mediaList);
		return map;
	}
	
	/**
	 * 添加推送
	 * @param informationId 资讯Id
	 * @param pushTime  预设推送时间
	 */
	@Override
	public void addPush(Long informationId, String pushTime,Long adminUserId,String informationTile) {
		Push push = new Push();
		push.setId(IDGenerator.getUniqueID());
		push.setCreated(new Date());
		push.setDeleted(false);
		push.setUpdated(new Date());
		push.setType(1);
		push.setInformationId(informationId);
		push.setUserId(adminUserId);
		push.setStatus(1);
		try {
			push.setPushTime(DateUtils.parse(pushTime,DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		pushDao.insertSelective(push);
	}

	/**
	 * 编辑推送
	 * @param informationId  资讯Id
	 * @param pushTime    预设推送时间
	 * @param pushId     推送Id
	 */
	@Override
	public void modifyPush(Long informationId, Date pushTime, Long pushId,Long adminUserId) {
		Push push = pushDao.selectByPrimaryKey(pushId);
		push.setInformationId(informationId);
		push.setPushTime(pushTime);
		push.setUpdated(new Date());
		push.setUserId(adminUserId);
		pushDao.updateByPrimaryKeySelective(push);
	}

	/**
	 * 取消推送
	 * @param pushId 推送Id
	 */
	@Override
	public void deletedPush(Long pushId,Long adminUserId) {
		Push push = pushDao.selectByPrimaryKey(pushId);
		push.setUpdated(new Date());
		push.setPubUid(adminUserId);
		push.setDeleted(true);
		push.setStatus(0);
		pushDao.updateByPrimaryKeySelective(push);
	}

	/**
	 * 通过预定发布时间查询推送
	 */
	@Override
	public List<Push> queryPushByPt(){
		return pushDao.queryPushByPt();
	}
}
