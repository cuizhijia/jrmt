package com.swift.jrmt.information.service;

import java.util.List;

import com.swift.jrmt.common.service.BaseService;
import com.swift.jrmt.information.dto.InformationDto;
import com.swift.jrmt.information.model.Information;

public interface InformationService extends BaseService<Information> {

	
	/**
	 * 添加资讯
	 * @param information
	 * @param userId 操作人
	 */
	void saveNewInformation(InformationDto information, Long userId);

	List<InformationDto> findInformationList(InformationDto model,
			Integer pageSize, Integer pageNo);

	/**
	 * 编辑资讯
	 * @param model
	 * @param markUpdate
	 * @param userId
	 */
	void updateInformation(Information model, Integer markUpdate, Long userId);
	
	/**
	 * 查询资讯信息model
	 * @param informationId 资讯信息id
	 * @return
	 */
	InformationDto findInformationModelById(Long informationId);

}
