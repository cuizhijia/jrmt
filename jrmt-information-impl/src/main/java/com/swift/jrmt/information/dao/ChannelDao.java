package com.swift.jrmt.information.dao;

import java.util.List;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.information.dto.ChannelDto;
import com.swift.jrmt.information.model.Channel;

/**
 * 日期  : 2016-08-01
 * 作者  : YJB
 * 项目  : Channel
 * 功能  :  ChannelDao数据库操作接口类
 * 
 **/

public interface ChannelDao  extends IBaseDao<Channel>{

	List<ChannelDto> findChannelListPage(ChannelDto model);


}