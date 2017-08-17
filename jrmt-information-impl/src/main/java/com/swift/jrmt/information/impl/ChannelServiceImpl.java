package com.swift.jrmt.information.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.service.impl.BaseServiceImpl;
import com.swift.jrmt.common.utils.IDGenerator;
import com.swift.jrmt.information.dao.ChannelDao;
import com.swift.jrmt.information.dao.InformationTopDao;
import com.swift.jrmt.information.dto.ChannelDto;
import com.swift.jrmt.information.dto.KeyWordDto;
import com.swift.jrmt.information.model.Channel;
import com.swift.jrmt.information.model.InformationTop;
import com.swift.jrmt.information.service.ChannelService;
@Service("channelService")
public class ChannelServiceImpl  extends BaseServiceImpl<Channel> implements ChannelService{

	@Resource
	public ChannelDao channelDao;
	@Resource
	public InformationTopDao informationTopDao;
	
	@Override
	public IBaseDao<Channel> getDao() {
		return channelDao;
	}

	@Override
	public void saveNewChannel(String title, Long userId, Integer rank) {
		Channel channel = new Channel();
		channel.setId(IDGenerator.getUniqueID());
		channel.setCreated(new Date());
		channel.setDeleted(false);
		channel.setUpdated(new Date());
		channel.setTitle(title);
		channel.setRank(rank);
		channel.setUserId(userId);
		channel.setStatus(4);
		channel.setPubUid(userId);
		channel.setPubTime(new Date());
		channelDao.insertSelective(channel);
		
		/*往资讯置顶列表添加数据*/
		InformationTop inTop = new InformationTop();
		inTop.setId(IDGenerator.getUniqueID());
		inTop.setCreated(new Date());
		inTop.setDeleted(false);
		inTop.setUpdated(new Date());
		inTop.setChannelId(channel.getId());
		inTop.setUserId(userId);
		inTop.setStatus(1);
		informationTopDao.insertSelective(inTop);
	}

	@Override
	public List<ChannelDto> findChannelList(ChannelDto channelDto, Integer pageSize,Integer pageNo) {
		List<ChannelDto> channelList = new ArrayList<ChannelDto>();
		channelDto.setPageNo(pageNo);
		channelDto.setPageSize(pageSize);
		channelList = channelDao.findChannelListPage(channelDto);
		System.out.println(channelDto.getTotalCount());
		channelList.add(channelDto);
		return channelList;
	}

}
