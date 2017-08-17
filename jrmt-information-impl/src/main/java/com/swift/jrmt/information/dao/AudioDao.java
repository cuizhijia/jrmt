package com.swift.jrmt.information.dao;

import java.util.List;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.information.dto.AudioDto;
import com.swift.jrmt.information.model.Audio;

/**
 * 日期  : 2016-08-04
 * 作者  : YJB
 * 项目  : audio
 * 功能  :  AudioDao数据库操作接口类
 * 
 **/
public interface AudioDao extends IBaseDao<Audio>{

	List<AudioDto> findAudioListPage(AudioDto model);

}
