package com.swift.jrmt.information.service;

import java.util.List;

import com.swift.jrmt.common.service.BaseService;
import com.swift.jrmt.information.dto.InformationTopDto;
import com.swift.jrmt.information.model.InformationTop;

public interface InformationTopService extends BaseService<InformationTop> {

	/**
	 * 置顶列表查询
	 * @param model
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	List<InformationTopDto> findInformationTopList(InformationTopDto model,
			Integer pageSize, Integer pageNo);
	/**
	 * 更改置顶
	 * @param id 更改的置顶id
	 * @param status  1 默认(取消置顶)， 4置顶
	 * @param channelId 频道id
	 * @param informationId 资讯id
	 */
	
	void updateInformationTop(Long id, Integer status, Long channelId,
			Long informationId,Long userId);

}
