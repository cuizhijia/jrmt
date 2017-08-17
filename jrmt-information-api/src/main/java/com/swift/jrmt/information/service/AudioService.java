package com.swift.jrmt.information.service;

import java.util.List;

import com.swift.jrmt.common.service.BaseService;
import com.swift.jrmt.information.dto.AudioDto;
import com.swift.jrmt.information.model.Audio;

public interface AudioService extends BaseService<Audio> {

	List<AudioDto> findAudioList(AudioDto model, Integer pageSize,
			Integer pageNo);
	
	/**
	 * 添加电台
	 * @param title 电台标题
	 * @param userid 创建人id
	 * @param pic_url 缩略图地址
	 * @param audio_url 电台保存地址
	 * @param remark 音频简介
	 */
	void saveAudio(String title, Long userid, String pic_url, String audio_url,
			String remark);
	
	/**
	 * 更新电台信息
	 * @param model
	 * @param markUpdate //markUpdate 1、编辑 2、冻结 、下架3、解冻、上架4、审核
	 * @param userId 操作人id
	 */
	void updateAudio(Audio model, Integer markUpdate,Long userId);

	/**
	 * 查询电台
	 * @param audioId 电台id
	 * @return
	 */
	Audio findAudioModelById(Long audioId);

}
