package com.swift.jrmt.information.service;

import java.util.List;

import com.swift.jrmt.common.service.BaseService;
import com.swift.jrmt.information.dto.ChannelDto;
import com.swift.jrmt.information.model.Channel;

public interface ChannelService extends BaseService<Channel> {

	/**
	 * 添加频道
	 * @param title 频道名称
	 * @param userId 操作人
	 * @param rank 权重
	 */
	void saveNewChannel(String title, Long userId, Integer rank);

	/**
	 * 查询频道列表
	 * @param model
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	List<ChannelDto> findChannelList(ChannelDto model, Integer pageSize,
			Integer pageNo);

}
